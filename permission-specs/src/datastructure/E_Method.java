package datastructure;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

//Set of Declared Methods
public class E_Method implements Cloneable {

	private String name;// method name
	private String identifier;// identifier
	private String returnType;// method return type
	private String declaringClass;// declaring class in which method is declared
	private String declClassQName;// declaring class Qualified Name 
	private boolean isConstr;
	private String modifier;
	private List<Expression> st;
	private List<String> state;
	
	private LinkedList<E_MParameter> parameters; // parameters list
	private LinkedList<E_MRefParameter> refparams;// parameters that represents class fields
	private LinkedList<E_MStatements> s;// set of method statements
	private LinkedList<E_MRefField> refVariables; // a data structure that stores all the class fields, class objects and parameters accessed inside method
	private LinkedList<E_MInvokedMethod> subMethods; // methods called inside a method
	private LinkedList<E_MLocalVariable> localvariables;// variables declared inside a method
	private E_Object qualifyingObject; // if any
	private String methodsignature = "";
	
	
	//private String methodSpecifications;
	// LinkedList<E_Specification> requires;
	// LinkedList<E_Specification> ensures;
	// LinkedList<String> concurrentMethods;

	public E_Method() {
		parameters = new LinkedList<E_MParameter>();
		subMethods = new LinkedList<E_MInvokedMethod>();
		s = new LinkedList<E_MStatements>();
		state = new LinkedList<String>();
		refVariables = new LinkedList<E_MRefField>();
		localvariables = new LinkedList<E_MLocalVariable>();
		refparams = new LinkedList<E_MRefParameter>();
		qualifyingObject = null;// receiver object
		
		isConstr = false;
		
	}
	@Override
    public boolean equals(Object o) {
        
		if (o == this) return true;
        
        if (!(o instanceof E_Method)) {
            return false;
        }
        E_Method method = (E_Method) o;
        return method.identifier.equals(identifier) &&
        		method.getDeclClassQName().equals(declClassQName)
        		&& method.getMethodSignatures().equals(methodsignature);
        }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, declClassQName);
    }

	public LinkedList<E_MRefField> getRefVariable() {
		return refVariables;
	}

	public void addRefVariable(E_MRefField rf) {
		refVariables.add(rf);
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

	public void setReturnType(String str) {
		returnType = str;
	}

	public String getReturnType() {
		return returnType.toString();
	}

	public void setIdentifier(String str) {
		identifier = str;
	}

	public String getIdentifier() {
		return identifier.toString();
	}

	public void addParameter(E_MParameter parameter) {
		parameters.add(parameter);
	}

	public LinkedList<E_MParameter> getParameters() {
		return parameters;
	}

	public LinkedList<E_MStatements> getStatements() {
		return s;
	}

	public LinkedList<E_MRefParameter> getRefparams() {
		return refparams;
	}

	public void addRefparams(E_MRefParameter refparam) {
		refparams.add(refparam);
	}

	public void addStatements(E_MStatements statement) {
		s.add(statement);
	}

	public LinkedList<E_MInvokedMethod> getSubMethods() {
		return subMethods;
	}

	public void addSubMethods(E_MInvokedMethod invMethod) {
		subMethods.add(invMethod);
	}

	public boolean isConstr() {
		return isConstr;
	}

	public void setConstr(boolean flag) {
		this.isConstr = flag;
	}

	public String getDeclClassQName() {
		return declClassQName;
	}

	public void setDeclClassQName(String QName) {
		this.declClassQName = QName;
	}

	public LinkedList<E_MLocalVariable> getLocalVar() {
		return localvariables;
	}

	public void addLocalVar(E_MLocalVariable var) {
		localvariables.add(var);
	}
	public void removeLocalVar(E_MLocalVariable var) {
		localvariables.remove(var);
	}


	public E_Object getQualifyingObject() {
		return qualifyingObject;
	}


	public String getMethodSignatures() {
		return methodsignature;
	}


	public void setMethodSignatures(String signature) {
		methodsignature = signature;
	}


	public void setQualifyingObject(E_Object qualifyingObject) {
		this.qualifyingObject = qualifyingObject;
	}


	public String getModifier() {
		return modifier;
	}


	public void setModifier(String mod) {
		this.modifier = mod;
	}
	
	public List<Expression> getSt() {
		return st;
	}
	public void addSt(Expression s) {
		this.st.add(s);
	}
	public List<String> getState() {
		return state;
	}
	public void addState(List<String> state) {
		this.state= state;
	}



	/*
	 * public LinkedList<E_Specification> getRequiresAP_TS(){ return requires; }
	 * public LinkedList<E_Specification> getEnsuresAP_TS(){ return ensures; }
	 */
	/*
	 * public void setRequiresClauseSatisfiability(Boolean flag) {
	 * requiresSatisfiability=flag; } public void setConcurrentMethod(String
	 * toMethod) { concurrentMethods.add(toMethod); } public boolean
	 * getRequiresClauseSatisfiability() { return requiresSatisfiability; }
	 * public boolean isConcurrentMethod(String str) {
	 * 
	 * for (String name:concurrentMethods){ if (name.compareTo(str)==0) return
	 * true; }
	 * 
	 * return false; } public Boolean isConcurrentMethod() {
	 * 
	 * if (concurrentMethods.size()== 0) return false; else return true; }
	 * public void addSpecifications(String annotation) {
	 * 
	 * methodSpecifications=annotation;
	 * 
	 * }
	 */

}
