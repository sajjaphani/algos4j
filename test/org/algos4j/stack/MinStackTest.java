package org.algos4j.stack;

/**
 * Test class for MinIntStack.
 * 
 * @author psajja
 * 
 */
public class MinStackTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = { 25, 20, 15, 30, 10 };
		MinIntStack stack = new MinIntStack(elements.length);
		for (int element : elements)
			stack.push(element);
		System.out.println("Min: " + stack.min());
		stack.pop();
		System.out.println("Min: " + stack.min());
		stack.pop();
		stack.pop();
		System.out.println("Min: " + stack.min());
		System.out.println("Peek: " + stack.peek());
	}
}
