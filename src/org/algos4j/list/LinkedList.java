package org.algos4j.list;

/**
 * Represents a single LinkedList. If the linked list contains duplicates, some
 * methods acts on the first occurrence.
 * 
 * @author psajja
 * 
 */
public class LinkedList {

	private Node head;
	// TODO add size
	
	public LinkedList() {
		head = null;
	}

	LinkedList(Node head) {
		this.head = head;
	}

	/**
	 * Return the head node.
	 * 
	 * @return the head
	 */
	public Node getHead() {
		return head;
	}

	/**
	 * Set the head of the list.
	 * 
	 * @param head
	 *            the head to set
	 */
	void setHead(Node head) {
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
	 * @return tail node if found, null otherwise
	 */
	public Node getTail() {

		Node head = getHead();
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
	 *            data to create
	 */
	static Node createNode(int data) {
		return new Node(data);
	}

	/**
	 * Print the data from the current linked list. O(n) for time, O(1) for space.
	 */
	void print() {
		Node temp = getHead();
		while (temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.next;
		}
	}

	/**
	 * Prints the list in reverse order. O(n) for time, O(n) for stack space.
	 */
	void printReverse() {
		Node head = getHead();
		printReverse(head);
	}
	
	/**
	 * Recursively print the list.
	 * 
	 * @param head
	 * 		current head
	 */
	private void printReverse(Node head) {
		if(head == null)
			return;
		printReverse(head.next);
		System.out.print(head.getData() + " ");
	}

	/**
	 * This method inserts the node at the front.
	 * 
	 * @param data
	 *            data to insert
	 */
	public void insertFront(int data) {
		Node temp = new Node(data);

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
	 *            data to insert
	 */
	public void insertLast(int data) {
		Node temp = new Node(data);

		if (getHead() == null)
			setHead(temp);
		else {
			Node current = getHead();
			while (current.next != null)
				current = current.next;
			current.next = temp;
		}
	}

	/**
	 * This method inserts the data after a given value.
	 * 
	 * @param data
	 * 		data to insert
	 * @param afterData
	 * 		after node data
	 * 
	 * @throws IllegalStateException
	 *             if the linked list is empty
	 * @throws IllegalArgumentException
	 *             if the 'afterData' element is not found
	 */
	public void insertAfter(int data, int afterData) {
		if (getHead() == null)
			throw new IllegalStateException("LinkedList is empty.");
		Node current = getHead();
		while (current != null) {
			if (current.getData() == afterData)
				break;
			current = current.next;
		}
		if (current != null) {
			Node temp = new Node(data);
			temp.next = current.next;
			current.next = temp;
		} else {
			throw new IllegalArgumentException("The element " + afterData
					+ " does not exist.");
		}
	}

	/**
	 * This method inserts the data before a given value.
	 * 
	 * @param data
	 * 		data to insert
	 * @param beforeData
	 * 		before node data
	 * 
	 * @throws IllegalStateException
	 *             if the linked list is empty
	 * @throws IllegalArgumentException
	 *             if the 'beforeData' element is not found
	 */
	public void insertBefore(int data, int beforeData) {
		if (getHead() == null)
			throw new IllegalStateException("LinkedList is empty.");
		Node current = getHead();
		// base case, head is the element itself
		if (current.getData() == beforeData) {
			insertFront(data);
		} else {
			while (current.next != null) {
				if (current.next.getData() == beforeData)
					break;
				current = current.next;
			}
			if (current.next != null) {
				Node temp = new Node(data);
				temp.next = current.next;
				current.next = temp;
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
	 *            data to delete
	 */
	public void delete(int data) {
		Node current = getHead();
		if (current == null)
			return;
		// base case, head is the element itself
		if (current.getData() == data) {
			setHead(current.next);
		}
		while (current.next != null) {
			if (current.next.getData() == data) {
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
		Node current = getHead();

		if (current == null)
			throw new IllegalStateException("Linked list is empty.");

		if (pos == 0) {
			setHead(current.next);
			return;
		}
		current = current.next;
		for (int i = 1; i < pos - 1 && current.next != null; i++) {
			current = current.next;
		}

		if (current.next == null)
			throw new IllegalArgumentException("No such element with index "
					+ pos);
		current.next = current.next.next;
	}

	/**
	 * Returns the size of the linked list.
	 * 
	 * @return current size of the linked list
	 */
	public int size() {
		int count = 0;
		Node current = getHead();
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
	 *            item to search
	 * 
	 * @return true if the data is found, false otherwise.
	 */
	public boolean contains(int data) {
		Node current = getHead();
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
	 *            position of node
	 * 
	 * @return the node value at the given position
	 * 
	 * @throws IllegalArgumentException
	 *             if the position is invalid or not found.
	 */
	public int getNth(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Invalid position " + n);
		Node current = getHead();
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
	 * 		element data to find
	 * 
	 * @return
	 * 		position if the element is found, -1 otherwise
	 */
	public int getPosition(int data) {
		Node temp = getHead();
		int pos = 0;
		while(temp != null) {
			if(temp.getData() == data)
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
		Node temp = getHead();
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
	 * Node in a linked list.
	 * 
	 * @author psajja
	 * 
	 */
	public static class Node implements Cloneable{

		int data;
		public Node next;

		public Node(int data) {
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
		
		/* (non-Javadoc)
		 * @see java.lang.Object#clone()
		 */
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}
}
