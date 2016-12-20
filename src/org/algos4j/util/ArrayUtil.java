package org.algos4j.util;

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
}
