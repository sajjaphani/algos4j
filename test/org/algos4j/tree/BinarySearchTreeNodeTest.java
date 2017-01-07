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
		
		BTNode kthSmall = BinarySearchTreeUtil.findKthSmallest(bst, 3);
		if(kthSmall != null)
			System.out.println("3rd smallest element " + kthSmall.getData());
		else
			System.out.println("No such node exists.");
		
		kthSmall = BinarySearchTreeUtil.findKthSmallest1(bst, 3);
		if(kthSmall != null)
			System.out.println("3rd smallest element " + kthSmall.getData());
		else
			System.out.println("No such node exists.");
		
		kthSmall = BinarySearchTreeUtil.findKthSmallestMorris(bst, 3);
		if(kthSmall != null)
			System.out.println("3rd smallest element Morris " + kthSmall.getData());
		else
			System.out.println("No such node exists.");
		
		BTNode kthLarge = BinarySearchTreeUtil.findKthLargest(bst, 3);
		if(kthLarge != null)
			System.out.println("3rd largest element " + kthLarge.getData());
		else
			System.out.println("No such node exists.");
		
		BTNode ceil = BinarySearchTreeUtil.ceil(bst, 32);
		if(ceil != null)
			System.out.println("Ceil for 32 is " + ceil.getData());
		else
			System.out.println("No such node exists.");
		
		BTNode floor = BinarySearchTreeUtil.floor(bst, 32);
		if(floor != null)
			System.out.println("Floor for 32 is " + floor.getData());
		else
			System.out.println("No such node exists.");
	}
}