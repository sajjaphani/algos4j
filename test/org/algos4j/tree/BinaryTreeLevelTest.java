package org.algos4j.tree;

/**
 * A test class to test level related utilities on binary tree.
 * 
 * @author psajja
 *
 */
public class BinaryTreeLevelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bt = BinaryTreeUtil.createBinaryTree();
		int level = BinaryTreeUtil.getLevel(bt, 10);
		if(level == -1)
			System.out.println("Element 10 does not found");
		else
			System.out.println("Element 10" + " found at level: " + level);
		
		level = BinaryTreeUtil.getLevel(bt, 7);
		if(level == -1)
			System.out.println("The element 7 does not found");
		else
			System.out.println("Element 7" + " found at level: " + level);
	}
}
