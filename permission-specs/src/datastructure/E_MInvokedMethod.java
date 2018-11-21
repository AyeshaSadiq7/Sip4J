package datastructure;

import java.util.LinkedList;

public class E_MInvokedMethod implements Cloneable {

	private String name;// method name
	private String declaringClass;// declaring class in which method is declared
	private boolean isConstr;
	private String declClassQName;// declaring class Qualified Name in which method is declared
	private LinkedList<E_MRefArgument> arguments;
	
	private E_Object qualifyingObject; // if any
	
	public E_MInvokedMethod(String name, String dclass, String QName, boolean flag,E_Object obj) {
		this.name = name;
		this.declaringClass = dclass;
		this.arguments = new LinkedList<E_MRefArgument>();
		declClassQName = QName;
		isConstr = flag;
		qualifyingObject = obj;// receiver object
	}
	public LinkedList<E_MRefArgument> getRefArguments() {
		return arguments;
	}
	public boolean isConstr() {
		return isConstr;
	}
	public void setConstr(boolean flag) {
		this.isConstr = flag;
	}
	public void addRefArguments(E_MRefArgument argument) {
		arguments.add(argument);
	}
	public String getDeclaringClass() {
		return declaringClass;
	}

	public void setDeclaringClass(String declaringClass) {
		this.declaringClass = declaringClass;
	}

	public void setName(String str) {
		name = str;
	}

	public String getName() {
		return name.toString();
	}
	public String getDeclClassQName() {
		return declClassQName;
	}
	public void setDeclClassQName(String QName) {
		this.declClassQName = QName;
	}

}
