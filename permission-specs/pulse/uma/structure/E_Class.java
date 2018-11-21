package uma.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class E_Class {
	
	String className;
	String superClass;
	int numberOfObjects;
	LinkedList<E_Field> fields;
	LinkedList<E_Method> methods;
	E_MCombinations parallelMethods;
	LinkedList<E_State> states;
	LinkedList<String> transitions; // added for graph
	LinkedList<E_State> reachability_state;   // added for metrics
	private int classIndex;
	ArrayList<String> variablesOfBoolInvariants;
	LinkedList<E_Dim> dimensions;
	private String ClassStates;
	 
	
	public E_Class(){
		
		fields=new LinkedList<E_Field> ();
		methods=new LinkedList<E_Method> ();
		states=new LinkedList<E_State>();
		transitions=new LinkedList<String>();
		reachability_state=new LinkedList<E_State>();
		parallelMethods = new E_MCombinations();
		superClass=null;
		numberOfObjects=0;
		variablesOfBoolInvariants=new ArrayList<String>();
		dimensions=new LinkedList<E_Dim>();
		E_State _state=new E_State("alive", 0); // by default every object is in state alive
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
		for(E_State _state:reachability_state)
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
	
	public void addMethod(E_Method method)
	{
		methods.add(method);	
	}
	public LinkedList<E_Method> getMethods()
	{
		return methods;
	}
	
	public void addField(E_Field field){
		fields.add(field);	
	}
	public LinkedList<E_Field> getFields()
	{
		return fields;
	}
	
	public LinkedList<E_State> getStates()
	{
		return states;
	}
	
	public void addState(E_State state){
	
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
	public LinkedList<E_State> getReachableStates()
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

		for (E_State state:states){
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
	
	public E_Method getConstructor(){
		
		for (E_Method _method:methods)
			if (className.compareTo(_method.identifier)==0)
				return _method;
		
		return null;
	}
	
	public ArrayList<String> getVariablesofBooleanInvariants()
	{
		return variablesOfBoolInvariants;
	}

	public void addDimension(E_Dim dim) {
		
		dimensions.add(dim);
		
	}

	public LinkedList<E_Dim> getDimensions() {
		
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

	public E_MCombinations getParallelMethods() {
		return parallelMethods;
	}

	public void setParallelMethods(E_MCombinations parallel_methods_stat) {
		this.parallelMethods = parallel_methods_stat;
	}

}
