package org.algos4j.list.ext;

/**
 * Represents a generic single LinkedList. If the linked list contains
 * duplicates, some methods acts on the first occurrence of the element. The
 * methods use Object's equals method for comparison.
 * 
 * @author psajja
 *
 */
public class LinkedList<E> {

	private Node<E> head;

	/**
	 * Initialize the list
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * Initialize the list with the given head.
	 * 
	 * Package private.
	 * 
	 * @param head
	 * 		given head
	 */
	LinkedList(Node<E> head) {
		this.head = head;
	}

	/**
	 * Return the head node.
	 * 
	 * @return the head
	 */
	public Node<E> getHead() {
		return head;
	}

	/**
	 * Set the head of the list.
	 * 
	 * @param head
	 *            the head to set
	 */
	void setHead(Node<E> head) {
		this.head = head;
	}

	/**
	 * Check whether the list is empty.
	 * 
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return getHead() == null;
	}

	/**
	 * Get the tail node.
	 * 
	 * @return tail node if found, null otherwise
	 */
	public Node<E> getTail() {

		Node<E> head = getHead();

		if (head == null)
			return null;

		while (head.next != null)
			head = head.next;

		return head;
	}

	/**
	 * Creates a new node with the given element value.
	 * 
	 * @param elt
	 *            elt data to create
	 */
	static <E> Node<E> createNode(E elt) {
		return new Node<E>(elt);
	}

	/**
	 * Print the elements from the current linked list. O(n) for time, O(1) for
	 * space.
	 */
	void print() {
		Node<E> current = getHead();
		while (current != null) {
			System.out.print(current.getData() + " ");
			current = current.next;
		}
	}

	/**
	 * Prints the list in reverse order. O(n) for time, O(n) for stack space.
	 */
	void printReverse() {
		Node<E> head = getHead();
		printReverse(head);
	}

	/**
	 * Recursively print the list.
	 * 
	 * @param head
	 *            current head
	 */
	private void printReverse(Node<E> head) {
		if (head == null)
			return;
		printReverse(head.next);
		System.out.print(head.getData() + " ");
	}

	/**
	 * This method inserts the element at the front.
	 * 
	 * @param elt
	 *            elt data to insert
	 */
	public void insertFront(E elt) {
		Node<E> temp = LinkedList.createNode(elt);

		if (getHead() == null)
			setHead(temp);
		else {
			temp.next = getHead();
			setHead(temp);
		}
	}

	/**
	 * This method inserts the node at the end.
	 * 
	 * @param data
	 *            elt data to insert
	 */
	public void insertLast(E elt) {
		Node<E> temp = LinkedList.createNode(elt);

		if (getHead() == null)
			setHead(temp);
		else {
			Node<E> current = getHead();
			while (current.next != null)
				current = current.next;
			current.next = temp;
		}
	}

	/**
	 * This method inserts the element after a given element.
	 * 
	 * @param elt
	 *            elt data to insert
	 * @param afterElt
	 *            after node element
	 * 
	 * @throws IllegalStateException
	 *             if the linked list is empty
	 * @throws IllegalArgumentException
	 *             if the element 'afterElt' is not found
	 */
	public void insertAfter(E elt, E afterElt) {
		if (getHead() == null)
			throw new IllegalStateException("LinkedList is empty.");
		Node<E> current = getHead();
		while (current != null) {
			if (current.getData().equals(afterElt))
				break;
			current = current.next;
		}
		if (current != null) {
			Node<E> temp = LinkedList.createNode(elt);
			temp.next = current.next;
			current.next = temp;
		} else {
			throw new IllegalArgumentException("The element " + afterElt + " does not exist.");
		}
	}

