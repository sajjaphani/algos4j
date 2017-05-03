package org.algos4j.util;

import java.util.Arrays;

/**
 * @author psajja
 *
 */
public class PrintKthLargestInStreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {10, 20, 11, 70, 50, 40, 100, 5};
		System.out.println("Array: " + Arrays.toString(array));
		System.out.println("Printing 3rd largest element from the stream");
		
		ArrayUtil.printKthLargest(array, 3);
	}

}
