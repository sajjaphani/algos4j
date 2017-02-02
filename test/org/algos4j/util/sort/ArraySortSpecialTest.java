package org.algos4j.util.sort;

import java.util.Arrays;

import org.algos4j.util.ArraySortUtil;

/**
 * Test on sorting the elements special cases.
 * 
 * @author psajja
 *
 */
public class ArraySortSpecialTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Sorting 0s and 1s");
		int[] array = {0, 1, 0, 1, 1, 1};
		System.out.println("Before: " + Arrays.toString(array));
	
		ArraySortUtil.sort0s1s(array);
		System.out.println("After: " + Arrays.toString(array));
		
		System.out.println();
		System.out.println("Sorting 0s, 1s and 2s");
		int[] array1 = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
		System.out.println("Before: " + Arrays.toString(array1));
	
		ArraySortUtil.sort0s1s2s(array1);
		System.out.println("After: " + Arrays.toString(array1));
		
		System.out.println();
		int[] array2 = {/*10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60*/ 0, 1, 15, 25, 6, 7, 30, 40, 50};
		System.out.println("Before: " + Arrays.toString(array2));
		int[] range = ArraySortUtil.findUnsortedRange(array2);
		System.out.println("Unsorted range: {" + range[0] + ", " + range[1] + "}");
	}
}
