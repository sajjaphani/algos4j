package org.algos4j.queue;

import org.algos4j.queue.ext.RotFruits;

/**
 * A test class to test whether all fruits can be rotten or not.
 * 
 * @author psajja
 * 
 */
public class RotFruitsTest {

	/**
	 * Entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		int[][] data = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 },
//				{ 1, 0, 0, 2, 1 } };
		
		int[][] data = { { 2, 1, 0, 2, 1 }, { 0, 0, 1, 2, 1 },
				{ 1, 0, 0, 2, 1 } };
		
		RotFruits rotter = new RotFruits();
		int times = rotter.rotFruits(data);
		if (times == -1)
			System.out.println("All fruits cannot be rotten");
		else
			System.out.println("Time required to rot all fruits: " + times
					+ " iterations");
	}
}
