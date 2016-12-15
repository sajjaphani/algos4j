package org.algos4j.list;

/**
 * This test class tests whether the given circular linked list is sorted or not.
 * 
 * @author psajja
 *
 */
public class CircularListSortedTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements1 = {10, 5, 20, 15, 25, 30, 50};
		CircularList linkedList1 = CircularListUtil.createList(elements1);
		System.out.println("Circular Linked List");
		System.out.println(linkedList1);
		boolean isSorted = CircularListUtil.isSorted(linkedList1);
		if(isSorted)
			System.out.println("The circular linked list is in sorted order.");
		else
			System.out.println("The circular linked list is not in sorted order.");
		
		System.out.println();
		
		int[] elements2 = {5, 15, 15, 20, 25, 30, 50};
		CircularList linkedList2 = CircularListUtil.createList(elements2);
		System.out.println("Circular Linked List");
		System.out.println(linkedList2);
		isSorted = CircularListUtil.isSorted(linkedList2);
		if(isSorted)
			System.out.println("The circular linked list is in sorted order.");
		else
			System.out.println("The circular linked list is not in sorted order.");
	}
}
