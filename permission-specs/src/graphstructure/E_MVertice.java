package graphstructure;

import graphconstruction.LabeledEdge;

import java.util.HashSet;
import java.util.Set;

public class E_MVertice {

	private String vName;
	
	private String vType;
	
	private E_MethodGraph refMethod;
	
	private String qualifiedName;
	
	private String mOperation;
	
	private String cOperation;
	
	private String aOperation;
	
	private boolean isField = false;
	
	private boolean isParam = false;
	
	private int position = -1;
	
	int expType=-1;
	
	private Set<LabeledEdge> incomingEdges = new HashSet<LabeledEdge>();
	
	private Set<LabeledEdge> outgoingEdges = new HashSet<LabeledEdge>();
	
	private String pre_permissions = "";
	
	private String post_permissions= "";
	
	private boolean isMethod = false;
	
	private boolean isContext = false;
	
	
	public String getVName() {
		
		return vName;
	}
	public void setVName(String v) {
		vName = v;
	}
	public Set<LabeledEdge> getIncomingEdges() {
		return incomingEdges;
	}
	public void setIncomingEdges(LabeledEdge incomingEdge) {
		this.incomingEdges.add(incomingEdge);
	}
	public Set<LabeledEdge> getOutgoingEdges() {
		return outgoingEdges;
	}
	public void setOutgoingEdges(LabeledEdge outEdge) {
		this.outgoingEdges.add(outEdge);
	}
	public String getPre_permissions() {
		return pre_permissions;
	}
	public void setPre_permissions(String pre_permissions) {
		this.pre_permissions = pre_permissions;
	}
	public String getPost_permissions() {
		return post_permissions;
	}
	public void setPost_permissions(String post_permissions) {
		this.post_permissions = post_permissions;
	}
	public String getQualifiedName() {
		return qualifiedName;
	}
	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}
	public String getVType() {
		return vType;
	}
	public void setVType(String type) {
		this.vType = type;
	}
	public boolean isMethod() {
		return isMethod;
	}
	public void setMethod(boolean method) {
		this.isMethod = method;
	}
	public boolean isContext() {
		return isContext;
	}
	public void setContext(boolean context) {
		this.isContext = context;
	}
	public String getmOperation() {
		return mOperation;
	}
	public void setmOperation(String mOperation) {
		this.mOperation = mOperation;
	}
	public String getcOperation() {
		return cOperation;
	}
	public void setcOperation(String cOperation) {
		this.cOperation = cOperation;
	}
	public String getaOperation() {
		return aOperation;
	}
	public void setaOperation(String aOperation) {
		this.aOperation = aOperation;
	}
	public int getExpType() {
		return expType;
	}
	public void setExpType(int etype) {
		this.expType = etype;
	}
	public boolean isField() {
		return isField;
	}
	public void setField(boolean isField) {
		this.isField = isField;
	}
	public boolean isParam() {
		return isParam;
	}
	public void setParam(boolean isParam) {
		this.isParam = isParam;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public E_MethodGraph getRefMethod() {
		return refMethod;
	}
	public void setRefMethod(E_MethodGraph refMethod) {
		this.refMethod = refMethod;
	}
	
	
}
