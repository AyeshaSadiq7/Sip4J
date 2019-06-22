package graphconstruction;


import graphstructure.E_ClassGraphs;
import graphstructure.E_MVertice;
import graphstructure.E_MethodGraph;
import graphstructure.E_PackGraphs;
import graphtraversal.Graph_Traversal;
import graphtraversal.Permission_Generation;
import graphutilities.Graph_Generator;
import graphutilities.Graph_Controller;
import graphutilities.Graph_Utilities;
import graphutilities.Graph_Visualization;
import datastructure.E_Class;
import datastructure.E_MInvokedMethod;
import datastructure.E_MParameter;
import datastructure.E_MRefField;
import datastructure.E_Method;
import datautilities.Data_Controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.eclipse.jdt.core.dom.Expression;
import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.GraphUnion;

import org.jgrapht.DirectedGraph;

public class Graph_Construction{

	private static JGraphModelAdapter<String, LabeledEdge> m_jgAdapter;
	
	public static void constructGraph(E_Class _class, LinkedList<E_Method> _methds){
			
			Graph_Generator.createNewPackage();
			
			E_ClassGraphs cgraph = new E_ClassGraphs();
			
			E_MethodGraph _methodgraph = null;
	   
			if(_methds!=null){	
		   
		 	   cgraph.setClassGraphName(_class.getName());
		 	   
		 	   //E_ClassGraphs.setClassSignatures(_class.getModifier()+" class "+_class.getName()+" {");
		 	 
		 	    E_ClassGraphs.setClassSignatures("class "+_class.getName()+" {");
		 	   
			   for(E_Method m : _methds ){
				 	 
				    _methodgraph = Graph_Construction.createMethodGraph(m);
					
					cgraph.addMethodgraphs(_methodgraph);
				
				}
	         }
			
			 Graph_Generator.getPackage().getClasses().addLast(cgraph);
			
			   	//addSubMethodGraphs();
				
				//Graph_Traversal.TraverseGraph();
				//System.out.println("Total no of nodes in the program"+(Graph_Controller.fetchAllVertices().size())*2);

			    Permission_Generation.generatePermissions();
				
				
		//return jgraph;
	}
	
	@SuppressWarnings("unchecked")
	
