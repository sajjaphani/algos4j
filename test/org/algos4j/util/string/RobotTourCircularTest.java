package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * Given a tour, this class tests whether tour made by robot is circular or not.
 * 
 * @author psajja
 *
 */
public class RobotTourCircularTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "GLGLGLG";
		System.out.println(path);
		if(StringUtil.isCircularTour(path))
			System.out.println("Tour is circular.");
		else
			System.out.println("Tour is not circular.");
	}

}
