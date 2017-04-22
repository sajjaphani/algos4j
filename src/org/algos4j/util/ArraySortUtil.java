package org.algos4j.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will contain utility methods to sort arrays.
 * It also contains methods which has relation to sorting, e.g utilities on sorted arrays.
 * The methods may assume that the array is sorted and does not check for sorted or not.
 * 
 * @author psajja
 *
 */
public class ArraySortUtil {

	/**
	 * Non-instantiable
	 */
	private ArraySortUtil() {
	}
	
	/**
	 * Given an array, this method sorts the elements using bubble sort algorithm.
	 * 
	 * Time: O(n^2).
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static void bubbleSoft(int[] array) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");

		int size = array.length;
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					swap(array, j, j - 1);
				}
			}
		}
	}
	
	/**
	 * Given an array, this method sorts the elements using insertion sort algorithm.
	 * 
	 * Time: O(n^2).
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static void insertion(int[] array) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");
		
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
				swap(array, j, j - 1);
			}
		}
	}

	/**
	 * Swaps the two elements in the array.
	 * 
	 * @param array
	 * 		given array
	 * @param j
	 * 		position 1
	 * @param i
	 * 		position 2
	 */
	private static void swap(int[] array, int j, int i) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
	
	/**
	 * Given an array, this method sorts the elements using selection sort algorithm.
	 * Time: O(n^2).
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static void selection(int[] array) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");

		for (int i = 0; i < array.length; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[min])
					min = j;
			}
			swap(array, i, min);
		}

		return;
	}
	
	/**
	 * Given an array contains 0s and 1s only this method sorts them.
	 * This method does not check whether the array contains only 0s and 1s.
	 * 
	 * @param array
	 * 		input array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static void sort0s1s(int[] array) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");

		int left = 0, right = array.length - 1;

		while (left < right) {
			while (array[left] == 0 && left < right)
				left++;

			while (array[right] == 1 && left < right)
				right--;

			if (left < right) {
				array[left] = 0;
				array[right] = 1;
				left++;
				right--;
			}
		}
	}
	
	/**
	 * Given an array contains 0s, 1s and 2s only this method sorts them.
	 * This method does not check whether the array contains only 0s, 1s and 2s.
	 * 
	 * @param array
	 * 		input array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static void sort0s1s2s(int[] array) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");

		int low = 0;
		int high = array.length - 1;
		int mid = 0;
		while (mid <= high) {
			switch (array[mid]) {
			case 0: {
				swap(array, low, mid);
				low++;
				mid++;
				break;
			}
			case 1:
				mid++;
				break;
			case 2: {
				swap(array, mid, high);
				high--;
				break;
			}
			}
		}
	}
	
	/**
	 * Given an array, this method counts the inversions in the array. Inversion
	 * count indicates how far the array is being sorted. Inversions count is
	 * zero if it is already sorted, is max if the array is in reverse sorted
	 * order. Two elements a[i] and a[j] form an inversion if a[i] > a[j] for i
	 * < j. Based on merge sort. Time: O(n log n)
	 * 
	 * @param array
	 * 		integer array
	 * 
	 * @return
	 * 		the number of inversions
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static int countInversions(int[] array) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");
		
		int[] sortedArray = new int[array.length];
	
		return countInversionsByMergeSort(array, sortedArray, 0, array.length - 1);
	}
	
	/**
	 * This method recursively sorts the input array and returns the number of
	 * inversions in the array.
	 * 
	 * @param array
	 * 		input array
	 * @param auxArray
	 * 		auxiliary array
	 * @param left
	 * 		left index
	 * @param right
	 * 		right index
	 * 
	 * @return
	 * 		inversions count
	 */
	private static int countInversionsByMergeSort(int[] array, int[] auxArray, int left, int right) {
		if (right <= left)
			return 0;
	
		int mid= (right + left) / 2, inversions = 0;

		// Total inversion count is given by left, right and merge part
		inversions = countInversionsByMergeSort(array, auxArray, left, mid);
		inversions += countInversionsByMergeSort(array, auxArray, mid + 1, right);

		inversions += countInversionsByMerge(array, auxArray, left, mid + 1, right);

		return inversions;
	}

