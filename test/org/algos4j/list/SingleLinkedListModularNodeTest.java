package org.algos4j.list;

import org.algos4j.list.LinkedList.Node;

/**
 * Test class that tests modular node of a linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListModularNodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Modulear node from begin");
		System.out.println("Liked List");
		System.out.println(list);
		Node modularNode = LinkedListUtil.getModularFromBegin(list, 7);
		if(modularNode == null)
			System.out.println("No such modular node");
		else
			System.out.println("Modular Node: " + modularNode.data);
		
		System.out.println();
		testModularNodeFromEnd();
	}

	/**
	 * Tests odd length of a given linked list
	 */
	private static void testModularNodeFromEnd() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Modulear node from end");
		System.out.println("Liked List");
		System.out.println(list);
		Node modularNode = LinkedListUtil.getModularFromEnd(list, 2);
		if(modularNode == null)
			System.out.println("No such modular node");
		else
			System.out.println("Modular Node: " + modularNode.data);
	}

}
