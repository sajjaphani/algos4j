package org.algos4j.list;

/**
 * Test class that test the max sum lint of two single linked lists.
 * The path between two linked lists.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListMaxSumListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements1 = {1, 3, 30, 90, 120, 240, 511};
		LinkedList list1 = LinkedListUtil.createList(elements1);
		System.out.println("Liked List 1");
		System.out.println(list1);

		int[] elements2 = {0, 3, 12, 32, 90, 125, 240, 249};
		LinkedList list2 = LinkedListUtil.createList(elements2);
		System.out.println("Liked List 2");
		System.out.println(list2);
		
		LinkedList maxSumList = LinkedListUtil.maxSumList(list1, list2);
		System.out.println("Max Sum List");
		System.out.println(maxSumList);
	}
}
