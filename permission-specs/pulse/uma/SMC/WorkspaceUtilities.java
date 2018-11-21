/**
 * Copyright (c) 2006, 2007, 2008 Marwan Abi-Antoun, Jonathan Aldrich, Nels E. Beckman,
 * Kevin Bierhoff, David Dickey, Ciera Jaspan, Thomas LaToza, Gabriel Zenarosa, and others.
 *
 * This file is part of Crystal.
 *
 * Crystal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Crystal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crystal.  If not, see <http://www.gnu.org/licenses/>.
 */
package uma.SMC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;



/**
 * A collection of methods used to extract useful data from the workspace.
 * These methods are used by the framework and should not be used by users
 * of the framework.
 * 
 * You can access must of the data collected from these methods via the
 * Crystal class.
 * 
 * @author David Dickey
 *
 */
public class WorkspaceUtilities {

	private static final Logger log = Logger.getLogger(WorkspaceUtilities.class.getName());

	/**
	 * Traverses the workspace for CompilationUnits.
	 * 
	 * @return	the list of all CompilationUnits in the workspace or
	 * <code>null</code> if no comp units were found.
	 */
	public static List<ICompilationUnit> scanForCompilationUnits() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if(workspace == null) {
			log.warning("No workspace");
			return null;
		}
		IWorkspaceRoot root = workspace.getRoot();
		if(root == null) {
			log.warning("No workspace root");
			return null;
		}
 		IJavaModel javaModel = JavaCore.create(root);
		if(javaModel == null) {
			log.warning("No Java Model in workspace");
			return null;
		}

