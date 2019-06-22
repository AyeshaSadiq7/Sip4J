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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import edu.cmu.cs.crystal.util.Pair;
import edu.cmu.cs.plural.fractions.Fraction;
import edu.cmu.cs.plural.fractions.NamedFraction;
import edu.cmu.cs.plural.fractions.OneFraction;
import edu.cmu.cs.plural.fractions.FractionRelation.Relop;

/**
 * @author Kevin Bierhoff
 *
 */
public class NormalizedFractionConstraint extends GeneralizedSum {
	
	public static NormalizedFractionConstraint createConstraint(GeneralizedSum left, 
			Relop relop, GeneralizedSum right) {
		Set<Fraction> fs = new HashSet<Fraction>(left.getFractions());
		fs.addAll(right.getFractions());
		SortedMap<Fraction, Rational> newCoeffs = new TreeMap<Fraction, Rational>();
		for(Fraction f : fs) {
			newCoeffs.put(f, left.getCoefficient(f).minus(right.getCoefficient(f)));
		}
		return new NormalizedFractionConstraint(newCoeffs, relop);
	}

	public static NormalizedFractionConstraint createConstraint(Fraction left,
			Relop relop, Fraction right) {
		SortedMap<Fraction, Rational> newCoeffs = new TreeMap<Fraction, Rational>();
		newCoeffs.put(left, Rational.one());
		newCoeffs.put(right, Rational.minusOne());
		return new NormalizedFractionConstraint(newCoeffs, relop);
	}

	private final Relop relop;
	private boolean rangeConstraint;
	
	protected NormalizedFractionConstraint(SortedMap<Fraction, Rational> coefficients) {
		this(coefficients, Relop.EQ);
	}
	
	protected NormalizedFractionConstraint(SortedMap<Fraction, Rational> coefficients, Relop relop) {
		super(coefficients, true);
		this.relop = relop;
		this.rangeConstraint = this.coefficients.size() < 2 || 
				(this.coefficients.size() == 2 && this.coefficients.containsKey(Fraction.one()));
	}
	
	/**
	 * @param x
	 * @return Boolean indicates whether the result and the eliminated variable swapped sides of the relational operator
	 * (by multiplying with -1).
	 */
	public Pair<NormalizedFractionSum, Boolean> isolateFraction(Fraction x) {
		SortedMap<Fraction, Rational> newCoeffs = new TreeMap<Fraction, Rational>();
		Rational r = coefficients.get(x).negation();
		for(Fraction f : coefficients.keySet()) {
			if(f.equals(x)) continue;
			newCoeffs.put(f, coefficients.get(f).div(r));
		}
		return Pair.create(new NormalizedFractionSum(newCoeffs), ! r.isPositive());
	}

	public GeneralizedSum getLeft() {
		return this;
	}

	public Relop getRelop() {
		return relop;
	}

	public GeneralizedSum getRight() {
		return NormalizedFractionSum.zero();
	}
	
	/**
	 * This method assumes that all variables are positive (zero incl.).
	 * Consequently, range constraints (constraining, e.g., a variable
	 * to be greater than 0), are never dominated.
	 * @param other
	 * @return
	 */
	public boolean dominates(NormalizedFractionConstraint other) {
		if(this == other)
			return true;
		if(this.relop.equals(Relop.EQ) || other.relop.equals(Relop.EQ))
			return this.equals(other);
		
		if(other.isRangeConstraint())
			return false;
		
		for(Map.Entry<Fraction, Rational> coeff : this.coefficients.entrySet()) {
			Rational thisV = coeff.getValue();
			Rational otherV = other.getCoefficient(coeff.getKey());
			if(thisV.isPositive()) {
				if(otherV.isPositive() && !thisV.isSmallerThan(otherV))
					continue;
			}
			else {
				// thisV is negative
				if(otherV.isNegative() || !thisV.isSmallerThan(otherV))
					continue;
			}
			return false;
			// Bug: doesn't take sign mismatch into account
//			if(coeff.getValue().isSmallerThan(other.getCoefficient(coeff.getKey()))) {
//				return false;
//			}
		}
		for(Map.Entry<Fraction, Rational> otherCoeff : other.coefficients.entrySet()) {
			if(this.coefficients.containsKey(otherCoeff.getKey()) == false) {
				// this.getCoefficient would return 0 for this Fraction
				if(otherCoeff.getValue().isPositive())
					return false;
			}
			// otherwise tested in first loop
		}
		return this.relop.equals(other.relop) || this.relop.equals(Relop.LE);
	}

	private boolean isRangeConstraint() {
		return rangeConstraint;
	}

	/**
	 * Checks if the constraint compares a multiple of one.
	 * @return <code>True</code> if constraint only compares a multiple of one, <code>false</code> otherwise.
	 * @see OneFraction 
	 */
	public boolean isPrimitive() {
		for(Fraction f : coefficients.keySet())
			// zero's should be dropped, other fractions would be constants or variables
			if((f instanceof OneFraction) == false)
				return false;
		return true;
	}
	
