package org.algos4j.stack;

/**
 * A fixed length k stack using a single array. 
 * Stack indices are zero based.
 * 
 * @author psajja
 * 
 */
public class KStack {

	private int[] elements;
	private int[] top;
	private int[] next;
	
	private int free;
	private int stacks, size;

	/**
	 * Creates instance.
	 * 
	 * @param stacks
	 *            number of stacks
	 * @param size
	 *            total number of elements
	 * 
	 * @throws IllegalArgumentException
	 *             if the size is less than stacks is <= 0 or no.of stacks
	 */
	public KStack(int stacks, int size) {
		if (stacks <= 0 || size < stacks)
			throw new IllegalArgumentException(
					"Check for no.of stacks > 0 & size >= no.of stacks");
		this.stacks = stacks;
		this.size = size;
		elements = new int[size];
		top = new int[stacks];
		next = new int[size];

		free = 0;
		init();
	}

	/**
	 * Initialize the top and next arrays.
	 */
	private void init() {
		for (int i = 0; i < stacks; i++) {
			top[i] = -1;
		}
		for (int i = 0; i < size - 1; i++) {
			next[i] = i + 1;
		}
		// -1 to indicate end of free list
		next[size - 1] = -1;
	}

	/**
	 * Checks whether there is any room for stacks.
	 * 
	 * @return true if there is no room for new elements, false otherwise
	 * 
	 */
	public boolean isFull() {
		return free == -1;
	}

	/**
	 * Checks whether the given stack is empty.
	 * 
	 * @param stackId
	 *            stack from
	 * 
	 * @return true if the given stack is empty, false otherwise
	 * 
	 * @throws IllegalArgumentException
	 *             if the stack id is invalid
	 */
	public boolean isEmpty(int stackId) {
		if (stackId < 0 || stackId >= stacks)
			throw new IllegalArgumentException("Invalid stack specified: "
					+ stackId);
		return top[stackId] == -1;
	}
	
	/**
	 * Pushes the given data on to the specified stack.
	 * 
	 * @param element
	 *            element to push
	 * @param stackId
	 *            stack id
	 * 
	 * @throws IllegalArgumentException
	 *             if the stack is invalid
	 * @throws IllegalStateException
	 *             if the specified stack is full
	 */
	public void push(int element, int stackId) {
		if (stackId < 0 || stackId >= stacks)
			throw new IllegalArgumentException("Invalid stack specified: "
					+ stackId);
		if (isFull())
			throw new IllegalStateException("Stack " + stackId + " is full");

		int i = free;
		free = next[i];
		next[i] = top[stackId];
		top[stackId] = i;

		elements[i] = element;
	}

	/**
	 * Pops and returns an element from the specified stack.
	 * 
	 * @param stackId
	 *            stack to pop an element from
	 * 
	 * @return popped element from the given stack
	 * 
	 * @throws IllegalArgumentException
	 *             if the stack is invalid
	 * @throws IllegalStateException
	 *             if the specified stack is empty
	 */
	public int pop(int stackId) {
		if (stackId < 0 || stackId >= stacks)
			throw new IllegalArgumentException("Invalid stack specified: "
					+ stackId);
		if (isEmpty(stackId))
			throw new IllegalStateException("Stack " + stackId + " is empty");

		int i = top[stackId];
		top[stackId] = next[i];
		next[i] = free;
		free = i;

		return elements[i];
	}

	/**
	 * Returns top element from the specified stack with out removing.
	 * 
	 * @param stackId
	 *            stack from
	 * 
	 * @return top element from the given stack
	 * 
	 * @throws IllegalArgumentException
	 *             if the stack is invalid
	 * @throws IllegalStateException
	 *             if the specified stack is empty
	 */
	public int peek(int stackId) {
		if (stackId < 0 || stackId >= stacks)
			throw new IllegalArgumentException("Invalid stack specified: "
					+ stackId);
		if (isEmpty(stackId))
			throw new IllegalStateException("Stack " + stackId + " is empty");

		return elements[top[stackId]];
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Stacks: [");
		sb.append(System.getProperty("line.separator"));
		sb.append("\tStack 0: ");
		appendString(sb, 0);
		for (int i = 1; i < stacks; i++) {
			sb.append(System.getProperty("line.separator"));
			sb.append("\tStack " + i + ": ");
			appendString(sb, i);
		}
		sb.append(System.getProperty("line.separator"));
		sb.append("]");
		return sb.toString();
	}

	/**
	 * add string for each of the individual stacks.
	 * 
	 * @param sb
	 * 		string builde to append
	 * 
	 * @param stackId
	 * 		stack id
	 */
	private void appendString(StringBuilder sb, int stackId) {
		if(isEmpty(stackId))
			sb.append("[]");
		else {
			sb.append("[");
			int stackTop = top[stackId];
			sb.append(elements[stackTop]);
			int nextTop = next[stackTop];
			while(nextTop != -1) {
				sb.append(", " + elements[nextTop]);
				nextTop = next[nextTop];
			}
			sb.append("]");
		}
	}
}
