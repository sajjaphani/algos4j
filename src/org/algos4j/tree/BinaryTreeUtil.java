package org.algos4j.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import org.algos4j.list.LinkedList.Node;
import org.algos4j.tree.BinaryTree.BTNode;
import org.algos4j.util.ArrayUtil;

/**
 * Holds some of the utility methods on Binary trees.
 * 
 * @author psajja
 *
 */
public class BinaryTreeUtil {

	/**
	 * Not-instantiable
	 */
	private BinaryTreeUtil() {
	}
	
	/**
	 * Creates a binary tree from the given array elements. It constructs and
	 * returns the below tree always.
	 * 
	 * <pre>
	 *   	 1
	 *      /    \
	 *     2      3
	 *   /   \      \
	 *  4     5      6
	 *       /  \    /
	 *      7    8  9
	 * </pre>
	 * 
	 * @return 
	 * 		constructed binary tree
	 */
	static BinaryTree createBinaryTree() {
		BinaryTree binaryTree = new BinaryTree();

		BTNode root = new BTNode(1);
		root.left = new BTNode(2);
		root.right = new BTNode(3);
		root.left.left = new BTNode(4);
		root.left.right = new BTNode(5);
		root.left.right.left = new BTNode(7);
		root.left.right.right = new BTNode(8);
		root.right.right = new BTNode(6);
		root.right.right.left = new BTNode(9);

		binaryTree.setRoot(root);
		
		return binaryTree;
	}
	
	/**
	 * Constructs and returns a complete binary tree from the given array elements.
	 * 
	 * @param array
	 * 		integer array
	 * 
	 * @return
	 * 		new complete binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the given array is null
	 */
	public static BinaryTree createCompleteBinaryTree(int[] array) {
		BinaryTree binaryTree = new CompleteBinaryTree();
		
		for (int elt : array) 
			binaryTree.insert(elt);
		
		return binaryTree;
	}
	
	/**
	 * Constructs and returns a binary search tree from the given array elements.
	 * 
	 * @param array
	 * 		integer array
	 * 
	 * @return
	 * 		new binary search tree
	 * 
	 * @throws NullPointerException
	 * 		if the given array is null
	 */
	public static BinaryTree createBinarySearchTree(int[] array) {
		BinaryTree binaryTree = new BinarySearchTree();
		
		for (int elt : array) 
			binaryTree.insert(elt);
		
		return binaryTree;
	}
	
	/**
	 * Computes the size of the given binary tree without recursion.
	 * It uses Level order traversal.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		the size of the tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	public static int size(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");

		BTNode root = bt.getRootNode();
		if (root == null)
			return 0;

		int size = 0;

		Queue<BTNode> queue = new LinkedList<>();
		queue.add(root);
		BTNode current = null;

		while (!queue.isEmpty()) {
			current = queue.remove();
			size += 1;
			if (current.left != null)
				queue.add(current.left);

			if (current.right != null)
				queue.add(current.right);
		}

		return size;
	}

	/**
	 * Search for an element in the given binary tree.
	 * Iterative solution.
	 * 
	 * @param key
	 *   	the key to check
	 * 
	 * @return 
	 * 		the node with matching key, null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	static BTNode search(BinaryTree bt, int key) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");

		BTNode root = bt.getRootNode();
		if (root == null)
			return null;

		Queue<BTNode> queue = new LinkedList<>();

		queue.add(root);

		while (!queue.isEmpty()) {
			BTNode current = queue.remove();
			if (current.getData() == key)
				return current;

			if (current.left != null)
				queue.add(current.left);

			if (current.right != null)
				queue.add(current.right);
		}

		return null;
	}
	
	/**
	 * Given a binary tree, this method compute the height of the tree. 
	 * Iterative approach.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		the height of the tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	public static int height(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");

		BTNode node = bt.root;
		if (node == null)
			return 0;

		Queue<BTNode> queue = new LinkedList<>();

		queue.add(node);
		int height = 0;

		while (!queue.isEmpty()) {
			height++;
			int nodeCount = queue.size();
			while (nodeCount > 0) {
				BTNode current = queue.remove();
				if (current.left != null)
					queue.add(current.left);
				if (current.right != null)
					queue.add(current.right);
				nodeCount--;
			}
		}
		
		return height;
	}
	
	/**
	 * Given a binary tree, this method compute the minimum height of the tree. 
	 * We can also use level order traversal and return the height when a first leaf node encountered.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		the minimum height of the tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	public static int minHeight(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Input binary tree should not be null");
		
		return minHeight(bt.getRootNode());
	}
	
	/**
	 * Recursively check the height.
	 * 
	 * @param node
	 * 		current subtree root
	 * 
	 * @return
	 * 		minimum height
	 */
	private static int minHeight(BTNode node) {
		if (node == null)
			return 0;

		if (node.left == null && node.right == null)
			return 1;

		if (node.left == null)
			return minHeight(node.right) + 1;

		if (node.right == null)
			return minHeight(node.left) + 1;

		return Math.min(minHeight(node.left), minHeight(node.right)) + 1;
	}
	
	/**
	 * Given a binary tree, this method counts the leaf nodes.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		number of leaf nodes
	 * 
	 * @throws NullPointerException
	 * 		if the binary tree is null
	 */
	public static int countLeafNodes(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Input binary tree should not be null");
		
		return countLeafNodes(bt.getRootNode());
	}

	/**
	 * Recursively count the leaf nodes in subtrees.
	 * 
	 * @param node
	 * 		current subtree node
	 * 
	 * @return
	 * 		number of leaf nodes in subtree
	 */
	private static int countLeafNodes(BTNode node) {
		if (node == null)
			return 0;
		if (node.left == null && node.right == null)
			return 1;
		else
			return countLeafNodes(node.left) + countLeafNodes(node.right);
	}
	
	/**
	 * Given a binary tree, this method count the univalued( single value) subtrees.
	 * All the nodes in the subtree contains the same value.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		number of univalued subtrees
	 * 
	 * @throws NullPointerException
	 * 		if the binary tree is null
	 */
	public static int countUnivaluedSubtrees(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		int[] count = new int[]{0};
		
		countUnivaluedSubtrees(bt.getRootNode(), count);
		
		return count[0];
	}
	
	/**
	 * Recursively check subtree for univalue.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param count
	 * 		count of univalued subtrees
	 * 
	 * @return
	 * 		true if subtree is univalue, false otherwise
	 */
	private static boolean countUnivaluedSubtrees(BTNode node, int[] count) {
		if (node == null)
			return true;

		boolean isLeftUnivalued = countUnivaluedSubtrees(node.left, count);
		boolean isRightUnivalued = countUnivaluedSubtrees(node.right, count);

		if (isLeftUnivalued == false || isRightUnivalued == false)
			return false;

		if (node.left != null && node.getData() != node.left.getData())
			return false;

		if (node.right != null && node.getData() != node.right.getData())
			return false;

		count[0] = count[0] + 1;
		
		return true;
	}

	/**
	 * Given a binary tree, this method computes the difference of node sum of
	 * odd and even level nodes. This can also be achieved using level order
	 * traversal.
	 * 
	 * @param bt
	 *    	given binary tree
	 * 
	 * @return 
	 * 		number of leaf nodes
	 * 
	 * @throws NullPointerException
	 *      if the binary tree is null
	 */
	static int getLevelDifference(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		return getLevelDifference(bt.getRootNode());
	}
	
	/**
	 * Recursively compute the level difference.
	 * 
	 * @param node
	 * 		current subtree node
	 * 
	 * @return
	 * 		the difference
	 */
	private static int getLevelDifference(BTNode node) {
		if (node == null)
			return 0;

		return node.getData() - getLevelDifference(node.left) - getLevelDifference(node.right);
	}

	/**
	 * Given a binary tree, this method computes the depth of the deepest node in odd level.
	 * 
	 *  @param bt
	 *    	given binary tree
	 * 
	 * @return 
	 * 		depth of the deepest odd level node
	 * 
	 * @throws NullPointerException
	 *      if the binary tree is null
	 */
	static int getDeepestOddLevel(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		return getDeepestOddLevel(bt.root, 1);
	 }
	
	/**
	 * Recursively check the subtree for deepest node.
	 * 
	 * @param node
	 * 		subtree root
	 * @param level
	 * 		current level
	 * 
	 * @return
	 * 		depth of the deepest odd level node		
	 */
	private static int getDeepestOddLevel(BTNode node, int level) {
		if (node == null)
			return 0;

		if (node.left == null && node.right == null && (level & 1) != 0)
			return level;

		return Math.max(getDeepestOddLevel(node.left, level + 1), getDeepestOddLevel(node.right, level + 1));
	}

	/**
	 * Given a binary tree it finds the deepest left node.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	static BTNode deepestLeftLeaf(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		BTNode node = bt.root;
		int[] level = new int[] { 0 };
		
		return deepestLeftLeaf(node, level, 0, false);
	}

	/**
	 * Recursively traverse the tree to find the left leave node.
	 * 
	 * @param node
	 * 		current subtree node
	 * @param level
	 * 		contains processed level
	 * @param currentLevel
	 * 		current level to process
	 * @param leftNode
	 * 		indicates whether this is left node or not
	 * 
	 * @return
	 * 		returns the left most node
	 */
	private static BTNode deepestLeftLeaf(BTNode node, int[] level, int currentLevel, boolean leftNode) {
		if (node == null)
			return null;

		if (leftNode != false && node.left == null && node.right == null && currentLevel > level[0]) {
			level[0] = currentLevel;
			return node;
		}

		BTNode leftLeaf = null;
		BTNode rightLeaf = null;

		int leftLevel = 0;

		leftLeaf = deepestLeftLeaf(node.left, level, currentLevel + 1, true);
		leftLevel = level[0];
		rightLeaf = deepestLeftLeaf(node.right, level, currentLevel + 1, false);

		if (leftLevel >= level[0])
			return leftLeaf;

		return rightLeaf;
	}
	
	/**
	 * Given a binary tree, it computes the diameter of the tree. The
	 * diameter of a tree is the number of nodes on the longest path between two
	 * leaves in the tree. Time: O(n^2)
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		diameter of the binary tree.
	 * 
	 * @throws NullPointerException
	 * 		if the binary tree is null
	 */
	static int diameter(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		return diameter(bt.getRootNode());
	}

	/**
	 * Recursively computes the diameter of the tree.
	 * 
	 * @param root
	 * 		current root node
	 * 
	 * @return
	 * 		diameter of the tree 
	 */
	private static int diameter(BTNode root) {
		if (root == null)
			return 0;

		int lheight = height(root.left);
		int rheight = height(root.right);

		int leftDiameter = diameter(root.left);
		int rightDiameter = diameter(root.right);

		return Math.max(lheight + rheight + 1, Math.max(leftDiameter, rightDiameter));
	}
	
	/**
	 * Computes the height of the subtree given subtree root.
	 * 
	 * @param root
	 * 		subtree node
	 * 
	 * @return
	 * 		height of subtree
	 */
	static int height(BTNode root) {
		if (root == null)
			return 0;
		return Math.max(height(root.left), height(root.right)) + 1;
	}
	
	/**
	 * Optimized version for finding diameter of a given binary tree. 
	 * See {@link #diameter(BinaryTree)}.
	 * Time: O(n)
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		diameter of the tree
	 * 
	 * @throws NullPointerException
	 * 		if the binary tree is null
	 */
	public static int diameterOptimized(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		return diameterOptimized(bt.getRootNode(), new int[] { 0 });
	}

	/**
	 * Recursively compute the subtree diameter.
	 * 
	 * @param root
	 * 		current subtree root
	 * @param height
	 * 		one element array holds the height
	 * 
	 * @return
	 * 		diameter of the subtree
	 */
	private static int diameterOptimized(BTNode root, int[] height) {

		int[] leftHeight = new int[] { 0 };
		int[] rightHeight = new int[] { 0 };

		if (root == null) {
			height[0] = 0;
			return 0;
		}

		leftHeight[0]++;
		rightHeight[0]++;

		int leftDiameter = diameterOptimized(root.left, leftHeight);
		int rightDiameter = diameterOptimized(root.right, rightHeight);

		height[0] = Math.max(leftHeight[0], rightHeight[0]) + 1;

		return Math.max(leftHeight[0] + rightHeight[0] + 1, Math.max(leftDiameter, rightDiameter));
	}
	
	/**
	 * Given a binary tree, it returns the maximum width. Max width is the
	 * maximum number of nodes in a level. Time: O(n).
	 * 
	 * @param bt
	 *   	given binary tree
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	public static int maxLevelWidth(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");
	
		BTNode current = bt.root;

		if (current == null)
			return 0;

		int maxWidth = 0;
		Queue<BTNode> queue = new LinkedList<>();
		queue.add(current);

		while (!queue.isEmpty()) {
			int nodeCount = queue.size();
			if(nodeCount > maxWidth)
				maxWidth = nodeCount;
			while (nodeCount > 0) {
				current = queue.remove();
				if (current.left != null)
					queue.add(current.left);
				if (current.right != null)
					queue.add(current.right);
				nodeCount--;
			}
		}
		
		return maxWidth;
	}
	
	/**
	 * Given a binary tree find the maximum level width.
	 * A variant of {@link #maxLevelWidth(BinaryTree)}
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	public static int maxLevelWidth1(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		if(node == null)
			return 0;
		
		int h = height(node);

		int[] width = new int[h];

		int level = 0;

		maxLevelWidth1(node, width, level);

		return ArrayUtil.getMax(width);
	}

	/**
	 * Recursively compute the nodes in each level.
	 * 
	 * @param node
	 * 		the current root node
	 * @param width
	 * 		array to hold the node count in each level
	 * @param level
	 * 		current level
	 */
	private static void maxLevelWidth1(BTNode node, int[] width, int level) {
		if (node != null) {
			width[level]++;
			maxLevelWidth1(node.left, width, level + 1);
			maxLevelWidth1(node.right, width, level + 1);
		}
	}
	
	/**
	 * Prints the nodes in a binary tree in inorder traversal, iterative approach.
	 * 
	 * @param bt
	 * 		given binary tree.
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	public static void inorder(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");

		BTNode current = bt.getRootNode();

		Stack<BTNode> stack = new Stack<BTNode>();

		while (current != null) {
			stack.push(current);
			current = current.left;
		}

		while (stack.size() > 0) {
			current = stack.pop();
			System.out.print(current.getData() + " ");
			if (current.right != null) {
				current = current.right;

				while (current != null) {
					stack.push(current);
					current = current.left;
				}
			}
		}
	}
	
	/**
	 * Traverses the nodes in the given binary tree in inorder traversal. It
	 * does not use any extra stack memory and is iterative. The idea of this
	 * traversal (Morris) is based on threaded binary Tree. It first create
	 * links to inorder successor and print the data using these links, and
	 * finally it restore original tree.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the binary tree is null
	 */
	public static void inorderMorris(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");

		BTNode current = bt.getRootNode();
		if (current == null)
			return;

		BTNode predecessor = null;

		while (current != null) {
			if (current.left == null) {
				System.out.print(current.getData() + " ");
				current = current.right;
			} else {
				// Find inorder predecessor
				predecessor = current.left;
				while (predecessor.right != null && predecessor.right != current)
					predecessor = predecessor.right;

				// current is right child of its inorder predecessor
				if (predecessor.right == null) {
					predecessor.right = current;
					current = current.left;
				} else {
					// Restore the original, fix the right child of predecessor
					predecessor.right = null;
					System.out.print(current.getData() + " ");
					current = current.right;
				}
			}
		}
	}
	
