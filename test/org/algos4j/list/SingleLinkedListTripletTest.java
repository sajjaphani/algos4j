package org.algos4j.list;

import java.util.Arrays;

/**
 * Test class that tests the presence of a triplet.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListTripletTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int[] elements1 = {20, 5, 15, 100};
		int[] elements1 = {5, 15, 20, 100};
		LinkedList list1 = LinkedListUtil.createList(elements1);
		System.out.println("Liked List 1");
		System.out.println(list1);
		
		// int[] elements2 = {10, 9, 4, 2};
		int[] elements2 = {2, 4, 9, 10};
		LinkedList list2 = LinkedListUtil.createList(elements2);
		System.out.println("Liked List 2");
		System.out.println(list2);
		
		int[] elements3 = {1, 2, 4, 8};
		LinkedList list3 = LinkedListUtil.createList(elements3);
		System.out.println("Liked List 3");
		System.out.println(list3);
		
		int[] elements = LinkedListUtil.findTriplet(list1, list2, list3, 25);
		System.out.println("Triplet: " + Arrays.toString(elements));
	}
}