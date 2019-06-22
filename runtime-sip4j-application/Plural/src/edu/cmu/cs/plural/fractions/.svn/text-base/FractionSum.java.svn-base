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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Kevin Bierhoff
 *
 */
public class FractionSum extends FractionTerm {

	private List<Fraction> summands;
	
	public FractionSum(List<Fraction> summands) {
		Collections.sort(summands);
		this.summands = Collections.unmodifiableList(summands);
	}
	
	public FractionSum(Fraction... summands) {
		Arrays.sort(summands);
		this.summands = Collections.unmodifiableList(Arrays.asList(summands));
	}

	public List<Fraction> getSummands() {
		return summands;
	}

	@Override
	public <T> T dispatch(FractionTermVisitor<T> visitor) {
		return visitor.sum(this);
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for(Fraction f : summands) {
			if(result.length() > 0) result.append(" + ");
			result.append(f);
		}
		return result.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((summands == null) ? 0 : summands.hashCode());
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
		final FractionSum other = (FractionSum) obj;
		if (summands == null) {
			if (other.summands != null)
				return false;
		} else if (!summands.equals(other.summands))
			return false;
		return true;
	}
	
}
