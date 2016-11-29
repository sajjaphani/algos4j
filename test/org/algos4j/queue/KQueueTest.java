package org.algos4j.queue;

/**
 * A test class for testing <code>KQueue</code>
 * 
 * @author psajja
 *
 */
public class KQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		KQueue queue = new KQueue(3, 9);
		System.out.println("Initial Queues");
		System.out.println(queue);
		System.out.println("Adding 10, 15, 20 to queue 0");
		queue.insert(10, 0);
		queue.insert(15, 0);
		queue.insert(20, 0);
		
		System.out.println("Adding 100 to queue 1");
		queue.insert(100, 1);
		System.out.println("Adding 1000 to queue 2");
		queue.insert(1000, 2);
		System.out.println(queue);
		System.out.println("Removing front of queue 0");
		queue.remove(0);
		System.out.println("Adding 250 to queue 1");
		queue.insert(250,  1);
		System.out.println("Queues now");
		System.out.println(queue);
	}
}
