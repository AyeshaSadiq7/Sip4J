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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jdt.core.dom.ASTNode;

import edu.cmu.cs.crystal.util.ConsList;
import edu.cmu.cs.crystal.util.Freezable;
import edu.cmu.cs.plural.fractions.elim.VariableElimination;
import edu.cmu.cs.plural.fractions.solver.SmtLibPrinter;
import static edu.cmu.cs.crystal.util.ConsList.cons;

/**
 * Collects sets of constraints on fractions and tests them for consistency.  To increase
 * efficiency, this class implements the {@link Freezable} interface.
 * 
 * This class offers three different tests for consistency, with different overhead.
 * <ul>
 *      <li>{@link #isImpossible()} is a trivial test (with constant overhead).
 *      <li>{@link #seemsConsistent()} is a lightweight test based on {@link #simplify()}.
 *      <li>{@link #isConsistent()} is the authoritative sound and complete test.
 * </ul>
 * The first two can be used to more quickly detect inconsistent constraints, but
 * only {@link #isConsistent()} can decide that a constraint set is indeed consistent.
 * 
 * It is <i>recommended</i> (and currently done on a best-effort basis, but not enforced)
 * that {@link VariableFraction variables} and {@link NamedFraction constants} that
 * are mentioned in constraints are registered with the constraint set.  Registration
 * can be by adding variables (constants) to the set returned by {@link #getVariables()}
 * ({@link #getConstants()}) or by calling {@link #registerFractions(Set)}.
 * 
 * @author Kevin Bierhoff
 * @see #addConstraint(FractionConstraint) add a constraint
 * @see #isConsistent() test for consistency
 * @see #simplify() find equalities between fractions and terms
 */
public final class FractionConstraints implements Freezable<FractionConstraints> {
	
	private static final Logger log = Logger.getLogger(FractionConstraints.class.getName());

	/**
	 * Returns a new (mutable) constraint object with no universal parameters.
	 * @return
	 */
	public static FractionConstraints createMutable() {
		return new FractionConstraints();
	}
	
	/**
	 * Returns a new (mutable) constraint object with the given universals.
	 * @param universalParameters
	 * @return
	 */
	public static FractionConstraints createMutable(Set<NamedFraction> universalParameters) {
		return new FractionConstraints(Collections.unmodifiableSet(universalParameters));
	}
	
	/** Constraint set.  This is what this class is all about. */
	private ConsList<FractionConstraint> constraints;
	
	/**
	 * The set of named fractions used in the constraints that represent
	 * instantiated universal quantifiers.  These typically come from
	 * method pre-conditions.  Named fractions that are not in this set
	 * are assumed to be existentially quantified.  They may be made
	 * subject to alpha-conversion during permission comparison.
	 */
	private final Set<NamedFraction> universalParameters;
	
	/** 
	 * Fraction variables used in the constraint.  We're currently making
	 * a best-effort attempt at keeping this set up to date.  
	 */
	private Set<VariableFraction> variables;

	/** 
	 * Fraction constants (unknowns) used in the constraint.  
	 * We're currently making
	 * a best-effort attempt at keeping this set up to date.  
	 */
	private Set<NamedFraction> constants;
	
	/** 
	 * Flag to mark frozen constraint sets.
	 * @see #freeze()
	 * @see Freezable 
	 */
	private boolean frozen;
	
	/** 
	 * Cached fraction assignment or <code>null</code>.
	 * {@link #simplifyInternal()} is only called if this field is <code>null</code>. 
	 */
	private FractionAssignment assignment;
	
	/** 
	 * Cached consistency test result or <code>null</code>.  
	 * {@link #isConsistentInternal()} is only called if this field is <code>null</code>.
	 */
	private Boolean consistent;
	
	/**
	 * Creates an empty constraint set.
	 */
	private FractionConstraints() {
		this(Collections.<NamedFraction>emptySet());
	}
	
	/**
	 * Creates an empty constraint set with the given universal parameters.
	 * @param universalParameters Should be immutable. 
	 */
	private FractionConstraints(Set<NamedFraction> universalParameters) {
		this.constraints = ConsList.empty();
		this.universalParameters = universalParameters;
		this.variables = new LinkedHashSet<VariableFraction>();
		this.constants = new LinkedHashSet<NamedFraction>();
	}
	
