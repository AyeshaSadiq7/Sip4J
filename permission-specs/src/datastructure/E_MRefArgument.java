package datastructure;



public class E_MRefArgument {
	private String name;
	private String type;
	private int position;
	private String declaClass;
	private boolean isField;
	private boolean isParameter;
	private String declMethod;
	
	public E_MRefArgument(String name, String type, String dclass, int position, boolean flag, String declM,boolean isParam) {
		
		this.name = name;
		this.type = type;
		this.declaClass = dclass;
		this.position = position;
		this.isField = flag;
		this.declMethod = declM;
		isParameter = isParam;
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
}
