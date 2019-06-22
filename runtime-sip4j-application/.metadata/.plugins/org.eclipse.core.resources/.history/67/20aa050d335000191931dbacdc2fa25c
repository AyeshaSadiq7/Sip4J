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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

import edu.cmu.cs.crystal.util.Pair;
import edu.cmu.cs.plural.fractions.Fraction;
import edu.cmu.cs.plural.fractions.FractionAssignment;
import edu.cmu.cs.plural.fractions.FractionConstraint;
import edu.cmu.cs.plural.fractions.FractionConstraintVisitor;
import edu.cmu.cs.plural.fractions.FractionRelation;
import edu.cmu.cs.plural.fractions.FractionSum;
import edu.cmu.cs.plural.fractions.FractionTerm;
import edu.cmu.cs.plural.fractions.FractionTermVisitor;
import edu.cmu.cs.plural.fractions.ImpossibleConstraint;
import edu.cmu.cs.plural.fractions.NamedFraction;
import edu.cmu.cs.plural.fractions.VariableFraction;
import edu.cmu.cs.plural.fractions.FractionRelation.Relop;

/**
 * @author Kevin Bierhoff
 */
public class VariableElimination {
	
	private Set<NamedFraction> constants;
	private Set<NormalizedFractionConstraint> assumptions;
	private Set<NormalizedFractionConstraint> groundRelations;
	private FractionAssignment assignment;
	private Map<NamedFraction, NamedFraction> upperBounds;
	private Map<VariableFraction, Integer> variableTiers;
	private long deadline;
	private long timeout = 10000;
	
	public Set<NormalizedFractionConstraint> eliminateVariables(
			Collection<FractionConstraint> constraints,
			FractionAssignment a) throws TimeoutException {
		this.assignment = a;
		this.groundRelations = new LinkedHashSet<NormalizedFractionConstraint>();
		this.assumptions = new LinkedHashSet<NormalizedFractionConstraint>();
		this.upperBounds = new HashMap<NamedFraction, NamedFraction>();
		this.variableTiers = new HashMap<VariableFraction, Integer>();
		this.deadline = System.currentTimeMillis() + getTimeout();
		
		// normalize constraints and extract assumptions about constants
		// normalizeConstraints initializes and populates assumptions as well
		// TODO might be cleaner to have a separate input parameter for assumptions
		Set<NormalizedFractionConstraint> rels = normalizeConstraints(constraints);

		// use gaussian elimination to get rid of all variables
		List<VariableFraction> vars = eliminationOrder(collectVariables(rels, VariableFraction.class));
		rels = addVariableConstraints(rels, vars);
		// every iteration through the loop eliminates the current variable x from rels 
		for(VariableFraction x : vars) {
			rels = eliminateFraction(rels, x, true);
		}
		assert rels.isEmpty();
		
		constants = collectVariables(groundRelations, NamedFraction.class);
		constants.addAll(collectVariables(assumptions, NamedFraction.class));
		assumptions = addConstConstraints(assumptions, constants);
		return rels;
	}

	/**
	 * Returns the timeout.
	 * @return the timeout.
	 */
	public long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	private Set<NormalizedFractionConstraint> addVariableConstraints(
			Set<NormalizedFractionConstraint> rels, Iterable<VariableFraction> vars) {
		for(VariableFraction x : vars) {
			rels.add(NormalizedFractionConstraint.createConstraint(Fraction.zero(), Relop.LEQ, x));
			rels.add(NormalizedFractionConstraint.createConstraint(x, Relop.LEQ, Fraction.one()));
		}
		return rels;
	}

	private Set<NormalizedFractionConstraint> addConstConstraints(
			Set<NormalizedFractionConstraint> rels, Iterable<NamedFraction> vars) {
		for(NamedFraction x : vars) {
			rels.add(NormalizedFractionConstraint.createConstraint(Fraction.zero(), Relop.LE, x));
			rels.add(NormalizedFractionConstraint.createConstraint(x, Relop.LE, Fraction.one()));
		}
		return rels;
	}

