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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cmu.cs.plural.states.StateSpace;

public abstract class AbstractFractionalPermission {
	
	private static final Logger log = Logger.getLogger(AbstractFractionalPermission.class.getName());

	protected final StateSpace stateSpace;
	protected final String rootNode;
	protected final FractionFunction fractions;
	protected final boolean mutable;
	protected final Set<String> stateInfo;

	public AbstractFractionalPermission(
			StateSpace stateSpace,
			String rootNode,
			FractionFunction fractions,
			boolean mutable,
			String... stateInfo) {
		this(stateSpace, rootNode, fractions, mutable, Arrays.asList(stateInfo));
	}
	
	public AbstractFractionalPermission(
			StateSpace stateSpace,
			String rootNode,
			FractionFunction fractions,
			boolean mutable,
			Iterable<String> stateInfo) {
		super();
		this.stateSpace = stateSpace;
		this.rootNode = rootNode;
		this.fractions = fractions;
		this.mutable = mutable;
		Set<String> stateSet = cleanStateInfo(stateSpace, rootNode,
				stateInfo, false);
		if(stateSet.isEmpty())
			this.stateInfo = Collections.singleton(rootNode);
		else {
			this.stateInfo = Collections.unmodifiableSet(stateSet);
		}
	}

	public static Set<String> filterStateInfo(StateSpace stateSpace,
			Iterable<String> stateInfo, String newRootNode) {
		Set<String> info = new LinkedHashSet<String>();
		for(String s : stateInfo) {
			if(stateSpace.firstBiggerThanSecond(newRootNode, s))
				info.add(s);
		}
		return info;
	}
	
	/**
	 * @param stateSpace
	 * @param stateInfo
	 * @param expectProblems
	 * @return
	 */
	public static Set<String> cleanStateInfo(StateSpace stateSpace,
			Iterable<String> stateInfo, boolean expectProblems) {
		return cleanStateInfo(stateSpace, StateSpace.STATE_ALIVE, stateInfo, expectProblems, false);
	}
	
	/**
	 * @param stateSpace
	 * @param rootNode
	 * @param stateInfo
	 * @param expectProblems
	 * @return
	 */
	public static Set<String> cleanStateInfo(StateSpace stateSpace,
			String rootNode, Iterable<String> stateInfo, boolean expectProblems) {
		return cleanStateInfo(stateSpace, rootNode, stateInfo, expectProblems, false);
	}

	/**
	 * @param stateSpace
	 * @param rootNode
	 * @param stateInfo
	 * @param expectProblems
	 * @param isFilterForRootNode
	 * @return
	 */
	public static Set<String> cleanStateInfo(StateSpace stateSpace,
			String rootNode, Iterable<String> stateInfo, boolean expectProblems, boolean isFilterForRootNode) {
		Level logLevel = expectProblems ? Level.FINE : Level.WARNING;
		LinkedHashSet<String> stateSet = new LinkedHashSet<String>();
		next_state:
		for(String s : stateInfo) {
			if(s == null || s.isEmpty()) {
				log.warning("Dropping invalid state name \"" + s + "\" in " + stateInfo);
				continue;
			}
			if(stateSpace.firstBiggerThanSecond(rootNode, s)) {
				for(Iterator<String> oldIt = stateSet.iterator(); oldIt.hasNext(); ) {
					String old_state = oldIt.next();
					if(stateSpace.firstBiggerThanSecond(s, old_state)) {
						if(log.isLoggable(logLevel))
							log.log(logLevel, "Dropping redundant state \"" + s + "\" in " + stateInfo);
						continue next_state;  // do not add s
					}
					else if(stateSpace.firstBiggerThanSecond(old_state, s)) {
						if(log.isLoggable(logLevel))
							log.log(logLevel, "Replacing redundant state \"" + old_state + "\" in " + stateInfo);
						oldIt.remove(); // remove old_state; add s below
					}
					else if(stateSpace.areOrthogonal(old_state, s) == false) {
						if(log.isLoggable(logLevel))
							log.log(logLevel, "Dropping mutually exclusive states \"" + old_state + "\" and \"" + s + "\" in: " + stateInfo);
						oldIt.remove();   // remove old_state and do not add s
						continue next_state;
					}
				}
				stateSet.add(s);
			}
			else {
				if(! isFilterForRootNode)
					log.warning("Presented state info not inside root node (" + rootNode + "): " + s);
			}
		}
		return stateSet;
	}

