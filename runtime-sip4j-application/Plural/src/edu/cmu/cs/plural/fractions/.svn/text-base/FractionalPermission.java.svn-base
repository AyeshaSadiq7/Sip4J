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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jdt.core.dom.ASTNode;

import edu.cmu.cs.crystal.util.Pair;
import edu.cmu.cs.plural.states.StateSpace;
import edu.cmu.cs.plural.track.Permission.PermissionKind;

/**
 * Representation of an individual permission as immutable objects, usually
 * kept in a {@link FractionalPermissions} object.
 * Many methods include {@link FractionConstraints} arguments, which reflects
 * that permissions are usually kept in the surrounding permission set.
 * @author Kevin Bierhoff
 */
public class FractionalPermission extends AbstractFractionalPermission { 
	
	private static final Logger log = Logger.getLogger(FractionalPermission.class.getName());

	/**
	 * Combines the given set of permissions to one permission with the given root.
	 * If this is no possible because we don't actually have a unique permission,
	 * a fractional permission will still be returned it will just have unsatisfiable
	 * constraints.
	 * 
	 * @param permissions Set of permissions with roots that are smaller than <code>neededRoot</code>.
	 * @param stateSpace State space for the new permission.
	 * @param neededRoot Root for the resulting permission.
	 * @param constraints Will add constraints amounting to sums of fractions from
	 * the given permission set equal to fractions in the resulting permission.
	 * @return Permission rooted in <code>neededRoot</code>
	 */
	public static FractionalPermission combine(
			Set<FractionalPermission> permissions, 
			StateSpace stateSpace, String neededRoot,
			FractionConstraints constraints) {
		if(permissions.isEmpty())
			throw new IllegalArgumentException("Empty permission set");
		assert stateSpace != null;
		if(permissions.size() == 1) {
			FractionalPermission p = permissions.iterator().next();
			// just need to move up root of the one available permission
			return p.moveUp(neededRoot, constraints);
		}
		
		// more than one permission to combine --> need to build a new permission with constraints to the old ones
		FractionFunction newF = FractionFunction.variableRemaining(stateSpace, neededRoot, false, 
				Collections.singletonMap(neededRoot, Fraction.one()), Fraction.one());

		// constraints for the sum of fractions from existing permissions to equal new fraction for same node
		// Since fraction for root node is set to one in newF, this will force the sum of the existing fractions
		// for the root to be one
		for(Iterator<String> it = stateSpace.stateIterator(neededRoot); it.hasNext(); ) {
			String n = it.next();
			ArrayList<Fraction> fs = new ArrayList<Fraction>(permissions.size());
			for(FractionalPermission p : permissions) {
				fs.add(p.getFractions().get(n));
			}
			constraints.addConstraint(FractionConstraint.createEquality(
					newF.get(n),
					FractionTerm.createSum(fs)));
		}
		
		// collect state info from all permissions
		// state info sets should not overlap since permissions don't overlap
		Set<String> newStateInfo = new LinkedHashSet<String>();
		for(FractionalPermission p : permissions) {
			newStateInfo.addAll(p.getStateInfo());
//			if(p.getStateSpace().firstImpliesSecond(p.getStateInfo(), neededStateInfo))
//				newStateInfo = p.getStateInfo();
		}
		
		return new FractionalPermission(stateSpace, neededRoot, newF, 
				true, // moved-up permissions always mutable 
				newStateInfo, // TODO plug in state info from given permission set 
				constraints);

		// TODO do we need constraints on the sums of fractions for nodes that go away? They should each equal 1 I guess
//		Set<String> eliminatedNodes = new LinkedHashSet<String>();
//		Set<String> conjoinedStateInfo = new LinkedHashSet<String>();
//		for(FractionalPermission p : permissions) {
//			if(p.getStateSpace().firstBiggerThanSecond(neededRoot, p.getRootNode()) == false)
//				throw new IllegalArgumentException("Cannot move root up to " + neededRoot + ": " + p);
//			conjoinedStateInfo.add(p.getStateInfo());
//			for(Iterator<String> it = p.getStateSpace().stateIterator(p.getRootNode()); it.hasNext(); ) {
//				String n = it.next();
//				if(neededRoot.equals(n))
//					break;
//				eliminatedNodes.add(n);
//			}
//		}
//		
//		for(String n : eliminatedNodes) {
//			LinkedList<Fraction> fs = new LinkedList<Fraction>();
//			for(FractionalPermission p : permissions) {
//				Fraction f = p.fractions.get(n);
//				if(f.isZero() == false)
//					fs.add(f);
//			}
//		}
		
	}

	protected FractionalPermission(StateSpace stateSpace, String rootNode,
			FractionFunction fractions, boolean mutable,
			Iterable<String> stateInfo) {
		super(stateSpace, rootNode, fractions, mutable, stateInfo);
	}
	