	private Set<NormalizedFractionConstraint> eliminateFraction(
			Set<NormalizedFractionConstraint> rels, Fraction x, boolean populateGroundRels) 
			throws TimeoutException {
		LinkedHashSet<NormalizedFractionConstraint> result = new LinkedHashSet<NormalizedFractionConstraint>();
		VariableRelativity relativity;
		if(populateGroundRels)
			relativity = new VariableRelativity(x, upperBounds, result, groundRelations, deadline);
		else
			relativity = new VariableRelativity(x, 
					Collections.<NamedFraction, NamedFraction>emptyMap(), result, result, deadline);
		for(NormalizedFractionConstraint rel : rels) {
			if(rel.getFractions().contains(x)) {
				Pair<NormalizedFractionSum, Boolean> elim = rel.isolateFraction(x);
				if(elim.snd())
					relativity.addRight(rel.getRelop(), elim.fst());
				else
					relativity.addLeft(elim.fst(), rel.getRelop());
			}
			else
				result.add(rel);
		}
		return relativity.dumpRelations();
	}

	private <T extends Fraction> SortedSet<T> collectVariables(
			Set<NormalizedFractionConstraint> rels, Class<T> variableType) {
		final SortedSet<T> result = new TreeSet<T>();
		for(final NormalizedFractionConstraint rel : rels) {
			for(Fraction f : rel.getFractions()) {
				if(variableType.isAssignableFrom(f.getClass()))
					result.add((T) f);
				//if(f instanceof T) result.add((T) f);
			}
		}
		return result;
	}
	
	private List<VariableFraction> eliminationOrder(Set<VariableFraction> vars) {
		List<VariableFraction> result = new ArrayList<VariableFraction>(vars);
		Collections.sort(result, new Comparator<VariableFraction>() {
			@Override 
			public int compare(VariableFraction o1, VariableFraction o2) {
				Integer tier1 = variableTiers.get(o1);
				Integer tier2 = variableTiers.get(o2);
				if(tier1 == null) {
					if(tier2 == null)
						return o2.compareTo(o1);
					return 1;
				}
				if(tier2 == null)
					return -1;
				if(tier1.equals(tier2))
					return o2.compareTo(o1);
				return tier2.compareTo(tier1);
			}
		});
		return result;
	}

	private Set<NormalizedFractionConstraint> normalizeConstraints(
			Collection<FractionConstraint> constraints) {
		final Set<NormalizedFractionConstraint> result = new HashSet<NormalizedFractionConstraint>();
		
		final FractionConstraintVisitor<Boolean> cv = new FractionConstraintVisitor<Boolean>() {
			@Override public Boolean impossible(ImpossibleConstraint fract) {
				return null;
			}
			@Override
			public Boolean relation(FractionRelation fract) {
				if(fract.getTerms().size() < 2) return null;
				
				int tier = 0;
				FractionTerm last = null;
				NamedFraction lastConst = null;
				for(FractionTerm t : fract.getTerms()) {
					if(t instanceof NamedFraction) {
						NamedFraction c = (NamedFraction) t;
						if(lastConst != null) {
							assumptions.add(createRelation(lastConst, fract.getRelop(), c));
							upperBounds.put(lastConst, c);
						}
						lastConst = c;
					}
					else if(fract.getRelop().equals(Relop.LEQ) && t instanceof VariableFraction) {
						variableTiers.put((VariableFraction) t, tier++);
					}
					if(last != null) {
						NormalizedFractionConstraint c = 
							createRelation(normalizeTerm(last), fract.getRelop(), normalizeTerm(t));
						if(c.isTriviallyTrue() == false) {
							if(c.isPrimitive())
								groundRelations.add(c);
							else if(c.isGround()) {
								// TODO really, we should keep track of assumptions separately 
								if(! Relop.EQ.equals(fract.getRelop()))
									// assumption (need not be proven)
									assumptions.add(c);
								else
									// constant-only term that's not an assumption (proven later)
									groundRelations.add(c);
							}
							else
								result.add(c);
						}
					}
					last = t;
				}
				return null;
			}
			private NormalizedFractionConstraint createRelation(
					NormalizedFractionSum left, Relop relop,
					NormalizedFractionSum right) {
				return NormalizedFractionConstraint.createConstraint(left, relop, right);
			}
			private NormalizedFractionConstraint createRelation(
					Fraction left, Relop relop,
					Fraction right) {
				return NormalizedFractionConstraint.createConstraint(left, relop, right);
			}
		};
		
		for(final FractionConstraint c : constraints) {
			c.dispatch(cv);
		}
		return result;
	}

