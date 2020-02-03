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
	
	/**
	 * Depth First Algorithm
	 * 
	 * @param root  : Type tNode<E>
	 * 		The root node of the subtree.
	 * 
	 * @param order : Type String
	 * 		The order of traversal desired
	 * 
	 * @warning
	 * Can't do an inorder traversal properly unless the tree
	 * is a binary tree which is not forced here.
	 */
	public void dftRecursive(tNode<E> root, String order) {
		
		if(root==null)
			return;
		
		assert(order.equals("preorder")  || 
				   order.equals("postorder") ||
				   order.equals("inorder")     );
		
		// For preorder traversal----------------------
		if (order.equals("preorder"))
			System.out.println(root.element().toString());

		//sLinkedList<tree<E>.tNode<E>>.sLinkedListIter<tree<E>.tNode<E>> iter = this.root.children.iterator();
		
		for(tNode<E> child : root.children) {
			
			dftRecursive(child, order);
		}
		
		// For postorder traversal----------------------
		System.out.println(root.element().toString());
		
	}
	
	/**
	 * Breadth First Search algorithm.
	 * 
	 * @param root  : Type tNode<E>
	 * 		The root node of the subtree.
	 * 
	 * @param order : Type String
	 * 		The order of traversal desired
	 * 
	 * @warning
	 * Can't do an inorder traversal properly unless the tree
	 * is a binary tree which is not forced here.
	 * 
	 * Might want to use switch statement instead of if for
	 * order params.
	 */
	public void bftRecursive(tNode<E> root, String order) {
		
		if(root==null)
			return;
		
		assert(order.equals("preorder")  || 
			   order.equals("postorder") ||
			   order.equals("inorder")     );
		
		// For preorder traversal----------------------
		if (order.equals("preorder"))
			System.out.println(root.element().toString());
		
		// For inorder traversal-----------------------
		int halfLengthChildern = 0;
		int loopCounter = 0;
		if (order.equals("inorder") 
				&& root.children!=null 
				&& root.children.size()>0
				)
			halfLengthChildern = root.children.size()/2;
		// ---------------------------------------------
		
		//sLinkedList<tree<E>.tNode<E>>.sLinkedListIter<tree<E>.tNode<E>> iter = this.root.children.iterator();
		
		// Visit the children 1 by 1 ----------------------
		for(tNode<E> child : root.children) {
			
			if (halfLengthChildern>0 && loopCounter == halfLengthChildern)
				System.out.println(root.element().toString());
			
			System.out.println(child.element().toString());
			loopCounter++;
		}
		// ------------------------------------------------

		// For postorder traversal----------------------
		System.out.println(root.element().toString());
		//
		// Recursive call
		for(tNode<E> child : root.children) {
			
			for (tNode<E> grandChild : child.children)
				bftRecursive(grandChild, order);
			
		}
		
	}
	
	@Test
	void treeTraversal() {
		assertEquals(0, 0);
	}

}