	public FractionalPermission(StateSpace stateSpace, String rootNode,
			FractionFunction fractions, boolean mutable,
			Iterable<String> stateInfo, FractionConstraints constraints) {
		super(stateSpace, rootNode, fractions, mutable, stateInfo);
		
		if(constraints == null)
			throw new NullPointerException("Use constructor without constraints parameter for null constraints");
		registerFractions(constraints);
		addIsFunctionConstraints(constraints);
	}
	
//	public FractionalPermission(AbstractFractionalPermission orig, FractionConstraints constraints) {
//		this(orig.stateSpace, orig.rootNode, orig.fractions, orig.mutable, orig.stateInfo, constraints);
//	}
	
	private boolean allBiggerThanAny(Set<String> bigger, Set<String> smaller) {
		next_big:
		for(String b : bigger) {
			for(String s : smaller) {
				if(getStateSpace().firstBiggerThanSecond(b, s))
					continue next_big;
			}
			return false;
		}
		return true;
	}

	/**
	 * Compares this with the given permission, at the given point in the AST
	 * and using the given constraints.
	 * This method does not modify the given constraints.
	 * @param other
	 * @param node
	 * @param constraints 
	 * @return <code>true</code> if this permission is at least as precise as the given one;
	 * <code>false</code> otherwise, including when the two permissions are incomparable.
	 */
	public boolean atLeastAsPrecise(FractionalPermission other, ASTNode node, FractionConstraints constraints) {
		if(other == this) return true;
		if(other == null) return true;
		
		/*
		 * 1. Compare current state information.
		 */
		if(allBiggerThanAny(other.stateInfo, this.stateInfo) == false)
			return false;

		/*
		 * 2. Compare fractions
		 */
		Fraction thisBelow = this.fractions.getBelowFraction();
		
		// this and other have the same root -> compare entire fraction function
		if(this.rootNode.equals(other.rootNode)) {
			// for efficiency, simplify constraints once in the beginning
			// DO NOT INTRODUCE NEW CONSTRAINTS AFTER THIS POINT without re-assigning a
			FractionAssignment a = constraints.simplify();
			Iterator<String> it = this.getStateSpace().stateIterator(this.rootNode);
			while(it.hasNext()) {
				String n = it.next();
				Fraction thisF = this.fractions.get(n);
				Fraction otherF = other.fractions.get(n);
				if(otherF instanceof NamedFraction && 
						! constraints.getUniversalParameters().contains(((NamedFraction) otherF))) {
//				if(otherF instanceof NamedFraction && ((NamedFraction) otherF).isVariable(node)) {
					if(a.isZero(thisF))
						// zero can't instantiate a named fraction
						return false;
					else
						// instantiate otherF with thisF
						continue;
				}
				if(a.areEquivalent(thisF, otherF) == false)
					return false;
				// TODO compare thisF and otherF structurally ? e.g. thisF is half of X, and so is otherF?
			}
			Fraction otherBelow = other.fractions.getBelowFraction();
			boolean below_okay = (otherBelow instanceof NamedFraction
//						&& ((NamedFraction) otherBelow).isVariable(node) 
						&& ! constraints.getUniversalParameters().contains(((NamedFraction) otherBelow))
						&& ! a.isZero(thisBelow)) 
					|| a.areEquivalent(thisBelow, otherBelow);
			return below_okay;
		}
		// this's root is bigger than other's -> check if we can move this's root down
		else if(other.getStateSpace().firstBiggerThanSecond(this.rootNode, other.rootNode)) {
			/*
			 * this.state info should be smaller than other.root
			 */
			boolean state_okay = 
				this.impliesState(other.rootNode);
			if( !state_okay )
				return false;

			// this has bigger root than other --> test if this can move root down (below fraction = 1)

			// DO NOT INTRODUCE NEW CONSTRAINTS AFTER THIS POINT without re-assigning a
			FractionAssignment a = constraints.simplify();
			return a.isOne(thisBelow);
//			return constraints.testConstraint(FractionConstraint.createEquality(thisBelow, Fraction.one()));
		}
		// this's root is smaller than other's -> check if we can move this's root up
		else if(this.getStateSpace().firstBiggerThanSecond(other.rootNode, this.rootNode)) {
			// other's root is bigger than this's --> test if this can move root up (fraction(other.rootNode) = 1)

			// DO NOT INTRODUCE NEW CONSTRAINTS AFTER THIS POINT without re-assigning a
			FractionAssignment a = constraints.simplify();
			return a.isOne(this.fractions.get(other.rootNode));
//			return constraints.testConstraint(FractionConstraint.createEquality(this.fractions.get(other.rootNode), Fraction.one()));
		}
		else
			// this's and other's root are in different dimensions
			return false;
	}

