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

import java.util.List;

/**
 * @author Kevin Bierhoff
 *
 */
public abstract class FractionTerm implements Comparable<FractionTerm> {
	
	/**
	 * Double-dispatch on the type of fraction term (non-descending).
	 * @param <T> Return type of the visitor.
	 * @param visitor
	 * @return Result returned by the visitor when invoked for this fraction term.
	 */
	public abstract <T> T dispatch(FractionTermVisitor<T> visitor);

	public static FractionTerm createSum(Fraction... summands) {
		if(summands.length == 0)
			return Fraction.zero();
		if(summands.length == 1)
			return summands[0];
		return new FractionSum(summands);
	}

	public static FractionTerm createSum(List<Fraction> summands) {
		if(summands.size() == 0)
			return Fraction.zero();
		if(summands.size() == 1)
			return summands.get(0);
		return new FractionSum(summands);
	}

	/**
	 * Implements the order 0 < 1 < named < VAR < sums.  Named and Variable fractions are compared
	 * based on their variable names.  Sums are compared element-wise.
	 */
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final FractionTerm o) {
		if(o.equals(this)) return 0;  // consistency with equals, resilience against null
		return dispatch(new FractionTermVisitor<Integer>() {

			@Override
			public Integer literal(Fraction fract) {
				if(o instanceof Fraction) {
					final Fraction f = (Fraction) o;
					return fract.dispatch(new FractionVisitor<Integer>(){
						@Override public Integer named(NamedFraction fract) {
							if(f instanceof ZeroFraction || f instanceof OneFraction)
								return 1;
							else if(f instanceof VariableFraction)
								return -1;
							else
								return fract.getVarName().compareTo(((NamedFraction) f).getVarName());
						}
						@Override public Integer one(OneFraction fract) {
							if(f instanceof ZeroFraction)
								return 1;
							else
								return f instanceof OneFraction ? 0 : -1;
						}
						@Override public Integer var(VariableFraction fract) {
							if(f instanceof ZeroFraction || f instanceof OneFraction || f instanceof NamedFraction)
								return 1;
							else
								return fract.compareToVar((VariableFraction) f);
						}
						@Override public Integer zero(ZeroFraction fract) {
							return f instanceof ZeroFraction ? 0 : -1;
						}
					});
				}
				else
					return -1;  // literals are smaller than other stuff
			}

			@Override
			public Integer sum(FractionSum fract) {
				if(o instanceof FractionSum) {
					FractionSum s = (FractionSum) o;
					if(fract.getSummands().size() == s.getSummands().size()) {
						for(int i = 0; i < fract.getSummands().size(); i++) {
							int elementCompare = fract.getSummands().get(i).compareTo(s.getSummands().get(i));
							if(elementCompare != 0) 
								return elementCompare;
						}
						return 0;
					}
					else
						return fract.getSummands().size() < s.getSummands().size() ? -1 : 1;
				}
				else 
					return 1;  // sums are bigger than other stuff
			}
			
		});
	}

}
