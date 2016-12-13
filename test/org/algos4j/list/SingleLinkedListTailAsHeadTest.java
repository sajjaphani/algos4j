package org.algos4j.list;

/**
 * Test making tail as head in a single linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListTailAsHeadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Making Tail as head");
		System.out.println("Liked List Before");
		System.out.println(list);
		
		LinkedListUtil.makeTailAsHead(list);
		System.out.println("Liked List After");
		System.out.println(list);
	}
}
