package org.algos4j.util.sort;

import java.util.Arrays;

import org.algos4j.util.ArraySortUtil;

/**
 * Test on insertion sort algorithm.
 * 
 * @author psajja
 *
 */
public class InsertionSortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {10, 20, 15, 30, 25, 20};
		System.out.println("Before: " + Arrays.toString(array));
	
		ArraySortUtil.insertion(array);
		System.out.println("After: " + Arrays.toString(array));
	}
}
