package working_examples;
class ArrayCollection{
 public Integer[] array1; 
public void createColl(Integer[] coll) { 
 coll = new Integer[10];
 for(int i = 0; i < coll.length-1; i++)
  coll[i] = (int)(Math.random() * 10);
 }
 public void printColl(Integer[] coll) { 
  for(int i = 0; i < coll.length-1; i++){
   System.out.println(" "+coll[i]);
   System.out.println(" "+array1[i]);}
 }
 public  void incrColl(Integer [] coll) { 
  for (int i = 0; i < this.array1.length; i++)
  { this.array1[i] = this.array1[i] + i; }
  for (int j = 0; j < coll.length; j++)
  { coll[j] = coll[j] + j; }
 }
 public static boolean isSorted(Integer[] coll) { 
 boolean flag = false;
 for (int i = 0; i < coll.length-1; i++){ 
  if(coll[i] > coll[i+1])
   flag = true;
  else 
   flag = false;
  }
  return flag;
 }
 public static Integer findMax(Integer[] coll) {
  int max;
  max = coll[0];
  for (int i = 1; i < coll.length-1; i++){ 
   if(coll[i] > max)
	 max = coll[i];}
  return max;
 }
 public void computeStat(Integer[] coll){
  printColl(coll);
  System.out.println("Array Sorted = "+isSorted(coll));
  System.out.println("Array Max = "+findMax(coll));
 }
 public void tidyupColls(Integer[] coll){
    this.array1 = null;
    coll = null;
}
}
class oObjectClass{
 public static Integer[] array2 = new Integer[10]; 
 public static Client x,y,z,w; 
 public static void manipulateObjects(Client p1, Client p2){
    x = w;
 Client t = x;
    y = t;
    x.data = 10;
    System.out.println("z.data = "+p1.data+"x.data = "+t.data);
 }
}
class Client{	
	 Integer data = 100;
	 public static void main(String[] args) {
	  ArrayCollection obj1 = new ArrayCollection();
	  //ObjectClass obj2 = new ObjectClass();
	  obj1.createColl(obj1.array1);
	  obj1.createColl(ObjectClass.array2);
	   obj1.incrColl(ObjectClass.array2);
	  obj1.computeStat(obj1.array1);
	  obj1.computeStat(ObjectClass.array2);
	  obj1.tidyupColls(ObjectClass.array2);
	 // oObjectClass.manipulateObjects(ObjectClass.w,ObjectClass.z);
	}
}
