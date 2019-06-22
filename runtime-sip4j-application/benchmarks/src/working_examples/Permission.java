package working_examples;

public class Permission{
	
 public static Integer[] c;
 public static Integer[] e;

 //@none(c) -> @Unique(c)
 public static void createColl(Integer[] coll) {
  coll = new Integer[10];
  for(int i = 0; i < getSize(coll); i++)
    coll[i] = (int)(Math.random() * 10);
 }
 //@Immutable(coll) -> @Immutable(coll)
 public static void printColl(Integer[] coll) {
  for(int i = 0; i < getSize(coll); i++)
     System.out.println(" "+coll[i]);
 }
 //@Immutable(coll) -> @Immutable(coll)
 public static int countEvenNumbers(Integer[] coll) {
  int count = 0;
  for (int i = 0; i < getSize(coll); i++)
     if(isEven(coll[i])){ 
    	 count++;
    }
  return count;
 }
 public static Integer[] getEvenNumbers(Integer[] coll) {
	  int count = 0 ;
	  Integer[] temp = new Integer[countEvenNumbers(coll)];
	  for(int i = 0; i < getSize(coll); i++)
	     if(isEven(coll[i])){ 
	    	 temp[count] = coll[i];
	    	 count++;
	    }
	  return temp;
 }
 //@None(n) -> @None(n)
 public static boolean isEven(Integer n) {
   if(n % 2 == 0) return true;
   else return false;
 }
 //@Full(c) -> @Full(c)
 public static void incrColl() {
  for (int i = 0; i < getSize(c); i++){c[i] = c[i] + i;}
  for (int j = 0; j < getSize(e); j++){e[j] = e[j] + j;}
 }
//@Immutable(coll) -> @Immutable(coll)
 public static int getSize(Integer[] coll) {
  int size = coll.length;
  return size;
 }
 public static boolean isSorted(Integer[] c) {
	 boolean flag = false;
	  for (int i = 0; i < getSize(c)-1; i++){ 
		  if(c[i] > c[i+1])
		  	flag = true;
		  else flag = false;
	  }
	  return flag;
  }
 public static Integer findMax(Integer[] c) {
	 Integer max;
	 max = c[0];
	  for (int i = 1; i < getSize(c); i++){ 
		  if(c[i] > max)
		  	max = c[i];
	  }
	  return max;
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
	  System.out.println("Array Sorted = "+isSorted(coll));
	  System.out.println("Array Max = "+findMax(coll));
	  System.out.println("Array min = "+findMin(coll));
	 
  }
 //@Unique(c)* Unique(e)-> @Unique(c), @Unique(e)
 public static void tidyupColl(){
    c = null;
    e = null;
 }
 //@none(c), @none(e) -> @Unique(c),@Unique(e) //Context N
 public static void main(String[] args) {
  Permission.createColl(c);
  Permission.computeStat(c);
  Integer[] evens = Permission.getEvenNumbers(c);
  Permission.createColl(e);
  Permission.computeStat(e);
  Permission.incrColl();
  Permission.tidyupColl();
  }
}