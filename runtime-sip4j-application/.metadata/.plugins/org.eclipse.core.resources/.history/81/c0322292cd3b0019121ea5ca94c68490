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
package edu.cmu.cs.plural.states;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import edu.cmu.cs.crystal.annotations.AnnotationDatabase;
import edu.cmu.cs.crystal.annotations.AnnotationSummary;
import edu.cmu.cs.crystal.annotations.ICrystalAnnotation;
import edu.cmu.cs.plural.perm.parser.PermAnnotation;
import edu.cmu.cs.plural.states.annowrappers.ClassStateDeclAnnotation;
import edu.cmu.cs.plural.states.annowrappers.StateDeclAnnotation;
import edu.cmu.cs.plural.states.annowrappers.StateInvAnnotation;
import edu.cmu.cs.plural.track.CrystalPermissionAnnotation;

/**
 * Singleton repository for state spaces.  This class has a crude
 * way of detecting when a new run of Crystal begins.  It would
 * be preferable to update the state space repository based on build
 * information, such as recompilations of source files.
 * 
 * @author Kevin Bierhoff
 *
 */
public class StateSpaceRepository {
	
	/** 
	 * Instances for in-flight annotation databases. Should be garbage-collected
	 * when annotation databases are no longer in use, but this <b>requires that all
	 * back-references to annotation database be weak.</b> */
	private static Map<AnnotationDatabase, StateSpaceRepository> instances =
		new WeakHashMap<AnnotationDatabase, StateSpaceRepository>();
	
	/**
	 * Returns the singleton instance of the state space repository.
	 * If none exists, one is created with the provided Crystal object.
	 * The Crystal object is needed for accessing the annotation
	 * database.
	 * @param crystal The Crystal instance, for retrieving the annotation database
	 * (and recognizing that a new Crystal run has begun).
	 * @return the singleton instance of the state space repository.
	 */
	public static StateSpaceRepository getInstance(AnnotationDatabase annoDB) {
		StateSpaceRepository instance;
		synchronized(instances) {
			instance = instances.get(annoDB);
			if(instance == null) {
				instance = new StateSpaceRepository(annoDB);
				instances.put(annoDB, instance);
			}
			return instance;
		}
	}

	/** 
	 * Maps Eclipse binding keys to their state spaces.
	 * This avoids causing memory problems by
	 * holding on to binding objects.
	 */
	private final Map<String, StateSpaceImpl> spaces;
	
	/**
	 * Maps Eclipse method binding keys to invocation signatures.
	 * This avoids memory problems by holding on to bindings.
	 */
	private final Map<String, IInvocationSignature> signatures;
	
	/** Annotation database used for building state spaces and signatures. */
	private final WeakReference<AnnotationDatabase> annoDB;
	
	/**
	 * Private constructor for singleton.
	 * @param annoDB Annotation database used for building state spaces and signatures.
	 */
	private StateSpaceRepository(AnnotationDatabase annoDB) {
		assert annoDB != null;
		this.annoDB = new WeakReference<AnnotationDatabase>(annoDB);
		spaces = new HashMap<String, StateSpaceImpl>();
		signatures = new HashMap<String, IInvocationSignature>();
	}
	
	/**
	 * Returns the state space for the given type binding.
	 * All state spaces are rooted in <i>alive</i>.
	 * @param type
	 * @return the state space for the given type binding.
	 * For "tricky" types such as arrays, primitives, type
	 * parameters, {@link StateSpace#SPACE_TOP} is returned 
	 * to at least give them at least an <i>alive</i> state.
	 * @see StateSpace#STATE_ALIVE
	 */
	public StateSpace getStateSpace(ITypeBinding type) {
		StateSpace result = getStateSpaceIfDefined(type);
		if(result != null)
			return result;
		else
			return StateSpace.SPACE_TOP;
	}
	
	/**
	 * Retrieves an existing state space or creates a new one, 
	 * if possible.
	 * @param type Type binding for the requested state space
	 * @return A "real" state space for classes and interfaces,
	 * <code>null</code> otherwise.
	 */
	private StateSpaceImpl getStateSpaceIfDefined(ITypeBinding type) {
		Map<String, StateSpaceImpl> spaces = getSpaces();
		String key = type.getKey();
		StateSpaceImpl result = spaces.get(key);
		if(result != null)
			return result;
		result = buildStateSpace(type, new HashMap<ICrystalAnnotation, Set<String>>());
		if(result != null) 
			spaces.put(key, result);
		return result;
	}
	
