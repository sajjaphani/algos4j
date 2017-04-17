package org.algos4j.util;

import java.util.Arrays;

/**
 * This method tests finding three numbers with the given sum in an array.
 * 
 * @author psajja
 *
 */
public class Find3NumbersTest {

	public static void main(String[] args) {
		int[] array = {1, 4, 45, 6, 10, 8};
        System.out.println("Array: " + Arrays.toString(array));

        int[] numbers = ArrayUtil.find3Numbers(array, 22);
		if(numbers.length == 0)
			System.out.println("Threre are no elements that sum upt to 22");
		System.out.println("Numbers: " + Arrays.toString(numbers));
	}
}
