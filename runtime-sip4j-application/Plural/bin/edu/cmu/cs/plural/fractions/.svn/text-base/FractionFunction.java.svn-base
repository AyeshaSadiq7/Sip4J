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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import edu.cmu.cs.plural.states.StateSpace;

/**
 * A fraction function represents the actual mapping from nodes in
 * the state hierarchy to fractional values. This is a pretty literal
 * encoding of the fraction function from the OOPSLA paper. There are
 * a number of methods for creating commonly used configurations.
 * 
 * @author Kevin Bierhoff
 *
 */
public class FractionFunction {

	private StateSpace stateSpace;
	private String rootNode;
	private Map<String, Fraction> values;
	private Fraction belowFraction;
	
	/**
	 * Creates a fraction function with the given root node with all fractional
	 * values set to the given fill value.
	 * @param stateSpace
	 * @param rootNode
	 * @param fill Fraction that all node mappings should be set to.  
	 * @return A new fraction function object.
	 */
	public static FractionFunction fixAll(StateSpace stateSpace, String rootNode, Fraction fill) {
		return new FractionFunction(stateSpace, rootNode, fill);
	}

	/**
	 * Creates a fraction function with a fixed (given) below fraction and either constant
	 * or variable fractions for every other node (determined by <code>namedFractions</code>). 
	 */
	public static FractionFunction fixedBelow(StateSpace stateSpace, String rootNode, boolean namedFractions, Fraction belowFraction) {
		return new FractionFunction(stateSpace, rootNode, namedFractions, belowFraction);
	}

	public static FractionFunction variableAll(StateSpace stateSpace, String rootNode, boolean namedFractions) {
		return new FractionFunction(stateSpace, rootNode, namedFractions);
	}

	/**
	 * This method returns a fraction function where the fraction values for the
	 * nodes in the map <code>values</code> are used as-is and the remaining 
	 * nodes are assigned to variable values.
	 * @param stateSpace
	 * @param rootNode
	 * @param namedFractions
	 * @param values The given mappings. The key/value pairs in this map
	 * will be used, but all others will be variable.
	 * @param belowFraction
	 * @return
	 */
	public static FractionFunction variableRemaining(StateSpace stateSpace, String rootNode, boolean namedFractions,
			Map<String, Fraction> values, Fraction belowFraction) {
		return new FractionFunction(stateSpace, rootNode, namedFractions, values, belowFraction);
	}

	/**
	 * Creates a new fraction function based on the existing one
	 * but with the given fraction values, where defined.
	 * @param base
	 * @param values
	 * @param below
	 * @return
	 */
	public static FractionFunction create(
			FractionFunction base,
			HashMap<String, Fraction> values,
			Fraction below) {
		// TODO check parameter validity
		return new FractionFunction(base.stateSpace, base.rootNode, values, below);
	}

	/**
	 * Creates a new fraction function filled with {@link VariableFraction}s.
	 * @param stateSpace
	 * @param rootNode
	 */
	public FractionFunction(StateSpace stateSpace, String rootNode) {
		this(stateSpace, rootNode, false);
	}

	/**
	 * Creates a new fraction function filled with {@link VariableFraction}s
	 * or {@link NamedFraction}s.
	 * @param stateSpace
	 * @param rootNode
	 * @param namedFractions Fills with fresh {@link NamedFraction}s if this parameter
	 * is <code>true</code>, otherwise with {@link VariableFraction}s. 
	 */
	private FractionFunction(StateSpace stateSpace, String rootNode, boolean namedFractions) {
		super();
		this.stateSpace = stateSpace;
		this.rootNode = rootNode;
		this.values = new HashMap<String, Fraction>();
		Iterator<String> it = stateSpace.stateIterator(rootNode);
		while(it.hasNext()) {
			this.values.put(it.next(), namedFractions ? new NamedFraction() : new VariableFraction());
		}
		this.belowFraction = namedFractions ? new NamedFraction() : new VariableFraction();
	}

