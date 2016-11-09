package org.algos4j.stack;

/**
 * An integer stack using linked list representation.
 * 
 * @author psajja
 * 
 */
public class LinkedIntStack {

	private Node top;
	private int size;

	/**
	 * Initialize the stack.
	 * 
	 * @param capacity
	 */
	public LinkedIntStack() {
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
	 * 
	 */
	public void push(int element) {
		Node top = new Node(element);
		top.next = this.top;
		this.top = top;
		size++;
	}

	/**
	 * Removes and Returns the top of the element.
	 * 
	 * @return
	 * 
	 * @throws IllegalStateException
	 *             if the stack underflows.
	 */
	public int pop() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflow.");
		Node top = this.top;
		this.top = this.top.next;
		size--;
		return top.getData();
	}

	/**
	 * Returns the top of the stack.
	 * 
	 * @return
	 */
	public int peek() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflow.");
		return top.getData();
	}

	/**
	 * Returns the size of the stack.
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * Node of stack in linked list.
	 * 
	 * @author psajja
	 * 
	 */
	private static class Node {
		private int data;
		public Node next;

		public Node(int data) {
			this.data = data;
		}

		public int getData() {
			return data;
		}
	}
}
