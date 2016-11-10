package org.algos4j.stack;

/**
 * A fixed length stack holds characters.
 * 
 * @author psajja
 * 
 */
public class CharStack {

	private char[] elements;
	private int capacity;
	private int top;

	/**
	 * Initialize the stack with the given capacity.
	 * 
	 * @param capacity
	 * 		given capacity
	 */
	public CharStack(int capacity) {
		this.capacity = capacity;
		elements = new char[capacity];
		top = -1;
	}

	/**
	 * Returns the size of the stack.
	 * 
	 * @return
	 * 		current size
	 */
	public int size() {
		return top + 1;
	}
	
	/**
	 * Check whether the stack is full.
	 * 
	 * @return
	 * 		true if the stack is full, false otherwise
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
	 * Push an element into the top of the stack.
	 * 
	 * @param element
	 * 		element to push
	 * 
	 * @throws IllegalStateException
	 *             if the stack overflows.
	 */
	public void push(char element) {
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
	public char pop() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflow.");
		char elt = elements[top];
		top = top - 1;
		return elt;
	}

	/**
	 * Returns the top of the stack.
	 * 
	 * @return
	 * 		top of stack
	 * 
	 * @throws IllegalStateException
	 * 		if the stack underflows
	 */
	public char peek() {
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
		
		return sb.toString();
	}
}
