package org.algos4j.util;

/**
 * Some utilities on arrays.
 * 
 * @author psajja
 *
 */
public class ArrayUtil {

	/**
	 * Not-instantiable
	 */
	private ArrayUtil() {
	}
	
	/**
	 * Given an integer array, it returns the maximum.
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @return
	 * 		maximum value
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 * @throws IllegalArgumentException
	 * 		if the input array is empty
	 */
	public static int getMax(int[] array) {
		if(array == null)
			throw new NullPointerException("Input array should not be null");
		if(array.length == 0)
			throw new IllegalArgumentException("Input array has no elements");
		
		int max = array[0];
		for (int i = 0; i < array.length; i++)
			if (array[i] > max)
				max = array[i];

		return max;
	}
	
	/**
	 * Given an array where the array elements represents a binary tree, this
	 * method prints them in inorder. If the array elements represent a binary
	 * search tree, inorder produces sorting order. For each node at position i,
	 * its left child is at '2 * i + 1' and its right child is at '2 * i + 2'.
	 * 
	 * @param array
	 *    	given integer array
	 * 
	 * @throws NullPointerException
	 *       	if the input array is null
	 */
	static void printInorder(int[] array) {
		if(array == null)
			throw new NullPointerException("Input array should not be null");
		
		printInorder(array, 0, array.length - 1);
	}
	
	/**
	 * Recursively print the array in inorder fashion.
	 * 
	 * @param array
	 * 		integer array
	 * @param start
	 * 		start position
	 * @param end
	 * 		end position
	 */
	private static void printInorder(int[] array, int start, int end) {
		if (start > end)
			return;

		printInorder(array, start * 2 + 1, end);

		System.out.print(array[start] + " ");

		printInorder(array, start * 2 + 2, end);
	}
	
	/**
	 * Given a preorder traversal, this method checks whether the given tree has
	 * exactly one child at each node. If all nodes have only one child in a
	 * BST, then for each node, the descendants of it are either smaller or
	 * larger than the node.
	 * 
	 * @param preorder
	 *     	given preorder traversal
	 * 
	 * @return 
	 * 		true if each node has a single child, false otherwise
	 * 
	 * @throws NullPointerException
	 *       	if the input array is null
	 */
	public static boolean hasOneChild(int[] preorder) {
		if (preorder == null)
			throw new NullPointerException("Preorder array should not be null");

		int size = preorder.length;

		for (int i = 0; i < size - 1; i++) {
			if (preorder[i + 1] < preorder[i] && preorder[i] < preorder[size - 1])
				return false;
		}

		return true;
	}
}
