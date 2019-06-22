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

import edu.cmu.cs.plural.pred.PredicateChecker.SplitOffTuple;
import edu.cmu.cs.plural.track.PluralTupleLatticeElement;

/**
 * Represents the right-hand side of an implication after it has been
 * eliminated.
 * 
 * The primary use of this result is to be able to put the results into
 * a PluralTuppleLattice without having to know what the type of the
 * implication was. 
 * 
 * @author Nels Beckman
 * @date Mar 11, 2008
 *
 */
public interface ImplicationResult {

	/**
	 * After you have eliminated an implication, this method, given a MUTABLE lattice,
	 * will MUTATE that lattice by adding in the result of the implication and return it.
	 * As currently used, this is the method that eliminates the implication.
	 * splitOffResult is only used for proving that a implication is true.
	 * @param value
	 * @return
	 */
	PluralTupleLatticeElement putResultIntoLattice(PluralTupleLatticeElement value);
	
	/**
	 * This will call the given call-back to remove the result of this implication
	 * from the lattice. Note that this method SHOULD NOT be used for eliminating
	 * the implication. Use putResultIntoLattice instead.
	 */
	boolean splitOffResult(SplitOffTuple tuple);
}
