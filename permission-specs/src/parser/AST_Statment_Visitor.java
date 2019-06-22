package parser;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import datastructure.*;

import datautilities.*;

public class AST_Statment_Visitor extends ASTVisitor {

	public AST_Statment_Visitor() {
		super();
	}
	@Override
	public void preVisit(ASTNode node) {
		super.preVisit(node);
	}
	@Override
	public void postVisit(ASTNode node) {
		super.postVisit(node);
	}
	// Class Referenced Fields Accessed here
	@SuppressWarnings("null")
	@Override
	public boolean visit(FieldAccess node) {
		Expression exp = node.getExpression();
		//E_Method _method = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
		E_Object obj = null;
		E_Method _method = AST_Parser.fetchParentMethod(node);
		if(_method!=null){
			obj = AST_Parser.getFieldAccessQualObj(node,_method);
			AST_Parser.addFieldAccessExp(_method,AST_Parser.getVariableBinding(node),AST_Parser.getFieldOperation(node),exp.getNodeType(),AST_Parser.ifReturnedField(exp),obj);
		
		}
		return super.visit(node);
	}
	@Override
	public void endVisit(FieldAccess node) {
		super.endVisit(node);
	}
	@Override
	public boolean visit(QualifiedName node) {
		
		E_Method _method = AST_Parser.fetchParentMethod(node);
		
		if(_method != null){
		
			E_Object obj = AST_Parser.getQualFieldAccessQualObj(node,_method);
			
			SimpleName qualifier = AST_Parser.getQualifier(node);
			
			if(qualifier != null ){
					
			    	IVariableBinding qBind = AST_Parser.getVariableBinding(qualifier);
			    	
			    	if(qBind != null){
					   AST_Parser.addSimpleName(_method,qualifier,GlobalVariables.READ,node.getNodeType(),obj);
		            }
			 }
			
			//AST_Parser.addFieldAccessExp(_method,AST_Parser.getVariableBinding(node),AST_Parser.getFieldOperation(node),node.getNodeType(),AST_Parser.ifReturnedField(node),obj);
		}
		return super.visit(node);
	}
	@Override
	public boolean visit(SuperFieldAccess node) {
		
		E_Method _method = AST_Parser.fetchParentMethod(node);
		
		if(_method!=null){
			
			E_Object obj = AST_Parser.getSuperFieldAccessQualObj(node, _method);
		
			AST_Parser.addFieldAccessExp(_method,AST_Parser.getVariableBinding(node), AST_Parser.getFieldOperation(node), node.getNodeType(), AST_Parser.ifReturnedField(node),obj);
		}
		return super.visit(node);
	}
	@Override
	public void endVisit(QualifiedName node) {
		super.endVisit(node);
	}
	@Override
	public boolean visit(SimpleName node) {
		
		AST_Parser.addSimleNameExp(node);
		return super.visit(node);
	}
	@Override
	public void endVisit(SimpleName node) {
		super.endVisit(node);
	}
	// Referenced Field Access Expressions End here 
	@Override
	public boolean visit(Assignment node) {
			// get left and right hand side expressions	
		Expression laExp = node.getLeftHandSide();
	
		Expression raExp = node.getRightHandSide();
		
		E_Method _method = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
	
	if(_method != null){
       
		if(laExp != null){
		   
    	   if (laExp.getNodeType() == ASTNode.FIELD_ACCESS || laExp.getNodeType() == ASTNode.SIMPLE_NAME ||
		    		laExp.getNodeType() == ASTNode.QUALIFIED_NAME){
		    		//||laExp.getNodeType() == ASTNode.ARRAY_ACCESS) {
		    	    AST_Parser.addAssignmentExpression(laExp,raExp,_method);		
			}
    	   
    	   else if(laExp.getNodeType() == ASTNode.SIMPLE_TYPE){
    		   System.out.println("SimpleType "+node.getProperty("name"));
    	   }
    	   /*else if (laExp.getNodeType() == ASTNode.ARRAY_ACCESS) {
    	   		
 			  ArrayAccess f = (ArrayAccess) laExp;
 			 
 			  List<ASTNode> l_children = AST_Parser.getChildren(f.getArray());
 				
 				for (ASTNode child: l_children) {
 					if (child.getNodeType() == ASTNode.FIELD_ACCESS || child.getNodeType() == ASTNode.SIMPLE_NAME ||
 							child.getNodeType() == ASTNode.QUALIFIED_NAME) {
 							Expression child_exp = (Expression) child;
 							AST_Parser.checkRightSide(child_exp,raExp,_method);
 					}
 				}
    	   }*/
		   else {
			
				List<ASTNode> l_children = AST_Parser.getChildren(laExp);
				
				for (ASTNode l_child: l_children) {
				
					if (l_child.getNodeType() == ASTNode.FIELD_ACCESS || l_child.getNodeType() == ASTNode.SIMPLE_NAME 
							|| l_child.getNodeType() == ASTNode.QUALIFIED_NAME){
							//||l_child.getNodeType() == ASTNode.ARRAY_ACCESS) {
				
						Expression childExp = (Expression) l_child;
						AST_Parser.addAssignmentExpression(childExp,raExp,_method);
					}
				}
			}
    	   
   		}
       // This is a special treatment for double dimension arrays
       if (laExp.getNodeType() == ASTNode.ARRAY_ACCESS) {
   		
			ArrayAccess array = (ArrayAccess) laExp;
			Expression arrayExp = array.getArray();
			AST_Parser.checkRightSide(arrayExp,raExp,_method);
			  /*List<ASTNode> l_children = AST_Parser.getChildren(laExp);
				
				for (ASTNode child: l_children) {
				
					if (child.getNodeType() == ASTNode.FIELD_ACCESS || child.getNodeType() == ASTNode.SIMPLE_NAME ||
							child.getNodeType() == ASTNode.QUALIFIED_NAME) {
						
						Expression child_exp = (Expression) child;
						
						AST_Parser.checkRightSide(child_exp,raExp,_method);
					}
				}*/
		}
	}
	       return super.visit(node);
 }
	public void endvisit(Assignment node) {
		super.endVisit(node);
	}
	// Local variables Accessed here
		@Override
	public boolean visit(VariableDeclarationStatement node) {
			
			E_Method _method = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
			
			if(_method != null){
			  
				AST_Parser.addMLocalVariables(_method, node);
			}
			
			return super.visit(node);
		}
		public void endvisit(VariableDeclarationStatement node) {
			super.endVisit(node);
		}
	@Override
	public boolean visit(MethodInvocation node) {
	
	//if(node.getName().toString().equals("indexOfHelper")){ 
		
		E_Object obj = new E_Object();
	
	    IMethodBinding smb = node.resolveMethodBinding();
	
		E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));

		if(smb != null){ 
			
			 MethodDeclaration mDec = AST_Parser.getMethodDeclaration(smb);// get method declaration of the called method to fetch its body
				
			 if(AST_Parser.ifUserDefinedMethod(smb)){ // if called method is a user defined method
			
				     AST_Parser.addInvokedMethodData(mDec,pMethod,obj); // add method data first
							
			}
		    else{// if called method is a library method
		 	 	
  			    Expression exp = node.getExpression();
  			    
  			    if(exp != null){
  				   
				   		IVariableBinding bind = AST_Parser.getVariableBinding(exp);
						
				   		if(bind != null && pMethod != null){	
							 if(exp.getNodeType() == ASTNode.FIELD_ACCESS){
								//obj = AST_Parser.getExpressionObject(exp,pMethod);
								AST_Parser.addFieldAccessExp(pMethod, bind, GlobalVariables.WRITE, exp.getNodeType(),false,obj);
							 }
						     else if(exp.getNodeType()== ASTNode.SIMPLE_NAME){
						    	SimpleName s = (SimpleName) exp;
						   	 	//obj = AST_Parser.getExpressionObject(exp,pMethod);
						   	 	if(AST_Parser.ifLocalVariable(bind)){
					  	     		 E_MRefField pointeefield = null;
									 E_MLocalVariable localVar  = Data_Controller.searchLocalVariable(
											 bind.getName().toString(),bind.getType().getQualifiedName().toString(), bind.getDeclaringMethod().getName());
									 pointeefield = AST_Parser.findpointeeFieldofLocalVariable(localVar,pMethod);
									 if(pointeefield != null){
										 //if(pointeefield.getExpType() == ASTNode.CLASS_INSTANCE_CREATION){
											  AST_Parser.addField(pMethod,"result"," "," ","rw",ASTNode.CLASS_INSTANCE_CREATION, false, null, false, false);   
										 //}
									 }
						   	 	}
						   	 	else{
						   	 		AST_Parser.addSimpleName(pMethod, s, GlobalVariables.WRITE, exp.getNodeType(),obj);
						   	 	}
						     }
						     else if(exp.getNodeType()== ASTNode.QUALIFIED_NAME){
						    	//obj = AST_Parser.getExpressionObject(exp,pMethod);
						    	AST_Parser.addFieldAccessExp(pMethod, bind, GlobalVariables.WRITE, exp.getNodeType(),false,obj);
								
						    }
						}
  			    	}
				else{
					
					List<Expression> arguments = node.arguments();
					
					for(Expression arg: arguments){
						
						IVariableBinding bind = AST_Parser.getVariableBinding(arg);
						
				   		if(bind!=null && pMethod!=null){	
							
				   			if(arg.getNodeType() == ASTNode.FIELD_ACCESS){
								obj = AST_Parser.getExpressionObject(arg,pMethod);
								AST_Parser.addFieldAccessExp(pMethod, bind, GlobalVariables.WRITE, ASTNode.CLASS_INSTANCE_CREATION, false,obj);
							 }
						     else if(arg.getNodeType() == ASTNode.SIMPLE_NAME){
						    	
						    	SimpleName s = (SimpleName) arg;
						   	 	obj = AST_Parser.getExpressionObject(arg,pMethod);
						   	 	
						    	AST_Parser.addSimpleName(pMethod, s, GlobalVariables.WRITE, ASTNode.CLASS_INSTANCE_CREATION,obj);
						     }
						     else if(arg.getNodeType() == ASTNode.QUALIFIED_NAME){
						    	obj = AST_Parser.getExpressionObject(arg,pMethod);
						    	AST_Parser.addFieldAccessExp(pMethod, bind, GlobalVariables.WRITE, ASTNode.CLASS_INSTANCE_CREATION, false,obj);
								
						     }
						     else{				
									List<ASTNode> r_children = AST_Parser.getChildren(arg);
									
									for (ASTNode child: r_children) {
								
										  if (child.getNodeType() == ASTNode.FIELD_ACCESS || child.getNodeType() == ASTNode.QUALIFIED_NAME 
												|| child.getNodeType() == ASTNode.SIMPLE_NAME){
											  Expression child_exp = (Expression) child; 	
											
											 //AST_Parser.addAssignmentStatem(laExp,child_exp,_method);
										}
									}
							 }
						}
					}
			//	}
		    }
		}
	}

		return super.visit(node);
	}
	@Override
	public boolean visit(ReturnStatement node) {
	
	E_Object obj = new E_Object();

	Expression returnExp = node.getExpression();

	Expression laExp = null;

    // find container method of this return statement
	E_Method _method = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
	
	 if(_method != null){
		 
		if(returnExp != null){
		
			if(returnExp.getNodeType() != ASTNode.NUMBER_LITERAL){
			  // if return expression is a variable binding
				  IVariableBinding binding  = AST_Parser.getVariableBinding(returnExp);
				  
				 
				  if(AST_Parser.getVariableBinding(returnExp) != null && AST_Parser.getMethodInvocBinding(returnExp) == null){
						
					    if(binding instanceof FieldAccess){
		    	 		   //obj = AST_Parser.getExpressionObject(returnExp,_method);	
		    	 		   AST_Parser.addFieldAccessExp(_method, binding, AST_Parser.getFieldOperation(returnExp),returnExp.getNodeType(), AST_Parser.ifReturnedField(node.getExpression()),obj);
		    	    	 }
		    	 	     else if(binding instanceof SimpleName){
				        	     
		    	 	    	     IVariableBinding leftExpB = AST_Parser.getVariableBinding(returnExp);
				        	     	
				        	     if(AST_Parser.ifLocalVariable(leftExpB)){
						  	     		/* E_MRefField pointeefield = null;
										 E_MLocalVariable localVar  = Data_Controller.searchLocalVariable(
												 leftExpB.getName().toString(),leftExpB.getType().getQualifiedName().toString(), leftExpB.getDeclaringMethod().getName());
										 pointeefield = AST_Parser.findpointeeFieldofLocalVariable(localVar,_method);
										 if(pointeefield != null){
											 if(pointeefield.getExpType() == ASTNode.CLASS_INSTANCE_CREATION){
												  AST_Parser.addField(_method,"result"," "," ","rw",ASTNode.CLASS_INSTANCE_CREATION, false, null, false, false);   
											 }
										 }*/
				        	     	    AST_Parser.addField(_method,"result"," "," ","rw",ASTNode.CLASS_INSTANCE_CREATION, true, null, false, false);    
						  	    	}
				        	     	else{
				        	     		//obj = AST_Parser.getExpressionObject(returnExp,_method);
				        	     		SimpleName s = (SimpleName) returnExp;
				        	     		
				        	     		AST_Parser.addSimpleName(_method, s, AST_Parser.getFieldOperation(returnExp),returnExp.getNodeType(),obj);
				        	     	}
				         	}
				         	else if(binding instanceof QualifiedName){
				         		//obj = AST_Parser.getExpressionObject(returnExp,_method);
				         		AST_Parser.addFieldAccessExp(_method, AST_Parser.getVariableBinding(returnExp), AST_Parser.getFieldOperation(returnExp),returnExp.getNodeType(), true,obj);
				 	    	}
					 }
				   else if(returnExp.getNodeType() == ASTNode.THIS_EXPRESSION){
				    	ThisExpression exp = (ThisExpression)returnExp;
				     if(exp.resolveTypeBinding()!=null){
				    	if(exp.resolveTypeBinding().isFromSource()){
				    		String declClass = 	"";
				    		if(exp.resolveTypeBinding().getDeclaringClass()!=null){
				    			declClass  = exp.resolveTypeBinding().getDeclaringClass().getQualifiedName().toString();
				    		}
				    		AST_Parser.addField(_method,"this",exp.resolveTypeBinding().getQualifiedName().toString(),declClass,"r",returnExp.getNodeType(), true, obj, true, false);  
				     	}
				      }
			    	    		
				    }
			        else if(AST_Parser.getMethodInvocBinding(returnExp) != null){
			        	
			        	 E_Method m = null;
			        	
			        	IMethodBinding methodBind = AST_Parser.getMethodInvocBinding(returnExp);
	 	            	
	 	        	   	if(methodBind != null){	
		 	        	   	
	 	        	   		if(methodBind.isConstructor() || returnExp.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION){
			 	        	   	
		 	        	   		AST_Parser.parserReturn_NewObjectCreations(laExp,returnExp);
							}
	 	        	   		else{
	 	        	 	          m = Data_Controller.searchMethod(methodBind);
			        		
	 	        	   		     if(AST_Parser.ifSourceMethod(methodBind)){
	 	        			
	 	        			 	    MethodDeclaration mDec = AST_Parser.getMethodDeclaration(methodBind);// get method declaration of the called method to fetch its body
	 	        			 	
	 	        			 	    if(returnExp.getNodeType() == ASTNode.METHOD_INVOCATION) {
	 	        						
	 			        		 	  MethodInvocation f = (MethodInvocation) returnExp;
	 			        		 	  
	 			        		 	  E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(f));
	 			        		 	
	 			        		 	//if(AST_Parser.ifReferenceType(methodBind.getReturnType())){
	 			        		 		if(methodBind.getReturnType().isArray() || !(methodBind.getReturnType().getTypeDeclaration().isPrimitive()) || methodBind.getReturnType().getTypeDeclaration().isParameterizedType()){
 	        								 	
	 			        		 		AST_Parser.addField(_method,"result"," "," ","rw",ASTNode.CLASS_INSTANCE_CREATION, true, null, false, false);
	 			        		 	}
	 			        		 	
		 			        		AST_Parser.addInvokedMethodData(mDec,pMethod,obj); // add method data first
	 			        		    
	 	        			 	   }
	 	        	   		     }
	 	        	   		     else{// if called method is a library method
	 	        		 	 	
	 	          			       Expression exp = node.getExpression();
	 	          			    
	 	          			        if(exp != null){
	 	          				   
	 	        				   		IVariableBinding bind = AST_Parser.getVariableBinding(exp);
	 	        						
	 	        				   		//E_Object obj = new E_Object();
	 	        				   		
	 	        						if(bind != null && _method != null){	
	 	        						//	 if(bind instanceof FieldAccess){
	 	        							//	obj = AST_Parser.getExpressionObject(exp,_method);
	 	        								AST_Parser.addFieldAccessExp(_method, bind, GlobalVariables.WRITE, ASTNode.CLASS_INSTANCE_CREATION, false,obj);
	 	        							 //}
	 	        						    // else if(bind instanceof SimpleName){
	 	        						    	
	 	        						    	//SimpleName s = (SimpleName) exp;
	 	        						   	 //	//obj = AST_Parser.getExpressionObject(exp,_method);
	 	        						    	//AST_Parser.addSimpleName(_method, s, GlobalVariables.WRITE, node.getNodeType(),obj);
	 	        						     //}
	 	        						     //else if(bind instanceof QualifiedName){
	 	        						    	//obj = AST_Parser.getExpressionObject(exp,_method);
	 	        						    	//AST_Parser.addFieldAccessExp(_method, bind, GlobalVariables.WRITE, node.getNodeType(), false,obj);
	 	        								
	 	        						    //}
	 	        						}
	 	        						else if(bind == null && _method != null){
	 	        							//if(AST_Parser.ifReferenceType(methodBind.getReturnType())){
	 	        								if(methodBind.getReturnType().isArray() || !(methodBind.getReturnType().getTypeDeclaration().isPrimitive())|| methodBind.getReturnType().getTypeDeclaration().isParameterizedType()){
	 	        									AST_Parser.addField(_method,"result"," "," ","rw",ASTNode.CLASS_INSTANCE_CREATION, true, null, false, false);
	 	        								}
	 	        							//}
	 	        						}
	 	          			    	}
	 	        	   		   }
	 	        	   		}
	 	        	   	}
			        	   /*else if(methodBind.isConstructor() || returnExp.getNodeType() == ASTNode.CLASS_INSTANCE_CREATION){
		    	 			 AST_Parser.parserReturn_NewObjectCreations(laExp,returnExp);
						 }*/
	 					//else{
	 					if(m != null){
	 										
		   			      E_MRefField classField = AST_Parser.getReturnField(methodBind);
							
						 // obj = AST_Parser.getMQualifyingObject(_method);
						  // if referenced field is a class field
						  if(classField != null){
							
						    //	AST_Parser.addClassField(_method, f.getName().toString(), f.getType(), f.getDeclaringClass(), GlobalVariables.READ,returnExp.getNodeType(), f.isRetFiel(),obj);
						  }
						  // if referenced field is a parameter
						  else{
							  
							   E_MRefParameter p = AST_Parser.getReturnParameter(m);
							
							   if(p != null){
								//System.out.println("returned parameter = "+p.getName());
								
								   E_MRefField paramField = Data_Controller.searchField(p.getfName(), p.getPfType(), p.getPfClass());
								
									if(paramField!=null){
										obj = paramField.getQualifyingObject();
									 }
								     AST_Parser.addClassField(_method,p.getfName(), p.getType(), p.getPfClass(), GlobalVariables.READ,returnExp.getNodeType(), p.isRetFiel(),obj,false,true);
								     //AST_Parser.addRefParameters(p.getfName(), p.getDeclaringClass(), p.getPosition(),GlobalVariables.READ , returnExp.getNodeType(),p.,p.isRetFiel());
								}
								else{
									MethodDeclaration mDecl = AST_Parser.getMethodDeclaration(methodBind);// get method declaration of the called method to fetch its body
									
									if(AST_Parser.ifReferenceType(methodBind.getReturnType())){
		 			        		 	 AST_Parser.addField(_method,"result"," "," ","rw",ASTNode.CLASS_INSTANCE_CREATION, true, null, false, false);  
		 			        		}  
			        		 		
									AST_Parser.addInvokedMethodData(mDecl,_method,obj); // add method data first
									//System.out.println("The returned field is a local variable, do nothing");
								}
						   }
					}
			      //}
	 			  }	
				 }
	             else if(returnExp.getNodeType() == ASTNode.ARRAY_CREATION || returnExp.getNodeType() == ASTNode.ARRAY_INITIALIZER) {
		 		      AST_Parser.addField(_method,"result"," "," ","rw",ASTNode.CLASS_INSTANCE_CREATION, true, null, false, false);
		         }
	 		}
		} 
	return super.visit(node);
	}
	public void endvisit(ReturnStatement node) {
		super.endVisit(node);
	}
	@Override
	public boolean visit(SuperMethodInvocation node) {
		
		E_Object obj = new E_Object();
		
		E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));	
		
		IMethodBinding smb = node.resolveMethodBinding();
		
		if(smb != null){ 
        	
			if(AST_Parser.ifUserDefinedMethod(smb)){ // if it is a user defined super constructor
				
				MethodDeclaration mDec = AST_Parser.getMethodDeclaration(smb);
				
				if(mDec!=null && pMethod!=null){
  		 	    
					AST_Parser.addInvokedMethodData(mDec,pMethod,obj); // add method data first
  		 	    }
			}
		}
			
		return super.visit(node);
	}
	@Override
	public boolean visit(SuperConstructorInvocation node) {
		
		E_Object obj = new E_Object();
		
		E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
		
		//MethodDeclaration mDec = AST_Parser.fetchParentMethodDecl(node);
	
		IMethodBinding smb = node.resolveConstructorBinding();
		
		if(smb != null){ 
        	
			if(AST_Parser.ifUserDefinedMethod(smb)){ // if it is a user defined super constructor
				
				MethodDeclaration mDec = AST_Parser.getMethodDeclaration(smb);
				
				obj = AST_Parser.getQualifyingObject(mDec);
				
				//E_MRefField field = AST_Parser.addQualObjofInvokMethod(node.getExpression(),pMethod,node.getNodeType());	// add receiver object of invoked method
  		 	   
  		 	   // if(field!=null){
					//obj = field.getQualifyingObject();
				//}
  		 	    if(mDec!=null && pMethod!=null){
  		 	    	AST_Parser.addInvokedMethodData(mDec,pMethod,obj); // add method data first
  		 	    }
  		 	   
			}
			else{// if it is a library method call
			 	 
	  			   Expression exp = node.getExpression();
				 
	  			   if(exp != null ){
					 
					     IVariableBinding bind = AST_Parser.getVariableBinding(exp);
							
					    if(bind!=null && pMethod!=null){	
					    	
							 if(exp.getNodeType() == ASTNode.FIELD_ACCESS){
							
								obj =  AST_Parser.getExpressionObject(exp,pMethod);
								
								AST_Parser.addFieldAccessExp(pMethod, bind, GlobalVariables.WRITE, node.getNodeType(), false,obj);
							
							 }
						     else if(exp.getNodeType() == ASTNode.SIMPLE_NAME){
						    	obj =  AST_Parser.getExpressionObject(exp,pMethod);	
						    	
						    	SimpleName s = (SimpleName) exp;
						    	
						    	AST_Parser.addSimpleName(pMethod, s, GlobalVariables.WRITE, node.getNodeType(),obj);
						     }
						     else if(exp.getNodeType() == ASTNode.QUALIFIED_NAME){
						    	 obj =  AST_Parser.getExpressionObject(exp,pMethod);	
						    	AST_Parser.addFieldAccessExp(pMethod, bind, GlobalVariables.WRITE, node.getNodeType(), false,obj);
								
						    }
					    }
	  			   }
			   }
		}
	
		return super.visit(node);
	}
	public boolean visit(ClassInstanceCreation node) {
		  
/*		  IMethodBinding cmb = node.resolveConstructorBinding();

  	      if(cmb!=null){
    	 
		      if (AST_Parser.ifUserDefinedMethod(cmb)){
		    	 // System.out.println("Constructor of class "+node.getType()+" is called");
				   
				  	
		    	 IVariableBinding bind = AST_Parser.getObjQualifBind(node.getExpression());
			 	// fetch this method arguments
			       List<Expression> argList = new LinkedList<Expression>();
				
					if(node.arguments() != null){	
						for(int i = 0;i < node.arguments().size();i++) {
							argList.add((Expression) node.arguments().get(i));
						}
					}
				    // add method invocation expression at class and method level
				    AST_Parser.addMethodInvocations(cmb,argList,bind);
          	   }
		      else{
		    	  
		    	  IMethodBinding bind = AST_Parser.getMethodInvocBinding(node);
		    	  
		    	//  System.out.println("Get Class Instance Creation Binding for library method = "+bind.getJavaElement().getElementName()+" is Constructor = "+bind.isConstructor()+" Node Type = "+node.getNodeType());
				//	 
		      }
  
	     }
		E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
		
		IMethodBinding smb = node.resolveConstructorBinding();
		
		E_Object obj = new E_Object();
		
		if(smb != null){ 
        	
			if(AST_Parser.ifUserDefinedMethod(smb)){ // if it is a user defined super constructor
				
				MethodDeclaration mDec = AST_Parser.getMethodDeclaration(smb);
				
				if(mDec!=null){
					
					obj = AST_Parser.getQualifyingObject(mDec);
					
					//E_MRefField field = AST_Parser.addQualObjofInvokMethod(node.getExpression(),pMethod,node.getNodeType());	// add receiver object of invoked method
					
					//if(field!=null){
						//obj = field.getQualifyingObject();
					//}
					if(mDec!=null && pMethod!=null){
						AST_Parser.addInvokedMethodData(mDec,pMethod,obj); // add method data first
					}
				}
  		 	   
			}
		}*/
	
		return super.visit(node);
    }

	public void endvisit(ClassInstanceCreation node) {
		super.endVisit(node);
	}
	
	/*@Override 
    public boolean visit(ArrayCreation node) { 
  	  
       ArrayType arrayType = node.getType();
  	  
  	  IMethodBinding[] methodBindings = arrayType.resolveBind ing().getDeclaredMethods();
  	 
  	  IMethodBinding tmb = null;
  	 
  	  for(IMethodBinding m : methodBindings){
  		  if(m .isConstructor()||m .isDefaultConstructor()){
  			  tmb = m;
  		  }
  	  }
  	  E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
  	
      if(tmb!=null){  
    		
  		  if (AST_Parser.ifUserDefinedMethod(tmb)){
  			  
  			  MethodDeclaration mDec = AST_Parser.getMethodDeclaration(tmb);
  			  
  			  System.out.println("Decalaring Class = "+Data_Controller.searchClass(tmb.getDeclaringClass()));
	        	
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
  			   if(pMethod!=null){
		 	    //AST_Parser.addInvokedMethodData(mDec,pMethod,qualObj); // add method data first
  			   
			     AST_Parser.addSubMethodInvocations(tmb, argList, pMethod,qualObj);
  			   }
  	       }
    
    	  }
        return super.visit(node); 
     }

	public void endvisit(ArrayCreation node) {
		super.endVisit(node);
	}
*/
	@Override
	public void endVisit(TypeDeclaration node) {
		super.endVisit(node);
	}
	
	@Override
	public void endVisit(PackageDeclaration node) {
		super.endVisit(node);
	}
	public void endvisit(SuperMethodInvocation node) {
		super.endVisit(node);
	}


	public void endvisit(MethodInvocation node) {
		super.endVisit(node);
	}

	public void endvisit(SuperConstructorInvocation node) {
		super.endVisit(node);
	}

	@Override
	public void endVisit(MethodDeclaration node) {
		super.endVisit(node);
	}


}