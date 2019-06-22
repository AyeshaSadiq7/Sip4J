package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.dom.*;

import datastructure.*;
import datautilities.Data_Controller;
import datautilities.Data_Generator;

public class AST_Parser {

	public static  E_Class createNewClass(ITypeBinding node){
		
		E_Class _class = null;
		
		E_Class tempClass =  Data_Controller.searchClass(node);
		   
	   if(tempClass != null){ 
		   _class = tempClass; 
	   }
	   else{
		 
		  _class = new E_Class();	  
		 // get class name and set it E_Class
		 if(node.isAnonymous()){
			 _class.setName("Anonymous");
			_class.setClassQName(node.getDeclaringClass().getQualifiedName().toString());
		 }
		 else{
		 _class.setName(node.getName().toString());
		 _class.setClassQName(node.getTypeDeclaration().getQualifiedName());
			
		 }
		
		
		_class.setModifier(setClassModifier(node));
		
		//_class.setIndex(_class.getLastClassIndex());
		
		// get and set super class name
		if(node.getSuperclass() != null){
		   
			ITypeBinding superClass = node.getSuperclass().getTypeDeclaration();
		
			if (superClass != null){
				
				if(superClass.isFromSource()){
				
					E_Class _superClass = new E_Class();
				
					_superClass.setName(superClass.getName().toString());
				
					_superClass.setClassQName(superClass.getQualifiedName());
				
					_class.setSuperClass(_superClass);		
			   }
			}
			}
	   }
		return _class;
	}
	public static Expression findThisExpression(FieldAccess node){
		
		Expression thisExp =null;
		
		List<ASTNode> nodes = AST_Parser.getChildren(node);
		
		for (ASTNode l_child: nodes) {
			
			if (l_child.getNodeType() == ASTNode.THIS_EXPRESSION){
				
				 thisExp = (Expression) l_child;
				 break;
			}
		}
		
		return thisExp;
		
	}
	public static IVariableBinding getVariableBinding(Expression exp){
		
	IVariableBinding binding = null;
	
	if(exp!= null){
    	if (exp.getNodeType() == ASTNode.FIELD_ACCESS) {
			  FieldAccess f = (FieldAccess) exp;
				if (f.resolveFieldBinding() instanceof IVariableBinding) {
			        binding = f.resolveFieldBinding();
			        return binding;
				}
			  
		 }
    	else if (exp.getNodeType() == ASTNode.SIMPLE_NAME) {
			  SimpleName fs = (SimpleName) exp;
			  IBinding bind = fs.resolveBinding();
			  if (bind instanceof IVariableBinding) {
					binding = (IVariableBinding) fs.resolveBinding();
					return binding;
			  }
		}
    	else if (exp.getNodeType() == ASTNode.QUALIFIED_NAME) {
			   QualifiedName qf = (QualifiedName) exp;
			   if (qf.resolveBinding() instanceof IVariableBinding) {
					binding = (IVariableBinding) qf.resolveBinding();
					return binding;
				}
		 }	
    	else {
    		
			List<ASTNode> l_children = AST_Parser.getChildren(exp);
			
			for (ASTNode l_child: l_children) {
			
				if (l_child.getNodeType() == ASTNode.FIELD_ACCESS || l_child.getNodeType() == ASTNode.SIMPLE_NAME || l_child.getNodeType() == ASTNode.QUALIFIED_NAME) {
				
					Expression childExp = (Expression) l_child;
			
					return AST_Parser.getVariableBinding(childExp);
				}
			}
		}
	}
   return binding; 
	}

	public static IMethodBinding getMethodInvocBinding(Expression exp){
		
		IMethodBinding binding = null;
		    if(exp!=null){
		    	if (exp.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION) {
		    		    ClassInstanceCreation f = (ClassInstanceCreation) exp;
		    		   // System.out.println("constructor binding = "+f.resolveConstructorBinding()+" type binding"+f.resolveTypeBinding());
						if (f.resolveConstructorBinding() instanceof IMethodBinding) {
					        binding = f.resolveConstructorBinding();
						}
						if(binding.getDeclaringClass().isParameterizedType()){
				   	   		
					   		binding = binding.getMethodDeclaration();
					   
					   }
					  
				 }
		    	else if (exp.getNodeType() == ASTNode.METHOD_INVOCATION) {
		    		    
		    		MethodInvocation f = (MethodInvocation) exp;
		    		    
		    		    if (f.resolveMethodBinding() instanceof IMethodBinding) {
					
		    		    	binding = f.resolveMethodBinding();
					        
		    		    	if(binding.getDeclaringClass().isParameterizedType()){
					   	   		
						   		binding = binding.getMethodDeclaration();
						   
						   	}
		    		    	
					    	IMethodBinding mb = (IMethodBinding) f.getName().resolveBinding();
					    	
					    	IMethod element = (IMethod) mb.getJavaElement().getAncestor( IJavaElement.METHOD);
					    	
					    	//System.out.println(element.getElementName().toString()+" ");
					    	
							//System.out.println("The parent method of "+f.getName() +" is = "+element.getElementName().toString());
						}
				  
			    }
	    	
			else {
	    		
				List<ASTNode> l_children = AST_Parser.getChildren(exp);
				
				for (ASTNode l_child: l_children) {
				
					if (l_child.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION || l_child.getNodeType() == ASTNode.METHOD_INVOCATION) {
					
						Expression childExp = (Expression) l_child;
				
						return AST_Parser.getMethodInvocBinding(childExp);
					}
				}
			}
		}		
    		return binding; 
	}
	public static String getFieldOperation(Expression exp){
		
		String operation = null;
		
		if(exp.getParent() != null){
			
			if (exp.getParent().getNodeType() == ASTNode.PREFIX_EXPRESSION || exp.getParent().getNodeType() == ASTNode.POSTFIX_EXPRESSION){
				operation = GlobalVariables.WRITE;
			}
			else
				operation = GlobalVariables.READ;
			
		}
		return operation;
	}
	public static void addMLocalVariables(E_Method _method, VariableDeclarationStatement node){
		
		List<VariableDeclarationFragment> varList = node.fragments();
		
		 if (varList != null){
	
			 for (VariableDeclarationFragment  vars: varList) {
			
				 IVariableBinding leftBind  = vars.resolveBinding();
				 
				 Expression rightExp = vars.getInitializer();
		
				 //IVariableBinding rightExpB = AST_Parser.getBinding(rightExp);

				 if(rightExp != null && leftBind != null){
					 if(ifReferenceType(leftBind.getType())){
						 if(ifReferenceType(rightExp.resolveTypeBinding())){
							 //if(!checkIfSelfAddressStatement(leftBind,rightExpB)){ 
								 AST_Parser.addAddressFlowExp(leftBind, rightExp, _method);
						     //}
					    }
						 
				    }
				  }
				}
			 }
	}
	public static boolean ifReferenceType(ITypeBinding bind){
	
		boolean flag = false;
			
			if(bind.isFromSource() || (bind.isArray())){
				
				flag = true;
			}
			else
			
				flag = false;
		
		return flag;
		
	}
	public static void addClassFields(E_Class _class, FieldDeclaration[] fields,E_Method method){
		
		E_Method m = null;
	
		if (fields != null) {
			
			for (FieldDeclaration field : fields) {
					@SuppressWarnings("unchecked")
					List<VariableDeclarationFragment> vf = field.fragments();
				if (vf != null) {
					
					for (VariableDeclarationFragment ff : vf) {
					
						 E_Field _field = new E_Field();
					
						 if (!field.getType().isPrimitiveType()) {
								// _field.setName(((VariableDeclarationFragment)field.fragments().get(0)).getName().toString());
								_field.setName(ff.getName().toString());
							if (field.getType().isParameterizedType())
									_field.setType(((ParameterizedType) field
											.getType()).getType().toString());
								else if (field.getType().isArrayType()) {
									Type componentType = ((ArrayType) field
											.getType()).getComponentType();
									if (componentType.isParameterizedType()) {
										_field.setType(((ParameterizedType) componentType)
												.getType().toString());
									} else
										_field.setType(((ArrayType) field.getType())
												.getComponentType().toString());
								  }
									_field.setModifier(field.getModifiers());
									
								 }  
							else
								_field.setName(ff.getName().toString());
								_field.setType(field.getType().toString());
								_field.setModifier(field.getModifiers());
							
								// add field in class}
								
								//_field.setClassIndex(_class.getLastClassIndex());
						
								// add initialised fields in a class constructor
								
								//if(ff.getInitializer()!=null){
									
									IVariableBinding fieldBind  = null;
										
									E_Object obj = new E_Object();
										
									obj.setName(_class.getClassQName());
										
									 if(method != null){
										if(ff.resolveBinding() != null){
											if(ff.resolveBinding() instanceof IVariableBinding) {
											  fieldBind = ff.resolveBinding();
											}
										}
										if(fieldBind!=null){
										   AST_Parser.addClassField(method, ff.getName().toString(),fieldBind.getType().getQualifiedName(),_class.getClassQName(),GlobalVariables.WRITE, ff.getNodeType(), false, obj,fieldBind.isField(),fieldBind.isParameter());
										}
									}
							//}
						
						_class.addField(_field);
					
					}// end if

				}

			}
		}
						
	}
	
	@SuppressWarnings("unchecked")
	public static E_Method createNewMethod(MethodDeclaration node, E_Object obj){
		
		 E_Method _method = null;
		 
		 E_Method m = Data_Controller.searchMethod(node);
		 
		if(m != null){ 
			  // modify this check properly to distinguish between overloaded methodss
			  //if(m.getReturnType().equals(node.resolveBinding().getReturnType().getName().toString()) && m.getParameters().size() == node.parameters().size()){
				  _method = m;
			  //}
		  }	  
		 else{
			  
			 _method = new E_Method();
	
			 IMethodBinding bind = node.resolveBinding();
			
			 _method.setName(node.getName().toString());
			 
			if (node.isConstructor()) {
				_method.setReturnType("");
			}
			else {
				if (node.getReturnType2() != null){
				 if(bind!=null){
					//_method.setReturnType(node.resolveBinding().getReturnType().getQualifiedName().toString());
					_method.setReturnType(node.resolveBinding().getReturnType().getName().toString());
					
				 }
				}
			}
			
			_method.setModifier(AST_Parser.setMethodModifier(bind));
			
			_method.setIdentifier(node.getName().getIdentifier().toString());
			
			_method.setConstr(node.isConstructor());
			
			if(bind != null){
			   if(bind.getDeclaringClass()!=null && bind.getDeclaringClass().getTypeDeclaration().isFromSource()){
				 _method.setDeclClassQName(bind.getDeclaringClass().getQualifiedName());
			 	 _method.setDeclaringClass(bind.getDeclaringClass().getName().toString());
			 }
			}
	      /*  
			E_InvokedMethod const_inv = null; 
			if(node.isConstructor()){
				E_Class _class = Data_Controller.fetchLastClass();
				LinkedList<E_InvokedMethod>  invM = new LinkedList<E_InvokedMethod>();
				invM = _class.getInvokMethods();
				 for(Iterator<E_InvokedMethod> iter = invM.iterator(); iter.hasNext();) {
					 E_InvokedMethod data = iter.next();
					 if(data.isConstr()){
						 if(data.getName().equals(bind.getName())
							 && data.getDeclClassQName().equals(bind.getDeclaringClass().getQualifiedName())) { 
							 const_inv = data;
						 }
					  }
				 }
			}
			else{
			
			*/
			
			//if(node.getName().equals("main")== false){
				
				/* E_Object obj = new E_Object();
				
				  MethodInvocation thi_inv = AST_Parser.getThisMethodInvokation(node);
		      
			    	if(thi_inv!=null){
	    			    
				    	IVariableBinding qualBind = null;
				    	
				     	if(thi_inv.getExpression()!=null){
				    		
				    		 qualBind = AST_Parser.getObjQualifBind(thi_inv.getExpression());
				    	
				    		  if(qualBind!=null ){
						    		obj.setObjBind(qualBind);
							    }
				    	}
				    	else {
				    		 
				    		 E_Method parentM = Data_Controller.searchMethod(AST_Parser.fetchSourceMethod(thi_inv));
				    		 if(parentM!=null){
				    			 if(parentM.getQualifyingObject()!=null){
				    				 obj = parentM.getQualifyingObject();
				    		 
				    			 }
				    		 }
				    	}	
			    	}
					_method.setQualifyingObject(obj);*/
				 _method.setQualifyingObject(obj);
				 // create and set method signatures
				 String signature_string = createMethodSignature(node);
				
				 _method.setMethodSignatures(" "+_method.getModifier()+" "+_method.getReturnType()+" "+_method.getIdentifier()+"("+signature_string+")");
				 
				 //_method.setMethodSignatures(_method.getModifier()+" "+_method.getReturnType()+" "+_method.getIdentifier()+"()");
				 
				 List<String> statements =  new LinkedList<String>();
				 Block body = node.getBody();
				 if(body != null){
					 statements = body.statements();
					 _method.addState(statements);
				 }
				 
		 }
			
		return _method;
	}
	public static String createMethodSignature(MethodDeclaration node){
		
	 Iterator<SingleVariableDeclaration> parameters = node.parameters().iterator();
	 String signature_string = "";
	 if(parameters!=null){
		 int i = 0;
		 String seperator = "";
		  while (parameters.hasNext()) {
			 SingleVariableDeclaration p = parameters.next();
			 if(i>0){
			   seperator = ", ";
			 }
			 signature_string = signature_string+seperator+p.toString(); 
			 i= i+1;
		  }
	   }
	 return signature_string;
	}
	public static String setMethodModifier(IMethodBinding mb){
		    String modifier = "";
			
		    if(mb.getModifiers() == Modifier.ABSTRACT){
				modifier = GlobalVariables.ABSTRACT;
			}
			else if(mb.getModifiers() == Modifier.PUBLIC){
				modifier = GlobalVariables.PUBLIC;
			}
			else if(mb.getModifiers() == Modifier.PRIVATE){
				modifier = GlobalVariables.PRIVATE;
			}
			else if(mb.getModifiers() == Modifier.FINAL){
				modifier = GlobalVariables.FINAL;
			}
			else if(mb.getModifiers() == Modifier.PROTECTED){
				modifier = GlobalVariables.PROTECTED;
			}
			else if(mb.getModifiers() == Modifier.STATIC){
				modifier = GlobalVariables.STATIC;
			}
			else if(mb.getModifiers() == Modifier.NONE){
				modifier = GlobalVariables.NONE;
			}
			
			return modifier;
			
			
	}
	public static E_Method createDefaultConstructor(TypeDeclaration node, E_Object obj){
		
		 E_Method _method = null;
		 
	if(node.resolveBinding().isFromSource() && node.resolveBinding().isClass()){
				
		  E_Method m = Data_Controller.searchMethod(node.getName().toString(),node.resolveBinding().getQualifiedName());
		  
		  if(m!=null){ 
			  //if(m.getQualifyingObject().equals(obj)){
				_method = m;
			  //}
			// else{
				//_method = m;
				//_method.setQualifyingObject(obj);
			 //}	
		  }
		  else{
			  
			        _method = new E_Method();
				 //create meta data of a constructor
					_method.setName(node.getName().toString());
					
					_method.setIdentifier(node.getName().getIdentifier().toString());
					
				    _method.setDeclClassQName(node.resolveBinding().getQualifiedName());
					
					_method.setDeclaringClass(node.resolveBinding().getName().toString());
					
					_method.setReturnType(GlobalVariables.NONE);
					
					_method.setModifier(GlobalVariables.PUBLIC);
					
					_method.setConstr(true);
					
					_method.setQualifyingObject(obj);
					
					_method.addStatements(null);
					
					// create and set method signatures
				    String signature_string = "";
				
				   _method.setMethodSignatures(_method.getModifier()+" "+_method.getReturnType()+" "+_method.getIdentifier()+"("+signature_string+")");
		  }
	    }
	return _method;
	}
	
	public static String setClassModifier(ITypeBinding tb){
	    String modifier = "";
		if(tb.getModifiers() == Modifier.ABSTRACT){
			modifier = GlobalVariables.ABSTRACT;
		}
		else if(tb.getModifiers() == Modifier.PUBLIC){
			modifier = GlobalVariables.PUBLIC;
		}
		else if(tb.getModifiers() == Modifier.PRIVATE){
			modifier = GlobalVariables.PRIVATE;
		}
		else if(tb.getModifiers() == Modifier.FINAL){
			modifier = GlobalVariables.FINAL;
		}
		else if(tb.getModifiers() == Modifier.PROTECTED){
			modifier = GlobalVariables.PROTECTED;
		}
		else if(tb.getModifiers() == Modifier.STATIC){
			modifier = GlobalVariables.STATIC;
		}
		else if(tb.getModifiers() == Modifier.NONE){
			modifier = GlobalVariables.NONE;
		}
		
		return modifier;
		
		
}
	public static void addMethodParameters(E_Method _method, MethodDeclaration node, List<SingleVariableDeclaration> _listParameters,List<MethodInvocation> thisMethInv){

		//List<MethodInvocation> thisMethInv = getThisMethodInvokation(node);
		
		List<E_InvokedMethod> inv = findThisMethodInvoData(node);
		
	   //fetch argument list
	   int i = 0;
		
	   if (_listParameters != null) {	 
 			/////////////////////////////////////
 			for (SingleVariableDeclaration para : _listParameters) {

 				IBinding binding = para.resolveBinding();
 				
 				if (binding instanceof IVariableBinding) {

 					IVariableBinding pb = (IVariableBinding) binding;
 					
 					//if(AST_Parser.ifParameterVariable(pb)){
 					E_MParameter param = new E_MParameter(pb.getName().toString(),pb.getType().getQualifiedName().toString(),i);
 					
 					_method.addParameter(param);
 					
 					i++;
 					
 					addMethodRefParameters(para,pb,inv,_method,thisMethInv);
 				}
 							
 			}
	   }
 	}

	public static void addConstParameters(E_Method _method, MethodDeclaration node, List<SingleVariableDeclaration> _listParameters, List<ClassInstanceCreation> thisConstInv){

		//List<ClassInstanceCreation> thisMethInv = getConstructorInvokation(node);
		
		List<E_InvokedMethod> inv = findThisMethodInvoData(node);
		
		   //fetch argument list
	   if (_listParameters != null) {	 
 			/////////////////////////////////////
 			for (SingleVariableDeclaration para : _listParameters) {

 				IBinding binding = para.resolveBinding();
 				
 				if (binding instanceof IVariableBinding) {

 					IVariableBinding pb = (IVariableBinding) binding;
 					
 					//if(AST_Parser.ifParameterVariable(pb)){
 							
 					addConstRefParameters(para,pb,inv,_method,thisConstInv);
 				}
 							
 			}
	   }
 	}

public static void addMethodRefParameters(SingleVariableDeclaration para,IVariableBinding pb, List<E_InvokedMethod> invkM, E_Method _method, List<MethodInvocation> thisMethInv){

	E_Object obj = new E_Object();
	
	E_Argument arg = null;

	MethodDeclaration mDecl = null;
	
	if(thisMethInv!=null && thisMethInv.isEmpty() == false){			
	
		for (MethodInvocation invM : thisMethInv) {
	
			if (invM != null) {
				
				mDecl = fetchParentMethodDecl(invM);
				
				arg = mapFormalParameter(invkM, pb, mDecl);
				
				 if (arg != null) {
					
					 if (arg.isField() == true) {
						
						 addMethodFieldParameter(arg, para, pb, _method, obj);
					 }  
					 else if (arg.isField() == false
							&& arg.isParameter() == true) {
	
								E_Object pObj = new E_Object();
			
								E_MRefParameter p = null;
									
								E_Method sourceMethod = fetchParentMethod(invM);
								
									if(sourceMethod == null){
										
										E_Method method = AST_Parser.createNewMethod(mDecl, pObj);
										
										AST_Parser.addMethodData(mDecl, pObj);
										
										E_Class _parentClass = null;
										
										if(mDecl.resolveBinding().getDeclaringClass() != null && mDecl.resolveBinding().getDeclaringClass().isInterface() == false){
										
											_parentClass = AST_Parser.createNewClass(mDecl.resolveBinding().getDeclaringClass());
										
											AST_Parser.addPackageClass(Data_Generator.getPackage(),_parentClass);
										
											AST_Parser.addClassMethod(_parentClass, method);
										}
										
									}
								     sourceMethod = fetchParentMethod(invM);
								    
								     if(sourceMethod!=null){
								    	
								    	 if(sourceMethod.getRefparams() != null) {
							 
								    		 LinkedList<E_MRefParameter> params = sourceMethod
												.getRefparams();
										
								    		 p = matchArgWithParam(params, arg);
										
								    		 if (p != null) {
								    			 if (pObj.getName().equals("this")) {
								    				 pObj.setName(p.getPfClass());
								    			 }
								    			 String paramType = "";
								    			 if(pb.getType().isParameterizedType()){
								    				 paramType = pb.getType().getTypeDeclaration().getQualifiedName().toString();
								    					
								    				}
								    				else {
								    					paramType = pb.getType().getQualifiedName().toString();
								    				}
											
								    			 E_MRefParameter pRef = AST_Parser.addRefParameters(_method, para.getName().getIdentifier()
													.toString(), paramType,
													p.getPfClass(),
													pb.getVariableId(),
													GlobalVariables.READ,
													para.getNodeType(), pb
															.getDeclaringMethod()
															.getName().toString(),
													false, pObj);
				
								    			 	if (pRef != null) {
													//if(pRef.getFields().isEmpty()){// changes made for plural translation
														pRef.setField(p.isField());
														pRef.setfName(p.getfName());
														pRef.setPfClass(p.getPfClass());
														pRef.setPfType(p.getPfType());
													 
														E_MRefField field = addParamField(_method,
															p.getfName(), p.getPfType(),
															p.getPfClass(),
															GlobalVariables.READ,
															para.getNodeType(), false, pObj,p.isField(),pb.isParameter());
						
														if (field != null) {
															pRef.addFields(field);
														}
													//}
												}
										}
								  }
								     }
							  }
					else if (arg.isLocal() == true
							&& arg.isField() == false
							&& arg.isParameter() == false) {
						addMethodLocalParameter(arg, para, pb, _method);
					}// all conditions ends here
	
				}
			}
		
    	}
	}
}

public static MethodInvocation matchInvMethod(E_InvokedMethod  inv, List<MethodInvocation>  thisMethInv){
	
	 MethodInvocation result =null;
	
	 for(MethodInvocation invM: thisMethInv){
		 	
		// System.out.println(inv.getName()+" "+invM.getName().toString()+" "+inv.getDeclClassQName()+" "+);
		 
		 if(inv.getName().equals(invM.getName().toString())
				 &&inv.getDeclClassQName().equals(invM.resolveMethodBinding().getMethodDeclaration().getDeclaringClass().getQualifiedName().toString())){
			 result  =  invM;
		 }
		 
	 }
	return result;
	
}
public static void addConstRefParameters(SingleVariableDeclaration para,IVariableBinding pb, List<E_InvokedMethod> invkM,E_Method _method,List<ClassInstanceCreation> thisMethInv){
	
	LinkedList<E_Argument> argList = new LinkedList<E_Argument>();

	E_Object obj = null;
	
  for(ClassInstanceCreation invM: thisMethInv){
	
	  if(invM!=null){
		
			MethodDeclaration sourceDecl = AST_Parser.fetchParentMethodDecl(invM);
			
			if(sourceDecl!=null){	
			
				if(sourceDecl.getName().toString().equals("main")){
					
					obj = Data_Controller.searchMethod(getMethodDeclaration(invM.resolveConstructorBinding())).getQualifyingObject();
							
				}
				else{
					
					obj = AST_Parser.getQualifyingObject(sourceDecl);
				}
		   }
			
		   for(E_InvokedMethod inv: invkM){
			
			E_Argument arg = null;
			if(inv != null){	
			  argList = inv.getArguments();
			  if (argList != null) {	 	
				arg = matchArgument(argList,pb);
			  }
		    }
		    if(arg!=null){	
				 
		    	if(arg.isField() == true){	
				
					 addMethodFieldParameter(arg,para,pb,_method,obj);
			     }    		  
				 else if(arg.isField() == false && arg.isParameter() == true){
					 
				 E_Object pObj = new E_Object();
				
				 E_MRefParameter p  = null;
				 
				 MethodDeclaration mDecl = fetchParentMethodDecl(invM);
				 
				 E_Method sourceMethod = fetchParentMethod(invM);
				 
				 if(sourceMethod == null ){
						
					 if(mDecl!=null){
						 
						 sourceMethod = AST_Parser.createNewMethod(mDecl, pObj);
					
						 AST_Parser.addMethodData(mDecl, pObj);
						 E_Class parentClass = null;
						 
						if(mDecl.resolveBinding().getDeclaringClass() != null && mDecl.resolveBinding().getDeclaringClass().isInterface()==false){
								
							parentClass = AST_Parser.createNewClass(mDecl.resolveBinding().getDeclaringClass());
					
							AST_Parser.addPackageClass(Data_Generator.getPackage(),parentClass);
					
						  AST_Parser.addClassMethod(parentClass, sourceMethod);
						}
					 }
						
				  }
				  if(sourceMethod!=null){  
				    	  
				   // pObj =  AST_Parser.getMQualifyingObject(sourceMethod);
			
				 	if(sourceMethod.getRefparams()!=null){
								    					
					   LinkedList<E_MRefParameter> params = sourceMethod.getRefparams();
						
					   p = matchArgWithParam(params,arg);
				 	}
				 }
			   
				  if(p!=null){
					    if(pObj.getName().equals("this")){
					        		  pObj.setName(p.getPfClass());
					     }
						E_MRefParameter pRef = AST_Parser.addRefParameters(_method, para.getName().getIdentifier().toString(),
						para.resolveBinding().getType().getQualifiedName().toString(),p.getPfClass(), pb.getVariableId(), 
						GlobalVariables.READ, para.getNodeType(), pb.getDeclaringMethod().getName().toString(),
						false,pObj);
						
						 if(pRef!=null){
							
							//if(pRef.getFields().isEmpty()){// changes made for plural translation
							 pRef.setField(p.isField());
							 pRef.setfName(p.getfName());
							 pRef.setPfClass(p.getPfClass());
							 pRef.setPfType(p.getPfType());
						 
						     E_MRefField field = addParamField(_method,
					         p.getfName(),p.getPfType(),
						     p.getPfClass(),
						     GlobalVariables.READ,para.getNodeType(),false,pObj,pb.isField(),pb.isParameter());
						    
						     if(field!=null){
						    	pRef.addFields(field);
						    }
						//}
	          	    }
			     }
			   }
			  else if (arg.isLocal() == true && arg.isField() == false && arg.isParameter() == false){
				  		addMethodLocalParameter(arg, para, pb, _method);
			  }// all conidtions ends here	  
		    	  
	}
}

}
}
}
public static MethodInvocation findRecursiveMInvocations(List<MethodInvocation>  thisMethInv){
	MethodInvocation result = null;
	for (MethodInvocation invM : thisMethInv) {
		if (invM != null) {
		    MethodDeclaration mDecl = fetchParentMethodDecl(invM);
		    if(invM.getName().toString().equals(mDecl.getName().toString())){
				 result = invM;
		
		     }
		 }
	}
	return result;
			 
}
public static E_Argument mapFormalParameter(List<E_InvokedMethod>  invkM, IVariableBinding pb, MethodDeclaration callerMethod){
	
	E_Argument a = null;
	
	LinkedList<E_Argument> argList = null;
	
	for (E_InvokedMethod inv : invkM){
		if (inv != null && inv.getCallerMethod() != null) {
			if(inv.getName().equals(callerMethod.getName().toString()) && 
					inv.getCallerMethod().getName().toString().equals(callerMethod.getName().toString())){
				continue;
			}
			else{
			  argList = inv.getArguments();
			  if (argList != null) {
				  a = matchArgument(argList, pb);
				   if(a != null){
					   break;
				   }
				   
				}
			}
		
		}
	}
	return a;
 }
  public static E_Argument matchArgument(LinkedList<E_Argument >  argList,IVariableBinding pb){
	E_Argument a = null;
	String paramType = "";
	
	for (E_Argument arg : argList) {
		/*if(pb.getJavaElement().getElementType() ==ASTNode.ARRAY_TYPE){
			paramType = pb.getType().getQualifiedName()+"[]";
		}*/
			//if (arg.getPosition() == pb.getVariableId()) {
		//System.out.println(arg.getType()+" "+pb.getType().getQualifiedName());	
		//System.out.println(" "+pb.getType().getQualifiedName());
		/*if(pb.getType().isArray()){
			paramType = pb.getType().getQualifiedName()+"[]";
		}
		else{
			paramType+=pb.getType().getQualifiedName();
		}*/
		//System.out.println("Arg type = "+arg.getType()+" para type = "+pb.getType().getQualifiedName());
		
		if(pb.getType().isParameterizedType()){
			//System.out.println("Arg type = "+arg.getType()+" para type = "+pb.getType().getTypeDeclaration().getQualifiedName());
			paramType = pb.getType().getTypeDeclaration().getQualifiedName();
			
		}
		else {
			paramType = pb.getType().getQualifiedName();
		}
		if (arg.getType().equals(paramType)
						&& arg.getPosition() == pb.getVariableId()) {
				a = arg;
				
				break;
			}
		}
		return a;				   
}
				
