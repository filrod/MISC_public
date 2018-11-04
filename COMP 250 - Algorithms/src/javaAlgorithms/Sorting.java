package javaAlgorithms;

public class Sorting {

	public static void main(String[] args) {
		int[] a = getRandArr();
		printArray(a);
		bubbleSort(a);
		printArray(a);
		
		int[] b = getRandArr();
		printArray(b);
		selectionSort(b);
		printArray(b);
		

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
					tmpVal = unsorted[unsortedI_o];;
					unsorted[unsortedI_o] = unsorted[i];
					unsorted[i] = tmpVal;
				}
				
			}
		}
		return unsorted;
	}
	
	

}
