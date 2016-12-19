package org.algos4j.tree;

/**
 * A test class to test binary search tree.
 * 
 * @author psajja
 *
 */
public class BinarySearchTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elts = {25, 15, 20, 5, 10, 35, 30, 45};
		BinaryTree bt = BinaryTreeUtil.createBinarySearchTree(elts);
		
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
		
		System.out.println();
		System.out.println("\nDeleting item " + 10);
		bt.delete(10);
		System.out.println("Levelorder");
		bt.levelorder();
		
		System.out.println();
		System.out.println("\nDeleting item " + 35);
		bt.delete(35);
		System.out.println("Levelorder");
		bt.levelorder();
		
		System.out.println();
		System.out.println("\nDeleting item " + 25);
		bt.delete(25);
		System.out.println("Levelorder");
		bt.levelorder();
	}
}
