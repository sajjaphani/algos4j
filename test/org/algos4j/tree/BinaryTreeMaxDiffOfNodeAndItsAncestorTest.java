package org.algos4j.tree;

/**
 * Given a binary tree, this method tests finding maximum difference of any node and its ancestor.
 * 
 * @author psajja
 *
 */
public class BinaryTreeMaxDiffOfNodeAndItsAncestorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elts = {25, 15, 20, 5, 10, 35, 30, 45};
		BinaryTree bt = BinaryTreeUtil.createCompleteBinaryTree(elts);
		
		System.out.println("Inorder Traversal");
		bt.inorder();
		
		System.out.println();
		System.out.println("Max Diff: " + BinaryTreeUtil.maxDiffOfNodeAndItsAncestor(bt));
	}

}
