package org.algos4j.tree;

/**
 * A test class to test printing of common nodes of two binary search trees.
 * 
 * @author psajja
 *
 */
public class BinarySearchTreeCommonNodesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elts = {25, 15, 20, 5, 10, 35, 30, 45};
		BinaryTree bt = BinaryTreeUtil.createBinarySearchTree(elts);
		BinarySearchTree bst = new BinarySearchTree();
		bst.root = bt.root;
		
		BinarySearchTreeUtil.printCommonNodes(bst, bst);
	}
}
