package org.algos4j.list;

/**
 * Test the union of of two single linked lists.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListUnionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements1 = {5, 10, 15, 20};
		LinkedList list1 = LinkedListUtil.createList(elements1);
		System.out.println("Liked List 1");
		System.out.println(list1);

		int[] elements2 = {15, 20, 25, 30};
		LinkedList list2 = LinkedListUtil.createList(elements2);
		System.out.println("Liked List 2");
		System.out.println(list2);
		
		LinkedList intersectList = LinkedListUtil.union(list1, list2);
		System.out.println("Union Liked List");
		System.out.println(intersectList);
	}
}
