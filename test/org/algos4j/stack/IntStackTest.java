package org.algos4j.stack;

/**
 * Test class for testing <code>IntStack</code>.
 * 
 * @author psajja
 *
 */
public class IntStackTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntStack stack = new IntStack(5);
		stack.push(10);
		stack.push(20);
		stack.push(30);
		System.out.println("Current Stack");
		System.out.println(stack);
		System.out.println("Popped Element : " + stack.pop());
		stack.push(50);
		stack.push(45);
		System.out.println("Stack Now");
		System.out.println(stack);
		System.out.println("Stack Size: " + stack.size());
		System.out.println("Top Elt: " + stack.peek());
	}
}
