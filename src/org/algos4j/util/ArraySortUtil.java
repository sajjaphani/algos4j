package org.algos4j.util;

/**
 * This class will contain utility methods to sort arrays.
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
}
