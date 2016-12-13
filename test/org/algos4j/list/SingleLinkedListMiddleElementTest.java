package org.algos4j.list;

/**
 * Test the middle element of a linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListMiddleElementTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List");
		System.out.println(list);
		System.out.println("Middle 1: " + LinkedListUtil.getMiddle(list));
		System.out.println("Middle 2: " + LinkedListUtil.getMiddle1(list));
	}

}
