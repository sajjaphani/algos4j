package org.algos4j.list;

/**
 * This test class tests splitting a given circular singly linked list into two.
 * 
 * @author psajja
 *
 */
public class CircularListSplitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] element = {10, 5, 20, 15, 25, 30, 50};
		CircularList linkedList = CircularListUtil.createList(element);
		System.out.println("Circular Linked List");
		System.out.println(linkedList);
		CircularList[] splits = CircularListUtil.split(linkedList);
		System.out.println("Split List 1: " + splits[0]);
		System.out.println("Split List 2: " + splits[1]);
	}

}
