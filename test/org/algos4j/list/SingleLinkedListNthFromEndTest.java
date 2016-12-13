package org.algos4j.list;

/**
 * Test the availability of nth node from the end of a linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListNthFromEndTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List");
		System.out.println(list);
		System.out.println("3rd from end: " + LinkedListUtil.getNthFromEnd(list, 3));
	}

}
