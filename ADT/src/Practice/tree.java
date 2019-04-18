package Practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Practice.sLinkedList.sLinkedListIter;

public class tree<E> {

	tNode<E> root;
	public tree(tNode<E> root) {
		// TODO Auto-generated constructor stub
		this.root = root;
	}
	
	public class tNode<E>{
		
		sLinkedList<tNode<E>> children = new sLinkedList<tNode<E>>();
		E e;
		public tNode(E e, sLinkedList<tNode<E>> children) {
			
			this.e = e;
			this.children = children;
		}
		
		public E element() {
			return this.e; 
		}
	}
	
	public void bftRecursive(tNode<E> root) {
		
		if(root==null)
			return;
		System.out.println(root.element().toString());
		//sLinkedList<tree<E>.tNode<E>>.sLinkedListIter<tree<E>.tNode<E>> iter = this.root.children.iterator();
		
		for(tNode<E> child : root.children) {
			
			bftRecursive(child);
		}
		
	}
	
	@Test
	void treeTraversal() {
		assertEquals(0, 0);
	}

}