	/**
	 * Given a binary tree, it prints the nodes in preorder traversal of binary tree. 
	 * Iterative approach.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the binary tree is null
	 */
	public static void preorder(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");

		BTNode node = bt.getRootNode();

		if (node == null)
			return;

		Stack<BTNode> stack = new Stack<BTNode>();
		stack.push(node);

		while (!stack.isEmpty()) {
			BTNode top = stack.pop();
			System.out.print(top.getData() + " ");

			if (top.right != null)
				stack.push(top.right);

			if (top.left != null)
				stack.push(top.left);
		}
	}
	
	/**
	 * Given a binary tree, it prints the nodes in postorder traversal of binary tree. 
	 * Iterative approach.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the binary tree is null
	 */
	public static void postorder(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");

		BTNode current = bt.getRootNode();

		if (current == null)
			return;

		Stack<BTNode> stack1 = new Stack<BTNode>();
		Stack<BTNode> stack2 = new Stack<BTNode>();

		stack1.push(current);

		while (!stack1.isEmpty()) {
			current = stack1.pop();
			stack2.push(current);

			if (current.left != null)
				stack1.push(current.left);
			if (current.right != null)
				stack1.push(current.right);
		}

		while (!stack2.isEmpty()) {
			current = stack2.pop();
			System.out.print(current.getData() + " ");
		}
	}
	
	/**
	 * Given a binary tree, it prints the tree in level order without any additional memory.
	 * Time: O(n^2).
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	static void printLevelOrder(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");
		BTNode root = bt.root;

		int h = bt.height();
		// If we iterate from h to 1, we can obtain reverse level order traversal
		for (int i = 1; i <= h; i++)
			printGivenLevel(root, i);
	}

	/**
	 * Print the given level nodes.
	 * 
	 * @param root
	 * 		current root node
	 * @param level
	 * 		current level
	 */
	private static void printGivenLevel(BTNode root, int level) {
		if (root == null)
			return;
		if (level == 1)
			System.out.print(root.getData() + " ");
		else if (level > 1) {
			printGivenLevel(root.left, level - 1);
			printGivenLevel(root.right, level - 1);
		}
	}
	
	/**
	 * Given a binary tree, it prints the tree in level order without any additional memory.
	 * It prints each level in a new line.
	 * Time: O(n).
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	static void printLevelOrderInLines(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");
		BTNode current = bt.root;

		if (current == null)
			return;

		Queue<BTNode> queue = new LinkedList<>();

		queue.add(current);

		while (!queue.isEmpty()) {
			int nodeCount = queue.size();

			while (nodeCount > 0) {
				current = queue.remove();
				System.out.print(current.getData() + " ");
				if (current.left != null)
					queue.add(current.left);
				if (current.right != null)
					queue.add(current.right);
				nodeCount--;
			}
			System.out.println();
		}
	}
	
	/**
	 * Given a binary tree, it prints the tree in level order in spiral fashion also known as zigzag.
	 * The direction is flipped at each level.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 	
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	static void printLevelOrderSpiral(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Input binary tree should not be null");

		BTNode root = bt.root;
		if (root == null)
			return;
		boolean leftToRight = true;

		Stack<BTNode> currentLevel = new Stack<>();
		Stack<BTNode> nextLevel = new Stack<>();
		currentLevel.push(root);
		while (!currentLevel.isEmpty()) {

			BTNode currNode = currentLevel.pop();

			if (currNode != null) {
				System.out.print(currNode.getData() + " ");
				if (leftToRight) {
					nextLevel.push(currNode.left);
					nextLevel.push(currNode.right);
				} else {
					nextLevel.push(currNode.right);
					nextLevel.push(currNode.left);
				}
			}

			if (currentLevel.isEmpty()) {
				System.out.println();
				leftToRight = !leftToRight;

				// Swap the stacks
				Stack<BTNode> temp = currentLevel;
				currentLevel = nextLevel;
				nextLevel = temp;
			}
		}
	}
	
	/**
	 * Print the level order in reverse. Prints in order of last level first, first level last.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 	
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	static void printLevelOrderReverse(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");

		BTNode root = bt.getRootNode();
		if (root == null)
			return;

		Queue<BTNode> queue = new LinkedList<>();
		Stack<BTNode> stack = new Stack<>();
		queue.add(root);
		BTNode current = null;

		while (!queue.isEmpty()) {
			current = queue.remove();
			stack.push(current);
			
			if (current.right != null)
				queue.add(current.right);
			
			if (current.left != null)
				queue.add(current.left);
		}

		while (!stack.isEmpty())
			System.out.print(stack.pop().getData() + " ");
	}
	
	/**
	 * Given a binary tree and start and end levels, it prints the corresponding level nodes.
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param startLevel
	 * 		starting level
	 * @param endLevel
	 * 		ending level
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 * @throws IllegalArgumentException
	 * 		if the start/end levels are not valid
	 */
	static void printLevelNodes(BinaryTree bt, int startLevel, int endLevel) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");
		if(startLevel <= 0 || endLevel <= 0)
			throw new IllegalArgumentException("Invalid start/end level, must be > 0");
		if(startLevel >= endLevel)
			throw new IllegalArgumentException("Start level must be less than end level");
		if(endLevel > bt.height())
			throw new IllegalArgumentException("End level beyond max level");
		
		BTNode node = bt.getRootNode();
		if (node == null)
			return;
		
		Queue<BTNode> queue = new LinkedList<>();
		  
        int level = 0;
  
        queue.add(node);
  
