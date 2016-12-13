package org.algos4j.list;

/**
 * Test the existence of a loop in a linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListLoopTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List");
		System.out.println(list);
		System.out.println("List has loop: " + LinkedListUtil.hasLoop(list));
		
		System.out.println();
		testRemoveLoop();
	}

	/**
	 * Test removing the loop
	 */
	private static void testRemoveLoop() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		list.getTail().next = list.getHead();
		
		if(LinkedListUtil.hasLoop(list)) {
			System.out.println("Linked List Has Loop");
			LinkedListUtil.removeLoop(list);		
			System.out.println(list);
		}
	}
}