package org.algos4j.queue;

/**
 * A test class for reversing the first k elements in a queue.,
 * 
 * @author psajja
 *
 */
public class QueueReverseByKTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		IntQueue queue = new IntQueue(5);
		queue.insert(10);
		queue.insert(20);
		queue.insert(30);
		queue.insert(40);
		queue.insert(50);
		System.out.println("Queue Before");
		System.out.println(queue);
		QueueUtil.reverse(queue, 3);
		System.out.println("Queue Now");
		System.out.println(queue);
	}
}
