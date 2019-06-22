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

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import edu.cmu.cs.plural.fractions.Fraction;
import edu.cmu.cs.plural.fractions.NamedFraction;

/**
 * @author Kevin Bierhoff
 *
 */
public abstract class GeneralizedSum {

	/**
	 * Should not mutate this map after construction finished.
	 */
	protected final SortedMap<Fraction, Rational> coefficients = new TreeMap<Fraction, Rational>();
	
	protected final SortedMap<NamedFraction, Rational> namedCoeffs = new TreeMap<NamedFraction, Rational>();

	protected Rational minValue = Rational.zero(), maxValue = Rational.zero();
	
	protected boolean ground = true;
	
	protected GeneralizedSum() {
	}

	public GeneralizedSum(Map<Fraction, Rational> coefficients) {
		for(final Fraction f : coefficients.keySet()) {
			if(f.isZero()) continue;
			Rational c = coefficients.get(f);
			if(c.isZero()) continue;
			this.coefficients.put(f, c);
			
			if(f.isOne()) {
				maxValue = maxValue.plus(c);
				minValue = minValue.plus(c);
			}
			else {
				if(c.isPositive()) {
					maxValue = maxValue.plus(c);
				}
				else {
					minValue = minValue.plus(c);
				}
			}
			
			if(f.isVariable()) {
				ground = false;
			}
			else if(f.isNamed()) {
				this.namedCoeffs.put((NamedFraction) f, c);
			}
		}
	}
	
	public GeneralizedSum(SortedMap<Fraction, Rational> coefficients, boolean normalizeCoeffs) {
		Rational scale = null;
		for(final Fraction f : coefficients.keySet()) {
			if(f.isZero()) continue;
			Rational c = coefficients.get(f);
			if(c.isZero()) continue;
			if(scale == null) {
				scale = c.abs();
			}
			if(normalizeCoeffs)
				c = c.div(scale);
			this.coefficients.put(f, c);
			
			if(f.isOne()) {
				maxValue = maxValue.plus(c);
				minValue = minValue.plus(c);
			}
			else {
				if(c.isPositive()) {
					maxValue = maxValue.plus(c);
				}
				else {
					minValue = minValue.plus(c);
				}
			}
			
			if(f.isVariable()) {
				ground = false;
			}
			else if(f.isNamed()) {
				this.namedCoeffs.put((NamedFraction) f, c);
			}
		}
	}
	
	public GeneralizedSum(Fraction... fractions) {
		this(1, fractions);
	}
	
	public GeneralizedSum(int multiplier, Fraction... fractions) {
		if(multiplier == 0)
			return;
		Rational r = new Rational(multiplier);
		for(Fraction f : fractions) {
			if(f.isZero()) continue;
			this.coefficients.put(f, r);
		}
	}
	
	/**
	 * @return
	 */
	public Set<Fraction> getFractions() {
		return Collections.unmodifiableSet(coefficients.keySet());
	}

	/**
	 * @param f
	 * @return
	 */
	public Rational getCoefficient(Fraction f) {
		Rational result = coefficients.get(f);
		if(result == null) 
			return Rational.zero();
		else
			return result;
	}
	
	public Rational getConstant() {
		return getCoefficient(Fraction.one());
	}
	
	public boolean isGround() {
		return ground;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		if(coefficients.isEmpty()) result.append("0");
		for(Fraction f : coefficients.keySet()) {
			if(result.length() > 0)
				result.append("+");
			result.append(coefficients.get(f) + "*" + f);
		}
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coefficients == null) ? 0 : coefficients.hashCode());
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
		final GeneralizedSum other = (GeneralizedSum) obj;
		if (coefficients == null) {
			if (other.coefficients != null)
				return false;
		} else if (!coefficients.equals(other.coefficients))
			return false;
		return true;
	}

}
