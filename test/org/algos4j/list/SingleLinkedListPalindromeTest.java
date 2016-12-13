package org.algos4j.list;

/**
 * Test the linked list is palindrome or not.
 * 
 * @author psajja
 *
 */
public class SingleLinkedListPalindromeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {5, 10, 20, 20, 10, 5};
		LinkedList list = LinkedListUtil.createList(elements);
		System.out.println("Liked List");
		System.out.println(list);
		System.out.println("Is palindrome: " + LinkedListUtil.isPalindrome(list));
	}

}
