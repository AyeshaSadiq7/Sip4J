package uma.SMC;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import uma.structure.E_Package;
import org.eclipse.core.resources.IFile;

import uma.SMC.E_GrarphWriter;
import uma.SMC.E_OutputLatex;



public class UserSelectedClassesAnalysis {
	
	static Time starttime, endtime;
	BufferedWriter dot;
	static int testType;

   public static void analyzeFromCommandLine(LinkedList<String> inputFiles, String strType,String strK) {
		
	    EVMDD_SMC_Generator.reset();
		testType=Integer.parseInt(strType);
		Boolean JML;
		for (String input:inputFiles){
				JML=false;
			    if (input.contains("//@")==true){
			    	JML=true;
			    	JMLAnnotatedJavaClass JClass=new JMLAnnotatedJavaClass();
			    	input=JClass.translateJMLAnnotationsToPlural(input);
			    }
				ASTParser parser = ASTParser.newParser(3);
				parser.setSource(input.toCharArray());
			    CompilationUnit result = (CompilationUnit) parser.createAST(null);
			    SMC_Visitor visitor = new SMC_Visitor();
			    result.accept(visitor);
			    if (JML==true){
					input=EVMDD_SMC_Generator.modifyConstructorSpecifications(input);
			    	E_GeneratedPluralSpecification.createFromCommandLine(input,"pluralAnnotated");
			    }
			}
	
	E_SMC_Model.setK(Integer.parseInt(strK));
	//E_SMC_Model.generateSMCmodel_Plugin(EVMDD_SMC_Generator.getPkgObject(),testType);
	E_SMC_Model.generateSMCmodel_CommandLine(EVMDD_SMC_Generator.getPkgObject(),testType); // create the model.stm file
	//callModelCheckerThroughPlugin();
	callModelCheckerThroughCommandLine();  // run the model 
		
	}
		
	
   public void analyzeFromPlugin( List<ICompilationUnit> compilationUnitList, int test) {
	
	try {
			testType=test;
			EVMDD_SMC_Generator.reset();
			Boolean JML;
			for (ICompilationUnit cunit : compilationUnitList) {
				JML=false;
				String prog=getInputStream(cunit);
				CompilationUnit cu=null;
				if (prog.contains("//@")==true){
					JML=true;
					JMLAnnotatedJavaClass JClass=new JMLAnnotatedJavaClass();
					prog=JClass.translateJMLAnnotationsToPlural(prog);
					cu=getCompilationUnit(prog);
				}
				else
					cu=getCompilationUnit(cunit);
				
				SMC_Visitor visitor = new SMC_Visitor();
				
				cu.accept(visitor);
				
				if (JML==true){
					prog=EVMDD_SMC_Generator.modifyConstructorSpecifications(prog);
					String className=EVMDD_SMC_Generator.getPkgObject().getClasses().getLast().getName();
					E_GeneratedPluralSpecification.createFromPlugin(prog,className);
				}
			}
			
			E_SMC_Model.generateSMCmodel_Plugin(EVMDD_SMC_Generator.getPkgObject(),test);
			starttime = getTime();	
			//callModelCheckerThroughPlugin(); //uncomment later
			callModelCheckerThroughCommandLine();//uncomment later
			
		} 
	catch (Exception e) {e.printStackTrace();}

}

   private CompilationUnit getCompilationUnit(String prog){
	ASTParser parser = ASTParser.newParser(3);
	parser.setSource(prog.toCharArray());
	CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	return cu;
	
}

   private CompilationUnit getCompilationUnit(ICompilationUnit cunit){
	
	CompilationUnit compilationUnit = (CompilationUnit)WorkspaceUtilities.getASTNodeFromCompilationUnit(cunit);
	return compilationUnit;
	
}
   public String  getInputStream(ICompilationUnit unit)  {
	
	InputStream in=null;
	if (unit != null) {
		try {
			in=((IFile)(unit.getCorrespondingResource())).getContents();
		} 
		catch (CoreException e) {e.printStackTrace();}
		
		    BufferedInputStream bis = new BufferedInputStream(in);
		    ByteArrayOutputStream buf = new ByteArrayOutputStream();
		    int result;
			try {
				result = bis.read();
				 while(result != -1) {
				      byte b = (byte)result;
				      buf.write(b);
				      result = bis.read();
				    }
			}
			catch (IOException e) {e.printStackTrace();}
		           
		    return buf.toString();
		}
	return null;
	
}