	/**
	 * This method merges the two sorted arrays and returns inversion count.
	   
	 * @param array
	 * 		input array
	 * @param auxArray
	 * 		auxiliary array
	 * @param left
	 * 		left index
	 * @param mid
	 * 		mid index
	 * @param right
	 * 		right index
	 * 
	 * @return
	 * 		inversions count
	 */
	private static int countInversionsByMerge(int[] array, int[] auxArray, int left, int mid, int right) {
		int i, j, k;
		int inversions = 0;

		i = left;
		j = mid;
		k = left; // index of merged sub-array
		while ((i <= mid - 1) && (j <= right)) {
			if (array[i] <= array[j]) {
				auxArray[k++] = array[i++];
			} else {
				auxArray[k++] = array[j++];

				// left and right sub-arrays are sorted they differ by mid - i
				inversions = inversions + (mid - i);
			}
		}

		while (i <= mid - 1)
			auxArray[k++] = array[i++];

		while (j <= right)
			auxArray[k++] = array[j++];

		for (i = left; i <= right; i++)
			array[i] = auxArray[i];

		return inversions;
	}

	/**
	 * Given an integer array, this method finds the range of array index that
	 * are unsorted. Sorting the resulting sub-array makes the whole array
	 * sorted.
	 * 
	 * @param array
	 * 		input array
	 * 
	 * @return
	 * 		a two element array with start and end indices, {-1, -1} if it is already sorted
	 */
	public static int[] findUnsortedRange(int[] array) {
		int n = array.length;
		int start = 0;
		int end = n - 1;

		// Find start element which is unsorted
		for (start = 0; start < n - 1; start++) {
			if (array[start] > array[start + 1])
				break;
		}

		// Already sorted
		if (start == n - 1)
			return new int[] { -1, -1 };

		// Find end element which is unsorted
		for (end = n - 1; end > 0; end--) {
			if (array[end] < array[end - 1])
				break;
		}

		// Find max, min in unsorted array
		int max = array[start];
		int min = array[start];
		for (int i = start + 1; i <= end; i++) {
			if (array[i] > max)
				max = array[i];
			if (array[i] < min)
				min = array[i];
		}

		// Adjust start index based on min
		for (int i = 0; i < start; i++) {
			if (array[i] > min) {
				start = i;
				break;
			}
		}

		// Adjust end index based on max
		for (int i = n - 1; i >= end + 1; i--) {
			if (array[i] < max) {
				end = i;
				break;
			}
		}

		return new int[] { start, end };
	}
	
