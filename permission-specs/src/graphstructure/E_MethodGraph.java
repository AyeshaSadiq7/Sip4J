package graphstructure;

import graphconstruction.LabeledEdge;

import java.util.HashMap;
import java.util.LinkedList;
import org.jgrapht.DirectedGraph;

import datastructure.E_MParameter;
import datastructure.E_MRefField;


public class E_MethodGraph {

	private String mgraphName;
	private String mRetType;
	private String MethodSignatures = "";
	private LinkedList<E_MVertice> vertices;
	private LinkedList<E_MParameter> parameters;

	private DirectedGraph<E_MVertice, LabeledEdge> graphStructure;
	
	public E_MethodGraph() {
		this.vertices = new LinkedList<E_MVertice>();
		this.parameters = new LinkedList<E_MParameter>();
	}
	public LinkedList<E_MVertice> getVertices() {
		return this.vertices;
	}
	public void addVertices(E_MVertice setV) {
		this.vertices.add(setV);
	}
	public DirectedGraph<E_MVertice, LabeledEdge> getMethodgraph() {
		return graphStructure;
	}
	public void setMethodgraph(DirectedGraph<E_MVertice, LabeledEdge> jgraph) {
		graphStructure = jgraph;
	}
	public String getMgraphName() {
		return mgraphName;
	}
	public void setMgraphName(String mgraph) {
		this.mgraphName = mgraph;
	}public String getMethodSignatures() {
		return MethodSignatures;
	}
	public void setMethodSignatures(String methodSignatures) {
		MethodSignatures = methodSignatures;
	}
	public String getMRetType() {
		return mRetType;
	}
	public void setMRetType(String mRetType) {
		this.mRetType = mRetType;
	}
	public LinkedList<E_MParameter> getParameters() {
		return parameters;
	}
	public void addParameters(E_MParameter params) {
		this.parameters.add(params);
	}
	

}
