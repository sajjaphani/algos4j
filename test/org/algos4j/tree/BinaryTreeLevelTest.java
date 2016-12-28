package org.algos4j.tree;

import org.algos4j.tree.BinaryTree.BTNode;

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
		
		System.out.println();
		System.out.println("Diff of odd and even level sum: " + BinaryTreeUtil.getLevelDifference(bt));
		
		System.out.println();
		System.out.println("Deepest odd level depth: " + BinaryTreeUtil.getDeepestOddLevel(bt));
		
		System.out.println();
		System.out.println("Are Leaves at same level: " + BinaryTreeUtil.areLeavesAtSameLevel(bt));
		
		bt.root.left.left.left = new BTNode(Integer.MIN_VALUE);
		
		System.out.println();
		System.out.println("Are Leaves at same level: " + BinaryTreeUtil.areLeavesAtSameLevel(bt));
		
		System.out.println();
		BTNode rightNode = BinaryTreeUtil.findNextRight(bt, 5);
		if( rightNode == null)
			System.out.println("There is no such right node for : " + 5);
		else
			System.out.println("The next right node for 5 is " + rightNode.getData());
	}
}