	public String getRootNode() {
		return rootNode;
	}

	public Set<String> getStateInfo() {
		return stateInfo;
	}

	public StateSpace getStateSpace() {
		return stateSpace;
	}

	public FractionFunction getFractions() {
		return fractions;
	}

	public boolean isReadOnly() {
		return (mutable == false);
	}
	
	public boolean impliesState(String node) {
		for(String s : stateInfo) {
			if(stateSpace.firstImpliesSecond(s, node))
				return true;
		}
		return false;
	}
	
	public boolean impliesAll(Iterable<String> nodes) {
		for(String n : nodes) {
			if(impliesState(n) == false)
				return false;
		}
		return true;
	}
	
	/**
	 * Indicates whether the given node is below this permission's root.
	 * @param node
	 * @return <code>true</code> if the given node is below this permission's root,
	 * <code>false</code> otherwise.
	 */
	public boolean coversNode(String node) {
		return stateSpace.firstBiggerThanSecond(rootNode, node);
	}

	protected void registerFractions(FractionConstraints constraints) {
		constraints.registerFractions(fractions.getAllFractions());
	}

	protected boolean addIsFunctionConstraints(FractionConstraints constraints) {
		// constraints for node fractions
		int count = fractions.size();
		if(count > 1) {
			FractionTerm[] newTerms = new FractionTerm[count];
			Iterator<String> it = stateSpace.stateIterator(rootNode);
			while(it.hasNext()) {
				// remember all new fractions for "is a function" constraint
				newTerms[--count] = fractions.get(it.next());
			}
			// "is a function" constraint: f(alive) <= ... <= f(rootNode)
			constraints.addConstraint(
					FractionConstraint.createLessThanOrEqual(newTerms));
			return true;
		}
		else
			// there's only one fraction in the function --> skip
			return false;
	}

	/**
	 * Adds an "existing permission is real" constraint to the given constraints. 
	 * @param constraints
	 */
	public void addIsUsedConstraint(FractionConstraints constraints) {
		// fraction function must be non-zero
		// since fractions.get(alive) <= all other fractions, 
		// just add 0 < fractions.get(alive)
		constraints.addConstraint(FractionConstraint.createLessThan(
				Fraction.zero(),
				this.fractions.get(this.getStateSpace().getRootState())));
		
	}

	/**
	 * Returns a string describing this permission, possibly resolving the given
	 * constraints.
	 * This operation is allowed to take a while, unlike {@link #toString()}.
	 * @param constraints
	 * @return a string describing this permission, or <code>null</code> 
	 * if this is not a real permission.
	 */
	public abstract String getUserString(FractionConstraints constraints);
	
	@Override
	public String toString() {
		return "access(" + rootNode + " " + fractions + (mutable ? " mutable" : " immutable") + ") in " + stateInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fractions == null) ? 0 : fractions.hashCode());
		result = prime * result + (mutable ? 1231 : 1237);
		result = prime * result
				+ ((rootNode == null) ? 0 : rootNode.hashCode());
		result = prime * result
				+ ((stateInfo == null) ? 0 : stateInfo.hashCode());
		result = prime * result
				+ ((stateSpace == null) ? 0 : stateSpace.hashCode());
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
		AbstractFractionalPermission other = (AbstractFractionalPermission) obj;
		if (fractions == null) {
			if (other.fractions != null)
				return false;
		} else if (!fractions.equals(other.fractions))
			return false;
		if (mutable != other.mutable)
			return false;
		if (rootNode == null) {
			if (other.rootNode != null)
				return false;
		} else if (!rootNode.equals(other.rootNode))
			return false;
		if (stateInfo == null) {
			if (other.stateInfo != null)
				return false;
		} else if (!stateInfo.equals(other.stateInfo))
			return false;
		if (stateSpace == null) {
			if (other.stateSpace != null)
				return false;
		} else if (!stateSpace.equals(other.stateSpace))
			return false;
		return true;
	}

}