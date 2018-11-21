package datastructure;

import java.util.LinkedList;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;


public class E_MRefAlias {

	private LinkedList<E_MRefField> fieldAlias;// list of fields as aliases
	
	private LinkedList<E_MLocalVariable> localVarAlias;
	
	private LinkedList<E_MRefParameter> parameterAlias;
	
	private int expType; // type of expression

	private String aliasOp;
	
	private String methodOp;
	
	private IVariableBinding paramBinding;
	
	private IMethodBinding methodBinding;
	
	public E_MRefAlias(E_MRefField pointerField, E_MLocalVariable pointerLocalVariable,E_MRefParameter pointerParam, IMethodBinding methodBinding, IVariableBinding paramBind) {
		
	    fieldAlias = new LinkedList<E_MRefField>();
		
		localVarAlias = new LinkedList<E_MLocalVariable>();
		
		parameterAlias = new LinkedList<E_MRefParameter>();
		
		if(paramBind!=null){
	
			this.paramBinding = paramBind;
		}
		if(methodBinding!=null){
			
			this.methodBinding = methodBinding;
		}
    }
	public LinkedList<E_MLocalVariable> getLocalVarAlias() {
		return localVarAlias;
	}
	public LinkedList<E_MRefParameter> getParamAlias() {
		return parameterAlias;
	}
	public LinkedList<E_MRefField> getFieldAlias() {
		return fieldAlias;
	}
	
	public boolean addLocalVarAlias(E_MLocalVariable var) {
		return localVarAlias.add(var);
	}
	public boolean removeLocalVarAlias(E_MLocalVariable var) {
		return localVarAlias.remove(var);	
	}
	
	public boolean addParameterAlias(E_MRefParameter param) {
		return parameterAlias.add(param);
	}

	public boolean removeParameterAlias(E_MRefParameter param) {
		return parameterAlias.remove(param);
	}
	public boolean addFieldAlias(E_MRefField field) {
		return fieldAlias.add(field);
	}

	public boolean removeFieldAlias(E_MRefField field) {
		return fieldAlias.remove(field);
	}

	public int getExpType() {
		return expType;
	}

	public void setExpType(int expType) {
		this.expType = expType;
	}
	

	public IVariableBinding getParaBind() {
		return paramBinding;
	}

	public void setParaBind(IVariableBinding paraBind) {
		this.paramBinding = paraBind;
	}
	public String getOp() {
		return aliasOp;
	}
	public void setOp(String op) {
		this.aliasOp = op;
	}
	public IMethodBinding getMethodBinding() {
		return methodBinding;
	}

	public void setMethodBinding(IMethodBinding methodBinding) {
		this.methodBinding = methodBinding;
	}
	public String getMethodOp() {
		return methodOp;
	}
	public void setMethodOp(String methodOp) {
		this.methodOp = methodOp;
	}
	
}
