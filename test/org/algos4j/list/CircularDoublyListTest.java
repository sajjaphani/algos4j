package org.algos4j.list;

/**
 * A test class on circular double linked list.
 * 
 * @author psajja
 * 
 */
public class CircularDoublyListTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 10, 5, 20, 15 };
		DoublyLinkedList list = new CircularDoublyList();
		for (int elt : array) {
			list.insertLast(elt);
		}
		System.out.println("Printing list");
		list.print();
		System.out.println();
		System.out.println("Inserting 0 from front");
		list.insertFront(0);
		list.print();
		System.out.println(); 
		System.out.println("Inserting 11 at end");
		list.insertLast(11);
		list.print();
		System.out.println();
		System.out.println("Inserting 17, after 20");
		list.insertAfter(17, 20);
		list.print();
		System.out.println();
		System.out.println("Inserting 9, before 20");
		list.insertBefore(9, 20);
		list.print();
		System.out.println();
		System.out.println("Removing 20");
		list.delete(20);
		list.print();
		System.out.println();
		System.out.println("Size: " + list.size());
		System.out.println("Deleting item at pos: 6");
		list.deleteAt(6);
		list.print();
		System.out.println();
		System.out.println(list);
	}
}
