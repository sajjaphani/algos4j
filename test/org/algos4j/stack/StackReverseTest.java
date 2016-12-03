package org.algos4j.stack;

/**
 * A test class to test the reverse of stack.
 * 
 * @author psajja
 *
 */
public class StackReverseTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {10, 15, 5, 25, 30, 15};
		IntStack stack = StackUtil.constructStack(elements);
		System.out.println("Stack before");
		System.out.println(stack);
		
		StackUtil.reverse(stack);
		System.out.println("Stack after");
		System.out.println(stack);
	}
	
}
