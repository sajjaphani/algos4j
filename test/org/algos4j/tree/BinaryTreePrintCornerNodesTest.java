package org.algos4j.tree;

/**
 * Given a binary tree, this method prints the extreme corner nodes.
 * 
 * @author psajja
 *
 */
public class BinaryTreePrintCornerNodesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		System.out.println("Inorder Traversal");
		bt.inorder();
		
		System.out.println();
		System.out.println("Printing Extreme Nodes");
		BinaryTreeUtil.printExtremeNodes(bt);
	}

}
