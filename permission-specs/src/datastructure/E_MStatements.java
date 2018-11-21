package datastructure;

public class E_MStatements {

	private String statement;
	private int expType;
	
	public E_MStatements(String exp, int type){
		this.statement = exp;
		this.expType =  type;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public int getExpType() {
		return expType;
	}
	public void setExpType(int expType) {
		this.expType = expType;
	}
	
	
}
