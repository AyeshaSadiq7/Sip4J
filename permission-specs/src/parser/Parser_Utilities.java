
package parser;

import graphutilities.Graph_Utilities;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

/**
 * A collection of methods used to extract useful data from the parser. 
 * 
 * @author Ayesha Sadiq
 * 
 */
public class Parser_Utilities{

	public static boolean checkWrapperType(String type) {
		Set<String> WRAPPER_TYPES = getWrapperTypes();
		return WRAPPER_TYPES.contains(type);
	}
	public static boolean checkPrimitiveType(String type) {
		Set<String> PRIMITIVE_TYPES = getPrimtiveTypes();
		return PRIMITIVE_TYPES.contains(type);
	}
	public static Set<String> getWrapperTypes() {
		Set<String> ret = new HashSet<String>();
		ret.add("Boolean");
		ret.add("Character");
		ret.add("Byte");
		ret.add("Short");
		ret.add("Integer");
		ret.add("Long");
		ret.add("Float");
		ret.add("Double");
		ret.add("Void");
		return ret;
	}
	public static Set<String> getPrimtiveTypes() {
		Set<String> ret = new HashSet<String>();
		ret.add("boolean");
		ret.add("character");
		ret.add("byte");
		ret.add("short");
		ret.add("int");
		ret.add("long");
		ret.add("float");
		ret.add("double");
		return ret;
	}
	/*public static void clearClassIndex(){
		E_Class.setIndex(0);
	}
	
	*/
	public static String createReturnStatement(String retType){
		
	String returnStatement = "";
	
	if(Parser_Utilities.checkPrimitiveType(retType)){
		returnStatement+=" return 0;\n";
	}
	else if(retType.equals("void")){
		returnStatement = "";
	}
	else if(retType.equals("boolean")){
		returnStatement = "true";
	}
	else{
		returnStatement+=" return null;\n";
	}
	return returnStatement;
	
	}
	public static File createPulseFile() throws IOException{
			
		//String folder= workspace.getRoot().getLocation().toFile().getPath().toString();
		
		//LinkedList<String> sipOutput = new LinkedList<String>();
	
		IProject[] projects = Workspace_Utilities.getWorkspaceProjects();
		
		IProject pulseProj = getPulseProject(projects);
		
		IFolder destination = pulseProj.getFolder("src/outputs");
		
		String folder = destination.getLocation().toString();
		
	    //create the pulse output file
		File file = createPulseFileInDir(folder);
		
		return file;
		
		
	}

	public static void writePulseSpecifications(File file, LinkedList<String> sipOutput) throws IOException{
		
		FileWriter pulseFile = null;
		
		try{
			
			pulseFile = new FileWriter(file,true);
			
			//sipOutput = Graph_Utilities.GeneratePulsePermissions();
			for(String pulseInput: sipOutput){
				pulseFile.write(pulseInput);
			} 
			pulseFile.flush();
			
		}catch (IOException e) {
					// TODO Auto-generated catch block
			e.printStackTrace();
					
		}
		pulseFile.close();
	}
	public static IProject getPulseProject(IProject[] projects) {
		//IFolder pulseTest = null;
		IProject pulseProj=null;
		for(IProject proj: projects){
			//pulseTest = proj.getFolder("output");
			if(proj.getName().toString().equals("pulseTests")){
				pulseProj = proj;
			}
		}
		return pulseProj;
	}
			
    public static File createPulseFileInDir(String path){

	
	File file = null;
	
	try {
    		 
		   file = new File(path+"/pulseTest.java");  
	     	   
		   if(Files.deleteIfExists(file.toPath())){
			   System.out.println("existing file is deleted");
		   }
		   if (file.createNewFile()){
		     System.out.println("File is created!");
		    }
	      else{
	         System.out.println("File already exists.");
	      }
      
    	} catch (IOException e) {
	      e.printStackTrace();
	}
	return file;
    }


public static void openPdfFile(){
		
		File file = new File("C:\\Users\\asad22\\AppData\\Local\\Programs\\MiKTeX 2.9\\miktex\\bin/model.pdf");
		if (file.toString().endsWith(".pdf"))
			try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
		    Desktop desktop = Desktop.getDesktop();
		    try {
				desktop.open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }	
	}
	
