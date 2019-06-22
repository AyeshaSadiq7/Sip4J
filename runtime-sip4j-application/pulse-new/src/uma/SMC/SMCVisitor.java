package uma.SMC;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.eclipse.jdt.core.dom.*;


import uma.SMC.PluralLexer;
import uma.SMC.PluralParser;
import uma.gap.plugin.PulseSettings;
import uma.structure.*;


public class SMCVisitor extends ASTVisitor {
	
	private EClass m_Class;
	
	private String caseHandling="notinsidecase$"; //handle @cases
	private String methodName="";  //handle @Cases
	

	public SMCVisitor() {
		super();
	}
	
	private void addUnparsedSpecifications(String annotation) {
		
		if (EVMDDSMCGenerator.getPkgObject().getClasses().getLast().getMethods().size()==0)
			EVMDDSMCGenerator.getPkgObject().getClasses().getLast().addClassStatesSpecifications(annotation);
		else
			EVMDDSMCGenerator.getPkgObject().getClasses().getLast().getMethods().getLast().addSpecifications(annotation);
	}
	
	@Override
	public void preVisit(ASTNode node) {
		super.preVisit(node);
	}
	
	// just for logging, does not use in parsing
	@Override
	public void postVisit(ASTNode node) {
		super.postVisit(node);
	}

	// we parse and store info but no rules apply for this case
	@Override
	public boolean visit(PackageDeclaration node) {
		
		return super.visit(node);
	}
	
	@Override
	public void endVisit(PackageDeclaration node) {
		
		super.endVisit(node);
	}
	
	@Override
	public boolean visit(TypeDeclaration node) {
		
		//System.out.println(node.getName().toString());
		
		
		methodName="";
		
		EClass _class=new EClass();
		_class.setName(node.getName().toString());
		if (PulseSettings.getInheritance() == 1 ||PulseSettings.getFullModel()==1){
			Type superClass=node.getSuperclassType();
			if (superClass!=null)
			_class.setSuperClassName(superClass.toString());
		}
		
		FieldDeclaration[] fields= node.getFields();
		if (fields!=null){
			for (FieldDeclaration field:fields){
				if (!field.getType().isPrimitiveType()){
					EField _field=new EField();
					_field.setName(((VariableDeclarationFragment)field.fragments().get(0)).getName().toString());
					
					if (field.getType().isParameterizedType())
						_field.setType(((ParameterizedType)field.getType()).getType().toString());
					else if (field.getType().isArrayType()){
						Type componentType=((ArrayType)field.getType()).getComponentType();
						if (componentType.isParameterizedType()){ //
							_field.setType(((ParameterizedType)componentType).getType().toString());
						}
						else
						_field.setType(((ArrayType)field.getType()).getComponentType().toString());
					}
					else
					_field.setType(field.getType().toString());
					
					_field.setModifier(field.getModifiers());
					_class.addField(_field);
				}
			}
			
		}
		
		EVMDDSMCGenerator.getPkgObject().getClasses().addLast(_class);
		return super.visit(node);
	}

	@Override
	public void endVisit(TypeDeclaration node) {
		super.endVisit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		
		methodName=node.getName().toString();// stored to manage @Cases
		//System.out.println(methodName);	
		EPackage pkg=EVMDDSMCGenerator.getPkgObject();
		EClass _class=pkg.getClasses().getLast();
		EMethod _method=new EMethod();
		_method.setName(node.getName().toString());
		if (node.getReturnType2()!=null)
			_method.setReturnType(node.getReturnType2().toString());
		
		_method.setIdentifier(node.getName().toString());
		_class.addMethod(_method);
		
		List<SingleVariableDeclaration> _listParameters=node.parameters();
		int pos=0;
		for (SingleVariableDeclaration para:_listParameters){
			EParameter _parameter= new EParameter(para.getName().getIdentifier().toString(),para.getType().toString(),pos);
			_method.addParameter(_parameter);
			pos++;
		}
		

		return super.visit(node);
	}
	
	@Override
	public void endVisit(MethodDeclaration node) {
		super.endVisit(node);
	}
	@Override
	public boolean visit(SingleVariableDeclaration node){
		return super.visit(node);
	}
	@Override
	public boolean visit(TypeParameter node){
		
		return super.visit(node);
	}
	
	@Override 
	public boolean visit(NormalAnnotation node)
	{
		if (methodName.compareTo(caseHandling)!=0 && !methodName.isEmpty()) // indicates that visit(SingleMemberAnnotation node) has been called
		{	callParser(node.toString());
			//System.out.println("from NA"+node.toString());
		}
		return super.visit(node);
	}
	@Override 
	public void endVisit(NormalAnnotation node) {
			super.endVisit(node);
		}
	
	
	@Override 
	public boolean visit(SingleMemberAnnotation node)
	{	
		//System.out.println("from SVD"+ node.toString());
		caseHandling = methodName;
		callParser(node.toString());
		return super.visit(node);
	}

	@Override 
	public void endVisit(SingleMemberAnnotation node) {
		
		//System.out.println("from end AnnotationTypeDeclaration"+ node.toString());
		super.endVisit(node);
		
	}


	private void callParser(String annotation){
		
		CharStream input = new ANTLRStringStream(annotation);
		
		PluralLexer lex = new PluralLexer(input);
			
		TokenStream token = new CommonTokenStream(lex);
		
		PluralParser parser = new PluralParser(token);
		
		try {
			parser.specifications();
		//System.out.println("Added in Model\n");
		}
		catch (RecognitionException e) {
				
				e.printStackTrace();
		}
		
	}


	
}

