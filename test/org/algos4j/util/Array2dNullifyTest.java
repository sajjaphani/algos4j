package org.algos4j.util;

/**
 * Given a 2d array of mXn matrix this method tests nullifying the array elements.
 * 
 * @author psajja
 *
 */
public class Array2dNullifyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] array = { { 1, 0, 2, 1, 0 }, { 0, 2, 1, 3, 0 }, { 1, 2, 1, 4, 1 }, { 1, 0, 1, 4, 2 },
				{ 1, 1, 3, 2, 1 } };
		System.out.println("Before nullify");
		Array2dUtil.print(array);

		Array2dUtil.nullify(array);

		System.out.println("After nullify");
		Array2dUtil.print(array);
		
		System.out.println();
		int[][] array1 = { { 1, 0, 2, 1, 0 }, { 0, 2, 1, 3, 0 }, { 1, 2, 1, 4, 1 }, { 1, 0, 1, 4, 2 },
				{ 1, 1, 3, 2, 1 } };
		System.out.println("Before nullify");
		Array2dUtil.print(array1);

		Array2dUtil.nullify1(array1);

		System.out.println("After nullify");
		Array2dUtil.print(array1);
	}
}
