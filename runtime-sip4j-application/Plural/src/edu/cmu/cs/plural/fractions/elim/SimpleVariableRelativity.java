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

import edu.cmu.cs.plural.fractions.NamedFraction;
import edu.cmu.cs.plural.fractions.OneFraction;
import edu.cmu.cs.plural.fractions.VariableFraction;
import edu.cmu.cs.plural.fractions.ZeroFraction;
import edu.cmu.cs.plural.fractions.FractionRelation.Relop;

/**
 * @author Kevin Bierhoff
 * @deprecated Use VariableRelativity instead.
 */
public class SimpleVariableRelativity {
	
	private VariableFraction var;
	private NormalizedFractionVisitor<Boolean> varCheck =
		new NormalizedFractionVisitor<Boolean>() {
			@Override public Boolean named(NamedFraction f) {
				return false;
			}
			@Override public Boolean one(OneFraction f) {
				return false;
			}
			@Override public Boolean sum(SimpleFractionSum f) {
				return f.getComponent1().dispatch(this) || f.getComponent2().dispatch(this);
			}
			@Override public Boolean var(VariableFraction f) {
				return f.equals(var);
			}
			@Override public Boolean zero(ZeroFraction f) {
				return false;
			}
	};
	private Set<NormalizedFractionTerm> lessThan = new HashSet<NormalizedFractionTerm>();
	private Set<NormalizedFractionTerm> lessOrEqual = new HashSet<NormalizedFractionTerm>();
	private Set<NormalizedFractionTerm> equal = new HashSet<NormalizedFractionTerm>();
	private Set<NormalizedFractionTerm> greaterOrEqual = new HashSet<NormalizedFractionTerm>();
	private Set<NormalizedFractionTerm> greaterThan = new HashSet<NormalizedFractionTerm>();
	
	public SimpleVariableRelativity(VariableFraction var) {
		this.var = var;
	}

	public boolean addRight(Relop relop, NormalizedFractionTerm term) {
		if(term.dispatch(varCheck))
			throw new IllegalArgumentException("Eliminated variable " + var + " in term: " + term);
		switch(relop) {
		case EQ:
			return equal.add(term);
		case LEQ:
			return greaterOrEqual.add(term);
		case LE:
			return greaterThan.add(term);
		}
		throw new IllegalArgumentException("Unknown relop: " + relop);
	}

	public boolean addLeft(NormalizedFractionTerm term, Relop relop) {
		if(term.dispatch(varCheck))
			throw new IllegalArgumentException("Eliminated variable " + var + " in term: " + term);
		switch(relop) {
		case EQ:
			return equal.add(term);
		case LEQ:
			return lessOrEqual.add(term);
		case LE:
			return lessThan.add(term);
		}
		throw new IllegalArgumentException("Unknown relop: " + relop);
	}

	public Set<RelationFractionPair> dumpRelations(
			Set<RelationFractionPair> result) {
		NormalizedFractionTerm last = null;
		for(NormalizedFractionTerm eq : equal) {
			// =='s (equalities)
			if(last != null && (eq.equals(last) == false))
				result.add(RelationFractionPair.createEqual(last, eq));
			last = eq;
			// == + <= yields <=
			for(NormalizedFractionTerm more : greaterOrEqual) {
				if(eq.equals(more) == false)
					result.add(RelationFractionPair.createLeq(eq, more));
			}
			// == + < yields <
			for(NormalizedFractionTerm more : greaterThan) {
				result.add(RelationFractionPair.createLess(eq, more));
			}
		}
		
		for(NormalizedFractionTerm less : lessOrEqual) {
			// <= + <= yields <=
			for(NormalizedFractionTerm more : greaterOrEqual) {
				if(less.equals(more) == false)
					result.add(RelationFractionPair.createLeq(less, more));
			}
			// <= + == yields <=
			for(NormalizedFractionTerm more : equal) {
				if(less.equals(more) == false)
					result.add(RelationFractionPair.createLeq(less, more));
			}
			// <= + < yields <
			for(NormalizedFractionTerm more : greaterThan) {
				result.add(RelationFractionPair.createLess(less, more));
			}
		}
		
		for(NormalizedFractionTerm less : lessThan) {
			// < + == yields <
			for(NormalizedFractionTerm more : equal) {
				result.add(RelationFractionPair.createLess(less, more));
			}
			// < + <= yields <
			for(NormalizedFractionTerm more : greaterOrEqual) {
				result.add(RelationFractionPair.createLess(less, more));
			}
			// < + < yields <
			for(NormalizedFractionTerm more : greaterThan) {
				result.add(RelationFractionPair.createLess(less, more));
			}
		}
		
		return result;
	}

}
