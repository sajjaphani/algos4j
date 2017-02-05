package org.algos4j.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * A trie, also called prefix tree (as they can be searched by prefixes) is a
 * search tree. We use a trie as an associative array to store pieces of data
 * that have a key and possibly a value. Acts as a dictionary.
 * 
 * @author psajja
 *
 */
public class Trie {

	private TrieNode root;
	private static int BUFFER_SIZE = 128;
	
	/**
	 * Default constructor.
	 */
	public Trie() {
		root = new TrieNode();
	}
	
	/**
	 * Get the root node.
	 * 
	 * @return
	 * 		the root node
	 */
	public TrieNode getRoot() {
		return root;
	}

	/**
	 * Set the root node
	 * 
	 * @param root
	 * 		the root node
	 */
	void setRoot(TrieNode root) {
		this.root = root;
	}
	
	/**
	 * Inserts the given word into the trie.
	 * 
	 * @param word
	 * 		the word to insert
	 * 
	 * @throws NullPointerException
	 * 		if the given word is null
	 */
	public void insert(String word) {
		if (word == null)
			throw new NullPointerException("Word to insert can't be null.");
		if (word.length() == 0)
			throw new IllegalArgumentException("Word to insert can't be empty");

		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char chr = word.charAt(i);
			TrieNode node = current.getChild(chr);
			if (node == null) {
				node = new TrieNode();
				current.insert(chr, node);
			}
			current = node;
		}
		current.setLeaf(true);
	}
	
	/**
	 * Search for the given word in the trie.
	 * This looks for exact match.
	 * 
	 * @param word
	 * 		word to find
	 * 
	 * @return
	 * 		true if the word is found, false otherwise
	 */
	public boolean contains(String word) {
		return contains(word, true);
	}
	
	/**
	 * Checks whether the given prefix is found in the trie.
	 * 
	 * @param prefix
	 * 		prefix to find
	 * @param whole
	 * 		indicates whether to find whole word or not
	 * 
	 * @return
	 * 		true if the prefix is found, false otherwise
	 */
	public boolean contains(String prefix, boolean whole) {
		if (prefix == null || prefix.length() == 0)
			return false;

		TrieNode current = root;
		for (int i = 0; i < prefix.length(); i++) {
			current = current.getChild(prefix.charAt(i));
			if (current == null) {
				return false;
			}
		}
		
		return !whole || current.isLeaf();
	}
	
	/**
	 * Removes the given word from the trie.
	 * It does nothing if the word is not found in the trie.
	 * 
	 * @param word
	 * 		word to find
	 * 
	 * @return
	 * 		true if the word is removed, false otherwise
	 */
	public boolean remove(String word) {
		if (word == null || word.length() == 0)
			return false;

		return remove(word, root, 0);
	}
	
	/**
	 * Recursively remove the word and the corresponding nodes if they are empty.
	 * 
	 * @param word
	 * 		word to remove
	 * @param current
	 * 		current trie node
	 * @param index
	 * 		current index of the character
	 * 
	 * @return
	 * 		true if the word is removed, false otherwise
	 */
	private boolean remove(String word, TrieNode current, int index) {
		if (index == word.length()) {
			if (!current.isLeaf())
				return false;

			current.setLeaf(false);

			// Any mappings are left in the map?
			return current.childCount() == 0;
		}

		char chr = word.charAt(index);
		TrieNode node = current.getChild(chr);
		if (node == null)
			return false;

		boolean deleteNode = remove(word, node, index + 1);

		// Remove the node from the mapping
		if (deleteNode) {
			current.remove(chr);

			// Any mappings are left in the map?
			return current.childCount() == 0;
		}

		return false;
	}
	
	/**
	 * Prints the current trie.
	 * 
	 */
	void print() {
		System.out.println("Trie{");
		char[] buffer = new char[BUFFER_SIZE];
		print(root, buffer, 0);
		System.out.println("}");
	}
	
	/**
	 * Recursively print the words.
	 * 
	 * @param current
	 * 		current trie node under process
	 * @param buffer
	 * 		buffer holding the prefix
	 * @param index
	 * 		current index of buffer
	 */
	private void print(TrieNode current, char buffer[], int index) {
		if (current != null) {
			if (current.isLeaf())
				System.out.println("\t" + new String(buffer, 0, index));
			
			for (char chr : current.children.keySet()) {
				TrieNode child = current.getChild(chr);
				if (child != null) {
					buffer[index] = chr;
					print(child, buffer, index + 1);
				}
			}
		}
	}
	
	/**
	 * Represents a node in a trie. This representation uses a map. We can
	 * replace the map with an array if we have definite set of chars, indexes
	 * can be obtained based on char to index mapping, e.g, chr - 'a' as index.
	 * 
	 * @author psajja
	 *
	 */
	static class TrieNode {
		private Map<Character, TrieNode> children;
		private char data;
		private boolean isLeaf;
		
		/**
		 * Initialize trie node.
		 */
		public TrieNode() {
			this('\0');
		}
		
		/**
		 * Initialize trie node with the given data.
		 * 
		 * @param data
		 * 		node data
		 */
		public TrieNode(char data) {
			this.children = new HashMap<>();
			this.data = data;
		}
		
		/**
		 * Get the data associated with this node.
		 * 
		 * @return
		 * 		the node data
		 */
		public char getData() {
			return data;
		}
		
		/**
		 * Check whether this is a leaf node.
		 * 
		 * @return
		 * 		true if it is leaf, false otherwise
		 */
		public boolean isLeaf() {
			return isLeaf;
		}
		
		/**
		 * Sets or resets leaf node.
		 * 
		 * @param isLeaf
		 * 		true if it is leaf, false otherwise
		 */
		public void setLeaf(boolean isLeaf) {
			this.isLeaf = isLeaf;
		}
		
		/**
		 * Get the child node for the given char.
		 * 
		 * @param chr
		 * 		character to find in tree
		 * 
		 * @return
		 * 		the node if found, null otherwise
		 */
		public TrieNode getChild(char chr) {
			return children.get(chr);
		}
		
		/**
		 * Insert a trie node with the given data.
		 * 
		 * @param chr
		 * 		current character
		 * @param node
		 * 		the node to insert
		 */
		void insert(char chr, TrieNode node) {
			children.put(chr, node);
		}
		
		/**
		 * Removes the node with the given character.
		 * 
		 * @param chr
		 * 		current character to remove
		 * 
		 * @return
		 * 		true if the character is removed, false otherwise
		 */
		boolean remove(char chr) {
			return children.remove(chr) != null;
		}
		
		/**
		 * Get the current size of the children.
		 * 
		 * @return
		 * 		current child count
		 */
		public int childCount() {
			return children.size();
		}
	}
}