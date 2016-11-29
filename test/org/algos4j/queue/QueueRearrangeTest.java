package org.algos4j.queue;

/**
 * Tests the queue creation from an array of elements.
 * 
 * @author psajja
 *
 */
public class QueueRearrangeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[]  elements = {10, 20, 30, 40, 15, 25};
		IntQueue queue = QueueUtil.createQueue(elements);
		System.out.println("Queue Before");
		System.out.println(queue);
		QueueUtil.rearrange(queue);
		System.out.println("Queue After");
		System.out.println(queue);
	}
}
