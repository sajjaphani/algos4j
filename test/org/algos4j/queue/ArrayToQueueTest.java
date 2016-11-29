package org.algos4j.queue;

import java.util.Arrays;

/**
 * Tests the queue creation from an array of elements.
 * 
 * @author psajja
 *
 */
public class ArrayToQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[]  elements = {10, 20, 30, 40};
		IntQueue queue = QueueUtil.createQueue(elements);
		System.out.println("Array");
		System.out.println(Arrays.toString(elements));
		System.out.println("Queue");
		System.out.println(queue);
	}

}
