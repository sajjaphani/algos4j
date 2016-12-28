package org.algos4j.tree;

import org.algos4j.tree.BinaryTree.BTNode;

/**
 * A test class to test paths in a binary tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreePathsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Binary Tree Paths");
		BinaryTreeUtil.printPaths(bt);
		
		System.out.println();
		System.out.println("Binary Tree Paths");
		BinaryTreeUtil.printPaths1(bt);
		
		System.out.println();
		System.out.println("Has Path Sum (25): " + BinaryTreeUtil.hasPathSum(bt, 25));
		
		System.out.println();
		System.out.println("Has Path Sum (19): " + BinaryTreeUtil.hasPathSum(bt, 19));
		
		System.out.println();
		System.out.println("Printing Nodes at distance 2:");
		BinaryTreeUtil.printNodes(bt, 2);
		
		System.out.println();
		System.out.println("Printing Nodes at distance 2 (from leaf):");
		BinaryTreeUtil.printNodesFromLeaf(bt, 2);
		
		System.out.println();
		System.out.println("Printing Nodes at distance 3 (from a given node 3):");
		BTNode target = bt.root.right;
		// target.right = null;
		BinaryTreeUtil.printNodes(bt, target, 3);
		
		System.out.println();
		System.out.println("Printing Ancestors of node 9:");
		BinaryTreeUtil.printAncestors(bt, 9);
		
		System.out.println();
		System.out.println("Printing Ancestors of node 9 (iterative):");
		BinaryTreeUtil.printAncestorsIterative(bt, 9);
		
		System.out.println();
		System.out.println("Least common ancestor");
		BTNode ancestor = BinaryTreeUtil.leastCommonAncestor(bt, 4, 8);
		if(ancestor == null)
			System.out.println("There is no common ancestor");
		else
			System.out.println("Common ancestor is: " + ancestor.getData());
		
		System.out.println();
		System.out.println("Distance " + BinaryTreeUtil.distance(bt, 4, 8));
		
		System.out.println();
		System.out.println("Printing Max Sum Path (root to leaf)");
		BinaryTreeUtil.printMaxSumPath(bt);
		
		System.out.println();
		System.out.println("Max Path Sum (leaf to leaf): " + BinaryTreeUtil.maxPathSum(bt));
		
		System.out.println();
		System.out.println("Pruning Tree (based on path sum)");
		System.out.println("Paths before pruning");
		BinaryTreeUtil.printPaths(bt);
		BinaryTreeUtil.pruneTree(bt, 15);
		System.out.println("Paths after pruning");
		BinaryTreeUtil.printPaths(bt);
	}
}
