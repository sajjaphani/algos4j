package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * A test class to test strings circular shift.
 * 
 * @author psajja
 *
 */
public class StringCircularShiftTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Circular Shift: (abcd, bcda): " + StringUtil.isCircularShift("abc", "bca"));
		System.out.println("Circular Shift: (abde, adeb): " + StringUtil.isCircularShift("abde", "adeb"));
	}

}