	private NormalizedFractionSum normalizeTerm(final FractionTerm term) {
		final FractionTermVisitor<NormalizedFractionSum> normalizer = 
			new FractionTermVisitor<NormalizedFractionSum>() {
				@Override public NormalizedFractionSum literal(Fraction fract) {
					return new NormalizedFractionSum(getRepresentative(fract));
				}
				@Override public NormalizedFractionSum sum(FractionSum fract) {
					return new NormalizedFractionSum(getRepresentatives(fract.getSummands()));
				}
				private Fraction getRepresentative(Fraction fract) {
					return assignment.getRepresentative(fract);
				}
				private Fraction[] getRepresentatives(
						List<Fraction> summands) {
					Fraction[] result = new Fraction[summands.size()];
					int i = 0;
					for(Fraction s : summands) {
						result[i++] = getRepresentative(s);
					}
					return result;
				}
		};
		return term.dispatch(normalizer);
	}
	
	public boolean isSatisfiable(Set<NormalizedFractionConstraint> rels, Set<? extends Fraction> vars) throws TimeoutException {
		for(Fraction x : vars) {
			rels = eliminateFraction(rels, x, false);
		}
		for(NormalizedFractionConstraint c : rels) {
			if(isPrimitiveConstraintSatisfiable(c) == false)
				return false;
		}
		return true;
	}

	/**
	 * @param c
	 */
	private static boolean isPrimitiveConstraintSatisfiable(NormalizedFractionConstraint c) {
		if(c.isPrimitive() == false)
			// sanity check
			throw new IllegalStateException("Constraint not primitive: " + c);
		Rational value = c.getCoefficient(Fraction.one());
		switch(c.getRelop()) {
		case LE:
			if(value.isZero())
				return false;
			// fall through to LEQ to also make sure value is not positive
		case LEQ:
			if(value.isPositive())
				return false;
			break;
		case EQ:
			if(value.isZero() == false)
				return false;
			break;
		default:
			throw new UnsupportedOperationException();
		}
		return true;
	}
	
	public boolean isConsistent() throws TimeoutException {
		if(isSatisfiable(assumptions, constants) == false) {
			throw new IllegalStateException("Contradictory assumptions--can prove anything.");
		}
		for(NormalizedFractionConstraint c : groundRelations) {
			if(c.isPrimitive()) {
				// c doesn't mention constants --> not influenced by assumptions
				// just test if c is satisfiable by itself and 
				// save work for negation and eliminating assumptions
				if(isPrimitiveConstraintSatisfiable(c))
					continue;
				else
					return false;
			}
			
			// for each non-primitive c, test if its negation is satisfiable together with the assumptions
			NormalizedFractionConstraint notC = null;
			switch(c.getRelop()) {
			case LE:
				notC = NormalizedFractionConstraint.createConstraint(c.getRight(), Relop.LEQ, c.getLeft());
				break;
			case LEQ:
				notC = NormalizedFractionConstraint.createConstraint(c.getRight(), Relop.LE, c.getLeft());
				break;
			case EQ:
				// negating equality yields two constraints, < and >
				// test < constraint
				NormalizedFractionConstraint notC2 = 
					NormalizedFractionConstraint.createConstraint(c.getLeft(), Relop.LE, c.getRight());
				if(assumptions.add(notC2) == false)
					// notC2 was already an assumption, and assumptions are satisfiable, 
					// so we're inconsistent because notC2 + assumptions should be unsatisfiable
					return false;
				boolean sat = isSatisfiable(assumptions, constants);
				assumptions.remove(notC2);
				if(sat)
					// we're inconsistent because assumptions + notC should be unsatisfiable
					return false;

				// test > constraint below
				notC = NormalizedFractionConstraint.createConstraint(c.getRight(), Relop.LE, c.getLeft());
				break;
			}
			assert notC != null;
			if(assumptions.add(notC) == false)
				// notC was already an assumption, and assumptions are satisfiable, 
				// so we're inconsistent because notC + assumptions should be unsatisfiable
				return false;
			boolean sat = isSatisfiable(assumptions, constants);
			assumptions.remove(notC);
			if(sat)
				// we're inconsistent because assumptions + notC should be unsatisfiable
				return false;
		}
		return true;
	}
}