  public static void addMethodLocalParameter(E_Argument arg,SingleVariableDeclaration para,IVariableBinding pb,E_Method _method){

		   E_MLocalVariable localvar = Data_Controller.searchLocalVariable1(arg.getName(), arg.getType(), arg.getDeclMethod());
		   
		   E_MRefField pointeeField = null;
		   
		   if(localvar!=null){
			   
			   pointeeField = findpointeeFieldofLocalVariable(localvar, localvar.getDeclMethod());

			   if(pointeeField != null){
		
 				    E_MRefParameter pRef   = AST_Parser.addRefParameters(_method, para.getName().getIdentifier().toString(),
					para.resolveBinding().getType().getQualifiedName().toString(),pointeeField.getDeclaringClass(),pb.getVariableId(), 
					GlobalVariables.READ, para.getNodeType(), pb.getDeclaringMethod().getName().toString(),
					false,pointeeField.getQualifyingObject());
			 			
						if(pRef != null){
							//if(pRef.getFields().isEmpty()){// changes made for plural translation
						     pRef.setField(true);
							 pRef.setfName(pointeeField.getName());
							 pRef.setPfClass(pointeeField.getDeclaringClass());
							 //pRef.setPfType(pointeeField.getType());
							 pRef.setPfType(para.resolveBinding().getType().getQualifiedName().toString());
						
					 		E_MRefField field = addParamField(_method,
			 				pointeeField.getName(), para.resolveBinding().getType().getQualifiedName().toString(),
			 				pointeeField.getDeclaringClass(),
			 				GlobalVariables.READ, para.getNodeType(), false,pointeeField.getQualifyingObject(),pointeeField.isField(),pb.isParameter());
					 		if(field != null){
					 			pRef.addFields(field);
					 		}
						  //}
						}
					 		//System.out.println("This argument is a local variable = "+localvar.getName()+" having field reference="+pointeeField.getName()+" method = "+_method.getName());
	    	}// if ends
		    else{
	    			E_MRefParameter pointeeParam = AST_Parser.ifAliasOfParam(localvar, null, null,localvar.getDeclMethod());
	    	
	    			if(pointeeParam != null)
			    	 { 
	    				 if(pointeeParam.isField() == true && pointeeParam.getPfClass() !=null){		
								
								E_MRefParameter pRef = AST_Parser.addRefParameters(_method, para.getName().getIdentifier().toString(),
								para.resolveBinding().getType().getQualifiedName().toString(),pointeeParam.getPfClass(), pb.getVariableId(), 
								GlobalVariables.READ, para.getNodeType(), pb.getDeclaringMethod().getName().toString(),
								false,pointeeParam.getQualifyingObject());
						 					
									 if(pRef!=null){
										
										 pRef.setField(pointeeParam.isField());
										 pRef.setfName(pointeeParam.getfName());
										 pRef.setPfClass(pointeeParam.getPfClass());
										 pRef.setPfType(pointeeParam.getPfType());
									 }
								 
									 E_MRefField field = addParamField(_method,
							    		pointeeParam.getfName(),pointeeParam.getPfType(),
							    		pointeeParam.getPfClass(),
							    		GlobalVariables.READ,para.getNodeType(),false,pointeeParam.getQualifyingObject(),pointeeParam.isField(),pb.isParameter());
									  if(field!=null){
								 			pRef.addFields(field);
								 		}
							   
							}
			    		// System.out.println("This argument is a local variable = "+localvar.getName()+" having parameter reference="+pointeeParam.getName()+" of method"+_method.getName());
			    	 }// if ends
	    		 }// else ends
		   		}//}
	}
public static void addMethodFieldParameter(E_Argument arg,SingleVariableDeclaration para,IVariableBinding pb,E_Method _method,E_Object obj){
  
	E_Object tempObj = new E_Object();
	
   if(obj!=null){
	 if(obj.getName().equals("this")){
		 if(arg.getDeclaClass()!=null){
		  obj.setName(arg.getDeclaClass());
		 }
	 }
	}
	else{
		obj = tempObj;
	}
   String paramType ="";
 
   if(pb.getType().isParameterizedType()){
		paramType = pb.getType().getTypeDeclaration().getQualifiedName().toString();
		
	}
	else {
		paramType = pb.getType().getQualifiedName().toString();
	}
   
    E_MRefParameter pRef   = AST_Parser.addRefParameters(_method, para.getName().getIdentifier().toString(),paramType,arg.getDeclaClass(), pb.getVariableId(), 
	GlobalVariables.READ, para.getNodeType(), pb.getDeclaringMethod().getName().toString(),
	false,obj);
			
 	 if(pRef!=null){
 		 //if(pRef.getFields().isEmpty()){// changes made for plural translation
		     pRef.setField(arg.isField());
			 pRef.setfName(arg.getName());
			 pRef.setPfClass(arg.getDeclaClass());
			 pRef.setPfType(arg.getType());
			 
			E_MRefField field  = addParamField(_method,
		    arg.getName(), arg.getType(),
		    arg.getDeclaClass(),
		    GlobalVariables.READ,para.getNodeType(),false,obj,arg.isField(),pb.isParameter());
		
			if(field != null){
				pRef.addFields(field);
			}
 		// }
 	 }
		

}
public static void addSubMethodLocalParameter(E_MRefArgument arg,SingleVariableDeclaration para,IVariableBinding pb,E_Method _method){
	
	   E_MLocalVariable localvar = Data_Controller.searchLocalVariable1(arg.getName(), arg.getType(), arg.getDeclMethod());
	   
	   E_MRefField pointeeField = null;
	   
	   if(localvar!=null){
		   
		   pointeeField = findpointeeFieldofLocalVariable(localvar, localvar.getDeclMethod());

		   if(pointeeField!=null){
	
			E_MRefParameter pRef   = AST_Parser.addRefParameters(_method, para.getName().getIdentifier().toString(),
				para.resolveBinding().getType().getQualifiedName().toString(),pointeeField.getDeclaringClass(),pb.getVariableId(), 
				GlobalVariables.READ, para.getNodeType(), pb.getDeclaringMethod().getName().toString(),
				false,pointeeField.getQualifyingObject());
		 			
						 if(pRef!=null){
					     pRef.setField(true);
						 pRef.setfName(pointeeField.getName());
						 pRef.setPfClass(pointeeField.getDeclaringClass());
						 pRef.setPfType(pointeeField.getType());
					 }
				    
			 		addParamField(_method,
			 				pointeeField.getName(), pointeeField.getType(),
			 				pointeeField.getDeclaringClass(),
			 				GlobalVariables.READ,para.getNodeType(),false,pointeeField.getQualifyingObject(),pb.isField(),pb.isParameter());
			 		
			 		//System.out.println("This argument is a local variable = "+localvar.getName()+" having field reference="+pointeeField.getName()+" method = "+_method.getName());
 	}// if ends
	    else{
 			E_MRefParameter pointeeParam = AST_Parser.ifAliasOfParam(localvar, null, null,localvar.getDeclMethod());
		    	
 			if(pointeeParam != null)
		    	 { 
		    		 if(pointeeParam.isField() == true && pointeeParam.getPfClass() !=null){		
							
							E_MRefParameter pRef = AST_Parser.addRefParameters(_method, para.getName().getIdentifier().toString(),
							para.resolveBinding().getType().getQualifiedName().toString(),pointeeParam.getPfClass(), pb.getVariableId(), 
							GlobalVariables.READ, para.getNodeType(), pb.getDeclaringMethod().getName().toString(),
							false,pointeeParam.getQualifyingObject());
					 					
								 if(pRef!=null){
									
									 pRef.setField(pointeeParam.isField());
									 pRef.setfName(pointeeParam.getfName());
									 pRef.setPfClass(pointeeParam.getPfClass());
									 pRef.setPfType(pointeeParam.getPfType());
								 }
							 
							 	addParamField(_method,
						    		pointeeParam.getfName(),pointeeParam.getPfType(),
						    		pointeeParam.getPfClass(),
						    		GlobalVariables.READ,para.getNodeType(),false,pointeeParam.getQualifyingObject(),pb.isField(),pb.isParameter());
						   
						}
		    		// System.out.println("This argument is a local variable = "+localvar.getName()+" having parameter reference="+pointeeParam.getName()+" of method"+_method.getName());
		    	 }// if ends
 		 }// else ends
	   		}//}
}
public static void addSubMethodFieldParameter(E_MRefArgument arg,SingleVariableDeclaration para,IVariableBinding pb,E_Method _method,E_Object obj){

if (arg.getType().equals(pb.getType().getQualifiedName())
			&& arg.getPosition() == pb.getVariableId()) {

E_MRefParameter pRef   = AST_Parser.addRefParameters(_method, para.getName().getIdentifier().toString(),
para.resolveBinding().getType().getQualifiedName().toString(),arg.getDeclaClass(), pb.getVariableId(), 
GlobalVariables.READ, para.getNodeType(), pb.getDeclaringMethod().getName().toString(),
false,obj);
		
if(pRef!=null){
  pRef.setField(arg.isField());
	 pRef.setfName(arg.getName());
	 pRef.setPfClass(arg.getDeclaClass());
	 pRef.setPfType(arg.getType());
}

addParamField(_method,
arg.getName(), arg.getType(),
arg.getDeclaClass(),
GlobalVariables.READ,para.getNodeType(),false,obj,pb.isField(),pb.isParameter());
}
}
	public static E_MRefParameter matchArgWithParam(LinkedList<E_MRefParameter> params, E_Argument a ){
		

		E_MRefParameter temp = null;
		
		for (E_MRefParameter p : params) {	
		
			if(a.getName().equals(p.getName()) && 
					//a.getType().equals(p.getType()) 
					 a.getDeclMethod().equals(p.getDeclMethod())) {		
			
				if(p.isField() == true && p.getPfClass() !=null){	
				
					temp = p;
					
					break;
				}
			}
							
	    }	  
		return temp;
	}
  public static SingleVariableDeclaration matchArgWithParam(List<SingleVariableDeclaration> params, E_Argument a ){
		

	     SingleVariableDeclaration param = null;
		
		for (SingleVariableDeclaration p : params) {	
		
			if(a.getName().equals(p.getName().toString()) && 
					//a.getType().equals(p.getType()) 
					 a.getDeclMethod().equals(p.resolveBinding().getDeclaringMethod().getName())) {		
			
				if(p.resolveBinding().isField() == true){	
				
					param = p;
					
					break;
				}
			}
							
	    }	  
		return param;
	}
	public static List<E_InvokedMethod> findThisMethodInvoData(MethodDeclaration node){
		
		LinkedList<E_InvokedMethod> _invm = Data_Controller.fetchAllInvokedMethods();
		
		//E_InvokedMethod inv =	matchThisMethodInvocation(_invm,_method);
		IMethodBinding thisMethodBind =  getMethodBinding(node);
		
		List<E_InvokedMethod> inv =	matchThisMethodInvocation(_invm,thisMethodBind);
		
		return inv;
	}
	
	public static IMethodBinding getMethodBinding(MethodDeclaration node){
		
		IMethodBinding thisMethodBind = null; 
		
		if(node.resolveBinding() instanceof IMethodBinding){
			
			thisMethodBind = (IMethodBinding) node.resolveBinding();
		}
		
		return thisMethodBind;
			
		
	}
	public static IJavaElement findParentMethod(MethodInvocation thisMethInv){
		
		IJavaElement PMethod = null;
		
		if(thisMethInv != null){
			
			IMethodBinding binding = (IMethodBinding) thisMethInv.getName().resolveBinding();
	
			PMethod =  binding.getJavaElement().getAncestor( IJavaElement.METHOD );
		}
		return PMethod;
	}
	public static void addMethodStatements(E_Method _method, List<Statement> sl){
		
		if (sl != null && !(sl.isEmpty())) {

			for (Statement s : sl) {
				
				   E_MStatements st = new E_MStatements(s.toString(),
						s.getNodeType());
				
				   /* if(s.getNodeType() == ASTNode.RETURN_STATEMENT){
					    ReturnStatement es = (ReturnStatement) s;
						if(es.getExpression().getNodeType() == ASTNode.FIELD_ACCESS){
							FieldAccess f = (FieldAccess)es.getExpression();
							System.out.println("Return field = "+f.getName().toString());
							
							
						}
					    else {
							List<ASTNode> l_children = AST_Parser.getChildren(es.getExpression());
							
							for (ASTNode l_child: l_children) {
							
								if (l_child.getNodeType() == ASTNode.FIELD_ACCESS || l_child.getNodeType() == ASTNode.SIMPLE_NAME || l_child.getNodeType() == ASTNode.QUALIFIED_NAME) {
								
									Expression childExp = (Expression) l_child;
							
								}
							}
						}*/
					
				
				//How to fetch referenced fields from Method Statements
				
				/*if(s.getNodeType() == ASTNode.EXPRESSION_STATEMENT){
						ExpressionStatement es = (ExpressionStatement) s;
						Expression e = es.getExpression();
						if(e.getNodeType() == ASTNode.FIELD_ACCESS){
							getVariableBinding(e);
							
							
						}
					  else {
							
							List<ASTNode> l_children = AST_Parser.getChildren(e);
							
							for (ASTNode l_child: l_children) {
							
								if (l_child.getNodeType() == ASTNode.FIELD_ACCESS || l_child.getNodeType() == ASTNode.SIMPLE_NAME || l_child.getNodeType() == ASTNode.QUALIFIED_NAME) {
								
									Expression childExp = (Expression) l_child;
							
									getVariableBinding(childExp);
								}
							}
						}
					}*/
						
				_method.addStatements(st);
				
			}
		}
	}
	public static E_Method getMainMethod(TypeDeclaration node){
		
		E_Object obj = new E_Object();
		
		HashMap<TypeDeclaration, MethodDeclaration> mainMap = AST_Parser.getMainDeclarationFromProject(node);
	
		TypeDeclaration mainClass = null;

		MethodDeclaration mainDecl = null;
		
		if(mainMap!=null && mainMap.isEmpty() == false){
			
			Set mapSet = (Set) mainMap.entrySet();
			
			Iterator mapIterator = mapSet.iterator();
			
			while (mapIterator.hasNext()) {
	            Map.Entry mapEntry = (Map.Entry) mapIterator.next();
	            // getKey Method of HashMap access a key of map
	            mainClass  = (TypeDeclaration) mapEntry.getKey();
	            //getValue method returns corresponding key's value
	            mainDecl  = (MethodDeclaration) mapEntry.getValue();
	           // System.out.println("Key : " + mainClass.getName().toString() + "= Value : " + mainDecl.getName());
			}
		}
		else{
		
			List<ASTNode> children = AST_Parser.getChildren(node);
		
			for (ASTNode child: children) {
				
				if (child.getNodeType() == ASTNode.METHOD_DECLARATION){
					
					MethodDeclaration md = (MethodDeclaration) child;
					
					IMethodBinding tmb = md.resolveBinding();
					
					if (AST_Parser.ifUserDefinedMethod(tmb)){
			        	  
		          	  if(tmb!=null && tmb.getName().equals("main")){
    		    		mainClass = node;
        	    		mainDecl = md;
			        	break;
			           }
					}
		         }
		        }
		}
		//E_Class fclass= Data_Controller.fetchLastClass();
		
		E_Method method = null;
		
		if(mainDecl!=null){
		
			E_Method m = Data_Controller.searchMethod(mainDecl);
			
			if(m!=null){ 
				  
				method = m;
				  
			  }
			 else{
				 method =  AST_Parser.createNewMethod(mainDecl,obj);	
			  }
		}
		
		E_Class _mainClass = null;
		
		if(mainClass.resolveBinding() != null && mainClass.isInterface() == false){
			  _mainClass  = AST_Parser.createNewClass(mainClass.resolveBinding());
	    }
			
		Data_Generator.getPackage().getClasses().addLast(_mainClass);
		
		AST_Parser.addClassMethod(_mainClass, method);
		
		return method;
	}
	public static void addFieldAccessExp(E_Method _method, IVariableBinding b, String opp , int expType, boolean retExp, E_Object obj) {

		if(b != null){

		 String fName = b.getName().toString();
		
		String fType = b.getType().getQualifiedName().toString();
		
		String declarClass = "";
		
		String declarMethod = "";
			
		if(b.getType().getSuperclass() != null){
				
			 if(b.getType().getSuperclass().isFromSource()){
					
			}
		 }
	
		if(ifFieldVariable(b) ){
			
			declarClass = b.getDeclaringClass().getQualifiedName().toString();
		
			AST_Parser.addMRefField(_method,fName, fType, declarClass, opp, expType, retExp,obj,true,b.isParameter());
		}
		//else if(ifParameterVariable(b)){
		else if(b.isField() == false && b.isParameter() == true){  
			   
				declarMethod = b.getDeclaringMethod().getName().toString();
			
			    int pos = b.getVariableId();
			   
			    //E_MRefField paramField = Data_Controller.searchField(fName, fType,declarClass);
				
			    //E_Object obj = new E_Object();
				
				E_MRefField paramField = searchRefParameterField(b);
			
				if(paramField!=null){
					if(paramField.getQualifyingObject()!=null){
						obj = paramField.getQualifyingObject();
						if(obj.getName().equals("this")){
							obj.setName(paramField.getDeclaringClass());
						}
					}
				}
				
				AST_Parser.addRefParameters(_method,fName, fType,declarClass, pos, opp, expType,declarMethod,retExp,obj);
				
		}
		else if(ifLocalVariable(b)){
			 
			E_MLocalVariable localVar  = Data_Controller.searchLocalVariable(b.getName().toString(), b.getType().getQualifiedName().toString(), b.getDeclaringMethod().getName());
			  
			  //System.out.println("This is a local variable named = "+localVar.getName());
			  
			  E_MRefField pointeefield = findpointeeFieldofLocalVariable(localVar,_method);
			  
			  if(pointeefield != null){
				  
				  pointeefield.setMOperation(GlobalVariables.WRITE);
				  
				  
				  updateFieldAliases(pointeefield,GlobalVariables.WRITE);
			  }
			  else{
				 
				   E_MRefParameter param =  findpointeeParameterofLocalVariable(localVar,_method);  
				  
				   if(param != null){  
				    	// System.out.println("Left Exp = "+laExp+" param="+param.getName());
					   //E_MRefFieldparamField = searchRefParameterField(leftExpB);
				    	E_MRefField paramField = Data_Controller.searchField(param.getfName(), param.getPfType(), param.getPfClass());
					 
           		        if(paramField != null){
           		        	//AST_Parser.addClassField(_method, paramField.getName(), paramField.getType(), paramField.getDeclaringClass(), GlobalVariables.WRITE, raExp.getNodeType(), AST_Parser.ifReturnedField(raExp));
           		        	// AST_Parser.addSimpleName(_method,obj, GlobalVariables.WRITE,raExp.getNodeType());
			    	  	 	paramField.setMOperation(GlobalVariables.WRITE);
           		        	
			    	  	 	updateFieldAliases(paramField,GlobalVariables.WRITE);
			    	  	 	
			    	  	 	updateParamAliases(param,GlobalVariables.WRITE);
			    	  	 	
				   		}
       		        }
			  }
		}
	}
}
/*If this type binding represents any class other than the class java.lang.Object, then the type binding for the direct superclass of this class is returned. 
 * If this type binding represents the class java.lang.Object, then null is returned. 
 * Loops that ascend the class hierarchy need a suitable termination test. 
 * Rather than test the superclass for null, it is more transparent to check whether the class is Object, by comparing whether the class binding is identical to ast.resolveWellKnownType("java.lang.Object"). 
//AST.resolveWellKnownType(String)*/
	public static void addQualifiedFieldExp(E_Method _method, QualifiedName node) {

		IVariableBinding b = AST_Parser.getVariableBinding(node);
		
		String opp = AST_Parser.getFieldOperation(node);
		
		int expType = node.getNodeType();
		
		boolean retExp = AST_Parser.ifReturnedField(node);
		
	if(b != null){

		 String fName = b.getName().toString();
		
		String fType = b.getType().getQualifiedName().toString();
		
		String declarClass = "";
		
		String declarMethod = "";
			
		SimpleName name = null;
		
		E_Object obj = new E_Object();
		
		E_MRefField field = null;

		 SimpleName s =  getQualifier(node);
	    
	     IVariableBinding qBind = null;
	    
	    if(s!=null){
	
	    	qBind = AST_Parser.getVariableBinding(s);
	    }
	    
	    if(qBind != null){
	    	if(ifFieldVariable(qBind)){
    		   field = Data_Controller.searchField(qBind.getName().toString(), qBind.getType().getQualifiedName(), qBind.getDeclaringClass().getQualifiedName());
	    		
	    	}
	    }
    	if(field!=null){
    		if(field.getQualifyingObject()!=null){
    			//System.out.println(field.getQualifyingObject().getName()+"."+field.getName()+"."+b.getName());
    			obj = field.getQualifyingObject();
    			obj.setName(field.getQualifyingObject().getName()+"."+field.getName());
    		}
		
    	}
		else{
			// obj = AST_Parser.getMQualifyingObject(_method);
		}
    	
	    if(ifFieldVariable(b)){
					
			declarClass = b.getDeclaringClass().getQualifiedName().toString();
			AST_Parser.addMRefField(_method,fName, fType, declarClass, opp, expType, retExp,obj,b.isField(),b.isParameter());
		}
		//else if(ifParameterVariable(b)){
		else if(b.isField() == false && b.isParameter() == true){  
			   
			declarMethod = b.getDeclaringMethod().getName().toString();
			
			    int pos = b.getVariableId();
			    
			    E_MRefField paramField = Data_Controller.searchField(fName, fType,declarClass);
				
			 	if(paramField!=null){
					
					obj = paramField.getQualifyingObject();
				}
					AST_Parser.addRefParameters(_method,fName, fType,declarClass, pos, opp, expType,declarMethod,retExp,obj);
				
		}
	}
}
	
public static void addSimleNameExp(SimpleName node){
	
	IVariableBinding bind = AST_Parser.getVariableBinding(node);
	
	E_Method _method = AST_Parser.fetchParentMethod(node);
	
	E_Object obj = new E_Object();
	
	if(bind!=null){
		if(_method!=null){
			/*if(AST_Parser.getMQualifyingObject(_method)!=null){
				obj = getMQualifyingObject(_method);		
			}*/
		
			if(AST_Parser.ifLocalVariable(bind) == false || AST_Parser.ifParameterVariable(bind) || AST_Parser.ifFieldVariable(bind)){
				//if(AST_Parser.ifLocalVariable(bind) == false || AST_Parser.ifFieldVariable(bind)){
						AST_Parser.addSimpleName(_method, node,GlobalVariables.READ,node.getNodeType(),obj);
			}
		}
		
	  }
	}

 public static void addSimpleName(E_Method _method, SimpleName node, String opp, int expType,E_Object obj) {
		if(node.getIdentifier().toString().equals("data")){
			//println("here");
		}
		String fName = node.getIdentifier().toString();
		
		String op = opp;
		
		int pos = 0;
		
		boolean retExp = false;
		
		String declarClass = "";
		
		String declarMethod = "";
		
		String fType = "";

		if(ifReturnedField(node))
			
			retExp = true;
		else
			retExp = false;
		
		IBinding binding = node.resolveBinding();
	
	if (binding instanceof IVariableBinding) {

			IVariableBinding pb = (IVariableBinding) binding;
			
			ITypeBinding typebinding = pb.getType();

			if(typebinding.isParameterizedType()){
				fType = typebinding.getTypeDeclaration().getQualifiedName().toString();
			}
			else{
				fType = typebinding.getQualifiedName().toString();
			}
		 if(pb.getDeclaringMethod() != null){
			 
			// if(ifReferenceType(typebinding)){
					 
			    declarMethod = pb.getDeclaringMethod().getName().toString();
					 
			// }
		 }
		// if simple name is a parameter
		//if (node.getParent().getNodeType() != ASTNode.SINGLE_VARIABLE_DECLARATION) {	
			if (pb.isParameter()) {
				pos = pb.getVariableId();
				//if (pb.getType().isPrimitive() == false) {
					//if (Parser_Utilities.checkWrapperType(pb.getType().getName()) == false) {
				E_MRefField paramField = searchRefParameterField(pb);
				
				if(paramField!=null){
					//obj = paramField.getQualifyingObject();
					/*if(obj.getName().equals("this")){
						obj.setName(paramField.getDeclaringClass());
					}*/
				}
				
				AST_Parser.addRefParameters(_method,pb.getName().toString(), fType,declarClass, pos, op, expType,declarMethod,retExp,obj);
				}
			//}
		//}
		// if simple name is a class field
		else if (node.isDeclaration() == false) {
		  if (pb.isField() == true
					&& pb.getDeclaringClass() != null
					&& pb.getDeclaringClass().getTypeDeclaration().isFromSource()) {
				// fetch field information
				declarClass = pb.getDeclaringClass().getQualifiedName();
				
				List<ASTNode> pNodes = new ArrayList<ASTNode>();
				pNodes = getParents(node);
				if (pNodes != null) {
					for (ASTNode p : pNodes) {
						if (p.getNodeType() == ASTNode.PREFIX_EXPRESSION
								|| p.getNodeType() == ASTNode.POSTFIX_EXPRESSION)
							op = GlobalVariables.WRITE;
					}
				}
				// add field
				if(obj == null){
					E_MRefField classField = Data_Controller.searchMRefField(_method, fName, fType, declarClass);
					if(classField!=null){
						obj = classField.getQualifyingObject();
					}
				}
				else if(obj.getName().equals("this")){
					obj.setName(declarClass);
				}
				 AST_Parser.addMRefField(_method,fName, fType, declarClass,
						op, expType,retExp,obj,pb.isField(),pb.isParameter());

			}
		}
		
	}		
}public static List<Expression> getArguments(MethodInvocation node){
	
	List<Expression> argList = new LinkedList<Expression>();
	
	if(node.arguments() != null){	
		
		for(int i = 0;i < node.arguments().size();i++) {
			argList.add((Expression) node.arguments().get(i));
		}
	}
	return argList;
}
public static void addMethodInvocations(IMethodBinding mb, List<Expression> argList,E_Object obj,MethodDeclaration mDecl){
  
	if(mb!=null){
	   
	 	if(mb.getDeclaringClass().getTypeDeclaration().isFromSource()){
	 	   	 
	 		 E_Class _class =  Data_Controller.fetchLastClass();
	 		
	 		 //E_Class _class =  Data_Controller.searchClass(mb.getDeclaringClass());
	 	  	
	 	     E_InvokedMethod _invokedMethods = new E_InvokedMethod(mb.getName(), mb.getDeclaringClass().getName(), mb.getDeclaringClass().getQualifiedName(), mb.isConstructor(),obj,mDecl);
	 	     
	 	 	
	 	   //if(ifMethodInvocExists(_class,_invokedMethods) == false){
	 	    	AST_Parser.addInvokedMethods( _class, _invokedMethods, argList);
		    //}
		}
   }
}
public static void addSubMethodInvocations(IMethodBinding mb, List<Expression> argList,E_Method pMethod,E_Object obj ){
   
 	if(mb.getDeclaringClass().getTypeDeclaration().isFromSource()){
	
 		E_MInvokedMethod refMethod = new E_MInvokedMethod(mb.getName(), mb.getDeclaringClass().getName(),mb.getDeclaringClass().getQualifiedName(),mb.isConstructor(),obj);

 		if(refMethod != null){
		   AST_Parser.addSubMethods(pMethod,refMethod,argList);
 		}
	}
}

public static E_InvokedMethod matchThisMethodInvocation(List<E_InvokedMethod> invokedMethods, E_Method sourceMethod){
  E_InvokedMethod ThisMethodCall = null;
	for(E_InvokedMethod invokedMethod: invokedMethods){
		    if (invokedMethod.getName().toString().equals(sourceMethod.getName().toString()) 
					 && invokedMethod.getDeclClassQName().toString().equals(sourceMethod.getDeclClassQName().toString())) { 
				   
				   	ThisMethodCall = invokedMethod;
		}
	}
	
	return ThisMethodCall;
	
}
public static List<E_InvokedMethod> matchThisMethodInvocation(List<E_InvokedMethod> invokedMethods, IMethodBinding methodBind){
	  List<E_InvokedMethod> ThisMethodCall = new LinkedList<E_InvokedMethod>();
		for(E_InvokedMethod invokedMethod: invokedMethods){
				//IMethodBinding imb = invokedMethod.resolveMethodBinding();
		      if (invokedMethod.getName().toString().equals(methodBind.getName().toString()) 
						 && invokedMethod.getDeclClassQName().toString().equals(methodBind.getDeclaringClass().getQualifiedName().toString())) { 
					   
		    	  ThisMethodCall.add(invokedMethod);
			}
		}
		
		return ThisMethodCall;
		
	}
public static List<E_MInvokedMethod> searchMethodInvocation(List<E_MInvokedMethod> invokedMethods, E_InvokedMethod sourceMethod){
	    
	    List<E_MInvokedMethod> ThisMethodCall = new ArrayList<E_MInvokedMethod>();
	    
		for(E_MInvokedMethod invokedMethod: invokedMethods){
		      if (invokedMethod.getName().toString().equals(sourceMethod.getName().toString()) 
						 && invokedMethod.getDeclClassQName().toString().equals(sourceMethod.getDeclClassQName().toString())) { 
					   
					   	ThisMethodCall.add(invokedMethod);
			}
		}
		
		return ThisMethodCall;
		
	}
 public static void addMethodInvocationExp(MethodInvocation ThisMethodInv){
	
	  E_Object qualObj = new E_Object();
		
	 if(ThisMethodInv != null){
		
		 IMethodBinding imb = ThisMethodInv.resolveMethodBinding();

	     List<Expression> argList = new LinkedList<Expression>();
	     
	     MethodDeclaration mDecl = fetchParentMethodDecl(ThisMethodInv);

		 imb = ThisMethodInv.resolveMethodBinding();
		 // fetch this method arguments
	     argList = AST_Parser.getArguments(ThisMethodInv);
	 
	    // add method invocation expression at class and method level
	    AST_Parser.addMethodInvocations(imb,argList,qualObj,mDecl);//  should pass parameter list here to map parameters with arguments
	 }
}
	public static IVariableBinding getObjQualifBind(Expression exp){
		
		 IVariableBinding temp = null;
		  
		 if(exp!=null){
			 
			 IVariableBinding qualifierBind = AST_Parser.getVariableBinding(exp);
			 
			 if(qualifierBind != null){
				 
				 if(AST_Parser.ifReferenceType(qualifierBind.getType())){
					
					 temp = qualifierBind;
				 }
			 }
			 
		}
			 return temp;
	}
public static void addSubMethodInvocationExp(MethodInvocation ThisMethodInv){
	
	 IMethodBinding imb = null;
	
	 List<Expression> argList = new LinkedList<Expression>();
		
	 E_Object qualObj = new E_Object();
	
	 if(ThisMethodInv != null){
	
		 imb = ThisMethodInv.resolveMethodBinding();
	
		 E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(ThisMethodInv));

		 IVariableBinding qualBind = AST_Parser.getObjQualifBind(ThisMethodInv.getExpression());
 	
		 // fetch this method arguments
		 argList = AST_Parser.getArguments(ThisMethodInv);
		 
		 // add method invocation expression at class and method level
		 
		 AST_Parser.addSubMethodInvocations(imb,argList,pMethod,qualObj);
		}
	}

	public static void addInvokedMethods(E_Class _class, E_InvokedMethod _invokedMethods, List<Expression> argList){	
	
		   int pos = 0;
		 	
		  // check if arguments are class fields or global variables
			
		   for (Expression exp : argList) {
			   
			   if (exp.getNodeType() == ASTNode.FIELD_ACCESS) {
					
					FieldAccess f = (FieldAccess) exp;

					IVariableBinding fb = f.resolveFieldBinding();
					
					String fName = fb.getName().toString();
					
					ITypeBinding typebinding = fb.getType();
					
					String fType = typebinding.getQualifiedName().toString();
					
					if(typebinding.isParameterizedType()){
						fType = typebinding.getTypeDeclaration().getQualifiedName();
					}
					
					String declarClass="";
					
					String declarMethod = "";
					// add in called methods in a method
					if (fb.isField() == true && fb.isParameter() == false){
							//&& (fb.getType().isFromSource() || fb.getType().isArray())) {
						
						if (fb.getDeclaringClass().getTypeDeclaration()
								.isFromSource()) {
							
							declarClass = fb.getDeclaringClass().getQualifiedName();
							// add in invoked methods
							E_Argument _arguments = new E_Argument(fName,
									fType, declarClass, pos,fb.isField(),declarMethod,fb.isParameter(),false);
							
							_invokedMethods.addArguments(_arguments);
	
							pos++;
							}

						}
					
				}

				else if (exp.getNodeType() == ASTNode.QUALIFIED_NAME) {
					
					QualifiedName qf = (QualifiedName) exp;
					
					IVariableBinding bq = (IVariableBinding) qf
							.resolveBinding();
						// fetch field information
						String fName = qf.getName().toString();
						ITypeBinding typebinding = bq.getType();
						String fType = typebinding.getQualifiedName().toString();
						if(typebinding.isParameterizedType()){
							fType = typebinding.getTypeDeclaration().getQualifiedName();
						}
						String declarClass = "";
						String declarMethod = "";
						
						if(bq.getDeclaringClass()!=null){
								declarClass = bq.getDeclaringClass().getQualifiedName().toString();
						
							if (bq.getDeclaringClass().getTypeDeclaration()
										.isFromSource()) {

								if (bq.isField() == true){ 
										//&& (bq.getType().isFromSource() || bq.getType().isArray())){
							
							// add in invoked methods
								E_Argument _arguments = new E_Argument(fName,
										fType, declarClass, pos,true,declarMethod,bq.isParameter(),false );
								_invokedMethods.addArguments(_arguments);
								pos++;
								}
							}
						}

				}
				else if (exp.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION) {
					
					
				}
				else if(exp.getNodeType() == ASTNode.ARRAY_ACCESS){
					
					String declarClass = "";
									
					String declarMethod = "";
				
					ArrayAccess array = (ArrayAccess) exp;
				
					Expression arrayExp = array.getArray();
				
					IVariableBinding arrayBind = getVariableBinding(arrayExp);
					
					String fName = arrayBind.getName().toString();
					
					String fType = "";
						
					ITypeBinding typebinding = array.resolveTypeBinding();
					
					fType = typebinding.getQualifiedName();
					
					if(typebinding.isParameterizedType()){
						fType = typebinding.getTypeDeclaration().getQualifiedName();
					}
					else{
						fType = typebinding.getQualifiedName();}
					/*if(array.resolveTypeBinding().isArray()){
						fType = exp.resolveTypeBinding().getQualifiedName();
					}
					else{
						 ITypeBinding typebinding = arrayBind.getType();
						 fType = typebinding.getQualifiedName().toString();
						
					}*/
					//System.out.println(arrayExp+" "+ array+" "+array.resolveTypeBinding().isFromSource());
					
					if (arrayBind.isField() && arrayBind.isParameter() == false 
							&& array.resolveTypeBinding().isArray()){ // changes made for plural specifications to distinguish parameters of referenced type
					
						if (arrayBind.getDeclaringClass() != null && arrayBind.getDeclaringClass().getTypeDeclaration()
								.isFromSource()) {
							// add in invoked methods
							E_Argument _arguments = new E_Argument(fName,
									fType, arrayBind.getDeclaringClass().getQualifiedName(), pos,arrayBind.isField(), declarMethod,arrayBind.isParameter(),false );
	
							_invokedMethods.addArguments(_arguments);
							
							pos++;

						}

					}
					else if(arrayBind.isField() == false && arrayBind.isParameter() 
							&& array.resolveTypeBinding().isArray()){// changes made for plural specifications to distinguish parameters of referenced type
						
						declarMethod = arrayBind.getDeclaringMethod().getName().toString();
						
						 E_Argument _argumentss = new E_Argument(fName,
								fType, declarClass, pos, arrayBind.isField(), arrayBind.getDeclaringMethod().getName(),arrayBind.isParameter(),false);
							
						 //matchArgWithParam(param, _argumentss);
							
						_invokedMethods.addArguments(_argumentss);
					     pos++;
						
					}
					else if(arrayBind.isField() == false && arrayBind.isParameter() == false 
							&& array.resolveTypeBinding().isArray()){// changes made for plural specifications to distinguish parameters of referenced type
						
						    E_Argument _argumentss = new E_Argument(fName,
									fType, declarClass, pos, arrayBind.isField(), arrayBind.getDeclaringMethod().getName(),arrayBind.isParameter(),true);
									
							_invokedMethods.addArguments(_argumentss);
							
						     pos++;
					}
					
				}
				// in this case add simple name node complete code where it checks for field name and as a parameter that is a class field
				else if (exp.getNodeType() == ASTNode.SIMPLE_NAME) {
								
					String declarClass = "";
				
					String declarMethod = "";
					
					String fType = "";
		
					SimpleName f = (SimpleName) exp;

					IVariableBinding sb = (IVariableBinding) f.resolveBinding();
					
					// fetch field information
					String fName = f.getIdentifier().toString();
				    
					if(sb!=null){
						
						ITypeBinding typebinding = sb.getType();
						
						fType = typebinding.getQualifiedName().toString();
						
						if(typebinding.isParameterizedType()){
							fType = typebinding.getTypeDeclaration().getQualifiedName();
						}
						
						if (sb.isField() == true && sb.isParameter() == false){ 
								//&& (sb.getType().isFromSource() || sb.getType().isArray())){
							// add ref argument
								if (sb.getDeclaringClass() != null && sb.getDeclaringClass().getTypeDeclaration()
									.isFromSource()) {
							
								// add in invoked methods
								E_Argument _arguments = new E_Argument(fName,
										fType, sb.getDeclaringClass().getQualifiedName(), pos,sb.isField(), declarMethod,sb.isParameter(),false );
		
								_invokedMethods.addArguments(_arguments);
								pos++;

							}
						  }
						  else if (sb.isParameter() == true && sb.isField() == false){
							
								//if(typebinding.isFromSource() || typebinding.isArray()){
									
									declarMethod = sb.getDeclaringMethod().getName().toString();
			
									 E_Argument _argumentss = new E_Argument(fName,
											fType, declarClass, pos,sb.isField(), sb.getDeclaringMethod().getName(),sb.isParameter(),false);
											
									_invokedMethods.addArguments(_argumentss);
								     pos++;
								//}
						     }
						     else if(sb.isField() == false && sb.isParameter() == false){
							  
							  // This is a local variable declared inside method, to process it , it should first be alias of some referenced variables of the method
							       // if(typebinding.isFromSource() || typebinding.isArray() ){
							    		  //&& sb.getType().isFromSource())){	
								  // check here if sb is alias of some referenced variable to consider it as an argument
								  
									  //declarClass = typebinding.getQualifiedName().toString();
							    	  
							    	  //S ystem.out.println("Argument Type Name="+sb.getType().getName()+"Argument Type is From Source = "+sb.getType().isFromSource()+" DeclarinG Class Qualified Name="+sb.getType().getQualifiedName());
										
										 E_Argument _argumentss = new E_Argument(fName,
												fType, declarClass, pos, sb.isField(), sb.getDeclaringMethod().getName(),sb.isParameter(),true);
												
										_invokedMethods.addArguments(_argumentss);
										
									     pos++;
							    	   //}
						           }
						       }
							  else{ 
								  declarClass = "";
								  pos++;
							  }        
				      }// simpleName ends here
					else{
						pos++;
					}// else ends here
				}
		 
		   _class.addInvokMethods(_invokedMethods);  
	
		}
	
