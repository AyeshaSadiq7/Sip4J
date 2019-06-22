package jomp.lufact;
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


import jomp.jgfutil.JGFInstrumentor;

public class JGFLUFactBenchSizeB{ 

  public static void main(String argv[]){

  //  JGFInstrumentor.printHeader(2,1);

    JGFLUFactBench lub = new JGFLUFactBench(); 
    
    lub.JGFrun(1);
 
  }
}

/*Class Name = JGFLUFactBenchSizeB
Method Name = main
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = n, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes, Post Permissions = unique, Pre-Permissions =none 
Vertex Name = ldaa, Post Permissions = unique, Pre-Permissions =none
Vertex Name = lda, Post Permissions = unique, Pre-Permissions =none
Vertex Name = a, Post Permissions = unique, Pre-Permissions =none
Vertex Name = b, Post Permissions = unique, Pre-Permissions =none
Vertex Name = x, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ipvt, Post Permissions = unique, Pre-Permissions =none
Vertex Name = norma, Post Permissions = unique, Pre-Permissions =none
Vertex Name = info, Post Permissions = unique, Pre-Permissions =none
Vertex Name = resid, Post Permissions = unique, Pre-Permissions =none
Vertex Name = normx, Post Permissions = unique, Pre-Permissions =none
/////////////////////////////////////////////////////////////////
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = none, Pre-Permissions =none
Vertex Name = ops, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = none, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = none, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none

*/
 
