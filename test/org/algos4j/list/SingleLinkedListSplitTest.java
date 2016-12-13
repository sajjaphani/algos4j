package org.algos4j.list;

/**
 * Test splitting a linked list into two halves.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListSplitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Splitting linked list");
		System.out.println("Liked List");
		System.out.println(list);

		LinkedList[] splits = LinkedListUtil.split(list);
		System.out.println("Liked List 1");
		System.out.println(splits[0]);
		System.out.println("Liked List 2");
		System.out.println(splits[1]);
	}

}
