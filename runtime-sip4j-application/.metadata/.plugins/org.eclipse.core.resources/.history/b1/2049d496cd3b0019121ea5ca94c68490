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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.IMemberValuePairBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;

import edu.cmu.cs.crystal.AbstractCompilationUnitAnalysis;
import edu.cmu.cs.crystal.annotations.AnnotationDatabase;
import edu.cmu.cs.crystal.annotations.ICrystalAnnotation;
import edu.cmu.cs.crystal.internal.Option;
import edu.cmu.cs.plural.perm.parser.PermParser;
import edu.cmu.cs.plural.states.annowrappers.ClassStateDeclAnnotation;
import edu.cmu.cs.plural.states.annowrappers.StateDeclAnnotation;
import edu.cmu.cs.plural.states.annowrappers.StateInvAnnotation;
import edu.cmu.cs.plural.util.Pair;

/**
 * An analysis that examines user annotations to ensure that they have been
 * constructed correctly.
 * 
 * @author Kevin Bierhoff
 *
 */
public class PluralAnnotationAnalysis extends AbstractCompilationUnitAnalysis {
	
	/* (non-Javadoc)
	 * @see edu.cmu.cs.crystal.AbstractCompilationUnitAnalysis#analyzeCompilationUnit(org.eclipse.jdt.core.dom.CompilationUnit)
	 */
	@Override
	public void analyzeCompilationUnit(CompilationUnit d) {
		d.accept(new AnnotationVisitor());
	}
	
	public StateSpaceRepository getRepository() {
		return StateSpaceRepository.getInstance(analysisInput.getAnnoDB());
	}
	
	private class AnnotationVisitor extends ASTVisitor {
		
		//
		// Declaration checks
		// 

