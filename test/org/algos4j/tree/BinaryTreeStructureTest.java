package org.algos4j.tree;

import org.algos4j.tree.BinaryTree.BTNode;

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
		
		System.out.println();
		
		BinaryTree bt1 = BinaryTreeUtil.createBinaryTree();
		
		boolean isomorphic = BinaryTreeUtil.areIsomorphic(bt, bt1);
		if(isomorphic)
			System.out.println("Binary trees are isomorphic");
		else
			System.out.println("Binary trees are not isomorphic");
		
		BinaryTree bt2 = BinaryTreeUtil.createBinaryTree();
		// Change the structure
		BTNode temp = bt2.root.left;
		bt2.root.left = bt2.root.right;
		bt2.root.right = temp;
		
		boolean quasiIsomorphic = BinaryTreeUtil.areQuasiIsomorphic(bt1, bt2);
		if(quasiIsomorphic)
			System.out.println("Binary trees are quasi isomorphic");
		else
			System.out.println("Binary trees are not quasi isomorphic");
		
		BinaryTree bt01 = BinaryTreeUtil.createBinaryTree();
		BinaryTree bt02 = BinaryTreeUtil.createBinaryTree();
		BinaryTreeUtil.mirror(bt02);
		BTNode root = new BTNode(Integer.MIN_VALUE);
		root.left = bt01.root;
		root.right = bt02.root;
		BinaryTree tree = new BinaryTree();
		tree.setRoot(root);
		boolean isSymmetric = BinaryTreeUtil.isSymmetric(tree);
		if(isSymmetric)
			System.out.println("Binary tree is symmetric");
		else
			System.out.println("Binary tree is not symmetric");
	}
}