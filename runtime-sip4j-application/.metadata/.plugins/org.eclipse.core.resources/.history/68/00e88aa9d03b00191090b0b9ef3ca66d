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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.jdt.core.dom.ITypeBinding;

import edu.cmu.cs.crystal.analysis.alias.Aliasing;
import edu.cmu.cs.crystal.annotations.AnnotationDatabase;
import edu.cmu.cs.crystal.annotations.ICrystalAnnotation;
import edu.cmu.cs.crystal.util.Pair;
import edu.cmu.cs.crystal.util.SimpleMap;
import edu.cmu.cs.plural.fractions.FractionalPermission;
import edu.cmu.cs.plural.perm.parser.AbstractAccessPredVisitor;
import edu.cmu.cs.plural.perm.parser.AccessPredVisitor;
import edu.cmu.cs.plural.perm.parser.BinaryExpr;
import edu.cmu.cs.plural.perm.parser.BinaryExprAP;
import edu.cmu.cs.plural.perm.parser.BoolLiteral;
import edu.cmu.cs.plural.perm.parser.Conjunction;
import edu.cmu.cs.plural.perm.parser.Disjunction;
import edu.cmu.cs.plural.perm.parser.EmptyPredicate;
import edu.cmu.cs.plural.perm.parser.EqualsExpr;
import edu.cmu.cs.plural.perm.parser.Identifier;
import edu.cmu.cs.plural.perm.parser.NotEqualsExpr;
import edu.cmu.cs.plural.perm.parser.Null;
import edu.cmu.cs.plural.perm.parser.ParamReference;
import edu.cmu.cs.plural.perm.parser.PermParser;
import edu.cmu.cs.plural.perm.parser.PermissionImplication;
import edu.cmu.cs.plural.perm.parser.PrimaryExpr;
import edu.cmu.cs.plural.perm.parser.PrimaryExprVisitor;
import edu.cmu.cs.plural.perm.parser.StateOnly;
import edu.cmu.cs.plural.perm.parser.TempPermission;
import edu.cmu.cs.plural.perm.parser.Withing;
import edu.cmu.cs.plural.states.annowrappers.ClassStateDeclAnnotation;
import edu.cmu.cs.plural.states.annowrappers.StateDeclAnnotation;
import edu.cmu.cs.plural.track.FractionalTransfer;
import edu.cmu.cs.plural.track.PluralTupleLatticeElement;

/**
 * This class holds various utility methods used for getting concrete state
 * information from annotations. Most of this code is either new or used to be
 * inside the PluralTupleLatticeElement class where it did not really belong.
 * 
 * @author Nels Beckman
 * @date Apr 22, 2008
 *
 */
public class ConcreteAnnotationUtils {

	private static final Logger log = Logger.getLogger(FractionalTransfer.class.getName());
	
	
	public static NullPredicate createNull(Aliasing v, boolean negate) {
		return negate ? NullPredicate.createNonNullVarPred(v) : NullPredicate.createNullVarPred(v);
	}
	
	public static BooleanPredicate createBoolean(Aliasing v, boolean bool, boolean negate) {
		if(negate) bool = ! bool;
		return bool ? BooleanPredicate.createTrueVarPred(v) : BooleanPredicate.createFalseVarPred(v);
	}

	/**
	 * Returns a violated field invariant, if any.
	 * @param value
	 * @param this_type
	 * @param thisPerm
	 * @param vars
	 * @param annotationDB
	 * @return a violated field invariant or <code>null</code> if the check succeeded.
	 */
	public static String checkConcreteFieldInvariants(
			PluralTupleLatticeElement value,
			ITypeBinding this_type, 
			FractionalPermission thisPerm, SimpleMap<String, Aliasing> vars,
			AnnotationDatabase annotationDB) {
		
		/*
		 * Get all state invariant information from the annotation database.
		 */
		for( ICrystalAnnotation csda : annotationDB.getAnnosForType(this_type)) {
			if( csda instanceof ClassStateDeclAnnotation ) {
				final List<StateDeclAnnotation> decls = 
					((ClassStateDeclAnnotation)csda).getStates();

				for( StateDeclAnnotation decl : decls ) {
					/*
					 * Is this declaration applicable to the current recvr state?
					 * We want the annotation state to be between the this state and
					 * the this root state, inclusive.
					 */
					final boolean applies =
						thisPerm.impliesState(decl.getStateName()) &&
								(thisPerm.coversNode(decl.getStateName()) || 
										thisPerm.getStateSpace().firstBiggerThanSecond(decl.getStateName(), thisPerm.getRootNode()));

					if( applies ) {
						String perm_string = decl.getInv();
						String error = checkConcreteFromString(perm_string, value, vars);
						if(error != null)
							return error;
					}
				}
			}
		}

		return null;
	}
	
