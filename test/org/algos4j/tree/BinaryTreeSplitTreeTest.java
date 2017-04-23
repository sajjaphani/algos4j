package org.algos4j.tree;

/**
 * Given a binary tree, this method prints the nodes in diagonal traversal.
 * 
 * @author psajja
 *
 */
public class BinaryTreeSplitTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Inorder Traversal");
		bt.inorder();
		
		System.out.println();
		boolean canSplit = BinaryTreeUtil.canSplit(bt);
		if(canSplit)
			System.out.println("The binary tree can be split into two halves.");
		else
			System.out.println("The binary tree can not be split into two halves.");
		
		bt.root.left.left = null;
		canSplit = BinaryTreeUtil.canSplit(bt);
		if(canSplit)
			System.out.println("The binary tree can be split into two halves.");
		else
			System.out.println("The binary tree can not be split into two halves.");
	}

}
