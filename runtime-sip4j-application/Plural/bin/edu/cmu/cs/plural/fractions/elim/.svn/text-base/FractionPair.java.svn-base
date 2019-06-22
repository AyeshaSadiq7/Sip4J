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

/**
 * @author Kevin Bierhoff
 * @deprecated
 */
public abstract class FractionPair<T extends NormalizedFractionTerm> {
	
	private T component1;
	private T component2;

	public FractionPair(T c1, T c2) {
		this.component1 = c1;
		this.component2 = c2;
	}

	public T getComponent1() {
		return component1;
	}

	public T getComponent2() {
		return component2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((component1 == null) ? 0 : component1.hashCode());
		result = prime * result
				+ ((component2 == null) ? 0 : component2.hashCode());
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
		final FractionPair<?> other = (FractionPair<?>) obj;
		if (component1 == null) {
			if (other.component1 != null)
				return false;
		} else if (!component1.equals(other.component1))
			return false;
		if (component2 == null) {
			if (other.component2 != null)
				return false;
		} else if (!component2.equals(other.component2))
			return false;
		return true;
	}

}
