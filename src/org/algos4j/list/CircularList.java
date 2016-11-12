package org.algos4j.list;

/**
 * Circular single linked list.
 * 
 * @author psajja
 *
 */
public class CircularList extends LinkedList {

	/* (non-Javadoc)
	 * @see org.algos4j.list.LinkedList1#getTail()
	 */
	@Override
	public Node getTail() {
		Node current = getHead();
		if (current == null)
			return null;
		while (current.next != getHead())
			current = current.next;

		return current;
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.list.LinkedList1#printList()
	 */
	@Override
	public void print() {
		Node current = getHead();
		if(current == null)
			return;
		System.out.print(current.getData());
		while (current.next != getHead()) {
			current = current.next;
			System.out.print(" " + current.getData());
		}
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.list.LinkedList1#insertFront(int)
	 */
	@Override
	public void insertFront(int data) {
		Node temp = new Node(data);

		if (getHead() == null) {
			temp.next = temp;
			setHead(temp);
		} else {
			temp.next = getHead();
			getTail().next = temp;
			setHead(temp);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.list.LinkedList1#insertLast(int)
	 */
	@Override
	public void insertLast(int data) {
		Node temp = new Node(data);

		if (getHead() == null)
			setHead(temp);
		else {
			Node current = getHead();
			while (current.next != getHead())
				current = current.next;
			current.next = temp;
		}
		temp.next = getHead();
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.list.LinkedList1#size()
	 */
	@Override
	public int size() {
		Node current = getHead();
		if(current == null)
			return 0;
		
		int count = 1;
		while (current.next != getHead()) {
			count++;
			current = current.next;
		}

		return count;
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.list.LinkedList1#getNth(int)
	 */
	@Override
	public int getNth(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Invalid input: " + n);
		Node current = getHead();
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
	 * @see org.algos4j.list.LinkedList#getPosition(int)
	 */
	@Override
	public int getPosition(int data) {
		Node temp = getHead();
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
}
