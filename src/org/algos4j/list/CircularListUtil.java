package org.algos4j.list;

import org.algos4j.list.LinkedList.Node;

/**
 * Some utility method on single circular linked lists.
 * 
 * @author psajja
 *
 */
public class CircularListUtil {

	/**
	 * Not-instantiable.
	 */
	private CircularListUtil() {
	}
	
	/**
	 * Constructs a circular linked list from the given integer array.
	 * 
	 * @param array
	 * 		input integer array
	 * 
	 * @return
	 * 		newly constructed circular linked list 
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static CircularList createList(int[] array) {
		if(array == null)
			throw new NullPointerException("Input array can not be null.");
		
		CircularList list = new CircularList();
		
		for(int elt : array)
			list.insertLast(elt);
		
		return list;
	}
	
	/**
	 * Splits the given circular linked list into two halves.
	 * If the length is odd, the first list contains one more element.
	 * 
	 * @param linkedList
	 * 		given linked list
	 * 
	 * @return
	 * 		an array containing two lists
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static CircularList[] split(CircularList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("Linked list can not be null.");

		CircularList[] splits = { new CircularList(), new CircularList() };

		Node head = linkedList.getHead();
		Node slow = head;
		Node fast = head;
		Node head1 = null;
		Node head2 = null;

		if (head == null)
			return splits;

		while (fast.next != head && fast.next.next != head) {
			fast = fast.next.next;
			slow = slow.next;
		}

		if (fast.next.next == head)
			fast = fast.next;

		head1 = head;

		if (head.next != head)
			head2 = slow.next;

		fast.next = slow.next;
		slow.next = head;

		splits[0].setHead(head1);
		splits[1].setHead(head2);

		return splits;
	}
	
	/**
	 * Check whether the given circular linked list is in sorted order.
	 * It checks the elements for sorted order.
	 * 
	 * @param linkedList
	 * 		given circular linked list
	 * 
	 * @return
	 * 		if the list elements are in sorted order
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static boolean isSorted(CircularList linkedList) {
		if(linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		
		Node current = linkedList.getHead();
		if(current == null)
			return true;
		
		while(current.next != linkedList.getHead()) {
			if(current.getData() <= current.next.getData())
				current = current.next;
			else
				return false;
		}
		
		return true;
	}
	
	/**
	 * Inserts the elements in sorted order. This method does not check whether
	 * the list is already sorted. Use {@link #isSorted(CircularList)} before
	 * invoking this, if in doubt.
	 * 
	 * @param linkedList
	 *     	given circular linked list
	 * @param element
	 * 		data element to insert
	 * 
	 * @throws NullPointerException
	 *    	if the linked list is null
	 */
	static void insertInSortedList(LinkedList linkedList, int element) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList1 can not be null.");

		Node head = linkedList.getHead();
		Node current = head;
		Node temp = new Node(element);

		if (current == null) {
			temp.next = temp;
			linkedList.setHead(temp);
		} else {
			while (current.next != head && current.next.getData() < element)
				current = current.next;

			temp.next = current.next;
			current.next = temp;
		}
	}
	
	/**
	 * Swaps the node references in the given linked list represented by data items.
	 * If the given two data items are same, it does nothing.
	 * 
	 * @param linkedList
	 * 			  circular linked list
	 * @param data1
	 *            first element data
	 * @param data2
	 *            second element data
	 * 
	 * @throws NullPointerException
	 *              if the linked list is null
	 * @throws IllegalArgumentException
	 *             if one or two elements given are not exists.
	 */
	public static void swap(CircularList linkedList, int data1, int data2) {
		if(linkedList == null)
			throw new NullPointerException("Linked list cannot be null.");
	
		Node head = linkedList.getHead();
		
		if (data1 == data2)
			return;

		if(head == head.next)
			throw new IllegalStateException("Linked list has only one element.");
		
		Node prev1 = null, current1 = head;
		Node prev2 = null, current2 = head;

		while (current1.next != head && current1.getData() != data1) {
			prev1 = current1;
			current1 = current1.next;
		}

		while (current2.next != head && current2.getData() != data2) {
			prev2 = current2;
			current2 = current2.next;
		}

		String errorMesg = "Elements(s)";
		if (current1 == head)
			errorMesg = errorMesg + " " + data1;
		if (current2 == head)
			errorMesg += " " + data2;

		if (current1 == head && current2 == head)
			throw new IllegalArgumentException(errorMesg + " not found");

		if (prev1 == null) {
			// Node with data2 is the root
			prev2.next = current1;
			Node temp = current2.next;
			current2.next = current1.next;
			current1.next = temp;
			linkedList.getTail().next = current2;
			linkedList.setHead(current2);
		} else if (prev2 == null) {
			// Node with data1 is the root
			prev1.next = current2;
			Node temp = current2.next;
			current2.next = current1.next;
			current1.next = temp;
			linkedList.getTail().next = current1;
			linkedList.setHead(current1);
		} else {
			prev1.next = current2;
			prev2.next = current1;
			Node temp = current2.next;
			current2.next = current1.next;
			current1.next = temp;
		}
	}
	
	/**
	 * This method will find the leader where people are standing in a circle.
	 * The leader will be identified by counting and skipping given m positions.
	 * The procedure is repeated with the remaining people, starting with the
	 * next person, going in the same direction and skipping the same number of
	 * people, until only one person remains, and is the leader.
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Josephus_problem">Josephus problem</a> for more details.
	 * 
	 * @param n
	 * 		number of persons
	 * @param m
	 *   	to eliminate in each iteration
	 * 
	 * @return 
	 * 		person elected as a leader.
	 * 
	 * @throws IllegalArgumentException
	 *    	if n or m is <= 0
	 */
	public static int getJosephusPosition(int n, int m) {
		if (n <= 0 || m <= 0)
			throw new IllegalArgumentException("'n' and 'm' must be > 0");

		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = i + 1;

		CircularList circularList = createList(array);
		Node head = circularList.getHead();

		Node leader, temp;

		leader = temp = head;
		while (leader.next != leader) {
			for (int i = 0; i < m - 1; i++) {
				temp = leader;
				leader = leader.next;
			}
			temp.next = leader.next;
			System.out.println("Person at position " + leader.data + " has been Killed.");

			// holds the leader when loop terminates
			leader = temp.next;
		}

		return leader.data;
	}
	
	/**
	 * Identifies the leader using Josephus approach. A mathematical variant of
	 * {@link #getJosephusPosition(int, int)}. 
	 * Formula: f(n, k) = ((f(n-1, k) + k - 1) mod n) + 1, with f(1, k) = 1
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Josephus_problem">Josephus problem</a> for more details.
	 * 
	 * @param n
	 *   	number of persons
	 * @param m
	 *    	to eliminate in each iteration
	 * 
	 * @return 
	 * 		person elected as a leader.
	 * 
	 * @throws IllegalArgumentException
	 *   	if n or m is <= 0
	 */
	static int getJosephusPosition1(int n, int m) {
	
		if (n == 1)
			return 1;

		return ((getJosephusPosition1(n - 1, m) + m - 1) % n) + 1;
	}
}