package datastructure;

public class E_Argument {
	
	private String name;
	private  String type;
	private int position;
	private  String declaClass;
	private String declMethod;
	private boolean isField;
	private boolean isParameter;
	private boolean isLocal;
	private E_Object qualifyingObject; // if any
	
	
	public E_Argument(String name, String type, String dclass, int pos, boolean isField, String declM, boolean isParam, boolean isLocal) {
		this.name = name;
		this.type = type;
		this.declaClass = dclass;
		this.position = pos;
		this.declMethod = declM;
		this.isField = isField;
		this.isParameter= isParam;
		this.isLocal = isLocal;
		//qualifyingObject = qualObj;// receiver object
	
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setType(String str) {
		type = str;
	}

	public String getType() {
		return type;
	}

	public String getDeclaClass() {
		return declaClass;
	}

	public void setDeclaClass(String declaClass) {
		this.declaClass = declaClass;
	}

	public boolean isField() {
		return isField;
	}

	public void setField(boolean flag) {
		this.isField = flag;
	}

	public String getDeclMethod() {
		return declMethod;
	}

	public void setDeclMethod(String dMethod) {
		this.declMethod = dMethod;
	}

	public boolean isParameter() {
		return isParameter;
	}

	public void setParameter(boolean isParameter) {
		this.isParameter = isParameter;
	}

	public boolean isLocal() {
		return isLocal;
	}

	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}

	public E_Object getQualifyingObject() {
		return qualifyingObject;
	}

	public void setQualifyingObject(E_Object qualifyingObject) {
		this.qualifyingObject = qualifyingObject;
	}

}
