package org.algos4j.tree;

/**
 * A test class to test sum tree property of a binary tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreeSumTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Levelorder in lines");
		BinaryTreeUtil.printLevelOrderInLines(bt);
		
		System.out.println();
		System.out.println("Approach 1");
		boolean hasChildSum = BinaryTreeUtil.isSumTree(bt);
		if(hasChildSum) 
			System.out.println("Binary Tree is satisfies sum tree");
		else
			System.out.println("Binary Tree does not satisfies sum tree");
		
		System.out.println();
		System.out.println("Approach 2");
		hasChildSum = BinaryTreeUtil.isSumTreeOptimized(bt);
		if(hasChildSum) 
			System.out.println("Binary Tree is satisfies sum tree");
		else
			System.out.println("Binary Tree does not satisfies sum tree");
		
		BinaryTreeUtil.convertToSumTree(bt);
		System.out.println();
		System.out.println("Levelorder in lines");
		BinaryTreeUtil.printLevelOrderInLines(bt);
	}
}