	/**
	 * This method inserts the element before a given value.
	 * 
	 * @param elt
	 *            elt data to insert
	 * @param beforeElt
	 *            before node element
	 * 
	 * @throws IllegalStateException
	 *             if the linked list is empty
	 * @throws IllegalArgumentException
	 *             if the 'beforeElt' element is not found
	 */
	public void insertBefore(E elt, E beforeElt) {
		if (getHead() == null)
			throw new IllegalStateException("LinkedList is empty.");
		Node<E> current = getHead();
		// base case, head is the element itself
		if (current.getData().equals(beforeElt)) {
			insertFront(elt);
		} else {
			while (current.next != null) {
				if (current.next.getData().equals(beforeElt))
					break;
				current = current.next;
			}
			if (current != null) {
				Node<E> temp = LinkedList.createNode(elt);
				temp.next = current.next;
				current.next = temp;
			} else {
				throw new IllegalArgumentException("The element " + beforeElt + " does not exist.");
			}
		}
	}

	/**
	 * Deletes the node represented by the given element. It does nothing if the
	 * element is not found.
	 * 
	 * @param elt
	 *            elt data to delete
	 */
	public void delete(E elt) {
		Node<E> current = getHead();
		if (current == null)
			return;
		// base case, head is the element itself
		if (current.getData().equals(elt)) {
			setHead(current.next);
		}
		while (current.next != null) {
			if (current.next.getData().equals(elt)) {
				current.next = current.next.next;
				return;
			}
			current = current.next;
		}
	}

	/**
	 * Delete an element at the given position. Zero based index.
	 * 
	 * @param pos
	 *            position of the node
	 * 
	 * @throws IllegalStateException
	 *             if the linked list is empty
	 * @throws IllegalArgumentException
	 *             if either the index is under or over the range of the linked
	 *             list
	 */
	public void deleteAt(int pos) {
		if (pos < 0)
			throw new IllegalArgumentException("Invalid index, must be >= 0.");
		Node<E> current = getHead();

		if (current == null)
			throw new IllegalStateException("Linked list is empty.");

		if (pos == 0) {
			setHead(current.next);
			return;
		}
		current = current.next;
		for (int i = 1; i < pos && current != null; i++) {
			current = current.next;
		}

		if (current == null || current.next == null)
			throw new IllegalArgumentException("No such element with index " + pos);
		current.next = current.next.next;
	}

	/**
	 * Returns the size of the linked list.
	 * 
	 * @return current size of the linked list
	 */
	public int size() {
		int count = 0;
		Node<E> current = getHead();
		while (current != null) {
			count++;
			current = current.next;
		}

		return count;
	}

	/**
	 * Search for an element with the given data.
	 * 
	 * @param elt
	 *            item to search
	 * 
	 * @return true if the data is found, false otherwise.
	 */
	public boolean contains(E elt) {
		Node<E> current = getHead();
		while (current != null) {
			if (current.getData().equals(elt))
				return true;
		}
		return false;
	}

	/**
	 * Get the nth node in the linked list.
	 * 
	 * @param n
	 *            position of node
	 * 
	 * @return the node at the given position
	 * 
	 * @throws IllegalArgumentException
	 *             if the position is invalid or not found.
	 */
	public E getNth(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Invalid position " + n);
		Node<E> current = getHead();
		for (int i = 0; i < n; i++) {
			if (current == null)
				break;
			current = current.next;
		}
		if (current == null)
			throw new IllegalArgumentException("There is no such element at position " + n);
		return current.getData();
	}

	/**
	 * For the given input element, gets the position in the linked list.
	 * 
	 * @param elt
	 *            element data to find
	 * 
	 * @return position if the element is found, -1 otherwise
	 */
	public int getPosition(E elt) {
		Node<E> temp = getHead();
		int pos = 0;
		while (temp != null) {
			if (temp.getData().equals(elt))
				return pos;
			pos += 1;
			temp = temp.next;
		}

		return -1;
	}

	/**
	 * Generic Node in a single linked list.
	 * 
	 * @author psajja
	 * 
	 */
	static class Node<E> {

		private E elt;
		public Node<E> next;

		public Node(E elt) {
			this.elt = elt;
		}

		/**
		 * Get the node data.
		 * 
		 * @return the data
		 */
		public E getData() {
			return elt;
		}

		/**
		 * Set the node data.
		 * 
		 * @param data
		 *            the data to set
		 */
		public void setData(E elt) {
			this.elt = elt;
		}
	}
}