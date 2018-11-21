package main;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;

import parser.UserSelectedClasses_Analysis;
import parser.Workspace_Utilities;
import plugin.Sip4JIFileAction;

import uma.SMC.UserSelectedClassesAnalysis;

public class Main  {

	static LinkedList<String> inputFiles;
	
public static void main(String[] args) {
	
		int num = args.length;
	    
		//inputFiles= new LinkedList<String>();
	    
		//inputFiles.add(testRead("D:\\PhD-Folder-August-2012\\PulseWebSite\\target.java"));
	    
		//UserSelectedClassesAnalysis.analyzeFromCommandLine(inputFiles, "0","2");
		
		//if (num>=2){
			//seprateJavaFile(args[0]);
			//UserSelectedClassesAnalysis.analyzeFromCommandLine(inputFiles, args[1],args[2]); 
		//}
		 
		/*Graph_Construction obj = new Graph_Construction();
		
		 obj.createGraph();*/ 
		
		
		//Sip4JIFileAction.run();
		
	}
private static void seprateJavaFile(String str) {
		boolean flag=false;
		do {
			if (str.lastIndexOf("ENDOFCLASS")>0){
				int index=str.indexOf("ENDOFCLASS");
				inputFiles.add(str.substring(0,index));
				str=str.substring(index+10,str.length());
				flag=true;
			}
			else{
				str=str.trim();
				if (str.isEmpty()==false)
					inputFiles.add(str);
				flag=false;
			}
		}
		while(flag);
}


private static String testRead(String file)
{
	String contents="";
	try{
		FileInputStream fstream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		
		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
			contents=contents+strLine;
		}
		
		in.close();
	  }
	catch (Exception e){System.err.println("Error: " + e.getMessage());}
	
	return contents;
}

private static void anTest(){
	
	String str= "requires_clause_of_APDU_setIncomingAndReceive_case1_0_0:";
			Boolean bRequires=true;
			int j=str.indexOf("_of_")+4;
			str=str.substring(j);
			j=str.indexOf("_");
			String className=str.substring(0,j);
			str=str.substring(j+1);
			//j=str.indexOf("_");
			
			int i=str.indexOf(":");
			String methodName=str.substring(0,i-4);
			
			String reachability=str.substring(i+1);
			reachability=reachability.trim();
			
}


}
