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
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import edu.cmu.cs.crystal.bridge.LatticeElement;
import edu.cmu.cs.crystal.util.Pair;
import edu.cmu.cs.plural.states.StateSpace;

/**
 * Permission set representing lattice information for an object, to be tracked by a flow analysis.
 * @author Kevin Bierhoff
 */
public class FractionalPermissions 
extends AbstractFractionalPermissionSet<FractionalPermission> 
implements LatticeElement<FractionalPermissions>, ObjectPermissionSet {
	
	/** Singleton bottom element */
	private static final FractionalPermissions BOTTOM = new FractionalPermissions(null, false);
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(FractionalPermissions.class.getName());

	private final FractionalPermission unpackedPermission;
	
//	private final Map<String, Aliasing> parameters;
//	private final Map<Aliasing, PermissionSetFromAnnotations> parameterPermissions;

	/** This field "caches" the state space. */
	private StateSpace stateSpace;
	
	public static FractionalPermissions bottom() {
		return BOTTOM;
	}
	
	public static FractionalPermissions createEmpty() {
		return new FractionalPermissions();
	}

	protected FractionalPermissions() {
		super();
//		this.parameters = Collections.emptyMap();
//		this.parameterPermissions = Collections.emptyMap();
		this.unpackedPermission = null;
	}
	
	/**
	 * This constructor can be used to create bottom by passing in <code>null</code> permissions.
	 * @param permissions
	 */
	protected FractionalPermissions(List<? extends FractionalPermission> permissions, 
			boolean isNamedUniversal) {
		super(permissions, isNamedUniversal);
//		this.parameters = Collections.emptyMap();
//		this.parameterPermissions = Collections.emptyMap();
		this.unpackedPermission = null;
	}
	
	/**
	 * This constructor is to turn permissions from annotations into 
	 * a regular permission set.
	 * @param permissions
	 * @param framePermissions 
	 * @param constraints
	 * @see PermissionSetFromAnnotations#toLatticeElement()
	 */
	protected FractionalPermissions(
			List<? extends FractionalPermission> permissions,
			List<? extends FractionalPermission> framePermissions, 
			FractionConstraints constraints) {
		super(permissions, framePermissions, constraints);
//		this.parameters = Collections.emptyMap();
//		this.parameterPermissions = Collections.emptyMap();
		this.unpackedPermission = null;
	}
			
	protected FractionalPermissions(
			List<? extends FractionalPermission> permissions,
			List<? extends FractionalPermission> framePermissions, 
			FractionConstraints constraints,
//			Map<String, Aliasing> parameters,
//			Map<Aliasing, PermissionSetFromAnnotations> parameterPermissions,
			FractionalPermission unpackedPermission) {
		super(permissions, framePermissions, constraints);
//		this.parameters = Collections.unmodifiableMap(parameters);
//		this.parameterPermissions = Collections.unmodifiableMap(parameterPermissions);
		this.unpackedPermission = unpackedPermission;
	}
	
	public boolean isBottom() {
		return permissions == null;
	}

	@Override
	public StateSpace getStateSpace() {
		if(stateSpace != null)
			return stateSpace;
		if(permissions == null || permissions.isEmpty())
			return StateSpace.SPACE_TOP;
		return stateSpace = permissions.iterator().next().getStateSpace();
	}

	private List<FractionalPermission> checkPermissions() {
		if(permissions != null)
			return permissions;
		throw new IllegalStateException("This is bottom.");
	}

	/**
	 * Splits off the given set of permissions from the available set of permissions
	 * and returns a new set of permissions with the given permissions removed.
	 * Splitting can require merging "smaller" permissions into one permission
	 * with the given permission's root.
	 * 
	 * This method must not manipulate permission sets itself but forward 
	 * to {@link #splitOff(PermissionSetFromAnnotations, FractionConstraints)}.
	 * @param permissionsToSplitOff
	 * @return
	 */
	public final FractionalPermissions splitOff(PermissionSetFromAnnotations permissionsToSplitOff) {
		if(this.isBottom() || permissionsToSplitOff.isEmpty())
			// trivially succeed
			return this;
		return splitOff(permissionsToSplitOff, constraints.mutableCopy());
	}
	
	/**
	 * Splits off the given permission from the available set of permissions
	 * and returns a new set of permissions with the given permission removed.
	 * Splitting can require merging "smaller" permissions into one permission
	 * with the given permission's root.
	 * 
	 * This method must not manipulate permission sets itself but forward 
	 * to {@link #splitOff(PermissionSetFromAnnotations, FractionConstraints)}.
	 * @param permission
	 * @return
	 */
	public final FractionalPermissions splitOff(PermissionFromAnnotation permissionToSplitOff) {
		if(isBottom())
			// trivially succeed
			return this;
		
		return splitOff(
				PermissionSetFromAnnotations.createSingleton(permissionToSplitOff, false), 
				constraints.mutableCopy());
	}
	
	/**
	 * Do <b>not</b> call this method on bottom!
	 * @param permissionsToSplitOff
	 * @param constraints
	 * @return
	 */
	protected FractionalPermissions splitOff(
			PermissionSetFromAnnotations permissionsToSplitOff,
			FractionConstraints constraints) {
		// add in constraints about incoming permissions
		constraints.addAll(permissionsToSplitOff.getConstraints());

		List<FractionalPermission> newPs = 
			splitOffPermissions(permissions, permissionsToSplitOff.getPermissions(), constraints);
		List<FractionalPermission> newFramePs = 
			splitOffPermissions(framePermissions, permissionsToSplitOff.getFramePermissions(), constraints);
		
		return createPermissions(newPs, newFramePs, constraints);
	}
	
	/**
	 * Returns a list of permissions from the given <code>permissions</code> with
	 * <code>permissionsToSplitOff</code> split off.  The returned list is either 
	 * the given <code>permissions</code> list or a whole-new list.  
	 * The given lists are not modified.
	 * @param permissions
	 * @param permissionsToSplitOff
	 * @param constraints
	 * @return a list of permissions from the given <code>permissions</code> with
	 * <code>permissionsToSplitOff</code> split off.
	 * @see FractionalPermission#splitOff(PermissionFromAnnotation, FractionConstraints)
	 */
	protected static List<FractionalPermission> splitOffPermissions(
			List<FractionalPermission> permissions, 
			List<PermissionFromAnnotation> permissionsToSplitOff,
			FractionConstraints constraints) {
		if(permissionsToSplitOff.isEmpty())
			// nothing to split off --> given list is unchanged
			return permissions;
		if(permissions.isEmpty()) {
			// this is an error... no permissions available
			constraints.addConstraint(FractionConstraint.impossible("No permissions available to split off " + permissionsToSplitOff));
			return permissions;
		}
		
		// new permission list
		ArrayList<FractionalPermission> newPs = new ArrayList<FractionalPermission>(permissions);
		// split each permission off separately
		for(PermissionFromAnnotation permission : permissionsToSplitOff) {
			String neededRoot = permission.getRootNode();
			FractionalPermission p = PermissionSet.removePermission(newPs, 
					neededRoot, 
					permission.isPure(), // tolerates permission with smaller root
					constraints);
			if(p == null) {
				// this is an error... no permission can split off the given permission
				constraints.addConstraint(FractionConstraint.impossible("No permission available to split off " + permission));
			}
			else {
				// split off and add back in
				p = p.splitOff(permission, constraints);
				newPs.add(p);
			}
		}
		return newPs;
	}

	/**
	 * Creates a new permission set with the given content plus the same {@link #unpackedPermission} 
	 * as this permission set.
	 * @param newPermissions
	 * @param newFramePermissions
	 * @param newConstraints
	 * @return a new permission set with the given content plus the same {@link #unpackedPermission} 
	 * as this permission set.
	 */
	private FractionalPermissions createPermissions(
			List<? extends FractionalPermission> newPermissions,
			List<? extends FractionalPermission> newFramePermissions,
			FractionConstraints newConstraints) {
		assert this.getClass().equals(FractionalPermissions.class);
		return new FractionalPermissions(newPermissions, newFramePermissions, newConstraints, 
				/*parameters, parameterPermissions,*/ unpackedPermission);
	}

	/**
	 * Creates a new permission set with the given content.
	 * @param newPermissions
	 * @param newFramePermissions
	 * @param new_cs
	 * @param unpacked_perm
	 * @return a new permission set with the given content.
	 */
	private FractionalPermissions createPermissions(
			List<? extends FractionalPermission> newPermissions,
			List<? extends FractionalPermission> newFramePermissions, FractionConstraints new_cs, FractionalPermission unpacked_perm) {
		assert this.getClass().equals(FractionalPermissions.class);
		return new FractionalPermissions(newPermissions, newFramePermissions, new_cs,
				/*parameters, parameterPermissions,*/ unpacked_perm);
	}
	
	@Override
	public boolean atLeastAsPrecise(FractionalPermissions other, ASTNode node) {
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
		if( this.unpackedPermission != other.unpackedPermission ) { 
			if( this.unpackedPermission == null || other.unpackedPermission == null ) {
				return false;
			}
			else if(
					!this.unpackedPermission.getRootNode().equals(other.unpackedPermission.getRootNode()) ||				
					!this.unpackedPermission.atLeastAsPrecise(other.unpackedPermission, node, constraints) ||
					!other.unpackedPermission.atLeastAsPrecise(this.unpackedPermission, node, constraints)) {
				// unpacked permissions must be "equal"
				return false;
			}
		}
		
		if(! PermissionSet.atLeastAsPrecise(this.permissions, other.permissions, node, constraints))
			return false;
		if(! PermissionSet.atLeastAsPrecise(this.framePermissions, other.framePermissions, node, constraints))
			return false;
		return true;
	}

	@Override
	public FractionalPermissions copy() {
		// this is an immutable lattice
		return this;
	}

	/*
	 * This method must not manipulate permission sets itself and instead
	 * forward to protected method
	 */
	@Override
	public final FractionalPermissions join(
			FractionalPermissions other, ASTNode node) {
		if(other == this)
			return this;
		if(other == null || other.isBottom())
			return this;
		if(this.isBottom())
			return other;

		// join constraints from both permission sets only if this is a join after if or conditional
		// drop constraints from loop body if this is a join for a loop
		// if node is a loop, let's hope this is the lattice information from before the loop entry
		if(isLoopNode(node)) {
			// since this join is for a loop, one of the incoming lattice elements should be
			// an "extension" of the other and therefore contain all constraints known in the other lattice element
			if(other.constraints.atLeastAsPrecise(this.constraints))
				return this.join(other, node, this.constraints.mutableCopy(), false); 
			if(this.constraints.atLeastAsPrecise(other.constraints))
				return other.join(this, node, other.constraints.mutableCopy(), false);
		}
		return this.join(other, node, this.constraints.concat(other.constraints), true); 
	}
	
	/**
	 * Tests if a given AST node defines a loop.
	 * @param node
	 * @return <code>True</code> if the given AST node defines a loop, <code>false</code> otherwise.
	 */
	private static boolean isLoopNode(ASTNode node) {
		if(node == null)
			return false;
		if(node instanceof WhileStatement)
			return true;
		if(node instanceof DoStatement)
			return true;
		if(node instanceof ForStatement)
			return true;
		if(node instanceof EnhancedForStatement) 
			return true;
		// what about labeled statements?
		return false;
	}

	/**
	 * Joins two sets of permissions symmetrically or asymmetrically.
	 * Symmetric joins treat both sets of permissions equal while asymmetric joins
	 * assume that the constraints from <code>other</code> will be dropped.
	 * Similarly, asymmetric joins are not allowed to introduce new constraints on
	 * variables only known in <code>other</code>.
	 * Asymmetric joins should be used for loops while symmetric joins are intended
	 * for conditional tests.
	 * The unpacked permissions of the two incoming permission sets must be equivalent.
	 * @param other
	 * @param node
	 * @param constraints
	 * @param symmetric
	 * @return Joined permission set.
	 */
	protected FractionalPermissions join(
			FractionalPermissions other, ASTNode node,
			FractionConstraints constraints,
			boolean symmetric) {
		FractionConstraints comparisonConstraints;
		if(symmetric)
			comparisonConstraints = constraints.mutableCopy();
		else
			comparisonConstraints = constraints.concat(other.constraints);
		comparisonConstraints.freeze();
		
		List<FractionalPermission> newPermissions = PermissionSet.join(
				this.permissions, other.permissions, node, constraints, symmetric, comparisonConstraints);
		List<FractionalPermission> newFramePermissions = PermissionSet.join(
				this.framePermissions, other.framePermissions, node, constraints, symmetric, comparisonConstraints);
		
		/*
		 * Do not tolerate differences in unpacked permission: must be resolved before join
		 * TODO make this an assert?
		 */
		if(! ((this.unpackedPermission == null && other.unpackedPermission == null) ||
				(this.unpackedPermission != null && other.unpackedPermission != null &&
						this.unpackedPermission.atLeastAsPrecise(other.unpackedPermission, node, constraints) &&
						other.unpackedPermission.atLeastAsPrecise(this.unpackedPermission, node, constraints))) )
			throw new IllegalArgumentException("Unpacked permissions not equivalent: " + this.unpackedPermission +
					" vs. " + other.unpackedPermission);
		
		return this.createPermissions(newPermissions, newFramePermissions, constraints, this.unpackedPermission);
	}

	/**
	 * Merge the given permission into the set of known permissions.
	 * If a permission with the same root as the given permission is already
	 * in the set then these two permissions will be coalesced immediately.
	 * This method must not manipulate permission sets itself but forward 
	 * to {@link #mergeIn(PermissionSetFromAnnotations, FractionConstraints)}.
	 * @param permission New permission to be added to the set.
	 * @return New permission set with <code>permission</code> added.
	 */
	public final FractionalPermissions mergeIn(PermissionSetFromAnnotations permissionsToMergeIn) {
		if(isBottom())
			// trivially succeed
			return this;
		return this.mergeIn(permissionsToMergeIn, 
				constraints.mutableCopy(permissionsToMergeIn.getConstraints().getUniversalParameters()));
	}

	/**
	 * Do <b>not</b> call this method on bottom!
	 * @param permissionsToMergeIn
	 * @param constraints
	 * @return
	 */
	protected FractionalPermissions mergeIn(
			PermissionSetFromAnnotations permissionsToMergeIn,
			FractionConstraints constraints) {
		// add in constraints about incoming permissions
		constraints.addAll(permissionsToMergeIn.getConstraints());
		// new permission list
		List<? extends FractionalPermission> newPs = 
			mergeInPermissions(permissions, permissionsToMergeIn.getPermissions(), constraints); 
		List<? extends FractionalPermission> newFramePs = 
			mergeInPermissions(framePermissions, permissionsToMergeIn.getFramePermissions(), constraints); 
		
		return createPermissions(newPs, newFramePs, constraints);
	}
	
	/**
	 * Returns a list of permissions with the given <code>permissionsToMergeIn</code> merged
	 * into <code>permissions</code>.  The returned list is either a whole-new list or
	 * one of the given lists.  The given lists are unchanged. 
	 * @param permissions
	 * @param permissionsToMergeIn
	 * @param constraints
	 * @return a list of permissions with the given <code>permissionsToMergeIn</code> merged
	 * into <code>permissions</code>.
	 */
	protected static List<? extends FractionalPermission> mergeInPermissions(List<? extends FractionalPermission> permissions,
			List<PermissionFromAnnotation> permissionsToMergeIn, FractionConstraints constraints) {
		if(permissionsToMergeIn.isEmpty())
			// nothing to merge
			return permissions;
		if(permissions.isEmpty())
			// nothing to merge with
			return permissionsToMergeIn;
		
		// new permission list
		ArrayList<FractionalPermission> newPs = new ArrayList<FractionalPermission>(permissions);
		// Merge each permission in separately
		for(PermissionFromAnnotation permission : permissionsToMergeIn) {
			PermissionSet.mergeInPermission(newPs, permission, permission.isPure(), constraints);
		}
		return newPs;
	}
	
	/**
	 * Factory method for a brand-new permission set from annotations,
	 * mostly useful to get a permission set of the same type as the receiver.
	 * @param initialPermissions
	 * @return a brand-new permission set from annotations
	 */
	public FractionalPermissions createReplacement(
			PermissionSetFromAnnotations initialPermissions) {
		return createPermissions(initialPermissions.getPermissions(), 
				initialPermissions.getFramePermissions(), 
				initialPermissions.getConstraints());
	}

	/**
	 * Forgets state information for permissions known to be "share" or "pure".
	 * @return
	 */
	public FractionalPermissions forgetShareAndPureStates() {
		if(isBottom())
			return this;
		List<FractionalPermission> newPs = 
			PermissionSet.forgetShareAndPureStates(permissions, constraints);
		List<FractionalPermission> newFramePs = 
			PermissionSet.forgetShareAndPureStates(framePermissions, constraints);
		
		// original constraints unchanged
		return createPermissions(newPs, newFramePs, constraints);
	}

	/**
	 * Returns new permissions with all state info <i>except marker states</i> removed.
	 * @return new permissions with all state info <i>except marker states</i> removed.
	 */
	public FractionalPermissions withoutStateInfo() {
		if(isBottom())
			return this;
		List<FractionalPermission> newPs = 
			PermissionSet.withoutStateInfo(permissions);
		List<FractionalPermission> newFramePs = 
			PermissionSet.withoutStateInfo(framePermissions);
		
		// original constraints unchanged
		return createPermissions(newPs, newFramePs, constraints);
	}

	@Deprecated
	public FractionalPermissions learnTemporaryStateInfo(String new_state) {
		return learnTemporaryStateInfo(new_state, false);
	}
	
	/**
	 * For each permission, this method returns a new FractionalPermissions
	 * object that has all of the same permissions except with the given
	 * temporary state information.
	 *  
	 * @param new_state
	 * @param forFrame <code>true</code> to learn frame state information, 
	 * <code>false</code> otherwise.
	 * @return Permissions with the new state information added
	 * @see FractionalPermission#addStateInfo(String)
	 */
	public FractionalPermissions learnTemporaryStateInfo(String new_state, boolean forFrame) {
		if(isBottom())
			return this;

		if(forFrame) {
			List<FractionalPermission> new_permissions = 
				PermissionSet.learnStateInfo(framePermissions, new_state);
			return createPermissions(permissions, new_permissions, constraints);
		}
		else {
			List<FractionalPermission> new_permissions = 
				PermissionSet.learnStateInfo(permissions, new_state);
			return createPermissions(new_permissions, framePermissions, constraints);
		}
	}
	
	public boolean isImpossible() {
		return constraints.isImpossible();
	}

	public boolean isUnsatisfiable() {
		return constraints.isConsistent() == false;
	}

	/**
	 * @return
	 * @deprecated Use {@link #getStateInfo(boolean)} instead.
	 */
	@Deprecated
	public List<String> getStateInfo() {
		List<String> result = getStateInfo(false);
		result.addAll(getStateInfo(true));
		return result;
//		if(permissions == null)
//			return Collections.emptyList();
//		ArrayList<String> result = new ArrayList<String>(permissions.size());
//		for(FractionalPermission p : permissions) {
//			result.addAll(p.getStateInfo());
//		}
//		return result;
	}

	public List<String> getStateInfo(boolean inFrame) {
		List<FractionalPermission> perms = inFrame ? framePermissions : permissions;
		if(perms == null)
			return Collections.emptyList();
		ArrayList<String> result = new ArrayList<String>(perms.size());
		for(FractionalPermission p : perms) {
			result.addAll(p.getStateInfo());
		}
		return result;
	}

	/**
	 * Tests if one of the permissions in the set of virtual permissions implies the given state.
	 * @param needed
	 * @deprecated Discouraged: this checks both frame and virtual permissions for the needed state.
	 * @see #isInState(String, boolean) to check only frame or virtual permissions
	 */
	@Deprecated
	public boolean isInState(String needed) {
		return isInState(needed, false) || isInState(needed, true);
	}
	
	/**
	 * Tests if one of the permissions in the specified set of permissions implies the given state.
	 * @param needed
	 * @param inFrame Uses frame permissions if <code>true</code>, virtual permissions otherwise.
	 * @return
	 */
	public boolean isInState(String needed, boolean inFrame) {
		if(isBottom())
			return true; // succeed for bottom
		List<FractionalPermission> perms = inFrame ? framePermissions : permissions;
		for(FractionalPermission p : perms) {
			if(p.impliesState(needed))
				return true;
		}
		return false;
	}

	/**
	 * Tests if the permissions in the set of virtual permissions imply the given set of states.
	 * @param needed
	 * @return
	 * @deprecated Discouraged: this checks both frame and virtual permissions for the needed state.
	 * @see #isInStates(Collection, boolean)} for checking only frame or virtual permissions
	 */
	@Deprecated
	public boolean isInStates(Collection<String> needed) {
		if(needed.isEmpty())
			return true;
		if(isBottom())
			return true; // succeed for bottom
		for(String s : needed) {
			if(!isInState(s))
				return false;
		}
		return true;
	}
	
	/**
	 * Tests if the permissions in the specified set of permissions imply the given set of states.
	 * @param needed
	 * @param inFrame Uses frame permissions if <code>true</code>, virtual permissions otherwise.
	 * @return
	 */
	public boolean isInStates(Collection<String> needed, boolean inFrame) {
		if(needed.isEmpty())
			return true;
		if(isBottom())
			return true; // succeed for bottom
		next_state:
		for(String s : needed) {
			for(FractionalPermission p : inFrame ? framePermissions : permissions) {
				if(p.impliesState(s))
					continue next_state;
			}
			return false;
		}
		return true;
	}
	
	/**
	 * Checks sets of states for virtual and frame permissions. 
	 * @param needed
	 * @return
	 */
	public boolean isInStates(Pair<? extends Collection<String>, ? extends Collection<String>> needed) {
		return isInStates(needed.fst(), false) && isInStates(needed.snd(), true);
	}

//	public Aliasing getParameter(String parameterName) {
//		return parameters.get(parameterName);
//	}
//
//	public PermissionSetFromAnnotations getParameterPermission(String parameterName) {
//		return parameterPermissions.get(getParameter(parameterName));
//	}

	/**
	 * Makes sure there is a modifiable permission with the given root.
	 * You may not call this method on bottom.
	 * This method must not manipulate permission sets itself but forward 
	 * to {@link #makeModifiable(String, boolean, FractionConstraints)}
	 * @param rootState
	 * @return
	 */
	public final FractionalPermissions makeModifiable(String rootState, boolean inFrame) {
		if(isBottom())
			return this;
		return makeModifiable(rootState, inFrame, constraints.mutableCopy());
	}
	
	/**
	 * Do <b>not</b> call this method on bottom!
	 * @param neededRoot
	 * @param inFrame
	 * @param constraints
	 * @return
	 */
	protected FractionalPermissions makeModifiable(String neededRoot, boolean inFrame, FractionConstraints constraints) {
		ArrayList<FractionalPermission> newPs = new ArrayList<FractionalPermission>(inFrame ? framePermissions : permissions);
		FractionalPermission p = PermissionSet.removePermission(newPs, neededRoot, false, constraints);
		if(p == null)
			constraints.addConstraint(FractionConstraint.impossible("No permission for available for root " + neededRoot));
		else {
			p = p.makeModifiable(constraints);
			newPs.add(p);
		}
		
		if(inFrame)
			return createPermissions(permissions, newPs, constraints);
		else
			return createPermissions(newPs, framePermissions, constraints);
	}
	
	public FractionalPermissions makeUnpackedPermissionModifiable() {
		if(unpackedPermission == null) 
			throw new IllegalStateException("Object not unpacked.");
		
		if(unpackedPermission.isReadOnly()) {
			FractionConstraints newCs = constraints.mutableCopy();
			return createPermissions(checkPermissions(), framePermissions, newCs, unpackedPermission.makeModifiable(newCs));
		}
		else
			return this;
	}

	/**
	 * Unpack the permission to the needed root state, removing permissions no longer
	 * applicable. Remembers what permission was unpacked, and the state information
	 * to go along with it.  Unpacking bottom results in 
	 * {@link FractionConstraints#isImpossible() impossible} constraints.
	 * TODO revisit this and see if we can't just unpack bottom to bottom
	 * @param neededRoot
	 * @return New permissions with permission for needed root unpacked.
	 */
	public FractionalPermissions unpack(String neededRoot) {
		if( this.unpackedPermission != null )
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
				this.createPermissions(permissions, notunpacked_perms, new_cs, unpacked_perm);
		}
		/*
		 * In this case, we have already failed. Just create a totally unsat permission.
		 */
		new_cs.addConstraint(FractionConstraint.impossible("No permission available to unpack to " + neededRoot));
		unpacked_perm = new FractionalPermission(this.getStateSpace(),
				neededRoot, FractionFunction.variableAll(this.getStateSpace(),
						neededRoot, false),
						true, Collections.<String>emptySet(), new_cs);
		
		List<FractionalPermission> newPs = 
			// protect against bottom case 
			// (not needed if unpacked_perm != null because that shouldn't happen on bottom)
			(permissions == null ? Collections.<FractionalPermission>emptyList() : permissions);
		return this.createPermissions(newPs, notunpacked_perms, new_cs, unpacked_perm);
	}

	/**
	 * When this is unpacked, returns the portion of the permission that was unpacked
	 * and is no longer accessible.
	 * @return
	 */
	public FractionalPermission getUnpackedPermission() {
		return this.unpackedPermission;
	}

	/**
	 * You may not call this method on bottom.
	 * TODO see if we can't just return bottom when packing bottom
	 * @param desiredState
	 * @return
	 */
	public FractionalPermissions pack(Set<String> desiredState) {
		checkPermissions();
		FractionalPermission new_rcvr_perm =
			this.getUnpackedPermission().copyNewState(desiredState);
		List<FractionalPermission> newPs = new ArrayList<FractionalPermission>(framePermissions);
		FractionConstraints constraints = this.constraints.mutableCopy();
		PermissionSet.mergeInPermission(newPs, new_rcvr_perm, false, constraints);
		
		return createPermissions(permissions, newPs, constraints, null);
	}

	/**
	 * You may not call this method on bottom.
	 * @return
	 */
	public FractionalPermissions invalidPack() {
		FractionConstraints newCs = constraints.mutableCopy();
		newCs.addConstraint(FractionConstraint.impossible("Couldn't pack"));
		return createPermissions(checkPermissions(), framePermissions, newCs, null);
	}

	/**
	 * This method must not be called while unpacked.
	 * @param stateInfo Pair of state info sets for virtual and frame permissions.
	 * @return
	 */
	public FractionalPermissions replaceStateInfo(Pair<Set<String>, Set<String>> stateInfo) {
		if(isBottom())
			// do nothing
			return this;
		assert unpackedPermission == null;

		List<FractionalPermission> newPs = 
			PermissionSet.replaceStateInfo(permissions, stateInfo.fst());
		List<FractionalPermission> newFramePs =
			PermissionSet.replaceStateInfo(framePermissions, stateInfo.snd());
		return createPermissions(newPs, newFramePs, constraints);
	}
	
	/**
	 * This method must not be called while unpacked.
	 * @param stateInfo
	 * @param inFrame Uses frame permissions if <code>true</code>, virtual permissions otherwise.
	 * @return
	 */
	public FractionalPermissions replaceStateInfo(Set<String> stateInfo, boolean inFrame) {
		if(isBottom())
			// do nothing
			return this;
		assert unpackedPermission == null;
		
		if(inFrame) {
			if(framePermissions.isEmpty())
				// shortcut
				return this;
			List<FractionalPermission> newFramePs = 
				PermissionSet.replaceStateInfo(framePermissions, stateInfo);
			return createPermissions(permissions, newFramePs, constraints);
		}
		else {
			if(permissions.isEmpty())
				// shortcut
				return this;
			List<FractionalPermission> newPs = 
				PermissionSet.replaceStateInfo(permissions, stateInfo);
			return createPermissions(newPs, framePermissions, constraints);
		}
	}

	@Override
	public String getUserString() {
		if(isBottom())
			return "BOTTOM";

		String vs = getUserString(permissions);
		String fs = getUserString(framePermissions);
		String us = null;
		if(unpackedPermission != null) {
			us = unpackedPermission.getUserString(constraints);
			if(us == null)
				// unpacked permission is not real -> weird
				us = "NOTHING";
		}

		String inSets; 
		if(vs != null && fs != null)
			inSets = vs + " frame " + fs;
		else if(vs != null)
			inSets = vs;
		else if(fs != null)
			inSets = "frame " + fs;
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
		if(unpackedPermission == null)
			return permissions + " frame " + framePermissions + " with " + constraints;
		return "unpacked[" + unpackedPermission + "] + " + permissions + " frame " + framePermissions + " with " + constraints;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((unpackedPermission == null) ? 0 : unpackedPermission
						.hashCode());
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
		final FractionalPermissions other = (FractionalPermissions) obj;
		if (unpackedPermission == null) {
			if (other.unpackedPermission != null)
				return false;
		} else if (!unpackedPermission.equals(other.unpackedPermission))
			return false;
		return true;
	}

}
