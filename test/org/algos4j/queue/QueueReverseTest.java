package org.algos4j.queue;

import org.algos4j.queue.IntQueue;
import org.algos4j.queue.QueueUtil;

/**
 * Tests the reverse of the queue elements.
 * 
 * @author psajja
 *
 */
public class QueueReverseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {2, 5, 3, 7, 8, 6};
		IntQueue queue = QueueUtil.createQueue(elements);
		queue.remove();
		queue.insert(2);
		System.out.println("Queue Before");
		System.out.println(queue);
		QueueUtil.reverse(queue);
		System.out.println("Queue After");
		System.out.println(queue);
	}
}
