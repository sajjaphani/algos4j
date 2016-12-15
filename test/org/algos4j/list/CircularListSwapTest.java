package org.algos4j.list;

/**
 * This test class tests swapping data nodes in a given circular singly linked list.
 * 
 * @author psajja
 *
 */
public class CircularListSwapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] element = {10, 5, 20, 15, 25, 30, 50};
		CircularList linkedList = CircularListUtil.createList(element);
		System.out.println("Linked List Before");
		System.out.println(linkedList);
		CircularListUtil.swap(linkedList, 10, 15);
		System.out.println("Linked List After");
		System.out.println(linkedList);
	}
}
