package graphtraversal;

import graphconstruction.LabeledEdge;
import graphstructure.E_ClassGraphs;
import graphstructure.E_MVertice;
import graphstructure.E_MethodGraph;
import graphstructure.E_PackGraphs;
import graphutilities.Graph_Generator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import parser.GlobalVariables;

public class Permission_Generation {

	public static void generatePermissions(){

		E_PackGraphs pkg = Graph_Generator.getPackage();

		LinkedList<E_ClassGraphs> _gclass = pkg.getClasses();
		
	for(E_ClassGraphs _class: _gclass){
			
		 LinkedList<E_MethodGraph> mgraphs = _class.getMethodgraphs();
	
		   for(E_MethodGraph graph : mgraphs){
			
			String v = null;
		
			LinkedList<E_MVertice> vertexes = graph.getVertices();	
					
			ArrayList<String> writeList = null;
			   
			ArrayList<String> readList  = null;
			
			if(vertexes != null){
				
				for(E_MVertice vertex : vertexes){
			 
					    v = vertex.getVName();
					   
					    writeList = null;
					   
						readList  = null;
					   
						if(vertex.isContext() || vertex.isMethod()){
							if(v.equals("foo") || v.equalsIgnoreCase("context")){
								
							}
						}
						else{
							
							if(!(vertex.getOutgoingEdges().isEmpty())){
								
								Set<LabeledEdge> outEdges =  vertex.getOutgoingEdges();
								
								readList = new ArrayList<String>();
								
								for (LabeledEdge oe : outEdges)
									
									readList.add(oe.getTarget().getVName().toString());
								
							  }
							  if(vertex.getIncomingEdges().isEmpty() == false){
								
								    //System.out.println("incoming Graph Vertex = "+v);
								    
									Set<LabeledEdge> inEdges = vertex.getIncomingEdges();

									writeList = new ArrayList<String>();
								   
									for (LabeledEdge ie : inEdges)	
										writeList.add(ie.getSource().getVName().toString());
							  }  
						// generate permissions for every vertex
								//assignPermissions(vertex, writeList,  readList, graph);
							  //if(graph.getMgraphName().equals(GlobalVariables.MAIN)){
								 assignDBCPermissions(vertex, writeList,  readList, graph);
						     //}
						}
							   
						}	
			}
		}
	}
	}

