package uma.gap.plugin;

public class PulseSettings {
	
	static int aliasPerObject=0;
	static int dimesnions=0;
	static int inheritance=0;
	static int invaraints=0;
	static int fullModel=0;
	
	public static void setAliasPerObject(int x){
		if (x>1)
		aliasPerObject = 1;
		else
		aliasPerObject = x;
	}
	public static void setDimensions(int x){
		dimesnions=x;
	}
	
	public static void setInheritance(int x){	
		inheritance = x;
		
	}
	public static void setFullModel(int x){	
		fullModel=x;
		
	}
	public static int getFullModel(){	
		return fullModel;
		
	}
	
	
	public static void setInvariants(int x){
		invaraints=x;	
	}
	
	public static int getAliasPerObject(){	
		return aliasPerObject;
	}
	public static int getDimensions(){
		return dimesnions;
	}
	
	
	public static int getInvariants(){
		return invaraints;	
	}
	public static int getInheritance() {
		
		return inheritance;
	}

}
