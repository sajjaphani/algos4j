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
		
		System.out.println("Binary Tree Search");
		BTNode searchNode = BinaryTreeUtil.search(bt, 5);
		if(searchNode == null)
			System.out.println("Element 5 is not found in the tree");
		else
			System.out.println("Element 5 is found in the tree");
		
		searchNode = BinaryTreeUtil.search(bt, 15);
		if(searchNode == null)
			System.out.println("Element 15 is not found in the tree");
		else
			System.out.println("Element 15 is found in the tree");
		
		System.out.println();
		System.out.println("Height: " + BinaryTreeUtil.height(bt));
		
		System.out.println();
		System.out.println("Min Height/Depth: " + BinaryTreeUtil.minHeight(bt));
		
		System.out.println();
		System.out.println("Leaf Nodes: " + BinaryTreeUtil.countLeafNodes(bt));
		
		System.out.println();
		System.out.println("Univalued (Single value) subtrees: " + BinaryTreeUtil.countUnivaluedSubtrees(bt));
		
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
