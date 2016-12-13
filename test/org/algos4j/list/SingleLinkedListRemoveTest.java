package org.algos4j.list;

/**
 * A test class to test removing nodes in a given linked list. 
 * 
 * @author psajja
 *
 */
public class SingleLinkedListRemoveTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25, 10, 15};
		System.out.println("Removing Duplicates");
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List Before");
		System.out.println(list);
		LinkedListUtil.removeDuplicates(list);
		System.out.println("Liked List After");
		System.out.println(list);
	
		System.out.println();
		int[] elements1 = {5, 5, 10, 20, 20, 30, 45, 50};
		LinkedList list1 = LinkedListUtil.createList(elements1);
		System.out.println("Removing Duplicates In sorted list");
		System.out.println("Liked List Before");
		System.out.println(list1);
		LinkedListUtil.removeDuplicatesInSortedList(list1);
		System.out.println("Liked List After");
		System.out.println(list1);
		
		System.out.println();
		testRemoveAlternateNodes();
		
		System.out.println();
		testRemoveNextGreaterNodes();
		
		System.out.println();
		testSkipAndRemoveNodes();
	}

	/**
	 * Remove alternative nodes
	 */
	private static void testRemoveAlternateNodes() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Removing Alternative Nodes");
		System.out.println("Liked List");
		System.out.println(list);
		
		LinkedListUtil.removeAlternateNodes(list);
		System.out.println("Liked List");
		System.out.println(list);		
	}

	/**
	 * Remove nodes which has next greater nodes
	 */
	private static void testRemoveNextGreaterNodes() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Removing Next Greater Nodes");
		System.out.println("Liked List");
		System.out.println(list);
		
		LinkedListUtil.removeNodesHasNextGreater(list);
		System.out.println("Liked List");
		System.out.println(list);		
	}
	
	/**
	 * Remove nodes by skipping nodes
	 */
	private static void testSkipAndRemoveNodes() {
		int[] elements = {5, 10, 20, 15, 30, 25, 45, 55};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Removing 3 Nodes Skipping 4 Nodes");
		System.out.println("Liked List");
		System.out.println(list);
		
		LinkedListUtil.remove(list, 4, 1);
		System.out.println("Liked List");
		System.out.println(list);		
	}
}
