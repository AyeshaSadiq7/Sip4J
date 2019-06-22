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
package edu.cmu.cs.plural.fractions;

import edu.cmu.cs.plural.fractions.elim.NormalizedFractionVisitor;

/**
 * @author Kevin Bierhoff
 *
 */
public class VariableFraction extends Fraction {
	
	private static long nextID = 0;
	private long id;
	
	public VariableFraction() {
		this.id = nextID++;
	}

	public String getVarName() {
		return "VAR" + id;
	}
	
	@Override
	public boolean isVariable() {
		return true;
	}

	@Override
	public <T> T dispatch(FractionVisitor<T> visitor) {
		return visitor.var(this);
	}

	@Override
	public <T> T dispatch(NormalizedFractionVisitor<T> visitor) {
		return visitor.var(this);
	}
	
	/**
	 * Implementation of {@link Comparable#compareTo(Object)} with
	 * different name due to Java restriction to one polymorphic type instantiation.
	 * @param other the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this 
	 * object is less than, equal to, or greater than the specified object.
	 * @throws NullPointerException if <code>other</code> is <code>null</code>.
	 */
	public int compareToVar(VariableFraction other) {
		long otherID = other.id; // throws NPE as required by comparator contract
		long thisID = this.id;
		return Long.signum(thisID - otherID);
	}

	@Override
	public String toString() {
		return getVarName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		VariableFraction other = (VariableFraction) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
