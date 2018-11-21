package uma.structure;

public class E_Specification implements Cloneable {
	
	boolean and; // used to store 'and' or 'or' part of specification
	String ap;   
	String ts;
	String ap_ts; // used to store access permission or typestate
	private String fieldType;
	private String fieldName;
	private E_Class _class;
	private E_Method _method;
	
	public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
	}
	
	public E_Specification(){
		_class=null;	
	}
	public String getAP()
	{
		return ap.toString();
	}
	
	public String getTS()
	{
		return ts.toString();
	}
	
	public void setAP_TS(String ap, String ts){
		this.ap=ap;
		this.ts=ts;
	}
	public void setAP(String ap){
		this.ap=ap;
	}
	public void setAP_TS(String ap, String ts, E_Class _class, String fieldName) { // used for inheritance 
	
		this.ap=ap;
		this.ts=ts;
		this._class=_class;
		this.fieldName=fieldName;
	}
	
	public E_Class getParentClass(){
		return _class;
		
	}

	public String getFieldName() {
		// TODO Auto-generated method stub
		return fieldName;
	}
	

}
