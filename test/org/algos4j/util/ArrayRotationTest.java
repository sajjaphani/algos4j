package org.algos4j.util;

import java.util.Arrays;

/**
 * This class tests rotations on arrays.
 * 
 * @author psajja
 *
 */
public class ArrayRotationTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int[] array1 = { 1, 2, 3, 4, 5, 6, 7 };
		int[] array2 = { 1, 2, 3, 4, 5, 6, 7 };
		int[] array3 = { 1, 2, 3, 4, 5, 6, 7 };
		int[] array4 = { 1, 2, 3, 4, 5, 6, 7 };
		
		System.out.println("Roate");
		System.out.println("Before: " + Arrays.toString(array1));
		ArrayRotateUtil.rotate(array1, 2);
		System.out.println("After: " + Arrays.toString(array1));
		
		System.out.println();
		System.out.println("Roate Juggling");
		System.out.println("Before: " + Arrays.toString(array2));
		ArrayRotateUtil.rotateJuggling(array2, 2);
		System.out.println("After: " + Arrays.toString(array2));
	
		System.out.println();
		System.out.println("Roate Reversal");
		System.out.println("Before: " + Arrays.toString(array3));
		ArrayRotateUtil.rotateReversal(array3, 2);
		System.out.println("After: " + Arrays.toString(array3));
		
		System.out.println();
		System.out.println("Roate Block-Swap");
		System.out.println("Before: " + Arrays.toString(array4));
		ArrayRotateUtil.rotateBlockSwap(array4, 2);
		System.out.println("After: " + Arrays.toString(array4));
	}
}