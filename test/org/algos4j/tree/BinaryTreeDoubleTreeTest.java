package org.algos4j.tree;

/**
 * A test class to test doubling a binary tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreeDoubleTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Before doubling");
		BinaryTreeUtil.printLevelOrderInLines(bt);
		
		BinaryTreeUtil.doubleTree(bt);
	
		System.out.println();
		System.out.println("After doubling");
		BinaryTreeUtil.printLevelOrderInLines(bt);
	}
}
