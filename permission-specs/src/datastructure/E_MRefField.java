package datastructure;

import java.util.LinkedList;


public class E_MRefField {

	private String fname;
	private String ftype;
	private String declaringType;// It should be E-Class object
	private E_Method refMethod;// the method reference field belong to, it is not the declaring method of some reference field
	
	// add local variables of methods as part of aliases of this referenced field 
	LinkedList<E_MRefAlias> aliases;
	
	private int classIndex;
	boolean  isRetFiel;
	// Type of Field expression 
	private int expType;
	// Operations on the referenced field
	private String mOperation;
	private String aliasOp;
	private String cOperation;
	//generated permissions
	private String prePermission;
	private String postPermission;
	boolean isLibraryMethod = false;
	boolean isField = false;
	boolean isParam = false;
	
	private E_Object qualifyingObject; // if any
	
	public E_MRefField(String name, String type, String dclass, String op, E_Method m, int eType, boolean retExp,E_Object qualObj,boolean fflag, boolean ppflag) {
		this.fname = name;
		this.ftype = type;
		this.declaringType = dclass;
		this.refMethod = m;
		this.expType = eType;
		this.isRetFiel = retExp;
		//Class and object
		this.classIndex = -1;
		// operation
		this.mOperation = new String(op);
		this.isField = fflag;
		this.isParam = ppflag;
		//this.isLibraryMethod = isMethodfromSource;
		//aliases
		aliases = new LinkedList<E_MRefAlias>();
		if(qualObj!=null){
			qualifyingObject = qualObj;
		}
	}
	public String getMOperation() {
		return mOperation;
	}

	public void setMOperation(String op) {
		mOperation = op;
	}

	public void setName(String str) {
		fname = str;
	}

	public void setType(String str) {
		ftype = str;
	}

	public String getName() {
		return fname;
	}

	public String getType() {
		return ftype;
	}

	public String getDeclaringClass() {
		return declaringType;
	}

	public void setDeclaringClass(String dc) {
		declaringType = dc;
	}

	public String getCOperation() {
		return cOperation;
	}

	public void setCOperation(String cop) {
		this.cOperation = cop;
	}

	public E_Method getMethod() {
		return refMethod;
	}

	public void setMethodname(E_Method m) {
		this.refMethod = m;
	}

	public int getExpType() {
		return expType;
	}

	public void setExpType(int expType) {
		this.expType = expType;
	}
	public String getPrePermission() {
		return prePermission;
	}
	public void setPrePermission(String prePermission) {
		this.prePermission = prePermission;
	}

	public String getPostPermission() {
		return postPermission;
	}

	public void setPostPermission(String postPermission) {
		this.postPermission = postPermission;
	}

	public E_Object getQualifyingObject() {
		return qualifyingObject;
	}

	public void setQualifyingObject(E_Object qualifyingObject) {
		this.qualifyingObject = qualifyingObject;
	}
	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
	}
	public int getClassIndex() {
		return classIndex;
	}
	public LinkedList<E_MRefAlias> getAliases() {
		return aliases;
	}
	public void addAlias(E_MRefAlias a) {
		aliases.add(a);
	}
	public boolean removeAlias(E_MRefAlias a) {
		return aliases.remove(a);
	}

	public boolean isRetFiel() {
		return isRetFiel;
	}

	public void setRetFiel(boolean isRetFiel) {
		this.isRetFiel = isRetFiel;
	}
	public String getAliasOp() {
		return aliasOp;
	}
	public void setAliasOp(String aliasOp) {
		this.aliasOp = aliasOp;
	}
	public boolean isField() {
		return isField;
	}
	public void setField(boolean isField) {
		this.isField = isField;
	}
	public boolean isParam() {
		return isParam;
	}
	public void setParam(boolean isParam) {
		this.isParam = isParam;
	}
	public boolean isLibraryMethod() {
		return isLibraryMethod;
	}
	public void setLibraryMethod(boolean isLM) {
		this.isLibraryMethod = isLM;
	}
}
