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

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import edu.cmu.cs.plural.states.StateSpace;

/**
 * Permission sets are immutable and should implement {@link #equals(Object)} and
 * {@link #hashCode()} using structural comparison.
 * @author Kevin Bierhoff
 *
 * @param <P> Type of the constituent permissions.
 */
public abstract class AbstractFractionalPermissionSet<P extends AbstractFractionalPermission> {
	
	private static final Logger log = Logger.getLogger(AbstractFractionalPermissionSet.class.getName());

	/**
	 * Immutable list of virtual permissions, must have orthogonal roots.
	 * @see StateSpace#areOrthogonal(String, String)
	 */
	protected final List<P> permissions;	         // immutable
	
	/**
	 * Immutable list of frame permissions, must have orthogonal roots.
	 * @see StateSpace#areOrthogonal(String, String)
	 */
	protected final List<P> framePermissions;		 // immutable
	
	/**
	 * Frozen constraints.
	 * @see FractionConstraints#freeze()
	 */
	protected final FractionConstraints constraints; // frozen; make a mutable copy to modify

	/**
	 * Creates an empty permission set.
	 */
	protected AbstractFractionalPermissionSet() {
		super();
		this.permissions = Collections.emptyList();
		this.framePermissions = Collections.emptyList();
		this.constraints = FractionConstraints.createMutable().freeze();
	}
	
	/**
	 * Creates a permission set with the given <i>virtual</i> permissions.		
	 * @param permissions <b>virtual</b> permission set or <code>null</code>.
	 * @param isNamedUniversal Set to <code>true</code> to interpret
	 * {@link NamedFraction named fractions} as universal parameters.
	 */
	protected AbstractFractionalPermissionSet(List<? extends P> permissions, boolean isNamedUniversal) {
		super();
		if(permissions == null) {
			this.permissions = null;
			this.framePermissions = null;
			this.constraints = FractionConstraints.createMutable().freeze();
		}
		else {
			this.permissions = Collections.unmodifiableList(permissions);
			this.framePermissions = Collections.emptyList();
			assert checkPermissionSet(permissions);
			if(isNamedUniversal) {
				Set<NamedFraction> universals = new HashSet<NamedFraction>();
				for(P p : permissions) {
					universals.addAll(p.getFractions().getAllFractionsOfType(NamedFraction.class));
				}
				this.constraints = FractionConstraints.createMutable(universals).freeze();
			}
			else
				this.constraints = FractionConstraints.createMutable().freeze();
		}
	}

	/**
	 * Creates a new permission set with the given permissions and constraints.
	 * @param permissions
	 * @param framePermissions
	 * @param constraints
	 */
	protected AbstractFractionalPermissionSet(
			List<? extends P> permissions,
			List<? extends P> framePermissions,
			FractionConstraints constraints) {
		super();
		this.permissions = Collections.unmodifiableList(permissions);
		this.framePermissions = Collections.unmodifiableList(framePermissions);
		this.constraints = constraints.freeze();
		assert checkPermissionSet(permissions);
		assert checkPermissionSet(framePermissions);
	}
	
	/**
	 * Checks that the given permission set has permissions with orthogonal roots.
	 * @param <P>
	 * @param perms
	 * @return
	 */
	private static <P extends AbstractFractionalPermission> boolean checkPermissionSet(List<P> perms) {
		if(perms.size() <= 1)
			return true; // cannot be wrong
		
		Set<String> roots = new LinkedHashSet<String>(perms.size());
		for(P p : perms) {
			for(String s : roots) {
				if(p.getStateSpace().areOrthogonal(p.getRootNode(), s) == false) {
					log.warning("Conflicting permissions: " + perms);
					return false;
				}
			}
			if(roots.add(p.getRootNode()) == false)
				// shouldn't happen after the previous check
				throw new IllegalStateException();
		}
		return true;
	}

