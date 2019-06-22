package datautilities;


import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import parser.AST_Parser;
import parser.GlobalVariables;

import datastructure.*;

public class Data_Controller {

	public static void showData(){	

		//E_LocalAliases alias = new E_LocalAliases(name, type, dm, op, eType, initMethod, initClass)

		LinkedList<E_Class> eclass = fetchAllClasses();

		for(E_Class _class : eclass){
			if(_class!=null){
				System.out.println("*******************************************************************************************");
				System.out.println("class "+_class.getName());
				//System.out.println("class Index "+E_Class.getLastClassIndex());
				System.out.println("class Qualified Name "+_class.getClassQName());
				if(_class.getSuperClass() !=null){
					System.out.println("Parent clas "+_class.getSuperClass().getName());
				}
				System.out.println("**************************************");
				System.out.println("Fields");
				System.out.println("");
				showClassField(_class);
				System.out.println("");
				System.out.println("**************************************");
				System.out.println("Methods");
				System.out.println("");
				showClassMethods(_class);
				System.out.println("");
			}
		}

	}
	static void showClassField(E_Class _class){
		LinkedList<E_Field> list =_class.getFields();
		if(list!=null){
			for (E_Field f:list){
				System.out.println("Name="+f.getName().toString()+" Type="+f.getType().toString()+" Modifier="+f.getModifier()+" Class Index="+f.getClassIndex());		}

		}
	}
	@SuppressWarnings("null")
	static void showClassMethods(E_Class _class){

		LinkedList<E_Method> _methds = _class.getMethods();

		LinkedList<E_InvokedMethod> _invokedMethods = _class.getInvokMethods();

		/*System.out.println("Invoked Methods");

		for (E_InvokedMethod im:_invokedMethods){
			    System.out.println("*******************************************************************************************");
				System.out.println("M-Name = "+im.getName());
				System.out.println("");
				System.out.println("M-Dec-Class="+im.getDeclaringClass());
				System.out.println("");

				LinkedList<E_Argument> _arg = im.getArguments();

				for(E_Argument a : _arg){
					System.out.println("***************************************");
					System.out.println("M-Arguments= "+a.getName()+", Type = "+a.getType()+" pos= "+a.getPosition()+" isField = "+a.isField()+" isLocal "+a.isLocal()+" is parameter = "+a.isParameter());
				}

			System.out.println("***************************************");
		}*/
	
		for (E_Method m:_methds){
		
		System.out.println("*******************************************************************************************");
		System.out.println("Method name = "+m.getName());
    	System.out.println("");
	    System.out.println("Declaring class = "+m.getDeclaringClass()+" Qualified Name of Class = "+m.getDeclClassQName());
	    if(m.getQualifyingObject()!=null){
	    	System.out.println("Receiver object = "+m.getQualifyingObject().getName().toString());
	    }
		/*if(m.getName().equals("main")){
			
		}
		else{*/
	    
		LinkedList<E_MRefParameter> _param = m.getRefparams();

		if(_param!=null){
		
			for(E_MRefParameter p : _param){
			
			displayParamter(p);
			
			System.out.println("************************************");
			
			LinkedList<E_MRefAlias> param_alias = Data_Controller.fetchAliasesOfParams(p);
			
			LinkedList<E_MRefParameter> paramAliases = new LinkedList<E_MRefParameter>();
			
			LinkedList<E_MRefField> fAliases = new LinkedList<E_MRefField>();
			
			System.out.println("Start of Parameter Aliase******************************************");
			
			if(param_alias != null && !(param_alias.isEmpty())){
				
				//fetchParametersAliases(param_alias,paramAliases);
				
				//fetchLocalAliases(param_alias,fAliases,paramAliases);
			
				//fetchFieldAliases(param_alias,fAliases,paramAliases);
			
				System.out.println("End of Parameter Aliase**************************");
			}
			else{
				System.out.println("No Parameter Aliase**************************");
			 }
			}	
		}
		System.out.println("Field of method continue = "+m.getIdentifier());
		
		LinkedList<E_MRefField> _ref = null;
	
		LinkedList<E_MRefAlias> aliases =  new LinkedList<E_MRefAlias>();
	
		_ref = m.getRefVariable();
	
		System.out.println("*********************************");
		
	    if(_ref != null){
			
			for(E_MRefField rf : _ref){
				
				displayField(rf);
				
				System.out.println("************************************");
				
				aliases = Data_Controller.fetchAliasesOfRefField((rf));
				
				LinkedList<E_MRefField> fAliases = new LinkedList<E_MRefField>();
				
				LinkedList<E_MRefParameter> paramAliases = new LinkedList<E_MRefParameter>();
				
				System.out.println("Start of Reference Field Aliase******************************************");
				
				if(aliases!=null && !(aliases.isEmpty())){
					//fetchFieldAliases(aliases,fAliases,paramAliases);
					//fetchLocalAliases(aliases,fAliases,paramAliases);
					//fetchParametersAliases(aliases,paramAliases);
					System.out.println("End of Referenced Fields Aliase**************************");
				}
				
				else{
					System.out.println("No Referenced Fields Aliase**************************");
					
				}

			}
	  //  }
		}
	 }
	}
	public static LinkedList<E_Class> fetchAllClasses(){

		E_Package pkg = Data_Generator.getPackage();
		
		LinkedList<E_Class> _class = null;
		
		if(pkg != null){
			
			if(pkg.getClasses() !=null)
				
			  _class = pkg.getClasses();
		}
		return _class;
	}

