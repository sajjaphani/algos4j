package org.algos4j.list;

import org.algos4j.list.LinkedList.Node;

/**
 * This test class will test whether two linked lists are intersecting at a node.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListIntersectNodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {10, 15, 30};
		LinkedList list1 = LinkedListUtil.createList(elements);
		System.out.println("Liked List 1");
		System.out.println(list1);
		
		int[] elementss = {3, 6, 9};
		LinkedList list2 = LinkedListUtil.createList(elementss);
		list2.getTail().next = list1.getHead().next;
		System.out.println("Liked List 2");
		System.out.println(list2);
		
		Node intersection = LinkedListUtil.getIntersectionNode(list1, list2);
		if(intersection != null)
			System.out.println("The linked lists intersect at node: " + intersection.data);
		else
			System.out.println("The linked lists does not intersect.");
	}

}
