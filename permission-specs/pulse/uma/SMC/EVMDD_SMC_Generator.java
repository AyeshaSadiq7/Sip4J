package uma.SMC;


import java.util.LinkedList;

import uma.gap.plugin.PulseSettings;
import uma.structure.*;

public class EVMDD_SMC_Generator {

	static E_Package pkg;
	static int STATE_NOT_FOUND=1;
	EVMDD_SMC_Generator(){	
		
	}
	
	public static void reset(){	
		pkg=new E_Package();
	}
	
	public static void addPkgObject(E_Package _pkg){

	}
	
	public static E_Package getPkgObject(){
		return pkg;
	}
	
	public static String modifyConstructorSpecifications(String prog){
		
			E_Class _class= pkg.getClasses().getLast();
			for (E_Method _method: _class.getMethods()){
				if (_method.getName().compareTo(_class.getName())==0){
					_method.getRequiresAP_TS().clear();
					_method.getEnsuresAP_TS().getFirst().setAP("Unique");
					 
					 int req=prog.indexOf("requires=");
					 int ens=prog.indexOf("ensures=");
					 if (req!=-1 && ens!=-1)
					 prog=prog.substring(0,req)+"ensures=\"Unique"+prog.substring(ens+13,prog.length());
					 return prog;
				}
			}
		return prog;
	
	}
	
	public static void addCase(){
		
		// This make a required copy- It is not exact clone
		E_Class _class=	pkg.getClasses().getLast();
		E_Method _method=_class.getMethods().getLast();
		E_Method _cloneMethod= new E_Method();
		_cloneMethod.setName(_method.getName().toString());
		_cloneMethod.setCaseNumber(_method.getCaseNumber()+1);
		_cloneMethod.setIdentifier(_method.getName().toString()+"Case"+_cloneMethod.getCaseNumber());
		
		for (E_Parameter _para:_method.getParameters()){
			E_Parameter _cloneParameter= new E_Parameter(_para.getName().toString(),_para.getType().toString(),_para.getNumber());
			_cloneMethod.addParameter(_cloneParameter);
		}
		_class.addMethod(_cloneMethod);
	}
	public static void addState(String stateName){
		E_Class _class=	pkg.getClasses().getLast();
		E_State _st=new E_State(stateName, 0);	
		_class.addState(_st);
	}
	public static void addRequiresAP_TS(String ap, String ts){
	
		E_Class _class=	pkg.getClasses().getLast();
		E_Method _method=_class.getMethods().getLast();
		LinkedList<E_Specification> _listspec=_method.getRequiresAP_TS();
		
		E_Specification _spec= new E_Specification();
		_spec.setAP_TS(ap.toString(),ts.toString());
		_listspec.add(_spec);
	}
	
	
	public static void addRequiresParam_AP_TS(String ap, String ts, String argumentNumber){
		
		E_Specification _spec= new E_Specification();
		_spec.setAP_TS(ap.toString(),ts.toString());
		
		E_Class _class=pkg.getClasses().getLast();
		E_Method _method=_class.getMethods().getLast();
		
		E_Parameter _parameter= _method.getParameters().get(Integer.parseInt(argumentNumber));
		_parameter.getRequiresAP_TS().add(_spec);
		
	}
	
 public static void addEnsuresResult_AP_TS(String ap, String ts){

	 		E_Specification _spec= new E_Specification();
			_spec.setAP_TS(ap.toString(),ts.toString());
			
			E_Class _class=pkg.getClasses().getLast();
			E_Method _method=_class.getMethods().getLast();
			
			E_Parameter _result= new E_Parameter("result",_method.getReturnType(),-1); // we treat result as a parameter
			_result.getEnsuresAP_TS().add(_spec);
		   _method.getParameters().add(_result);
			 
	}
  public static void addEnsuresParam_AP_TS(String ap, String ts, String argumentNumber){
		
	  	E_Specification _spec= new E_Specification();
		_spec.setAP_TS(ap.toString(),ts.toString());
		
		E_Class _class=pkg.getClasses().getLast();
		E_Method _method=_class.getMethods().getLast();
		
		E_Parameter _parameter= _method.getParameters().get(Integer.parseInt(argumentNumber));
		_parameter.getEnsuresAP_TS().add(_spec);
	}
	
	public static void addEnsuresAP_TS(String ap, String ts){
		
		E_Class _class=	pkg.getClasses().getLast();
		E_Method _method=_class.getMethods().getLast();
		LinkedList<E_Specification> _listspec=_method.getEnsuresAP_TS();
		
		E_Specification _spec= new E_Specification();
		_spec.setAP_TS(ap.toString(),ts.toString());
		_listspec.add(_spec);
	}
	public static void addStateInvariant(String accessPermission,
			String variable, String state) {
	
		if (PulseSettings.getInvariants()==1 ||PulseSettings.getInvariants()==3 ||PulseSettings.getFullModel()==1){
			E_Invariant inv= new E_Invariant(accessPermission,variable,state);
			E_State _state=pkg.getClasses().getLast().getStates().getLast();
			_state.addInvariant(inv);
		}
	}
	
	public static void addBoolStateInvariant(String variable,
			String operator, String value) {
		if (PulseSettings.getInvariants()==2 ||PulseSettings.getInvariants()==3 ||PulseSettings.getFullModel()==1){
		
			if (operator.compareTo("==")==0){
			
			if (value.compareToIgnoreCase("NULL")==0)
				value="0";
			else if (value.compareToIgnoreCase("FALSE")==0) 
				value="0";
			else if (value.compareToIgnoreCase("TRUE")==0)
				value="1";
		}
		else if (operator.compareTo("!=")==0){
			if (value.compareToIgnoreCase("NULL")==0)
				value="1";
			else if (value.compareToIgnoreCase("FALSE")==0) 
				value="1";
			else if (value.compareToIgnoreCase("TRUE")==0)
				value="0";
		}
		
		if (pkg.getClasses().getLast().getVariablesofBooleanInvariants().contains(variable)==false)
			pkg.getClasses().getLast().getVariablesofBooleanInvariants().add(variable);
		
		E_Bool_Invariant inv= new E_Bool_Invariant(variable,value);
		E_State _state=pkg.getClasses().getLast().getStates().getLast();
		_state.addBoolInvariant(inv);
	}
	}
	public static void addDimension(String name){
		
		if (PulseSettings.getDimensions()==1 ||PulseSettings.getFullModel()==1){
			
			E_Dim dim= new E_Dim(name);
			pkg.getClasses().getLast().addDimension(dim);
		}
		
	}
	
	public static void addDimensionValue(String value){
		
		if (PulseSettings.getDimensions()==1 ||PulseSettings.getFullModel()==1){
			pkg.getClasses().getLast().getDimensions().getLast().addValue(value);
		}
		
	}
	
}