	/**
	 * Adds the given constraint to the set, if not frozen.
	 * @param newConstraint
	 * @return This constraint set (for chained calls).
	 */
	public FractionConstraints addConstraint(FractionConstraint newConstraint) {
		if(frozen)
			throw new IllegalStateException("Cannot modify frozen constraint set");
		
		constraints = cons(newConstraint, constraints);
		
		// once inconsistent, constraints cannot become consistent
		if(consistent != null && consistent)
			consistent = null;
		
		return this;
	}
	
	/**
	 * Adds the constraints from the given set to this constraint set,
	 * if not frozen.
	 * @param moreConstraints
	 */
	public void addAll(FractionConstraints moreConstraints) {
		if(frozen)
			throw new IllegalStateException("Cannot modify frozen constraint set");
		
		this.constraints = ConsList.concat(moreConstraints.constraints, this.constraints);
		
		if(consistent != null && consistent)
			// once inconsistent, constraints cannot become consistent
			consistent = null;
		
		this.variables.addAll(moreConstraints.variables);
		this.constants.addAll(moreConstraints.constants);
	}
	
	/**
	 * Returns an unmodifiable view of the constraint set.
	 * @return An unmodifiable view of the constraint set.
	 */
	public Collection<FractionConstraint> getConstraints() {
		return constraints;
	}

	/**
	 * Returns the variables (can be used to add or remove elements).
	 * @return the variables.
	 */
	public Set<VariableFraction> getVariables() {
		return variables;
	}

	/**
	 * Returns the constants (can be used to add or remove elements).
	 * @return the constants.
	 */
	public Set<NamedFraction> getConstants() {
		return constants;
	}
	
	public Set<NamedFraction> getUniversalParameters() {
		return universalParameters;
	}
	
	/**
	 * Adds variables and constants in the given fraction set to the respective
	 * sets, if this constraint set is not frozen.
	 * @param fractions
	 * @see #getVariables()
	 * @see #getConstants()
	 */
	public void registerFractions(Set<Fraction> fractions) {
		if(frozen)
			throw new IllegalStateException("Cannot modify frozen constraint set");
		FractionVisitor<Boolean> v = new FractionVisitor<Boolean>() {
			@Override public Boolean named(NamedFraction fract) {
				constants.add(fract);
				return null;
			}
			@Override public Boolean one(OneFraction fract) {
				return null;
			}
			@Override public Boolean var(VariableFraction fract) {
				variables.add(fract);
				return null;
			}
			@Override public Boolean zero(ZeroFraction fract) {
				return null;
			}
		};
		
		for(Fraction f : fractions) {
			f.dispatch(v);
		}
	}
	
	/**
	 * Tests whether this constraint set contains the {@link FractionConstraint#impossible()
	 * impossible} constraint.  This is the simplest possible way a constraint set
	 * can be inconsistent, and calling this method can be used as a very shallow test
	 * for consistency.
	 * @return <code>true</code> if this constraint set contains the 
	 * {@link FractionConstraint#impossible() impossible} constraint, <code>false</code>
	 * otherwise.
	 * @see #isConsistent() for a sound and complete consistency test
	 * @see #seemsConsistent() for a lightweight consistency test
	 */
	public boolean isImpossible() {
		return constraints.contains(FractionConstraint.impossible());
	}

	/**
	 * Tests if this constraint set has at least the constraints of 
	 * the given set.  This method is intended for use in a lattice structure.
	 * @param other
	 * @return <code>true</code> if this constraint set has at least the constraints
	 * of the given set, <code>false</code> otherwise.
	 * @deprecated This is not the comparison one would want.
	 * @see edu.cmu.cs.crystal.bridge.LatticeElement#atLeastAsPrecise(edu.cmu.cs.crystal.bridge.LatticeElement, ASTNode)
	 */
	@Deprecated
	public boolean atLeastAsPrecise(FractionConstraints other) {
		return this.constraints.containsAll(other.constraints);
	}

	public FractionConstraints freeze() {
		if(frozen == false) {
			frozen = true;
			variables = Collections.unmodifiableSet(variables);
			constants = Collections.unmodifiableSet(constants);
		}
		return this;
	}

