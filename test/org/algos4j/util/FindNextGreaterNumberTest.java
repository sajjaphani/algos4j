package org.algos4j.util;

/**
 * @author psajja
 *
 */
public class FindNextGreaterNumberTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int number1 = 218765;
		System.out.println("Number: " + number1 + ", Next Greater: " + ArrayUtil.findNextGreater(number1));
		
		int number2 = 1234;
		System.out.println("Number: " + number2 + ", Next Greater: " + ArrayUtil.findNextGreater(number2));
		
		int number3 = 4321;
		System.out.println("Number: " + number3 + ", Next Greater: " + ArrayUtil.findNextGreater(number3));
	}

}
