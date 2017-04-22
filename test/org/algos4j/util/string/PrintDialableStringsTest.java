package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * Given a number this method tests printing of dialable string to get that number.
 * 
 * @author psajja
 *
 */
public class PrintDialableStringsTest {

	public static void main(String[] args) {
		System.out.println("Dialable Sequences for : " + 23);
		StringUtil.printDialableStrings(23);
		
		System.out.println();
		System.out.println("Dialable Sequences for : " + 121);
		StringUtil.printDialableStrings(121);
	}
}