		@Override
		public void endVisit(AnonymousClassDeclaration node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		@Override
		public void endVisit(CompilationUnit node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		@Override
		public void endVisit(TypeDeclaration node) {
			// Errors in declared state dimensions
			Map<ICrystalAnnotation, Set<String>> problems = 
				PluralAnnotationAnalysis.this.getRepository().checkStateSpace(node);
			for(Map.Entry<ICrystalAnnotation, Set<String>> p : problems.entrySet()) {
				// TODO localize annotation that causes the problem
				for(String desc : p.getValue()) {
					reporter.reportUserProblem(desc, node, PluralAnnotationAnalysis.this.getName());
				}
			}
				
			/*
			 * Get all errors of ClassStates annotations, and print them out.
			 */
			final List<Pair<ASTNode, String>> errors = 
				PluralAnnotationAnalysis.checkClassStatesAnnot(analysisInput.getAnnoDB(),
						node);
			for( Pair<ASTNode, String> error : errors ) {
				reporter.reportUserProblem(error.snd(), error.fst(),
						PluralAnnotationAnalysis.this.getName());
			}
			super.endVisit(node);
		}

		@Override
		public void endVisit(TypeDeclarationStatement node) {
			// TODO Auto-generated method stub
			super.endVisit(node);
		}

		@Override
		public void endVisit(MarkerAnnotation node) {
			// check that @Cases is not empty, i.e., prevent @Cases({ })
			if("edu.cmu.cs.plural.annot.Cases".equals(node.resolveTypeBinding().getQualifiedName()))
				reporter.reportUserProblem("Must have cases in @Cases", node, PluralAnnotationAnalysis.this.getName());
			super.endVisit(node);
		}

		@Override
		public void endVisit(NormalAnnotation node) {
			// check that @Cases is not empty, i.e., prevent @Cases({ })
			if("edu.cmu.cs.plural.annot.Cases".equals(node.resolveTypeBinding().getQualifiedName())) {
				if(! checkValueArrayNonEmpty(node.resolveAnnotationBinding()))
					reporter.reportUserProblem("Must have cases in @Cases", node, PluralAnnotationAnalysis.this.getName());
			}
			// Do the user's annotations actually parse correctly?
			else if( "edu.cmu.cs.plural.annot.State".equals(node.resolveTypeBinding().getQualifiedName()) ) {
				Option<Object> perm_ = getAnnotationParam(node.resolveAnnotationBinding(), "inv");
				if( perm_.isSome() ) {
					String perm = (String)perm_.unwrap();
					Option<String> parse_error = PermParser.getParseError(perm);
					if( parse_error.isSome() ) {
						reporter.reportUserProblem("Parse error in annotation string: " + parse_error.unwrap(), 
								node, PluralAnnotationAnalysis.this.getName());
					}
				}
				
			}
			else if( "edu.cmu.cs.plural.annot.Perm".equals(node.resolveTypeBinding().getQualifiedName()) ) {
				Option<Object> req_ = getAnnotationParam(node.resolveAnnotationBinding(), "requires");
				Option<Object> ens_ = getAnnotationParam(node.resolveAnnotationBinding(), "ensures");
				
				if( req_.isSome() ) {
					String perm = (String)req_.unwrap();
					Option<String> parse_error = PermParser.getParseError(perm);
					if( parse_error.isSome() ) {
						reporter.reportUserProblem("Parse error in annotation string: " + parse_error.unwrap(), 
								node, PluralAnnotationAnalysis.this.getName());
					}
				}
				if( ens_.isSome() ) {
					String perm = (String)ens_.unwrap();
					Option<String> parse_error = PermParser.getParseError(perm);
					if( parse_error.isSome() ) {
						reporter.reportUserProblem("Parse error in annotation string: " + parse_error.unwrap(), 
								node, PluralAnnotationAnalysis.this.getName());
					}
				}
			}
			super.endVisit(node);
		}

		@Override
		public void endVisit(SingleMemberAnnotation node) {
			// check that @Cases is not empty, i.e., prevent @Cases({ })
			if("edu.cmu.cs.plural.annot.Cases".equals(node.resolveTypeBinding().getQualifiedName())) {
				if(! checkValueArrayNonEmpty(node.resolveAnnotationBinding()))
					reporter.reportUserProblem("Must have cases in @Cases", node, PluralAnnotationAnalysis.this.getName());
			}
			super.endVisit(node);
		}

		@Override
		public void endVisit(MethodDeclaration node) {
			String ambiguity = checkAmbiguousSpecification(node.resolveBinding());
			if(ambiguity != null)
				reporter.reportUserProblem(ambiguity, node, PluralAnnotationAnalysis.this.getName());
			super.endVisit(node);
		}

		//
		// use checks
		//
		
		@Override
		public void endVisit(MethodInvocation node) {
			// check at call sites in case the ambiguity happens in a method not currently checked 
			String ambiguity = checkAmbiguousSpecification(node.resolveMethodBinding());
			if(ambiguity != null)
				reporter.reportUserProblem(ambiguity + ".  Provide specification in " + 
						node.resolveMethodBinding().getDeclaringClass().getName(), 
						node, PluralAnnotationAnalysis.this.getName());
			super.endVisit(node);
		}

		@Override
		public void endVisit(SuperMethodInvocation node) {
			// check at call sites in case the ambiguity happens in a method not currently checked 
			String ambiguity = checkAmbiguousSpecification(node.resolveMethodBinding());
			if(ambiguity != null)
				reporter.reportUserProblem(ambiguity + ".  Provide specification in " + 
						node.resolveMethodBinding().getDeclaringClass().getName(), 
						node, PluralAnnotationAnalysis.this.getName());
			super.endVisit(node);
		}

	}

	/**
	 * This method checks the well-formedness of a ClassStates annotation. It does so
	 * given a type declaration, so it is entirely possible that no annotations of this
	 * type will even exist.
	 * 
	 * Returned list is a list of error-full AST nodes and the error messages that should
	 * be printed with them.
	 */
	private static List<Pair<ASTNode, String>> 
	checkClassStatesAnnot(AnnotationDatabase annotationDatabase,
			TypeDeclaration node) {
		
		/*
		 * No matter what, we need a set of the types in the class.
		 */
		final Set<String> field_names = new HashSet<String>();
		for( IVariableBinding field : node.resolveBinding().getDeclaredFields() ) {
			field_names.add(field.getName());
		}
		
		/*
		 * First check to see that problems even exist, which they may not.
		 */
		final List<ICrystalAnnotation> annos = 
			annotationDatabase.getAnnosForType(node.resolveBinding());
		boolean is_problem = false;
		String problem_field = "";
		
		outer_loop:
		for( ICrystalAnnotation anno : annos ) {
			if( anno instanceof ClassStateDeclAnnotation ) {
				for( StateDeclAnnotation sda : ((ClassStateDeclAnnotation)anno).getStates() ) {
					for( StateInvAnnotation inv : sda.getInvs() ) {
						if( !field_names.contains(inv.getField()) ) {
							/*
							 * We don't catch all incorrect fields, only the first one.
							 * For now....
							 */
							is_problem = true;
							problem_field = inv.getField();
							break outer_loop;
						}
					}
				}
			}
		}
		
		if( !is_problem ) 
			return Collections.emptyList();
		
		/*
		 * There is a problem, we need to find the appropriate annotation to flag.
		 */
		for( Object obj : node.modifiers() ) {
			if( obj instanceof SingleMemberAnnotation ) {
				final boolean this_one = 
					((SingleMemberAnnotation)obj).getTypeName().getFullyQualifiedName().endsWith("ClassStates");
					
				if( this_one ) {
					return Collections.singletonList(new Pair<ASTNode, String>((SingleMemberAnnotation)obj,
					"This annotation refers to field " + problem_field + " of class " +
					node.getName() + " which cannot be found. Possible misspelling or use of " +
					"superclass field."));
				}
			}
		}
		
		throw new IllegalStateException("Probable bug");
	}

	/**
	 * Returns the value of the annotation parameter with the given name, or
	 * NONE if it is not in the given annotation.
	 */
	private Option<Object> getAnnotationParam(IAnnotationBinding anno, String p_name) {
		for(IMemberValuePairBinding p : anno.getAllMemberValuePairs()) {
			if(p_name.equals(p.getName())) {
				return Option.some(p.getValue());
			}
		}		
		return Option.none();
	}
	
	/**
	 * Returns the value of the parameter named "value" for the given
	 * annotation binding, or NONE if there is no key called "value."
	 */
	private Option<Object> getAnnotationValue(IAnnotationBinding anno) {
		return getAnnotationParam(anno, "value");
	}
	
	/**
	 * Checks that the given annotation has a non-empty "value" array parameter.
	 * @param casesAnnotation
	 * @return <code>true</code> if the "value" array parameter is non-empty, 
	 * <code>false</code> otherwise
	 */
	private boolean checkValueArrayNonEmpty(
			IAnnotationBinding casesAnnotation) {
		
		Option<Object> value_ = getAnnotationValue(casesAnnotation);
		
		if( value_.isNone() ) return false;
		
		Object value = value_.unwrap();
		
		if( value instanceof Object[] ) {
			return ((Object[]) value).length > 0;
		}
		else {
			return true;
		}
	}

	/**
	 * Checks whether the effective specification for the given method is ambiguous
	 * because specs are inherited from multiple places.
	 * @param binding
	 * @return An error message if the specification for the given method is ambiguous,
	 * <code>null</code> otherwise (i.e., <code>null</code> means everything is ok).
	 */
	private String checkAmbiguousSpecification(
			IMethodBinding binding) {
		Set<IMethodBinding> specSources = getRepository().findAllMethodsWithSpecification(binding);
		if(specSources.size() > 1) {
			StringBuffer error = new StringBuffer();
			error.append("Ambiguous protocol annotations inherited from types ");
			boolean first = true;
			for(IMethodBinding m : specSources) {
				if(first)
					first = false;
				else
					error.append(", ");
				error.append(m.getDeclaringClass().getName());
			}
			return error.toString();
		}
		return null;
	}
	
}
