package org.algos4j.util;

import java.util.Arrays;

/**
 * This method tests finding a sub-array with the given sum in an array.
 * 
 * @author psajja
 *
 */
public class FindSubarrayWithSumTest {

	public static void main(String[] args) {
		int[] array = {10, 2, -2, -20, 10};
        System.out.println("Array: " + Arrays.toString(array));

        int[] subarray = ArrayUtil.subarrayWithSum(array, -10);
		if(subarray.length == 0)
			System.out.println("Threre is no such sub-array with the sum -10");
		System.out.println("SubArray: " + Arrays.toString(subarray));
	}
}
