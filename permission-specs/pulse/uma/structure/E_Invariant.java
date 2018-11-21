package uma.structure;

import java.util.LinkedList;

public class E_Invariant {
	
	String ap;
	String variable;
	String stateName;
	String classType;
	
	public E_Invariant(String accessPermission, String var, String st){
		ap=accessPermission;
		variable=var;
		stateName=st;
	}
	
	public void setAP(String str){
		ap=str;	
	}
	public void setVariable(String str){
		variable=str;	
	}
	public void setState(String str){
		stateName=str;	
	}
	
	
	public String getAP(){
		return ap;	
	}
	public String getVariable(){
		return variable;	
	}
	public String getStateName(){
		return stateName;	
	}
	
	public LinkedList<E_Invariant> getStateInvariants(E_Package _pkg){
		
		for(E_Class _class:_pkg.getClasses()){
			if (_class.getName().compareTo(classType)==0){
				for(E_State _state:_class.getStates()){
					if (_state.getName().compareTo(stateName)==0){
						return _state.getInvariants();
					
					}
				}
			}
		}
		
		return null;	
	}

	public void setStateIndex(int stateIndex) {
		// TODO Auto-generated method stub
		
	}

	public void setVariableType(String type) {
		classType=type;
		
	}

	public String getVariableType() {
		// TODO Auto-generated method stub
		return classType;
	}
	
}