    public static void callModelCheckerThroughPlugin(){
	
    try {
    	
    	IWorkspace workspace = ResourcesPlugin.getWorkspace(); 
		String folder= workspace.getRoot().getLocation().toFile().getPath().toString();
		E_OutputLatex.reset();
		
		   //create the shell file
		   FileWriter fshell = new FileWriter(folder+"/location.sh");
		
		   fshell.write(folder+"/evmdd-smc -q"+" < "+folder+"/model.stm");
		
		   fshell.close();
		
		    Process p1 = Runtime.getRuntime().exec("chmod 777 " + "./location.sh"); 
		   /*Process p1 = Runtime.getRuntime().exec(folder+"/location.sh"); 
		    InputStream s = p1.getInputStream();
	        InputStreamReader ss = new InputStreamReader(s);
	        BufferedReader brr = new BufferedReader(ss);
	        String lines;
	        while ((lines = brr.readLine()) != null) {
	        	
	          System.out.println(lines);
	        }*/
		  p1.waitFor();
		
		//execute the shell file
    	ArrayList<String> command = new ArrayList<String>();
    	//command.add("chmod 777 " + folder+"/location.sh");	
    	command.add(folder+"/location.sh");  // this is exe 
    	System.out.println("Checking model.stm ...");
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
         
          E_OutputLatex.setText(line);
          
          if (testType==3 ||testType==0) 
        	  E_GrarphWriter.addTrnsitions(line);
          if (testType==2 ||testType==0)
        	  E_GrarphWriter.parseMethodReachability(line);
        }
        p.waitFor();
       
        if (testType==3 ||testType==0)
        {
        	E_GrarphWriter.createGraph();
        	System.out.println("State Transition Graphs are created in folder "+folder);
        	printMetrics();
        	
        }
        
        if (testType==2 ||testType==0){
        	E_GrarphWriter.createGraph();
        	printMethodMetrics();
        }
        
        endtime=getTime();
      
        System.out.println("Model Checked");
		//System.out.println("<=======================");
        CreatePdfSummary_Plugin(starttime, endtime);
        
        System.out.println("Start Time: "+starttime.toString());
		System.out.println("End Time: "+endtime.toString());
		System.out.println("Elapsed Time in milli seconds: "+(endtime.msecond-starttime.msecond));
      
    }
    catch (Exception err) {
      err.printStackTrace();
    }
  }

	private static void CreatePdfSummary_Plugin(Time starttime2, Time endtime2) throws IOException {
	
		E_OutputLatex.create_Plugin();
		try {
			makePdf_Plugin();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}

	private static void CreatePdfSummary_CommandLine(Time starttime2, Time endtime2) throws IOException {
		E_OutputLatex.create_CommandLine();
		makePdf_CommandLine();
		
	}
    private static void makePdf_CommandLine() throws IOException {
		
         File workingDirectory = new File (".");
		
		System.out.println("model.pdf generated in the directory "+workingDirectory.getAbsolutePath());
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace(); 
		
		String folder= workspace.getRoot().getLocation().toFile().getPath().toString();
		
		
		//System.out.println("method makePdf_CommandLine()");
    	
		//String cmd = "/usr/local/texlive/2017basic/bin/x86_64-darwin/pdflatex " + folder+"/annotatedmodel.tex";
		
		String cmd = "/usr/local/texlive/2015/bin/x86_64-darwin/pdflatex " + folder+"/model.tex";
		
		//String cmd = "/usr/local/texlive/2017basic/bin/x86_64-darwin/pdflatex "+folder+"/model.tex";
		try {
				Runtime run = Runtime.getRuntime();
			//	System.out.println(cmd);
				Process pr = run.exec(cmd);
			//	System.out.println("finished executing command latex");
				BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			//	System.out.println("finished executing command latex2");
				
				 String line = "";
					while ((line=buf.readLine())!=null) 
					System.out.println(line);
				
				pr.waitFor();
				
				run.exec("open -a Preview "+workingDirectory.getAbsolutePath()+"/model.pdf");
				run.exec("open -a Preview "+folder+"/model.pdf");
				
				//run.exec("open -a Preview "+workingDirectory.getAbsolutePath()+"/model.pdf");
			}
		catch (InterruptedException e) {
			//System.out.println("Error in executing command pdflatex");
			e.printStackTrace();
		}
		//System.out.println(" end of method makePdf_CommandLine()");
	
	}

	private static void makePdf_Plugin() throws IOException, InterruptedException {
	
	   
		File workingDirectory = new File (".");
		
		System.out.println("model.pdf generated in the directory "+workingDirectory.getAbsolutePath());
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace(); 
		
		String folder= workspace.getRoot().getLocation().toFile().getPath().toString();
		
		String cmd = "/usr/local/texlive/2015/bin/x86_64-darwin/pdflatex " + folder+"/model.tex";
		
		//String cmd = "C:\\Users\\asad22\\AppData\\Local\\Programs\\MiKTeX 2.9\\miktex\\bin\\pdflatex "+folder+"/model.tex";
		
		try{
			
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(cmd);
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			 String line = "";
				while ((line=buf.readLine())!=null) 
				System.out.println(line);
			pr.waitFor();
			
			run.exec("open -a Preview "+workingDirectory.getAbsolutePath()+"/model.pdf");
			
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
			
		}
		
		//added by ayeshaf
		
		//runPdfLatex();
		//createLatex_BatchFile();
		//createLatex_BatchFile();
		//moveLatexFile();
		//runPdfLatex_CommandLine(folder);
		//runPdfLatex_BatchFile();
		//openPdfFile();
	}
	
	public static void callModelCheckerThroughCommandLine(){

		IWorkspace workspace = ResourcesPlugin.getWorkspace(); 
		
		String folder= workspace.getRoot().getLocation().toFile().getPath().toString();
	
		    try {
		    	E_OutputLatex.reset();
		    	FileWriter fshell = new FileWriter(folder+"/location.sh");
				fshell.write(folder+"/evmdd-smc -q <  "+folder+"/model.stm");
				fshell.close();
				Process p1 = Runtime.getRuntime().exec("chmod 777 "+folder+"/location.sh" ); 
				p1.waitFor();
				
				//execute the shell file
		    	ArrayList<String> command = new ArrayList<String>();
		    	//command.add("chmod 777 " + folder+"/location.sh");
		    	//command.add("/Users/ijazahmed/Sites/location.sh");  // this is exe 
		    	command.add(folder+"/location.sh"); 
		    	ProcessBuilder builder = new ProcessBuilder(command);
		    	final Process p = builder.start();
		    	
		    //	System.err.println("Please move the evmdd-smc executeable into folder: "+folder+ " ,if not moved before\n");
		        InputStream is = p.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
		        String line;
		        while ((line = br.readLine()) != null) {
		        	System.out.println(line);
		        	E_OutputLatex.setText(line);
		        	
		          if (testType==3 ||testType==0) 
		        	  E_GrarphWriter.addTrnsitions(line);
		          if (testType==2 ||testType==0)
		        	  E_GrarphWriter.parseMethodReachability(line);
		        }
		        p.waitFor();
		       
		       
		        if (testType==3 ||testType==0)
		        {
		        	E_GrarphWriter.createGraph();
		        	//System.out.println("State Transition Graphs are created in folder "+folder);
		        	printMetrics();
		        	
		        }
		        
		        if (testType==2 ||testType==0)
		        {
		        	E_GrarphWriter.createGraph();
		        	printMethodMetrics();
		        	
		        }
		        
		        endtime=getTime();
		      //  System.out.println("End of callModelCheckerThroughCommandLine()");
		        CreatePdfSummary_CommandLine(starttime, endtime);   
		    }
		    catch (Exception err) {
		      err.printStackTrace();
		    }
	  }
	
	private static void printMethodMetrics() {
		
		//System.out.println("The total number of unreachable methods are :"+E_GrarphWriter.getNumberofUnReachableMethods());
		E_GrarphWriter.setNumberofUnReachableMethods();
		
	}

	private static void printMetrics() {
		E_Package _pkg=EVMDD_SMC_Generator.getPkgObject();
    	int totalstates=_pkg.getTotalStates();
    	int reachablesates=_pkg.getTotalReachableStates(); // added as the the initial states is usually unreachable in our model 
    	int unreachblestates=totalstates-reachablesates;
    	System.out.println("The total number of states are 			 :"+totalstates);
    	System.out.println("The total number of reachable states are :"+reachablesates);
    	System.out.println("The total number of unreachable states are :"+unreachblestates);
	}

	public static void callExe(){
		
	    try {
	    	ArrayList<String> command = new ArrayList<String>();
	    	command.add("java/Users/ijazahmed/Documents/workspace/EVMDD_SMC_ModelGenerator/bin/pt/uma/EVMDD_SMC/abc.class");  // this is exe 
	    	//command.add("/Users/ijazahmed/model.stm"); // this is input to that exe
	    	ProcessBuilder builder = new ProcessBuilder(command);
	    	final Process p = builder.start();
	    	p.waitFor();
	    	
	    	InputStream is = p.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String line;
	        
	        while ((line = br.readLine()) != null) {
	          System.out.println(line);
	        }
	    	
	    }
	    catch (Exception err) {
	      err.printStackTrace();
	    }
	  }
	
	public static Time getTime() {
			
			Time t= new Time();
			Calendar calendar = new GregorianCalendar();
			t.hour = calendar.get(Calendar.HOUR);
			t.minute =calendar.get(Calendar.MINUTE);
			t.second =calendar.get(Calendar.SECOND);
			t.msecond=calendar.getTimeInMillis();	
			return t;
			
		}
}




