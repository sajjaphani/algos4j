package org.algos4j.list;

/**
 * Test the merging of two single linked lists.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListMergeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements1 = {5, 10, 20};
		LinkedList list1 = LinkedListUtil.createList(elements1);
		System.out.println("Liked List 1");
		System.out.println(list1);

		int[] elements2 = {15, 25, 30};
		LinkedList list2 = LinkedListUtil.createList(elements2);
		System.out.println("Liked List 2");
		System.out.println(list2);
		
		LinkedList mergedList = LinkedListUtil.merge(list1, list2);
		System.out.println("Merged Liked List");
		System.out.println(mergedList);
		
		mergedList = LinkedListUtil.mergeRecursive(list1, list2);
		System.out.println("Merged Liked List Recursive");
		System.out.println(mergedList);
		
		System.out.println();
		testMergeAtAlternatePosition();
	}

	/**
	 * Merge at alternative positions
	 */
	private static void testMergeAtAlternatePosition() {
		int[] elements1 = {5, 10, 20};
		LinkedList list1 = LinkedListUtil.createList(elements1);
		System.out.println("Merge at alternative positions");
		System.out.println("Liked List 1");
		System.out.println(list1);

		int[] elements2 = {15, 25, 30, 45, 50};
		LinkedList list2 = LinkedListUtil.createList(elements2);
		System.out.println("Liked List 2");
		System.out.println(list2);
		
		LinkedListUtil.mergeAtAlternatePosition(list1, list2);
		System.out.println("After Merge");
		System.out.println("Liked List 1");
		System.out.println(list1);
		System.out.println("Liked List 2");
		System.out.println(list2);
	}
}
