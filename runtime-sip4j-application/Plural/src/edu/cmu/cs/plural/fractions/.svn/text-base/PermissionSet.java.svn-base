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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jdt.core.dom.ASTNode;

import edu.cmu.cs.crystal.util.Pair;
import edu.cmu.cs.plural.states.StateSpace;

/**
 * @author Kevin
 *
 */
class PermissionSet {
	
	private static final Logger log = Logger.getLogger(PermissionSet.class.getName());

	/**
	 * Tests whether the first given list is more precise than the second list of permissions.
	 * @param thisPermissions
	 * @param otherPermissions
	 * @param node
	 * @param constraints
	 * @return
	 */
	static boolean atLeastAsPrecise(
			List<FractionalPermission> thisPermissions, List<FractionalPermission> otherPermissions,
			ASTNode node, FractionConstraints constraints) {
		// shortcuts for empty sets (consistent with nested loop implementation below)
		if(otherPermissions.isEmpty())
			// any thisPermissions will do
			return true;
		if(thisPermissions.isEmpty())
			// otherPermissions is not empty --> thisPermissions less precise
			return false;
		
		nextPermission:
		for(FractionalPermission otherP : otherPermissions) {
			for(FractionalPermission thisP : thisPermissions) {
				if(thisP.getRootNode().equals(otherP.getRootNode())) {
					// exact root node match
					if(thisP.atLeastAsPrecise(otherP, node, constraints))
						continue nextPermission;
					else
						return false;
				}
				// no permission in this with the same root --> check to move roots up / down
				if(otherP.getStateSpace().firstBiggerThanSecond(thisP.getRootNode(), otherP.getRootNode())) {
					// test if this can move root down
					if(thisP.atLeastAsPrecise(otherP, node, constraints))
						continue nextPermission;
					else
						// thisP is the only permission in this that's bigger than otherP --> fail
						return false;
				}
				if(thisP.getStateSpace().firstBiggerThanSecond(otherP.getRootNode(), thisP.getRootNode())) {
					// test if this can move root up
					// TODO collect all permissions with root inside otherP's root node and compare them bulk
					// see splitOff() for a way to do this (semi-)efficiently
					if(thisP.atLeastAsPrecise(otherP, node, constraints))
						continue nextPermission;
				}
			}
			// no permission in this that could be used in place of otherP
			return false;
		}
		// there might be additional permissions in this that are not in other, but that's considered more precise
		return true;
	}
	
