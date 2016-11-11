package org.algos4j.queue;

/**
 * Queue implementation (linked list representation), holds integer data.
 * 
 * @author psajja
 * 
 */
public class LinkedIntQueue {

	private QNode front, rear;
	private int size;

	/**
	 * Initialize the queue.
	 * 
	 */
	public LinkedIntQueue() {
		size = 0;
	}

	/**
	 * Check whether the queue is empty.
	 * 
	 * @return
	 * 		true if queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return front == null;
	}

	/**
	 * Get the current size of the queue.
	 * 
	 * @return
	 * 		size of the queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Inserts an element in to the queue.
	 * 
	 * @param element
	 * 		element to insert
	 */
	public void insert(int element) {
		QNode newNode = new QNode(element);
		if (isEmpty())
			front = rear = newNode;
		else {
			rear.next = newNode;
			rear = newNode;
		}
		size++;
	}

	/**
	 * Removes and returns the front of the queue.
	 * 
	 * @return
	 * 		front element removed
	 * 
	 * @throws IllegalStateException
	 * 		if the queue is empty
	 */
	public int remove() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty.");
		int item = front.getData();
		front = front.next;
		if (front == null)
			rear = null;
		size--;
		
		return item;
	}

	/**
	 * Returns the front element of the queue.
	 * 
	 * @return
	 * 		front of the queue
	 * 
	 * @throws IllegalStateException
	 * 		if the queue is empty
	 */
	public int getFront() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty.");
		
		return front.getData();
	}

	/**
	 * Returns the rear element of the queue.
	 * 
	 * @return
	 * 		rear of the queue
	 * 
	 * @throws IllegalStateException
	 * 		if the queue is empty
	 */
	public int getRear() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty.");
		
		return rear.getData();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		if (isEmpty())
			return "[]";

		QNode current = front;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(current.getData());
		current = current.next;
	
		while (current != null) {
			sb.append(", " + current.getData());
			current = current.next;
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	/**
	 * Node of queue.
	 * 
	 * @author psajja
	 * 
	 */
	private static class QNode {
		
		private int data;
		QNode next;
		
		/**
		 * Initialize a queue node with the given data.
		 * 
		 * @param data
		 * 		given data
		 */
		public QNode(int data) {
			this.data = data;
		}
		
		/**
		 * Get the node data.
		 * 
		 * @return
		 * 		node data
		 */
		public int getData() {
			return data;
		}
	}
}
