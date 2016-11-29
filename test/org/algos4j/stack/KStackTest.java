package org.algos4j.stack;

/**
 * A test class for testing <code>KStack</code>
 * 
 * @author psajja
 *
 */
public class KStackTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		KStack stack = new KStack(3, 9);
		System.out.println("Initial Stacks");
		System.out.println(stack);
		System.out.println("Adding 10, 15, 20 to stack 0");
		stack.push(10, 0);
		stack.push(15, 0);
		stack.push(20, 0);
		
		System.out.println("Adding 100 to stack 1");
		stack.push(100, 1);
		System.out.println("Adding 1000 to stack 2");
		stack.push(1000, 2);
		
		System.out.println(stack);
		
		System.out.println("Removing top of stack 0");
		stack.pop(0);
		System.out.println("Stacks now");
		System.out.println(stack);
	}
}
