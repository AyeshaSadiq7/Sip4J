package graphutilities;

import graphconstruction.LabeledEdge;
import graphstructure.E_ClassGraphs;
import graphstructure.E_MVertice;
import graphstructure.E_MethodGraph;
import graphstructure.E_PackGraphs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.jgrapht.DirectedGraph;

import parser.GlobalVariables;
import parser.Parser_Utilities;

import datastructure.E_MRefField;

public class Graph_Utilities {

	public static boolean ifReadEdgeExist(E_MethodGraph graph, String rf,
			String method) {

		boolean flag = false;

		/*
		 * if(graph.getMethodgraph().containsVertex(rf)){
		 * if(graph.getMethodgraph().containsEdge(rf, method)){ flag = true; }
		 * 
		 * }
		 */

		return flag;

	}

	public static boolean ifWriteEdgeExist(E_MethodGraph graph, String rf,
			String method) {

		boolean flag = false;

		/*
		 * if(graph.getMethodgraph().containsVertex(rf)){
		 * if(graph.getMethodgraph().containsEdge(method,rf)){ flag = true; }
		 * 
		 * }
		 */
		return flag;

	}

	public static boolean checkVertex(
			DirectedGraph<HashMap<String, String>, LabeledEdge> gg,
			E_MRefField var) {
		boolean flag = false;
		Iterator<HashMap<String, String>> it = gg.vertexSet().iterator();
		if (it != null) {
			while (it.hasNext()) {
				HashMap<String, String> gv = it.next();
				/*
				 * if(gv.get(v).equals(var.getName()){
				 * if(var.getDeclaringClass()
				 * Graph_Utilities.equals(gv.getDeclaringClass()))) return true;
				 * }
				 */
			}
		}
		return flag;
	}

	public static void displayPermissions() {

		// System.out.println("Hello");

		E_PackGraphs pkg = Graph_Generator.getPackage();

		LinkedList<E_ClassGraphs> _class = pkg.getClasses();

		for (E_ClassGraphs c : _class) {

			System.out.println("Class Name = " + c.getClassGraphName());

			LinkedList<E_MethodGraph> mgraphs = c.getMethodgraphs();

			for (E_MethodGraph graph : mgraphs) {

				System.out.println("Method Name = " + graph.getMgraphName());

				LinkedList<E_MVertice> vertexes = graph.getVertices();

				for (E_MVertice v : vertexes) {
					if (v.getVName().equals("foo")
							|| v.getVName().equals("context")) {

					} else {

						// System.out.println("Ref-Var= "+v.getQualifiedName()+"."+v.getVName()+", Pre-Permissions="+v.getPre_permissions()+", Post Permissions="+v.getPost_permissions());
						System.out.println("Ref-Var= " + v.getVName()
								+ ", Pre-Permissions=" + v.getPre_permissions()
								+ ", Post Permissions="
								+ v.getPost_permissions());

					}
				}
			}
		}
	}

