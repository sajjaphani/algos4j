package org.algos4j.util;

/**
 * This class tests printing jumping numbers from 0 to upto a given number.
 * A jumping number is a number where all the adjacent digits differ by one.
 * 0 to 9 are also jumping numbers
 * 
 * @author psajja
 *
 */
public class PrintJumpingNumbersTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Jumping numbers from 0 to " + 30);
		ArrayUtil.printJumpingNumbers(30);
	}
}
