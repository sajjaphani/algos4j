package org.algos4j.stack;

/**
 * A test class to test <code>MiddleIntStack</code>
 * 
 * @author psajja
 *
 */
public class MiddleIntStackTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MiddleIntStack stack = new MiddleIntStack();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		System.out.println("Middle: " + stack.getMiddle());
		System.out.println(stack);
		System.out.println("Popped: " + stack.pop());
		System.out.println(stack);
		System.out.println("Middle: " + stack.getMiddle());
		
		stack.push(40);
		stack.push(50);
		stack.push(60);
		System.out.println(stack);
		System.out.println("Removed middle: " + stack.deleteMiddle());
		System.out.println(stack);
	}
}
