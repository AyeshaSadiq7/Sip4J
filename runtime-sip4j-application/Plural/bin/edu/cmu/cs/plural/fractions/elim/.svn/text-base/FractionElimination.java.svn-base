/**
 * Copyright (C) 2007, 2008 Carnegie Mellon University and others.
 *
 * This file is part of Plural.
 *
 * Plural is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * Plural is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Plural; if not, see <http://www.gnu.org/licenses>.
 *
 * Linking Plural statically or dynamically with other modules is
 * making a combined work based on Plural. Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * In addition, as a special exception, the copyright holders of Plural
 * give you permission to combine Plural with free software programs or
 * libraries that are released under the GNU LGPL and with code
 * included in the standard release of Eclipse under the Eclipse Public
 * License (or modified versions of such code, with unchanged license).
 * You may copy and distribute such a system following the terms of the
 * GNU GPL for Plural and the licenses of the other code concerned.
 *
 * Note that people who make modified versions of Plural are not
 * obligated to grant this special exception for their modified
 * versions; it is their choice whether to do so. The GNU General
 * Public License gives permission to release a modified version
 * without this exception; this exception also makes it possible to
 * release a modified version which carries forward this exception.
 */
package edu.cmu.cs.plural.fractions.elim;

import java.util.HashSet;
import java.util.Set;

import edu.cmu.cs.plural.fractions.Fraction;
import edu.cmu.cs.plural.fractions.FractionConstraint;
import edu.cmu.cs.plural.fractions.FractionConstraintVisitor;
import edu.cmu.cs.plural.fractions.FractionRelation;
import edu.cmu.cs.plural.fractions.FractionSum;
import edu.cmu.cs.plural.fractions.FractionTermVisitor;
import edu.cmu.cs.plural.fractions.ImpossibleConstraint;
import edu.cmu.cs.plural.fractions.NamedFraction;
import edu.cmu.cs.plural.fractions.OneFraction;
import edu.cmu.cs.plural.fractions.VariableFraction;
import edu.cmu.cs.plural.fractions.ZeroFraction;
import edu.cmu.cs.plural.fractions.FractionRelation.Relop;
import edu.cmu.cs.plural.fractions.elim.SimpleFractionSum.Sumop;

/**
 * @author Kevin Bierhoff
 * @deprecated Use VariableElimination instead
 */
public class FractionElimination {
	
	private Set<RelationFractionPair> groundRelations;
	
	public Set<RelationFractionPair> eliminateVariables(Set<FractionConstraint> constraints) {
		Set<RelationFractionPair> rels = normalizeConstraints(constraints);
		Set<VariableFraction> vars = collectVariables(rels);
		
		// every iteration through the loop eliminates the current variable x from rels 
		for(VariableFraction x : vars) {
			rels = eliminateVariable(rels, x);
		}
		groundRelations = rels;
		return rels;
	}

