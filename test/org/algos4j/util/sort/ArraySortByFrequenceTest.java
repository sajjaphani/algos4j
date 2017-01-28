package org.algos4j.util.sort;

import java.util.Arrays;

import org.algos4j.util.ArraySortUtil;

/**
 * Test on sorting the elements by frequency.
 * 
 * @author psajja
 *
 */
public class ArraySortByFrequenceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {2, 5, 2, 8, 5, 6, 8, 8} /*{2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8}*/;
		System.out.println("Before: " + Arrays.toString(array));
	
		int[] sortedArray = ArraySortUtil.sortByFrequence(array);
		System.out.println("After: " + Arrays.toString(sortedArray));
	}
}
