package aeminium.quicksort;

public class SeqQuickSort {
	
	static long[] original_array;
	
	public static void main(String[] args) {
		
		long start = System.nanoTime();// do -nothing
    	
    	int size = QuickSort.DEFAULT_SIZE;

		ArrayHelper.generateRandomArray(original_array,size);// include this exprssion in 
	
		SeqQuickSort.sort(original_array);
			
	     System.out.println("Sorted: " + ArrayHelper.checkArray(original_array));

    	long elapsedTime = System.nanoTime()-start;//do-nothing
    	
    	double ms = (double) elapsedTime / 1000000.0;//do-nothing
    	
    	System.out.println(" Milli Seconds Time = "+ms);//do-nothing	
	}

	public static void sort(long[] original_array) {
		qsort_seq(original_array, 0, original_array.length - 1);
	}

	public static void qsort_seq(long[] data, int left, int right) {		
		int index = QuickSort.partition(data, left, right);
		if (left < index - 1) qsort_seq(data, left, index - 1);
		if (index < right) qsort_seq(data, index, right);
	}

}


 
