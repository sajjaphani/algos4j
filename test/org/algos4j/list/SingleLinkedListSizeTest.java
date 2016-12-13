package org.algos4j.list;

/**
 * Test class that tests the size of a linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListSizeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List");
		System.out.println(list);
		System.out.println("List size: " + LinkedListUtil.size(list));
		
		System.out.println();
		testHadOddLength();
	}

	/**
	 * Tests odd length of a given linked list
	 */
	private static void testHadOddLength() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Odd Length Test");
		System.out.println("Liked List");
		System.out.println(list);
		System.out.println("List length is odd: " + LinkedListUtil.hasOddLength(list));		
	}
}
