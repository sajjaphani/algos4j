package org.algos4j.util;

import java.util.Arrays;

/**
 * Given an array of ropes, this class tests connecting them with min cost.
 * 
 * @author psajja
 *
 */
public class ConnectRopesMinCostTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] ropes = { 4, 3, 2, 6 };
		System.out.println("Ropes: " + Arrays.toString(ropes));
		System.out.println("Cost to connect: " + ArrayUtil.getCostToConnect(ropes));
	}

}
