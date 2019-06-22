/**
 * Copyright (C) 2007-2009 Carnegie Mellon University and others.
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
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import edu.cmu.cs.crystal.util.Pair;

/**
 * This class represents a permission set that treats virtual and frame permissions
 * interchangeably.  This is useful for tracking permissions of a virtual method receiver frame.
 * 
 * The class is implemented as an extension to {@link FractionalPermissions} where
 * {@link AbstractFractionalPermissionSet#permissions} equal 
 * {@link AbstractFractionalPermissionSet#framePermissions}.
 * This strategy makes inherited "query" methods such as {@link #getStateInfo(boolean)}
 * work correctly.
 * Every method that creates new from existing permission sets must be overridden 
 * to only use {@link AbstractFractionalPermissionSet#framePermissions}.  
 * The new methods usually look almost the same as to overridden ones.
 * Methods related to packing and unpacking are indeed identical.
 * @author Kevin Bierhoff
 * @since Mar 21, 2009
 */
public class VirtualFramePermissionSet extends FractionalPermissions implements ObjectPermissionSet {

//	/**
//	 * Immutable list of frame permissions, must have orthogonal roots.
//	 * @see StateSpace#areOrthogonal(String, String)
//	 */
//	private final List<FractionalPermission> framePermissions;		 // immutable
//	
//	/**
//	 * Frozen constraints.
//	 * @see FractionConstraints#freeze()
//	 */
//	private final FractionConstraints constraints; // frozen; make a mutable copy to modify
//
//	private final StateSpace stateSpace;
//
//	private FractionalPermission unpackedPermission;
	
	public static VirtualFramePermissionSet createEmpty() {
		return new VirtualFramePermissionSet();
	}
	
	/**
	 * Creates an empty permission set.
	 */
	private VirtualFramePermissionSet() {
		super();
	}

	private VirtualFramePermissionSet(List<? extends FractionalPermission> permissions,
			FractionConstraints constraints) {
		// use the same permissions for frame and virtual
		super(permissions, permissions, constraints);
	}

	private VirtualFramePermissionSet(List<? extends FractionalPermission> permissions,
			FractionConstraints constraints, FractionalPermission unpacked_perm) {
		// use the same permissions for frame and virtual
		super(permissions, permissions, constraints, unpacked_perm);
	}

	/**
	 * Creates a new permission set with the given parameters.
	 * @param newFramePermissions
	 * @param new_cs
	 * @param unpacked_perm
	 * @return
	 */
	private VirtualFramePermissionSet createPermissions(
			List<? extends FractionalPermission> newFramePermissions,
			FractionConstraints new_cs, FractionalPermission unpacked_perm) {
		assert this.getClass().equals(VirtualFramePermissionSet.class);
		return new VirtualFramePermissionSet(newFramePermissions, new_cs, unpacked_perm);
	}

