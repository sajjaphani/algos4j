package org.algos4j.util;

import java.util.Arrays;

/**
 * This class tests solving the nuts and bolts problem.
 * 
 * @author psajja
 *
 */
public class NutsAndBoltsMatchPairTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[] nuts = { '@', '#', '$', '%', '^', '&' };
		char[] bolts = { '$', '%', '&', '^', '@', '#' };

		System.out.println("Before Match");
		System.out.println("Nuts:  " + Arrays.toString(nuts));
		System.out.println("Bolts: " + Arrays.toString(bolts));

		ArraySortUtil.matchPairs(nuts, bolts);

		System.out.println("After Match");
		System.out.println("Nuts:  " + Arrays.toString(nuts));
		System.out.println("Bolts: " + Arrays.toString(bolts));
	}
}
