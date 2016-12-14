package org.algos4j.list;

/**
 * This test class tests cloning a doubly linked list.
 * 
 * @author psajja
 *
 */
public class DoublyLinkedListCloneTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] element = {10, 5, 20, 15, 25, 30};
		DoublyLinkedList linkedList = DoublyLinkedListUtil.createList(element);
		System.out.println("Oritinal Linked List");
		System.out.println(linkedList);
		DoublyLinkedList clone = DoublyLinkedListUtil.clone(linkedList);
		System.out.println("Cloned Linked List");
		System.out.println(clone);
	}
}
