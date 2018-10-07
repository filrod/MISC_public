package javaAlgorithms;

import java.util.Scanner;

public class ArrayStructures {
	
	///////////////////////////////////////////////////
	//                    Fields                     //
	///////////////////////////////////////////////////
	
	private int[] theArray = new int[50];
	private int arraySize = 10;
	
	///////////////////////////////////////////////////
	//                    Methods                   //
	///////////////////////////////////////////////////

	public static void main(String[] args) {
		
		// Create new Array 
		ArrayStructures newArray = new ArrayStructures();
		
		// Generate and print this array
		newArray.genRandArr();
		newArray.printArr();
		
		
		// User input 
		Scanner input = new Scanner(System.in);
		
		System.out.println(
				           "An array of random "
				           + "numbers from 10 to "
				           + "19 of 10 slots has "
				           + "been created."
				           + "\n"
				           + "\n"
				           + "Use: Find index 3"
				           + "\n"
				           + "To find the third "
				           + "element of the array."
				           + ""
				           + "\n"
				           + "Use: Find 15"
				           + "\n"
				           + "To find the first index"
				           + " that contains 15."
				           + ""
				           + "\n"
				           + "Use: Contains 15"
				           + "\n"
				           + "To get a confirmation"
				           + " (yes/no) to the question."
				           );
		
		String command = "" + input.nextLine().toLowerCase();
		int commandInt;
		
		while(!input.nextLine().toLowerCase().contains("quit")) {
			
			try {
				
				commandInt =  Integer.parseInt(command.replaceAll("[^0-9]+", " "));
						
				if (command.contains("find index")) 
				{
				
					System.out.println(
							           newArray
							           .getValueAtIndex(commandInt)
					                   );
				} else if (
						   command.contains("find") 
						   && !command.contains("contains")
						   ) 
				{
					
					if(newArray.valIntheArray(commandInt)) {
						
						System.out.println(
						                   newArray
						                   .Find(commandInt)
						                   );	
					} else {
						System.out.println("This value is not in the array!");
					}
					
				} else if(command.contains("Contains")) {
					
	
					if(newArray.valIntheArray(commandInt)) {
						System.out.println("Yes!");
					} else {
						
						System.out.println("No");
					}
					
				} else {
					
					System.out.println("Wrong input!");
					System.out.println(
							           "An array of random "
							           + "numbers from 10 to "
							           + "19 of 10 slots has "
							           + "been created."
							           + "\n"
							           + "\n"
							           + "Use: Find index 3"
							           + "\n"
							           + "To find the third "
							           + "element of the array."
							           + ""
							           + "\n"
							           + "Use: Find 15"
							           + "\n"
							           + "To find the first index"
							           + " that contains 15."
							           + ""
							           + "\n"
							           + "Use: Contains 15"
							           + "\n"
							           + "To get a confirmation"
							           + " (yes/no) to the question."
							           );
				}
				command = "" + input.nextLine().toLowerCase();
			} catch(NumberFormatException e){
				System.out.println(e.getMessage());
			}
			
		}
			input.close();

	}

	
	public int Find(int value) {
		/*
		 * Finds the first occurrence of value in
		 * theArray.
		 * 
		 * Parameters
		 * --------------------------------------------
		 * value: int
		 * 
		 *     The value we wish to find in theArray.
		 *     
		 * Return
		 * --------------------------------------------
		 * index: int 
		 * 
		 *     The index of the first occurrence of value
		 *     of -1 if error.
		 */
		int index = -1;
		
		for (int i=0; i < arraySize; i++) {
			
			if (theArray[i]>index)
				index = theArray[i];
		}
		
		return index;
	}


	public void genRandArr() {
		/*
		 * Generate a random array with values ranging
		 * from 10 to 20.
		 */
		
		for(int i=0; i<arraySize; i++) {
			theArray[i] = (int)(Math.random()*10)+10;
		}
	}
	
	public void printArr() {
		/*
		 * Prints theArray element by element.
		 */
		
		System.out.println("----------");
		for(int i=0; i<arraySize; i++) {
			
			System.out.print("| " +i+ " | ");
			System.out.println(theArray[i] + " |");
			System.out.println("----------");
			
		}
	}

	public int getValueAtIndex(int index) {
		/*
		 * Gets value of theArray at passed index.
		 * 
		 * Parameter
		 * --------------------------------------------
		 * index: int
		 * 
		 *     The passed index we wish to search in.
		 *     
		 * Return
		 * --------------------------------------------
		 * value: int
		 * 
		 *     The value at the passed index
		 *     or the index if invalid.
		 *     
		 * Note: Set-up try catch!!!
		 */
		
		if(index<arraySize) return theArray[index];
		
		else return index;
	}
	
	public boolean valIntheArray(int value) {
		
		boolean isIn = false;
		
		for (int i=0; i<arraySize; i++) {
			if (value == theArray[i])
				isIn = true;
		}
		return isIn;
	}	
}
