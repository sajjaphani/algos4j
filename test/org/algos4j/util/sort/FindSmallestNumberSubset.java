package org.algos4j.util.sort;

import java.util.Arrays;

import org.algos4j.util.ArraySortUtil;

/**
 * This class tests finding the smallest number that can't be represented as subset sum.
 * 
 * @author psajja
 *
 */
public class FindSmallestNumberSubset {

	public static void main(String[] args) {
		int[] numbers1 = { 1, 3, 4, 5 };
		System.out.println("Input: " + Arrays.toString(numbers1));
		System.out.println("Smallest number: " + ArraySortUtil.findMinNumber(numbers1));

		int[] numbers2 = { 1, 2, 6, 10, 11, 15 };
		System.out.println("Input: " + Arrays.toString(numbers2));
		System.out.println("Smallest number: " + ArraySortUtil.findMinNumber(numbers2));

		int[] numbers3 = { 1, 1, 1, 1 };
		System.out.println("Input: " + Arrays.toString(numbers3));
		System.out.println("Smallest number: " + ArraySortUtil.findMinNumber(numbers3));

		int[] numbers4 = { 1, 1, 3, 4 };
		System.out.println("Input: " + Arrays.toString(numbers4));
		System.out.println("Smallest number: " + ArraySortUtil.findMinNumber(numbers4));
	}
}