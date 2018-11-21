package jomp.search;
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
import jomp.search.*;

public class JGFSearchBenchSizeA { 
	/*Class Name = JGFSearchBenchSizeA
			Method Name = main
			Vertex Name = NSAMELOCK, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = nodes, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = timers, Post Permissions = none, Pre-Permissions =none
			Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = plycnt, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = dias, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = columns, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = height, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = rows, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = moves, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = he, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = posed, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = hits, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = msecs, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = WIN, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = LOSE, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = htindex, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = ht, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = lock, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = stride, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = history, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
			Vertex Name = DRAW, Post Permissions = none, Pre-Permissions =none
			Vertex Name = DRAWLOSE, Post Permissions = none, Pre-Permissions =none
			Vertex Name = opname, Post Permissions = none, Pre-Permissions =none
			Vertex Name = DRAWWIN, Post Permissions = none, Pre-Permissions =none
			Vertex Name = startingMoves, Post Permissions = none, Pre-Permissions =none
			Vertex Name = STRIDERANGE, Post Permissions = none, Pre-Permissions =none
			Vertex Name = INTMODSTRIDERANGE, Post Permissions = none, Pre-Permissions =none
			Vertex Name = name, Post Permissions = none, Pre-Permissions =none
			Vertex Name = TRANSIZE, Post Permissions = none, Pre-Permissions =none
			Vertex Name = colthr, Post Permissions = none, Pre-Permissions =none
			Vertex Name = PROBES, Post Permissions = none, Pre-Permissions =none
			Vertex Name = ABSENT, Post Permissions = none, Pre-Permissions =none
			Vertex Name = calls, Post Permissions = none, Pre-Permissions =none
	
			
*/
  public static void main(String argv[])
  {

    //JGFInstrumentor.printHeader(3,0);

    JGFSearchBench sb = new JGFSearchBench();
    
    sb.JGFrun(0);
  }

}
