package org.algos4j.tree;

import java.util.Iterator;

import org.algos4j.tree.GenericTree.GenericNode;
import org.algos4j.util.StringUtil;

/**
 * Utility class which contains methods related to generic trees.
 * 
 * @author psajja
 *
 */
public class GenericTreeUtil {

	/**
	 * Not-instantiable
	 */
	private GenericTreeUtil() {
	}
	
	/**
	 * Given a serialized string, it constructs the generic tree from the string.
	 * 
	 * @param serializedString
	 * 		given serialized string
	 * @param size
	 * 		max size of each node (arity)
	 * @param delimiter
	 * 		delimiter character in the serialized string to identify a leaf
	 * 
	 * @return
	 * 		generic tree constructed from the string
	 */
	public static GenericTree<Character> deserialize(String serializedString, int size, char delimiter) {
		GenericTree<Character> tree = new GenericTree<Character>();
		if (StringUtil.isNullOrEmpty(serializedString))
			return tree;

		GenericNode<Character> root = deserialize(serializedString, size, delimiter, new int[] { 0 });
		tree.setRoot(root);

		return tree;
	}

	/**
	 * Construct the tree recursively.
	 * 
	 * @param serializedString
	 * 		serialized string
	 * @param size
	 * 		size of arity, max child nodes a node can have
	 * @param delimiter
	 * 		a delimiter character to mark a leaf
	 * @param index
	 * 		current index of the searialized string
	 * 
	 * @return
	 * 		subtree constructed starting at the current index
	 */
	private static GenericNode<Character> deserialize(String serializedString, int size, char delimiter, int[] index) {
		if (index[0] == serializedString.length())
			return null;

		char chr = serializedString.charAt(index[0]);
		if (chr == delimiter)
			return null;

		GenericNode<Character> node = new GenericNode<>(chr);
		for (int i = 0; i < size; i++) {
			index[0] = index[0] + 1;
			GenericNode<Character> child = deserialize(serializedString, size, delimiter, index);
			if (child == null)
				break;

			node.addChild(child);
		}

		return node;
	}
	
	/**
	 * Given a generic tree this method serializes the tree into a string. It
	 * uses the delimiter to mark a leaf node.
	 * 
	 * @param tree
	 * 		given generic tree
	 * @param size
	 * 		max size of children of any node
	 * @param delimiter
	 * 		the delimiter to use to indicates a leaf
	 * 
	 * @return
	 * 		the serialized string
	 */
	public static String serialize(GenericTree<Character> tree, int size, char delimiter) {
		if(tree == null)
			throw new NullPointerException();
		
		GenericNode<Character> root = tree.getRoot();
		if(root == null)
			return "";
	
		StringBuilder sb = new StringBuilder();
		serialize(root, size, delimiter, sb);
		
		return sb.toString();
	}

	/**
	 * Recursively serialize the subtree.
	 * 
	 * @param root
	 * 		current subtree root
	 * @param size
	 * 		max size of children of any node
	 * @param delimiter
	 * 		delimiter to use to represent leaf
	 * @param sb
	 * 		string builder
	 */
	private static void serialize(GenericNode<Character> root, int size, char delimiter, StringBuilder sb) {
		if (root == null)
			return;

		sb.append(root.getData());
		Iterator<GenericNode<Character>> iterator = root.getChildren().iterator();
		for (int i = 0; i < size && iterator.hasNext(); i++)
			serialize(iterator.next(), size, delimiter, sb);

		sb.append(delimiter);
	}
}
