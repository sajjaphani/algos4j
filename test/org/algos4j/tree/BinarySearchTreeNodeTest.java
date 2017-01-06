package org.algos4j.tree;

import org.algos4j.tree.BinaryTree.BTNode;

/**
 * A test class to test special nodes in binary search tree.
 * 
 * @author psajja
 *
 */
public class BinarySearchTreeNodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elts = {25, 15, 20, 5, 10, 35, 30, 45};
		BinaryTree bt = BinaryTreeUtil.createBinarySearchTree(elts);
		BinarySearchTree bst = new BinarySearchTree();
		bst.root = bt.root;
		
		BTNode predecessor = BinarySearchTreeUtil.inorderPredecessor(bst, 20);
		if(predecessor != null)
			System.out.println("Inorder predecessor for 20 is " + predecessor.getData());
		else
			System.out.println("No such preorder node exists.");
		
		BTNode successor = BinarySearchTreeUtil.inorderSuccessor(bst, 20);
		if(successor != null)
			System.out.println("Inorder successor for 20 is " + successor.getData());
		else
			System.out.println("No such preorder node exists.");
		
		BTNode ancestor = BinarySearchTreeUtil.leastCommonAncestor(bst, 10, 30);
		if(ancestor != null)
			System.out.println("Least common ancestor for 10 and 30 is " + ancestor.getData());
		else
			System.out.println("No such ancestor node exists.");
	}
}