	/**
	 * Returns a violated concrete predicate, if any.
	 * @param perm_string
	 * @param value
	 * @param vars
	 * @return a violated predicate or <code>null</code> if the check succeeded.
	 */
	public static String checkConcreteFromString(
			String perm_string, PluralTupleLatticeElement value,
			SimpleMap<String, Aliasing> vars) {
		final ConcreteVisitor v = new ConcreteVisitor(vars, null /* no assigned field */);
		PermParser.accept(perm_string, v);

		// check predicates
		for(Pair<Aliasing, ? extends VariablePredicate> p : v.preds) {
			if(p.snd().isSatisfied(value) == false)
				return p.snd().toString();
		}

		// check implications
		for(Pair<Aliasing, ? extends Implication> p : v.impls) {
			if(p.snd().isSatisfied(value) == false)
				return p.snd().toString();
		}

		// success
		return null;
	}
	
	private static class ConcreteVisitor implements AccessPredVisitor<Boolean> {

		// inputs
		private final SimpleMap<String, Aliasing> vars;
		private final String assignedField;
		
		// outputs
		final Set<Aliasing> result = new LinkedHashSet<Aliasing>();
		final Set<Pair<Aliasing, ? extends VariablePredicate>> preds = new LinkedHashSet<Pair<Aliasing, ? extends VariablePredicate>>(); 
		final Set<Pair<Aliasing, ? extends Implication>> impls = new LinkedHashSet<Pair<Aliasing, ? extends Implication>>(); 

		public ConcreteVisitor(SimpleMap<String, Aliasing> vars, String assignedField) {
			this.vars = vars;
			this.assignedField = assignedField;
		}
		
		@Override
		public Boolean visit(TempPermission perm) {
			// not concrete...
			return null;
		}

		@Override
		public Boolean visit(Disjunction disj) {
			// not supported...
			log.warning("Not supported: " + disj);
			return null;
		}

		@Override
		public Boolean visit(Conjunction conj) {
			conj.getP1().accept(this);
			conj.getP2().accept(this);
			return null;
		}

		@Override
		public Boolean visit(Withing withing) {
			// not supported...
			log.warning("Not supported: " + withing);
			return null;
		}

		@Override
		public Boolean visit(BinaryExprAP binaryExpr) {
			Pair<Aliasing, ? extends VariablePredicate> p =
				handleBinary(binaryExpr.getBinExpr());
			if(p != null) {
				result.add(p.fst());
				preds.add(p);
			}
			return null;
		}

		@Override
		public Boolean visit(EqualsExpr equalsExpr) {
			throw new IllegalStateException("Should be handled as BinaryExprAP");
		}
		
		@Override
		public Boolean visit(NotEqualsExpr notEqualsExpr) {
			throw new IllegalStateException("Should be handled as BinaryExprAP");
		}

		private Pair<Aliasing, ? extends VariablePredicate> 
		handleBinary(BinaryExpr expr) {
			boolean negate = expr instanceof NotEqualsExpr;
			
			Pair<Boolean, ? extends Object> p1 = mapPrimary(expr.getE1());
			Pair<Boolean, ? extends Object> p2 = mapPrimary(expr.getE2());
			if(p1.fst() || p2.fst())
				// ignore this thing
				return null;
			Object e1 = p1.snd();
			Object e2 = p2.snd();
			
			if(e1 == e2) {
				if(negate)
					log.warning("Ignore contradiction: " + expr);
				// ignore trivial equality
				return null;
			}
			
			if(e1 instanceof Aliasing) {
				return equate(expr, (Aliasing) e1, e2, negate);
			}
			else if(e2 instanceof Aliasing) {
				return equate(expr, (Aliasing) e2, e1, negate);
			}
			else {
				log.warning("Ignore expression: " + expr);
				return null;
			}
		}

