package org.algos4j.util;

/**
 * This class provides utility methods related to rotating the array.
 * 
 * @author psajja
 *
 */
public final class ArrayRotateUtil {

	/**
	 * Not-instantiable
	 */
	private ArrayRotateUtil() {
	}

	/**
	 * Rotates the given array elements to the left by the number of rotations
	 * given in d. Time: O(n * d).
	 * 
	 * @param array
	 *   	given integer array
	 * @param d
	 *   	number of rotations
	 * 
	 * @throws NullPointerException
	 *    	if any of the input array is null
	 * @throws IllegalArgumentException
	 * 		if the given d <= 0.
	 */
	public static void rotate(int[] array, int d) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		if(d <= 0)
			throw new IllegalArgumentException("Invalid rotations: " + d);
		
		for (int i = 0; i < d; i++) {
			int temp = array[0];
			int j = 0;
			for (; j < array.length - 1; j++)
				array[j] = array[j + 1];
			array[j] = temp;
		}
	}

	/**
	 * Rotates the given array elements to the left by the rotations given in d.
	 * Based on Juggling Algorithm. This method rotate the elements by moving them in
	 * steps of d. This process loops back after a certain number of moves, so
	 * that we need to apply m cycles of length l = n/m in total.
	 * 
	 * <p>
	 * The juggling algorithm is apparently twice as fast as the reversal
	 * algorithm. It stores and retrieves each element of the array just once.
	 * while the reversal algorithm does so twice.
	 * </p>
	 * Time: O(n).
	 * 
	 * @param array
	 *   	given integer array
	 * @param d
	 *   	number of rotations
	 * 
	 * @throws NullPointerException
	 *    	if any of the input array is null
	 * @throws IllegalArgumentException
	 * 		if the given d <= 0.
	 */
	public static void rotateJuggling(int[] array, int d) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		if(d <= 0)
			throw new IllegalArgumentException("Invalid rotations: " + d);
		
		int i, j, k, temp;
		int n = array.length;
		int gcd = MathUtil.gcd(d, n);

		for (i = 0; i < gcd; i++) {
			/* Move ith values of blocks */
			temp = array[i];
			j = i;
			while (true) {
				k = j + d;
				if (k >= n)
					k = k - n;
				if (k == i)
					break;
				array[j] = array[k];
				j = k;
			}
			array[j] = temp;
		}
	}

	/**
	 * Rotates the given array by d placements using reversal algorithm. Let AB
	 * two parts of the array. Reverse A to get ArB. Reverse B to get ArBr.
	 * Reverse all to get (ArBr)r = BA. Reversal algorithm.
	 * 
	 * @throws NullPointerException
	 *    	if any of the input array is null
	 * @throws IllegalArgumentException
	 * 		if the given d <= 0.
	 */
	public static void rotateReversal(int[] array, int d) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		if(d <= 0)
			throw new IllegalArgumentException("Invalid rotations: " + d);
		
		reverese(array, 0, d - 1);
		reverese(array, d, array.length - 1);
		reverese(array, 0, array.length - 1);
	}

	/**
	 * Reverse the elements given by start and end indices.
	 * 
	 * @param array
	 * 		integer array
	 * @param start
	 * 		start index
	 * @param end
	 * 		end index
	 */
	private static void reverese(int array[], int start, int end) {
		int temp;
		while (start < end) {
			temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			start++;
			end--;
		}
	}

	/**
	 * Rotates the array by d positions using Block-Swap algorithm. When A and B
	 * are of equal length, change AB to BA. If a is shorter, divide B into Bl
	 * and Br. Swap A and Br to change ABlBr into BrBlA. Recur on pieces of b.
	 * 
	 * @throws NullPointerException
	 *    	if any of the input array is null
	 * @throws IllegalArgumentException
	 * 		if the given d <= 0.
	 */
	public static void rotateBlockSwap(int[] array, int d) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");
		if(d <= 0)
			throw new IllegalArgumentException("Invalid rotations: " + d);
		
		int n = array.length;
		int i, j;
		if (d == 0 || d == n)
			return;
		i = d;
		j = n - d;
		while (i != j) {
			if (i < j) {
				swap(array, d - i, d + j - i, i);
				j -= i;
			} else {
				swap(array, d - i, d, j);
				i -= j;
			}
		}
		swap(array, d - i, d, i);
	}

	/**
	 * Swap the elements.
	 * 
	 * @param array
	 * 		integer array
	 * @param first
	 * 		first index
	 * @param second
	 * 		second index
	 * @param d
	 * 		number of swaps
	 */
	public static void swap(int[] array, int first, int second, int d) {
		int i, temp;
		for (i = 0; i < d; i++) {
			temp = array[first + i];
			array[first + i] = array[second + i];
			array[second + i] = temp;
		}
	}

}