	/**
	 * Approximates this and the given permission with a single permission, 
	 * at the given point in the AST and using the given <code>comparisonConstraints</code>,
	 * possibly adding to <code>constraints</code>
	 * @param other
	 * @param node
	 * @param constraints This set may be extended with additional constraints.
	 * @param comparisonConstraints Constraints to compare the permissions with, not modified.
	 * @return a permission that approximates this and the given permission.
	 */
	public FractionalPermission join(FractionalPermission other, ASTNode node,
			FractionConstraints constraints, FractionConstraints comparisonConstraints) {
		if(this == other) return this;
		
		if(this.rootNode.equals(other.rootNode) == false)
			throw new IllegalArgumentException("Cannot join permissions with different roots:" + this.rootNode + " vs. " + other.rootNode);
		
		// for efficiency, simplify constraints once in the beginning
		// DO NOT CHANGE comparisonConstraints AFTER THIS POINT without re-assigning a
		FractionAssignment a = comparisonConstraints.simplify();
		
		// fraction function
		Iterator<String> it = this.getStateSpace().stateIterator(this.getRootNode());
		HashMap<String, Fraction> fracts = new HashMap<String, Fraction>();
		while(it.hasNext()) {
			String s = it.next();
			Fraction thisF = this.getFractions().get(s);
			Fraction otherF = other.getFractions().get(s);

			if(a.areEquivalent(thisF, otherF)) {
				// thisF = otherF --> just use thisF 
				fracts.put(s, thisF);
			}
			else if(a.isZero(thisF) || a.isZero(otherF)) {
				// coalesce to zero
				fracts.put(s, Fraction.zero());
			}
			else if(thisF instanceof NamedFraction 
					&& constraints.isKnown(thisF)
					&& ! constraints.getUniversalParameters().contains(((NamedFraction) thisF))) {
//					&& ((NamedFraction) thisF).isVariable(node)) {
				fracts.put(s, thisF);
			}
			else if(otherF instanceof NamedFraction 
					&& constraints.isKnown(otherF)
					&& ! constraints.getUniversalParameters().contains(((NamedFraction) otherF))) {
//					&& ((NamedFraction) otherF).isVariable(node)) {
				fracts.put(s, otherF);
			}
			else if(!a.isZero(thisF) && !a.isZero(otherF)) {
				// for efficiency, don't do full test:
//				if( comparisonConstraints.testConstraints(
//					FractionConstraint.createLessThan(Fraction.zero(), thisF),
//					FractionConstraint.createLessThan(Fraction.zero(), otherF) )) {
				// thisF != otherF --> introduce new existential variable
				NamedFraction c = constraints.newNamedFraction(node);
				fracts.put(s, c);
				if(constraints.isKnown(thisF)) {
					// thisF must be > 0 in order to coalesce it into an existential
					constraints.addConstraint(FractionConstraint.createLessThan(Fraction.zero(), thisF));
				}
				else {
					// TODO else test on the spot?
					if(log.isLoggable(Level.WARNING))
						log.warning("Cannot ensure " + thisF + " > 0");
				}
				if(constraints.isKnown(otherF)) {
					// otherF must be > 0 in order to coalesce it into an existential
					constraints.addConstraint(FractionConstraint.createLessThan(Fraction.zero(), otherF));
				} 
				else {
					// TODO else test on the spot?
					if(log.isLoggable(Level.WARNING))
						log.warning("Cannot ensure " + otherF + " > 0");
				}
			}
			else {
				// They weren't greater than zero
				fracts.put(s, Fraction.zero());
			}
		}
		
		// mutability
		boolean newMutable;
		Fraction blw = null;
		if(this.mutable != other.mutable) {
			FractionalPermission mut;
			FractionalPermission ro;
			if(this.mutable) { mut = this; ro = other; } else { mut = other; ro = this; }
			if(a.isOne(ro.fractions.getBelowFraction())) {
				// readonly is full permission --> will be downgraded to share if needed
				newMutable = true;
			}
			else if(a.isOne(mut.fractions.getBelowFraction())) {
				// mutable is full permission --> will be downgraded to immutable or pure
				newMutable = false;
			}
			else {
				// one may be share and one immutable --> downgrade to pure
				blw = Fraction.zero();
				newMutable = false;
			}
		}
		else {
			newMutable = this.mutable;
		}
		
		// below fraction
		if(blw == null) {
			Fraction thisF = this.getFractions().getBelowFraction();
			Fraction otherF = other.getFractions().getBelowFraction();
			if(a.areEquivalent(thisF, otherF)){
				blw = thisF;
				// keep constraints for this's below fraction
			}
			else if(a.isZero(thisF) || a.isZero(otherF)) {
				blw = Fraction.zero();
			}
			else if(thisF instanceof NamedFraction 
					&& constraints.isKnown(thisF)
					&& ! constraints.getUniversalParameters().contains(((NamedFraction) thisF))) {
//					&& ((NamedFraction) thisF).isVariable(node)) {
				blw = thisF;
			}
			else if(otherF instanceof NamedFraction 
					&& constraints.isKnown(otherF)
					&& ! constraints.getUniversalParameters().contains(((NamedFraction) otherF))) {
//					&& ((NamedFraction) otherF).isVariable(node)) {
				blw = otherF;
			}
			else if(!a.isZero(thisF) && !a.isZero(otherF)) { 
				// for efficiency, don't do full test:
//				if( comparisonConstraints.testConstraints(
//					FractionConstraint.createLessThan(Fraction.zero(), thisF),
//					FractionConstraint.createLessThan(Fraction.zero(), otherF) )) {
				blw = constraints.newNamedFraction(node);
				if(constraints.isKnown(thisF)) {
					// thisF must be > 0 in order to coalesce it into an existential
					constraints.addConstraint(FractionConstraint.createLessThan(Fraction.zero(), thisF));
				}
				else {
					// TODO else test on the spot?
					if(log.isLoggable(Level.WARNING))
						log.warning("Cannot ensure " + thisF + " > 0");
				}
				if(constraints.isKnown(otherF)) {
					// otherF must be > 0 in order to coalesce it into an existential
					constraints.addConstraint(FractionConstraint.createLessThan(Fraction.zero(), otherF));
				}
				else {
					// TODO else test on the spot?
					if(log.isLoggable(Level.WARNING))
						log.warning("Cannot ensure " + otherF + " > 0");
				}
			}
			else {
				// constraints weren't greater than zero
				blw = Fraction.zero();
			}
		}
		
		// state information: lowest common denominator between the two given permissions
		Set<String> newState = new LinkedHashSet<String>();
		for(String thisState : this.getStateInfo()) {
			String commonState = null;
			for(String otherState : other.getStateInfo()) {
				// find pair of states in the same state dimension and pick the less precise one
				// commonState should be uniquely defined for a given thisState
				// because the state info sets should not contain states s, t
				// where one is "bigger" than the other.
				if(getStateSpace().firstBiggerThanSecond(otherState, thisState)) {
					assert commonState == null || commonState == otherState;
					commonState = otherState;
				}
				else if(getStateSpace().firstBiggerThanSecond(thisState, otherState)) {
					assert commonState == null || commonState == thisState;
					commonState = thisState;
				}
			}
			if(commonState != null)
				newState.add(commonState);
			// if no commonState is found we can in theory try to find a common
			// super-state implied both by thisState and the states in other.getStateInfo()
			// we're not doing that for now.
		}

		return createPermission(
				this.stateSpace, 
				this.rootNode, 
				FractionFunction.create(fractions, fracts, blw), 
				newMutable, 
				newState,
				constraints);
	}

