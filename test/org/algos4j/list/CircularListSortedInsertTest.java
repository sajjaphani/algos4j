package org.algos4j.list;

/**
 * This test class tests insertion into a sorted circular single linked list.
 * 
 * @author psajja
 *
 */
public class CircularListSortedInsertTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements1 = {5, 15, 20, 25, 30, 50};
		CircularList linkedList1 = CircularListUtil.createList(elements1);
		System.out.println("Circular Linked List");
		System.out.println(linkedList1);
		System.out.println("Inserting 35");
		CircularListUtil.insertInSortedList(linkedList1, 35);
		System.out.println("Linked List Now");
		System.out.println(linkedList1);
		CircularListUtil.insertInSortedList(linkedList1, 10);
		System.out.println("Linked List Now");
		System.out.println(linkedList1);
	}
}
