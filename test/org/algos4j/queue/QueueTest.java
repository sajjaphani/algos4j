package org.algos4j.queue;

import org.algos4j.queue.ext.Queue;

/**
 * Test class for testing <code>Queue</code> class.
 * 
 * @author psajja
 *
 */
public class QueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<String> queue = new Queue<>();
		queue.insert("Queue");
		queue.insert("Stack");
		queue.insert("Tree");
		System.out.println("Current Queue");
		System.out.println(queue);
		System.out.println("Removed Element : " + queue.remove());
		queue.insert("Graph");
		System.out.println("Queue Now");
		System.out.println(queue);
		System.out.println("Queue Size: " + queue.size());
		System.out.println("Front Elt: " + queue.getFront());
	}

}
