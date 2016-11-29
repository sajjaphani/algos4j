package org.algos4j.util.string;

import java.util.Arrays;

import org.algos4j.util.StringUtil;

/**
 * A test class to test whether a given array of strings are sorted.
 * 
 * @author psajja
 *
 */
public class StringArraySortedTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strings = {"Java", "JavaScript", "Python", "Ruby"};
		System.out.println(Arrays.asList(strings));
		System.out.println("Strings are sorted: " + StringUtil.isSorted(strings));
	}
}
