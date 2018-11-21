package uma.SMC;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

import uma.structure.EClass;
import uma.structure.EPackage;
import uma.structure.EState;

public class EGrarphWriter {
	static int unreachableMethod=0;

	public static void createGraph(){

		try{
			BufferedWriter dot;

			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			
			String folder= workspace.getRoot().getLocation().toFile().getPath().toString();

			EPackage _pkg=EVMDDSMCGenerator.getPkgObject();
			
			LinkedList<EClass> _listClasses=_pkg.getClasses();
			
			for (EClass _class:_listClasses)
			{

					FileWriter fstream;
					
					fstream = new FileWriter(folder+"/"+_class.getName()+".dot");
					
					dot = new BufferedWriter(fstream);
					
					dot.write("digraph stateTransitions { "+
							_class.getName()+"[shape=plaintext,style=bold, fontsize=14];");

					for (EState state:_class.getStates())
						dot.write(state.getName()+"[shape=box,fontsize=12];\n");
					for (String transition:_class.getTransitions())
						dot.write(transition+"\n");
					dot.write("}");
			 	dot.flush();
			}
		}

		catch (IOException e) {
				e.printStackTrace();
		}
	}


public static void addTrnsitions(String str){

		EPackage _pkg = EVMDDSMCGenerator.getPkgObject();

		if (str.contains("stateTransition")){
			int i=str.indexOf("of_");
			int j=str.indexOf("_from");
			int k=str.indexOf(":");
			String className=str.substring(i+3,j);
			String _from_to=str.substring(j+6,k);
			_from_to=_from_to.replace("_to_", "->");

			String transition=str.substring(k+1);

			transition=transition.trim();

			if (transition.substring(0,1).compareTo("0")!=0){

				 LinkedList<EClass> _listClasses = _pkg.getClasses();

					for (EClass _class:_listClasses){
						if (_class.getName().compareTo(className)==0)
						{
				 			System.out.println(_from_to);
				 			_class.getTransitions().add(_from_to);

			 				int index=_from_to.indexOf("->");
				 			String _to=_from_to.substring(index+2);

				 			for (EState _state :_class.getReachableStates()){
				 				if (_state.isReachable()==0)
				 				{
					 				if (_state.getName().compareTo(_to)==0){
					 					_state.setReachability(1);
					 					break;
					 				}
				 				}

				 			}
				 			break;
						}

					}
			 }

		}

	}

public static void parseMethodReachability(String str) {

	//Number of states satisfying property methodReachability_of_MttsCollection_MttsCollection_0: 244,798,470 (2x10^8)

	if (str.contains("requires_clause"))
	{
		int i=str.indexOf(":");
		String reachability=str.substring(i+1);
		reachability=reachability.trim();

		if (reachability.substring(0,1).compareTo("0")==0){
			unreachableMethod++;

		 }

	}
}
public static int getNumberofUnReachableMethods(){
		return unreachableMethod;
	}

public static void setNumberofUnReachableMethods(){
	unreachableMethod=0;
}
}
