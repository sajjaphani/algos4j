package org.algos4j.tree;

/**
 * A test class to test binary tree traversals, additional methods on traversals.
 * 
 * @author psajja
 *
 */
public class BinaryTreeTraversalTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Binary Tree Traversals");
		BinaryTreeUtil.printLevelOrder(bt);
		
		System.out.println();
		System.out.println("Inorder Traversal (Iterative)");
		BinaryTreeUtil.inorder(bt);
		
		System.out.println();
		System.out.println("Inorder Traversal (Morris)");
		BinaryTreeUtil.inorderMorris(bt);
		
		System.out.println();
		System.out.println("Levelorder Traversal (each level in new line)");
		BinaryTreeUtil.printLevelOrderInLines(bt);
		
		System.out.println();
		System.out.println("Levelorder Traversal (Spiral/ ZigZag)");
		BinaryTreeUtil.printLevelOrderSpiral(bt);
	}
}