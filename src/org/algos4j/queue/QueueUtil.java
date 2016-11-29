package org.algos4j.queue;

import java.util.Deque;
import java.util.LinkedList;

import org.algos4j.stack.IntStack;

/**
 * Utility methods using Queue data structure.
 * 
 * @author psajja
 *
 */
public class QueueUtil {

	/**
	 * Not-instantiable
	 */
	private QueueUtil () {
	}
	
	/**
	 * Given an array, it creates a queue.
	 * Order of inserting into queue from array is first to last.
	 * 
	 * @param elements
	 * 		input array
	 * 
	 * @return
	 * 		created queue
	 * 
	 * @throws NullPointerException
	 * 		if the array is null
	 */
	public static IntQueue createQueue(int[] elements) {
		if (elements == null)
			throw new NullPointerException("Input array cannot be null.");

		IntQueue queue = new IntQueue(elements.length);

		for (int element : elements)
			queue.insert(element);
		
		return queue;
	}
	
	/**
	 * Given a queue, it reverses the elements. 
	 * Uses Stack as intermediate data structure.
	 * 
	 * @param queue
	 * 		given queue
	 * 
	 * @throws NullPointerException
	 * 		if the queue is null
	 */
	public static void reverse(IntQueue queue) {
		if (queue == null)
			throw new NullPointerException("Input queue cannot be null.");

		if (queue.size() <= 1)
			return;

		IntStack stack = new IntStack(queue.size());
		while (!queue.isEmpty())
			stack.push(queue.remove());

		while (!stack.isEmpty())
			queue.insert(stack.pop());
	}
	
	/**
	 * Given a queue, rearrange the queue elements of the two half in interleaved fashion. 
	 * For example, {1, 2, 3, 4, 5, 6} becomes {1, 4, 3, 5, 3, 6}.
	 * <p>If the size of the queue is not multiple of 2 then 
	 * the above condition will not hold and results in a different sequence than expected.</p>
	 * 
	 * Time: O(n), Space: O(n)
	 * 
	 * @param queue
	 * 		queue holds integers
	 * 
	 * @throws NullPointerException
	 * 		if the queue is null
	 */
	public static void rearrange(IntQueue queue) {
		if (queue == null)
			throw new NullPointerException("Queue cannot be null.");
		if (queue.size() <= 1)
			return;
		IntStack stack = new IntStack(queue.size());
		int half = queue.size() / 2;
		for (int i = 0; i < half; i++)
			stack.push(queue.remove());
		while (!stack.isEmpty())
			queue.insert(stack.pop());
		for (int i = 0; i < half; i++)
			queue.insert(queue.remove());
		for (int i = 0; i < half; i++)
			stack.push(queue.remove());
		while (!stack.isEmpty()) {
			queue.insert(stack.pop());
			queue.insert(queue.remove());
		}
	}
	
	/**
	 * Given a queue, it reverses the first k elements. Uses Stack.
	 * Time: O(n), Space: O(k)
	 * 
	 * @param queue
	 * 		given queue
	 * @param k
	 * 		size to reverse
	 * 
	 * @throws NullPointerException
	 * 		if the queue is null
	 * 
	 * @throws IllegalArgumentException
	 * 		if the k is invalid  <= 0 or > queue size)
	 */
	public static void reverse(IntQueue queue, int k) {
		if (queue == null)
			throw new NullPointerException("Input queue cannot be null.");
		if (k <= 0 || k > queue.size())
			throw new IllegalArgumentException("Invalid k: " + k);

		if (k == 1)
			return;

		IntStack stack = new IntStack(k);
		for (int i = 0; i < k; i++)
			stack.push(queue.remove());

		while (!stack.isEmpty())
			queue.insert(stack.pop());

		// Move the rest of size - k elements to end
		for (int i = 0; i < queue.size() - k; i++)
			queue.insert(queue.remove());
	}
	
	/**
	 * Given an array of integers, and a window size w, 
	 * this method computes the maximum of sliding windows of size w.
	 * Time: O(n), Space: O(w)
	 * 
	 * @param array
	 * 		input array
	 * @param w
	 * 		window size
	 * 
	 * @return
	 * 		array containing max for each window size of w
	 * 
	 * @throws NullPointerException
	 * 		if the given array is null
	 * @throws IllegalArgumentException
	 * 		if the given window size is not valid (> 0 && <= array size)
	 */
	public static int[] computeSlidingWindowMax(int[] array, int w) {

		if (array == null)
			throw new NullPointerException("Array should not be null.");
		if (w <= 0 || w > array.length)
			throw new IllegalArgumentException(
					"Window size must be in the range of array size (> 0 && <= array size)");

		int[] slides = new int[array.length];

		// Deque holds the element indices, elements are in decreasing order
		Deque<Integer> deque = new LinkedList<>();

		// process initial w elements
		for (int i = 0; i < w; i++) {
			while (!deque.isEmpty() && array[i] >= array[deque.getLast()])
				deque.removeLast();
			deque.addLast(i);
		}

		for (int i = w; i < array.length; i++) {
			slides[i - w] = array[deque.getFirst()];

			// Remove all smaller elements than the current one
			while (!deque.isEmpty() && array[i] >= array[deque.getLast()])
				deque.removeLast();

			// Remove elements which are outside window range
			while (!deque.isEmpty() && deque.getFirst() <= i - w)
				deque.removeFirst();

			deque.addLast(i);
		}
		
		slides[array.length - w] = array[deque.removeFirst()];

		return slides;
	}
}
