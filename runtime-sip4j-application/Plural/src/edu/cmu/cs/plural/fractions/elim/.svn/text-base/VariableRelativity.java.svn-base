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

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeoutException;

import edu.cmu.cs.plural.fractions.Fraction;
import edu.cmu.cs.plural.fractions.NamedFraction;
import edu.cmu.cs.plural.fractions.FractionRelation.Relop;

/**
 * @author Kevin Bierhoff
 */
public class VariableRelativity {
	
	private final WeakHashMap<NormalizedFractionConstraint, Boolean> rejectedCache =
		new WeakHashMap<NormalizedFractionConstraint, Boolean>();
	
	private final Fraction var;
	private final Map<NamedFraction, NamedFraction> upperBounds;
	private final Set<NormalizedFractionConstraint> variable;
	private final Set<NormalizedFractionConstraint> ground;
	
	private final Set<NormalizedFractionSum> lessThan = new HashSet<NormalizedFractionSum>();
	private final Set<NormalizedFractionSum> lessOrEqual = new HashSet<NormalizedFractionSum>();
	private final Set<NormalizedFractionSum> equal = new HashSet<NormalizedFractionSum>();
	private final Set<NormalizedFractionSum> greaterOrEqual = new HashSet<NormalizedFractionSum>();
	private final Set<NormalizedFractionSum> greaterThan = new HashSet<NormalizedFractionSum>();
	
	private final long deadline;
	
	private LinkedList<NormalizedFractionConstraint> searchVariable;

	private int lastTimeoutCheck;
	
	public VariableRelativity(Fraction var, 
			Map<NamedFraction, NamedFraction> upperBounds,
			Set<NormalizedFractionConstraint> variable, 
			Set<NormalizedFractionConstraint> ground,
			long deadline) {
		this.var = var;
		this.upperBounds = upperBounds;
		this.variable = variable;
		this.ground = ground;
		this.deadline = deadline;
	}

	public boolean addRight(Relop relop, NormalizedFractionSum term) {
		if(term.getCoefficient(var).isZero() == false)
			throw new IllegalArgumentException("Eliminated variable " + var + " in term: " + term);
		switch(relop) {
		case EQ:
			// this is an error
//			if(lessThan.contains(term) || greaterThan.contains(term))
//				; 
			if(equal.add(term)) {
				greaterOrEqual.remove(term);
				lessOrEqual.remove(term);
				return true;
			}
			return false;
		case LEQ:
			if(equal.contains(term) || greaterThan.contains(term))
				// superseded by stronger relation -> drop
				return false;
			if(lessOrEqual.remove(term)) {
				// should not be necessary: 
				// greaterOrEqual.remove(term);
				return equal.add(term);
			}
			// this is an error
//			if(lessThan.contains(term))
//				;
			return greaterOrEqual.add(term);
		case LE:
			if(greaterThan.add(term)) {
				greaterOrEqual.remove(term);
				// could check for term in equal, lessOrEqual, lessThan -> error
				return true;
			}
			return false;
		}
		throw new IllegalArgumentException("Unknown relop: " + relop);
	}

	public boolean addLeft(NormalizedFractionSum term, Relop relop) {
		if(term.getCoefficient(var).isZero() == false)
			throw new IllegalArgumentException("Eliminated variable " + var + " in term: " + term);
		switch(relop) {
		case EQ:
			if(equal.add(term)) {
				greaterOrEqual.remove(term);
				lessOrEqual.remove(term);
				return true;
			}
			return false;
		case LEQ:
			if(equal.contains(term) || lessThan.contains(term))
				// superseded by stronger relation -> drop
				return false;
			if(greaterOrEqual.remove(term)) {
				return equal.add(term);
			}
			return lessOrEqual.add(term);
		case LE:
			if(lessThan.add(term)) {
				lessOrEqual.remove(term);
				return true;
			}
			return false;
		}
		throw new IllegalArgumentException("Unknown relop: " + relop);
	}

	public Set<NormalizedFractionConstraint> dumpRelations() throws TimeoutException {
		searchVariable = new LinkedList<NormalizedFractionConstraint>(variable);
		
		NormalizedFractionSum last = null;
		for(NormalizedFractionSum eq : equal) {
			// =='s (equalities)
			if(last != null && (eq.equals(last) == false))
				dumpRelation(last, Relop.EQ, eq);
			last = eq;
			// eq == var && more >= var yields eq <= more
			for(NormalizedFractionSum more : greaterOrEqual) {
				if(eq.equals(more) == false)
					dumpRelation(eq, Relop.LEQ, more);
			}
			// == + > yields <
			for(NormalizedFractionSum more : greaterThan) {
				dumpRelation(eq, Relop.LE, more);
			}
		}
		
		for(NormalizedFractionSum less : lessOrEqual) {
			// less <= var && more >= var yields less <= more
			for(NormalizedFractionSum more : greaterOrEqual) {
				if(less.equals(more) == false)
					dumpRelation(less, Relop.LEQ, more);
			}
			// less <= var && var == more yields less <= more
			for(NormalizedFractionSum more : equal) {
				if(less.equals(more) == false)
					dumpRelation(less, Relop.LEQ, more);
			}
			// <= + > yields <
			for(NormalizedFractionSum more : greaterThan) {
				dumpRelation(less, Relop.LE, more);
			}
		}
		
		for(NormalizedFractionSum less : lessThan) {
			// < + == yields <
			for(NormalizedFractionSum more : equal) {
				dumpRelation(less, Relop.LE, more);
			}
			// < + >= yields <
			for(NormalizedFractionSum more : greaterOrEqual) {
				dumpRelation(less, Relop.LE, more);
			}
			// < + > yields <
			for(NormalizedFractionSum more : greaterThan) {
				dumpRelation(less, Relop.LE, more);
			}
		}
		
		return variable;
	}

	private void dumpRelation(NormalizedFractionSum less, 
			Relop relop, NormalizedFractionSum more) throws TimeoutException {
		if(++lastTimeoutCheck >= 100) {
			// check timeout every 100 calls
			lastTimeoutCheck = 0;
			if(System.currentTimeMillis() > deadline)
				throw new TimeoutException("Exceeded deadline: " + deadline);
		}
		
		NormalizedFractionConstraint c = NormalizedFractionConstraint.createConstraint(less, relop, more);
		if(rejectedCache.containsKey(c)) {
			// previously rejected --> toss it
			return;
		}
		else if(c.isTrueWithAssumptions(upperBounds)) {
			// constraint trivially satisfied --> remember that 
			rejectedCache.put(c, null);
		}
		else {
			if(c.isGround())
				ground.add(c);
			else if(variable.contains(c)) {
				// already in the set -- no need to scan again
				return;
			}
			else {
				for(Iterator<NormalizedFractionConstraint> vcIter = searchVariable.iterator(); vcIter.hasNext(); ) {
					// this is slow as balls but seems to keep the set at a reasonable size
					NormalizedFractionConstraint vc = vcIter.next();
					if(vc.dominates(c)) {
						// "harder" constraint already in there --> remember that
						rejectedCache.put(c, null);
						// move vc to front of searchVariable
						vcIter.remove();
						searchVariable.addFirst(vc);
						return;
					}
					if(c.dominates(vc)) {
						// c is harder than an existing constraint --> get rid of the existing one
						vcIter.remove();
						variable.remove(vc);
						rejectedCache.put(vc, null);
					}
				}
				searchVariable.addFirst(c);
				variable.add(c);
			}
		}
	}

}
