package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * Tests counting number of operations required to transform one string into another 
 * where the only allowed operation is remove a character and insert it at front.
 * 
 * @author psajja
 *
 */
public class StringTransformCountMinOperationsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string1 = "EACBD";
		String string2 = "EABCD";

		System.out.println("String 1: " + string1);
		System.out.println("String 2: " + string2);
		System.out.println("Min Operations: " + StringUtil.getMinOperations(string1, string2));
	}
}
