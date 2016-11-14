package org.algos4j.stack;

/**
 * Test class for testing <code>IntStackWithQueue</code>.
 * 
 * @author psajja
 *
 */
public class IntStackWithQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntStackWithQueue stack = new IntStackWithQueue(5);
		stack.push(15);
		stack.push(25);
		stack.push(30);
		System.out.println("Current Stack");
		System.out.println(stack);
		System.out.println("Popped Element : " + stack.pop());
		stack.push(50);
		stack.push(40);
		System.out.println("Stack Now");
		System.out.println(stack);
		System.out.println("Top Elt: " + stack.peek());
	}
}
