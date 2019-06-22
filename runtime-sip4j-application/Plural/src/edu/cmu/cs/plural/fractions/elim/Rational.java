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
 *
 */
public class Rational {
	
	private static final Rational ZERO = new Rational();
	private static final Rational ONE = new Rational(1);
	private static final Rational MINUS_ONE = new Rational(-1);
	
	public static Rational zero() {
		return ZERO;
	}
	
	public static Rational one() {
		return ONE;
	}

	public static Rational minusOne() {
		return MINUS_ONE;
	}

	/**
	 * Implements Euclid's algorithm to find the greatest common divisor or any two integers.
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a, int b) {
		if(a < 0)
			a = -1 * a;
		if(b < 0) 
			b = -1 * b;
	     while(b != 0) {
	         int t = b;
	         b = a % b;
	         a = t;
	     }
	     return a;
	}
	
	private final int p;
	private final int q;
	
	public Rational() {
		this(0);
	}
	
	public Rational(int number) {
		this.p = number;
		this.q = 1;
	}
	
	public Rational(int p, int q) {
		if(q == 0) throw new IllegalArgumentException("q must be non-zero: " + q);
		if(q < 0) { 
			p = -1 * p; 
			q = -1 * q; 
		}
		int gcd = gcd(p, q);
		this.p = p / gcd;
		this.q = q / gcd;
	}

	/**
	 * Returns the p.
	 * @return the p.
	 */
	public int getP() {
		return p;
	}

	/**
	 * Returns the q.
	 * @return the q.
	 */
	public int getQ() {
		return q;
	}

	/**
	 * @return
	 */
	public boolean isZero() {
		return p == 0;
	}
	
	/**
	 * @return
	 */
	public boolean isOne() {
		return p == q;
	}

	public boolean isPositive() {
		return (p > 0 && q > 0) || (p < 0 && q < 0);
	}

	public boolean isNegative() {
		return (p < 0 && q > 0) || (p > 0 && q < 0);
	}

	/**
	 * @param i
	 * @return
	 */
	public Rational times(int i) {
		return new Rational(p * i, q);
	}

	/**
	 * @return
	 */
	public Rational inverse() {
		return new Rational(q, p);
	}

	/**
	 * @param r
	 * @return
	 */
	public Rational times(Rational r) {
		return new Rational(this.p * r.p, this.q * r.q);
	}

	/**
	 * @param r
	 * @return
	 */
	public Rational div(Rational r) {
		return new Rational(this.p * r.q, this.q * r.p);
	}

	/**
	 * @param coefficient
	 * @return
	 */
	public Rational minus(Rational r) {
		return plus(r.negation());
	}

	/**
	 * @param r
	 * @return
	 */
	public Rational plus(Rational r) {
		return new Rational(this.p * r.q + r.p * this.q, this.q * r.q);
	}

	/**
	 * @return
	 */
	public Rational negation() {
		return new Rational(-1 * p, q);
	}

	public Rational abs() {
		if(isPositive()) return this;
		return negation();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(q == 1) return "" + p;
		return p + "/" + q;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + p;
		result = prime * result + q;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Rational other = (Rational) obj;
		if (p != other.p)
			return false;
		if (q != other.q)
			return false;
		return true;
	}

	public boolean isSmallerThan(Rational other) {
		return this.p * other.q < this.q * other.p;
	}

}
