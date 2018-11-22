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
*      adapted from SciMark 2.0, author Roldan Pozo (pozo@cam.nist.gov)   *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/

package jomp.sparsematmult;
import jomp.jgfutil.*;

public class SparseMatmult
{

  public static double ytotal = 0.0;

	/* 10 iterations used to make kernel have roughly
		same granulairty as other Scimark kernels. */
  

	public static void test( double y[], double val[], int row[],
				int col[], double x[], int NUM_ITERATIONS)
	{
		int nz = val.length;

        JGFInstrumentor.startTimer("Section2:SparseMatmult:Kernel"); 

		for (int reps=0; reps<NUM_ITERATIONS; reps++)
		{
			for (int i=0; i<nz; i++)
			{
      			y[ row[i] ] += x[ col[i] ] * val[i];
			}
		}

        JGFInstrumentor.stopTimer("Section2:SparseMatmult:Kernel"); 

          for (int i=0; i<nz; i++) {
            ytotal += y[ row[i] ];
          }
       	}
}
