package org.algos4j.list;

/**
 * Test the intersection of of two single linked lists.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListIntersectionTest {

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
		
		LinkedList intersectList = LinkedListUtil.intersectionSorted(list1, list2);
		System.out.println("Intersect Liked List");
		System.out.println(intersectList);
		
		System.out.println();
		testUnsortedIntersection();
	}

	/**
	 * Intersection on unsorted linked list.
	 */
	private static void testUnsortedIntersection() {
		int[] elements1 = {5, 15, 10, 20};
		LinkedList list1 = LinkedListUtil.createList(elements1);
		System.out.println("Liked List 1");
		System.out.println(list1);

		int[] elements2 = {25, 20, 15, 30};
		LinkedList list2 = LinkedListUtil.createList(elements2);
		System.out.println("Liked List 2");
		System.out.println(list2);
		
		LinkedList intersectList = LinkedListUtil.intersection(list1, list2);
		System.out.println("Intersect Liked List");
		System.out.println(intersectList);		
	}
}
