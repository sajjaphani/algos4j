package org.algos4j.tree;

/**
 * This class tests serializing/deserializing a generic tree of given arity.
 * 
 * @author psajja
 *
 */
public class GenericTreeSerializationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char delimiter = ')';
		
		String serializedString = "ABE)FK)))C)DG)H)I)J)))";
		GenericTree<Character> tree = GenericTreeUtil.deserialize(serializedString, 4, delimiter);
		
		System.out.println(tree);
		
		String serializedStr = GenericTreeUtil.serialize(tree, 4, delimiter);
		System.out.println("Serialized String: " + serializedStr);
	}
}
