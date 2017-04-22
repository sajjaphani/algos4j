package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * This method tests printing zig-zag concatenation of string by arranging the chars in rows.
 * 
 * @author psajja
 *
 */
public class StringPrintZigZagConcatenationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string1 = "PRINTCONCATZIGZAG";
		System.out.println("String: " + string1);
		StringUtil.printZigZagConcatination(string1, 3);
		
		System.out.println();
		String string2 = "ABCDEFGH";
		System.out.println("String: " + string2);
		StringUtil.printZigZagConcatination(string2, 2);
	}
}