		private Pair<Aliasing, ? extends VariablePredicate> 
		equate(BinaryExpr expr, Aliasing x, Object other, boolean negate) {
			if(other == null) {
				return Pair.create(x, createNull(x, negate));
			}
			else if(other instanceof Boolean) {
				boolean b = (Boolean) other;
				return Pair.create(x, createBoolean(x, b, negate));
			}
			else if(other instanceof Aliasing) {
				log.warning("Ignoring variable relation: " + expr);
				// TODO support for relations between variables
				return null;
			}
			else
				throw new IllegalArgumentException("Don't know what to do: " + expr);
		}

		/**
		 * Returns the corresponding primitive value for {@link Null} and
		 * {@link BoolLiteral}s and an {@link Aliasing} object for {@link
		 * Identifier}.
		 * @param e
		 * @return a pair of a boolean that is <code>true</code> if
		 * the primary is to be ignored (because it corresponds to the assigned-to
		 * field and the corresponding primitive value for {@link Null} and
		 * {@link BoolLiteral}s and an {@link Aliasing} object for {@link
		 * Identifier}s.
		 */
		private Pair<Boolean, ? extends Object> mapPrimary(PrimaryExpr e) {
			return e.dispatch(new PrimaryExprVisitor<Pair<Boolean, ? extends Object>>() {

				@Override
				public Pair<Boolean, ? extends Object> visitBool(BoolLiteral bool) {
					return Pair.create(false, bool.hasValue(true));
				}

				@Override
				public Pair<Boolean, ? extends Object> visitId(Identifier id) {
					return Pair.create(
							id.getName().equals(assignedField),
							vars.get(id.getName()));
				}

				@Override
				public Pair<Boolean, ? extends Object> visitNull(Null nul) {
					return Pair.create(false, null);
				}

				@Override
				public Pair<Boolean, ? extends Object> visitParam(
						ParamReference paramReference) {
					// should only occur in method pre- and post-conditions
					throw new UnsupportedOperationException("This visitor is meant for state invariants");
				}
				
			});
		}

		@Override
		public Boolean visit(StateOnly stateOnly) {
			// not concrete... should be folded directly into refined permission
			return null;
		}

		@Override
		public Boolean visit(PermissionImplication permissionImplication) {
			final Pair<Aliasing, ? extends VariablePredicate> p = 
				handleBinary(permissionImplication.ant());
			
			if(p == null)
				return null;
			if(! (p.snd() instanceof BooleanPredicate)) {
				log.warning("Ignore non-boolean antecedent: " + permissionImplication);
				return null;
			}
			
			final boolean truth = p.snd().denotesBooleanTruth();
			
			AbstractAccessPredVisitor<Boolean> consVisitor = new AbstractAccessPredVisitor<Boolean>() {

				@Override
				public Boolean visit(BinaryExprAP binaryExpr) {
					Pair<Aliasing, ? extends VariablePredicate> q =
						handleBinary(binaryExpr.getBinExpr());
					if(q != null && q.snd() instanceof NullPredicate) {
						boolean nonNull = q.snd().denotesNonNullVariable();
						result.add(p.fst());
						impls.add(Pair.create(
								p.fst(),
								NullImplication.createNullImplication(
										p.fst(), truth, q.fst(), !nonNull)));
						return true;
					}
					return null;
				}

				@Override
				public Boolean visit(StateOnly stateOnly) {
					Aliasing x = vars.get(((Identifier) stateOnly.getVar()).getName());
					result.add(p.fst());
					if(truth)
						impls.add(Pair.create(p.fst(), StateImplication.createTrueVarImplies(
								p.fst(), 
								x, 
								stateOnly.getStateInfo())));
					else
						impls.add(Pair.create(p.fst(), StateImplication.createFalseVarImplies(
								p.fst(), 
								x, 
								stateOnly.getStateInfo())));
					return true;
				}
				
			};
			if(permissionImplication.cons().accept(consVisitor) == null)
				log.warning("Ignore consequence: " + permissionImplication);
			return null;
		}

		@Override
		public Boolean visit(EmptyPredicate emptyPredicate) {
			// not concrete...
			return null;
		}
		
	}

}