		while (!queue.isEmpty()) {
			level ++;
			
			if(level > endLevel)
				break;
			
			int nodeCount = queue.size();

			while (nodeCount > 0) {
				node = queue.remove();
				if(level >= startLevel)
					System.out.print(node.getData() + " ");
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);
				nodeCount--;
			}
			if(level >= startLevel)
				System.out.println();
		}
	}
	
	/**
	 * This method check whether the given two binary trees are identical.
	 * It checks the structure and the corresponding data in nodes.
	 * 
	 * @param bt1
	 * 		first binary tree
	 * @param bt2
	 * 		second binary tree
	 * 
	 * @return
	 * 		true if both are identical, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if any of the given binary tree is null
	 */
	public static boolean areIdentical(BinaryTree bt1, BinaryTree bt2) {
		if (bt1 == null || bt2 == null)
			throw new NullPointerException("Binary tree(s) should not be null");

		BTNode node1 = bt1.getRootNode();
		BTNode node2 = bt2.getRootNode();

		return areIdentical(node1, node2);
	}

	/**
	 * Check recursively the sub trees are identical.
	 * 
	 * @param node1
	 * 		current node in first tree
	 * @param node2
	 * 		current node in second tree
	 * 
	 * @return
	 * 		true if the current sub-trees are identical, false otherwise
	 */
	private static boolean areIdentical(BTNode node1, BTNode node2) {
		if (node1 == null && node2 == null)
			return true;

		if (node1 != null && node2 != null)
			return (node1.getData() == node2.getData() && areIdentical(node1.left, node2.left)
					&& areIdentical(node1.right, node2.right));

		return false;
	}
	
	/**
	 * Given a binary tree, it check whether it is symmetric tree.
	 * It checks whether the tree is mirror to itself.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if it is symmetric, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	public static boolean isSymmetric(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
	
		BTNode node = bt.root;
		if(node == null)
			return true;
		
		return isSymmetric(bt.root.left, bt.root.right);
	}
	
	/**
	 * Recursively check the subtrees for symmetry.
	 * 
	 * @param node1
	 * 		first subtree root
	 * @param node2
	 * 		second subtree root
	 * 
	 * @return
	 * 		true if they are symmetric, false otherwise
	 */
	private static boolean isSymmetric(BTNode node1, BTNode node2) {
		if (node1 == null && node2 == null)
			return true;

		if (node1 != null && node2 != null && node1.getData() == node2.getData())
			return (isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left));

		return false;
	}
	
	/**
	 * Given a binary tree, it converts the tree to its mirror image.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	public static void mirror(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		mirror(bt.getRootNode());
	}

	/**
	 * Recursively create the mirror image of sub tree.
	 * 
	 * @param node
	 * 		current subtree node
	 */
	private static void mirror(BTNode node) {
		if (node == null) {
			return;
		} else {
			mirror(node.left);
			mirror(node.right);

			BTNode temp = node.left;
			node.left = node.right;
			node.right = temp;
		}
	}
	
	/**
	 * Given a binary tree, it print all paths from root to each leaf node.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	static void printPaths(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		printPaths(bt.getRootNode(), "");
	}

	/**
	 * Recursively append the node to the path and print.
	 * 
	 * @param node
	 * 		current node to process
	 * @param path
	 * 		path to print
	 */
	private static void printPaths(BTNode node, String path) {
		if (node == null)
			return;
	
		if (node.left == null && node.right == null) {
			System.out.println(path + " " + node.getData());
			return;
		}

		printPaths(node.left, path + " " + node.getData());
		printPaths(node.right, path + " " + node.getData());
	}

	/**
	 * Given a binary tree, it print all paths from root to each leaf node.
	 * A variant of {@link #printPaths(BinaryTree)}
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	static void printPaths1(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		int[] array = new int[bt.size()];
		printPaths1(bt.getRootNode(), array, 0);
	}

	/**
	 * Recursively append the node to the path and print.
	 * 
	 * @param node
	 * 		current node to process
	 * @param path
	 * 		path array to print
	 * @param pathLen
	 * 		path length
	 */
	private static void printPaths1(BTNode node, int[] path, int pathLen) {
		if (node == null)
			return;

		path[pathLen] = node.getData();
		pathLen++;

		if (node.left == null && node.right == null) {
			printPath(path, pathLen);
		} else {
			printPaths1(node.left, path, pathLen);
			printPaths1(node.right, path, pathLen);
		}
	}
	
	/**
	 * Print the path in array.
	 * 
	 * @param path
	 * 		path array
	 * @param pathLen
	 * 		path length
	 */
	private static void printPath(int[] path, int pathLen) {
		for (int i = 0; i < pathLen; i++)
			System.out.print(path[i] + " ");
		System.out.println();
	}

	/**
	 * Given a binary tree, convert it to child sum property tree. Child sum
	 * property is that at any node the node's value is equal to the sum of left
	 * and right node's value.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	public static void convertToChildSumTree(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Input binary tree should not be null");

		convertToChildSumTree(bt.getRootNode());
	}

	/**
	 * Recursively convert each node to satisfy child sum property.
	 * 
	 * @param node
	 * 		current node
	 */
	private static void convertToChildSumTree(BTNode node) {
		int leftData = 0, rightData = 0, diff;

		if (node == null || (node.left == null && node.right == null))
			return;

		convertToChildSumTree(node.left);
		convertToChildSumTree(node.right);

		if (node.left != null)
			leftData = node.left.getData();

		if (node.right != null)
			rightData = node.right.getData();

		diff = leftData + rightData - node.getData();

		// Node's child sum is greater
		if (diff > 0)
			node.setData(node.getData() + diff);

		// Node's data is greater
		if (diff < 0)
			increment(node, diff);
	}

	/**
	 * Recursively adjust the difference to the children.
	 * It first considers left child then right child.
	 * 
	 * @param node
	 * 		current node
	 * @param diff
	 * 		difference to adjust
	 */
	private static void increment(BTNode node, int diff) {
		if (node.left != null) {
			node.left.setData(node.left.getData() - diff);

			increment(node.left, diff);
		} else if (node.right != null) {
			node.right.setData(node.right.getData() - diff);

			increment(node.right, diff);
		}
	}
	
	/**
	 * Check whether the tree nodes satisfies child sum property. For every node,
	 * node data must be equal to sum of node data in left and right child.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if the tree follows child sum property, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the binary tree is null
	 */
	public static boolean hasChildSumProperty(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Input binary tree should not be null");
	
		return hasChildSumProperty(bt.getRootNode());
	}

	/**
	 * For each node, recursively check the child sum property.
	 * 
	 * @param node
	 * 		current node
	 * 
	 * @return
	 * 		true if the subtree has child sum, false otherwise
	 */
	private static boolean hasChildSumProperty(BTNode node) {
		int leftData = 0, rightData = 0;

		if (node == null || (node.left == null && node.right == null))
			return true;
		else {
			if (node.left != null)
				leftData = node.left.getData();

			if (node.right != null)
				rightData = node.right.getData();

			if ((node.getData() == leftData + rightData) && hasChildSumProperty(node.left) && hasChildSumProperty(node.right))
				return true;
		}

		return false;
	}
	
	/**
	 * Check whether the height of the tree is balanced. The tree is height balenced, 
	 * if for every node the height of the left and right subtrees differ by max 1.
	 * Time: O(n^2)
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if the tree height is balanced, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the binary tree is null
	 */
	static boolean hasBalancedHeight(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Input binary tree should not be null");
	
		return hasBalancedHeight(bt.getRootNode());
	}
	
	/**
	 * Recursively check each subtree for height balanced.
	 * 
	 * @param node
	 * 		subtree root node
	 * 
	 * @return
	 * 		true if subtree height balanced, false otherwise
	 */
	private static boolean hasBalancedHeight(BTNode node) {
		int leftHeight = 0;
		int rightHeight = 0;

		if (node == null)
			return true;

		leftHeight = height(node.left);
		rightHeight = height(node.right);

		if (Math.abs(leftHeight - rightHeight) <= 1 && hasBalancedHeight(node.left) && hasBalancedHeight(node.right))
			return true;

		return false;
	}
	
	/**
	 * Check whether the height of the tree is balanced. The tree is height balenced, 
	 * if for every node the height of the left and right subtrees differ by max 1.
	 * Optimized version of {@link #hasBalancedHeight(BinaryTree)}.
	 * Time: O(n)
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if the tree height is balanced, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the binary tree is null
	 */
	static boolean hasBalancedHeightOptimized(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Input binary tree should not be null");
	
		return hasBalancedHeightOptimized(bt.getRootNode(), new int[] {0});
	}
	
	/**
	 * Recursively check the subtree for height balance.
	 * 
	 * @param root
	 * 		current subtree root
	 * @param height
	 * 		one element array holds the height
	 * 
	 * @return
	 * 		true if the subtree is height balanced, false otherwise
	 */
	private static boolean hasBalancedHeightOptimized(BTNode root, int[] height) {

		if (root == null) {
			height[0] = 0;
			return true;
		}

		int[] leftHeight = new int[] { 0 };
		int[] rightHeight = new int[] { 0 };

		boolean leftBalanced = hasBalancedHeightOptimized(root.left, leftHeight);
		boolean rightBalanced = hasBalancedHeightOptimized(root.right, rightHeight);
	
		height[0] = Math.max(leftHeight[0], rightHeight[0]) + 1;

		if (Math.abs(leftHeight[0] - rightHeight[0]) > 1)
			return false;
		else
			return leftBalanced && rightBalanced;
	}
	
	/**
	 * Given a binary tree, this method checks that the tree height is red-black
	 * tree balanced. Tree is red-black balanced if for every node, the length
	 * of the longest path has not more than twice length of shortest path from
	 * node to leaf.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if the height is balanced, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	public static boolean isRedBlackBalanced(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		return isRedBlackBalanced(bt.root, new int[] {0}, new int[] {0});
		
	}
	
	/**
	 * Recursively check for balance of height.
	 * 
	 * @param root
	 * 		current subtree root
	 * @param maxHeight
	 * 		holds maximum height
	 * @param minHeight
	 * 		holds minimum height
	 * 
	 * @return
	 * 		true if height is balenced, false otherwise
	 */
	private static boolean isRedBlackBalanced(BTNode root, int[] maxHeight, int[] minHeight) {
		if (root == null) {
			maxHeight[0] = minHeight[0] = 0;
			return true;
		}

		int[] leftMax = new int[] { 0 };
		int[] leftMin = new int[] { 0 };

		int[] rightMax = new int[] { 0 };
		int[] rightMin = new int[] { 0 };

		if (!isRedBlackBalanced(root.left, leftMax, leftMin))
			return false;

		if (!isRedBlackBalanced(root.right, rightMax, rightMin))
			return false;

		maxHeight[0] = Math.max(leftMax[0], rightMax[0]) + 1;
		minHeight[0] = Math.min(leftMin[0], rightMin[0]) + 1;

		if (maxHeight[0] <= 2 * minHeight[0])
			return true;

		return false;
	}

	/**
	 * Checks whether there is a path from root to leaf with the given sum.
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param sum
	 * 		path sum
	 * 
	 * @return
	 * 		true if there is a path with the given sum, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	public static boolean hasPathSum(BinaryTree bt, int sum) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		return hasPathSum(bt.getRootNode(), sum);
	}

	/**
	 * Recursively find path in the given subtree.
	 * 
	 * @param node
	 * 		subtree root node
	 * @param sum
	 * 		current sum to find
	 * 
	 * @return
	 * 		true if the path sum is equal to given sum, false otherwise
	 */
	private static boolean hasPathSum(BTNode node, int sum) {
		if (node == null)
			return sum == 0;

		boolean hasPathSum = false;

		int subsum = sum - node.getData();
		if (subsum == 0 && node.left == null && node.right == null)
			return true;

		if (node.left != null)
			hasPathSum = hasPathSum || hasPathSum(node.left, subsum);

		if (node.right != null)
			hasPathSum = hasPathSum || hasPathSum(node.right, subsum);

		return hasPathSum;
	}
	
	/**
	 * This method converts the given binary tree into its double tree. A double
	 * tree of the given tree is produced by creating a new duplicate for each
	 * node, and is inserted to the left child of the original node.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	public static void doubleTree(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		doubleTree(bt.getRootNode());
	}

	/**
	 * Recursively convert each subtree to its double tree.
	 * 
	 * @param node
	 * 		current subtree root
	 */
	private static void doubleTree(BTNode node) {

		if (node == null)
			return;

		BTNode left = null;

		doubleTree(node.left);
		doubleTree(node.right);

		// Duplicate node to its left
		left = node.left;
		node.left = new BTNode(node.getData());
		node.left.left = left;
	}
	
	/**
	 * Given a binary tree check whether the tree is foldable. A tree is
	 * foldable if the left and right subtrees are structure wise mirror image
	 * of each other.
	 * 
	 * @param bt
	 *   	given binary tree
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	public static boolean isFoldable(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		return isFoldable(bt.getRootNode());
	}

	/**
	 * Check the tree is foldable at root.
	 * 
	 * @param node
	 * 		given root node
	 * 
	 * @return
	 * 		true if it is foldable, false otherwise
	 */
	private static boolean isFoldable(BTNode node) {
		boolean isFoldable = false;

		if (node == null)
			return true;

		// Mirror of left
		mirror(node.left);

		isFoldable = hasSameStructure(node.left, node.right);

		// Restore the structure back
		mirror(node.left);

		return isFoldable;
	}
	
	/**
	 * Recursively check the two subtrees are foldable.
	 * 
	 * @param node1
	 * 		first subtree
	 * @param node2
	 * 		second subtree
	 * 
	 * @return
	 * 		true if both subtrees are foldable, false otherwise
	 */
	private static boolean hasSameStructure(BTNode node1, BTNode node2) {
		if (node1 == null && node2 == null)
			return true;
	
		if (node1 != null && node2 != null && hasSameStructure(node1.left, node2.left) && hasSameStructure(node1.right, node2.right))
			return true;

		return false;
	}
	
	/**
	 * Given a binary tree check whether the tree is foldable. A variant of 
	 * {@link #isFoldable(BinaryTree)}
	 * 
	 * @param bt
	 *   	given binary tree
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	static boolean isFoldable1(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		BTNode node = bt.root;
		if (node == null)
            return true;
		
		return isFoldable(node.left, node.right);
	}
	
	/**
	 * Recursively verifies the two subtrees for mirror.
	 * 
	 * @param node1
	 * 		first subtree
	 * @param node2
	 * 		second subtree
	 * 
	 * @return
	 * 		if both subtrees are mirror of each other
	 */
	private static boolean isFoldable(BTNode node1, BTNode node2) {

		if (node1 == null && node2 == null)
			return true;

		if (node1 == null || node2 == null)
			return false;

		return isFoldable(node1.left, node2.right) && isFoldable(node1.right, node2.left);
	}
	
    /**
     * Given two binary trees, check whether they are isomorphic.
     * Two trees are called isomorphic if they have the same structure.
     * The values does not affect whether the two trees are isomorphic.
     * 
     * @param bt1
     * 		first binary tree
     * @param bt2
     * 		second binary tree
     * 
     * @return
     * 		true if they are isomorphic, false otherwise
     * 
     * @throws NullPointerException
	 *    	if the given binary tree is null
     */
	public static boolean areIsomorphic(BinaryTree bt1, BinaryTree bt2) {
		if (bt1 == null || bt2 == null)
			throw new NullPointerException("Binary tree should not be null");
		
		return areIsomorphic(bt1.getRootNode(), bt2.getRootNode());
	}

	/**
	 * Recursively check tree nodes for isomorphism.
	 * 
	 * @param node1
	 * 		first tree subtree node
	 * @param node2
	 * 		second tree subtree node
	 * 
	 * @return
	 * 		true if they are isomorphic, false otherwise
	 */
	private static boolean areIsomorphic(BTNode node1, BTNode node2) {
		if (node1 == null && node2 == null)
			return true;

		if (node1 == null || node2 == null)
			return false;

		return areIsomorphic(node1.left, node2.left) && areIsomorphic(node1.right, node2.right);
	}

	/**
	 * Given two binary trees, check whether they are quasi isomorphic. Two
	 * trees are called quasi isomorphic if one tree can be transformed into
	 * another by swapping the children. The shape is important in determining
	 * quasi isomorphism.
	 * 
	 * @param bt1
     * 		first binary tree
     * @param bt2
     * 		second binary tree
     * 
     * @return
     * 		true if they are isomorphic, false otherwise
     * 
     * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	public static boolean areQuasiIsomorphic(BinaryTree bt1, BinaryTree bt2) {
		if (bt1 == null || bt2 == null)
			throw new NullPointerException("Binary tree should not be null");
		
		return areQuasiIsomorphic(bt1.getRootNode(), bt2.getRootNode());
	}
	
	/**
	 * Recursively check tree nodes for quasi isomorphism.
	 * 
	 * @param node1
	 * 		first tree subtree node
	 * @param node2
	 * 		second tree subtree node
	 * 
	 * @return
	 * 		true if they are isomorphic, false otherwise
	 */
	private static boolean areQuasiIsomorphic(BTNode node1, BTNode node2) {
		if (node1 == null && node2 == null)
			return true;

		if (node1 == null || node2 == null)
			return false;

		return (areIsomorphic(node1.left, node2.left) && areIsomorphic(node1.right, node2.right))
				|| (areIsomorphic(node1.left, node2.right) && areIsomorphic(node1.right, node2.left));
	}
	
	/**
	 * Given a binary tree and a distance k (in its path), it prints the nodes
	 * that are k distant from the root. Time: O(n)
	 * 
	 * @param bt
	 *     	given binary tree
	 * @param k
	 *     	distance from root
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 * @throws IllegalArgumentException
	 *     	if the distance is <= 0
	 */
	static void printNodes(BinaryTree bt, int k) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		if (k <= 0)
			throw new IllegalArgumentException("Distance should be > 0.");

		printNodes(bt.getRootNode(), k);
	}

	/**
	 * Recursively print the nodes that can be reached from the given distance.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param k
	 * 		current distance from root
	 */
	private static void printNodes(BTNode node, int k) {
		if (node == null)
			return;

		if (k == 0)
			System.out.print(node.getData() + " ");
		else {
			printNodes(node.left, k - 1);
			printNodes(node.right, k - 1);
		}
	}
	
	/**
	 * Given a binary tree and a distance k (in its path), it prints the nodes
	 * that are k distant from the leaf. Time: O(n)
	 * 
	 * @param bt
	 *     	given binary tree
	 * @param k
	 *     	distance from root
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 * @throws IllegalArgumentException
	 *     	if the distance is <= 0
	 */
	static void printNodesFromLeaf(BinaryTree bt, int k) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		if (k <= 0)
			throw new IllegalArgumentException("Distance should be > 0.");

		int h = bt.size();
		boolean[] visited = new boolean[h];
		int[] path = new int[h];
		
		printNodesFromLeaf(bt.getRootNode(), path, visited, 0, k);
	}
	
	/**
	 * Recursively traverse the tree and print the nodes that are k distance from leaf.
	 * 
	 * @param node
	 * 		current subtree node
	 * @param path
	 * 		stores the path
	 * @param visited
	 * 		indicates whether the node is already visiteed
	 * @param pathLen
	 * 		length of the current path from root
	 * @param k
	 */
	private static void printNodesFromLeaf(BTNode node, int[] path, boolean[] visited, int pathLen, int k) {
		if (node == null)
			return;

		path[pathLen] = node.getData();
		visited[pathLen] = false;
		pathLen++;

		// Print the ancestor of this leaf
		if (node.left == null && node.right == null && pathLen - k - 1 >= 0 && visited[pathLen - k - 1] == false) {
			System.out.print(path[pathLen - k - 1] + " ");
			visited[pathLen - k - 1] = true;
			return;
		}

		printNodesFromLeaf(node.left, path, visited, pathLen, k);
		printNodesFromLeaf(node.right, path, visited, pathLen, k);
	}

	/**
	 * Given a binary tree, a target node and a distance k (in its path), it
	 * prints the nodes that are k distant from the given target node found in
	 * the binary tree. We not only need to look for descendants of target, we
	 * also need to consider the nodes which are also ancestors of the target.
	 * Time: O(n)
	 * 
	 * @param bt
	 *   	given binary tree
	 * @param target
	 *      target node
	 * @param k
	 *     	distance from root
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null or given target node is null
	 * @throws IllegalArgumentException
	 *     	if the distance is <= 0
	 */
	static void printNodes(BinaryTree bt, BTNode target, int k) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		if (target == null)
			throw new NullPointerException("Target node should not be null");
		if (k <= 0)
			throw new IllegalArgumentException("Distance should be > 0.");

	
		printNodes(bt.getRootNode(), target, k);
	}
	
	/**
	 * Recursively print the k distant nodes of the given target.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param target
	 * 		target node
	 * @param k
	 * 		the distance of nodes
	 * 
	 * @return
	 * 		distance of root from target node, -1 otherwise
	 */
	private static int printNodes(BTNode node, BTNode target, int k) {
		if (node == null)
			return -1;

		if (node == target) {
			printDistantNodes(node, k);
			return 0;
		}

		// Process left subtree
		int distance = printNodes(node.left, target, k);
		if (distance != -1) {
			if (distance + 1 == k)
				System.out.print(node.getData() + " ");
			else
				printDistantNodes(node.right, k - distance - 2); // Right child is 2 edges away from left

			return 1 + distance;
		}

		// Process right subtree
		distance = printNodes(node.right, target, k);
		if (distance != -1) {
			if (distance + 1 == k)
				System.out.print(node.getData() + " ");
			else
				printDistantNodes(node.left, k - distance - 2); // Left child is 2 edges away from right

			return 1 + distance;
		}

		return -1;
	}
    
    /**
     * Prints the nodes down to the given node with the given distance.
     * 
     * @param node
     * 		current subtree
     * @param k
     * 		distance of nodes
     */
	private static void printDistantNodes(BTNode node, int k) {
		if (node == null || k < 0)
			return;

		if (k == 0) {
			System.out.print(node.getData() + " ");
			return;
		}

		printDistantNodes(node.left, k - 1);
		printDistantNodes(node.right, k - 1);
	}
    
	/**
	 * Given a binary tree and a node value, this method finds the closest node to the given node value.
	 * The closest node can either be a descendant of given key or can be reached through one of the ancestors.
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param value
	 * 		node value
	 * 
	 * @return
	 * 		the closest node or null if not found
	 */
	static int getClosestNodeDistance(BinaryTree bt, int value) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		BTNode[] ancestors = new BTNode[bt.height()];
		
		return closestDistance(bt.root, value, ancestors, 0);
	}
	
	/**
	 * Returns the closest distance for the given node.
	 * 
	 * @param node
	 * 		current subtree node
	 * @param value
	 * 		corresponding node
	 * @param ancestors
	 * 		keeps track of ancestors of current node
	 * @param index
	 * 		current index of ancestor
	 * 
	 * @return
	 * 		the closest distance
	 */
	private static int closestDistance(BTNode node, int value, BTNode ancestors[], int index) {
		if (node == null)
			return Integer.MAX_VALUE;

		if (node.getData() == value) {
			int distance = distanceDown(node);

			// Update if there is any ancestor's path leads to min distance
			for (int i = index - 1; i >= 0; i--)
				distance = Math.min(distance, index - i + distanceDown(ancestors[i]));

			return distance;
		}

		ancestors[index] = node;

		return Math.min(closestDistance(node.left, value, ancestors, index + 1), closestDistance(node.right, value, ancestors, index + 1));
	}
    
	/**
	 * Find the closest distance down.
	 * 
	 * @param node
	 *    	current subtree node
	 * 
	 * @return 
	 * 		distance of the closest node down
	 */
	private static int distanceDown(BTNode node) {
		if (node == null)
			return Integer.MAX_VALUE;

		if (node.left == null && node.right == null)
			return 0;

		return 1 + Math.min(distanceDown(node.left), distanceDown(node.right));
	}
    
	/**
	 * Given a binary tree and a value, it prints the ancestor node values of the given node.
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param value
	 * 		node value
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	static void printAncestors(BinaryTree bt, int value) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		if (!printAncestors(bt.getRootNode(), value))
			System.out.println("The value " + value + " is not found.");
	}

	/**
	 * Recursively traverse the tree and print all its ancestors.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param value
	 * 		node value to find
	 * 
	 * @return
	 * 		true if node is found with the value, false otherwise
	 */
	private static boolean printAncestors(BTNode node, int value) {
		if (node == null)
			return false;

		if (node.getData() == value)
			return true;

		if (printAncestors(node.left, value) || printAncestors(node.right, value)) {
			System.out.print(node.getData() + " ");
			return true;
		}

		return false;
	}
	
	/**
	 * Given a binary tree and a key, print the ancestors of the given key. The
	 * idea is to use iterative postorder traversal till we reach the
	 * corresponding node.
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param value
	 * 		node value
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	static void printAncestorsIterative(BinaryTree bt, int value) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.getRootNode();
		if (node == null)
			throw new IllegalStateException("Binary tree is empty.");

		Stack<BTNode> stack = new Stack<>();
		boolean found = false;

		while (node != null) {
			while (node != null && node.getData() != value) {
				stack.push(node);
				node = node.left;
			}

			// We found the node
			if (node != null && node.getData() == value) {
				found = true;
				break;
			}

			// Remove node of top which does not have right child
			if (stack.peek().right == null) {
				node = stack.pop();

				// remove right children of top element
				while (!stack.isEmpty() && stack.peek().right == node)
					node = stack.pop();
			}

			node = stack.isEmpty() ? null : stack.peek().right;
		}

		if (found)
			while (!stack.isEmpty())
				System.out.print(stack.pop().getData() + " ");
		else
			System.out.println("There is no node with the value: " + value);
	}
	
	/**
	 * Given a binary tree and two node values, it returns the least common
	 * ancestor node of the two nodes. The least common ancestor between two
	 * nodes defined as the lowest node in the tree that has both nodes as
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
	 *     	if the given binary tree is null
	 */
	static BTNode leastCommonAncestor(BinaryTree bt, int value1, int value2) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		boolean[] exists = new boolean[] { false, false };

		BTNode ancestor = leastCommonAncestor(node, value1, value2, exists);
		if (exists[0] && exists[1])
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
	 * @param exists
	 * 		stores the existence of node
	 * 
	 * @return
	 * 		return the common ancestor node
	 */
	private static BTNode leastCommonAncestor(BTNode node, int value1, int value2, boolean[] exists) {
		if (node == null)
			return null;

		if (node.getData() == value1) {
			exists[0] = true;
			return node;
		}
		
		if (node.getData() == value2) {
			exists[1] = true;
			return node;
		}

		BTNode leftAncestor = leastCommonAncestor(node.left, value1, value2, exists);
		BTNode rightAncestor = leastCommonAncestor(node.right, value1, value2, exists);

		if (leftAncestor != null && rightAncestor != null)
			return node;

		return leftAncestor != null ? leftAncestor : rightAncestor;
	}

	/**
	 * Given two nodes in a binary tree, this method finds and returns the
	 * common ancestor node for the two. This algorithm assumes that the nodes
	 * also has a link to its parent. Time: O(d), d the depth of the deeper node.
	 * 
	 * @param node1
	 * 		first node
	 * @param node2
	 * 		second node
	 * 
	 * @return
	 * 		the first common ancestor node if exists, null otherwise
	 */
	static BTNode leastCommonAncestor(BTNode node1, BTNode node2) {
		int delta = height(node1) - height(node2);
		BTNode first = delta > 0 ? node2 : node1;
		BTNode second = delta > 0 ? node1 : node2;
		second = goNodeUp(second, Math.abs(delta));

		while (first != second && first != null && second != null) {
			// first = first.parent;
			// second = second.parent;
		}

		return null;
	}
	
	/**
	 * Move the deeper node up by delta.
	 * 
	 * @param node
	 * 		deeper node
	 * @param delta
	 * 		number of moves up
	 * 
	 * @return
	 * 		the parent node by delta distant
	 */
	private static BTNode goNodeUp(BTNode node, int delta) {
		while (delta > 0 && node != null) {
			// node= node.parent;
			delta--;
		}
		
		return node;
	}
	
	/**
	 * Given a binary tree and two nodes of the binary tree, this method finds 
	 * and returns the common ancestor node for the two. This algorithm works 
	 * based on assumption that the nodes are not liked to parent node.
	 * Time: O(n)
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param node1
	 * 		first node
	 * @param node2
	 * 		second node
	 * 
	 * @return
	 * 		the first common ancestor node if exists, null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null.
	 */
	static BTNode leastCommonAncestor(BinaryTree bt, BTNode node1, BTNode node2) {
		if (bt == null)
			throw new NullPointerException("Given binary tree cannot be null.");

		BTNode root = bt.root;

		if (!exists(root, node1) || !exists(root, node2))
			return null;

		return leastCommonAncestor(root, node1, node2);
	}
	
	/**
	 * Checks whether the given node existing in the tree.
	 * 
	 * @param root
	 * 		the current subtree
	 * @param node
	 * 		node to check
	 * 
	 * @return
	 * 		true if it exists, false otherwise
	 */
	private static boolean exists(BTNode root, BTNode node) {
		if (root == null)
			return false;
		
		if (root == node)
			return true;
	
		return exists(root.left, node) || exists(root.right, node);
	}
	
	/**
	 * Recursively traverse the tree and find the common ancestor.
	 * 
	 * @param root
	 * 		the current subtree root
	 * @param node1
	 * 		first node
	 * @param node2
	 * 		second node
	 * 
	 * @return
	 * 		the common ancestor if found, null otherwise
	 */
	private static BTNode leastCommonAncestor(BTNode root, BTNode node1, BTNode node2) {
		if (root == null || root == node1 || root == node2)
			return root;

		boolean node1OnLeft = exists(root.left, node1);
		boolean node2OnLeft = exists(root.left, node2);
		// Nodes are on different side
		if (node1OnLeft != node2OnLeft)
			return root;

		BTNode childSide = node1OnLeft ? root.left : root.right;
		
		return leastCommonAncestor(childSide, node1, node2);
	}

	/**
	 * Given a binary tree and two nodes of the binary tree, this method finds 
	 * and returns the common ancestor node for the two. This algorithm works 
	 * based on assumption that the nodes are not liked to parent node.
	 * Optimized version of {@link #leastCommonAncestor(BinaryTree, BTNode, BTNode)}.
	 * Time: O(n)
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param node1
	 * 		first node
	 * @param node2
	 * 		second node
	 * 
	 * @return
	 * 		the first common ancestor node if exists, null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null.
	 */
	static BTNode leastCommonAncestorOpt(BinaryTree bt, BTNode node1, BTNode node2) {
		if (bt == null)
			throw new NullPointerException("Given binary tree cannot be null.");

		// TODO we need to check whether both node1 and node2 exists in the tree before proceeding
		
		AncestorNode ancestorNode = leastCommonAncestorOpt(bt.root, node1, node2);
		if (ancestorNode.isAncestor)
			return ancestorNode.node;

		return null;
	}
	
	/**
	 * Recursively traverse the tree and find the common ancestor.
	 * 
	 * @param root
	 * 		current subtree root
	 * @param node1
	 * 		first node
	 * @param node2
	 * 		second node
	 * 
	 * @return
	 * 		<code>AncestorNode</code> object holding the possible ancestor
	 */
	private static AncestorNode leastCommonAncestorOpt(BTNode root, BTNode node1, BTNode node2) {
		if (root == null)
			return new AncestorNode(null, false);

		if (root == node1 && root == node2)
			return new AncestorNode(root, true);

		AncestorNode ancestorLeft = leastCommonAncestorOpt(root.left, node1, node2);
		if (ancestorLeft.isAncestor)
			return ancestorLeft;

		AncestorNode ancestorRight = leastCommonAncestorOpt(root.right, node1, node2);
		if (ancestorRight.isAncestor)
			return ancestorRight;

		if (ancestorLeft.node != null && ancestorRight.node != null)
			return new AncestorNode(root, true);
		else if (root == node1 || root == node2) {
			// We are currently at node1 or node2
			boolean isAncestor = ancestorLeft.node != null || ancestorRight.node != null;

			return new AncestorNode(root, isAncestor);
		} else
			return new AncestorNode(ancestorLeft.node != null ? ancestorLeft.node : ancestorRight.node, false);
	}

	/**
	 * Represents an ancestor node.
	 * 
	 * @author psajja
	 *
	 */
	private static class AncestorNode {
		public BTNode node;
		public boolean isAncestor;

		/**
		 * Initialize the object.
		 * 
		 * @param node
		 * 		probable ancestor
		 * @param isAncestor
		 * 		indicates whether this is ancestor or not
		 */
		public AncestorNode(BTNode node, boolean isAncestor) {
			this.node = node;
			this.isAncestor = isAncestor;
		}
	}

	/**
	 * Given a binary tree and two node values, it returns the distance between the two nodes.
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param value1
	 * 		first node value
	 * @param value2
	 * 		second node value
	 * 
	 * @return
	 * 		the distance between the nodes, -1 otherwise
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	static int distance(BinaryTree bt, int value1, int value2) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		int[] distances = new int[] { -1, -1 };
		int[] distance = new int[] { 0 };

		BTNode ancestor = distance(node, value1, value2, distances, distance, 1);

		if (distances[0] != -1 && distances[1] != -1)
			return distance[0];

		// value1 is ancestor of value2
		if (distances[0] != -1)
			return findLevel(ancestor, value2, 0);

		// value2 is ancestor of value1
		if (distances[1] != -1)
			return findLevel(ancestor, value1, 0);

		return -1;
	}
	
	/**
	 * Recursively traverse the tree and compute the distance.
	 * 
	 * @param node
	 * 		current subtree node
	 * @param value1
	 * 		first node value
	 * @param value2
	 * 		second node value
	 * @param distances
	 * 		holds left right distances
	 * @param distance
	 * 		computed distance
	 * @param lvl
	 * 		current level
	 * 
	 * @return
	 * 		the common ancestor node
	 */
	private static BTNode distance(BTNode node, int value1, int value2, int[] distances, int[] distance, int lvl) {
		if (node == null)
			return null;

		if (node.getData() == value1) {
			distances[0] = lvl;
			return node;
		}

		if (node.getData() == value2) {
			distances[1] = lvl;
			return node;
		}

		BTNode leftAncestor = distance(node.left, value1, value2, distances, distance, lvl + 1);
		BTNode rightAncestor = distance(node.right, value1, value2, distances, distance, lvl + 1);

		if (leftAncestor != null && rightAncestor != null) {
			distance[0] = distances[0] + distances[1] - 2 * lvl;
			return node;
		}

		return leftAncestor != null ? leftAncestor : rightAncestor;
	}

	/**
	 * Get the level of node given by value.
	 * 
	 * @param node
	 * 		current subtree node
	 * @param value
	 * 		node value
	 * @param level
	 * 		current level
	 * 
	 * @return
	 * 		the level of value, -1 if not exists
	 */
	private static int findLevel(BTNode node, int value, int level) {
		if (node == null)
			return -1;

		if (node.getData() == value)
			return level;

		int lvl = findLevel(node.left, value, level + 1);
		if(lvl != -1)
			return lvl;
		
		return findLevel(node.right, value, level + 1);
	}
	
	/**
	 * Given a binary tree and  a value, it returns the level of the given node.
	 * Root node at level 1.
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param value
	 * 		node value
	 * 
	 * @return
	 * 		level of the node if found, -1 otherwise
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	public static int getLevel(BinaryTree bt, int value) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		return getLevel(bt.getRootNode(), value, 1);
	}

	/**
	 * Recursively traverse the tree and find the node level.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param value
	 * 		node value
	 * @param level
	 * 		current level
	 * 
	 * @return
	 * 		level of the node if found, -1 otherwise
	 */
	private static int getLevel(BTNode node, int value, int level) {

		if (node == null)
	        return -1;
	 
	    if (node.getData() == value)
	        return level;
	 
	    int downlevel = getLevel(node.left, value, level+1);
	    if (downlevel != -1)
	        return downlevel;
	 
	    return getLevel(node.right, value, level+1);
	}
	
	/**
	 * Given a binary tree this method checks whether all the leaves are at the same level or not.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if the leaves are at same level, false otherwise
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	public static boolean areLeavesAtSameLevel(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		if (node == null)
			return true;

		return areLeavesAtSameLevel(bt.getRootNode(), height(node), 1);
	}
	
	/**
	 * Recursively check leaves are at the same level.
	 * 
	 * @param node
	 * 		current node
	 * @param level
	 * 		max level
	 * @param currentLevel
	 * 		current level
	 * 
	 * @return
	 * 		true if leaves are at the same level, false otherwise
	 */
	private static boolean areLeavesAtSameLevel(BTNode node, int level, int currentLevel) {
		if (node == null)
			return true;
		
		if (node.left == null && node.right == null && level != currentLevel)
			return false;

		return areLeavesAtSameLevel(node.left, level, currentLevel + 1)
				&& areLeavesAtSameLevel(node.right, level, currentLevel + 1);
	}

	/**
	 * Given a binary tree and two nodes, it checks whether the nodes are
	 * cousins. Cousins are those which are at the same level and do not share
	 * same parent.
	 * 
	 * @param bt
	 *     	given binary tree
	 * @param node1
	 * 		first node
	 * @param node2
	 * 		second node
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null or any given node is null
	 */
	static boolean areCousins(BinaryTree bt, BTNode node1, BTNode node2) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		if (node1 == null || node2 == null)
			throw new NullPointerException("Tree node(s) should not be null");

		BTNode node = bt.root;
		if (node == null)
			return false;

		return level(node, node1, 1) == level(node, node2, 1) && !areSibling(node, node1, node2);
	}
	
	/**
	 * Recursively find the level of the given node.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param target
	 * 		node to find
	 * @param level
	 * 		current level
	 * 
	 * @return
	 * 		level of the target node
	 */
	private static int level(BTNode node, BTNode target, int level) {
		if (node == null)
            return -1;
 
        if (node == target)
            return level;
 
        int lvl = level(node.left, target, level + 1);
        if (lvl != -1)
            return lvl;
 
        return level(node.right, target, level + 1);
	}

	/**
	 * Check whether the given two nodes are siblings.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param node1
	 * 		first sibling
	 * @param node2
	 * 		second sibling
	 * 
	 * @return
	 * 		true if they are siblings, false otherwise
	 */
	private static boolean areSibling(BTNode node, BTNode node1, BTNode node2) {
		if (node == null)
			return false;

		return node.left == node1 && node.right == node2 || node.left == node2 && node.right == node1
				|| areSibling(node.left, node1, node2) || areSibling(node.right, node1, node2);
	}

	/**
	 * Given a binary tree, it checks whether the tree is sum tree. At each
	 * node, the node value equal to the sum of its left and right subtree.
	 * Time: O(n^2)
	 * 
	 * @param bt
	 *    	given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	public static boolean isSumTree(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		return isSumTree(bt.getRootNode());
	}

	/**
	 * Recursively check each node for sum tree property.
	 * 
	 * @param node
	 * 		current root
	 * 
	 * @return
	 * 		true if the subtree is sum tree, false otherwise
	 */
	private static boolean isSumTree(BTNode node) {
		int leftSum, rightSum;

		if ((node == null) || (node.left == null && node.right == null))
			return true;

		leftSum = sumTree(node.left);
		rightSum = sumTree(node.right);

		if (node.getData() == leftSum + rightSum && isSumTree(node.left) && isSumTree(node.right))
			return true;

		return false;
	}
	
	/**
	 * Compute the current subtree sum.
	 * 
	 * @param node
	 * 		current subtree root
	 * 
	 * @return
	 * 		sum of the nodes in subtree
	 */
	private static int sumTree(BTNode node) {
		if (node == null)
			return 0;

		return sumTree(node.left) + node.getData() + sumTree(node.right);
	}
	
	/**
	 * Given a binary tree, it checks whether the tree is sum tree. An optimized
	 * version for {@link #isSumTree(BinaryTree)}. 1) If the current node is a
	 * leaf node then sum of subtree rooted at this node is equal to itself. 
	 * 2) If the current node is a non-leaf node then sum of subtree rooted
	 * at this node must be equal to twice the value of this node.
	 * Time: O(n)
	 * 
	 * @param bt
	 *   	given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	public static boolean isSumTreeOptimized(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		return isSumTreeOptimized(bt.getRootNode());
	}
	
	/**
	 * Recursively check each node for sum tree property.
	 * 
	 * @param node
	 * 		current root
	 * 
	 * @return
	 * 		true if the subtree is sum tree, false otherwise
	 */
	private static boolean isSumTreeOptimized(BTNode node) {
		int leftSum;
		int rightSum;

		if (node == null || isLeaf(node))
			return true;

		if (isSumTree(node.left) && isSumTree(node.right)) {
			if (node.left == null)
				leftSum = 0;
			else if (isLeaf(node.left))
				leftSum = node.left.getData();
			else
				leftSum = 2 * node.left.getData();

			if (node.right == null)
				rightSum = 0;
			else if (isLeaf(node.right))
				rightSum = node.right.getData();
			else
				rightSum = 2 * node.right.getData();

			if (node.getData() == rightSum + leftSum)
				return true;
			else
				return false;
		}

		return false;
	}
	
	/**
	 * Given a binary tree it converts the tree to a special sum tree which satisfies
	 * 1. Each node's value is the sum of the left and right sub trees in its original tree
	 * 2. Value in leaves will be zero.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 * 		if the given binary tree is null
	 */
	static void convertToSumTree(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode root = bt.root;
		if (root == null)
			return;

		convertToSumTree(root);
	}
	
	/**
	 * Recursively compute the sum tree.
	 * 
	 * @param node
	 * 		current root node
	 * 
	 * @return
	 * 		computed sum
	 */
	private static int convertToSumTree(BTNode node) {
		if (node == null)
			return 0;

		int oldData = node.getData();

		node.setData(convertToSumTree(node.left) + convertToSumTree(node.right));

		return node.getData() + oldData;
	}
	
	/**
	 * Given a binary tree, it returns the sum of paths. The path is the integer formed by appending each digit in the node while computing the path.
	 * Assumption: the node values are single digits.
	 * 
	 * @param bt
	 * 		binary tree
	 * 
	 * @return
	 * 		the path sum
	 */
	static int sumPaths(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		
		return sumPaths(node, 0);
	}
	
	/**
	 * Recursively traverse the tree and compute the path sum.
	 * 
	 * @param node
	 * 		current node
	 * @param value
	 * 		value till now
	 * 
	 * @return
	 * 		computed sum
	 */
	private static int sumPaths(BTNode node, int value) {
		if (node == null)
			return 0;

		value = value * 10 + node.getData();

		if (node.left == null && node.right == null)
			return value;

		return sumPaths(node.left, value) + sumPaths(node.right, value);
	}

	/**
	 * Check whether the given node is a leaf.
	 * 
	 * @param node
	 * 		node to check
	 * 
	 * @return
	 * 		true if it is leaf, false otherwise
	 */
	private static boolean isLeaf(BTNode node) {
		if(node == null)
			return false;
		
		if (node.left == null && node.right == null)
			return true;
		
		return false;
	}
	
	/**
	 * Given two binary trees, check if the second tree is a subtree of the first one. 
	 * This is based on building unique pre-order traversal and uses special value 
	 * for null nodes while constructing the pre-order strings.
	 * Time: O(m + n), Space: O(m + n) 
	 * 
	 * @param bt1
	 * 		first binary tree
	 * @param bt2
	 * 		second binary tree
	 * 
	 * @return
	 * 		true if second is a subtree of first
	 * 
	 * @throws NullPointerException
	 * 		if any of the given tree is null
	 */
	static boolean isSubTree0(BinaryTree bt1, BinaryTree bt2) {
		if (bt1 == null || bt2 == null)
			throw new NullPointerException("Binary tree should not be null");
	
		StringBuilder stringl = new StringBuilder();
		StringBuilder string2 = new StringBuilder();

		getPreorderString(bt1.root, stringl);
		getPreorderString(bt2.root, string2);

		return stringl.indexOf(string2.toString()) != -1;
	}
	
	/**
	 * Traverse the tree in pre-order and construct the pre-order string.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param sb
	 * 		buffer to add nodes
	 */
	private static void getPreorderString(BTNode node, StringBuilder sb) {
		if (node == null) {
			sb.append("$");
			return;
		}

		sb.append(node.getData() + ",");
		getPreorderString(node.left, sb);
		getPreorderString(node.right, sb);
	}

	/**
	 * Given two binary trees, check if the second tree is a subtree of the first one. 
	 * Time: O(m*n) -> O ( n + k*m), K is the number of occurrences of bt2's root in bt1
	 * 
	 * @param bt1
	 * 		first binary tree
	 * @param bt2
	 * 		second binary tree
	 * 
	 * @return
	 * 		true if second is a subtree of first
	 * 
	 * @throws NullPointerException
	 * 		if any of the given tree is null
	 */
	static boolean isSubTree(BinaryTree bt1, BinaryTree bt2) {
		if (bt1 == null || bt2 == null)
			throw new NullPointerException("Binary tree should not be null");

		return isSubTree(bt1.getRootNode(), bt2.getRootNode());
	}
	
	/**
	 * Recursively check corresponding subtrees of each tree.
	 * 
	 * @param node1
	 * 		first subtree
	 * @param node2
	 * 		second subtree
	 * 
	 * @return
	 * 		true if second is subtree, false otherwise
	 */
	private static boolean isSubTree(BTNode node1, BTNode node2) {
		if (node2 == null)
			return true;

		if (node1 == null)
			return false;

		if (areIdentical(node1, node2))
			return true;

		return isSubTree(node1.left, node2) || isSubTree(node1.right, node2);
	}
	
	/**
	 * Given two binary trees, check if the second tree is a subtree of the
	 * first one. Optimized version of {@link #isSubTree(BinaryTree, BinaryTree)}. 
	 * Tree S is a subtree of T if both inorder and preorder traversals of 
	 * S are sub-traversals of inorder and preorder of T respectively. 
	 * Time: O(n), Space: O(n)
	 * 
	 * @param bt1
	 *    	first binary tree
	 * @param bt2
	 *    	second binary tree
	 * 
	 * @return 
	 * 		true if second is a subtree of first
	 */
	public static boolean isSubTreeOptimized(BinaryTree bt1, BinaryTree bt2) {
		if (bt1 == null || bt2 == null)
			throw new NullPointerException("Binary tree should not be null");

		return isSubTreeOptimized(bt1.getRootNode(), bt2.getRootNode());
	}
	
	/**
	 * It checks for subtree by computing inorder and preorder traversals of each tree.
	 * 
	 * @param node1
	 * 		first subtree
	 * @param node2
	 * 		second subtree
	 * 
	 * @return
	 * 		true if second is subtree, false otherwise
	 */
	private static boolean isSubTreeOptimized(BTNode node1, BTNode node2) {
		if (node2 == null)
			return true;

		if (node1 == null)
			return false;

		// TODO, use StringBuilder instead
		String inorder1 = getInorder(node1, "");
		String inorder2 = getInorder(node2, "");

		if (inorder1.indexOf(inorder2) == -1)
			return false;

		String preorder1 = getPreorder(node1, "");
		String preorder2 = getPreorder(node2, "");

		return preorder1.indexOf(preorder2) != -1;
	}

	/**
	 * Get the inorder string from the given node.
	 * 
	 * @param node
	 * 		the current node
	 * @param inorder
	 * 		current inorder
	 * 
	 * @return
	 * 		inorder string
	 */
	private static String getInorder(BTNode node, String inorder) {
		
		if (node == null)
			return inorder;

		String inorderLeft = getInorder(node.left, inorder);
		inorderLeft += " " + node.getData();
	
		return getInorder(node.right, inorderLeft);

	}
	
	/**
	 * Get the preorder string from the given node.
	 * 
	 * @param node
	 * 		the current node
	 * @param inorder
	 * 		current preorder
	 * 
	 * @return
	 * 		preorder string
	 */
	private static String getPreorder(BTNode node, String preorder) {
		if (node == null)
			return preorder;

		preorder += " " + node.getData();
		String preorderLeft = getPreorder(node.left, preorder);

		return getPreorder(node.right, preorderLeft);
	}
	
	/**
	 * Given a binary tree it prints the vertical sum of nodes, where the nodes
	 * are are at same horizontal distance. The horizontal distance of root is
	 * at zero. When we move right we increment the distance by one and when we
	 * move left we decrement the distance by one.
	 * 
	 * @param bt
	 *    	given binary tree
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	static void printVerticalSum(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		computeVerticalSum(bt.getRootNode(), map, 0);
		System.out.println(map.values());
	}
	
	/**
	 * Recursively compute the vertical sum.
	 * 
	 * @param node
	 * 		current node
	 * @param map
	 * 		map holding the sum data
	 * @param distance
	 * 		current distance
	 */
	private static void computeVerticalSum(BTNode node, Map<Integer, Integer> map, int distance) {
		if (node == null)
			return;

		computeVerticalSum(node.left, map, distance - 1);

		int prevSum = (map.get(distance) == null) ? 0 : map.get(distance);
		map.put(distance, prevSum + node.getData());

		computeVerticalSum(node.right, map, distance + 1);
	}
	
	/**
	 * Given a binary tree it prints the nodes in vertical traversal. In
	 * vertical order, root is at level 0 and when we move left it decrements
	 * the vertical level by 1 and when we move to the right we increment the
	 * level by 1. Time: O(n^2)
	 * 
	 * @param bt
	 *    	given binary tree
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	static void printVerticalTraversal(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		int[] levels = new int[] { 0, 0 };
	
		findLevels(node, levels, 0);

		// From min to max level
		for (int level = levels[0]; level <= levels[1]; level++) {
			printVerticalTraversal(node, level, 0);
			System.out.println();
		}
	}
	
	/**
	 * Find the min and max levels in vertical order.
	 * 
	 * @param node
	 * 		current subtree node
	 * @param levels
	 * 		holds min max levels
	 * @param level
	 * 		current level
	 */
	private static void findLevels(BTNode node, int[] levels, int level) {
		if (node == null)
			return;

		if (level < levels[0])
			levels[0] = level;
		else if (level > levels[1])
			levels[1] = level;

		findLevels(node.left, levels, level - 1);
		findLevels(node.right, levels, level + 1);
	}

	/**
	 * Print the node for the given level.
	 * 
	 * @param node
	 * 		current subtree node
	 * @param expectedLevel
	 * 		expected level for the node to print
	 * @param level
	 * 		current level
	 */
	private static void printVerticalTraversal(BTNode node, int expectedLevel, int level) {
		if (node == null)
			return;

		if (level == expectedLevel)
			System.out.print(node.getData() + " ");

		printVerticalTraversal(node.left, expectedLevel, level - 1);
		printVerticalTraversal(node.right, expectedLevel, level + 1);
	}
	
	/**
	 * Given a binary tree it prints the nodes in vertical traversal. An
	 * optimized version of {@link #printVerticalTraversal(BinaryTree)}.
	 * Time: O(n), Space: O(n)
	 * 
	 * @param bt
	 *    	given binary tree
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	static void printVerticalTraversalOptimal(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		computeVerticalNodes(bt.getRootNode(), map, 0);
		
		for(Entry<Integer, List<Integer>> entry : map.entrySet()) {
			System.out.println("Level: " + entry.getKey());
			for(Integer value : entry.getValue())
				System.out.print(value + " ");
			System.out.println();
		}
	}
	
	/**
	 * Recursively compute the vertical nodes.
	 * 
	 * @param node
	 * 		current node
	 * @param map
	 * 		map holding the level entries
	 * @param distance
	 * 		current distance
	 */
	private static void computeVerticalNodes(BTNode node, Map<Integer, List<Integer>> map, int distance) {
		if (node == null)
			return;

		computeVerticalNodes(node.left, map, distance - 1);

		List<Integer> entries = map.get(distance);
		if (entries == null)
			entries = new ArrayList<>();
		entries.add(node.getData());
		map.put(distance, entries);

		computeVerticalNodes(node.right, map, distance + 1);
	}
	
	/** 
	 * Given a binary tree, this method prints the max sum in its path from root to leaf.
	 * It prints the path from leaf to root.
	 * 
	 * @param bt
	 *    	given binary tree
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	static void printMaxSumPath(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		BTNode root = bt.root;
        if (root == null)
            return;
 
        int[] max = new int[]{0};
        BTNode targetLeaf = null;
        
        targetLeaf = getMaxSumPathLeaf(root, max, 0);
 
        System.out.println("Maximum sum in its path: " + max[0]);
        
        printMaxSumPath(root, targetLeaf);
	}

	/**
	 * Get the leaf node that is on the max sum path.
	 * 
	 * @param node
	 * 		current root node
	 * @param max
	 * 		holds the max computed
	 * @param currentMax
	 * 		current max
	 * 
	 * @return
	 * 		the leaf node in its max sum path
	 */
	private static BTNode getMaxSumPathLeaf(BTNode node, int[] max, int currentMax) {
		if (node == null)
			return null;

		currentMax = currentMax + node.getData();

		if (node.left == null && node.right == null) {
			if (currentMax > max[0]) {
				max[0] = currentMax;
				return node;
			}
		}

		BTNode leftLeaf = null;
		BTNode rightLeaf = null;

		int leftMax = 0;

		leftLeaf = getMaxSumPathLeaf(node.left, max, currentMax);
		leftMax = max[0];
		rightLeaf = getMaxSumPathLeaf(node.right, max, currentMax);

		if (leftMax >= max[0])
			return leftLeaf;

		return rightLeaf;
	}

	/**
	 * Print the max sum path nodes.
	 * 
	 * @param root
	 * 		root node
	 * @param targetLeaf
	 * 		target leaf node
	 */
	private static boolean printMaxSumPath(BTNode root, BTNode targetLeaf) {
		if (root == null)
			return false;

		if (root == targetLeaf || printMaxSumPath(root.left, targetLeaf) || printMaxSumPath(root.right, targetLeaf)) {
			System.out.print(root.getData() + " ");
			return true;
		}

		return false;
	}
	
	/**
	 * Given a binary tree, this method returns the maximum path sum between two leaf nodes.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 * @throws IllegalStateException
	 *  	if there is no such path sum exists because of tree
	 */
	public static int maxPathSum(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		int[] max = new int[] { Integer.MIN_VALUE };

		maxPathSum(node, max);

		if (max[0] == Integer.MIN_VALUE)
			throw new IllegalStateException("There is no such path exists.");

		return max[0];
	}
	
	/**
	 * Recursively compute max path sum.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param max
	 * 		holds max path some between leaves
	 * 
	 * @return
	 * 		max sum root to leaf node
	 */
	private static int maxPathSum(BTNode node, int[] max) {
		if (node == null)
			return 0;
	
		if (node.left == null && node.right == null)
			return node.getData();

		int leftSum = maxPathSum(node.left, max);
		int rightSum = maxPathSum(node.right, max);

		if (node.left != null && node.right != null) {
			max[0] = Math.max(max[0], leftSum + rightSum + node.getData());

			return Math.max(leftSum, rightSum) + node.getData();
		}

		return node.left == null ? rightSum + node.getData() : leftSum + node.getData();
	}
	
	/**
	 * Given a binary tree, this method returns the maximum path sum
	 * where the nodes need not be leaves.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 * @throws IllegalStateException
	 *  	if there is no such path sum exists because of tree
	 */
	public static int maxPathSum1(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		
		int[] sum = new int[] { 0 };
		
        maxPathSum1(node, sum);
        
        return sum[0];
	}
	
	/**
	 * Recursively find the max path sum.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param sum
	 * 		max path sum till now
	 * 
	 * @return
	 * 		maximum sum path
	 */
	private static int maxPathSum1(BTNode node, int[] sum) {
		if (node == null)
			return 0;

		int leftSum = maxPathSum1(node.left, sum);
		int rightSum = maxPathSum1(node.right, sum);

		// One of the path (left or right)
		int maxHere = Math.max(Math.max(leftSum, rightSum) + node.getData(), node.getData());

		// No possibility of including its ancestors in the path, E.g, node is root
		int maxRoot = Math.max(maxHere, leftSum + rightSum + node.getData());

		sum[0] = Math.max(sum[0], maxRoot);

		return maxHere;
	}
	
	/**
	 * Given a binary tree it computes the sum of left leaves in the tree.
	 * It recursively traverses the tree to find all the left leaf nodes.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		sum of all left leaves
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	public static int getLeftLeaveSum(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");	
		
		return getLeftLeaveSum(bt.root);
	}
	
	/**
	 * Recursively compute the leaves sum.
	 * 
	 * @param node
	 * 		current subtree root
	 * 
	 * @return
	 * 		sum of left leaves
	 */
	private static int getLeftLeaveSum(BTNode node) {
		if (node == null)
			return 0;

		int sum = 0;

		if (isLeaf(node.left))
			sum += node.left.getData();
		else
			sum += getLeftLeaveSum(node.left);

		sum += getLeftLeaveSum(node.right);

		return sum;
	}

	/**
	 * Given a binary tree it adds the left sum to each node.
	 * It traverses the tree for each node it adds the left subtree sum.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	static void addLeftSum(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");	
		
		BTNode node = bt.root;
		if (node == null)
			return;
		
		addLeftSum(node);
	}
	
	/**
	 * Recursively update the left sum for each node.
	 * 
	 * @param node
	 * 		current subtree root
	 * 
	 * @return
	 * 		the subtree sum
	 */
	private static int addLeftSum(BTNode node) {
		if (node == null)
			return 0;

		if (node.left == null && node.right == null)
			return node.getData();

		int leftSum = addLeftSum(node.left);
		int rightSum = addLeftSum(node.right);

		node.setData(node.getData() + leftSum);

		return node.getData() + rightSum;
	}
	
	/**
	 * Given a binary tree it finds the multiplication of sum of leaf nodes in each level.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		multiplication of level sum
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	static int getMultiplicaitonOfLevelSum(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");

		BTNode node = bt.root;
		if (node == null)
			return 0;

		int result = 1;
		Queue<BTNode> queue = new LinkedList<>();
		queue.add(node);

		while (!queue.isEmpty()) {

			int nodeCount = queue.size();

			int levelSum = 0;
			boolean hasLeaf = false;

			while (nodeCount > 0) {
				BTNode current;
				current = queue.remove();

				if (isLeaf(current)) {
					hasLeaf = true;
					levelSum += current.getData();
				}

				if (current.left != null)
					queue.add(current.left);

				if (current.right != null)
					queue.add(current.right);

				nodeCount--;
			}

			if (hasLeaf) {
				result *= levelSum;
			}
		}

		return result;
	}
	
	/**
	 * Checks whether the given binary tree is complete or not. A binary tree
	 * with n levels is complete if all levels except possibly the last are
	 * completely full, and the last level has all its nodes to the left side.
	 * 
	 * @param bt
	 *    	given binary tree
	 * 
	 * @return
	 * 		true if it is complete binary tree, false otherwise
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	public static boolean isCompleteBinaryTree(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");
	
		BTNode root = bt.getRootNode();
		if (root == null)
			return true;

		Queue<BTNode> queue = new LinkedList<>();
		queue.add(root);

		boolean flag = false;

		while (!queue.isEmpty()) {
			BTNode current = queue.remove();
			if (current.left != null) {
				if (flag == true)
					return false;
				queue.add(current.left);
			} else
				flag = true;

			if (current.right != null) {
				if (flag == true)
					return false;
				queue.add(current.right);
			} else
				flag = true;
		}

		return true;
	}
	
	/**
	 * Checks whether the given binary tree is complete or not. If we represent
	 * a binary tree in an array, if the parent node is at index 'i', the left
	 * child is at index of '2*i + 1' and the right child is assigned an index
	 * of '2*i + 2. Recursive version of {@link #isCompleteBinaryTree(BinaryTree)}.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if it is complete binary tree, false otherwise
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	static boolean isCompleteBinaryTreeRecursive(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");
		BTNode root = bt.getRootNode();

		return isCompleteBinaryTreeRecursive(root, 0, bt.size());
	}
	
	/**
	 * Recursively check whether the current tree is binary tree. 
	 * If index goes beyond the number of nodes, it is not complete binary tree.
	 * 
	 * @param node
	 * 		current node to verify
	 * @param index
	 * 		current index
	 * @param size
	 * 		total no of nodes
	 * 
	 * @return
	 * 		true if it is complete binary tree, false otherwise
	 */
	private static boolean isCompleteBinaryTreeRecursive(BTNode node, int index, int size) {
		if (node == null)
			return true;

		if (index >= size)
			return false;

		return (isCompleteBinaryTreeRecursive(node.left, 2 * index + 1, size)
				&& isCompleteBinaryTreeRecursive(node.right, 2 * index + 2, size));
	}
	
	/** 
	 * Given a binary tree check whether it is a full binary tree or not.
	 * A full binary tree is a binary tree where each node has either zero or two children.
	 * 
	 * @param bt
	 *    	given binary tree
	 * 
	 * @return
	 * 		true if it is full binary tree, false otherwise
	 * 
	 * @throws NullPointerException
	 *    	if the given binary tree is null
	 */
	public static boolean isFullBinaryTree(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");
	
		return isFullBinaryTree(bt.root);
	 }
	
	/**
	 * Recursively check for full binary tree.
	 * 
	 * @param node
	 * 		current subtree root
	 * 
	 * @return
	 * 		true if it is full binary tree, false otherwise
	 */
	private static boolean isFullBinaryTree(BTNode node) {
		if (node == null)
			return true;

		if (node.left == null && node.right == null)
			return true;

		if ((node.left != null) && (node.right != null))
			return isFullBinaryTree(node.left) && isFullBinaryTree(node.right);

		return false;
	}
	
	/**
	 * Given a binary tree, it prints the boundary nodes
	 * in counter-clock wise starting from root.
	 *  Time: O(n)
	 * 
	 * @param bt
	 *     	given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	static void printBoundaryNodes(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;

		if (node != null) {
			System.out.print(node.getData() + " ");

			// Left boundary
			printBoundaryLeft(node.left);

			printLeaves(node.left);
			printLeaves(node.right);

			// Right boundary
			printBoundaryRight(node.right);
		}
	}
	
	/**
	 * Print boundary nodes on the left.
	 * 
	 * @param node
	 * 		current node
	 */
	private static void printBoundaryLeft(BTNode node) {
		if (node == null)
			return;

		if (node.left != null) {
			System.out.print(node.getData() + " ");
			printBoundaryLeft(node.left);
		} else if (node.right != null) {
			System.out.print(node.getData() + " ");
			printBoundaryLeft(node.right);
		}
	}

	/**
	 * Print boundary nodes on the right.
	 * 
	 * @param node
	 * 		current node
	 */
	private static void printBoundaryRight(BTNode node) {
		if (node == null)
			return;
	
		if (node.right != null) {
			printBoundaryRight(node.right);
			System.out.print(node.getData() + " ");
		} else if (node.left != null) {
			printBoundaryRight(node.left);
			System.out.print(node.getData() + " ");
		}
	}

	/**
	 * Print leaf nodes.
	 * 
	 * @param node
	 * 		current node
	 */
	private static void printLeaves(BTNode node) {
		if (node == null)
			return;

		printLeaves(node.left);

		if (node.left == null && node.right == null)
			System.out.print(node.getData() + " ");

		printLeaves(node.right);
	}
	
	/**
	 * Given a binary tree, it prints the left view of the tree.
	 * The left view is the view that contains all nodes that are first nodes in each level.
	 * Time: O(n)
	 * 
	 * @param bt
	 *     	given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	static void printLeftView(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		int[] level = new int[] { 0 };

		printLeftView(node, level, 1);
	}
	
	/**
	 * Recursively traverse the tree and print the left most node in each level.
	 * 
	 * @param node
	 * 		current node
	 * @param level
	 * 		single element array contains the previous handled level
	 * @param currentLevel
	 * 		current level
	 */
	private static void printLeftView(BTNode node, int[] level, int currentLevel) {
		if (node == null)
			return;

		// Process and update the handled level
		if (level[0] < currentLevel) {
			System.out.print(" " + node.getData());
			level[0] = currentLevel;
		}

		printLeftView(node.left, level, currentLevel + 1);
		printLeftView(node.right, level, currentLevel + 1);
	}
	
	/**
	 * Given a binary tree, it prints the top view of the tree.
	 * The top view of a binary tree is the set of nodes visible from the top.
	 * A node is a top node if it is the first node in its horizontal distance.
	 * Time: O(n)
	 * 
	 * @param bt
	 *     	given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	static void printTopView(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		if (node == null)
			return;

		Set<Integer> distanceSet = new HashSet<>();

		Queue<BTNode> nodeQueue = new LinkedList<>();
		Queue<Integer> distanceQueue = new LinkedList<Integer>();

		nodeQueue.add(node);
		distanceQueue.add(0);

		while (!nodeQueue.isEmpty()) {
			BTNode current = nodeQueue.remove();
			int horizontalDistance = distanceQueue.remove();

			// First node with horizontal distance
			if (!distanceSet.contains(horizontalDistance)) {
				distanceSet.add(horizontalDistance);
				System.out.print(current.getData() + " ");
			}

			if (current.left != null) {
				nodeQueue.add(current.left);
				distanceQueue.add(horizontalDistance - 1);
			}
			if (current.right != null) {
				nodeQueue.add(current.right);
				distanceQueue.add(horizontalDistance + 1);
			}
		}
	}
	
	/**
	 * Given a binary tree, it prints the top view of the tree.
	 * The bottom view of a binary tree is the set of nodes visible from the bottom.
	 * A node a bottom node if it is the bottom most(last) node in its horizontal distance.
	 * Time: O(n)
	 * 
	 * @param bt
	 *     	given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	static void printBottomView(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		BTNode node = bt.root;
		if (node == null)
			return;

		Map<Integer, Integer> distanceMap = new TreeMap<>();

		Queue<BTNode> nodeQueue = new LinkedList<>();
		Queue<Integer> distanceQueue = new LinkedList<Integer>();

		nodeQueue.add(node);
		distanceQueue.add(0);

		while (!nodeQueue.isEmpty()) {
			BTNode temp = nodeQueue.remove();
			int horizontalDistance = distanceQueue.remove();

			distanceMap.put(horizontalDistance, temp.getData());

			if (temp.left != null) {
				nodeQueue.add(temp.left);
				distanceQueue.add(horizontalDistance - 1);
			}
			if (temp.right != null) {
				nodeQueue.add(temp.right);
				distanceQueue.add(horizontalDistance + 1);
			}
		}

		for (Entry<Integer, Integer> distanceEntry : distanceMap.entrySet()) {
			System.out.print(distanceEntry.getValue() + " ");
		}
	}
	
	/**
	 * Given a binary tree, this method removes the half nodes from the tree.
	 * Half nodes have only one child. Uses post-order traversal.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	public static void removeHalfNodes(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		bt.setRoot(removeHalfNodes(bt.root));
	}
	
	/**
	 * Recursively remove the half nodes.
	 * 
	 * @param node
	 * 		current subtree root
	 * 
	 * @return
	 * 		new root after removing
	 */
	private static BTNode removeHalfNodes(BTNode node) {
		if (node == null)
			return null;

		node.left = removeHalfNodes(node.left);
		node.right = removeHalfNodes(node.right);

		if (node.left == null && node.right == null)
			return node;

		if (node.left == null)
			return node.right;

		if (node.right == null)
			return node.left;

		return node;
	}
	
	/**
	 * Given a binary tree and a path sum, this method removes the path nodes 
	 * that does not add up the sum path (complete path).
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param pathSum
	 * 		given path sum
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	public static void pruneTree(BinaryTree bt, int pathSum) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		BTNode node = bt.root;
		int[] sum = new int[] { 0 };
		
		bt.setRoot(pruneTree(node, sum, pathSum));
	}
	
	/**
	 * Recursively prune the tree, which nodes does not sum up the path sum.
	 * 
	 * @param node
	 * 		current root
	 * @param sum
	 * 		sum till now
	 * @param pathSum
	 * 		path sum
	 * 
	 * @return
	 * 		new root after pruning
	 */
	private static BTNode pruneTree(BTNode node, int[] sum, int pathSum) {
		if (node == null)
			return null;

		int[] leftSum = new int[] { sum[0] + node.getData() };
		int[] rightSum = new int[] { sum[0] + node.getData() };

		node.left = pruneTree(node.left, leftSum, pathSum);
		node.right = pruneTree(node.right, rightSum, pathSum);

		sum[0] = Math.max(leftSum[0], rightSum[0]);

		if (sum[0] < pathSum)
			node = null;

		return node;
	}
	
	/**
	 * Given a binary tree and a path length, this method removes the path nodes 
	 * that does not add up the path length (complete path).
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param pathLength
	 * 		given path length
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	public static void prunePaths(BinaryTree bt, int pathLength) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		BTNode node = bt.root;
		
		bt.setRoot(prunePaths(node, 1, pathLength));
	}
	
	/**
	 * Recursively prune the paths which are less then the given length
	 * 
	 * @param node
	 * 		current subtree node
	 * @param level
	 * 		current level
	 * @param pathLength
	 * 		expected length
	 * 
	 * @return
	 * 		the new tree node after pruning
	 */
	private static BTNode prunePaths(BTNode node, int level, int pathLength) {
		if (node == null)
			return null;

		node.left = prunePaths(node.left, level + 1, pathLength);
		node.right = prunePaths(node.right, level + 1, pathLength);

		if (node.left == null && node.right == null && level < pathLength)
			return null;

		return node;
	}

	/**
	 * Given a binary tree, it finds the right node corresponding to the given
	 * node to its right. Right node is the one where it is at the same level of
	 * the given node.
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param value
	 * 		the node value
	 * 
	 * @return
	 * 		the right node, or null
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	static BTNode findNextRight(BinaryTree bt, int value) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
	
		BTNode first = bt.root;
		if (first == null)
			return null;

		Queue<BTNode> nodeQueue = new LinkedList<>();
		Queue<Integer> levelQueue = new LinkedList<Integer>();

		int level = 0;

		nodeQueue.add(first);
		levelQueue.add(level);

		while (!nodeQueue.isEmpty()) {
			BTNode current = nodeQueue.remove();
			level = levelQueue.remove();

			if (current.getData() == value) {
				if (levelQueue.size() == 0 || levelQueue.peek() != level)
					return null;

				return nodeQueue.peek();
			}

			if (current.left != null) {
				nodeQueue.add(current.left);
				levelQueue.add(level + 1);
			}
			if (current.right != null) {
				nodeQueue.add(current.right);
				levelQueue.add(level + 1);
			}
		}

		return null;
	}
	
	/**
	 * Given a binary tree, this method checks whether it is a binary search tree based on current nodes.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if it is binary search tree, false otherwise
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	public static boolean isBinarySearchTree(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		return isBinarySearchTree(bt.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * Recursively check for each subtree for binary search tree.
	 * 
	 * @param node
	 * 		current subtree root
	 * 
	 * @param min
	 * 		min permitted value for this subtree
	 * @param max
	 * 		max permitted value for this subtree
	 * 
	 * @return
	 * 		true if it is binary search tree, false otherwise
	 */
	private static boolean isBinarySearchTree(BTNode node, int min, int max) {
		if (node == null)
			return true;

		if (node.getData() < min || node.getData() > max)
			return false;

		return isBinarySearchTree(node.left, min, node.getData() - 1)
				&& isBinarySearchTree(node.right, node.getData() + 1, max);
	}
	
	/**
	 * Given a binary tree, it converts to a doubly circular linked list. It re
	 * arranges the internal nodes to make them appear as list. If an ordered
	 * binary tree is given it produces a sorted linked list. The 'prev' links
	 * will be stored in the 'left' field and the 'next' links will be stored in
	 * the 'right' field. The transformed list is equal to inorder traversal.
	 * Time: O(n).
	 * 
	 * @see <a href="http://cslibrary.stanford.edu/109/TreeListRecursion.html">
	 *      The Great Tree-List Recursion Problem</a>
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	static void toList(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		BTNode root = bt.root;
		bt.root = toList(root);
	}

	/**
	 * Recursively change the tree to circular list.
	 * 
	 * @param node
	 * 		current tree root
	 * 
	 * @return
	 * 		head of the node after transforming the tree to list
	 */
	private static BTNode toList(BTNode node) {
		if (node == null)
			return null;

		BTNode leftList = toList(node.left);
		BTNode rightList = toList(node.right);

		// Circular link
		node.left = node;
		node.right = node;

		// Append (leftList, node, rightList)
		leftList = append(leftList, node);
		leftList = append(leftList, rightList);

		return leftList;
	}

	/**
     * Appends the two circular doubly linked lists.
     * 
     * @param node1
     * 		first list
     * @param node2
     * 		second list
     * 
     * @return
     * 		resultant circular list
     */
	private static BTNode append(BTNode node1, BTNode node2) {
		if (node1 == null)
			return node2;
		if (node2 == null)
			return node1;

		// Catch the left nodes (prev)
		BTNode firstLeft = node1.left;
		BTNode secondLeft = node2.left;

		// Join the two nodes to make it circularly connected
		join(firstLeft, node2);
		join(secondLeft, node1);

		return node1;
	}
    
	/**
	 * Join the two nodes where second immediately follow the first.
	 * 
	 * @param node1
	 * 		first node
	 * @param node2
	 * 		second node
	 */
	private static void join(BTNode node1, BTNode node2) {
		node1.right = node2;
		node2.left = node1;
	}
	
	/**
	 * Given a binary tree, this method creates a lists of nodes 
	 * where each list contains nodes of each level.
	 * This is based on pre-order traversal.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		list containing level nodes
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	static List<List<BTNode>> getLevelLists(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		List<List<BTNode>> lists = new ArrayList<List<BTNode>>();
		getLevelLists(bt.root, lists, 0);
	
		return lists;
	}

	/**
	 * Recursively traverse the tree and create the level lists.
	 * 
	 * @param root
	 * 		the current root
	 * @param lists	
	 * 		list of linked nodes
	 * @param level
	 * 		current level
	 */
	private static void getLevelLists(BTNode root, List<List<BTNode>> lists, int level) {
		if (root == null)
			return;

		List<BTNode> list = null;
		if (lists.size() == level) {
			list = new LinkedList<BTNode>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}

		list.add(root);
		getLevelLists(root.left, lists, level + 1);
		getLevelLists(root.right, lists, level + 1);
	}
	
	/**
	 * Given a binary tree, this method creates a lists of nodes 
	 * where each list contains nodes of each level.
	 * This is based on breadth first search.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		list containing level nodes
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	static List<List<BTNode>> getLevelLists1(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");

		List<List<BTNode>> lists = new ArrayList<List<BTNode>>();

		List<BTNode> current = new LinkedList<BTNode>();
		if (bt.root != null)
			current.add(bt.root);

		while (current.size() > 0) {
			lists.add(current);
			List<BTNode> parentList = current;
			current = new LinkedList<BTNode>();
			for (BTNode parent : parentList) {
				if (parent.left != null)
					current.add(parent.left);

				if (parent.right != null)
					current.add(parent.right);
			}
		}

		return lists;
	}
	
	/**
	 * Given a root node of a binary tree, this method finds the inorder
	 * successor of the given node. This method assumes that the nodes contains
	 * a link to their parent. This is just a pseudo-code not a working code.
	 * 
	 * @param bt
	 *    	given binary tree
	 * 
	 * @return 
	 * 		inorder successor if exists, null otherwise
	 */
	static BTNode inorderSuccessor(BTNode root) {
		if (root == null)
			return null;

		if (root.right != null)
			return leftMostChild(root.right);

		BTNode current = root;
		BTNode parent = null/* current.parent */;
		// Go up until we're on left instead of right
		while (parent != null && parent.left != current) {
			current = parent;
			// update parent
			// parent = parent.parent;
		}
		
		return parent;
	}
	
	/**
	 * Given a node, it returns the left most node of this node.
	 * 
	 * @param node
	 * 		given node
	 * 
	 * @return
	 * 		the left most node in this subtree
	 */
	private static BTNode leftMostChild(BTNode node) {
		if (node == null)
			return null;

		while (node.left != null)
			node = node.left;

		return node;
	}
	
	/**
	 * Given a binary tree, it finds the number of paths with the given sum.
	 * A path need not start at root, and need not end at leaf. Time:  O(n).
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param pathSum
	 * 		the path sum
	 * 
	 * @return
	 * 		the number of the paths with the given sum
	 * 
	 * @throws NullPointerException
	 *     	if the given binary tree is null
	 */
	public static int countPaths(BinaryTree bt, int pathSum) {
		if (bt == null)
			throw new NullPointerException("Binary tree should not be null");
		
		return countPaths(bt.root, pathSum, 0, new HashMap<Integer, Integer>());
	}

	/**
	 * Recursively traverse the tree and compute the no of paths.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param pathSum
	 * 		actual sum
	 * @param runningSum
	 * 		current running sum
	 * @param hashMap
	 * 		map holding the path sums and corresponding count
	 * 
	 * @return
	 * 		number of paths in the subtree
	 */
	private static int countPaths(BTNode node, int pathSum, int runningSum, HashMap<Integer, Integer> pathCounts) {
		if (node == null)
			return 0;

		// Count paths with sum ending at the current node.
		runningSum += node.getData();
		int sum = runningSum - pathSum;
		int totalPaths = pathCounts.containsKey(sum) ? pathCounts.get(sum) : 0;

		// If runningSum equals targetSum, one additional path starts at root.
		if (runningSum == pathSum)
			totalPaths++;

		incrementHashTable(pathCounts, runningSum, 1); // Increment by 1
		totalPaths += countPaths(node.left, pathSum, runningSum, pathCounts);
		totalPaths += countPaths(node.right, pathSum, runningSum, pathCounts);
		incrementHashTable(pathCounts, runningSum, -1); // Decrement by 1

		return totalPaths;
	}
	
	/**
	 * Update the paths count for the given sum.
	 * 
	 * @param pathCounts
	 * 		holds sum and no.of paths 
	 * @param sum
	 * 		current sum
	 * @param delta
	 * 		delta to be adjusted (count)
	 */
	private static void incrementHashTable(HashMap<Integer, Integer> pathCounts, int sum, int delta) {
		int newCount = (pathCounts.containsKey(sum) ? pathCounts.get(sum) : 0) + delta;
		if (newCount == 0) {
			pathCounts.remove(sum);
		} else {
			pathCounts.put(sum, newCount);
		}
	}
	
	/**
	 * Given a binary tree this method prints the extreme nodes of each level in
	 * alternative order. First prints the root, next level last node followed
	 * by next level first node and so on.
	 * 
	 * @param bt
	 * 		given binary tree
	 */
	static void printExtremeNodes(BinaryTree bt) {
		if (bt == null)
			return;

		BTNode root = bt.getRootNode();
		if (root == null)
			return;

		Queue<BTNode> queue = new LinkedList<>();
		queue.add(root);

		boolean leftCorner = true;
		while (!queue.isEmpty()) {
			int nodeCount = queue.size();

			for (int n = nodeCount; n > 0; n--) {
				BTNode curr = queue.remove();

				if (curr.left != null)
					queue.add(curr.left);

				if (curr.right != null)
					queue.add(curr.right);

				// if leftCorner is true, print leftmost
				if (leftCorner && n == nodeCount)
					System.out.print(curr.getData() + " ");

				// if leftCorner is false, print rightmost
				if (!leftCorner && n == 1)
					System.out.print(curr.getData() + " ");
			}

			// Change corner for next level
			leftCorner = !leftCorner;
		}
	}
	
	/**
	 * Given a binary tree, this method traverses the nodes in diagonal fashion.
	 * 
	 * @param bt
	 * 		given binary tree
	 */
	static void diagonalTraversal(BinaryTree bt) {
		if (bt == null)
			return;

		BTNode root = bt.getRootNode();
		if (root == null)
			return;
		
		Map<Integer, List<BTNode>> diagonalsMap = new HashMap<>();
		diagonalTraversal(bt.root, 0, diagonalsMap);
		
		for(Entry<Integer, List<BTNode>> entry : diagonalsMap.entrySet()) {
			System.out.println("Diagonal Level: " + entry.getKey());
			for(BTNode node : entry.getValue())
				System.out.print(node.getData() + " ");

			System.out.println();
		}
	}

	/**
	 * Recursively traverse the tree and update the diagonals map.
	 * 
	 * @param root
	 * 		current root
	 * @param distance
	 * 		diagonal distance
	 * @param diagonalsMap
	 * 		the map contains the diagonal nodes
	 */
	private static void diagonalTraversal(BTNode root, int distance, Map<Integer, List<BTNode>> diagonalsMap) {
		if (root == null)
	        return;
	 
		List<BTNode> nodeList = diagonalsMap.get(distance);
		if(nodeList == null)
			nodeList = new ArrayList<>();
		
		nodeList.add(root);
		diagonalsMap.put(distance, nodeList);
	 
	    // Left at distance + 1 level
	    diagonalTraversal(root.left, distance + 1, diagonalsMap);
	 
	    // Right at the same distance
	    diagonalTraversal(root.right, distance, diagonalsMap);		
	}
	
	/**
	 * Given a binary tree and a node value, this method prints all the cousins
	 * of the given node. A cousin is at the same level of the node but belongs
	 * to a different parent.
	 * 
	 * @param bt
	 * 		given binary tree
	 * @param nodeValue
	 * 		given node
	 */
	static List<BTNode> getCousinNodes(BinaryTree bt, int nodeValue) {
		if(bt == null)
			return new ArrayList<>();
		
		BTNode node = search(bt, nodeValue);
		if(node == null)
			return new ArrayList<>();
		
		int level = getLevel(bt, nodeValue);
		
		List<BTNode> cousins = new ArrayList<>();
		getCousinNodes(bt.root, node, level, cousins);
		
		return cousins;
	}

	/**
	 * Recursively traverse the tree to the level and update the cousins.
	 * 
	 * @param root
	 * 		current root
	 * @param node
	 * 		given node
	 * @param level
	 * 		level of the cousin
	 * @param cousins
	 * 		cousins
	 */
	private static void getCousinNodes(BTNode root, BTNode node, int level, List<BTNode> cousins) {
		if (root == null || level < 2)
			return;

		// Level should be at least 2 otherwise cousins not possible
		if (level == 2) {
			if (root.left == node || root.right == node)
				return;
			if (root.left != null)
				cousins.add(root.left);
			if (root.right != null)
				cousins.add(root.right);
		} else if (level > 2) {
			getCousinNodes(root.left, node, level - 1, cousins);
			getCousinNodes(root.right, node, level - 1, cousins);
		}
	}
	
	/**
	 * Given a binary tree, this method checks whether removing a node can split
	 * the tree into two trees of equal length.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if the tree can be split into two equal halves, false otherwise
	 */
	public static boolean canSplit(BinaryTree bt) {
		if(bt == null)
			return false;
		
		BTNode root = bt.root;
		int size = bt.size();
		 
        boolean[] canSplit = new boolean[1];
        
        canSplit(root, size, canSplit);
        
		return canSplit[0];
	}

	/**
	 * Recursively checks if it can split above the subtree rooted at the current node.
	 * 
	 * @param root
	 * 		current subtree root
	 * @param size
	 * 		size of the tree.
	 * 
	 * @param canSplit
	 * 		updates to true if we can split at this node, false otherwise
	 */
	private static int canSplit(BTNode root, int size, boolean[] canSplit) {
		if (root == null)
			return 0;

		// Compute sizes of the current subtree
		int sizeHere = canSplit(root.left, size, canSplit) + 1 + canSplit(root.right, size, canSplit);

		// We can split the tree at this node
		if (sizeHere == size - sizeHere)
			canSplit[0] = true;

		// Return current subtree size rooted at this node
		return sizeHere;
	}
	
	/**
	 * Given a binary tree, this method returns the max difference between any
	 * node and its ancestor node.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		max difference of node and its ancestor
	 */
	public static int maxDiffOfNodeAndItsAncestor(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException();

		int[] maxDiff = { Integer.MIN_VALUE };

		maxDiffOfNodeAndItsAncestor(bt.root, maxDiff);

		return maxDiff[0];

	}

	/**
	 * Recursively compute the max difference by traversing the tree in bottom up fashion.
	 * 
	 * @param root
	 * 		current root
	 * @param maxDiff
	 * 		max difference obtained so far
	 */
	private static int maxDiffOfNodeAndItsAncestor(BTNode root, int[] maxDiff) {
		if (root == null)
			return Integer.MAX_VALUE;

		if (root.left == null && root.right == null)
			return root.getData();

		int minOfChildren = Math.min(maxDiffOfNodeAndItsAncestor(root.left, maxDiff), maxDiffOfNodeAndItsAncestor(root.right, maxDiff));

		// Update max value
		maxDiff[0] = Math.max(maxDiff[0], root.getData() - minOfChildren);

		// Max value from the current root
		return Math.min(minOfChildren, root.getData());
	}
	
	/**
	 * Given a perfect binary tree, where each node has exactly two children
	 * except leaf nodes, this method reverse the alternative levels. This 
	 * method does not validate the tree for perfectness.
	 * 
	 * @param bt
	 *            given binary tree
	 * 
	 * @throws NullPointerException
	 *             if the given binary tree is null
	 */
	static void reverseAlternativeLevels(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException("BinaryTree can not be null.");

		BTNode root = bt.root;
		if (root == null)
			return;

		reverseAlternativeLevels(root.left, root.right, 0);
	}

	/**
	 * Recursively traverse the tree and reverse the levels.
	 * 
	 * @param root1
	 * 		first subtree root
	 * @param root2
	 * 		second subtree root
	 * @param level
	 * 		current level
	 */
	private static void reverseAlternativeLevels(BTNode root1, BTNode root2, int level) {
		if (root1 == null || root2 == null)
			return;

		if (level % 2 == 0)
			swap(root1, root2);

		// go deeper in two subtrees
		reverseAlternativeLevels(root1.left, root2.right, level + 1);
		reverseAlternativeLevels(root1.right, root2.left, level + 1);
	}

	/**
	 * Swap two node values.
	 * 
	 * @param root1
	 * 		first node
	 * @param root2
	 * 		second node
	 */
	private static void swap(BTNode root1, BTNode root2) {
		int temp = root1.getData();
		root1.setData(root2.getData());
		root2.setData(temp);
	}
	
	/**
	 * Given a binary tree, this method checks whether the given tree is a max
	 * binary heap. A max binary heap has the following properties
	 * <ul>
	 * <li>It should be a complete tree </li>
 	 * <li>Every node’s value should be greater than or equal to its child.
	 * </ul>
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if the given binary tree is max heap, false otherwise
	 */
	public static boolean isMaxHeap(BinaryTree bt) {
		if(bt == null)
			throw new NullPointerException();
		
		BTNode root = bt.root;
		if(root == null)
			return true;
		
		return isCompleteBinaryTree(bt) && isMaxHeap(root);
	}

	/**
	 * Check the subtree recursively for max heap property.
	 * 
	 * @param root
	 * 		current subtree root
	 * 
	 * @return
	 * 		true if subtree is max heap, false otherwise
	 */
	private static boolean isMaxHeap(BTNode root) {
		if (root == null)
			return true;

		if (root.left == null && root.right == null)
			return true;

		if (root.right == null) {
			// Left most node
			return root.getData() >= root.left.getData();
		} else {
			// Recur on each sub tree
			if (root.getData() >= root.left.getData() && root.getData() >= root.right.getData())
				return isMaxHeap(root.left) && isMaxHeap(root.right);

			return false;
		}
	}
	
	/**
	 * Given a binary tree, this method checks whether the given tree is a min
	 * binary heap. A min binary heap has the following properties
	 * <ul>
	 * <li>It should be a complete tree </li>
 	 * <li>Every node’s value should be less than or equal to its child.
	 * </ul>
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		true if the given binary tree is min heap, false otherwise
	 */
	public static boolean isMinHeap(BinaryTree bt) {
		if(bt == null)
			throw new NullPointerException();
		
		BTNode root = bt.root;
		if(root == null)
			return true;
		
		return isCompleteBinaryTree(bt) && isMinHeap(root);
	}

	/**
	 * Check the subtree recursively for min heap property.
	 * 
	 * @param root
	 * 		current subtree root
	 * 
	 * @return
	 * 		true if subtree is min heap, false otherwise
	 */
	private static boolean isMinHeap(BTNode root) {
		if (root == null)
			return true;

		if (root.left == null && root.right == null)
			return true;

		if (root.right == null) {
			// Left most node
			return root.getData() <= root.left.getData();
		} else {
			// Recur on each sub tree
			if (root.getData() <= root.left.getData() && root.getData() <= root.right.getData())
				return isMinHeap(root.left) && isMinHeap(root.right);

			return false;
		}
	}
	
	/**
	 * Represents a class to hold the max match of identical subtree.
	 * This holds the matched subtree node along with the size of the match.
	 * 
	 * @author psajja
	 *
	 */
	private static class MatchNode {
		BTNode matchedRoot;
		int size;
	}
	
	/**
	 * Given a binary tree, this method computes the size of the largest
	 * subtree having identical left and right subtrees. Traverses the 
	 * nodes in post order to process left and right subtrees.
	 * 
	 * @param bt
	 * 		given binary tree
	 * 
	 * @return
	 * 		the largest subtree size
	 */
	static int getMatchedSubtreeCount(BinaryTree bt) {
		if (bt == null)
			throw new NullPointerException();

		MatchNode match = new MatchNode();

		getMatchedSubtreeCount(bt.root, match, new StringBuilder());

		System.out.println("The max identical subtree can be founded at node rooted: " + match.matchedRoot);

		return match.size;
	}

	/**
	 * Recursively traverse the the left and right subtrees in postorder and find the matching sequences.
	 * 
	 * @param root
	 * 		current root node
	 * @param match
	 * 		holds the current subtree node along with the max size
	 * @param sb
	 * 		buffer to hold the sequence
	 */
	private static int getMatchedSubtreeCount(BTNode root, MatchNode match, StringBuilder sb) {
		if (root == null)
			return 0;

		StringBuilder left = new StringBuilder();
		StringBuilder right = new StringBuilder();

		int leftSize = getMatchedSubtreeCount(root.left, match, left);

		int rightSize = getMatchedSubtreeCount(root.right, match, right);

		int sizeHere = leftSize + rightSize + 1;
		if (left.equals(right)) {
			if (sizeHere > match.size) {
				match.size = sizeHere;
				match.matchedRoot = root;
			}
		}

		sb.append("|").append(left).append("|");
		sb.append("|").append(root.getData()).append("|");
		sb.append("|").append(right).append("|");

		return sizeHere;
	}
	
	/**
	 * Given a singly linked list of <code>org.algos4j.list.LinkedList</code>, 
	 * this method builds a complete binary tree
	 * from list nodes.
	 * 
	 * @param list
	 * 		given linked list
	 * 
	 * @return
	 * 		constructed binary tree;
	 */
	public static BinaryTree toTree(org.algos4j.list.LinkedList list) {
		if (list == null)
			throw new NullPointerException();

		BinaryTree bt = new BinaryTree();
		Node listHead = list.getHead();
		if (listHead == null)
			return bt;

		Queue<BTNode> queue = new java.util.LinkedList<>();
		BTNode treeHead = new BTNode(listHead.getData());
		queue.add(treeHead);

		listHead = listHead.next;
		while (listHead != null) {
			BTNode currentTreeNode = queue.remove();

			BTNode leftChild = new BTNode(listHead.getData());
			queue.add(leftChild);

			BTNode rightChild = null;

			listHead = listHead.next;

			if (listHead != null) {
				rightChild = new BTNode(listHead.getData());
				queue.add(rightChild);
				listHead = listHead.next;
			}

			currentTreeNode.left = leftChild;
			currentTreeNode.right = rightChild;
		}
		bt.root = treeHead;

		return bt;
	}
	
	/**
	 * Builds a binary tree from the given inoder and preorder traversal.
	 * 
	 * @param inorder
	 * 		inorder traversal
	 * @param preorder
	 * 		preorder traversal
	 * 
	 * @return
	 * 		tree constructed from the given traversals
	 */
	public static BinaryTree buildTreeFromInorderPreorder(int[] inorder, int[] preorder) {
		if (inorder == null || preorder == null)
			throw new NullPointerException();

		if (inorder.length != preorder.length)
			throw new IllegalArgumentException();

		int[] preorderIndex = new int[] { 0 };
		BTNode root = buildTreeFromInorderPreorder(inorder, preorder, preorderIndex, 0, inorder.length - 1);
		BinaryTree bt = new BinaryTree();
		bt.root = root;

		return bt;
	}

	/**
	 * Recursively build the tree.
	 * 
	 * @param inorder
	 * 		inorder traversal
	 * @param preorder
	 * 		preorder traversal
	 * @param preorderIndex
	 * 		holds current preorder index
	 * @param inorderStart
	 * 		start index of inorder
	 * @param inorderEnd
	 * 		end index of inorder
	 * 
	 * @return
	 * 		constructed tree
	 */
	private static BTNode buildTreeFromInorderPreorder(int[] inorder, int[] preorder, int[] preorderIndex,
			int inorderStart, int inorderEnd) {
		if (inorderStart > inorderEnd)
			return null;
		
		BTNode node = new BTNode(preorder[preorderIndex[0]++]);

		if (inorderStart == inorderEnd)
			return node;

		int inorderIndex = search(inorder, inorderStart, inorderEnd, node.getData());
		if (inorderIndex == -1)
			throw new IllegalArgumentException("Traversals are not proper..");

		node.left = buildTreeFromInorderPreorder(inorder, preorder, preorderIndex, inorderStart, inorderIndex - 1);
		node.right = buildTreeFromInorderPreorder(inorder, preorder, preorderIndex, inorderIndex + 1, inorderEnd);

		return node;
	}

	/**
	 * Finds the index of data in inorder traversal.
	 * 
	 * @param inorder
	 * 		inorder traversal
	 * @param inorderStart
	 * 		current inorder start index
	 * @param inorderEnd
	 * 		current inorder end index
	 * @param data
	 * 		data item to find
	 * 
	 * @return
	 * 		index of the data item, -1 if not found
	 */
	private static int search(int[] inorder, int inorderStart, int inorderEnd, int data) {
		for (int i = inorderStart; i <= inorderEnd; i++) {
			if (inorder[i] == data)
				return i;
		}

		return -1;
	}
	
	/**
	 * Builds a binary tree from the given inoder and preorder traversal.
	 * 
	 * @param inorder
	 * 		inorder traversal
	 * @param postorder
	 * 		postorder traversal
	 * 
	 * @return
	 * 		tree constructed from the given traversals
	 */
	public static BinaryTree buildTreeFromInorderPostorder(int[] inorder, int[] postorder) {
		if (inorder == null || postorder == null)
			throw new NullPointerException();

		if (inorder.length != postorder.length)
			throw new IllegalArgumentException();

		int[] postorderIndex = new int[] { postorder.length - 1 };
		BTNode root = buildTreeFromInorderPostorder(inorder, postorder, postorderIndex, 0, inorder.length - 1);
		BinaryTree bt = new BinaryTree();
		bt.root = root;

		return bt;
	}

	/**
	 * Recursively build the tree.
	 * 
	 * @param inorder
	 * 		inorder traversal
	 * @param postorder
	 * 		postorder traversal
	 * @param postorderIndex
	 * 		holds current preorder index
	 * @param inorderStart
	 * 		start index of inorder
	 * @param inorderEnd
	 * 		end index of inorder
	 * 
	 * @return
	 * 		constructed tree
	 */
	private static BTNode buildTreeFromInorderPostorder(int[] inorder, int[] postorder, int[] postorderIndex,
			int inorderStart, int inorderEnd) {
		if (inorderStart > inorderEnd)
			return null;

		BTNode node = new BTNode(postorder[postorderIndex[0]--]);

		if (inorderStart == inorderEnd)
			return node;

		int inorderIndex = search(inorder, inorderStart, inorderEnd, node.getData());
		if (inorderIndex == -1)
			throw new IllegalArgumentException("Traversals are not proper..");

		node.right = buildTreeFromInorderPostorder(inorder, postorder, postorderIndex, inorderIndex + 1, inorderEnd);
		node.left = buildTreeFromInorderPostorder(inorder, postorder, postorderIndex, inorderStart, inorderIndex - 1);

		return node;
	}
}