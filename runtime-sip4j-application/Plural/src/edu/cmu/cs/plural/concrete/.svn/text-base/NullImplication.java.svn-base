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
package edu.cmu.cs.plural.concrete;

import java.util.Collections;
import java.util.Set;

import edu.cmu.cs.crystal.analysis.alias.Aliasing;
import edu.cmu.cs.plural.pred.PredicateChecker.SplitOffTuple;
import edu.cmu.cs.plural.track.PluralTupleLatticeElement;

/**
 * An implication where the right-hand side (the consequent)
 * is some fact about a variable that must or must not be null.
 * 
 * @author Nels E. Beckman
 */
public class NullImplication implements Implication {


	/*
	 * Left-hand side of implication.
	 */
	private final BooleanPredicate antecedantPred;

	/*
	 * Right-hand side of implication.
	 */
	private final NullPredicate consequencePred;

	private NullImplication(BooleanPredicate ant, NullPredicate con) {
		this.antecedantPred = ant;
		this.consequencePred = con;
	}
	
	public static NullImplication createNullImplication(Aliasing v_1, boolean is_v1_true,
			                                            Aliasing v_2, boolean is_v2_null) {
		BooleanPredicate ant = (is_v1_true ? BooleanPredicate.createTrueVarPred(v_1) : BooleanPredicate.createFalseVarPred(v_1));
		NullPredicate con = (is_v2_null ? NullPredicate.createNullVarPred(v_2) : NullPredicate.createNullVarPred(v_2));
		return new NullImplication(ant,con);
	}
	
	@Override
	public Implication createCopyWithNewAntecedant(Aliasing other) {
		return new NullImplication((BooleanPredicate)this.antecedantPred.createIdenticalPred(other),
				                   this.consequencePred);
	}

	@Override
	public Implication createCopyWithOppositeAntecedant(Aliasing other) {
		return new NullImplication((BooleanPredicate)this.antecedantPred.createOppositePred(other),
				                   this.consequencePred);
	}
	
	@Override
	public ImplicationResult result() {
		return new ImplicationResult() {
			@Override
			public PluralTupleLatticeElement putResultIntoLattice(
					PluralTupleLatticeElement value) {
				if( consequencePred.denotesNullVar() ) { 
					value.addNullVariable(consequencePred.getVariable());
				}
				else if( consequencePred.denotesNonNullVariable() ) {
					value.addNonNullVariable(consequencePred.getVariable());
				}
				return value;
			}

			@Override
			public boolean splitOffResult(SplitOffTuple tuple) {
				if( consequencePred.denotesNullVar() ) {
					return tuple.checkNull(consequencePred.getVariable(), null);
				}
				else if( consequencePred.denotesNonNullVariable() ) {
					return tuple.checkNonNull(consequencePred.getVariable(), null);
				}
				return true;
			}
		};
	}

	@Override
	public boolean isSatisfied(PluralTupleLatticeElement value) {
		final Aliasing anteVar = antecedantPred.getVariable();
		if(value.isKnownImplication(anteVar, this).isSome())
			return true;
		
		if(antecedantPred.isUnsatisfiable(value))
			// antecedent is false --> implication trivially holds
			return true;
		
		// TODO can we assume antecedent when testing the consequence? 
		return consequencePred.isSatisfied(value);
	}

	@Override
	public VariablePredicate getAntecedant() {
		return antecedantPred;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((antecedantPred == null) ? 0 : antecedantPred.hashCode());
		result = prime * result
				+ ((consequencePred == null) ? 0 : consequencePred.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NullImplication other = (NullImplication) obj;
		if (antecedantPred == null) {
			if (other.antecedantPred != null)
				return false;
		} else if (!antecedantPred.equals(other.antecedantPred))
			return false;
		if (consequencePred == null) {
			if (other.consequencePred != null)
				return false;
		} else if (!consequencePred.equals(other.consequencePred))
			return false;
		return true;
	}

	@Override
	public Implication createCopyWithoutTemporaryState() {
		return this;
	}

	@Override
	public boolean hasTemporaryState() {
		return false;
	}

	@Override
	public Set<Aliasing> getConsequentVariables() {
		return Collections.singleton(consequencePred.getVariable());
	}

	@Override
	public boolean isImpliedBy(Implication impl) {
		return this.equals(impl);
	}

	@Override
	public Implication createLinkedCopyWithNewAntecedant(Aliasing other) {
		/*
		 * Since a NullImplication can be freely duplicated, a linked copy
		 * is really just a copy.
		 */
		return this.createCopyWithNewAntecedant(other);
	}

	@Override
	public Implication createLinkedCopyWithOppositeAntecedent(Aliasing other) {
		// Same logic as previous method.
		return this.createCopyWithOppositeAntecedant(other);
	}
}