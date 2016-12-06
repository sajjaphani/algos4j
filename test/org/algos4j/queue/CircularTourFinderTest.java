package org.algos4j.queue;

import org.algos4j.queue.ext.CircularTourFinder;

/**
 * A test class to test finding of a circular tour possibility given petrol station data.
 * 
 * @author psajja
 * 
 */
public class CircularTourFinderTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] data = { { 6, 4 }, { 3, 6 }, { 7, 3 } };

		CircularTourFinder tourFinder = new CircularTourFinder();
		int startingPoint = tourFinder.findStartingPoint(data);
		if (startingPoint == -1)
			System.out.println("Circular tour is not possible");
		else
			System.out.println("Circular tour can be found strting from : "
					+ startingPoint);
	}
}
