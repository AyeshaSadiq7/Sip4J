package datastructure;

import java.util.LinkedList;


public class E_MLocalVariable {

	private String vname;
	
	private String vtype;
	
	private E_Method declarMethod;
	
	private String aliasOp;
	
	// add local variables of methods as part of aliases of this referenced field 
	
	LinkedList<E_MRefAlias> aliases;
		
	public E_MLocalVariable(String name, String type, E_Method method, String aliasOp,boolean retExp) {
		
		this.vname = name;
		this.vtype = type;
		this.declarMethod = method;
		this.aliasOp = aliasOp;
		//aliases
		aliases = new LinkedList<E_MRefAlias>();
	}
	
	
	public String getMOperation() {
		return aliasOp.toString();
	}

	public void setMOperation(String op) {
		aliasOp = op;
	}

	public void setName(String str) {
		vname = str;
	}

	public void setType(String str) {
		vtype = str;
	}

	public String getName() {
		return vname.toString();
	}

	public String getType() {
		return vtype.toString();
	}

	public E_Method getDeclMethod() {
		return declarMethod;
	}

	public void setDeclMethod(E_Method dc) {
		declarMethod = dc;
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

	

	
}
