package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * Tests whether given two strings are anagrams.
 * 
 * @author psajja
 *
 */
public class StringAnagramsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string1 = "anagram";
		String string2 = "magaran";
		
		System.out.println(string1);
		System.out.println(string2);
		System.out.println("Are Anagrams: " + StringUtil.permutation(string1, string2));
	}

}
