package org.algos4j.list;

/**
 * This test class tests whether two linked lists are identical.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListIdenticalTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list1 = LinkedListUtil.createList(elements);
		System.out.println("Liked List 1");
		System.out.println(list1);
		
		LinkedList list2 = LinkedListUtil.createList(elements);
		System.out.println("Liked List 2");
		System.out.println(list2);
		
		System.out.println("Are Identical: " + LinkedListUtil.areIdentical(list1, list2));
	}
}
