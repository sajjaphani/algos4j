package org.algos4j.tree;

/**
 * A test class to test removing node(s) of a binary tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreeRemoveTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
		System.out.println("Preorder Traversal Before");
		bt.preorder();
		
		System.out.println();
		BinaryTreeUtil.removeHalfNodes(bt);
		System.out.println("Preorder Traversal After");
		bt.preorder();
		
		System.out.println();
		System.out.println("Pruning Tree (based on path sum)");
		System.out.println("Paths before pruning");
		BinaryTreeUtil.printPaths(bt);
		BinaryTreeUtil.pruneTree(bt, 15);
		System.out.println("Paths after pruning");
		BinaryTreeUtil.printPaths(bt);
		
		BinaryTree bt1 = BinaryTreeUtil.createBinaryTree();
		System.out.println();
		System.out.println("Pruning Tree (based on path length)");
		System.out.println("Paths before pruning");
		BinaryTreeUtil.printPaths(bt1);
		BinaryTreeUtil.prunePaths(bt1, 4);
		System.out.println("Paths after pruning");
		BinaryTreeUtil.printPaths(bt1);
	}
}
