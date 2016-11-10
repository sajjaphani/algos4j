package org.algos4j.stack.ext;

/**
 * A generic stack with linked list representation.
 * 
 * @author psajja
 * 
 */
public class Stack<E> {

	private Node<E> top;
	private int size;

	/**
	 * Initialize
	 */
	public Stack() {
		top = null;
		size = 0;
	}

	/**
	 * Check whether the stack is empty.
	 * 
	 * @return
	 * 		true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return top == null;
	}

	/**
	 * Current size of the stack.
	 * 
	 * @return
	 * 		current size
	 */
	public int size() {
		return size;
	}

	/**
	 * Push an element onto the top of the stack.
	 * 
	 * @param element
	 * 		element to push
	 * 
	 */
	public void push(E element) {
		Node<E> newTop = new Node<E>(element);
		newTop.next = top;
		top = newTop;
		size = size + 1;
	}

	/**
	 * Removes and Returns the top of the element.
	 * 
	 * @return
	 * 		top element
	 * 
	 * @throws IllegalStateException
	 * 		if the stack underflows
	 */
	public E pop() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflows.");
		E temp = top.item;
		top = top.next;
		size = size - 1;
		return temp;
	}

	/**
	 * Returns the top of the stack without removing it.
	 * 
	 * @return
	 * 		top element
	 * 
	 * @throws IllegalStateException
	 * 		if the stack underflows
	 */
	public E peek() {
		if (isEmpty())
			throw new IllegalStateException("Stack underflows.");
		return top.item;
	}

	/**
	 * A node in a stack.
	 * 
	 * @author psajja
	 * 
	 * @param <E>
	 */
	private static class Node<E> {
		private E item;
		private Node<E> next;

		public Node(E item) {
			this.item = item;
		}
	}
}
