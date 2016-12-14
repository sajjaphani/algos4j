package org.algos4j.list;

/**
 * This test class tests swapping two nodes given their element data in a doubly linked list.
 * 
 * @author psajja
 *
 */
public class DoublyLinkedListSwapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] element = {10, 5, 20, 15, 25, 30};
		DoublyLinkedList linkedList = DoublyLinkedListUtil.createList(element);
		System.out.println("Linked List Before");
		System.out.println(linkedList);
		DoublyLinkedListUtil.swap(linkedList, 20, 30);
		System.out.println("Linked List After");
		System.out.println(linkedList);
	}
}