	/**
	 * Given an array, sort the elements by their decreasing frequency.
	 * If frequencies are same then preserve the occurrence.
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static int[] sortByFrequence(int[] array) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");

		Map<Integer, int[]> freqMap = new LinkedHashMap<Integer, int[]>();

		for (int i = 0; i < array.length; i++) {
			if (freqMap.containsKey(array[i])) {
				int[] c = freqMap.get(array[i]);
				c[0] = c[0] + 1;
				freqMap.put(array[i], c);
			} else {
				freqMap.put(array[i], new int[] { 1, i });
			}
		}
		int[][] freqElts = new int[freqMap.size()][];
		int i = 0;
		for (int[] elt : freqMap.values()) {
			freqElts[i++] = elt;
		}

		Arrays.sort(freqElts, new java.util.Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] != o2[0])
					return Integer.compare(o2[0], o1[0]);

				return Integer.compare(o1[1], o2[1]);
			}
		});
		int[] result = new int[array.length];
		i = 0;
		for (int[] elt : freqElts) {
			for (int j = 0; j < elt[0]; j++)
				result[i++] = array[elt[1]];
		}
		
		return result;
	}
	
	/**
	 * Given an array and an element to find, this method finds the index of
	 * first occurrence of the element. It returns the sole location if the 
	 * element does not occur more than once. A variation of binary search.
	 * 
	 * @param array
	 *    	input array
	 * @param elt
	 *    	element to find
	 * 
	 * @return 
	 * 		the index of the first occurrence, -1 if element not found
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static int findFirst(int[] array, int elt) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");
		
		int low = 0;
		int high = array.length - 1;
		int result = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (elt == array[mid]) {
				result = mid;
				high = mid - 1;
			} else if (elt < array[mid])
				high = mid - 1;
			else 
				low = mid + 1;
		}
		
		return result;
	}

	/**
	 * Given an array and an element to find, this method finds the index of
	 * last occurrence of the element. It returns the sole location if the 
	 * element does not occur more than once. A variation of binary search.
	 * 
	 * @param array
	 *    	input array
	 * @param elt
	 *    	element to find
	 * 
	 * @return 
	 * 		the index of the first occurrence, -1 if element not found
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static int findLast(int[] array, int elt) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");
		
		int low = 0;
		int high = array.length - 1;
		int result = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (elt == array[mid]) {
				result = mid;
				low = mid + 1;
			} else if (elt < array[mid])
				high = mid - 1;
			else 
				low = mid + 1;
		}
		
		return result;
	}
	
	/**
	 * Find the number of occurrences of the element in an integer array. Uses a
	 * variant of binary search based on the fact that the elements found
	 * continuously.
	 * 
	 * @param array
	 *  	the input array
	 * @param elt
	 *    	element to search
	 * 
	 * @return 
	 * 		the number of occurrences
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static int count(int[] array, int elt) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");
		
		int first = findFirst(array, elt);
		if (first == -1)
			return 0;
		int last = findLast(array, elt);
		
		return last - first + 1;
	}
	
	/**
	 * Given a sorted array and an element, this method checks whether the 
	 * element is majority element or not. An element is a majority element 
	 * if it occurs at least (n/2 + 1) times. Uses a variant of binary search 
	 * based on the fact that the elements found continuously.
	 * 
	 * @param array
	 *  	the input array
	 * @param elt
	 *    	element to check
	 * 
	 * @return 
	 * 		the number of occurrences
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static boolean isMajority(int[] array, int elt) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");
		
		int first = findFirst(array, elt);		if (first == -1)
			return false;
		
		int n = array.length;
		if ((first + n/2) <= (n -1) && array[first + n/2] == elt)
	        return true;
		
		return false;
	}

	/**
	 * Given two sorted arrays, this method prints the union of the two.
	 * 
	 * @param array1
	 * 		first array
	 * @param array2
	 * 		second array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	static void printUnion(int[] array1, int[] array2) {
		if (array1 == null || array2 == null)
			throw new NullPointerException("Array(s) cannot be null.");

		int len1 = array1.length;
		int len2 = array2.length;
		int i = 0, j = 0;
		while (i < len1 && j < len2) {
			if (array1[i] < array2[j]) {
				System.out.print(array1[i] + " ");
				i++;
			} else if (array2[j] < array1[i]) {
				System.out.print(array2[j] + " ");
				j++;
			} else {
				System.out.print(array1[i] + " ");
				i++;
				j++;
			}
		}

		while (i < len1) {
			System.out.print(array1[i] + " ");
			i++;
		}

		while (j < len2) {
			System.out.print(array2[j] + " ");
			j++;
		}
	}
	
	/**
	 * Given two sorted arrays, this method prints the intersection of the two.
	 * 
	 * @param array1
	 * 		first array
	 * @param array2
	 * 		second array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	static void printIntersection(int[] array1, int[] array2) {
		if (array1 == null || array2 == null)
			throw new NullPointerException("Array(s) cannot be null.");

		int len1 = array1.length;
		int len2 = array2.length;
		int i = 0, j = 0;
		while (i < len1 && j < len2) {
			if (array1[i] < array2[j]) {
				i++;
			} else if (array2[j] < array1[i]) {
				j++;
			} else {
				System.out.print(array1[i]  + " ");
				i++;
				j++;
			}
		}
	}
	
	/**
	 * Given a sorted array and an element, this method finds the ceil of the given number. 
	 * The ceil of an element is the smallest element in array greater than or equal to given element.
	 * 
	 * @param array
	 *  	the input array
	 * @param elt
	 *    	element to find ceil
	 * 
	 * @return 
	 * 		the ceil value
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 * @throws IllegalArgumentException
	 * 		if the array is empty
	 * @throws IllegalStateException
	 * 		if the ceil is not found in the array
	 */
	public static int ceil(int[] array, int elt) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");
		if(array.length == 0)
			throw new IllegalArgumentException("Array is empty");
		
