package graphutilities;

import graphstructure.E_PackGraphs;

public class Graph_Generator {

	static E_PackGraphs packgraph = new E_PackGraphs();
	
	Graph_Generator(){	
		
	}
	public static void createNewPackage(){	
		
		packgraph =  new E_PackGraphs();
		
	}
	public static void addPackage(E_PackGraphs _pkg){

	}
	public static E_PackGraphs getPackage(){
		return packgraph;
	}
	
}
