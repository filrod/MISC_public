package javaAlgorithms;

import java.util.*;

public class linkedListExercises {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("!!");
		list.addFirst("chill");
		list.addFirst("is");
		list.addFirst("250");
		list.addFirst("Comp");
		
		sLinkedListTraversal(list);
		
		list.clear();
		
		

	}
	
	public static void sLinkedListTraversal(LinkedList<String> l) {
		
		assert(!l.isEmpty());
		
		Iterator<String> itr = l.iterator();
		
		while (itr.hasNext()) {
			
			System.out.print(itr.next()+" ");
		}

		/*
		for (String currStr: l) {
			
			System.out.print(currStr+" ");
		}
		*/
		
	}

}
