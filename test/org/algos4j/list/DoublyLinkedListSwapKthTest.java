package org.algos4j.list;

/**
 * This test class tests swapping kth node from both ends in a doubly linked list.
 * 
 * @author psajja
 *
 */
public class DoublyLinkedListSwapKthTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] element = {10, 5, 20, 15, 25, 30};
		DoublyLinkedList linkedList = DoublyLinkedListUtil.createList(element);
		System.out.println("Linked List Before");
		System.out.println(linkedList);
		DoublyLinkedListUtil.swapKth(linkedList, 3);
		System.out.println("Linked List After");
		System.out.println(linkedList);
	}
}
