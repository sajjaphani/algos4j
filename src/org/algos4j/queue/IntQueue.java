package org.algos4j.queue;

/**
 * Fixed length Queue. Implementation is based on circular queue, which holds integers.
 * 
 * @author psajja
 * 
 */
public class IntQueue {

	private int[] elements;
	private int capacity;
	private int front;
	private int rear;
	private int size;

	/**
	 * Initialize the queue with the given capacity.
	 * 
	 * @param capacity
	 * 		capacity of the queue
	 * 
	 * @throws IllegalArgumentException
	 * 		if the queue size is <= 0
	 */
	public IntQueue(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Invalid capacity: " + capacity);
		this.capacity = capacity;
		elements = new int[capacity];
		front = 0;
		rear = -1;
		size = 0;
	}

	/**
	 * Check whether the queue is empty.
	 * 
	 * @return
	 * 		true if the queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Check whether the queue is full.
	 * 
	 * @return
	 * 		true if queue is full, false otherwise
	 */
	public boolean isFull() {
		return size == capacity;
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
	 * Inserts an element in to the queue.
	 * 
	 * @param element
	 * 		the element to insert
	 * 
	 * @throws IllegalStateException
	 * 		if the queue is full
	 */
	public void insert(int element) {
		if (isFull())
			throw new IllegalStateException("Queue is full.");
		rear = (rear + 1) % capacity;
		elements[rear] = element;
		size = size + 1;
	}

	/**
	 * Removes and returns the front element of the queue.
	 * 
	 * @return
	 * 		the element removed
	 * 
	 * @throws IllegalStateException
	 * 		if the queue is empty
	 */
	public int remove() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty.");
		int item = elements[front];
		front = (front + 1) % capacity;
		size = size - 1;
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
	public int getFront() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty.");
		return elements[front];
	}

	/**
	 * Returns the rear element of the queue.
	 * 
	 * @return
	 * 		rear element
	 * 
	 * @throws IllegalStateException
	 * 		if the queue is empty
	 */
	public int getRear() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty.");
		return elements[rear];
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (isEmpty())
			return "[]";
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(elements[front]);
	
		for (int i = 1; i < size(); i++)
			sb.append(", " + elements[(front + i) % capacity]);
		
		sb.append("]");
		
		return sb.toString();
	}
}
