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
  
  /*Method Name = test
		  Vertex Name = y, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = val, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = row, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = col, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = x, Post Permissions = pure, Pre-Permissions =pure
		  Vertex Name = SPARSE_NUM_ITER, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = ytotal, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = timers, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = on, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = name, Post Permissions = immutable, Pre-Permissions =immutable
		  Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = time, Post Permissions = share, Pre-Permissions =share
		  Vertex Name = calls, Post Permissions = immutable, Pre-Permissions =immutable*/
  
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

	
	
	
/*
Class Name = JGFSparseMatmultBench
Class Name = JGFSparseMatmultBench
Method Name = main
Vertex Name = RANDOM_SEED, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_M, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_N, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_nz, Post Permissions = unique, Pre-Permissions =none
Vertex Name = SPARSE_NUM_ITER, Post Permissions = unique, Pre-Permissions =none
Vertex Name = R, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = x, Post Permissions = unique, Pre-Permissions =none
Vertex Name = y, Post Permissions = unique, Pre-Permissions =none
Vertex Name = val, Post Permissions = unique, Pre-Permissions =none
Vertex Name = col, Post Permissions = unique, Pre-Permissions =none
Vertex Name = row, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ytotal, Post Permissions = unique, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Method Name = JGFsetsize
Vertex Name = size, Post Permissions = share, Pre-Permissions =share
Method Name = JGFinitialise
Vertex Name = x, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_N, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = R, Post Permissions = share, Pre-Permissions =share
Vertex Name = y, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_M, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = val, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_nz, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = col, Post Permissions = unique, Pre-Permissions =none
Vertex Name = row, Post Permissions = unique, Pre-Permissions =none
Method Name = RandomVector
Vertex Name = R, Post Permissions = share, Pre-Permissions =share
Method Name = JGFkernel
Vertex Name = y, Post Permissions = share, Pre-Permissions =share
Vertex Name = val, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = row, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = col, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = x, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = SPARSE_NUM_ITER, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = ytotal, Post Permissions = share, Pre-Permissions =share
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Method Name = test
Vertex Name = y, Post Permissions = share, Pre-Permissions =share
Vertex Name = val, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = row, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = col, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = x, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = SPARSE_NUM_ITER, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = ytotal, Post Permissions = share, Pre-Permissions =share
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Method Name = startTimer
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Method Name = start
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Method Name = stopTimer
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = start_time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Method Name = stop
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = start_time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Method Name = JGFvalidate
Vertex Name = ytotal, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
Method Name = JGFtidyup
Method Name = JGFrun
Vertex Name = SPARSE_NUM_ITER, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = share, Pre-Permissions =share
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = size, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = x, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_N, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = R, Post Permissions = share, Pre-Permissions =share
Vertex Name = y, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_M, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = val, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_nz, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = col, Post Permissions = unique, Pre-Permissions =none
Vertex Name = row, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ytotal, Post Permissions = share, Pre-Permissions =share
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Method Name = addTimer
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = share, Pre-Permissions =share
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = size, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Method Name = JGFTimer
Vertex Name = name, Post Permissions = share, Pre-Permissions =share
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = size, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Method Name = reset
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Method Name = addOpsToTimer
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Method Name = addops
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Method Name = printTimer
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = opcount, Post Permissions = pure, Pre-Permissions =pure
Method Name = print
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = opcount, Post Permissions = pure, Pre-Permissions =pure
Method Name = perf
Vertex Name = opcount, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = time, Post Permissions = pure, Pre-Permissions =pure
Method Name = printHeader
Class Name = SparseMatmult
Class Name = JGFSparseMatmultBench
Method Name = main
Vertex Name = RANDOM_SEED, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_M, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_N, Post Permissions = unique, Pre-Permissions =none
Vertex Name = datasizes_nz, Post Permissions = unique, Pre-Permissions =none
Vertex Name = SPARSE_NUM_ITER, Post Permissions = unique, Pre-Permissions =none
Vertex Name = R, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = x, Post Permissions = unique, Pre-Permissions =none
Vertex Name = y, Post Permissions = unique, Pre-Permissions =none
Vertex Name = val, Post Permissions = unique, Pre-Permissions =none
Vertex Name = col, Post Permissions = unique, Pre-Permissions =none
Vertex Name = row, Post Permissions = unique, Pre-Permissions =none
Vertex Name = ytotal, Post Permissions = unique, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Method Name = test
Vertex Name = y, Post Permissions = share, Pre-Permissions =share
Vertex Name = val, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = row, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = col, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = x, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = SPARSE_NUM_ITER, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = ytotal, Post Permissions = share, Pre-Permissions =share
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
////////////////////////////////////////////////////////////////////
 * Class Name = JGFSparseMatmultBench
Class Name = JGFSparseMatmultBench
Method Name = main
Vertex Name = JGFSparseMatmultBench.RANDOM_SEED, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFSparseMatmultBench.datasizes_M, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFSparseMatmultBench.datasizes_N, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFSparseMatmultBench.datasizes_nz, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFSparseMatmultBench.SPARSE_NUM_ITER, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFSparseMatmultBench.R, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.SPARSE_NUM_ITER, Post Permissions = none, Pre-Permissions =none
Vertex Name = JGFInstrumentor.timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.x, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_N, Post Permissions = none, Pre-Permissions =none
Vertex Name = cb.R, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.y, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_M, Post Permissions = none, Pre-Permissions =none
Vertex Name = cb.val, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_nz, Post Permissions = none, Pre-Permissions =none
Vertex Name = cb.col, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.row, Post Permissions = unique, Pre-Permissions =none
Vertex Name = SparseMatmult.ytotal, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.start_time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.ytotal, Post Permissions = none, Pre-Permissions =none
Method Name = JGFsetsize
Vertex Name = cb.size, Post Permissions = share, Pre-Permissions =share
Method Name = JGFinitialise
Vertex Name = cb.x, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_N, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = cb.size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.R, Post Permissions = share, Pre-Permissions =share
Vertex Name = cb.y, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_M, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = cb.val, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_nz, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = cb.col, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.row, Post Permissions = unique, Pre-Permissions =none
Method Name = RandomVector
Vertex Name = cb.R, Post Permissions = share, Pre-Permissions =share
Method Name = JGFkernel
Vertex Name = cb.y, Post Permissions = share, Pre-Permissions =share
Vertex Name = cb.val, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.row, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.col, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.x, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.SPARSE_NUM_ITER, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = SparseMatmult.ytotal, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Method Name = test
Vertex Name = cb.y, Post Permissions = share, Pre-Permissions =share
Vertex Name = cb.val, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.row, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.col, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.x, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.SPARSE_NUM_ITER, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = SparseMatmult.ytotal, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Method Name = startTimer
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.start_time, Post Permissions = share, Pre-Permissions =share
Method Name = start
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.start_time, Post Permissions = share, Pre-Permissions =share
Method Name = stopTimer
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.start_time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Method Name = stop
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.start_time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Method Name = JGFvalidate
Vertex Name = cb.ytotal, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.size, Post Permissions = pure, Pre-Permissions =pure
Method Name = JGFtidyup
Method Name = JGFrun
Vertex Name = cb.SPARSE_NUM_ITER, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = cb.size, Post Permissions = share, Pre-Permissions =share
Vertex Name = cb.x, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_N, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = cb.R, Post Permissions = share, Pre-Permissions =share
Vertex Name = cb.y, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_M, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = cb.val, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_nz, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = cb.col, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.row, Post Permissions = unique, Pre-Permissions =none
Vertex Name = SparseMatmult.ytotal, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = cb.ytotal, Post Permissions = pure, Pre-Permissions =pure
Method Name = addTimer
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Method Name = JGFTimer
Vertex Name = JGFTimer.name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Method Name = reset
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Method Name = addOpsToTimer
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.opcount, Post Permissions = share, Pre-Permissions =share
Method Name = addops
Vertex Name = JGFTimer.opcount, Post Permissions = share, Pre-Permissions =share
Method Name = printTimer
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.opcount, Post Permissions = pure, Pre-Permissions =pure
Method Name = print
Vertex Name = JGFTimer.opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.time, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.opcount, Post Permissions = pure, Pre-Permissions =pure
Method Name = perf
Vertex Name = JGFTimer.opcount, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.time, Post Permissions = pure, Pre-Permissions =pure
Method Name = printHeader
Class Name = SparseMatmult
Class Name = JGFSparseMatmultBench
Method Name = main
Vertex Name = JGFSparseMatmultBench.RANDOM_SEED, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFSparseMatmultBench.datasizes_M, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFSparseMatmultBench.datasizes_N, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFSparseMatmultBench.datasizes_nz, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFSparseMatmultBench.SPARSE_NUM_ITER, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFSparseMatmultBench.R, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.SPARSE_NUM_ITER, Post Permissions = none, Pre-Permissions =none
Vertex Name = JGFInstrumentor.timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.x, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_N, Post Permissions = none, Pre-Permissions =none
Vertex Name = cb.R, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.y, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_M, Post Permissions = none, Pre-Permissions =none
Vertex Name = cb.val, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.datasizes_nz, Post Permissions = none, Pre-Permissions =none
Vertex Name = cb.col, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.row, Post Permissions = unique, Pre-Permissions =none
Vertex Name = SparseMatmult.ytotal, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.start_time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = cb.ytotal, Post Permissions = none, Pre-Permissions =none
Method Name = test
Vertex Name = cb.y, Post Permissions = share, Pre-Permissions =share
Vertex Name = cb.val, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.row, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.col, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.x, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = cb.SPARSE_NUM_ITER, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = SparseMatmult.ytotal, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
////////////////////////////////////////////////////////////////////////////////////////
 * Compilation Unit Names = JGFSparseMatmultBench.java
Compilation Unit Names = SparseMatmult.java
Class Name = JGFSparseMatmultBench
Method Name = JGFsetsize
Ref-Var= cb.size, Pre-Permissions=share, Post Permissions=share
Method Name = JGFinitialise
Ref-Var= cb.x, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.datasizes_N, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= cb.size, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.R, Pre-Permissions=share, Post Permissions=share
Ref-Var= cb.y, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.datasizes_M, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= cb.val, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.datasizes_nz, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= cb.col, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.row, Pre-Permissions=none, Post Permissions=unique
Method Name = RandomVector
Ref-Var= cb.R, Pre-Permissions=share, Post Permissions=share
Method Name = JGFkernel
Ref-Var= cb.y, Pre-Permissions=share, Post Permissions=share
Ref-Var= cb.val, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.row, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.col, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.x, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.SPARSE_NUM_ITER, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= sparsematmult.SparseMatmult.ytotal, Pre-Permissions=share, Post Permissions=share
Method Name = test
Ref-Var= cb.y, Pre-Permissions=share, Post Permissions=share
Ref-Var= cb.val, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.row, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.col, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.x, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.SPARSE_NUM_ITER, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= sparsematmult.SparseMatmult.ytotal, Pre-Permissions=share, Post Permissions=share
Method Name = JGFvalidate
Ref-Var= cb.ytotal, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.size, Pre-Permissions=pure, Post Permissions=pure
Method Name = JGFtidyup
Method Name = JGFrun
Ref-Var= cb.size, Pre-Permissions=share, Post Permissions=share
Ref-Var= cb.x, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.datasizes_N, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= cb.R, Pre-Permissions=share, Post Permissions=share
Ref-Var= cb.y, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.datasizes_M, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= cb.val, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.datasizes_nz, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= cb.col, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.row, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.SPARSE_NUM_ITER, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= sparsematmult.SparseMatmult.ytotal, Pre-Permissions=share, Post Permissions=share
Ref-Var= cb.ytotal, Pre-Permissions=pure, Post Permissions=pure
Method Name = main
Ref-Var= cb.size, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.x, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.datasizes_N, Pre-Permissions=none, Post Permissions=none
Ref-Var= cb.R, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.y, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.datasizes_M, Pre-Permissions=none, Post Permissions=none
Ref-Var= cb.val, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.datasizes_nz, Pre-Permissions=none, Post Permissions=none
Ref-Var= cb.col, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.row, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.SPARSE_NUM_ITER, Pre-Permissions=none, Post Permissions=none
Ref-Var= sparsematmult.SparseMatmult.ytotal, Pre-Permissions=none, Post Permissions=unique
Ref-Var= cb.ytotal, Pre-Permissions=none, Post Permissions=none
Method Name = printHeader

//////////////////////////////////////////////////////


//////////////////////////////////////////////////////

//////////////////////////////////////////////////////
Class Name = SparseMatmult
Method Name = test
Ref-Var= cb.y, Pre-Permissions=share, Post Permissions=share
Ref-Var= cb.val, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.row, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.col, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.x, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= cb.SPARSE_NUM_ITER, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= sparsematmult.SparseMatmult.ytotal, Pre-Permissions=share, Post Permissions=share

//////////////////////////////////////////////////////


//////////////////////////////////////////////////////

//////////////////////////////////////////////////////
 Milli Seconds Time = 420.399188


 */