	public static LinkedList<E_Method> fetchAllMethods(){

		E_Package pkg = Data_Generator.getPackage();

		LinkedList<E_Class> eclass = pkg.getClasses();
		
		LinkedList<E_Method> _methds = new LinkedList<E_Method>();

		for(E_Class _class: eclass){
			if(_class.getMethods() != null){		
				LinkedList<E_Method> m = _class.getMethods();
				for(E_Method ms: m){
					_methds.add(ms);
				}
			}
		}
		return _methds;
	}
	public static LinkedList<E_MRefField> fetchAllReference(){

		LinkedList<E_Method> _methds = fetchAllMethods();

		LinkedList<E_MRefField> _fields = new LinkedList<E_MRefField>();

		for(E_Method m:_methds){
			LinkedList<E_MRefField> f = m.getRefVariable();
			for(E_MRefField  ff : f){
				_fields.add(ff);
			}
		}

		return _fields;
	}
	public static LinkedList<E_MRefField> fetchMRefFields(IMethodBinding method){

		LinkedList<E_Method> _methds = fetchAllMethods();

		LinkedList<E_MRefField> _fields = new LinkedList<E_MRefField>();

    	for(E_Method m:_methds){
			
			if(m.getName().equals(method.getName()) && m.getDeclClassQName().equals(method.getDeclaringClass().getQualifiedName())){	
			
				LinkedList<E_MRefField> f = m.getRefVariable();
				
				for(E_MRefField  ff: f){
					
					_fields.add(ff);
				}
			}
		}

		return _fields;
	}
	public static LinkedList<E_MRefParameter> fetchMRefParameters(E_Method method){

		LinkedList<E_Method> _methds = fetchAllMethods();

		LinkedList<E_MRefParameter> _params = new LinkedList<E_MRefParameter>();

		for(E_Method m:_methds){
			
			if(m.getName().equals(method.getName()) && m.getDeclClassQName().equals(method.getDeclClassQName())){	
			
				LinkedList<E_MRefParameter> params = m.getRefparams();
				
				for(E_MRefParameter  pp: params){
					
					_params.add(pp);
				}
			}
		}

		return _params;
	}
	public static LinkedList<E_MLocalVariable> fetchAllLocals(){

		LinkedList<E_Method> _methds = fetchAllMethods();

		LinkedList<E_MLocalVariable> _vars = new LinkedList<E_MLocalVariable>();

		for(E_Method m:_methds){
			
					LinkedList<E_MLocalVariable> vars = m.getLocalVar();
					
					for(E_MLocalVariable  var: vars){
					
						_vars.add(var);
					}
		}
			
		return _vars;
	}
	public static LinkedList<E_InvokedMethod> fetchAllInvokedMethods(){

		E_Package pkg = Data_Generator.getPackage();

		List<E_Class> eclass = pkg.getClasses();

		LinkedList<E_InvokedMethod> _methds = new LinkedList<E_InvokedMethod>();

		for(E_Class _class:eclass){
			if(_class.getInvokMethods() != null){		
				LinkedList<E_InvokedMethod> m  = _class.getInvokMethods();
				for(E_InvokedMethod ms: m){
					_methds.add(ms);
				}
			}
		}
		return _methds;
	}
	public static  E_Class fetchLastClass(){

		E_Package pkg = Data_Generator.getPackage();
		E_Class _class = null;
		if(pkg != null){
			if(pkg.getClasses() !=null){
				_class = pkg.getClasses().getLast();
			}
		}
		return _class;
	}
	public static E_Method fetchLastMethod(){
		E_Method _method = null;
		E_Package pkg = Data_Generator.getPackage();
		E_Class _class = null;
		if(pkg != null){
			if(pkg.getClasses() != null){
				_class = pkg.getClasses().getLast();
				if(!(_class.getMethods().isEmpty()))
					if(_class.getMethods().getLast() != null){
						_method = _class.getMethods().getLast();}

			}
		}
		return  _method;
	}
	public static E_MRefField searchField(String fName, String fType, String declarClass){

		E_MRefField _field = null;
		LinkedList<E_MRefField> _fields = new LinkedList<E_MRefField>();
		LinkedList<E_Method> _methods = new LinkedList<E_Method>();
		_methods = Data_Controller.fetchAllMethods();
					
		for(E_Method m: _methods){
			if(m.getRefVariable() != null ){
			_fields = m.getRefVariable();
				for (int i = 0; i < _fields.size(); i++) {
					if (_fields.get(i).getName().equals(fName)
						&& _fields.get(i).getType().equals(fType)
						&& _fields.get(i).getDeclaringClass().equals(declarClass)){
						_field = _fields.get(i);
						break;
						
					}
				}
			}

		}
					
		return  _field;
	}
	public static E_MLocalVariable searchLocalVariable1(String fName, String fType, String declMethod){

		LinkedList<E_Method> _methods = new LinkedList<E_Method>();
		
		_methods = fetchAllMethods();
		
		E_MLocalVariable _var = null;
		
		LinkedList<E_MLocalVariable> _vars = new LinkedList<E_MLocalVariable>();

		for(E_Method m: _methods){
			System.out.println("method name ="+m.getName());
			_var = searchMLocalVariable(m, fName, fType, declMethod);
			if(_var != null){
				break;
			}
			/*if(m.getLocalVar()!=null){
				_vars = m.getLocalVar();
					for (int i = 0; i < _vars.size(); i++) {
						if (_vars.get(i).getName().equals(fName)
								&& _vars.get(i).getType().equals(fType)
								&& _vars.get(i).getDeclMethod().getName().equals(declMethod)){
							
							   _var = _vars.get(i);
							   break;
						}
					}

				}	*/		
		}
							
					
		return  _var;
	}
	public static E_MLocalVariable searchLocalVariable(String fName, String fType, String declMethod){

		E_Method _method = null;

		E_MLocalVariable _var = null;

		E_Package pkg = Data_Generator.getPackage();

		LinkedList<E_MLocalVariable> _vars = new LinkedList<E_MLocalVariable>();

		LinkedList<E_Method> _methods = Data_Controller.fetchAllMethods();
		
		if(_methods != null){
			for(E_Method m :_methods){
				 _var = searchMLocalVariable(m, fName, fType, declMethod);
				 if(_var != null){
					 break;
				 }
						/*if(m.getLocalVar() != null ){
							_vars = m.getLocalVar();
							if(_vars!=null){
								for(E_MLocalVariable v: _vars){
								    if (v.getName().equals(fName)
											&& v.getType().equals(fType)
											&& v.getDeclMethod().getName().equals(declType)){
										_var = v;
									}
								}
					    	}
						}*/
				}
			}
		return  _var;
	}
	public static E_MRefField fetchLastRefField1(E_Method _method){

		E_MRefField _field = null;
		if(_method != null){
			if(_method.getRefVariable() != null )
				_field = _method.getRefVariable().getLast();
		}
		return  _field;
	}
	public static E_MLocalVariable fetchLastRefLocalVariable(E_Method _method){

		E_MLocalVariable _var = null;
		if(_method != null){
			if(_method.getLocalVar() != null )
				_var = _method.getLocalVar().getLast();
		}

		return  _var;
	}
	public static E_MRefField searchMRefField(E_Method _method,
			String fName, String fType, String declarType) {

		E_MRefField refField = null;

		LinkedList<E_MRefField> list = _method.getRefVariable();

		if(list!=null && list.isEmpty() == false){
			/*System.out.println("start Pairing");
			System.out.println("a"+fName);
			System.out.println("b"+fType);
			System.out.println("c"+declarType);
			System.out.println("*************");
		*/	for(E_MRefField data:list){
				/*System.out.println("d"+data.getName());
				System.out.println("f"+data.getType());
				System.out.println("f"+data.getDeclaringClass());
				System.out.println("End Pairing");*/
				if (data.getName().equals(fName)
					&& data.getType().equals(fType)
					&& data.getDeclaringClass().equals(declarType)){
				     refField = data;
				     break;
				  }
			}
		}


		return refField;
	}
	public static E_MLocalVariable searchMLocalVariable(E_Method _method,
			String fName, String fType, String declarmethod) {

		E_MLocalVariable variable = null;

		LinkedList<E_MLocalVariable> list = _method.getLocalVar();

		if(list.isEmpty()== false){
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getName().equals(fName)
					&& list.get(i).getType().equals(fType)
					&& list.get(i).getDeclMethod().getName().equals(declarmethod)){
					
				variable = list.get(i);
				break;
			}
		   }
		}

		return variable;
	}
	public static LinkedList<E_MInvokedMethod> fetchSubMethods(E_Method sourceMethod, LinkedList<E_MInvokedMethod> tempList){

		LinkedList<E_Method> _methds = fetchAllMethods();

		LinkedList<E_MInvokedMethod> _invm =  new LinkedList<E_MInvokedMethod>();

		if(sourceMethod.getSubMethods() != null ){

			_invm = sourceMethod.getSubMethods();
			
			if(_invm != null){

				for(E_MInvokedMethod sm: _invm){

					if(sm.isConstr()){
						
						System.out.println("constructor called");
					}
					else if(sourceMethod.getName().equals(sm.getName()) && sourceMethod.getDeclClassQName().equals(sm.getDeclClassQName())){

						System.out.println("This is a recursive method");

						tempList.addLast(sm);
					}
					else{

						tempList.addLast(sm);

						for (E_Method m:_methds){

							if(sm.getName().equalsIgnoreCase(m.getName()) && sm.getDeclClassQName().equals(m.getDeclClassQName())){

								fetchSubMethods(m, tempList);
							}
						}
					}
				}	
			}

			return tempList;
		}
		else
			return null;

	
}

	public static LinkedList<E_MInvokedMethod> fetchSubMethodCalls(E_Method sourceMethod, LinkedList<E_MInvokedMethod> tempList){
		
	LinkedList<E_MInvokedMethod> _invm =  new LinkedList<E_MInvokedMethod>();

	if(sourceMethod.getSubMethods() != null){

	   _invm = sourceMethod.getSubMethods();
	   
	 	   for(E_MInvokedMethod sm: _invm){
				
	 		   /*if(sm.isConstr()){
	 			   continue;
	 		   }
	 		   else{*/
	 			   tempList.addLast(sm);
	 		   //}	 
			   //E_Method  m = searchInvokedMethod(sm);
			  
			  //if(m != null){
				   
				 // fetchSubMethodCalls(m, tempList);
				//}
		   }
		}
	   return tempList;
	}
	
	public static LinkedList<E_MRefField> fetchSubMethodCallFields(E_MInvokedMethod inv) {

		LinkedList<E_MRefField> _refs = new LinkedList<E_MRefField>();

		E_Method m = searchInvokedMethod(inv);
	
		if(m != null){
		  LinkedList<E_MRefField> _refsTemp = m.getRefVariable();
		  if(_refsTemp!=null){
			for (E_MRefField r:_refsTemp){
				_refs.add (r);
			}
		  }
				
		}
			return _refs;

	}

	
	public static LinkedList<E_MRefField> fetchSubMethodFields(E_MInvokedMethod inv) {

		
		//LinkedList<E_Method> _methds  = fetchAllMethods();

		LinkedList<E_MRefField> _refs = new LinkedList<E_MRefField>();

	    E_Method m   =	searchInvokedMethod(inv);
		
		//for (E_Method m:_methds){

			//if(inv.getName().equalsIgnoreCase(m.getName())&& inv.getDeclClassQName().equals(m.getDeclClassQName())){

	    LinkedList<E_MRefField> _refsTemp = m.getRefVariable();

		for (E_MRefField r:_refsTemp){

			_refs.add (r);
		}
	//}
//}

		return _refs;

	}

	public static LinkedList<E_Method> fetchMainMethod(){

		E_Package pkg = Data_Generator.getPackage();

		List<E_Class> eclass = pkg.getClasses();

		LinkedList<E_Method> _methds = new LinkedList<E_Method>();

		LinkedList<E_MInvokedMethod> submethods = new LinkedList<E_MInvokedMethod>();


		for(E_Class _class:eclass){
			if(_class!= null){	
				_methds = _class.getMethods();
			}
		}
		for (E_Method m:_methds){

			if(m.getName().equalsIgnoreCase(GlobalVariables.MAIN)){

				LinkedList<E_MRefField> refs= m.getRefVariable();

				if(refs != null){

					for(E_MRefField rf : refs){
						System.out.println("Ref-Name= "+rf.getName());
						System.out.println("Type = "+ rf.getType());
						System.out.println("Decl-Class = "+rf.getDeclaringClass());
						System.out.println("Exp Type = "+rf.getExpType());
						System.out.println("M-Operation = "+rf.getMOperation());
						System.out.println("C-Operation = "+rf.getCOperation());
						System.out.println("Exp Type = "+rf.getExpType());
						System.out.println("***************************************");
					}
				}	
				submethods = fetchSubMethods(m, submethods );

				for (E_MInvokedMethod sm:submethods){

					LinkedList<E_MRefField> fields = fetchSubMethodFields(sm); 

					System.out.println("Sub-Method Name = "+sm.getName()+" is constructor "+sm.isConstr());

					if(fields != null){

						for(E_MRefField rf : fields){

							System.out.println("Ref-Name= "+rf.getName());
							System.out.println("Type = "+ rf.getType());
							System.out.println("Decl-Class = "+rf.getDeclaringClass());
							System.out.println("Exp Type = "+rf.getExpType());
							System.out.println("M-Operation = "+rf.getMOperation());
							System.out.println("C-Operation = "+rf.getCOperation());
							System.out.println("Exp Type = "+rf.getExpType());
							System.out.println("***************************************");
						}
					}


				}

			}
		}
		return _methds;

	}
	
	public static E_Method searchInvokedMethod(E_MInvokedMethod sm){
		
		LinkedList<E_Method> _methds = fetchAllMethods();
		E_Method method = null;
		for (E_Method m:_methds){
			/*if(sm.isConstr()){
				continue;
			}
			else{*/
				if(sm.getName().equals(m.getName()) && sm.getDeclClassQName().equals(m.getDeclClassQName())){
					method = m;
					break;
				}
			//}
		}
	return method;
	
	}
   public static E_Method searchMethod(E_Method sourceMethod){
		
		LinkedList<E_Method> _methds = fetchAllMethods();
		E_Method method = null;
		for (E_Method m:_methds){
			if(sourceMethod.getName().equals(m.getName()) 
					&& sourceMethod.getDeclClassQName().equals(m.getDeclClassQName())){
				method = m;
			}
		}
	     
		return method;
	
		
	}
   public static E_Method searchConstMethod(TypeDeclaration _class){
	   
	   E_Class init_class = Data_Controller.searchClass(_class);
	   
	   E_Object obj = new E_Object();
	   
	    E_Method _methd = null;
	    
	    _methd = searchConstMethod(init_class);
	    
	   if(_methd!=null)
		  
		   return _methd;  
	   else
		  
		   return AST_Parser.createDefaultConstructor(_class, obj);	
}
public static E_Method searchConstMethod(E_Class init_class){
	
	LinkedList<E_Method> _methds = new LinkedList<E_Method>();
	 
	E_Method _methd = null;
	   
	if(init_class != null){
	   
	   _methds  = init_class.getMethods(); 
	
	   for (E_Method m:_methds){
		   
		   if(m.isConstr() && m.getDeclClassQName().equals(init_class.getClassQName())){
			   _methd = m;
			
			   break;
			}
		}
	}
	return _methd;
}
	
   public static E_Method searchMethod(String name, String qualifiedClassName){
		
		LinkedList<E_Method> _methds = fetchAllMethods();
		E_Method _method = null;
		for (E_Method m:_methds){
			if(name.equals(m.getName()) && qualifiedClassName.equals(m.getDeclClassQName())){
				_method = m;
			}
		}
	     
		return _method;
	
	}
   
  public static E_Method searchMethod(MethodDeclaration mDecl){
			
	E_Method method = null;

	if (mDecl != null) {
		IMethodBinding binding = (IMethodBinding) mDecl.getName()
				.resolveBinding();
			if(binding!=null){
				method = Data_Controller.searchMethod(binding);
			}
	}
	return method;
		
  }
   public static E_Method searchMethod(IMethodBinding bind){
	   
	  E_Method method = null;

      LinkedList<E_Method> _methds = fetchAllMethods();
			
	  if(_methds != null){
		 for (E_Method m:_methds){
			// if(m.equals(o))
			       if(bind.getName().toString().equals(m.getName().toString()) 
					&& bind.getDeclaringClass().getQualifiedName().toString().equals(m.getDeclClassQName()) 
					){
					
				method = m;
				break;
			}
		}
	}
	return method;
	}
   
   public static E_Class searchClass(TypeDeclaration node){
		
		E_Class _class = null;
		
		if(node!=null){
	
			LinkedList<E_Class> classes = fetchAllClasses();
	
			if(classes!=null){
				for (E_Class c:classes){
					if(node.getName().toString().equals(c.getName()) 
							&& c.getClassQName().equals(node.resolveBinding().getTypeDeclaration().getQualifiedName())){
						_class = c;
					}
				}
			}
		}
return _class;

}

   public static E_Class searchClass(ITypeBinding bind){
		
		LinkedList<E_Class> classes = fetchAllClasses();
		
		E_Class _class = null;
	
		if(classes != null && bind != null){
			for (E_Class c : classes){
					if(bind.getName().toString().equals(c.getName())
							&& c.getClassQName().equals(bind.getTypeDeclaration().getQualifiedName().toString())){
						_class = c;
						break;
					}
					if(c.getName().equals("Anonymous") ){
						if(bind.getDeclaringClass() != null){
							 if(c.getClassQName().equals(bind.getDeclaringClass().getQualifiedName().toString())){
								   _class = c;
								   break;
							 }
							}
					}
			}
		}
	return _class;
	
	}	
   public static E_MInvokedMethod searchInvokedMethod(E_Method method,IMethodBinding bind){
		
		LinkedList<E_MInvokedMethod> _methds = method.getSubMethods();
		
		E_MInvokedMethod inv= null;
		
		for (E_MInvokedMethod m:_methds){
		
			if(bind.getName().equalsIgnoreCase(m.getName()) && bind.getDeclaringClass().getQualifiedName().equals(m.getDeclClassQName())){
				
				inv = m;
			}
		}
	return inv;
	
	}
	public static LinkedList<E_MRefAlias> fetchAliasesOfRefField(E_MRefField _field){
		
		
		LinkedList<E_MRefAlias> aliases= new LinkedList<E_MRefAlias>();
		
		LinkedList<E_Method> _methods = Data_Controller.fetchAllMethods();
		
		for(E_Method m:_methods){
			 
		   if(m.getName().equals(_field.getMethod().getName())&& 
				 
				 m.getDeclClassQName().equals(_field.getMethod().getDeclClassQName())){
				
		 		 // E_Method m = searchMethod(_field.getMethod());
		 
			      LinkedList<E_MRefField> _refs = m.getRefVariable();
		
			      for(E_MRefField r:  _refs){
						
						if(r.getName().equals(_field.getName()) 
								&& r.getType().equals(_field.getType())&& 
								r.getDeclaringClass().equals(_field.getDeclaringClass())){
							  
							aliases = r.getAliases();
							  break;
						}
					}
		    }
		 }
			
		
	return aliases;
}
public static LinkedList<E_MRefAlias> fetchAliasesOfParams(E_MRefParameter param){
		
		
		LinkedList<E_MRefAlias> aliases = new LinkedList<E_MRefAlias>();
		
		LinkedList<E_Method> _methods = Data_Controller.fetchAllMethods();
		
		if(param!=null){
			for(E_Method m:_methods){
				 
			   if(m.getName().equals(param.getDeclMethod())){
					 //&& m.getDeclClassQName().equals(param.getDeclMethod().getDeclClassQName())){
					
			 		  LinkedList<E_MRefParameter> params = m.getRefparams();
			
				      for(E_MRefParameter p:  params){
							
							if(p.getName().equals(param.getName()) && p.getType().equals(param.getType())&& p.getDeclMethod().equals(param.getDeclMethod())){
								
								  aliases = p.getAliases();
								  break;
								  
							}
						}
			    }
		  }
		}
		
	return aliases;
}
	
	public static LinkedList<E_MRefAlias> fetchAliasesOfLocalVar(E_MLocalVariable var){
	
	 LinkedList<E_MRefAlias> aliases= new LinkedList<E_MRefAlias>();
	
	 LinkedList<E_Method> _methods = Data_Controller.fetchAllMethods();
	 
	 E_Method m = searchMethod(var.getDeclMethod());
	 
	//for(E_Method m:_methods){
			 
	 //if(m.getName().equals(var.getDeclMethod().getName())){
	 
	 	 		LinkedList<E_MLocalVariable> locals = m.getLocalVar();
				
				for(E_MLocalVariable l:  locals){
					
					if(l.getName().equals(var.getName()) && l.getType().equals(var.getType()) && l.getDeclMethod().getName().equals(var.getDeclMethod().getName())){
						
						aliases = l.getAliases();
						
						break;
					}
				}
	 	//}
	//}
		 return aliases;
	}
	public static E_MLocalVariable fetchMLocalVar(E_MLocalVariable localVar,E_Method _method){
		
		LinkedList<E_Method> _methods = Data_Controller.fetchAllMethods();
		
		E_MLocalVariable var = null;
	 
		for(E_Method m:_methods){
	
			if(m.getName().equals(_method.getName())){
							
			      LinkedList<E_MLocalVariable> locals = m.getLocalVar();
			 
					for(E_MLocalVariable l:  locals){
							
						if(l.getName().equals(localVar.getName()) && l.getType().equals(localVar.getType())&& l.getDeclMethod().getName().equals(localVar.getDeclMethod().getName())){
							var = l;
							break;
						}
					}
			   }
			}
	
	 return var;
	}
	public static LinkedList<E_MRefAlias> fetchAliasOfMLocalVar(E_MLocalVariable localVar,E_Method _method){
	
		E_MLocalVariable l = fetchMLocalVar(localVar, _method);
		
		 LinkedList<E_MRefAlias> localAliases = new LinkedList<E_MRefAlias>();
		
		 if(l != null){
			
			LinkedList<E_MRefAlias>  aliases = l.getAliases();
								
			if(aliases != null){
									
				for(E_MRefAlias a:aliases){
		
					localAliases.add(a);
				}
				
			}
		 }
									
		 return localAliases;
	}

	
	public static LinkedList<E_MRefField>  fetchLocalAliases(LinkedList<E_MRefAlias> aliases,LinkedList<E_MRefField> fieldAliases,LinkedList<E_MRefParameter> paramAliases){
	
			for(E_MRefAlias a : aliases){
				
			     if(a.getLocalVarAlias()!= null){
						
					LinkedList<E_MLocalVariable> localAliases = a.getLocalVarAlias();
					
					 for(E_MLocalVariable la : localAliases){
						
						//displayLocalAlias(la);

						//LinkedList<E_MRefAlias> vAliases  = Data_Controller.fetchAliasOfMLocalVar(la,la.getDeclMethod());
						
						LinkedList<E_MRefAlias> vAliases  = Data_Controller.fetchAliasesOfLocalVar(la);
						
							if(vAliases!=null && vAliases.isEmpty() == false){
								
								fetchLocalAliases(vAliases,fieldAliases,paramAliases);
								
								fetchFieldAliases(vAliases,fieldAliases,paramAliases);
								
								fetchParametersAliases(vAliases, paramAliases);
								
							}
							/*else 
								break;*/
					  }
							
					}
						
		  }
		return fieldAliases;
		}	

