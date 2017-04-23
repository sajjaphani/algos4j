package org.algos4j.tree;

/**
 * Given a binary tree, this method prints the nodes in diagonal traversal.
 * 
 * @author psajja
 *
 */
public class BinaryTreeDiagonalTraversalTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Inorder Traversal");
		bt.inorder();
		
		System.out.println();
		System.out.println("Diagonal Traversal");
		BinaryTreeUtil.diagonalTraversal(bt);
	}

}
