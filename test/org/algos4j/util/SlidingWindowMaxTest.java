package org.algos4j.util;

import java.util.Arrays;

/**
 * A test class to compute the sliding window max for windows of size w.
 * 
 * @author psajja
 *
 */
public class SlidingWindowMaxTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int[] elements = {1, 2, 3, 1, 4, 5, 2, 3, 6};
		int[] elements = {12, 1, 78, 90, 57, 89, 56};
		System.out.println("Array");
		System.out.println(Arrays.toString(elements));
		int[] slides = ArrayUtil.computeSlidingWindowMax(elements, 3);
		System.out.println("Slides");
		System.out.println(Arrays.toString(slides));
	}
}