 		// Get all CompilationUnits
 		return collectCompilationUnits(javaModel);
	}

	/**
	 * A recursive traversal of the IJavaModel starting from the given
	 * element to collect all ICompilationUnits.
	 * Each compilation unit corresponds to each java file.
	 *  
	 * @param javaElement a node in the IJavaModel that will be traversed
	 * @return a list of compilation units or <code>null</code> if no comp units are found
	 */
	public static List<ICompilationUnit> collectCompilationUnits(IJavaElement javaElement) {
		
		List<ICompilationUnit> list = null, temp = null;
		
		// We are traversing the JavaModel for COMPILATION_UNITs
 		
		if(javaElement.getElementType() == IJavaElement.COMPILATION_UNIT) {
 			list = new ArrayList<ICompilationUnit>();
 			list.add((ICompilationUnit) javaElement);
 			return list;
 		}
 		
		// Non COMPILATION_UNITs will have to be further traversed
		if(javaElement instanceof IParent) {
 	 		IParent parent = (IParent) javaElement;
 	 		
 	 		// Do not traverse PACKAGE_FRAGMENT_ROOTs that are ReadOnly
 	 		// this ignores libraries and .class files
 	 		if(javaElement.getElementType() == IJavaElement.PACKAGE_FRAGMENT_ROOT
 	 				&& javaElement.isReadOnly()) {
				return null;
 	 		}
 	 		
 			// Traverse
 	 		try {
	 			if(parent.hasChildren()) {
	 				IJavaElement[] children = parent.getChildren();
					for(int i = 0; i < children.length; i++) {
						temp = collectCompilationUnits(children[i]);
						if(temp != null)
							if(list == null)
								list = temp;
							else
								list.addAll(temp);
					}
	 			}
			} catch (JavaModelException jme) {
				log.log(Level.SEVERE, "Problem traversing Java model element: " + parent, jme);
			}
		} 
		else {
			log.warning("Encountered a model element that's not a comp unit or parent: " + javaElement);
		}
		
 		return list;
	}
	
	
	/**
	 * Goes through a list of compilation units and parses them.  The act of parsing
	 * creates the AST structures from the source code.
	 * 
	 * @param compilationUnits	the list of compilation units to parse
	 * @return	the mapping from compilation unit to the AST roots of each
	 * @throws Exception 
	 */
	public static Map<ICompilationUnit, ASTNode> parseCompilationUnits(List<ICompilationUnit> compilationUnits) throws Exception {
		if(compilationUnits == null)
			throw new Exception("null list of compilation units");
		
		Map<ICompilationUnit, ASTNode> parsedCompilationUnits = new HashMap<ICompilationUnit, ASTNode>();
 		Iterator<ICompilationUnit> iter = compilationUnits.iterator();
 		ICompilationUnit compUnit = null;
 		ASTParser parser = null;
 		ASTNode node = null;
 		for(; iter.hasNext() ;) {
 			compUnit = iter.next();
 	 		parser = ASTParser.newParser(AST.JLS3);
 			parser.setResolveBindings(true);
 			parser.setSource(compUnit);
 			node = parser.createAST(null);
 			parsedCompilationUnits.put(compUnit, node);
 		}
 		return parsedCompilationUnits;
	}
	
	/**
	 * Collects all top level methods from CompilationUnits.
	 * 
	 * (Embedded Methods are currently not collected.)
	 * 
	 * @param compilationUnitToASTNode	the mapping of CompilationUnits to preparsed ASTNodes
	 * @return							the list of all top level methods within the CompilationUnits
	 * @throws Exception 
	 */
	public static List<MethodDeclaration> scanForMethodDeclarations(Map<ICompilationUnit, ASTNode> compilationUnitToASTNode) throws Exception {
		if(compilationUnitToASTNode == null)
			throw new Exception("null map of compilation units to ASTNodes");
		
		// Create an empty list
		List<MethodDeclaration> methodList = new LinkedList<MethodDeclaration>();
		List<MethodDeclaration> tempMethodList; 
		// Get all CompilationUnits and look for MethodDeclarations in each
		Set<ICompilationUnit> compUnits = compilationUnitToASTNode.keySet();
		Iterator<ICompilationUnit> compUnitIterator = compUnits.iterator();
		ICompilationUnit icu;
		for(;compUnitIterator.hasNext();){
			icu = compUnitIterator.next();
			tempMethodList = scanForMethodDeclarationsFromAST(compilationUnitToASTNode.get(icu));
			methodList.addAll(tempMethodList);
		}
		return methodList;
	}
	
	/**
	 * Collects all top level methods from an AST including embedded methods.
	 * 
	 * @param node	the root of an AST
	 * @return		all top level methods within the AST
	 * @throws Exception 
	 */
	public static List<MethodDeclaration> scanForMethodDeclarationsFromAST(ASTNode node) throws Exception {
		if(node == null)
			throw new Exception("AST tree not found from ICompilationUnit");
		
		// Visitor Class
		class MethodFindVisitor extends ASTVisitor {
			List<MethodDeclaration> methodList;
			public MethodFindVisitor(List<MethodDeclaration> inMethodList) {
				methodList = inMethodList;
			}
			// Visit MethodDeclarations
			public boolean visit(MethodDeclaration methodDeclaration) {
				methodList.add(methodDeclaration);
				
				// false returns us back, instead of traversing further down
				return true;
			}
		}

		// Create an empty list, populate methods by traversing using the visitor
		List<MethodDeclaration> methodList = new LinkedList<MethodDeclaration>();
		MethodFindVisitor visitor = new MethodFindVisitor(methodList);
		node.accept(visitor);
		return methodList;
	}



	/**
	 * Returns the list of compilation units for a given list of file names.
	 * All compilation units that <i>contain</i> one of the given strings are
	 * returned.
	 * @param files List of file names to search for.  They will be compared to
	 * the result of {@link #getWorkspaceRelativeName(IJavaElement)}.
	 * @return List of compilation units for a given list of file names.
	 */
	public static List<ICompilationUnit> findCompilationUnits(List<String> files) {
		List<ICompilationUnit> allCompUnits = WorkspaceUtilities.scanForCompilationUnits();

		int foundCount = 0;
		ICompilationUnit[] resultArray = new ICompilationUnit[files.size()];
		for(ICompilationUnit compUnit : allCompUnits) {
			String relativeName = getWorkspaceRelativeName(compUnit);
			for(int i = 0; i < files.size(); i++) {
				if(relativeName.indexOf(files.get(i)) >= 0) {
					resultArray[i] = compUnit;
					++foundCount;
				}
			}
		}
		
		List<ICompilationUnit> result;
		if(foundCount == files.size())
			result = Arrays.asList(resultArray); 
		else {
			result = new ArrayList<ICompilationUnit>(foundCount);
			for(ICompilationUnit compUnit : resultArray) {
				if(compUnit != null)
					result.add(compUnit);
			}
		}
		return result;
	}

	/**
	 * Walks up the Java model hierarchy and separates the names of encountered
	 * elements by forward slashes
	 * @param element
	 * @return Symbolic name of the given Java element relative to the workspace root 
	 */
	public static String getWorkspaceRelativeName(IJavaElement element) {
		String result = element.getElementName();
		while(element.getParent() != null) {
			element = element.getParent();
			result = element.getElementName() + "/" + result;
		}
		return result;
	}

	/**
	 * Gets the root ASTNode for a compilation unit, with bindings on.
	 * @param compUnit
	 * @return the root ASTNode for a compilation unit, with bindings on. 
	 */
	public static ASTNode getASTNodeFromCompilationUnit(ICompilationUnit compUnit) {
	 	ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setResolveBindings(true);
		parser.setSource(compUnit);
		return parser.createAST(/* passing in monitor messes up previous monitor state */ null);
	}
	


}

