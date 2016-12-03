package org.algos4j.stack;

/**
 * A test class to test the sorting of a stack.
 * 
 * @author psajja
 *
 */
public class StackSortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {10, 5, 25, 15, 30};
		IntStack stack = StackUtil.constructStack(elements);
		System.out.println(stack);
		StackUtil.sort(stack);
		System.out.println("After sort");
		System.out.println(stack);
	}
}