	/**
	 * Creates a new fraction function filled with {@link VariableFraction}s
	 * or {@link NamedFraction}s.
	 * @param stateSpace
	 * @param rootNode
	 * @param namedFractions Fills with fresh {@link NamedFraction}s if this parameter
	 * is <code>true</code>, otherwise with {@link VariableFraction}s. 
	 */
	private FractionFunction(StateSpace stateSpace, String rootNode, 
			boolean namedFractions, Map<String, Fraction> values, Fraction belowFraction) {
		super();
		this.stateSpace = stateSpace;
		this.rootNode = rootNode;
		this.values = new HashMap<String, Fraction>();
		Iterator<String> it = stateSpace.stateIterator(rootNode);
		while(it.hasNext()) {
			String n = it.next();
			if(values.get(n) != null)
				this.values.put(n, values.get(n));
			else
				this.values.put(n, namedFractions ? new NamedFraction() : new VariableFraction());
		}
		this.belowFraction = belowFraction;
	}

	/**
	 * Creates a new fraction function filled with {@link VariableFraction}s
	 * or {@link NamedFraction}s.
	 * @param stateSpace
	 * @param rootNode
	 * @param namedFractions Fills with fresh {@link NamedFraction}s if this parameter
	 * is <code>true</code>, otherwise with {@link VariableFraction}s.
	 * @param belowFraction 
	 */
	private FractionFunction(StateSpace stateSpace, String rootNode, boolean namedFractions,
			Fraction belowFraction) {
		super();
		this.stateSpace = stateSpace;
		this.rootNode = rootNode;
		this.values = new HashMap<String, Fraction>();
		Iterator<String> it = stateSpace.stateIterator(rootNode);
		while(it.hasNext()) {
			this.values.put(it.next(), namedFractions ? new NamedFraction() : new VariableFraction());
		}
		this.belowFraction = belowFraction;
	}

	/**
	 * Creates a new fraction function filled with a given fraction.
	 * @param stateSpace
	 * @param rootNode
	 * @param fill All nodes will be mapped to this fraction.
	 */
	private FractionFunction(StateSpace stateSpace, String rootNode, Fraction fill) {
		super();
		this.stateSpace = stateSpace;
		this.rootNode = rootNode;
		this.values = new HashMap<String, Fraction>();
		Iterator<String> it = stateSpace.stateIterator(rootNode);
		while(it.hasNext()) {
			this.values.put(it.next(), fill);
		}
		this.belowFraction = fill;
	}

	/**
	 * Creates a fraction function based on an existing one and fills any
	 * nodes that don't have a fraction in the existing function with a given value.
	 * @param fractions
	 * @param newRootNode
	 * @param fill
	 */
	public FractionFunction(FractionFunction fractions, String newRootNode, Fraction fill) {
		this.stateSpace = fractions.stateSpace;
		this.rootNode = newRootNode;
		this.values = new HashMap<String, Fraction>();
		Iterator<String> it = stateSpace.stateIterator(newRootNode);
		while(it.hasNext()) {
			String n = it.next();
			if(fractions.values.containsKey(n))
				this.values.put(n, fractions.get(n));
			else
				this.values.put(n, fill);
		}
		this.belowFraction = fill;
	}

	/**
	 * Creates a new fraction function with the given information.
	 * @param stateSpace
	 * @param rootNode
	 * @param values
	 * @param belowFraction
	 */
	public FractionFunction(StateSpace stateSpace, String rootNode,
			Map<String, Fraction> values, Fraction belowFraction) {
		this.stateSpace = stateSpace;
		this.rootNode = rootNode;
		this.values = values;
		this.belowFraction = belowFraction;
	}
	
	/**
	 * Returns the fraction function's root node, which
	 * is the most precise node in the state space for which
	 * this fraction function holds a fraction.
	 * @return the fraction function's root node.
	 */
	public String getRootNode() {
		return rootNode;
	}

