package org.algos4j.list;

/**
 * Test the sort related methods of a single linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListSortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List");
		System.out.println(list);
		System.out.println("List sorted: " + LinkedListUtil.isSorted(list));
		
		int[] elements1 = {5, 10, 15, 20, 25, 30};
		LinkedList list1 = LinkedListUtil.createList(elements1);
		System.out.println("Liked List");
		System.out.println(list1);
		System.out.println("List sorted: " + LinkedListUtil.isSorted(list1));
		
		System.out.println();
		testInsertInSortedList();
	}

	/**
	 * Sorted insert test
	 */
	private static void testInsertInSortedList() {
		int[] elements = {5, 10, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Testing insertion into a sorted list");
		System.out.println("Liked List");
		System.out.println(list);
		System.out.println("Inserting 30");
		LinkedListUtil.insertInSortedList(list, 30);
		System.out.println("Inserting 20");
		LinkedListUtil.insertInSortedList(list, 20);

		System.out.println("Liked List");
		System.out.println(list);		
	}
}
