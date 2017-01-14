package org.algos4j.tree;

/**
 * A test class to test sum related stuff in a binary search tree.
 * 
 * @author psajja
 *
 */
public class BinarySearchTreeSumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elts = {25, 15, 20, 5, 10, 35, 30, 45};
		BinaryTree bt = BinaryTreeUtil.createBinarySearchTree(elts);
		BinarySearchTree bst = new BinarySearchTree();
		bst.root = bt.root;
		
		System.out.println("Inorder Before");
		bst.inorder();
		System.out.println();
	
		BinarySearchTreeUtil.toSumTree(bst);
		System.out.println("Inorder After");
		bst.inorder();
		System.out.println();
	}
}