	/**
	 * Returns the fraction held by this fraction function for
	 * the given node.
	 * This is:
	 * <ul>
	 * <li>The fraction stored for the given node, if defined.</li>
	 * <li>The {@link #getBelowFraction() below fraction}, if the given
	 * node is strictly inside the {@link #getRootNode() root node}.</li>
	 * <li>Zero otherwise.</li>
	 * </ul>
	 * @param node A node in the state space.
	 * @return the fraction held by this fraction function for
	 * the given node.
	 */
	public Fraction get(String node) {
		if(values.containsKey(node))
			return values.get(node);
		if(stateSpace.firstBiggerThanSecond(rootNode, node))
			return belowFraction;
		return Fraction.zero();
	}

	/**
	 * Returns the below fraction of this fraction function.
	 * @return the below fraction.
	 */
	public Fraction getBelowFraction() {
		return belowFraction;
	}
	
	/**
	 * Returns fresh set of all fractions used.  
	 * @return Fresh set of all fractions used.
	 */
	public Set<Fraction> getAllFractions() {
		HashSet<Fraction> result = new HashSet<Fraction>(values.values());
		result.add(belowFraction);
		return result;
	}
	
	/**
	 * Returns the number of nodes in this fraction function, excluding
	 * the {@link #getBelowFraction() below fraction}.
	 * @return the number of nodes in this fraction function.
	 */
	public int size() {
		return values.size();
	}

	/**
	 * Tests if this fraction function is guaranteed bigger than the argument.
	 * It does so by comparing pointwise comparison of fractions for the same
	 * node from the two functions.
	 * This is a very conservative test and probably rarely useful.
	 * @param other Another fraction function with the same 
	 * {@link #getRootNode root node} as this fraction function.
	 * @return <code>true</code> if this fraction function is guaranteed bigger
	 * than <code>other</code>.
	 */
	public boolean isBiggerThan(FractionFunction other) {
		if(rootNode.equals(other.rootNode) == false)
			throw new IllegalArgumentException("Cannot compare functions with different roots");
		Iterator<String> it = stateSpace.stateIterator(rootNode);
		while(it.hasNext()) {
			String s = it.next();
			if(other.values.get(s).isPossiblyGreaterOrEqual(this.values.get(s)))
				return false;
		}
		return !other.belowFraction.isPossiblyGreaterOrEqual(this.belowFraction);
	}
	
	/**
	 * Returns all fractions used in this fraction function that are of the given
	 * type.  
	 * This is useful to, e.g., filter out all {@link NamedFraction} instances
	 * used in this fraction function.
	 * @param <T> Type parameter for better typing the returned set.
	 * @param fractionType Desired fraction type.
	 * @return Set of all all fractions used in this fraction function that are 
	 * of the given type.
	 */
	public <T extends Fraction> Set<T> getAllFractionsOfType(Class<T> fractionType) {
		Set<T> result = new HashSet<T>();
		for(Fraction f : values.values()) {
			if(fractionType.isInstance(f))
				result.add(fractionType.cast(f));
		}
		if(fractionType.isInstance(belowFraction))
			result.add(fractionType.cast(belowFraction));
		return result;
	}

	/**
	 * Returns a new fraction function that uses Zero as the below fraction,
	 * to indicate a <i>pure</i> permission.
	 * @return a new fraction function that uses Zero as the below fraction.
	 */
	public FractionFunction purify() {
		return new FractionFunction(stateSpace, rootNode, values, Fraction.zero());
	}

	@Override
	public String toString() {
		return values + " below=" + belowFraction;
				
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((belowFraction == null) ? 0 : belowFraction.hashCode());
		result = prime * result
				+ ((rootNode == null) ? 0 : rootNode.hashCode());
		result = prime * result
				+ ((stateSpace == null) ? 0 : stateSpace.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		final FractionFunction other = (FractionFunction) obj;
		if (belowFraction == null) {
			if (other.belowFraction != null)
				return false;
		} else if (!belowFraction.equals(other.belowFraction))
			return false;
		if (rootNode == null) {
			if (other.rootNode != null)
				return false;
		} else if (!rootNode.equals(other.rootNode))
			return false;
		if (stateSpace == null) {
			if (other.stateSpace != null)
				return false;
		} else if (!stateSpace.equals(other.stateSpace))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

}