	public FractionConstraints mutableCopy() {
		return mutableCopy(Collections.<NamedFraction>emptySet());
	}

	/**
	 * @param universals
	 * @return
	 */
	public FractionConstraints mutableCopy(Set<NamedFraction> universals) {
		FractionConstraints result;
		if(universals.isEmpty())
			result = new FractionConstraints(this.universalParameters);
		else {
			universals = new HashSet<NamedFraction>(universals);
			universals.addAll(this.universalParameters);
			result = new FractionConstraints(Collections.unmodifiableSet(universals));
		}
			
		result.constraints = this.constraints;
		result.variables.addAll(this.variables);
		result.constants.addAll(this.constants);
		result.consistent = consistent;
		return result;
	}

	/**
	 * Creates a whole-new constraint set containing
	 * all constraints from both the receiver and the argument.
	 * Use other methods to manipulate constraints in-place.
	 * @param other
	 * @return A whole-new constraint set containing
	 * all constraints from both the receiver and the argument.
	 * @see #addAll(FractionConstraints)
	 */
	public FractionConstraints concat(FractionConstraints other) {
		Set<NamedFraction> universals;
		if(this.universalParameters == other.universalParameters ||
				this.universalParameters.containsAll(other.universalParameters)) {
			universals = this.universalParameters;
		}
		else if(other.universalParameters.containsAll(this.universalParameters)) {
			universals = other.universalParameters;
		}
		else {
			universals = new HashSet<NamedFraction>(this.universalParameters);
			universals.addAll(other.universalParameters);
			universals = Collections.unmodifiableSet(universals);
		}
		FractionConstraints result = new FractionConstraints(universals);
		
		// Just an optimization...
		if( other.constraints == this.constraints )
			result.constraints = this.constraints;
		else
			result.constraints = ConsList.concat(other.constraints, this.constraints);
		
		// TODO: Can we check that the consistency flag is still accurate?
		result.variables.addAll(this.variables);
		result.variables.addAll(other.variables);
		result.constants.addAll(this.constants);
		result.constants.addAll(other.constants);
		if(this.consistent != null && ! this.consistent)
			result.consistent = false;
		else if(other.consistent != null && ! other.consistent)
			result.consistent = false;
		return result;
	}
	
	/**
	 * Returns a fraction assignment corresponding to the current constraint set.
	 * This allows replacing equal fractions with one unique representative.
	 * @return a fraction assignment corresponding to the current constraint set.
	 */
	public FractionAssignment simplify() {
		if(assignment != null)
			return assignment;
		if(frozen) {
			assignment = simplifyInternal();
			return assignment;
		}
		else
			return simplifyInternal();
	}
	
