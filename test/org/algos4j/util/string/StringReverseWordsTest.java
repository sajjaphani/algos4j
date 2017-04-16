package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * This  class tests the reversing the string by words in a given string.
 * 
 * @author psajja
 *
 */
public class StringReverseWordsTest {

	public static void main(String[] args) {
		String string1 = "Welcome!";
		System.out.println("String: " + string1);
		System.out.println("Reverse: " + StringUtil.reverseWords(string1));
		
		System.out.println();
		String string2 = "you! are how";
		System.out.println("String: " + string2);
		System.out.println("Reverse: " + StringUtil.reverseWords(string2));
	}
}
