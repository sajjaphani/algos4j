package org.algos4j.tree;

import java.util.Arrays;

/**
 * @author psajja
 *
 */
public class BuildBinaryTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] inorder = { 40, 20, 50, 10, 60, 30 };
		int[] preorder = { 10, 20, 40, 50, 30, 60 };

		System.out.println("Inorder: " + Arrays.toString(inorder));
		System.out.println("Preorder: " + Arrays.toString(preorder));
	
		System.out.println();
		BinaryTree bt = BinaryTreeUtil.buildTreeFromInorderPreorder(inorder, preorder);
		System.out.print("Inorder: ");
		bt.inorder();
		System.out.println();
		System.out.print("Preorder: ");
		bt.preorder();
		
		System.out.println();
		System.out.println();
		testBuildTree();
	}

	private static void testBuildTree() {
		int[] inorder = { 4, 8, 2, 5, 1, 6, 3, 7 };
		int[] postorder = { 8, 4, 5, 2, 6, 7, 3, 1 };

		System.out.println("Inorder: " + Arrays.toString(inorder));
		System.out.println("Postorder: " + Arrays.toString(postorder));
	
		System.out.println();
		BinaryTree bt = BinaryTreeUtil.buildTreeFromInorderPreorder(inorder, postorder);
		System.out.print("Inorder: ");
		bt.inorder();
		System.out.println();
		System.out.print("Postorder: ");
		bt.preorder();		
	}
}
