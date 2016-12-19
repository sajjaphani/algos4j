package org.algos4j.tree;

/**
 * A test class to test two binary trees are identical or not.
 * 
 * @author psajja
 *
 */
public class BinaryTreeIdenticalTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt1 = BinaryTreeUtil.createBinaryTree();
		BinaryTree bt2 = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Binary Tree 1 Inorder");
		bt1.inorder();
		System.out.println();
		System.out.println("Binary Tree 2 Inorder");
		bt2.inorder();
		System.out.println();
		boolean identical = BinaryTreeUtil.areIdentical(bt1, bt2);
		if(identical)
			System.out.println("Binary trees are identical");
		else
			System.out.println("Binary trees are not identical");
		
		// Cut some portion of the tree
		bt2.root.right = null;
		System.out.println("Binary Tree 1 Inorder");
		bt1.inorder();
		System.out.println();
		System.out.println("Binary Tree 2 Inorder");
		bt2.inorder();
		System.out.println();
		identical = BinaryTreeUtil.areIdentical(bt1, bt2);
		if(identical)
			System.out.println("Binary trees are identical");
		else
			System.out.println("Binary trees are not identical");
	}
}
