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
package edu.cmu.cs.plural.track;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;

import edu.cmu.cs.crystal.AbstractCrystalMethodAnalysis;
import edu.cmu.cs.crystal.BooleanLabel;
import edu.cmu.cs.crystal.analysis.alias.Aliasing;
import edu.cmu.cs.crystal.annotations.AnnotationDatabase;
import edu.cmu.cs.crystal.flow.ITACFlowAnalysis;
import edu.cmu.cs.crystal.internal.Option;
import edu.cmu.cs.crystal.tac.TACFlowAnalysis;
import edu.cmu.cs.crystal.tac.ThisVariable;
import edu.cmu.cs.crystal.tac.Variable;
import edu.cmu.cs.crystal.tac.eclipse.CompilationUnitTACs;
import edu.cmu.cs.plural.fractions.AbstractFractionalPermission;
import edu.cmu.cs.plural.fractions.FractionalPermission;
import edu.cmu.cs.plural.fractions.FractionalPermissions;
import edu.cmu.cs.plural.fractions.PermissionSetFromAnnotations;
import edu.cmu.cs.plural.states.IConstructorSignature;
import edu.cmu.cs.plural.states.IInvocationCaseInstance;
import edu.cmu.cs.plural.states.IMethodSignature;
import edu.cmu.cs.plural.states.StateSpace;
import edu.cmu.cs.plural.states.StateSpaceRepository;
import edu.cmu.cs.plural.util.SimpleMap;

/**
 * @author Nels Beckman
 * @author Kevin Bierhoff
 * @deprecated This analysis no longer works. It is only around for reference
 *             purposes.
 * @see edu.cmu.cs.plural.track.FractionalAnalysis
 */