	/**
	 * Computes a new fraction assignment corresponding to the current constraint set.
	 * Essentially this method computes equivalence classes between terms.
	 * @return a new fraction assignment corresponding to the current constraint set.
	 */
	private FractionAssignment simplifyInternal() {
		FractionAssignment result = new FractionAssignment();

		TreeSet<FractionRelation> relations = new TreeSet<FractionRelation>();
		for(FractionConstraint c : constraints) {
			if(c instanceof FractionRelation)
				relations.add((FractionRelation) c);
		}
	
		do {
			result.resetChangedFlag();
			for(FractionRelation r : relations) {
				boolean isLE = false;
				switch(r.getRelop()) {
				case EQ:
					// equivalent
					result.makeEquivalent(r.getTerms());
					// take advantage of 0's and 1's in sums
					// also take advantage of sums where one summand is equal to whole sum
					for(FractionTerm t : r.getTerms()) {
						boolean isZero = result.isZero(t);
						Fraction total = result.getLiteral(t);
						if(t instanceof FractionSum) {
							FractionSum sum = (FractionSum) t;
							Fraction one = null;
							Fraction equalToTotal = null;
							int zeros = 0;
							for(Fraction f : sum.getSummands()) {
								if(result.isOne(f))
									one = f;
								if(result.isZero(f))
									zeros++;
								if(total != null && result.areEquivalent(total, f)) 
									equalToTotal = f;
							}
							if(isZero || one != null || equalToTotal != null) {
								for(Fraction f : sum.getSummands()) {
									if(isZero || (one != null && f != one) || (equalToTotal != null && f != equalToTotal)) 
										result.makeZero(f);
								}
							}
							if(zeros == sum.getSummands().size() - 1) {
								for(Fraction f : sum.getSummands()) {
									if(result.isZero(f) == false) {
										// equate f with other terms in relation
										ArrayList<FractionTerm> terms = new ArrayList<FractionTerm>(r.getTerms());
										terms.remove(r);
										terms.add(f);
										result.makeEquivalent(terms);
									}
								}
							}
							else if(zeros == sum.getSummands().size()) {
								result.makeZero(r.getTerms());
							}							
						}
					}
					break;
				case LE:
					isLE = true;
				case LEQ:
					// if one of the terms is one then all following terms must also be one
					// if one of the terms is non-zero, then all following terms must also be non-zero
					// if this is an LE relation then all but the first term must be non-zero
					boolean one = false;
					boolean nonZero = false;
					for(FractionTerm t : r.getTerms()) {
						if(one) {
							// TODO for LE, this is actually an error
							result.makeOne(t);
						}
						else if(result.isOne(t)) {
							one = true;
						}
						
						if(nonZero) {
							result.makeNonZero(t);
						}
						else if(isLE) {
							// only the first term in < can be zero
							nonZero = true;
						}
						else if(result.isNonZero(t)) {
							nonZero = true;
						}
					}
					// if one of the terms is zero then all preceding terms must also be zero
					boolean zero = false;
					for(int i = r.getTerms().size()-1; i >= 0; i--) {
						if(zero) {
							// TODO for LE, this is actually an error
							result.makeZero(r.getTerms().get(i));
						}
						else if(result.isZero(r.getTerms().get(i))) {
							zero = true;
						}
					}
					break;
				}
			}
		} 
		while(result.isChanged());

		return result;
	}
	
	/**
	 * Tests the constraints for consistency (satisfiability).
	 * This is the authoritative (sound and complete) but also most expensive
	 * test for consistency.
	 * @return <code>true</code> if the constraints are consistent, <code>false</code>
	 * if they are inconsistent.
	 * @see #isImpossible() for a trivial consistency test
	 * @see #seemsConsistent() for a lightweight consistency test
	 */
	public boolean isConsistent() {
		if(consistent != null)
			return consistent;
		if(frozen) {
			consistent = isConsistentInternal();
			if(log.isLoggable(Level.FINEST)) {
				log.finest(new SmtLibPrinter().toString(this, consistent));
			}
			return consistent;
		}
		else {
			boolean result = isConsistentInternal();
			if(log.isLoggable(Level.FINEST)) {
				log.finest(new SmtLibPrinter().toString(this, result));
			}
			return result;
		}
	}
	
	/**
	 * Performs a sound and complete test for consistency of the constraints.
	 * @return <code>true</code> if the constraints are consistent, <code>false</code>
	 * if they are inconsistent.
	 */
	private boolean isConsistentInternal() {
		if(isImpossible())
			return false;
		final FractionAssignment assignment = simplify();
		if(assignment.isConsistent() == false)
			return false;
	
		// simple algorithm thinks constraints are consistent
		
		VariableElimination elim = new VariableElimination();
		try {
			elim.eliminateVariables(constraints, assignment);
			if(elim.isConsistent() == false) {
				if(log.isLoggable(Level.FINE))
					log.fine("Simple algorithm fails to detect inconsistency: " +
							this);
				return false;
			}
			return true;
		} 
		catch (TimeoutException e) {
			if(log.isLoggable(Level.WARNING)) 
				log.log(Level.WARNING, "Timed out: " + this, e);
			return false;
		}
	}
	
	/**
	 * Runs a simple <b>unsound</b> method to determine constraint satisfiability.
	 * If this method returns <code>false</code> then the constraints are definitely
	 * unsatisfiable.  This is a lightweight test for consistency based on
	 * inconsistencies in the {@link FractionAssignment equivalence classes} returned
	 * by {@link #simplify()}.
	 * @return <code>false</code> if the constraints are unsatisfiable, <code>true</code>
	 * if they may or may not be satisfiable.
	 * @see #isConsistent() for a sound and complete consistency test
	 * @see #isImpossible() for a trivial consistency test
	 */
	public boolean seemsConsistent() {
		if(isImpossible())
			return false;
		final FractionAssignment assignment = simplify();
		return assignment.isConsistent();
	}
	
