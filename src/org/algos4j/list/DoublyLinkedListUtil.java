package org.algos4j.list;

import java.util.HashMap;
import java.util.Map;

import org.algos4j.list.DoublyLinkedList.DoublyNode;

/**
 * Utilities on doubly linked lists.
 * 
 * @author psajja
 * 
 */
public class DoublyLinkedListUtil {

	/**
	 * Not-instantiable
	 */
	private DoublyLinkedListUtil() {
	}

	/**
	 * Constructs a doubly linked list from the given integer array.
	 * 
	 * @param array
	 *  	input integer array
	 * 
	 * @return 
	 * 		doubly linked list constructed from the given array
	 * 
	 * @throws NullPointerException
	 *   	if the input array is null
	 */
	public static DoublyLinkedList createList(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array can not be null.");

		DoublyLinkedList list = new DoublyLinkedList();

		for (int elt : array) 
			list.insertLast(elt);

		return list;
	}

	/**
	 * Reverses the given doubly linked list.
	 * 
	 * @param linkedList
	 *    	given doubly linked list
	 * 
	 * @throws NullPointerException
	 *    	if the given linked list is null
	 */
	public static void reverse(DoublyLinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("Linked list can not be null.");

		DoublyNode temp = null;
		DoublyNode current = linkedList.getHead();

		while (current != null) {
			temp = current.prev;
			current.prev = current.next;
			current.next = temp;
			current = current.prev;
		}

		if (temp != null) {
			linkedList.setHead(temp.prev);
		}
	}

	/**
	 * Clones the given linked list. This method logic can be adapted when we
	 * are cloning a doubly linked list where prev pointer is a random pointer.
	 * 
	 * @param linkedList
	 *   	doubly linked list.
	 * 
	 * @return 
	 * 		newly cloned list.
	 * 
	 * @throws NullPointerException
	 *   	if the linked list is null
	 */
	public static DoublyLinkedList clone(DoublyLinkedList linkedList) {

		if (linkedList == null)
			throw new NullPointerException("Linked list can not be null.");

		DoublyNode original = linkedList.getHead(), clone = null;
		Map<DoublyNode, DoublyNode> map = new HashMap<DoublyNode, DoublyNode>();

		while (original != null) {
			clone = new DoublyNode(original.getData());

			clone.next = null;
			clone.prev = null;

			map.put(original, clone);
			original = original.next;
		}

		original = linkedList.getHead();

		while (original != null) {
			clone = map.get(original);
			clone.next = map.get(original.next);
			clone.prev = map.get(original.prev);
			original = original.next;
		}

		DoublyLinkedList newList = new DoublyLinkedList();
		newList.setHead(map.get(linkedList.getHead()));
	
		return newList;
	}

	/**
	 * Swap the kth node from both ends from the given linked list.
	 * 
	 * @param linkedList
	 *    	linked list
	 * @param k
	 *    	position of element from both ends
	 * 
	 * @throws NullPointerException
	 *     	if the input linked list is null
	 * 
	 * @throws IllegalArgumentException
	 *    	if the k is invalid or is larger than the size
	 */
	public static void swapKth(DoublyLinkedList linkedList, int k) {
		if (linkedList == null)
			throw new NullPointerException("Linked list can not be null.");
		if (k <= 0)
			throw new IllegalArgumentException("K must be > 0");

		int size = linkedList.size();
		if (size < k)
			throw new IllegalArgumentException("K must be in the range of size: " + size);

		if (size == 2 * k - 1)
			return;

		DoublyNode front = linkedList.getHead();
		DoublyNode prevFront = null;
		for (int i = 1; i < k; i++) {
			prevFront = front;
			front = front.next;
		}

		DoublyNode end = linkedList.getHead();
		DoublyNode prevEnd = null;
		for (int i = 1; i < size - k + 1; i++) {
			prevEnd = end;
			end = end.next;
		}

		if (prevFront != null)
			prevFront.next = end;

		if (prevEnd != null)
			prevEnd.next = front;

		DoublyNode temp = front.next;
		front.next = end.next;
		end.next = temp;

		// Head is the last node
		if (k == 1)
			linkedList.setHead(end);

		// Head is the front node
		if (k == size)
			linkedList.setHead(front);
	}

	/**
	 * Swaps the node references represented by the given data elements in the list.
	 * If there are multiple such elements, this method will pick the first occurrence of each
	 * 
	 * @param linkedList
	 * 		given doubly linked list
	 * @param data1
	 * 		first element data
	 * @param data2
	 * 		second element data
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 * @throws IllegalArgumentException
	 * 		if one or more elements not found
	 */
	public static void swap(DoublyLinkedList linkedList, int data1, int data2) {
		if (linkedList == null)
			throw new NullPointerException("Linked list can not be null.");

		if (data1 == data2)
			return;

		DoublyNode prev1 = null, current1 = linkedList.getHead();
		DoublyNode prev2 = null, current2 = linkedList.getHead();

		while (current1 != null && current1.getData() != data1) {
			prev1 = current1;
			current1 = current1.next;
		}

		while (current2 != null && current2.getData() != data2) {
			prev2 = current2;
			current2 = current2.next;
		}

		String errorMesg = "Elements(s)";
		if (current1 == null)
			errorMesg = errorMesg + " " + data1;
		if (current2 == null)
			errorMesg += " " + data2;

		if (current1 == null || current2 == null)
			throw new IllegalArgumentException(errorMesg + " not found");

		if (prev1 == null) {
			// Node with data2 is the root
			prev2.next = current1;
			DoublyNode temp = current2.next;
			current2.next = current1.next;
			current1.next = temp;
			linkedList.setHead(current2);
		} else if (prev2 == null) {
			// Node with data1 is the root
			prev1.next = current2;
			DoublyNode temp = current2.next;
			current2.next = current1.next;
			current1.next = temp;
			linkedList.setHead(current1);
		} else {
			prev1.next = current2;
			prev2.next = current1;
			DoublyNode temp = current2.next;
			current2.next = current1.next;
			current1.next = temp;
		}
	}
}