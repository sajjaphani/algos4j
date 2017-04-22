package org.algos4j.util;

import java.util.Arrays;

/**
 * This class tests mazimizing the value (array[l] – array[k] + array[j] – array[i]), such that i < j < k < l.
 * 
 * @author psajja
 *
 */
public class MaximizeValueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = { 4, 8, 9, 2, 20 };
		System.out.println("Array: " + Arrays.toString(array));
		System.out.println("Max: " + ArrayUtil.maximizeValue(array));
	}

}
