package uma.SMC;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

public class E_GeneratedPluralSpecification {

	static BufferedWriter plural;
	
	public static void createFromPlugin(String prog, String className) {
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace(); 
	
		String folder= workspace.getRoot().getLocation().toFile().getPath().toString();
		
		System.out.println("A plural annotated class created in the directory "+folder);
		
		FileWriter fstream;
		try {
			fstream = new FileWriter(folder+"/"+className+".java");
			plural = new BufferedWriter(fstream);
			plural.write(prog);
			plural.flush();
			plural.close();
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void createFromCommandLine(String prog, String className) {
	
		FileWriter fstream;
		try {
			fstream = new FileWriter("target.java");
			plural = new BufferedWriter(fstream);
			plural.write(prog);
			plural.flush();
			plural.close();
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	

}
