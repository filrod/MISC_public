package lists;

import java.util.Iterator;

/**
 * 
 * @author Filipe Rodrigues
 * @email  filipe.rodrigues@mail.mcgill.ca
 *
 * @param <E>
 * @param head : Type sNode<E>
 * @param tail : Type sNode<E>
 * @param size : Type int
 * 
 * @version 1.0
 * 
 * @warning All methods are to be tested
 * 
 * Note: the type <E> and casting from object to this
 * must be done better. Many methods are unfinished.
 */

public class sLinkedList<E> implements listContract<E>{
	
	// Attributes	
	private sNode<E> head;
	private sNode<E> tail;
	private int      size;
	
	// Constructor
	public sLinkedList() {
		// TODO Auto-generated constructor stub
		size = 0;
	}

	// ------------------------------------------------------
	// Encapsulated objects: a node class and an iterator
	private class sNode<E> {
		
		sNode<E>  next     = null;
		E         element  = null;
		
		public sNode(E e) {
			
			this.element = e;
			
		}
		
	}

	private class sLinkedListIter implements Iterator<E>{
		
		sNode<E> current;
		
		sLinkedListIter(sLinkedList<E> list){
			current = list.head;
		}
		
		@Override
		public boolean hasNext(){
			return (current != null);
		}

		@Override
		public E next() 
		{
			sNode<E> tmp = current;
			current = current.next;
			return tmp.element;
		}
	}
	
	@Override
	public sLinkedListIter iterator() {
		// TODO Auto-generated method stub
		sLinkedListIter iter = new sLinkedListIter(this);
		return iter;
	}
	// ------------------------------------------------------
	
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public void addFirst(E e) {
		// TODO Auto-generated method stub
		
		// Edge case: The sLinkedList is empty
		if (this.size == 0) {
			
			this.head = new sNode<E>( e );
			this.tail = this.head;
			this.size++;
			return;
		}
		
		sNode<E> tmp = this.head;
		this.head = new sNode<E>( e );
		this.head.next = tmp;
		this.size++;
	}

	@Override
	public void addLast(E e) {
		// TODO Auto-generated method stub
		
		// Edge case: The sLinkedList is empty
		if (this.size == 0) {
			
			this.head = new sNode<E>( e );
			this.tail = this.head;
			this.size++;
			return;
		}
		
		this.tail.next = new sNode<E>( e );
		this.tail = this.tail.next;
		this.size++;
		
	}

	@Override
	public void add(E e, int i) {
		/**
		 * @Overide
		 * @param e : Type E
		 * @param i : Type int
		 * 
		 * Note: not sure it works well yet.
		 * I wish to use the iterator private class.
		 */
		
		if (i==this.size) {
			addLast(e);
			return;
		}
		
		assert(i<this.size);
		
		sNode<E> currentNode = this.head;
		for (int j=1; j<=i; j++)
			currentNode = currentNode.next;
		sNode<E> tmp = currentNode;
		currentNode = new sNode<E>( (E)e );
		currentNode.next = tmp;
		this.size++;
		
		
	}

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub
		
		// Edge case: The list is empty
		assert(this.size>0);
		
		E tmpElement = this.head.element;
		this.head = this.head.next;
		this.size--;
		return tmpElement;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		
		// Edge case: The list is empty
		assert(this.size>0);
		
		E tmpElement = this.tail.element;
		sNode<E> currentNode = this.head;
		for (int j=1; j<this.size-1; j++)
			currentNode = currentNode.next;
		this.tail = currentNode;
		currentNode.next = null;
		this.size--;
		return tmpElement;
	}

	@Override
	public E remove(int i) {
		// TODO Auto-generated method stub
		
		assert(i<this.size);
		assert(i>=0);
		
		E tmpElement;
		sNode<E> currentNode = this.head;
		
		// Edge case 1
		if (i==0) {
			tmpElement = removeFirst();
			return tmpElement;
		}
		
		// Edge case 2
		if (i==this.size-1) {
			tmpElement = removeLast();
			return tmpElement;
		}
			
		for (int j=0; j<i-1; j++)
			currentNode = currentNode.next;
		
		tmpElement = currentNode.next.element;
		currentNode.next = currentNode.next.next;
		this.size--;
		
		return tmpElement;
	}

	@Override
	public E get(int i) {
		// TODO Auto-generated method stub
		
		assert(this.size!=0);
		assert(i<this.size);
		
		
		
		// Edge case 1
		if (i==0)
			return this.head.element;
		
		// Edge case 2
		if (i==this.size-1)
			return this.tail.element;
		
		sNode<E> currentNode = this.head;
		E tmpElement = currentNode.element;
		
		for (int j=0; j<=i-1; j++)
			currentNode = currentNode.next;
		
		tmpElement = currentNode.element;
		
		return tmpElement;
	}


	@Override
	/**
	 * @unfinished
	 */
	public boolean getElement(E e) {
		return false;
	}


}
