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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import edu.cmu.cs.crystal.util.Pair;
import edu.cmu.cs.plural.states.StateSpace;

/**
 * @author Kevin Bierhoff
 * @since Mar 21, 2009
 *
 */
public interface ObjectPermissionSet {

	public boolean isBottom();

	public StateSpace getStateSpace();

	/**
	 * Splits off the given set of permissions from the available set of permissions
	 * and returns a new set of permissions with the given permissions removed.
	 * Splitting can require merging "smaller" permissions into one permission
	 * with the given permission's root.
	 * @param permissionsToSplitOff
	 * @return
	 */
	public FractionalPermissions splitOff(
			PermissionSetFromAnnotations permissionsToSplitOff);

	/**
	 * Splits off the given permission from the available set of permissions
	 * and returns a new set of permissions with the given permission removed.
	 * Splitting can require merging "smaller" permissions into one permission
	 * with the given permission's root.
	 * @param permission
	 * @return
	 */
	public FractionalPermissions splitOff(
			PermissionFromAnnotation permissionToSplitOff);

//	public boolean atLeastAsPrecise(FractionalPermissions other, ASTNode node);
//
//	public FractionalPermissions copy();
//
//	public FractionalPermissions join(FractionalPermissions other, ASTNode node);

	/**
	 * Merge the given permission into the set of known permissions.
	 * If a permission with the same root as the given permission is already
	 * in the set then these two permissions will be coalesced immediately.
	 * @param permission New permission to be added to the set.
	 * @return New permission set with <code>permission</code> added.
	 */
	public FractionalPermissions mergeIn(
			PermissionSetFromAnnotations permissionsToMergeIn);

	/**
	 * Forgets state information for permissions known to be "share" or "pure".
	 * @return
	 */
	public ObjectPermissionSet forgetShareAndPureStates();

	public ObjectPermissionSet withoutStateInfo();

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
	public ObjectPermissionSet learnTemporaryStateInfo(String new_state,
			boolean forFrame);

	public boolean isImpossible();

	public boolean isUnsatisfiable();

	public List<String> getStateInfo(boolean inFrame);

	/**
	 * Tests if one of the permissions in the specified set of permissions implies the given state.
	 * @param needed
	 * @param inFrame Uses frame permissions if <code>true</code>, virtual permissions otherwise.
	 * @return
	 */
	public boolean isInState(String needed, boolean inFrame);

	/**
	 * Tests if the permissions in the specified set of permissions imply the given set of states.
	 * @param needed
	 * @param inFrame Uses frame permissions if <code>true</code>, virtual permissions otherwise.
	 * @return
	 */
	public boolean isInStates(Collection<String> needed, boolean inFrame);

	/**
	 * Checks sets of states for virtual and frame permissions. 
	 * @param needed
	 * @return
	 */
	public boolean isInStates(
			Pair<? extends Collection<String>, ? extends Collection<String>> needed);

	/**
	 * Makes sure there is a modifiable permission with the given root.
	 * You may not call this method on bottom.
	 * @param rootState
	 * @return
	 */
	public ObjectPermissionSet makeModifiable(String rootState,
			boolean inFrame);

	public ObjectPermissionSet makeUnpackedPermissionModifiable();

	/**
	 * Unpack the permission to the needed root state, removing permissions no longer
	 * applicable. Remembers what permission was unpacked, and the state information
	 * to go along with it.  Unpacking bottom results in 
	 * {@link FractionConstraints#isImpossible() impossible} constraints.
	 * @param neededRoot
	 * @return New permissions with permission for needed root unpacked.
	 */
	public ObjectPermissionSet unpack(String neededRoot);

	/**
	 * When this is unpacked, returns the portion of the permission that was unpacked
	 * and is no longer accessible.
	 * @return
	 */
	public FractionalPermission getUnpackedPermission();

	/**
	 * You may not call this method on bottom.
	 * @param desiredState
	 * @return
	 */
	public ObjectPermissionSet pack(Set<String> desiredState);

	/**
	 * You may not call this method on bottom.
	 * @return
	 */
	public ObjectPermissionSet invalidPack();

	/**
	 * This method must not be called while unpacked.
	 * @param stateInfo Pair of state info sets for virtual and frame permissions.
	 * @return
	 */
	public ObjectPermissionSet replaceStateInfo(
			Pair<Set<String>, Set<String>> stateInfo);

	/**
	 * This method must not be called while unpacked.
	 * @param stateInfo
	 * @param inFrame Uses frame permissions if <code>true</code>, virtual permissions otherwise.
	 * @return
	 */
	public ObjectPermissionSet replaceStateInfo(Set<String> stateInfo,
			boolean inFrame);

	public String getUserString();

}