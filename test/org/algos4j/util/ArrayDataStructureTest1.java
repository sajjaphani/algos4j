package org.algos4j.util;

import java.util.Arrays;

/**
 * This class tests some additional problems on arrays.
 * 
 * @author psajja
 *
 */
public class ArrayDataStructureTest1 {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int[] elements = new int[] {/*2, 3, 10, 6, 4, 8, 1*/ 7, 9, 5, 6, 3, 2 };
		System.out.println(Arrays.toString(elements));
		
		System.out.println("Max Pair Diff: " + ArrayUtil.getMaxPairDiff(elements));
		
		int[] array1 = new int[] {1, 2, 4, 5, 6};
		int[] array2 = new int[] {2, 3, 5, 7};
		System.out.println();
		System.out.println("Array 1: " + Arrays.toString(array1));
		System.out.println("Array 2: " + Arrays.toString(array2));
		System.out.println("Union: ");
		ArraySortUtil.printUnion(array1, array2);
		System.out.println();
		System.out.println("Intersection: ");
		ArraySortUtil.printIntersection(array1, array2);
		
		System.out.println();
		int[] array3 = new int[] {1, 2, 8, 10, 10, 12, 19};
		System.out.println(Arrays.toString(array3));
		System.out.println("Ceil of 13: " + ArraySortUtil.ceil(array3, 13));
		System.out.println("Floor of 20: " + ArraySortUtil.floor(array3, 20));
	}
}