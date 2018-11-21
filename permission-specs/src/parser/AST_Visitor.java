package parser;
import datastructure.*;

import datautilities.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.IfStatement;
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
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.sun.org.apache.xml.internal.serialize.Method;


 public class AST_Visitor extends ASTVisitor {

	public AST_Visitor() {
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
	// create new package
	@Override
	public boolean visit(PackageDeclaration node) {
		
	  E_Package pack = Data_Generator.getPackage();
	  
	  pack.setName(node.getName().toString());
				 
	  return super.visit(node);
	}

	// create class or interface
	@Override
	public boolean visit(TypeDeclaration node) {
		
		//create new class
		//ITypeBinding classBind = node.resolveBinding().getTypeDeclaration();
			
	//	if(node.resolveBinding().getName().toString().equals("CallAppDemo")){
		
		E_Class _class = AST_Parser.createNewClass(node.resolveBinding());
		
		// add class to a package
		AST_Parser.addPackageClass(Data_Generator.getPackage(),_class);
		
		//Data_Generator.getPackage().getClasses().addLast(_class);
		E_Object obj = new E_Object();
		
		E_Method constructor = AST_Parser.createDefaultConstructor(node,obj);
	
		AST_Parser.addClassMethod(_class, constructor);
		
		AST_Parser.addPackageClass(Data_Generator.getPackage(),_class);
		
		// fetch main method and add initalized field of the class in it 
		
		/*E_Class _mainClass = null;

		MethodDeclaration mainDecl = null;

		TypeDeclaration mainClass = null;
		
		E_Method mainMethod = null;*/
		
		/*HashMap<TypeDeclaration, MethodDeclaration> mainMap = AST_Parser.getMainDeclarationFromProject(node);
	
		if(mainMap!=null && mainMap.isEmpty() == false){
			
			Set mapSet = (Set) mainMap.entrySet();
			
			Iterator mapIterator = mapSet.iterator();
			
			while (mapIterator.hasNext()) {
	            Map.Entry mapEntry = (Map.Entry) mapIterator.next();
	            // getKey Method of HashMap access a key of map
	            mainClass = (TypeDeclaration) mapEntry.getKey();
	            //getValue method returns corresponding key's value
	            mainDecl = (MethodDeclaration) mapEntry.getValue();
	            //System.out.println("Key : " + mainClass.getName().toString() + "= Value : " + mainDecl.getName());
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
		if(mainDecl!=null){
		
			E_Method m = Data_Controller.searchMethod(mainDecl);
			
			if(m!=null){ 
					  
					if(m.getQualifyingObject().equals(obj)){
						mainMethod = m;
				  }
				 else{
					 mainMethod = m;
					 mainMethod.setQualifyingObject(obj);
				 }	
					  
			  }
			 else{
				 mainMethod =  AST_Parser.createNewMethod(mainDecl,obj);	
			  }
		}*/

		// get Class fields
		FieldDeclaration[] fields = node.getFields();
		
		// create a Field data structure and add field in a class
		AST_Parser.addClassFields(_class,fields,constructor);
		
		/*if(mainClass!=null){

			_mainClass = AST_Parser.createNewClass(mainClass.resolveBinding());
			
			if(_mainClass!=null){
				//Data_Generator.getPackage().getClasses().addLast(_mainClass);
				AST_Parser.addPackageClass(Data_Generator.getPackage(),_mainClass);
				AST_Parser.addClassMethod(_mainClass, mainMethod);
			}
		}*/
		
		//fetch all method invocations in a class
		  
		List<MethodInvocation> invokedMethods = AST_Parser.getMethodInvokations(node);
		
		 if(!(invokedMethods.isEmpty())){
			 for(MethodInvocation inv:invokedMethods){ 
				 //System.out.println("Method inv = "+inv.getName()+" for method"+node.getName());
				 AST_Parser.addMethodInvocationExp(inv);
				 
			 }
		 }
		 //}
		return super.visit(node);
	}
	@Override
	public boolean visit(MethodDeclaration node) {
		
		//System.out.println("In method = "+node.getName().toString());
		
		E_Object obj = new E_Object();
		
	    //obj = AST_Parser.getQualifyingObject(node);
	   
		if(node.resolveBinding()!=null && node.getBody() != null){
		
			AST_Parser.addMethodData(node,obj);
		}
	//}
	return super.visit(node);
		    
	}
	@Override
	public boolean visit(final Initializer bNode) {
		
		//TypeDeclaration initClass = null;
		
		//E_Method method = null;
		
		if(bNode.getParent().getNodeType() == ASTNode.TYPE_DECLARATION) {
			
			bNode.getBody().accept(new ASTVisitor() {
					
		      @Override 
		      public boolean visit(Assignment node) { 
					
					Expression laExp = node.getLeftHandSide();
					
					Expression raExp = node.getRightHandSide();
					
					//E_Method _method = Data_Controller.searchMethod(AST_Parser.fetchParentMethodDecl(node));
					
					//E_Class _mainClass = null;

					//HashMap<TypeDeclaration, MethodDeclaration> mainMap = AST_Parser.getMainDeclarationFromProject((TypeDeclaration)bNode.getParent());
				
					/*f(mainMap != null && mainMap.isEmpty() == false){
						
						Set mapSet = (Set) mainMap.entrySet();
						
						Iterator mapIterator = mapSet.iterator();
						
						while (mapIterator.hasNext()) {
				            Map.Entry mapEntry = (Map.Entry) mapIterator.next();
				            // getKey Method of HashMap access a key of map
				            mainClass = (TypeDeclaration) mapEntry.getKey();
				            //getValue method returns corresponding key's value
				            mainDecl = (MethodDeclaration) mapEntry.getValue();
				            //System.out.println("Key : " + mainClass.getName().toString() + "= Value : " + mainDecl.getName());
						}
					}
					else{*/
						MethodDeclaration thisMethod = null;
						
						E_Method method = null;
						
						TypeDeclaration initClass = null;
					
						List<ASTNode> children = AST_Parser.getChildren(node);
					
						for (ASTNode child: children) {
							
							if (child.getNodeType() == ASTNode.METHOD_DECLARATION){
								
								MethodDeclaration md = (MethodDeclaration) child;
								
								IMethodBinding tmb = md.resolveBinding();
								
								if (AST_Parser.ifUserDefinedMethod(tmb)){
						        	  
					          	    if(tmb != null){
			    		    		
					          		  thisMethod = md;
						        	 
					          		  break;
						             }
					          	    //else part of method here
								}
					          }
						}
					
						// if method is given as a part of initializer
						
						if(thisMethod != null){
						
							E_Method m = Data_Controller.searchMethod(thisMethod);
							
							if(m != null){ 
								  
								method = m;
								  
							  }
							 else{
								 
								 E_Object obj = new E_Object();
								 
								 method =  AST_Parser.createNewMethod(thisMethod,obj);	
							  }
						}
						else{
							initClass = (TypeDeclaration) bNode.getParent();
							
							method = Data_Controller.searchConstMethod(initClass);
							
						}
					
					 if(laExp != null && method!=null){
						
			    	   if (laExp.getNodeType() == ASTNode.FIELD_ACCESS || laExp.getNodeType() == ASTNode.SIMPLE_NAME ||
					    		laExp.getNodeType() == ASTNode.QUALIFIED_NAME){
					    		//||laExp.getNodeType() == ASTNode.ARRAY_ACCESS) {
					    	    AST_Parser.addAssignmentExpression(laExp,raExp,method);		
						}
			    	   else if(laExp.getNodeType() == ASTNode.SIMPLE_TYPE){
			    		   System.out.println("SimpleType "+node.getProperty("name"));
			    	   }
					   else {
						
							List<ASTNode> l_children = AST_Parser.getChildren(laExp);
							
							for (ASTNode l_child: l_children) {
							
								if (l_child.getNodeType() == ASTNode.FIELD_ACCESS || l_child.getNodeType() == ASTNode.SIMPLE_NAME 
										|| l_child.getNodeType() == ASTNode.QUALIFIED_NAME
										||l_child.getNodeType() == ASTNode.ARRAY_ACCESS) {
							
									Expression childExp = (Expression) l_child;
							
									AST_Parser.addAssignmentExpression(childExp,raExp,method);
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
									
									AST_Parser.checkRightSide(child_exp,raExp,method);
								}
							}
					 }
		       	  	
		             return super.visit(node); 
		         }; 
		        
			});

		}
		return super.visit(bNode);	    
	}

}