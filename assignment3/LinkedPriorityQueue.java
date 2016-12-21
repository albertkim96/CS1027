/**
 * This class is implementation of PriorityQueueADT using Linked List data
 * 
 * @author jlee2967
 * CS1027B
 * @param <T> represents the data type stored in the queue
 */

public class LinkedPriorityQueue<T> implements PriorityQueueADT<T>
{
	//Attribute Declaration
	private int count;						// Number of how many elements are in queue
	private PriorityNode<T> front, rear;	// Start and end of the Priority Queue
	private double priority;				// The priority of the Queues
   
	/**
	 * Constructor for empty Queue
	 */
	public LinkedPriorityQueue(){
		count = 0;
		front = rear = null;
		priority = 0;
	}
   
	/**
	 * Enqueues the given element into the Priority Queue
	 * @param element the element of the node to be enqueued
	 */
	public void enqueue(T element){
		// Declare new node and set given element to be element of new node
		PriorityNode<T> node = new PriorityNode<T>();
		node.setElement(element);
		
		// If empty, then set node equal to front and rear
		if(isEmpty()){
			front = rear = node;
		}
		// Otherwise, set next of rear to be node.
		else{
			rear.setNext(node);
		}
		
		// Set rear to node and increment the count
		rear = node;
		count++;
	}
	
	/**
	 * Enqueues the given element and priority into the end of the Priority Queue based 
	 * 		on priority
	 * @param element the element of the node to be enqueued
	 * @param p the priority of the node to be enqueued
	 */
	public void enqueue(T element, double p){
		
		// Declare new node and set given element and priority to be element and 
		// priority of new node
		PriorityNode<T> node = new PriorityNode<T>();
		node.setElement(element);
		node.setPriority(p);
		
		/// If empty, then set node equal to front and rear
		if(isEmpty()){
			front = rear = node;
		}
		
		// If priority of front is greater or equal to node's priority, set front
		// to be next of node and make node the new front.
		else if(front.getPriority() >= node.getPriority()){
			node.setNext(front);
			front = node;
		}
		
		// If priority of rear is less or equal to node's priority, set node to 
		// be the next of rear, and make node the new rear.
		else if(rear.getPriority() <= node.getPriority()){
			rear.setNext(node);
			rear = node;
		}
		
		// Otherwise, check each priorities in queue til there is one less than p,
		// then I set it as currentNode. I set next of currentNode to be node's 
		// next, then I make currentNode''s next to be node
		else{
			PriorityNode<T> currentNode = front;
		
			while(currentNode.getNext().getPriority() <= p){
				currentNode = currentNode.getNext();
			}
		
			node.setNext(currentNode.getNext());
			currentNode.setNext(node);
		}	
		count++;
	}
   
	/**
	 * Dequeues the front of the Priority Queue
	 * @return returns the front element of the Priority Queue
	 * @throws EmptyCollectionException if Priority Queue is empty
	 */
	public T dequeue() throws EmptyCollectionException{
		
		// If Priority Queue is empty, throw an EmptyCollectionException
		if(isEmpty()){
			throw new EmptyCollectionException("priority queue");
		}
	   
		// Sets front equal to the next of front and decrement the count
		T result = front.getElement();
		front = front.getNext();
		count--;
		
		// If Priority Queue is empty after decrement, then make front and rear
		// equal to null
		if (isEmpty()){
			front = rear = null;
		}	
	   
		// return the front that was removed
		return result;
	}
   
	/**
	 * Returns the first element of the Priority Queue
	 * @return returns the first element of Priority Queue
	 */
	public T first() throws EmptyCollectionException{
		return front.getElement();
	}
   
	/**
	 * Returns boolean value if Priority Queue is empty or not
	 * @return boolean value of result after checking if Priority Queue is
	 * 		empty or not
	 */
	public boolean isEmpty(){
		return count == 0; 
	}
   
	/**
	 * Returns size of Priority Queue
	 * @return returns size of Priority Queue
	 */
	public int size(){
		return count; 
	}
   
	/**
	 * Returns String value of the elements and priorities of nodes in 
	 * Priority Queue
	 * @return String value of String value of the elements and priorities 
	 * of nodes in Priority Queue
	 */
	public String toString(){
		String result = "";
	   
		PriorityNode<T> temp = front;
		while(temp != null){
			result += temp + "\n";
			temp = temp.getNext();
		}
		return result;
	}
}
