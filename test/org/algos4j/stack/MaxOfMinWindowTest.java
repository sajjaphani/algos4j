package org.algos4j.stack;

import java.util.Arrays;

import org.algos4j.stack.StackUtil;

/**
 * A test class to test the max of each min window of varying size from 1 to n.
 * 
 * @author psajja
 *
 */
public class MaxOfMinWindowTest {

	public static void main(String[] args) {
		int[] input = {10, 20, 30, 50, 10, 70, 30};
		int[] result = StackUtil.maxOfMinWindow(input);
		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(result));
	}
}
