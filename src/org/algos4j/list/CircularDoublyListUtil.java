package org.algos4j.list;

import org.algos4j.list.DoublyLinkedList.DoublyNode;

/**
 * Some utilities on doubly circular lists.
 * 
 * @author psajja
 *
 */
public class CircularDoublyListUtil {

	/**
	 * Not-instantiable
	 */
	private CircularDoublyListUtil() {
	}
	
	/**
	 * Constructs a doubly circular linked list from the given integer array.
	 * 
	 * @param array
	 * 		input integer array
	 * 
	 * @return
	 * 		newly constructed doubly circular linked list 
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static CircularDoublyList createList(int[] array) {
		if(array == null)
			throw new NullPointerException("Input array can not be null.");
		
		CircularDoublyList list = new CircularDoublyList();
		
		for(int elt : array)
			list.insertLast(elt);
		
		return list;
	}
	
	/**
	 * Swaps the node references in the list represented by the given data items.
	 * If the given two data items are same, it does nothing.
	 * 
	 * @param linkedList
	 * 		doubly circular linked list
	 * @param data1
	 *  	first element data
	 * @param data2
	 *      second element data
	 * 
	 * @throws NullPointerException
	 *     	if the linked list is null
	 * @throws IllegalArgumentException
	 *     	if one or two elements given are not exists.
	 */
	public static void swap(CircularDoublyList linkedList, int data1, int data2) {
		if(linkedList == null)
			throw new NullPointerException("Linked list cannot be null.");
	
		DoublyNode head = linkedList.getHead();
		
		if (data1 == data2)
			return;

		if(head == head.next)
			throw new IllegalStateException("Linked list has only one element.");
		
		DoublyNode prevElt1 = null, currentElt1 = head;
		DoublyNode prevElt2 = null, currentElt2 = head;

		while (currentElt1.next != head && currentElt1.getData() != data1) {
			prevElt1 = currentElt1;
			currentElt1 = currentElt1.next;
		}

		while (currentElt2.next != head && currentElt2.getData() != data2) {
			prevElt2 = currentElt2;
			currentElt2 = currentElt2.next;
		}

		String errorMesg = "Elements(s)";
		if (currentElt1 == head)
			errorMesg = errorMesg + " " + data1;
		if (currentElt2 == head)
			errorMesg += " " + data2;

		if (currentElt1 == head && currentElt2 == head)
			throw new IllegalArgumentException(errorMesg + " not found");

		if (prevElt1 == null) {
			// Node with data2 is the root
			prevElt2.next = currentElt1;
			DoublyNode temp = currentElt2.next;
			currentElt2.next = currentElt1.next;
			currentElt1.next = temp;
			DoublyNode tail = linkedList.getTail();
			tail.next = currentElt2;
			currentElt2.prev = tail;
			linkedList.setHead(currentElt2);
		} else if (prevElt2 == null) {
			// Node with data1 is the root
			prevElt1.next = currentElt2;
			DoublyNode temp = currentElt2.next;
			currentElt2.next = currentElt1.next;
			currentElt1.next = temp;
			DoublyNode tail = linkedList.getTail();
			tail.next = currentElt1;
			currentElt1.prev= tail;
			linkedList.setHead(currentElt1);
		} else {
			prevElt1.next = currentElt2;
			prevElt2.next = currentElt1;
			DoublyNode temp = currentElt2.next;
			currentElt2.next = currentElt1.next;
			currentElt1.next = temp;
		}
	}
}
