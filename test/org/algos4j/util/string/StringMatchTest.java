package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * A test class to test whether a string found in another string.
 * 
 * @author psajja
 *
 */
public class StringMatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String txt = "This is a beautiful world.";
		String word = "world";
		System.out.println("Test: " + txt);
		int index = StringUtil.findMatch(txt, word);
		if(index == -1)
			System.out.println("The given word is not found...");
		else
			System.out.println("The given word is found at position: " + index);
	}

}
