package org.algos4j.util;

import java.util.Arrays;

/**
 * This class tests computing max subarray of 0s and 1s equal.
 * 
 * @author psajja
 *
 */
public class MaxSubarrayOf0s1sTest {

	public static void main(String[] args) {
		int[] array = { 1, 0, 0, 1, 0, 1, 1 };
		System.out.println(Arrays.toString(array));
		int[] range = ArrayUtil.subarrayWith0s1s(array);
		System.out.println("Start index: " + range[0] + ", End index: " + range[1] + ", Size: " + (range[1] - range[0] + 1));
		
		findSubarrayOptimzed();
	}

	private static void findSubarrayOptimzed() {
		int[] array = { 0, 0, 0, 1, 0, 1, 1 };
		System.out.println(Arrays.toString(array));
		int[] range = ArrayUtil.subarrayWith0s1sOptimal(array);
		System.out.println("Start index: " + range[0] + ", End index: " + range[1] + ", Size: " + (range[1] - range[0] + 1));		
	}
}
