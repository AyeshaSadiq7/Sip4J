package aeminium.integral;


import java.text.DecimalFormat;

import java.lang.Double;

public class SeqIntegral {
	
	public static Double x1 = 0.0;
	
	public static Double x2 = 1.0;
	
	public static Double area;
	
	public static Double compute(Double x1, Double x2) {
		
		Double EPSILON = 0.000001 ;
		
		double diff;
		
		diff = x2-x1;// Read-Only rule fro x1 and x2
		
		if ( diff < EPSILON ) { //Read-Only rule for Epsilon
			double f1, f2, combinedf, avgf; // do nothing
			// compute trapez area 
			f1 = f(x1);// Immutable-M_Call(x1)
			f2 = f(x2);// Immutable-M_Call(x2)
			combinedf = f1+f2;// do nothing
			avgf = combinedf/2;// do nothing
			area = avgf * (x2-x1); // Value-flow Rule for area and Read-Only rule for x1 and x2
			return area;// Read-Only rule for area
		} else {
			// divide and conquer
			final double middle = (x2+x1) / 2; //Read-Only rule for x1 and x2
			return compute(x1, middle) + compute(middle, x2);// Apply Immutable-M-Call(x1), Immutable-M-Call(x2) rules for x1 and x2 respectively
		}
	}
	public static Double f(final Double x1) {// convert back to one parameter
		return x1 * x1 ; // Read-Only for x1 and x2
	}
	public static void display(Double area){
		System.out.println((new DecimalFormat("#.####")).format(area));
	}
	public static void main(String[] args) {
		
		SeqIntegral obj = new SeqIntegral();
		   
		long startTime = System.nanoTime();// do nothings
	
		compute(x1,x2);// Immutable-M-Call(x1), Immutable-M-Call2), Full-M-Call(area), Value flow rule for area here
		
		display(area);// Immutable-M-Call(area);
		     
	    // for java built in class we should generate unique for this object
	    
		long elapsedTime  = System.nanoTime() - startTime; // do-nothing
		
		double ms = (double) elapsedTime / 1000000.0; // do-nothing
    	
    	System.out.println(" Milli Seconds Time = "+ms); // do-nothing
		
	}
}
/*

Class Name = SeqIntegral
Method Name = main
Ref-Var= x1, Pre-Permissions=none, Post Permissions=unique
Ref-Var= x2, Pre-Permissions=none, Post Permissions=unique
Ref-Var= area, Pre-Permissions=none, Post Permissions=unique
Method Name = compute
Ref-Var= x1, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= x2, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= area, Pre-Permissions=full, Post Permissions=full
Method Name = f
Ref-Var= x1, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= x2, Pre-Permissions=immutable, Post Permissions=immutable
Method Name = display
Ref-Var= area, Pre-Permissions=pure, Post Permissions=pure

//////////////////////////////////////////////////////

