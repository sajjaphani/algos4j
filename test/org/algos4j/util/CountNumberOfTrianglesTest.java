package org.algos4j.util;

import java.util.Arrays;

/**
 * This method tests the counting of the number of triangles possible from given array of positive integers.
 * 
 * @author psajja
 *
 */
public class CountNumberOfTrianglesTest {

	public static void main(String[] args) {
		int[] elements = new int[] { 4, 6, 3, 7 };
		System.out.println(Arrays.toString(elements));
		System.out.println("Number of triangles: " + ArrayUtil.numberOfTrianglesPossible(elements));

		int[] elements1 = new int[] { 10, 21, 22, 100, 101, 200, 300 };
		System.out.println(Arrays.toString(elements1));
		System.out.println("Number of triangles: " + ArrayUtil.numberOfTrianglesPossible(elements1));
	}
}
