package org.algos4j.util.sort;

import java.util.Arrays;

import org.algos4j.util.ArraySortUtil;

/**
 * This class tests sorting the given array in a wave form fashion.
 * 
 * @author psajja
 *
 */
public class SortInWafeFormTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array1 = { 10, 5, 6, 3, 2, 20, 100, 80 };
		System.out.println("Before: " + Arrays.toString(array1));
		ArraySortUtil.sortInWaveForm(array1);
		System.out.println("After: " + Arrays.toString(array1));

		System.out.println();
		int[] array2 = { 2, 4, 6, 8, 10, 20 };
		System.out.println("Before: " + Arrays.toString(array2));
		ArraySortUtil.sortInWaveForm(array2);
		System.out.println("After: " + Arrays.toString(array2));
	}

}
