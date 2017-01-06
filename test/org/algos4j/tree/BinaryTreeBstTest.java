package org.algos4j.tree;

import org.algos4j.tree.BinaryTree.BTNode;

/**
 * A test class to test whether a given binary tree is binary search tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreeBstTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.root = new BTNode(50);
		bt.root.left = new BTNode(40);
		bt.root.left.right = new BTNode(45);
		bt.root.right = new BTNode(60);
		bt.root.right.left = new BTNode(55);
		
		boolean isBst = BinaryTreeUtil.isBinarySearchTree(bt);
		if(isBst)
			System.out.println("Given binary tree is a binary search tree");
		else
			System.out.println("Given binary tree is not a binary search tree");
	}
}
