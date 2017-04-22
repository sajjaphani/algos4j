package org.algos4j.util;

import java.util.Arrays;

/**
 * This class tests re-arranging the elements from array[i] to array[array[i]].
 * 
 * @author psajja
 *
 */
public class ArrayRearrangeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array1 = {2, 0, 1, 4, 5, 3};
		System.out.println("Array: " + Arrays.toString(array1));
		ArrayUtil.rearrange(array1);
		System.out.println("Rearrange: " + Arrays.toString(array1));
	}

}
