package datastructure;


import java.util.LinkedList;


public class E_Class {

	private String className;
	private String modifier;
	private String classQName =""; // qualifyng name
	private LinkedList<E_Field> fields; // class fields
	private LinkedList<E_Method> methods;// list of declared methods
	private LinkedList<E_InvokedMethod> invokMethods;// invoked methods
	
	//stores super class of this class
	E_Class superClass;//super class
	
	//maintain index of this class
	static int classIndex = 0;
	//maintain index of object
	static int numberOfObjects = 0;
	// store number of object for this class
	LinkedList<E_Object> objectList;

	public E_Class() {
		fields = new LinkedList<E_Field>();
		methods = new LinkedList<E_Method>();
		invokMethods = new LinkedList<E_InvokedMethod>();
		objectList = new LinkedList<E_Object>();
		superClass = null;
		createClassIndex();
	}
	public void setSuperClass(E_Class sClass) {
		superClass = sClass;
	}

	public E_Class getSuperClass() {

		return superClass;
	}

	public void setName(String str) {
		className = str;
	}

	public String getName() {

		return className.toString();
	}

	public void addMethod(E_Method method) {
		methods.add(method);
	}

	public LinkedList<E_Method> getMethods() {
		return methods;
	}

	public void addField(E_Field field) {
		fields.add(field);
	}

	public LinkedList<E_Field> getFields() {
		return fields;
	}
	
	public void addObject(E_Object obj) {	
		objectList.add(obj);
	}

	public LinkedList<E_Object> getObject() {
		return objectList;
	}

	public void createObject() {
		// TODO Auto-generated method stub
		numberOfObjects++;

	}
	public static void createClassIndex() {
		// TODO Auto-generated method stub
		classIndex = getLastClassIndex()+1;
	}
	public int getLastObjectIndex() {
		// TODO Auto-generated method stub
		return numberOfObjects;
	}
	public static void setIndex(int index) {
		classIndex = index;
	}

	public static int getLastClassIndex() {
		return classIndex;
	}

	/*public E_Method getConstructor() {

		for (E_Method _method : methods)
			if (className.compareTo(_method.identifier) == 0)
				return _method;

		return null;
	}*/

	public LinkedList<E_InvokedMethod> getInvokMethods() {
		return invokMethods;
	}

	public void addInvokMethods(E_InvokedMethod im) {
		invokMethods.add(im);
	}

	public String getClassQName() {
		return classQName;
	}

	public void setClassQName(String QName) {
		classQName = QName;
	}
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String mod) {
		this.modifier = mod;
	}

}
