package uma.structure;
import java.util.LinkedList;

public class E_Method implements Cloneable {
	
	String name;
	String identifier;
	String returnType;
	String JMLPermission="Pure";
	int case_Number;
	boolean and;	
	boolean requiresSatisfiability;
	
	LinkedList<E_Specification> requires;
	
	LinkedList<E_Specification> ensures;
	
	LinkedList<E_Parameter> parameters;
	
	LinkedList<String> concurrentMethods;
	
	private int methodIndex;
	
	private String methodSpecifications; 
	
	public E_Method(){
		requires=new LinkedList<E_Specification> ();
		ensures=new LinkedList<E_Specification> ();
		parameters=new LinkedList<E_Parameter>();
		concurrentMethods=new LinkedList<String>();
		case_Number=0;
		methodIndex=0;
		requiresSatisfiability=false;
	}
	
	public void setJMLPermission(String Permission){
		JMLPermission=Permission;
	}
	public String getJMLPermission(){
		return JMLPermission.toString();
	}
	public int getCaseNumber(){
		return case_Number;
	}
	
	public void setCaseNumber(int x){
		case_Number=case_Number+x;
	}
	
	public void setName(String str){
		name=str;
	}
	
	public String getName(){
		return name.toString();
	}
	
	public void setReturnType(String str){
		returnType=str;
	}
	
	public String getReturnType(){
		return returnType.toString();
	}
	
	public void setIdentifier(String str){
		identifier=str;
	}
	
	public String getIdentifier(){
		return identifier.toString();
	}

	public void addParameter(E_Parameter parameter ){
		parameters.add(parameter);
	}
	public LinkedList<E_Specification> getRequiresAP_TS(){
		return requires;
	}
	
	public LinkedList<E_Specification> getEnsuresAP_TS(){
		return ensures;	
	}
	public LinkedList<E_Parameter> getParameters(){
		return parameters;	
	}


	public void setIndex(int methodIndex) {
		this.methodIndex=methodIndex;
	}
	public int getIndex() {
		return methodIndex;
	}


	public void setRequiresClauseSatisfiability(Boolean flag) {
		
		requiresSatisfiability=flag;
		
	}

	public void setConcurrentMethod(String toMethod) {
		concurrentMethods.add(toMethod);
	}
	public LinkedList<String> getConcurrentMethod() {
		return concurrentMethods;
	}
	public boolean getRequiresClauseSatisfiability() {
		
		return requiresSatisfiability;
	}
	public boolean isConcurrentMethod(String str) {
		
		for (String name:concurrentMethods){
			if (name.compareTo(str)==0)
				return true;
			
		}
		
		return false;
	}

	
	public Boolean isConcurrentMethod() {
		
		if (concurrentMethods.size()==0)
			return false;
		else
			return true;
	}
	public void addSpecifications(String annotation) {
		
		methodSpecifications = annotation;
		
	}


}
