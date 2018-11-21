package datastructure;

import java.util.LinkedList;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class E_InvokedMethod implements Cloneable {

	private String name;// method name
	private String declaringClass;// declaring class in which method is declared
	private String declClassQName;// declaring class Qualified Name in which method is declared
	private LinkedList<E_Argument> arguments;
	boolean isConstr;
	private MethodInvocation MInv;
	MethodDeclaration callerMethod;
	

	private E_Object qualifyingObject; // if any

	public E_InvokedMethod(String name, String dclass,String classQName, boolean flag, E_Object obj,MethodDeclaration callerM) {
		this.name = name;
		this.declaringClass = dclass;
		this.arguments = new LinkedList<E_Argument>();
		declClassQName = new String (classQName);
		isConstr = flag;
		qualifyingObject = obj;// receiver object
		callerMethod = callerM;
	}

	public LinkedList<E_Argument> getArguments() {
		return arguments;
	}

	public void addArguments(E_Argument argument) {
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

	public boolean isConstr() {
		return isConstr;
	}

	public void setConstr(boolean isConstr) {
		this.isConstr = isConstr;
	}
	public String getDeclClassQName() {
		return declClassQName;
	}
	public void setDeclClassQName(String QName) {
		this.declClassQName = QName;
	}

	public E_Object getQualifyingObject() {
		return qualifyingObject;
	}

	public void setQualifyingObject(E_Object qualifyingObject) {
		this.qualifyingObject = qualifyingObject;
	}

	public MethodInvocation getMInv() {
		return MInv;
	}

	public void setMInv(MethodInvocation inv) {
		MInv = inv;
	}

	public MethodDeclaration getCallerMethod() {
		return callerMethod;
	}

	public void setCallerMethod(MethodDeclaration callerMethod) {
		this.callerMethod = callerMethod;
	}

}
