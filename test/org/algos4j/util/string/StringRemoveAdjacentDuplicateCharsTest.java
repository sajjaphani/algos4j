package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * This class tests removing the adjacent duplicate characters recursively.
 * 
 * @author psajja
 *
 */
public class StringRemoveAdjacentDuplicateCharsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string1 = "azxxzy";
		System.out.println("String: " + string1);
		System.out.println("After: " + StringUtil.removeAdjacentDuplicates(string1));
		
		String string2 = "acbbcddc";
		System.out.println("String: " + string2);
		System.out.println("After: " + StringUtil.removeAdjacentDuplicates(string2));
		
		String string3 = "caaabbbaacdddd";
		System.out.println("String: " + string3);
		System.out.println("After: " + StringUtil.removeAdjacentDuplicates(string3));
	}

}
