package org.algos4j.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.algos4j.tree.BinaryTree.BTNode;

/**
 * Holds some of the utility method on Binary trees including binary search tree.
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
	 * Given a binary tree, it computes the diameter (width) of the tree. The
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
	 * Time: O(n^2).
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
			throw new NullPointerException(
					"Input binary tree should not be null");
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

		if (Math.abs(leftHeight[0] - rightHeight[0]) >= 2)
			return false;
		else
			return leftBalanced && rightBalanced;
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
	
}