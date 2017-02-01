package org.algos4j.util;

import java.util.Arrays;

/**
 * This class tests finding the closest pair with a given sum.
 * 
 * @author psajja
 *
 */
public class ArrayClosestPairTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int[] elements = new int[] { 1, 60, -10, 70, -80, 85 };
		int[] pair = ArrayUtil.getClosestPair(elements, 0);
		System.out.println(Arrays.toString(elements));
		System.out.println("Closest Pair: {" + pair[0] + ", " + pair[1] + "}");
	}

}
