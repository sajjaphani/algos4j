package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * Tests compressing a given string.
 * 
 * @author psajja
 *
 */
public class StringCompressTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string1 = "wwwwaaadexxxxxx";
		System.out.println("String: " + string1);
		System.out.println("Compressed: " + StringUtil.compress(string1));
	}

}
