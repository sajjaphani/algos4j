package org.algos4j.queue;

/**
 * This test class will test the largest multiple that can be derived from a given digits.
 * 
 * @author psajja
 *
 */
public class LargestMultipleOf3Test {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] digits = {8, 1, 7, 6, 0};
		System.out.println("Largest multiple of 3: " + QueueUtil.findLargestMultipleOf3(digits));
	}
}
