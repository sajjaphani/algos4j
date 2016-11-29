package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * A test class to test whether a given string is palindrome.
 * 
 * @author psajja
 *
 */
public class StringPalindromeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Palindrome (Hello World!): " + StringUtil.isPalindrome("Hello World!"));
		System.out.println("Palindrome (inaani): " + StringUtil.isPalindrome("inaani"));
	}

}