public static void addSubMethods(E_Method _method, E_MInvokedMethod refMethod, List<Expression> argList){
	
	int pos = 0;
	
	boolean flag = false;
	  // check if arguments are class fields or global variables
	if(argList!=null && argList.isEmpty()== false){
		
	 for (Expression exp : argList) {
		 
		 if (exp.getNodeType() == ASTNode.FIELD_ACCESS || exp.getNodeType() == ASTNode.SIMPLE_NAME || exp.getNodeType() == ASTNode.QUALIFIED_NAME) {
			 
			 addMethodArguments(exp,_method,refMethod,argList);
		/*		
		 if (exp.getNodeType() == ASTNode.FIELD_ACCESS) {
				
				FieldAccess f = (FieldAccess) exp;

				IVariableBinding fb = f.resolveFieldBinding();
				
				String fName = fb.getName().toString();
				
				ITypeBinding typebinding = fb.getType();
				
				String fType = typebinding.getQualifiedName().toString();
				
				String declarClass="";
				
				String declarMethod = "";
				// add in called methods in a method
				if (fb.isField() == true) 
						//&& (fb.getType().isFromSource() || fb.getType().isArray()))
				{
					
					if (fb.getDeclaringClass().getTypeDeclaration()
							.isFromSource()) {
						
						declarClass = fb.getDeclaringClass().getQualifiedName();
					
							// add in nested methods
						E_MRefArgument _refarg = new E_MRefArgument(fName,
								fType, declarClass, pos,true,declarMethod,false);
						
						refMethod.addRefArguments(_refarg);
						
						flag = true;
						pos++;
						}

					}
				
			}

			else if (exp.getNodeType() == ASTNode.QUALIFIED_NAME) {
				
				QualifiedName qf = (QualifiedName) exp;
				
				IVariableBinding bq = (IVariableBinding) qf
						.resolveBinding();
					// fetch field information
					String fName = qf.getName().toString();
					ITypeBinding typebinding = bq.getType();
					String fType = typebinding.getQualifiedName().toString();
					String declarClass = "";
					String declarMethod = "";
					
					if(bq.getDeclaringClass()!=null){
							declarClass = bq.getDeclaringClass().getQualifiedName().toString();
					
						if (bq.getDeclaringClass().getTypeDeclaration()
									.isFromSource()) {

							if (bq.isField() == true ){
									//&& (bq.getType().isFromSource() || bq.getType().isArray())){
						
								// add in nested methods
							E_MRefArgument _refarg = new E_MRefArgument(fName,
									fType, declarClass, pos,true,declarMethod,false);
							
							refMethod.addRefArguments(_refarg);
							flag = true;
							pos++;
						
							}
						}
					}

			}
			else if (exp.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION) {
				
				
			}
			// in this case add simple name node complete code where it checks for field name and as a parameter that is a class field
			else if (exp.getNodeType() == ASTNode.SIMPLE_NAME) {

				SimpleName f = (SimpleName) exp;

				IVariableBinding sb = (IVariableBinding) f.resolveBinding();
				
				// fetch field information
				String fName = f.getIdentifier().toString();
				ITypeBinding typebinding = sb.getType();
				String fType = typebinding.getQualifiedName().toString();
				String declarClass = "";
				String declarMethod = "";
				
				
					if (sb.isField() == true){ 
							//&& (sb.getType().isFromSource() || sb.getType().isArray())){
						// add ref argument
							if (sb.getDeclaringClass() != null && sb.getDeclaringClass().getTypeDeclaration()
								.isFromSource()) {
						
							// add in nested methods

							E_MRefArgument _refarg = new E_MRefArgument(fName,
									fType, sb.getDeclaringClass().getQualifiedName(), pos,true,declarMethod,false);
							
							refMethod.addRefArguments(_refarg);
							
							flag = true;
							pos++;
						

						}
					  }
					  else if (sb.isParameter()){
						
							//if(typebinding.isFromSource() || typebinding.isArray()){
								
								declarMethod = sb.getDeclaringMethod().getName().toString();

								E_MRefArgument _refarg = new E_MRefArgument(fName,
										fType, declarClass, pos,false, sb.getDeclaringMethod().getName(),true);
								
								refMethod.addRefArguments(_refarg);
								
								flag = true;
								pos++;
							
							//}
					     }
					     else if(sb.isField() == false && sb.isParameter() == false){
						  
						  // This is a local variable declared inside method, to process it , it should first be alias of some referenced variables of the method
						     // if(typebinding.isFromSource() || (typebinding.isArray() && sb.getType().isFromSource())){	
							  // check here if sb is alias of some referenced variable to consider it as an argument
							  

								  // declarClass = typebinding.getQualifiedName().toString();
								
								  //S ystem.out.println("Argument Type Name="+sb.getType().getName()+"Argument Type is From Source = "+sb.getType().isFromSource()+" DeclarinG Class Qualified Name="+sb.getType().getQualifiedName());
									
									E_MRefArgument _refarg = new E_MRefArgument(fName,
											fType, declarClass, pos,false, sb.getDeclaringMethod().getName(),false);
									
									refMethod.addRefArguments(_refarg);
								
									flag = true;
									pos++;
								
						    	 //}
					     	   }
							  else{ 
								  declarClass = "";
								  pos++;
							  }        
			      }// simpleName ends here*/
			      }
			      else {
					List<ASTNode> l_children = AST_Parser.getChildren(exp);
					for (ASTNode l_child: l_children) {
						if (l_child.getNodeType() == ASTNode.FIELD_ACCESS || l_child.getNodeType() == ASTNode.SIMPLE_NAME 
							|| l_child.getNodeType() == ASTNode.QUALIFIED_NAME){
							Expression childExp = (Expression) l_child;
							 addMethodArguments(childExp,_method,refMethod,argList);
						}
						
					}
				}
				/*else{
					
					 pos++;
				}*/
			
	 }// loop ends here	
	}
   if(_method!= null && refMethod!=null){
	 
	   _method.addSubMethods(refMethod);
	}  	
}

public static void addMethodArguments(Expression exp, E_Method _method, E_MInvokedMethod refMethod, List<Expression> argList){
	
	boolean flag = false;
	
	int pos = 0;
	
	if (exp.getNodeType() == ASTNode.FIELD_ACCESS) {
		
		FieldAccess f = (FieldAccess) exp;

		IVariableBinding fb = f.resolveFieldBinding();
		
		String fName = fb.getName().toString();
		
		ITypeBinding typebinding = fb.getType();
		
		String fType = typebinding.getQualifiedName().toString();
		
		String declarClass="";
		
		String declarMethod = "";
		// add in called methods in a method
		if (fb.isField() == true) 
				//&& (fb.getType().isFromSource() || fb.getType().isArray()))
		{
			
			if (fb.getDeclaringClass().getTypeDeclaration()
					.isFromSource()) {
				
				declarClass = fb.getDeclaringClass().getQualifiedName();
			
					// add in nested methods
				E_MRefArgument _refarg = new E_MRefArgument(fName,
						fType, declarClass, pos,true,declarMethod,false);
				
				refMethod.addRefArguments(_refarg);
				
				flag = true;
				pos++;
				}

			}
		
	}

	else if (exp.getNodeType() == ASTNode.QUALIFIED_NAME) {
		
		QualifiedName qf = (QualifiedName) exp;
		
		IVariableBinding bq = (IVariableBinding) qf
				.resolveBinding();
			// fetch field information
			String fName = qf.getName().toString();
			ITypeBinding typebinding = bq.getType();
			String fType = typebinding.getQualifiedName().toString();
			String declarClass = "";
			String declarMethod = "";
			
			if(bq.getDeclaringClass()!=null){
					declarClass = bq.getDeclaringClass().getQualifiedName().toString();
			
				if (bq.getDeclaringClass().getTypeDeclaration()
							.isFromSource()) {

					if (bq.isField() == true ){
							//&& (bq.getType().isFromSource() || bq.getType().isArray())){
				
						// add in nested methods
					E_MRefArgument _refarg = new E_MRefArgument(fName,
							fType, declarClass, pos,true,declarMethod,false);
					
					refMethod.addRefArguments(_refarg);
					flag = true;
					pos++;
				
					}
				}
			}

	}
	else if (exp.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION) {
		
		
	}
	// in this case add simple name node complete code where it checks for field name and as a parameter that is a class field
	else if (exp.getNodeType() == ASTNode.SIMPLE_NAME) {

		SimpleName f = (SimpleName) exp;
		
		IVariableBinding sb  = null;
		
		if(f.resolveBinding() instanceof IVariableBinding){
			sb =  (IVariableBinding) f.resolveBinding();
		}
		// fetch field information
		if(sb!=null){
			String fName = f.getIdentifier().toString();
			ITypeBinding typebinding = sb.getType();
			String fType = typebinding.getQualifiedName().toString();
			String declarClass = "";
			String declarMethod = "";
		
		
			if (sb.isField() == true){ 
					//&& (sb.getType().isFromSource() || sb.getType().isArray())){
				// add ref argument
					if (sb.getDeclaringClass() != null && sb.getDeclaringClass().getTypeDeclaration()
						.isFromSource()) {
				
					// add in nested methods

					E_MRefArgument _refarg = new E_MRefArgument(fName,
							fType, sb.getDeclaringClass().getQualifiedName(), pos,true,declarMethod,false);
					
					refMethod.addRefArguments(_refarg);
					
					flag = true;
					pos++;
				

				}
			  }
			  else if (sb.isParameter()){
				
					//if(typebinding.isFromSource() || typebinding.isArray()){
						
						declarMethod = sb.getDeclaringMethod().getName().toString();

						E_MRefArgument _refarg = new E_MRefArgument(fName,
								fType, declarClass, pos,false, sb.getDeclaringMethod().getName(),true);
						
						refMethod.addRefArguments(_refarg);
						
						flag = true;
						pos++;
					
					//}
			     }
			     else if(sb.isField() == false && sb.isParameter() == false){
				  
				  // This is a local variable declared inside method, to process it , it should first be alias of some referenced variables of the method
				     // if(typebinding.isFromSource() || (typebinding.isArray() && sb.getType().isFromSource())){	
					  // check here if sb is alias of some referenced variable to consider it as an argument
					  

						  // declarClass = typebinding.getQualifiedName().toString();
						
						  //S ystem.out.println("Argument Type Name="+sb.getType().getName()+"Argument Type is From Source = "+sb.getType().isFromSource()+" DeclarinG Class Qualified Name="+sb.getType().getQualifiedName());
							
							E_MRefArgument _refarg = new E_MRefArgument(fName,
									fType, declarClass, pos,false, sb.getDeclaringMethod().getName(),false);
							
							refMethod.addRefArguments(_refarg);
						
							flag = true;
							pos++;
						
				    	 //}
			     	   }
					  else{ 
						  declarClass = "";
						  pos++;
					  }        
	      }// simpleName ends here
		else{
		
		 pos++;
		}
	}
	
}
public static E_Object getFieldAccessQualObj(FieldAccess node,E_Method _method){
	Expression thisExp = null;
	E_Object obj = new E_Object();
	thisExp = AST_Parser.findThisExpression(node);
	if(thisExp != null){
		obj = _method.getQualifyingObject();
	}
	return obj;
}
	public static E_Object getQualFieldAccessQualObj(QualifiedName node,E_Method _method){
	
	E_Object obj = new E_Object();
	
	SimpleName qualifier = AST_Parser.getQualifier(node);
	
	//MethodDeclaration mDec = AST_Parser.getMethodDeclaration(_method);
	
	//obj = AST_Parser.getMQualifyingObject(_method);
	

	/*E_MRefField field = AST_Parser.addQualObjofInvokMethod(qualifier, _method, node.getNodeType());
	
	if(field!=null){
		obj = field.getQualifyingObject();
	}
	else{
		obj = getMQualifyingObject(_method);
	}*/
	return obj;
	}
public static E_Object getSuperFieldAccessQualObj(SuperFieldAccess node,E_Method _method){

E_Object obj = new E_Object();

SimpleName qualifier = AST_Parser.getSuperFieldQualifier(node);
//MethodDeclaration mDec = AST_Parser.getMethodDeclaration(_method);
//obj = getMQualifyingObject(_method);
//obj = AST_Parser.getQualifyingObject(mDec);
/*E_MRefField field = AST_Parser.addQualObjofInvokMethod(qualifier, _method, node.getNodeType());

//if(field!=null){
	//obj = field.getQualifyingObject();
//}
else{
	obj = getMQualifyingObject(_method);
}*/
return obj;
}
public static void addAssignmentStatem(Expression laExp,Expression raExp,E_Method _method){
	
		E_Object qualObj = getExpressionObject(laExp,_method);
		
		int expType=-1;
		
		if(_method.isConstr()){
			//if(qualObj.equals(_method.getQualifyingObject())){
				expType = ASTNode.CLASS_INSTANCE_CREATION;
			//}
			//else{
				//expType = AST_Parser.findRightHandExpType(raExp);
			//}
			
		}
		else{
			expType = AST_Parser.findRightHandExpType(raExp);
		}
	    E_MRefField pointeefield = null;
		
		IVariableBinding leftExpB = AST_Parser.getVariableBinding(laExp);
		
		IVariableBinding rightExpB = AST_Parser.getVariableBinding(raExp);

		if(laExp.getNodeType() == ASTNode.FIELD_ACCESS){
						
						//FieldAccess node = (FieldAccess) laExp;
						
						//E_Object obj  = AST_Parser.getFieldAccessQualObj(node, _method);
					
						if(ifReferenceType(leftExpB.getType())  && laExp.getParent().getNodeType() != ASTNode.ARRAY_ACCESS){
							
							 checkRighHandSides(leftExpB, raExp, _method);
							 
						}
						else if(ifReferenceType(leftExpB.getType())  && laExp.getParent().getNodeType() == ASTNode.ARRAY_ACCESS){
								
							    E_MRefField sourceField = Data_Controller.searchMRefField(_method,leftExpB.getName().toString(), leftExpB.getType().getQualifiedName(), leftExpB.getDeclaringClass().getQualifiedName());
				    		
								// E_MRefField sourceField = Data_Controller.searchField(leftExpB.getName().toString(), leftExpB.getType().getQualifiedName(), leftExpB.getDeclaringClass().getQualifiedName());
				    		
					    		 if(sourceField != null){
					    			 
					    			    pointeefield = null;
								   		
					    			    pointeefield = findpointeeFieldofField(leftExpB,_method);// Do we need to update this pointeeField
						   			  
						   			     if(pointeefield != null){
						   			    	 
						   			    	 pointeefield.setMOperation(GlobalVariables.WRITE);
						   				 
						   			    	 updateFieldAliases(pointeefield,GlobalVariables.WRITE);
						   			     }
						   			     else{
		
						   			    	 sourceField.setMOperation(GlobalVariables.WRITE);
						    			
						   			    	 updateFieldAliases(sourceField,GlobalVariables.WRITE);
						   			     }
						    		  
					   			  }
				   			      else{
				   			    	 	 AST_Parser.addFieldAccessExp(_method,leftExpB,GlobalVariables.WRITE,expType,AST_Parser.ifReturnedField(laExp),qualObj);
				   			    	 	
				   			    	     if(raExp.getNodeType() != ASTNode.NUMBER_LITERAL && raExp.getNodeType() != ASTNode.NULL_LITERAL && raExp.getNodeType() != ASTNode.CLASS_INSTANCE_CREATION && raExp.getNodeType() != ASTNode.ARRAY_CREATION){
				   			    	       
				   			    	    	//IVariableBinding rqualBind = getObjQualifBind(laExp);
									       
				   			    	    	AST_Parser.addFieldAccessExp(_method,rightExpB,GlobalVariables.READ, expType, AST_Parser.ifReturnedField(raExp),qualObj);
									    }
				   			     }
					    		 
					    }
				       else{
					
				           AST_Parser.addFieldAccessExp(_method,leftExpB,GlobalVariables.WRITE,expType,AST_Parser.ifReturnedField(laExp),qualObj);	
				        }
				 }
		
    if (laExp.getNodeType() == ASTNode.QUALIFIED_NAME) {
        
	     E_Object qObj = null;
	    
         SimpleName qualifier =  getQualifier(laExp);
	    
	     IVariableBinding qBind = null;
	    
	    if(qualifier!=null){
	
	    	qBind = AST_Parser.getVariableBinding(qualifier);
	    }
	 
		 if(qBind != null){
			// AST_Parser.addSimpleName(_method,qualifier, GlobalVariables.WRITE,expType,sourceParamField.getQualifyingObject());
        					
	       if(ifLocalVariable(qBind)){
	    	  	 pointeefield = null;
				 E_MLocalVariable localVar  = Data_Controller.searchLocalVariable(qBind.getName().toString(),qBind.getType().getQualifiedName().toString(), qBind.getDeclaringMethod().getName());
				 pointeefield = findpointeeFieldofLocalVariable(localVar,_method);
				 if(pointeefield!=null){
    	    		pointeefield.setMOperation(GlobalVariables.WRITE);
	    			updateFieldAliases(pointeefield,GlobalVariables.WRITE);
	    			qObj = pointeefield.getQualifyingObject();
	    		
	    			AST_Parser.addFieldAccessExp(_method,leftExpB,GlobalVariables.WRITE,expType,AST_Parser.ifReturnedField(laExp),qObj);	
	    		    if(raExp.getNodeType() != ASTNode.NUMBER_LITERAL && raExp.getNodeType() != ASTNode.NULL_LITERAL && raExp.getNodeType() != ASTNode.CLASS_INSTANCE_CREATION && raExp.getNodeType() != ASTNode.ARRAY_CREATION){
	    		    	  AST_Parser.addFieldAccessExp(_method,rightExpB,GlobalVariables.READ, expType, AST_Parser.ifReturnedField(raExp),qObj);
	    		    }
				 }
				 
			}
		   else if(ifParameterVariable(qBind)){
			   E_MRefField sourceParamField = null;
			   E_MRefParameter sourceParam = searchRefParameter(qBind);
			   if(sourceParam!=null){  
				  if(sourceParam.isField()){
				     sourceParamField = searchRefParameterField(qBind);
				  }
	    		   if(sourceParamField!=null){
	    			    AST_Parser.addSimpleName(_method,qualifier, GlobalVariables.WRITE,expType,sourceParamField.getQualifyingObject());
		    	  	 	updateFieldAliases(sourceParamField,GlobalVariables.WRITE);
		    	  	 	updateParamAliases(sourceParam,GlobalVariables.WRITE);
		    	  	 	
		    	  	 	//AST_Parser.addFieldAccessExp(_method,leftExpB,GlobalVariables.WRITE,expType,AST_Parser.ifReturnedField(laExp),sourceParamField.getQualifyingObject());	
		    		    if(raExp.getNodeType() != ASTNode.NUMBER_LITERAL && raExp.getNodeType() != ASTNode.NULL_LITERAL && raExp.getNodeType() != ASTNode.CLASS_INSTANCE_CREATION && raExp.getNodeType() != ASTNode.ARRAY_CREATION){
		    		    	  AST_Parser.addFieldAccessExp(_method,rightExpB,GlobalVariables.READ, expType, AST_Parser.ifReturnedField(raExp),sourceParamField.getQualifyingObject());
		    		    }
			   		}
			   }
		  }
		  else if(ifFieldVariable(qBind)){
		  	   
			    pointeefield = null;
		       
			    pointeefield = Data_Controller.searchField(qBind.getName().toString(), qBind.getType().getQualifiedName(), qBind.getDeclaringClass().getQualifiedName());
	    		
			    if(pointeefield != null){
	    			pointeefield.setMOperation(GlobalVariables.WRITE);
	    			updateFieldAliases(pointeefield,GlobalVariables.WRITE);
	    			qObj = pointeefield.getQualifyingObject();
				}
			    else{
			      //AST_Parser.addFieldAccessExp(_method,leftExpB,GlobalVariables.WRITE,expType,AST_Parser.ifReturnedField(laExp),qObj);	
			    }
	    		if(raExp.getNodeType() != ASTNode.NUMBER_LITERAL && raExp.getNodeType() != ASTNode.NULL_LITERAL && raExp.getNodeType() != ASTNode.CLASS_INSTANCE_CREATION && raExp.getNodeType() != ASTNode.ARRAY_CREATION){
    		    	  AST_Parser.addFieldAccessExp(_method,rightExpB,GlobalVariables.READ, expType, AST_Parser.ifReturnedField(raExp),qObj);
    		    }
	    		
	     }
		 //}
	   /// check here for qualifier as a reference type
	     //qObj = AST_Parser.getMQualifyingObject(_method);
		  /*AST_Parser.addFieldAccessExp(_method,leftExpB,GlobalVariables.WRITE,expType,AST_Parser.ifReturnedField(laExp),qualObj);	
	      if(raExp.getNodeType() != ASTNode.NUMBER_LITERAL && raExp.getNodeType() != ASTNode.NULL_LITERAL && raExp.getNodeType() != ASTNode.CLASS_INSTANCE_CREATION && raExp.getNodeType() != ASTNode.ARRAY_CREATION){
	    	  AST_Parser.addFieldAccessExp(_method,rightExpB,GlobalVariables.READ, expType, AST_Parser.ifReturnedField(raExp),qualObj);
	      }	*/
		 }
	   
  }
  else if (laExp.getNodeType() == ASTNode.SIMPLE_NAME){
	
	  // if left side is local variable	or a parameter
		    if(leftExpB.getDeclaringMethod() != null && leftExpB.getDeclaringClass() == null){	 
			    	   
			  	      if(ifReferenceType(leftExpB.getType()) && laExp.getParent().getNodeType() == ASTNode.ARRAY_ACCESS) {
									
			  	    	 	pointeefield = null;
			  	    	 	
			  	    	 	if(ifLocalVariable(leftExpB)){
								
								 E_MLocalVariable localVar  = 
								
										Data_Controller.searchLocalVariable(leftExpB.getName().toString(),
										leftExpB.getType().getQualifiedName().toString(), 
										leftExpB.getDeclaringMethod().getName());
								  
								  //System.out.println("This is a local variable named = "+localVar.getName());
								  
								  pointeefield = findpointeeFieldofLocalVariable(localVar,_method);
								  
								  if(pointeefield != null){
									  
									  pointeefield.setMOperation(GlobalVariables.WRITE);
									 
									  updateFieldAliases(pointeefield,GlobalVariables.WRITE);
								  }
								  else 
								  {
									 
									 // System.out.println("laExp = "+laExp+" raExp="+raExp+" method"+ _method.getName());

									   E_MRefParameter param =  findpointeeParameterofLocalVariable(localVar,_method);
									    
									   if(param!=null){  

									    	// System.out.println("Left Exp = "+laExp+" param="+param.getName());
										   //E_MRefFieldparamField = searchRefParameterField(leftExpB);
									    	E_MRefField paramField = Data_Controller.searchField(param.getfName(), param.getPfType(), param.getPfClass());
										 
					            		     if(paramField!=null){
					            			   
					            		    //	 System.out.println("Left Exp = "+laExp+" param Field="+paramField.getName());
			
					            			   AST_Parser.addRefParameters(_method, param.getName(), param.getType(), param.getDeclaringClass(), param.getPosition(), GlobalVariables.WRITE, raExp.getNodeType(), param.getDeclMethod(),  AST_Parser.ifReturnedField(raExp),paramField.getQualifyingObject());
									           
					            			    //AST_Parser.addClassField(_method, paramField.getName(), paramField.getType(), paramField.getDeclaringClass(), GlobalVariables.WRITE, raExp.getNodeType(), AST_Parser.ifReturnedField(raExp));
					            			   // AST_Parser.addSimpleName(_method,obj, GlobalVariables.WRITE,raExp.getNodeType());
								    	  	 	updateFieldAliases(paramField,GlobalVariables.WRITE);
								    	  	 	updateParamAliases(param,GlobalVariables.WRITE);
								    	  	 	
									   		}
				            		   }
								  }
								  
							  }
							else if(ifParameterVariable(leftExpB) && laExp.getParent().getNodeType() == ASTNode.ARRAY_ACCESS){
								 
								   SimpleName obj = (SimpleName) laExp;
							  
			            		   E_MRefParameter sourceParam = searchRefParameter(leftExpB);
			            		
			            		  // System.out.println(" 2nd time in assignem hand operation laExp = "+laExp+" in method = "+_method.getName());
			            		    
			            			   
			            		   if(sourceParam!=null){  
			            			    
			            				  E_MRefField sourceParamField = null;
			            			   
			            			     if(sourceParam.isField() == true){
			            			        sourceParamField = searchRefParameterField(leftExpB);
			            			        if(sourceParamField !=null){
			            				    	
			            			        	AST_Parser.addSimpleName(_method,obj, GlobalVariables.WRITE,expType,sourceParamField.getQualifyingObject());
						            			   
						            			 updateParamAliases(sourceParam,GlobalVariables.WRITE);

			            				    	 sourceParamField.setMOperation(GlobalVariables.WRITE);
			            				    	 
			            				    	 updateFieldAliases(sourceParamField,GlobalVariables.WRITE);
							    	  	  }
			            			   }
			            		      
			            		   }
							}//else if
							
					}
				  else if(ifReferenceType(leftExpB.getType()) && laExp.getParent().getNodeType() != ASTNode.ARRAY_ACCESS ){
					 checkRighHandSides(leftExpB, raExp, _method);
				  }
				  else {	
					    SimpleName fff = (SimpleName) laExp;
					    
					    AST_Parser.addSimpleName(_method,fff,GlobalVariables.WRITE,expType,qualObj);
		    	 }					 
			}
	       else if (leftExpB.getDeclaringClass() != null){
						
			   if (leftExpB.getDeclaringClass().getTypeDeclaration().isFromSource()){
								
					if(ifReferenceType(leftExpB.getType())  && laExp.getParent().getNodeType() != ASTNode.ARRAY_ACCESS){
						
						 checkRighHandSides(leftExpB, raExp, _method);
					}
					else if(ifReferenceType(leftExpB.getType())  && laExp.getParent().getNodeType() == ASTNode.ARRAY_ACCESS){
	
					        E_MRefField sourceField = Data_Controller.searchMRefField(_method,leftExpB.getName().toString(), leftExpB.getType().getQualifiedName(), leftExpB.getDeclaringClass().getQualifiedName());
			    		
						    // E_MRefField sourceField = Data_Controller.searchField(leftExpB.getName().toString(), leftExpB.getType().getQualifiedName(), leftExpB.getDeclaringClass().getQualifiedName());
				    		
				    		 if(sourceField!=null){
				    			 
				    			    pointeefield = null;
							   		
				    			    pointeefield = findpointeeFieldofField(leftExpB,_method);// Do we need to update this pointeeField
					   			  
					   			     if(pointeefield != null){
					   			    	 
					   			    	 pointeefield.setMOperation(GlobalVariables.WRITE);
					   				 
					   			    	 updateFieldAliases(pointeefield,GlobalVariables.WRITE);
					   			     }
					   			     else{
	
					   			    	 sourceField.setMOperation(GlobalVariables.WRITE);
					    			
					   			    	 updateFieldAliases(sourceField,GlobalVariables.WRITE);
					   			     }
				    	    }
			    		    else 
			    		    {	
						      SimpleName fff = (SimpleName) laExp;
						    
						      AST_Parser.addSimpleName(_method,fff, GlobalVariables.WRITE,expType,qualObj);
			    		    }	  
					}
					else {	
					    SimpleName fff = (SimpleName) laExp;
					    
					    AST_Parser.addSimpleName(_method,fff, GlobalVariables.WRITE,expType,qualObj);
		    		 }	  
			  }
	       }	       
  	}
}

public static IVariableBinding ifAliasofParameter(E_MLocalVariable sourceVar, E_MRefField sourceField, E_MRefParameter sourceParameter,E_Method method){

	boolean flag = false;
	
	LinkedList<E_MRefAlias> aliases = null;
	
	IVariableBinding paraBind= null;
	
	if(sourceVar != null){
		
		aliases = Data_Controller.fetchAliasOfMLocalVar(sourceVar, sourceVar.getDeclMethod());
	}
	else if (sourceField != null){
		
		aliases = Data_Controller.fetchAliasesOfRefField(sourceField);
	}
	if(aliases != null){
					
	  for (E_MRefAlias a: aliases){
			    	 
		 if(a.getParaBind() != null){
			    	   
		   if(a.getParaBind().isParameter()){
				paraBind = a.getParaBind();	
			    flag = true;	
			}
		 }
		}
	}
	return paraBind;
	 // return flag;
}
public static boolean ifAliasofMethodCall(E_MLocalVariable sourceVar,E_MRefField sourceField){

	boolean flag = false;
	
	LinkedList<E_MRefAlias> aliases = null;
			
	if(sourceVar !=null){
		aliases = Data_Controller.fetchAliasOfMLocalVar(sourceVar, sourceVar.getDeclMethod());
	}
	else if (sourceField != null){
		aliases = Data_Controller.fetchAliasesOfRefField(sourceField);
	}
		   
	if(aliases != null){			
	  for (E_MRefAlias a: aliases)	{   	 
		 if(a.getMethodBinding() != null)
			 flag = true;	
	  }
	}
	  return flag;
}
public static void updateLocalAsFieldAlias(LinkedList<E_Class> _class){

LinkedList<E_Method> _methods = null;

E_MRefField pointeeField = null;

E_MRefField pointerField = null;

E_MLocalVariable pointeeLocalVar = null;

IVariableBinding paraBind = null;

IMethodBinding methodBind = null;
	
 for (E_Class c : _class) {
	
	_methods = c.getMethods();
	
  for (E_Method m : _methods) {
	  	  
	  LinkedList<E_MLocalVariable> localList = null;
	
	  localList =  m.getLocalVar();
	  
	for(int i = 0; i < localList.size();i++)  {
	
		LinkedList<E_MRefAlias> aliases = null;
		
		aliases = localList.get(i).getAliases();
	   
	     for (E_MRefAlias a: aliases){
	    	 
	    	 if(a.getParaBind() != null ){
	    	   
		    	   if(a.getParaBind().isParameter()){
						
		    		    pointeeLocalVar = null;
						
						pointeeField = searchRefParameterField(a.getParaBind());
						
						if(pointeeField != null){

							AST_Parser.addPointer(localList.get(i),pointerField,null, pointeeField, pointeeLocalVar,null, GlobalVariables.ALIAS, pointeeField.getExpType(),paraBind, methodBind, m);
							
						}
		    	   }
		    	   
			}
	       else if(a.getMethodBinding() != null){	 
					
	    	              //LinkedList<E_MRefField> _fields = Data_Controller.fetchMRefFields(a.getMethodBinding());
	    	   
						LinkedList<E_MRefField> _fields = m.getRefVariable();						
						
						for(E_MRefField r: _fields){
								
								if(r.getMethod().getName().equals(localList.get(i).getDeclMethod().getName()) && r.getType().equals(localList.get(i).getType())){
								
									if(r.isRetFiel())
									
										pointeeField = r;
									
										if(pointeeField != null){
											
											AST_Parser.addPointer(localList.get(i),pointerField,null, pointeeField, pointeeLocalVar,null, GlobalVariables.ALIAS, pointeeField.getExpType(),paraBind,methodBind,m);
										
											break;
										}
								}
	       				 	}
									
						}
										
					}
				}
  				}
 			}
 			}
	  
