package parser;

import graphstructure.E_MethodGraph;
import graphutilities.Graph_Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java_annotations.*;

import datastructure.*;
import org.junit.Assert;
import org.junit.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import datautilities.Data_Controller;

import parser.UserSelectedClasses_Analysis;
import uma.structure.E_Method;

public class MyClassLoader extends ClassLoader{
	 
	public MyClassLoader() {
	}
  
    public static void getAnnotatedCompilationUnits() {
    	
    	List<ICompilationUnit> comUnits = null;
    	
    	Member[] mbrs = null;
    	
    	
    	 Class<?> c = null;
    	 //this.getClass().getClassLoader().getResource("").getPath()
    	
		try {
			c = Class.forName("java_annotations.AnnotationExample");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mbrs = c.getDeclaredMethods();
    	 
		for (Member mbr : mbrs) {
			System.out.printf("  %s%n", ((Method)mbr).toGenericString());
		}
		
		
	   IProject[] projects = Workspace_Utilities.getWorkspaceProjects();
		
	   IJavaElement javaElement = null;
				
		IProject annProj = Parser_Utilities.getAnnotationProject(projects);
		
		if(annProj != null){
			javaElement = JavaCore.create(annProj);
			
		}
		if (javaElement == null) {
		  System.out.println("No Java Model in workspace");
		}
		/*IFolder destination = annProj.getFolder("src/java_annotations");
		
		String folder = destination.getLocation().toString();
		
		final String path = folder+"/AnnotationExample.java";
	    //create the pulse output file*/

		else{
			comUnits = Workspace_Utilities.collectCompilationUnits(javaElement);

				for (ICompilationUnit cunit : comUnits) {
		
					  CompilationUnit cu = null;
		
					   cu = UserSelectedClasses_Analysis.getCompilationUnit(cunit);
					   
					   System.out.println(cu.getJavaElement().getElementName());
					   
					   if(cu.getJavaElement().getElementName().equals("java_annotations.AnnotationExample.java")){
						   
						  cu.accept(new ASTVisitor(){ 
				          @Override 
				          public boolean visit(TypeDeclaration node) { 
				        	
				           System.out.println("class name = "+node.getName().toString());
				           
				           MethodDeclaration[] _methods = node.getMethods();
				           
				           for(MethodDeclaration m: _methods){
				        	   
				        	   if(m.getName().toString().equals("SimpleName")){
				        		try {
									testcases.Sip4JTest.testMethodParsing(m);
									LinkedList<datastructure.E_Method> mgs = Data_Controller.fetchAllMethods();
									
								} catch (NoSuchMethodException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SecurityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				        	   }
						          
				           }
				           
				        	Member[] mbrs = null;
				        	 
							Class<?> c = node.getName().toString().getClass();
							
							try {
								c = Class.forName("java_annotations."+node.getName().toString());
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							mbrs = c.getDeclaredMethods();
							
							for (Member mbr : mbrs) {
								System.out.printf("  %s%n", ((Method)mbr).toGenericString());
							}
							Method[] methods = c.getDeclaredMethods();
							
							testAnnotations(methods);
				           
							return super.visit(node); 
				        }; 
				  }); 	  
			}
		 }
		}
    }
    public static void testAnnotations(Method[] methods){
    	
    	int safeApproxmiations = 0;
	    	
	    int independentmethods = 0;
	    
	   for (Method m : methods){
    		try {
            	/*Assert.assertNotNull("Method :"+m.getName() + " does not have any permission annotations",
            			m.isAnnotationPresent(java_annotations.Permissions.class));
            	
            	if(!m.isAnnotationPresent(java_annotations.Permissions.class)){
            		independentmethods ++;
            	}*/
    
	    		if(m.getName().toString().equals("Fibonacci")){
	        		Annotation[] anno = m.getDeclaredAnnotations();
	        		//System.out.println("Hello g Annotation in Method '" + m + "' : " + anno[0]);
					Assert.assertEquals("Permissions are either not correct or safe permissions are generateds", "@java_annotations.Permissions(perms=@Perm(ensures=unique(this) in alive)", anno[0].toString());
				}
	            if(m.getName().toString().equals("computeFibonacci")){
	        		Annotation[] anno = m.getDeclaredAnnotations();
	        		//System.out.println("Hello g Annotation in Method '" + m + "' : " + anno[0]);
					Assert.assertEquals("Permissions for method "+ m.getName().toString()+" are either not correct or safe permissions are generateds", "@java_annotations.Permissions(perms=@Perm(requires=pure(this) in alive * pure(#0) in alive,ensures=pure(this) in alive * pure(#0) in alive)", anno[0].toString());
				}
	            if(m.getName().toString().equals("display")){
	        		Annotation[] anno = m.getDeclaredAnnotations();
	        		//System.out.println("Hello g Annotation in Method '" + m + "' : " + anno[0]);
					Assert.assertEquals("Permissions are either not correct or safe permissions are generateds", 
							"@java_annotations.Permissions(perms=@Perm(requires=pure(this) in alive,ensures=pure(this) in alive)", anno[0].toString());
				}
	        	if(m.getName().toString().equals("main")){
	        		Annotation[] anno = m.getDeclaredAnnotations();
	        		//System.out.println("Hello g Annotation in Method '" + m + "' : " + anno[0]);
					Assert.assertEquals("Permissions are either not correct or safe permissions are generateds", "@java_annotations.Permissions(perms=@Perm(requires=unique(this) in alive,ensures=unique(this) in alive)", anno[0].toString());
	        	}
	        	if(m.getName().toString().equals("compute")){
	        		Annotation[] anno = m.getDeclaredAnnotations();
	        		System.out.println("I am in Sequential Integral Method '" + m + "' : " + anno[0]);
					Assert.assertEquals("Permissions are either not correct or safe permissions are generateds", "@java_annotations.Permissions(perms=@Perm(requires=full(this) in alive * pure(#0) in alive * pure(#1) in alive,ensures=full(this) in alive * pure(#0) in alive * pure(#1) in alive)", anno[0].toString());
				}
	        	
	            /*for (Annotation anno : m.getDeclaredAnnotations()) {
					System.out.println("Hello g Annotation in Method '" + m + "' : " + anno);
					Assert.assertEquals("Permissions are either not correct or safe permissions are generateds", "@java_annotations.Permissions(perms=@Perm(requires=pure(this) in alive * pure(#0) in alive,ensures=pure(this) in alive * pure(#0) in alive)", anno.toString());		
	            }*/
    		}catch (AssertionError error) {
            // Output expected AssertionErrors.
    			safeApproxmiations ++;
    			continue;
    		} 
    	}
    		System.out.println("Total number of safe approximation = "+safeApproxmiations);
    		System.out.println("Total number of independent methods = "+independentmethods);
	//}
 }



    public Class loadClass(String name) throws ClassNotFoundException {
        if(!"annotations.TestAnns".equals(name))
                return super.loadClass(name);

        try {
            String url = "file:C:/data/projects/tutorials/web/WEB-INF/" +
                            "classes/reflection/MyObject.class";
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass("reflection.MyObject",
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void main(String[] args) throws
    ClassNotFoundException,
    IllegalAccessException,
    InstantiationException {
    
    	
   ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
   // MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
    //classLoader.getAnnotatedCompilationUnits();
   // Class myObjectClass = classLoader.loadClass("reflection.MyObject");
    //create new class loader so classes can be reloaded.
    //classLoader = new MyClassLoader(parentClassLoader);
    //myObjectClass = classLoader.loadClass("reflection.MyObject");
    
}

}