	/**
	 * This methods splits off from the given permission a permission with the same root
	 * and returns the remaining permission.  The given permissions are unchanged.
	 * If a permission with a different root needs to be split off then the root of the
	 * receiver permission must be moved first, e.g. with {@link #moveDown(String, FractionConstraints)},
	 * except if a pure permission is to be split off with a bigger root than this permission.
	 * @param permission Permission to be split off
	 * @param constraints Constraints arising from the split will be added to this parameter
	 * @return Remaining permission after split.
	 * @see #moveDown(String, FractionConstraints)
	 * @see #moveUp(String, FractionConstraints)
	 * @see FractionalPermissions#splitOff(FractionalPermission)
	 */
	public FractionalPermission splitOff(
			PermissionFromAnnotation permission,
			FractionConstraints constraints) {
		if(!permission.isPure() && !this.rootNode.equals(permission.rootNode))
			// this method can split permissions with matching roots
			throw new IllegalArgumentException("Roots do not match: " + this.rootNode + " vs. " + permission.rootNode);
		if(permission.isPure() && !permission.coversNode(this.rootNode))
			// this method can split permissions with matching roots
			throw new IllegalArgumentException("Root does not imply needed: " + this.rootNode + " vs. " + permission.rootNode);
		// ignore state info: separately checked
//		if(this.stateSpace.firstBiggerThanSecond(permission.stateInfo, this.stateInfo) == false)
//			throw new IllegalArgumentException();
		
		// include constraints for permission being split off
//		permission.registerFractions(constraints);
//		permission.addIsFunctionConstraints(constraints);
//		permission.addIsUsedConstraints(constraints);
		
		FractionFunction f = new FractionFunction(this.stateSpace, this.rootNode);
		
		// constraints for node fractions
		Iterator<String> it = this.stateSpace.stateIterator(this.rootNode);
		while(it.hasNext()) {
			String n = it.next();
			
			// equality constraints between given and new fractions
			FractionSum sum = new FractionSum(permission.fractions.get(n), f.get(n));
			FractionConstraint eq = FractionConstraint.createEquality(this.fractions.get(n), sum);
			constraints.addConstraint(eq);
			// TODO this is inefficient if we're splitting off a pure permission
			// with larger root: could just keep the old fraction (same for below).
		}
		
		// constraint(s) for below fraction
		// the following tests whether this might be share while permission is immutable or vice versa 
		if((permission.isImmutable() || permission.isShare()) && this.mutable != permission.mutable) {
			// this's below fraction must be one in order to switch from immutable to share
			FractionConstraint eqOne = FractionConstraint.createEquality(
					this.fractions.getBelowFraction(), Fraction.one());
			constraints.addConstraint(eqOne);
		}
		FractionSum sum = new FractionSum(permission.fractions.getBelowFraction(), f.getBelowFraction());
		FractionConstraint eq = FractionConstraint.createEquality(this.fractions.getBelowFraction(), sum);
		constraints.addConstraint(eq);
		
		// TODO does this really work?
		boolean remainderMutable = this.mutable && (permission.isPure() || permission.isShare());
		return createPermission(
				this.stateSpace, 
				this.rootNode, 
				f, 
				remainderMutable, 
				this.stateInfo,
				constraints);
	}

