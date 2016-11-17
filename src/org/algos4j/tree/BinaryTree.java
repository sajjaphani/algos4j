package org.algos4j.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A binary tree representation.
 * This tree holds integer values.
 * 
 * @author psajja
 * 
 */
public class BinaryTree {

	protected BTNode root;

	public BinaryTree() {
		root = null;
	}

	/**
	 * Get the root node of this binary tree.
	 * 
	 * @return the root node.
	 */
	public BTNode getRootNode() {
		return root;
	}
	
	/**
	 * Set the root node.
	 * 
	 * @param root
	 */
	void setRoot(BTNode root) {
		this.root = root;
	}
	
	/**
	 * Checks whether the binary tree is empty.
	 * 
	 * @return true if the tree is empty, false otherwise
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * insert an element into the tree with the given data.
	 * 
	 * @param data
	 * 		data to insert
	 * 
	 */
	public void insert(int data) {
		setRoot(insert(getRootNode(), data));
	}

	/**
	 * Insert an element into the binary tree. Sub classes should provide their own implementation.
	 * 
	 * @param root
	 * 		current root
	 * @param data
	 * 		data to insert
	 * 
	 * @return
	 * 		new root
	 * 
	 * @throws UnsupportedOperationException
	 * 		if this method is accessed
	 */
	protected BTNode insert(BTNode root, int data) {
		throw new UnsupportedOperationException(
				"This operation is not supported.");
	}

	/**
	 * Delete an element into the tree with the given data.
	 * 
	 * @param data
	 * 		data to be deleted
	 * 
	 */
	public void delete(int data) {
		setRoot(delete(getRootNode(), data));
	}

	/**
	 * Delete an element from the binary tree. Sub classes should provide their own implementation.
	 * 
	 * @param root
	 * 		current root
	 * @param data
	 * 		data to be deleted
	 * 
	 * @return
	 * 		new root
	 * 
	 * @throws UnsupportedOperationException
	 * 		if this method is accessed
	 * @throws IllegalArgumentException
	 * 		if the given data item is not found
	 */
	protected BTNode delete(BTNode root, int data) {
		throw new UnsupportedOperationException(
				"This operation is not supported.");
	}
	
	/**
	 * Get the max node from the binary tree.
	 * 
	 * @return
	 * 		max node
	 */
	BTNode max() {
		return max(root);
	}
	
	/**
	 * Returns the maximum node.
	 * 
	 * @param root
	 * 		current root
	 * 
	 * @return
	 * 		max node
	 */
	protected BTNode max(BTNode root) {
		if (root == null)
			return null;
		
		BTNode lmax = max(root.left);
		BTNode rmax = max(root.right);

		BTNode max = root;
		if (lmax != null && lmax.getData() > root.getData())
			max = lmax;
		if (rmax != null && rmax.getData() > root.getData())
			max = rmax;

		return max;
	}
	
	/**
	 * Get the min node from the binary tree.
	 * 
	 * @return
	 * 		min node
	 */
	BTNode min() {
		return min(root);
	}
	
	/**
	 * Returns the minimum node.
	 * 
	 * @param root
	 * 		current root
	 * 
	 * @return
	 * 		min node
	 */
	protected BTNode min(BTNode root) {
		if (root == null)
			return null;

		BTNode lmin = min(root.left);
		BTNode rmin = min(root.right);

		BTNode min = root;
		if (lmin != null && lmin.getData() < root.getData())
			min = lmin;
		if (rmin != null && rmin.getData() < root.getData())
			min = rmin;

		return min;
	}
	
	/**
	 * Returns the size of the tree.
	 * 
	 * @return the tree size
	 */
	public int size() {
		return size(root);
	}

	private int size(BTNode node) {
		if (node == null)
			return 0;
		else
			return (size(node.left) + 1 + size(node.right));
	}

	/**
	 * Get the height (depth) of this tree.
	 * 
	 * @return
	 */
	public int height() {
		return height(root);
	}

	/**
	 * Get the height of this tree.
	 * 
	 * @param node
	 * @return
	 */
	private int height(BTNode node) {
		if (node == null) {
			return 0;
		} else {
			int lDepth = height(node.left);
			int rDepth = height(node.right);

			return Math.max(lDepth, rDepth) + 1;
		}
	}

	/**
	 * Prints the elements in inorder traversal
	 */
	public void inorder() {
		inorder(root);
	}

	/**
	 * Recursively print elements in inorder.
	 * 
	 * @param node
	 * 		current node
	 */
	private void inorder(BTNode node) {
		if (node != null) {
			inorder(node.left);
			System.out.print(node.getData() + " ");
			inorder(node.right);
		}
	}

	/**
	 * Prints the elements in preorder traversal
	 */
	public void preorder() {
		preorder(root);
	}

	/**
	 * Recursively print elements in preorder.
	 * 
	 * @param node
	 * 		current node
	 */
	private void preorder(BTNode node) {
		if (node != null) {
			System.out.print(node.getData() + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}

	/**
	 * Prints the elements in post order traversal.
	 */
	public void postorder() {
		postorder(root);
	}

	/**
	 * Recursively print elements in postorder.
	 * 
	 * @param node
	 * 		current node
	 */
	private void postorder(BTNode node) {
		if (node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.getData() + " ");
		}
	}

	/**
	 * Prints the elements in level order traversal
	 */
	public void levelorder() {
		levelorder(root);
	}

	/**
	 * Print elements in levelorder.
	 * 
	 * @param root
	 * 		the root node
	 */
	private void levelorder(BTNode root) {
		if (root == null)
			return;

		Queue<BTNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {

			BTNode current = queue.remove();

			System.out.print(current.getData());

			if (current.left != null)
				queue.add(current.left);

			if (current.right != null)
				queue.add(current.right);
		}
	}

	/**
	 * Search for an element in the binary tree.
	 * 
	 * @param key
	 *            the key to check
	 * 
	 * @return the node with matching key, null otherwise
	 */
	BTNode search(int key) {
		return search(root, key);
	}

	/**
	 * Search for an element. Subclasses provides their own implementation. For
	 * example BST.
	 * 
	 * @param node
	 *            root node
	 * @param val
	 *  	the value to check
	 * 
	 * @return 
	 * 		the node with matching key, null otherwise
	 */
	protected BTNode search(BTNode node, int val) {
		if (node != null) {
			if (node.getData() == val)
				return node;
			search(node.left, val);
			search(node.right, val);
		}

		return null;
	}

	/**
	 * Represents a node in a binary tree.
	 * 
	 * @author psajja
	 * 
	 */
	static class BTNode {
		BTNode left;
		BTNode right;
		private int data;

		public BTNode(int data) {
			this.data = data;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}
	}
}
