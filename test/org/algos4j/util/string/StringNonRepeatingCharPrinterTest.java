package org.algos4j.util.string;

import org.algos4j.util.ext.StringNonRepeatingCharPrinter;

/**
 * This class tests printing the first non-repeating characters while reading the string chars.
 * 
 * @author psajja
 *
 */
public class StringNonRepeatingCharPrinterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string = "stringarestringandstringendhere";
		System.out.println("Input String: " + string);
		System.out.println();
		StringNonRepeatingCharPrinter.use(string);
	}
}