	/**
	 * This method can be used to check the validity of state space
	 * annotations on a type declaration.
	 * @param type
	 * @return Map of annotations on the given type to problems.
	 * The map can be empty, and the problem sets can be empty, but
	 * neither map nor any problem set may be <code>null</code>.
	 */
	public Map<ICrystalAnnotation, Set<String>> checkStateSpace(TypeDeclaration type) {
		Map<ICrystalAnnotation, Set<String>> result = new HashMap<ICrystalAnnotation, Set<String>>();
		ITypeBinding typeBinding = type.resolveBinding();
		StateSpaceImpl space = buildStateSpace(typeBinding, result);
		if(space != null)
			spaces.put(typeBinding.getKey(), space);
		return result;
	}
	
	/**
	 * Returns a signature object for the given method binding.
	 * This can be a constructor or actual method.
	 * @param binding
	 * @return Signature object for the given method binding.
	 * @see #getMethodSignature(IMethodBinding)
	 * @see #getConstructorSignature(IMethodBinding)
	 */
	public IInvocationSignature getSignature(IMethodBinding binding) {
		// TODO special treatment of annotation members?  Like, result is immutable? 
		while(binding != binding.getMethodDeclaration())
			binding = binding.getMethodDeclaration();
		
		Map<String, IInvocationSignature> signatures = getSignatures();
		String key = binding.getKey();
		IInvocationSignature result = signatures.get(key);
		if(result == null) {
			result = createSignature(binding);
			signatures.put(key, result);
		}
		return result;
	}

	/**
	 * Returns the method signature for the given method.
	 * @param binding This must be an actual method and not a constructor.
	 * @return Method signature object of the given method.
	 * @see #getConstructorSignature(IMethodBinding)
	 */
	public IMethodSignature getMethodSignature(IMethodBinding binding) {
		if(binding.isConstructor())
			throw new IllegalArgumentException("Cannot accept constructor binding: " + binding);
		return getSignature(binding).getMethodSignature();
	}

	/**
	 * Returns the constructor signature for the given constructor.
	 * @param binding This must be a constructor binding.
	 * @return Constructor signature object of the given constructor.
	 * @see #getMethodSignature(IMethodBinding)
	 */
	public IConstructorSignature getConstructorSignature(IMethodBinding binding) {
		if(binding.isConstructor() == false)
			throw new IllegalArgumentException("Not a constructor binding: " + binding);
		return getSignature(binding).getConstructorSignature();
	}

	/**
	 * Creates a fresh signature for the given binding.
	 * @param binding
	 * @return a fresh signature for the given binding.
	 */
	private IInvocationSignature createSignature(
			IMethodBinding binding) {
		IMethodBinding specBinding = findSpecificationMethod(binding);
		final AnnotationDatabase adb = getAnnotationDB();
		ICrystalAnnotation cases = adb.getSummaryForMethod(specBinding).getReturn("edu.cmu.cs.plural.annot.Cases");
		
		if(cases == null) {
			ICrystalAnnotation perm = adb.getSummaryForMethod(specBinding).getReturn("edu.cmu.cs.plural.annot.Perm");
			if(perm == null) {
				// no @Perm
				return binding.isConstructor() ? 
						new MultiCaseConstructorSignature(adb, specBinding, binding.getDeclaringClass()) : 
							new MultiCaseMethodSignature(adb, specBinding, binding.getDeclaringClass());
			}
			else {
				// @Perm on method
				return binding.isConstructor() ? 
						new MultiCaseConstructorSignature(adb, specBinding, binding.getDeclaringClass(), (PermAnnotation) perm) : 
							new MultiCaseMethodSignature(adb, specBinding, binding.getDeclaringClass(), (PermAnnotation) perm);
			}
		}
		else
			// @Cases on method
			return binding.isConstructor() ? 
					new MultiCaseConstructorSignature(adb, specBinding, binding.getDeclaringClass(), 
							downcast((Object[]) cases.getObject("value"), PermAnnotation.class))
					:
					new MultiCaseMethodSignature(adb, specBinding, binding.getDeclaringClass(),
							downcast((Object[]) cases.getObject("value"), PermAnnotation.class));
	}
	
