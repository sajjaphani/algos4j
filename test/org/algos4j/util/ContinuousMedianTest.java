package org.algos4j.util;

import java.util.Arrays;

/**
 * Tests computing continuous median of integers.
 * 
 * @author psajja
 *
 */
public class ContinuousMedianTest {

	public static void main(String[] args) {
		int[] numbers = { 5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4 };
		System.out.println("Numbers");
		System.out.println(Arrays.toString(numbers));
		
		double[] median = ArrayUtil.getMedians(numbers);
		
		System.out.println("Medians");
		System.out.println(Arrays.toString(median));
	}
	
}
