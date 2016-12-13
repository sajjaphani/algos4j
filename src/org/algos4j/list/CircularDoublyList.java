package org.algos4j.list;

/**
 * Doubly circular linked list.
 * 
 * @author psajja
 *
 */
public class CircularDoublyList extends DoublyLinkedList {
	
	// TODO add size
	
	/* (non-Javadoc)
	 * @see org.algos4j.list.DoublyLinkedList#getTail()
	 */
	@Override
	public DoublyNode getTail() {
		DoublyNode current = getHead();
		if (current == null)
			return null;

		return current.prev;
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.list.DoublyLinkedList#print()
	 */
	@Override
	void print() {
		DoublyNode current = getHead();
		if(current == null)
			return;
		System.out.print(current.getData());
		while (current.next != getHead()) {
			current = current.next;
			System.out.print(" " + current.getData());
		}
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.list.DoublyLinkedList#insertFront(int)
	 */
	@Override
	public void insertFront(int data) {
		DoublyNode temp = new DoublyNode(data);
		DoublyNode head = getHead();
		
		if (head == null) {
			temp.next = temp;
			temp.prev = temp;
			setHead(temp);
		} 	else {
			head.prev = temp;
			temp.next = head;
			DoublyNode tail = getTail();
			tail.next = temp;
			temp.prev = tail;
			setHead(temp);
		}
	}

	/* (non-Javadoc)
	 * @see org.algos4j.list.DoublyLinkedList#insertLast(int)
	 */
	@Override
	public void insertLast(int data) {
		
		DoublyNode temp = new DoublyNode(data);
		DoublyNode current = getHead();
		
		if (current == null) {
			temp.next = temp;
			temp.prev = temp;
			setHead(temp);
		} else {
			DoublyNode tail = getTail();
			tail.next = temp;
			temp.prev = tail;
			temp.next = current;
			current.prev = tail;
		}
	}

	// TODO validate insertAfter, insertBefore
	
	/* (non-Javadoc)
	 * @see org.algos4j.list.DoublyLinkedList#delete(int)
	 */
	@Override
	public void delete(int data) {
		DoublyNode current = getHead();
		if (current == null)
			return;
		// base case, head is the element itself
		if (current.getData() == data) {
			if(current.next == getHead())
				setHead(null);
			else {
				DoublyNode tail = getTail();
				DoublyNode nxtHead = current.next;
				current.next.prev = tail;
				tail.next = current.next;
				setHead(nxtHead);
			}
		} else {
			while (current.next != getHead()) {
				if (current.next.getData() == data) {
					current.next = current.next.next;
					current.next.prev = current;
					return;
				}
				current = current.next;
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.algos4j.list.DoublyLinkedList#deleteAt(int)
	 */
	@Override
	public void deleteAt(int pos) {
		if (pos < 0)
			throw new IllegalArgumentException("Invalid index, must be >= 0.");
		DoublyNode current = getHead();

		if(current == null)
			throw new IllegalStateException("Linked list is empty.");
		
		if (pos == 0) {
			if(current.next == getHead())
				setHead(null);
			else {
				DoublyNode tail = getTail();
				tail.prev = current.next;
				setHead(current.next);
				current.next.prev = tail;
			}
			return;
		}
		current = current.next;
		for (int i = 1; i < pos - 1 && current != null; i++) {
			current = current.next;
		}

		if (current == getHead())
			throw new IllegalArgumentException("No such element with index "
					+ pos);
		current.next = current.next.next;
		current.next.next.prev = current;
	}

	/* (non-Javadoc)
	 * @see org.algos4j.list.DoublyLinkedList#size()
	 */
	@Override
	public int size() {
		int count = 0;
		DoublyNode current = getHead();
		if(current == null)
			return 0;
		while (current.next != getHead()) {
			count++;
			current = current.next;
		}

		return count + 1;
	}

	/* (non-Javadoc)
	 * @see org.algos4j.list.DoublyLinkedList#search(int)
	 */
	@Override
	public boolean contains(int data) {
		DoublyNode current = getHead();
		if(current == null)
			return false;
		if(current.getData() == data)
			return true;
		while (current.next != getHead()) {
			if (current.getData() == data)
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.algos4j.list.DoublyLinkedList#getNth(int)
	 */
	@Override
	public int getNth(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Invalid input: " + n);
		DoublyNode current = getHead();
		if (current == null)
			throw new IllegalStateException("Linked list is empty");
		if (n == 0)
			return current.getData();
		current = current.next;
		for (int i = 1; i <= n && current != getHead(); i++) {
			current = current.next;
		}
		if (current == getHead())
			throw new IllegalArgumentException(
					"There is no such element at position " + n);
		return current.getData();
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.list.DoublyLinkedList#getPosition(int)
	 */
	@Override
	public int getPosition(int data) {
		DoublyNode temp = getHead();
		if(temp == null)
			return -1;
		int pos = 0;
		while(temp != temp.next) {
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
		DoublyNode temp = getHead();
		if (temp == null)
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(temp.data);
		temp = temp.next;
		while (temp != getHead()) {
			sb.append(", " + temp.getData());
			temp = temp.next;
		}
		sb.append("]");
		
		return sb.toString();
	}
}