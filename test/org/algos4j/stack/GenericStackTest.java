package org.algos4j.stack;

import org.algos4j.stack.ext.Stack;

/**
 * Generic Stack test.
 * 
 * @author psajja
 *
 */
public class GenericStackTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		stack.push("Hello");
		System.out.println("Popped element: " + stack.pop());
		// Below is compile time error
		// stack.push(new Integer(0));
	}
}
