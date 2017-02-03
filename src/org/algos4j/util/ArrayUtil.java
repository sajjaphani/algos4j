package org.algos4j.util;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import org.algos4j.heap.BinaryHeap;
import org.algos4j.heap.MaxBinaryHeap;
import org.algos4j.heap.MinBinaryHeap;

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
		for (int i = 1; i < array.length; i++)
			if (array[i] > max)
				max = array[i];

		return max;
	}
	
	/**
	 * Given an integer array, it returns the minimum.
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @return
	 * 		minimum value
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 * @throws IllegalArgumentException
	 * 		if the input array is empty
	 */
	public static int getMin(int[] array) {
		if(array == null)
			throw new NullPointerException("Input array should not be null");
		if(array.length == 0)
			throw new IllegalArgumentException("Input array has no elements");
		
		int min = array[0];
		for (int i = 1; i < array.length; i++)
			if (array[i] < min)
				min = array[i];

		return min;
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
	 * Given an array, it finds the maximum sum from the array such that 
	 * no two consecutive elements are not picked to compute the sum.
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
	public static int maxSumNotAdjacent(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int sumInclusive = array[0];
		int sumExclusive = 0;

		for (int i = 1; i < array.length; i++) {
			int newExclusive = Math.max(sumInclusive, sumExclusive);

			sumInclusive = sumExclusive + array[i];
			sumExclusive = newExclusive;
		}

		return Math.max(sumInclusive, sumExclusive);
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
	 *    	if the input array is null
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
	 *    	if the input array is null
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
	
	/**
	 * 
	 * Given an array of integers, this method all the leaders in the array. 
	 * An element is leader if it is greater than all the elements to its right. 
	 * 
	 * @throws NullPointerException
	 *    	if the input array is null
	 */
	static void printLeaders(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int maxFromRight = array[array.length - 1];

		// Right most is always leader
		System.out.print(maxFromRight);

		for (int i = array.length - 2; i >= 0; i--) {
			if (maxFromRight < array[i]) {
				System.out.print(" " + array[i]);
				maxFromRight = array[i];
			}
		}
	}
	
	/**
	 * Given an array and a sum, this method finds the pair whose sum results to
	 * the closest of given sum.
	 * 
	 * @param array
	 * 		given integer array
	 * @param sum
	 * 		expected closest sum
	 * 
	 * @return
	 * 		a pair whose sum is close
	 * 
	 * @throws NullPointerException
	 *    	if the input array is null
	 *  @throws IllegalArgumentException
	 *  	if the array does not have enough elements (< 2)
	 */
	public static int[] getClosestPair(int[] array, int sum) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		if (array.length < 2)
			throw new IllegalArgumentException("Not enough elements in the array");

		int sumNow, minSum = Integer.MAX_VALUE;

		int l = 0, r = array.length - 1;

		// Keep track of the left and right indices
		int lMin = l, rMin = array.length - 1;

		// Sort
		Arrays.sort(array);

		while (l < r) {
			sumNow = array[l] + array[r];

			if (Math.abs(sumNow) < Math.abs(minSum)) {
				minSum = sumNow;
				lMin = l;
				rMin = r;
			}

			if (sumNow < sum)
				l++;
			else
				r--;
		}

		return new int[] { array[lMin], array[rMin] };
	}
	
	/**
	 * Given an array, it prints the k smallest elements in the array.
	 * Uses min binary heap. 
	 * 
	 * @param array
	 * 		input array
	 * @param k
	 * 		number of elements to print
	 * 
	 * @throws NullPointerException
	 *    	if the input array is null
	 *  @throws IllegalArgumentException
	 *  	if the given k (< 1)
	 */
	static void printSmallestK(int[] array, int k) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		if (array.length < 1)
			throw new IllegalArgumentException("Invalid k, should be > 0.");

		BinaryHeap<Integer> heap = new MinBinaryHeap<>();
		for (int elt : array)
			heap.add(elt);

		for (int i = 0; i < k; i++)
			System.out.print(heap.remove() + " ");
		System.out.println();
	}
	
	/**
	 * Given an array, it prints the k largest elements in the array.
	 * Uses max binary heap. 
	 * 
	 * @param array
	 * 		input array
	 * @param k
	 * 		number of elements to print
	 * 
	 * @throws NullPointerException
	 *    	if the input array is null
	 *  @throws IllegalArgumentException
	 *  	if the given k (< 1)
	 */
	static void printLargestK(int[] array, int k) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		if (array.length < 1)
			throw new IllegalArgumentException("Invalid k, should be > 0.");

		BinaryHeap<Integer> heap = new MaxBinaryHeap<>();
		for (int elt : array)
			heap.add(elt);

		for (int i = 0; i < k; i++)
			System.out.print(heap.remove() + " ");
		System.out.println();
	}
	
	
	/**
	 * Given an array, it finds the maximum difference of the elements. 
	 * The larger element appears after the smaller number for the element diff.
	 * 
	 * @param array
	 * 		input array
	 * 
	 * @return
	 * 		max difference
	 * 
	 * @throws NullPointerException
	 *    	if the input array is null
	 *  @throws IllegalArgumentException
	 *  	if the array does not have sufficient elements ( < 2).
	 */
	public static int getMaxPairDiff(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		if (array.length < 2)
			throw new IllegalArgumentException("Not enough elements in the array");

		int diff = array[1] - array[0];
		int currSum = diff;
		int maxSum = diff;

		for (int i = 1; i < array.length - 1; i++) {
			diff = array[i + 1] - array[i];

			// Update current sum
			if (currSum > 0)
				currSum += diff;
			else
				currSum = diff;

			// Update max sum
			if (currSum > maxSum)
				maxSum = currSum;
		}

		return maxSum;
	}
	
	/**
	 * Given an array, it produces product array such that the product[i] is the
	 * result of multiplying the array elements other than element at i.
	 * 
	 * @param array
	 * 		input array
	 * 
	 * @return
	 * 		product array
	 * 
	 * @throws NullPointerException
	 *    	if any of the input array is null
	 */
	public static int[] product(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int[] product = new int[array.length];
		int prod = 1;
		for (int j = 0; j < array.length; j++)
			product[j] = 1;

		// 'prod' contains product on left side of i
		for (int i = 0; i < array.length; i++) {
			product[i] = prod;
			prod *= array[i];
		}
		
		// reset
		prod = 1;

		// 'prod' contains product on right side of i
		for (int i = array.length - 1; i >= 0; i--) {
			product[i] *= prod;
			prod *= array[i];
		}

		return product;
	}
	
	/**
	 * Given an array, it re-arranges all elements such that the odd
	 * numbers follows all even numbers.
	 * 
	 * @param array
	 * 		input array
	 * 
	 * @throws NullPointerException
	 *    	if any of the input array is null
	 */
	public static void segregateEvenOdd(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int left = 0, right = array.length - 1;
		while (left < right) {
			while (array[left] % 2 == 0 && left < right)
				left++;

			while (array[right] % 2 == 1 && left < right)
				right--;

			if (left < right) {
				int temp = array[left];
				array[left] = array[right];
				array[right] = temp;
				left++;
				right--;
			}
		}
	}
	
	/**
	 * Given an array, this method prints the duplicates in the array.
	 * This method does modify the array elements.
	 * 
	 * @param array
	 * 		input array
	 * 
	 * @throws NullPointerException
	 *    	if any of the input array is null
	 */
	static void printDuplicates(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		
		for (int i = 0; i < array.length; i++) {
			if (array[Math.abs(array[i])] >= 0)
				array[Math.abs(array[i])] = -array[Math.abs(array[i])];
			else
				System.out.print(Math.abs(array[i]) + " ");
		}
	}
	
	/**
	 * Given an array it finds the equilibrium index in the array. Equilibrium
	 * index is an index such that the sum of elements at lesser 
	 * indices is equal to the sum of elements at higher indices.
	 * 
	 * @param array
	 *     	input array
	 * 
	 * @return
	 * 		the equilibrium index, -1 if there is no such index
	 * 
	 * @throws NullPointerException
	 *             if any of the input array is null
	 */
	public static int getEquilibriumIndex(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int sum = 0;
		int leftSum = 0;

		for (int i = 0; i < array.length; ++i)
			sum += array[i];

		for (int i = 0; i < array.length; ++i) {
			// Holds right sum
			sum -= array[i];

			if (leftSum == sum)
				return i;

			leftSum += array[i];
		}

		return -1;
	}
	
	/**
	 * Given an array, this method finds the next greater element for each
	 * element. The greater for an element is the first greater element on the
	 * right side in the array. Elements for which no greater element exist is
	 * represented -1.
	 * 
	 * @param array
	 * 		input array
	 * 
	 * @return
	 * 		array containing next greater
	 * 
	 * @throws NullPointerException
	 *             if any of the input array is null
	 */
	public static int[] findNextGreater(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		int[] result = new int[array.length];

		Stack<Integer> stack = new Stack<>();
		int index, nextIndex;

		stack.push(0);

		for (int i = 1; i < array.length; i++) {
			nextIndex = i;

			if (!stack.isEmpty()) {
				index = stack.pop();

				while (array[index] < array[nextIndex]) {
					result[index] = array[nextIndex];
					if (stack.isEmpty())
						break;
					index = stack.pop();
				}

				if (array[index] > array[nextIndex])
					stack.push(index);
			}

			stack.push(nextIndex);
		}

		while (!stack.isEmpty())
			result[stack.pop()] = -1;

		return result;
	}
	
	/**
	 * Given an array of elements this method checks whether the numbers are
	 * consecutive or not. Time: O(n), Space: O(n)
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @return
	 * 		true if the numbers are consecutive, false otherwise
	 * 
	 * @throws NullPointerException
	 *     	if any of the input array is null
	 */
	public static boolean areConsecutive(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int min = getMin(array);
		int max = getMax(array);

		if (max - min + 1 != array.length)
			return false;

		boolean[] visited = new boolean[array.length];

		for (int i = 0; i < array.length; i++) {
			if (visited[array[i] - min] != false)
				return false;

			visited[array[i] - min] = true;
		}

		return true;
	}
	
	/**
	 * Given an array, it finds the maximum array difference such that the first
	 * element is lesser than the second one. In other words, maximum (j – i)
	 * such that array[j] > array[i].
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @return
	 * 		max index array diff
	 * 
	 * @throws NullPointerException
	 *     	if any of the input array is null
	 */
	public static int maxIndexDifference(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		int n = array.length;

		int rMax[] = new int[array.length];
		int lMin[] = new int[array.length];

		// lMin[i] holds the minimum value till index i
		lMin[0] = array[0];
		for (int i = 1; i < n; ++i)
			lMin[i] = Math.min(array[i], lMin[i - 1]);

		// lMax[i] holds the minimum value from index j
		rMax[n - 1] = array[n - 1];
		for (int j = n - 2; j >= 0; --j)
			rMax[j] = Math.max(array[j], rMax[j + 1]);

		int i = 0;
		int j = 0;
		int maxDiff = -1;
		while (j < n && i < n) {
			if (lMin[i] < rMax[j]) {
				maxDiff = Math.max(maxDiff, j - i);
				j = j + 1;
			} else
				i = i + 1;
		}

		return maxDiff;
	}
	
	/**
	 * Given an array of integers, and a window size w, 
	 * this method computes the maximum of sliding windows of size w.
	 * Time: O(n), Space: O(w)
	 * 
	 * @param array
	 * 		input array
	 * @param w
	 * 		window size
	 * 
	 * @return
	 * 		array containing max for each window size of w
	 * 
	 * @throws NullPointerException
	 * 		if the given array is null
	 * @throws IllegalArgumentException
	 * 		if the given window size is not valid (> 0 && <= array size)
	 */
	public static int[] computeSlidingWindowMax(int[] array, int w) {

		if (array == null)
			throw new NullPointerException("Array should not be null.");
		if (w <= 0 || w > array.length)
			throw new IllegalArgumentException(
					"Window size must be in the range of array size (> 0 && <= array size)");

		int[] slides = new int[array.length];

		// Deque holds the element indices, elements are in decreasing order
		Deque<Integer> deque = new LinkedList<>();

		// process initial w elements
		for (int i = 0; i < w; i++) {
			while (!deque.isEmpty() && array[i] >= array[deque.getLast()])
				deque.removeLast();
			deque.addLast(i);
		}

		for (int i = w; i < array.length; i++) {
			slides[i - w] = array[deque.getFirst()];

			// Remove all smaller elements than the current one
			while (!deque.isEmpty() && array[i] >= array[deque.getLast()])
				deque.removeLast();

			// Remove elements which are outside window range
			while (!deque.isEmpty() && deque.getFirst() <= i - w)
				deque.removeFirst();

			deque.addLast(i);
		}
		
		slides[array.length - w] = array[deque.removeFirst()];

		return slides;
	}
}