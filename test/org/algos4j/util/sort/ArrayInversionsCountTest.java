package org.algos4j.util.sort;

import java.util.Arrays;

import org.algos4j.util.ArraySortUtil;

/**
 * Test on counting the inversions in an array.
 * 
 * @author psajja
 *
 */
public class ArrayInversionsCountTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {1, 20, 6, 4, 5};
		System.out.println(Arrays.toString(array));
	
		System.out.println("Inversions: " + ArraySortUtil.countInversions(array));
	}
}
