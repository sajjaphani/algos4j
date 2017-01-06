package org.algos4j.tree;

/**
 * This class represents a Binary Search Tree implementation. 
 * A Binary Search Tree is a binary tree data structure which has the following properties: 
 * <ul>
 * <li>The left subtree of a node contains keys less than the node's key.</li>
 * <li>The right subtree of a node contains keys greater than the node's key.</li>
 * <li>The left and right subtrees must also be a binary search tree. </li>
 * <li>No duplicate nodes are allowed.</li>
 * </ul>
 * 
 * @author psajja
 *
 */
public class BinarySearchTree extends BinaryTree {

	/* (non-Javadoc)
	 * @see org.algos4j.tree.BinaryTree#search(org.algos4j.tree.BinaryTree.BTNode, int)
	 * 
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see org.algos4j.tree.BinaryTree#insert(org.algos4j.tree.BinaryTree.BTNode, int)
	 */
	@Override
	protected BTNode insert(BTNode root, int data) {
		if (root == null)
			root = new BTNode(data);
		else if (root.getData() > data)
			root.left = insert(root.left, data);
		else if (root.getData() < data)
			root.right = insert(root.right, data);
		
		return root;
	}

	/* (non-Javadoc)
	 * @see org.algos4j.tree.BinaryTree#delete(org.algos4j.tree.BinaryTree.BTNode, int)
	 */
	@Override
	protected BTNode delete(BTNode root, int data) {
		if (root == null)
			throw new IllegalArgumentException("The given data is not found in the tree.");

		if (data < root.getData())
			root.left = delete(root.left, data);
		else if (data > root.getData())
			root.right = delete(root.right, data);
		else {
			// Found the element
			if (root.left != null && root.right != null) {
				BTNode temp = max(root.left);
				root.setData(temp.getData());
				root.left = delete(root.left, root.getData());
			} else if (root.left == null)
				root = root.right;
			else
				root = root.left;
		}
		
		return root;
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.tree.BinaryTree#max(org.algos4j.tree.BinaryTree.BTNode)
	 */
	@Override
	protected BTNode max(BTNode node) {
		if (node == null)
			return null;

		if (node.right == null)
			return node;
		
		return max(node.right);
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.tree.BinaryTree#min(org.algos4j.tree.BinaryTree.BTNode)
	 */
	@Override
	protected BTNode min(BTNode node) {
		if (node == null)
			return null;
		
		if (node.left == null)
			return node;
		
		return min(node.left);
	}
}
