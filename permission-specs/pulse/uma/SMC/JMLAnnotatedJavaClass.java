package uma.SMC;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;

import uma.SMC.PluralLexer;
import uma.SMC.PluralParser;

public class JMLAnnotatedJavaClass {

public String translateJMLAnnotationsToPlural(String JProgram) {
		
	//@ ghost int APDU;
	 JProgram=translateClassSpecifications(JProgram);
	 JProgram=translateMethodSpecification(JProgram);
	 return JProgram;
	 

}

private String translateMethodSpecification(String JProgram) {
	int start=0;
	 while (start!=-1){
		 start=JProgram.indexOf("/*@",start); 
		 if (start!=-1){
			 int end=JProgram.indexOf("*/", start);
			 if (end!=-1){
				 String JMLAnnotation=JProgram.substring(start,end+2);
				 E_JmlSpecification.reset();
				 System.out.println("[");
				 System.out.println(JMLAnnotation);
				 parseAndStoreJMLAnnotation(JMLAnnotation);
				 String PluralAnnotation=E_JmlSpecification.JmlMethodSpec2PluralMethodSpec();
				 System.out.println(PluralAnnotation);
				 System.out.println("]");
				 JProgram=JProgram.substring(0,start)+PluralAnnotation+JProgram.substring(end+2,JProgram.length());
			 }
				 start=end;
			
		 }
	 }
	 
	 return JProgram;
}

private String translateClassSpecifications(String JProgram) {
	
	int startGhost=JProgram.indexOf ("ghost"); 
	 int endGhost=JProgram.indexOf (";", startGhost);
	 String ghostDeclaration=JProgram.substring(startGhost,endGhost+1);
	 JProgram=JProgram.substring(0,startGhost)+JProgram.substring(endGhost+1,JProgram.length());
	 int startInv=JProgram.indexOf ("invariant");
	 int endInv=JProgram.indexOf (";", startInv);
	 String ghostInv=JProgram.substring(startInv,endInv+1);
	 JProgram=JProgram.substring(0,startInv)+JProgram.substring(endInv+1,JProgram.length());

	 JProgram=JProgram.replaceFirst("//@ ","");
	 JProgram=JProgram.replaceFirst("//@ ","");
	 System.out.println("[");
	 System.out.println(ghostDeclaration);
	 System.out.println(ghostInv);
	 parseAndStoreJMLAnnotation(ghostDeclaration);
	 parseAndStoreJMLAnnotation(ghostInv);
	 String Plural=E_JmlSpecification.JmlClassSpec2PluralClassSpec();
	 System.out.println(Plural);
	 System.out.println("]");
	 
	 
	 int startPublic=JProgram.indexOf("public");
	 JProgram=JProgram.substring(0,startPublic)+Plural+JProgram.substring(startPublic,JProgram.length());
	 return JProgram;
}

private void parseAndStoreJMLAnnotation(String JMLAnnotation) {
	
	CharStream input= new ANTLRStringStream(JMLAnnotation);
	PluralLexer lex= new PluralLexer(input);
		
	TokenStream token= new CommonTokenStream(lex);
	PluralParser parser= new PluralParser(token);
	try {
		parser.jmlSpecifications();
	
	}
	catch (RecognitionException e) {e.printStackTrace();}
}

public String  getInputStream(ICompilationUnit unit)  {
	
	InputStream in=null;
	if (unit != null) {
		try {
			in=((IFile)(unit.getCorrespondingResource())).getContents();
		} catch (CoreException e) {
			// TODO: should throw CoreException
			e.printStackTrace();
		}
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
			catch (IOException e) {
				
				e.printStackTrace();
			}
		           
		    return buf.toString();
		}
	return null;
	
}

public  String readFileAsString(String filePath) {
	
	byte[] buffer = new byte[(int) new File(filePath).length()];
	BufferedInputStream f = null; 
	try { 
		try {
			f = new BufferedInputStream(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			f.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		} 
	finally { 
			if (f != null) 
			try { f.close(); 
			} 
			catch (IOException ignored) {
				
			} 
		}
	return new String(buffer); 
}

 
}
