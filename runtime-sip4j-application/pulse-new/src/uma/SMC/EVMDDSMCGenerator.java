package uma.SMC;


import java.util.LinkedList;

import uma.gap.plugin.PulseSettings;
import uma.structure.*;

public class EVMDDSMCGenerator {

	static EPackage pkg;
	
	static int STATE_NOT_FOUND=1;
	public static void reset(){	
		pkg = new EPackage();
	}
	
	EVMDDSMCGenerator(){	
		
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
		
		EBoolInvariant inv= new EBoolInvariant(variable,value);
		EState _state=pkg.getClasses().getLast().getStates().getLast();
		_state.addBoolInvariant(inv);
	}
	}
			
	
	public static void addPkgObject(EPackage _pkg){

	}
	
	public static EPackage getPkgObject(){
		return pkg;
	}
	
	public static String modifyConstructorSpecifications(String prog){
		
			EClass _class= pkg.getClasses().getLast();
			for (EMethod _method: _class.getMethods()){
				if (_method.getName().compareTo(_class.getName())==0){
					_method.getRequiresAPTS().clear();
					_method.getEnsuresAPTS().getFirst().setAP("Unique");
					 
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
		EClass _class=	pkg.getClasses().getLast();
		EMethod _method=_class.getMethods().getLast();
		EMethod _cloneMethod= new EMethod();
		_cloneMethod.setName(_method.getName().toString());
		_cloneMethod.setCaseNumber(_method.getCaseNumber()+1);
		_cloneMethod.setIdentifier(_method.getName().toString()+"Case"+_cloneMethod.getCaseNumber());
		
		for (EParameter _para:_method.getParameters()){
			EParameter _cloneParameter= new EParameter(_para.getName().toString(),_para.getType().toString(),_para.getNumber());
			_cloneMethod.addParameter(_cloneParameter);
		}
		_class.addMethod(_cloneMethod);
	}
	public static void addState(String stateName){
		EClass _class=	pkg.getClasses().getLast();
		EState _st = new EState(stateName, 0);	
		_class.addState(_st);
	}
	public static void addRequiresAPTS(String ap, String ts){
	
		EClass _class=	pkg.getClasses().getLast();
		EMethod _method=_class.getMethods().getLast();
		LinkedList<ESpecification> _listspec=_method.getRequiresAPTS();
		
		ESpecification _spec= new ESpecification();
		_spec.setAPTS(ap.toString(),ts.toString());
		_listspec.add(_spec);
	}
	
	
	public static void addRequiresParamAPTS(String ap, String ts, String argumentNumber){
		
		ESpecification _spec= new ESpecification();
		
		_spec.setAPTS(ap.toString(),ts.toString());
		
		EClass _class = pkg.getClasses().getLast();
		
		EMethod _method=_class.getMethods().getLast();
		
		EParameter _parameter= _method.getParameters().get(Integer.parseInt(argumentNumber));
		
		_parameter.getRequiresAPTS().add(_spec);
		
	}
	
 public static void addEnsuresResultAPTS(String ap, String ts){

	 		ESpecification _spec= new ESpecification();
			_spec.setAPTS(ap.toString(),ts.toString());
			
			EClass _class = pkg.getClasses().getLast();
			EMethod _method=_class.getMethods().getLast();
			
			EParameter _result= new EParameter("result",_method.getReturnType(),-1); // we treat result as a parameter
			_result.getEnsuresAPTS().add(_spec);
		   _method.getParameters().add(_result);
			 
	}
  public static void addEnsuresParamAPTS(String ap, String ts, String argumentNumber){
		
	  	ESpecification _spec= new ESpecification();
		_spec.setAPTS(ap.toString(),ts.toString());
		
		EClass _class=pkg.getClasses().getLast();
		EMethod _method=_class.getMethods().getLast();
		
		EParameter _parameter= _method.getParameters().get(Integer.parseInt(argumentNumber));
		_parameter.getEnsuresAPTS().add(_spec);
	}
	
	public static void addEnsuresAPTS(String ap, String ts){
		
		EClass _class=	pkg.getClasses().getLast();
		EMethod _method=_class.getMethods().getLast();
		LinkedList<ESpecification> _listspec=_method.getEnsuresAPTS();
		
		ESpecification _spec= new ESpecification();
		_spec.setAPTS(ap.toString(),ts.toString());
		_listspec.add(_spec);
	}
	public static void addStateInvariant(String accessPermission,
			String variable, String state) {
	
		if (PulseSettings.getInvariants()==1 ||PulseSettings.getInvariants()==3 ||PulseSettings.getFullModel()==1){
			EInvariant inv= new EInvariant(accessPermission,variable,state);
			EState _state=pkg.getClasses().getLast().getStates().getLast();
			_state.addInvariant(inv);
		}
	}
	
	/*public static void addBoolStateInvariant(String variable,
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
		
		EBoolInvariant inv= new EBoolInvariant(variable,value);
		EState _state=pkg.getClasses().getLast().getStates().getLast();
		_state.addBoolInvariant(inv);
	}
	}*/
	public static void addDimension(String name){
		
		if (PulseSettings.getDimensions()==1 ||PulseSettings.getFullModel()==1){
			
			EDim dim= new EDim(name);
			pkg.getClasses().getLast().addDimension(dim);
		}
		
	}
	
	public static void addDimensionValue(String value){
		
		if (PulseSettings.getDimensions()==1 ||PulseSettings.getFullModel()==1){
			pkg.getClasses().getLast().getDimensions().getLast().addValue(value);
		}
		
	}
	
}
