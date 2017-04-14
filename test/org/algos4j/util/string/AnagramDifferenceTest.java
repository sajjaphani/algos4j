package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * This method test anagram difference between two strings.
 * 
 * @author psajja
 *
 */
public class AnagramDifferenceTest {

	public static void main(String[] args) {
		String string1 = "hello";
		String string2 = "billion";
		
		int delta = StringUtil.computeAnagramDifference(string1, string2);
		
		System.out.println(delta + " characters are needed to be removed from either string to make them anagrams.");
	}
}
