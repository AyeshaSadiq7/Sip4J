package uma.structure;

public class ESpecification implements Cloneable {
	
	boolean and; // used to store 'and' or 'or' part of specification
	String ap;   
	String ts;
	String ap_ts; // used to store access permission or typestate
	private String fieldType;
	private String fieldName;
	private EClass _class;
	private EMethod _method;
	
	public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
	}
	
	public ESpecification(){
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
	
	public void setAPTS(String ap, String ts){
		this.ap=ap;
		this.ts=ts;
	}
	public void setAP(String ap){
		this.ap=ap;
	}
	public void setAPTS(String ap, String ts, EClass _class, String fieldName) { // used for inheritance 
	
		this.ap=ap;
		this.ts=ts;
		this._class=_class;
		this.fieldName=fieldName;
	}
	
	public EClass getParentClass(){
		return _class;
		
	}

	public String getFieldName() {
		// TODO Auto-generated method stub
		return fieldName;
	}
	

}