public static void updateFieldAsFieldAlias(LinkedList<E_Class> _class){

LinkedList<E_Method> _methods = null;

E_MRefField pointeeField = null;

E_MLocalVariable pointerLocalVar = null;

E_MLocalVariable pointeeLocalVar = null;

IVariableBinding paraBind = null;

IMethodBinding methodBind = null;
	
 for (E_Class c : _class) {
	
	_methods = c.getMethods();
	
  for (E_Method m : _methods) {
	  	  
	  LinkedList<E_MRefField> refFields = null;
	
	  refFields =  m.getRefVariable();
	  
	for(int i =0; i < refFields.size();i++)  {
	
	//for(E_MLocalVariable localVarPointer: localList){
	
		LinkedList<E_MRefAlias> aliases = null;
		
		aliases = refFields.get(i).getAliases();
	   
	     for (E_MRefAlias a: aliases){
	    	 
	    	 if(a.getParaBind() != null ){
	    	   
		    	   if(a.getParaBind().isParameter()){
						
		    		    pointeeLocalVar = null;
						
						pointeeField = searchRefParameterField(a.getParaBind());
						
						if(pointeeField != null){

							AST_Parser.addPointer(pointerLocalVar, refFields.get(i),null, pointeeField, pointeeLocalVar,null, GlobalVariables.ALIAS, pointeeField.getExpType(),paraBind, methodBind, m);
		
					
						}
		    	   }
		    	   
			}
	       else if(a.getMethodBinding() != null){	 
					
	    	              //LinkedList<E_MRefField> _fields = Data_Controller.fetchMRefFields(a.getMethodBinding());
	    	   
						LinkedList<E_MRefField> _fields = m.getRefVariable();						
						
						for(E_MRefField r: _fields){
								
								if(r.getDeclaringClass().equals(refFields.get(i).getDeclaringClass()) && r.getType().equals(refFields.get(i).getType())){
								
									if(r.isRetFiel())
									
										pointeeField = r;
									
										if(pointeeField != null){
											
											AST_Parser.addPointer(pointerLocalVar, refFields.get(i),null, pointeeField, pointeeLocalVar,null, GlobalVariables.ALIAS, pointeeField.getExpType(),paraBind,methodBind,m);
										
											break;
										}
								}
	       				 	}
									
						}
										
					}
				}
  				}
 			}
 			}
	  
public static E_MRefField searchRefParameterField(IVariableBinding paramBind){

	E_MRefField field = null;
	
	String paramType = "";
	String paramCType = "";
	
	LinkedList<E_MRefParameter> params = new LinkedList<>();
		
	LinkedList<E_Method> _methods = Data_Controller.fetchAllMethods();

	if(_methods!=null && _methods.isEmpty() == false){
		
		for (E_Method m : _methods) {
		
			if(m.getName().equals(paramBind.getDeclaringMethod().getName())){

				params = m.getRefparams();
		    
		    	if(params != null && params.isEmpty() == false){
			 
		    		for(E_MRefParameter p: params){
				
		    			if(p.isField() == true){
				 
		    				if(paramBind.getType().isParameterizedType()){
		    					paramType = paramBind.getType().getTypeDeclaration().getQualifiedName().toString();
		    					//paramCType = paramBind.getType().getTypeDeclaration().getComponentType().getQualifiedName().toString();
		    				}
		    				else
		    				{
		    					paramType = paramBind.getType().getQualifiedName().toString();
		    					//paramCType = paramBind.getType().getComponentType().getQualifiedName().toString();
		    					
		    				}
		    				
		    				String temp = "";
		    				if(paramBind.getType().getComponentType()!=null){
		    					temp = paramBind.getType().getComponentType().getQualifiedName().toString();
		    				}
		    				if(p.getName().equals(paramBind.getName().toString()) && 
		    						(p.getType().equals(paramType) || 
		    								p.getType().equals(temp) )&& 
		    						p.getDeclMethod().equals(paramBind.getDeclaringMethod().getName().toString()) ){
		
		    						field = Data_Controller.searchMRefField(m, p.getfName(), p.getPfType(), p.getPfClass());
					
							break;
					}
				 }
		    	}
		    	}
			 }
	   }
	}
	return field;		
}
public static E_MRefParameter searchRefParameter(IVariableBinding paramBind){

	LinkedList<E_MRefParameter> params = new LinkedList<>();

	E_MRefParameter parameter = null;
		
	LinkedList<E_Method> _methods = Data_Controller.fetchAllMethods();

	for (E_Method m : _methods) {
		
	   if(m.getName().equals(paramBind.getDeclaringMethod().getName())){

		    params = m.getRefparams();
	
			 for(E_MRefParameter p: params){
				
				if(p.isField() == true){
					if(p.getName().equals(paramBind.getName().toString()) && p.getDeclMethod().equals(paramBind.getDeclaringMethod().getName().toString())){
						if(p.getType().equals(paramBind.getType().getQualifiedName().toString()) 
								|| paramBind.getType().getComponentType().getQualifiedName().toString().equals(p.getType())){
							parameter = p;
							break;
						}
						
					}
				}
			 }
	   }
	}
	return parameter;		
}
public static void addSubMethodsFields(LinkedList<E_Class> _class) {
	
//public static void addSubMethodsFields(E_Class c) {
			
		for(E_Class c: _class){
			
			LinkedList<E_Method> _methods = c.getMethods();
			
			for (E_Method m : _methods) {
			 		
				LinkedList<E_MInvokedMethod> tempList = new LinkedList<E_MInvokedMethod>();
				
				tempList = Data_Controller.fetchSubMethods(m, tempList);
			
				if(tempList != null){
				
					for (E_MInvokedMethod t: tempList){
					
						LinkedList<E_MRefField> refs = Data_Controller.fetchSubMethodFields(t);
					
					if(refs != null){
						
						for(E_MRefField rf : refs){
							
							//addClassField(m, rf.getName(), rf.getType(), rf.getDeclaringClass(), rf.getMOperation(), rf.getExpType(), rf.isRetFiel());
								
						}
					}
				}
			  }
			
			}
		}
	}
public static void addSubMethodFields(E_Method sourceMethod) {
	    
		LinkedList<E_MInvokedMethod> subMethodCalls = new LinkedList<E_MInvokedMethod>();
		
		subMethodCalls = Data_Controller.fetchSubMethodCalls(sourceMethod, subMethodCalls);
		
		if(subMethodCalls != null && subMethodCalls.isEmpty() == false){
		
		 for (E_MInvokedMethod mCall: subMethodCalls){
			
				LinkedList<E_MRefField> refs = Data_Controller.fetchSubMethodCallFields(mCall);
			
				if(refs != null && refs.isEmpty() == false){
					
					for(E_MRefField rf : refs){
						
						if(rf.isParam()){// changes made for plural translation
							continue;
						}else
						if(rf.getName().equals("result") &&( Parser_Utilities.checkPrimitiveType(sourceMethod.getReturnType().toString()) ||Parser_Utilities.checkWrapperType(sourceMethod.getReturnType().toString())|| (sourceMethod.getReturnType().equals("") 
								|| sourceMethod.getReturnType().equals("void")))){
							continue;
						}
						else {
							 addClassField(sourceMethod, rf.getName(), rf.getType(), rf.getDeclaringClass(), rf.getMOperation(), rf.getExpType(), rf.isRetFiel(), rf.getQualifyingObject(), rf.isField(), rf.isParam());
						}
						}
					}
			//}
		}
	}
}
		
/*public static void addParam(E_Method _method, E_InvokedMethod in) {
	
	LinkedList<E_Argument> argList = null;
	
	if (_method.getName().equalsIgnoreCase(GlobalVariables.MAIN)) {

	}
	else if (in.getName()
					.equalsIgnoreCase(_method.getName())
					&& in.getDeclaringClass().equalsIgnoreCase(
							_method.getDeclaringClass())
						
							&& in.isConstr() == _method.isConstr()) {
					
				argList = in.getArguments();
				
				LinkedList<E_MRefParameter> params = _method.getRefparams();
				
				if(argList!=null){
					if (params != null) {
						for (E_Argument arg : argList) {
								for (E_MRefParameter p : params) {			
									//if(p.getDeclMethod().equalsIgnoreCase(arg.getDeclMethod())){
										if(arg.isField()){
											if(matchArgwithParam(arg,  p)){
												p.setField(true);
												p.setfName(arg.getName());
												p.setPfClass(arg.getDeclaClass());
													addParamField(_method,
											 				arg.getName(), p.getType(),
											 				arg.getDeclaClass(),
											 				p.getMOperation(),
											 				p.getExpType(), false);
												}
										  }
									 }
									}
								}
							}
						}
	}*/
 /*public static void addMParamAsFields(LinkedList<E_Class> _class) {

  LinkedList<E_InvokedMethod> _invm = Data_Controller.fetchAllInvokedMethods();
	
 for(E_Class c: _class){

	LinkedList<E_Method> _methods = c.getMethods();
	
	 if(_methods != null){			
	 
	   if (_invm != null) {
		 
		 for (E_Method _method : _methods) {
			 
			    LinkedList<E_Argument> argList = new LinkedList<E_Argument>();
		
				for (E_InvokedMethod in : _invm) {
				
			    // match method invocation with its declaration
					
			    if(in.getName().equalsIgnoreCase(_method.getName()) && in.getDeclClassQName().equalsIgnoreCase(_method.getDeclClassQName())) {
										
			    // fetch actual parameters (arguments) to find if the formal parameter (parameters) are fields or not
						
						// fetch argument list
						argList = in.getArguments();
						
						// fetch parameter list
						LinkedList<E_MRefParameter> params = _method.getRefparams();
						
						if(argList!=null){
								
							if (params != null) {
											
									for (E_MRefParameter p : params) {	
										
										for (E_Argument arg : argList) {
											
											if(arg.isField() == true){
											    
												if(matchArgwithParam(arg, p)){
													   p.setField(true);
													   p.setfName(arg.getName());
													   p.setPfClass(arg.getDeclaClass());
													 	addParamField(_method,
														    arg.getName(), arg.getType(),
														    arg.getDeclaClass(),
															p.getMOperation(),p.getExpType(), false);
													 }
											}	
											else{
											      //AST_Parser.setArgAsField(in);
   
											}
										}
									}
								}
							}
						}
					}
				}
		    }
		  }
		}
	}*/

public static boolean matchArgwithParam(E_Argument arg, E_MRefParameter p){
			
			boolean flag = false;
			
			if (arg.getType().equalsIgnoreCase(
					p.getType())
					&& arg.getPosition() == p.getPosition()) {
					
					flag = true; 	
			}
			
		return flag;
	}
	/*	private static void addPArgsAsField(LinkedList<E_Method> _methods, E_InvokedMethod in){
			
			for (E_Method _method : _methods) {
				 addParam(_method, in);
			}
		
		}*/
	public static void addMFieldsContext(LinkedList<E_Class> _class){

 LinkedList<E_Method> _allmethods = Data_Controller.fetchAllMethods();
			
  for(E_Class c: _class){
			
	  LinkedList<E_Method> _cmethods = c.getMethods();
					
		for (int i = 0; i < _cmethods.size(); i++) {
				
			  if (_cmethods.listIterator(i).hasNext()) {
					
				if (_cmethods.listIterator(i).next().getName().equals(_cmethods.listIterator(i).next().getDeclaringClass())) {

					LinkedList<E_MRefField> rv1 = _cmethods.listIterator(i).next()
							.getRefVariable();

						for (E_MRefField r1 : rv1) {
							 r1.setCOperation(null);
						}
				} 
				else if (_cmethods.listIterator(i).next().getName().equals(GlobalVariables.MAIN) || _cmethods.listIterator(i).next().getName().equals(GlobalVariables.JGFrun)) {

					LinkedList<E_MRefField> rv1 = _cmethods.listIterator(i).next().getRefVariable();
					
					if(rv1.isEmpty() == false && rv1 !=null){
						
						for (E_MRefField r1 : rv1) {
	
							r1.setCOperation(null);
						}
					}
	
				}else{
					
					     LinkedList<E_MRefField> rv1 = _cmethods.listIterator(i).next().getRefVariable();
					
				       if(rv1.isEmpty() == false && rv1 !=null){
					 
					      for (int j = 0; j < _allmethods.size(); j++) {

						     if (_allmethods.listIterator(j).hasNext()) {
							
							  /*if(_allmethods.listIterator(j).next().getName().equals("main")){
							 	continue;
							 }
							 else*/
							  ////////
								if(_cmethods.listIterator(i).next().getName().equals(_allmethods.listIterator(j).next().getName()) 
										&& _cmethods.listIterator(i).next().getDeclClassQName().equals(_allmethods.listIterator(j).next().getDeclClassQName())){
									continue;
								}
								else if(_allmethods.listIterator(j).next().isConstr()){ 
										//&& _cmethods.listIterator(i).next().getDeclClassQName().equals(_allmethods.listIterator(j).next().getDeclClassQName())){
									continue;//added on 23MAy 2019
								}
								else {
									
									LinkedList<E_MRefField> rv2 = _allmethods.listIterator(j).next().getRefVariable();
									
									if(rv2.isEmpty() == false && rv2 !=null){
									   for (E_MRefField r1 : rv1) {
										  for (E_MRefField r2 : rv2) {
											if (r1.getName().equals(
													r2.getName())
													&& r1.getType().equals(
															r2.getType())
													&& r1.getDeclaringClass()
															.equals(
																	r2.getDeclaringClass())){
												// check for already present COperation
												// in the reference variable list
			
													if (AST_Parser.ifCOperationExist(r1, r2) == false) {
													// adding context operation with the
													// referenced variable
														if (r1.getExpType() == 3
																|| r1.getExpType() == 14
																|| r1.getExpType() == 33){
																//r1.getExpType() == 32) {
															r1.setCOperation(null);
														}else {
															r1.setCOperation(r2
																	.getMOperation());
														}
													}
											   }
										   }
									   }
									}
								 } 
							  }
							}
					       }
					      }
						 }
						}
					}
	}
	public static E_MRefField addParamField(E_Method _method, String fName,
			String fType, String declarClass, String op, int eType, boolean retExp,E_Object obj,boolean isF, boolean isP) {
		
		E_MRefField field = null;
		
		if(_method != null){
			addClassField(_method, fName,
					fType, declarClass, op, eType, retExp,obj,isF,isP);
		}
		
		field = Data_Controller.searchMRefField(_method, fName,
				fType, declarClass);
	
		return field;

	}
	
	public static void addClassField(E_Method _method, String fName,
			String fType, String declarClass, String op, int eType, boolean retExp,E_Object obj,boolean isF,boolean isP){
		
		LinkedList<E_MRefField> refList = _method.getRefVariable();
		
		/*E_Object qualObj = null;
		
		if(_method!=null){
			
			if(_method.getQualifyingObject()!=null){
				
				qualObj = _method.getQualifyingObject();
				//qualObj = new E_Object();
		
				if(_method.getQualifyingObject().getObjBind()!=null){
					
					//qualObj.setObjBind(_method.getQualifyingObject().getObjBind());
				}
				
			}
		}*/
		if (refList != null) {
			// Checking if the same referenced variable already present in the referenced variable list
			if(fName!=null && fType!=null && declarClass!=null){
			  
				if (ifRefExists(refList, fName, fType, declarClass, op, eType,retExp,obj) == false) {

				// adding referenced variable in the referenced variable
				// list
				/*if(_method.getName().equalsIgnoreCase("main") && op.equalsIgnoreCase("r")){
				   op = "rw";
				}*/

				E_MRefField _ref = new E_MRefField(fName, fType,
						declarClass, op, _method, eType, retExp,obj,isF,isP);

				_method.addRefVariable(_ref);
			 }
			}
		  }
	 }
	@SuppressWarnings("null")
	
	public static E_Object getQualifyingObject(MethodDeclaration mDecl){
		
		E_Object obj = new E_Object();
	
		if(mDecl!=null){
		
		if(mDecl.getName().toString().equals("main") == false){
	
			   E_Method _method = Data_Controller.searchMethod(mDecl);
			
				if(_method != null){
				
					if(_method.getQualifyingObject()!=null){
					
						obj = _method.getQualifyingObject();
						
						return obj;
			
					}
				}
				else{
			    
				//if(mDecl.getName().equals("main")== false){
				
				    List<MethodInvocation> thi_Minv = AST_Parser.getThisMethodInvokation(mDecl);
				   
				     if(thi_Minv!=null && thi_Minv.isEmpty() == false){
					   
					     for(MethodInvocation thi_inv:thi_Minv){
						   
						     IVariableBinding qualBind = null;
					    	
						     	 if(thi_inv.getExpression()!= null){
						    		
						    		 qualBind = AST_Parser.getObjQualifBind(thi_inv.getExpression());
						    	
						    		  if(qualBind!=null ){
						    			  obj.setObjBind(qualBind);
						    			  return obj;
									   }
						    		  else{
						    				 if(thi_inv.getExpression().resolveTypeBinding().isFromSource()){
						    					 obj.setName(thi_inv.getExpression().resolveTypeBinding().getQualifiedName().toString());
						    				 }
						    		  }
						    			 
						    	 }
						    	else{
						    		 
						    		MethodDeclaration pDecl = fetchParentMethodDecl(thi_inv);
						    		
						    		List<MethodInvocation> methodCalls = new LinkedList<MethodInvocation>();;
						    		
						    		List<MethodInvocation> subInvs = getSubMethodInvocations(pDecl, methodCalls,_method);
						    		
						    		for(MethodInvocation inv:subInvs){
						    			if(inv.getName().toString().equals(thi_inv.getName().toString())){
						    				obj = new E_Object();
						    				return obj;
						    				//break;
						    			}
						    			else{
						    				if(pDecl.getName().toString().equals(thi_inv.getName().toString())){
								    			continue;
								    		 }
								    		 else if(pDecl.getName().toString().equals("main")){
								    			 //E_Method mainM = Data_Controller.searchMethod(pDecl);
								    				
								 				//if(mainM != null){
								 					//obj.setName(mainM.getDeclClassQName()); 
								 					obj.setName(mDecl.resolveBinding().getDeclaringClass().getQualifiedName());
								 				//}
								    		 }
								    		 else{
								    			 return getQualifyingObject(pDecl);
								    		 }
						    			}
						    		}
						    		
									
						    	}
					     }
					
				     }
				    /* else{
					 List<ClassInstanceCreation> thisConstInv = getConstructorInvokation(mDecl);
					 
					 if(thisConstInv!=null && thi_Minv.isEmpty() == false){
						 for(ClassInstanceCreation thi_cinv:thisConstInv){
							 //MethodDeclaration pDecl = fetchParentMethodDecl(thi_cinv);
							MethodDeclaration cDecl= getMethodDeclaration((IMethodBinding)thi_cinv.resolveConstructorBinding())
							 obj = getQualifyingObject(cDecl);
				   			  
						}
					}
				 }*/
			}
		}
		else{
			obj = new E_Object();
		}
			
	}
	 return obj;
	}
	 
	public static void addInvokedMethodData(MethodDeclaration childMethod, E_Method pMethod, E_Object obj){
		
		if(childMethod!= null && pMethod!=null){
			
			E_Method child = Data_Controller.searchMethod(childMethod);
			 
			if(child != null){
				   if(pMethod.equals(child)){
						//System.out.println("This is a recursive method call"+child.getName());// don't parse resursive method	 
					}
				   else if (!pMethod.equals(child)){
					   LinkedList<E_MInvokedMethod> submethod_list  = child.getSubMethods();
					   if(submethod_list.isEmpty() == false){
						   for(E_MInvokedMethod m: submethod_list){
							   if(m.getName().equals(pMethod.getName().toString())&&m.getDeclClassQName().equals(pMethod.getDeclClassQName())){
								  System.out.println("Parent = "+pMethod.getName().toString()+" child who was a parent before ="+child.getName().toString());
							   }
						    }
					   }
					   else{
						   if( childMethod.getBody()!=null ){
							   AST_Parser.addMethodData(childMethod, obj);}
					       }
					   
				   }
				   else{
					   if( childMethod.getBody()!=null ){
						 AST_Parser.addMethodData(childMethod,obj);
					   }
					}
			 }
	         else{
	    	  // System.out.println("method name = "+mDec.getName().toString()+"object = "+obj.getName());
	        	 if( childMethod.getBody()!=null ){
					 AST_Parser.addMethodData(childMethod,obj);
			}
	         }
		}
	}
	/*public static E_Object getMQualifyingObject(E_Method _method){
		
		E_Object obj = new E_Object();
		
	//	if(_method.getName().equals("main") == false){
			
			E_Method m = Data_Controller.searchMethod(_method);
		
			if(m!=null){
			
				if(m.getQualifyingObject()!=null){
			
					obj =  m.getQualifyingObject();
				}	
		}
		return obj;
		
	}*/
		public static void addClassMethod(E_Class _class, E_Method _method){
		
			LinkedList<E_Method> methodList = new LinkedList<E_Method>();
			
		  if (_class != null && _method!=null) {
			
			   methodList = _class.getMethods();

			  if (methodList != null) {
				// Checking if the same method already exists in the class 
				if (ifMethodExists(methodList,_method) == false) {

					_class.addMethod(_method);
				}
			  }
			}
	}
		public static void addPackageClass(E_Package pkg, E_Class _class){
			
			LinkedList<E_Class> classList = new LinkedList<E_Class>();
			
			if (pkg != null && _class!= null) {

			  classList = pkg.getClasses();

				if (classList != null) {
					// Checking if the same method already exists in the class 
					if (ifClassExists(classList,_class) == false) {
						pkg.addClasses(_class);
					}
				  }
				}
		}
	public static void addMLocalVar(E_Method _method,E_MLocalVariable localVar){
			
	if (_method.getName() != null) {

		LinkedList<E_MLocalVariable> refList = _method.getLocalVar();

		if (refList != null) {

			// Checking if the same referenced variable already present in the referenced variable list
			if (ifMLocalVarExists(refList, localVar.getName(), localVar.getType(), localVar.getDeclMethod().getName()) == false) {

				E_MLocalVariable _var = new E_MLocalVariable(localVar.getName(), localVar.getType(), _method,
						GlobalVariables.ALIAS,  false);

				_method.addLocalVar(_var);
			}
		  }
		
		}
	
	 }

/*public static void addFieldAlias(E_MRefField _field, String fName,
			String fType, E_Method declarType, String op, int eType, String initName, String initType,String initDeclType, boolean isMethod, IVariableBinding pb, boolean isLocal){
		
		LinkedList<E_MRefAlias> aliasList = new LinkedList<E_MRefAlias>();
		
		E_MRefAlias alias = null;
	
		if (_field != null) {

			aliasList = _field.getAliases();

			if (aliasList != null) {
				
				// Checking if the same referenced variable already present in the referenced variable list
				if (ifAliasExists(aliasList, fName, fType, declarType.getName(), op, eType) == false) {

					//alias = new E_MRefAlias(fName, fType,declarType.getName(), op, eType,initName, initType, initDeclType, isMethod, pb, isLocal);
					
					_field.addAlias(alias);
				}
			 }
	 }
}
*/
public static void addFieldAsFieldAlias(E_MRefField pointeeField,E_MRefField pointerField,String aliasOp,int eType, IVariableBinding rightBind, IMethodBinding methodBind, E_Method _method){
		
		E_MRefAlias alias = null;
		
		E_MLocalVariable pointerLocalVar = null; 
		
		LinkedList<E_MRefAlias> aliasList = new LinkedList<E_MRefAlias>();
		
		if (pointeeField != null) {
			
			aliasList = pointeeField.getAliases();

			if (aliasList != null) {
						
				alias = addFieldAsAlias(aliasList,pointerField,pointerLocalVar,methodBind,rightBind,_method);
				
				if(alias!=null){
					
					pointeeField.addAlias(alias);
				
				}
			}
				
		}
	}
public static void addParamAsParamAlias(E_MRefParameter pointeeParam,E_MRefParameter pointerParam,String aliasOp,int eType, IVariableBinding rightBind, IMethodBinding methodBind, E_Method _method){
	
	E_MRefAlias alias = null;
	
	E_MLocalVariable pointerLocalVar = null; 
	
	E_MRefField pointerField = null; 
	
	LinkedList<E_MRefAlias> aliasList = new LinkedList<E_MRefAlias>();
	
	if (pointeeParam != null) {
		
		aliasList = pointeeParam.getAliases();

		if (aliasList != null) {
					
			alias = addParamAsAlias(aliasList,pointerField,pointerParam,pointerLocalVar,methodBind,rightBind,_method);
			
			pointeeParam.addAlias(alias);
		}
	}
}
public static void addParamAsFieldAlias(E_MRefField pointeeField,E_MRefParameter pointerParam,String aliasOp,int eType, IVariableBinding rightBind, IMethodBinding methodBind, E_Method _method){
	
	E_MRefAlias alias = null;
	
	E_MLocalVariable pointerLocalVar = null; 
	
	E_MRefField pointerField = null; 
	
	LinkedList<E_MRefAlias> aliasList = new LinkedList<E_MRefAlias>();
	
	if (pointeeField != null) {
		
		aliasList = pointeeField.getAliases();

		if (aliasList != null) {
					
			alias = addParamAsAlias(aliasList,pointerField,pointerParam,pointerLocalVar,methodBind,rightBind,_method);
			
			pointeeField.addAlias(alias);
		}
	}
}
public static void addLocalAsFieldAlias(E_MRefField pointeeField,E_MLocalVariable pointerLocalVar,String aliasOp,int eType, IVariableBinding rightBind, IMethodBinding methodBind, E_Method _method){
	
	E_MRefAlias alias = null;
	
	E_MRefField pointerField = null;
	
	LinkedList<E_MRefAlias> aliasList = new LinkedList<E_MRefAlias>();
	
	if (pointeeField != null) {
		
		aliasList = pointeeField.getAliases();

		if (aliasList != null) {
					
			alias = addLocalVariableAsAlias(aliasList, pointerLocalVar,pointerField,methodBind,rightBind,_method);
			
			pointeeField.addAlias(alias);
		}
	}
}
@SuppressWarnings("null")
public static void addLocalAsParamAlias(E_MRefParameter pointeeParam,E_MLocalVariable pointerLocalVar,String aliasOp,int eType, IVariableBinding rightBind, IMethodBinding methodBind, E_Method _method){
	
	E_MRefAlias alias = null;
	
	E_MRefField pointerField = null;
	
	LinkedList<E_MRefAlias> aliasList = new LinkedList<E_MRefAlias>();
	
		if (pointeeParam != null) {
			/*if(pointeeParam.getType().equals(pointerLocalVar.getType())== false){
				E_MRefParameter  pp = addRefParameters(pointerLocalVar.getDeclMethod(), pointeeParam.getName(), pointerLocalVar.getType(), pointeeParam.getPfClass(),pointeeParam.getPosition(), pointeeParam.getMOperation(), pointeeParam.getExpType(), pointeeParam.getDeclMethod(), pointeeParam.isRetFiel(), pointeeParam.getQualifyingObject());
				if(pp != null){
					pointeeParam = pp;
				}
			}*/
		
			aliasList = pointeeParam.getAliases();

			if (aliasList != null) {
					
				alias = addLocalVariableAsAlias(aliasList, pointerLocalVar,pointerField,methodBind,rightBind,_method);
			}
			
			if(alias!=null){
			  //pointeeParam.setType(pointerLocalVar.getType());
					pointeeParam.addAlias(alias);
				}
	 }
}
public static E_MRefAlias addLocalVariableAsAlias(LinkedList<E_MRefAlias> aliasList,E_MLocalVariable pointerLocalVar,E_MRefField pointerField, IMethodBinding methodBind,IVariableBinding rightBind,E_Method _method){
	
	E_MRefAlias alias = null;
	
	E_MRefParameter pointerParam = null;
	
	AST_Parser.addMLocalVar(_method, pointerLocalVar);
	
	if(aliasList.isEmpty() == false){
	 
		if(ifLocalAliasExists(aliasList, pointerLocalVar.getName().toString(), pointerLocalVar.getType().toString(), pointerLocalVar.getDeclMethod().getName().toString()) == false){
	  
			alias = new E_MRefAlias(pointerField,pointerLocalVar,pointerParam,methodBind,rightBind);
	
			alias.addLocalVarAlias(pointerLocalVar);
	
	  }
	}
	else{
		 alias = new E_MRefAlias(pointerField,pointerLocalVar,pointerParam, methodBind,rightBind);
		 alias.addLocalVarAlias(pointerLocalVar);
			
	}
	return alias;

}

public static E_MRefAlias addFieldAsAlias(LinkedList<E_MRefAlias> aliasList,E_MRefField pointerField,E_MLocalVariable pointerLocalVar, IMethodBinding methodBind,IVariableBinding rightBind,E_Method _method){
	
	
	E_MRefAlias alias = null;
	
	E_MRefParameter pointerParam = null;
	
	if(aliasList.isEmpty() == false){
		if(ifFieldAsAliasExists(aliasList, pointerField.getName(), pointerField.getType(), pointerField.getDeclaringClass()) == false){
		    alias = new E_MRefAlias(pointerField,pointerLocalVar,pointerParam, methodBind,rightBind);}
	}
	else{
		alias = new E_MRefAlias(pointerField,pointerLocalVar,pointerParam, methodBind,rightBind);
	}
	if(alias != null){
			alias.addFieldAlias(pointerField);
	 }
	return alias;

}
public static E_MRefAlias addParamAsAlias(LinkedList<E_MRefAlias> aliasList,E_MRefField pointerField, E_MRefParameter pointerParam,E_MLocalVariable pointerLocalVar, IMethodBinding methodBind,IVariableBinding rightBind,E_Method _method){
	
	E_MRefAlias alias = null;
	
	if(aliasList.isEmpty() == false){
	
		if(ifParamAsAliasExists(aliasList, pointerParam.getName(), pointerParam.getType(), pointerParam.getDeclaringClass()) == false){
		  
			alias = new E_MRefAlias(pointerField,pointerLocalVar,pointerParam,methodBind,rightBind);
		}
	}
	else{
		 alias = new E_MRefAlias(pointerField,pointerLocalVar,pointerParam, methodBind,rightBind);
	}
	if(alias!=null){
			alias.addParameterAlias(pointerParam);
	}
	return alias;

}

 public static void addFieldAsLocalAlias(E_MLocalVariable pointeeLocalVar,E_MRefField pointerField,String aliasOp,int eType, IVariableBinding rightBind, IMethodBinding methodBind, E_Method _method){
	
	E_MRefAlias alias = null;
	
	E_MLocalVariable pointerLocalVar = null;
	
	LinkedList<E_MRefAlias> aliasList= new LinkedList<E_MRefAlias>();
	
	if (pointeeLocalVar != null) {
		
		   aliasList = pointeeLocalVar.getAliases();

		if (aliasList != null) {
			
			alias = addFieldAsAlias(aliasList, pointerField,pointerLocalVar,methodBind,rightBind,_method);	
		
			if(alias!=null){
			
				pointeeLocalVar.addAlias(alias);
			 }
			}
		}

	}
 public static void addFieldAsParamAlias(E_MRefParameter pointeeParam,E_MRefField pointerField,String aliasOp,int eType, IVariableBinding rightBind, IMethodBinding methodBind, E_Method _method){
		
	E_MRefAlias alias = null;
	
	E_MLocalVariable pointerLocalVar = null;
	
	LinkedList<E_MRefAlias> aliasList= new LinkedList<E_MRefAlias>();
	
	if (pointeeParam != null) {
		
		   aliasList = pointeeParam.getAliases();

			alias = addFieldAsAlias(aliasList, pointerField,pointerLocalVar,methodBind,rightBind,_method);	
		
			if(alias!=null){
				pointeeParam.addAlias(alias);
			}
		
	}

}
 public static void addParamAsLocalAlias(E_MLocalVariable pointeeLocalVar,E_MRefParameter pointerParam,String aliasOp,int eType, IVariableBinding rightBind, IMethodBinding methodBind, E_Method _method){
		
	E_MRefAlias alias = null;
	
	E_MLocalVariable pointerLocalVar = null;
	
	E_MRefField pointerField = null;
	
	LinkedList<E_MRefAlias> aliasList= new LinkedList<E_MRefAlias>();
	
	if (pointeeLocalVar != null) {
		
		   aliasList = pointeeLocalVar.getAliases();

		if (aliasList != null) {
			
			  alias = addParamAsAlias(aliasList,pointerField,pointerParam,pointerLocalVar,methodBind,rightBind,_method);	
			  if(alias!=null){
				  pointeeLocalVar.addAlias(alias);
			  }
	
			}
		}

	}
	
public static void addLocalAsLocalAlias(E_MLocalVariable pointeeLocalVar,E_MLocalVariable pointerLocalVar,String aliasOp,int eType, IVariableBinding rightBind, IMethodBinding methodBind, E_Method _method){
	
	E_MRefAlias alias = null;
	
	E_MRefField pointerField = null;
	
	LinkedList<E_MRefAlias> aliasList= new LinkedList<E_MRefAlias>();
	
	if (pointeeLocalVar != null) {
		
		   aliasList = pointeeLocalVar.getAliases();

		if (aliasList != null) {
			
			alias = addLocalVariableAsAlias(aliasList, pointerLocalVar,pointerField,methodBind,rightBind,_method);	
			
			if(alias != null){
				
				pointeeLocalVar.addAlias(alias);
			}
	
		}
	  }
	}
	

