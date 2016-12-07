package org.algos4j.util.sort;

import java.util.Arrays;

import org.algos4j.util.ArraySortUtil;

/**
 * Test on selection sort algorithm.
 * 
 * @author psajja
 *
 */
public class SelectionSortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {45, 10, 20, 15, 30, 25, 20, 5};
		System.out.println("Before: " + Arrays.toString(array));
	
		ArraySortUtil.selection(array);
		System.out.println("After: " + Arrays.toString(array));
	}
}
