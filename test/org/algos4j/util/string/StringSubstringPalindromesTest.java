package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * This class tests counting and computing the sub-string palindromes.
 * 
 * @author psajja
 *
 */
public class StringSubstringPalindromesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string1 = "abaab";
		System.out.println("String: " + string1);
		System.out.println("Palindrome Count: " + StringUtil.countPalindromeSubstrings(string1));
		
		System.out.println();
		String string2 = "abbaeae";
		System.out.println("String: " + string2);
		System.out.println("Palindrome Count: " + StringUtil.countPalindromeSubstrings(string2));
		
		printPalindromes();
	}

	private static void printPalindromes() {
		System.out.println();
		String string1 = "abaab";
		System.out.println("String: " + string1);
		System.out.println("Palindromes: " + StringUtil.getPalindromeSubstrings(string1));
	}
}
