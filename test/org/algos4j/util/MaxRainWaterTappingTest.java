package org.algos4j.util;

import java.util.Arrays;

/**
 * Tests computing the max rain water that can be captured between the towers.
 * 
 * @author psajja
 *
 */
public class MaxRainWaterTappingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] array1 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println("Array:" + Arrays.toString(array1));
		System.out.println("Max Rain Water: " + ArrayUtil.maxRainWaterBetweenTowers(array1));

		System.out.println();
		int[] array2 = { 3, 0, 0, 2, 0, 4 };
		System.out.println("Array:" + Arrays.toString(array2));
		System.out.println("Max Rain Water: " + ArrayUtil.maxRainWaterBetweenTowers(array2));
	}

}
