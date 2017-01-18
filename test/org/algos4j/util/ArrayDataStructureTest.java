package org.algos4j.util;

import java.util.Arrays;

/**
 * This class tests problems on arrays.
 * 
 * @author psajja
 *
 */
public class ArrayDataStructureTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Tree
		int[] elements = new int[] {4, 2, 5, 1, 3};
		System.out.println(Arrays.toString(elements));
		boolean sorted = ArrayUtil.isSorted(elements);
		if(sorted)
			System.out.println("Array is sorted.");
		else
			System.out.println("Array is not sorted.");
		
		int[] elements1 = new int[] {1, 2, 4, 6, 7, 9};
		System.out.println(Arrays.toString(elements1));
		sorted = ArrayUtil.isSorted(elements1);
		if(sorted)
			System.out.println("Array is sorted.");
		else
			System.out.println("Array is not sorted.");
		
		System.out.println();
		System.out.println(Arrays.toString(elements1));
		int index = ArrayUtil.binarySearch(elements1, 4);
		if(index == -1)
			System.out.println("Element is not found");
		else
			System.out.println("Element is found at: " + index);
		
		System.out.println();
		int[] elements2 = { 5, 6, 4, -1, 7, 8, 10 };
		System.out.println(Arrays.toString(elements2));
		int[] pair = ArrayUtil.getPair0(elements2, 10);
		if(pair != null)
			System.out.println("Pair: [" + pair[0] + ", " + pair[1] + "]");
		else
			System.out.println("No pair exists with the sum.");
		pair = ArrayUtil.getPair(elements2, 10);
		if(pair != null)
			System.out.println("Pair: [" + pair[0] + ", " + pair[1] + "]");
		else
			System.out.println("No pair exists with the sum.");
		
		int[] elements3 = { 3, 3, 4, 2, 4, 4, 2, 4, 4 };
		//int[] array = { 1, 2, 2, 2, 3 };
		System.out.println();
		System.out.println(Arrays.toString(elements3));
		int element = ArrayUtil.getMajorityElement(elements3);
		if (element == Integer.MIN_VALUE)
			System.out.println("No such majority element.");
		else
			System.out.println("Majority Element: " + element);
		
		int[] elements4 = new int[]{2, 3, 5, 4, 5, 2, 4, 3, 5, 2, 4, 4, 2};
		System.out.println();
		System.out.println(Arrays.toString(elements4));
        System.out.println("Odd number: " + ArrayUtil.findOddNumber(elements4));
        
        int[] elements5 =  {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println();
		System.out.println(Arrays.toString(elements5));
		System.out.println("Max Sum: " + ArrayUtil.maxSumSubArray(elements5));
		int[] result = ArrayUtil.maxSumSubArray1(elements5);
		System.out.println("Max Sum: " + result[0] + ", Start: " + result[1] + ", End: " + result[2]);
		
		int[] elements6 = { 1, 2, 4, 5, 6, 3, 8, 7 };
		System.out.println();
		System.out.println(Arrays.toString(elements6));
		System.out.println("Missing Number: " + ArrayUtil.findMissingNumber(elements6));
		
		int[] elements7 = { 4, 5, 6, 7, 1, 2, 3};
		System.out.println();
		System.out.println(Arrays.toString(elements7));
		System.out.println("3 Found at: " + ArrayUtil.searchPivoted(elements7, 3));
		System.out.println("Number of rations: " + ArrayUtil.findRotations(elements7));
		
		int[] array1 = {2, 8, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 13, Integer.MIN_VALUE, 15, 20};
        int[] array2 = {5, 7, 9, 25};
        System.out.println();
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
		ArrayUtil.merge(array1, array2);
		System.out.println("Merge: " + Arrays.toString(array1));
		
		System.out.println();
		System.out.println(Arrays.toString(elements7));
		ArrayUtil.reverse(elements7);
		System.out.println("Reverse: " + Arrays.toString(elements7));
		ArrayUtil.reverseRecursive(elements7);
		System.out.println("Reverse Recursive: " + Arrays.toString(elements7));
	}
}