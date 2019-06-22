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
 * Immutable class. Is a particular variable null?
 * 
 * @author Nels Beckman
 * @date Mar 11, 2008
 *
 */
final public class NullPredicate implements VariablePredicate {
	/*
	 * Does this predicate declare that the variable is true?
	 */
	private final boolean isNull;
	/*
	 * To which variable does this predicate refer?
	 */
	private final Aliasing variable;

	private NullPredicate() {
		throw new RuntimeException("Should never be called.");
	}

	private NullPredicate(boolean isNull, Aliasing v) {
		this.isNull = isNull;
		this.variable = v;
	}

	public boolean denotesNullVar() {
		return isNull;
	}
	
	@Override
	public boolean isSatisfied(PluralTupleLatticeElement value) {
		return isNull ? value.isNull(variable) : value.isNonNull(variable);
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
		if(denotesNullVar())
			value.addNullVariable(variable);
		else
			value.addNonNullVariable(variable);
		return value;
	}

	/**
	 * Create a new predicate declaring the truth of a variable:
	 * true(v)
	 */
	public static NullPredicate createNullVarPred(Aliasing v) {
		return new NullPredicate(true, v);
	}

	/**
	 * Create a new predicate declaring the falsehood of a variable:
	 * false(v)
	 */
	public static NullPredicate createNonNullVarPred(Aliasing v) {
		return new NullPredicate(false, v);
	}

	@Override
	public String toString() {
		return (isNull ? "null(" : "not-null(") + variable.toString() + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isNull ? 1231 : 1237);
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
		final NullPredicate other = (NullPredicate) obj;
		if (isNull != other.isNull)
			return false;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		return true;
	}

	@Override
	public VariablePredicate createIdenticalPred(Aliasing other) {
		return (this.isNull ? NullPredicate.createNullVarPred(other):
            		          NullPredicate.createNonNullVarPred(other));
	}

	@Override
	public VariablePredicate createOppositePred(Aliasing other) {
		return (this.isNull ? NullPredicate.createNonNullVarPred(other):
			                  NullPredicate.createNullVarPred(other));
	}

	@Override
	public boolean denotesBooleanFalsehood() {
		return false;
	}
	
	@Override
	public boolean denotesBooleanTruth() {
		return false;
	}

	@Override
	public boolean denotesNonNullVariable() {
		return !this.isNull;
	}

	@Override
	public boolean denotesNullVariable() {
		return this.isNull;
	}

	@Override
	public boolean isAlwaysTrue() {
		return false;
	}
	
}