public static LinkedList<E_MRefField> fetchFieldAliases(LinkedList<E_MRefAlias> aliases,LinkedList<E_MRefField> fieldAliases,	LinkedList<E_MRefParameter> paramAliases){
		
		LinkedList<E_MRefField> fAliases = new LinkedList<E_MRefField>();
	
		if(aliases != null && aliases.isEmpty() == false){

			for(E_MRefAlias a : aliases){
				
				if(a.getFieldAlias() != null){
					
					if(a.getFieldAlias().isEmpty() == false){
					
					   fAliases = a.getFieldAlias();
			
					   for(E_MRefField r : fAliases){
						
						   displayField(r);
						
						    fieldAliases.add(r);
						     
						    	LinkedList<E_MRefAlias> rAliases  = Data_Controller.fetchAliasesOfRefField(r);
						    	
						    	if(rAliases!=null && rAliases.isEmpty() == false){
								
						    		fetchFieldAliases(rAliases,fieldAliases,paramAliases);
							
						    		fetchLocalAliases(rAliases,fieldAliases,paramAliases);
								
						    		fetchParametersAliases(rAliases, paramAliases);
						   
						    }
							else{
								break;
							}
					}
					}		
				}
			}
			}
		     return fieldAliases;
		}
		public static LinkedList<E_MRefParameter> fetchParametersAliases(LinkedList<E_MRefAlias> aliases,LinkedList<E_MRefParameter> paramAliases){
			
			LinkedList<E_MRefParameter> pAliases = new LinkedList<E_MRefParameter>();
			
			if(!aliases.isEmpty()){

				for(E_MRefAlias a : aliases){
					
					if(a.getParamAlias() != null && a.getParamAlias().isEmpty() == false){
						
						pAliases = a.getParamAlias();
				
						for(E_MRefParameter p : pAliases){
							
							displayParamter(p);
							
							paramAliases.add(p);
							
							LinkedList<E_MRefAlias> ppAliases  = Data_Controller.fetchAliasesOfParams(p);
							
							LinkedList<E_MRefField> fieldAliases = new LinkedList<E_MRefField>();
							
							if(ppAliases!=null && ppAliases.isEmpty() == false){
								
								fetchParametersAliases(ppAliases,paramAliases);
								fetchFieldAliases(ppAliases,fieldAliases,paramAliases);
								fetchLocalAliases(ppAliases,fieldAliases,paramAliases);
							}
							else{
								break;
							}
						}		
					}
				
			   }
			}
		     return paramAliases;
		}
		public static  void displayLocalAlias(E_MLocalVariable la)
		{
			System.out.println("Local Alias Name= "+la.getName());

			System.out.println("Local Alias Type = "+ la.getType());

			System.out.println("Local Alias declaration Type = "+ la.getDeclMethod().getName());

			System.out.println("Local Alias operation = "+ la.getMOperation());
		}
		public static  void displayField(E_MRefField rf)
		{
			System.out.println("Referenced Fields");
			
			System.out.println("Ref-Name= "+rf.getName());
			System.out.println("Type = "+ rf.getType());
			System.out.println("Decl-Class = "+rf.getDeclaringClass());
			System.out.println("Exp Type = "+rf.getExpType());
			System.out.println("Method name = "+rf.getMethod().getName());
			if(rf.getQualifyingObject()!=null){
				System.out.println("Field Qualifying object = "+rf.getQualifyingObject().getName().toString());
			}
			//System.out.println("Field name = "+rf.getQualifyingObject().getObjBind().getName());
			System.out.println("M-Operation = "+rf.getMOperation());
			System.out.println("C-Operation = "+rf.getCOperation());
			System.out.println("Alias-Operation = "+rf.getAliasOp());
			System.out.println("If Return Expression = "+rf.isRetFiel());
		}
		
		public static  void displayParamter(E_MRefParameter p)
		{
			System.out.println("Parameter Name= "+p.getName());
			System.out.println("Type = "+p.getType());
			System.out.println("Position = "+p.getPosition());
			System.out.println("Method Operation = "+p.getMOperation());
			System.out.println("Alias Operation = "+p.getAliasOp());
			System.out.println("exp Type = "+p.getExpType());
			System.out.println("Is Field= "+p.isField());
			List<E_MRefField> fields = p.getFields();
			if(fields!=null && fields.isEmpty() == false){
				for(E_MRefField rf:fields){
					displayField(rf);
				}
			}
			if(p.getQualifyingObject()!=null){
				System.out.println("Qualifying Object= "+p.getQualifyingObject().getName());
			}
			System.out.println("If Returned parameter= "+p.isRetFiel());
		}
		
		public static  void displayArguments(E_Argument rf)
		{
			System.out.println("Arg Fields");
			
			System.out.println("Arg-Name= "+rf.getName());
			System.out.println("Type = "+ rf.getType());
			System.out.println("Decl-Class = "+rf.getDeclaClass());
			System.out.println("Exp Type = "+rf.getDeclMethod());
			
		}
				
		
}