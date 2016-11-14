package org.algos4j.list;

/**
 * A test class on single linked list.
 * 
 * @author psajja
 * 
 */
public class LinkedListTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 10, 5, 20, 15 };
		LinkedList list = new LinkedList();
		for (int elt : array) {
			list.insertLast(elt);
		}

		list.print();
		System.out.println();
		list.insertFront(0);
		list.print();
		System.out.println();
		list.insertLast(11);
		list.print();
		System.out.println();
		list.insertAfter(17, 20);
		list.print();
		System.out.println();
		list.insertBefore(9, 20);
		list.print();
		System.out.println();
		list.delete(20);
		list.print();
		System.out.println();
		System.out.println("Size: " + list.size());
		list.deleteAt(6);
		list.print();
		System.out.println();
		System.out.println(list);
		list.insertBefore(19, 200);
		list.print();
	}
}
