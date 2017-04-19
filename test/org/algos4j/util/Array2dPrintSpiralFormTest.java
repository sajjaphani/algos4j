package org.algos4j.util;

/**
 * This method tests printing the given 2d array in spiral form.
 * 
 * @author psajja
 *
 */
public class Array2dPrintSpiralFormTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[][] array1 = new int[][] {
										{ 1, 2, 3, 4 }, 
										{ 5, 6, 7, 8 }, 
										{ 9, 10, 11, 12 }, 
										{ 13, 14, 15, 16 }
									};
		System.out.println("Array: ");
		Array2dUtil.print(array1);
		System.out.println("Spiral Form: ");
		Array2dUtil.spiralPrint(array1);

		System.out.println();
		System.out.println();

		int[][] array2 = new int[][] {
										{ 1, 2, 3, 4, 5, 6 }, 
										{ 7, 8, 9, 10, 11, 12 }, 
										{ 13, 14, 15, 16, 17, 18 }
									};

		System.out.println("Array: ");
		Array2dUtil.print(array2);
		System.out.println("Spiral Form: ");
		Array2dUtil.spiralPrint(array2);
		}
	}

