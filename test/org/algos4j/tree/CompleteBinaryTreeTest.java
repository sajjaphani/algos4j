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
		System.out.println("Complete binary tree");
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
		System.out.println();
		System.out.println("Checking Complete Binary tree");
		
		boolean isComplete = BinaryTreeUtil.isCompleteBinaryTree(bt);
		if(isComplete)
			System.out.println("Binary tree is complete binary tree");
		else
			System.out.println("Binary tree is not a complete binary tree");
		
		System.out.println("Approach 2 Recursive");
		isComplete = BinaryTreeUtil.isCompleteBinaryTreeRecursive(bt);
		if(isComplete)
			System.out.println("Binary tree is complete binary tree");
		else
			System.out.println("Binary tree is not a complete binary tree");
		
		System.out.println();
		BinaryTree bt1 = BinaryTreeUtil.createBinaryTree();
		System.out.println("Approach 1");
		isComplete = BinaryTreeUtil.isCompleteBinaryTree(bt1);
		if(isComplete)
			System.out.println("Binary tree 1 is complete binary tree");
		else
			System.out.println("Binary tree 1 is not a complete binary tree");
		
		System.out.println("Approach 2 Recursive");
		isComplete = BinaryTreeUtil.isCompleteBinaryTreeRecursive(bt1);
		if(isComplete)
			System.out.println("Binary tree 1 is complete binary tree");
		else
			System.out.println("Binary tree 1 is not a complete binary tree");
	}
}
