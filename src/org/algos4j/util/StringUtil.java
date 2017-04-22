package org.algos4j.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
	 * Anagrams.
	 * 
	 * @param string1
	 * 		first string
	 * @param string2
	 * 		second string
	 * 
	 * @return
	 * 		true if one is permutation of other, false otherwise
	 */
	public static boolean permutation(String string1, String string2) {
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
	 * Also knows as Run Length Encoding.
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
	
	/**
	 * Given a string this method reverse the the string by words.
	 * E.g, 'you are how' becomes 'how are you'. Time: O(n)
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		string with words in reverse order.
	 */
	public static String reverseWords(String string) {
		if (string == null)
			throw new NullPointerException("Input string cannot be null.");

		char[] chars = string.toCharArray();

		// First reverse the whole string
		reverse(chars, 0, chars.length - 1);

		int wIndex = 0;
		// Reverse individual words
		for (int index = 0; index <= chars.length; index++) {
			if (index == chars.length || chars[index] == ' ') {
				reverse(chars, wIndex, index - 1);
				wIndex = index + 1;
			}
		}

		return new String(chars);
	}
	
	/**
	 * Given character array, this method reverse the characters from start to
	 * end index.
	 * 
	 * @param chars
	 *   	character array
	 * @param startIndex
	 *     	start index
	 * @param endIndex
	 *    	end index
	 */
	protected static void reverse(char[] chars, int startIndex, int endIndex) {
		while (startIndex < endIndex) {
			char temp = chars[startIndex];
			chars[startIndex++] = chars[endIndex];
			chars[endIndex--] = temp;
		}
	}
	
	/**
	 * Given an integer, this method prints all the sequence of characters that can be used to dial the number.
	 * <p>
	 * Below summarizes the characters that can be used to dial each digit.
	 * <pre>
	 * 	2 -> using A or B or C
	 * 	3 -> using D or E or F
	 *	4 -> using G or H or I
	 *	5 -> using J or K or L
	 *	6 -> using M or N or O
	 *	7 -> using P or Q or R or S
	 *	8 -> using T or U or V
	 *	9 -> using W or X or Y or Z
	 *	1 -> using 1
	 *	0 -> using 0
	 *         	
	 * </pre>
	 * </p>
	 * 
	 * @param number
	 * 		given number
	 * 
	 * @throws IllegalArgumentException
	 * 		if the number is -ve
	 */
	public static void printDialableStrings(int number) {
		if (number < 0)
			throw new IllegalArgumentException();
		Map<Character, String> map = new HashMap<Character, String>();

		map.put('2', "ABC");
		map.put('3', "DEF");
		map.put('4', "GHI");
		map.put('5', "JKL");
		map.put('6', "MNO");
		map.put('7', "PQRS");
		map.put('8', "TUV");
		map.put('9', "WXYZ");
		map.put('1', "1");
		map.put('0', "0");

		StringBuilder str = new StringBuilder();

		printStrings(Integer.toString(number), 0, map, str);
	}
	
	/**
	 * Recursively finds all combinations and prnts.
	 * 
	 * @param numberString
	 * 		string representation of number
	 * @param index
	 * 		current index
	 * @param map
	 * 		number mappings
	 * @param sb
	 * 		string builder to hold partial result
	 */
	private static void printStrings(String numberString, int index, Map<Character, String> map, StringBuilder sb) {

		if (index == numberString.length()) {
			System.out.print(sb.toString() + " ");
			return;
		}

		String value = map.get(numberString.charAt(index));
		for (int j = 0; j < value.length(); j++) {
			sb.append(value.charAt(j));
			printStrings(numberString, index + 1, map, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	/**
	 * Given a string, this method computes the number of palindromic sequences
	 * to be removed to make it an empty string. At max we have 2, since we can
	 * remove all 1s or 0s to make it a palindrome.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		min sequences to be removed
	 */
	public static int minPalindromicSubsequences(String string) {
		if (string == null || string.length() == 0)
			return 0;
	
		// TODO data validation for only 0s and 1s

		if (isPalindrome(string))
			return 1;

		return 2;
	}
	
	/**
	 * Given a string, this method prints the concatenated string formed by
	 * arranging them in zig-zag row column fashion.
	 * 
	 * <pre>
	 * 		Input: PRINTCONCATZIGZAG
	 * 
	 * 		Row Zig-Zag arrangement:
	 * 
	 * 			P     T      C     I     G
  	 *			 R  N   C  N   A  Z  G  A
     *			  I      O      T     Z
     *
     *		Output: PTCIGRNCNAZGAIOTZ
	 * </pre>
	 * 
	 * @param string
	 * 		given string
	 * @param rows
	 * 		number of rows
	 * 
	 * @throws IllegalArgumentException
	 * 		if n is -ve or greater than string size
	 */
	public static void printZigZagConcatination(String string, int rows) {
		if (string == null)
			return;

		if (rows == 1) {
			System.out.println(string);
			return;
		}

		int charLen = string.length();

		String[] arr = new String[rows];
		Arrays.fill(arr, "");

		int row = 0;
		boolean down = false; 

		for (int i = 0; i < charLen; ++i) {
			// append current character to the current row
			arr[row] = arr[row] + string.charAt(i);
			
			if (row == rows - 1) // If last row change direction to 'up'
				down = false;
			else if (row == 0) // If 1st row change direction to 'down'
				down = true;

			// change row based on direction
			if (down)
				row++;
			else
				row--;
		}

		for (int i = 0; i < rows; ++i)
			System.out.print(arr[i]);
	}
	
	/**
	 * Given column number in an Excel sheep, this method returns the column name.
	 * In Excel the column names be like A, B, C, ..., Z, AA, AB, AC, ..., AZ, BA, 
	 * BB, ...,ZZ, ... etc.
	 * 
	 * @param number
	 * 		 column number
	 * 
	 * @return
	 * 		column name
	 * 
	 * @throws IllegalArgumentException
	 * 		if the column number is invalid ( < 0 )
	 */
	public static String getExcelColumnName(int number) {
		if (number < 0)
			throw new IllegalArgumentException("");

		// 7 chars are sufficient for up to max integer value
		char[] chars = new char[7];
		int index = 0;

		while (number > 0) {
			int rem = number % 26;

			if (rem == 0) {
				chars[index++] = 'Z';
				number = (number / 26) - 1;
			} else {
				chars[index++] = (char)((rem - 1) + 65);
				number = number / 26;
			}
		}

		reverse(chars, 0, index - 1);

		return new String(chars, 0, index);
	}
	
	/**
	 * Given two strings, this method counts the minimum number of operations
	 * required to transform the string1 into string2. The operations allowed is
	 * pick any character from first string and insert it to the front of it.
	 * 
	 * @param string1
	 * 		first string
	 * @param string2
	 * 		second string
	 * 
	 * @return
	 * 		number of operations required, -1 if not possible, 0 when strings are same
	 */
	public static int getMinOperations(String string1, String string2) {
		if (string1 == null || string2 == null)
			return -1;

		if (string1.length() != string2.length())
			return -1;

		int[] charCounts1 = getCharCounts(string1);
		int[] charCounts2 = getCharCounts(string2);

		for (int i = 0; i < charCounts1.length; i++)
			if (charCounts1[i] != charCounts2[i])
				return -1;

		int count = 0;
		for (int i = string1.length() - 1, j = string1.length() - 1; i >= 0;) {
			// If there is a mismatch, continue until string2[j] is not found in string1[0...i]
			while (i >= 0 && string1.charAt(i) != string2.charAt(j)) {
				i--;
				count++;
			}

			// chars at index i matches in both
			if (i >= 0) {
				i--;
				j--;
			}
		}

		return count;
	}
	
	/**
	 * The number of ASCII characters
	 */
	public static final int NUMBER_OF_ASCII_CHARS = 128;
	
	/**
	 * 
	 * @author psajja
	 *
	 */
	static class Char {
		int startIndex;
		int count;
	
		Char(int startIndex, int count) {
			this.startIndex = startIndex;
			this.count = count;
		}
		
		@Override
		public String toString() {
			return "{" + startIndex + ", " + count + "}";
		}
	}
	
	/**
	 * Given a string, this method returns the first non-repeating character.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		the first non-repeating character, returns char '\0' if there is no such char
	 */
	public static char getFirstNonRepeatingCharacter(String string) {
		if(string == null)
			throw new NullPointerException();
		
		if(string.isEmpty())
			throw new IllegalArgumentException();
		
		Char[] chars = getChars(string);
		for(Char chr : chars) {
			if(chr != null && chr.count == 1)
				return string.charAt(chr.startIndex);
		}
		
		return '\0';
	}

	/**
	 * Compute the char array which stores the first occurrence and count of character.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		chars array
	 */
	private static Char[] getChars(String string) {
		Char[] chars = new Char[NUMBER_OF_ASCII_CHARS];
		for (int i = 0; i < string.length(); i++) {
			int chrIndex = string.charAt(i);
			Char chr = chars[chrIndex];
			if (chr == null)
				chars[chrIndex] = new Char(i, 1);
			else
				chars[chrIndex].count++;
		}

		return chars;
	}
	
	/**
	 * Given a tour where the tour contains sequence of steps by a robot, this
	 * method checks whether the tour is a circular tour.
	 * 
	 * <p>
	 * Each char in the string can be one of the following:
	 * <pre>
	 * 	G - Go one unit
	 * 	L - Turn left
	 * 	R - Turn right 
	 * </pre>
	 * </p>
	 * 
	 * @param tour
	 * 		given tour
	 * 
	 * @return
	 * 		true if forms a circle, false otherwise
	 */
	public static boolean isCircularTour(String tour) {
	
		// directions
		int N = 0;
		int E = 1;
		int S = 2;
		int W = 3;

		// starting point
		int x = 0, y = 0;
		
		int dir = N;
		char[] path = tour.toCharArray();
		
		for (int i = 0; i < path.length; i++) {
			// Find current move
			char move = path[i];

			if (move == 'R')
				dir = (dir + 1) % 4;
			else if (move == 'L')
				dir = (4 + dir - 1) % 4;
			else if (move == 'G') {
				if (dir == N)
					y++;
				else if (dir == E)
					x++;
				else if (dir == S)
					y--;
				else if (dir == W)
					x--;
			}
		}

		// IS the robot at starting point (0, 0)?
		return (x == 0 && y == 0);
	}
	
	/**
	 * Given a string, this method removes the adjacent duplicate characters recursively.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		string after removing the chars
	 */
	public static String removeAdjacentDuplicates(String string) {
		if (isNullOrEmpty(string))
			return string;

		char[] chars = string.toCharArray();
		char lastChar = chars[0];

		// Holds the index of the output string
		int outputIndex = 1;
		for (int i = 1; i < chars.length; i++) {
			if (outputIndex > 0 && chars[i] == chars[outputIndex - 1]) {
				lastChar = chars[outputIndex - 1];
				while (outputIndex > 0 && chars[outputIndex - 1] == lastChar) {
					outputIndex--;
				}
			} else if (chars[i] == lastChar) {
				// do nothing, we are seeing the lastChar again after removing adjacent once
				continue;
			} else {
				chars[outputIndex++] = chars[i];
			}
		}

		return new String(chars, 0, outputIndex);
	}
	
	/**
	 * Given a string, this method returns the number of palindrome sub-strings in the string.
	 * Length of the palindrome sub-string must be at least 2.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		count of palindromic substrings
	 */
	public static int countPalindromeSubstrings(String string) {
		if (isNullOrEmpty(string))
			return 0;

		// Map holds the count of palindromes from i to j (i->j as key)
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		
		// Map holds whether the sequence from i to j (i->j as key) is a palindrome.
		Map<String, Boolean> palindromeMap = new HashMap<String, Boolean>();
		
		String separator = "->";
	
		int n = string.length();
		
		// Initialize palindromes of all to false, counts to zero
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				countMap.put(i + separator + j, 0);
				palindromeMap.put(i + separator + j, false);
			}
		}
	
		// Update palindrome map for single chars
		for (int i = 0; i < n; i++) {
			palindromeMap.put(i + separator + i, true);
		}
		
		// find and update palindromes of 2 chars (aa, bb, etc.)
		for (int i = 0; i < n - 1; i++) {
			if (string.charAt(i) == string.charAt(i + 1)) {
				palindromeMap.put(i + separator + (i + 1), true);
				countMap.put(i + separator + (i + 1), 1);
			}
		}
	
		// find and update palindromes of more than 2 chars
		for (int size = 2; size < n; size++) {
			for (int i = 0; i < n - size; i++) {
				int j = size + i;
				// Current string is palindrome
				if (string.charAt(i) == string.charAt(j) && palindromeMap.get((i + 1) + separator + (j - 1)))
					palindromeMap.put(i + separator + j, true);
				
				// Palindromes from i to j (excluding current, overlapping)
				int count = countMap.get(i + separator + (j - 1)) + countMap.get((i + 1) + separator + j) - countMap.get((i + 1) + separator + (j - 1));
				if (palindromeMap.get(i + separator + j)) {
					// Current one is also a palindrome
					countMap.put(i + separator + j, count + 1);
				} else {
					countMap.put(i + separator + j, count);
				}
			}
		}

		// Final Count of palindromes (0 to size-1)
		return countMap.get(0 + separator + (n - 1));
	}
	
	/**
	 * Given a string, this method returns the number of palindrome sub-strings in the string.
	 * Length of the palindrome sub-string must be at least 2.
	 * 
	 * @param string
	 * 		given string
	 * 
	 * @return
	 * 		collection of palindromes, the returned collection does not preserve the order of their occurrence.
	 */
	public static Collection<String> getPalindromeSubstrings(String string) {
		if (isNullOrEmpty(string))
			return new HashSet<>();

		// Set holds the palindromes
		Collection<String> palindromes = new HashSet<String>();
		
		// Map holds whether the sequence from i to j (i->j as key) is a palindrome.
		Map<String, Boolean> palindromeMap = new HashMap<String, Boolean>();
		
		String separator = "->";

		int n = string.length();
		
		// Initialize palindromes of all sizes to false
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				palindromeMap.put(i + separator + j, false);
			}
		}
	
		// Update palindromes for all single chars
		for (int i = 0; i < n; i++) {
			palindromes.add(string.charAt(i) + "");
			palindromeMap.put(i + separator + i, true);
		}
		
		// find and update palindromes of 2 chars (aa, bb, etc.)
		for (int i = 0; i < n - 1; i++) {
			if (string.charAt(i) == string.charAt(i + 1)) {
				palindromeMap.put(i + separator + (i + 1), true);
				palindromes.add(string.substring(i, i + 2));
			}
		}
	
		// find and update palindromes of more than  chars
		for (int size = 2; size < n; size++) {
			for (int i = 0; i < n - size; i++) {
				int j = size + i;
				// Current string is palindrome
				if (string.charAt(i) == string.charAt(j) && palindromeMap.get((i + 1) + separator + (j - 1))) {
					palindromeMap.put(i + separator + j, true);
					palindromes.add(string.substring(i, j + 1));
				}
			}
		}

		return palindromes;
	}
	
	/**
	 * Given a string and a pattern string, this method returns the minimum
	 * string that contains the pattern string. All the characters in pattern
	 * string must contain in window string. The order does not matter in the
	 * window string.
	 * 
	 * <p>
	 * An Example:
	 * <pre>
	 * Input String:   "this is a test string"
	 * Pattern String: "tist"
	 * Window String   "t stri"
	 * </pre>
	 * </p>
	 * 
	 * @param string
	 * @param pattern
	 * 
	 * @return
	 * 		the min window string if exists, null in other cases
	 */
	public static String getWindowString(String string, String pattern) {
		if (string == null || pattern == null)
			return null;

		int stringLength = string.length();
		int patternLength = pattern.length();

		if (stringLength < patternLength)
			return null;

		int[] patternCounts = new int[NUMBER_OF_ASCII_CHARS];
		int[] stringCounts = new int[NUMBER_OF_ASCII_CHARS];

		for (int i = 0; i < patternLength; i++)
			patternCounts[pattern.charAt(i)]++;

		int currentStart = 0, startIndex = -1, minLength = Integer.MAX_VALUE;

		int count = 0; // count of characters
		for (int i = 0; i < stringLength; i++) {
			stringCounts[string.charAt(i)]++;

			// string character and pattern character matches
			if (patternCounts[string.charAt(i)] != 0
					&& stringCounts[string.charAt(i)] <= patternCounts[string.charAt(i)])
				count++;

			// Found all the chars in pattern in string so far
			if (count == patternLength) {
				// Remove useless and redundant characters
				while (stringCounts[string.charAt(currentStart)] > patternCounts[string.charAt(currentStart)]
						|| patternCounts[string.charAt(currentStart)] == 0) {

					if (stringCounts[string.charAt(currentStart)] > patternCounts[string.charAt(currentStart)])
						stringCounts[string.charAt(currentStart)]--;
					
					currentStart++;
				}

				// Update minimum window size
				int windowLength = i - currentStart + 1;
				if (minLength > windowLength) {
					minLength = windowLength;
					startIndex = currentStart;
				}
			}
		}

		// No match found
		if (startIndex == -1)
			return null;

		// Matched window
		return string.substring(startIndex, startIndex + minLength);
	}
}