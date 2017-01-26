package org.algos4j.tree;

import org.algos4j.tree.BinaryTree.BTNode;

/**
 * A test class to test binary tree to list convention.
 * 
 * @author psajja
 *
 */
public class BinaryTreeToListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// BinaryTree bt = BinaryTreeUtil.createBinaryTree();
	
		/*BinaryTree bt = new BinaryTree();
		bt.root = new BTNode(4);
		bt.root.left = new BTNode(2);
		bt.root.right = new BTNode(5);
		bt.root.left.left = new BTNode(1);
		bt.root.left.right = new BTNode(3);*/
		
		int[] elts = {25, 15, 20, 5, 10, 35, 30, 45};
		BinaryTree bt = BinaryTreeUtil.createBinarySearchTree(elts);
		
		System.out.println("Inorder");
		bt.inorder();
		System.out.println();
	
		BinaryTreeUtil.toList(bt);
		
		BTNode head = bt.root;
		if(head == null)
			return;
		
		System.out.println("List");
		System.out.print(head.getData());
		while (head.right != bt.root) {
			head = head.right;
            System.out.print(" " + head.getData());
        }
        
        System.out.println();
	}
}
