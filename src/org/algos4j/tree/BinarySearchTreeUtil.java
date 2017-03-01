package org.algos4j.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
	
	/**
	 * Given a binary search tree, it transforms the tree to greater sum tree.
	 * After transformation each node contains the sum of all nodes greater than that.
	 * Reverse inorder.
	 * 
	 * @param bst
	 * 		given binary search tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 */
	static void toSumTree(BinarySearchTree bst) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null.");

		int[] sum = new int[] { 0 };

		toSumTree(bst.root, sum);
	}
	
	/**
	 * Recursively update the sum of each node.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param sum
	 * 		sum till now
	 */
	private static void toSumTree(BTNode node, int[] sum) {
		if (node == null)
			return;

		toSumTree(node.right, sum);

		sum[0] = sum[0] + node.getData();

		node.setData(sum[0] - node.getData());

		toSumTree(node.left, sum);
	}
	
	/**
	 * Given a binary search tree, where two node values are swapped. 
	 * Corrects the node values to correct the BST.
	 * 
	 * @param bst
	 * 		given binary search tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 */
	public static void correctTree(BinarySearchTree bst) {
		if (bst == null)
			throw new NullPointerException("Binary search tree should not be null.");

		BTNode[] nodes = new BTNode[3];
		correctTree(bst.root, nodes, null);

		// Nodes are distant
		if (nodes[0] != null && nodes[2] != null)
			swap(nodes[0], nodes[2]);
		else if (nodes[0] != null && nodes[1] != null) // Nodes are adjacent
			swap(nodes[0], nodes[1]);
	}

	/**
	 * Recursively traverse (inorder) the tree to find the nodes to be swapped.
	 * If the swapped nodes are adjacent to each other, then nodes[0] and nodes[1]
	 * contain the nodes otherwise nodes[0] and nodes[2] contain the nodes to be swapped.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param nodes
	 * 		the node references contains the nodes to be swapped
	 * @param prev
	 * 		previous node in traversal
	 */
	private static void correctTree(BTNode node, BTNode[] nodes, BTNode prev) {
		if (node == null)
			return;

		correctTree(node.left, nodes, prev);

		// Violation of BST
		if (prev != null && node.getData() < prev.getData()) {
			if (nodes[0] == null) {
				nodes[0] = prev;
				nodes[1] = node;
			} else
				nodes[2] = node;
		}

		correctTree(node.right, nodes, node);
	}

	/**
	 * Swap the data of two nodes.
	 * 
	 * @param node1
	 * 		first node
	 * @param node2
	 * 		second node
	 */
	private static void swap(BTNode node1, BTNode node2) {
		int temp = node1.getData();
		node1.setData(node2.getData());
		node2.setData(temp);
	}
	
	/**
	 * Given two binary search trees, it prints the common nodes (intersection).
	 * Follows iterative inorder traversal. Time: O(n), Space: O(h1 + h2) .
	 * 
	 * @param bst1
	 * 		first binary search tree
	 * @param bst2
	 * 		second binary search tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary search tree is null
	 */
	static void printCommonNodes(BinarySearchTree bst1, BinarySearchTree bst2) {
		if (bst1 == null || bst2 == null)
			throw new NullPointerException("Binary search tree should not be null.");

		BTNode node1 = bst1.root;
		BTNode node2 = bst2.root;

		Stack<BTNode> stack1 = new Stack<>();
		Stack<BTNode> stack2 = new Stack<>();

		pushLeft(node1, stack1);
		pushLeft(node2, stack2);

		while (!stack1.empty() && !stack2.empty()) {
			node1 = stack1.peek();
			node2 = stack2.peek();

			if (node1.getData() == node2.getData()) {
				System.out.print(node1.getData() + " ");
				stack1.pop();
				stack2.pop();

				// Inorder successor
				pushLeft(node1.right, stack1);
				pushLeft(node2.right, stack2);
			} else if (node1.getData() < node2.getData()) {
				stack1.pop();
				node1 = node1.right;
			} else if (node1.getData() > node2.getData()) {
				stack2.pop();
				node2 = node2.right;
			}
		}
	}

	/**
	 * Push the left of the current node elements recursively.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param stack
	 * 		stack to push
	 */
	private static void pushLeft(BTNode node, Stack<BTNode> stack) {
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	}
	
	/**
	 * Given a sorted array (increasing order), this method constructs a binary
	 * search tree which has minimal height. This method assumes the array is
	 * sorted and does not explicitly check whether the array is sorted or not.
	 * 
	 * @param array
	 * 		given sorted array
	 * 
	 * @return
	 * 		constructed binary search tree
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static BinaryTree createMinimalBst(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null");

		BinaryTree bt = new BinarySearchTree();
		bt.setRoot(createMinimalBst(array, 0, array.length - 1));

		return bt;
	}

	/**
	 * Recursively construct the tree.
	 * 
	 * @param array
	 * 		given array
	 * @param start
	 * 		current sub array start
	 * @param end
	 * 		current sub array end
	 * 
	 * @return
	 * 		root node of this subtree
	 */
	private static BTNode createMinimalBst(int[] array, int start, int end) {
		if (end < start)
			return null;

		int mid = (start + end) / 2;
	
		BTNode root = new BTNode(array[mid]);
		root.left = createMinimalBst(array, start, mid - 1);
		root.right = createMinimalBst(array, mid + 1, end);

		return root;
	}
	
	/**
	 * Given a binary search tree with distinct elements, this method returns
	 * the possible arrays that can be used to construct the search tree.
	 * 
	 * @param bst
	 * 		given binary search tree
	 * 
	 * @return
	 * 		possible arrays, in the form of lists of lists
	 */
	static List<List<Integer>> sequences(BinarySearchTree bst) {
		if(bst == null)
			throw new NullPointerException("Binary search tree cannot be null.");
		
		return sequences(bst.root);
	}

	/**
	 * Recursively traverse the subtrees to finds the sequences.
	 * 
	 * @param node
	 * 		the current subtree root.
	 * 
	 * @return
	 * 		the sequences, list of lists possible
	 */
	private static List<List<Integer>> sequences(BTNode node) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (node == null) {
			result.add(new LinkedList<Integer>());
			return result;
		}

		List<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.getData());

		List<List<Integer>> sequencesLeft = sequences(node.left);
		List<List<Integer>> sequencesRight = sequences(node.right);

		// Weave lists
		for (List<Integer> sequenceLeft : sequencesLeft) {
			for (List<Integer> sequenceRight : sequencesRight) {
				List<List<Integer>> weavedLists = new ArrayList<List<Integer>>();
				weaveLists(sequenceLeft, sequenceRight, prefix, weavedLists);
				result.addAll(weavedLists);
			}
		}
		
		return result;
	}

	/**
	 * Recursively weave the lists in all possible ways.
	 * 
	 * @param sequenceLeft
	 * 		first list
	 * @param sequenceRight
	 * 		second list
	 * @param prefix
	 * 		current prefix
	 * @param weavedLists
	 * 		weaved lists
	 */
	private static void weaveLists(List<Integer> sequenceLeft, List<Integer> sequenceRight, List<Integer> prefix,
			List<List<Integer>> weavedLists) {
		// One of the list is empty
		if (sequenceLeft.size() == 0 || sequenceRight.size() == 0) {
			List<Integer> result = new LinkedList<>(prefix);
			result.addAll(sequenceLeft);
			result.addAll(sequenceRight);
			weavedLists.add(result);
			return;
		}

		// Process first list (sequenceLeft) by removing front of the list and
		// adding it to end of prefix
		int firstHead = sequenceLeft.remove(0);
		prefix.add(prefix.size(), firstHead);
		weaveLists(sequenceLeft, sequenceRight, prefix, weavedLists);
		prefix.remove(prefix.size() - 1);
		sequenceLeft.add(0, firstHead);

		// Process second list (sequenceRight) by removing front of the list and
		// adding it to end of prefix
		int secondHead = sequenceRight.remove(0);
		prefix.add(prefix.size(), secondHead);
		weaveLists(sequenceLeft, sequenceRight, prefix, weavedLists);
		prefix.remove(prefix.size() - 1);
		sequenceRight.add(0, secondHead);
	}
}