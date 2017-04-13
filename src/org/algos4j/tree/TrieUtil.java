package org.algos4j.tree;

import org.algos4j.tree.Trie.TrieNode;

/**
 * This class implements some utility methods on Trie data structure.
 * 
 * @author psajja
 *
 */
public class TrieUtil {

	/**
	 * Not-instantiable
	 */
	private TrieUtil() {
	}
	
	/**
	 * Given an array of words this method constructs a trie.
	 * 
	 * @param words
	 *   	given words
	 * 
	 * @return 
	 * 		returns the trie
	 * 
	 * @throws NullPointerException
	 *   	if the array or any of the word in the array is null
	 */
	public static Trie build(String[] words) {
		Trie trie = new Trie();
		for (String word : words)
			trie.insert(word);

		return trie;
	}
	
	/**
	 * Given a trie and a word (prefix) to find, this method returns the number
	 * of words that start with the given word.
	 * 
	 * @param trie
	 * 		given trie
	 * @param word
	 * 		word prefix to find
	 * 
	 * @return
	 * 		count of words, 0 if there are no words with the given prefix
	 * 
	 * @throws NullPointerException
	 *   	if the trie is null
	 */
	public static int count(Trie trie, String word) {
		if(word == null)
			return 0;
		
		return count(trie.getRoot(), word, 0);
	}

	/**
	 * Traverse the nodes and find the count.
	 * 
	 * @param root
	 * 		current trie root
	 * @param word
	 * 		word to find
	 * @param index
	 * 		current character index
	 * 		
	 * @return
	 * 		the count, 0 if there is no prefix with the given word
	 */
	private static int count(TrieNode root, String word, int index) {
		if (index == word.length())
			return root.childCount();

		TrieNode child = root.getChild(word.charAt(index));
		if (child == null)
			return 0;

		return count(child, word, index + 1);
	}
}