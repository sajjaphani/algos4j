package org.algos4j.tree;

/**
 * A test class to test child sum property of a binary tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreeChildSumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Levelorder in lines");
		BinaryTreeUtil.printLevelOrderInLines(bt);
		
		BinaryTreeUtil.convertToChildSumTree(bt);
		
		System.out.println();
		System.out.println("Levelorder in lines after");
		BinaryTreeUtil.printLevelOrderInLines(bt);
		
		System.out.println();
		boolean hasChildSum = BinaryTreeUtil.hasChildSumProperty(bt);
		if(hasChildSum) 
			System.out.println("Binary Tree satisfies child sum property");
		else
			System.out.println("Binary Tree does not satisfies child sum property");
	}
}
