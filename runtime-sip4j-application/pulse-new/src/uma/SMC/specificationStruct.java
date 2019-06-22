package uma.SMC;

public class specificationStruct {
	
	class Clause {
		String accessPermission="NULL";
		String typeState="NULL";
	};
	
	class Signature{
		public String methodName="Method_Name";
		public String className="Class_Name";	
	}
	
	public Clause requires, ensures;
	public Signature signature;
	
	specificationStruct()
	{ 
		requires= new Clause();
		ensures= new Clause();
		signature=new Signature();
	}
	
}