	public static E_MethodGraph createMethodGraph(E_Method  m){

		//JGraph jgraph = null;
		
		LinkedList<E_MRefField> _mrefs = new LinkedList<E_MRefField>();
		  
		  DirectedGraph<E_MVertice, LabeledEdge> jgraph = null;

		  E_MethodGraph _methodgraph = new E_MethodGraph();
		
		  if(!m.getName().equals(null)){
			     
			  	    _mrefs = m.getRefVariable();
			  	
			        // construct graph
			        _methodgraph.setMgraphName(m.getName());
			        
			        _methodgraph.setDeclClass(m.getDeclaringClass());
			         
			        _methodgraph.setMRetType(m.getReturnType());
			        
			        _methodgraph.setMethodSignatures(m.getMethodSignatures());
			        
			        LinkedList<E_MParameter> params = m.getParameters();
			        
			        for(E_MParameter p:params){
			          
			        	_methodgraph.addParameters(p);
			        }
			        // set foo vertex
			        if(m.getState() != null && m.getState().isEmpty() == false){
			        	_methodgraph.setMethodBody(m.getState());
			        }
			        
			        E_MVertice mObj = new E_MVertice();
					
			        mObj.setVName("foo");
					
					//mObj.setQualifiedName(m.getDeclClassQName());
					
					mObj.setMethod(true);
					
					AddGraphVertex(_methodgraph, mObj);	
					
				    // set context vertex
					E_MVertice contextObj = new E_MVertice();
					
					contextObj.setVName("context");
					
					contextObj.setContext(true);
					
					AddGraphVertex(_methodgraph, contextObj);	
						
					if(_mrefs != null){
							 	
					   for(E_MRefField var : _mrefs){
						   	 
						   	// set ref variable vertex
						    
						     E_MVertice obj = null;
					
							 obj  = new E_MVertice();
							
					 	     obj.setVName(var.getName().toString());
					 	     
					 	     obj.setVType(var.getType().toString());
					 	     
					 	     obj.setDeclClass(var.getDeclaringClass().toString());
					 	     
					 	     obj.setmOperation(var.getMOperation());
					 	     
					 	     obj.setaOperation(var.getAliasOp());
					 	     
					 	     obj.setcOperation(var.getCOperation());
					 	     
					 	     obj.setField(var.isField());
					 	     
					 	     obj.setParam(var.isParam());
					 	     
					 	     //obj.setQualifiedName(var.getQualifyingObject().getName());
					 	     
					 	     obj.setExpType(var.getExpType());
					 	     
					 	     obj.setRefMethod(_methodgraph);
					 	     
					 	     obj.setRetField(var.isRetFiel());
					 	     
					 	     obj.setMethod(false);
					 	     
					 	     obj.setContext(false);
					 	     
					 	    	if(obj.getmOperation() != null ){
								
									if(obj.getmOperation().equals("rw")){
										
										LabeledEdge inEdge = new LabeledEdge(mObj, obj, obj.getmOperation());
										
										obj.setIncomingEdges(inEdge);
									}	
									else if(obj.getmOperation().equalsIgnoreCase("r")){
										
										LabeledEdge outEdge = new LabeledEdge(obj,mObj,obj.getmOperation());
										
										obj.setOutgoingEdges(outEdge);
										
									}
					 	     	}
								if(obj.getcOperation() != null){
										
										if(obj.getcOperation().equalsIgnoreCase("rw")){
										
											LabeledEdge incomingEdge = new LabeledEdge(contextObj, obj,obj.getcOperation());
											
											obj.setIncomingEdges(incomingEdge);
											
										}
										else if(obj.getcOperation().equalsIgnoreCase("r")){
											LabeledEdge outgoingEdge = new LabeledEdge(obj,contextObj,obj.getcOperation());
											obj.setOutgoingEdges(outgoingEdge);
										}
								}
							
								AddGraphVertex(_methodgraph, obj);	
								 	
							}
						}
		  				// create jgraph structure here
				 		//jgraph = createGraphStructure(_methodgraph);
				 		// set jgraph in method graph
						//_methodgraph.setMethodgraph(jgraph);  
		  	}
					
			return _methodgraph;
    }
	