	/**
	 * Returns a list with the permissions in the given lists joined.  The returned list is
	 * either a whole-new list or one of the given lists.  The given lists are not modified.
	 * @param thisPermissions
	 * @param otherPermissions
	 * @param node
	 * @param constraints
	 * @param symmetric
	 * @param comparisonConstraints
	 * @return a list with the permissions in the given lists joined.
	 */
	static List<FractionalPermission> join(List<FractionalPermission> thisPermissions, 
			List<FractionalPermission> otherPermissions, 
			ASTNode node, FractionConstraints constraints, 
			boolean symmetric, FractionConstraints comparisonConstraints) {
		// shortcuts for empty lists: joining an empty list with any list will result in an empty list
		if(thisPermissions.isEmpty())
			return thisPermissions;
		if(otherPermissions.isEmpty())
			return otherPermissions;
		
		ArrayList<FractionalPermission> newPermissions = new ArrayList<FractionalPermission>();
		HashMap<FractionalPermission, FractionalPermission> thisCombine = new HashMap<FractionalPermission, FractionalPermission>();
		for(FractionalPermission thisP : thisPermissions) {
			String thisRoot = thisP.getRootNode();
			List<Fraction> otherFractions = new LinkedList<Fraction>();
			HashSet<FractionalPermission> otherCombine = new HashSet<FractionalPermission>();
			for(FractionalPermission otherP : otherPermissions) {
				if(thisRoot.equals(otherP.getRootNode())) {
					FractionalPermission newP = thisP.join(otherP, node, constraints, comparisonConstraints);
					newPermissions.add(newP);
				}
				else if(thisP.getStateSpace().firstBiggerThanSecond(thisRoot, otherP.getRootNode())) {
					otherCombine.add(otherP);
					otherFractions.add(otherP.getFractions().get(thisRoot));
				}
				else if(otherP.getStateSpace().firstBiggerThanSecond(otherP.getRootNode(), thisRoot)) {
					thisCombine.put(thisP, otherP);
				}
			}
			if(otherCombine.isEmpty() == false) {
				// can we move the roots of otherCombine up?
				if(comparisonConstraints.testConstraint(FractionConstraint.createEquality(
						FractionTerm.createSum(otherFractions), Fraction.one()))) {
					if(symmetric) {
						// TODO always combine but don't modify constraints in the asymmetric case
						FractionalPermission otherP = FractionalPermission.combine(otherCombine, thisP.getStateSpace(), thisRoot, constraints);
						newPermissions.add(thisP.join(otherP, node, constraints, comparisonConstraints));
					}
					else {
						// thisP is, if anything, weaker than otherCombine, so it's safe to just use it
						newPermissions.add(thisP.makeExistential(constraints, comparisonConstraints, node));
					}
				}
				// can we move thisP's root down?
				else if(constraints.testConstraint(FractionConstraint.createEquality(
						thisP.getFractions().getBelowFraction(), Fraction.one()))) {
					// permissions in otherCombine are, if anything, weaker than thisP with root moved down
					// TODO could move thisP's down to the roots of the permissions in otherCombine and join there
					for(FractionalPermission otherP : otherCombine) {
						newPermissions.add(otherP.makeExistential(constraints, comparisonConstraints, node));
					}
				}
				else
					; // can't join thisP with otherCombine --> do nothing
			}
		}
		// try to join smaller permissions from this with larger permissions from other
		while(thisCombine.isEmpty() == false) {
			FractionalPermission otherP = null;
			HashSet<FractionalPermission> combine = new HashSet<FractionalPermission>();
			StateSpace combinedSpace = null;
			List<Fraction> fractions = new LinkedList<Fraction>();
			for(Iterator<FractionalPermission> thisIt = thisCombine.keySet().iterator(); thisIt.hasNext(); ) {
				FractionalPermission thisP = thisIt.next();
				if(otherP == null) otherP = thisCombine.get(thisP);
				if(otherP.equals(thisCombine.get(thisP))) {
					combine.add(thisP);
					fractions.add(thisP.getFractions().get(otherP.getRootNode()));
					combinedSpace = thisP.getStateSpace();
					thisIt.remove();
				}
			}
			if(otherP == null) continue; // this should never happen
			assert combinedSpace != null;
			
			// can we move the roots of combine up?
			if(constraints.testConstraint(FractionConstraint.createEquality(
					FractionTerm.createSum(fractions), Fraction.one()))) {
				FractionalPermission thisP = FractionalPermission.combine(combine, combinedSpace, otherP.getRootNode(), constraints);
				newPermissions.add(thisP.join(otherP, node, constraints, comparisonConstraints));
			}
			// can we move otherP's root down?
			else if(comparisonConstraints.testConstraint(FractionConstraint.createEquality(
					otherP.getFractions().getBelowFraction(), Fraction.one()))) {
				// permissions in combine are, if anything, weaker than otherP with root moved down
				// TODO could move thisP's down to the roots of the permissions in otherCombine and join there
				for(FractionalPermission thisP : combine) {
					newPermissions.add(thisP.makeExistential(constraints, comparisonConstraints, node));
				}
			}
			else
				; // can't join otherP with combine --> do nothing
		}
		return newPermissions;
	}
	
	/**
	 * Removes a permission with the given root from the given list, <b>modifying the given list</b>.
	 * @param newPs Modifiable list of permissions to remove a permission with the 
	 * <code>neededRoot</code> from.
	 * @param neededRoot
	 * @param tolerateSmaller
	 * @param constraints
	 * @return a permission with the given root that was removed from the given list or
	 * <code>null</code> if no such permission was available.
	 */
	static FractionalPermission removePermission(List<FractionalPermission> newPs, 
			String neededRoot, boolean tolerateSmaller, FractionConstraints constraints) {
		HashSet<FractionalPermission> combine = new HashSet<FractionalPermission>();
		StateSpace combineSpace = null;
		for(FractionalPermission p : newPs) {
			if(p.getRootNode().equals(neededRoot) ||
					(tolerateSmaller && p.getStateSpace().firstBiggerThanSecond(neededRoot, p.getRootNode()))
					) {
				// root match or p's root is smaller than needed and that's ok
				newPs.remove(p);
				return p;
			}
			else if(p.getStateSpace().firstBiggerThanSecond(p.getRootNode(), neededRoot)
//					&& p.getStateSpace().firstBiggerThanSecond(neededStateInfo, p.getStateInfo())) {
					&& p.impliesState(neededRoot)) {
				// can move p's root down to match needed root
				Pair<FractionalPermission, List<FractionalPermission>> ps = p.moveDown(neededRoot, constraints);
				// create new permissions list
				newPs.remove(p);
				newPs.addAll(ps.snd());
				return ps.fst();
			}
			else if(p.getStateSpace().firstBiggerThanSecond(neededRoot, p.getRootNode())) {
				// collect all permissions that can move root up to match needed root
				combine.add(p);
				combineSpace = p.getStateSpace();
			}
		}
		if(combine.isEmpty()) {
			// couldn't match needed root
			return null;
		}
		else {
			// found permissions to combine
			FractionalPermission up = FractionalPermission.combine(
					combine, combineSpace, neededRoot, constraints);
			// make sure we have sufficient state info
			// create new permissions list
			newPs.removeAll(combine);
			return up;
		}
	}
	
