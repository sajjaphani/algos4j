package org.algos4j.queue;

import org.algos4j.stack.IntStack;

/**
 * A Queue implementation using two stacks.
 * 
 * @author psajja
 * 
 */
public class IntQueueWithStack {

	// Stack used to push elements
	private IntStack stack1;
	// Stack used to pop elements
	private IntStack stack2;

	private int capacity;
	
	/**
	 * Initialize the queue with the given capacity.
	 * 
	 * @param capacity
	 * 		queue capacity
	 */
	public IntQueueWithStack(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Invalid capacity: " + capacity);
		this.capacity = capacity;
		stack1 = new IntStack(capacity);
		stack2 = new IntStack(capacity);
	}

	/**
	 * Check whether the queue is empty.
	 * 
	 * @return
	 * 		true if the queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}

	/**
	 * Check whether the queue is full.
	 * 
	 * @return
	 * 		true if the queue is full, false otherwise
	 */
	public boolean isFull() {
		return stack1.size() + stack2.size() == capacity;
	}

	/**
	 * Inserts an element in to the queue.
	 * 
	 * @param element
	 * 		element to insert
	 * 
	 * @throws IllegalStateException
	 * 		if the queue is full
	 */
	public void insert(int element) {
		if (isFull())
			throw new IllegalStateException("Queue is full.");
		stack1.push(element);
	}

	/**
	 * Removes and returns the front element of the queue.
	 * 
	 * @return
	 * 		element removed
	 * 
	 *  @throws IllegalStateException
	 * 		if the queue is empty
	 */
	public int remove() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty.");
		shiftElements();

		return stack2.pop();
	}
	
	/**
	 * Retrieves and returns the front element of the queue.
	 * 
	 * @return
	 * 		front element
	 * 
	 *  @throws IllegalStateException
	 * 		if the queue is empty
	 */
	public int peek() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty.");
		shiftElements();

		return stack2.peek();
	}

	/**
	 * Shift the stack elements from stack1 to stack2.
	 */
	protected void shiftElements() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty())
				stack2.push(stack1.pop());
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "Stack 1: " + stack1.toString() + " |" + " Stack 2: " + stack2.toString();
	}
}
