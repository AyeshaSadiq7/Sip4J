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

import edu.cmu.cs.crystal.analysis.alias.Aliasing;
import edu.cmu.cs.plural.track.PluralTupleLatticeElement;

/**
 * A variable predicate is a predicate declaring the truth or falsehood of a
 * particular variable.
 * eg.
 *   true(var43)
 *   false(x)
 *   
 * @author Nels Beckman
 * @date Feb 11, 2008
 *
 */
final class BooleanPredicate implements VariablePredicate {
	/*
	 * Does this predicate declare that the variable is true?
	 */
	private final boolean truth;
	/*
	 * To which variable does this predicate refer?
	 */
	private final Aliasing variable;

	private BooleanPredicate() {
		throw new RuntimeException("Should never be called.");
	}

	private BooleanPredicate(boolean t, Aliasing v) {
		truth = t;
		variable = v;
	}

	private boolean denotesTruth() {
		return truth;
	}
	
	@Override
	public boolean isSatisfied(PluralTupleLatticeElement value) {
		return truth ? value.isBooleanTrue(variable) : value.isBooleanFalse(variable);
	}
	
	@Override
	public boolean isUnsatisfiable(PluralTupleLatticeElement value) {
		return createOppositePred(variable).isSatisfied(value);
	}
	
	@Override
	public Aliasing getVariable() {
		return variable;
	}

	@Override
	public PluralTupleLatticeElement putIntoLattice(
			PluralTupleLatticeElement value) {
		if(denotesTruth())
			value.addTrueVarPredicate(variable);
		else
			value.addFalseVarPredicate(variable);
		return value;
	}

	/**
	 * Create a new predicate declaring the truth of a variable:
	 * true(v)
	 */
	public static BooleanPredicate createTrueVarPred(Aliasing v) {
		return new BooleanPredicate(true, v);
	}

	/**
	 * Create a new predicate declaring the falsehood of a variable:
	 * false(v)
	 */
	public static BooleanPredicate createFalseVarPred(Aliasing v) {
		return new BooleanPredicate(false, v);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (truth ? 1231 : 1237);
		result = prime * result
		+ ((variable == null) ? 0 : variable.hashCode());
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
		final BooleanPredicate other = (BooleanPredicate) obj;
		if (truth != other.truth)
			return false;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return (truth ? "true(" : "false(") + variable.toString() + ")";
	}

	@Override
	public VariablePredicate createIdenticalPred(Aliasing other) {
		return (this.denotesTruth() ? BooleanPredicate.createTrueVarPred(other) :
                                      BooleanPredicate.createFalseVarPred(other));
	}

	@Override
	public VariablePredicate createOppositePred(Aliasing other) {
		return (this.denotesTruth() ? BooleanPredicate.createFalseVarPred(other) :
			                          BooleanPredicate.createTrueVarPred(other));
	}

	@Override
	public boolean denotesBooleanFalsehood() {
		return !denotesTruth();
	}

	@Override
	public boolean denotesBooleanTruth() {
		return denotesTruth();
	}

	@Override
	public boolean denotesNonNullVariable() {
		return false;
	}

	@Override
	public boolean denotesNullVariable() {
		return false;
	}

	@Override
	public boolean isAlwaysTrue() {
		return false;
	}
}