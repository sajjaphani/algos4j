package org.algos4j.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents a complete binary tree. A complete binary tree is a
 * binary tree where every level, except possibly the last, is completely
 * filled, and all nodes in the last level are as far left as possible.
 * 
 * @author psajja
 * 
 */
public class CompleteBinaryTree extends BinaryTree {

	private Queue<BTNode> queue;

	public CompleteBinaryTree() {
		queue = new LinkedList<>();
	}

	/* (non-Javadoc)
	 * @see org.algos4j.tree.BinaryTree#insert(org.algos4j.tree.BinaryTree.BTNode, int)
	 */
	@Override
	protected BTNode insert(BTNode root, int data) {
		return insert(root, data, queue);
	}

	/**
	 * Insert an element into the complete binary tree.
	 * 
	 * @param root
	 * 		current root
	 * @param data
	 * 		the data to insert
	 * @param queue
	 * 		queue holding the completeness of the binary tree
	 * 
	 * @return
	 * 		new root
	 */
	private BTNode insert(BTNode root, int data, Queue<BTNode> queue) {

		BTNode temp = new BTNode(data);

		if (root == null)
			root = temp;
		else {
			BTNode front = queue.element();
			if (front.left == null)
				front.left = temp;
			else if (front.right == null)
				front.right = temp;

			if (front.left != null && front.right != null)
				queue.remove();
		}

		queue.add(temp);
		
		return root;
	}
}
