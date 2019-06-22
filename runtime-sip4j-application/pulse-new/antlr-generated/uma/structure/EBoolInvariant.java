package uma.structure;

// This class stores invariant of type data!= null or flag=flase etc

public class EBoolInvariant {
	
	String variable;
	String value;
	
	
	public EBoolInvariant(String variable, String value){
		this.variable=variable;
		this.value=value;
	
	}
	
	public String getVariable(){
		return variable;
	}

	public String getValue(){
		return value;
	}
}
