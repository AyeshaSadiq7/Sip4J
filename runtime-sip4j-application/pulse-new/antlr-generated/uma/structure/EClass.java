package uma.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class EClass {
	
	String className;
	String superClass;
	int numberOfObjects;
	LinkedList<EField> fields;
	LinkedList<EMethod> methods;
	LinkedList<EState> states;
	LinkedList<String> transitions; // added for graph
	LinkedList<EState> reachability_state;   // added for metrics
	private int classIndex;
	ArrayList<String> variablesOfBoolInvariants;
	LinkedList<EDim> dimensions;
	private String ClassStates;
	 
	
	public EClass(){
		
		fields=new LinkedList<EField> ();
		methods=new LinkedList<EMethod> ();
		states=new LinkedList<EState>();
		transitions=new LinkedList<String>();
		reachability_state=new LinkedList<EState>();
		superClass=null;
		numberOfObjects=0;
		variablesOfBoolInvariants=new ArrayList<String>();
		dimensions=new LinkedList<EDim>();
		EState _state=new EState("alive", 0); // by default every object is in state alive
		states.add(_state);
	}
	
	public void setSuperClassName(String str){
		superClass=str;	
	}
	public String getSuperClassName(){
		
		return 	superClass;
	}
	
	public int getTotalStates(){
		return states.size();
		
	}
	public int getTotalReachableStates(){
		int count=0;
		for(EState _state:reachability_state)
			if(_state.isReachable()==1)
			 count++;
		return count;
		
	}
	
	public void setName(String str){
		className=str;	
	}
	public String getName(){
		
		return 	className.toString();
	}
	
	public void addMethod(EMethod method)
	{
		methods.add(method);	
	}
	public LinkedList<EMethod> getMethods()
	{
		return methods;
	}
	
	public void addField(EField field){
		fields.add(field);	
	}
	public LinkedList<EField> getFields()
	{
		return fields;
	}
	
	public LinkedList<EState> getStates()
	{
		return states;
	}
	
	public void addState(EState state){
	
		states.add(state);
		
		reachability_state.add(state);
		
		/*states.add(state);	
		E_State _st=new E_State(state, 0);
		reachability_state.add(_st);*/
		
	}
	
	// only used for graph
	public LinkedList<String> getTransitions()
	{
		return transitions;
	}
	//added later to handle metrics
	public LinkedList<EState> getReachableStates()
	{
		return reachability_state;
	}

	public void createObject() {
		// TODO Auto-generated method stub
		numberOfObjects++;
		
	}

	public int getLastObjectIndex() {
		// TODO Auto-generated method stub
		return numberOfObjects;
	}

	public int findStateIndex(String st) {

		for (EState state:states){
			if (state.getName().compareTo(st)==0)
				return state.getStateIndex();
		}
		return -1;
	}

	public void setIndex(int classIndex) {
		this.classIndex=classIndex;
	}
	
	public int getIndex() {
		return classIndex;
	}
	
	public EMethod getConstructor(){
		
		for (EMethod _method:methods)
			if (className.compareTo(_method.identifier)==0)
				return _method;
		
		return null;
	}
	
	public ArrayList<String> getVariablesofBooleanInvariants()
	{
		return variablesOfBoolInvariants;
	}

	public void addDimension(EDim dim) {
		
		dimensions.add(dim);
		
	}

	public LinkedList<EDim> getDimensions() {
		
		return dimensions;
		
	}

	public boolean hasMoreThanOneDimension() {
		
		if (dimensions.size()>1)
			 return true;

		return false;
	}

	public void addClassStatesSpecifications(String annotation) {
		
		ClassStates="annotation";
		
	}

}
