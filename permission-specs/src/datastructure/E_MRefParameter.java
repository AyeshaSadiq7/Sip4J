package datastructure;

import java.util.LinkedList;

public class E_MRefParameter {

	private String paramName;
	private String type;
	private int position;
	private int expType;
	private String declMethod;
	// declaring class is always empty for a parameter
	private String declaringClass;
	private boolean isField; // if this parameter is a class field
//////////////////////////////////////////////////////////////////////////
	private String pfName;// class field corresponding to this parameter
	private String pfClass;// declaring class of class field corresponding to this parameter
	private String pfType;
	private String mOperation;
	boolean  isRetFiel;
////////////////////////////////////////////////////////////////
	private LinkedList<E_MRefField> fields;
//////////////////////////////////////////////////////////////
	LinkedList<E_MRefAlias> aliases;
	private String aliasOp;
/////////////////////////////////////////////////////////
	private E_Object qualifyingObject;
	
	// LinkedList<E_Specification> requires;
	// LinkedList<E_Specification> ensures;

	public E_MRefParameter(String name, String type, String declClass,
			int position, String op, int eType, String d_Method,boolean retExp,E_Object obj) {
		this.paramName = name;
		this.type = type;
		this.position = position;
		this.mOperation = op;
		declMethod = d_Method;
		expType = eType;
		this.isRetFiel = retExp;
		declaringClass = declClass;
		aliases = new LinkedList<E_MRefAlias>();
		fields = new LinkedList<E_MRefField>();
		this.qualifyingObject = obj;
	}
	public boolean isRetFiel() {
		return isRetFiel;
	}

	public void setRetFiel(boolean isRetFiel) {
		this.isRetFiel = isRetFiel;
	}
	public void addAlias(E_MRefAlias a) {
		aliases.add(a);
	}
	public LinkedList<E_MRefAlias> getAliases() {
		return aliases;
	}
	public boolean removeAlias(E_MRefAlias a) {
		return aliases.remove(a);
	}
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setNumber(int n) {
		position = n;
	}

	public int getNumber() {
		return position;
	}

	public void setName(String str) {
		paramName = str;
	}

	public void setType(String str) {
		type = str;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return paramName.toString();
	}

	public String getMOperation() {
		return mOperation;
	}

	public void setMOperation(String operation) {
		this.mOperation = operation;
	}

	public int getExpType() {
		return expType;
	}

	public void setExpType(int expType) {
		this.expType = expType;
	}

	public boolean isField() {
		return isField;
	}

	public void setField(boolean isField) {
		this.isField = isField;
	}

	public String getfName() {
		return pfName;
	}

	public void setfName(String name) {
		this.pfName = name;
	}

	public String getDeclaringClass() {
		return declaringClass;
	}

	public void setDeclaringClass(String declaringClass) {
		this.declaringClass = declaringClass;
	}

	public String getDeclMethod() {
		return declMethod;
	}

	public void setDeclMethod(String declMethod) {
		this.declMethod = declMethod;
	}

	public String getPfClass() {
		return pfClass;
	}

	public void setPfClass(String fclass) {
		this.pfClass = fclass;
	}
	public String getAliasOp() {
		return aliasOp;
	}
	public void setAliasOp(String aliasOp) {
		this.aliasOp = aliasOp;
	}
	public String getPfType() {
		return pfType;
	}
	public void setPfType(String pfType) {
		this.pfType = pfType;
	}
	public E_Object getQualifyingObject() {
		return this.qualifyingObject;
	}
	public void setQualifyingObject(E_Object qualifyingObject) {
		this.qualifyingObject = qualifyingObject;
	}
	public LinkedList<E_MRefField> getFields() {
		return fields;
	}
	public void addFields(E_MRefField field) {
		fields.add(field);
	}
	public boolean removeField(E_MRefField field) {
		return fields.remove(field);
	}

	/*
	 * public LinkedList<E_Specification> getRequiresAP_TS(){ return requires; }
	 * 
	 * public LinkedList<E_Specification> getEnsuresAP_TS(){ return ensures; }
	 */

}
