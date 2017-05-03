package org.algos4j.tree;

import java.util.List;

import org.algos4j.tree.GenericTree.GenericNode;

/**
 * This class tests some generic tree functionalities.
 * 
 * @author psajja
 *
 */
public class GenericTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char delimiter = ')';
		
		String serializedString = "ABE)FK)))C)DG)H)I)J)))";
		GenericTree<Character> tree = GenericTreeUtil.deserialize(serializedString, 4, delimiter);
		
		System.out.println("Height: " + tree.height());
		System.out.println("Size: " + tree.size());
		
		System.out.println("Preorder: " + tree.preorder());
		System.out.println("Postorder: " + tree.postorder());
		
		System.out.println();
		System.out.println("All Paths");
		List<List<GenericNode<Character>>> allPaths = tree.getAllPaths();
		for(List<GenericNode<Character>> path : allPaths)
			System.out.println(path);
		
		System.out.println("Longest Path: " + tree.getLongestPath());
	}
}
