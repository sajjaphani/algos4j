package org.algos4j.stack;

import java.util.Arrays;

/**
 * A test class to test stock span computation.
 * 
 * @author psajja
 *
 */
public class StockSpanTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] price = {10, 4, 5, 90, 120, 80};
		int[] span = StackUtil.computeStockSpan(price);
		System.out.println(Arrays.toString(price));
		System.out.println(Arrays.toString(span));
	}

}