	private FractionalPermission createPermission(
			StateSpace newStateSpace,
			String newRootNode, 
			boolean newMutable,
			String... newStateInfo) {
		return new FractionalPermission(newStateSpace, newRootNode, fractions, newMutable, 
				Arrays.asList(newStateInfo));
	}

	private FractionalPermission createPermission(
			StateSpace newStateSpace,
			String newRootNode, 
			boolean newMutable,
			Set<String> newStateInfo) {
		return new FractionalPermission(newStateSpace, newRootNode, fractions, newMutable, newStateInfo);
	}

	private FractionalPermission createPermission(
			StateSpace newStateSpace,
			String newRootNode, 
			FractionFunction newFractions, 
			boolean newMutable,
			Set<String> newStateInfo,
			FractionConstraints constraints) {
		return new FractionalPermission(newStateSpace, newRootNode, newFractions, newMutable, newStateInfo, constraints);
	}

	/**
	 * Takes this permission and returns the result of moving down the root to
	 * the given root node.
	 * First permission in returned list has desired root, additional permissions
	 * may follow.
	 * @param newRootNode
	 * @param constraints
	 * @return
	 */
	public Pair<FractionalPermission, List<FractionalPermission>> moveDown(String newRootNode,
			FractionConstraints constraints) {
		if(coversNode(newRootNode) == false)
			// this method can only move the root down
			throw new IllegalArgumentException(rootNode + " must be bigger than " + newRootNode);
		// state info is not reliable during fraction resolution, therefore ignored
		if(impliesState(newRootNode) == false)
			// state info not sufficient for new root
			throw new IllegalArgumentException("State information " + this.stateInfo + 
					" not sufficient for new root " + newRootNode);
		
		if(newRootNode.equals(rootNode))
			return Pair.create(this, Collections.<FractionalPermission>emptyList());
		
		// constraint for moving down: below fraction has to be 1
		constraints.addConstraint(FractionConstraint.createEquality(
				Fraction.one(), fractions.getBelowFraction()));

		// figure out if we'll need additional permissions (for orthogonal dimensions)
		boolean noSplits = true;
		for(Iterator<String> it = stateSpace.stateIterator(newRootNode); noSplits && it.hasNext(); ) {
			String n = it.next();
			if((n.equals(newRootNode) == false) 
					&& (stateSpace.isDimension(n) == false) 
					&& (stateSpace.getDimensions(n).size() > 1))
				noSplits = false;
			if(n.equals(rootNode))
				break;
		}
		
		if(noSplits) {
			// no permissions for other dimensions needed 
			// --> simplification: reuse old fraction function
			FractionFunction newF = new FractionFunction(fractions, newRootNode, Fraction.one());
			// state info should only change insofar as nodes above newRootNode are dropped (now implicit)
			Set<String> filteredInfo = filterStateInfo(newRootNode);
			// create desired permission
			FractionalPermission wanted = createPermission(
					stateSpace, newRootNode, newF, 
					true /* can only move down full permission */, 
					filteredInfo,  
					constraints);
			return Pair.create(wanted, Collections.<FractionalPermission>emptyList());
		}
		
		// fraction function for desired permission
		FractionFunction newF = 
			FractionFunction.variableRemaining(stateSpace, newRootNode, false, 
					Collections.singletonMap(newRootNode, Fraction.one()), Fraction.one());
		// permissions for other dimensions split off along the way
		List<FractionalPermission> otherPs = new LinkedList<FractionalPermission>();
		String immediateChildNode = null;
		for(Iterator<String> it = stateSpace.stateIterator(newRootNode); it.hasNext(); ) {
			String n = it.next(); 
			// n is current node, immediateChildNode is its 
			// immediate child or null if first iteration (n is newRootNode in that case)
			if(immediateChildNode != null) {
				List<Fraction> fractionsToImmChild = new ArrayList<Fraction>(otherPs.size() + 1);
				fractionsToImmChild.add(newF.get(immediateChildNode));
				for(FractionalPermission otherP : otherPs) {
					fractionsToImmChild.add(otherP.fractions.get(immediateChildNode));
				}
				if(stateSpace.isDimension(immediateChildNode)) {
					// may need permissions for additional dimensions
					Set<String> dims = stateSpace.getDimensions(n);
					boolean foundImmediateChild = false;
					for(String dim : dims) {
						if(dim.equals(immediateChildNode))
							foundImmediateChild = true;
						else if( immediateChildNode.equals(newRootNode) ) {
							// For the actual new root node, we can give EVERY
							// dimension 1 fraction. See the new version of the
							// F-Split-\tens rule. This is sound and allow
							// 'unique permissions to dimensions' to work.
							// 
							FractionFunction otherF = FractionFunction.variableRemaining(stateSpace, 
									dim, false, Collections.singletonMap(dim, Fraction.one()), Fraction.one());
							Set<String> dimInfo = filterStateInfo(dim);
							otherPs.add(createPermission(stateSpace, dim, otherF, true, dimInfo, constraints));
						}
						else {
							// create permission for every dimension other than the one newRootNode is in
							FractionFunction otherF = FractionFunction.variableRemaining(stateSpace, dim, false, 
									Collections.singletonMap(dim, Fraction.one()), Fraction.one());
							Set<String> dimInfo = filterStateInfo(dim);
							otherPs.add(createPermission(stateSpace, dim, otherF, true, dimInfo, constraints));

							fractionsToImmChild.add(otherF.get(dim));
						}
					}
					if(! foundImmediateChild)
						// sanity check
						throw new IllegalStateException(immediateChildNode + " wasn't a dimension in " + n);
				}

//				if( !immediateChildNode.equals(newRootNode) ) {
//					// new constraint: sum of fractions on below's "level" (below node and peer dimensions) must be 1
//					// But only for the non-root-level nodes.
//					// NEB: 1/20/10. I think I wrote this code incorrectly. In this
//					// case, where the new root node is a node below the dimensions
//					// around which the permission will be split, I think that things
//					// only need to sum up to 1 above the newly split dimensions!
//					// That could be arbitrarily high up, right?
//					constraints.addConstraint(FractionConstraint.createEquality(
//							Fraction.one(),
//							FractionTerm.createSum(fractionsToImmChild)));
//				}
			}
			if(n.equals(rootNode))
				break;
			immediateChildNode = n;
		}
		
		// sum of fractions for nodes from rootNode to alive (inclusive) must be 1
		for(Iterator<String> it = stateSpace.stateIterator(rootNode); it.hasNext(); ) {
			String n = it.next();
			List<Fraction> fs = new ArrayList<Fraction>(otherPs.size() + 1);
			fs.add(newF.get(n));
			for(FractionalPermission otherP : otherPs) {
				fs.add(otherP.fractions.get(n));
			}
			constraints.addConstraint(FractionConstraint.createEquality(
					Fraction.one(),
					FractionTerm.createSum(fs)));
		}
		
		// create desired permission
		Set<String> info = filterStateInfo(newRootNode);
		FractionalPermission wanted = createPermission(
				stateSpace, newRootNode, newF, 
				true /* can only move down full permission */, 
				info, constraints);
		
		return Pair.create(wanted, otherPs);
	}