	public boolean isTriviallyTrue() {
		return isTriviallyTrue(maxValue, minValue, relop);
	}
	
	private static boolean isTriviallyTrue(Rational maxValue, Rational minValue, Relop relop) {
		switch(relop) {
		case LEQ:
			return ! maxValue.isPositive();
		case LE:
			return maxValue.isNegative();
		case EQ:
			return maxValue.isZero() && minValue.isZero();
		default:
			throw new UnsupportedOperationException("Unknown relop: " + relop);
		}
	}
	
	public boolean isTrueWithAssumptions(Map<NamedFraction, NamedFraction> upperBounds) {
		if(isTriviallyTrue())
			return true;
		if(namedCoeffs.size() < 2)
			return false;
		switch(relop) {
		case EQ:
			return false;
		case LE:
			if(getConstant().isPositive())
				return false;
			if(getConstant().isZero() && ! ground)
				return false;
			break;
		case LEQ:
			if(getConstant().isPositive())
				return false;
			break;
		}
		Rational myMax = maxValue;
		Map<NamedFraction, Rational> adjusted = new HashMap<NamedFraction, Rational>(namedCoeffs);
		for(Map.Entry<NamedFraction, Rational> named : namedCoeffs.entrySet()) {
			if(named.getValue().isPositive())
				// take coefficient out for now
				myMax = myMax.minus(named.getValue());

			NamedFraction smaller = named.getKey();
			Rational smallerValue = adjusted.get(smaller);
			if(smallerValue == null)
				continue;
			
			search_upper:
			while(smallerValue.isPositive()) {
				NamedFraction upper = upperBounds.get(smaller);
				Rational biggerValue = adjusted.get(upper);
				while(biggerValue == null) {
					upper = upperBounds.get(upper);
					if(upper == null) {
						break search_upper;
					}
					biggerValue = adjusted.get(upper);
				}
				adjusted.remove(smaller);
				smallerValue = biggerValue.plus(smallerValue);
				smaller = upper;
			}
			if(named.getKey() != smaller)
				adjusted.put(smaller, smallerValue);
		}
		
//		next_const:
//		for(Map.Entry<NamedFraction, Rational> pair : namedCoeffs.entrySet()) {
//			Rational value = pair.getValue();
//			if(value.isPositive())
//				// take coefficient out for now
//				myMax = myMax.minus(value);  
//			NamedFraction upper = pair.getKey();
//			if(adjusted.containsKey(upper))
//				value = value.plus(adjusted.get(upper));
//			if(value.isPositive()) {
//				// only approximate positive constants
//				while(upperBounds.get(upper) != null) {
//					upper = upperBounds.get(upper);
//					if(namedCoeffs.containsKey(upper))
//						break;
//				}
//				Rational r = adjusted.get(upper);
//				if(r == null)
//					r = value;
//				else
//					r = r.plus(value);
//				adjusted.put(upper, r);
//			}
//			else {
//				adjusted.put(upper, value);
//			}
			
//			while(upper != null) {
//				Rational y = getCoefficient(upper);
//				if(y.isZero()) 
//					continue next_const;
//				Rational sum = y.plus(value);
//				if(value.isPositive() && y.isNegative()) {
//					if(sum.isPositive())
//						myMax = myMax.minus(sum);
//					else
//						myMax = myMax.minus(value);
//					continue next_const;
//				}
//				else if(value.isNegative() && y.isPositive()) {
//					if(sum.isPositive())
//						myMin = myMin.minus(value);
//					else
//						myMin = myMin.minus(sum);
//					continue next_const;
//				}
//				upper = upperBounds.get(upper);
//			}
//		}
		// too big even without coeffs from constatns
		if(myMax.isPositive())
			return false;
		
		boolean hasNegativeConstant = false;
		for(Map.Entry<NamedFraction, Rational> newCoeff : adjusted.entrySet()) {
			if(newCoeff.getValue().isPositive()) {
				myMax = myMax.plus(newCoeff.getValue());
			}
			else if(newCoeff.getValue().isNegative()) {
				hasNegativeConstant = true;
			}
		}
		if(ground && hasNegativeConstant && ! myMax.isPositive())
			// constants are strictly greater than 0, and there is a negative constant
			// --> myMax = 0 means that sum is strictly less than 0 --> succeed
			return true;
		return isTriviallyTrue(myMax, minValue, relop);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " " + relop + " 0";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (rangeConstraint ? 1231 : 1237);
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
		NormalizedFractionConstraint other = (NormalizedFractionConstraint) obj;
		if (rangeConstraint != other.rangeConstraint)
			return false;
		if (relop == null) {
			if (other.relop != null)
				return false;
		} else if (!relop.equals(other.relop))
			return false;
		return true;
	}

}
