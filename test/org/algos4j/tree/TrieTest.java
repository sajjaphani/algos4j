package org.algos4j.tree;

/**
 * A test class to test trie.
 * 
 * @author psajja
 *
 */
public class TrieTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("abcd");
		trie.insert("abgl");
		trie.insert("lmno");
		
		trie.print();
		
		System.out.println();
		System.out.println("Contains (lmno): " + trie.contains("lmno"));
		System.out.println("Contains (lmn) prefix: " + trie.contains("lmn", false));
		
		trie.remove("abgl");
		
		System.out.println();
		trie.print();
	}
}
