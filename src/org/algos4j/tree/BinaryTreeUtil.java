package org.algos4j.tree;

import java.util.LinkedList;
import java.util.Queue;

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
}
