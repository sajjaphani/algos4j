package org.algos4j.util;

import java.util.Arrays;

/**
 * Test class for coin change problems.
 * 
 * @author psajja
 *
 */
public class MakeChangeTest {

	public static void main(String[] args) {
		int[] denominations = {1, 2, 3};
		int ways = MathUtil.findWaysToMakeChange(denominations, 5);
		System.out.println(ways + " ways are there to make amount " + 5);
		
		int[] denominations1 = {2, 5, 3};
		int coins = MathUtil.findMinCoinsToMakeChange(denominations1, 7);
		
		System.out.println(coins + " coins are min required to make change for " + 7);
		
		int[] denominations2 = {1, 5, 12, 25};
		int[] coins1 = MathUtil.makeChange(denominations2, 16);
		
		System.out.print("Coins required to make change for " + 16 );
		System.out.print(" " + Arrays.toString(coins1));
		System.out.println();
	}
}
