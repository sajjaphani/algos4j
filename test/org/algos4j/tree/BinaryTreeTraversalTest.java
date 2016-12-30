package org.algos4j.tree;

/**
 * A test class to test binary tree traversals, additional methods on traversals.
 * 
 * @author psajja
 *
 */
public class BinaryTreeTraversalTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Binary Tree Traversals");
		System.out.println("Level order traversal");
		BinaryTreeUtil.printLevelOrder(bt);
		
		System.out.println();
		System.out.println("Level order traversal");
		BinaryTreeUtil.printLevelOrderReverse(bt);
		
		System.out.println();
		System.out.println("Inorder Traversal (Iterative)");
		BinaryTreeUtil.inorder(bt);
		
		System.out.println();
		System.out.println("Inorder Traversal (Morris)");
		BinaryTreeUtil.inorderMorris(bt);
		
		System.out.println();
		System.out.println("Preorder Traversal (Iterative)");
		BinaryTreeUtil.preorder(bt);
		
		System.out.println();
		System.out.println("Postorder Traversal (Iterative)");
		BinaryTreeUtil.postorder(bt);
		
		System.out.println();
		System.out.println("Levelorder Traversal (each level in new line)");
		BinaryTreeUtil.printLevelOrderInLines(bt);
		
		System.out.println();
		System.out.println("Levelorder Traversal (Spiral/ ZigZag)");
		BinaryTreeUtil.printLevelOrderSpiral(bt);
		
		System.out.println();
		System.out.println("Print Chosen Levels");
		BinaryTreeUtil.printLevelNodes(bt, 2, 3);
		
		System.out.println();
		System.out.println("Bouondary Traversal ");
		BinaryTreeUtil.printBoundaryNodes(bt);
		
		System.out.println();
		System.out.println("BinaryTree Left View");
		BinaryTreeUtil.printLeftView(bt);
		
		System.out.println();
		System.out.println("BinaryTree Top View");
		BinaryTreeUtil.printTopView(bt);
		
		System.out.println();
		System.out.println("BinaryTree Bottom View");
		BinaryTreeUtil.printBottomView(bt);
		
		System.out.println();
		System.out.println("Printing Vertical sum");
		BinaryTreeUtil.printVerticalSum(bt);
		
		System.out.println();
		System.out.println("Vertical Traversal");
		BinaryTreeUtil.printVerticalTraversal(bt);
		
		System.out.println();
		System.out.println("Vertical Traversal Optimized");
		BinaryTreeUtil.printVerticalTraversalOptimal(bt);
	}
}