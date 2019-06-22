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
 * An implication useful for implementing dynamic state tests. Often it is the
 * case that the truth of some variable implies another variable is in a particular
 * state, and this information can then be used in dynamic state tests. This class
 * represents those implications.
 *
 * Eventually we envision this class being replaced by actual linear logic
 * implication as described in Kevin's paper, but for the time-being, this will do.
 *
 * @author Nels Beckman
 * @date Feb 11, 2008
 *
 */
public final class StateImplication implements Implication {

	/*
	 * An implication is represented by a variable whose truth is under discussion,
	 * the truth or falsehood of the variable, depending on which one matters for
	 * the implication, the variable that will be in a certain state if the antecedent
	 * is true, and the state that variable will be in given elimination of the
	 * implication.
	 *
	 * ex.
	 * true(var43) implies this in "Closed"
	 * false(x) implies gi in "Open"
	 *
	 * We anticipate that objects of this type will be stored in a map from variable
	 * name to implication involving that variable.
	 */

	/*
	 * The antecedant; true(var43) or false(x) from above
	 */
	private final VariablePredicate antecedantPred;

	/*
	 * The variable whose state is under discussion. this or gi from above.
	 */
	private final Aliasing describedVar;

	/*
	 * The state of the describedVar. Closed or Open from above.
	 */
	private final String varState;

	/**
	 * Returns the right hand side of the implication, assuming
	 * the left is true.
	 * @return
	 */
	@Override
	public ImplicationResult result() {
		return new ImplicationResult() {
			@Override
			public PluralTupleLatticeElement putResultIntoLattice(
					final PluralTupleLatticeElement value) {
				value.put(describedVar, value.get(describedVar).learnTemporaryStateInfo(varState));
				return value;
			}

			@Override
			public boolean splitOffResult(SplitOffTuple tuple) {
				return tuple.checkStateInfo(describedVar, null,
						Collections.singleton(varState), false);
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
		return value.get(describedVar).isInState(varState);
	}

	@Override
	public VariablePredicate getAntecedant() {
		return antecedantPred;
	}

	/**
	 * Returns the implied state.
	 * @return the implied state.
	 */
	public String getVarState() {
		return varState;
	}

	private StateImplication() {
		throw new RuntimeException("Should never be called.");
	}

	private StateImplication(VariablePredicate ant, Aliasing dv, String state) {
		this.antecedantPred = ant;
		this.describedVar = dv;
		this.varState = state;
	}

	/**
	 * Creates a new implication of the form
	 * <code>antecedant -> describedVar in state describedState</code>
	 * @param antecedant
	 * @param describedVar
	 * @param describedState
	 * @return new implication
	 */
	public static StateImplication createTrueVarImplies(Aliasing antecedant,
			Aliasing describedVar, String describedState) {
		return new StateImplication(BooleanPredicate.createTrueVarPred(antecedant),
				describedVar, describedState);
	}

	/**
	 * Creates a new implication of the form
	 * <code>!antecedant -> describedVar in state describedState</code>
	 * @param antecedant
	 * @param describedVar
	 * @param describedState
	 * @return new implication
	 */
	public static StateImplication createFalseVarImplies(Aliasing antecedant,
			Aliasing describedVar, String describedState) {
		return new StateImplication(BooleanPredicate.createFalseVarPred(antecedant),
				describedVar, describedState);
	}

	@Override
	public String toString() {
		return antecedantPred.toString() + " implies "
			+ describedVar.toString() + " in " + varState;
	}

	/**
	 * Creates an identical implication, except that the variable on the
	 * left-hand side will be <code>other</code>.
	 */
	public StateImplication createCopyWithNewAntecedant(Aliasing other) {
		return new StateImplication(this.antecedantPred.createIdenticalPred(other),
				this.describedVar, this.varState);
	}

	/**
	 * Just like createCopyWithNewAntecedant, but if the antecedant is true,
	 * the copy will be false.
	 */
	public StateImplication createCopyWithOppositeAntecedant(Aliasing other) {
		return new StateImplication(this.antecedantPred.createOppositePred(other),
				this.describedVar, this.varState);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((antecedantPred == null) ? 0 : antecedantPred.hashCode());
		result = prime * result
				+ ((describedVar == null) ? 0 : describedVar.hashCode());
		result = prime * result
				+ ((varState == null) ? 0 : varState.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final StateImplication other = (StateImplication) obj;
		if (antecedantPred == null) {
			if (other.antecedantPred != null)
				return false;
		} else if (!antecedantPred.equals(other.antecedantPred))
			return false;
		if (describedVar == null) {
			if (other.describedVar != null)
				return false;
		} else if (!describedVar.equals(other.describedVar))
			return false;
		if (varState == null) {
			if (other.varState != null)
				return false;
		} else if (!varState.equals(other.varState))
			return false;
		return true;
	}

	@Override
	public Implication createCopyWithoutTemporaryState() {
		return null;
	}

	@Override
	public boolean hasTemporaryState() {
		return true;
	}

	@Override
	public Set<Aliasing> getConsequentVariables() {
		return Collections.singleton(describedVar);
	}

	@Override
	public boolean isImpliedBy(Implication impl) {
		return this.equals(impl);
	}

	@Override
	public Implication createLinkedCopyWithNewAntecedant(Aliasing other) {
		/*
		 * I believe that a state implication can be freely duplicated, so
		 * creating a linked implication in this case merely returns a copy.
		 */
		return this.createCopyWithNewAntecedant(other);
	}

	@Override
	public Implication createLinkedCopyWithOppositeAntecedent(Aliasing other) {
		// Same logic as previous method.
		return this.createCopyWithOppositeAntecedant(other);
	}
}