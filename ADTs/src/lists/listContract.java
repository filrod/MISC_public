package lists;

public interface listContract<E> extends Iterable<E>{
	
	public int size();
	
	public void addFirst(E e);
	public void addLast(E e);
	public void add(E e, int i);
	
	public E removeFirst();
	public E removeLast();
	public E remove(int i);
	
	public E  get(int i);
	public boolean getElement(E e);

}
