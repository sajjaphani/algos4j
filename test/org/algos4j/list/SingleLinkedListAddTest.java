package org.algos4j.list;

/**
 * Test class that tests adding two numbers represented by linked lists.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListAddTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// List 1: 	5->6->3, number - 365
		// List 2: 	8->4->2, number - 248
		// Result:	3->1->6, number - 613
		
		int[] elements1 = {5, 6, 3};
		LinkedList list1 = LinkedListUtil.createList(elements1);
		System.out.println("Adding two linked lists reverse");
		System.out.println("Liked List 2");
		System.out.println(list1);
		
		int[] elements2 = {8, 4, 2};
		LinkedList list2 = LinkedListUtil.createList(elements2);
		System.out.println("Liked List 2");
		System.out.println(list2);
		
		LinkedList result = LinkedListUtil.addReverse(list1, list2);
		System.out.println("Result List");
		System.out.println(result);
		
		System.out.println();
		testAddNumbers();
	}

	/**
	 * Tests adding of two linked lists
	 */
	private static void testAddNumbers() {
		// List 1:	5->6->3,    number - 563
		// List 2:	8->4->2,    number - 824
		// Result:	1->4->0->5, number - 1405
		 
		int[] elements1 = {5, 6, 3};
		LinkedList list1 = LinkedListUtil.createList(elements1);
		System.out.println("Adding two linked lists");
		System.out.println("Liked List 2");
		System.out.println(list1);
		
		int[] elements2 = {8, 4, 2};
		LinkedList list2 = LinkedListUtil.createList(elements2);
		System.out.println("Liked List 2");
		System.out.println(list2);
		
		LinkedList result = LinkedListUtil.add(list1, list2);
		System.out.println("Result List");
		System.out.println(result);
		
	}

}
