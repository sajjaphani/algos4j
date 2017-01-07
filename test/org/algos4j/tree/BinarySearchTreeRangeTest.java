package org.algos4j.tree;

/**
 * A test class to test range of nodes in binary search tree.
 * 
 * @author psajja
 *
 */
public class BinarySearchTreeRangeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elts = {25, 15, 20, 5, 10, 35, 30, 45};
		BinaryTree bt = BinaryTreeUtil.createBinarySearchTree(elts);
		BinarySearchTree bst = new BinarySearchTree();
		bst.root = bt.root;
		
		System.out.println("Printing binary search tree in range: ");
		BinarySearchTreeUtil.printRange(bst, 10, 30);
		
		System.out.println("Removing outside ragne nodes: ");
		System.out.println("Inorder before");
		bst.inorder();
		
		System.out.println();
		System.out.println("Count of nodes in range: " + BinarySearchTreeUtil.countRangeNodes(bst, 15, 30));
		
		System.out.println();
		System.out.println("Count of sub trees in range: " + BinarySearchTreeUtil.countRangeSubTrees(bst, 15, 30));
		
		System.out.println();
		BinarySearchTreeUtil.removeOutsideRange(bst, 10, 30);
		System.out.println("Inorder after");
		bst.inorder();
	}
}