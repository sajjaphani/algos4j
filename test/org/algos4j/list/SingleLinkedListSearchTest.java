package org.algos4j.list;

/**
 * Test the search related methods in a linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListSearchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List");
		System.out.println(list);
		System.out.println("Found 20: " + LinkedListUtil.search(list, 20));
		System.out.println("Found 22: " + LinkedListUtil.search(list, 22));
		
		System.out.println();
		testOccurences();
	}

	/**
	 * Test the occurrences of a given number
	 */
	private static void testOccurences() {
		int[] elements = {5, 10, 20, 15, 10, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List");
		System.out.println(list);
		System.out.println("10 Occurs: " + LinkedListUtil.occurs(list, 10) + " time(s)");		
	}
}
