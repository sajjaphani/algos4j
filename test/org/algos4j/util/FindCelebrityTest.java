package org.algos4j.util;

/**
 * This class tests finding the celebrity.
 * 
 * @author psajja
 *
 */
public class FindCelebrityTest {

	public static void main(String[] args) {
		boolean[][] array = { { false, false, true, false }, { false, false, true, false },
				{ false, false, false, false }, { false, false, true, false } };

		int celebrity = Array2dUtil.findCelebrity1(array);
		if (celebrity == -1)
			System.out.println("No Celebrity Found!");
		else
			System.out.println("Celebrity: " + celebrity);
		
		System.out.println();
		int celebrity1 = Array2dUtil.findCelebrity(array);
		if (celebrity1 == -1)
			System.out.println("No Celebrity Found!");
		else
			System.out.println("Celebrity: " + celebrity1);
	}
}
