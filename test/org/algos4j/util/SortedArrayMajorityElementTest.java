package org.algos4j.util;

import java.util.Arrays;

import org.algos4j.util.ArraySortUtil;

/**
 * Test on checking a majority element in the given sorted array.
 * 
 * @author psajja
 *
 */
public class SortedArrayMajorityElementTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 3, 3, 3, 10};
		System.out.println(Arrays.toString(array));
	
		System.out.println("Is majority (3): " + ArraySortUtil.isMajority(array, 3));
	}
}
