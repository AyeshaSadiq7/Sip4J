package graphstructure;

import graphconstruction.LabeledEdge;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.Expression;
import org.jgrapht.DirectedGraph;

import datastructure.E_MParameter;
import datastructure.E_MRefField;
import datastructure.E_MStatements;


public class E_MethodGraph {

	private String mgraphName;
	private String declClass;
	private String mRetType;
	private String MethodSignatures = "";
	private List<String> methodBody;
	private LinkedList<E_MVertice> vertices;
	private LinkedList<E_MParameter> parameters;

	private DirectedGraph<E_MVertice, LabeledEdge> graphStructure;
	
	public E_MethodGraph() {
		this.vertices = new LinkedList<E_MVertice>();
		this.parameters = new LinkedList<E_MParameter>();
		this.methodBody = new LinkedList<String>();
		
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
	public List<String> getMethodBody() {
		return methodBody;
	}
	public void setMethodBody(List<String> list) {
		this.methodBody = list;
	}
	public String getDeclClass() {
		return declClass;
	}
	public void setDeclClass(String declClass) {
		this.declClass = declClass;
	}
	

}
