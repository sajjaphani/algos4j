package org.algos4j.tree;

/**
 * A test class to test binary tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		
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
