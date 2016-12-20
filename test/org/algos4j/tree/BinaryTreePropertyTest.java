package org.algos4j.tree;

/**
 * A test class to test binary tree properties.
 * 
 * @author psajja
 *
 */
public class BinaryTreePropertyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Binary Tree Properties");
		System.out.println("Size: " + BinaryTreeUtil.size(bt));
		
		System.out.println();
		System.out.println("Leaf Nodes: " + BinaryTreeUtil.countLeafNodes(bt));
		
		System.out.println();
		System.out.println("Diameter: " + BinaryTreeUtil.diameter(bt));
		
		System.out.println();
		System.out.println("Diameter Optimized: " + BinaryTreeUtil.diameterOptimized(bt));
		
		System.out.println();
		System.out.println("Max Width: " + BinaryTreeUtil.maxLevelWidth(bt));
		
		System.out.println();
		System.out.println("Max Width: " + BinaryTreeUtil.maxLevelWidth1(bt));
		
		System.out.println();
		System.out.println("Height Balanced: " + BinaryTreeUtil.hasBalancedHeight(bt));
		
		System.out.println();
		System.out.println("Height Balanced Optimized: " + BinaryTreeUtil.hasBalancedHeightOptimized(bt));
	}
}
