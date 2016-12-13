package org.algos4j.list;

/**
 * Test class that tests random node value in linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListRandomNodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List");
		System.out.println(list);
		LinkedListUtil.random(list);
		 
		System.out.println("Random: " + LinkedListUtil.random(list));
	}
}