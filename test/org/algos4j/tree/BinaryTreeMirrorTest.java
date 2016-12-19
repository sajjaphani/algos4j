package org.algos4j.tree;

/**
 * A test class to test the mirror image of a binary tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreeMirrorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		System.out.println("Binary Tree Mirror");
		System.out.println("Inorder before");
		bt.inorder();
		BinaryTreeUtil.mirror(bt);
		System.out.println();
		System.out.println("Inorder after");
		bt.inorder();
	}
}
