package org.algos4j.stack;

/**
 * A test class to test the largest rectangle area in a given histogram bars.
 * 
 * @author psajja
 *
 */
public class LargeRectAreaOfHistogramTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 int[] bars = {6, 2, 5, 4, 5, 1, 6};
		 System.out.println("Max area possible: " + StackUtil.getMaxRectangleArea(bars));
	}

}
