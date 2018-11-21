package datastructure;

import java.util.LinkedList;

public class E_Package {

	private String packageName;
	
	private LinkedList<E_Class> classes;

	public E_Package() {
		classes = new LinkedList<E_Class>();
	}

	public void setName(String str) {
		packageName = str;
	}

	public String getName() {
		return packageName.toString();
	}

	public LinkedList<E_Class> getClasses() {
		return classes;
	}
	public void addClasses(E_Class _class) {
		classes.add(_class);
	}
}
