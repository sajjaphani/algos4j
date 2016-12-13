package org.algos4j.list.ext;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.algos4j.list.ext.LinkedList.Node;

/**
 * Utilities on generic linked lists.
 * 
 * @author psajja
 *
 */
public class LinkedListUtil {

	/**
	 * Non-instantiale.
	 */
	private LinkedListUtil() {
	}

	/**
	 * Given an array of sorted linked lists, merge them and return a single
	 * list.
	 *
	 * 
	 * @param lists
	 * 		linked lists
	 * 
	 * @return 
	 * 		merged list
	 * 
	 * @throws NullPointerException
	 *   	if the given array is null
	 */
	public static LinkedList<Integer> mergeSortedLists(LinkedList<Integer>[] lists) {
		if (lists == null)
			throw new NullPointerException("Lists array should not be null");
		// TODO - convert this to a more generic version
		PriorityQueue<Node<Integer>> heap = new PriorityQueue<>(lists.length, getNodeComparator());
		for (LinkedList<Integer> aList : lists) {
			if (aList.getHead() != null)
				heap.add(aList.getHead());
		}

		Node<Integer> head = null;
		Node<Integer> current = null;
		while (!heap.isEmpty()) {
			if (head == null) {
				head = heap.poll();
				current = head;
			} else {
				current.next = heap.poll();
				current = current.next;
			}
			if (current.next != null)
				heap.add(current.next);
		}

		return new LinkedList<>(head);
	}

	/**
	 * Return the comparator.
	 * 
	 * @return the comparator
	 */
	private static Comparator<Node<Integer>> getNodeComparator() {

		return new Comparator<Node<Integer>>() {

			@Override
			public int compare(Node<Integer> o1, Node<Integer> o2) {
				return o1.getData().compareTo(o2.getData());
			}
		};
	}
}
