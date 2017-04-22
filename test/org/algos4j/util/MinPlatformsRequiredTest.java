package org.algos4j.util;

/**
 * Given arrivals and departure times, this class tests the min number of platforms required.
 * 
 * @author psajja
 *
 */
public class MinPlatformsRequiredTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arrivals = { 900, 940, 950, 1100, 1500, 1800 };
		int[] departures = { 910, 1200, 1120, 1130, 1900, 2000 };
		
		System.out.println("Min Platforms: " + ArrayUtil.minPlatformsRequired(arrivals, departures));
	}

}
