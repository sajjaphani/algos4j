package org.algos4j.util;

/**
 * Some utility methods on strings.
 * 
 * @author psajja
 * 
 */
public class StringUtil {

	private static final int ALPHABETS = 26;
	
	/**
	 * Non-instantiable.
	 */
	private StringUtil() {
	}

	/**
	 * Checks whether the given string is empty or null.
	 * 
	 * @return true if the string is null or empty, false otherwise.
	 */
	public static boolean isNullOrEmpty(String string) {
		if (string == null || string.isEmpty())
			return true;
		
		return false;
	}
	
	/**
	 * Reverses a given string.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		reversed string
	 */
	public static String reverse(String string) {
		if (isNullOrEmpty(string))
			return string;

		if (string.length() == 1)
			return string;

		char[] chars = string.toCharArray();
		int length = string.length();
		for (int i = 0; i < length / 2; i++) {
			char chr = chars[i];
			chars[i] = chars[length - i - 1];
			chars[length - i - 1] = chr;
		}

		return new String(chars);
	}
	
	/**
	 * Check whether one string is circular shift of other.
	 * 
	 * @param str1
	 * 		first string
	 * @param str2
	 * 		second string
	 * 
	 * @return
	 * 		true if one is circular shift of other, false otherwise
	 */
	public static boolean isCircularShift(String str1, String str2) {

		if (str1 == null || str2 == null || str1.length() != str2.length())
			return false;
		
		return str1.concat(str1).contains(str2);
	}
	
	/**
	 * Check whether the given string is palindrome.
	 * 
	 * @param string
	 * 		input string
	 * 
	 * @return
	 * 		true if the string is palindrome, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the input string is null
	 */
	public static boolean isPalindrome(String string) {
		if (string == null)
			throw new NullPointerException("Input string should not be null.");
		int length = string.length();
		for (int i = 0; i < length / 2; i++)
			if (string.charAt(i) != string.charAt(length - 1 - i))
				return false;
		return true;
	}
	
	/**
	 * Check whether the given array of strings are in sorted order.
	 * 
	 * @param array
	 * 		string array
	 * 
	 * @return
	 * 		true if in sorted order, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null or one of the elements in the array is null
	 */
	public static boolean isSorted(String[] array) {
		if (array == null)
			throw new NullPointerException("Input array should not be null.");
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1].compareTo(array[i]) > 0)
				return false;
		}
		return true;
	}
	
	/**
	 * Given a string, this method prints all the permutations of it.
	 * Time: O(n*n!)
	 * 
	 * @param str
	 *            input string
	 * 
	 * @throws NullPointerException
	 *             if the input string is null
	 */
	public static void printPermutations(String str) {
		printPermutations(str, "");
	}

	/**
	 * Recursively prints all the permutations.
	 * 
	 * @param str
	 *            string
	 * @param prefix
	 *            current prefix
	 */
	private static void printPermutations(String str, String prefix) {
		if (str.length() == 0)
			System.out.println(prefix);
		else {
			for (int i = 0; i < str.length(); i++) {
				String rem = str.substring(0, i) + str.substring(i + 1);
				printPermutations(rem, prefix + str.charAt(i));
			}
		}
	}
	
	/**
	 * Prints all strings of length k where the characters are in sorted order.
	 * Time: O( k * c^k),
	 * 
	 * @param k
	 * 		string length
	 */
	public static void printSortedStrings(int k) {
		if(k <= 0)
			return;
		printSortedStrings(k, "");
	}

	/**
	 * Recursively print the strings.
	 * 
	 * @param k
	 * 		length
	 * @param prefix
	 * 		current prefix
	 */
	private static void printSortedStrings(int k, String prefix) {
		if (k == 0) {
			if (isInOrder(prefix)) {
				System.out.println(prefix);
			}
		} else {
			for (int i = 0; i < ALPHABETS; i++) {
				char c = ithLetter(i);
				printSortedStrings(k - 1, prefix + c);
			}
		}
	}

	/**
	 * Checks whether the string is in sorted order.
	 * 
	 * @param s
	 * 		given string
	 * 
	 * @return
	 * 		true if the chars are in sorted order, false otherwise.
	 */
	private static boolean isInOrder(String s) {
		for (int i = 1; i < s.length(); i++) {
			int prev = ithLetter(s.charAt(i - 1));
			int curr = ithLetter(s.charAt(i));
			if (prev > curr) { return false; }
		}
		return true;
	}

	/**
	 * Get the ith letter.
	 * Starting from 0 ('a').
	 * 
	 * @param i
	 * 		position
	 * 
	 * @return
	 * 		ith letter in sequence of ASCII 
	 */
	private static char ithLetter(int i) {
		return (char) (((int) 'a') + i);
	}
	
	/**
	 * This method will test whether the given pattern is existing in the text.
	 * It considers the first occurrence of the match.
	 * This is not 'regex' pattern, simple search for the match.
	 * 
	 * @param text
	 * 		given text
	 * @param pattern
	 * 		pattern string to find
	 * 
	 * @return
	 * 		starting index, -1 if not found
	 * 
	 * @throws NullPointerException
	 * 		if the text is null
	 */
	public static int findMatch(String text, String pattern) {
		if (text == null)
			throw new NullPointerException("Input text cannot be null.");
		if (pattern == null || "".equals(pattern))
			return -1;

		int n = text.length();
		int m = pattern.length();

		for (int i = 0; i <= (n - m); i++) {
			int j = 0;
			
			while ((j < m) && text.charAt(i + j) == pattern.charAt(j))
				j = j + 1;
			
			if (j == m)
				return i;
		}
		
		return -1;
	}
}
