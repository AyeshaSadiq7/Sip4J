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
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import edu.cmu.cs.crystal.util.Pair;
import edu.cmu.cs.plural.states.StateSpace;

/**
 * @author Kevin Bierhoff
 *
 */
public class PermissionSetFromAnnotations extends
		AbstractFractionalPermissionSet<PermissionFromAnnotation> {

	public static PermissionSetFromAnnotations createEmpty(StateSpace stateSpace) {
		return new PermissionSetFromAnnotations(stateSpace);
	}

	public static PermissionSetFromAnnotations createSingleton(
			PermissionFromAnnotation singleton, boolean isNamedUniversal) {
		FractionConstraints constraints;
		if(isNamedUniversal) {
			Set<NamedFraction> universals = singleton.getFractions().getAllFractionsOfType(NamedFraction.class);
			constraints = FractionConstraints.createMutable(universals);
		}
		else
			constraints = FractionConstraints.createMutable();
		
		singleton.registerFractions(constraints);
		singleton.addIsUsedConstraints(constraints);
		singleton.addIsFunctionConstraints(constraints);
		
		List<PermissionFromAnnotation> perms;
		List<PermissionFromAnnotation> framePerms;
		if(singleton.isFramePermission()) {
			perms = Collections.emptyList();
			framePerms = Collections.singletonList(singleton);
		}
		else {
			perms = Collections.singletonList(singleton);
			framePerms = Collections.emptyList();
		}
			
		return new PermissionSetFromAnnotations(
				singleton.getStateSpace(), 
				perms, framePerms,
				constraints);
	}

	public static Pair<PermissionSetFromAnnotations, PermissionSetFromAnnotations> splitPermissionSets(
			PermissionSetFromAnnotations perms) {
		PermissionSetFromAnnotations virt = new PermissionSetFromAnnotations(
				perms.getStateSpace(),
				perms.getPermissions(), 
				Collections.<PermissionFromAnnotation>emptyList(), 
				perms.getConstraints());
		PermissionSetFromAnnotations frame = new PermissionSetFromAnnotations(
				perms.getStateSpace(),
				Collections.<PermissionFromAnnotation>emptyList(), 
				perms.getFramePermissions(), 
				perms.getConstraints());
		
//		PermissionSetFromAnnotations virt = PermissionSetFromAnnotations.createEmpty(perms.getStateSpace());
//		PermissionSetFromAnnotations frame = PermissionSetFromAnnotations.createEmpty(perms.getStateSpace());
//		for(PermissionFromAnnotation v : perms.getPermissions()) {
//			virt = virt.combine(v);
//		}
//		for(PermissionFromAnnotation f : perms.getFramePermissions()) {
//			frame = frame.combine(f);
//		}
		return Pair.create(virt, frame);
	}
	
	private StateSpace stateSpace;
	
	protected PermissionSetFromAnnotations(StateSpace stateSpace) {
		super();
		this.stateSpace = stateSpace;
	}
	
	protected PermissionSetFromAnnotations(
			StateSpace stateSpace,
			List<PermissionFromAnnotation> permissions,
			List<PermissionFromAnnotation> framePermissions,
			FractionConstraints constraints) {
		super(permissions, framePermissions, constraints);
		this.stateSpace = stateSpace;
	}

	@Override
	public StateSpace getStateSpace() {
		return stateSpace;
	}

	/**
	 * Merge the given permission into the set of known permissions.
	 * If a permission with the same root as the given permission is already
	 * in the set then these two permissions will be coalesced immediately.
	 * @param permission New permission to be added to the set.
	 * @param isNamedUniversal 
	 * @return New permission set with <code>permission</code> added.
	 */
	public PermissionSetFromAnnotations combine(PermissionFromAnnotation permission, 
			boolean isNamedUniversal) {
		FractionConstraints constraints;
		if(isNamedUniversal) {
			Set<NamedFraction> universals = permission.getFractions().getAllFractionsOfType(NamedFraction.class);
			constraints = this.constraints.mutableCopy(universals);
		}
		else
			constraints = this.constraints.mutableCopy();
		
		// maybe it would be better to use the permission factory to create
		// this permission in place
		permission.registerFractions(constraints);
		permission.addIsUsedConstraints(constraints);
		permission.addIsFunctionConstraints(constraints);

		boolean addToFramePerms = permission.isFramePermission();
		
		ArrayList<PermissionFromAnnotation> newPs = new ArrayList<PermissionFromAnnotation>(
				addToFramePerms ? framePermissions.size() : permissions.size() );
		String neededRoot = permission.getRootNode(); 
		boolean combined = false;
		for(PermissionFromAnnotation p : (addToFramePerms ? framePermissions : permissions) ) {
			if(p.getRootNode().equals(neededRoot)) {
				// eager merging of permissions for same root
				p = p.combine(permission, constraints);
				combined = true;
			}
			newPs.add(p);
		}
		if(! combined)
			newPs.add(permission);
		
		if(addToFramePerms)
			return createPermissions(permissions, newPs, constraints);
		else
			return createPermissions(newPs, framePermissions, constraints);
	}

	/**
	 * Turns this permission set into a regular 
	 * permission set that can be joined, split, and merged.
	 * @return
	 */
	public FractionalPermissions toLatticeElement() {
		return new FractionalPermissions(permissions, framePermissions, constraints);
	}

	private PermissionSetFromAnnotations createPermissions(
			List<PermissionFromAnnotation> newPermissions,
			List<PermissionFromAnnotation> newFramePermissions,
			FractionConstraints newConstraints) {
		return new PermissionSetFromAnnotations(stateSpace, newPermissions, newFramePermissions, newConstraints);
	}

	/**
	 * Union of state infos from contained permissions.
	 * @return
	 * @deprecated Discouraged: this returns state info from both frame and virtual permissions.
	 * @see FractionalPermission#getStateInfo()
	 */
	@Deprecated
	public Set<String> getStateInfo() {
		LinkedHashSet<String> result = new LinkedHashSet<String>();
		for(PermissionFromAnnotation p : permissions) {
			result.addAll(p.getStateInfo());
		}
		for(PermissionFromAnnotation p : framePermissions) {
			result.addAll(p.getStateInfo());
		}
		return result;
	}
	
	public Set<String> getStateInfo(boolean inFrame) {
		LinkedHashSet<String> result = new LinkedHashSet<String>();
		for(PermissionFromAnnotation p : inFrame ? framePermissions : permissions) {
			result.addAll(p.getStateInfo());
		}
		return result;
	}

	/**
	 * Returns a pair of state infos for virtual and frame permissions.
	 * @return a pair of state infos for virtual and frame permissions.
	 */
	public Pair<Set<String>, Set<String>> getStateInfoPair() {
		return Pair.create(getStateInfo(false), getStateInfo(true));
	}

	/**
	 * Tests if this permission set consists only of 
	 * {@link PermissionFromAnnotation#isReadOnly() read-only} permissions.
	 * @return <code>true</code> if all permissions in this set are 
	 * {@link PermissionFromAnnotation#isReadOnly() read-only}, <code>false</code>
	 * otherwise
	 */
	public boolean isReadOnly() {
		for(PermissionFromAnnotation p : permissions) {
			if(p.isReadOnly() == false)
				return false;
		}
		return true;
	}

	/**
	 * Return a new permission set where every permission has been purified.
	 */
	public PermissionSetFromAnnotations purify() { 
		List<PermissionFromAnnotation> perms = new ArrayList<PermissionFromAnnotation>(permissions.size());
		List<PermissionFromAnnotation> frame_perms = new ArrayList<PermissionFromAnnotation>(framePermissions.size());
		
		for( PermissionFromAnnotation permission : permissions )
			perms.add(permission.purify());
		
		for( PermissionFromAnnotation framePermission : framePermissions )
			frame_perms.add(framePermission.purify());
		
		return new PermissionSetFromAnnotations(this.stateSpace, perms, frame_perms, this.constraints);
	}
	
	/**
	 * Returns the permissions in this set with all state information except marker
	 * and root states removed.
	 * @return the permissions in this set with all state information except marker
	 * and root states removed.
	 */
	public PermissionSetFromAnnotations withoutStateInfo() {
		List<PermissionFromAnnotation> newPs = 
			new ArrayList<PermissionFromAnnotation>(permissions.size());
		for(PermissionFromAnnotation p : permissions) {
			newPs.add(p.forgetStateInfo());
		}
		
		List<PermissionFromAnnotation> newFramePs = 
			new ArrayList<PermissionFromAnnotation>(framePermissions.size());
		for(PermissionFromAnnotation p : framePermissions) {
			newFramePs.add(p.forgetStateInfo());
		}

		return createPermissions(newPs, newFramePs, constraints);
	}

	public boolean hasShareOrPurePermissions() {
		for(PermissionFromAnnotation p : permissions) {
			if(p.isWeak())
				return true;
		}
		for(PermissionFromAnnotation p : framePermissions) {
			if(p.isWeak())
				return true;
		}
		return false;
	}

	public PermissionSetFromAnnotations forgetShareAndPureStates() {
		List<PermissionFromAnnotation> newPs = 
			new ArrayList<PermissionFromAnnotation>(permissions.size());
		for(PermissionFromAnnotation p : permissions) {
			if(p.isWeak())
				newPs.add(p.forgetStateInfo());
			else
				newPs.add(p);
		}
		
		List<PermissionFromAnnotation> newFramePs = 
			new ArrayList<PermissionFromAnnotation>(framePermissions.size());
		for(PermissionFromAnnotation p : framePermissions) {
			if(p.isWeak())
				newFramePs.add(p.forgetStateInfo());
			else
				newFramePs.add(p);
		}

		return createPermissions(newPs, newFramePs, constraints);
	}

	@Override
	public String getUserString() {
		String vs = getUserString(permissions);
		String fs = getUserString(framePermissions);
		if(vs != null && fs != null)
			return vs + " frame " + fs;
		else if(vs != null)
			return vs;
		else if(fs != null)
			return "frame " + fs;
		else
			return "NOTHING";
	}

	@Override
	public String toString() {
		return permissions + " frame " + framePermissions + " with " + constraints;
	}

}
