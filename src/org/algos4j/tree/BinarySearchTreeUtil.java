package org.algos4j.tree;

import java.util.Stack;

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
	
	/**
	 * Given a binary search tree, this method finds the kth smallest node.
	 * Based on inorder traversal, k is (1 <= k <= size(tree)).
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param k
	 * 		position of smallest node in tree
	 * 
	 * @return
	 * 		the kth smallest node if exists, null otherwise (k > size)
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 * @throws IllegalArgumentException
	 * 		if the given position is -ve
	 */
	static BTNode findKthSmallest(BinarySearchTree bst, int k) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		if (k <= 0)
			throw new IllegalArgumentException("position k must be positive");

		Stack<BTNode> stack = new Stack<>();
		BTNode node = bst.root;
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	
		int pos = 0;
		while (!stack.isEmpty()) {
			pos++;

			if (pos == k)
				return stack.pop();

			BTNode right = stack.pop().right;
			while (right != null) {
				stack.push(right);
				right = right.left;
			}
		}

		return null;
	}
	
	/**
	 * Given a binary search tree, this method finds the kth smallest node.
	 * Based on inorder traversal, k is (1 <= k <= size(tree)).
	 * Recursive version
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param k
	 * 		position of smallest node in tree
	 * 
	 * @return
	 * 		the kth smallest node if exists, null otherwise (k > size)
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 * @throws IllegalArgumentException
	 * 		if the given position is -ve
	 */
	static BTNode findKthSmallest1(BinarySearchTree bst, int k) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		if (k <= 0)
			throw new IllegalArgumentException("position k must be positive");
		
		int[] pos = new int[]{ 0 };
		
		return findKthSmallest1(bst.root, pos, k);
	}
	
	/**
	 * Recursively find the kth smallest node.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param pos
	 * 		current position of node in inorder
	 * @param k
	 * 		position of k
	 * 
	 * @return
	 * 		the kth smallest or null
	 */
	private static BTNode findKthSmallest1(BTNode node, int[] pos, int k) {
		if (node == null)
			return null;

		BTNode small = findKthSmallest1(node.left, pos, k);
		if (small != null)
			return small;
	
		pos[0]++;
		if (k == pos[0])
			return node;

		return findKthSmallest1(node.right, pos, k);
	}
	
	/**
	 * Given a binary search tree, this method finds the kth smallest node.
	 * Based on Morris inorder traversal, k is (1 <= k <= size(tree)).
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param k
	 * 		position of smallest node in tree
	 * 
	 * @return
	 * 		the kth smallest node if exists, null otherwise (k > size)
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 * @throws IllegalArgumentException
	 * 		if the given position is -ve
	 */
	static BTNode findKthSmallestMorris(BinarySearchTree bst, int k) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		if (k <= 0)
			throw new IllegalArgumentException("position k must be positive");
		
		int pos = 0;
		 
	    BTNode kthSmall = null;
	    BTNode curr = bst.root;
	 
		while (curr != null) {
			if (curr.left == null) {
				pos++;

				if (pos == k)
					kthSmall = curr;

				curr = curr.right;
			} else {
				BTNode pre = curr.left;
				while (pre.right != null && pre.right != curr)
					pre = pre.right;

				if (pre.right == null) {
					pre.right = curr;
					curr = curr.left;
				} else {
					pre.right = null;

					pos++;

					if (pos == k)
						kthSmall = curr;

					curr = curr.right;
				}
			}
		}
	    
	    return kthSmall;
	}
	
	/**
	 * Given a binary search tree, this method finds the kth largest node.
	 * Based on reverse inorder traversal, k is (1 <= k <= size(tree)).
	 * Recursive version
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param k
	 * 		position of largest node in tree
	 * 
	 * @return
	 * 		the kth largest node if exists, null otherwise (k > size)
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 * @throws IllegalArgumentException
	 * 		if the given position is -ve
	 */
	static BTNode findKthLargest(BinarySearchTree bst, int k) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		if (k <= 0)
			throw new IllegalArgumentException("position k must be positive");
		
		int[] pos = new int[]{ 0 };
		
		return findKthLargest(bst.root, pos, k);
	}
	
	/**
	 * Recursively find the kth largest node.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param pos
	 * 		current position of node in inorder
	 * @param k
	 * 		position of k
	 * 
	 * @return
	 * 		the kth largest or null
	 */
	private static BTNode findKthLargest(BTNode node, int[] pos, int k) {
		if (node == null)
			return null;

		BTNode large = findKthLargest(node.right, pos, k);
		if (large != null)
			return large;
	
		pos[0]++;
		if (pos[0] == k)
			return node;

		return findKthLargest(node.left, pos, k);
	}
	
	/**
	 * Given a binary search tree two values value1 and value2, this method
	 * prints all the keys of tree in range value1 to value2. Print all the keys
	 * in increasing order.
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param value1
	 * 		first value
	 * @param value2
	 * 		second value
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 * @throws IllegalArgumentException
	 * 		if value1 > value2
	 */
	static void printRange(BinarySearchTree bst, int value1, int value2) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		if(value1 > value2)
			throw new IllegalArgumentException("First value should not be greater than second one.");
		
		printRange(bst.root, value1, value2);
	}
	
	/**
	 * Recursively print the nodes that are in range.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param value1
	 * 		first value
	 * @param value2
	 * 		second value
	 */
	private static void printRange(BTNode node, int value1, int value2) {
		if (node == null)
			return;

		if (value1 < node.getData())
			printRange(node.left, value1, value2);

		if (value1 <= node.getData() && value2 >= node.getData())
			System.out.print(node.getData() + " ");

		if (value2 > node.getData())
			printRange(node.right, value1, value2);
	}
	
	/**
	 * Given a binary search tree two values value1 and value2, this method
	 * removes all the keys of tree that are outside of range value1 to value2. 
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param value1
	 * 		first value
	 * @param value2
	 * 		second value
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 * @throws IllegalArgumentException
	 * 		if value1 > value2
	 */
	static void removeOutsideRange(BinarySearchTree bst, int value1, int value2) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		if(value1 > value2)
			throw new IllegalArgumentException("First value should not be greater than second one.");
		
		bst.root = removeOutsideRange(bst.root, value1, value2);
	}

	/**
	 * Recursively remove the nodes outside of range.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param value1
	 * 		forst value
	 * @param value2
	 * 		second value
	 * 
	 * @return
	 * 		new root after pruning
	 */
	private static BTNode removeOutsideRange(BTNode root, int value1, int value2) {
		if (root == null)
			return null;

		root.left = removeOutsideRange(root.left, value1, value2);
		root.right = removeOutsideRange(root.right, value1, value2);

		if (root.getData() < value1)
			return root.right;

		if (root.getData() > value2)
			return root.left;

		return root;
	}
	
	/**
	 * Given a binary search tree and the two range values, this method returns
	 * the number of nodes that lie in the range. Time: O(h + k), k: nodes in the range
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param value1
	 * 		first value
	 * @param value2
	 * 		second value
	 * 
	 * @return
	 * 		count of nodes in the range
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 * @throws IllegalArgumentException
	 * 		if value1 > value2
	 */
	public static int countRangeNodes(BinarySearchTree bst, int value1, int value2) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		if (value1 > value2)
			throw new IllegalArgumentException("First value should not be greater than second one.");

		return countRangeNodes(bst.root, value1, value2);
	}
	
	/**
	 * Recursively count the nodes in range.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param value1
	 * 		first value
	 * @param value2
	 * 		second value
	 * 
	 * @return
	 * 		count of nodes in range
	 */
	private static int countRangeNodes(BTNode node, int value1, int value2) {
		if (node == null)
			return 0;

		if (node.getData() == value1 && node.getData() == value2)
			return 1;

		if (node.getData() >= value1 && node.getData() <= value2)
			return 1 + countRangeNodes(node.left, value1, value2) + countRangeNodes(node.right, value1, value2);

		if (node.getData() < value1)
			return countRangeNodes(node.right, value1, value2);

		return countRangeNodes(node.left, value1, value2);
	}
	
	/**
	 * Given a binary search tree and the two range values, this method returns
	 * the number of subtrees that lie in the range. Time: O(h + k), k: nodes in the range
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param value1
	 * 		first value
	 * @param value2
	 * 		second value
	 * 
	 * @return
	 * 		count of subtrees in the range
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 * @throws IllegalArgumentException
	 * 		if value1 > value2
	 */
	public static int countRangeSubTrees(BinarySearchTree bst, int value1, int value2) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		if (value1 > value2)
			throw new IllegalArgumentException("First value should not be greater than second one.");

		int[] count = new int[] { 0 };
		countRangeSubTrees(bst.root, value1, value2, count);

		return count[0];
	}
	
	/**
	 * Recursively count the subtrees in range.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param value1
	 * 		first value
	 * @param value2
	 * 		second value
	 * 
	 * @return
	 * 		count of subtrees in range
	 */
	private static boolean countRangeSubTrees(BTNode root, int value1, int value2, int[] count) {
		if (root == null)
			return true;

		boolean leftInRange = root.left != null ? countRangeSubTrees(root.left, value1, value2, count) : true;
		boolean rightInRange = root.right != null ? countRangeSubTrees(root.right, value1, value2, count) : true;

		if (leftInRange && rightInRange && root.getData() >= value1 && root.getData() <= value2) {
			count[0]++;
			return true;
		}

		return false;
	}
	
	/**
	 * Given a binary search tree, this method finds the ceil for the given node value.
	 * Ceil value node is the node with smallest value larger than or equal to given node value.
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param value
	 * 		the node for which to find the ceil node
	 * 
	 * @return
	 * 		the ceil node if exists null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 */
	static BTNode ceil(BinarySearchTree bst, int value) {
		if(bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		
		return ceil(bst.root, value);
	}
	
	/**
	 * Recursively find the ceil node.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param value
	 * 		 node value for ceil
	 * 
	 * @return
	 * 		the ceil node if exists null otherwise
	 */
	private static BTNode ceil(BTNode node, int value) {
		if (node == null)
			return null;

		if (node.getData() == value)
			return node;

		if (node.getData() < value)
			return ceil(node.right, value);

		BTNode ceil = ceil(node.left, value);

		return (ceil != null && ceil.getData() >= value) ? ceil : node;
	}
	
	/**
	 * Given a binary search tree, this method finds the floor for the given node value.
	 * Floor value node is the node with largest value lesser than or equal to given node value.
	 * 
	 * @param bst
	 * 		given binary search tree
	 * @param value
	 * 		the node for which to find the floor node
	 * 
	 * @return
	 * 		the floor node if exists null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 */
	static BTNode floor(BinarySearchTree bst, int value) {
		if(bst == null)
			throw new NullPointerException("Binary search tree should not be null.");
		
		return floor(bst.root, value);
	}
	
	/**
	 * Recursively find the floor node.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param value
	 * 		 node value for floor
	 * 
	 * @return
	 * 		the floor node if exists null otherwise
	 */
	private static BTNode floor(BTNode node, int value) {
		if (node == null)
			return null;

		if (node.getData() == value)
			return node;

		if (node.getData() > value)
			return floor(node.left, value);

		BTNode floor = floor(node.right, value);

		return (floor != null && floor.getData() <= value) ? floor : node;
	}
}