@Deprecated
public class SingleTruthFractionalAnalysis extends AbstractCrystalMethodAnalysis 
		implements FractionAnalysisContext {
	
	public SingleTruthFractionalAnalysis() {
		super();
	}

	private ITACFlowAnalysis<PluralTupleLatticeElement> fa;
	private static Logger logger = Logger.getLogger(SingleTruthFractionalAnalysis.class.getName());

	private SingleTruthFractionalTransfer tf;

	private MethodDeclaration currentMethod;
	
	/**
	 * Factory method to allow subclasses to return a different subclass of
	 * FractionalTransfer. This method should ALWAYS be used for initializing
	 * <code>this.tf</code>.
	 * @return
	 */
	protected SingleTruthFractionalTransfer createNewFractionalTransfer() {
		return new SingleTruthFractionalTransfer(this);
	}
	
	/**
	 * Another factory method. Allows subclasses to also extend FractionalChecker. 
	 * @return
	 */
	protected FractionalChecker createASTWalker() {
		return new FractionalChecker();
	}
	
	protected ITACFlowAnalysis<PluralTupleLatticeElement> getFa() {
		return fa;
	}

	protected SingleTruthFractionalTransfer getTf() {
		return tf;
	}
	
	/* (non-Javadoc)
	 * @see edu.cmu.cs.crystal.AbstractCrystalMethodAnalysis#analyzeMethod(org.eclipse.jdt.core.dom.MethodDeclaration)
	 */
	@Override
	public void analyzeMethod(MethodDeclaration d) {
		if(isAbstract(d)) {
			if(logger.isLoggable(Level.FINE))
				logger.finer("Skip abstract method " + d.getName());
		}
		else {
			// only analyze methods with code in them; skip abstract methods
			currentMethod = d;
			tf = createNewFractionalTransfer();
			fa = new TACFlowAnalysis<PluralTupleLatticeElement>(tf, 
					this.analysisInput.getComUnitTACs().unwrap());
			if(logger.isLoggable(Level.FINE))
				logger.finer("Results for " + d.getName());
			
			d.accept(createASTWalker());
		}
	}	
	
	@Override
	public StateSpaceRepository getRepository() {
		return StateSpaceRepository.getInstance(getAnnoDB());
	}

	@Override
	public Option<CompilationUnitTACs> getComUnitTACs() {
		return analysisInput.getComUnitTACs();
	}

	@Override
	public AnnotationDatabase getAnnoDB() {
		return analysisInput.getAnnoDB();
	}

	@Override
	public IInvocationCaseInstance getAnalyzedCase() {
		throw new UnsupportedOperationException("Not needed for now.");
	}

	protected class FractionalChecker extends ASTVisitor {

		@Override
		public void endVisit(MethodDeclaration node) {
			if(isAbstract(node) == false) {
				final Block block = node.getBody();
				// check whether post-conditions for parameters are validated
				PluralTupleLatticeElement exit = getFa().getResultsBefore(block);
				exit = checkParameterPostCondition(exit, block);
				// check whether post-conditions for packing are validated
				exit = checkRecvrFieldsPostCondition(exit, block);
				
				// debug
				if(logger.isLoggable(Level.FINEST))
					logger.finest("exit: " + exit);
			}
			super.endVisit(node);
		}

		@Override
		public void endVisit(ReturnStatement node) {
			// check whether post-conditions for parameters are validated
			PluralTupleLatticeElement exit = getFa().getResultsBefore(node); 
			exit = checkParameterPostCondition(exit, node);
			// check whether post-conditions for packing are validated
			exit = checkRecvrFieldsPostCondition(exit, node);
			// check accuracy of dynamic state test
			exit = checkDynamicStateTest(exit, node);
			// check whether post-condition for return value is validated
			if( node.getExpression() != null &&
				tf.getResultPostCondition() != null) {
				Aliasing return_value_loc = exit.getLocationsAfter(node, fa.getVariable(node.getExpression()));
				// If the return value is not the value null, then it must fulfill its spec.
				if( !exit.isNull(return_value_loc) ) {
					FractionalPermissions resultPerms = exit.get(return_value_loc);
					
					for(String needed : tf.getResultPostCondition().getStateInfo()) {
						if(resultPerms.isInState(needed) == false)
							reporter.reportUserProblem("Return value must be in state " + 
									needed + " but is in " + resultPerms.getStateInfo(),
									node.getExpression(), SingleTruthFractionalAnalysis.this.getName());
					}
					
					FractionalPermissions resultRemainder = resultPerms.splitOff(tf.getResultPostCondition());
					if(resultRemainder.isUnsatisfiable())
						reporter.reportUserProblem("Return value carries no suitable permission", node.getExpression(), SingleTruthFractionalAnalysis.this.getName());
				}
			}

			// debug
			if(logger.isLoggable(Level.FINEST)) {
				logger.finest("" + fa.getResultsBefore(node));
				logger.finest("  " + node);
			}
		}

		@Override
		public void endVisit(ClassInstanceCreation node) {
			final PluralTupleLatticeElement before = fa.getResultsBefore(node);
			final PluralTupleLatticeElement after = fa.getResultsAfter(node);
			final IMethodBinding constructorBinding = node.resolveConstructorBinding();
			final IConstructorSignature sig = getRepository().getConstructorSignature(constructorBinding);
			
			// arguments
			int argIndex = 0;
			for(Expression e : (List<Expression>) node.arguments()) {
				final Variable arg = fa.getVariable(e);
				final FractionalPermissions perms = after.get(node, arg);
				if(perms.isUnsatisfiable())
					// TODO better reporting
					reporter.reportUserProblem("" + e + " yields no suitable permission for surrounding call", e, SingleTruthFractionalAnalysis.this.getName());
				
				checkStateOptions(before.get(node, arg), sig.getRequiredParameterStateOptions(argIndex), arg, e);
				
				++argIndex;
			}
			
			// debug
			if(logger.isLoggable(Level.FINEST)) {
				logger.finest(before.toString());
				logger.finest("  " + node);
				logger.finest(after.toString());
			}
			super.endVisit(node);
		}

		@Override
		public void endVisit(MethodInvocation node) {
			final PluralTupleLatticeElement before = fa.getResultsBefore(node);
			final PluralTupleLatticeElement after = fa.getResultsAfter(node);
			final IMethodBinding methodBinding = node.resolveMethodBinding();
			final IMethodSignature sig = getRepository().getMethodSignature(methodBinding);
			
			// receiver
			if(sig.hasReceiver()) {
				final Variable receiver;
				if(node.getExpression() == null)
					receiver = fa.getImplicitThisVariable(methodBinding);
				else
					receiver = fa.getVariable(node.getExpression());
				
				final FractionalPermissions permsAfter = after.get(node, receiver);
				if(permsAfter.isUnsatisfiable())
					// TODO better reporting
					reporter.reportUserProblem("" + receiver.getSourceString() + " carries no suitable permission", node, SingleTruthFractionalAnalysis.this.getName());
				
				checkStateOptions(before.get(node, receiver), sig.getRequiredReceiverStateOptions(), receiver, node);
			}
			// TODO what to do with static methods?
			
			// arguments
			int argIdx = 0;
			for(Expression e : (List<Expression>) node.arguments()) {
				Variable arg = fa.getVariable(e);
				FractionalPermissions perms = after.get(node, arg);
				if(perms.isUnsatisfiable())
					// TODO better reporting
					reporter.reportUserProblem("" + e + " yields no suitable permission for surrounding call", e, SingleTruthFractionalAnalysis.this.getName());
				
				checkStateOptions(before.get(node, arg), sig.getRequiredParameterStateOptions(argIdx), arg, e);
				++argIdx;
			}

			// debug
			if(logger.isLoggable(Level.FINEST)) {
				logger.finest(before.toString());
				logger.finest("  " + node);
				logger.finest(after.toString());
			}
			super.endVisit(node);
		}

		@Override
		public void endVisit(SuperMethodInvocation node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		@Override
		public void endVisit(ArrayAccess node) {
			if(node.getParent() instanceof Assignment) {
				Assignment store = (Assignment) node.getParent();
				if(store.getLeftHandSide().equals(node)) {
					if(fa.getResultsAfter(store).get(node, fa.getVariable(node.getArray())).isUnsatisfiable())
						// TODO better reporting
						reporter.reportUserProblem("no suitable permission for assignment to " + node, node, SingleTruthFractionalAnalysis.this.getName());
				}
			}
			super.endVisit(node);
		}

		@Override
		public boolean visit(AnonymousClassDeclaration node) {
			// do *not* visit anonymous inner classes
			// methods of inner classes should be checked with separate "analyzeMethod" calls to FractionalAnalysis
			return false;
		}

		@Override
		public boolean visit(TypeDeclarationStatement node) {
			// do *not* visit local type declarations
			// methods of local types should be checked with separate "analyzeMethod" calls to FractionalAnalysis
			return false;
		}

		/**
		 * @param node
		 * @return Analysis result before the given node, which should be the "exit" result for this node.
		 */
		private PluralTupleLatticeElement checkParameterPostCondition(
				PluralTupleLatticeElement curLattice,
				ASTNode node) {
			// get results first so the post-condition is populated
			//PluralTupleLatticeElement exit = fa.getResultsBefore(node);
			// Sometimes this method is called on unreachable statements :-(
			if( curLattice.isBottom() ) {
				return curLattice;
			}
			
			curLattice = curLattice.mutableCopy();
			
			for(Aliasing x : tf.getParameterPostConditions().keySet()) {
				if( curLattice.getLocationsAfter(node, tf.getAnalysisContext().getThisVariable()).equals(x) ) {
					/*
					 * Receiver is checked elsewhere.
					 */
					continue;
				}
				
				FractionalPermissions paramPerms = curLattice.get(x);

				for(String needed : tf.getParameterPostConditions().get(x).getStateInfo()) {
					if(paramPerms.isInState(needed) == false)
						reporter.reportUserProblem(x + " must return in state " + needed + " but is in " + paramPerms.getStateInfo(), node, SingleTruthFractionalAnalysis.this.getName());
				}

				FractionalPermissions paramRemainder = paramPerms.splitOff(tf.getParameterPostConditions().get(x));

				if(paramRemainder.isUnsatisfiable())
					reporter.reportUserProblem(x + " returns no suitable permissions for post-condition", node, SingleTruthFractionalAnalysis.this.getName());
				
				/*
				 * Either way, the rest goes back into the lattice.
				 */
				curLattice.put(x, paramRemainder);
			}
			return curLattice;
		}

		/**
		 * This method makes sure that the 'packing' condition is fulfilled, which boils
		 * down to checking the post condition for fields.
		 * 
		 * This method should be extremely similar to checkParameterPostCondtion, at least
		 * at the outset.
		 * @return 
		 */
		private PluralTupleLatticeElement checkRecvrFieldsPostCondition(PluralTupleLatticeElement curLattice,
				ASTNode node) {
			// get results first so the post-condition is populated
			//PluralTupleLatticeElement exit = fa.getResultsBefore(node);
			if( curLattice.isBottom() ) return curLattice; // Means unreachable node.
			
			curLattice = curLattice.mutableCopy();
			final Aliasing this_loc = curLattice.getLocationsAfter(node, tf.getAnalysisContext().getThisVariable());
			final PermissionSetFromAnnotations this_post_perm = tf.getParameterPostConditions().get(this_loc);
			
			Set<String> needed_rcvr_states;
			if(this_post_perm == null)
				needed_rcvr_states = Collections.emptySet();
			else
				needed_rcvr_states = this_post_perm.getStateInfo();
			
			PluralTupleLatticeElement packed_lattice =
				this.wrangleIntoPackedStates(node, needed_rcvr_states, curLattice);
			
			if( packed_lattice == null ) {
				if(needed_rcvr_states.isEmpty())
					reporter.reportUserProblem("Could not pack receiver to any state " +
							"due to insufficient field permissions",
							node, SingleTruthFractionalAnalysis.this.getName());					
				else
					reporter.reportUserProblem("Could not pack receiver to post-condition states " +
							needed_rcvr_states + " due to insufficient field permissions",
							node, SingleTruthFractionalAnalysis.this.getName());	
				return curLattice;
			}
			else if(this_post_perm != null) {
				FractionalPermissions remainder_perm = 
					packed_lattice.get(this_loc).splitOff(this_post_perm);
				if( remainder_perm.isUnsatisfiable() ) {
					reporter.reportUserProblem("Receiver returns no suitable permissions for post-condition",
							node, SingleTruthFractionalAnalysis.this.getName());
				}
				packed_lattice.put(this_loc, remainder_perm);
			}
			return packed_lattice;
		}
		
		/**
		 * Check to ensure the correspondence between the return value and the receiver state,
		 * if this is a state test. 
		 * @return 
		 */
		private PluralTupleLatticeElement checkDynamicStateTest(PluralTupleLatticeElement curLattice,
				ReturnStatement node) {
			/*
			 * There might not even be a dynamic state test!
			 */
			if( node.getExpression() == null || !node.getExpression().resolveTypeBinding().isPrimitive() )
				return curLattice;
			
			/*
			 * If this is going to work for more than just booleans, it needs to become
			 * a lot more general.
			 */
			String true_state  = tf.getDynamicStateTest().get(Boolean.TRUE);
			String false_state = tf.getDynamicStateTest().get(Boolean.FALSE);

			/*
			 * We need to know what the return variable is so we can query the
			 * dynamicStateTestLogic.
			 */
			Variable ret_var = 
				//EclipseTAC.getMethodTAC(currentMethod).variable(node.getExpression());
			analysisInput.getComUnitTACs().unwrap().getMethodTAC(currentMethod).variable(node.getExpression());
			
			Aliasing ret_loc = 
				curLattice.getLocationsAfter(node, ret_var);
			
			if( curLattice.isBooleanTrue(ret_loc) ) {
				/*
				 * If we know the result to be true universally, then we don't have to check
				 * the false branch.  
				 */
				if( true_state != null ) {
					curLattice = curLattice.mutableCopy();
					PluralTupleLatticeElement l =
						wrangleIntoPackedState(node, true_state, curLattice);
					if( l == null ) {
						reporter.reportUserProblem("On true branch of return, receiver is not in " +
								true_state + " as is specified.", node, SingleTruthFractionalAnalysis.this.getName());
					}
					else {
						curLattice = l;
					}
				}
			}
			else if( curLattice.isBooleanFalse(ret_loc) ) {
				/*
				 * Same for the false. We don't have to check the true branch.
				 */
				if( false_state != null ) {
					curLattice = curLattice.mutableCopy();
					PluralTupleLatticeElement l = wrangleIntoPackedState(node, false_state, curLattice);
					if( l == null ) {
						reporter.reportUserProblem("On false branch of return, receiver is not in " +
								false_state + " as is specified.", node, SingleTruthFractionalAnalysis.this.getName());					
					}
					else {
						curLattice = l;
					}
				}
			}
			else {
				/*
				 * We couldn't be sure that the result was definitely true or definitely
				 * false, we need to check both the true and false lattices.
				 */
				if(true_state != null) {
					PluralTupleLatticeElement true_lattice = 
						fa.getLabeledResultsAfter(node).get(BooleanLabel.getBooleanLabel(true));
					
					true_lattice = true_lattice.mutableCopy();
					true_lattice = wrangleIntoPackedState(node, true_state, true_lattice);
					if( true_lattice == null ) {
						reporter.reportUserProblem("On true branch of return, receiver is not in " +
								true_state + " as is specified.", node, SingleTruthFractionalAnalysis.this.getName());
					}
				}
				
				if(false_state != null) {
					PluralTupleLatticeElement false_lattice = 
						fa.getLabeledResultsAfter(node).get(BooleanLabel.getBooleanLabel(false));
	
					false_lattice = false_lattice.mutableCopy();
					false_lattice = wrangleIntoPackedState(node, false_state, false_lattice);
					if( false_lattice == null ) {
						reporter.reportUserProblem("On false branch of return, receiver is not in " +
								false_state + " as is specified.", node, SingleTruthFractionalAnalysis.this.getName());					
					}
				}
			}
			
			return curLattice;
		}
		
		/**
		 * When the receiver is in some dubious state, this method packs/unpacks the
		 * lattice and tries to wrangle it into the given state.
		 * @param node
		 * @param state
		 * @param value
		 * @return <code>null</code> if we could not wrangle the receiver into the specified state, the resulting lattice otherwise.
		 */
		private PluralTupleLatticeElement wrangleIntoPackedState(ASTNode node, 
				String state, PluralTupleLatticeElement value) {
			return wrangleIntoPackedStates(node, Collections.singleton(state), value);
		}
		
		/**
		 * When the receiver is in some dubious state, this method packs/unpacks the
		 * lattice and tries to wrangle it into the given set of states.
		 * @param node
		 * @param neededStates
		 * @param value
		 * @return <code>null</code> if we could not wrangle the receiver into the specified state, the resulting lattice otherwise.
		 */
		private PluralTupleLatticeElement wrangleIntoPackedStates(
				final ASTNode node, 
				Set<String> neededStates,
				final PluralTupleLatticeElement value) {
			
			final SimpleMap<Variable, Aliasing> loc_map = new SimpleMap<Variable, Aliasing>() {
				@Override
				public Aliasing get(Variable key) {
					return value.getLocationsAfter(node, key);
				}};
			
			if(neededStates.isEmpty()) {
				/*
				 * No post-condition for the receiver.
				 * Make sure we can pack to SOMETHING. Go home.
				 */
				if( !value.isRcvrPacked() ) {
					if(!value.packReceiverToBestGuess(tf.getAnalysisContext().getThisVariable(),
							getRepository(), loc_map) ) {
						return null;
					}					
				}
				return value;
			}
			else {
				// this code used to be in wrangleToPackedState--but now we can pack to multiple states at the same time
				ThisVariable this_var = tf.getAnalysisContext().getThisVariable();
				Set<String> states = AbstractFractionalPermission.cleanStateInfo(
						getRepository().getStateSpace(this_var.resolveType()), 
						neededStates, true);
				if( value.isRcvrPacked() ) {
					FractionalPermissions this_perms = value.get(node, this_var);
					states = filterUnsatisfied(this_perms, states);
					if( !states.isEmpty() ) {
						// there are states we need to get to that we're not already in
						String unpack_state = null;
						for(String s : states) {
							for(FractionalPermission p : this_perms.getPermissions()) {
								if(p.getStateSpace().firstBiggerThanSecond(p.getRootNode(), s)) {
									if(unpack_state == null)
										unpack_state = p.getRootNode();
									else if(! unpack_state.equals(p.getRootNode())) {
										// TODO unpack to tightest root around needed states
										// if we would have to unpack multiple permissions,
										// just try to unpack alive for now
										unpack_state = StateSpace.STATE_ALIVE;
									}
									break;
								}
								if(p.getStateSpace().firstBiggerThanSecond(s, p.getRootNode())) {
									// can't happen: isInState(state) should be true in this case
									logger.warning("Object not in state " + s + " even though " +
											"more precise permission is available: " + this_perms);
								}
							}
						}
						if(unpack_state == null)
							// no permission could be unpacked to reach desired state
							return null;
						
						/*
						 * We have to try to unpack and pack to the correct state.
						 */
						value.unpackReceiver(this_var, node,
								getRepository(), loc_map, unpack_state, null);
						boolean packed =
							value.packReceiver(tf.getAnalysisContext().getThisVariable(),
									getRepository(), loc_map, states);
						if( !packed ) {
							return null;
						}
						else {
							// PASSED!
						}
					}
					else {
						// PASSED!
					}
				}
				else {
					/*
					 * Just see if it will pack to the correct state.
					 */
					final boolean packed = 
						value.packReceiver(tf.getAnalysisContext().getThisVariable(),
							getRepository(), loc_map, states);
					
					if( !packed ) {
						return null;
					}
				}
				
				return value;
			}
		}

		private Set<String> filterUnsatisfied(FractionalPermissions perms, Set<String> states) {
			LinkedHashSet<String> result = new LinkedHashSet<String>();
			for(String s : states) {
				if(! perms.isInState(s))
					result.add(s);
			}
			return result;
		}

//		private PluralTupleLatticeElement wrangleIntoPackedState(ASTNode node, 
//				String state, PluralTupleLatticeElement value) {
//			
//			ThisVariable this_var = tf.getAnalysisContext().getThisVariable();
//			
//			if( value.isRcvrPacked() ) {
//				FractionalPermissions this_perms = value.get(node, this_var);
//				if( !this_perms.isInState(state) ) {
//					String unpack_state = null;
//					for(FractionalPermission p : this_perms.getPermissions()) {
//						if(p.getStateSpace().firstBiggerThanSecond(p.getRootNode(), state)) {
//							unpack_state = p.getRootNode();
//							break;
//						}
//						if(p.getStateSpace().firstBiggerThanSecond(state, p.getRootNode())) {
//							// can't happen: isInState(state) should be true in this case
//							logger.warning("Object not in state " + state + " even though " +
//									"more precise permission is available: " + this_perms);
//						}
//					}
//					if(unpack_state == null)
//						// no permission could be unpacked to reach desired state
//						return null;
//					
//					/*
//					 * We have to try to unpack and pack to the correct state.
//					 */
//					value.unpackReceiver(this_var,
//							unpack_state,
//							null,
//							getRepository(), node);
//					boolean packed =
//						value.packReceiver(tf.getAnalysisContext().getThisVariable(),
//								getRepository(), node, state);
//					if( !packed ) {
//						return null;
//					}
//					else {
//						// PASSED!
//					}
//				}
//				else {
//					// PASSED!
//				}
//			}
//			else {
//				/*
//				 * Just see if it will pack to the correct state.
//				 */
//				final boolean packed = 
//					value.packReceiver(tf.getAnalysisContext().getThisVariable(),
//						getRepository(), node, state);
//				
//				if( !packed ) {
//					return null;
//				}
//			}
//			
//			return value;
//		}
	}
		
//	@Deprecated
//	protected List<CrystalPermissionAnnotation> receiverAnnotations(
//			IMethodBinding method) {
//		return CrystalPermissionAnnotation.receiverAnnotations(
//				crystal.getAnnotationDatabase(), method);
//	}

	private boolean isAbstract(MethodDeclaration node) {
		return node.getBody() == null;
	}

//	@Deprecated
//	protected List<CrystalPermissionAnnotation> resultAnnotations(
//			IMethodBinding method) {
//		return CrystalPermissionAnnotation.resultAnnotations(
//				crystal.getAnnotationDatabase(), method);
//	}
//
//	@Deprecated
//	protected List<CrystalPermissionAnnotation> argumentAnnotations(
//			IMethodBinding method, int argIndex) {
//		return CrystalPermissionAnnotation.parameterAnnotations(
//				crystal.getAnnotationDatabase(), method, argIndex);
//	}

	protected boolean checkStateOptions(
			FractionalPermissions perms,
			Set<Set<String>> requiredOptions,
			Variable x, ASTNode node) {
		assert requiredOptions.size() == 1;
		return checkState(perms, requiredOptions.iterator().next(), x, node);
	}
	
	protected boolean checkState(
			FractionalPermissions perms,
			Set<String> required,
			Variable x, ASTNode node) {
		if(required == null || required.isEmpty()) 
			return true;
		for(String needed : required) {
			if(perms.getUnpackedPermission() != null && 
					perms.getStateSpace().firstBiggerThanSecond(perms.getUnpackedPermission().getRootNode(), needed)) {
				if(logger.isLoggable(Level.FINE))
					logger.fine("Skipping pre-condition state test on " + needed + " for unpacked permission: " + perms);
			}
			else if(perms.isInState(needed) == false) { 
				reporter.reportUserProblem("" + x.getSourceString() + " must be in state " + needed + " but is in " + perms.getStateInfo(), node, this.getName());
				return false;
			}
		}
		return true;
	}
}