/*public static void addLocalAlias(E_MLocalVariable pointeeLocalVariable,String lfName,String lfType, String ldeclarType, String aliasOp, int eType, String initName, String initType, boolean isMethod, IVariableBinding rightBind, String initDeclType, boolean isLocal){
	
	//E_MLocalVariable var = Data_Controller.searchMLocalVariable(_method,rightBind.getName().toString(),rightBind.getType().getQualifiedName().toString(),initDeclType);
	
	LinkedList<E_MRefAlias> aliasList = new LinkedList<E_MRefAlias>();
	
	E_MRefAlias alias = null;

	if (pointeeLocalVariable != null) {// pointee is alocal variable

		aliasList = pointeeLocalVariable.getAliases();
	
		if (aliasList != null) {
			// Checking if the same referenced variable already present in the referenced variable list
			if (ifAliasExists(aliasList,rightBind.getName().toString(),rightBind.getType().getQualifiedName().toString(),initDeclType, aliasOp, eType) == false) {
	
				alias = new E_MRefAlias(rightBind.getName().toString(),rightBind.getType().getQualifiedName().toString(),initDeclType, aliasOp, eType,initName, initType,initDeclType, isMethod, rightBind,  isLocal );
				
				//E_MLocalVariable localAlias= new E_MLocalVariable(rightBind.getName().toString(),rightBind.getType().getQualifiedName().toString(),initDeclType, aliasOp, eType, false);
				
			    //alias.addLocalVarAlias(localAlias);
				
			    pointeeLocalVariable.addAlias(alias);
			}
		}
	}
 }*/
/*public static void addLocalAlias(E_MLocalVariable pointeeLocalVariable,String lfName,String lfType, E_Method ldeclarType, String aliasOp, int eType, String initName, String initType, boolean isMethod, IVariableBinding rightBind, String initDeclType, boolean isLocal){
	
	LinkedList<E_MRefAlias> aliasList = new LinkedList<E_MRefAlias>();
	
	E_MRefAlias alias = null;

	if (pointeeLocalVariable != null) {// right side is a pointee that is a local variable

		aliasList = pointeeLocalVariable.getAliases();
	
		if (aliasList != null) {
			// Checking if the same referenced variable already present in the referenced variable list
			if (ifAliasExists(aliasList,rightBind.getName().toString(),rightBind.getType().getQualifiedName().toString(),initDeclType, aliasOp, eType) == false) {
	
				E_MLocalVariable pointerLocalVariable= new E_MLocalVariable(lfName,lfType, ldeclarType, aliasOp, eType, false);
				
				//alias = new E_MRefAlias(pointeeLocalVariable, pointerLocalVariable.getName(), pointerLocalVariable.getType(), pointerLocalVariable.getDeclMethod(), isMethod, rightBind, isLocal);
				
			    //alias.addLocalVarAlias(pointerLocalVariable);
				
			    //pointeeLocalVariable.addAlias(alias);
			}
		}
	}
 }
 */
	public static void addMRefField(E_Method _method, String fName, String fType, String declarClass, String op, int eType, boolean retExp,E_Object obj,boolean fFlag, boolean pFlag) {
		
		if(_method!= null){	
			
			addClassField(_method, fName,fType, declarClass, op, eType, retExp,obj,fFlag,pFlag);
		}
	}
	
	public static void updateFOperation(E_MRefField list,
			String fName, String fType, String declarClass, String op, int eType) {

		/*if (list != null) {

			for (int i = 0; i < list.size(); i++) {

				    if (list.get(i).getName().equalsIgnoreCase(fName)
						&& list.get(i).getType().equalsIgnoreCase(fType)
						&& list.get(i).getDeclaringClass()
								.equalsIgnoreCase(declarClass)) {*/

					if (list.getMOperation().equals(GlobalVariables.READ)
							&& op.equals(GlobalVariables.WRITE))

						list.setMOperation(op);

					else if (list.getMOperation().equals(GlobalVariables.WRITE)
							&& op.equals(GlobalVariables.READ)){
						     list.setMOperation(list.getMOperation());
						     //System.out.println("Do-nothing");
					}
				//}
			//}
		//}
	}

	public static boolean ifRefExists(LinkedList<E_MRefField> list,
			String fName, String fType, String declarClass, String op, int eType, boolean retExp,E_Object qualObj) {

		boolean flag = false;

		if(list != null && list.isEmpty() == false){
		
			for(Iterator<E_MRefField> iter = list.iterator(); iter.hasNext();) {
			
				E_MRefField data = iter.next();
				
				if(data!= null){
					
				 if (data.getName().equals(fName)
						&& data.getType().equals(fType)
						&& data.getDeclaringClass().equals(declarClass)){
						//&& data.getQualifyingObject().equals(qualObj)){
					if (data.getMOperation().equals(op)
							&& data.getExpType() == eType)
						flag = true;
					else if (!(data.getMOperation().equals(op))
							&& data.getExpType() != eType) {
						updateFOperation(data, fName, fType, declarClass, op,
								eType);
						updateFExpType(data, fName, fType, declarClass, op,
								eType);
						flag = true;
					} else if (data.getMOperation().equals(op)
							&& data.getExpType() != eType) {
						updateFExpType(data, fName, fType, declarClass, op,
								eType);
						flag = true;
					} else if (!(data.getMOperation().equals(op))
							&& data.getExpType() == eType) {
						updateFOperation(data, fName, fType, declarClass, op,
								eType);
						flag = true;
					}
				 }

			}

		}
		}

		return flag;
	}
	public static boolean ifMethodExists(LinkedList<E_Method> list,
			E_Method _method) {

		boolean flag = false;

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getIdentifier().equals(_method.getIdentifier())
					&& list.get(i).getReturnType().equals(_method.getReturnType())
					&& list.get(i).getDeclClassQName().equals(_method.getDeclClassQName())){
					flag = true;
					break;
			}
			else
				flag = false;

			}

		return flag;
	}
	public static boolean ifClassExists(LinkedList<E_Class> list,
			E_Class _class) {

		boolean flag = false;

		 for(Iterator<E_Class> iter = list.iterator(); iter.hasNext();) {
		
			 E_Class data = iter.next();
			
			if (data.getName().toString().equals(_class.getName().toString())
				 && data.getClassQName().toString().equals(_class.getClassQName().toString())){
				
				flag = true;
				break;
			}
			else
				flag = false;

			}

		return flag;
	}
	public static boolean ifMethodInvocExists(E_Class _class, E_InvokedMethod inv) {

		boolean flag = false;
		
		LinkedList<E_InvokedMethod> list = _class.getInvokMethods();
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equalsIgnoreCase(inv.getName()) && list.get(i).isConstr() == inv.isConstr() 
					&& list.get(i).getDeclClassQName().equalsIgnoreCase(inv.getDeclClassQName())){
					
			//	LinkedList<E_Argument>  argList = list.get(i).getArguments();
				
				//if(ifArgumentExist(argList,inv.getArguments())== true){
					flag = true;
				//}
				//else
					//flag = false;
	
		  }
	 }
	return flag;
}
	public static boolean ifArgumentExist(LinkedList<E_Argument>  argList, LinkedList<E_Argument>  invArgs){
		
		boolean flag = false;
		
		if(argList.size() == invArgs.size()){
			
			for(E_Argument  arg1: argList){
				for(E_Argument  arg2: invArgs){
					if(arg1.getName().equals(arg2.getName()) && arg1.getType().equals(arg2.getType())){
					  flag = true;
					}
					else
						flag = false;

					}
				}
			}
		
		return flag; 
	}
	public static boolean ifMLocalVarExists(LinkedList<E_MLocalVariable> list,
			String fName, String fType, String declarM) {

		boolean flag = false;

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getName().equalsIgnoreCase(fName)
					&& list.get(i).getType().equalsIgnoreCase(fType)){
					//&& list.get(i).getDeclMethod().getName()
						//	.equalsIgnoreCase(declarM)) {
				flag = true;
			}
			else flag = false;

		}

		return flag;
	}
	public static boolean ifLocalAliasExists(LinkedList<E_MRefAlias> list,
			String fName, String fType, String declarM) {

		boolean flag = false;
		
		for(E_MRefAlias l: list){
			
			if(l!=null){
				if(l.getLocalVarAlias() != null){
				
				LinkedList<E_MLocalVariable> localAliases = l.getLocalVarAlias();
				
				for(E_MLocalVariable la:localAliases){
					if(la!=null){
					  if(la.getName().equals(fName)	&& la.getType().equals(fType)){
							//&& la.getDeclMethod().getName()
							//.equalsIgnoreCase(declarM)){
							
						flag = true;
						
						break;
						
					}
				  }
				}
			 }
			}
		}
		return flag;
	}
	public static boolean ifFieldAsAliasExists(LinkedList<E_MRefAlias> list,
			String fName, String fType, String declType) {

		boolean flag = false;
		
		Iterator<E_MRefAlias> it = list.iterator();
		
		LinkedList<E_MRefField> fAliases = new LinkedList<E_MRefField>();
		
		while (it.hasNext()) {
			
			E_MRefAlias l = it.next();
		
			if(l != null){
	
		     //for(E_MRefAlias l: list){
			
			   //for(Iterator<E_MRefAlias> iter = list.iterator(); iter.hasNext();) {
				
				 //E_MRefAlias l = iter.next();
		
				     if(l.getFieldAlias() != null && l.getFieldAlias().isEmpty() == false){
						
				    	 fAliases = l.getFieldAlias();
				
							for(E_MRefField ra: fAliases){
								
								if(ra.getName().equals(fName)&&ra.getType().equals(fType)
										&& ra.getDeclaringClass().equals(declType)){
										
									flag = true;
								}
								else
								{
									flag = false;
								}
						  }
				     }
			    }
		}
			
		return flag;
	}
	public static boolean ifParamAsAliasExists(LinkedList<E_MRefAlias> list,
			String fName, String fType, String declType) {

		boolean flag = false;
		
		for(E_MRefAlias l:list){
			if(l.getParamAlias() != null){
				LinkedList<E_MRefParameter> aliases = l.getParamAlias();
				for(E_MRefParameter p:aliases){
					if(p.getName().equalsIgnoreCase(fName)	&& p.getType().equalsIgnoreCase(fType)
							&& p.getDeclaringClass().equalsIgnoreCase(declType)){
							
						flag = true;
						
						
					}
					else
					{
						flag = false;
					}
					
				}
			}
		}
			
		return flag;
	}
	public static boolean ifCOperationExist(E_MRefField r1, E_MRefField r2) {

		boolean flag = false;

		if (r1.getCOperation() != null) {
			if(r2.getMOperation() != null){
				if (r1.getCOperation().equals(r2.getMOperation()))
					flag = true;
				else {
					updateFCOperation(r1, r2);
					flag = true;
				}
			}
		}
		else {// added on May 23, 2019s
			     flag = false;
		}
		return flag;
	}

	public static void updateFCOperation(E_MRefField r1, E_MRefField r2) {

		if (r1.getCOperation().equals(GlobalVariables.READ)
				&& r2.getMOperation().equals(GlobalVariables.WRITE))

			r1.setCOperation(r2.getMOperation());

		else if (r1.getCOperation().equals(GlobalVariables.WRITE)
				&& r2.getMOperation().equals(GlobalVariables.READ)) {
			// System.out.println("Do-nothing");
		}
	}

	//public static void updateFExpType(LinkedList<E_MRefField> list,String fName, String fType, String declarClass, String op, int eType) {
	public static void updateFExpType(E_MRefField list,String fName, String fType, String declarClass, String op, int eType) {

		/* System.out.println("exp type = "+ASTNode.CLASS_INSTANCE_CREATION+"14");
		 System.out.println("exp type = "+ASTNode.ARRAY_CREATION+"3");
		 System.out.println("exp type = "+ASTNode.ARRAY_INITIALIZER+"4");
		 System.out.println("exp type = "+ASTNode.NULL_LITERAL+"33");
	*/			
		if (eType != 33 && (list.getExpType() == 3
				|| list.getExpType() == 14
				|| list.getExpType() == 4)){
				//|| list.getExpType() == 33) {
			list.setExpType(list.getExpType());
		} 
		if (eType == 33){
			list.setExpType(eType);
		} 
		else if (list.getExpType() != 3
				&& list.getExpType() != 14
				&& list.getExpType() != 4
				&& list.getExpType() != 33) {// this was for library function calls
			list.setExpType(eType);
		}

	}
	
	/*public static void updateRetExp(LinkedList<E_MRefField> list,
			String fName, String fType, String declarClass, String op, int eType, boolean retExp) {

		if (list != null) {

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).isRetFiel() == false && retExp == true) {

				  list.get(i).setRetFiel(retExp);
				  
				} else

					list.get(i).setRetFiel(list.get(i).isRetFiel());
				}

			}*/
	public static void updateRetExp(E_MRefField list,
			String fName, String fType, String declarClass, String op, int eType, boolean retExp) {
		{
		if (list.isRetFiel() == false && retExp == true) {

			  list.setRetFiel(retExp);
			  
			} else

				list.setRetFiel(list.isRetFiel());
			}
		
	}

	public static void updateRefField(E_Method _method, String fName,
			String fType, String declarClass, String op, int location) {

		LinkedList<E_MRefField> refList = _method.getRefVariable();

		if (refList != null) {
			if (refList.get(location).getName().equalsIgnoreCase(fName)
					&& refList.get(location).getType().equalsIgnoreCase(fType)
					&& refList.get(location).getDeclaringClass()
							.equalsIgnoreCase(declarClass))
				refList.get(location).setMOperation(op);
		}
	}

	public static E_MRefParameter addRefParameters(E_Method _method, String fName, String fType,
			String decClass, int pos, String op, int eType, String decl_Method,boolean retExp,E_Object obj) {
		
		E_MRefParameter pRef = null;
	
		if(_method.getRefparams()!= null){
		  
			LinkedList<E_MRefParameter> refList = _method.getRefparams();
		
			// Checking if the same parameter already exists in the referenced variable list
			if (ifRefPExist(refList, fName, fType, decClass, op, pos, eType, decl_Method,obj) == false) {

				// adding referenced variable in the referenced variable list
				pRef = new E_MRefParameter(fName, fType,
						decClass, pos, op, eType, decl_Method,retExp,obj);
				
				_method.addRefparams(pRef);
			}
			else{
				LinkedList<E_MRefParameter> params = Data_Controller.fetchMRefParameters(_method);
				for(E_MRefParameter p:params){
					if(p.getName().equals(fName) && p.getDeclMethod().equals(decl_Method)){
						//if(p.getType().equals(fType)){
							pRef = p;
							break;
						}
				 	}
				 //}
					
			}
		}
		return pRef;
	}
	

	public static boolean ifRefPExist(LinkedList<E_MRefParameter> list,
			String fName, String fType, String declClass, String op, int pos,
			int eType, String decl_Method,E_Object obj) {
	  
		
		boolean flag = false;
		List<E_MRefField> field = new LinkedList<E_MRefField>();
		String declType = "";
		
	for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getDeclMethod() != null){
				declType = list.get(i).getDeclMethod();
			}
			else if(list.get(i).getDeclaringClass()!=null){
				declType = list.get(i).getDeclaringClass();
			}
			if (list.get(i).getName().equals(fName)
					&& list.get(i).getType().equals(fType)
					&& list.get(i).getPosition() == pos
					&& declType.equals(decl_Method)){
					//&& list.get(i).getQualifyingObject().equals(obj)) {
										
					if (list.get(i).getMOperation().equals(op) && list.get(i).getExpType() == eType){
							//&& list.get(i).getfName().equals(pfName) 
							//&& list.get(i).getPfClass().equals(declClass))
	
						 /*if(list.get(i).isField()){	
							field = Data_Controller.searchField(list.get(i).getfName(), list.get(i).getPfType(),list.get(i).getPfClass());
						}
						if(field!=null){
							if(field.getQualifyingObject()!=null){
							 if(!(field.getQualifyingObject().equals(obj))){
								field.setQualifyingObject(list.get(i).getQualifyingObject());
							 }
							}
						}*/
						flag = true;
					}
					else if (!(list.get(i).getMOperation().equals(op))
							&& list.get(i).getExpType() != eType) {
						
						   updatePOperation(list, fName, fType, declClass, op,
								eType, decl_Method);
						  
						   updatePExpType(list, fName, fType, declClass, op,
											eType, decl_Method);
						   /*if(list.get(i).isField()){	
								field = Data_Controller.searchField(list.get(i).getfName(), list.get(i).getPfType(),list.get(i).getPfClass());
							}
						   if(field!=null){
								addClassField(field.getMethod(), list.get(i).getfName(),list.get(i).getPfType(),list.get(i).getPfClass(), op, eType,false,list.get(i).getQualifyingObject());
							}*/
						   if(list.get(i).isField()){	
							   field = list.get(i).getFields();
							   //field.add(Data_Controller.searchField(list.get(i).getfName(), list.get(i).getPfType(),list.get(i).getPfClass()));
							}
						   if(field!=null && field.isEmpty() == false){
							for(E_MRefField f:field){
							   addClassField(f.getMethod(),f.getName(),f.getType(),f.getDeclaringClass(), op, eType,false,list.get(i).getQualifyingObject(),f.isField(),f.isParam());
							}
						   }
						flag = true;
					 } else if (list.get(i).getMOperation().equals(op)
							&& list.get(i).getExpType() != eType) {
						
						        updatePExpType(list, fName, fType, declClass, op,
								eType, decl_Method);
						       
						        if(list.get(i).isField()){	
									   field = list.get(i).getFields();
									}
								   if(field!=null && field.isEmpty() == false){
									for(E_MRefField f:field){
									   addClassField(f.getMethod(),f.getName(),f.getType(),f.getDeclaringClass(), op, eType,false,list.get(i).getQualifyingObject(),f.isField(),f.isParam());
									}
								   }
						        /*if(list.get(i).isField()){	
								 field = Data_Controller.searchField(list.get(i).getfName(), list.get(i).getPfType(),list.get(i).getPfClass());
								}
								if(field!=null){
								  addClassField(field.getMethod(), list.get(i).getfName(),list.get(i).getPfType(),list.get(i).getPfClass(), op, eType,false,list.get(i).getQualifyingObject());
							    }*/
								flag = true;
					 } else if (!(list.get(i).getMOperation().equals(op))
							&& list.get(i).getExpType() == eType) {
						 	
						 	updatePOperation(list, fName, fType, declClass, op,
								eType, decl_Method);
						
						 	if(list.get(i).isField()){	
								   field = list.get(i).getFields();
								}
							   if(field!=null && field.isEmpty() == false){
								for(E_MRefField f:field){
								   addClassField(f.getMethod(),f.getName(),f.getType(),f.getDeclaringClass(), op, list.get(i).getExpType(),false,list.get(i).getQualifyingObject(),f.isField(),f.isParam());
								}
							   }
						 	 /*if(list.get(i).isField()){	
									field = Data_Controller.searchField(list.get(i).getfName(), list.get(i).getPfType(),list.get(i).getPfClass());
								}
								if(field!=null){
									addClassField(field.getMethod(), list.get(i).getfName(),list.get(i).getPfType(),list.get(i).getPfClass(), op, list.get(i).getExpType(),false,list.get(i).getQualifyingObject());
								}*/
						flag = true;
					}
			}

			}

		return flag;

	}

	public static void updatePOperation(LinkedList<E_MRefParameter> list,
			String fName, String fType, String declarClass, String op, int eType, String decl_Method) {

		if (list != null) {

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).getName().equals(fName)
						&& list.get(i).getType().equals(fType)
						&& list.get(i).getDeclMethod()
								.equals(decl_Method)) {

					if (list.get(i).getMOperation().equals(GlobalVariables.READ)
							&& op.equals(GlobalVariables.WRITE))

						list.get(i).setMOperation(op);

					else if (list.get(i).getMOperation().equals(GlobalVariables.WRITE)
							&& op.equals(GlobalVariables.READ)){
						   list.get(i).setMOperation(list.get(i).getMOperation());
						 //System.out.println("Do-nothing");
					}
				}
			}
		}
	}
	public static void updatePQualObj(LinkedList<E_MRefParameter> list,
			String fName, String fType, String declarClass, String op, int eType, String decl_Method,E_Object obj) {

		if (list != null) {

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).getName().equalsIgnoreCase(fName)
						&& list.get(i).getType().equalsIgnoreCase(fType)
						&& list.get(i).getDeclMethod()
								.equalsIgnoreCase(decl_Method)) {

					if (list.get(i).getQualifyingObject().equals(obj)
							&& op.equalsIgnoreCase(GlobalVariables.WRITE))

						list.get(i).setQualifyingObject(obj);

					else if (list.get(i).getMOperation().equalsIgnoreCase(GlobalVariables.WRITE)
							&& op.equalsIgnoreCase(GlobalVariables.READ))

					{
						// list.get(i).setMOperation(list.get(i).getMOperation());
						// System.out.println("Do-nothing");
					}
				}
			}
		}
	}
	public static void updatePExpType(LinkedList<E_MRefParameter> list,
			String fName, String fType, String declarClass, String op, int eType, String decl_Method) {

		if (list != null) {

			for (int i = 0; i < list.size(); i++) {
			
				if (list.get(i).getName().equals(fName)
						&& list.get(i).getType().equals(fType)
						&& list.get(i).getDeclMethod()
								.equals(decl_Method)) {
					
						if (eType != 33 && list.get(i).getExpType() == 3
								|| list.get(i).getExpType() == 14 
								|| list.get(i).getExpType() == 14) {
		
								list.get(i).setExpType(list.get(i).getExpType());
						}
						else if (eType == 33){
							list.get(i).setExpType(eType);
						} 
						else if (list.get(i).getExpType() != 3
								&& list.get(i).getExpType() != 14
								&& list.get(i).getExpType() != 4
								&& list.get(i).getExpType() != 33) {
							
							   list.get(i).setExpType(eType);
						  
						}
				}
			}
		}
	}
	
	public static void extractContextInformation(){
		
	    LinkedList<E_Class> _class = Data_Controller.fetchAllClasses();
			
	    // we need to fetch all methods irrespective of class when there are multiple classes
	
		//addMParamAsFields(_class);// find and add referenced parameters at method level
		
	     //addSubMethodsFields(_class);// add sub methods fields as referenced fields at method level
			
		//updateLocalAsFieldAlias(_class);
		
		//updateFieldAsFieldAlias(_class);
		
		// find and add context for all the referenced variables in a method
		addMFieldsContext(_class);
	
	}
	public static List<ASTNode> getParents(ASTNode node) {

		ASTNode pNode;

		List<ASTNode> parents = new ArrayList<ASTNode>();

		if(node.getParent() != null){
			
			pNode = node.getParent();
				
			parents.add((ASTNode) pNode);
			
			do {
				if(pNode.getParent()!=null){
					pNode = pNode.getParent();
					
					parents.add((ASTNode) pNode);
				}
			} while (pNode != node.getRoot());
		}
		return parents;

	}

	public static List<ASTNode> getChildren(ASTNode node) {

		List<ASTNode> children = new ArrayList<ASTNode>();
		
		List list = node.structuralPropertiesForType();
		
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Object child = node.getStructuralProperty((StructuralPropertyDescriptor) list
								.get(i));
				if (child instanceof ASTNode) {
					children.add((ASTNode) child);
				}
			}
		}
		return children;
	}
	
// L-Address-Flow Rule
 public static void addAddressFlowExp(IVariableBinding leftBind, Expression rightExp, E_Method _method){

		String aliasOp = GlobalVariables.ALIAS;
		
		E_MRefParameter pointerParameter = null;
		
		E_MRefParameter pointeeParameter = null;
		
		E_MLocalVariable pointerLocalVar = null;
			
		E_MLocalVariable pointeeLocalVar = null;
		
		E_MRefField pointerField = null;
		
		E_MRefField pointeeField = null;
		
		IVariableBinding paraBind = null;
			
		IMethodBinding methodBind = null;

		// initializer information to be set				
	    int initializer = -1;
		
		//get right hand side 
	//System.out.println("method = "+ _method.getName()+" class = "+_method.getDeclClassQName()+" "+rightExp.toString());
	
	if(rightExp != null){
		
		initializer = AST_Parser.getlExprType(rightExp);
	}		

   if(initializer != -1){
	     
	 IVariableBinding rightBind = null;
		
	 rightBind = AST_Parser.getVariableBinding(rightExp);
	
	 if(rightBind != null){
		//Left hand side is a local variable or a parameter
		if(leftBind.getDeclaringMethod() != null){ 
			
			if(leftBind.isParameter() == false && leftBind.isField() == false) {
						
				pointerLocalVar = createLocalPointer(leftBind,_method);
			}
			else if (leftBind.isParameter() && ifReferenceType(leftBind.getType())) {	
				
				pointerParameter = searchRefParameter(leftBind);
					
			}
		}
		// left hand side is a class field
		else if (leftBind.getDeclaringClass() != null){
			
			pointerField = createFieldPointer(leftBind, _method, rightExp.getNodeType());
		}
		
	   // if initializer is a Field Access or qualified field name
		 		
		 if(initializer == ASTNode.FIELD_ACCESS || initializer == ASTNode.QUALIFIED_NAME){	
						
			pointeeLocalVar = null;
			
			pointeeParameter = null;

			paraBind = null;
	
			methodBind = null;
							
				if(pointerLocalVar != null || pointerField != null || pointerParameter!=null){
					// The right hand side is a field , pointee is a field in this case
						addFieldAddressFlow(pointerLocalVar,pointerField,pointerParameter,pointeeLocalVar,aliasOp, rightBind, paraBind, methodBind, _method, rightExp.getNodeType());	
				}
		   }
		 //double [] Gi = G[i]; //alias of G[i]
			// initializer is a simple name
		  else if(initializer == ASTNode.SIMPLE_NAME){ 
			  // if right hand side is a parameter or a local variable
			          if(rightBind.getDeclaringMethod() != null && rightBind.getDeclaringClass() == null){	    	
			        	  // If initializer is an Array Parameter or a Reference Type Parameter
			        	//  System.out.println("Left exp = "+ leftBind.getName()+" and right hand = "+rightBind.getName());
		      	  	    		    
			        	      if (rightBind.isParameter() && ifReferenceType(rightBind.getType())) {	
						    		
						    		//Number numObj6 = ntemp;// Parameter with Reference Type
			        	    	
			        	    	  		/*if(rightExp.getNodeType() == ASTNode.ARRAY_ACCESS){

			        	    	  			String fType = rightExp.resolveTypeBinding().getQualifiedName();
			        	    	  			
			        	    	  			ArrayAccess array = (ArrayAccess) rightExp;
			        	    		
			        	    	  		}*/
			        	    	  		methodBind = null;
							    		
							    		paraBind = rightBind;
							    		
							    		rightBind = null;
							    		
							    		String decClass = "";
							    		
							    	  if(pointerLocalVar != null){
							    			
							    			AST_Parser.addMLocalVariable(_method, pointerLocalVar, rightExp.getNodeType(), false);
											
							     		}
							    		if(pointerField != null ){
							    			
							    			addFieldAccess(_method, leftBind, GlobalVariables.WRITE, rightExp.getNodeType(),_method.getQualifyingObject());
							    		}
							    		if(pointerLocalVar != null || pointerField != null || pointerParameter!=null){ 			

							    				addFieldAddressFlow(pointerLocalVar,pointerField,pointerParameter, pointeeLocalVar,aliasOp, rightBind, paraBind, methodBind, _method, rightExp.getNodeType());	
										 }
						    		
						    		}
						    	
								// if initializer is a local variable
							      else if(rightBind.isParameter() == false && rightBind.isField() == false) {
								
										pointeeField =  null;
										 
										 paraBind = null;
										 
										 methodBind = null;
										 
										 pointeeLocalVar = null;
										
										 pointeeParameter = null;
										  
										  E_MLocalVariable pointeeLVar = new E_MLocalVariable(rightBind.getName(),rightBind.getType().getQualifiedName().toString(),_method,aliasOp,false);
										  
										  if(pointerField != null ){
								    			
								    			addFieldAccess(_method, leftBind, GlobalVariables.WRITE, rightExp.getNodeType(),_method.getQualifyingObject());
								    	  }
										  if(pointerLocalVar != null || pointerField != null || pointerParameter!=null){ 	
											 
											  AST_Parser.addLocalAddFlow(pointeeLVar, pointerLocalVar,pointerField,pointeeField,pointerParameter, pointeeParameter, aliasOp,rightExp,  paraBind,  methodBind, _method ); 
										  }
								 		 			
								  }//else if end heres	
							} // if right hand side is a parameter or a local variable
							else if(rightBind.getDeclaringClass() != null) {	
								if (rightBind.getDeclaringClass().getTypeDeclaration().isFromSource()){	
								// if initializer is a class field with simple Name
										pointeeLocalVar = null;
										
										paraBind = null;
										
										methodBind = null;
										if(pointerLocalVar != null || pointerField != null || pointerParameter!=null){ 		
											addFieldAddressFlow(pointerLocalVar,pointerField,pointerParameter, pointeeLocalVar,aliasOp, rightBind, paraBind, methodBind, _method, rightExp.getNodeType());
										}
								} // 
							}// else if ends here
				}//simple name ends here
				/**********************************Simple Name Ends Here************************************************************/
	 			}// initializer ends here
				else if(initializer == ASTNode.METHOD_INVOCATION){
							
					 paraBind = null;
					 
					 methodBind = null;
					 
					 MethodInvocation m = null;
					
					 //System.out.println(""+_method.getName()+" "+rightExp.toString());
					   methodBind = AST_Parser.getMethodInvocBinding(rightExp);
					 
					//m = (MethodInvocation) rightExp;
					
					 if(methodBind!=null){
						// methodBind = m.resolveMethodBinding();
						 if(ifSourceMethod(methodBind)){
							if(leftBind.getDeclaringMethod()!=null){ 
								pointerLocalVar = AST_Parser.createLocalPointer(leftBind,_method);
							}
							else if (leftBind.getDeclaringClass() != null){
								pointerField = createFieldPointer(leftBind, _method, rightExp.getNodeType());
							}
							if(pointerLocalVar != null){
								//AST_Parser.addMLocalVariable(_method, pointerLocalVar, rightExp.getNodeType(), false);
							   pointerLocalVar = AST_Parser.addMLocalVariable(_method, pointerLocalVar, rightExp.getNodeType(), false);
							}
							if(pointerLocalVar != null || pointerField != null || pointerParameter!=null){ 			
			    			   addFieldAddressFlow(pointerLocalVar,pointerField,pointerParameter, pointeeLocalVar,aliasOp, null, paraBind, methodBind, _method, rightExp.getNodeType());	
				  		   }
						 }
					}// if source method end here
				}// method invocation ends here	 			
				else if (initializer == ASTNode.CLASS_INSTANCE_CREATION) {
					ClassInstanceCreation exp= (ClassInstanceCreation) rightExp;
					IMethodBinding mb = exp.resolveConstructorBinding();
					if(mb.getDeclaringClass().isParameterizedType()){
			   	   		
				   		mb = mb.getMethodDeclaration();
				   
				   	 }
					if(mb.getDeclaringClass().getTypeDeclaration().isFromSource()){
						List<Expression> arg = exp.arguments();
						
						for (Expression s : arg) {
						    // System.out.println("Class Instance Creation = "+exp+" with arguments"+s.toString());
					    }
					}
				}// class instance creation ends here
				else if (initializer == ASTNode.THIS_EXPRESSION){
					pointerField = createFieldPointer(leftBind, _method, rightExp.getNodeType());
					if(pointerLocalVar != null || pointerField != null || pointerParameter!=null){ 			
						if(pointerField != null ){
			    			addFieldAccess(_method, leftBind, GlobalVariables.WRITE, rightExp.getNodeType(),_method.getQualifyingObject());
			    		}
						addFieldAddressFlow(pointerLocalVar,pointerField,pointerParameter, pointeeLocalVar,aliasOp, rightBind, paraBind, methodBind, _method, rightExp.getNodeType());	
				     }
				}
				else{
						//Find children of Expression to find the field accesses 
						if(rightExp !=null){
						
							List<ASTNode> children = AST_Parser.getChildren(rightExp);
							
							for (ASTNode childd: children) {
								
								if (childd.getNodeType() == ASTNode.FIELD_ACCESS || childd.getNodeType() == ASTNode.QUALIFIED_NAME || childd.getNodeType() == ASTNode.SIMPLE_NAME || childd.getNodeType() == ASTNode.METHOD_INVOCATION ) {
								
									AST_Parser.addAddressFlowExp(leftBind, rightExp, _method);
								
								}//if ends	
								
							}//for ends
						}// rightExp ends here
				 }// else ends here
	 
  }
}
 public static E_MRefField getReturnField(IMethodBinding methodBind){
	 E_MRefField  field = null;
	 LinkedList<E_MRefField>  fields = Data_Controller.fetchMRefFields(methodBind);
	 for(E_MRefField f:  fields){
		 if(f.isRetFiel()){
			 field = f;
		 }
	 }
		return field;
 }
 public static E_MRefParameter getReturnParameter(E_Method _methd){
	 
	 E_Method m = Data_Controller.searchMethod(_methd);
	 E_MRefParameter  param = null;
	 if(m!=null){
		 LinkedList<E_MRefParameter> params = m.getRefparams();
		 if(params!=null){	
			for(E_MRefParameter  f: params){
			if(f.isRetFiel()){
				param = f;
			 }
			}
		}

	 }
		return param;
 }

public static E_MLocalVariable createLocalPointer(IVariableBinding leftBind,E_Method method){
	
	E_MLocalVariable pointerLocalVar = null;
	
		 if(leftBind.isField() == false && leftBind.isParameter() == false){
			 pointerLocalVar = AST_Parser.createLocalVariable(leftBind,method);
	 }
	return pointerLocalVar;
}

