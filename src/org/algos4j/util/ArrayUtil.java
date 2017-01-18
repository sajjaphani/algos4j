package org.algos4j.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Some utilities on arrays.
 * 
 * @author psajja
 *
 */
public class ArrayUtil {

	/**
	 * Not-instantiable
	 */
	private ArrayUtil() {
	}
	
	/**
	 * Given an integer array, it returns the maximum.
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @return
	 * 		maximum value
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 * @throws IllegalArgumentException
	 * 		if the input array is empty
	 */
	public static int getMax(int[] array) {
		if(array == null)
			throw new NullPointerException("Input array should not be null");
		if(array.length == 0)
			throw new IllegalArgumentException("Input array has no elements");
		
		int max = array[0];
		for (int i = 0; i < array.length; i++)
			if (array[i] > max)
				max = array[i];

		return max;
	}
	
	/**
	 * Given an array where the array elements represents a binary tree, this
	 * method prints them in inorder. If the array elements represent a binary
	 * search tree, inorder produces sorting order. For each node at position i,
	 * its left child is at '2 * i + 1' and its right child is at '2 * i + 2'.
	 * 
	 * @param array
	 *    	given integer array
	 * 
	 * @throws NullPointerException
	 *       	if the input array is null
	 */
	static void printInorder(int[] array) {
		if(array == null)
			throw new NullPointerException("Input array should not be null");
		
		printInorder(array, 0, array.length - 1);
	}
	
	/**
	 * Recursively print the array in inorder fashion.
	 * 
	 * @param array
	 * 		integer array
	 * @param start
	 * 		start position
	 * @param end
	 * 		end position
	 */
	private static void printInorder(int[] array, int start, int end) {
		if (start > end)
			return;

		printInorder(array, start * 2 + 1, end);

		System.out.print(array[start] + " ");

		printInorder(array, start * 2 + 2, end);
	}
	
	/**
	 * Given a preorder traversal, this method checks whether the given tree has
	 * exactly one child at each node. If all nodes have only one child in a
	 * BST, then for each node, the descendants of it are either smaller or
	 * larger than the node.
	 * 
	 * @param preorder
	 *     	given preorder traversal
	 * 
	 * @return 
	 * 		true if each node has a single child, false otherwise
	 * 
	 * @throws NullPointerException
	 *       	if the input array is null
	 */
	public static boolean hasOneChild(int[] preorder) {
		if (preorder == null)
			throw new NullPointerException("Preorder array should not be null");

		int size = preorder.length;

		for (int i = 0; i < size - 1; i++) {
			if (preorder[i + 1] < preorder[i] && preorder[i] < preorder[size - 1])
				return false;
		}

		return true;
	}
	