	/**
	 * Finds a method binding with specs.
	 * @param binding
	 * @return Method binding with specs or <code>binding</code> if no spec was found anywhere.
	 */
	private IMethodBinding findSpecificationMethod(IMethodBinding binding) {
		if(binding.isConstructor() /* TODO static methods */)
			// constructors do not inherit specs
			return binding;
		
		if(hasSpecification(binding))
			return binding;
		
		Set<IMethodBinding> result = new LinkedHashSet<IMethodBinding>(1);
		if(findOverriddenMethodsWithSpecification(
				binding.getDeclaringClass(), // is this unique? 
				binding, new HashSet<ITypeBinding>(), result, 
				true,  // find only the first method
				true)) // skip the bindings's declaring class: we checked that already
			return result.iterator().next();
		else
			// no specs for this method: just return it
			return binding;
	}
	
	/**
	 * Finds all methods that define specs inherited by the given binding.
	 * @param binding
	 * @return (possibly empty) set of all methods that define specs inherited by the given binding.
	 */
	public Set<IMethodBinding> findAllMethodsWithSpecification(IMethodBinding binding) {
		if(hasSpecification(binding))
			return Collections.singleton(binding);
		
		if(binding.isConstructor() /* TODO static methods */)
			// constructors do not inherit specs
			return Collections.emptySet();

		Set<IMethodBinding> result = new LinkedHashSet<IMethodBinding>();
		findOverriddenMethodsWithSpecification(
				binding.getDeclaringClass(), // is this unique?  
				binding, new HashSet<ITypeBinding>(), result, 
				false, // find all matching methods 
				true); // skip the bindings's declaring class: we checked that already
		return Collections.unmodifiableSet(result);
	}
	
	private boolean hasSpecification(IMethodBinding binding) {
		AnnotationSummary summary = getAnnotationDB().getSummaryForMethod(binding);
		boolean hasSpec = false;
		if(summary.getReturn("edu.cmu.cs.plural.annot.Cases") != null)
			hasSpec = true;
		else if(summary.getReturn("edu.cmu.cs.plural.annot.Perm") != null)
			hasSpec = true;
		else if(!CrystalPermissionAnnotation.receiverAnnotations(getAnnotationDB(), binding).isEmpty())
			hasSpec = true;
		else if(!CrystalPermissionAnnotation.resultAnnotations(getAnnotationDB(), binding).isEmpty())
			hasSpec = true;
		else 
			for(int i = 0; i < binding.getParameterTypes().length; i++) {
				if(!CrystalPermissionAnnotation.parameterAnnotations(getAnnotationDB(), binding, i).isEmpty()) {
					hasSpec = true;
					break;
				}
			}
		
		return hasSpec;
	}

	/**
	 * <b>Return value is useless if <code>shortCicuit</code> is false.</b>
	 * @param type
	 * @param overriding
	 * @param seen
	 * @param result
	 * @param shortCircuit
	 * @param skipGivenType
	 * @return <b>useless if <code>shortCicuit</code> is false</b>; otherwise,
	 * indicates whether a method was found (<code>result</code> set is non-empty).
	 */
	private boolean findOverriddenMethodsWithSpecification(ITypeBinding type, 
			IMethodBinding overriding, Set<ITypeBinding> seen, Set<IMethodBinding> result, 
			boolean shortCircuit, boolean skipGivenType) {
		if(type == null || seen.add(type) == false)
			return false;
		
		if(!skipGivenType) {
			for(IMethodBinding m : type.getDeclaredMethods()) {
				if(overriding.isSubsignature(m)) {
					if(hasSpecification(m)) {
						result.add(m);
						if(shortCircuit)
							return true;
					}
				}
			}
		}
		
		// 2.1 search the superclass, if any
		if(type.isClass()) {
			if(findOverriddenMethodsWithSpecification(type.getSuperclass(), 
					overriding, seen, result, shortCircuit, false) && shortCircuit)
				return true;
		}
		// 2.2 search interfaces, if any
		for(ITypeBinding t : type.getInterfaces()) {
			if(findOverriddenMethodsWithSpecification(t, 
					overriding, seen, result, shortCircuit, false) && shortCircuit)
				return true;
		}
		
		// 3. nothing
		return false;
	}
	
