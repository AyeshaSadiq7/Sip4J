/**************************************************************************
*                                                                         *
*             Java Grande Forum Benchmark Suite - Version 2.0             *
*                                                                         *
*                            produced by                                  *
*                                                                         *
*                  Java Grande Benchmarking Project                       *
*                                                                         *
*                                at                                       *
*                                                                         *
*                Edinburgh Parallel Computing Centre                      *
*                                                                         * 
*                email: epcc-javagrande@epcc.ed.ac.uk                     *
*                                                                         *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/


package jomp.sparsematmult;
import jomp.jgfutil.*; 

import java.util.Random;

public class JGFSparseMatmultBench extends SparseMatmult implements JGFSection2{ 

	
  private static int size = 0; 
  private static final long RANDOM_SEED = 10101010;

  private static final int datasizes_M[] = {50000,100000,500000};
  private static final int datasizes_N[] = {50000,100000,500000};
  private static final int datasizes_nz[] = {250000,500000,2500000};
  private static final int SPARSE_NUM_ITER = 200;
  int lastElement;
  
  Random R = new Random(RANDOM_SEED);

  double [] x; 
  double [] y; 
  double [] val; 
  int [] col;
  int [] row;

  public static void main(String argv[]){

      JGFInstrumentor.printHeader(2,0);

      JGFSparseMatmultBench cb = new JGFSparseMatmultBench(); 
      cb.JGFrun(size);
       
    }
  /*Method Name = JGFsetsize
		  Vertex Name = size, Post Permissions = share, Pre-Permissions =share*/
  public void JGFsetsize(int size){
    this.size = size;

  }
  /*Method Name = JGFinitialise
		  Vertex Name = x, Post Permissions = unique, Pre-Permissions =unique 
		  Vertex Name = datasizes_N, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = R, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = y, Post Permissions = unique, Pre-Permissions =none
		  Vertex Name = datasizes_M, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = val, Post Permissions = unique, Pre-Permissions =none
		  Vertex Name = datasizes_nz, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = col, Post Permissions = unique, Pre-Permissions =none
		  Vertex Name = row, Post Permissions = unique, Pre-Permissions =none*/
 
  public void JGFinitialise(){
	  
	  lastElement = datasizes_N[size];
	  
	  x = RandomVector(lastElement, R); // here x should be unique as method returns a reference Type
	 
	  y = new double[datasizes_M[size]];
	
	  val = new double[datasizes_nz[size]];
	  
	  col = new int[datasizes_nz[size]];
	  
	  row = new int[datasizes_nz[size]];
	
	    for (int i=0; i < datasizes_nz[size]; i++) {
	
	        // generate random row index (0, M-1)
	        row[i] = Math.abs(R.nextInt()) % datasizes_M[size];
	
	        // generate random column index (0, N-1)
	        col[i] = Math.abs(R.nextInt()) % datasizes_N[size];
	
	        val[i] = R.nextDouble();
	
	    }

  }
 
  public void JGFkernel(){
    SparseMatmult.test(y, val, row, col, x, SPARSE_NUM_ITER);

  }
  
  public void JGFvalidate(){

    double refval[] = {75.02484945753453,150.0130719633895,749.5245870753752};
    double dev = Math.abs(ytotal - refval[size]);
    if (dev > 1.0e-12 ){
      System.out.println("Validation failed");
      System.out.println("ytotal = " + ytotal + "  " + dev + "  " + size);
    }

  }

  public void JGFtidyup(){
   x = null;
   System.gc();
  }  

  public void JGFrun(int size){
    
	 JGFInstrumentor.addTimer("Section2:SparseMatmult:Kernel", "Iterations",size);

    JGFsetsize(size); 
    JGFinitialise(); 
    JGFkernel(); 
    JGFvalidate(); 
    JGFtidyup(); 

    JGFInstrumentor.addOpsToTimer("Section2:SparseMatmult:Kernel", (double) (SPARSE_NUM_ITER));
 
    JGFInstrumentor.printTimer("Section2:SparseMatmult:Kernel"); 
  }
    private static double[] RandomVector(int N, java.util.Random R)
    {
        double A[] = new double[N];

        for (int i = 0; i < N; i++){
                
        	A[i] = R.nextDouble() * 1e-6;
        }

        return A;
    }
   

}
