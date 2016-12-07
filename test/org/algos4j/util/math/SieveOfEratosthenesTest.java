package org.algos4j.util.math;

import org.algos4j.util.MathUtil;

/**
 * A test class to test the sieve of Eratosthenes for a given number.
 * 
 * @author psajja
 *
 */
public class SieveOfEratosthenesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("First 100 prime numbers");
		MathUtil.sieveOfEratosthenes(100);
	}

}