	/**
	 * Extracts the subset of states in stateInfo that is covered by
	 * the given node.
	 * @param newRootNode
	 * @return
	 */
	private Set<String> filterStateInfo(String newRootNode) {
		return AbstractFractionalPermission.filterStateInfo(stateSpace, stateInfo, newRootNode);
	}

	/**
	 * Use this method only to move the root of a <b>single</b> permission "up" in the hierarchy. 
	 * @param newRootNode
	 * @param constraints
	 * @return
	 * @see #combine(Set, StateSpace, String, FractionConstraints)
	 */
	public FractionalPermission moveUp(String newRootNode,
			FractionConstraints constraints) {
		if(stateSpace.firstBiggerThanSecond(newRootNode, rootNode) == false)
			// this method can only move the root up
			throw new IllegalArgumentException(newRootNode + " must be bigger than " + rootNode);
		
		if(rootNode.equals(newRootNode))
			return this;
		
		// constraint for moving up: new root's fraction has to be 1
		constraints.addConstraint(FractionConstraint.createEquality(
				Fraction.one(), fractions.get(newRootNode)));
		
		FractionFunction newF = new FractionFunction(fractions, newRootNode, 
				Fraction.one()); // one will become new below fraction
		return createPermission(stateSpace, newRootNode, newF, true /* can only move up full perm */, stateInfo, constraints);
	}

