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
import edu.cmu.cs.plural.contexts.TensorPluralTupleLE;
import edu.cmu.cs.plural.track.PluralTupleLatticeElement;

/**
 * The interface for all predicates in the DynamicStateTestLogic system.
 * Implementations should override {@link #equals(Object)} and {@link #hashCode()}.
 * 
 * @author Nels Beckman
 * @date Mar 11, 2008
 *
 */
public interface VariablePredicate extends Predicate {

	VariablePredicate createOppositePred(Aliasing other);

	VariablePredicate createIdenticalPred(Aliasing other);

	boolean denotesBooleanTruth();

	boolean denotesBooleanFalsehood();
	
	boolean denotesNullVariable();

	boolean denotesNonNullVariable();

	/** The variable by which this predicate is indexed in the state logic. */
	Aliasing getVariable();

	/**
	 * After you have eliminated an implication, this method, given a MUTABLE lattice,
	 * will MUTATE that lattice by adding in the result of the implication and return it.
	 * @param value
	 * @return
	 */
	PluralTupleLatticeElement putIntoLattice(PluralTupleLatticeElement value);

	/**
	 * Tests if this predicate is definitely FALSE in the given lattice element.  
	 * Usually this amounts to testing a contradictory predicate for truth.
	 * @param value
	 * @return <code>true</code> if this predicate is definitely FALSE,
	 * <code>true</code> otherwise.
	 * @see #isSatisfied(PluralTupleLatticeElement) to test for truth
	 */
	boolean isUnsatisfiable(PluralTupleLatticeElement value);	

	/**
	 * Indicates whether this predicate will always be <b>true</b>,
	 * regardless of the current analysis context (linear logic <b>1</b>).
	 * In other words, this method indicates whether the predicate is a tautology.
	 * @return <code>true</code> if this predicate is always <b>true</b>,
	 * <code>false</code> otherwise.
	 */
	boolean isAlwaysTrue();
}
