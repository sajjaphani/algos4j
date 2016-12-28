package org.algos4j.tree;

import org.algos4j.tree.BinaryTree.BTNode;

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
		System.out.println("Height: " + BinaryTreeUtil.height(bt));
		
		System.out.println();
		System.out.println("Leaf Nodes: " + BinaryTreeUtil.countLeafNodes(bt));
		
		System.out.println();
		BTNode deepestNode = BinaryTreeUtil.deepestLeftLeaf(bt);
		System.out.println("Deepest Left Leaf: " + deepestNode.getData());
		BinaryTree bt1 = new BinaryTree();
		bt1.root = new BTNode(1);
		bt1.root.right = new BTNode(2);
		BTNode deepestNode1 = BinaryTreeUtil.deepestLeftLeaf(bt1);
		if(deepestNode1 == null)
			System.out.println("Deepest left node does not exist");
		else
			System.out.println("Deepest Left Leaf: " + deepestNode1.getData());
		
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
		
		System.out.println();
		System.out.println("Height Balanced (Red-Black): " + BinaryTreeUtil.isRedBlackBalanced(bt));
	}
}
