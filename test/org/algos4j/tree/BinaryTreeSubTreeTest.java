package org.algos4j.tree;

/**
 * A test class to test subtree property of two binary trees.
 * 
 * @author psajja
 *
 */
public class BinaryTreeSubTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt1 = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Binary Tree 1");
		BinaryTreeUtil.printLevelOrderInLines(bt1);
		
		BinaryTree bt2 = new BinaryTree();
		bt2.root = 	bt1.root.left;

		System.out.println("Binary Tree 2");
		BinaryTreeUtil.printLevelOrderInLines(bt2);
		
		System.out.println();
		System.out.println("Approach 1");
		boolean hasChildSum = BinaryTreeUtil.isSubTree(bt1, bt2);
		if(hasChildSum) 
			System.out.println("Second Binary Tree is a subtree of first one");
		else
			System.out.println("Second Binary Tree is not a subtree of first one");
		
		System.out.println();
		System.out.println("Approach 2");
		hasChildSum = BinaryTreeUtil.isSubTreeOptimized(bt1, bt2);
		if(hasChildSum) 
			System.out.println("Second Binary Tree is a subtree of first one");
		else
			System.out.println("Second Binary Tree is not a subtree of first one");
	}
}
