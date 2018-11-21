package uma.structure;


import java.util.HashMap;

import java.util.LinkedList;

public class E_Parameter {
	
	String name;
	String type;
	boolean and;
	int position;
	
	LinkedList<E_Specification> requires;
	
	LinkedList<E_Specification> ensures;
	
	public E_Parameter(String name, String type,int position){
	
		requires=new LinkedList<E_Specification> ();
		ensures=new LinkedList<E_Specification> ();
		this.name=name;
		this.type=type;
		this.position=position;

	}
	
	public void setNumber(int n){
		position=n;
	}
	
	public int getNumber(){
		return position;
	}

	public void setName(String str){
		name=str;
	}
	public void setType(String str){
		type=str;
	}
	public String getType(){
		return type;
	}
	
	public String getName(){
		return name.toString();
	}

	public LinkedList<E_Specification> getRequiresAP_TS(){
		return requires;
	}
	
	public LinkedList<E_Specification> getEnsuresAP_TS(){
		return ensures;	
	}

}
