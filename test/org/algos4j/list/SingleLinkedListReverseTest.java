package org.algos4j.list;

/**
 * Test the reversing in full or in parts of a linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListReverseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Reverse Test");
		System.out.println("Liked List");
		System.out.println(list);
		LinkedListUtil.reverse(list);
		System.out.print("Reverse 1: " + list);
		System.out.println();
		LinkedListUtil.reverseRecursive(list);
		System.out.print("Reverse 2: " + list);
		
		System.out.println();
		System.out.println();
		testReverseInPair();

		System.out.println();
		System.out.println();
		testReverseInGroup();
		
		System.out.println();
		System.out.println();
		testReverseAlternativeK();
	}

	/**
	 * Test reverse in pairs
	 */
	private static void testReverseInPair() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Reverse In Pair Test");
		System.out.println("Liked List");
		System.out.println(list);
		LinkedListUtil.reverseInPair(list);
		System.out.print("Reverse 1: " + list);
		System.out.println();
		LinkedListUtil.reverseInPairRecursive(list);
		System.out.print("Reverse 2: " + list);		
	}

	/**
	 * Test reverse in groups
	 */
	private static void testReverseInGroup() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Reverse In Group Test");
		System.out.println("Liked List");
		System.out.println(list);
		LinkedListUtil.reverseInGroup(list, 7);
		System.out.print("Reverse 1: " + list);
		System.out.println();
		LinkedListUtil.reverseInGroupRecursive(list, 4);
		System.out.print("Reverse 2: " + list);		
	}
	
	/**
	 * Test reverse alternative k
	 */
	private static void testReverseAlternativeK() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Reverse Alternative K");
		System.out.println("Liked List");
		System.out.println(list);
		LinkedListUtil.reverseAlternateK(list, 2);
		System.out.print("Reverse K: " + list);
	
	}
}