	/**
	 * Merge this with the given permission, returning the resulting permission.
	 * This and the given permission are not changed.
	 * @param permission
	 * @param constraints
	 * @return A permission resulting from merging this with the given permission.
	 */
	public FractionalPermission mergeIn(FractionalPermission permission,
			FractionConstraints constraints) {
		if(permission instanceof PermissionFromAnnotation &&
				((PermissionFromAnnotation) permission).isPure()) {
			if(! permission.coversNode(this.rootNode))
				throw new IllegalArgumentException("Cannot join permissions with different roots:" + this.rootNode + " vs. " + permission.rootNode);
		}
		else if(this.rootNode.equals(permission.rootNode) == false)
			throw new IllegalArgumentException("Cannot join permissions with different roots:" + this.rootNode + " vs. " + permission.rootNode);

		// function constraints should be added in surrounding call
//		permission.registerFractions(constraints);
//		permission.addIsFunctionConstraints(constraints);
		
		FractionFunction f = new FractionFunction(this.stateSpace, this.rootNode);
		
		// constraints for node fractions
		// TODO which state space?
		Iterator<String> it = this.stateSpace.stateIterator(this.rootNode);
		while(it.hasNext()) {
			String n = it.next();
			FractionSum sum = new FractionSum(
					this.fractions.get(n), 
					permission.fractions.get(n));
			FractionConstraint eq = FractionConstraint.createEquality(f.get(n), sum);
			constraints.addConstraint(eq);
			
			// for constants, this asserts that the combined fractions do not exceed one
			// We put this in when we decided that share permissions from invariants were
			// not being correctly combined.
			constraints.addConstraint(FractionConstraint.createLessThanOrEqual(sum, Fraction.one()));
		}
		
		// constraint for below fraction
		FractionSum sum = new FractionSum(
				this.fractions.getBelowFraction(), 
				permission.fractions.getBelowFraction());
		FractionConstraint eq = FractionConstraint.createEquality(
				f.getBelowFraction(), sum);
		constraints.addConstraint(eq);

		// assert that const permissions all add up to one or less.
		constraints.addConstraint(FractionConstraint.createLessThanOrEqual(sum, Fraction.one()));

		// determine more precise state info from the two permissions
		Set<String> newState = mergeStateInfo(permission.getStateInfo());
//		String newState = permission.getStateInfo();
//		if(getStateSpace().firstBiggerThanSecond(newState, this.getStateInfo()))
//			newState = this.getStateInfo();
		
		return createPermission(
				this.stateSpace, 
				this.rootNode, 
				f, 
				// merging read-only permissions yields read-only--switching to mutable done with additional constraint during splitting
				this.mutable || permission.mutable, 
				newState,
				constraints);
	}

	/**
	 * determine more precise state info from the two permissions.
	 * If state information is contradictory, <code>otherStateInfo</code>
	 * takes precedence.
	 * @param otherStateInfo
	 * @return
	 */
	protected Set<String> mergeStateInfo(Set<String> otherStateInfo) {
		Set<String> newState = new LinkedHashSet<String>(otherStateInfo);
		for(String thisState : this.getStateInfo()) {
			boolean orth = true;
			for(String otherState : otherStateInfo) {
				if(thisState.equals(otherState))
					continue; // don't bother
				if(this.getStateSpace().firstBiggerThanSecond(otherState, thisState)) {
					newState.remove(otherState);  // may have already been removed in a previous iteration
					newState.add(thisState);      // may be able to just leave orth as-is and not add here
					orth = false;
				}
				else if(this.getStateSpace().areOrthogonal(otherState, thisState) == false) {
					if(! this.getStateSpace().firstBiggerThanSecond(thisState, otherState)) {
						if(log.isLoggable(Level.FINER))
							// this will e.g. happen for state tests that cannot succeed
							log.finer("Contradictory state takes precedence: " + otherState + " over " + thisState);
//						newState.remove(otherState);
					}
					orth = false;
				}
			}
			if(orth)
				newState.add(thisState);
		}
		return newState;
	}
	
	/**
	 * Returns a permission with the given state information.
	 * This is in contrast to {@link #addStateInfo(String)} which
	 * <i>includes</i> a new state in the previous state information.
	 * @param stateInfo
	 * @return
	 */
	public FractionalPermission copyNewState(String... newState) {
		return createPermission(stateSpace, rootNode, mutable, newState);
	}

	/**
	 * Returns a permission with the given state information.
	 * This is in contrast to {@link #addStateInfo(String)} which
	 * <i>includes</i> a new state in the previous state information.
	 * @param stateInfo
	 * @return
	 */
	public FractionalPermission copyNewState(Set<String> newState) {
		return createPermission(stateSpace, rootNode, mutable, newState);
	}

	/**
	 * Returns a permission with the given state information included.
	 * This is in contrast to {@link #copyNewState(String...)} which
	 * <i>replaces</i> the previous state information with new states.
	 * @param stateInfo
	 * @return
	 */
	public FractionalPermission addStateInfo(String stateInfo) {
		Set<String> newStateInfo = mergeStateInfo(Collections.singleton(stateInfo));
		return createPermission(stateSpace, rootNode, mutable, newStateInfo);
	}

