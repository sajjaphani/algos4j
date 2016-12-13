package org.algos4j.list;

/**
 * Test class that tests the rotation of a linked list.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListRotationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Rotation Counter-clockwise");
		System.out.println("Liked List Before");
		System.out.println(list);
		LinkedListUtil.rotate(list, 2);
		System.out.println("Liked List After");
		System.out.println(list);
		
		System.out.println();
		testRotateRight();
	}

	/**
	 * Tests right rotation
	 */
	private static void testRotateRight() {
		int[] elements = {5, 10, 20, 15, 30, 25};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Rotation Right");
		System.out.println("Liked List Before");
		System.out.println(list);
		LinkedListUtil.rotateRight(list, 2);
		System.out.println("Liked List After");
		System.out.println(list);	
	}

}
