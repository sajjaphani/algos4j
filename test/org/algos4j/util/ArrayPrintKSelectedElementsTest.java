package org.algos4j.util;

import java.util.Arrays;

/**
 * This class tests printing of the selected k elements (smallest/largest k).
 * 
 * @author psajja
 *
 */
public class ArrayPrintKSelectedElementsTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Tree
		int[] elements = new int[] {1, 23, 12, 9, 30, 2, 50};
		System.out.println(Arrays.toString(elements));
		System.out.println("Smallest K (3)");
		ArrayUtil.printSmallestK(elements, 3);
		
		System.out.println();
		System.out.println("Largest K (3)");
		ArrayUtil.printLargestK(elements, 3);
	}

}
