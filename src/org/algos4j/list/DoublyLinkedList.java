package org.algos4j.list;

/**
 * Doubly linked list.
 * 
 * @author psajja
 * 
 */
public class DoublyLinkedList {

	private DoublyNode head;

	/**
	 * Get the head node.
	 * 
	 * @return the head
	 */
	public DoublyNode getHead() {
		return head;
	}

	/**
	 * set the head node.
	 * 
	 * @param head
	 *            the head to set
	 */
	void setHead(DoublyNode head) {
		this.head = head;
	}

	/**
	 * Check whether the list is empty.
	 * 
	 * @return
	 * 		true if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return getHead() == null;
	}
	
	/**
	 * Get the tail node.
	 * 
	 * @return tail node
	 */
	public DoublyNode getTail() {

		DoublyNode head = getHead();
		if (head == null)
			return null;
		while (head.next != null)
			head = head.next;

		return head;
	}

	/**
	 * Creates a new node with the given element value.
	 * 
	 * @param data
	 *            element's data
	 */
	public static DoublyNode createNode(int data) {
		return new DoublyNode(data);
	}

	/**
	 * Print the data from the current linked list.
	 */
	void print() {
		DoublyNode current = getHead();
		while (current != null) {
			System.out.print(current.getData() + " ");
			current = current.next;
		}
	}

	/**
	 * This method inserts the node at the front.
	 * 
	 * @param data
	 *            data to insert
	 */
	public void insertFront(int data) {
		DoublyNode temp = new DoublyNode(data);

		DoublyNode head = getHead();

		if (head == null)
			setHead(temp);
		else {
			head.prev = temp;
			temp.next = head;
			setHead(temp);
		}
	}

	/**
	 * This method inserts the node at the end.
	 * 
	 * @param data
	 *            data to insert
	 */
	public void insertLast(int data) {
		DoublyNode temp = new DoublyNode(data);

		DoublyNode current = getHead();

		if (current == null)
			setHead(temp);
		else {
			while (current.next != null)
				current = current.next;
			current.next = temp;
			temp.prev = current;
		}
	}

	/**
	 * This method inserts the data after a given value.
	 * 
	 * @param data
	 *            data to insert
	 * @param afterData
	 *            after node data
	 * 
	 * @throws IllegalStateException
	 *             if the linked list is empty.
	 * @throws IllegalArgumentException
	 *             if either the linked list is empty or the 'afterData' element
	 *             is not found
	 */
	public void insertAfter(int data, int afterData) {
		if (getHead() == null)
			throw new IllegalStateException("LinkedList is empty.");
		DoublyNode current = getHead();
		while (current != null) {
			if (current.getData() == afterData)
				break;
			current = current.next;
		}
		if (current != null) {
			DoublyNode temp = new DoublyNode(data);
			temp.next = current.next;
			current.next = temp;
			temp.prev = current;
			if (temp.next != null)
				temp.next.prev = temp;
		} else {
			throw new IllegalArgumentException("The element " + afterData
					+ " does not exist.");
		}
	}

	/**
	 * This method inserts the data before a given value.
	 * 
	 * @param data
	 *            data to insert
	 * @param beforeData
	 *            before node data
	 * 
	 * @throws IllegalStateException
	 *             if the linked list is empty.
	 * @throws IllegalArgumentException
	 *             if either the linked list is empty or the 'beforeData'
	 *             element is not found
	 */
	public void insertBefore(int data, int beforeData) {
		if (getHead() == null)
			throw new IllegalStateException("LinkedList is empty.");
		DoublyNode current = getHead();
		// base case, head is the element itself
		if (current.getData() == beforeData) {
			insertFront(data);
		} else {
			current = current.next;
			while (current != null) {
				if (current.getData() == beforeData)
					break;
				current = current.next;
			}
			if (current != null) {
				DoublyNode temp = new DoublyNode(data);
				temp.next = current;
				temp.prev = current.prev;
				current.prev.next = temp;
				current.prev = temp;
			} else {
				throw new IllegalArgumentException("The element " + beforeData
						+ " does not exist.");
			}
		}
	}