public static E_MRefField createFieldPointer(IVariableBinding leftBind,E_Method _method, int expType){
	E_MRefField pointerField = null;
	
	E_Object obj = new E_Object();
	if(leftBind.getDeclaringClass()!=null){
	if (leftBind.getDeclaringClass().getTypeDeclaration().isFromSource()){		
		obj.setName(leftBind.getDeclaringClass().getQualifiedName());
		
			pointerField = addFieldAccess(_method, leftBind, GlobalVariables.READ,expType,obj);// needs to be discussed
			pointerField.setAliasOp(GlobalVariables.ALIAS);
		}
	}
	return pointerField;
			
}
 public static void addPMAlias(E_MRefField pointerField,E_MLocalVariable pointerLocalVar,IMethodBinding methodBind,IVariableBinding paraBind){
		
	    E_MRefParameter pointerParam = null;
		
	    E_MRefAlias alias = new E_MRefAlias(pointerField,pointerLocalVar,pointerParam,methodBind,paraBind);
		
		if(pointerLocalVar != null){
			
			pointerLocalVar.addAlias(alias);
		}
		else if(pointerField != null)
		{
			pointerField.addAlias(alias);

		}						    		
		
	}
 public static  E_MLocalVariable createLocalVariable(IVariableBinding  bind,E_Method method){
     
	   // E_Method ldeclType = new E_Method();
		
	    String  lfName  = bind.getName().toString();
		
	    String lfType = bind.getType().getQualifiedName().toString();
	
		//ldeclType.setName(findDeclarationType(bind));
		
		E_MLocalVariable pointerLocalVar = new E_MLocalVariable(lfName, lfType,method,GlobalVariables.ALIAS,false);
		
	return pointerLocalVar;
}

public static void addFieldAddressFlow(E_MLocalVariable pointerLocalVar,E_MRefField pointerField,E_MRefParameter pointerParamter, E_MLocalVariable pointeeLocalVar,String aliasop, IVariableBinding rightBind, IVariableBinding paraBind, IMethodBinding methodBind,E_Method _method, int expType ){  

	 	E_MRefField pointeeField = null;
	 	
	 	E_MRefParameter pointeeParameter = null;
	   
	 	if(rightBind != null){
	    	pointeeField = addFieldAccess(_method, rightBind, GlobalVariables.READ, expType, _method.getQualifyingObject());
	    }
	    else if (paraBind != null){
	    	pointeeParameter = searchRefParameter(paraBind);
	    
	    }
	    else if (methodBind != null){
	    	pointeeField = getReturnField(methodBind);
			
	    }
	 	int eType = findExpType(pointeeField,pointeeParameter);
	 	
	 	if(pointeeField != null || pointeeParameter!=null){
			
			AST_Parser.addPointer(pointerLocalVar,pointerField,pointerParamter,pointeeField,pointeeLocalVar,pointeeParameter,aliasop,eType, paraBind, methodBind, _method);

		}	
}	
public static int findExpType(E_MRefField sourceField ,
	E_MRefParameter sourceParameter){
	int type = 0;
	
	if(sourceField!=null && sourceParameter==null){
		type = sourceField.getExpType();
	}
	else if(sourceParameter!=null&&sourceField==null){
		type = sourceParameter.getExpType();
	}
	
	return type;
}
public static void addLocalAddFlow(E_MLocalVariable pointeeLocalVar, E_MLocalVariable pointerLocalVar, E_MRefField pointerField, E_MRefField pointeeField,E_MRefParameter pointerParameter, E_MRefParameter pointeeParameter, String aliasop,Expression rightSide, IVariableBinding paraBind, IMethodBinding methodBind,E_Method _method ){  
	    
	    E_MLocalVariable tempVar = null;
	    
		if(checkPointee(pointeeLocalVar,pointeeField,pointeeParameter,_method)){
			
			tempVar = AST_Parser.addLocalVarAsPointee(_method,pointeeLocalVar,rightSide.getNodeType());
		}
		if(tempVar!=null){
			
			AST_Parser.addPointer(pointerLocalVar,pointerField,pointeeParameter, pointeeField,pointeeLocalVar,pointeeParameter,aliasop,rightSide.getNodeType(), paraBind, methodBind,_method);
		 }
}
	public static E_MRefField addField(E_Method _method,String name,String type, String declClass,String op, int expType, boolean retExp,E_Object obj,boolean fFlag, boolean pFlag){
		
	    AST_Parser.addClassField(_method,name,type,declClass, op, expType,retExp,obj,fFlag,pFlag);
	    
	    E_MRefField _field = Data_Controller.fetchLastRefField1(_method);	
	   
	   return _field;
							
	}
  public static E_MLocalVariable addMLocalVariable(E_Method _method, E_MLocalVariable _localVar,int expType, boolean isPointee){
	   
	    AST_Parser.addMLocalVar(_method, _localVar);

	   _localVar = Data_Controller.fetchLastRefLocalVariable(_method);	

		return _localVar;
							
	}

 @SuppressWarnings("unused")
public static E_MRefField ifAliasOfField(E_MLocalVariable pointerVar, E_MRefField pointerField, E_MRefParameter pointerParam){

		 E_MRefField ref= null;
		 
		 LinkedList<E_MRefAlias>  aliases  = null;

		 LinkedList<E_MRefField> _refs = Data_Controller.fetchAllReference();
		      
    for(E_MRefField r:  _refs){
	   
    	if(r.getAliases()!=null && r.getAliases().isEmpty() == false){
		
		   aliases = r.getAliases();
		 
		   if(pointerVar != null && pointerField == null && pointerParam == null){	
 			
			  ref = findPointeeFieldofLocalAlias(pointerVar,r,aliases);
				
			  if(ref != null){
					break;
				}
				else
					continue;
	  		}
			else if(pointerField !=null && pointerVar == null&& pointerParam == null ){
		  	     
				ref = 	findPointeeFieldofFieldAlias(pointerField,r,aliases);
				
				if(ref!= null){
						break;
					}
					else
						continue;				
			 }
			else if(pointerParam != null && pointerField ==null && pointerVar == null ){
	  	       
				ref = 	findPointeeFieldofParamAlias(pointerParam,r,aliases);
				
				if(ref!= null){
					break;
				}
				else
					continue;				
		    }

		 }
	  }
			return ref;
		 
}
 public static E_MRefParameter ifAliasOfParam(E_MLocalVariable pointerVar, E_MRefField pointerField, E_MRefParameter pointerParam,E_Method method){

	 E_MRefParameter param= null;
	 
	 LinkedList<E_MRefAlias>  aliases  = null;

	 LinkedList<E_MRefParameter> _params = Data_Controller.fetchMRefParameters(method);
	      
for(E_MRefParameter p:  _params){
   if(p.getAliases()!=null&&p.getAliases().isEmpty() == false){
	  aliases = p.getAliases();
	   if(pointerVar != null && pointerField == null && pointerParam == null){	
		   param = findParamFieldofLocalAlias(pointerVar,p,aliases);
			if(param != null){
				break;
			}
			else
				continue;
  		}
		else if(pointerField !=null && pointerVar == null&& pointerParam == null ){
			param = 	findPointeeParamofFieldAlias(pointerField,p,aliases);
				if(param!= null){
					break;
				}
				else
					continue;				
		 }
		else if(pointerParam != null && pointerField ==null && pointerVar == null ){
			param = 	findPointeeParamofParamAlias(pointerParam,p,aliases);
			if(param!= null){
				break;
			}
			else
				continue;				
	    }

	 }
  }
		return param;
	 
}
 
 public static E_MLocalVariable ifAliasOfLocal(E_MLocalVariable pointerVar, E_MRefField pointerField, E_MRefParameter pointerParam){
	 
	 E_MLocalVariable var= null;

	 LinkedList<E_MRefAlias>  aliases  = null;
	
	 LinkedList<E_MLocalVariable> locals = Data_Controller.fetchAllLocals();//Here we should fetch local variables of a method that is method of lVar

 for(E_MLocalVariable v:  locals){
	 if(v.getAliases() !=null&&v.getAliases().isEmpty()==false){		
 		   aliases = v.getAliases();
			if(pointerVar != null && pointerField == null && pointerParam == null){
				var = null;
				var = findLocalPointeeofLocalAlias(pointerVar,v,aliases);
				if(var != null){
					break;
				}
				else
					continue;
			}
			else if(pointerField !=null && pointerVar == null&& pointerParam == null){
				var = null;
				var = findLocalPointeeofFieldAlias(pointerField,v,aliases);
				if(var!= null){
					break;
				}
				else
					continue;
			}
			else if(pointerParam !=null && pointerField ==null && pointerVar == null ){
				var = null;
				var = findLocalPointeeofParamAlias(pointerParam,v,aliases);
				if(var!= null){
					break;
				}
				else
					continue;
			}
		}
 }
	 return var;
}

 public static boolean	matchLocalAlias(E_MLocalVariable pointerVar,LinkedList<E_MLocalVariable> lAliases){
	
	 boolean flag= false;	
	
	 
	for(E_MLocalVariable la:lAliases){
		//if(la.equals(sourceVar)){
		   if(la.getName().equals(pointerVar.getName())&&la.getType().equals(pointerVar.getType())&&la.getDeclMethod().getName().equals(pointerVar.getDeclMethod().getName()) ){
			  //System.out.println("Local Variable removed = "+la+" "+lAliases.remove(la));
			  flag = true;
			  break;
			}
			
	}
	return flag;
}
	
 public static boolean	matchFieldAlias(E_MRefField sourceField,LinkedList<E_MRefField> fAliases){
		
	 boolean flag= false;	
	
		for(E_MRefField ra:fAliases){
			if(ra!=null){
				//if(ra.equals(sourceField)){
				if(ra.getName().equals(sourceField.getName()) && ra.getType().equals(sourceField.getType()) && ra.getDeclaringClass().equals(sourceField.getDeclaringClass())){	
					//fAliases.remove(sourceField);
					flag = true;
					break;
				}
			}
			
		}
	return flag;
}
 public static E_MRefField findPointeeFieldofLocalAlias(E_MLocalVariable pointerVar,E_MRefField pointeeField,LinkedList<E_MRefAlias> aliases){
		
	 LinkedList<E_MLocalVariable> lAliases = new LinkedList<E_MLocalVariable>();
	
	    E_MRefField ref= null;

	     lAliases = fetchLocalAliases(aliases);
	     if(!(lAliases.isEmpty())){
	      //if(matchLocalAlias(pointerVar,lAliases)){
	    	  for(Iterator<E_MLocalVariable> iter = lAliases.iterator(); iter.hasNext();) {
				   
	    		  E_MLocalVariable data = iter.next();
				   
				   if(data.getName().equals(pointerVar.getName())&&data.getType().equals(pointerVar.getType())&&data.getDeclMethod().getName().equals(pointerVar.getDeclMethod().getName())){
					   ref = pointeeField;
					   //iter.remove();
					   break;
				   }
				   else
						continue;
			}
	      // }
		

		}return ref;
}
 public static E_MRefParameter findParamFieldofLocalAlias(E_MLocalVariable pointerVar,E_MRefParameter pointeeParam,LinkedList<E_MRefAlias> aliases){
		
	 	LinkedList<E_MLocalVariable> lAliases = new LinkedList<E_MLocalVariable>();
	
	 	E_MRefParameter param= null;

	       lAliases = fetchLocalAliases(aliases);
			//if(matchLocalAlias(pointerVar,lAliases)){
	       if(!(lAliases.isEmpty())){
			     for(Iterator<E_MLocalVariable> iter = lAliases.iterator(); iter.hasNext();) {
				   E_MLocalVariable data = iter.next();
				   if(data.getName().equals(pointerVar.getName())&&
						   data.getType().equals(pointerVar.getType())&&
						   data.getDeclMethod().getName().equals(pointerVar.getDeclMethod().getName())){
					   param = pointeeParam;
					   //iter.remove();
					   break;
				   }
				   else
						continue;
			}
	       }
		
			return param;
}
 public static boolean removeLocalAlias(E_MLocalVariable pointerVar, LinkedList<E_MRefAlias> aliases){
		
	 LinkedList<E_MLocalVariable> lAliases = new LinkedList<E_MLocalVariable>();
	 
	 boolean flag = false;
	   
	lAliases = fetchLocalAliases(aliases);
	
	for(Iterator<E_MLocalVariable> iter = lAliases.iterator(); iter.hasNext();) {
		   E_MLocalVariable data = iter.next();
		   if(data.getName().equals(pointerVar.getName())&&data.getType().equals(pointerVar.getType())&&data.getDeclMethod().getName().equals(pointerVar.getDeclMethod().getName())){
			   iter.remove();
			   flag = true;
			   break;
		   }
		   else
				continue;
	}
return flag;
}
 public static boolean removeFieldAlias(E_MRefField pointerField, LinkedList<E_MRefAlias> aliases){
		
	 LinkedList<E_MRefField> lAliases = new LinkedList<E_MRefField>();
	 
	 boolean flag = false;
	   
	lAliases = fetchFieldAliases(aliases);
	
	for(Iterator<E_MRefField> iter = lAliases.iterator(); iter.hasNext();) {
		   E_MRefField data = iter.next();
		   if(data.getName().equals(pointerField.getName())&&data.getType().equals(pointerField.getType())&&data.getDeclaringClass().equals(pointerField.getDeclaringClass())){
			  // System.out.println("Alias Edge of variable = "+pointerField.getName());
			   iter.remove();
			   flag = true;
			   break;
		   }
		   else
				continue;
	}
return flag;
}

 public static boolean removeParamAlias(E_MRefParameter pointerParam, LinkedList<E_MRefAlias> aliases){
		
	 LinkedList<E_MRefParameter> pAliases = new LinkedList<E_MRefParameter>();
	 
	 boolean flag = false;
	   
	 pAliases = fetchParamAliases(aliases);
	
	for(Iterator<E_MRefParameter> iter = pAliases.iterator(); iter.hasNext();) {
		  E_MRefParameter data = iter.next();
		   if(data.getName().equals(pointerParam.getName())&&data.getType().equals(pointerParam.getType())&&data.getDeclaringClass().equals(pointerParam.getDeclaringClass())){
			  // System.out.println("Alias Edge of Parameter = "+pointerParam.getName());
			   iter.remove();
			   flag = true;
			   break;
		   }
		   else
				continue;
	}
return flag;
}

 public static E_MLocalVariable findLocalPointeeofLocalAlias(E_MLocalVariable sourceVar,E_MLocalVariable v,LinkedList<E_MRefAlias> aliases){
		
	 E_MLocalVariable var= null;

	 LinkedList<E_MLocalVariable> lAliases = null;
	 
	 lAliases = fetchLocalAliases(aliases);
	  for(Iterator<E_MLocalVariable> iter = lAliases.iterator(); iter.hasNext();) {
		   E_MLocalVariable data = iter.next();
			   if(data.getName().equals(sourceVar.getName())&&data.getType().equals(sourceVar.getType())&&data.getDeclMethod().getName().equals(sourceVar.getDeclMethod().getName())){
				   var = v;
				  // iter.remove();
				   break;
			   }
			   else
					continue;
	  	}
			  /*E_MLocalVariable var= null;

				 LinkedList<E_MLocalVariable> lAliases = null;
			 	
			for(E_MRefAlias aa:aliases){
				
			  if(aa.getLocalVarAlias()!= null){
				  lAliases = aa.getLocalVarAlias();
			  if(matchLocalAlias(sourceVar,lAliases)){
				  var = v;
				  if(var!=null){
					  //lAliases.remove(sourceVar);
					    aa.removeLocalVarAlias(sourceVar);
					  break;
				  }
				  
								  
				}
				else
					continue;*/
			  
	 return var;
 }
public static LinkedList<E_MLocalVariable> fetchLocalAliases(LinkedList<E_MRefAlias> aliases){

	LinkedList<E_MLocalVariable> lAliases = new LinkedList<E_MLocalVariable>();

	if(!aliases.isEmpty()){
		for(E_MRefAlias aa:aliases){
			if(aa!=null){
			  if(aa.getLocalVarAlias()!= null && aa.getLocalVarAlias().isEmpty()==false){
				  lAliases = aa.getLocalVarAlias();
				  break;  // check if needs to break here
			  }
			}
		}
	}
return lAliases;
}
public static LinkedList<E_MRefField> fetchFieldAliases(LinkedList<E_MRefAlias> aliases){
	
	LinkedList<E_MRefField> fAliases = new LinkedList<E_MRefField>();
	 
	Iterator<E_MRefAlias> it = aliases.iterator();
		
	while (it.hasNext()) {
		E_MRefAlias aa = it.next();
		if(aa!=null){
			if(aa.getFieldAlias() != null && aa.getFieldAlias().isEmpty() == false){
				fAliases = aa.getFieldAlias();
			}
		}
	
	}
		
/*for(E_MRefAlias aa:aliases){
	  if(aa.getFieldAlias() != null){
		 if( aa.getFieldAlias().isEmpty() == false){
			 fAliases = aa.getFieldAlias();
		 }
	  }
	}
*/
	return fAliases;
}

 public static E_MRefField findPointeeFieldofFieldAlias(E_MRefField sourceField, E_MRefField r, LinkedList<E_MRefAlias> aliases){
		
	 LinkedList<E_MRefField> fAliases = new LinkedList<E_MRefField>();
		
	 E_MRefField ref = null;
	/* for(E_MRefAlias aa:aliases){
		  if(aa.getFieldAlias()!= null){
			   fAliases = aa.getFieldAlias();
			   if(matchFieldAlias(sourceField,fAliases)){
				   	ref = r;
					if(ref!=null){
					  aa.removeFieldAlias(sourceField);
					}
				   	break;
								  
				}
				else{
					continue;
			    }
			}
		}*/
	 
	 		if(aliases!=null && aliases.isEmpty() == false){
			   fAliases = fetchFieldAliases(aliases);
			   if(fAliases!=null){
			     for(Iterator<E_MRefField> iter = fAliases.iterator(); iter.hasNext();) {
				   E_MRefField data = iter.next();
					   if(data.getName().equals(sourceField.getName())&&data.getType().equals(sourceField.getType())&&data.getDeclaringClass().equals(sourceField.getDeclaringClass())){
						 	ref = r;
						   //iter.remove();
						   break;
					   }
					   else
							continue;
			  	}
			   }
			   }
			   
	 return ref;
 		
 }
 public static E_MRefParameter findPointeeParamofFieldAlias(E_MRefField sourceField, E_MRefParameter p, LinkedList<E_MRefAlias> aliases){
		
	 LinkedList<E_MRefParameter> fAliases = new LinkedList<E_MRefParameter>();
		
	 E_MRefParameter param = null;
	/* for(E_MRefAlias aa:aliases){
		  if(aa.getFieldAlias()!= null){
			   fAliases = aa.getFieldAlias();
			   if(matchFieldAlias(sourceField,fAliases)){
				   	ref = r;
					if(ref!=null){
					  aa.removeFieldAlias(sourceField);
					}
				   	break;
								  
				}
				else{
					continue;
			    }
			}
		}*/
	 
			   fAliases = fetchParamAliases(aliases);
			   for(Iterator<E_MRefParameter> iter = fAliases.iterator(); iter.hasNext();) {
				   E_MRefParameter data = iter.next();
					   if(data.getName().equals(sourceField.getName())&&data.getType().equals(sourceField.getType())&&data.getDeclaringClass().equals(sourceField.getDeclaringClass())){
						 	param = p;
						   //iter.remove();
						   break;
					   }
					   else
							continue;
			  	}
			   
	 return param;
 		
 }
 public static E_MRefField findPointeeFieldofParamAlias(E_MRefParameter pointerParam, E_MRefField r, LinkedList<E_MRefAlias> aliases){
		
	 LinkedList<E_MRefParameter> fAliases = new LinkedList<E_MRefParameter>();
		
	   E_MRefField ref = null;
	   fAliases = fetchParamAliases(aliases);
	   for(Iterator<E_MRefParameter> iter = fAliases.iterator(); iter.hasNext();) {
		   E_MRefParameter data = iter.next();
			   if(data.getName().equals(pointerParam.getName())&&data.getType().equals(pointerParam.getType())&&data.getDeclaringClass().equals(pointerParam.getDeclaringClass())){
				 	ref = r;
				   break;
			   }
			   else
					continue;
	  	}
	   
	 return ref;
 		
 }
 public static E_MRefParameter findPointeeParamofParamAlias(E_MRefParameter pointerParam, E_MRefParameter p, LinkedList<E_MRefAlias> aliases){
		
	 LinkedList<E_MRefParameter> fAliases = new LinkedList<E_MRefParameter>();
		
	 E_MRefParameter param = null;
	   fAliases = fetchParamAliases(aliases);
	   for(Iterator<E_MRefParameter> iter = fAliases.iterator(); iter.hasNext();) {
		   E_MRefParameter data = iter.next();
			   if(data.getName().equals(pointerParam.getName())&&data.getType().equals(pointerParam.getType())&&data.getDeclaringClass().equals(pointerParam.getDeclaringClass())){
				   param = p;
				   break;
			   }
			   else
					continue;
	  	}
	   
	 return param;
 		
 }
 public static LinkedList<E_MRefParameter> fetchParamAliases(LinkedList<E_MRefAlias> aliases){
	 LinkedList<E_MRefParameter> fAliases = new LinkedList<E_MRefParameter>();
	for(E_MRefAlias aa:aliases){
	  if(aa.getParamAlias()!= null){
			fAliases = aa.getParamAlias();
	  }
	}
	return fAliases;
}
	
