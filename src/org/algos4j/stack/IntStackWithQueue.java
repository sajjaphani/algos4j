package org.algos4j.stack;

import org.algos4j.queue.IntQueue;

/**
 * A fixed length integer stack using two queues.
 * 
 * @author psajja
 * 
 */
public class IntStackWithQueue {

	private IntQueue queue1;
	private IntQueue queue2;

	private int capacity;

	/**
	 * Initialize the stack with the given capacity.
	 * 
	 * @param capacity
	 */
	public IntStackWithQueue(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Invalid capacity: " + capacity);
	
		this.capacity = capacity;
		
		queue1 = new IntQueue(capacity);
		queue2 = new IntQueue(capacity);
	}

	/**
	 * Check whether the stack is full.
	 * 
	 * @return
	 * 		true if the stack is full, false otherwise
	 */
	public boolean isFull() {
		return queue1.isFull();
	}

	/**
	 * Checks whether the stack is empty.
	 * 
	 * @return
	 * 		true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return queue1.isEmpty();
	}

	/**
	 * Push an element into the top of the stack.
	 * 
	 * @param element
	 * 		element to push
	 * 
	 * @throws IllegalStateException
	 *             if the stack overflows.
	 */
	public void push(int element) {
		if (isFull())
			throw new IllegalStateException("Stack overflow.");
		queue1.insert(element);
	}

	/**
	 * Removes and Returns the top of the element.
	 * 
	 * @return
	 * 		element removed
	 * 
	 * @throws IllegalStateException
	 *             if the stack underflows.
	 */
	public int pop() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflow.");
		while (queue1.size() > 1)
			queue2.insert(queue1.remove());

		int element = queue1.remove();
		IntQueue temp = new IntQueue(capacity);
		temp = queue1;
		queue1 = queue2;
		queue2 = temp;

		return element;
	}

	/**
	 * Returns the top of the stack.
	 * 
	 * @return
	 * 		top element
	 * 
	 * @throws IllegalStateException
	 * 		if the stack underflows
	 */
	public int peek() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflows.");
		return queue1.getRear();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return queue1.toString() + " <- Top";
	}
}
