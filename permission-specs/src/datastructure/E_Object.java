package datastructure;

import java.util.Objects;

import org.eclipse.jdt.core.dom.IVariableBinding;

public class E_Object {
	
	private IVariableBinding otherObj;
	
	private String thisObj;
	//private String ObjKey
	//private int objectIndex;
	private String previousObj = ""; 
	public E_Object() {
		//objectIndex = -1;
		thisObj = "this";
		otherObj = null;
	}
	public IVariableBinding getObjBind() {
		return otherObj;
	}
	public void setObjBind(IVariableBinding objBind) {
		thisObj = objBind.getName().toString();
		otherObj = objBind;
	}
	@Override
    public boolean equals(Object o) {
       boolean flag = false;
		if (o == this) return true;
        if (!(o instanceof E_Object)) {
            return false;
        }
        E_Object obj = (E_Object) o;
  
        /*if(obj.getObjBind()!= null){
            flag = obj.getObjBind().equals(otherObj) ;
        }*/
        
        	return obj.getName().equals(thisObj);
        
      }

    @Override
    public int hashCode() {
        return Objects.hash(thisObj);
    }
	public void setName(String str) {
		this.thisObj = str;
	}
	public String getName() {
		return this.thisObj;
	}
		/*
		public void setObjectIndex(int objectIndex) {
			this.objectIndex = objectIndex;
		}

		public int getObjectIndex() {
			return objectIndex;
		}*/
	public String getPreviousObj() {
		return previousObj;
	}
	public void setPreviousObj(String previousObj) {
		this.previousObj = previousObj;
	}

	}

