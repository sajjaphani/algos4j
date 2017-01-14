package org.algos4j.tree;

/**
 * A test class to test correcting the nodes that are otherwise swapped in BST.
 * 
 * @author psajja
 *
 */
public class BinarySearchTreeCorrectTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elts = {25, 15, 20, 5, 10, 35, 30, 45};
		BinaryTree bt = BinaryTreeUtil.createBinarySearchTree(elts);
		BinarySearchTree bst = new BinarySearchTree();
		bst.root = bt.root;
		
		bst.root.left.setData(35);
		bst.root.right.setData(15);
		
		System.out.println("Inorder Before");
		bst.inorder();
		System.out.println();
	
		BinarySearchTreeUtil.correctTree(bst);
		System.out.println("Inorder After");
		bst.inorder();
		System.out.println();
	}
}