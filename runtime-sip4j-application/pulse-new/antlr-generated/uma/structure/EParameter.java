package uma.structure;


import java.util.HashMap;

import java.util.LinkedList;

public class EParameter {
	
	String name;
	String type;
	boolean and;
	int position;
	
	LinkedList<ESpecification> requires;
	
	LinkedList<ESpecification> ensures;
	
	public EParameter(String name, String type,int position){
	
		requires=new LinkedList<ESpecification> ();
		ensures=new LinkedList<ESpecification> ();
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

	public LinkedList<ESpecification> getRequiresAPTS(){
		return requires;
	}
	
	public LinkedList<ESpecification> getEnsuresAPTS(){
		return ensures;	
	}

}