	/**
	 * Deletes the node represented by the given data. It does nothing if the
	 * data is not found.
	 * 
	 * @param data
	 *            node data to delete
	 */
	public void delete(int data) {
		DoublyNode current = getHead();
		if (current == null)
			return;
		// base case, head is the element itself
		if (current.getData() == data) {
			setHead(current.next);
			if (current.next != null)
				current.next.prev = null;
		}
		while (current.next != null) {
			if (current.next.getData() == data) {
				current.next = current.next.next;
				current.next.prev = null;
				if (current.next.next != null)
					current.next.next.prev = current;
				return;
			}
			current = current.next;
		}
	}

	/**
	 * Delete an element at the given position. Zero based index.
	 * 
	 * @param pos
	 *            node position
	 * 
	 * @throws IllegalStateException
	 *             if the linked list is empty.
	 * @throws IllegalArgumentException
	 *             if either the index is under or over the range of the linked
	 *             list
	 */
	public void deleteAt(int pos) {
		if (pos < 0)
			throw new IllegalArgumentException("Invalid index, must be >= 0.");
		DoublyNode current = getHead();

		if (current == null)
			throw new IllegalStateException("Linked list is empty.");

		if (pos == 0) {
			setHead(current.next);
			if (current.next != null)
				current.next.prev = null;
			return;
		}
		current = current.next;
		for (int i = 1; i < pos - 1 && current != null; i++) {
			current = current.next;
		}

		if (current == null || current.next == null)
			throw new IllegalArgumentException("No such element with index "
					+ pos);
		current.next = current.next.next;
		if (current.next != null)
			current.next.prev = current;
	}

	/**
	 * Returns the size of the linked list.
	 * 
	 * @return current list size
	 */
	public int size() {
		int count = 0;
		DoublyNode current = getHead();
		while (current != null) {
			count++;
			current = current.next;
		}

		return count;
	}

	/**
	 * Search for an element with the given data.
	 * 
	 * @param data
	 *            node data to find
	 * 
	 * @return true if found, false otherwise
	 */
	public boolean contains(int data) {
		DoublyNode current = getHead();
		while (current != null) {
			if (current.getData() == data)
				return true;
		}
		return false;
	}

	/**
	 * Get the value of the nth node in the linked list.
	 * 
	 * @param n
	 *            node's position
	 * 
	 * @return the data if found
	 * 
	 * @throws IllegalArgumentException
	 *             if no elements exists with the given position.
	 * 
	 */
	public int getNth(int n) {
		DoublyNode current = getHead();
		for (int i = 0; i < n; i++) {
			if (current == null)
				break;
			current = current.next;
		}
		if (current == null)
			throw new IllegalArgumentException(
					"There is no such element at position " + n);
		return current.getData();
	}

	/**
	 * For the given input data, gets the position in the linked list.
	 * 
	 * @param data
	 *            element data to find
	 * 
	 * @return position if the element is found, -1 otherwise
	 */
	public int getPosition(int data) {
		DoublyNode temp = getHead();
		int pos = 0;
		while (temp != null) {
			if (temp.getData() == data)
				return pos;
			pos += 1;
			temp = temp.next;
		}

		return -1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		DoublyNode temp = getHead();
		if (temp == null)
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(temp.data);
		temp = temp.next;
		while (temp != null) {
			sb.append(", " + temp.getData());
			temp = temp.next;
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * Node in a doubly linked list.
	 * 
	 * @author psajja
	 * 
	 */
	static class DoublyNode {

		private int data;
		public DoublyNode prev;
		public DoublyNode next;

		public DoublyNode(int data) {
			this.data = data;
		}

		/**
		 * Get the node data.
		 * 
		 * @return the data
		 */
		public int getData() {
			return data;
		}

		/**
		 * Set the node data.
		 * 
		 * @param data
		 *            the data to set
		 */
		public void setData(int data) {
			this.data = data;
		}
	}
}
