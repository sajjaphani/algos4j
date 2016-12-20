package org.algos4j.tree;

/**
 * A test class to test structure of any binary tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreeStructureTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		boolean isFoldable = BinaryTreeUtil.isFoldable(bt);
		
		System.out.println("Approach 1");
		if(isFoldable)
			System.out.println("Binary Tree is foldable");
		else
			System.out.println("Binary Tree is not foldable");
		
		isFoldable = BinaryTreeUtil.isFoldable1(bt);
		
		System.out.println("Approach 2");
		if(isFoldable)
			System.out.println("Binary Tree is foldable");
		else
			System.out.println("Binary Tree is not foldable");
		
	}
}