	/**
	 * Returns <code>true</code> if there are no permissions in this set.
	 * @return <code>true</code> if there are no permissions in this set, <code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return permissions.isEmpty() && framePermissions.isEmpty();
	}

	/**
	 * Returns the virtual permissions held in this set.
	 * @return the virtual permissions held in this set.
	 */
	public List<P> getPermissions() {
		return permissions;
	}
	
	/**
	 * Returns the frame permissions held in this set.
	 * @return the frame permissions held in this set.
	 */
	public List<P> getFramePermissions() {
		return framePermissions;
	}

	/**
	 * Returns the (frozen) constraints associated with this permission set. 
	 * @return the (frozen) constraints associated with this permission set.
	 */
	public FractionConstraints getConstraints() {
		return constraints;
	}
	
	/**
	 * Returns the list of free existentially quantified fractions, which
	 * can relatively easily be made subject to alpha-conversion.
	 * These are {@link NamedFraction named fractions} used directly in permissions
	 * that are <i>not</i> universal parameters (from a method pre-condition).
	 * Named fractions used in permissions can be considered free because nothing
	 * was split off from them or merged with them; once they are are split or merged,
	 * new permissions with {@link VariableFraction variable fractions} are created,
	 * which binds the previously free existentials because new constraints will depend
	 * on their name.
	 * @return the list of free existentially quantified fractions.
	 */
	protected Set<NamedFraction> getFreeExistentials() {
		Set<NamedFraction> result = new HashSet<NamedFraction>();
		for(P p : permissions) {
			result.addAll(p.getFractions().getAllFractionsOfType(NamedFraction.class));
		}
		for(P p : framePermissions) {
			result.addAll(p.getFractions().getAllFractionsOfType(NamedFraction.class));
		}
		result.removeAll(constraints.getUniversalParameters());
		return result;
	}
	
	/**
	 * Returns the state space for which this permission set was created.
	 * 
	 * <i>Note:</i> This method is abstract simply because 
	 * {@link FractionalPermissions} lazily determines the state space from
	 * the constituent permissions.
	 * @return the state space for which this permission set was created.
	 */
	public abstract StateSpace getStateSpace();

	/**
	 * Returns a string describing this permission set for a user.  
	 * It is permitted for this operation to take a while, unlike {@link #toString()}.
	 * @return a string describing this permission for a user, never <code>null</code>.  
	 */
	public abstract String getUserString();
	
	/**
	 * Helper method for {@link #getUserString()} that formats the given permission set.
	 * It is permitted for this operation to take a while, unlike {@link #toString()}.
	 * @param permissions
	 * @return  a string describing the given permission set for a user, or
	 * <code>null</code> if the given set contains no (real) permissions.
	 * @see AbstractFractionalPermission#getUserString(FractionConstraints)
	 */
	protected String getUserString(List<? extends AbstractFractionalPermission> permissions) {
		if(permissions.isEmpty())
			return null;

		StringBuffer result = new StringBuffer("[");
		boolean first = true;
		for(AbstractFractionalPermission p : permissions) {
			String ps = p.getUserString(constraints);
			if(ps == null)
				// not a real permission -> skip
				continue;
			if(first)
				first = false;
			else
				result.append(", ");
			result.append(ps.toString());
		}
		result.append("]");
		if(first)
			// no real permissions in the set
			return null;
		return result.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((constraints == null) ? 0 : constraints.hashCode());
		result = prime
				* result
				+ ((framePermissions == null) ? 0 : framePermissions.hashCode());
		result = prime * result
				+ ((permissions == null) ? 0 : permissions.hashCode());
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
		final AbstractFractionalPermissionSet other = (AbstractFractionalPermissionSet) obj;
		if (constraints == null) {
			if (other.constraints != null)
				return false;
		} else if (!constraints.equals(other.constraints))
			return false;
		if (framePermissions == null) {
			if (other.framePermissions != null)
				return false;
		} else if (!framePermissions.equals(other.framePermissions))
			return false;
		if (permissions == null) {
			if (other.permissions != null)
				return false;
		} else if (!permissions.equals(other.permissions))
			return false;
		return true;
	}

}