	public static void generatePlaidPermissions() {

		E_PackGraphs pkg = Graph_Generator.getPackage();

		LinkedList<E_ClassGraphs> _class = pkg.getClasses();

		for (E_ClassGraphs c : _class) {

			System.out.println("Class Name = " + c.getClassGraphName());

			LinkedList<E_MethodGraph> mgraphs = c.getMethodgraphs();

			for (E_MethodGraph graph : mgraphs) {

				System.out.println("Method Name = " + graph.getMgraphName());

				LinkedList<E_MVertice> vertexes = graph.getVertices();

				for (E_MVertice v : vertexes) {
					if (v.getVName().equals("foo")
							|| v.getVName().equals("context")) {

					} else {
						// System.out.println(v.getPre_permissions()+" "+v.getVType()+" "+v.getQualifiedName()+"."+v.getVName()+" >> "+v.getPost_permissions()+" "+v.getVType()+" "+
						// v.getQualifiedName()+"."+v.getVName());
						System.out.println(v.getPre_permissions() + " "
								+ v.getVType() + " " + v.getVName() + " >> "
								+ v.getPost_permissions() + " " + v.getVType()
								+ " " + v.getVName());

					}
				}
			}
		}
	}

	
	public static LinkedList<String> generatePulsePermissions(int classCounter) {

		E_PackGraphs pkg = Graph_Generator.getPackage();
		LinkedList<E_ClassGraphs> _class = pkg.getClasses();
		LinkedList<String> output = new LinkedList<String>();
		
		String classContracts = "";
		String pulseHeader = "";
		String classStatesHeader = "";
		
		pulseHeader +="package outputs;\n";
		
		pulseHeader += "import edu.cmu.cs.plural.annot.*;" + "\n\n";
		
	   classStatesHeader = "@ClassStates({@State(name = " + "\"Alive\""
				+ ")})";
	    
	   //int classCounter = 0;
		
	    for (E_ClassGraphs c : _class) {
			if(classCounter == 0){
			  classContracts += pulseHeader+classStatesHeader+ "\n\n" + c.getClassSignatures()+ "\n";
			}
			else{
				classContracts += "\n\n" + c.getClassSignatures()+ "\n";
			}			
			System.out.println("@ClassStates({@State(name = alive)})");
			System.out.println();
			System.out.println(c.getClassSignatures());
			
			String constrContract = "@Perm(ensures=" + "\""
					+ "unique(this) in alive" + "\"" + ")";

			//classContracts += constrContract + "\n";

			System.out.println("@Perm(ensures=" + "\""
					+ "unique(this) in alive" + "\"" + ")");
			System.out.println();
			System.out.println(c.getClassGraphName() + "() {   }");

			String const_method = c.getClassGraphName() + "() {   }";

			classContracts += const_method + "\n\n";

			System.out.println();

			LinkedList<E_MethodGraph> mgraphs = c.getMethodgraphs();
			String methodcontracts = "";
			for (E_MethodGraph graph : mgraphs) {
				/*if(graph.getMgraphName().equals(c.getClassGraphName())){
					continue;
				}
				else{*/     
				LinkedList<E_MVertice> vertexes = graph.getVertices();
				if (vertexes != null) {
					Iterator<E_MVertice> it = vertexes.iterator();
					if (it != null) {
						String prePermissionSet = "";
						String postPermissionSet = "";
						String returnStatement = "";
						int i = 0;
						
					while (it.hasNext()) {
							E_MVertice v = it.next();
							if (v.getVName().equals("foo")
									|| v.getVName().equals("context")) {

							}else {
								 String prePerm ="";
								 String asterick = "";
								 String postPerm ="";
								 
								// v.getPre_permissions()+"(#"+i+")";
								// String postPerm =
								// v.getPost_permissions()+"(#"+i+")";
								// v.getPre_permissions()+"("+v.getQualifiedName()+"."+v.getVName()+")";
								// String postPerm =
								// v.getPost_permissions()+"("+v.getQualifiedName()+"."+v.getVName()+")";
								
								if(v.isParam()){
									/*prePerm = v.getPre_permissions() + "("
									+"#"+i+")";
									postPerm = v.getPost_permissions() + "("
									+ "#"+i+")";*/
									prePerm = v.getPre_permissions() + "("
									+v.getVName()+")";
									postPerm = v.getPost_permissions() + "("
									+ "#"+i+")";
								}
								else{
									prePerm = v.getPre_permissions() + "("
										+ v.getVName() + ")";
								    postPerm = v.getPost_permissions() + "("
								    		+v.getVName()+")";
								}
								 if (i > 0 ) {
										//i = i-1;
										asterick = " * ";
								 }
								 prePermissionSet = prePermissionSet + asterick
										+ prePerm;
								 postPermissionSet = postPermissionSet
										+ asterick + postPerm;
								 
				    		i++;
			     		}

						}

						String pattern = "@Perm(requires=" + "\""
								+ prePermissionSet + " in alive" + "\""
								+ ", \n ensures= " + "\"" + postPermissionSet
								+ " in Alive" + "\"" + ")";
						System.out.println("" + pattern);
						System.out.println();
						System.out.println(graph.getMethodSignatures() + "{ }");
						returnStatement+= Parser_Utilities.createReturnStatement(graph.getMRetType());
						System.out.println();
						methodcontracts += pattern + "\n"
								+ graph.getMethodSignatures() + " { \n"+returnStatement+"} "
								+ "\n";
					}
				//}

			 }

			}
			System.out.println("}");
			System.out.println("ENDOFCLASS");
			String classend = "";
			classend += "}"; 
			//+ "ENDOFCLASS\n\n";
		    classContracts += methodcontracts + "\n" + classend;
			output.add(classContracts);
		
		}
		
		return output;
	}

	
	/*public static LinkedList<String> generatePulsePermissions(int classCounter) {

		E_PackGraphs pkg = Graph_Generator.getPackage();
		LinkedList<E_ClassGraphs> _class = pkg.getClasses();
		LinkedList<String> output = new LinkedList<String>();
		
		String classContracts = "";
		String pulseHeader = "";
		String classStatesHeader = "";
		
		pulseHeader +="package outputs;\n";
		
		pulseHeader += "import edu.cmu.cs.plural.annot.*;" + "\n\n";
		
	   classStatesHeader = "@ClassStates({@State(name = " + "\"Alive\""
				+ ")})";
	    
	   //int classCounter = 0;
		
	    for (E_ClassGraphs c : _class) {
			if(classCounter == 0){
			  classContracts += pulseHeader+classStatesHeader+ "\n\n" + c.getClassSignatures()+ "\n";
			}
			else{
				classContracts += "\n\n" + c.getClassSignatures()+ "\n";
			}			
			System.out.println("@ClassStates({@State(name = alive)})");
			System.out.println();
			System.out.println(c.getClassSignatures());
			
			String constrContract = "@Perm(ensures=" + "\""
					+ "unique(this) in alive" + "\"" + ")";

			//classContracts += constrContract + "\n";

			System.out.println("@Perm(ensures=" + "\""
					+ "unique(this) in alive" + "\"" + ")");
			System.out.println();
			System.out.println(c.getClassGraphName() + "() {   }");

			String const_method = c.getClassGraphName() + "() {   }";

			//classContracts += const_method + "\n\n";

			System.out.println();

			LinkedList<E_MethodGraph> mgraphs = c.getMethodgraphs();
			String methodcontracts = "";
			for (E_MethodGraph graph : mgraphs) {
				if(graph.getMgraphName().equals(c.getClassGraphName())){
					continue;
				}
				else{     
				LinkedList<E_MVertice> vertexes = graph.getVertices();
				if (vertexes != null) {
					Iterator<E_MVertice> it = vertexes.iterator();
					if (it != null) {
						String prePermissionSet = "";
						String postPermissionSet = "";
						String returnStatement = "";
						int i = 0;
						
					while (it.hasNext()) {
							E_MVertice v = it.next();
							if (v.getVName().equals("foo")
									|| v.getVName().equals("context")) {

							}else {
								 String prePerm ="";
								 String asterick = "";
								 String postPerm ="";
								 
								// v.getPre_permissions()+"(#"+i+")";
								// String postPerm =
								// v.getPost_permissions()+"(#"+i+")";
								// v.getPre_permissions()+"("+v.getQualifiedName()+"."+v.getVName()+")";
								// String postPerm =
								// v.getPost_permissions()+"("+v.getQualifiedName()+"."+v.getVName()+")";
								
								if(v.isParam()){
									prePerm = v.getPre_permissions() + "("+v.getVName().toString()+")";
											//"("+"#"+i+")";
									postPerm = v.getPost_permissions() +  "("+v.getVName().toString()+")";
									//"("+ "#"+i+")";
								}
								else{
									
									prePerm = v.getPre_permissions();
									
									if(prePerm.equals("") || prePerm.equals("none")){
										prePerm = "";
									}else{
										prePerm = v.getPre_permissions() + "("
											+ v.getVName() + ")";
									}
								    postPerm = v.getPost_permissions() + "("
										+ v.getVName() + ")";
								}
								 if (i > 0 ) {
										//i = i-1;
										asterick = "*";
								 }
								 if(prePerm.equals("") && i > 0){
									 
								 }
								 else{
									 prePermissionSet = prePermissionSet + asterick
										+ prePerm;
								 }
								 postPermissionSet = postPermissionSet
										+ asterick + postPerm;
								 
				    		i++;
				    		
			     		   }

						}
						
							String pattern = generatePattern(prePermissionSet,postPermissionSet);
					
					 
						String pattern = "@Perm(requires=" + "\""
								+ prePermissionSet + " in alive" + "\""
								+ ", \n ensures= " + "\"" + postPermissionSet
								+ " in alive" + "\"" + ")";
						System.out.println("" + pattern);
						System.out.println();
						System.out.println(graph.getMethodSignatures() + "{ }");
						returnStatement+= Parser_Utilities.createReturnStatement(graph.getMRetType());
						System.out.println();
						methodcontracts += pattern + "\n"
								+ graph.getMethodSignatures() + " { \n"+returnStatement+"} "
								+ "\n";
					}
				//}

			 }

			}
			System.out.println("}");
			System.out.println("ENDOFCLASS");
			String classend = "";
			classend += "}"; 
			//+ "ENDOFCLASS\n\n";
		    classContracts += methodcontracts + "\n" + classend;
			output.add(classContracts);
		
		}
		
		return output;
	}
*/
	public static LinkedList<String> generateObjectPermissions(int classCounter, int methodcount) {

		E_PackGraphs pkg = Graph_Generator.getPackage();
		
		LinkedList<E_ClassGraphs> _class = pkg.getClasses();
		
		LinkedList<String> output = new LinkedList<String>();
		
		String classContracts = "";
		String pulseHeader = "";
		String classStatesHeader = "";
		
		pulseHeader +="package outputs;\n";
		
		pulseHeader += "import edu.cmu.cs.plural.annot.*;" + "\n\n";
		
	   classStatesHeader = "@ClassStates({@State(name = " + "\"alive\""
				+ ")})";
	    
	   //int classCounter = 0;
		//int methodcount = 0;
	    for (E_ClassGraphs c : _class) {
			if(classCounter == 0){
			  classContracts += pulseHeader+classStatesHeader+ "\n" + c.getClassSignatures()+ "\n";
			}
			else{
				classContracts += classStatesHeader+ "\n\n" + c.getClassSignatures()+ "\n";
			}
	    	System.out.println("@ClassStates({@State(name = alive)})");
			System.out.println();
			System.out.println(c.getClassSignatures());
			
			String constrContract = "@Perm(ensures=" + "\""
					+ "unique(this) in alive" + "\"" + ")";

			classContracts += constrContract + "\n";

			System.out.println("@Perm(ensures=" + "\""
					+ "unique(this) in alive" + "\"" + ")");
			System.out.println();
			
			System.out.println(c.getClassGraphName() + "() {   }");

			String const_method = c.getClassGraphName() + "() {   }";

			classContracts += const_method + "\n\n";

			System.out.println();

			LinkedList<E_MethodGraph> mgraphs = c.getMethodgraphs();
			
			String methodcontracts = "";
			
			for (E_MethodGraph graph : mgraphs) {
				
				methodcount++;
				
				if(graph.getMgraphName().equals(c.getClassGraphName())){
					continue;
				}
				else{     
					
					 LinkedList<E_MVertice> vertexes = graph.getVertices();
					 
					 if (vertexes != null) {
					   
						String prePermissionSet = "";
						String postPermissionSet = "";
						String returnStatement = "";
						int i = 0;
						int preMax;
						int postMax;
						String prePerm ="";
						String mainasteric=" * ";
						String postPerm ="*";
						 
						preMax =  generateObjectPrePermission(vertexes);
						
						prePerm = setObjectPermission(preMax);
						
						postMax = generateObjectPostPermissions(vertexes);
						
						postPerm = setObjectPermission(postMax);
							
						//System.out.println("The max permission for object is = "+preMax);
						
						//System.out.println("The max permission for object is = "+prePerm);
						
						if(!prePerm.equals("")){
							prePermissionSet = prePerm + "("
									+ "this"+") in alive";
						}
						if(!postPerm.equals("")){
						   postPermissionSet = postPerm + "("
								+ "this"+") in alive";
						}
						String preParam = "";  
					    String postParam = "";  
						String paramPre="";
						String paramPost="";
						String asterick = "";
						
					int allowed = countMParameters(graph);
					
					Iterator<E_MVertice> it = vertexes.iterator();
					  
				if (it != null) {
					if(i < allowed){	
					  while (it.hasNext()) {
						   E_MVertice v = it.next();
							if (v.getVName().equals("foo")
									|| v.getVName().equals("context")) {

							}
							else{
								if(v.isParam()){
									if(!v.getPre_permissions().equals("")){
											paramPre = v.getPre_permissions() + "("+"#"+i+") in alive";
									}
									if(!v.getPost_permissions().equals("")){
											paramPost = v.getPost_permissions() + "(" + "#"+i+") in alive";
									}
							
									if (i > 0 ) {
									  asterick = " * ";
						    	    }
									preParam = preParam+asterick+paramPre;
									postParam = postParam+asterick+paramPost; 
									i++;
							}
						  }// else ends here
					    }// while ends here
					}
					   
					   if(!prePermissionSet.equals("") && !preParam.equals("")){
					      prePermissionSet = prePermissionSet+mainasteric+preParam;
					   }
					   else if(prePermissionSet.equals("") && !preParam.equals("")){
						   prePermissionSet = preParam;
					   }
					   else if(preParam.equals("") && !prePermissionSet.equals("")){
						      prePermissionSet = prePermissionSet;
					   }
					   if(!postPermissionSet.equals("") && !postParam.equals("")){
						   postPermissionSet = postPermissionSet+mainasteric+postParam;
						   }
						   else if(postPermissionSet.equals("") && !postParam.equals("")){
							   postPermissionSet = postParam;
						   }
						   else if(postParam.equals("") && !prePermissionSet.equals("")){
							   postPermissionSet = postPermissionSet;
						  }
					   
					   //postPermissionSet = postPermissionSet+mainasteric+postParam;
					  }
					 
					String pattern = generatePattern(prePermissionSet,postPermissionSet);
							
					/*String pattern = "@Perm(requires=" + "\""
							+ prePermissionSet + " in Alive" + "\""
							+ ", \n ensures= " + "\"" + postPermissionSet
							+ " in Alive" + "\"" + ")";*/
					System.out.println("" + pattern);
					System.out.println();
					System.out.println(graph.getMethodSignatures() + "{ }");
					returnStatement += Parser_Utilities.createReturnStatement(graph.getMRetType());
					System.out.println();
					//if(!pattern.equals("")){
						methodcontracts += pattern + "\n"
							+ graph.getMethodSignatures() + " { \n"+returnStatement+"} "
							+ "\n";
					//}
				}
					 
			 }

			}
			System.out.println("}");
			System.out.println("ENDOFCLASS");
			String classend = "";
			classend += "}" + "ENDOFCLASS\n\n";
		    classContracts += methodcontracts + "\n" + classend;
			output.add(classContracts);
			System.out.println("total methods"+methodcount);
		}
		
		return output;
	}
public static String generatePattern(String prePermissionSet,String postPermissionSet){
	
		String requireClause="";
		String ensuresClause="";
		String pattern;
	
		if(prePermissionSet.equals("")||prePermissionSet.equals(null)||prePermissionSet.equals("*")){
			requireClause = "";
		}
		else{
		 requireClause = "requires=" + "\""
					+prePermissionSet+"\""+",\n";
		}
		if(postPermissionSet.equals("")||postPermissionSet.equals(null)){
			ensuresClause="";
		} 
		else{
		  ensuresClause = "ensures="+ "\"" +postPermissionSet+"\"";
		}
		
		if(!requireClause.equals("") ||!ensuresClause.equals("")){
			pattern = "@Perm("+requireClause+ensuresClause+")";	
		}
		else 
			pattern = "";
		
	return pattern;
	}
	

private static String setObjectPermission(int max) {
	
		String prePerm = "";
		
	   if(max == 1){
			prePerm = "none";
		}
		else if (max == 2){
			prePerm = "pure";
		}
		else if(max == 3){
			prePerm = "full";
		}
		else if (max == 4){
			prePerm = "unique";
		}
		else if(max == 0){
			prePerm = "";
		}
	   
	return prePerm;
	}

public static int generateObjectPrePermission(LinkedList<E_MVertice> vertexes){
		
		Iterator<E_MVertice> it = vertexes.iterator();
		
		int size = countFields(vertexes);
		
		Integer[] temp = new Integer[vertexes.size()];
		
		for(int k = 0;k < temp.length;k++){
			
			temp[k] = -1;
		}

	 int i = 0;
	 
	 int max = -1;

	 int tempMax = -1;
	 
	 if (it != null) {
			
	    	while (it.hasNext()) {
				
				E_MVertice var = it.next();
				
				if(var.isField()){
					
					if(var.getPre_permissions().equals("unique")){
						temp[i] = 4;
					}
					else if (var.getPre_permissions().equals("full")){
						temp[i] = 3;
					}
					else if (var.getPre_permissions().equals("pure")){
						temp[i] = 2;
					}
					else if (var.getPre_permissions().equals("none") && var.getPost_permissions().equals("unique")){
						temp[i] = 1;
						tempMax = 1;
						return tempMax;
						/*if(var.getRefMethod().getMgraphName().equals(GlobalVariables.MAIN)){
							return tempMax;
						}*/
					}
				   else if (var.getPre_permissions().equals("")){
						temp[i] = 0;
				   }
				i++;
	    	}
		}
	 }
	    
	 max = getMaxValue(temp);
	    
	 System.out.println(max);
		
	    return max;
				
}
public static int generateObjectPostPermissions(LinkedList<E_MVertice> vertexes){
	
	Iterator<E_MVertice> it = vertexes.iterator();
	
	//int size = countFields(vertexes);
	
	Integer[] temp = new Integer[vertexes.size()];
	
	for(int k =0;k<temp.length;k++){
		
		temp[k] = -1;
	}

 int i = 0;
 
 int tempMax = -1;
 
 if (it != null) {
		
    	while (it.hasNext()) {
			
			E_MVertice var = it.next();
			
			if(var.isField()){
				
			  if (var.getPre_permissions().equals("unique") && var.getPost_permissions().equals("none")){
					temp[i] = 4;
					tempMax = 4;
					//return tempMax;
			  }
			  else if(var.getPost_permissions().equals("unique")){
					temp[i] = 4;
				}
				else if (var.getPost_permissions().equals("full")){
					temp[i] = 3;
				}
				else if (var.getPost_permissions().equals("pure")){
					temp[i] = 2;
				}
				else if (var.getPost_permissions().equals("none") && !var.getPre_permissions().equals("unique")){
					temp[i] = 1;
				}
			   else if (var.getPost_permissions().equals("")){{
					temp[i] = 0;
				}
			}
			i++;
    	}
	}
 }
    int max = getMaxValue(temp);
	
    return max;
			
}
public static int getMaxValue(Integer[] temp) {
    int maxValue = temp[0];
    for (int i = 1; i < temp.length; i++) {
        if (temp[i] > maxValue) {
            maxValue = temp[i];
        }
    }
    return maxValue;
}
static int countFields(LinkedList<E_MVertice> vertexes){
	
		Iterator<E_MVertice> it = vertexes.iterator();
	
		int i = 0;
	
 if (it != null) {
		
    	while (it.hasNext()) {
			
			E_MVertice var = it.next();
			
			if(var.isField()){
				i++;
			}
    	}
 }

return i;
	
}
public static int countFieldParameters(LinkedList<E_MVertice> vertexes){
	
	Iterator<E_MVertice> it = vertexes.iterator();

	int i = 0;

if (it != null) {
	
	while (it.hasNext()) {
		
		E_MVertice var = it.next();
		
		if(var.isParam()){
			i++;
		}
	}
}

return i;

}

public static int findDiffInParamsCount(LinkedList<E_MVertice> vertexes, E_MethodGraph graph){
	
	int diff = 0;
	if(countFieldParameters(vertexes) > countMParameters(graph)){
		diff = countFieldParameters(vertexes) - countMParameters(graph);
		
	}
	if(countFieldParameters(vertexes) < countMParameters(graph)){
		diff = countFieldParameters(vertexes) - countMParameters(graph);
	}
	return diff;
}
public static int countMParameters(E_MethodGraph graph){
	
	int size = graph.getParameters().size();
	return size;
}

}
