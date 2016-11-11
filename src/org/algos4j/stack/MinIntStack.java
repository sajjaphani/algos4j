package org.algos4j.stack;

/**
 * The stack which also operates min in O(1).
 * It used only Stack data structure.
 * 
 * @author psajja
 * 
 */
public class MinIntStack extends IntStack {

	private IntStack minStack;

	/**
	 * Initialize the stack with the given capacity.
	 * 
	 * @param capacity
	 * 		stack capacity
	 */
	public MinIntStack(int capacity) {
		super(capacity);
		minStack = new IntStack(capacity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.algos4j.stack.IntStack#push(int)
	 */
	@Override
	public void push(int element) {
		super.push(element);
		if (minStack.isEmpty() || element <= minStack.peek()) {
			minStack.push(element);
		} else {
			minStack.push(minStack.peek());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.algos4j.stack.IntStack#pop()
	 */
	@Override
	public int pop() {
		if(isEmpty())
			throw new IllegalStateException("Stack underflow.");
		// Pop one from each stack
		minStack.pop();
		return super.pop();
	}

	/**
	 * Return the minimum value from the stack.
	 * 
	 * @return
	 * 		minimum element
	 */
	public int min() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflow.");

		return minStack.peek();
	}
}