	private Set<RelationFractionPair> eliminateVariable(
			Set<RelationFractionPair> rels, VariableFraction x) {
		SimpleVariableRelativity relativity = new SimpleVariableRelativity(x);
		HashSet<RelationFractionPair> result = new HashSet<RelationFractionPair>();
		Relations:
		for(RelationFractionPair r : rels) {
			int in1, in2;
			do {
				if(r.getComponent1().equals(r.getComponent2()) && !r.getRelop().equals(Relop.LE))
					continue Relations;
				in1 = containsVariable(r.getComponent1(), x);
				in2 = containsVariable(r.getComponent2(), x);
				if(in1 == 0 && in2 == 0) {
					result.add(r);
					continue Relations;
				}
				else if(in1 == in2) {
					r = subtractVariable(r, x, in1);
				}
				else if(in1 == -1 * in2)
					throw new UnsupportedOperationException("Variable on both sides: " + r);
				else 
					break;
			} 
			while(true);
			// in1 != 0 xor in2 != 0;
			if(in1 != 0) {
				if(r.getComponent1().equals(x)) {
					relativity.addRight(r.getRelop(), r.getComponent2());
					continue;
				}
				// assume that Sums are the only non-atomic NormalizedFractionTerm
				// also assume that only one side can be a sum, the other must
				// be an atom (Fraction).  Therefore, casting the other side to
				// Fraction should be ok.
				SimpleFractionSum s = (SimpleFractionSum) r.getComponent1();
				switch(s.getSumop()) {
				case PLUS:
					SimpleFractionSum diff;
					diff = SimpleFractionSum.createSub((Fraction) r.getComponent2(), 
							s.getComponent1().equals(x) ? s.getComponent2() : s.getComponent1());
					relativity.addRight(r.getRelop(), diff);
					break;
				case MINUS:
					if(s.getComponent1().equals(x)) {
						relativity.addRight(r.getRelop(),
								SimpleFractionSum.createAdd((Fraction) r.getComponent2(), s.getComponent2()));
					}
					else {
						relativity.addLeft(
								SimpleFractionSum.createSub(s.getComponent1(), (Fraction) r.getComponent2()),
								r.getRelop());
					}
					break;
				}
			}
			if(in2 != 0) {
				if(r.getComponent2().equals(x)) {
					// (y sumop z) relop x
					relativity.addLeft(r.getComponent1(), r.getRelop());
					continue;
				}
				SimpleFractionSum s = (SimpleFractionSum) r.getComponent2();
				switch(s.getSumop()) {
				case PLUS:
					// z relop (x + y)   or   z relop (y + x)
					SimpleFractionSum diff;
					diff = SimpleFractionSum.createSub((Fraction) r.getComponent1(), 
							s.getComponent1().equals(x) ? s.getComponent2() : s.getComponent1());
					// turned into (z - y) relop x
					relativity.addLeft(diff, r.getRelop());
					break;
				case MINUS:
					if(s.getComponent1().equals(x)) {
						// z relop (x - y)  turns into  (z + y) relop x
						relativity.addLeft(
								SimpleFractionSum.createAdd((Fraction) r.getComponent1(), s.getComponent2()),
								r.getRelop());
					}
					else {
						// z relop (y - x)  turns into  x relop (y - z)
						relativity.addRight(r.getRelop(),
								SimpleFractionSum.createSub(s.getComponent1(), (Fraction) r.getComponent1()));
					}
					break;
				}
			}
		}
		return relativity.dumpRelations(result);
	}

	private static RelationFractionPair subtractVariable(
			final RelationFractionPair rel,
			final VariableFraction x, final int sign) {
		final NormalizedFractionVisitor<NormalizedFractionTerm> v = new NormalizedFractionVisitor<NormalizedFractionTerm>() {
			@Override public NormalizedFractionTerm named(NamedFraction f) {
				return f;
			}
			@Override public NormalizedFractionTerm one(OneFraction f) {
				return f;
			}
			@Override public NormalizedFractionTerm sum(SimpleFractionSum f) {
				if(f.getComponent1().equals(x) && f.getComponent2().equals(x)) {
					return Fraction.zero();
				}
				else if(f.getComponent1().equals(x)) {
					switch(f.getSumop()) {
					case PLUS:
						return f.getComponent2();
					case MINUS:
						return SimpleFractionSum.createSub(Fraction.zero(), f.getComponent2());
					default:
						throw new IllegalArgumentException("Unknown sumop " + f.getSumop() + " in " + rel);
					}
				}
				else if(f.getComponent2().equals(x)) {
					return f.getComponent1();
				}
				else return f;
			}
			@Override public NormalizedFractionTerm var(VariableFraction f) {
				if(f.equals(x))
					return Fraction.zero();
				return f;
			}
			@Override public NormalizedFractionTerm zero(ZeroFraction f) {
				return f;
			}
		};
		NormalizedFractionTerm t1 = rel.getComponent1().dispatch(v);
		NormalizedFractionTerm t2 = rel.getComponent2().dispatch(v);
		switch(rel.getRelop()) {
		case EQ: 
			return RelationFractionPair.createEqual(t1, t2);
		case LEQ: 
			return RelationFractionPair.createLeq(t1, t2);
		case LE: 
			return RelationFractionPair.createLess(t1, t2);
		default:
			throw new IllegalArgumentException("Unknown relop: " + rel);
		}
	}