 public static void assignPermissions(E_MVertice vertex, ArrayList<String> writeList, ArrayList<String> readList, E_MethodGraph graph){
		
//if(graph.getMgraphName().equals("buildTestData")){
	if(checkFooInRead(readList) && !checkFooInWrite(writeList) && checkContextInRead(readList) &&  (!checkContextInWrite(writeList))){
		  //System.out.println("Immutable Access = "+vertex.getVName());
		  vertex.setPre_permissions("pure");// should be immutable
		  vertex.setPost_permissions("pure");
		 // graph.addVertices(vertex);
	  }
	 else if(checkFooInWrite(writeList) && checkContextInWrite(writeList)){
		 // System.out.println("Share Access = "+vertex.getVName());
		  vertex.setPre_permissions("full");// should be share
		  vertex.setPost_permissions("full");
		  //graph.addVertices(vertex);
	  } 
	  else if(checkFooInRead(readList) && checkContextInWrite(writeList) && !checkFooInWrite(writeList)){
			  //System.out.println("Pure Access = "+vertex.getVName());
			  vertex.setPre_permissions("pure");
			  vertex.setPost_permissions("pure");
			  //graph.addVertices(vertex);
	  }
	 else if(checkFooInWrite(writeList) && checkContextInWrite(writeList) == false && checkContextInRead(readList) == false){
		// System.out.println("Unique Access = "+vertex.getVName());
		 if(vertex.getExpType() == ASTNode.NULL_LITERAL){
			 vertex.setPre_permissions("unique");
			 vertex.setPost_permissions("none");
		 }
		 else{
			 vertex.setPre_permissions("none"); 
			 vertex.setPost_permissions("unique");
		 }
		 // graph.addVertices(vertex);
	  }
	  else if(checkFooInWrite(writeList) && checkFooInRead(readList) && checkContextInRead(readList) && checkContextInWrite(writeList) == false){
		 // System.out.println("Full Access= "+vertex.getVName());
		  vertex.setPre_permissions("full");
		  vertex.setPost_permissions("full");
		  //graph.addVertices(vertex);
	  }
	  else if(checkFooInRead(readList)  && checkContextInWrite(writeList) == false && checkContextInRead(readList) == false 
			  && vertex.getRefMethod().getMgraphName().equals(GlobalVariables.MAIN)){
		  vertex.setPre_permissions("none");
		  vertex.setPost_permissions("unique");
	  }
	  else{
		  vertex.setPre_permissions("none");
		  vertex.setPost_permissions("none");
	  }
//}
	
	}
 public static void assignDBCPermissions(E_MVertice vertex, ArrayList<String> writeList, ArrayList<String> readList, E_MethodGraph graph){
		//if(graph.getMgraphName().equals("cons")){	
			
	  if(checkFooInRead(readList) && !checkFooInWrite(writeList) && checkContextInRead(readList) &&  (!checkContextInWrite(writeList))){
			  //System.out.println("Immutable Access = "+vertex.getVName());
			  vertex.setPre_permissions("immutable");// should be immutable
			  vertex.setPost_permissions("immutable");//changed on 23May 2013
			 // graph.addVertices(vertex);
		}
		else if(checkFooInWrite(writeList) && checkContextInWrite(writeList)){
			 // System.out.println("Share Access = "+vertex.getVName());
			  vertex.setPre_permissions("share");// should be share
			  vertex.setPost_permissions("share");
			  //graph.addVertices(vertex);
		  } 
		  else if(checkFooInRead(readList) && !checkFooInWrite(writeList) && checkContextInWrite(writeList)){
				  //System.out.println("Pure Access = "+vertex.getVName());
				  vertex.setPre_permissions("pure");
				  vertex.setPost_permissions("pure");
				  //graph.addVertices(vertex);
		  }
		 else if(checkFooInWrite(writeList) && checkContextInWrite(writeList) == false && checkContextInRead(readList) == false && vertex.getRefMethod().getMgraphName().equals(GlobalVariables.MAIN) == false){
			 //if(vertex.getRefMethod().getMgraphName().equals(GlobalVariables.MAIN) == false){
			 	if(vertex.getExpType() == ASTNode.CLASS_INSTANCE_CREATION || vertex.getExpType() == ASTNode.ARRAY_INITIALIZER 
			    		 || vertex.getExpType() == ASTNode.ARRAY_CREATION){
			 		 vertex.setPre_permissions("unique");// toggle between none and unique
			 		 vertex.setPost_permissions("unique");
		 		}
			   else if(vertex.getExpType() == ASTNode.NULL_LITERAL){
			    	  /*if(graph.getDeclClass().equals(graph.getMgraphName())){// This is added to check null pointer exceptions on 18th June
			    		     vertex.setPre_permissions(""); 
				 			 vertex.setPost_permissions("");
			    	  }
			    	  else{*/
			 			 vertex.setPre_permissions("unique"); 
			 			 vertex.setPost_permissions("unique");
			    	 // }
		 		}
			   else{
				     vertex.setPre_permissions("unique");// toggle between none and unique
			 		 vertex.setPost_permissions("unique");
			   }
			 //}
			 // graph.addVertices(vertex);
		  }
		  else if(checkFooInWrite(writeList) && checkContextInRead(readList) && checkContextInWrite(writeList) == false){
			 // System.out.println("Full Access= "+vertex.getVName());
			  vertex.setPre_permissions("full");
			  vertex.setPost_permissions("full");
			  //graph.addVertices(vertex);
		  }
		 else if((checkFooInRead(readList) || checkFooInWrite(writeList)) && checkContextInWrite(writeList) == false && checkContextInRead(readList) == false 
				  && (vertex.getRefMethod().getMgraphName().equals(GlobalVariables.MAIN) || vertex.getRefMethod().getMgraphName().equals(GlobalVariables.JGFrun))){
			      vertex.setPre_permissions("unique");//can be revert back to original permission i.e. none
			      vertex.setPost_permissions("unique");
		  }
		  else{
			  vertex.setPre_permissions("none");
			  vertex.setPost_permissions("none");
		  }
		//}
	}
	public static boolean checkFooInRead(ArrayList<String> arraylist) {
		
		boolean flag = false;
	
		if(arraylist!=null){
			for (String str : arraylist) {
				if (str.toLowerCase().contains("foo")) {
					flag = true;
				}
			}
		}
    return flag;
	}
	public static boolean checkFooInWrite(ArrayList<String> arraylist) {
		
		boolean flag = false;
	
		if(arraylist!=null){
			for (String str : arraylist) {
				if (str.toLowerCase().contains("foo")) {
					flag = true;
				}
			}
		}
    return flag;
	}
	public static boolean checkContextInRead(ArrayList<String> arraylist) {
	
		boolean flag = false;
		
		if(arraylist!=null){
			
			for (String str : arraylist) {
			
				if (str.toLowerCase().contains("context")){
					flag = true;
				}
			}
		}
	   return flag;
	}
	public static boolean checkContextInWrite(ArrayList<String> arraylist) {
		
		boolean flag = false;
		
		if(arraylist!=null){
			
			for (String str : arraylist) {
			
				if (str.toLowerCase().contains("context")){
					flag = true;
				}
			}
		}
	   return flag;
	}
}