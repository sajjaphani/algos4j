package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * A test class to test the first non-repeating character finding.
 * 
 * @author psajja
 *
 */
public class StringFirstNonRepeatingCharTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string = "geeksforgeeks";
		System.out.println(string);
		System.out.println("First Non Repeating Char: " + StringUtil.getFirstNonRepeatingCharacter(string));
	}

}
