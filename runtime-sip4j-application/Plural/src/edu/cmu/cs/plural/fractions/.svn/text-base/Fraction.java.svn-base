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

import edu.cmu.cs.plural.fractions.elim.NormalizedFractionTerm;

/**
 * @author Kevin Bierhoff
 *
 */
public abstract class Fraction extends FractionTerm implements NormalizedFractionTerm {
	
	/**
	 * Double-dispatch on the type of fraction (non-descending).
	 * @param <T> Return type of the visitor.
	 * @param visitor
	 * @return Result returned by the visitor when invoked for this fraction.
	 */
	public abstract <T> T dispatch(FractionVisitor<T> visitor);

	@Override
	public final <T> T dispatch(FractionTermVisitor<T> visitor) {
		return visitor.literal(this);
	}

	/**
	 * Returns the one (1) fraction.
	 * @return One (1).
	 */
	public static Fraction one() {
		return OneFraction.INSTANCE;
	}

	/**
	 * Returns the zero (0) fraction.
	 * @return Zero (0).
	 */
	public static Fraction zero() {
		return ZeroFraction.INSTANCE;
	}
	
	/**
	 * Returns a name fraction object with the given symbolic name.
	 * @param name
	 * @return Named fraction object with the given symbolic name.
	 */
	public static Fraction createNamed(String name) {
		return new NamedFraction(name);
	}
	
	/**
	 * Returns a fraction object representing a fraction with the given dividend and divisor.
	 * @param p Dividend
	 * @param q Divisor
	 * @return Fraction object representing <code>p / q</code>.
	 */
	public static Fraction createExplicit(int p, int q) {
		// TODO catch these error cases in the annotatoin analysis
		if(q == 0)
			// error: division by zero
			return zero();
		if(p > q)
			// error: greater than 1
			return zero();
		if(p < 0 || q < 0)
			// error: only positive numbers allowed (don't worry about p and q being negative)
			return zero();
		
		if(p == 0)
			return zero();
		if(p == q)
			return one();
		
		throw new UnsupportedOperationException("Implement representation for explicit fractions");
	}

	/**
	 * Test whether this fraction is the literal {@link #one()}.
	 * @return <code>true</code> if this is {@link #one()}; <code>false</code> otherwise.
	 */
	public boolean isOne() {
		return false;
	}

	/**
	 * Test whether this fraction is the literal {@link #zero()}.
	 * @return <code>true</code> if this is {@link #zero()}; <code>false</code> otherwise.
	 */
	public boolean isZero() {
		return false;
	}
	
	/**
	 * Test whether this fraction is a {@link VariableFraction}.
	 * @return <code>true</code> if this is a {@link VariableFraction}; <code>false</code> otherwise.
	 */
	public boolean isVariable() {
		return false;
	}

	/**
	 * Test whether this fraction is a {@link NamedFraction}.
	 * @return <code>true</code> if this is a {@link NamedFraction}; <code>false</code> otherwise.
	 */
	public boolean isNamed() {
		return false;
	}
	
	/**
	 * Indicates whether this fraction has a fixed, albeit possibly unknown, value, i.e.
	 * whether this is zero, one, or a named fraction, but not a variable. 
	 * @return <code>true</code> if this fraction has a fixed value, <code>false</code> otherwise.
	 */
	public final boolean isFixed() {
		return ! isVariable();
	}

	/**
	 * Tests whether this is neither {@link #zero()} nor {@link #one()}.
	 * @return <code>true</code> if this is neither {@link #zero()} no {@link #one()}; 
	 * <code>false</code> if it's {@link #zero()} or {@link #one()}.
	 */
	public final boolean isNeitherZeroNorOne() {
		return (isOne() == false) && (isZero() == false);
	}
	
	/**
	 * Tests whether this is guaranteed not (i.e., strictly greater than) {@link #zero()}.
	 * @return
	 */
	public final boolean isGuaranteedGreaterThanZero() {
		return dispatch(new FractionVisitor<Boolean>() {
			@Override public Boolean named(NamedFraction fract) {
				return true;
			}
			@Override public Boolean one(OneFraction fract) {
				return true;
			}
			@Override public Boolean var(VariableFraction fract) {
				return false; // can be zero
			}
			@Override public Boolean zero(ZeroFraction fract) {
				return false; // must be zero
			}
		});
	}

	/**
	 * Tests whether this is possibly greater than or equal to another fraction.
	 * @param other Another fraction.
	 * @return <code>true</code> if this may be greater than <code>other</code>;
	 * <code>false</code> otherwise.
	 */
	public final boolean isPossiblyGreaterOrEqual(final Fraction other) {
		return dispatch(new FractionVisitor<Boolean>() {
			@Override public Boolean named(NamedFraction fract) {
				return other.isZero() || (other instanceof VariableFraction);
			}
			@Override public Boolean one(OneFraction fract) {
				return true;
			}
			@Override public Boolean var(VariableFraction fract) {
				return true;
			}
			@Override public Boolean zero(ZeroFraction fract) {
				return other.isZero();
			}
		});
	}

}
