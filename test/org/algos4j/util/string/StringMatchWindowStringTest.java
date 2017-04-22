package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * This class tests finding the min window that matches the pattern in a string.
 * 
 * @author psajja
 *
 */
public class StringMatchWindowStringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string = "this is a test string";
		String pattern = "tist";

		System.out.println("String: " + string);
		System.out.println("Pattern: " + pattern);

		String windowString = StringUtil.getWindowString(string, pattern);
		if (windowString == null)
			System.out.println("No such window exists");
		else
			System.out.println("Window String: " + windowString);
	}

}
