package org.algos4j.queue.ext;

/**
 * A generic queue, internally uses linked representation.
 * 
 * @author psajja
 *
 */
public class Queue<E> {
	
	private Node<E> first;
	private Node<E> last;
	private int size;
	
	/**
	 * Instantiates the queue.
	 */
	public Queue() {
        first = null;
        last  = null;
        size = 0;
    }
	
	/**
	 * Check whether the queue is empty.
	 * 
	 * @return
	 * 		true if the queue is empty, false otherwise
	 */
	public boolean isEmpty() {
        return first == null;
    }
	
	/**
	 * Returns the current size of the queue.
	 * 
	 * @return
	 * 		size of the queue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Inserts an element into the queue.
	 * 
	 * @param item
	 * 		item to insert
	 */
	public void insert(E item) {
		Node<E> temp = new Node<E>(item);
		if (isEmpty())
			first = temp;
		else
			last.next = temp;
		last = temp;
		size++;
	}
	
	/**
	 * Removes and returns the front element of the queue.
	 * 
	 * @return
	 * 		removed element
	 * 
	 * @throws IllegalStateException
	 * 		if the queue is empty
	 */
	public E remove() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty.");
		E item = first.item;
		first = first.next;
		size--;
		if (isEmpty())
			last = null;
		return item;
	}
	
	/**
	 * Returns the front element of the queue.
	 * 
	 * @return
	 * 		front element
	 * 
	 * @throws IllegalStateException
	 * 		if the queue is empty
	 */
	public E getFront() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty.");
		
		return first.item;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		if (isEmpty())
			return "[]";

		Node<E> current = first;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(current.item);
		current = current.next;
	
		while (current != null) {
			sb.append(", " + current.item);
			current = current.next;
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	/**
	 * A node in a queue.
	 * 
	 * @author psajja
	 *
	 * @param <E>
	 */
	private static class Node<E> {
        private E item;
        private Node<E> next;
        
        public Node(E item) {
        	this.item = item;
        }
    }
}
