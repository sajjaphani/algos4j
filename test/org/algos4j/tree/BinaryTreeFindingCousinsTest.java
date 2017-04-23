package org.algos4j.tree;

/**
 * Given a binary tree, this method tests finding the cousin nodes.
 * 
 * @author psajja
 *
 */
public class BinaryTreeFindingCousinsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Inorder Traversal");
		bt.inorder();
		
		System.out.println();
		System.out.print("Cousins of " + 4 + ": ");
		System.out.println(BinaryTreeUtil.getCousinNodes(bt, 4));
		
		System.out.println();
		System.out.print("Cousins of " + 7 + ": ");
		System.out.println(BinaryTreeUtil.getCousinNodes(bt, 7));
	}
}
