package org.algos4j.util;

import java.util.Arrays;

/**
 * This class tests finding the next greater elements.
 * 
 * @author psajja
 *
 */
public class ArrayNextGreaterTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int[] elements = new int[] { 11, 13, 21, 3 };
		System.out.println(Arrays.toString(elements));
		int[] result = ArrayUtil.findNextGreater(elements);
		
		System.out.println("Greater: " + Arrays.toString(result));
	}

}
