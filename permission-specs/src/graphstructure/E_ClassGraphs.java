package graphstructure;

import java.util.LinkedList;

public class E_ClassGraphs {
	
	private static String className;
	private static String ClassSignatures = "";
	private static LinkedList<E_MethodGraph> methodgraphs;
	
	public E_ClassGraphs(){	
		methodgraphs = new LinkedList<E_MethodGraph>();
	}
	public String getClassGraphName() {
		return className;
	}
	public void setClassGraphName(String cName) {
		className = cName;
	}
	public  LinkedList<E_MethodGraph> getMethodgraphs() {
		return methodgraphs;
	}
	public void addMethodgraphs(E_MethodGraph graph) {
		methodgraphs.add(graph);
	}
	public void updateMethodgraphs(E_MethodGraph graph) {
		methodgraphs.set(methodgraphs.indexOf(graph), graph);
	}
	public String getClassSignatures() {
		return ClassSignatures;
	}
	public static void setClassSignatures(String classSignatures) {
		ClassSignatures = classSignatures;
	}
	
}
