package org.algos4j.util;

/**
 * A test class on string some of the utilities.
 * 
 * @author psajja
 *
 */
public class StringUtilitiesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string = "Mr Will Smith";
		char[] chars = new char[17];
		System.arraycopy(string.toCharArray(), 0, chars, 0, 13);
		
		System.out.println(string);
		StringUtil.urlify(chars, 13);
		System.out.println(new String(chars));
		
		String palindrome = "Tact Coa";
		System.out.println(palindrome);
		System.out.println("Is a palindrome permutation: " + StringUtil.isPalindromePermutation(palindrome));
		System.out.println("Is a palindrome permutation: " + StringUtil.isPalindromePermutation1(palindrome));
	}
}
