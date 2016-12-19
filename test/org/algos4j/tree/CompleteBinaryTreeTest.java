package org.algos4j.tree;

/**
 * A test class to test complete binary tree.
 * 
 * @author psajja
 *
 */
public class CompleteBinaryTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elts = {25, 15, 20, 5, 10, 35, 30, 45};
		BinaryTree bt = BinaryTreeUtil.createCompleteBinaryTree(elts);
		
		System.out.println("Preorder");
		bt.preorder();
		System.out.println();
	
		System.out.println("Postorder");
		bt.postorder();
		System.out.println();
	
		System.out.println("Inorder");
		bt.inorder();
		System.out.println();
	
		System.out.println("Levelorder");
		bt.levelorder();
		
	}
}