	public static void createLatex_BatchFile(){
		
		BufferedWriter fileOut;
		
		String latexFileLocation = "C:\\Users\\asad22\\AppData\\Local\\Programs\\MiKTeX 2.9\\miktex\\bin\\";
	    
		   try {
		     fileOut = new BufferedWriter(new FileWriter("C:\\test.bat"));
		     fileOut.write("cd\\"+"\n");
		     fileOut.write("cd "+ latexFileLocation +"\n");
		     fileOut.write("pdflatex.exe "+"\n");
		     //fileOut.write(" exit"+"\n");
		     
		     fileOut.close(); // Close the output stream after all output is done.
		    } catch (IOException e1) {
		     e1.printStackTrace();
		    } 
	        // run a batch file from command line
		     Runtime runtime = Runtime.getRuntime();
		     System.out.println("cmd /c start C:\\test.bat");
		     Process pr = null;
			try {
				pr = runtime.exec("cmd /c start C:\\test.bat");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				//	System.out.println("finished executing command latex");
					BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				//	System.out.println("finished executing command latex2");
					/*
					 String line = "";
						while ((line=buf.readLine())!=null) 
						System.out.println(line);
					*/
					//pr.waitFor();
		    
	}
	
	public static void runPdfLatex_CommandLine(String folder){
	
		BufferedWriter fileOut;
		
		String itsFileLocation = "C:\\Users\\asad22\\AppData\\Local\\Programs\\MiKTeX 2.9\\miktex\\bin\\";
	       try {
		     fileOut = new BufferedWriter(new FileWriter("C:\\test.bat"));
		     fileOut.write("cd\\"+"\n");
		     fileOut.write("cd "+ itsFileLocation +"\n");
		     fileOut.write("pdflatex.exe "+"\n");
		     //fileOut.write(" exit"+"\n");
		     
		     fileOut.close(); // Close the output stream after all output is done.
		    } catch (IOException e1) {
		     e1.printStackTrace();
		    } 
	      try {
		        	
		        //"cmd.exe", "/c", "cd \"C:\\Program Files\\Microsoft SQL Server\" && dir");
	    	  
		        // run pdflatex from command prompt and execute model.tex
	    	    
	        	ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\Users\\asad22\\AppData\\Local\\Programs\\MiKTeX 2.9\\miktex\\bin\" && pdflatex","model.tex", "exit");
	        	builder.redirectErrorStream(true);
	            Process p = builder.start();
	            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            String line;
	            while (true) {
	                line = r.readLine();
	                if (line == null) { break; }
	                System.out.println(line);
	            }
				//Process p = runtime.exec("cmd.exe /c start C:\\test.bat && model.tex");
				
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    
	}
	
	public static void runPdfLatex_BatchFile(){
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace(); 
		
		String folder= workspace.getRoot().getLocation().toFile().getPath().toString();
		
	     try{
		
			  // run a pdf latex from batch file
		    
		    //Runtime runtime = Runtime.getRuntime();
		    //System.out.println("cmd /c start C:\\test.bat  "+folder+"/model.tex");
		    
			//execute the bat file using Process Builder
	    	ArrayList<String> command = new ArrayList<String>();
	    	//command.add("chmod 777 " + folder+"/location.sh");	
	    	command.add(folder+"/test.bat");  // this is exe 
	    	//System.out.println("=======================>");
	    	ProcessBuilder builder = new ProcessBuilder(command);

	    	final Process p = builder.start();
	    	
	    	//System.err.println("Please move the evmdd-smc executeable into folder: "+folder+ " ,if not moved before\n");
	        InputStream is = p.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String line;
	        while ((line = br.readLine()) != null) {
	        	
	          System.out.println(line);
	        }
              p.waitFor();
            
         }
        catch (Exception err) {
         err.printStackTrace();
        }
	}
	
	public static void moveLatexFile(){
		Path temp = null;
		try {
			
			/*File file = new File("C:\\Users\\asad22\\runtime-Pulse_configuration\\model.tex");

            // Destination directory
            File dir = new File("C:\\Users\\asad22\\runtime-Pulse_configuration");

            // Move file to new directory
            boolean success = file.renameTo(new File(dir, file.getName()));
            if (!success) {
                System.out.print("not good");
            }*/
            
			temp = Files.move(Paths.get("C:\\Users\\asad22\\runtime-Pulse_configuration\\model.tex"), 
			
			Paths.get("C:\\Users\\asad22\\AppData\\Local\\Programs\\MiKTeX 2.9\\miktex\\bin\\"),StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	        if(temp != null)
	        {
	            System.out.println("File renamed and moved successfully");
	    }
	    else
	    {
	        System.out.println("Failed to move the file");
	    }
	}

}
