package aeminium.fibonacci;

public class Fibonacci{
	
	public Integer number = 5;

	//Immutable(num) -> Immutable(num)
	public static Integer computeFibonacci(Integer num) {
		if (num <= 1) {// Read-Only rule applied on n
			 return num; //  Read-Only rule applied here
        	}
        else 
        	return computeFibonacci(num-1) + computeFibonacci(num-2); // Immutable-M-Call(number)
    }
	//Immutable(num) -> Immutable(num)
	public static void display(Integer num){  
		
		System.out.println("The Fibonacci of Number =  " +num+" is = ");
	}
	//none(number) -> unique(number)
    public static void main(String[] args) {
        
    	long start = System.nanoTime(); //library function (do -nothing)
    	
    	Fibonacci obj = new Fibonacci();
    	
    	Fibonacci.computeFibonacci(obj.number);//MethodCall(<Immutable>, number)
    	
    	Fibonacci.display(obj.number);// // MethodCall(<Immutable>, number)
    	
    	long elapsedTime = System.nanoTime()-start;//library function (do -nothing)
    	
    	double ms = (double) elapsedTime / 1000000.0;//library function (do -nothing)
    	
    	System.out.println(" Milli Seconds Time = "+ms);//library function (do -nothing)
    }
    
}
