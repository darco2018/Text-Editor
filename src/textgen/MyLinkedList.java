package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Dariusz Ustrzycki - the implementation of all the methods
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

/** Creates a new empty LinkedList */
	public MyLinkedList() {
		
		head = new LLNode<E>();
		tail = new LLNode<E>();
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		add(size(), element );
		
		// return true if  Returns true if this collection changed as a result of the call. (Returns false 
		//if this collection does not permit duplicates and already contains the specified element.)
				
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 )
			throw new IndexOutOfBoundsException("Index less than zero");	
		
		if (isEmpty())
			throw new IndexOutOfBoundsException("The list is empty");
		
		if (index >= size() )
			throw new IndexOutOfBoundsException("Index equal to or larger than the size of the list");	
			
		return nodeAt(index).data ;
	}
	
	public boolean isEmpty(){
		
		return (head.next == tail && tail.prev == head) ? true : false;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (index < 0 )
			throw new IndexOutOfBoundsException("Index less than zero");	
		
		if (index > size() )
			throw new IndexOutOfBoundsException("Index greater than the size of the list");
		
		if (element == null)
			throw new NullPointerException("The element to set can't be null!");
		
		LLNode<E> nextNode = nodeAt(index);		
		// create a new node inserted between prevNode and nextNode
		new LLNode<E> (element, nextNode.prev, nextNode);
		
		size++;
	}


	/** Returns the size of the list 
	 *  @return the number of elements in this list
	 * If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 */
	@Override
	public int size() 
	{
		return Math.min(size, Integer.MAX_VALUE);             //-1;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	@Override
	public E remove(int index) 
	{
		if (index < 0 )
			throw new IndexOutOfBoundsException("Index less than zero");	
		
		if (index >= size() )
			throw new IndexOutOfBoundsException("Index greater than or equal to the size of the list");
		
		LLNode<E> nodeToRemove = nodeAt(index);	
		///////////// remove the node
		LLNode<E> prevNode = nodeToRemove.prev;
		LLNode<E> nextNode = nodeToRemove.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		
		size--;		
		
		return nodeToRemove.getData();
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
	
		if (index < 0 )
			throw new IndexOutOfBoundsException("Index less than zero");	
		
		if (index >= size() )
			throw new IndexOutOfBoundsException("Index greater than or equal to the size of the list");
		
		if (element == null)
			throw new NullPointerException("The element to set can't be null!");
		
		LLNode<E> theNode = nodeAt(index);	
		E oldData = theNode.getData();
		theNode.setData(element);
		
		return oldData;
	}   
	/**
	 * Returns the node at the given index. If the list has only the sentinel values
	 * (list is empty), the method returns the tail node.
	 * @param index the index of the node to return
	 * @return the node at the specified index or the tail node if the list is empty
	 */
	private LLNode<E> nodeAt(int index){

		LLNode<E> currentNode = head.next;	
		int counter = 0; 
		
		while (counter < index){ 
			
			LLNode<E> temp = null;
			temp = currentNode.next;
			currentNode = temp;
			counter++;			
		}

		return currentNode;
	}

}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;
	
	public LLNode(E element) 
	{
		this.data = element;
		this.prev = null;
		this.next = null;
	}
	
	
	public LLNode() 
	{
		this(null);
	}
	
	/**
	 * Creates a new node which is inserted between the nextNode and the previousNode
	 * @param element the data of the node
	 * @param previousNode the node before the newly created node
	 * @param nextNode the node after the newly created node
	 */	
	public LLNode(E element, LLNode<E> previousNode, LLNode<E> nextNode) 
	{
		this.data = element;
		
		previousNode.next = this;
		this.prev = previousNode;		
		
		this.next = nextNode;
		nextNode.prev = this;
	}
		
	
	public E getData(){
		return data;
	}
	
	public void setData(E e){
		this.data = e;
	}

	public LLNode<E> getPrev() {
		return prev;
	}

	public void setPrev(LLNode<E> prev) {
		this.prev = prev;
	}

	public LLNode<E> getNext() {
		return next;
	}

	public void setNext(LLNode<E> next) {
		this.next = next;
	}
		
	
}