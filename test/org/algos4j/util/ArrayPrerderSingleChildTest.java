package org.algos4j.util;

/**
 * This class tests whether the given preorder of BST has a single child at each node.
 * 
 * @author psajja
 *
 */
public class ArrayPrerderSingleChildTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int[] preorder = new int[] {20, 10, 11, 13, 12} /*{ 8, 3, 5, 7, 6 }*/;

		boolean hasSingleChild = ArrayUtil.hasOneChild(preorder);
		if (hasSingleChild)
			System.out.println("Each node has exactly one child");
		else
			System.out.println("Each node has more than one child");
	}

}