	/**
	 * Builds a new state space for a given type binding, if possible.
	 * @param type
	 * @param problemsOut Map to place problems due to ill-formed state
	 * space annotations into.  This map will be populated by this method
	 * and must not be <code>null</code>.
	 * @return An all-new "real" state space for classes and interfaces,
	 * <code>null</code> otherwise.
	 */
	private StateSpaceImpl buildStateSpace(ITypeBinding type, 
			Map<ICrystalAnnotation, Set<String>> problemsOut) {
		if(type.isPrimitive())
			return null;
		if(type.isArray())
			return null;
		if(type.isTypeVariable())
			return null;
		if(type.isNullType())
			return null;
		if(type.isAnnotation())
			return null;
		
		StateSpaceImpl result = new StateSpaceImpl(type.getQualifiedName());
		
		// process annotations on supertypes
		visitSupertypes(type, result);
		// process state annotations on this type, if any
		if(getAnnotationDB().getAnnosForType(type) != null) {
			Map<String, ICrystalAnnotation> refinedStates = new HashMap<String, ICrystalAnnotation>();
			for(ICrystalAnnotation a : getAnnotationDB().getAnnosForType(type)) {
				if("edu.cmu.cs.plural.annot.States".equals(a.getName())) {
					String[] states = downcast((Object[]) a.getObject("value"), String.class);
					String dimName = (String) a.getObject("dim");
					String refined = (String) a.getObject("refined");
					boolean marker = (Boolean) a.getObject("marker");
					
					if("".equals(dimName)) {
						problemsOut.put(a, 
								result.addAnonymousDimension(states, refined, marker));
					}
					else {
						problemsOut.put(a, 
								result.addNamedDimension(dimName, states, refined, marker));
					}
					refinedStates.put(refined, a);
				}
			}
			// make sure all refined states are defined
			for(String refined : refinedStates.keySet()) {
				if(result.isKnown(refined) == false) {
					Set<String> annoProblems = problemsOut.get(refinedStates.get(refined));
					if(annoProblems == null) {
						annoProblems = new LinkedHashSet<String>();
						problemsOut.put(refinedStates.get(refined), annoProblems);
					}
					annoProblems.add("Refined state unknown: " + refined);
					// slam in unknown states...
					result.addAnonymousState(refined);
				}
			}
		}
		
		// traverse defined state invariants to infer root node for fields
		// this must happen after processing @States annotations
		// because we need to compare states
		Map<String, String> fieldMap = new HashMap<String, String>();
		for(ICrystalAnnotation a : getAnnotationDB().getAnnosForType(type)) {
			if(a instanceof ClassStateDeclAnnotation) {
				ClassStateDeclAnnotation csda = (ClassStateDeclAnnotation) a;
				for(StateDeclAnnotation stateAnno : csda.getStates()) {
					String state = stateAnno.getStateName();
					if(! result.isKnown(state))
						result.addAnonymousState(state);
					for(StateInvAnnotation inv : stateAnno.getInvs()) {
						String field = inv.getField();
						String oldNode = fieldMap.get(field);
						if(oldNode == null)
							fieldMap.put(field, state);
						else {
							if(result.firstBiggerThanSecond(state, oldNode)) {
								fieldMap.put(field, state);
							}
							else if(result.firstBiggerThanSecond(oldNode, state)) {
								// nothing!
							}
							else {
								// find some mutual super-state
								String parent = result.getLowestCommonParent(state, oldNode);
								fieldMap.put(field, parent);
							}
						}
					}
				}
			}
		}
		result.setFieldMap(fieldMap);
		return result;
	}
	