public static E_MLocalVariable findLocalPointeeofParamAlias(E_MRefParameter sourceParam,E_MLocalVariable v,LinkedList<E_MRefAlias> aliases){

	 
	 E_MLocalVariable var = null;
	 
	 LinkedList<E_MRefParameter> fAliases = new LinkedList<E_MRefParameter>();
	
	 fAliases = fetchParamAliases(aliases);
	 for(Iterator<E_MRefParameter> iter = fAliases.iterator(); iter.hasNext();) {
		 E_MRefParameter data = iter.next();
			   if(data.getName().equals(sourceParam.getName())&&data.getType().equals(sourceParam.getType())&&data.getDeclaringClass().equals(sourceParam.getDeclaringClass())){
				   var = v;
				   //iter.remove();
				   break;
			   }
			   else
					continue;
	  	}
	 
	 return var;
 }
 public static E_MLocalVariable findLocalPointeeofFieldAlias(E_MRefField sourceField,E_MLocalVariable v,LinkedList<E_MRefAlias> aliases){

	 
	 E_MLocalVariable var = null;
	 
	 LinkedList<E_MRefField> fAliases = new LinkedList<E_MRefField>();
	
	 fAliases = fetchFieldAliases(aliases);
	 for(Iterator<E_MRefField> iter = fAliases.iterator(); iter.hasNext();) {
		   E_MRefField data = iter.next();
			   if(data.getName().equals(sourceField.getName())&&data.getType().equals(sourceField.getType())&&data.getDeclaringClass().equals(sourceField.getDeclaringClass())){
				   var = v;
				   //iter.remove();
				   break;
			   }
			   else
					continue;
	  	}
	 
	 return var;
 }
 /*public static E_MLocalVariable findLocalPointeeofFieldAlias(E_MRefField sourceField,E_MLocalVariable sourceVar,E_MRefAlias aa){
		
	 LinkedList<E_MLocalVariable> aliases = null;
	 
	 E_MLocalVariable var = null;
	 
	 if(aa.getLocalVarAlias()!= null){
			  aliases = aa.getLocalVarAlias();
				for(E_MLocalVariable v:aliases){
					if(v!=null){
						if(v.getName().equals(sourceVar.getName()) && v.getType().equals(sourceVar.getType()) && v.getDeclMethod().getName().equals(sourceVar.getDeclMethod().getName())){
							var = v;
							 break;
								  
						}
					 }
					}
				}	
	 return var;
 }*/


 public static int findRightHandExpType(Expression raExp){
		
		int assignExpType = -1;
		
		if (raExp.getNodeType() == ASTNode.FIELD_ACCESS) {
			//System.out.println(" Right hand side = Field Access = Exp Type = "+raExp.getNodeType());	
			assignExpType = raExp.getNodeType();
		}else if (raExp.getNodeType() == ASTNode.QUALIFIED_NAME) {
		//System.out.println(" Right hand side = Class Instance Creation Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		}  
		else if (raExp.getNodeType() == ASTNode.SIMPLE_NAME) {
			//System.out.println(" Right hand side = Simple Name =  Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		}
		else if (raExp.getNodeType() == ASTNode.THIS_EXPRESSION) {
			//System.out.println(" Right hand side = Simple Name =  Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		}
		else if (raExp.getNodeType() == ASTNode.ARRAY_ACCESS) {
			//System.out.println(" Right hand side = Simple Name =  Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		}else if (raExp.getNodeType() == ASTNode.SUPER_FIELD_ACCESS) {
			//System.out.println("Right Hand side = Method Invocation  Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		}
		else if (raExp.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION) {
			//System.out.println(" Right hand side = Class Instance Creation Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		} 
		else if (raExp.getNodeType() == ASTNode.ARRAY_CREATION) {
			//System.out.println("Right hand side =  Array Creation Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		} 
		else if (raExp.getNodeType() == ASTNode.METHOD_INVOCATION) {
			// System.out.println(" Right hand side = Simple Name =  Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		}
		else if (raExp.getNodeType() == ASTNode.SUPER_CONSTRUCTOR_INVOCATION) {
			//System.out.println("Right Hand side = Method Invocation  Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		}
		else if (raExp.getNodeType() == ASTNode.INFIX_EXPRESSION) {
			//System.out.println("Right hand side = Infix Expression Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		}
		else if (raExp.getNodeType() == ASTNode.NUMBER_LITERAL) {
			// System.out.println("Right Hand side = Number Literal Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		}
		else if (raExp.getNodeType() == ASTNode.NULL_LITERAL) {
			 //System.out.println("Right Hand side = Null Literal Exp Type = "+raExp.getNodeType());
			assignExpType = raExp.getNodeType();
		}

		return assignExpType;
}
public static int getlExprType(Expression node){

	int initializer = -1;
			
	    ITypeBinding b = node.resolveTypeBinding();
		
	    if(b!=null){
		if(b.isFromSource() || (b.isArray())){
			
				if(node.getNodeType() == ASTNode.FIELD_ACCESS){
					initializer = ASTNode.FIELD_ACCESS;
				}
				else if(node.getNodeType() == ASTNode.SIMPLE_NAME){
					initializer = ASTNode.SIMPLE_NAME;
				}
				else if(node.getNodeType() == ASTNode.THIS_EXPRESSION){
					initializer = ASTNode.THIS_EXPRESSION;
				}
				else if(node.getNodeType() == ASTNode.QUALIFIED_NAME){
						initializer = ASTNode.QUALIFIED_NAME;
				}
				else if(node.getNodeType() == ASTNode.METHOD_INVOCATION){	
					initializer = ASTNode.METHOD_INVOCATION;
				}
				else if(node.getNodeType() == ASTNode.SUPER_METHOD_INVOCATION){

					initializer = ASTNode.METHOD_INVOCATION;
				}
				else if(node.getNodeType()== ASTNode.SUPER_CONSTRUCTOR_INVOCATION){
					initializer = ASTNode.SUPER_CONSTRUCTOR_INVOCATION;
				}
				/*else if(node.getNodeType() == ASTNode.ARRAY_ACCESS){
					initializer = ASTNode.ARRAY_ACCESS;
				}
				*/else if(node.getNodeType()== ASTNode.CLASS_INSTANCE_CREATION){
					initializer = ASTNode.CLASS_INSTANCE_CREATION;
				}
				else if(node.getNodeType() == ASTNode.ARRAY_CREATION){
						initializer = ASTNode.ARRAY_CREATION;
				}
				else if(node.getNodeType() == ASTNode.ARRAY_INITIALIZER){
					initializer = ASTNode.ARRAY_INITIALIZER;
				}
				else if(node.getNodeType() == ASTNode.SUPER_FIELD_ACCESS){
					initializer = ASTNode.SUPER_FIELD_ACCESS;
				}
				/*else if (node.getNodeType() == ASTNode.CAST_EXPRESSION){
					initializer = ASTNode.CAST_EXPRESSION;
				}*/
				else if (node.getNodeType() == ASTNode.INFIX_EXPRESSION) {
					// System.out.println("Right hand side = Infix Expression Exp Type = "+raExp.getNodeType());
					initializer = node.getNodeType();
				}
				else {
		    		
					List<ASTNode> l_children = AST_Parser.getChildren(node);
					
					for (ASTNode l_child: l_children) {
					
						if (l_child.getNodeType() == ASTNode.FIELD_ACCESS || l_child.getNodeType() == ASTNode.SIMPLE_NAME || l_child.getNodeType() == ASTNode.QUALIFIED_NAME||l_child.getNodeType() == ASTNode.METHOD_INVOCATION) {
						
							Expression childExp = (Expression) l_child;
					
							return AST_Parser.getlExprType(childExp);
						}
					}
				}
		    		
			
	}	
	    }
	return initializer;
  }

public static boolean ifReturnedField(ASTNode node){
	
	boolean flag= false;
	
	List<ASTNode> pNodes = AST_Parser.getParents(node);
	
	if (pNodes != null) {
		for (ASTNode p : pNodes) {
			if (p.getNodeType() == ASTNode.RETURN_STATEMENT){
				flag = true;
				break;
			}
		}
	}
		
		return flag;
}
public static void checkRighHandSides(IVariableBinding leftExpB, Expression raExp, E_Method _method){
	
	IVariableBinding rightExpB = null;
	
	E_Object obj = new E_Object();
	
	rightExpB = AST_Parser.getVariableBinding(raExp);
	
	//obj = getExpressionObject(raExp,_method);

	int expType=-1;
	
	if(_method.isConstr()){
		if(obj.equals(_method.getQualifyingObject())){
			expType = ASTNode.CLASS_INSTANCE_CREATION;
		}
		else{
			expType = AST_Parser.findRightHandExpType(raExp);
		}
		
	}
	else{
		expType = AST_Parser.findRightHandExpType(raExp);
	}
	if(rightExpB!=null){
		if(rightExpB.getDeclaringClass()!=null) {
			//Right hand is a field access  or qualified field access
			   if (rightExpB.getDeclaringClass().getTypeDeclaration().isFromSource()){
						if(!(ifReferenceType(rightExpB.getType())) ){
							AST_Parser.addFieldAccessExp(_method,rightExpB,GlobalVariables.READ,expType, AST_Parser.ifReturnedField(raExp),obj);
						}
						else if(ifReferenceType(rightExpB.getType()) && raExp.getParent().getNodeType() == ASTNode.ARRAY_ACCESS){
							AST_Parser.addFieldAccessExp(_method,rightExpB,GlobalVariables.READ,expType, AST_Parser.ifReturnedField(raExp),obj);
						}
						else if (ifReferenceType(rightExpB.getType()) && raExp.getParent().getNodeType() != ASTNode.ARRAY_ACCESS ){	
							AST_Parser.addAddressFlowExp(leftExpB, raExp,_method);		
						}
			    }	
		 }
		else if(rightExpB.getDeclaringClass() == null && rightExpB.getDeclaringMethod() != null){  
			//Right hand is a method local variable or parameter
			if (ifReferenceType(rightExpB.getType()) && raExp.getParent().getNodeType() != ASTNode.ARRAY_ACCESS){	
				AST_Parser.addAddressFlowExp(leftExpB, raExp,_method);
			}
	     }
		
	}
}

public static void updateFieldAliases(E_MRefField ref, String op){
	
	LinkedList<E_MRefField>  fAliases = new LinkedList<E_MRefField>();
	
	LinkedList<E_MRefParameter>  pAliases = new LinkedList<E_MRefParameter>();
	
	LinkedList<E_MRefAlias> aliases = Data_Controller.fetchAliasesOfRefField(ref);
	
	//System.out.println(""+ref.getName()+" "+ref.getMethod().getName()+" "+ref.getMethod().getDeclClassQName());
	
	if(!aliases.isEmpty()){
		
		fAliases = Data_Controller.fetchFieldAliases(aliases,fAliases,pAliases);
		
		fAliases = Data_Controller.fetchLocalAliases(aliases, fAliases,pAliases);	
		
		pAliases = Data_Controller.fetchParametersAliases(aliases, pAliases);
		
		if(fAliases!=null && fAliases.isEmpty() == false){
		
			for(E_MRefField r:  fAliases){
				
				r.setMOperation(op);
			}
		}
		if(pAliases!=null && pAliases.isEmpty() == false){

			for(E_MRefParameter p:  pAliases){
				
				p.setMOperation(op);
			}
		}
	}
}
public static void updateParamAliases(E_MRefParameter param, String op){
	
	LinkedList<E_MRefField>  fAliases = new LinkedList<E_MRefField>();
	
	LinkedList<E_MRefParameter>  pAliases = new LinkedList<E_MRefParameter>();
	
	LinkedList<E_MRefAlias> aliases = Data_Controller.fetchAliasesOfParams(param);
	
	if(!aliases.isEmpty()){
		
		pAliases = Data_Controller.fetchParametersAliases(aliases, pAliases);
		
		fAliases = Data_Controller.fetchFieldAliases(aliases,fAliases,pAliases);
		
		fAliases = Data_Controller.fetchLocalAliases(aliases, fAliases,pAliases);	
		
		if(fAliases!=null&& !fAliases.isEmpty()){
			for(E_MRefField r:  fAliases){
				r.setMOperation(op);
			}
		}
		
		if(pAliases!=null && !pAliases.isEmpty()){
			
	
		for(E_MRefParameter p:  pAliases){
			
			p.setMOperation(op);
		}
		}	
	}
}
public static E_MRefField findpointeeFieldofLocalVariable(E_MLocalVariable localVar,E_Method _method){
	
	E_MRefField pointeeField = null;
	
	if((pointeeField = AST_Parser.ifThisIsAliasOfField(localVar,null,null,_method))!= null){
 	
		//System.out.println("Field = "+pointeeField.getName()+" "+pointeeField.getType()+" "+pointeeField.getDeclaringClass());
 	}

	return pointeeField;
				       
}
public static E_MRefParameter findpointeeParameterofLocalVariable(E_MLocalVariable localVar,E_Method _method){
	
	E_MRefParameter pointeeParam = null;
	
	if((pointeeParam = AST_Parser.ifAliasOfParam(localVar, null,null,_method))!= null){
 	
		//System.out.println("Parameter = "+pointeeParam.getName()+" "+pointeeParam.getType()+" "+pointeeParam.getDeclMethod());
 	}

	return pointeeParam;
				       
}
public static E_MRefField findpointeeFieldofField(IVariableBinding binding,E_Method _method){
	
	E_MRefField pointeeField = null;
	
	E_MRefField field  = Data_Controller.searchField(binding.getName().toString(),binding.getType().getQualifiedName().toString(), binding.getDeclaringClass().getQualifiedName());
 
	if(field!=null){
		if((pointeeField = AST_Parser.ifThisIsAliasOfField(null,field,null,_method))!= null){
			//System.out.println("Field = "+pointeeField.getName()+" "+pointeeField.getType()+" "+pointeeField.getDeclaringClass());
		}
	}
		return pointeeField;
				       
}
public static SimpleName getQualifier(Expression laExp){

	QualifiedName qf = (QualifiedName) laExp;
	SimpleName obj = null;
	Name qualifier =  qf.getQualifier();
	if(qualifier!=null){
		if(qualifier.isSimpleName()){
			obj = (SimpleName) qualifier;
			
		}
		else{
		   List<ASTNode> l_children = AST_Parser.getChildren(qualifier);
			for (ASTNode child: l_children) {
						if (child.getNodeType() == ASTNode.QUALIFIED_NAME) {
								Expression child_exp = (Expression) child;
								obj = AST_Parser.getQualifier(child_exp);
								if(obj !=null){
									break;
								}
						}
					}
			}
		}

	return obj;
}
public static SimpleName getSuperFieldQualifier(Expression laExp){

	SuperFieldAccess qf = (SuperFieldAccess) laExp;
	SimpleName obj = null;
	Name qualifier =  qf.getQualifier();
	if(qualifier!=null){
		if(qualifier.isSimpleName()){
			obj = (SimpleName) qualifier;
			
		}
		else{
		   List<ASTNode> l_children = AST_Parser.getChildren(qualifier);
			for (ASTNode child: l_children) {
					
						if (child.getNodeType() == ASTNode.FIELD_ACCESS || child.getNodeType() == ASTNode.SIMPLE_NAME ||
								child.getNodeType() == ASTNode.SUPER_FIELD_ACCESS) {
								Expression child_exp = (Expression) child;
								obj = AST_Parser.getSuperFieldQualifier(child_exp);
								if(obj!=null){
									break;
								}
						}
					}
			}
		}

	return obj;
}
public static boolean ifLocalVariable(IVariableBinding bind){
   boolean flag = false;
   if(bind.getDeclaringMethod() != null && bind.getDeclaringClass()== null){
	   if(bind.isField() == false && bind.isParameter() == false){
		   if(ifReferenceType(bind.getType()))
			flag = true;
	   }
    }
		
return flag;		       
}
public static boolean ifFieldVariable(IVariableBinding bind){
	   boolean flag = false;
	   if(bind.getDeclaringClass() != null && bind.getDeclaringClass().getTypeDeclaration().isFromSource()){
		   if(bind.isField() == true){
				flag = true;
			 	
		   }
	    }
			
	return flag;
					       
}
public static boolean ifSourceMethod(IMethodBinding methodBind){
	   
	boolean flag = false;
	   
	   if(methodBind.getDeclaringClass() != null){
			
			if (methodBind.getDeclaringClass().getTypeDeclaration().isFromSource()){
						
				//if(methodBind.getReturnType().isFromSource() || (methodBind.getReturnType().isArray())){
					flag = true;
				//}
			}
	   }

	return flag;
					       
}

public static boolean ifParameterVariable(IVariableBinding bind){
	boolean flag = false;
	if(bind != null){
		if(bind.getDeclaringMethod() != null && bind.getDeclaringClass()== null){
		   if(bind.isField() == false && bind.isParameter() == true){
				//if(ifReferenceType(bind.getType()))
					flag = true;		 	
		   }
	    }
	}
		return flag;
				       
}
public static void addAssignmentExpression(Expression laExp, Expression raExp, E_Method _method){

	
	if(laExp != null){
		
		 if (laExp.getNodeType() == ASTNode.FIELD_ACCESS|| laExp.getNodeType() == ASTNode.QUALIFIED_NAME 
				 || laExp.getNodeType() == ASTNode.SIMPLE_NAME){
				// |laExp.getNodeType() == ASTNode.ARRAY_ACCESS) {
			
			 if(raExp!=null){
			 	
				checkRightSide( laExp,  raExp,  _method);

		   }
		   else{				
				List<ASTNode> l_children = AST_Parser.getChildren(laExp);
				
				for (ASTNode child: l_children) {
				
					if (child.getNodeType() == ASTNode.FIELD_ACCESS || child.getNodeType() == ASTNode.QUALIFIED_NAME || 
							child.getNodeType() == ASTNode.SIMPLE_NAME){
							//||child.getNodeType() == ASTNode.ARRAY_ACCESS) {
					
						Expression childExp = (Expression) child;		
						
						AST_Parser.addAssignmentStatem(laExp,childExp,_method);
					}
				}
		   } 
				
		 }
	}
	
}
public static void checkRightSide(Expression laExp,Expression raExp, E_Method _method){
	
	E_Object qualObj = new E_Object();
			
	qualObj = getExpressionObject(laExp,_method);
	
	int expType=-1;
	
	//if(_method.isConstr()){
		/*if(qualObj.equals(_method.getQualifyingObject())){
			expType = ASTNode.CLASS_INSTANCE_CREATION;
		}
		else{
			expType = AST_Parser.findRightHandExpType(raExp);
		}*/
	//}
	//else{
	expType = AST_Parser.findRightHandExpType(raExp);
	//}
	if(raExp != null){	
		//System.out.println("class = "+_method.getDeclClassQName().toString()+"Assign:"+raExp.toString()+" = "+laExp.toString());
		if (raExp.getNodeType() == ASTNode.FIELD_ACCESS|| raExp.getNodeType() == ASTNode.QUALIFIED_NAME
	    		 || raExp.getNodeType() == ASTNode.SIMPLE_NAME
	    		 ||raExp.getNodeType() == ASTNode.ARRAY_ACCESS
				 ||raExp.getNodeType() == ASTNode.NUMBER_LITERAL
				 ||raExp.getNodeType() == ASTNode.PRIMITIVE_TYPE){
				 //||raExp.resolveTypeBinding().isPrimitive()){// changes made on 24-Jan-2019
	    	        	
			 AST_Parser.addAssignmentStatem(laExp,raExp,_method);
		 }
		else if (raExp.getNodeType() == ASTNode.THIS_EXPRESSION){
   			if (AST_Parser.ifReferenceType(AST_Parser.getVariableBinding(laExp).getType())){	
   				AST_Parser.addAddressFlowExp(AST_Parser.getVariableBinding(laExp), raExp, _method);		
   			}
    	  }
		else if (raExp.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION ||
				raExp.getNodeType() == ASTNode.ARRAY_CREATION ||
				raExp.getNodeType() == ASTNode.ARRAY_INITIALIZER) {
				 
				IVariableBinding leftExpB = AST_Parser.getVariableBinding(laExp);
			
				AST_Parser.addFieldAccessExp(_method,leftExpB,GlobalVariables.WRITE,expType,AST_Parser.ifReturnedField(laExp),qualObj);	
		 	
				if(raExp.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION){
				
					ClassInstanceCreation m = (ClassInstanceCreation) raExp;
				
					IMethodBinding  smb = m.resolveConstructorBinding();
					
					if(smb.getDeclaringClass().isParameterizedType()){
			   	   		
				   		smb = smb.getMethodDeclaration();
				   
				   	 }
			 
					if(smb != null){ 	
				  
						if(AST_Parser.ifUserDefinedMethod(smb)){ // if it is a user defined method
					
							if(smb.isConstructor() || smb.isDefaultConstructor()){
						
								E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(m));
					
								MethodDeclaration mDec = AST_Parser.getMethodDeclaration(smb);
					
						//qualObj = AST_Parser.getQualifyingObject(mDec);
						
								E_MRefField field = AST_Parser.addQualObjofInvokMethod(laExp, pMethod, m.getNodeType());// add receiver object
							 
							   if(field != null){
								/*qualObj = field.getQualifyingObject();					
								if(qualObj.getName().equals("this")){
									//qualObj.setName(laExp.resolveTypeBinding().getDeclaringClass().getQualifiedName());
									qualObj.setName(leftExpB.getDeclaringClass().getQualifiedName());
								}*/
								AST_Parser.addInvokedMethodData(mDec,pMethod,qualObj); // add method data first
							}
						}
					}
				}
			} 
		}
		else if (raExp.getNodeType() == ASTNode.METHOD_INVOCATION){
			
			IVariableBinding leftExpB = AST_Parser.getVariableBinding(laExp);
			//String fName = leftExpB.getName().toString();
			//System.out.println("Array Name = "+fName);
			/*if(laExp.resolveTypeBinding().isArray()){
				
			}*/
			 //IVariableBinding leftExpB = AST_Parser.getVariableBinding(laExp);
			 IMethodBinding imb = getMethodInvocBinding(raExp);
			 if(imb != null){
				    MethodInvocation m = (MethodInvocation) raExp;
				     if(AST_Parser.ifUserDefinedMethod(imb)){ // if it is a user defined method
						  E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(m));
						  MethodDeclaration mDec = AST_Parser.getMethodDeclaration(imb);
						  AST_Parser.addInvokedMethodData(mDec,pMethod,qualObj);
				     }
				    if(imb.getReturnType() != null){
					   if(ifReferenceType(imb.getReturnType())){
						 E_MRefField field = getReturnField(imb);	 
							 if(field != null){
								AST_Parser.addAddressFlowExp(leftExpB, raExp,_method);
							 }
					}
					else{
						  /*if(qualObj.getName().equals("this")){
							if(leftExpB.getDeclaringClass() != null){
							   qualObj.setName(leftExpB.getDeclaringClass().getQualifiedName());
							}
						  }*/
					    AST_Parser.addFieldAccessExp(_method,leftExpB,GlobalVariables.WRITE,expType,AST_Parser.ifReturnedField(laExp),qualObj);	
					}
			 }
			 AST_Parser.addFieldAccessExp(_method,leftExpB,GlobalVariables.WRITE,expType,AST_Parser.ifReturnedField(laExp),qualObj);	
		  }
		}
		else if(raExp.resolveTypeBinding().isPrimitive() || raExp.getNodeType() == ASTNode.NUMBER_LITERAL){
			IVariableBinding leftExpB = AST_Parser.getVariableBinding(laExp);	
			AST_Parser.addFieldAccessExp(_method,leftExpB,GlobalVariables.WRITE,expType,AST_Parser.ifReturnedField(laExp), qualObj);	
		}
		else{				
				List<ASTNode> r_children = AST_Parser.getChildren(raExp);
				
				for (ASTNode child: r_children) {
			
					  if (child.getNodeType() == ASTNode.FIELD_ACCESS || child.getNodeType() == ASTNode.QUALIFIED_NAME 
							|| child.getNodeType() == ASTNode.SIMPLE_NAME ||child.getNodeType() == ASTNode.ARRAY_ACCESS
							||child.getNodeType() == ASTNode.NUMBER_LITERAL){
							//||child.resolveTypeBinding().isPrimitive()) {
					
						  Expression child_exp = (Expression) child; 	
						
						 AST_Parser.addAssignmentStatem(laExp,child_exp,_method);
					}
				}
		 }
	  	 
	}
	 if (raExp == null || raExp.getNodeType() == ASTNode.NULL_LITERAL){		
		
		 IVariableBinding leftExpB = AST_Parser.getVariableBinding(laExp);
		
		 if(ifFieldVariable(leftExpB)){
			E_MRefField  field  = addField(_method,leftExpB.getName(),leftExpB.getType().getQualifiedName().toString(),leftExpB.getDeclaringClass().getQualifiedName(),
						GlobalVariables.WRITE,ASTNode.NULL_LITERAL,false,qualObj,leftExpB.isField(),leftExpB.isParameter());	
			updateFieldAliases(field,GlobalVariables.WRITE);
			
		}
		else if (ifLocalVariable(leftExpB)){
			
		}
		else if (ifParameterVariable(leftExpB)){
			
			//SimpleName s = (SimpleName) leftExpB;
			SimpleName s = (SimpleName) laExp;
			
			AST_Parser.addSimpleName(_method, s, GlobalVariables.WRITE,ASTNode.NULL_LITERAL,qualObj);
			
			E_MRefField field = null;
			
			E_MRefParameter param  = searchRefParameter(leftExpB);
			
			if(param!=null)
				if(param.isField()){
					field = searchRefParameterField(leftExpB);
				}
				updateParamAliases(param,GlobalVariables.WRITE);
				if(field!=null){
					updateFieldAliases(field,GlobalVariables.WRITE);
				}
			}
		
	}		
	
	
	}
	public static E_Object getExpressionObject(Expression sourceExp,E_Method _method){
	
	 E_Object obj = new E_Object();
	
	 if(sourceExp.getNodeType() == ASTNode.FIELD_ACCESS){
		 FieldAccess exp = (FieldAccess) sourceExp;
		 obj = AST_Parser.getFieldAccessQualObj(exp, _method);
	 }
	 else if (sourceExp.getNodeType() == ASTNode.QUALIFIED_NAME){
		 QualifiedName exp = (QualifiedName) sourceExp;
		 obj = AST_Parser.getQualFieldAccessQualObj(exp, _method);
	 }
	 else if (sourceExp.getNodeType() == ASTNode.SIMPLE_NAME){
		 SimpleName exp = (SimpleName) sourceExp;
		 //obj = AST_Parser.getMQualifyingObject(_method);
	 }
	 else{
		
		 List<ASTNode> r_children = AST_Parser.getChildren(sourceExp);
			
			for (ASTNode child: r_children) {
				
				Expression child_exp = (Expression) child; 
				
				  if (child_exp.getNodeType() == ASTNode.FIELD_ACCESS || child_exp.getNodeType() == ASTNode.QUALIFIED_NAME 
						|| child_exp.getNodeType() == ASTNode.SIMPLE_NAME) {
				
					 AST_Parser.getExpressionObject(child_exp,_method);
				}
			}
	 }
	return obj;
}
	
	public static void parserReturn_NewObjectCreations(Expression laExp, Expression raExp){
	
	  if(raExp.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION){
	
		  ClassInstanceCreation m = (ClassInstanceCreation) raExp;
		  
		  IMethodBinding  smb = m.resolveConstructorBinding();
		
		  if(smb.getDeclaringClass().isParameterizedType()){
   	   		
	   			//System.out.println("method name = "+ smb.getMethodDeclaration().getName());
	   			
	   			smb = smb.getMethodDeclaration();
	   
	   	 }
		  E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(m));// invoked method name
			
		  int NodeType = m.getNodeType();
		  
		  parseRet_newObjectCreation(laExp, smb, NodeType,  pMethod);
	  }
	  
}

	public static void parseRet_newObjectCreation(Expression laExp, IMethodBinding smb,int NodeType, E_Method pMethod){
	  
    if(smb != null){ 	

		//if(AST_Parser.ifUserDefinedMethod(smb)){ // if it is a user defined method
	
			if(smb.isConstructor() || smb.isDefaultConstructor()){
		
				MethodDeclaration mDec = AST_Parser.getMethodDeclaration(smb);
				
				System.out.println("PMethod = "+pMethod.getName());
				
				AST_Parser.addField(pMethod,"result"," "," ","rw",ASTNode.CLASS_INSTANCE_CREATION, true, null, false, false);
				
				E_MRefField field = AST_Parser.addQualObjofInvokMethod(laExp, pMethod, NodeType);// add receiver object
			 
				//if(field != null) {
					AST_Parser.addInvokedMethodData(mDec,pMethod,null); // add method data first
				 //}
				 //else{
				//	AST_Parser.addField(pMethod,"result"," "," ","rw",ASTNode.CLASS_INSTANCE_CREATION, true, null, false, false);
				//}
			}
		//}
	  } 
	}
	public static MethodDeclaration fetchParentMethodDecl(ASTNode node){
		
		MethodDeclaration mDecl = null;
		
		List<ASTNode> Parents = AST_Parser.getParents(node);

		for (ASTNode p: Parents){
			if (p.getNodeType() == ASTNode.METHOD_DECLARATION){
				mDecl = (MethodDeclaration) p;
				break;
			}	
		}
	
		return mDecl;	
	}
	
public static IVariableBinding getObjectBinding(Expression exp){
	
	IVariableBinding qBind = null;
	
	if(exp!=null){
	 	  
		  if(AST_Parser.getObjQualifBind(exp)!=null){
			  
			  qBind = AST_Parser.getObjQualifBind(exp);
		  }
		  else{
			  if (exp.getNodeType() == ASTNode.ARRAY_ACCESS) {
			   		
				  ArrayAccess f = (ArrayAccess) exp;
				 
				  List<ASTNode> l_children = AST_Parser.getChildren(f.getArray());
					
					for (ASTNode child: l_children) {
					
						if (child.getNodeType() == ASTNode.FIELD_ACCESS || child.getNodeType() == ASTNode.SIMPLE_NAME ||
								child.getNodeType() == ASTNode.QUALIFIED_NAME) {
								Expression child_exp = (Expression) child;
							
							qBind = AST_Parser.getObjQualifBind(child_exp);
							if(qBind!=null){
								
								break;
							}
						}
					}
			}

		  }
	}
		  return qBind;
	}
public static HashMap<TypeDeclaration, MethodDeclaration> fetchMainMethodDecl(ASTNode cuNode,HashMap<TypeDeclaration, MethodDeclaration> classMethodMap){

	final TypeDeclaration[]  classNode = new TypeDeclaration[1];
	 
	final MethodDeclaration[] decl = new MethodDeclaration[1]; 
	
	cuNode.accept(new ASTVisitor() {
		@Override 
        public boolean visit(final TypeDeclaration node) { 
			
			node.accept(new ASTVisitor() {
				
				@Override 
		         public boolean visit(MethodDeclaration mNode) { 
		             
		       	  	IMethodBinding tmb = mNode.resolveBinding();
		       	  
			          	  if(tmb!=null){
				        	 
			        		  if (ifUserDefinedMethod(tmb)){
				        	   	    //System.out.println("Invoked Method = "+node.getName().toString());
				        	    	if(tmb.getName().toString().equals("main")){
				        	    	 decl[0] = mNode;
				        	    	 classNode[0] = node;
				        	    	// System.out.println("main found ="+tmb.getName());

				        	    	}
					          	}
			          
			        	  }
		    
		             return super.visit(mNode); 
		         }; 
		
			});
			
			return super.visit(node); 
		 }; 
        
	});
	
	if(decl[0]!=null && classNode[0]!=null){
		classMethodMap.put(classNode[0],decl[0]);
	}
	
			
	return classMethodMap;
		
	}

	public static E_Method fetchParentMethod(ASTNode node) {

		E_Method m = null;
		if(node != null){
		MethodDeclaration mDecl = fetchParentMethodDecl(node);
		if (mDecl != null) {
			IMethodBinding mb = getMethodBinding(mDecl);
			if (mb != null) {
				m = Data_Controller.searchMethod(mb);
			}
		}
		}
		return m;
	}
	
   public static boolean checkIfSelfAddressStatement(IVariableBinding leftExpB,IVariableBinding rightExpB ){
	  
	   boolean flag = false;
	   
	   if(leftExpB.getDeclaringMethod()!= null && rightExpB.getDeclaringMethod()!= null){
		   
		   if(leftExpB.getName().equals(rightExpB.getName()) && leftExpB.getDeclaringMethod().getName().equals(rightExpB.getDeclaringMethod().getName()))
			
			   flag = true;
   		}
   		else if(leftExpB.getDeclaringClass() != null && rightExpB.getDeclaringClass() != null ){
   		    
   			if(leftExpB.getName().equals(rightExpB.getName()) && leftExpB.getDeclaringClass().getQualifiedName().equals(rightExpB.getDeclaringClass().getQualifiedName()))  
   		    	
   				flag = true;
   		}
	   
	   return flag;
 }
  
 public static void addPointer(E_MLocalVariable pointerLocalVar,E_MRefField pointerField, E_MRefParameter  pointerParameter, E_MRefField pointeeField, E_MLocalVariable pointeeLocalVar, E_MRefParameter pointeeParameter,  String aliasOp, int eType,IVariableBinding paramBind, IMethodBinding methodBind, E_Method _method){
	  
	  E_MLocalVariable sourceVar = null;
	  
	  E_MRefField sourceField = null;
	  
	  E_MRefParameter sourceparameter = null;
	  
	  E_MRefParameter sourceParam = null;
   
   // it might be alias of a field
   sourceField = AST_Parser.ifAliasOfField(pointerLocalVar,pointerField, pointerParameter);
  
    if(sourceField  != null){
    	sourceField.setMOperation(GlobalVariables.WRITE);
	    if(removeAliasEdge(sourceField, sourceVar,sourceParam,pointerLocalVar, pointerField,pointerParameter)){
		   AST_Parser.addAliasEdge(pointerLocalVar, pointerField, pointerParameter, pointeeField, pointeeLocalVar,pointeeParameter, aliasOp, eType, paramBind,methodBind,_method);
		}
	}
    else{ 
		    sourceparameter = AST_Parser.ifAliasOfParam(pointerLocalVar,pointerField, pointerParameter,_method);
		    if(sourceparameter  != null){
		    	  
		    	 sourceparameter.setMOperation(GlobalVariables.WRITE);
		    	
		    	  if(removeAliasEdge(sourceField, sourceVar,sourceparameter,pointerLocalVar, pointerField,pointerParameter)){
					  //System.out.println("hello there");
					  AST_Parser.addAliasEdge(pointerLocalVar, pointerField, pointerParameter, pointeeField, pointeeLocalVar,pointeeParameter, aliasOp, eType, paramBind,methodBind,_method);
							
			    	}    
		    	   E_MRefParameter tempParam = ifParameterisAliasOfField(sourceVar, sourceField,sourceparameter,_method);
				     
				   if(tempParam != null){
			    		
						 if(removeAliasEdge(sourceField, sourceVar,sourceparameter,pointerLocalVar, pointerField,pointerParameter)){
							 // System.out.println("hello there");
							  AST_Parser.addAliasEdge(pointerLocalVar, pointerField, pointerParameter, pointeeField, pointeeLocalVar,pointeeParameter, aliasOp, eType, paramBind,methodBind,_method);
									
					    	}
					  }
			 
		    }      
    		//else if(sourceField == null){
		    else{// it might be alias of local variable
			    sourceVar = AST_Parser.ifAliasOfLocal(pointerLocalVar,pointerField, pointerParameter);
		        if(sourceVar != null){// These check should be in a recursive call
					 if(pointerLocalVar!=null){  
						if(removeAliasEdge(null, sourceVar,null, pointerLocalVar, null,null)){
							//System.out.println("Alias Edge of variable"+sourceVar.getName()+" removed with "+pointerLocalVar.getName());
						}
					  }
					 else if(pointerField!=null){
						if(removeAliasEdge(null, sourceVar,null, null, pointerField,null)){
							//System.out.println("Alias Edge of variable"+sourceVar.getName()+" removed with "+pointerField.getName());
						}
					 }
					 else if(pointerParameter!=null){
						 if(removeAliasEdge(null, sourceVar,null, null, null,pointerParameter)){
								//System.out.println("Alias Edge of variable"+sourceVar.getName()+" removed with "+pointerParameter.getName());
							}
						 
					 }
					  //E_MRefField tempField = ifThisIsAliasOfField(sourceVar, sourceField,sourceParam,_method);
					 // if(tempField != null){
			    		AST_Parser.addAliasEdge(pointerLocalVar,pointerField,pointerParameter, pointeeField, pointeeLocalVar,pointeeParameter,aliasOp, eType, paramBind,methodBind,_method);
					 // }
					  
		        }
		        else{
				    AST_Parser.addAliasEdge(pointerLocalVar, pointerField,pointerParameter, pointeeField, pointeeLocalVar,pointeeParameter, aliasOp, eType,paramBind,methodBind,_method);
				   }
		    }
       }
    }
	 // it might be alias of a parameter
	     /* else{
	    	
	    	 sourceParam = ifAliasOfParam(pointerLocalVar,pointerField,pointerParameter,_method);
		  
	    	if(sourceParam != null){
				     if(pointerLocalVar!=null){  
						if(removeAliasEdge(null, null,sourceParam, pointerLocalVar, null,null)){
							System.out.println("Alias Edge of PARAMTER"+sourceParam.getName()+" removed with "+pointerLocalVar.getName());
						}
					  }
					 else if(pointerField!=null){
						if(removeAliasEdge(null, null,sourceParam, null, pointerField,null)){
							System.out.println("Alias Edge of PARAMETER"+sourceParam.getName()+" removed with "+pointerField.getName());
						}
					 }
					 else if(pointerParameter!=null){
						 if(removeAliasEdge(null, null,sourceParam, null, null,pointerParameter)){
								System.out.println("Alias Edge of PARAMETER"+sourceParam.getName()+" removed with "+pointerParameter.getName());
							} 
					 }
			     //E_MRefField tempField = ifThisIsAliasOfField(sourceVar, sourceField,sourceParam,_method);
				     E_MRefParameter tempField = ifParameterisAliasOfField(sourceVar, sourceField,sourceParam,_method);
				     
					 if(tempField != null){
			    		AST_Parser.addAliasEdge(pointerLocalVar,pointerField,pointerParameter, pointeeField, pointeeLocalVar,pointeeParameter,aliasOp, eType, paramBind,methodBind,_method);
					  }
	    	} 	
		   else{
		    AST_Parser.addAliasEdge(pointerLocalVar, pointerField,pointerParameter, pointeeField, pointeeLocalVar,pointeeParameter, aliasOp, eType,paramBind,methodBind,_method);
		   }
	    }*/
 
 public static boolean checkPointee(E_MLocalVariable Var, E_MRefField field, E_MRefParameter param,E_Method method){
	  
	 boolean flag = false;
	  
	   E_MRefField sourceField = AST_Parser.ifAliasOfField(Var,field, param);
	 
	   if(sourceField  != null){
		   flag = true;
	   }
	   else if(sourceField == null){
		   	  E_MRefParameter  sourceparameter = AST_Parser.ifAliasOfParam(Var,field, param,method);
			  if(sourceparameter  != null){
				  flag = true;
			  }
		    else if (sourceparameter  == null){
			    E_MLocalVariable sourceVar = AST_Parser.ifAliasOfLocal(Var,field, param);
		        if(sourceVar != null){
		        	  E_MRefField tempField = ifThisIsAliasOfField(sourceVar, field,param,method);
					  if(tempField != null){
			    		flag = true;
					  }
					  else{
				        	return checkPointee(sourceVar,field,param,method);
				        }
		        }
		        
		   }
		   
		  
	   }
		
	 
	 return flag;
	 
 }
 public static boolean removeAliasEdge(E_MRefField pointeeField, E_MLocalVariable pointeeVar,E_MRefParameter pointeeParam,
		 E_MLocalVariable pointerLocalVar,E_MRefField pointerField,E_MRefParameter pointerParam){
	    
	 boolean flag= false;
	
	 LinkedList<E_MRefAlias>  aliases = new LinkedList<E_MRefAlias>();
	 // fetch aliases
	 if(pointeeField != null){
		 aliases = Data_Controller.fetchAliasesOfRefField(pointeeField);
	 }
	 else if (pointeeVar !=null){
		 aliases = Data_Controller.fetchAliasesOfLocalVar(pointeeVar);
	 }
	 else if(pointeeParam!=null){
		 aliases = Data_Controller.fetchAliasesOfParams(pointeeParam);
		 
	 }
	 
	// remove alias edge from pointer to pointee
	 if(aliases!=null){
		 if(pointerLocalVar!=null && pointeeField != null && !(aliases.isEmpty())){
			 flag = removeLocalAlias(pointerLocalVar,aliases);
		 }
		 else if(pointerField!=null && pointeeField != null && !(aliases.isEmpty())){
			 flag = removeFieldAlias(pointerField,aliases);
		 }
		 else if(pointerLocalVar!=null && pointeeVar != null && !(aliases.isEmpty())){
			 flag = removeLocalAlias(pointerLocalVar,aliases);
		  }
		 else if(pointerField!=null  && pointeeVar != null && !(aliases.isEmpty())){
			 flag= removeFieldAlias(pointerField,aliases);
		  }
		 else if(pointerParam!=null  && pointeeField != null && !(aliases.isEmpty())){
			 flag = removeParamAlias(pointerParam,aliases);
		  }
		 else if(pointerLocalVar!=null && pointeeParam != null && !(aliases.isEmpty())){
			 flag = removeLocalAlias(pointerLocalVar,aliases);
		 }
		 else if(pointerParam!=null && pointeeVar != null && !(aliases.isEmpty())){
			 flag = removeParamAlias(pointerParam,aliases);
		  }
		 else if(pointerField!=null && pointeeParam != null && !(aliases.isEmpty())){
			 flag = removeFieldAlias(pointerField,aliases);
		 }
		 else if(pointerParam!=null  && pointeeParam != null && !(aliases.isEmpty())){
			 flag= removeParamAlias(pointerParam,aliases);
		  }
		
		 
	 }
	 return flag;

}

 public static void addAliasEdge(E_MLocalVariable pointerLocalVar,E_MRefField pointerField,E_MRefParameter pointerParam, E_MRefField pointeeField,E_MLocalVariable pointeeLocalVar,E_MRefParameter pointeeParam, String aliasOp, int eType, IVariableBinding paramBind, IMethodBinding methodBind, E_Method _method){
	  
	  if(pointerLocalVar != null && pointeeLocalVar!=null){
		 
		  addLocalAsLocalAlias(pointeeLocalVar,pointerLocalVar,aliasOp,eType,paramBind,methodBind,_method);
	  }  
	  else if(pointerLocalVar != null && pointeeField!=null){
		 
		  addLocalAsFieldAlias(pointeeField,pointerLocalVar,aliasOp,eType,paramBind,methodBind,_method);
	  }
	  else if(pointerField != null && pointeeField!=null){
		  
		  addFieldAsFieldAlias(pointeeField,pointerField,aliasOp,eType,paramBind,methodBind,_method);
	  }
	  else if(pointerField != null && pointeeLocalVar!=null){
		   
		    addFieldAsLocalAlias(pointeeLocalVar,pointerField,aliasOp,eType,paramBind,methodBind,_method);
	  } 
	  else if(pointerParam!=null  && pointeeField != null){

		  addParamAsFieldAlias(pointeeField,pointerParam,aliasOp,eType,paramBind,methodBind,_method);
	  }
	  else if(pointerParam!=null && pointeeLocalVar != null){
		  
		  addParamAsLocalAlias(pointeeLocalVar,pointerParam,aliasOp,eType,paramBind,methodBind,_method);
	  }
	  else if(pointerParam!=null  && pointeeParam != null){
		 
		  addParamAsParamAlias(pointeeParam,pointerParam,aliasOp,eType,paramBind,methodBind,_method);
	   }
	  else if(pointerField!=null && pointeeParam != null){
		  
		  addFieldAsParamAlias(pointeeParam,pointerField,aliasOp,eType,paramBind,methodBind,_method); 
	  }
	  else if(pointerLocalVar!=null && pointeeParam != null){
		  
		  addLocalAsParamAlias(pointeeParam,pointerLocalVar,aliasOp,eType,paramBind,methodBind,_method);
	  }
  }
 	   
  public static String findDeclarationType(IVariableBinding binding){
		
	  String initDeclType="";
		
		if(binding.getDeclaringClass()!=null){
			
			initDeclType = binding.getDeclaringClass().getQualifiedName().toString();
		}
		
		else{
			
			initDeclType = binding.getDeclaringMethod().getName().toString();
		}
		return initDeclType;
	}	  
  
  
 public static E_MRefField addFieldAccess(E_Method _method, IVariableBinding rightBind, String op, int expType,E_Object obj){
 
  E_MRefField _field = null;
	
  LinkedList<E_MRefField> refList = _method.getRefVariable();

 	if(AST_Parser.ifRefExists(refList, rightBind.getName(),rightBind.getType().getQualifiedName().toString(), rightBind.getDeclaringClass().getQualifiedName().toString(),op, expType,false,obj) == false){
		
		_field = AST_Parser.addField( _method, rightBind.getName(),rightBind.getType().getQualifiedName().toString(), rightBind.getDeclaringClass().getQualifiedName().toString(),op, expType, false,obj,rightBind.isField(),rightBind.isParameter()); 
	}
	else{
		_field = Data_Controller.searchMRefField(_method, rightBind.getName(),rightBind.getType().getQualifiedName().toString(), rightBind.getDeclaringClass().getQualifiedName().toString());
	}
	return _field;	 
  }
 public static E_MLocalVariable addLocalVarAsPointee(E_Method _method, E_MLocalVariable pointeeLocalVar, int expType){
	
	 	E_MLocalVariable var = null;
	 
	     LinkedList<E_MLocalVariable> varList = _method.getLocalVar();
	  
			 	if(AST_Parser.ifMLocalVarExists(varList, pointeeLocalVar.getName(),pointeeLocalVar.getType().toString(), pointeeLocalVar.getDeclMethod().getName().toString()) == false){
					
			 		var = addMLocalVariable( _method, pointeeLocalVar,expType, false); 
			 	}
			  else{
				  var = Data_Controller.searchMLocalVariable(_method, pointeeLocalVar.getName(),pointeeLocalVar.getType().toString(), pointeeLocalVar.getDeclMethod().getName().toString());
				  
			  }
	
	 return var;	 

}
 public static E_MRefField ifThisIsAliasOfField(E_MLocalVariable localVar, E_MRefField sourceField, E_MRefParameter pointerParameter,E_Method method){
	     
		 E_MRefField pointeeField  = null;
	
		 E_MLocalVariable pointeeLocal = null;
		 
		 pointeeField = AST_Parser.ifAliasOfField(localVar, sourceField, pointerParameter);
		 
		 if(pointeeField != null)
			 
			 return pointeeField;
		 
		 else{
			
			 pointeeLocal  = AST_Parser.ifAliasOfLocal(localVar,sourceField,pointerParameter);
			 
			 if(pointeeLocal != null){
				
				 return AST_Parser.ifThisIsAliasOfField(pointeeLocal,sourceField,pointerParameter, method);
			 }
		     else
				 return null;
		 }
	     
}
 public static E_MRefParameter ifParameterisAliasOfField(E_MLocalVariable pointerLocalVar, E_MRefField pointerField, E_MRefParameter pointerParameter,E_Method method){
     
	 E_MRefParameter pointeeParam  = null;
	 
	 E_MRefField pointeefield = null;

	 E_MLocalVariable pointeeLocal = null;
	 
	 pointeeParam = AST_Parser.ifAliasOfParam(pointerLocalVar, pointerField, pointerParameter,method);
	 
	 if(pointeeParam != null)
		 
		 return pointeeParam;
		 
	 else {
		  
		     pointeeLocal  = AST_Parser.ifAliasOfLocal(pointerLocalVar,pointerField,pointerParameter);
	  
			  if(pointeeLocal != null){
					
				   pointeefield = AST_Parser.ifThisIsAliasOfField(pointeeLocal,pointerField,pointerParameter,method);
				   
				   if(pointeefield !=null){
					  
					   return ifParameterisAliasOfField(pointerLocalVar,pointerField,pointerParameter,method);
					}
				   else
					   return null;
			  }
			  else 
				  {
				    pointeefield = AST_Parser.ifAliasOfField(pointerLocalVar,pointerField,pointerParameter);
				    E_MRefField field = AST_Parser.ifThisIsAliasOfField(pointeeLocal,pointerField,pointerParameter,method);
			   
				    if(field !=null){
					   return ifParameterisAliasOfField(pointerLocalVar,pointerField,pointerParameter,method);
					}
				    else
						   return null;
				 }
	 	}	  

}
 public static MethodDeclaration getMethodDeclaration(IMethodBinding binding){
		
		//IMethodBinding binding = (IMethodBinding) node.getName().resolveBinding();
	 	
	      MethodDeclaration decl = null;
	     
	 	 if(binding.getJavaElement() != null){
	 		
	 		//List<ICompilationUnit> unit = Workspace_Utilities.collectCompilationUnits(binding.getJavaElement().getAncestor(IJavaElement.PACKAGE_FRAGMENT)); 
	 		
	 		 ICompilationUnit cunit = (ICompilationUnit) binding.getJavaElement().getAncestor( IJavaElement.COMPILATION_UNIT);
			
			/*if ( unit == null ) {
			   // not available, external declaration
			}
			if (unit != null){
				
				//final List<ICompilationUnit> compUnits = unit;

				for (ICompilationUnit cunit : compUnits) {
					*/
					if ( cunit == null ) {
						//System.out.println("Java mdoel of class "+unit.getElementName()+" = "+unit.getJavaModel().getElementName().toString());	
					}
					else{
						ASTParser parser = ASTParser.newParser(AST.JLS3);
			
						parser.setKind( ASTParser.K_COMPILATION_UNIT );
			
						parser.setSource(cunit);
			
						parser.setResolveBindings( true );
			
						CompilationUnit cu = (CompilationUnit) parser.createAST( null );
					
						if(cu.findDeclaringNode(binding.getKey())!=null){
							   if(cu.findDeclaringNode(binding.getKey()).getNodeType() == ASTNode.METHOD_DECLARATION){
						         decl = (MethodDeclaration) cu.findDeclaringNode(binding.getKey());
							  }
						      if(decl!=null){   
						    	  		if(decl.getBody()!= null){
						    	  			return decl; 
										 // break;
								      }
							  }
						}
						////
					}
					//}
			//}
		  //}
	 	 }
		return decl;
	}
 
 public static boolean isAbstract(MethodDeclaration decl){
 	boolean flag = false;
 	List<String> modifiers = new LinkedList<String>();
	modifiers = decl.modifiers();
	for(String s: modifiers){
		if(s.equals("abstract")){
			flag = true;
		}
	}
	return flag;
	}

 public static MethodDeclaration getMethodDeclaration(IJavaElement node){
		
		ICompilationUnit unit = (ICompilationUnit) node.getAncestor( IJavaElement.COMPILATION_UNIT );
		
		IMethod bind = (IMethod) node;
	
		if ( unit == null ) {
		   // not available, external declaration
		}
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		
		parser.setKind( ASTParser.K_COMPILATION_UNIT );
		
		parser.setSource(unit);
		
		parser.setResolveBindings( true );
		
		CompilationUnit cu = (CompilationUnit) parser.createAST( null );
		
		MethodDeclaration decl = (MethodDeclaration)cu.findDeclaringNode(bind.getKey());
			
		return decl;
	}
 
 public static HashMap<TypeDeclaration, MethodDeclaration> getMainDeclarationFromProject(final TypeDeclaration node){
	 
	 HashMap<TypeDeclaration, MethodDeclaration> classMethodMap = new HashMap<TypeDeclaration, MethodDeclaration>();

	 List<ICompilationUnit> temp = Workspace_Utilities.collectCompilationUnits(node.resolveBinding().getJavaElement().getAncestor(IJavaElement.PACKAGE_FRAGMENT)); 
	 
	MethodDeclaration mDecl = null;
	
	if (temp != null){
			
			final List<ICompilationUnit> compUnits = temp;

			for (ICompilationUnit cunit : compUnits) {
				
				if ( cunit == null ) {
					//System.out.println("Java mdoel of class "+unit.getElementName()+" = "+unit.getJavaModel().getElementName().toString());	
				}
				else{
					//System.out.println("Compilation Unit Names = "+ cunit.getElementName().toString());
					
					ASTParser parser = ASTParser.newParser(AST.JLS4);
					
					parser.setKind( ASTParser.K_COMPILATION_UNIT );
					
					parser.setSource(cunit);
					
					parser.setResolveBindings( true );
					
					CompilationUnit cu = (CompilationUnit) parser.createAST( null );
					
					classMethodMap = AST_Parser.fetchMainMethodDecl(cu,classMethodMap);
					
					if(classMethodMap!=null && classMethodMap.isEmpty() == false ){
						break;
					}
					
				}
			}
		}
				
				return classMethodMap;
	}

	@SuppressWarnings("null")
	public static List<MethodInvocation> getMethodInvokations(final TypeDeclaration node){
		
		List<MethodInvocation> tempInvokedMethods = new ArrayList<MethodInvocation>();
		
		List<MethodInvocation> invokedMethods = new ArrayList<MethodInvocation>();
		
		ITypeBinding binding = (ITypeBinding) node.getName().resolveBinding();
		
		//System.out.println("Java IPackage Fragment of "+snode.getName()+"="+ binding.getJavaElement().getAncestor(IJavaElement.PACKAGE_FRAGMENT).getElementName());
		
		List<ICompilationUnit> temp = Workspace_Utilities.collectCompilationUnits(binding.getJavaElement().getAncestor(IJavaElement.PACKAGE_FRAGMENT));
		//List<ICompilationUnit> temp = Workspace_Utilities.collectCompilationUnits(binding.getJavaElement());
		
		if (temp != null){
			
			final List<ICompilationUnit> compUnits = temp;

			for (ICompilationUnit cunit : compUnits) {
				if ( cunit == null ) {
					//System.out.println("Java mdoel of class "+unit.getElementName()+" = "+unit.getJavaModel().getElementName().toString());	
				}
				else{
					//System.out.println("Compilation Unit Names = "+ cunit.getElementName().toString());
					
					ASTParser parser = ASTParser.newParser(AST.JLS4);
					
					parser.setKind( ASTParser.K_COMPILATION_UNIT );
					
					parser.setSource(cunit);
					
					parser.setResolveBindings( true );
					
					CompilationUnit cu = (CompilationUnit) parser.createAST( null );
				
					tempInvokedMethods = getMethodCalls(cu,binding,tempInvokedMethods);//dgefa(a,lda,n,ipvt)
					
					for(MethodInvocation inv:tempInvokedMethods){
						invokedMethods.add(inv);
					}
					
				}
			}
		}
		
	
		return invokedMethods;
	}
	public static List<MethodInvocation> getThisMethodInvokation(final MethodDeclaration snode){
		
		List<MethodInvocation> invokedMethod = new LinkedList<MethodInvocation>();
		
		List<MethodInvocation> tempp = new LinkedList<MethodInvocation>();
		
		IMethodBinding binding = (IMethodBinding) snode.getName().resolveBinding();
		
		List<ICompilationUnit> temp = Workspace_Utilities.collectCompilationUnits(binding.getJavaElement().getAncestor(IJavaElement.PACKAGE_FRAGMENT));
		
		if (temp != null){
			
			final List<ICompilationUnit> compUnits = temp;

			for (ICompilationUnit cunit : compUnits) {
				if ( cunit == null ) {
					//System.out.println("Java mdoel of class "+unit.getElementName()+" = "+unit.getJavaModel().getElementName().toString());	
				}
				else{
					//System.out.println("Compilation Unit Names = "+ cunit.getElementName().toString());
					
					ASTParser parser = ASTParser.newParser(AST.JLS4);
					
					parser.setKind( ASTParser.K_COMPILATION_UNIT );
					
					parser.setSource(cunit);
					
					parser.setResolveBindings( true );
					
					CompilationUnit cu = (CompilationUnit) parser.createAST( null );
					
					if(cu != null){
					
						invokedMethod = getThisMethodCall(cu,binding,invokedMethod);
					
						for(MethodInvocation inv: invokedMethod){
						
							tempp.add(inv);
						}
					}
					/*if(invokedMethod!=null){
						break;
					}*/
				}
			}// ends compilation units
			
		}
		return tempp;
	}
public static List<ClassInstanceCreation> getConstructorInvokation(final MethodDeclaration snode){
		
		List<ClassInstanceCreation> invokedMethod = new LinkedList<ClassInstanceCreation>();
		
		List<ClassInstanceCreation> tempp = new LinkedList<ClassInstanceCreation>();
		
		IMethodBinding binding = (IMethodBinding) snode.getName().resolveBinding();
		
			List<ICompilationUnit> temp = Workspace_Utilities.
				
				collectCompilationUnits(binding.getJavaElement().getAncestor(IJavaElement.PACKAGE_FRAGMENT));
		
		if (temp != null){
			
			final List<ICompilationUnit> compUnits = temp;

			for (ICompilationUnit cunit : compUnits) {
				if ( cunit == null ) {
					//System.out.println("Java mdoel of class "+unit.getElementName()+" = "+unit.getJavaModel().getElementName().toString());	
				}
				else{
					//System.out.println("Compilation Unit Names = "+ cunit.getElementName().toString());
					
					ASTParser parser = ASTParser.newParser(AST.JLS4);
					
					parser.setKind( ASTParser.K_COMPILATION_UNIT );
					
					parser.setSource(cunit);
					
					parser.setResolveBindings( true );
					
					CompilationUnit cu = (CompilationUnit) parser.createAST( null );
					
					invokedMethod = getThisConsCall(cu,binding,invokedMethod);
					
					for(ClassInstanceCreation inv:invokedMethod){
						
						tempp.add(inv);
					}
					/*if(invokedMethod!=null){
						break;
					}*/
				}
			}
		}
		return tempp;
	}
	
	public static List<MethodInvocation> getMethodCalls(CompilationUnit cu, final ITypeBinding binding, List<MethodInvocation> methodCall ){
	
		//final MethodInvocation[] MethodInvocations = new MethodInvocation[1]; 
		final List<MethodInvocation> invokedMethods = new ArrayList<MethodInvocation>();
		
		cu.accept(new ASTVisitor() { 
          @Override 
          public boolean visit(MethodInvocation node) { 
              
        	  	IMethodBinding tmb = node.resolveMethodBinding();
        	  
	          	  if(tmb!=null){
	          		  if(tmb.getDeclaringClass().isParameterizedType()){
	           	   		
	    	   			//System.out.println("method name = "+ cmb.getMethodDeclaration().getName());
	    	   			
	          			tmb = tmb.getMethodDeclaration();
	    	   
	          		  }
	        		  if (ifUserDefinedMethod(tmb)){
		        	   	    //System.out.println("Invoked Method = "+node.getName().toString());
		        	    	invokedMethods.add(node);
			          	}
	          
	        	  }
     
              return super.visit(node); 
          }; 
          @Override 
          public boolean visit(SuperConstructorInvocation node) { 
             	
        	   IMethodBinding cmb = node.resolveConstructorBinding();
             	
            	MethodDeclaration pDecl = fetchParentMethodDecl(node);
          	  
  	          
	          	  if(cmb!=null){
	          		  
	          		  if(cmb.getDeclaringClass().isParameterizedType()){
	           	   		
	    	   			//System.out.println("method name = "+ cmb.getMethodDeclaration().getName());
	    	   			
	          			cmb = cmb.getMethodDeclaration();
	    	   
	          		  }
	        		  if (ifUserDefinedMethod(cmb)){
	
	        			  	 E_Object qualObj = new E_Object();
	        				
	        				 E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));

		        			  IVariableBinding qualBind = getObjQualifBind(node.getExpression());
		        			  
	        				 if(qualBind!=null){
	        					qualObj.setObjBind(qualBind);
	        				 }
	        				 else{
        						 MethodDeclaration mDecl = getMethodDeclaration(cmb);
        						 
        						 qualObj = getQualifyingObject(mDecl);
        					 }
        					 if(qualObj.getName().equals("this")){
        						qualObj.setName(cmb.getDeclaringClass().getQualifiedName().toString());
        				     }
	        			 	// fetch this method arguments
	        			  	List<Expression> argList = new LinkedList<Expression>();
	        				
	        				if(node.arguments() != null){	
	        					for(int i = 0;i < node.arguments().size();i++) {
	        						argList.add((Expression) node.arguments().get(i));
	        					}
	        				}
	        				    // add super constructor invocation expression at class and method level
	        				   AST_Parser.addMethodInvocations(cmb,argList,qualObj,pDecl);
			          	}
	          
	        	  }
              return super.visit(node); 
          }; 
          @Override 
          public boolean visit(ClassInstanceCreation node) { 
              
        	  	IMethodBinding cmb = node.resolveConstructorBinding();
        	  	
        	  	MethodDeclaration pDecl = fetchParentMethodDecl(node);
        	  
	          	  if(cmb!=null){
		        	 
	          		  if(cmb.getDeclaringClass().isParameterizedType()){
	           	   		
	    	   			//System.out.println("method name = "+ cmb.getMethodDeclaration().getName());
	    	   			
	          			cmb = cmb.getMethodDeclaration();
	    	   
	          		  }
	        		  if (ifUserDefinedMethod(cmb)){
	        			     
	        			     E_Object qualObj = new E_Object();
	        				
	        				 IVariableBinding qualBind = getObjQualifBind(node.getExpression());
	 	        			
	        				    if(qualBind!=null){
	        						qualObj.setObjBind(qualBind);
	        					 }
	        					 else{
	        						 MethodDeclaration mDecl = getMethodDeclaration(cmb);
	        						 
	        						 qualObj = getQualifyingObject(mDecl);
	        						
	        					 }
	        					 if(qualObj.getName().equals("this")){
	        						qualObj.setName(cmb.getDeclaringClass().getQualifiedName().toString());
	        				     }
	        			
	        					 // fetch this method arguments
		        			   List<Expression> argList = new LinkedList<Expression>();
		        				
		        				if(node.arguments() != null){	
		        					for(int i = 0;i < node.arguments().size();i++) {
		        						argList.add((Expression) node.arguments().get(i));
		        					}
		        				}
	        				    // add method invocation expression at class and method level
	        				
	        				AST_Parser.addMethodInvocations(cmb,argList,qualObj,pDecl);
			          	}
	          
	        	  }
     
              return super.visit(node); 
          }; 
      }); 
	  if (invokedMethods != null) 
      { 
		  methodCall = invokedMethods; 
      } 
	  return methodCall;
}
	public static List<MethodInvocation> getThisMethodCall(CompilationUnit cu, final IMethodBinding smb, List<MethodInvocation> methodCall ){
		
		//final MethodInvocation[] methodInvocation = new MethodInvocation[1]; 
		
		final List<MethodInvocation> invokedMethods = new ArrayList<MethodInvocation>();
		
		cu.accept(new ASTVisitor(){ 
          @Override 
          public boolean visit(MethodInvocation node) { 
              
        	  IMethodBinding tmb = node.resolveMethodBinding();
        	  
          	  if(tmb!=null){
	        	 
        		  if (ifUserDefinedMethod(tmb)){
        				// match method declaration with method invocation
        	        	if (tmb.getName().toString().equals(smb.getName().toString())
        	          		&& tmb.getDeclaringClass().getQualifiedName().toString().equals(smb.getDeclaringClass().getQualifiedName().toString())) { 
        	        
        	        		invokedMethods.add(node);
        	        		//methodInvocation[0] = node;
        	        	}
          
        		  	}
          	  }
              return super.visit(node); 
          }; 
      }); 
	  if (invokedMethods != null) 
      { 
		 // methodCall = methodInvocation[0]; 
		  methodCall = invokedMethods;
      } 
	return methodCall;
}
public static List<ClassInstanceCreation> getThisConsCall(CompilationUnit cu, final IMethodBinding smb, List<ClassInstanceCreation> methodCall ){
		
		//final MethodInvocation[] methodInvocation = new MethodInvocation[1]; 
		
		final List<ClassInstanceCreation> invokedMethods = new ArrayList<ClassInstanceCreation>();
		
		cu.accept(new ASTVisitor(){ 
          @Override 
          public boolean visit(ClassInstanceCreation node) { 
              
        	  IMethodBinding tmb = node.resolveConstructorBinding();
        	  
          	  if(tmb!=null){
	        	 
        		  if (ifUserDefinedMethod(tmb)){
        				// match method declaration with method invocation
        	        	if (tmb.getName().toString().equals(smb.getName().toString())
        	          		&& tmb.getDeclaringClass().getQualifiedName().toString().equals(smb.getDeclaringClass().getQualifiedName().toString())) { 
        	        
        	        		invokedMethods.add(node);
        	        		//methodInvocation[0] = node;
        	        	}
          
        		  	}
          	  }
              return super.visit(node); 
          }; 
      }); 
	  if (invokedMethods != null) 
      { 
		 // methodCall = methodInvocation[0]; 
		  methodCall = invokedMethods;
      } 
	return methodCall;
}
 public static List<MethodInvocation> getSubMethodInvocations(final MethodDeclaration mDec,List<MethodInvocation> methodCalls,E_Method _method){
			
		getSubMethodCalls(mDec, methodCalls,_method);

		return methodCalls;
	}
	
	public static void getSubMethodCalls(final MethodDeclaration mDec, final List<MethodInvocation> methodCalls,final E_Method _method){
	
	final E_Object obj = new E_Object();
	     
    final IMethodBinding mb = getMethodBinding(mDec);
		 
     if(mDec.getBody()!=null){
		
    	  mDec.getBody().accept(new ASTVisitor() { 
          
		  @Override 
          public boolean visit(MethodInvocation node) { 
  	    	
			IMethodBinding smb = node.resolveMethodBinding();
	    	
  	    	if(smb != null){ 
  	    		if(smb.getDeclaringClass().isParameterizedType()){
           	   		
    	   			//System.out.println("method name = "+ cmb.getMethodDeclaration().getName());
    	   			
  	    			smb = smb.getMethodDeclaration();
    	   
          		}
  	    		if (ifUserDefinedMethod(smb)){
    		    
  	    			methodCalls.add(node);
    		  
  	    		}
	    	}		            
          	return super.visit(node); 
          }; 
          @Override
      	 public boolean visit(SuperMethodInvocation node) {
      		
      		E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));	
      		IMethodBinding smb = node.resolveMethodBinding();
      		if(smb != null){
      			if(smb.getDeclaringClass().isParameterizedType()){
           	   		
    	   			//System.out.println("method name = "+ cmb.getMethodDeclaration().getName());
    	   			
      				smb = smb.getMethodDeclaration();
    	   
          		}
      			if(AST_Parser.ifUserDefinedMethod(smb)){ // if it is a user defined super constructor
      				List<Expression> argList = new LinkedList<Expression>();
      					if(node.arguments() != null){	
        					for(int i = 0;i < node.arguments().size();i++) {
        						argList.add((Expression) node.arguments().get(i));
        					}
        				}
        				    // add super constructor invocation expression at class and method level
        				AST_Parser.addSubMethodInvocations(smb,argList,pMethod,obj);

      			}
      		}
      			
      		return super.visit(node);
      	}
      
          @Override 
          public boolean visit(SuperConstructorInvocation node) { 
              
        	  	IMethodBinding cmb = node.resolveConstructorBinding();
        	  	
        	  	E_Method pMethod = Data_Controller.searchMethod(mDec);
         		
	          	if(cmb != null){
	          		if(cmb.getDeclaringClass().isParameterizedType()){
	           	   		
	    	   			//System.out.println("method name = "+ cmb.getMethodDeclaration().getName());
	    	   			
	    	   			cmb = cmb.getMethodDeclaration();
	    	   
	          		 }
		        	 
	        	     if (ifUserDefinedMethod(cmb)){
		        	   		
	        			    E_Object qualObj = new E_Object();
	        				
	        				// fetch this method arguments
	        			  	List<Expression> argList = new LinkedList<Expression>();
	        				
	        				if(node.arguments() != null){	
	        					for(int i = 0;i < node.arguments().size();i++) {
	        						argList.add((Expression) node.arguments().get(i));
	        					}
	        				}
	        				    // add super constructor invocation expression at class and method level
	        				AST_Parser.addSubMethodInvocations(cmb,argList,pMethod, qualObj);
			          	}
	          
	        	  }
              return super.visit(node); 
          }; 
          @Override 
          public boolean visit(ClassInstanceCreation node) { 
        	    
        	   	IMethodBinding cmb = node.resolveConstructorBinding();
        	  
	          	  if(cmb != null){
	          		  
	          		if(cmb.getDeclaringClass().isParameterizedType()){
	           	   		
	    	   			//System.out.println("method name = "+ cmb.getMethodDeclaration().getName());
	    	   			
	    	   			cmb = cmb.getMethodDeclaration();
	    	   
	          		 }
		        	 
	        		  if (ifUserDefinedMethod(cmb)){
		        	   	
	        			     E_Object qualObj = new E_Object();
	        				
	        				 //E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
	        			  // fetch this method arguments
		        		  List<Expression> argList = new LinkedList<Expression>();
		        			   
	        			   if(node.arguments() != null){	
	        					for(int i = 0;i < node.arguments().size();i++) {
	        						argList.add((Expression) node.arguments().get(i));
	        					}
	        				}
        				    // add method invocation expression at class and method level
        				   // AST_Parser.addSubMethodInvocations(cmb, argList, _method,qualObj);//commented on 19-Feb-2019
			          	}
	          
	        	  }
     
	        	 return super.visit(node); 
	          }; 
	         /* @Override 
	          public boolean visit(ArrayCreation node) { 
	        	  
	        	  E_Method _method = Data_Controller.searchMethod(mb);
	        	  
	        	  if(Data_Controller.searchClass(mb.getDeclaringClass())!=null){
	        		  System.out.println("Decalaring Class = "+Data_Controller.searchClass(mb.getDeclaringClass()).getClassQName());
	        	  }
	        	  ArrayType arrayType = node.getType();
	        	
	        	  System.out.println(arrayType.resolveBinding().getComponentType().getName().toString());
	        	  
	        	  IMethodBinding[] methodBindings = (arrayType.resolveBinding().getComponentType()).getDeclaredMethods();
	        	 
	        	  IMethodBinding tmb = null;
	        	 
	        	  for(IMethodBinding m : methodBindings){
	        		  if(m .isConstructor()||m .isDefaultConstructor()){
	        			  tmb = m;
	        		  }
	        	  }
	          	  if(tmb!=null){
		        	 
	        		  if (ifUserDefinedMethod(tmb)){
	        			  
	        			  E_Object qualObj = new E_Object();
	        			  // fetch this method arguments
		        		   List<Expression> argList = new LinkedList<Expression>();
		        			   
	        			   if(node.getInitializer()!= null){	
	        				   
	        				   //ArrayInitializer arrayInit = node.getInitializer();
	        				   //List<Expression> arguments = new LinkedList<Expression>();
	        				 
	        					//for(int i = 0;i < node.arguments().size();i++) {
	        						//argList.add((Expression) node.arguments().get(i));
	        					//}
	        				}
     				    // add method invocation expression at class and method level
     				    AST_Parser.addSubMethodInvocations(tmb, argList, _method,qualObj);
	        	       }
	          
	          	  }
	              return super.visit(node); 
	          };*/ 
     }); 
//return methodCalls;
   }
}	
	public static void parserMethodStatements(final Block node){
		if(node != null){
				 node.accept(new AST_Statment_Visitor() {
				}); 
		}
	}
	class MethodCallVisitor extends ASTVisitor {
	 
		@Override public boolean visit(MethodInvocation node)
	      { 
				//System.out.println("Method invocation ="+ node.getName() + " Kind = "+node.getNodeType());
			
				return super.visit(node); 
	      } 
	}
	class MethodStatementsVisitor extends ASTVisitor {
		 
		@Override public boolean visit(MethodInvocation node)
	      { 
				//System.out.println("Method invocation ="+ node.getName() + " Kind = "+node.getNodeType());
			
				return super.visit(node); 
	      } 
	}


	public static boolean ifUserDefinedMethod(IMethodBinding mb){
		boolean flag = false;
		if (mb.getDeclaringClass() != null){
	  		if(mb.getDeclaringClass().getTypeDeclaration().isFromSource()){
	  			flag = true;
	  		}
	  		else 
	  			flag = false;
		}
		return flag;
	}

	public static void addMethodData(MethodDeclaration node, E_Object obj){
	
		//if(node.getName().toString().equals("getResultVariable")){
		  // method parameter list
	  List<SingleVariableDeclaration> _listParameters =  new LinkedList<SingleVariableDeclaration>();
	
	  // sub method calls list inside a method
	  List<MethodInvocation> subMethods =  new LinkedList<MethodInvocation>();
	
	   //E_Class _class= Data_Controller.searchClass(node.resolveBinding().getDeclaringClass());
	
	   // create or search class	
	  E_Class _class = null;
	  
	  if(node.resolveBinding().getDeclaringClass() != null && node.resolveBinding().getDeclaringClass().isInterface()==false){
			   
		 _class = AST_Parser.createNewClass(node.resolveBinding().getDeclaringClass());
	   
	   // add class to a package
		  E_Package pkg = Data_Generator.getPackage();
	   
		  AST_Parser.addPackageClass(pkg,_class);
	  }
	  
	   //E_Class _class = Data_Controller.fetchLastClass();
	
	   //fetch or create new method 
	  
	   E_Method _method = AST_Parser.createNewMethod(node,obj);
	  
  	  //add method in  a data structure
       AST_Parser.addClassMethod(_class, _method);
    
       // fetch and add sub method calls
       subMethods = AST_Parser.getSubMethodInvocations(node,subMethods,_method);
		 
		 if(subMethods!=null && !(subMethods.isEmpty())){
			 for(MethodInvocation subM:subMethods){
				  AST_Parser.addSubMethodInvocationExp(subM);
			}
		}
	 // add Referenced parameters
	_listParameters = node.parameters();//[double a[][], int lda, int n, int ipvt[]]
		 
	 if(!(_listParameters.isEmpty())){
		 
		  List<MethodInvocation> thisMethInv = getThisMethodInvokation(node);
		  
		  if(thisMethInv!=null && thisMethInv.isEmpty() == false){
			AST_Parser.addMethodParameters(_method, node, _listParameters,thisMethInv);
		  }
		  else{
			 List<ClassInstanceCreation> thisConstInv = getConstructorInvokation(node);
			
			 if(thisConstInv!=null){
				 AST_Parser.addConstParameters(_method, node, _listParameters, thisConstInv);
			 }
		 }
	  }
	 // Parser Method Statements
	 AST_Parser.parserMethodStatements(node.getBody());
	 
	 //add sub method fields
	 E_Method sourcemethod = Data_Controller.searchMethod(node);
	 
	 if(sourcemethod != null){
	
	   AST_Parser.addSubMethodFields(sourcemethod);	
		 //AST_Parser.addSubMethodFields(_method);	
	// }
		}
	}
 public static E_MRefField addQualObjofInvokMethod(Expression exp, E_Method pMethod, int expType){
	
	E_MRefField pointeefield = null;
	
	IVariableBinding qBind = null;
	
    if(exp!= null && pMethod != null){	 	  
	  
		  if(AST_Parser.getObjQualifBind(exp)!=null){
			  
			  qBind = AST_Parser.getObjQualifBind(exp);
		  }
		  if(qBind!=null &&  pMethod!=null){
				  
			  pointeefield =  addQualObject(qBind,exp,pMethod,expType);
						
		  }//qbind ends here
		  else{
			  if (exp.getNodeType() == ASTNode.ARRAY_ACCESS) {
			   		
				  ArrayAccess f = (ArrayAccess) exp;
				 
				  List<ASTNode> l_children = AST_Parser.getChildren(f.getArray());
					
					for (ASTNode child: l_children) {
					
						if (child.getNodeType() == ASTNode.FIELD_ACCESS || child.getNodeType() == ASTNode.SIMPLE_NAME ||
								child.getNodeType() == ASTNode.QUALIFIED_NAME) {
								Expression child_exp = (Expression) child;
							
							qBind = AST_Parser.getVariableBinding(child_exp);
							if(qBind!=null){
								pointeefield = addQualObject(qBind,child_exp,pMethod,expType);
								break;
							}
						}
					}
			}
	
		  }
	  }
    else{
    	pointeefield = null;
    }
	 
 return pointeefield;

}
	
	public static E_MRefField addQualObject(IVariableBinding qBind, Expression exp, E_Method pMethod, int expType){
		
	  E_MRefField pointeefield = null;
		
	  if(qBind!=null && pMethod !=null){
		 
		if(AST_Parser.ifLocalVariable(qBind)){
	 		
			 pointeefield = null;
			
			 E_MLocalVariable localVar  = Data_Controller.searchLocalVariable(qBind.getName().toString(),qBind.getType().getQualifiedName().toString(), qBind.getDeclaringMethod().getName());
	
			 pointeefield = AST_Parser.findpointeeFieldofLocalVariable(localVar,pMethod);
			
				 if(pointeefield!=null){
					 pointeefield.setMOperation(GlobalVariables.WRITE);
					 AST_Parser.updateFieldAliases(pointeefield,GlobalVariables.WRITE);
	    			
				 }
				 
			 }
		   else if(AST_Parser.ifParameterVariable(qBind)){
		
			   		E_MRefField sourceParamField = null;
			   		
			   		E_MRefParameter sourceParam = AST_Parser.searchRefParameter(qBind);
	  		    
			   		if(sourceParam!=null){  
	      			  if(sourceParam.isField()){
	      			   sourceParamField = AST_Parser.searchRefParameterField(qBind);
	      			  }
			   
	          		  if(sourceParamField!=null){
	          			    SimpleName s = (SimpleName) exp;
	          			    AST_Parser.addSimpleName(pMethod,s, GlobalVariables.WRITE,expType,sourceParamField.getQualifyingObject());
	          			    AST_Parser.updateFieldAliases(sourceParamField,GlobalVariables.WRITE);
	          				AST_Parser.updateParamAliases(sourceParam,GlobalVariables.WRITE);
				    	  	 	
					  }
			   		}
	
	 		}
		else if(AST_Parser.ifFieldVariable(qBind)){
				
			E_Object obj = new E_Object();
	 			
				pointeefield = null;
	 			
	 			//obj = AST_Parser.getMQualifyingObject(pMethod);
	 			
	 			E_Object fieldObj = new E_Object();
	 			
	 			fieldObj.setName(obj.getName()+"."+qBind.getName());
	 			
	 			pointeefield = AST_Parser.addField(pMethod, qBind.getName().toString(), qBind.getType().getQualifiedName(), qBind.getDeclaringClass().getQualifiedName(), GlobalVariables.WRITE, expType, false,fieldObj,qBind.isField(),qBind.isParameter());
	 				
				if(pointeefield != null){
					pointeefield.setMOperation(GlobalVariables.WRITE);
					AST_Parser.updateFieldAliases(pointeefield,GlobalVariables.WRITE);
			   }	
	    	}
	  }
		return pointeefield;
	}
}
