package parser;

import graphutilities.Graph_Controller;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import datautilities.Data_Generator;

import uma.SMC.UserSelectedClassesAnalysis;

public class UserSelectedClasses_Analysis {

	static int testType;

	public void visitCompilationUnits(List<ICompilationUnit> compilationUnitList, long startTime)
			throws IOException, JavaModelException {

		//System.out.println("Visit compilation units");
		try {
				//MyClassLoader.getAnnotatedCompilationUnits();
			// UserSelectedClassesAnalysis.testType = testType;

			Data_Generator.createNewPackage(); // creating new package -> pkg =
												// new
			// E_Package();

			// System.out.println("Getting AST root node for each compilation unit");

			// Step 3: get AST root node for each compilation unit
			for (ICompilationUnit cunit : compilationUnitList) {

				// String prog=getInputStream(cunit);
					CompilationUnit cu = null;

				   cu = getCompilationUnit(cunit);

				// Step 4: Visit each compilation unit AST node type

				// System.out.println("Visiting each compilation unit AST Node Type");
 
				    AST_Visitor visitor = new AST_Visitor();

					try {
						cu.accept(visitor);
					} catch (IllegalArgumentException ex) {
						ex.printStackTrace();
					}
				}

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		AST_Parser.extractContextInformation();
		System.out.println("meta-data extraction is done");
		
		Graph_Controller.createGraph();
		System.out.println("Graph Construction and permission inference is done");
	   
		IJavaElement javaElement = UserSelectedClasses_Analysis.getPulseCompilationUnit();
	   // getAnnotationsCompilationUnit
	    ////////////////////////////////////
	    long end = System.nanoTime();
		long elapsedTime = end - startTime;
		double seconds = (double)elapsedTime / 1000000000.0;
		System.out.println("Seconds Time = "+seconds);
		////////////////////////////////////////////////
	    System.out.println("Pulse processing starts here");
	    UserSelectedClasses_Analysis.analyzePulseCompilationUnits(javaElement,0);
	    System.out.println("Third stage is done");
	    //MyClassLoader.getAnnotatedCompilationUnits();
		
	}

	// get the root AST node for a particular compilation unit
	public static CompilationUnit getCompilationUnit(ICompilationUnit cunit) {
		CompilationUnit compilationUnit = (CompilationUnit) Workspace_Utilities
				.getASTNodeFromCompilationUnit(cunit);
		return compilationUnit;
	}

	// get compilation unit from selected file
	@SuppressWarnings("unused")
	private CompilationUnit getCompilationUnit(String prog) {
		ASTParser parser = ASTParser.newParser(3);
		parser.setSource(prog.toCharArray());
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		return cu;
	}

	public String getInputStream(ICompilationUnit unit) {

		InputStream in = null;
		if (unit != null) {
			try {
				in = ((IFile) (unit.getCorrespondingResource())).getContents();
			} catch (CoreException e) {
				e.printStackTrace();
			}

			BufferedInputStream bis = new BufferedInputStream(in);
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			int result;
			try {
				result = bis.read();
				while (result != -1) {
					byte b = (byte) result;
					buf.write(b);
					result = bis.read();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return buf.toString();
		}
		return null;
	}
	
    public static void analyzePulseCompilationUnits(IJavaElement element, int testType){// added by ayesha for pulse
	
		final int ftestType = testType;
		
		List<ICompilationUnit> temp = Workspace_Utilities.collectCompilationUnits(element);
		
		if(temp != null) {
		
			final List<ICompilationUnit> compUnits = temp;
					
			UserSelectedClassesAnalysis UAnalysis = new UserSelectedClassesAnalysis();
				
			UAnalysis.analyzeFromPlugin(compUnits,ftestType);
		}

}
    public static IJavaElement getPulseCompilationUnit() {
		// TODO Auto-generated method stub
	
	   IProject[] projects = Workspace_Utilities.getWorkspaceProjects();
		
		IJavaElement javaElement = null;
				
		IProject pulseProj = Parser_Utilities.getPulseProject(projects);
				
		if(pulseProj!=null){
			javaElement = JavaCore.create(pulseProj);
		}
		if (javaElement == null) {
		  System.out.println("No Java Model in workspace");
			return null;
		}
		
		return javaElement;
		
	}
   
}