	/**
	 * Entry method for depth-first search traversal of supertypes of the given type
	 * to populate the given state space object with state dimensions
	 * defined in supertypes.
	 * @param type
	 * @param result
	 * @see #visitSupertype(ITypeBinding, Set, StateSpaceImpl)
	 */
	private void visitSupertypes(ITypeBinding type, StateSpaceImpl result) {
		Set<ITypeBinding> seen = new HashSet<ITypeBinding>();
		seen.add(type);
		visitSupertype(type.getSuperclass() /* may be null */, seen, result);
		for(ITypeBinding itf : type.getInterfaces() /* may not be null */) {
			visitSupertype(itf, seen, result);
		}
	}
	
	/**
	 * Depth-first search traversal of supertypes of the given type
	 * to populate the given state space object with state dimensions
	 * defined in supertypes.
	 * @param type
	 * @param seen
	 * @param result
	 */
	private void visitSupertype(ITypeBinding type, Set<ITypeBinding> seen, StateSpaceImpl result) {
		if(type == null || seen.contains(type))
			return;
		seen.add(type);
		visitSupertype(type.getSuperclass() /* may be null */, seen, result);
		for(ITypeBinding itf : type.getInterfaces() /* may not be null */) {
			visitSupertype(itf, seen, result);
		}
		
		if(getAnnotationDB().getAnnosForType(type) == null)
			return;
		for(ICrystalAnnotation a : getAnnotationDB().getAnnosForType(type)) {
			if("edu.cmu.cs.plural.annot.States".equals(a.getName())) {
				String[] states = downcast((Object[]) a.getObject("value"), String.class);
				String dimName = (String) a.getObject("dim");
				String refined = (String) a.getObject("refined");
				boolean marker = (Boolean) a.getObject("marker");
				
				if("".equals(dimName)) {
					// anonymous dimension: try to look up existing name for it in inherited state space
					if(states.length == 0)
						continue;
					StateSpaceImpl superSpace = getStateSpaceIfDefined(type);
					if(superSpace.isDimension(superSpace.getParent(states[0])))
						dimName = superSpace.getParent(states[0]);
				}
				if("".equals(dimName)) {
					result.addAnonymousDimension(states, refined, marker);
				}
				else {
					result.addNamedDimension(dimName, states, refined, marker);
				}
			}
		}
		// TODO check for undefined refined states
	}
	
	/**
	 * Returns a new array of the given type and copies the elements from
	 * the given array into it, effectively coercing the given array to a subtype.
	 * Retrieving an element from the resulting array will only succeed if
	 * that element is a subtype of <code>T</code>.
	 * @param <S> Type of the original array
	 * @param <T> Type for the new array, must be a subtype of <code>S</code>
	 * @param array
	 * @param target Parameter to identify the type of the new array
	 * @return a new array of the given type with the elements of the given array
	 */
	private static <S, T extends S> T[] downcast(S[] array, Class<T> target) {
		T[] result = (T[]) Array.newInstance(target, array.length);
		System.arraycopy(array, 0, result, 0, array.length);
		return result;
	}

	/**
	 * Returns the annotation database.
	 * @return The Crystal annotation database used by this repository.
	 */
	private AnnotationDatabase getAnnotationDB() {
		AnnotationDatabase result = annoDB.get();
		assert result != null : "Annotation database was unexpectedly garbage-collected";
		return result;
	}

	/**
	 * Returns the cache of available state spaces, indexed by binding keys;
	 * <b>always use this method instead of accessing {@link #spaces} directly</b>.
	 * Before returning the map the method checks if the annotation database
	 * has changed, which indicates a new Crystal run, and if so clears the cache.
	 * @return the cache of available state spaces, indexed by binding keys.
	 * @see #getAnnotationDB()
	 */
	private Map<String, StateSpaceImpl> getSpaces() {
		return spaces;
	}

	/**
	 * Returns the cache of available signatures, indexed by binding keys;
	 * <b>always use this method instead of accessing {@link #signatures} directly</b>.
	 * Before returning the map the method checks if the annotation database
	 * has changed, which indicates a new Crystal run, and if so clears the cache.
	 * @return the cache of available signatures, indexed by binding keys.
	 * @see #getAnnotationDB()
	 */
	private Map<String, IInvocationSignature> getSignatures() {
		return signatures;
	}

}
