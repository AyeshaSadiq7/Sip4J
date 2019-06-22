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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import edu.cmu.cs.plural.states.StateSpace;
import edu.cmu.cs.plural.track.IPermission;
import edu.cmu.cs.plural.track.Permission.PermissionKind;

/**
 * @author Kevin Bierhoff
 *
 */
public class PermissionFromAnnotation extends FractionalPermission 
		implements IPermission<PermissionFromAnnotation> {
	
	private final boolean isFramePermission;

	/**
	 * Creates a frame or virtual permission with the given parameters, depending
	 * on <code>isFramePermission</code>.
	 * @param stateSpace
	 * @param rootNode
	 * @param fractions
	 * @param mutable
	 * @param isFramePermission
	 * @param stateInfo
	 * @see PermissionFactory for creating permissions.
	 */
	protected PermissionFromAnnotation(StateSpace stateSpace,
			String rootNode, FractionFunction fractions, boolean mutable,
			boolean isFramePermission,
			Iterable<String> stateInfo) {
		super(stateSpace, rootNode, fractions, mutable, stateInfo);
		this.isFramePermission = isFramePermission;
	}

	/**
	 * Creates a frame or virtual permission with the given parameters, depending
	 * on <code>isFramePermission</code>.
	 * @param stateSpace
	 * @param rootNode
	 * @param fractions
	 * @param mutable
	 * @param isFramePermission
	 * @param stateInfo
	 * @see PermissionFactory for creating permissions.
	 */
	protected PermissionFromAnnotation(StateSpace stateSpace,
			String rootNode, FractionFunction fractions, boolean mutable,
			boolean isFramePermission,
			String... stateInfo) {
		super(stateSpace, rootNode, fractions, mutable, Arrays.asList(stateInfo));
		this.isFramePermission = isFramePermission;
	}

	/**
	 * Creates a frame or virtual permission with the given parameters, depending
	 * on <code>isFramePermission</code>.
	 * @param stateSpace
	 * @param rootNode
	 * @param fractions
	 * @param mutable
	 * @param isFramePermission
	 * @param stateInfo
	 * @param constraints
	 * @see PermissionFactory for creating permissions.
	 */
	protected PermissionFromAnnotation(StateSpace stateSpace,
			String rootNode, FractionFunction fractions, boolean mutable,
			boolean isFramePermission,
			Iterable<String> stateInfo, FractionConstraints constraints) {
		super(stateSpace, rootNode, fractions, mutable, stateInfo, constraints);
		this.isFramePermission = isFramePermission;
	}

	public boolean isFull() {
		return fractions.getBelowFraction().isOne() && (isUnique() == false);
	}

	public boolean isImmutable() {
		return fractions.getBelowFraction().isNeitherZeroNorOne() && (mutable == false);
	}

	public boolean isPure() {
		return fractions.getBelowFraction().isZero();
	}

	public boolean isShare() {
		return fractions.getBelowFraction().isNeitherZeroNorOne() && mutable;
	}

	public boolean isUnique() {
		return fractions.get(getRootNode()).isOne();
	}

	/**
	 * @return the isFramePermission
	 */
	public boolean isFramePermission() {
		return isFramePermission;
	}

	@Override
	public PermissionFromAnnotation addStateInfo(String stateInfo) {
		Set<String> newStateInfo = mergeStateInfo(Collections.singleton(stateInfo));
		return createAnnotation(stateSpace, rootNode, mutable, newStateInfo);
	}

	@Override
	public PermissionFromAnnotation copyNewState(String... newState) {
		return createAnnotation(stateSpace, rootNode, mutable, newState);
	}

	@Override
	public PermissionFromAnnotation copyNewKind(PermissionKind newKind) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PermissionFromAnnotation purify() {
		if(isReadOnly())
			return this;
		return new PermissionFromAnnotation(stateSpace, rootNode, fractions.purify(), false, isFramePermission, stateInfo);
	}

	private PermissionFromAnnotation createAnnotation(
			StateSpace newStateSpace, String newRootNode,
			boolean newMutable,	String... newStateInfo) {
		return new PermissionFromAnnotation(newStateSpace, newRootNode, fractions, newMutable, isFramePermission, newStateInfo);
	}

	private PermissionFromAnnotation createAnnotation(
			StateSpace newStateSpace, String newRootNode,
			boolean newMutable,	Set<String> newStateInfo) {
		return new PermissionFromAnnotation(newStateSpace, newRootNode, fractions, newMutable, isFramePermission, newStateInfo);
	}

	private PermissionFromAnnotation createAnnotation(
			StateSpace newStateSpace, String newRootNode, FractionFunction newFractions,
			boolean newMutable,	Set<String> newStateInfo, FractionConstraints constraints) {
		return new PermissionFromAnnotation(newStateSpace, newRootNode, newFractions, newMutable, isFramePermission, newStateInfo, constraints);
	}

	/**
	 * Specialized method for combining two permission annotations.  This helps preserving the
	 * invariants needed for {@link #isShare()}, {@link #isPure()} etc.  The implementation
	 * is mostly a clone of {@link FractionalPermission#mergeIn(FractionalPermission, FractionConstraints)}.
	 * @param permission
	 * @param constraints
	 * @return
	 */
	public PermissionFromAnnotation combine(PermissionFromAnnotation permission,
			FractionConstraints constraints) {
		if(this.rootNode.equals(permission.rootNode) == false)
			throw new IllegalArgumentException("Cannot join permissions with different roots:" + this.rootNode + " vs. " + permission.rootNode);
		if(this.isFramePermission != permission.isFramePermission)
			throw new IllegalArgumentException("Cannot combine frame with virtual permission.");
		
		// should happen at the call site
//		permission.registerFractions(constraints);
//		permission.addIsUsedConstraints(constraints);
//		permission.addIsFunctionConstraints(constraints);
		
		// constraints for node fractions
		Map<String, Fraction> newFs = new HashMap<String, Fraction>(this.fractions.size());
		Iterator<String> it = this.stateSpace.stateIterator(this.rootNode);
		while(it.hasNext()) {
			String n = it.next();
			Fraction thisF = this.fractions.get(n);
			Fraction otherF = permission.fractions.get(n);
			
			Fraction newF;
			if(thisF.isZero())
				newF = otherF; // this in particular preserves one's
			else if(otherF.isZero())
				newF = thisF; // this in particular preserves one's
			else {
				newF = constraints.newVariableFraction();
				FractionSum sum = new FractionSum(
						thisF, 
						otherF);
				FractionConstraint eq = FractionConstraint.createEquality(newF, sum);
				constraints.addConstraint(eq);
				
				// for constants, this asserts that the combined fractions do not exceed one
				constraints.addConstraint(FractionConstraint.createLessThanOrEqual(sum, Fraction.one()));
			}
			newFs.put(n, newF);
		}
		
		// constraint for below fraction
		Fraction thisF = this.fractions.getBelowFraction();
		Fraction otherF = permission.fractions.getBelowFraction();
		Fraction belowF;
		if(thisF.isZero())
			belowF = otherF; // this in particular preserves one's
		else if(otherF.isZero())
			belowF = thisF; // this in particular preserves one's
		else {
			belowF = constraints.newVariableFraction();
			FractionSum sum = new FractionSum(
					thisF, 
					otherF);
			FractionConstraint eq = FractionConstraint.createEquality(belowF, sum);
			constraints.addConstraint(eq);
			constraints.addConstraint(FractionConstraint.createLessThanOrEqual(sum, Fraction.one()));
		}
		
		// create fraction function
		// TODO which state space?
		FractionFunction f = new FractionFunction(this.stateSpace, this.rootNode, newFs, belowF);

		return createAnnotation(
				this.stateSpace, 
				this.rootNode, 
				f, 
				// merging read-only permissions yields read-only--switching to mutable done with additional constraint during splitting
				this.mutable || permission.mutable, 
				mergeStateInfo(permission.getStateInfo()),
				constraints);
	}
	
	public PermissionFromAnnotation forgetStateInfo() {
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
			return createAnnotation(stateSpace, rootNode, mutable, newStateInfo);
	}

	/**
	 * Returns the same permission but as virtual permission, if it isn't already.
	 * @return
	 */
	public PermissionFromAnnotation asVirtual() {
		if(!isFramePermission)
			return this;
		else
			return new PermissionFromAnnotation(stateSpace, rootNode, fractions, mutable, 
					false /* virtual */, stateInfo);
	}

	/**
	 * This method relies on the permission being
	 * generated from permission annotations.  It assumes that
	 * variable / constant below fractions 
	 * indicate Share or Immutable annotations.
	 * @param constraints
	 */
	void addIsUsedConstraints(FractionConstraints constraints) {
		super.addIsUsedConstraint(constraints);
		
		// variable below fractions must be non-zero
		if(isShare() || isImmutable())
			constraints.addConstraint(
					FractionConstraint.createLessThan(
							Fraction.zero(), fractions.getBelowFraction()));
	}

	/**
	 * Returns this permission's kind.
	 * @return this permission's kind, never <code>null</code>.
	 */
	public PermissionKind getKind() {
		if(isUnique())
			return PermissionKind.UNIQUE;
		if(isFull())
			return PermissionKind.FULL;
		if(isShare())
			return PermissionKind.SHARE;
		if(isImmutable())
			return PermissionKind.IMMUTABLE;
		if(isPure())
			return PermissionKind.PURE;
		throw new IllegalStateException("Not a valid permission: " + this);
	}
	
	@Override
	public PermissionKind getKind(FractionConstraints constraints) {
		// don't need the constraints for permissions from annotations
		return getKind();
	}
	
	/**
	 * Indicates whether this is a weak permission that forgets state information,
	 * i.e., a {@link #isShare() share} or {@link #isPure() pure} permission.
	 * @return <code>true</code> if this is a {@link #isShare() share} or {@link #isPure() pure} 
	 * permission, <code>false</code> otherwise.
	 */
	public boolean isWeak() {
		return isShare() || isPure();
	}

	@Override
	public String toString() {
		return getKind().toString() + "(" + rootNode + " " + fractions + ") in " + stateInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isFramePermission ? 1231 : 1237);
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
		final PermissionFromAnnotation other = (PermissionFromAnnotation) obj;
		if (isFramePermission != other.isFramePermission)
			return false;
		return true;
	}

}
