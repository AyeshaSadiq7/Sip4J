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
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;

import edu.cmu.cs.crystal.AbstractCrystalMethodAnalysis;
import edu.cmu.cs.crystal.annotations.AnnotationDatabase;
import edu.cmu.cs.crystal.flow.ITACFlowAnalysis;
import edu.cmu.cs.crystal.internal.Option;
import edu.cmu.cs.crystal.tac.TACFlowAnalysis;
import edu.cmu.cs.crystal.tac.Variable;
import edu.cmu.cs.crystal.tac.eclipse.CompilationUnitTACs;
import edu.cmu.cs.plural.linear.PluralDisjunctiveLE;
import edu.cmu.cs.plural.states.IConstructorSignature;
import edu.cmu.cs.plural.states.IInvocationCase;
import edu.cmu.cs.plural.states.IInvocationCaseInstance;
import edu.cmu.cs.plural.states.IInvocationSignature;
import edu.cmu.cs.plural.states.IMethodSignature;
import edu.cmu.cs.plural.states.StateSpaceRepository;

/**
 * @author Nels Beckman
 * @author Kevin Bierhoff
 */
public class FractionalAnalysis extends AbstractCrystalMethodAnalysis 
		implements FractionAnalysisContext {
	
	/** 
	 * Set this to <code>true</code> to enable array assignment checks. 
	 * @see #endVisit(ArrayAccess)
	 */
	public static boolean checkArrays = false;

	private ITACFlowAnalysis<PluralDisjunctiveLE> fa;
	private static Logger logger = Logger.getLogger(FractionalAnalysis.class.getName());

	private FractionalTransfer tf;

	private IInvocationCaseInstance analyzedCase;

	public FractionalAnalysis() {
		super();
	}

	/**
	 * Factory method to allow subclasses to return a different subclass of
	 * FractionalTransfer. This method should ALWAYS be used for initializing
	 * <code>this.tf</code>.
	 * @return
	 */
	protected FractionalTransfer createNewFractionalTransfer() {
		return new FractionalTransfer(analysisInput, this);
	}
	
	/**
	 * Another factory method. Allows subclasses to also extend FractionalChecker. 
	 * @return
	 */
	protected FractionalChecker createASTWalker() {
		return new FractionalChecker();
	}
	
	protected ITACFlowAnalysis<PluralDisjunctiveLE> getFa() {
		return fa;
	}

	protected FractionalTransfer getTf() {
		return tf;
	}
	
	@Override
	public void analyzeMethod(MethodDeclaration d) {
		if(isAbstract(d)) {
			if(logger.isLoggable(Level.FINE))
				logger.fine("Skip abstract method " + d.getName());
		}
		else {
			// only analyze methods with code in them; skip abstract methods
			IInvocationSignature sig = getRepository().getSignature(d.resolveBinding());
			
			for(IInvocationCase c : sig.cases()) {
				analyzedCase = c.createPermissions(true, false);
				tf = createNewFractionalTransfer();
				fa = new TACFlowAnalysis<PluralDisjunctiveLE>(getTf(),
						this.analysisInput.getComUnitTACs().unwrap());
				
				FractionalChecker checker = createASTWalker();
				if(sig.cases().size() > 1)
					// make sure checker prints the case in which errors occurred 
					// (if more than one case)
					checker.setErrorContext(c.toString());
				if(logger.isLoggable(Level.FINE))
					logger.fine("Results for " + d.getName() + " case " + c);
				d.accept(checker);
			}
		}
	}	
	
	@Override
	public AnnotationDatabase getAnnoDB() {
		return analysisInput.getAnnoDB();
	}
	
	@Override
	public Option<CompilationUnitTACs> getComUnitTACs() {
		return analysisInput.getComUnitTACs();
	}

	@Override
	public StateSpaceRepository getRepository() {
		return StateSpaceRepository.getInstance(analysisInput.getAnnoDB());
	}

	@Override
	public IInvocationCaseInstance getAnalyzedCase() {
		return analyzedCase;
	}

	protected class FractionalChecker extends ASTVisitor {
		
		protected String errorCtx = "";
		
		public void setErrorContext(String ctx) {
			errorCtx = " (" + ctx + ")";
		}

		@Override
		public void endVisit(MethodDeclaration node) {
			if(isAbstract(node) == false) {
				final Block block = node.getBody();
				// check whether post-conditions for parameters are validated
				PluralDisjunctiveLE exit = getFa().getResultsBefore(block);
				
				// Sometimes this method is called on unreachable statements :-(
				if( exit.isBottom() ) {
					if(logger.isLoggable(Level.FINEST))
						logger.finest("no implicit exit at the end of the method body");
					return;
				}
				
				reportIfError(
						exit.checkPostCondition(block, 
								null /* no result value */, 
								FractionalAnalysis.this.getAnalyzedCase().getPostconditionChecker(),
								getTf().getInitialLocations(), 
								Collections.<Boolean, String>emptyMap()), 
						block);

				// debug
				if(logger.isLoggable(Level.FINEST))
					logger.finest("exit: " + exit);
			}
			super.endVisit(node);
		}

		@Override
		public void endVisit(ReturnStatement node) {
			// pull results first
			PluralDisjunctiveLE exit = getFa().getResultsBefore(node);

			if(exit.isBottom()) {
				logger.warning("Bottom encountered before return: " + node);
				super.endVisit(node);
				return;
			}
			
			Variable resultVar = node.getExpression() != null ? getFa().getVariable(node.getExpression()) : null;

			// check whether post-conditions for parameters are validated
			reportIfError(
					exit.checkPostCondition(node, 
							resultVar, 
							FractionalAnalysis.this.getAnalyzedCase().getPostconditionChecker(),
							getTf().getInitialLocations(), 
							getTf().getDynamicStateTest()), 
					node);

			// debug
			if(logger.isLoggable(Level.FINEST)) {
				logger.finest("" + getFa().getResultsBefore(node));
				logger.finest("  " + node);
			}
		}

		@Override
		public void endVisit(ClassInstanceCreation node) {
			final PluralDisjunctiveLE before = getFa().getResultsBefore(node);
//			final PluralDisjunctiveLE after = getFa().getResultsAfter(node);
			final IMethodBinding constructorBinding = node.resolveConstructorBinding();
			final IConstructorSignature sig = getRepository().getConstructorSignature(constructorBinding);
			
			if(FractionalAnalysis.isBottom(before, node)) {
				super.endVisit(node);
				return;
			}
			
			checkCases(node, before, sig, null /* no receiver yet */, 
					variables((List<Expression>) node.arguments()), false /* "new" */);
			
			// arguments
//			int argIndex = 0;
//			for(Expression e : (List<Expression>) node.arguments()) {
//				final Variable arg = getFa().getVariable(e);
//				
//				if(! after.checkConstraintsSatisfiable(arg))
//					// TODO better reporting
//					reporter.reportUserProblem(
//							"" + e + " yields no suitable permission for surrounding call" + errorCtx, 
//							e, FractionalAnalysis.this.getName());
//				
//				reportIfError(
//						before.checkStates(arg, sig.getRequiredParameterStateOptions(argIndex)),
//						e);
//				
//				++argIndex;
//			}
			
			// debug
			if(logger.isLoggable(Level.FINEST)) {
				logger.finest(before.toString());
				logger.finest("  " + node);
//				logger.finest(after.toString());
			}
			super.endVisit(node);
		}

		@Override
		public void endVisit(MethodInvocation node) {
			final PluralDisjunctiveLE before = getFa().getResultsBefore(node);
//			final PluralDisjunctiveLE after = getFa().getResultsAfter(node);
			final IMethodBinding methodBinding = node.resolveMethodBinding();
			final IMethodSignature sig = getRepository().getMethodSignature(methodBinding);
			
			if(FractionalAnalysis.isBottom(before, node)) {
				super.endVisit(node);
				return;
			}
			
			// receiver
			final Variable receiver;
			if(sig.hasReceiver()) {
				if(node.getExpression() == null)
					receiver = getFa().getImplicitThisVariable(methodBinding);
				else
					receiver = getFa().getVariable(node.getExpression());
				
//				if(! after.checkConstraintsSatisfiable(receiver))
//					// TODO better reporting
//					reporter.reportUserProblem(
//							"" + receiver.getSourceString() + " carries no suitable permission" + errorCtx, 
//							node, FractionalAnalysis.this.getName());
//				reportIfError(
//						before.checkStates(receiver, sig.getRequiredReceiverStateOptions()),
//						node);
			}
			else
				// static method
				receiver = null;
			
			// arguments
//			int argIdx = 0;
//			for(Expression e : (List<Expression>) node.arguments()) {
//				Variable arg = getFa().getVariable(e);
//				if(! after.checkConstraintsSatisfiable(arg))
//					// TODO better reporting
//					reporter.reportUserProblem(
//							"" + e + " yields no suitable permission for surrounding call" + errorCtx, 
//							e, FractionalAnalysis.this.getName());
//				
//				reportIfError(
//						before.checkStates(arg, sig.getRequiredParameterStateOptions(argIdx)),
//						e);
//				++argIdx;
//			}

			checkCases(node, before, sig, receiver, 
					variables((List<Expression>) node.arguments()), false /* dynamic dispatch or static method call */);
			
			// debug
			if(logger.isLoggable(Level.FINEST)) {
				logger.finest(before.toString());
				logger.finest("  " + node);
//				logger.finest(after.toString());
			}
			super.endVisit(node);
		}

		@Override
		public void endVisit(SuperMethodInvocation node) {
			final PluralDisjunctiveLE before = getFa().getResultsBefore(node);
			final IMethodBinding methodBinding = node.resolveMethodBinding();
			final IMethodSignature sig = getRepository().getMethodSignature(methodBinding);
			
			if(FractionalAnalysis.isBottom(before, node)) {
				super.endVisit(node);
				return;
			}
			
			checkCases(node, before, sig, null /* lattice fills in super */, 
					variables((List<Expression>) node.arguments()), true /* static dispatch */);
			
			// debug
			if(logger.isLoggable(Level.FINEST)) {
				logger.finest(before.toString());
				logger.finest("  " + node);
			}
			super.endVisit(node);
		}

		@Override
		public void endVisit(ArrayAccess node) {
			// check stores into arrays: was array modifiable?
			if(checkArrays  && node.getParent() instanceof Assignment) {
				Assignment store = (Assignment) node.getParent();
				if(store.getLeftHandSide().equals(node)) {
					final PluralDisjunctiveLE before = getFa().getResultsBefore(store);
					final PluralDisjunctiveLE after = getFa().getResultsAfter(store);

					if(FractionalAnalysis.isBottom(before, after, node)) {
						super.endVisit(node);
						return;
					}
					
					if(! after.checkConstraintsSatisfiable(getFa().getVariable(node.getArray())))
						// TODO better reporting
						reporter.reportUserProblem(
								"no suitable permission for assignment to " + node + errorCtx, 
								node, FractionalAnalysis.this.getName());
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

		@Override
		public void endVisit(ConstructorInvocation node) {
			final PluralDisjunctiveLE before = getFa().getResultsBefore(node);
//			final PluralDisjunctiveLE after = getFa().getResultsAfter(node);
			final IMethodBinding constructorBinding = node.resolveConstructorBinding();
			final IConstructorSignature sig = getRepository().getConstructorSignature(constructorBinding);
			
			if(FractionalAnalysis.isBottom(before, node)) {
				super.endVisit(node);
				return;
			}
			
			// receiver is always "this"  (super constructor call is different node type)
			final Variable receiver = getFa().getImplicitThisVariable(constructorBinding);
			
			checkCases(node, before, sig, receiver, 
					variables((List<Expression>) node.arguments()), true);
			
			//			if(! after.checkConstraintsSatisfiable(receiver))
//				// TODO better reporting
//				reporter.reportUserProblem(
//						"this carries no suitable permission for constructor call" + errorCtx, 
//						node, FractionalAnalysis.this.getName());
//			
//			reportIfError(
//					before.checkStates(receiver, sig.getRequiredReceiverStateOptions()),
//					node);
			
			// arguments
//			int argIdx = 0;
//			for(Expression e : (List<Expression>) node.arguments()) {
//				Variable arg = getFa().getVariable(e);
//				if(! after.checkConstraintsSatisfiable(arg))
//					// TODO better reporting
//					reporter.reportUserProblem(
//							"" + e + " yields no suitable permission for constructor call" + errorCtx, 
//							e, FractionalAnalysis.this.getName());
//				
//				reportIfError(
//						before.checkStates(arg, sig.getRequiredParameterStateOptions(argIdx)),
//						e);
//				++argIdx;
//			}

			// debug
			if(logger.isLoggable(Level.FINEST)) {
				logger.finest(before.toString());
				logger.finest("  " + node);
//				logger.finest(after.toString());
			}
			super.endVisit(node);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.SuperConstructorInvocation)
		 */
		@Override
		public void endVisit(SuperConstructorInvocation node) {
			final PluralDisjunctiveLE before = getFa().getResultsBefore(node);
//			final PluralDisjunctiveLE after = getFa().getResultsAfter(node);
			final IMethodBinding constructorBinding = node.resolveConstructorBinding();
			final IConstructorSignature sig = getRepository().getConstructorSignature(constructorBinding);
			
			if(FractionalAnalysis.isBottom(before, node)) {
				super.endVisit(node);
				return;
			}
			
			checkCases(node, before, sig, null /* lattice fills in super variable */, 
					variables((List<Expression>) node.arguments()), true /* static dispatch */);

			// receiver
			// TODO use "super" variable
//			final Variable receiver = getFa().getSuperVariable();
			
//			if(! after.checkConstraintsSatisfiable(receiver))
//				// TODO better reporting
//				reporter.reportUserProblem(
//						"super carries no suitable permission for super-constructor call" + errorCtx, 
//						node, FractionalAnalysis.this.getName());
//			
//			reportIfError(
//					before.checkStates(receiver, sig.getRequiredReceiverStateOptions()),
//					node);
			
			// arguments
//			int argIdx = 0;
//			for(Expression e : (List<Expression>) node.arguments()) {
//				Variable arg = getFa().getVariable(e);
//				if(! after.checkConstraintsSatisfiable(arg))
//					// TODO better reporting
//					reporter.reportUserProblem(
//							"" + e + " yields no suitable permission for super-constructor call" + errorCtx, 
//							e, FractionalAnalysis.this.getName());
//				
//				reportIfError(
//						before.checkStates(arg, sig.getRequiredParameterStateOptions(argIdx)),
//						e);
//				++argIdx;
//			}

			// debug
			if(logger.isLoggable(Level.FINEST)) {
				logger.finest(before.toString());
				logger.finest("  " + node);
//				logger.finest(after.toString());
			}
			super.endVisit(node);
		}

		/**
		 * @param node
		 * @param before
		 * @param sig
		 * @param receiver <code>null</code> for <code>super</code> and static methods.
		 * @param arguments
		 * @param receiverIsStaticallyBound for methods that have a receiver, <code>true</code>
		 * indicates that this is a statically dispatched call, ie. a call to <code>super</code>
		 * or a constructor invocation on <code>this</code>, but <b>not</b> a object instantiation
		 * with <code>new</code>.
		 */
		private void checkCases(ASTNode node,
				final PluralDisjunctiveLE before, final IInvocationSignature sig,
				final Variable receiver, List<Variable> arguments, boolean receiverIsStaticallyBound) {
			List<String> errors = new LinkedList<String>();
			for(IInvocationCaseInstance c : sig.createPermissionsForCases(false, receiverIsStaticallyBound)) {
				String err;
				if(receiver == null && receiverIsStaticallyBound)
					err = before.checkSuperCallPrecondition(node, 
							arguments,
							c.getPreconditionChecker());
				else
					// any other call, including "new"
					err = before.checkRegularCallPrecondition(node, 
									receiver, arguments,
									c.getPreconditionChecker());
				if(err == null)
					return;
				errors.add(err);
			}
			if(errors.size() == 1)
				reportIfError(errors.iterator().next(), node);
			else if(errors.size() > 1)
				reportIfError("One of: " + errors, node);
		}

		private List<Variable> variables(List<? extends ASTNode> nodes) {
			if(nodes.isEmpty())
				return Collections.emptyList();
			ArrayList<Variable> result = new ArrayList<Variable>(nodes.size());
			for(ASTNode node: nodes) {
				result.add(getFa().getVariable(node));
			}
			return result;
		}

		/**
		 * Reports an error to the user if the given string is non-<code>null</code>.
		 * @param errorOrNull
		 * @param node
		 */
		protected void reportIfError(String errorOrNull, ASTNode node) {
			if(errorOrNull != null)
				reporter.reportUserProblem(errorOrNull + errorCtx, node, FractionalAnalysis.this.getName());
		}

	} // END FractionalChecker
	
	private boolean isAbstract(MethodDeclaration node) {
		return node.getBody() == null;
	}

	private static boolean isBottom(
			PluralDisjunctiveLE before,
			PluralDisjunctiveLE after,
			ASTNode node) {
		if(before.isBottom())
			logger.warning("Encountered bottom before node: " + node);
		else if(after.isBottom())
			logger.warning("Encountered bottom after node: " + node);
		else 
			return false;
		return true;
	}

	private static boolean isBottom(
			PluralDisjunctiveLE before,
			ASTNode node) {
		if(before.isBottom())
			logger.warning("Encountered bottom before node: " + node);
		else 
			return false;
		return true;
	}

}
