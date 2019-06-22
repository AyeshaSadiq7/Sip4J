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

import java.util.Set;

import edu.cmu.cs.crystal.analysis.alias.Aliasing;

/**
 * Interface for implications in the dynamic state logic system.
 * Implementations of this interface should override {@link #equals(Object)}
 * and {@link #hashCode()}.
 * 
 * @author Nels Beckman
 * @date Mar 11, 2008
 *
 */
public interface Implication extends Predicate {
		
	/**
	 * Returns the antecedent.
	 */
	VariablePredicate getAntecedant();
	
	/**
	 * Return the variables mentioned in the consequence predicate.
	 */
	Set<Aliasing> getConsequentVariables();

	/**
	 * Returns the result of this implication.
	 */
	ImplicationResult result();

	/**
	 * Creates a linked copy of this implication with a different
	 * antecedent. The main purpose of this method is to allow 
	 * copies of implications to be created where of all the implications, 
	 * at most one can ever be eliminated. If the consequent is
	 * a permission, this is important so that permissions are not
	 * duplicated.
	 */
	Implication createLinkedCopyWithNewAntecedant(Aliasing other);
	
	/**
	 * Creates a linked copy of this implication with a new
	 * antecedent opposite to this one. The main purpose of this method
	 * is to allow 
	 * copies of implications to be created where of all the implications, 
	 * at most one can ever be eliminated. If the consequent is
	 * a permission, this is important so that permissions are not
	 * duplicated.
	 * @param other
	 * @return
	 */
	Implication createLinkedCopyWithOppositeAntecedent(Aliasing other);
	
	/**
	 * Creates a new implication based on this one but with the
	 * antecedent negated and written in terms of the given
	 * object, if that is possible.
	 * @param other
	 * @return a new implication based on this one but with the
	 * antecedent negated, or <code>null</code> if the current
	 * antecedent cannot be negated (e.g., if the antecedent
	 * includes a permission).
	 */
	Implication createCopyWithOppositeAntecedant(Aliasing other);

	/**
	 * Creates a new implication based on this one but with the
	 * antecedent written in terms of the given object, if that is possible.
	 * @param other
	 * @return Creates a new implication based on this one but with the
	 * antecedent written in terms of the given object, or <code>null</code> 
	 * if that is not possible (not sure if that can happen).
	 */
	Implication createCopyWithNewAntecedant(Aliasing other);

	/**
	 * @return <code>true</code> if this implication implies temporary state information,
	 * <code>false</code> otherwise.
	 */
	boolean hasTemporaryState();

	/**
	 * Returns an implication without temporary state information, if any.
	 * The returned implication should return <code>false</code> from {@link #hasTemporaryState()}.
	 * @return an implication without temporary state information or <code>null</code>
	 * if the implication should be dropped entirely.
	 */
	Implication createCopyWithoutTemporaryState();

	
	/**
	 * Is this implication implied by the given implication? 
	 */
	boolean isImpliedBy(Implication impl);
}
