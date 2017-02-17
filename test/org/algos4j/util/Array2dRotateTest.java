package org.algos4j.util;

/**
 * Given a 2d array of nXn matrix this method tests rotating the array by 90 degrees.
 * 
 * @author psajja
 *
 */
public class Array2dRotateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] array = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		System.out.println("Before rotate");
		Array2dUtil.print(array);
		
		Array2dUtil.rotate(array);
		
		System.out.println("After rotate");
		Array2dUtil.print(array);
	}
}