	/**
	 * This method only adds existential variables and constraints between those.
	 * @param constraints
	 * @return
	 */
	public FractionalPermission makeExistential(
			final FractionConstraints constraints, FractionConstraints comparisonConstraints, final ASTNode node) {
		// for efficiency, simplify constraints once in the beginning
		// DO NOT CHANGE comparisonConstraints AFTER THIS POINT without re-assigning a
		FractionAssignment a = comparisonConstraints.simplify();
		
		HashMap<String, Fraction> newFs = new HashMap<String, Fraction>();
		
		final FractionVisitor<Fraction> existentialize = new FractionVisitor<Fraction>() {
			@Override public Fraction named(NamedFraction fract) {
				if(constraints.isKnown(fract))
					return fract;
				return constraints.newNamedFraction(node);
			}
			@Override public Fraction one(OneFraction fract) {
				return fract;
			}
			@Override public Fraction var(VariableFraction fract) {
				return constraints.newNamedFraction(node);
			}
			@Override public Fraction zero(ZeroFraction fract) {
				return fract;
			}
		};
		
		// abstracting fraction function
		Iterator<String> it = this.stateSpace.stateIterator(this.rootNode);
		while(it.hasNext()) {
			String n = it.next();
			Fraction constF = a.getConstant(this.fractions.get(n));
			Fraction newF = constF != null ? constF.dispatch(existentialize) : constraints.newNamedFraction(node);
			newFs.put(n, newF);
		}
		Fraction constBelow = a.getConstant(this.fractions.getBelowFraction());
		Fraction newBelow = constBelow != null ? constBelow.dispatch(existentialize) : constraints.newNamedFraction(node);
		
		FractionFunction newF = FractionFunction.create(this.fractions, newFs, newBelow);
		return createPermission(stateSpace, rootNode, newF, newBelow.isOne() ? true : mutable, stateInfo, constraints);
	}

	/**
	 * Drops all state info except marker states.
	 * @return
	 */
	public FractionalPermission forgetStateInfo() {
		LinkedHashSet<String> newStateInfo = new LinkedHashSet<String>();
		for(String s : stateInfo) {
			if(stateSpace.isMarker(s))
				// TODO what about non-marker substates or marker states?
				newStateInfo.add(s);
			if(s.equals(rootNode))
				continue;
		}
		if(newStateInfo.isEmpty())
			newStateInfo.add(rootNode);
		if(newStateInfo.containsAll(stateInfo))
			return this;
		else
			return createPermission(stateSpace, rootNode, mutable, newStateInfo);
	}

	/**
	 * Creates a modifiable permission based on this one, possibly adding constraints.
	 * @param constraints
	 * @return a modifiable permission based on this one.
	 */
	public FractionalPermission makeModifiable(FractionConstraints constraints) {
		if(mutable)
			return this;
		constraints.addConstraint(FractionConstraint.createEquality(Fraction.one(), fractions.getBelowFraction()));
		return createPermission(stateSpace, rootNode, true, stateInfo);
	}

	/**
	 * Creates a permission based on this one but with the given state information.
	 * This method can be too harsh because it drops all existing state information.
	 * @param stateInfo
	 * @return
	 */
	public FractionalPermission replaceStateInfo(Set<String> stateInfo) {
		Set<String> newStates = null; 
		for(String s : stateInfo) {
			if(getStateSpace().areOrthogonal(getRootNode(), s))
				// doesn't affect this permission
				continue;
			if(newStates == null)
				// need to drop old state info
				newStates = new LinkedHashSet<String>();
			// generally, s should be a subnode of rootNode
			// but for pure permissions it could be above, in which case we ignore it
			if(coversNode(s))
				newStates.add(s);
		}
		
		if(newStates == null)
			// unaffected --> keep
			return this;
		else
			// create permission with new states
			return copyNewState(newStates);
	}
	
	/**
	 * Resolves the given constraints to figure out what kind of permission
	 * this is.
	 * @param constraints
	 * @return This permission's kind or <code>null</code> if this is not
	 * a real permission.
	 */
	public PermissionKind getKind(FractionConstraints constraints) {
		FractionAssignment a = constraints.simplify();
		Fraction rootF = getFractions().get(getRootNode());
		if(a.isZero(rootF))
			// root is zero -> not a real permission
			return null;
		
		Fraction belowF = getFractions().getBelowFraction();
		if(a.isOne(rootF) && a.isOne(belowF))
			// readonly flag can be true or false for UNIQUE
			return PermissionKind.UNIQUE;
		if(a.isOne(belowF))
			// readonly flag can be true or false for FULL
			return PermissionKind.FULL;
		if(a.isZero(belowF)) {
			if(! isReadOnly())
				log.warning("Pure permission with modifying flag found: " + toString());
			return PermissionKind.PURE;
		}
		return isReadOnly() ? PermissionKind.IMMUTABLE : PermissionKind.SHARE; 
	}

	/*
	 * this method is final b/c it delegates the real work to getKind(constraints)
	 */
	public final String getUserString(FractionConstraints constraints) {
		PermissionKind kind = getKind(constraints);
		if(kind == null)
			// not a real permission -> return null
			return null;
		if(getRootNode().equals(getStateSpace().getRootState()))
			return kind + " in " + getStateInfo();
		else
			return kind + "(" + getRootNode() + ") in " + getStateInfo();
	}

}
