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
	
	/**
	 * Generate and prints all the strings of n bits. Backtracking problem. 
	 * Time: O(2^n)
	 * 
	 * @param n
	 * 		number of bits
	 * 
	 * @throws IllegalArgumentException
	 * 		if the number of bits is negative
	 */
	public static void printBinaryStrings(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Input size must be positive");
	
		int[] array = new int[n];
	
		printBinaryStrings(n, array);
	}

	/**
	 * Recursively generate and print all the binary strings of length n.
	 * 
	 * @param n
	 * 		array index to set or reset the bit
	 * @param array
	 * 		contains current digits
	 */
	private static void printBinaryStrings(int n, int[] array) {
		if (n < 1)
			System.out.println(toString(array));
		else {
			array[n - 1] = 0;
			printBinaryStrings(n - 1, array);
			array[n - 1] = 1;
			printBinaryStrings(n - 1, array);
		}
	}
	
	/**
	 * Convert digit array to string.
	 * 
	 * @param array
	 * 		digit array
	 * 
	 * @return
	 * 		string of digits
	 */
	private static String toString(int[] array) {
		String digitString = "";
		
		for(int digit : array) 
			digitString += digit;
		
		return digitString;
	}

	/**
	 * Generate all the strings of length n drawn from 0...k-1.
	 * Backtracking problem. 
	 * Time: O(k^n)
	 * 
	 * @param n
	 * 		length of the string
	 * 
	 * @param k
	 * 		digits from 0 to k-1
	 * 
	 * @throws IllegalArgumentException
	 * 		if the supplied n or k is negative
	 */
	public static void printNumericStrings(int n, int k) {
		if (n < 0 || k < 0)
			throw new IllegalArgumentException("Input must be positive");
	
		int[] array = new int[n];
		
		printNumericStrings(n, k, array);
	}

	/**
	 * Recursively generate the numeric strings.
	 * 
	 * @param n
	 * 		current n
	 * @param k
	 * 		current k
	 * @param array
	 * 		contains current digits
	 */
	private static void printNumericStrings(int n, int k, int[] array) {
		if (n < 1)
			System.out.println(toString(array));
		else {
			for (int i = 0; i < k; i++) {
				array[n - 1] = i;
				printNumericStrings(n - 1, k, array);
			}
		}
	}
	
	/**
	 * Given a string it checks whether the given string contains all unique
	 * characters. This method assumes that the string characters are ASCII only.
	 * It will not work as expected for other than ASCII (128) characters.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		true if it contains unique characters, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given string is null
	 */
	static boolean hasUniqueChars(String string) {
		if(string == null)
			throw new NullPointerException("If the input string is null");
		
		if (string.length() > 128)
			return false;

		boolean[] occured = new boolean[128];
		for (int i = 0; i < string.length(); i++) {
			int val = string.charAt(i);
			// First appearance of duplicate char
			if (occured[val])
				return false;
			
			occured[val] = true;
		}
		
		return true;
	}
	
	/**
	 * Given a string it checks whether the given string contains all unique
	 * characters. This method assumes that string contains only lower case
	 * ASCII characters.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		true if it contains unique characters, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the given string is null
	 */
	static boolean hasUniqueChars1(String string) {
		if (string == null)
			throw new NullPointerException("Input string cannot be null");

		int bits = 0;
		for (int i = 0; i < string.length(); i++) {
			int chr = string.charAt(i) - 'a';
			if ((bits & (1 << chr)) > 0)
				return false;
			
			bits |= (1 << chr);
		}
		
		return true;
	}
	
	/**
	 * Given two string this method checks whether one is a permutation of the
	 * other. This method assumes that the string characters are ASCII only. It
	 * will not work as expected for other than ASCII (128) characters.
	 * 
	 * @param string1
	 * 		first string
	 * @param string2
	 * 		second string
	 * 
	 * @return
	 * 		true if one is permutation of other, false otherwise
	 */
	static boolean permutation(String string1, String string2) {
		if (string1 == null || string2 == null)
			return false;

		if (string1.length() != string2.length())
			return false;

		int[] letters = new int[128];
		for (char chr : string1.toCharArray())
			letters[chr]++;

		for (int i = 0; i < string2.length(); i++) {
			int chr = string2.charAt(i);
			letters[chr]--;
			if (letters[chr] < 0)
				return false;
		}

		return true;
	}
	
	/**
	 * Given a character array which holds the original string, it replaces all
	 * the spaces with '%20'. It assumes that the array has sufficient space to
	 * replace the spaces.
	 * 
	 * @param chars
	 * 		character array holds the original string chars
	 * @param length
	 * 		length of the characters in the array
	 * 
	 * @throws NullPointerException
	 * 		if the given character array is null
	 * @throws IllegalArgumentException
	 * 		if the length is <= 0
	 * @throws ArrayIndexOutOfBoundsException
	 * 		if chars array cannot accommodate the new chars
	 */
	static void urlify(char[] chars, int length) {
		if (chars == null)
			throw new NullPointerException("Chars array should not be null");
		if (length <= 0)
			throw new IllegalArgumentException("Invalid length provided.");

		int spaces = 0, index;
		for (int i = 0; i < length; i++) {
			if (chars[i] == ' ')
				spaces++;
		}

		index = length + spaces * 2;
		if (index > chars.length)
			throw new ArrayIndexOutOfBoundsException("Not sufficient space in array.");

		for (int i = length - 1; i >= 0; i--) {
			if (chars[i] == ' ') {
				chars[--index] = '0';
				chars[--index] = '2';
				chars[--index] = '%';
			} else {
				chars[--index] = chars[i];
			}
		}
	}
	
	/**
	 * Given a string, this method checks whether it is a permutation of a
	 * palindrome. A string is palindrome if it is the same forwards and 
	 * backwards. To qualify a palindrome permutation, a string can have
	 * at most one character in odd number and the rest are in even numbers.
	 * Non letter characters are ignored.
	 * 
	 * @param string
	 * 		input string
	 * 
	 * @return
	 * 		true if it a permutation of a palindrome, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the input string is null
	 */
	static boolean isPalindromePermutation(String string) {
		if (string == null)
			throw new NullPointerException("Input string cannot be null");
		
		return matchedPalindromeFrequency(buildFrequencyTable(string));
	}
	
	/**
	 * Builds the frequency table for the characters in the string. There table
	 * is an array of 26 elements holds the occurence of each char a-z ignoring
	 * the case.
	 * 
	 * @param string
	 * 		input string
	 * 
	 * @return
	 * 		array holding the character frequency 
	 */
	private static int[] buildFrequencyTable(String string) {
		int[] freq = new int[26];
		for (char c : string.toCharArray()) {
			int pos = getPosition(c);
			if (pos != -1)
				freq[pos]++;
		}
		
		return freq;
	}
	
	/**
	 * Get the position of the given character.
	 * 
	 * @param chr
	 * 		given character
	 * 
	 * @return
	 * 		position ranging from 0 to 25, -1 for non character
	 */
	private static int getPosition(char chr) {
		int start = 10;
		int end = 35;
		// irrespective of case, the char value for a-z is in the range of 10-35
		int val = Character.getNumericValue(chr);
		if (start <= val && val <= end)
			return val - start;

		return -1;
	}
	
	/**
	 * Given an array holding the character frequency, this method check if
	 * the frequencies matches according to the definition of palindrome permutation.
	 * 
	 * @param freq
	 * 		array holding frequencies
	 * 
	 * @return
	 * 		false for more than one odd frequency, true otherwise
	 */
	private static boolean matchedPalindromeFrequency(int[] freq) {
		boolean foundOddFreq = false;
		for (int count : freq) {
			if (count % 2 == 1) {
				if (foundOddFreq)
					return false;
				
				foundOddFreq = true;
			}
		}
		
		return true;
	}
	
	/**
	 * Given a string, this method checks whether it is a permutation of a
	 * palindrome. This method is space optimized version and uses a bit 
	 * vector rather than an array to compute the frequencies.
	 * 
	 * @param string
	 * 		input string
	 * 
	 * @return
	 * 		true if it a permutation of a palindrome, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the input string is null
	 */
	static boolean isPalindromePermutation1(String string) {
		if (string == null)
			throw new NullPointerException("Input string cannot be null");
		
		int bitVector = computeBitVector(string);
	
		return bitVector == 0 || ((bitVector & (bitVector - 1)) == 0);
	}
	
	/**
	 * Compute the bit vector for the given string.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		integer holding the bits of the corresponding chars in the string
	 */
	private static int computeBitVector(String string) {
		int bitVector = 0;
		for (char chr : string.toCharArray()) {
			int pos = getPosition(chr);
			if (pos != -1)
				bitVector = toggle(bitVector, pos);
		}
		
		return bitVector;
	}

	/**
	 * Toggle the corresponding bit at the given index.
	 * 
	 * @param bitVector
	 * 		current bit vector
	 * @param index
	 * 		bit positioin to toggle
	 * 
	 * @return
	 * 		bit vector after toggling the bit at index
	 */
	private static int toggle(int bitVector, int index) {
		int mask = 1 << index;
		if ((bitVector & mask) == 0)
			bitVector |= mask;
		else
			bitVector &= ~mask;

		return bitVector;
	}

	/**
	 * Given two strings, this method checks whether the strings are either one
	 * edit away or zero edit away. The three type of edits that can be
	 * performed on strings are insert a character, remove a character, or
	 * replace a character.
	 * 
	 * @param string1
	 * 		first string
	 * @param string2
	 * 		second string
	 * 
	 * @return
	 * 		true if they are one (or zero) edit away, false otherwise
	 */
	public static boolean areOneEditAway(String string1, String string2) {
		if (string1 == null || string2 == null)
			return false;

		if (Math.abs(string1.length() - string2.length()) > 1)
			return false;

		String strl = string1.length() < string2.length() ? string1 : string2;
		String str2 = string1.length() < string2.length() ? string2 : string1;

		int indexl = 0;
		int index2 = 0;
		boolean replaceEdit = false;
		while (index2 < str2.length() && indexl < strl.length()) {
			if (strl.charAt(indexl) != str2.charAt(index2)) {
				// More than one replace difference
				if (replaceEdit)
					return false;
				
				replaceEdit = true;
				if (strl.length() == str2.length())
					indexl++;
			} else {
				// Move shorter pointer
				indexl++;
			}
			// Move longer pointer
			index2++;
		}
		
		return true;
	}
	
	/**
	 * Given a string this method compresses the string using counts of repeated
	 * characters. For example, it the string is 'aaabbc', the compressed string
	 * will be 'a3b2c1'. This method returns the original string if the
	 * "compressed" string would not become smaller than the original string.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		compressed string if the length of compressed one is smaller, original string otherwise
	 */
	public static String compress(String string) {
		if (string == null)
			return null;

		StringBuilder compressed = new StringBuilder();
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			count++;
			// We are seeing a different character or reached end of string
			if (i + 1 >= string.length() || string.charAt(i) != string.charAt(i + 1)) {
				compressed.append(string.charAt(i));
				compressed.append(count);
				count = 0;
			}
		}

		return compressed.length() < string.length() ? compressed.toString() : string;
	}
	
	/**
	 * The number of lower case letters
	 */
	public static final int NUMBER_OF_LETTERS_LOWERCASE = 26;
		
	/**
	 * Given two strings, this method computes the number of characters that are
	 * needed to be removed from either of the string to make them anagrams. This
	 * method assumes the given strings contain lower case characters only.
	 * 
	 * @param string1
	 * 		first string
	 * @param string2
	 * 		second string
	 * 
	 * @return
	 * 		number of characters to remove from either of the string
	 */
	public static int computeAnagramDifference(String string1, String string2) {
		// TODO - error handling, null checks

		int[] charCounts1 = getCharCounts(string1);
		int[] charCounts2 = getCharCounts(string2);

		return computeDelta(charCounts1, charCounts2);
	}

	/**
	 * This method computes the counts of each character in the given string.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		array holding the count of each character
	 */
	private static int[] getCharCounts(String string) {
		return buildFrequencyTable(string);
	}

	/**
	 * This method computes the difference of number of character.
	 * 
	 * @param charCounts1
	 * 		character frequencies of first string
	 * @param charCounts2
	 * 		character frequencies of second string
	 * 
	 * @return
	 * 		the number of characters differ
	 */
	private static int computeDelta(int[] charCounts1, int[] charCounts2) {
		int delta = 0;
		for (int i = 0; i < charCounts1.length; i++)
			delta += Math.abs(charCounts1[i] - charCounts2[i]);

		return delta;
	}
}