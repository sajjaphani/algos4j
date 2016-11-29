package org.algos4j.stack;

/**
 * A fixed length integer stack.
 * 
 * @author psajja
 * 
 */
public class IntStack {

	private int[] elements;
	private int capacity;
	private int top;

	/**
	 * Initialize the stack with the given capacity.
	 * 
	 * @param capacity
	 * 		given capacity
	 */
	public IntStack(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Invalid capacity: " + capacity);
		this.capacity = capacity;
		elements = new int[capacity];
		top = -1;
	}

	/**
	 * Check whether the stack is full.
	 * 
	 * @return
	 * 		true if the stack if full, false otherwise
	 */
	public boolean isFull() {
		return top == capacity - 1;
	}

	/**
	 * Checks whether the stack is empty.
	 * 
	 * @return
	 * 		true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return top == -1;
	}

	/**
	 * Returns the current size of the stack
	 * 
	 * @return
	 * 		current size
	 */
	public int size() {
		return top + 1;
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
		top = top + 1;
		elements[top] = element;
	}

	/**
	 * Removes and Returns the top of the element.
	 * 
	 * @return
	 * 		popped element
	 * 
	 * @throws IllegalStateException
	 *             if the stack underflows.
	 */
	public int pop() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflow.");
		int elt = elements[top];
		top = top - 1;
		return elt;
	}

	/**
	 * Returns the top of the stack.
	 * 
	 * @return
	 * 		top of the stack
	 * 
	 * @throws IllegalStateException
	 *             if the stack underflows.
	 */
	public int peek() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflow.");
		return elements[top];
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
		sb.append(elements[top]);
	
		for (int i = top -1; i >= 0; i--)
			sb.append(", " + elements[i]);
		
		sb.append("]");
		
		return sb.toString() + "<- Top";
	}
}
