package org.algos4j.tree;

/**
 * This class tests constructing a balanced binary search tree from the given sorted array.
 * 
 * @author psajja
 *
 */
public class ArrayToBalencedBSTTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] array1 = {1, 2, 3, 4};
		BinaryTree bst = BinarySearchTreeUtil.createBalancedBst(array1);
		System.out.println("Height: " + bst.height());
	}

}
