

package aeminium.fft;

import java.util.Random;

public class SeqFFT {
	
	public static Complex[] x;
	
	
	public static Complex[] sequentialFFT(Complex[] x) {
	
		int N = x.length;
		// base case
		if (N == 1) 
			return new Complex[]{x[0]};
		// radix 2 Cooley-Tukey FFT
		if (N % 2 != 0) {
			throw new RuntimeException("N is not a power of 2");
		}
		// fft of even terms
		Complex[] even = new Complex[N / 2];
		for (int k = 0; k < N / 2; k++) {
			even[k] = x[2 * k];
		}
		Complex[] q = sequentialFFT(even);
		// fft of odd terms
		Complex[] odd = even; // reuse the array
		for (int k = 0; k < N / 2; k++) {
			odd[k] = x[2 * k + 1];
		}
		Complex[] r = sequentialFFT(odd);
		// combine
		Complex[] y = new Complex[N];
		for (int k = 0; k < N / 2; k++) {
			double kth = -2 * k * Math.PI / N;
			Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
			y[k] = q[k].plus(wk.times(r[k]));
			y[k + N / 2] = q[k].minus(wk.times(r[k]));
		}
		return y;
	}
}
class Client{
		
		public static void main(String[] args) { 
	 
	    long start = System.nanoTime();// do-nothing
	        
	    FFTUtility.createRandomComplexArray(SeqFFT.x,1000);
	    
	    SeqFFT.sequentialFFT(SeqFFT.x); // 
        
	    FFTUtility.show(SeqFFT.x, "Result");
	    
        long end = System.nanoTime();//do-nothing
    	
        long elapsedTime = end - start;//do-nothing
    	
    	double seconds = (double)elapsedTime / 1000000.0;//do-nothing
    	
    	System.out.println(" Milli Seconds Time = "+seconds);//do-nothing
    }
}
