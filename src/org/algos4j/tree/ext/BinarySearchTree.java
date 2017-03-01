package org.algos4j.tree.ext;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * A binary tree representation. A binary tree is a tree data structure in which
 * each node has at most two children referred to as the left and the right
 * child. This tree holds integer values. This is a special binary search
 * tree in addition to normal operations it supports finding a random node.
 * 
 * @see org.algos4j.tree.BinarySearchTree
 * 
 * @author psajja
 * 
 */
public class BinarySearchTree {

	protected BTNode root;

	/**
	 * Default constructor
	 */
	public BinarySearchTree() {
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
		if(root == null)
			root = new BTNode(data);
		else 
			insert(root, data);
	}

	/**
	 * Recursively traverse the tree and insert the data.
	 * 
	 * @param root
	 * 		current subtree root
	 * @param data
	 * 		data to insert
	 */
	private void insert(BTNode root, int data) {
		if (data <= root.data) {
			if (root.left == null) {
				root.left = new BTNode(data);
			} else {
				insert(root.left, data);
			}
		} else {
			if (root.right == null) {
				root.right = new BTNode(data);
			} else {
				insert(root.right, data);
			}
		}

		root.incrementSize();
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
	 * @param node
	 * 		current root
	 * 
	 * @return
	 * 		max node
	 */
	protected BTNode max(BTNode node) {
		if (node == null)
			return null;

		if (node.right == null)
			return node;
		
		return max(node.right);
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
	 * @param node
	 * 		current root
	 * 
	 * @return
	 * 		min node
	 */
	protected BTNode min(BTNode node) {
		if (node == null)
			return null;
		
		if (node.left == null)
			return node;
		
		return min(node.left);
	}
	
	/**
	 * Returns the size of the tree.
	 * 
	 * @return the tree size
	 */
	public int size() {
		if (root == null)
			return 0;
		else
			return root.size();
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

			System.out.print(current.getData() + " ");

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
		if (node == null)
			return null;

		if (node.getData() == val)
			return node;
		if (val < node.getData())
			return search(root.left, val);
		if (val > node.getData())
			return search(root.right, val);

		return null;
	}

	/**
	 * Get t random node from the binary search tree.
	 * 
	 * @return
	 * 		a randomly chosen node
	 */
	public BTNode getRandomNode() {
		if (root == null)
			return null;

		Random random = new Random();
		int i = random.nextInt(size());

		return getIthNode(root, i);
	}
	
	/**
	 * Get the ith node.
	 * 
	 * @param root
	 * 		current root
	 * @param i
	 * 		position of the node
	 * 
	 * @return
	 * 		the ith node
	 */
	private BTNode getIthNode(BTNode root, int i) {
		int leftSize = root.left == null ? 0 : root.left.size();

		if (i < leftSize) {
			return getIthNode(root.left, i);
		} else if (i == leftSize) {
			return root;
		} else {
			// Skip leftSize + 1 nodes
			return getIthNode(root.right, i - (leftSize + 1));
		}
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
		private int size = 0;
		
		/**
		 * Initialize node with the given data.
		 * 
		 * @param data
		 * 		node data
		 */
		public BTNode(int data) {
			this.data = data;
			this.size = 1;
		}

		/**
		 * Get the node data.
		 * 
		 * @return
		 * 		node data
		 */
		public int getData() {
			return data;
		}

		/**
		 * Set new data to the node.
		 * 
		 * @param data
		 * 		the node data
		 */
		public void setData(int data) {
			this.data = data;
		}
		
		/**
		 * Get the size of this subtree.
		 * 
		 * @return
		 * 		the size of this subtree
		 */
		public int size() { 
			return size;
		}
		
		/**
		 * Increment current size by one.
		 */
		void incrementSize() {
			size = size + 1; 
		}
		
		/**
		 * Decrement current size by one.
		 */
		void decrementSize() {
			size = size - 1; 
		}
	}
}
