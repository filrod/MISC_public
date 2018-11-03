package javaAlgorithms;

import java.util.ArrayList;
import java.util.HashSet;

public class iterviewPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//System.out.println(reverseStr("reversed"));
		ArrayList<String> list = new ArrayList<>();
		for(int i=0; i<200; i++) {
			if (i%50==0) {
				list.add("Hello");
			}
			else {
				list.add(""+i);
			}
		}
		System.out.println(list.toString());
		
		removeDuplicates(list);
	}
	
	public static String reverseStr(String toBReversed) {
		/*
		 * Reverses a string without using libraries.
		 * 
		 * Parameter
		 * ------------------------------------------------
		 * toBReversed: String
		 * 
		 *     Input string we wish reversed
		 *     
		 * Return
		 * ------------------------------------------------
		 * reversed: String
		 * 
		 *     The a reversed version of the original
		 *     string.
		 */

		StringBuilder reversed = new StringBuilder(
												   toBReversed
												   .length()
												   );
		
		for (int i=toBReversed.length()-1; i>=0; i--) {
			reversed.append(toBReversed.charAt(i));
		}
		
		return reversed.toString();
	}
	
	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> nonUnique) {
		/*
		 * Removes duplicate values in an ArrayList
		 * 
		 * Parameters
		 * ------------------------------------------------
		 * nonUnique: ArrayList<E>
		 * 
		 *     An ArrayList, of any type, that may contain
		 *     duplicate values.
		 * 
		 * Return
		 * ------------------------------------------------
		 * unique: ArrayList<E>
		 * 
		 *     A basis, no repeating elements, of nonUnique.
		 *     
		 * Note: Uses HashSet which is built in and deals
		 * with this removing duplicates.
		 */
				
		ArrayList<E> unique = new ArrayList<E>(new HashSet<E>(nonUnique));
		
		System.out.println(unique.toString());
		return unique;
	}
}
