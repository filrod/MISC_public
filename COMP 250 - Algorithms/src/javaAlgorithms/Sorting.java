package javaAlgorithms;

import java.util.Arrays;

import org.junit.Test;

public class Sorting {

	public static void main(String[] args) {
		
		
		System.out.println(  fibonacciCheckSame( 30, new int[]{2, 5} )  );
		/*
		int[] a = getRandArr();
		printArray(a);
		bubbleSort(a);
		printArray(a);
		
		int[] b = getRandArr();
		printArray(b);
		selectionSort(b);
		printArray(b);
		*/
	}
	
	private static int[] getRandArr() {
		
		int[] randArr = new int[(int)(Math.random()*100)];
		
		for(int i=0; i<randArr.length; i++) {
			randArr[i] = (int)(Math.random()*100);
		}
		return randArr;
	}
	
	private static void printArray(int[] intArray) {
		
		System.out.print("\n[\n");
		
		for (int i=0; i<intArray.length; i++) {
			
			if (i!=intArray.length-1)
				System.out.print(intArray[i]+", ");
			else
				System.out.print(intArray[i]);
			
			if ((i+1)%15==0)
				System.out.println();
		}
		System.out.print("\n]\n");
	}
	
	public static int[] bubbleSort(int[] unsorted) {
		
		Boolean isUnsorted = true;
		//int counter = 0;
		
		while(isUnsorted) {
			int tmp;
			isUnsorted = false;
			for (int i=0; i<unsorted.length-1; i++) {
				//counter+=1;
				
				if (unsorted[i]>unsorted[i+1]) {
					
					tmp            = unsorted[i];
					unsorted[i]    = unsorted[i+1];
					unsorted[i+1]  = tmp;
					isUnsorted = true;
				}
			}
		}
		//System.out.println(1.0*counter/Math.pow(unsorted.length, 2)+"*n^2");
		return unsorted;
	}
	
	public static int[] selectionSort(int[] unsorted) {
		
		int tmpVal;
		
		for (int unsortedI_o = 0; unsortedI_o < unsorted.length; unsortedI_o++) {
			for (int i=unsortedI_o; i<unsorted.length; i++) {
				
				
				if (unsorted[i]<unsorted[unsortedI_o]) {
					tmpVal = unsorted[unsortedI_o];
					unsorted[unsortedI_o] = unsorted[i];
					unsorted[i] = tmpVal;
				}
				
			}
		}
		return unsorted;
	}
	
	private static int fibonacciIterative(int p_Num) {
		
		if (p_Num<3)
			return 1;
		
		int tmp;
		
		int f_a    = 1; 
		int f_b    = 1;
		
		for (int i=2; i<p_Num; i++) {
			tmp = f_b;
			f_b = f_a + f_b;
			f_a = tmp;
		}
		return f_b;
	}
	
	private static int fibonacciRecursive(int p_Num) {
		
		if (p_Num < 3)
			return 1;
		
		return fibonacciRecursive(p_Num-1) + fibonacciRecursive(p_Num-2);
	}
	
	private static boolean fibonacciCheckSame(int p_sampleSize, int[] p_sampleRange) {
		
		// Must handle exception when p_sampleRange.length > 2
		
		final int MIN = p_sampleRange[0];
		final int MAX = p_sampleRange[1];
		
		// ----------- User constraints -------------------
		if (p_sampleRange[1] - p_sampleRange[0] > 6) {
			System.out.println("Sample range too big default [0, 5) was made");
			p_sampleRange[0] = 0;
			p_sampleRange[1] = 5;
		}
		
		if (p_sampleRange[0] > 4 || p_sampleRange[1] > 6) {
			System.out.println("Argumet in sample range too big default [0, 5) was made");
			p_sampleRange[0] = 0;
			p_sampleRange[1] = 5;
		}
		// --------------------------------------------------
		
		int num;
		
		for (int i=0; i<p_sampleSize; i++) {
			
			num = MIN + (int)(Math.random() * ((MAX - MIN)));
			
			if (fibonacciRecursive(num) != fibonacciIterative(num)) {
				
				System.out.println("nb: " +num+ "\nRecursive: " +fibonacciRecursive(num)+ "\nIterative: " +fibonacciIterative(num));
				return false;
			}
				
		}
		
		return true;
	}
	
	/*@Test
	public void testFib(int min, int max) {
		int num = min + (int)(Math.random() * ((max - min)));
		assertEquals(fibonacciRecursive(num), fibonacciIterative(num));
	}
	*/
	// Searching
	
	public static int binSearch(double[] p_orderedArrayToSearch, double p_val) throws IllegalArgumentException{
		
		if (p_orderedArrayToSearch == null || p_orderedArrayToSearch.length ==0) {
			System.out.println("The array must have length greater than 0");
			throw new IllegalArgumentException();
		}
		
		int l=0;
		int r= p_orderedArrayToSearch.length-1;
		int mid;
		
		while(l<r) {
			
			mid = (r+l)/2;
			if (p_val<p_orderedArrayToSearch[mid]) {
				l = mid+1;
			} else if (p_val>p_orderedArrayToSearch[mid]) {
				r = mid-1;
			} else {
				return mid;
			}
		}
		return l;
	}
	
	/**
	 * binarySearchRecursive() searches an int[] and tries to find 
	 * pKey, the integer passed. If found this method returns true
	 * and if not, it returns false. This method makes recursive 
	 * calls each time into an array of half the size until it 
	 * reaches the base case: pData's length is of one element.
	 * 
	 * @param pData      type: int[], 
	 * 					 this is the array to search in
	 * 
	 * @param pKey       type: int,
	 * 					 this is the element we wish to find
	 * 
	 * @return           type boolean,
	 *                   true if pKey was found in pData, 
	 *                   false otherwise
	 */
	public static boolean binarySearchRecursive(int[] pData, int pKey) {
		
		assert(pData.length>0);
		
		if (pData.length==1)
			return pData[0]==pKey;
		
		int mid = (pData.length)/2;
		if (pKey >= pData[mid])
			return binarySearchRecursive(
					Arrays.copyOfRange(pData, mid, pData.length), 
					pKey
					);
		else
			return binarySearchRecursive(
					Arrays.copyOfRange(pData, 0, mid), 
					pKey
					);
	}
	
}
