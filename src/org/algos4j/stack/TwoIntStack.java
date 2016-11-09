package org.algos4j.stack;

/**
 * A fixed length 2 stack implementation, which holds ints.
 * Stack ids are zero based.
 * 
 * @author psajja
 * 
 */
public class TwoIntStack {

	int[] arr;
	int top1, top2;
	int[] next;
	int free;
	int size;

	/**
	 * Initializes the two stack object with the given size.
	 * 
	 * @param size
	 *            size of the two stack
	 * 
	 * @throws IllegalArgumentException
	 *             if the size is less than 2
	 */
	public TwoIntStack(int size) {
		if (size < 2)
			throw new IllegalArgumentException("Input size must be >= 2");

		this.size = size;
		arr = new int[size];
		top1 = -1;
		top2 = size;
	}

	/**
	 * Pushes the given data on to the specified stack.
	 * 
	 * @param data
	 *            data to push
	 * @param stackId
	 *            stack id
	 * 
	 * @throws IllegalArgumentException
	 *             if the stack is invalid
	 * @throws IllegalStateException
	 *             if the specified stack is full
	 */
	public void push(int data, int stackId) {
		if (stackId < 0 || stackId > 1)
			throw new IllegalArgumentException("Invalid stack specified: "
					+ stackId);
		if (top2 == top1 + 1)
			throw new IllegalStateException("Stack " + stackId + " is full");
		if (stackId == 0)
			arr[++top1] = data;
		else
			arr[--top2] = data;
	}

	/**
	 * Pops and returns an element from the specified stack.
	 * 
	 * @param stackId
	 * 		stack to pop an element from
	 * 
	 * @return
	 * 		popped element from the  given stack
	 * 
	 * @throws IllegalArgumentException
	 *             if the stack is invalid
	 * @throws IllegalStateException
	 *             if the specified stack is empty
	 */
	public int pop(int stackId) {
		if (stackId < 0 || stackId > 1)
			throw new IllegalArgumentException("Invalid stack specified: "
					+ stackId);
		if(stackId == 0) {
			if(top1 == -1)
				throw new IllegalStateException("Stack " + stackId + " is empty");
			return arr[top1--];
		} else {
			if(top2 == size)
				throw new IllegalStateException("Stack " + stackId + " is empty");
			return arr[top2++];
		}
	}

	/**
	 * Returns an element from the specified stack with out removing.
	 * 
	 * @param stackId
	 * 		stack from
	 * 
	 * @return
	 * 		top element from the  given stack
	 * 
	 * @throws IllegalArgumentException
	 *             if the stack is invalid
	 * @throws IllegalStateException
	 *             if the specified stack is empty
	 */
	public int peek(int stackId) {
		if (stackId < 0 || stackId > 1)
			throw new IllegalArgumentException("Invalid stack specified: "
					+ stackId);
		if(stackId == 0) {
			if(top1 == -1)
				throw new IllegalStateException("Stack " + stackId + " is empty");
			return arr[top1];
		} else {
			if(top2 == size)
				throw new IllegalStateException("Stack " + stackId + " is empty");
			return arr[top2];
		}
	}
	
	/**
	 * Checks whether the given stack is empty.
	 * 
	 * @param stackId
	 * 		stack from
	 * 
	 * @return
	 * 		true if the given stack is empty, false otherwise
	 * 
	 * @throws IllegalArgumentException
	 *             if the stack is invalid
	 */
	boolean isEmpty(int stackId) {
		if (stackId < 0 || stackId > 1)
			throw new IllegalArgumentException("Invalid stack specified: "
					+ stackId);
		if (stackId == 0)
			return top1 == -1;
		else
			return top2 == size;
	}

	/**
	 * Checks whether there  is any room for any stack.
	 * 
	 * @return
	 * 		true if there is no room for new elements, false otherwise
	 * 
	 */
	boolean isFull() {
		return top2 == top1 + 1;
	}
}
