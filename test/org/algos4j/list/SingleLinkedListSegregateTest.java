package org.algos4j.list;

/**
 * Test class that tests segregation related methods in a linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListSegregateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Segregate Even and Odd");
		System.out.println("Liked List Before ");
		System.out.println(list);
		LinkedListUtil.segregateEvenOdd(list);
		System.out.println("Liked List After ");
		System.out.println(list);
		
		System.out.println();
		testSegregate0to2();
	}

	/**
	 * segregate 0s, 1s, 2s
	 */
	private static void testSegregate0to2() {
		int[] elements = {0, 0, 1, 2, 0, 2, 0, 2, 2, 1, 1};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Segregate 0s, 1s and 2s");
		System.out.println("Liked List Before");
		System.out.println(list);
		LinkedListUtil.segregate0s1s2s(list);
		System.out.println("Liked List After");
		System.out.println(list);
	}

}
