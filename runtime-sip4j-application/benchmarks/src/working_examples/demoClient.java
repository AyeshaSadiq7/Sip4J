package working_examples;

class ObjectClass{
	 public static Integer[] array2 = new Integer[10]; 
	 public static demoClient x,y,z,w; 
	 public static void manipulateObjects(){
	    x = w;
	    demoClient t = x;
	    y = t;
	    x.data = 10;
	 }
	 public static void readObjects(demoClient p1, demoClient p2){
		    System.out.println("w.data = "+p1.data+"z.data = "+p2.data);
		 }
	 public static void readObjects1(demoClient p1, demoClient p2){
		    System.out.println("w.data = "+p1.data+"z.data = "+p2.data);
		 }
	}
	public class demoClient{	
		 Integer data = 100;
		 public static void main(String[] args) {
		 ObjectClass.manipulateObjects();
		 ObjectClass.readObjects(ObjectClass.w,ObjectClass.z);
		 ObjectClass.readObjects1(ObjectClass.w,ObjectClass.z);
		}
	}