		int index = ceil(array, 0, array.length-1, elt);
		if(index == -1)
			throw new IllegalStateException("Ceil does not exist in the array.");
		
		return array[index];
	}
	
	/**
	 * Recursively find the ceil in the array.
	 * 
	 * @param array
	 * 		integer array
	 * @param low
	 * 		current low index
	 * @param high
	 * 		current high index
	 * @param elt
	 * 		element to find ceil
	 * 
	 * @return
	 * 		index of the ceil value
	 */
	private static int ceil(int[] array, int low, int high, int elt) {
		if (elt <= array[low])
			return low;

		if (elt > array[high])
			return -1;

		int mid = (low + high) / 2;

		if (array[mid] == elt)
			return mid;

		if (array[mid] < elt) {
			if (mid + 1 <= high && elt <= array[mid + 1])
				return mid + 1;
			else
				return ceil(array, mid + 1, high, elt);
		} else {
			if (mid - 1 >= low && elt > array[mid - 1])
				return mid;
			else
				return ceil(array, low, mid - 1, elt);
		}
	}
	
	/**
	 * Given a sorted array and an element, this method finds the floor of the given number. 
	 * The floor of an element is the greatest element in array smaller than or equal to given element.
	 * 
	 * @param array
	 *  	the input array
	 * @param elt
	 *    	element to find floor
	 * 
	 * @return 
	 * 		the floor value
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 * @throws IllegalArgumentException
	 * 		if the array is empty
	 * @throws IllegalStateException
	 * 		if the floor is not found in the array
	 */
	public static int floor(int[] array, int elt) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");
		if(array.length == 0)
			throw new IllegalArgumentException("Array is empty");
		
		int index = floor(array, 0, array.length-1, elt);
		if(index == -1)
			throw new IllegalStateException("Floor does not exist in the array.");
		
		return array[index];
	}
	
	/**
	 * Recursively find the floor in the array.
	 * 
	 * @param array
	 * 		integer array
	 * @param low
	 * 		current low index
	 * @param high
	 * 		current high index
	 * @param elt
	 * 		element to find floor
	 * 
	 * @return
	 * 		index of the floor value
	 */
	private static int floor(int[] array, int low, int high, int elt) {
		if (elt < array[low])
			return -1;

		if (elt >= array[high])
			return high;

		int mid = (low + high) / 2;

		if (array[mid] == elt)
			return mid;

		if (array[mid] < elt) {
			if (mid + 1 <= high && elt <= array[mid + 1])
				return mid;
			else
				return floor(array, mid + 1, high, elt);
		} else {
			if (mid - 1 >= low && elt > array[mid + 1])
				return mid + 1;
			else
				return floor(array, low, mid - 1, elt);
		}
	}
	
	/**
	 * Given an array of sorted elements, find the first missing numbers in the series.
	 * Use a variation of binary search. Assumption: numbers start from 0.
	 * 
	 * @param array
	 *  	the input array
	 * 
	 * @return 
	 * 		the first number missing
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 * @throws IllegalArgumentException
	 * 		if the array is empty
	 */
	public static int findFirstMissing(int[] array) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");
		if (array.length == 0)
			throw new IllegalArgumentException("Array is empty");

		return findFirstMissing(array, 0, array.length - 1);
	}
	
	/**
	 * Recursively find the missing number.
	 * 
	 * @param array
	 * 		input array
	 * @param start
	 * 		current start index
	 * @param end
	 * 		current end index
	 * 
	 * @return
	 * 		first missing number
	 */
	private static int findFirstMissing(int array[], int start, int end) {
		if (start > end)
			return end + 1;

		if (start != array[start])
			return start;

		int mid = (start + end) / 2;

		if (array[mid] > mid)
			return findFirstMissing(array, start, mid);
		else
			return findFirstMissing(array, mid + 1, end);
	}
	
	/**
	 * Given an array of integers, it sorts them using quicksort algorithm.
	 * Time: (n log n)
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static void quickSort(int[] array) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");
		
		quickSort(array, 0, array.length-1);
	}

	/**
	 * Recursively sort the subarrays.
	 * 
	 * @param array
	 * 		given array
	 * @param start
	 * 		current start index
	 * @param end
	 * 		current end index
	 */
	private static void quickSort(int[] array, int start, int end) {
		if (start >= end)
			return;

		int pivot = array[(start + end) / 2];
		int index = partition(array, start, end, pivot);
		quickSort(array, start, index - 1);
		quickSort(array, index, end);
	}

	/**
	 * Partition the the array such that the elements are arranged around pivot element.
	 * 
	 * @param array
	 * 		given array
	 * @param start
	 * 		current start index
	 * @param end
	 * 		current end index
	 * @param pivot
	 * 		pivot element
	 * 
	 * @return
	 * 		index of the partition
	 */
	private static int partition(int[] array, int start, int end, int pivot) {
		while (start <= end) {
			while (array[start] < pivot)
				start++;

			while (array[end] > pivot)
				end--;

			if (start <= end) {
				swap(array, start, end);
				start++;
				end--;
			}
		}

		return start;
	}
	
	/**
	 * Given an array of integers, it sorts them using mergesort algorithm.
	 * Time: (n log n), Space: O(n)
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static void mergeSort(int[] array) {
		if (array == null)
			throw new NullPointerException("Array cannot be null.");

		mergeSort(array, new int[array.length], 0, array.length - 1);
	}

	/**
	 * Recursively sort the array, from the start to end index.
	 * 
	 * @param array
	 * 		given array
	 * @param aux
	 * 		auxiliary array
	 * @param start
	 * 		left start
	 * @param end
	 * 		right end
	 */
	private static void mergeSort(int[] array, int[] aux, int start, int end) {
		if (start >= end)
			return;

		int middle = (start + end) / 2;
		mergeSort(array, aux, start, middle);
		mergeSort(array, aux, middle + 1, end);
		merge(array, aux, start, end);
	}

	/**
	 * Merge the halves.
	 * 
	 * @param array
	 * 		given array
	 * @param aux
	 * 		auxiliary array
	 * @param leftStart
	 * 		current left start
	 * @param rightEnd
	 * 		current right end
	 */
	private static void merge(int[] array, int[] aux, int leftStart, int rightEnd) {
		int leftEnd = (leftStart + rightEnd) / 2;
		int rightStart = leftEnd + 1;
		int curSize = rightEnd-leftStart;
		
		int left = leftStart;
		int right = rightStart;
		int index = leftStart;
		
		while(left <= leftEnd && right <= rightEnd) {
			if(array[left] <= array[right]) {
				aux[index] = array[left];
				left++;
			} else {
				aux[index] = array[right];
			}
			index++;
		}
		
		// Copy remainder elements
		// Copy left remaining elements to aux
		System.arraycopy(array, left, aux, index, leftEnd - left + 1);
		// Copy right remaining elements to aux
		System.arraycopy(array, right, aux, index, rightEnd - right + 1);
		// Copy back to the original
		System.arraycopy(aux, leftStart, array, leftStart, curSize);
	}
	
	/**
	 * Given an array of numbers (sorted), this method finds the smallest number
	 * that can not be represented as sum of any subset. Time: O(n). 
	 * For unsorted numbers complexity O(n log n), as we need to sort elements.
	 * 
	 * @param numbers
	 * 		given numbers
	 * 
	 * @return
	 * 		the minimum number
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static int findMinNumber(int[] numbers) {
		if (numbers == null)
			throw new NullPointerException("Input numbers array can not be null.");

		int smallNumber = 1;

		for (int i = 0; i < numbers.length && numbers[i] <= smallNumber; i++)
			smallNumber = smallNumber + numbers[i];

		return smallNumber;
	}
	
	/**
	 * Given three sorted (increasing order) arrays, this method returns the
	 * common elements from the three elements. If any of the array is null
	 * this method returns an empty array.
	 * 
	 * @param array1
	 * 		first array
	 * @param array2
	 * 		second array
	 * @param array3
	 * 		third array
	 * 
	 * @return
	 * 		array holding common elements, empty array otherwise
	 */
	public static int[] getCommonElements(int[] array1, int[] array2, int[] array3) {
		if (array1 == null || array2 == null || array3 == null)
			return new int[0];

		List<Integer> commonElements = new ArrayList<>();

		// Indices of three arrays
		int i = 0, j = 0, k = 0;

		while (i < array1.length && j < array2.length && k < array3.length) {
			if (array1[i] == array2[j] && array2[j] == array3[k]) {
				commonElements.add(array1[i]);
				i++;
				j++;
				k++;
			} else if (array1[i] < array2[j])
				i++;
			else if (array2[j] < array3[k])
				j++;
			else
				k++;
		}

		return ArrayUtil.toArray(commonElements);
	}
	
	/**
	 * Given an array of integers, sort the array into a wave form array. An
	 * array is sorted in wave form if array[0] >= array[1] <= array[2] >=
	 * array[3] <= array[4] > ...
	 * 
	 * <p>Approach:</p>
	 * <pre>
	 * 1. Traverse even positioned elements of the array
	 *    a. Swap current and previous elements if current is smaller than previous.
	 *    b. Swap current and next elements if current is smaller than next.
	 * 
	 * </pre>
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static void sortInWaveForm(int[] array) {
		if (array == null)
			throw new NullPointerException("Input numbers array can not be null.");

		for (int i = 0; i < array.length; i += 2) {

			if (i > 0 && array[i - 1] > array[i])
				swap(array, i - 1, i);

			if (i < array.length - 1 && array[i] < array[i + 1])
				swap(array, i, i + 1);
		}
	}
	
	/**
	 * Given a set of nuts of different sizes and bolts of different sizes, this
	 * method matches nuts and bolts efficiently. There is a one-one
	 * corresponding mapping between nuts and bolts. Also known as Lock-Key
	 * problem. Based on Quick sort.
	 * 
	 * <p>
	 * Constraint: Only comparison of nut to a bolt and bolt to a nut is allowed.
	 * </p>
	 * 
	 * @param nuts
	 * 		given nuts
	 * @param bolts
	 * 		given bolts
	 */
	public static void matchPairs(char[] nuts, char[] bolts) {
		// TODO error handling, data validation
		
		matchPairs(nuts, bolts, 0, nuts.length - 1);
	}

	/**
	 * Re-arranges the nuts and bolts by applying quick sort approach.
	 * 
	 * @param nuts
	 * 		given nuts
	 * @param bolts
	 * 		given bolts
	 * @param low
	 * 		current low
	 * @param high
	 * 		current high
	 */
	private static void matchPairs(char[] nuts, char[] bolts, int low, int high) {
		if (low < high) {
		
			// Partition nuts based on chosen bolt
			int pivot = partition(nuts, low, high, bolts[high]);

			// Partition bolts based on chosen nut
			partition(bolts, low, high, nuts[pivot]);

			matchPairs(nuts, bolts, low, pivot - 1);
			matchPairs(nuts, bolts, pivot + 1, high);
		}
	}
	
	/**
	 * Partition the array based on given pivot.
	 * 
	 * @param array
	 * 		given array
	 * @param low
	 * 		current low
	 * @param high
	 * 		current high
	 * @param pivot
	 * 		pivot element
	 * 
	 * @return
	 * 		index after partition
	 */
	private static int partition(char[] array, int low, int high, char pivot) {
		int i = low;
		char temp;
		for (int j = low; j < high; j++) {
			if (array[j] < pivot) {
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
			} else if (array[j] == pivot) {
				temp = array[j];
				array[j] = array[high];
				array[high] = temp;
				j--;
			}
		}
		
		temp = array[i];
		array[i] = array[high];
		array[high] = temp;

		return i;
	}
}
