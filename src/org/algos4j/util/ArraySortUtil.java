package org.algos4j.util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class will contain utility methods to sort arrays.
 * It also contains methods which has relation to sorting.
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
		
		return;
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
}
