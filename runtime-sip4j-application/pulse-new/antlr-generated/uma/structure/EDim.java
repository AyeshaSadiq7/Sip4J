package uma.structure;

import java.util.ArrayList;

public class EDim {
	
	String dimensionName;
	ArrayList<String> values;
	
	public EDim(String name){
		
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
