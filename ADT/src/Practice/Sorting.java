package Practice;

import java.util.ArrayList;

public class Sorting {

	public static void main(String[] args) {
		
		ArrayList<Double> randList = makeUnsortedArrList();
		
		printArrList(randList);
		
		ArrayList<Double> randSList = makeSortedArrList();
		
		printArrList(randSList);
		
		printArrList(mergeToSorted(randSList, randList));

	}
	
	private static ArrayList<Double> makeUnsortedArrList(){
		
		ArrayList<Double> randList = new ArrayList<Double>();
		
		int size = (int) (Math.random()*100);
		
		for (int i=0; i<size; i++) {
			System.out.println("        " +i);
			randList.add((double) ((int)10*Math.random()));
		}
		return randList;
	}
	
	private static ArrayList<Double> makeSortedArrList(){
		
		ArrayList<Double> randList = new ArrayList<Double>();
		
		int size = (int) (Math.random()*100 + Math.random()*100);
		
		for (int i=0; i<size; i++) {
			System.out.println(i);
			randList.add(i, (double) i);
		}
		System.out.println("Done");
		return randList;
	}
	
	public static <T> void printArrList(ArrayList<T> a) {
		
		System.out.println("Size: " +a.size());
		System.out.print("\n[  ");
		
		for (int i=0; i<a.size(); i++)
			System.out.print(a.get(i)+ "  ");
		System.out.print("]/n\n");
	}
	
	/*
	 * To be rigorously tested. Seems to work fine except maybe the natural flooring of
	 * the Average(l, r) might introduce errors.
	 */
	private static <T> ArrayList<T> mergeToSorted(ArrayList<T> sorted, ArrayList<T> unsorted){
		
		int leftIndex = 0;
		int rightIndex = sorted.size()-1;
		
		System.out.println("--------------------mergeToSorted-------------------------------");
		System.out.println("Size of sorted: " +sorted.size());
		System.out.println("l: " +leftIndex);
		System.out.println("r: " +rightIndex);
		
		for (int i=0; i<unsorted.size(); i++) {
			
			while(leftIndex != rightIndex) {
				if((double)unsorted.get(i)<(double)sorted.get((rightIndex+leftIndex)/2)) {
					rightIndex = (rightIndex+leftIndex)/2;
				} else if((double)unsorted.get(i)>(double)sorted.get((rightIndex+leftIndex)/2)) {
					leftIndex = (rightIndex+leftIndex)/2;
				} else if ((double)unsorted.get(i)==(double)sorted.get(leftIndex)) {
					sorted.add(leftIndex, unsorted.get(rightIndex));
					break;
				} else if ((double)unsorted.get(i)==(double)sorted.get(rightIndex)) {
					sorted.add(rightIndex, unsorted.get(rightIndex));
					break;
				}
				
				if (leftIndex+1 == rightIndex) {
					sorted.add(rightIndex, unsorted.get(i));
					break;
				}
				//System.out.println("\nl: " +leftIndex);
				//System.out.println("r: " +rightIndex);
			}
			//System.out.println("\n\nSize: " +sorted.size());
			leftIndex = 0;
			rightIndex = sorted.size()-1;
		}
		return sorted;
	}
	
	private static <T> int listIntersection(ArrayList<T> a, ArrayList<T> b){
		int total = 0;
		
		for (int i=0; i<a.size(); i++) {
			for(int j=0; j<b.size(); j++) {
				if (a.get(i) == b.get(j))
					total++;
			}
		}
		return total;
		
	}
}
