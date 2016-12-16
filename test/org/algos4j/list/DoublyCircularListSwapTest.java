package org.algos4j.list;

/**
 * This test class tests swapping data nodes in a given circular doubly linked list.
 * 
 * @author psajja
 *
 */
public class DoublyCircularListSwapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] element = {10, 5, 20, 15, 25, 30, 50};
		CircularDoublyList linkedList = CircularDoublyListUtil.createList(element);
		System.out.println("Linked List Before");
		System.out.println(linkedList);
		CircularDoublyListUtil.swap(linkedList, 10, 15);
		System.out.println("Linked List After");
		System.out.println(linkedList);
	}
}
