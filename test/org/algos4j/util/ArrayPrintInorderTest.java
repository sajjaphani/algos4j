package org.algos4j.util;

/**
 * This class tests printing of the given array element in inorder fashion.
 * 
 * @author psajja
 *
 */
public class ArrayPrintInorderTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Tree
		int[] elements = new int[] {4, 2, 5, 1, 3};
		ArrayUtil.printInorder(elements);
	}

}
