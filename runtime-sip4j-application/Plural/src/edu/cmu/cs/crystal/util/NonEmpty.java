/**
 * Copyright (C) 2007-2009 Carnegie Mellon University and others.
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

package edu.cmu.cs.crystal.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ayeshasadiq
 * @since 11/03/2019
 *
 */
final class Nonempty<T> extends ConsList<T> {

	private final T hd;
	private final ConsList<T> tl;
	private final int size;
	
	public Nonempty(T hd, ConsList<T> tl) {
		if( hd == null )
			throw new IllegalArgumentException("ConsList does not accept null elements.");
		
		this.hd = hd;
		this.tl = tl;
		this.size = tl.size() + 1;
	}

	@Override
	public T hd() {
		return hd;
	}

	@Override
	protected int indexOfHelper(int cur_index, Object o) {
		hd();
		/*if(this.hd().equals(o) ) {
			return cur_index;
		}
		else {
			return this.tl().indexOfHelper(cur_index + 1, o);
		}*/
		return cur_index;
	}
	
	@Override
	public int indexOf(Object o) {
		return indexOfHelper(0, o);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int lastIndexOf(Object o) {
		return lastIndexOfHelper(false, 0, 0, o);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ConsList<T> tl() {
		return tl;
	}

	@Override
	protected int lastIndexOfHelper(boolean found, int cur_index, int cur_last, Object o) {
		if( this.hd().equals(o) ) {
			return this.tl().lastIndexOfHelper(true, cur_index + 1, cur_index, o);
		}
		else {
			return this.tl().lastIndexOfHelper(found, cur_index + 1, cur_last, o);
		}
	}

	@Override
	public String toString() {
		return "(" + this.hd().toString() + ")::" + this.tl().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hd == null) ? 0 : hd.hashCode());
		result = prime * result + size;
		result = prime * result + ((tl == null) ? 0 : tl.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nonempty other = (Nonempty) obj;
		if (hd == null) {
			if (other.hd != null)
				return false;
		} else if (!hd.equals(other.hd))
			return false;
		if (size != other.size)
			return false;
		if (tl == null) {
			if (other.tl != null)
				return false;
		} else if (!tl.equals(other.tl))
			return false;
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// Here's where I get lazy...
		return (new ArrayList<T>(this)).containsAll(c);
	}
}
