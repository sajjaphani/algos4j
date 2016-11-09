package org.algos4j.stack;

/**
 * A fixed length integer stack.
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
	 */
	public int size() {
		return top + 1;
	}
	
	/**
	 * Check whether the stack is full.
	 * 
	 * @return
	 */
	public boolean isFull() {
		return top == capacity - 1;
	}

	/**
	 * Checks whether the stack is empty.
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return top == -1;
	}

	/**
	 * Push an element into the top of the stack.
	 * 
	 * @param element
	 * 
	 * @throws IllegalStateException
	 *             if the stack overflows.
	 */
	public void push(char element) {
		if (isFull())
			throw new IllegalStateException("Stack overflow.");
		elements[++top] = element;
	}

	/**
	 * Removes and Returns the top of the element.
	 * 
	 * @return
	 * 
	 * @throws IllegalStateException
	 *             if the stack underflows.
	 */
	public char pop() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflow.");
		return elements[top--];
	}

	/**
	 * Returns the top of the stack.
	 * 
	 * @return
	 */
	public char peek() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflow.");
		return elements[top];
	}
}
