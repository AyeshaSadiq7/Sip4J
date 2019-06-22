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
package edu.cmu.cs.plural.concrete;

import static edu.cmu.cs.crystal.util.ConsList.cons;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.jdt.core.dom.ASTNode;

import edu.cmu.cs.crystal.analysis.alias.Aliasing;
import edu.cmu.cs.crystal.tac.model.Variable;
import edu.cmu.cs.crystal.util.ConsList;
import edu.cmu.cs.crystal.util.Freezable;
import edu.cmu.cs.crystal.util.Lambda;
import edu.cmu.cs.crystal.util.Lambda2;
import edu.cmu.cs.crystal.util.Option;
import edu.cmu.cs.crystal.util.Pair;
import edu.cmu.cs.plural.fractions.PermissionSetFromAnnotations;
import edu.cmu.cs.plural.linear.ReleasePermissionImplication;
import edu.cmu.cs.plural.perm.parser.ReleaseHolder;
import edu.cmu.cs.plural.pred.PredicateChecker.SplitOffTuple;
import edu.cmu.cs.plural.track.PluralTupleLatticeElement;

/**
 * This type is in charge of keeping track of facts about variables and state
 * implications and then eliminating implications when the time is right.
 * 
 * @author Nels Beckman
 * @date Feb 11, 2008
 *
 */
final public class DynamicStateLogic implements Freezable<DynamicStateLogic> {

	/*
	 * Variables predicates known to be true. Weak because there may be a large number
	 * of variables in this program, and while I expect Variables to remain for all the
	 * time we need them, soundness is not affected by these predicates being removed.
	 */
	final private Map<Aliasing, VariablePredicate> knownPredicates;
	/*
	 * Implications about variable states known to hold.
	 */
	final private Map<Aliasing, ConsList<Implication>> knownImplications;
	
//	final private Map<Aliasing, List<DelayedImplication>> delayedImplications;
		
	/*
	 * Can the dynamic state logic be modified?
	 */
	private boolean frozen = false;
	
	public DynamicStateLogic() {
		this.knownPredicates = new WeakHashMap<Aliasing, VariablePredicate>();
		this.knownImplications = new WeakHashMap<Aliasing, ConsList<Implication>>();
//		this.delayedImplications = new WeakHashMap<Aliasing, List<DelayedImplication>>();
	}
	
	private DynamicStateLogic(Map<Aliasing, VariablePredicate> kp,
			                 Map<Aliasing, ConsList<Implication>> ki) {
		this.knownPredicates = kp;
		this.knownImplications = ki;
//		this.delayedImplications = di;
	}
	
