package org.algos4j.tree;

/**
 * A test class to test paths in a binary tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreePathsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Binary Tree Paths");
		BinaryTreeUtil.printPaths(bt);
		
		System.out.println();
		System.out.println("Binary Tree Paths");
		BinaryTreeUtil.printPaths1(bt);
		
		System.out.println();
		System.out.println("Has Path Sum (25): " + BinaryTreeUtil.hasPathSum(bt, 25));
		
		System.out.println();
		System.out.println("Has Path Sum (19): " + BinaryTreeUtil.hasPathSum(bt, 19));
		
		System.out.println();
		System.out.println("Printing Nodes at distance 2:");
		BinaryTreeUtil.printNodes(bt, 2);
		
		System.out.println();
		System.out.println("Printing Ancestors of node 9:");
		BinaryTreeUtil.printAncestors(bt, 9);
		
		System.out.println();
		System.out.println("Printing Vertical sum");
		BinaryTreeUtil.printVerticalSum(bt);
	}
}
