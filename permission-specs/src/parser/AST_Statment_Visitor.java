package parser;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
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
	// Local variables Accessed here
	@Override
	 public boolean visit(VariableDeclarationStatement node) {
		
		E_Method _method = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
		
		if(_method!=null){
		  
			AST_Parser.addMLocalVariables(_method, node);
		}
		
		return super.visit(node);
	}
	public void endvisit(VariableDeclarationStatement node) {
		super.endVisit(node);
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
		
		if(_method!=null){
		
			E_Object obj = AST_Parser.getQualFieldAccessQualObj(node,_method);
			
			SimpleName qualifier = AST_Parser.getQualifier(node);
			
			if(qualifier!=null){
					
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
							|| l_child.getNodeType() == ASTNode.QUALIFIED_NAME
							||l_child.getNodeType() == ASTNode.ARRAY_ACCESS) {
				
						Expression childExp = (Expression) l_child;
				
						AST_Parser.addAssignmentExpression(childExp,raExp,_method);
					}
				}
			}
    	   
   		}
       // This is a special treatment for double dimension arrays
       if (laExp.getNodeType() == ASTNode.ARRAY_ACCESS) {
   		
			  ArrayAccess f = (ArrayAccess) laExp;
			 
			  List<ASTNode> l_children = AST_Parser.getChildren(f.getArray());
				
				for (ASTNode child: l_children) {
				
					if (child.getNodeType() == ASTNode.FIELD_ACCESS || child.getNodeType() == ASTNode.SIMPLE_NAME ||
							child.getNodeType() == ASTNode.QUALIFIED_NAME) {
						
						Expression child_exp = (Expression) child;
						
						AST_Parser.checkRightSide(child_exp,raExp,_method);
					}
				}
		}
	}
	       return super.visit(node);
 }
	public void endvisit(Assignment node) {
		super.endVisit(node);
	}
	
	@Override
	public boolean visit(ReturnStatement node) {
	
	E_Object obj = new E_Object();

	Expression returnExp = node.getExpression();

    // find container method of this return statement
	E_Method _method = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
	
	if(_method != null){
	
		  IVariableBinding qualBind  = AST_Parser.getObjQualifBind(returnExp);
		
		   if(returnExp != null && returnExp.getNodeType() != ASTNode.NUMBER_LITERAL){
		  
			  // if return expression is a variable binding
	          if(AST_Parser.getVariableBinding(returnExp) != null){
	    	
	    	 	 if(returnExp.getNodeType() == ASTNode.FIELD_ACCESS){
		    	   
	    	 		obj = AST_Parser.getExpressionObject(returnExp,_method);	
		    	    AST_Parser.addFieldAccessExp(_method, AST_Parser.getVariableBinding(returnExp), AST_Parser.getFieldOperation(returnExp),returnExp.getNodeType(), AST_Parser.ifReturnedField(node.getExpression()),obj);
		
				 }
			     else if(returnExp.getNodeType() == ASTNode.SIMPLE_NAME){
			    	 obj = AST_Parser.getExpressionObject(returnExp,_method);
			    	 SimpleName s = (SimpleName) returnExp;
			    	 AST_Parser.addSimpleName(_method, s, AST_Parser.getFieldOperation(returnExp),returnExp.getNodeType(),obj);
			     }
			     else if(returnExp.getNodeType() == ASTNode.QUALIFIED_NAME){
			    	 obj = AST_Parser.getExpressionObject(returnExp,_method);
			    	 AST_Parser.addFieldAccessExp(_method, AST_Parser.getVariableBinding(returnExp), AST_Parser.getFieldOperation(returnExp),returnExp.getNodeType(), AST_Parser.ifReturnedField(node.getExpression()),obj);
			 			
			    }
	    	 
	    	 
	    	 //obj = getExpressionObject(returnExp,_method);
		    	
			 //AST_Parser.addFieldAccessExp(_method, AST_Parser.getVariableBinding(returnExp), AST_Parser.getFieldOperation(returnExp),returnExp.getNodeType(), AST_Parser.ifReturnedField(node.getExpression()),obj);
		     }
	        // if return expression is a method call
	         else if(AST_Parser.getMethodInvocBinding(returnExp)!=null){
	    	   
	    	 	IMethodBinding methodBind = AST_Parser.getMethodInvocBinding(returnExp);
	    	 	
	    	 	 if(methodBind != null){
					
	    	 		if(AST_Parser.ifSourceMethod(methodBind)){
	    	 			  
	    	 			 E_Method m = Data_Controller.searchMethod(methodBind);
					       	  
		    	 		  if(methodBind.isConstructor()){
								  
						  }
						  else{
							    
							  if(m != null){
						
				   			      E_MRefField classField = AST_Parser.getReturnField(methodBind);
									
								 // obj = AST_Parser.getMQualifyingObject(_method);
								  // if referenced field is a class field
								  if(classField!= null){
									
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
											//System.out.println("The returned field is a local variable, do nothing");
										}
								   }
							}
						}
				    }
	    	 	 }
						
			}
	    	  
		}
	}
	return super.visit(node);
	}
	
	
	public void endvisit(ReturnStatement node) {
		super.endVisit(node);
	}
	@Override
	public boolean visit(MethodInvocation node) {
	
		E_Object obj = new E_Object();
	
	    IMethodBinding smb = node.resolveMethodBinding();
	
		E_Method pMethod = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));

		if(smb != null){ 
			
			 MethodDeclaration mDec = AST_Parser.getMethodDeclaration(smb);// get method declaration of the called method to fetch its body
				
			if(AST_Parser.ifUserDefinedMethod(smb)){ // if called method is a user defined method
			
				 //IVariableBinding qBind = AST_Parser.getObjectBinding(node.getExpression());
				 
				 //obj = AST_Parser.getQualifyingObject(mDec);
				
				//E_MRefField field = AST_Parser.addQualObjofInvokMethod(node.getExpression(), pMethod, node.getNodeType());// add receiver object
		
				//if(field!=null){
				
				//	obj = field.getQualifyingObject();
				//}
				
			    AST_Parser.addInvokedMethodData(mDec,pMethod,obj); // add method data first
							
			}
			
		    else{// if called method is a library method
		 	 	
  			    Expression exp = node.getExpression();
  			    
  			    if(exp != null){
  				   
				   		//System.out.println("method expresion "+exp.toString());
				     	
				   		IVariableBinding bind = AST_Parser.getVariableBinding(exp);
						
				   		if(bind != null && pMethod != null){	
							 if(exp.getNodeType() == ASTNode.FIELD_ACCESS){
								obj = AST_Parser.getExpressionObject(exp,pMethod);
								AST_Parser.addFieldAccessExp(pMethod, bind, GlobalVariables.WRITE, node.getNodeType(), false,obj);
							 }
						     else if(exp.getNodeType() == ASTNode.SIMPLE_NAME){
						    	
						    	SimpleName s = (SimpleName) exp;
						   	 	obj = AST_Parser.getExpressionObject(exp,pMethod);
						    	AST_Parser.addSimpleName(pMethod, s, GlobalVariables.WRITE, node.getNodeType(),obj);
						     }
						     else if(exp.getNodeType() == ASTNode.QUALIFIED_NAME){
						    	obj = AST_Parser.getExpressionObject(exp,pMethod);
						    	AST_Parser.addFieldAccessExp(pMethod, bind, GlobalVariables.WRITE, node.getNodeType(), false,obj);
								
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
								AST_Parser.addFieldAccessExp(pMethod, bind, GlobalVariables.WRITE, node.getNodeType(), false,obj);
							 }
						     else if(arg.getNodeType() == ASTNode.SIMPLE_NAME){
						    	
						    	SimpleName s = (SimpleName) arg;
						   	 	obj = AST_Parser.getExpressionObject(arg,pMethod);
						    	AST_Parser.addSimpleName(pMethod, s, GlobalVariables.WRITE, node.getNodeType(),obj);
						     }
						     else if(arg.getNodeType() == ASTNode.QUALIFIED_NAME){
						    	obj = AST_Parser.getExpressionObject(arg,pMethod);
						    	AST_Parser.addFieldAccessExp(pMethod, bind, GlobalVariables.WRITE, node.getNodeType(), false,obj);
								
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
				}
		    }
		}
	//}

		return super.visit(node);
	}
	@Override
	public boolean visit(EnhancedForStatement node) {

		//System.out.println("expression = "+node.getExpression().toString());
		//System.out.println("Expression binding = "+AST_Parser.getVariableBinding(node.getExpression()).isField());
		//System.out.println("parameters = "+node.getParameter().getName().toString());
		
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
				 
	  			   if(exp!=null ){
					 
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
  	  
  	  IMethodBinding[] methodBindings = arrayType.resolveBinding().getDeclaredMethods();
  	 
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
	@Override
	public boolean visit(NormalAnnotation node) {
		return super.visit(node);
	}

	@Override
	public void endVisit(NormalAnnotation node) {
		super.endVisit(node);
	}

	@Override
	public boolean visit(SingleMemberAnnotation node) {
		return super.visit(node);
	}

	@Override
	public void endVisit(SingleMemberAnnotation node) {
		super.endVisit(node);
	}

	@Override
	public boolean visit(ExpressionStatement node) {

		return super.visit(node);
	}

	public void endvisit(ExpressionStatement node) {
		super.endVisit(node);
	}

	@Override
	public boolean visit(Block node) {
		return super.visit(node);
	}

	@Override
	public void endVisit(Block node) {

		super.endVisit(node);
	}

	@Override
	public boolean visit(SingleVariableDeclaration node) {
		return super.visit(node);
	}
	public boolean visit(InfixExpression node) {
		return super.visit(node);
	}

	public void endvisit(InfixExpression node) {
		super.endVisit(node);
	}

	public boolean visit(AnonymousClassDeclaration node) {
		return super.visit(node);
	}

	public void endvisit(AnonymousClassDeclaration node) {
		super.endVisit(node);
	}

	public boolean visit(ArrayInitializer node) {
		return super.visit(node);
	}

	public void endvisit(ArrayInitializer node) {
		super.endVisit(node);
	}

	public boolean visit(ArrayType node) {
		return super.visit(node);
	}

	public void endvisit(ArrayType node) {
		super.endVisit(node);
	}

	public boolean visit(AssertStatement node) {
		return super.visit(node);
	}

	public void endvisit(AssertStatement node) {
		super.endVisit(node);
	}

	public boolean visit(BreakStatement node) {
		return super.visit(node);
	}

	public void endvisit(BreakStatement node) {
		super.endVisit(node);
	}

	public boolean visit(TypeDeclarationStatement node) {
		return super.visit(node);
	}

	public void endvisit(TypeDeclarationStatement node) {
		super.endVisit(node);
	}

	@Override
	public boolean visit(Modifier node) {
		return super.visit(node);
	}

	@Override
	public void endVisit(Modifier node) {
		super.endVisit(node);
	}

	public void endvisit(MethodInvocation node) {
		super.endVisit(node);
	}

	@Override
	public boolean visit(TypeParameter node) {
		// System.out.println("TypeParameter called ");
		return super.visit(node);
	}

	public void endvisit(TypeParameter node) {
		super.endVisit(node);
	}
	public void endvisit(SuperConstructorInvocation node) {
		
	
		super.endVisit(node);
	}
	@Override
	public boolean visit(ConstructorInvocation node) {
		//System.out.println("Costructor of class "+node.getClass().getName()+"called");
		return super.visit(node);
	}

	@Override
	public void endVisit(ConstructorInvocation node) {
		super.endVisit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		
		return super.visit(node);
	}
	@Override
	public void endVisit(FieldDeclaration node) {
		super.endVisit(node);
	}
	public boolean visit(Initializer node){
		
		return super.visit(node);
	}
	public void endVisit(Initializer node){
		 super.endVisit(node);	
	}
	@Override
	public void endVisit(MethodDeclaration node) {
		super.endVisit(node);
	}
	@Override
	public boolean visit(VariableDeclarationFragment node) {
		return super.visit(node);
	}
	
	@Override
	public void endVisit(VariableDeclarationFragment node) {
		super.endVisit(node);
	}
	@Override
	public boolean visit(ThisExpression node) {
		return super.visit(node);
	}
	public void endVisit(ThisExpression node) {
		super.endVisit(node);
	}
	@Override
	public boolean visit(ArrayAccess node) {
		return super.visit(node);
	}
	public void endvisit(ArrayAccess node) {
		super.endVisit(node);
	}

}