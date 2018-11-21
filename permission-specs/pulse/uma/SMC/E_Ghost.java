package uma.SMC;

public class E_Ghost {
	
	String ghostName;
	int invLowValue;
	int invHighValue;
	public void setDimensionName(String str) {
		ghostName=str;
		
	}
	public void setDimensionValues(int low, int high) {
		
		invLowValue=low;
		invHighValue=high;
		
	}
	public String getDimensionName(){
		return ghostName;
	}
	public int getLowValueofInv(){
		return invLowValue;
	}
	public int getHighValueofInv(){
		return invHighValue;
	}

}