	/**
	 * Given an array it checks whether the array elements are in sorted order.
	 * Ascending order.
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @return
	 * 		true if the array elements are in sorted order
	 * 
	 * @throws NullPointerException
	 *       	if the input array is null
	 */
	public static boolean isSorted(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1] > array[i])
				return false;
		}
		
		return true;
	}
	
	/**
	 * Searches for an element in a sorted array with the given key.
	 * This method does not test whether the array is sorted.
	 * 
	 * @param array
	 *   	input array
	 * @param key
	 *    	given key
	 * 
	 * @return 
	 * 		index if element is found, -1 otherwise.
	 * 
	 * @throws NullPointerException
	 *    	if the input array is null
	 */
	public static int binarySearch(int[] array, int key) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int low = 0;
		int high = array.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (key == array[mid])
				return mid;
			else if (key < array[mid])
				high = mid - 1;
			else
				low = mid + 1;
		}

		return -1;
	}
	
	/**
	 * Given an array and a sum, this method finds whether there is a pair
	 * exists in the array with the sum. Returns first occurrence.
	 * 
	 * This method first sorts the array using Merge sort and then checks from
	 * left to right for any pairs. Time: O(n log n).
	 * 
	 * @param array
	 *     	the input integer array
	 * @param sum
	 *     the sum to find
	 *     
	 * @return
	 *     the array pair if exists, null otherwise
	 *     
	 * @throws NullPointerException
	 *    	if the input array is null
	 */
	static int[] getPair0(int[] array, int sum) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		
		Arrays.sort(array);

		int left = 0;
		int right = array.length - 1;
		while (left < right) {
			if (array[left] + array[right] == sum) {
				return new int[] {array[left], array[right]};
				// left++;
				// right--;
			} else if (array[left] + array[right] < sum)
				left++;
			else
				right--;
		}
		
		return null;
	}

	/**
	 * Time optimized version of {@link #hasPair(int[], int)}.
	 * 
	 * Uses Map for storing the values. Time: O(n), Space: O(n).
	 * 
	 * @param array
	 *     	the input integer array
	 * @param sum
	 *     	the sum to find
	 * @return 
	 * 		the array pair if exists, null otherwise
	 *     
	 * @throws NullPointerException
	 *    	if the input array is null
	 */
	public static int[] getPair(int[] array, int sum) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		
		int rem;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < array.length; i++) {
			rem = sum - array[i];
			if (map.containsKey(rem)) {
				//System.out.println(array[i] + " " + temp);
				return new int[] { array[i], rem };
			}
			map.put(array[i], 1);
		}
		
		return null;
	}
	
	/**
	 * Given an integer array it finds the majority element if one exists. A
	 * majority element in an array is an element that appears more than
	 * half times. There exists only one such element in any given array.
	 * Time: O(n), space: O(n)
	 * 
	 * @param array
	 *    	integer array
	 * 
	 * @return 
	 * 		majority element if exists, {@link Integer#MIN_VALUE} otherwise
	 * 
	 * @throws NullPointerException
	 *    	if the input array is null
	 */
	static int getMajorityElement0(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int count = 0;
		for (int element : array) {
			count = 0;
			if (map.containsKey(element))
				count = map.get(element);
			count++;
			if (count > array.length / 2)
				return element;
			map.put(element, count);
		}

		return Integer.MIN_VALUE;
	}
	
	/**
	 * This method computes the majority element using which is using
	 * Bayer-Moore’s Voting Algorithm. It is a two phase algorithm and in each
	 * phase it takes time O(n). The majority vote problem is to determine in
	 * any given sequence of choices whether there is a choice with more
	 * occurrences than all the others.
	 * 
	 * @param array
	 *     	integer array
	 * 
	 * @return 
	 * 		majority element if exists, {@link Integer#MIN_VALUE} otherwise
	 * 
	 * @throws NullPointerException
	 *     	if the input array is null
	 */
	public static int getMajorityElement(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int size = array.length;
		int candidate = 0, count = 0;
		for (int i = 0; i < size; i++) {
			if (count == 0) {
				candidate = array[i];
				count = 1;
			} else {
				if (array[i] == candidate) {
					count++;
				} else {
					count--;
				}
			}
		}

		count = 0;
		for (int i = 0; i < size; i++) {
			if (array[i] == candidate)
				count++;
		}

		if (count > size / 2)
			return candidate;

		return Integer.MIN_VALUE;
	}
	
	/**
	 * Given an array of positive integers, all the numbers occur even number of
	 * times except one number, which occurs odd number of times. Time: O(n).
	 * 
	 * @param array
	 * 		given integer array
	 * 
	 * @return 
	 * 		the number which occurs odd times, zero if all are even times,
	 *      unknown if multiple numbers occurs odd times.
	 *      
	 * @throws NullPointerException
	 *     	if the input array is null
	 */
	public static int findOddNumber(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		
		int oddNumber = 0;
		for (int num : array)
			oddNumber = oddNumber ^ num;

		return oddNumber;
	}
	
	/**
	 * Given an array it computes the maximum of sub-array sum.
	 * This is based on Kadane's algorithm. While scanning the array values, at
	 * each position, it computes the maximum of sub-array ending at that
	 * position. The sub-array can be either empty or consists of one more
	 * element than the maximum sub-array ending at the previous position.
	 * Dynamic programming problem. Time: O(n).
	 * 
	 * @param array
	 *      	the given input array.
	 * 
	 * @return 
	 * 		the maximum sub-array sum
	 * 
	 * @throws NullPointerException
	 *     	if the input array is null
	 */
	public static int maxSumSubArray(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int maxHere = array[0];
		int maxSoFar = array[0];

		for (int i = 1; i < array.length; i++) {
			maxHere = Math.max(array[i], maxHere + array[i]);
			maxSoFar = Math.max(maxSoFar, maxHere);
		}

		return maxSoFar;
	}
	
	/**
	 * A variant version of {@link #maxSumSubArray(int[])}, which also returns the
	 * start and end index of the sub-array.
	 * 
	 * @param array
	 *     	the given input array.
	 * 
	 * @return 
	 * 		an array whose values are, {0: max sum, 1: start index, 2: end index}
	 * 
	 * @throws NullPointerException
	 *     	if the input array is null
	 */
	static int[] maxSumSubArray1(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		
		int maxHere = array[0];
		int maxSoFar = array[0];

		int[] result = { array[0], 0, 0 };
		int tmpStart = 0;
		for (int i = 1; i < array.length; i++) {
			if (maxHere + array[i] > array[i]) {
				maxHere = maxHere + array[i];
			} else {
				maxHere = array[i];
				tmpStart = i;
			}
			if (maxHere > maxSoFar) {
				maxSoFar = maxHere;
				result[0] = maxSoFar;
				result[1] = tmpStart;
				result[2] = i;
			}
		}

		return result;
	}
	
	/**
	 * Given an array of n numbers, which are supposed to be in the range of 
	 * 1 to n + 1 with no duplicates, find the number that is missing. We can 
	 * compute the number based on formulae n*(n+1)/2, but integer overflow 
	 * might occur. This method is based on XOR operation. Eliminates integer 
	 * overflow. Time: O(n).
	 * 
	 * @param array
	 * 		integer array
	 * 
	 * @return
	 * 		missing number if the array is proper, otherwise unknown for the same reason
	 * 
	 * @throws NullPointerException
	 *     	if the input array is null
	 */
	public static int findMissingNumber(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		
		int xorArray = array[0];
		int xorNumbers = 1;

		// XOR of array elements
		for (int i = 1; i < array.length; i++)
			xorArray = xorArray ^ array[i];

		// XOR of numbers from 1 to n + 1
		for (int i = 2; i <= array.length + 1; i++)
			xorNumbers = xorNumbers ^ i;

		return (xorArray ^ xorNumbers);
	}
	
	/**
	 * Given an array, which is sorted and rotated, it finds the given number in
	 * the array. It uses a variant of Binary Search. Based on the fact that one
	 * sub-array will always be sorted. This approach does not work if the array
	 * has duplicates as it is not possible to determine the pivoted point.
	 * 
	 * Computes in O(log n).
	 * 
	 * @param array
	 * 		the integer array
	 * 
	 * @return 
	 * 		number of times the array is rotated.
	 * 
	 * @throws NullPointerException
	 *  	if the input array is null
	 */
	public static int searchPivoted(int[] array, int elt) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int low = 0, high = array.length - 1;
		int mid;

		while (low <= high) {
			mid = (low + high) / 2;
			if (elt == array[mid])
				return mid;
			if (array[mid] <= array[high]) {
				if (elt > array[mid] && elt <= array[high])
					low = mid + 1;
				else
					high = mid - 1;
			} else {
				if (elt <= array[mid] && elt >= array[low])
					high = mid - 1;
				else
					low = mid + 1;
			}
		}

		return -1;
	}
	
	/**
	 * Given an array, which is sorted and rotated, it finds the number of
	 * rotations in the array. The number of times the array is rotated towards
	 * right. It uses a variant of Binary Search, based on the fact that the
	 * next and previous elements of the pivot element are greater. This
	 * approach doesn't handle the duplicates in the given array. Time: O(log n).
	 * 
	 * @param array
	 *    	the integer array
	 * 
	 * @return 
	 * 		number of times the array is rotated.
	 * 
	 * @throws NullPointerException
	 *     	if the input array is null
	 */
	public static int findRotations(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int size = array.length;
		int low = 0, high = size - 1;
		int next, prev;
		int mid = 0;

		while (low <= high) {
			if (array[low] <= array[high])
				return low;
		
			mid = (low + high) / 2;
			next = (mid + 1) % size;
			prev = (mid - 1 + size) % size;
			if (array[mid] < array[next] && array[mid] < array[prev])
				return mid;
		
			if (array[mid] < array[high])
				high = mid - 1;

			if (array[mid] > array[low])
				low = mid + 1;
		}

		return -1;
	}
	
	/**
	 * Given two arrays, where first one has m + n positions where the empty
	 * slots are represented by {@link Integer#MIN_VALUE}, this method merges
	 * the second array elements at alternative positions into first one.
	 * 
	 * @param array1
	 * 		first array (m + n) positions
	 * @param array2
	 * 		second array (n) positions
	 * 
	 * @throws NullPointerException
	 *     	if any of the input array is null
	 */
	public static void merge(int[] array1, int[] array2) {
		if (array1 == null || array2 == null)
			throw new NullPointerException("Input array should not be null");

		// Move empty slots to right end
		for (int i = array1.length - 1, j = array1.length - 1; i >= 0; i--) {
			if (array1[i] != Integer.MIN_VALUE) {
				array1[j] = array1[i];
				j--;
			}
		}

		int i = array2.length;
		int j = 0, k = 0;

		while (k < (array1.length)) {
			if ((i < (array1.length) && array1[i] <= array2[j]) || (j == array2.length)) {
				array1[k] = array1[i];
				k++;
				i++;
			} else {
				array1[k] = array2[j];
				k++;
				j++;
			}
		}
	}
	
	/**
	 * Given an integer array, it reverses the array.
	 * 
	 * @param array
	 *    	the input array
	 * 
	 * @throws NullPointerException
	 *    	if any of the input array is null
	 */
	public static void reverse(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		
		int size = array.length;
		for (int i = 0; i < size / 2; i++) {
			int temp = array[i];
			array[i] = array[size - i - 1];
			array[size - i - 1] = temp;
		}
	}

	/**
	 * Reverses the given integer array. Recursive version.
	 * 
	 * @param array
	 *     	the input array to reverse
	 *     
	 * @throws NullPointerException
	 *    	if any of the input array is null
	 */
	public static void reverseRecursive(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		
		if (array.length <= 1)
			return;
		reverse(array, 0, array.length - 1);
	}

	/**
	 * Reverse recursively from the given range elements.
	 * 
	 * @param array
	 * 		integer array
	 * @param start
	 * 		start index
	 * @param end
	 * 		end index
	 */
	private static void reverse(int array[], int start, int end) {
		if (start >= end)
			return;

		int temp;
		temp = array[start];
		array[start] = array[end];
		array[end] = temp;
		reverse(array, start + 1, end - 1);
	}
}