	/**
	 * Forget stateInfo for permissions that either must be pure or must be share.
	 * Returns a new list with those modified permissions, without changing the
	 * original list.
	 * @see #withoutStateInfo(List)
	 */
	static List<FractionalPermission> forgetShareAndPureStates(List<FractionalPermission> permissions, FractionConstraints constraints) {
		if(permissions.isEmpty())
			// nothing to do
			return permissions;
		
		ArrayList<FractionalPermission> newPs = new ArrayList<FractionalPermission>(permissions.size());
		FractionAssignment assignment = constraints.simplify();
		for(FractionalPermission p : permissions) {
			if((p.isReadOnly() == false) && (assignment.isOne(p.getFractions().getBelowFraction()) == false))
				// share
				// TODO what if below fraction may or may not be one?
				p = p.forgetStateInfo();
			else if(p.isReadOnly() && assignment.isZero(p.getFractions().getBelowFraction()))
				// pure
				// TODO what if below fraction may or may not be zero?
				p = p.forgetStateInfo();
			newPs.add(p);
		}
		return newPs;
	}
	
	/**
	 * Returns a list with permissions based on the given list but additionally with the
	 * given state info.  The returned list is either the given list or a whole-new list.
	 * The given list is not modified.
	 * @param permissions
	 * @param stateInfo
	 * @return a list with permissions based on the given list but additionally with the
	 * given state info.
	 */
	static List<FractionalPermission> learnStateInfo(List<FractionalPermission> permissions, String stateInfo) {
		if(permissions.isEmpty())
			// nothing to do
			return permissions;
		
		/*
		 * Build a new fractional permissions where we copy each permission
		 * with the new state. Use the ___ method to determine which states
		 * we can actually overwrite. 
		 */
		List<FractionalPermission> new_permissions = 
			new ArrayList<FractionalPermission>(permissions.size());
		
		for(FractionalPermission p : permissions) {
			if( p.coversNode(stateInfo) ) {
				/*
				 * The new state is a sub-state of this permission's state.
				 * Include the new_state into p's state information
				 */
				new_permissions.add(p.addStateInfo(stateInfo));
			}
			else
				new_permissions.add(p);
		}
		
		return new_permissions;
	}
	
	/**
	 * Merges the given permission into the given list, <b>modifying the list</b>.
	 * @param newPs Modifiable list to merge <code>permission</code> into.  
	 * @param permission
	 * @param tolerateSmaller I
	 * @param constraints
	 * @return <code>true</code> if the merge succeeded, <code>false</code> if the given
	 * permission could not be merged in because it was incompatible with the existing permissions.
	 */
	static boolean mergeInPermission(List<FractionalPermission> newPs, 
			FractionalPermission permission, boolean tolerateSmaller, FractionConstraints constraints) {
		if(newPs.isEmpty()) {
			// if newPs is empty then we can just add the given permission
			newPs.add(permission);
			return true;
		}
		
		String neededRoot = permission.getRootNode();
		for(FractionalPermission p : newPs) {
			if(p.getRootNode().equals(neededRoot) ||
					(tolerateSmaller && permission.coversNode(p.getRootNode()))) {
				// eager merging of permissions for same root
				FractionalPermission newP = p.mergeIn(permission, constraints);
				// manipulate permissions list
				newPs.remove(p);
				newPs.add(newP);
				return true;
			}
			else if( !permission.getStateSpace().areOrthogonal(p.getRootNode(), permission.getRootNode()) ) {
				// this is an error: presented a permission for a mutually exclusive node, or
				// a node above or below p's root--permission and p cannot co-exist
				constraints.addConstraint(FractionConstraint.impossible());
				if(log.isLoggable(Level.FINE))
					log.fine("Tried to merge in a permission with a conflicting root to an existing permission: " + permission.getRootNode() + " vs. " + p.getRootNode());
				// preserve invariant that only permissions with orthogonal roots can co-exist: drop permission
				return false;
			}
		}
		// nothing to combine with --> just add to the list
		newPs.add(permission);
		return true;
	}

	/**
	 * Returns a list of permissions that is based on the given permissions but with
	 * all state information removed.  The returned list may be the given list.
	 * @param permissions
	 * @return a list of permissions that is based on the given permissions but with
	 * all state information removed.
	 * @see #forgetShareAndPureStates(List, FractionConstraints)
	 */
	static List<FractionalPermission> withoutStateInfo(
			List<FractionalPermission> permissions) {
		if(permissions.isEmpty())
			// nothing to do
			return permissions;
		
		ArrayList<FractionalPermission> newPs = new ArrayList<FractionalPermission>(permissions.size());
		for(FractionalPermission p : permissions) {
			newPs.add(p.forgetStateInfo());
		}
		return newPs;
	}
	
	static List<FractionalPermission> replaceStateInfo(
			List<FractionalPermission> permissions,
			Set<String> stateInfo) {
		List<FractionalPermission> newPs;
		if(permissions.isEmpty())
			newPs = permissions;
		else {
			newPs = new ArrayList<FractionalPermission>(permissions.size());
			for(FractionalPermission p : permissions)
				newPs.add(p.replaceStateInfo(stateInfo));
		}
		return newPs;
	}
	
}
