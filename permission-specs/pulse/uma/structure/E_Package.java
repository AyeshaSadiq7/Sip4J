package uma.structure;


import java.util.LinkedList;

public class E_Package {
	
	String pkgName;
	LinkedList<E_Class> classes;
	
	String sinkStates;
	
	public E_Package(){
		
		classes=new LinkedList<E_Class> ();
	
		sinkStates="";
		
	}
	
	public void setName(String str)
	{
		pkgName=str;	
	}
	public String getName()
	{
		return 	pkgName.toString();
	}
	
	public LinkedList<E_Class> getClasses()
	{
		return 	classes;
	}
	
	public int getTotalStates(){
		int count=0;
		
		for(E_Class _class: classes)
		
			count=count+_class.getTotalStates();
		
		return count;
		
	}
	public int getTotalReachableStates(){
		int count=0;
		for(E_Class _class: classes)
			count=count+_class.getTotalReachableStates();
		return count;
		
	}

	public void setSinkStates(String sinkStates) {
		
		this.sinkStates=sinkStates;
	}
	
	public String getSinkStates() {
		
		return sinkStates;
	}
	

}
