package org.algos4j.list;

import org.algos4j.list.LinkedList.Node;

/**
 * Test class that tests fractional node in linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListFractionalNodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List");
		System.out.println(list);
		Node fractionalNode = LinkedListUtil.getFractionalNode(list, 4);
		if(fractionalNode == null)
			System.out.println("No such fractional node");
		else
			System.out.println("Fractional Node: " + fractionalNode.data);
	}
}