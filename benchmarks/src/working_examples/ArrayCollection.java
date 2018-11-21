package working_examples;

public class ArrayCollection{

    public  Integer[] coll;
   
    // @none(coll) -> @unique(coll) // Context-RW
    public ArrayCollection(int size) {// no parameter is map as size is not a class field
      this.coll = new Integer[size]; // Apply(<G-New-Obj>, <ref-var>) where <ref-var> = coll
    }
   // @share(coll) -> @share(coll) // Context-RW
    public void initColl(){//The proposed technique maps the formal parameter with the actual parameter i.e. coll
    	int i = 0; // Apply (L-Initialization, <local-var>) where i = <local-var>
    	for(;i < this.coll.length; i++) //Apply(G-Read-Only, <ref-var>) where <ref-var> = coll AND Apply(L-Val-Flow, <local-var>) where i = <local-var>
        this.coll[i] = (int)(Math.random()*10); // Apply(G-Val-Flow, <ref-var>) where <ref-var> = coll
    }
    // @pure(coll) -> @pure(coll)       
    public void displayColl() {//The proposed technique maps the formal parameter with the actual parameter i.e. coll
    	for(int i = 0; i < this.coll.length; i++) { // Apply(G-Read-Only, <ref-var>) where <ref-var> = coll AND Apply(L-Val-Flow, <local-var>) where i = <local-var>
         System.out.println("coll[i] ="+coll[i]); // Apply(G-Read-Only, <ref-var>) where <ref-var> = coll
      } 
    }
    // @pure(e1) -> @pure(e1)        
    public void displayE(Integer[] e) {
         System.out.println(""+e[0]);
     }
    // @share(coll) * share(e1)  -> @share(coll) * share(e1)       
    public  void initE(Integer[] e1) {
	   for (int i = 0; i < coll.length; i++) // Apply (L-Read-Only, <local-ref-var>) where temp = <local-ref-var> AND Apply(L-Val-Flow, <local-var>) where i = <local-var>
   		   e1[i] = coll[i]*2; //Apply(L-Val-Flow, <local-ref-var>) where temp = <local-ref-var>;
    }
   // @share(coll) -> @share(coll) // Context-RW
    public void copyColl() { // The proposed technique maps the formal parameter with the actual parameter i.e. coll
       Integer[] temp;// Apply(<L-Decl>, <local-ref-var>)
       temp = coll;  //Apply (L-Addr-Flow, <local-ref-var>) where temp = <local-ref-var>;
       // Apply (L-Initialization, <local-var>) where i = <local-var>
    	for (int i = 0 ; i < temp.length; i++) // Apply (L-Read-Only, <local-ref-var>) where temp = <local-ref-var> AND Apply(L-Val-Flow, <local-var>) where i = <local-var>
    		temp[i] = temp[i]+i; //Apply(L-Val-Flow, <local-ref-var>) where temp = <local-ref-var>;
     }
    // @unique(coll) * unique(e1)  -> none(coll) * none(e1) // Context-RW
     public void tidyupColl() {
	      this.coll = null; // Apply(<G-Null-Addr-Flow>, <ref-var>) where <ref-var> = coll
	      //e = null; //Apply(<G-Null-Addr-Flow>, <ref-var>) where <ref-var> = e
	 }
    // @pure(coll), @none(e) -> @pure(coll),@unique(e)           // Context-RW
      /*public void initE(Integer[] e) {
        e = this.coll; //Apply(G-Addr-Flow, <ref-var>) where <ref-var> = e 
      } */
}
class ArrayClient{
	public static Integer[] e1 = new Integer[10];
	
	public static void main(String[] args) { 
		
		  ArrayCollection array = new ArrayCollection(10);//Apply(<LR-New-Obj>, <local-ref-var>) where <local-ref-var> = coll
		  
		  array.initColl(); //Apply (MethodCall<post-perm>, <ref-var>) where <ref-var> = coll;
	      array.displayColl(); //Apply (MethodCall<post-perm>, <ref-var>) where <ref-var> = coll;
	      array.initE(e1); // initialise e 
	      array.copyColl(); //Apply (MethodCall<post-perm>, <ref-var>) where <ref-var> = coll;
	      array.displayE(e1); //Apply (MethodCall<post-perm>, <ref-var>) where <ref-var> = coll;
	     // box.incrE(e1); //Apply (MethodCall<post-perm>, <ref-var>) where <ref-var> = coll;
		  array.tidyupColl(); //Apply (MethodCall<post-perm>, <ref-var>) where <ref-var> = coll;*/
	   }
 
}