	@Override
	public String toString() {
		return constraints.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((constraints == null) ? 0 : constraints.hashCode());
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
		final FractionConstraints other = (FractionConstraints) obj;
		if (constraints == null) {
			if (other.constraints != null)
				return false;
		} else if (!constraints.equals(other.constraints))
			return false;
		return true;
	}

	/**
	 * Returns a fresh variable that's registered with these constraints.
	 * @return A fresh variable that's registered with these constraints.
	 */
	public VariableFraction newVariableFraction() {
		if(frozen)
			throw new IllegalStateException("Cannot modify frozen constraint set");
		VariableFraction result = new VariableFraction();
		variables.add(result);
		return result;
	}

	/**
	 * Returns a fresh constant that's registered with these constraints.
	 * @return A fresh constant that's registered with these constraints.
	 */
	public NamedFraction newNamedFraction() {
		if(frozen)
			throw new IllegalStateException("Cannot modify frozen constraint set");
		NamedFraction result = new NamedFraction();
		constants.add(result);
		return result;
	}

	/**
	 * Creates a named fraction that's a universal on the given AST node.
	 * This means that this named fraction can subsume any fraction term
	 * when compared on that AST node.  This is useful for joins
	 * because named fractions introduced in joins can take on any value
	 * in subsequent joins.  A way to think about this is that 
	 * the named fraction is universally quantified for incoming lattice
	 * elements but existentially quantified for subsequent steps.
	 * @return a named fraction that's a universal on the given AST node.
	 */
	public NamedFraction newNamedFraction(ASTNode node) {
		if(frozen)
			throw new IllegalStateException("Cannot modify frozen constraint set");
		NamedFraction result = new NamedFraction(node);
		constants.add(result);
		return result;
	}

	/**
	 * Tests if the given constraint together with the existing constraints
	 * is consistent.
	 * @param test
	 * @return <code>true</code> if the given constraint together with the
	 * existing constraints is satisfiable, <code>false</code> otherwise (which
	 * can mean that the existing constraints were already not consistent).
	 */
	public boolean testConstraint(FractionConstraint test) {
		if(consistent != null && !consistent)
			// constraints are already inconsistent as they are
			return false;
		if(frozen && !isConsistent())
			// for frozen constraints, make sure they're not already inconsistent
			return false;
		FractionConstraints testConstraints = this.mutableCopy();
		testConstraints.addConstraint(test);
		return testConstraints.isConsistent();
	}

	/**
	 * Tests if the given constraints together with the existing constraints
	 * are consistent.
	 * @param test Constraints to test; if no constraints are provided then
	 * this method is equivalent to calling {@link #isConsistent()}
	 * @return <code>true</code> if the given constraints together with the
	 * existing constraints are satisfiable, <code>false</code> otherwise (which
	 * can mean that the existing constraints were already not consistent).
	 */
	public boolean testConstraints(FractionConstraint... test) {
		if(test.length == 0)
			// not actually a test...
			return this.isConsistent();
		if(consistent != null && !consistent)
			// constraints are already inconsistent as they are
			return false;
		if(frozen && !isConsistent())
			// for frozen constraints, make sure they're not already inconsistent
			return false;
		FractionConstraints testConstraints = this.mutableCopy();
		for(FractionConstraint c : test)
			testConstraints.addConstraint(c);
		return testConstraints.isConsistent();
	}

	/**
	 * Tests whether the given fraction is known to this constraint set.
	 * A fraction is known if it's either a literal or was previously added
	 * to the {@link #getVariables() variables} or {@link #getConstants() constants}.
	 * @param f
	 * @return <code>true</code> if this fraction is known, <code>false</code> otherwise.
	 */
	public boolean isKnown(Fraction f) {
		final FractionVisitor<Boolean> known = new FractionVisitor<Boolean>() {
			@Override public Boolean named(NamedFraction fract) {
				return constants.contains(fract);
			}
			@Override public Boolean one(OneFraction fract) {
				return true;
			}
			@Override public Boolean var(VariableFraction fract) {
				return variables.contains(fract);
			}
			@Override public Boolean zero(ZeroFraction fract) {
				return true;
			}
		};
		return f.dispatch(known);
	}

}