	private static int containsVariable(final NormalizedFractionTerm t, 
			final VariableFraction x) {
		NormalizedFractionVisitor<Integer> v = 
			new NormalizedFractionVisitor<Integer>() {
			@Override
			public Integer named(NamedFraction f) {
				return 0;
			}
			@Override
			public Integer one(OneFraction f) {
				return 0;
			}
			@Override
			public Integer sum(SimpleFractionSum f) {
				int in1 = f.getComponent1().dispatch(this);
				int in2 = f.getComponent2().dispatch(this);
				if(in1 != 0 && in2 != 0)
					throw new UnsupportedOperationException("Variable " + x + " in both branches of sum: " + t);
				if(f.getSumop().equals(Sumop.MINUS))
					in2 = -1 * in2;
				return in1 + in2;
			}
			@Override
			public Integer var(VariableFraction f) {
				return f.equals(x) ? 1 : 0;
			}
			@Override
			public Integer zero(ZeroFraction f) {
				return 0;
			}
		};
		return t.dispatch(v);
	}

	private Set<VariableFraction> collectVariables(
			Set<RelationFractionPair> rels) {
		final Set<VariableFraction> result = new HashSet<VariableFraction>();
		NormalizedFractionVisitor<Boolean> v = new NormalizedFractionVisitor<Boolean>() {
			@Override public Boolean named(NamedFraction f) {
				return null;
			}
			@Override public Boolean one(OneFraction f) {
				return null;
			}
			@Override public Boolean sum(SimpleFractionSum f) {
				f.getComponent1().dispatch(this);
				f.getComponent2().dispatch(this);
				return null;
			}
			@Override public Boolean var(VariableFraction f) {
				result.add(f);
				return null;
			}
			@Override public Boolean zero(ZeroFraction f) {
				return null;
			}
		};
		for(RelationFractionPair r : rels) {
			r.getComponent1().dispatch(v);
			r.getComponent2().dispatch(v);
		}
		return result;
	}

	private Set<RelationFractionPair> normalizeConstraints(
			Set<FractionConstraint> constraints) {
		final Set<RelationFractionPair> result = new HashSet<RelationFractionPair>();
		
		final FractionTermVisitor<NormalizedFractionTerm> normalizer = 
			new FractionTermVisitor<NormalizedFractionTerm>() {
				@Override public NormalizedFractionTerm literal(Fraction fract) {
					return fract;
				}
				@Override public NormalizedFractionTerm sum(FractionSum fract) {
					if(fract.getSummands().size() == 1)
						return fract.getSummands().get(0);
					if(fract.getSummands().size() == 2)
						return SimpleFractionSum.createAdd(
								fract.getSummands().get(0), fract.getSummands().get(1));
					throw new UnsupportedOperationException("Sum with bad number of summands: " + fract);
				}
		};
		
		final FractionConstraintVisitor<Boolean> cv = new FractionConstraintVisitor<Boolean>() {
			@Override public Boolean impossible(ImpossibleConstraint fract) {
				return null;
			}
			@Override
			public Boolean relation(FractionRelation fract) {
				if(fract.getTerms().size() < 2) return null;
				switch(fract.getRelop()) {
				case EQ:
					if(fract.getTerms().size() == 2) {
						result.add(RelationFractionPair.createEqual(
								fract.getTerms().get(0).dispatch(normalizer), 
								fract.getTerms().get(1).dispatch(normalizer)));
					}
					else
						throw new UnsupportedOperationException("Equality between more than two terms not supported: " + fract);
					break;
				case LEQ:
					for(int i = 1; i < fract.getTerms().size(); i++) {
						result.add(RelationFractionPair.createLeq(
								fract.getTerms().get(i-1).dispatch(normalizer), 
								fract.getTerms().get(i).dispatch(normalizer)));
					}
					break;
				case LE:
					if(fract.getTerms().size() == 2) {
						result.add(RelationFractionPair.createLess(
								fract.getTerms().get(0).dispatch(normalizer), 
								fract.getTerms().get(1).dispatch(normalizer)));
					}
					else
						throw new UnsupportedOperationException("Disequality between more than two terms not supported: " + fract);
					break;
				default:
					throw new IllegalArgumentException("Relation with unknown operator: " + fract.getRelop());
				}
				return null;
			}
			
		};
		
		for(final FractionConstraint c : constraints) {
			c.dispatch(cv);
		}
		return result;
	}

	public boolean isConsistent() {
		// TODO Auto-generated method stub
		return true;
	}
}