	/**
	 * Creates a new permission set with the same unpacked permission as the receiver.
	 * @param newFramePermissions
	 * @param newConstraints
	 * @return
	 */
	private VirtualFramePermissionSet createPermissions(
			List<? extends FractionalPermission> newFramePermissions,
			FractionConstraints newConstraints) {
		assert this.getClass().equals(VirtualFramePermissionSet.class);
		return new VirtualFramePermissionSet(newFramePermissions, newConstraints,
				getUnpackedPermission());
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	public boolean atLeastAsPrecise(FractionalPermissions other, ASTNode node) {
		assert other instanceof VirtualFramePermissionSet : 
			"Cannot compare with " + other.getClass();
		if(other == this || this.isBottom()) 
			return true;
		
		if(other == null || other.isBottom())
			// treat null as equivalent to bottom
			return false;
		
		if(! other.constraints.seemsConsistent())
			return true;
		if(! this.constraints.seemsConsistent())
			return false;
		
		FractionConstraints constraints = this.constraints.concat(other.constraints).freeze();
		
		/*
		 * For unpacked permission.
		 */
		if( this.getUnpackedPermission() != other.getUnpackedPermission() ) { 
			if( this.getUnpackedPermission() == null || other.getUnpackedPermission() == null ) {
				return false;
			}
			else if(
					!this.getUnpackedPermission().getRootNode().equals(other.getUnpackedPermission().getRootNode()) ||				
					!this.getUnpackedPermission().atLeastAsPrecise(other.getUnpackedPermission(), node, constraints) ||
					!other.getUnpackedPermission().atLeastAsPrecise(this.getUnpackedPermission(), node, constraints)) {
				// unpacked permissions must be "equal"
				return false;
			}
		}
		
		if(! PermissionSet.atLeastAsPrecise(this.framePermissions, other.framePermissions, node, constraints))
			return false;
		return true;
	}

	/*
	 * This method is only overridden to strengthen the method return type
	 */
	public VirtualFramePermissionSet copy() {
		// this is an immutable class
		return this;
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	protected VirtualFramePermissionSet join(
			FractionalPermissions other, ASTNode node,
			FractionConstraints constraints,
			boolean symmetric) {
		assert other instanceof VirtualFramePermissionSet : "Cannot join with " + other.getClass();
		
		FractionConstraints comparisonConstraints;
		if(symmetric)
			comparisonConstraints = constraints.mutableCopy();
		else
			comparisonConstraints = constraints.concat(other.constraints);
		comparisonConstraints.freeze();
		
		List<FractionalPermission> newFramePermissions = PermissionSet.join(
				this.framePermissions, other.framePermissions, node, constraints, symmetric, comparisonConstraints);
		
		/*
		 * Do not tolerate differences in unpacked permission: must be resolved before join
		 */
		if(! ((this.getUnpackedPermission() == null && other.getUnpackedPermission() == null) ||
				(this.getUnpackedPermission() != null && other.getUnpackedPermission() != null &&
						this.getUnpackedPermission().atLeastAsPrecise(other.getUnpackedPermission(), node, constraints) &&
						other.getUnpackedPermission().atLeastAsPrecise(this.getUnpackedPermission(), node, constraints))) )
			throw new IllegalArgumentException("Unpacked permissions not equivalent: " + this.getUnpackedPermission() +
					" vs. " + other.getUnpackedPermission());
		
		return this.createPermissions(newFramePermissions, constraints);
	}
	
	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	public FractionalPermissions createReplacement(PermissionSetFromAnnotations initial) {
		FractionConstraints newCs = initial.getConstraints().mutableCopy();
		List<? extends FractionalPermission> newFramePs = 
			mergeInPermissions(initial.getPermissions(), 
					initial.getFramePermissions(), 
					newCs); 
		
		return createPermissions(newFramePs, newCs);
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	public FractionalPermissions forgetShareAndPureStates() {
		if(isBottom())
			return this;
		List<FractionalPermission> newFramePs = 
			PermissionSet.forgetShareAndPureStates(framePermissions, constraints);
		
		// original constraints unchanged
		return createPermissions(newFramePs, constraints);
	}

	/*
	 * This is a code clone from the overridden superclass method 
	 * to make sure an object of the right type is constructed.
	 */
	@Override
	public FractionalPermissions invalidPack() {
		if(isBottom())
			throw new IllegalStateException("Bottom cannot be invalid");
		FractionConstraints newCs = constraints.mutableCopy();
		newCs.addConstraint(FractionConstraint.impossible("Couldn't pack"));
		return createPermissions(framePermissions, newCs, null);
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	public FractionalPermissions learnTemporaryStateInfo(String new_state,
			boolean forFrame) {
		if(isBottom())
			return this;

		// ignore forFrame...
		List<FractionalPermission> new_permissions = 
			PermissionSet.learnStateInfo(framePermissions, new_state);
		return createPermissions(new_permissions, constraints);
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	protected FractionalPermissions makeModifiable(String neededRoot, boolean inFrame,
			FractionConstraints constraints) {
		// ignore inFrame...
		ArrayList<FractionalPermission> newPs = new ArrayList<FractionalPermission>(framePermissions);
		FractionalPermission p = PermissionSet.removePermission(newPs, neededRoot, false, constraints);
		if(p == null)
			constraints.addConstraint(FractionConstraint.impossible("No permission for available for root " + neededRoot));
		else {
			p = p.makeModifiable(constraints);
			newPs.add(p);
		}
		
		return createPermissions(newPs, constraints);
	}

	/*
	 * This is a code clone from the overridden superclass method 
	 * to make sure an object of the right type is constructed.
	 */
	@Override
	public FractionalPermissions makeUnpackedPermissionModifiable() {
		if(getUnpackedPermission() == null) 
			throw new IllegalStateException("Object not unpacked.");
		
		if(getUnpackedPermission().isReadOnly()) {
			FractionConstraints newCs = constraints.mutableCopy();
			return createPermissions(framePermissions, newCs, getUnpackedPermission().makeModifiable(newCs));
		}
		else
			return this;
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	protected FractionalPermissions mergeIn(
			PermissionSetFromAnnotations permissionsToMergeIn,
			FractionConstraints constraints) {
		// add in constraints about incoming permissions
		constraints.addAll(permissionsToMergeIn.getConstraints());
		// new permission list
		// merge frame and virtual permissions into one set
		List<? extends FractionalPermission> newPs = 
			mergeInPermissions(framePermissions, permissionsToMergeIn.getPermissions(), constraints); 
		List<? extends FractionalPermission> newFramePs = 
			mergeInPermissions(newPs, permissionsToMergeIn.getFramePermissions(), constraints); 
		
		return createPermissions(newFramePs, constraints);
	}

	/*
	 * This is a code clone from the overridden superclass method 
	 * to make sure an object of the right type is constructed.
	 */
	@Override
	public FractionalPermissions pack(Set<String> desiredState) {
		if(isBottom())
			throw new IllegalStateException();
		FractionalPermission new_rcvr_perm =
			this.getUnpackedPermission().copyNewState(desiredState);
		List<FractionalPermission> newPs = new ArrayList<FractionalPermission>(framePermissions);
		FractionConstraints constraints = this.constraints.mutableCopy();
		PermissionSet.mergeInPermission(newPs, new_rcvr_perm, false, constraints);
		
		return createPermissions(newPs, constraints, null);
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	public FractionalPermissions replaceStateInfo(
			Pair<Set<String>, Set<String>> stateInfo) {
		if(isBottom())
			// do nothing
			return this;
		assert getUnpackedPermission() == null;

		if(framePermissions.isEmpty())
			// shortcut
			return this;
		
		Set<String> allInfo = new LinkedHashSet<String>(stateInfo.fst());
		allInfo.addAll(stateInfo.snd());
		List<FractionalPermission> newFramePs =
			PermissionSet.replaceStateInfo(framePermissions, allInfo);
		return createPermissions(newFramePs, constraints);
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	public FractionalPermissions replaceStateInfo(Set<String> stateInfo,
			boolean inFrame) {
		if(isBottom())
			// do nothing
			return this;
		assert getUnpackedPermission() == null;
		
		// ignore inFrame
		if(framePermissions.isEmpty())
			return this;
		
		List<FractionalPermission> newFramePs = 
			PermissionSet.replaceStateInfo(framePermissions, stateInfo);
		return createPermissions(newFramePs, constraints);
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	protected FractionalPermissions splitOff(
			PermissionSetFromAnnotations permissionsToSplitOff,
			FractionConstraints constraints) {
		// add in constraints about incoming permissions
		constraints.addAll(permissionsToSplitOff.getConstraints());

		// split everything off of framePermissions
		List<FractionalPermission> newPs = 
			splitOffPermissions(framePermissions, permissionsToSplitOff.getPermissions(), constraints);
		List<FractionalPermission> newFramePs = 
			splitOffPermissions(newPs, permissionsToSplitOff.getFramePermissions(), constraints);
		
		return createPermissions(newFramePs, constraints);
	}

	/*
	 * This is a code clone from the overridden superclass method 
	 * to make sure an object of the right type is constructed.
	 */
	@Override
	public FractionalPermissions unpack(String neededRoot) {
		if( getUnpackedPermission() != null )
			throw new IllegalStateException("Double unpack. Not cool.");

		FractionConstraints new_cs = constraints.mutableCopy();
		FractionalPermission unpacked_perm;
		List<FractionalPermission> notunpacked_perms;
		
		if(isBottom() || framePermissions.isEmpty()) {
			unpacked_perm = null; // will cause unpack failure below
			notunpacked_perms = Collections.emptyList();
		}
		else {
			notunpacked_perms = new ArrayList<FractionalPermission>(framePermissions);
			unpacked_perm = 
				PermissionSet.removePermission(notunpacked_perms, neededRoot, false, new_cs); 
		}

		/*
		 * By this point, we HOPE we have a permission and constraints.
		 */
		if( unpacked_perm != null ) {
			assert(new_cs != null);
			unpacked_perm.addIsUsedConstraint(new_cs);
			return 
				this.createPermissions(notunpacked_perms, new_cs, unpacked_perm);
		}
		/*
		 * In this case, we have already failed. Just create a totally unsat permission.
		 */
		new_cs.addConstraint(FractionConstraint.impossible("No permission available to unpack to " + neededRoot));
		unpacked_perm = new FractionalPermission(this.getStateSpace(),
				neededRoot, FractionFunction.variableAll(this.getStateSpace(),
						neededRoot, false),
						true, Collections.<String>emptySet(), new_cs);
		
		return this.createPermissions(notunpacked_perms, new_cs, unpacked_perm);
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	public FractionalPermissions withoutStateInfo() {
		if(isBottom())
			return this;
		List<FractionalPermission> newFramePs = 
			PermissionSet.withoutStateInfo(framePermissions);
		
		// original constraints unchanged
		return createPermissions(newFramePs, constraints);
	}

	/*
	 * This is a simplified version of the superclass method 
	 * that only manipulates framePermissions.
	 */
	@Override
	public String getUserString() {
		if(isBottom())
			return "BOTTOM";

		String fs = getUserString(framePermissions);
		String us = null;
		if(getUnpackedPermission() != null) {
			us = getUnpackedPermission().getUserString(constraints);
			if(us == null)
				// unpacked permission is not real -> weird
				us = "NOTHING";
		}

		String inSets; 
		if(fs != null)
			inSets = "virtual frame " + fs;
		else if(us == null)
			return "NOTHING";
		else
			return "unpacked[" + us + "]";
		if(us == null)
			return inSets;
		else
			return "unpacked[" + us + "] + " + inSets; 
	}

	@Override
	public String toString() {
		if(getUnpackedPermission() == null)
			return "virtual frame " + framePermissions + " with " + constraints;
		return "unpacked[" + getUnpackedPermission() + "] + " + " virtual frame " + framePermissions + " with " + constraints;
	}

}
