package datastructure;

import java.util.ArrayList;

public class E_Dim {
	
	String dimensionName;
	ArrayList<String> values;
	
	public E_Dim(String name){
		
		dimensionName=name;
		values=new ArrayList<String>();
		
	}
	
	public void setName(String str){
		dimensionName=str;	
	}
	
	public String getName(String str){
		return dimensionName;	
	}
	
	public void addValue(String str){
		values.add(str);
		
	}
	
	public ArrayList<String> getValues(){
		return values;
	}

}
