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

import edu.cmu.cs.plural.fractions.FractionRelation.Relop;

/**
 * @author Kevin Bierhoff
 * @deprecated
 */
public class RelationFractionPair extends FractionPair<NormalizedFractionTerm> {
	
	private Relop relop;
	
	public static RelationFractionPair createEqual(NormalizedFractionTerm c1, NormalizedFractionTerm c2) {
		return new RelationFractionPair(c1, Relop.EQ, c2);
	}

	public static RelationFractionPair createLeq(NormalizedFractionTerm c1, NormalizedFractionTerm c2) {
		return new RelationFractionPair(c1, Relop.LEQ, c2);
	}

	public static RelationFractionPair createLess(NormalizedFractionTerm c1, NormalizedFractionTerm c2) {
		return new RelationFractionPair(c1, Relop.LE, c2);
	}

	private RelationFractionPair(NormalizedFractionTerm c1, Relop relop, NormalizedFractionTerm c2) {
		super(c1, c2);
		this.relop = relop;
	}

	public Relop getRelop() {
		return relop;
	}

	@Override
	public String toString() {
		return getComponent1() + " " + getRelop() + " " + getComponent2();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((relop == null) ? 0 : relop.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RelationFractionPair other = (RelationFractionPair) obj;
		if (relop == null) {
			if (other.relop != null)
				return false;
		} else if (!relop.equals(other.relop))
			return false;
		return true;
	}

}
