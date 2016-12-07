package org.algos4j.stack;

/**
 * A stack which supports operations on middle elements with O(1) complexity.
 * Supporting operations on the middle element can be done using a linked list
 * (doubly) based stack implementation.
 * 
 * @author psajja
 * 
 */
public class MiddleIntStack {

	private StackNode top;
	private StackNode middle;

	private int size;

	/**
	 * Instantiate the stack.
	 */
	public MiddleIntStack() {
		size = 0;
	}

	/**
	 * Checks whether the stack is empty.
	 * 
	 * @return 
	 * 		true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return top == null;
	}

	/**
	 * Push an element into the top of the stack.
	 * 
	 * @param element
	 * 		new element to insert
	 */
	public void push(int element) {
		StackNode newNode = new StackNode(element);

		newNode.prev = null;
		newNode.next = top;

		size += 1;

		if (size == 1)
			middle = newNode;
		else {
			top.prev = newNode;

			if (size % 2 != 0)
				middle = middle.prev;
		}

		top = newNode;
	}

	/**
	 * Removes and returns the top of the element.
	 * 
	 * @return top of the stack
	 * 
	 * @throws IllegalStateException
	 * 		if the stack underflows.
	 */
	public int pop() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflow.");
		StackNode head = top;
		int item = head.data;
		top = head.next;

		if (top != null)
			top.prev = null;

		size -= 1;

		if (size % 2 == 0)
			middle = middle.next;

		return item;
	}

	/**
	 * Returns the middle element.
	 * 
	 * @return 
	 * 		middle element
	 * 
	 * @throws IllegalStateException
	 * 		if the stack is empty.
	 */
	public int getMiddle() {
		if (middle == null)
			throw new IllegalStateException("Stack underflow.");

		return middle.data;
	}

	/**
	 * Removes and returns the middle element.
	 * 
	 * @return 
	 * 		middle element
	 * 
	 * @throws IllegalStateException
	 * 		if the stack is empty.
	 */
	public int deleteMiddle() {
		if (middle == null)
			throw new IllegalStateException("Stack underflow.");

		int data;
		data = middle.data;
		size--;
		if (size == 0) {
			top = null;
			middle = null;
		} else if (size % 2 == 0) {
			StackNode temp = middle.prev;
			if (temp != null) {
				temp.next = middle.next;
			}
			if (middle.next != null) {
				middle.next.prev = temp;
			}
			middle = temp;
		} else {
			StackNode temp = middle.next;
			if (temp != null) {
				temp.prev = middle.prev;
			}
			if (middle.prev != null) {
				middle.prev = middle.next;
			}
			middle = temp;
		}
		return data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (top == null)
			return "[]";

		StackNode current = top;

		StringBuilder sb = new StringBuilder();
		sb.append("Top -> [");
		sb.append(current.data);
		while (current.next != null) {
			current = current.next;
			sb.append(", " + current.data);
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	/**
	 * Doubly linked node in stack.
	 * 
	 * @author psajja
	 * 
	 */
	private static class StackNode {
		StackNode prev;
		StackNode next;
		
		int data;

		public StackNode(int data) {
			this.data = data;
		}
	}
}
