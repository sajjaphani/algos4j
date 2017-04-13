package org.algos4j.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests converting a list of integers to any int array.
 * 
 * @author psajja
 *
 */
public class ListToArrayTest {

	public static void main(String[] args) {
		
		List<Integer> values = new ArrayList<>();
		values.add(null);
		
		System.out.println(Arrays.toString(ArrayUtil.toArray(values)));
	}
}
