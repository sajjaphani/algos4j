package org.algos4j.queue;

/**
 * Test class for testing <code>IntQueue</code> class.
 * 
 * @author psajja
 *
 */
public class IntQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntQueue queue = new IntQueue(5);
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
		System.out.println("Queue Size: " + queue.size());
		System.out.println("Front Elt: " + queue.getFront() + ", Rear Elt: " + queue.getRear());
	}

}