	/**
	 * First off, you probably want to call {@link #solveWithHint(Variable)} as this
	 * method is quite slow. Given the current state of the truth and implications,
	 * this method will return all facts that it can deduce from those facts and
	 * implications.
	 * @return
	 */
	public List<ImplicationResult> solve(PluralTupleLatticeElement value) {
		if( this.isBottom() ) return Collections.emptyList();
		
		List<ImplicationResult> result = new LinkedList<ImplicationResult>();
		
		for( Aliasing var : knownPredicates.keySet() ) {
			VariablePredicate pred = knownPredicates.get(var);
			/*
			 * If this predicate can eliminate an implication, we get
			 * those facts.
			 */
			if( !knownImplications.containsKey(var) ) continue;
			
			List<Implication> impls = knownImplications.get(var);
			for(Implication impl : impls ) {
				if( impl.getAntecedant().isSatisfied(value) ) {
					result.add(impl.result());
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Solves equations, eliminating implications given a hint. 
	 * 
	 * The hint actually determines which facts you will get back. The hint is,
	 * "which variable did I just look at and therefore is a very good candidate
	 * for teaching me new facts."
	 * 
	 * @param v
	 * @return
	 */
	public List<ImplicationResult> solveWithHint(PluralTupleLatticeElement value, Aliasing v) {
		if( this.isBottom() ) return Collections.emptyList();
		
		if( !knownPredicates.containsKey(v) ) 
			return Collections.emptyList();

		if( !knownImplications.containsKey(v) )
			return Collections.emptyList();

		List<ImplicationResult> result = new LinkedList<ImplicationResult>();
	
		List<Implication> impls = knownImplications.get(v);
		
		for( Implication impl : impls ) {
			if( impl.getAntecedant().isSatisfied(value) ) {
				result.add(impl.result());
			}
		}
		
		return result;
	}
	
	/**
	 * Same as {@link #solveWithHint(Aliasing)}, but with multiple hints. 
	 * In fact, this method just calls
	 * solveWithHint several times.
	 * @param vs
	 * @return
	 */
	public List<ImplicationResult> solveWithHints(PluralTupleLatticeElement value, Aliasing... vs) {
		List<ImplicationResult> result = new LinkedList<ImplicationResult>();
		
		for( Aliasing v : vs ) {
			result.addAll(this.solveWithHint(value, v));
		}
		
		return result;
	}
	
	/**
	 * Same as {@link #solveWithHint(Aliasing)}, but with multiple hints. 
	 * In fact, this method just calls
	 * solveWithHint several times.
	 * @param vs
	 * @return
	 */
	public List<ImplicationResult> solveWithHints(PluralTupleLatticeElement value, Iterable<Aliasing> vs) {
		List<ImplicationResult> result = new LinkedList<ImplicationResult>();
		
		for( Aliasing v : vs ) {
			result.addAll(this.solveWithHint(value, v));
		}
		
		return result;
	}
	
	/**
	 * the idea is to pass a filter, and the DSL solves for all locations for which 
	 * the filter returns true in the GC case, the filter returns true iff the 
	 * location is dead i do it this way because i don't want to find out beforehand
	 * all locations that are dead, and instead have the DSL ask me about the
	 * locations it actually has facts for
	 */
	public List<ImplicationResult> solveFilteredVariables(PluralTupleLatticeElement value, 
			Lambda<Aliasing, Boolean> liveness) {
		
		List<ImplicationResult> result = new LinkedList<ImplicationResult>();
		for(Map.Entry<Aliasing, ConsList<Implication>> impls : knownImplications.entrySet()) {
			if( liveness.call(impls.getKey()) ) {
				for(Implication impl : impls.getValue()) {
					if(impl.getAntecedant().isSatisfied(value))
						result.add(impl.result());
				}
			}
		}
		return Collections.unmodifiableList(result);
	}
	
	/**
	 * Record the fact that the two variables given are equal.
	 * @param v_1
	 * @param v_2
	 */
	public void addEquality(Aliasing v_1, Aliasing v_2) {
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");
		
		if( knownPredicates.containsKey(v_1) ) {
			this.addIdenticalPredicate(v_1, v_2);
		}
		else if( knownPredicates.containsKey(v_2) ) {
			this.addIdenticalPredicate(v_2, v_1);
		}
		if( knownImplications.containsKey(v_1) ) {
			this.addIdenticalImplication(v_1, v_2);
		}
		else if( knownImplications.containsKey(v_2) ) {
			this.addIdenticalImplication(v_2, v_1);
		}
		
	}
	
	/**
	 * Record the fact that two variables given are not equal.
	 * @param v_1
	 * @param v_2
	 */
	public void addInequality(Aliasing v_1, Aliasing v_2) {
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");
		
		if( knownPredicates.containsKey(v_1) ) {
			this.addOppositePredicate(v_1, v_2);
		}
		else if( knownPredicates.containsKey(v_2) ) {
			this.addOppositePredicate(v_2, v_1);
		}
		if( knownImplications.containsKey(v_1) ) {
			this.addOppositeImplication(v_1, v_2);
		}
		else if( knownImplications.containsKey(v_2) ) {
			this.addOppositeImplication(v_2, v_1);
		}
	}
	
	private void addOppositeImplication(Aliasing known, Aliasing other) {
		assert(this.knownImplications.containsKey(known));
		
		List<Implication> impls = knownImplications.get(known);
		for( Implication impl : impls ) {
			Implication new_impl = impl.createCopyWithOppositeAntecedant(other);
			if(new_impl != null)
				this.addImplication(other, new_impl);
		}
	}

	private void addOppositePredicate(Aliasing known, Aliasing other) {
		assert( knownPredicates.containsKey(known) );		
		
		VariablePredicate pred = knownPredicates.get(known);
		VariablePredicate opposite = pred.createOppositePred(other);
		
		knownPredicates.put(other, opposite);
	}

	private void addIdenticalImplication(Aliasing known, Aliasing other) {
		assert(knownImplications.containsKey(known));
		
		List<Implication> impls = knownImplications.get(known);
		for( Implication impl : impls ) {
			Implication new_impl = impl.createLinkedCopyWithNewAntecedant(other);
			
			if(new_impl != null)
				this.addImplication(other, new_impl);
		}
	}

	private void addIdenticalPredicate(Aliasing known, Aliasing other) {
		assert(knownPredicates.containsKey(known));
		
		VariablePredicate pred = knownPredicates.get(known);
		VariablePredicate new_pred = pred.createIdenticalPred(other);
				
		knownPredicates.put(other, new_pred);
	}
	
	/**
	 * Add the knowledge that a given variable is true.
	 */
	public void addTrueVarPredicate(Aliasing v) {		
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");

		final BooleanPredicate truePred = BooleanPredicate.createTrueVarPred(v);

		// old knowledge about v doesn't seem to be a problem--we're just overriding with something new
//		assert(!knownPredicates.containsKey(v) || knownPredicates.get(v).equals(truePred)) : 
//			"Contradiction?  Already know " + v + " to be " + knownPredicates.get(v);
		// TODO could detect superfluous tests here
		knownPredicates.put(v, truePred);
	}
	/**
	 * Add the knowledge that a given variable is false.
	 */
	public void addFalseVarPredicate(Aliasing v) {
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");

		final BooleanPredicate falsePred = BooleanPredicate.createFalseVarPred(v);

		// old knowledge about v doesn't seem to be a problem--we're just overriding with something new
//		assert(!knownPredicates.containsKey(v) || knownPredicates.get(v).equals(falsePred)) :
//			"Contradiction?  Already know " + v + " to be " + knownPredicates.get(v);
		// TODO could detect superfluous and impossible tests here
		knownPredicates.put(v, falsePred);
	}
	
	/**
	 * Add an implication:
	 * If the variable 'ant' is true, then the 'object' is in state 'state.'
	 * @param ant
	 * @param object
	 * @param state
	 */
	public void addTrueImplication(Aliasing ant, Aliasing object, String state) {
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");
		
		StateImplication impl = StateImplication.createTrueVarImplies(ant, object, state);
		this.addImplication(ant, impl);
	}
	
	/**
	 * Add an implication:
	 * If the variable 'ant' is false, then the 'object is in state 'state.'
	 * @param ant
	 * @param object
	 * @param state
	 */
	public void addFalseImplication(Aliasing ant, Aliasing object, String state) {
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");
		
		StateImplication impl = StateImplication.createFalseVarImplies(ant, object, state);
		this.addImplication(ant, impl);
	}
	
	/**
	 * Declare that the given variable is null.
	 * @param ant
	 */
	public void addNullVariable(Aliasing ant) {		
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");
		knownPredicates.put(ant, NullPredicate.createNullVarPred(ant));
	}
	
	/**
	 * Declare that the given variable is not null.
	 * @param ant
	 */
	public void addNonNullVariable(Aliasing ant) {		
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");
		knownPredicates.put(ant, NullPredicate.createNonNullVarPred(ant));		
	}
	
	/**
	 * Adds a predicate of the form
	 * 
	 * is_v1_true(v_1) implies is_v2_null(v_2)
	 * 
	 * In other words the truth (or falsehood) of the first predicate implies the null
	 * (or non-null) ness of the second variable.
	 * 
	 * @param v_1
	 * @param is_v1_null
	 * @param v_2
	 * @param is_v2_null
	 */
	public void addNullImplication(Aliasing v_1, boolean is_v1_true,
			                       Aliasing v_2, boolean is_v2_null) {
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");
		
		NullImplication impl = NullImplication.createNullImplication(v_1, is_v1_true, v_2, is_v2_null);
		this.addImplication(v_1, impl);
	}
	
	/**
	 * Adds a new implication to the set where the given object location is
	 * the target of the antecedent.
	 */
	public void addImplication(Aliasing ant, Implication impl) {
		if( this.knownImplications.containsKey(ant) ) {
			ConsList<Implication> new_val = cons(impl, this.knownImplications.get(ant)); 
			this.knownImplications.put(ant, new_val);
		}
		else {
			this.knownImplications.put(ant, ConsList.singleton(impl));
		}
	} 

	public DynamicStateLogic copy() {
		return this.freeze();
	}

	public DynamicStateLogic mutableCopy() {
		// Don't really have a good idea of what to do here.
		// Maybe I should try it first w/o this line.
		if( this.isBottom() ) return this;
		
		// Create new maps with contents of old ones. Old values are immutable, so
		// shallow copy is good enough.
		Map<Aliasing,VariablePredicate> vps = new HashMap<Aliasing,VariablePredicate>(this.knownPredicates);
		Map<Aliasing, ConsList<Implication>> imps = new HashMap<Aliasing, ConsList<Implication>>(this.knownImplications);

		return new DynamicStateLogic(vps, imps);
	}
	
	public DynamicStateLogic freeze() {
		this.frozen = true;
		return this;
	}
	
	public DynamicStateLogic join(DynamicStateLogic other,
			ASTNode node) {
		this.freeze();
		if( this == other || other == null )
			return this;
		other.freeze();
		
		if( this.isBottom() )
			return other;
		if( other.isBottom() )
			return this;
		
		DynamicStateLogic result = new DynamicStateLogic();
		/*
		 * We need to do an INTERSECTION on both implications and predicates.
		 */
		Set<Map.Entry<Aliasing, VariablePredicate>> pred_intersection = 
			new HashSet<Map.Entry<Aliasing, VariablePredicate>>(this.knownPredicates.entrySet());
		pred_intersection.retainAll(other.knownPredicates.entrySet());
		for( Map.Entry<Aliasing, VariablePredicate> entry : pred_intersection ) {
			result.knownPredicates.put(entry.getKey(), entry.getValue());
		}
		
		for(Map.Entry<Aliasing, ConsList<Implication>> thisE : this.knownImplications.entrySet()) {
			ConsList<Implication> thisImpls = thisE.getValue();
			if(thisImpls == null || thisImpls.isEmpty())
				continue;
			Aliasing var = thisE.getKey();
			final ConsList<Implication> otherImpls = other.knownImplications.get(var);
			if(otherImpls == null || otherImpls.isEmpty())
				continue;
			ConsList<Implication> resultImpls;
			if(thisImpls == otherImpls)
				// need not filter identical lists
				resultImpls = thisImpls;
			else
				resultImpls = thisImpls.filter(new edu.cmu.cs.crystal.util.Lambda<Implication, Boolean>() {
					@Override
					public Boolean call(Implication i) {
						if(otherImpls.contains(i))
							return true;
						else
							return false;
					}
				});
			result.knownImplications.put(var, resultImpls);
		}
		
//		Set<Map.Entry<Aliasing, ConsList<Implication>>> impl_intersection =
//			new HashSet<Map.Entry<Aliasing, ConsList<Implication>>>(this.knownImplications.entrySet());
//		impl_intersection.retainAll(other.knownImplications.entrySet());
//		for( Map.Entry<Aliasing, ConsList<Implication>> entry : impl_intersection ) {
//			result.knownImplications.put(entry.getKey(), entry.getValue());
//		}
		
//		Set<Map.Entry<Aliasing, List<DelayedImplication>>> delayed_intersection =
//			new HashSet<Map.Entry<Aliasing, List<DelayedImplication>>>(this.delayedImplications.entrySet());
//		impl_intersection.retainAll(other.delayedImplications.entrySet());
//		for( Map.Entry<Aliasing, List<DelayedImplication>> entry : delayed_intersection ) {
//			result.delayedImplications.put(entry.getKey(), entry.getValue());
//		}
		
		return result;
	}
	
	/*
	 * Oh man, this looks slow. Can we change the collection types to make this
	 * faster? NEB
	 */
	public boolean atLeastAsPrecise(DynamicStateLogic other) {
		this.freeze();
		if( this == other ) 
			return true;
		if( other == null )
			return false;
		other.freeze();
		
		if( this.isBottom() )
			return true;
		if( other.isBottom() )
			return false;
		
		if( !this.knownPredicates.entrySet().containsAll(other.knownPredicates.entrySet()) )
			return false;
		
//		if( !this.knownPredicates.values().containsAll(other.knownPredicates.values()) )
//			return false;
		
		for(Map.Entry<Aliasing, ConsList<Implication>> otherE : other.knownImplications.entrySet()) {
			ConsList<Implication> otherImpls = otherE.getValue();
			if(otherImpls == null || otherImpls.isEmpty())
				// ignore empty list
				continue;
			Aliasing var = otherE.getKey();
			ConsList<Implication> thisImpls = this.knownImplications.get(var);
			if(thisImpls == null || thisImpls.isEmpty())
				// no implications for var -> fail
				return false;
			if(! thisImpls.containsAll(otherImpls))
				// TODO make sure duplicate implications in other are also duplicate in this
				return false;
		}
		
//		if( !this.knownImplications.keySet().containsAll(other.knownImplications.keySet()) )
//			return false;
//		
//		if( !this.knownImplications.values().containsAll(other.knownImplications.values()))
//			return false;
			
//		if( !this.delayedImplications.keySet().containsAll(other.delayedImplications.keySet()) )
//			return false;
//		
//		if( !this.delayedImplications.values().containsAll(other.delayedImplications.values()))
//			return false;
			
		
		return true;
	}

	public boolean isBooleanTrue(Aliasing var) {
		if( this.isBottom() ) return false;	
		
		return this.knownPredicates.containsKey(var) && this.knownPredicates.get(var).denotesBooleanTruth();
	}

	public boolean isBooleanFalse(Aliasing var) {
		if( this.isBottom() ) return false;
		
		return this.knownPredicates.containsKey(var) && this.knownPredicates.get(var).denotesBooleanFalsehood();
	}

	public boolean isNull(Aliasing loc) {
		if( this.isBottom() ) return false;
		
		return this.knownPredicates.containsKey(loc) &&
			this.knownPredicates.get(loc).denotesNullVariable();
	}
	
	public boolean isNonNull(Aliasing loc) {
		if( this.isBottom() ) return false;
		
		return this.knownPredicates.containsKey(loc) &&
		this.knownPredicates.get(loc).denotesNonNullVariable();
	}

	public DynamicStateLogic bottom() {
		return (new DynamicStateLogic(null, null /*, null */)).freeze();
	}

	public boolean isBottom() {
		return 
			this.knownImplications == null &&
			this.knownPredicates == null /*&& 
			this.delayedImplications == null*/;
	}

	void addPredicates(Set<Pair<Aliasing, ? extends VariablePredicate>> preds) {
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");
		
		for(Pair<Aliasing, ? extends VariablePredicate> pred : preds) {
			knownPredicates.put(pred.fst(), pred.snd());
		}
	}

	/**
	 * Remove one of the given implication with the given object location as its
	 * antecedent.
	 */
	public void removeImplication(Aliasing var, Implication impl) {
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");
		
		ConsList<Implication> is = knownImplications.get(var);
		
		// We only remove one copy of the implication, because an
		// implication is a linear fact.
		if( is != null )
			knownImplications.put(var, is.removeElementOnce(impl));
	}

	/**
	 * Does the dynamic state logic contain this implication or another implication
	 * that implies this implication?
	 * @param v
	 * @param impl
	 * @return Option indicating whether or not DynamicStateLogic knows about this
	 * implication. If it does, the Implication itself or the implication that implied
	 * the given implication (whichever is actually in this DynamicStateLogic) will be
	 * returned.
	 */
	public Option<Implication> isKnownImplication(Aliasing v, Implication impl) {
		List<Implication> v_impls = knownImplications.get(v);
		if( v_impls == null )
			return Option.none();
		
		if( v_impls.contains(impl) ) {
			// We know this exact implication
			return Option.some(impl);
		}
		
		// Maybe it doesn't contain it, but it still could imply it...
		for( Implication v_impl : v_impls ) {
			if( impl.isImpliedBy(v_impl) )
				return Option.some(v_impl);
		}
		
		return Option.none();
	}

	/**
	 * @param anteLoc
	 * @param paramLoc
	 * @return
	 */
	public List<ReleaseHolder> findImpliedParameter(Aliasing anteLoc,
			Aliasing paramLoc) {
		List<ReleaseHolder> result = null;
		List<Implication> impls = knownImplications.get(anteLoc);
		if(impls == null || impls.isEmpty())
			return Collections.emptyList();
		for(Implication impl : impls) {
			if(impl instanceof ReleasePermissionImplication) {
				List<ReleaseHolder> l = ((ReleasePermissionImplication) impl).findImpliedParameter(paramLoc);
				if(result == null)
					result = l;
				else
					result.addAll(l);
			}
		}
		return result == null ? Collections.<ReleaseHolder>emptyList() : result;
	}

	/**
	 * Remove temporary state information for all of the implications
	 * contained by this DynamicStateLogic object.
	 */
	public void forgetTemporaryStateInImplications() {
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");

		List<Pair<Aliasing, ConsList<Implication>>> new_entries = new LinkedList<Pair<Aliasing, ConsList<Implication>>>();
		
		for(Map.Entry<Aliasing, ConsList<Implication>> impls : knownImplications.entrySet()) {
			ConsList<Implication> cur_list = impls.getValue();
			
			// Fold over the old list, creating a new list with fewer implications or
			// with implications that have no temporary state information.
			ConsList<Implication> new_list;
			new_list = cur_list.foldl(
					new Lambda2<Implication, ConsList<Implication>, ConsList<Implication>>(){
						@Override
						public ConsList<Implication> call(Implication i1,
								ConsList<Implication> i2) {
							if( i1.hasTemporaryState() ) {
								i1 = i1.createCopyWithoutTemporaryState();
								if( i1 == null )
									// Drop implication entirely
									return i2;
								else
									// replace problematic implication with sanitized 
									return cons(i1, i2);
							}
							else {
								// Implication is fine.
								return cons(i1, i2);
							}
						}}, 
					ConsList.<Implication>empty());
			
			// Use the old list as long as the new list and the old list are
			// the same. This gives us some additional sharing.
			new_list = new_list.equals(cur_list) ? cur_list : new_list;
			new_entries.add(Pair.create(impls.getKey(), new_list));
		}
		
		// Update the map by putting the new alias/list pairs back in.
		for( Pair<Aliasing, ConsList<Implication>> new_entry : new_entries ) {
			knownImplications.put(new_entry.fst(), new_entry.snd());
		}
	}

	/**
	 * Removes predicates and implications for variables for which the given filter
	 * returns <code>true</code>.
	 * @param filter Return <code>true</code> from {@link AliasingFilter#isConsidered(Aliasing)}
	 * for all variables to be deleted.
	 * @return <code>true</code> if variables were removed, <code>false</code> otherwise.
	 */
	public boolean removeVariables(Lambda<Aliasing, Boolean> filter) {
		if( frozen ) 
			throw new IllegalStateException("Cannot change frozen object. Get a mutable copy to do this.");

		boolean result = false;
		for(Iterator<Aliasing> it = knownPredicates.keySet().iterator(); it.hasNext(); ) {
			Aliasing var = it.next();
			if( filter.call(var) ) {
				result = true;
				it.remove();
			}
		}
		
		for(Iterator<Aliasing> it = knownImplications.keySet().iterator(); it.hasNext(); ) {
			Aliasing var = it.next();
			if( filter.call(var) ) {
				result = true;
				it.remove();
			}
		}
		return result;
	}

	public Set<Aliasing> getLiveInImplVariables() {
		if(knownImplications.isEmpty())
			return Collections.emptySet();
		
		HashSet<Aliasing> result = new HashSet<Aliasing>();
		for(Map.Entry<Aliasing, ConsList<Implication>> impls : knownImplications.entrySet()) {
			Aliasing ant = impls.getKey();
			for(Implication impl : impls.getValue()) {
				Set<Aliasing> implied = impl.getConsequentVariables();
				for(Aliasing a : implied) {
					if(a.equals(ant))
						continue;
					if(knownImplications.containsKey(a))
						// should keep a around b/c its one of its antecedents may
						// become available after eliminating impl
						result.add(a);
				}
			}
		}
		return result;
	}	
}