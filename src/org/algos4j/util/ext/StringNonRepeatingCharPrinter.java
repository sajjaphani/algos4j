package org.algos4j.util.ext;

import org.algos4j.util.StringUtil;

/**
 * This class is a processor to process the given string and print the non
 * repeating character at each step while reading the characters in the string.
 * For each new character retrieved from the string it updates and prints the 
 * non-repeating character at that moment.
 * 
 * @author psajja
 * 
 * @see StringUtil#getFirstNonRepeatingCharacter(String)
 *
 */
public class StringNonRepeatingCharPrinter {
	
	private static final int ASCII_CHAR_COUNT = 128;
	
	private Node head;
	private Node tail;
	
	/**
	 * Private constructor
	 */
	private StringNonRepeatingCharPrinter() {
		
	}
	
	/**
	 * Consume the given string to process.
	 * 
	 * @param string
	 * 		given string
	 */
	public static void use(String string) {
		StringNonRepeatingCharPrinter printer = new StringNonRepeatingCharPrinter();
		try {
			printer.process(string);
		} finally {
			// Cleanup
			printer.cleanup();
		}
	}
	
	/**
	 * Cleanup the stuff
	 */
	private void cleanup() {
		head = null;
		tail = null;
	}

	/**
	 * Process the given string character by character.
	 * 
	 * @param string
	 * 		given string
	 */
	private void process(String string) {
		if (string == null)
			System.err.println("Given a null string. Try with a new one.");

		// inList[x] contains a node if x is present in DLL
		Node[] inList = new Node[ASCII_CHAR_COUNT];

		// repeated[x] is true if x appears more than once
		boolean[] repeated = new boolean[ASCII_CHAR_COUNT];

		char[] chars = string.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char x = chars[i];
			System.out.println("Processing character '" + x + "' from the stream.");

			if (!repeated[x]) {
				if (inList[x] == null) {
					addNode(chars[i]);
					inList[x] = tail;
				} else {
					removeNode(inList[x]);
					inList[x] = null;
					repeated[x] = true;
				}
			}

			if (head != null)
				System.out.println("First non-repeating character so far is '" + head.chr + "'");
		}
	}
	
	/**
	 * Add a node to the doubly linked list.
	 * 
	 * @param chr
	 * 		character to add
	 */
	private void addNode(char chr) {
		Node temp = new Node(chr);

		if (head == null) {
			head = tail = temp;
			return;
		} else {
			tail.next = temp;
			temp.prev = tail;
			tail = temp;
		}
	}

	/**
	 * Remove the node from the doubly linked list.
	 * 
	 * @param head
	 * 		head node
	 * @param tail
	 * 		tail node
	 * @param node
	 * 		node to remove
	 */
	private void removeNode(Node node) {
		if (head == null)
			return;

		if (head == node)
			head = head.next;
		if (tail == node)
			tail = tail.prev;
		if (node.next != null)
			node.next.prev = node.prev;
		if (node.prev != null)
			node.prev.next = node.next;
	}

	/**
	 * A doubly linked linked list node.
	 * 
	 * @author psajja
	 *
	 */
	private static class Node {
		char chr;
		Node next;
		Node prev;
		
		Node(char chr) {
			this.chr = chr;
		}
	}
}