package org.algos4j.queue;

/**
 * Test class for testing <code>IntQueueWithStack</code> class.
 * 
 * @author psajja
 *
 */
public class IntQueueWithStackTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntQueueWithStack queue = new IntQueueWithStack(5);
		queue.insert(10);
		queue.insert(20);
		queue.insert(30);
		System.out.println("Current Queue");
		System.out.println(queue);
		System.out.println("Removed Element : " + queue.remove());
		queue.insert(50);
		queue.insert(45);
		System.out.println("Queue Now");
		System.out.println(queue);
	}
}