	private static void AddGraphVertex(E_MethodGraph graph, E_MVertice obj) {
		
		graph.addVertices(obj);
		
	}
	/*private static DirectedGraph<E_MVertice, LabeledEdge> createGraphStructure(E_MethodGraph graph){
		DirectedGraph<E_MVertice, LabeledEdge> gg = new DirectedMultigraph<E_MVertice, LabeledEdge>(
				
				 new ClassBasedEdgeFactory<E_MVertice, LabeledEdge>(LabeledEdge.class));
		
		LinkedList<E_MVertice> vertices = graph.getVertices();
		
		Iterator<E_MVertice> it = vertices.iterator();
		
		if(it != null){
			
			while(it.hasNext()) {	
				
				 //E_MVertice obj = null;

				 //obj  = new E_MVertice();
			
		 	    E_MVertice vertex = it.next();
		 	      
		 	    if(vertex.isMethod() && vertex.isContext()== false){
		 	    	gg.addVertex("foo");
		 	    }
		 	    else if (vertex.isContext() && vertex.isMethod() == false){
		 	    	gg.addVertex("context");
		 	    }
		 	    else{
			
		 	   
					  if(gg.containsVertex(vertex)==false){
					   
					  		 gg.addVertex(vertex);
					  	
					  }
					  if(vertex.getmOperation() != null ){
						
						if(vertex.getmOperation().equalsIgnoreCase("rw")){
							
							gg.addEdge("foo", vertex,new LabeledEdge<String>("foo", vertex,vertex.getMOperation()));
						}
						else if(vertex.getMOperation().equalsIgnoreCase("r"))
						{
							gg.addEdge(vertex,"foo",new LabeledEdge<String>(vertex,"foo",vertex.getMOperation()));
						}
					}
					if(vertex.getCOperation() != null){
						
						if(vertex.getCOperation().equalsIgnoreCase("rw")){
						
							gg.addEdge("context",vertex,new LabeledEdge<String>("context", vertex,vertex.getCOperation()));
						}
						else if(vertex.getCOperation().equalsIgnoreCase("r")){
							
							gg.addEdge(vertex,"context",new LabeledEdge<String>(vertex,"context",vertex.getCOperation()));	
						}
					}
		 	    }
			return gg;
	  
	}*/

/*public static void addSubMethodGraphs(){
	
	E_PackGraphs pkg = Graph_Generator.getPackage();

		E_ClassGraphs _class = pkg.getClasses().getLast();
		
		LinkedList<E_MethodGraph> mgraphs = _class.getMethodgraphs();
  
		DirectedGraph<HashMap, LabeledEdge> ugg = new DirectedMultigraph<HasMap<String,String>, LabeledEdge>(
				
				 new ClassBasedEdgeFactory<HasMap<String,String>, LabeledEdge>(LabeledEdge.class));
		
		  for(E_MethodGraph graph : mgraphs){
		  		
			  DirectedGraph<HashMap<String, String>, LabeledEdge> gg = graph.getMethodgraph();
		    
			  updateMethodGraph(graph, gg);
			 
			  //_class.addMethodgraphs(graph);
			 		  
			  //mgraphs.set(mgraphs.indexOf(graph), graph);
			  
			 
		  }
		 // return ugg; 
}

public static void updateMethodGraph(E_MethodGraph graph, DirectedGraph<HashMap<String, String>, LabeledEdge> gg){
	
	//get referenced fields from submethods and add them as a part of method graph
		
	  LinkedList<E_Method> _methods = Graph_Controller.fetchAllMethods();
	  
	  LinkedList<E_MRefFields> _invmrefs = null;
	  
	  LinkedList<E_MInvokedMethods> _minvms = null;

if(_methods!=null){
  	
	for(E_Method m : _methods){
  		
  			if(m.getName()!= null && m.getName().equalsIgnoreCase(graph.getMgraphName())){
  			
		  		_minvms = Graph_Controller.fetchSubMethods(m);
		
		  		if(_minvms != null){
		  
		  			for(E_MInvokedMethods invm : _minvms){
		  				
		  				_invmrefs  =  Graph_Controller.fetchsubMethodFields(invm);
		
							if(_invmrefs!= null){
							
							   for(E_MRefFields rf : _invmrefs){
								
									//add vertex for referenced field in sub methods
								  if(!gg.containsVertex(rf.getName())){
									 
									  gg.addVertex(rf.getName());
								   }
									
									String postPerms = fetchPostPermissions(invm, rf.getName());
									
									if(postPerms != null){
											
										   if(postPerms.equalsIgnoreCase("immutable")){
												
												if(!Graph_Utilities.ifReadEdgeExist(graph, rf.getName(), "foo")){
													if(!Graph_Utilities.ifWriteEdgeExist(graph, rf.getName(), "foo")){
														gg.addEdge(rf.getName(),"foo",new LabeledEdge<String>(rf.getName(),"foo","r"));
													}
												}
												if(!m.getName().equalsIgnoreCase("main")){
													if(!Graph_Utilities.ifReadEdgeExist(graph, rf.getName(), "context")){
														if(!Graph_Utilities.ifWriteEdgeExist(graph, rf.getName(), "context")){
															gg.addEdge(rf.getName(),"context",new LabeledEdge<String>(rf.getName(),"context","r"));
														}
													}
												}
											}
											else if(postPerms.equalsIgnoreCase("share")){
												if(!Graph_Utilities.ifWriteEdgeExist(graph, rf.getName(), "foo")){
													if(Graph_Utilities.ifReadEdgeExist(graph, rf.getName(), "foo")){
														gg.removeEdge(rf.getName(), "foo");
														//gg.removeEdge(Graph_Utilities.getReadEdge(graph, rf.getName(), "foo"));
														//graph.setMethodgraph(gg);
													}
													gg.addEdge("foo", rf.getName(),new LabeledEdge<String>("foo", rf.getName(),"rw"));
													
												}
												if(!m.getName().equalsIgnoreCase("main")){
													if(!Graph_Utilities.ifWriteEdgeExist(graph, rf.getName(), "context")){
														if(Graph_Utilities.ifReadEdgeExist(graph, rf.getName(), "context")){
															gg.removeEdge("context", "r");
															//gg.removeEdge(Graph_Utilities.getReadEdge(graph, rf.getName(), "context"));
															
														}
														gg.addEdge("context",rf.getName(),new LabeledEdge<String>("context", rf.getName(),"rw"));
													}
												}
											
											}
											else if(postPerms.equalsIgnoreCase("pure")){
												if(!Graph_Utilities.ifReadEdgeExist(graph, rf.getName(), "foo")){
													if(!Graph_Utilities.ifWriteEdgeExist(graph, rf.getName(), "foo")){
														gg.addEdge(rf.getName(),"foo",new LabeledEdge<String>( rf.getName(),"foo","r"));
													}
												}
												if(!m.getName().equalsIgnoreCase("main")){
													if(!Graph_Utilities.ifWriteEdgeExist(graph, rf.getName(), "context")){
														if(Graph_Utilities.ifReadEdgeExist(graph, rf.getName(), "context")){
															gg.removeEdge(rf.getName(), "context");
															//gg.removeEdge(Graph_Utilities.getReadEdge(graph, rf.getName(), "context"));
															
														}
														
														gg.addEdge("context",rf.getName(),new LabeledEdge<String>("context", rf.getName(),"rw"));
												     }	
												}
											}
											else if(postPerms.equalsIgnoreCase("unique")){
												if(!Graph_Utilities.ifWriteEdgeExist(graph, rf.getName(), "foo")){
													if(Graph_Utilities.ifReadEdgeExist(graph, rf.getName(), "foo")){
														gg.removeEdge(rf.getName(), "foo");
														//gg.removeEdge(Graph_Utilities.getReadEdge(graph, rf.getName(), "foo"));
													}
													gg.addEdge("foo", rf.getName(),new LabeledEdge<String>("foo", rf.getName(),"rw"));
													
												}
												gg.removeAllEdges("context", rf.getName());
											}
											else if(postPerms.equalsIgnoreCase("full")){
												
												if(!Graph_Utilities.ifWriteEdgeExist(graph, rf.getName(), "foo")){
													if(Graph_Utilities.ifReadEdgeExist(graph, rf.getName(), "foo")){
														gg.removeEdge(rf.getName(), "foo");
														//gg.removeEdge(Graph_Utilities.getReadEdge(graph, rf.getName(), "foo"));
													}
													gg.addEdge("foo", rf.getName(),new LabeledEdge<String>("foo", rf.getName(),"rw"));
												}
												if(!m.getName().equalsIgnoreCase("main")){
													if(!Graph_Utilities.ifReadEdgeExist(graph, rf.getName(), "context")){
														if(!Graph_Utilities.ifWriteEdgeExist(graph, rf.getName(), "context"))
															gg.addEdge(rf.getName(),"context",new LabeledEdge<String>(rf.getName(),"context","r"));
													}
												}
											}
									  }				
							   	}
							}
				}
		  	}
  		}
}
  	}
    graph.setMethodgraph(gg);
  	//return gg;
  }*/
public static String fetchPostPermissions(E_MInvokedMethod invm, String rf){
	
	String postPerms = null;
	
	E_PackGraphs pkg = Graph_Generator.getPackage();

	LinkedList<E_ClassGraphs> classes = pkg.getClasses();
	
	String v = null;

 //System.out.println("My sub-Method Name for fetching post permissions = "+invm.getName());
		
 for(E_ClassGraphs _class: classes ){
	
	LinkedList<E_MethodGraph> mgraphs = _class.getMethodgraphs();

	for(E_MethodGraph graph : mgraphs){
		
	//	System.out.println("I found sub method for fetching post permissions = "+invm.getName());
		
		if(graph.getMgraphName().equalsIgnoreCase(invm.getName())){
			
			LinkedList<E_MVertice> vertexes = graph.getVertices();	
				
				if(vertexes != null){
					
					for(E_MVertice vertex : vertexes){
				 
						   v = vertex.getVName();
						   
						   if(v.equalsIgnoreCase(rf)){
							   postPerms = vertex.getPost_permissions();
						   }
						   
				      }
			     }
				
		}
		
	}
}
				   	   
	return postPerms;
}	


}
