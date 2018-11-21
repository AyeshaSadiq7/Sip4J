package graphstructure;

import java.util.LinkedList;

public class E_PackGraphs {

	String packageName;
	
	LinkedList<E_ClassGraphs> classgraphs;

	public E_PackGraphs() {
		classgraphs = new LinkedList<E_ClassGraphs>();
	}

	public void setName(String str) {
		packageName = str;
	}

	public String getName() {
		return packageName.toString();
	}

	public LinkedList<E_ClassGraphs> getClasses() {
		return classgraphs;
	}

}
