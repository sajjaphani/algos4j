package org.algos4j.tree;

import org.algos4j.tree.BinaryTree.BTNode;

/**
 * This class implements some utility methods on Binary search trees.
 * 
 * @author psajja
 *
 */
public class BinarySearchTreeUtil {

	/**
	 * Not-instantiable
	 */
	private BinarySearchTreeUtil() {
	}
	
	/**
	 * Given a subtree node and a value, this method returns the target node with the given value.
	 * 
	 * @param node
	 * 		subtree root
	 * @param value
	 * 		node value of the target node
	 * 
	 * @return
	 * 		node with the given value or null if not found
	 * 		
	 */
	static BTNode getTargetNode(BTNode node, int value) {
		if(node == null)
			return null;
		
		if(node.getData() < value)
			return getTargetNode(node.right, value);
		
		if(node.getData() > value)
			return getTargetNode(node.left, value);
		
		return node;
	}
	
	/**
	 * Given a binary search tree, this method finds the inorder successor node for a given node.
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param value
	 * 		the node for which to find the inorder successor
	 * 
	 * @return
	 * 		the inorder successor node if exists null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 * @throws IllegalArgumentException
	 * 		if the given node value does not exist in the tree
	 */
	static BTNode inorderSuccessor(BinarySearchTree bst, int value) {
		if(bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		
		return inorderSuccessor(bst.root, value);
	}
	
	/**
	 * Recursively find the inorder successor node.
	 * 
	 * @param root
	 * 		tree root
	 * @param value
	 * 		the node value
	 * 
	 * @return
	 * 		the inorder successor node or null
	 */
	private static BTNode inorderSuccessor(BTNode root, int value) {
		BTNode node = getTargetNode(root, value);
		if (node == null)
			throw new IllegalArgumentException("There is no node with the given value: " + value);

		// We found the node
		if (node.right != null)
			return minValue(node.right);

		BTNode successor = null;

		while (root != null) {
			if (node.getData() < root.getData()) {
				successor = root;
				root = root.left;
			} else if (node.getData() > root.getData())
				root = root.right;
			else
				break;
		}

		return successor;
	}
	
	/**
	 * Return the minimum node in this subtree.
	 * 
	 * @param node
	 * 		current subtree root
	 * 
	 * @return
	 * 		minimum value node
	 */
	private static BTNode minValue(BTNode node) {
		BTNode current = node;
		while (current.left != null)
			current = current.left;

		return current;
	}
	
	/**
	 * Given a binary search tree, this method finds the inorder predecessor
	 * node for a given node. The predecessor of a given node in a binary search
	 * tree is the node with largest key and that is strictly less than given
	 * node key.
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param value
	 * 		the node for which to find the inorder predecessor
	 * 
	 * @return
	 * 		the inorder predecessor node if exists null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 * @throws IllegalArgumentException
	 * 		if the given node value does not exist in the tree
	 */
	static BTNode inorderPredecessor(BinarySearchTree bst, int value) {
		if(bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		
		return inorderPredecessor(bst.root, value);
	}
	
	/**
	 * Recursively find the inorder predecessor node.
	 * 
	 * @param root
	 * 		tree root
	 * @param value
	 * 		the node value
	 * 
	 * @return
	 * 		the inorder predecessor node or null
	 */
	private static BTNode inorderPredecessor(BTNode root, int value) {
		BTNode node = getTargetNode(root, value);
		if (node == null)
			throw new IllegalArgumentException("There is no node with the given value: " + value);

		// We found the node
		if (node.left != null)
			return maxValue(node.left);

		BTNode predecessor = null;

		while (root != null) {
			if (node.getData() > root.getData()) {
				predecessor = root;
				root = root.right;
			} else if (node.getData() < root.getData())
				root = root.left;
			else
				break;
		}

		return predecessor;
	}

	/**
	 * Return the maximum node in this subtree.
	 * 
	 * @param node
	 * 		current subtree root
	 * 
	 * @return
	 * 		maximum value node
	 */
	private static BTNode maxValue(BTNode node) {
		BTNode current = node;
		while (current.right != null)
			current = current.right;
	
		return current;
	}
	
	/**
	 * Given a binary search tree and two node values, it returns the least common
	 * ancestor node of the two nodes. The least common ancestor between two
	 * nodes defined as the lowest node in the tree that has both nodes as its
	 * descendants.
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param value1
	 * 		first node value
	 * @param value2
	 * 		second node value
	 * 
	 * @return
	 * 		least common ancestor node, null otherwise
	 * 
	 * @throws NullPointerException
	 *     	if the given binary search tree is null
	 */
	static BTNode leastCommonAncestor(BinarySearchTree bst, int value1, int value2) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null");

		BTNode ancestor = leastCommonAncestor(bst.root, value1, value2);
		if (ancestor != null && getTargetNode(ancestor, value1) != null && getTargetNode(ancestor, value2) != null)
			return ancestor;

		return null;
	}
	
	/**
	 * Recursively find the ancestor node by looking at each node.
	 * 
	 * @param node
	 * 		current node
	 * @param value1
	 * 		first node value
	 * @param value2
	 * 		second node value
	 * 
	 * @return
	 * 		return the common ancestor node
	 */
	private static BTNode leastCommonAncestor(BTNode node, int value1, int value2) {
		if (node == null)
			return null;

		if (node.getData() > value1 && node.getData() > value2)
			return leastCommonAncestor(node.left, value1, value2);
		
		if (node.getData() < value1 && node.getData() < value2)
			return leastCommonAncestor(node.right, value1, value2);

		return node;
	}
}