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
*                  Original version of this code by                       *
*                 Gabriel Zachmann (zach@igd.fhg.de)                      *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/

/**
* Class SeriesTest
*
* Performs the transcendental/trigonometric portion of the
* benchmark. This test calculates the first n fourier
* coefficients of the function (x+1)^x defined on the interval
* 0,2 (where n is an arbitrary number that is set to make the
* test last long enough to be accurately measured by the system
* clock). Results are reported in number of coefficients calculated
* per sec.
*
* The first four pairs of coefficients calculated shoud be:
* (2.83777, 0), (1.04578, -1.8791), (0.2741, -1.15884), and
* (0.0824148, -0.805759).
*/

package jomp.series; 
import jomp.jgfutil.JGFInstrumentor;

class SeriesTest 
{

// Declare class data.


int array_rows; 
double [][] TestArray;  // Array of arrays.


/*
* buildTestData
*
*/

// Instantiate array(s) to hold fourier coefficients.

void buildTestData()
{
    // Allocate appropriate length for the double array of doubles.
    TestArray = new double [2][array_rows];
}



/*
* Do
*
* This consists of calculating the
* first n pairs of fourier coefficients of the function (x+1)^x on
* the interval 0,2. n is given by array_rows, the array size.
* NOTE: The # of integration steps is fixed at 1000. 
*/

void Do()
{
    double omega;       // Fundamental frequency.

    // Start the stopwatch.

    //JGFInstrumentor.startTimer("Section2:Series:Kernel"); 

    // Calculate the fourier series. Begin by calculating A[0].

    TestArray[0][0] = TrapezoidIntegrate((double)0.0, // Lower bound.
                            (double)2.0,            // Upper bound.
                            1000,                    // # of steps.
                            (double)0.0,            // No omega*n needed.
                            0) / (double)2.0;       // 0 = term A[0].

    // Calculate the fundamental frequency.
    // ( 2 * pi ) / period...and since the period
    // is 2, omega is simply pi.

    omega = (double) 3.1415926535897932;

    //omp parallel for 
    for (int i = 1; i < array_rows; i++)
    {
        // Calculate A[i] terms. Note, once again, that we
        // can ignore the 2/period term outside the integral
        // since the period is 2 and the term cancels itself
        // out.

        TestArray[0][i] = TrapezoidIntegrate((double)0.0,
                          (double)2.0,
                          1000,
                          omega * (double)i,
                          1);                       // 1 = cosine term.

        // Calculate the B[i] terms.

        TestArray[1][i] = TrapezoidIntegrate((double)0.0,
                          (double)2.0,
                          1000,
                          omega * (double)i,
                          2);                       // 2 = sine term.
    }


    // Stop the stopwatch.

    //JGFInstrumentor.stopTimer("Section2:Series:Kernel"); 
}

/*
* TrapezoidIntegrate
*
* Perform a simple trapezoid integration on the function (x+1)**x.
* x0,x1 set the lower and upper bounds of the integration.
* nsteps indicates # of trapezoidal sections.
* omegan is the fundamental frequency times the series member #.
* select = 0 for the A[0] term, 1 for cosine terms, and 2 for
* sine terms. Returns the value.
*/

private double TrapezoidIntegrate (double x0,     // Lower bound.
                        double x1,                // Upper bound.
                        int nsteps,               // # of steps.
                        double omegan,            // omega * n.
                        int select)               // Term type.
{
    double x;               // Independent variable.
    double dx;              // Step size.
    double rvalue;          // Return value.

    // Initialize independent variable.

    x = x0;

    // Calculate stepsize.

    dx = (x1 - x0) / (double)nsteps;

    // Initialize the return value.

    rvalue = thefunction(x0, omegan, select) / (double)2.0;

    // Compute the other terms of the integral.

    if (nsteps != 1)
    {
            --nsteps;               // Already done 1 step.
            while (--nsteps > 0)
            {
                    x += dx;
                    rvalue += thefunction(x, omegan, select);
            }
    }

    // Finish computation.

    rvalue = (rvalue + thefunction(x1,omegan,select) / (double)2.0) * dx;
    return(rvalue);
}

/*
* thefunction
*
* This routine selects the function to be used in the Trapezoid
* integration. x is the independent variable, omegan is omega * n,
* and select chooses which of the sine/cosine functions
* are used. Note the special case for select=0.
*/

private double thefunction(double x,      // Independent variable.
                double omegan,              // Omega * term.
                int select)                 // Choose type.
{

    // Use select to pick which function we call.

    switch(select)
    {
        case 0: return(Math.pow(x+(double)1.0,x));

        case 1: return(Math.pow(x+(double)1.0,x) * Math.cos(omegan*x));

        case 2: return(Math.pow(x+(double)1.0,x) * Math.sin(omegan*x));
    }

    // We should never reach this point, but the following
    // keeps compilers from issuing a warning message.

    return (0.0);
}

/*
* freeTestData
*
* Nulls array that is created with every run and forces garbage
* collection to free up memory.
*/

void freeTestData()
{
    TestArray = null;    // Destroy the array.
    System.gc();         // Force garbage collection.
}


}
/*
Class Name = JGFSeriesBench
Class Name = JGFSeriesBenchSizeB
Method Name = main
Vertex Name = datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = array_rows, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Method Name = JGFsetsize
Vertex Name = size, Post Permissions = share, Pre-Permissions =share
Method Name = JGFinitialise
Vertex Name = array_rows, Post Permissions = share, Pre-Permissions =share
Vertex Name = datasizes, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = TestArray, Post Permissions = unique, Pre-Permissions =none
Method Name = buildTestData
Vertex Name = TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = array_rows, Post Permissions = pure, Pre-Permissions =pure
Method Name = JGFkernel
Vertex Name = TestArray, Post Permissions = share, Pre-Permissions =share
Vertex Name = array_rows, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Method Name = Do
Vertex Name = TestArray, Post Permissions = share, Pre-Permissions =share
Vertex Name = array_rows, Post Permissions = pure, Pre-Permissions =pure
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
Method Name = TrapezoidIntegrate
Method Name = thefunction
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
Vertex Name = TestArray, Post Permissions = pure, Pre-Permissions =pure
Method Name = JGFtidyup
Vertex Name = TestArray, Post Permissions = unique, Pre-Permissions =none
Method Name = freeTestData
Vertex Name = TestArray, Post Permissions = unique, Pre-Permissions =none
Method Name = JGFrun
Vertex Name = array_rows, Post Permissions = share, Pre-Permissions =share
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = share, Pre-Permissions =share
Vertex Name = opname, Post Permissions = share, Pre-Permissions =share
Vertex Name = size, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = datasizes, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = TestArray, Post Permissions = unique, Pre-Permissions =none
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
Class Name = SeriesTest
Class Name = JGFSeriesBenchSizeB
Method Name = main
Vertex Name = datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = array_rows, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Method Name = buildTestData
Vertex Name = TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = array_rows, Post Permissions = pure, Pre-Permissions =pure
Method Name = Do
Vertex Name = TestArray, Post Permissions = share, Pre-Permissions =share
Vertex Name = array_rows, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = on, Post Permissions = share, Pre-Permissions =share
Vertex Name = name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = time, Post Permissions = share, Pre-Permissions =share
Vertex Name = calls, Post Permissions = share, Pre-Permissions =share
Method Name = TrapezoidIntegrate
Method Name = thefunction
Method Name = freeTestData
Vertex Name = TestArray, Post Permissions = unique, Pre-Permissions =none
Class Name = JGFSeriesBenchSizeB
Class Name = JGFSeriesBenchSizeB
Method Name = main
Vertex Name = datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = array_rows, Post Permissions = unique, Pre-Permissions =none
Vertex Name = timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = start_time, Post Permissions = unique, Pre-Permissions =none
Method Name = printHeader*/
//////////////////////////////////////////////////////////////////////////
/*Class Name = JGFSeriesBench
Class Name = JGFSeriesBenchSizeB
Method Name = main
Vertex Name = JGFSeriesBench.datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.array_rows, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFInstrumentor.timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.datasizes, Post Permissions = none, Pre-Permissions =none
Vertex Name = se.TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.start_time, Post Permissions = unique, Pre-Permissions =none
Method Name = JGFsetsize
Vertex Name = se.size, Post Permissions = share, Pre-Permissions =share
Method Name = JGFinitialise
Vertex Name = se.array_rows, Post Permissions = share, Pre-Permissions =share
Vertex Name = se.datasizes, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = se.size, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = se.TestArray, Post Permissions = unique, Pre-Permissions =none
Method Name = buildTestData
Vertex Name = se.TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.array_rows, Post Permissions = pure, Pre-Permissions =pure
Method Name = JGFkernel
Vertex Name = se.TestArray, Post Permissions = share, Pre-Permissions =share
Vertex Name = se.array_rows, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Method Name = Do
Vertex Name = se.TestArray, Post Permissions = share, Pre-Permissions =share
Vertex Name = se.array_rows, Post Permissions = pure, Pre-Permissions =pure
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
Method Name = TrapezoidIntegrate
Method Name = thefunction
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
Vertex Name = se.TestArray, Post Permissions = pure, Pre-Permissions =pure
Method Name = JGFtidyup
Vertex Name = se.TestArray, Post Permissions = unique, Pre-Permissions =none
Method Name = freeTestData
Vertex Name = se.TestArray, Post Permissions = unique, Pre-Permissions =none
Method Name = JGFrun
Vertex Name = se.array_rows, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.opcount, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = se.size, Post Permissions = share, Pre-Permissions =share
Vertex Name = se.datasizes, Post Permissions = immutable, Pre-Permissions =immutable
Vertex Name = se.TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.start_time, Post Permissions = share, Pre-Permissions =share
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
Class Name = SeriesTest
Class Name = JGFSeriesBenchSizeB
Method Name = main
Vertex Name = JGFSeriesBench.datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.array_rows, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFInstrumentor.timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.datasizes, Post Permissions = none, Pre-Permissions =none
Vertex Name = se.TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.start_time, Post Permissions = unique, Pre-Permissions =none
Method Name = buildTestData
Vertex Name = se.TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.array_rows, Post Permissions = pure, Pre-Permissions =pure
Method Name = Do
Vertex Name = se.TestArray, Post Permissions = share, Pre-Permissions =share
Vertex Name = se.array_rows, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFInstrumentor.timers, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.on, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.name, Post Permissions = pure, Pre-Permissions =pure
Vertex Name = JGFTimer.start_time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.time, Post Permissions = share, Pre-Permissions =share
Vertex Name = JGFTimer.calls, Post Permissions = share, Pre-Permissions =share
Method Name = TrapezoidIntegrate
Method Name = thefunction
Method Name = freeTestData
Vertex Name = se.TestArray, Post Permissions = unique, Pre-Permissions =none
Class Name = JGFSeriesBenchSizeB
Class Name = JGFSeriesBenchSizeB
Method Name = main
Vertex Name = JGFSeriesBench.datasizes, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.array_rows, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFInstrumentor.timers, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.name, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opname, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.time, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.calls, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.opcount, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.on, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.size, Post Permissions = unique, Pre-Permissions =none
Vertex Name = se.datasizes, Post Permissions = none, Pre-Permissions =none
Vertex Name = se.TestArray, Post Permissions = unique, Pre-Permissions =none
Vertex Name = JGFTimer.start_time, Post Permissions = unique, Pre-Permissions =none
Method Name = printHeader

///////////////////////////////////////////////////////////////////////////////////////////////////
Compilation Unit Names = JGFSeriesBench.java
Compilation Unit Names = SeriesTest.java
Compilation Unit Names = JGFSeriesBenchSizeB.java
Class Name = JGFSeriesBench

//////////////////////////////////////////////////////


//////////////////////////////////////////////////////

//////////////////////////////////////////////////////
Class Name = JGFSeriesBenchSizeB
Method Name = main
Ref-Var= series.SeriesTest.datasizes, Pre-Permissions=none, Post Permissions=unique
Ref-Var= se.size, Pre-Permissions=none, Post Permissions=unique
Ref-Var= se.array_rows, Pre-Permissions=none, Post Permissions=unique
Ref-Var= se.datasizes, Pre-Permissions=none, Post Permissions=none
Ref-Var= se.TestArray, Pre-Permissions=none, Post Permissions=unique
Method Name = JGFsetsize
Ref-Var= se.size, Pre-Permissions=share, Post Permissions=share
Method Name = JGFinitialise
Ref-Var= se.array_rows, Pre-Permissions=share, Post Permissions=share
Ref-Var= se.datasizes, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= se.size, Pre-Permissions=pure, Post Permissions=pure
Ref-Var= se.TestArray, Pre-Permissions=none, Post Permissions=unique
Method Name = buildTestData
Ref-Var= se.TestArray, Pre-Permissions=none, Post Permissions=unique
Ref-Var= se.array_rows, Pre-Permissions=pure, Post Permissions=pure
Method Name = JGFkernel
Ref-Var= se.TestArray, Pre-Permissions=share, Post Permissions=share
Ref-Var= se.array_rows, Pre-Permissions=pure, Post Permissions=pure
Method Name = Do
Ref-Var= se.TestArray, Pre-Permissions=share, Post Permissions=share
Ref-Var= se.array_rows, Pre-Permissions=pure, Post Permissions=pure
Method Name = TrapezoidIntegrate
Method Name = thefunction
Method Name = JGFvalidate
Ref-Var= se.TestArray, Pre-Permissions=pure, Post Permissions=pure
Method Name = JGFtidyup
Ref-Var= se.TestArray, Pre-Permissions=none, Post Permissions=unique
Method Name = freeTestData
Ref-Var= se.TestArray, Pre-Permissions=none, Post Permissions=unique
Method Name = JGFrun
Ref-Var= se.size, Pre-Permissions=share, Post Permissions=share
Ref-Var= se.array_rows, Pre-Permissions=share, Post Permissions=share
Ref-Var= se.datasizes, Pre-Permissions=immutable, Post Permissions=immutable
Ref-Var= se.TestArray, Pre-Permissions=none, Post Permissions=unique

//////////////////////////////////////////////////////


//////////////////////////////////////////////////////

//////////////////////////////////////////////////////
Class Name = SeriesTest
Method Name = buildTestData
Ref-Var= se.TestArray, Pre-Permissions=none, Post Permissions=unique
Ref-Var= se.array_rows, Pre-Permissions=pure, Post Permissions=pure
Method Name = Do
Ref-Var= se.TestArray, Pre-Permissions=share, Post Permissions=share
Ref-Var= se.array_rows, Pre-Permissions=pure, Post Permissions=pure
Method Name = TrapezoidIntegrate
Method Name = thefunction
Method Name = freeTestData
Ref-Var= se.TestArray, Pre-Permissions=none, Post Permissions=unique
Method Name = main
Ref-Var= series.SeriesTest.datasizes, Pre-Permissions=none, Post Permissions=unique
Ref-Var= se.size, Pre-Permissions=none, Post Permissions=unique
Ref-Var= se.array_rows, Pre-Permissions=none, Post Permissions=unique
Ref-Var= se.datasizes, Pre-Permissions=none, Post Permissions=none
Ref-Var= se.TestArray, Pre-Permissions=none, Post Permissions=unique
Method Name = printHeader

//////////////////////////////////////////////////////


//////////////////////////////////////////////////////

//////////////////////////////////////////////////////
 Milli Seconds Time = 810.092321

*
*/