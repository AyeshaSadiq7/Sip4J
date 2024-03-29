package working_examples;

public class ArrayColl {
	public static Integer[] c;
	public static void createColl(Integer[] coll) {
	  coll = new Integer[10];
	  for(int i = 0; i < getSize(coll); i++)
	    coll[i] = (int)(Math.random() * 10);
	}
	public static void printColl(Integer[] coll) {
	  for(int i = 0; i < getSize(coll); i++)
	     System.out.println(" "+coll[i]);
	}
	public static void incrColl() {
	  for (int i = 0; i < getSize(c); i++){c[i] = c[i] + i;}
	}
	public static int getSize(Integer[] coll) {
	 return coll.length;
	}
	public static Integer findMin(Integer[] c) {
	Integer min;
	min = c[0];
	for (int i = 1; i < getSize(c); i++){
	if(c[i] < min)
		min = c[i];
	}
	return min;
	}

	public static void computeStat(Integer[] coll){
	printColl(coll);
	System.out.println("Array Size = "+getSize(coll));
	System.out.println("Array min = "+findMin(coll));
	}
	public static void tidyupColl(){
		c = null;
	}
	public static void main(String[] args) {
	ArrayColl.createColl(c);
	ArrayColl.incrColl();
	ArrayColl.computeStat(c);
	ArrayColl.tidyupColl();
	}
}
