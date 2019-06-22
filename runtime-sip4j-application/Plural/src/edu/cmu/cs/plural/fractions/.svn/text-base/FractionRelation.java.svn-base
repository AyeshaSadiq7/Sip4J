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
 * A FractionRelation is a left-to-right list of fractions that hold a particular equality or
 * inequality relationship. If the relop is '==', then it is just a set of fractions that must
 * be equal. However, if the relop is either '<' or '<=' then it is an order of values. For example,
 * Fraction relation, <code><=[VAR1,VAR2,VAR3]</code> stands for the expression,
 * <code>VAR1 <= VAR2 <= VAR3</code>.
 * 
 * @author Kevin Bierhoff
 *
 */
public class FractionRelation extends FractionConstraint implements Comparable<FractionRelation> {
	
	public enum Relop {
		EQ("=="), LE("<"), LEQ("<="); 
		public String symbol;
		private Relop(String symbol) {
			this.symbol = symbol;
		}
		@Override
		public String toString() {
			return symbol;
		}
	}
	
	private List<FractionTerm> terms;
	private Relop relop;

	public FractionRelation(
			Relop relop,
			FractionTerm... terms) {
		if(Relop.EQ.equals(relop))
			Arrays.sort(terms);
		this.terms = Collections.unmodifiableList(Arrays.asList(terms));
		this.relop = relop;
	}

	public List<FractionTerm> getTerms() {
		return terms;
	}

	public Relop getRelop() {
		return relop;
	}

	@Override
	public <T> T dispatch(FractionConstraintVisitor<T> visitor) {
		return visitor.relation(this);
	}

	@Override
	public String toString() {
		if(getTerms().size() == 2)
			return getTerms().get(0) + " " + getRelop() + " " + getTerms().get(1);
		return "" + getRelop() + getTerms();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((relop == null) ? 0 : relop.hashCode());
		result = prime * result + ((terms == null) ? 0 : terms.hashCode());
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
		final FractionRelation other = (FractionRelation) obj;
		if (relop == null) {
			if (other.relop != null)
				return false;
		} else if (!relop.equals(other.relop))
			return false;
		if (terms == null) {
			if (other.terms != null)
				return false;
		} else if (!terms.equals(other.terms))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FractionRelation o) {
		if(o == null) return 1;
		if(o == this) return 0;
		int minLength = Math.min(this.terms.size(), o.terms.size());
		for(int i = 0; i < minLength; i++) {
			int result = this.terms.get(i).compareTo(o.terms.get(i));
			if(result != 0) return result;
		}
		if(this.terms.size() < o.terms.size()) return -1;
		if(this.terms.size() > o.terms.size()) return 1;
		return this.relop.compareTo(o.relop);
	}

}
