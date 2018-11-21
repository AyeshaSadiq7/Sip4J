package uma.structure;

import java.util.LinkedList;

public class E_State {
	
	String name;
	int reachable=0;
	LinkedList<E_Invariant> invariants;
	LinkedList<E_Bool_Invariant> boolInvariants;
	private int stateIndex;
	LinkedList<String> reachableStates;
	
	public E_State(String name, int flag){
		this.name=name;
		reachable=0;
		invariants=new LinkedList<E_Invariant>();
		boolInvariants=new LinkedList<E_Bool_Invariant>();
		reachableStates= new LinkedList<String>();
	}
	public void addInvariant(E_Invariant inv){
		invariants.add(inv);
	}
	public void addBoolInvariant(E_Bool_Invariant inv){
		boolInvariants.add(inv);
	}
	
	public LinkedList<E_Bool_Invariant> getBoolInvariants() {
		// TODO Auto-generated method stub
		return boolInvariants;
	}
	
	public String getName(){
		return this.name;
		
	}
	public int isReachable(){
		return reachable;
	}
	public void setReachability(int value){
		reachable=value;
	}
	public LinkedList<E_Invariant> getInvariants() {
		// TODO Auto-generated method stub
		return invariants;
	}
	public void setIndex(int stateIndex) {
		this.stateIndex=stateIndex;
		
	}
	public int getStateIndex() {
		return stateIndex;
		
	}
	public void setReachability(String toState) {
		
		reachableStates.add(toState);
	}
	
	public Boolean isReachableState(String str){
		
		for (String state:reachableStates){
			if (state.compareTo(str)==0)
				return true;
		}
		
		return false;
		
	}
	
	public Boolean isReachableState(){
		
		if (name.compareTo("alive")==0)
			return true;
		
		if (reachableStates.size()==0)
			return false;
		else
			return true;
		
	}
	

}
