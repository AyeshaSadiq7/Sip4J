package uma.SMC;
public class Time {
	
	public Integer hour = 0;
	public Integer minute =0; 
	public Integer second =0; 
	public long msecond=0;
	String str;
	public Time(){
		
	}
	public String toString()
	{
		str=hour+":"+minute+":"+second;
		return str;
		
	}
	

}
