package org.algos4j.list;

/**
 * Test class the re-arranging the elements in a given order.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListRearrangeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Rearranging the list");
		System.out.println("Liked List Before");
		System.out.println(list);
		LinkedListUtil.rearrange(list);
		System.out.println("Linked List After");
		System.out.println(list);
		
		System.out.println();
		testPartition();
	}

	/**
	 * Tests odd length of a given linked list
	 */
	private static void testPartition() {
		int[] elements = {1, 4, 3, 2, 5, 2};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Partitioning a List Test");
		System.out.println("Liked List Before");
		System.out.println(list);
		LinkedListUtil.partition(list, 3);
		System.out.println("Linked List After");
		System.out.println(list);	
	}
}
