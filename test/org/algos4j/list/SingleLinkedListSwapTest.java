package org.algos4j.list;

/**
 * This test class will test swap functionality.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListSwapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Testing Swap in Pairs");
		System.out.println("Liked List Before");
		System.out.println(list);

		LinkedListUtil.swapInPairs(list);
		System.out.println("Liked List After");
		System.out.println(list);
		
		System.out.println();
		testSwapKthNodes();
		
		System.out.println();
		testSwapDataNodes();
	}

	/**
	 * Tests swap of kth nodes
	 */
	private static void testSwapKthNodes() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Testing Swap kth Nodes");
		System.out.println("Liked List Before");
		System.out.println(list);

		LinkedListUtil.swapKth(list, 3);
		System.out.println("Liked List After");
		System.out.println(list);
	}

	/**
	 * Tests swap of data nodes
	 */
	private static void testSwapDataNodes() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Testing Swap data nodes");
		System.out.println("Liked List Before");
		System.out.println(list);

		LinkedListUtil.swap(list, 10, 15);
		System.out.println("Liked List After");
		System.out.println(list);
	}
}
