package org.algos4j.stack;

import java.util.Arrays;

/**
 * @author psajja
 *
 */
public class NextGreaterElementTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {10, 55, 15, 45, 20, 33};
		System.out.println(Arrays.toString(elements));
		int[] nextElements = StackUtil.computeNextGreaterElements(elements);
		System.out.println(Arrays.toString(nextElements));
	}
}
