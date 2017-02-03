package org.algos4j.queue;

import java.util.Arrays;

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
	 * For example, {1, 2, 3, 4, 5, 6} becomes {1, 4, 2, 5, 3, 6}.
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
	 * Given an array of numbers ranging from 0 to 9, this method computes the largest number 
	 * that can be formed by arranging these numbers and is divided by 3.
	 * Some of the digits may not be part of end result.
	 * 
	 * @return
	 * 		returns the largest number possible, 0 if not
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 * @throws IllegalArgumentException
	 * 		if any of the array entry is not a digit
	 */
	public static int findLargestMultipleOf3(int[] numbers) {
		if (numbers == null)
			throw new NullPointerException("Array should not be null.");
	
		if(numbers.length == 0)
			return 0;
		
		validate(numbers);
	
		Arrays.sort(numbers);
		
		IntQueue queue0 = new IntQueue(numbers.length);
		IntQueue queue1 = new IntQueue(numbers.length);
		IntQueue queue2 = new IntQueue(numbers.length);
		
		int sum = 0;
	    for (int i = 0; i < numbers.length; ++i ) {
	        sum += numbers[i];
	        if ( (numbers[i] % 3) == 0 )
	            queue0.insert(numbers[i]);
	        else if ( (numbers[i] % 3) == 1 )
	        	queue1.insert(numbers[i]);
	        else
	        	queue2.insert(numbers[i]);
	    }
	    
		if ((sum % 3) == 1) {
			if (!queue1.isEmpty())
				queue1.remove();
			else {
				if (!queue2.isEmpty()) {
					queue2.remove();
					if (!queue2.isEmpty())
						queue2.remove();
					else
						return 0;
				} else
					return 0;
			}
		} else if ((sum % 3) == 2) {
			if (!queue2.isEmpty())
				queue2.remove();
			else {
				if (!queue1.isEmpty()) {
					queue1.remove();
					if (!queue1.isEmpty())
						queue1.remove();
					else
						return 0;
				} else
					return 0;
			}
		}
	    
		int[] resultArray = new int[queue0.size() + queue2.size() + queue2.size()];
		int i =0;
		
		while(!queue0.isEmpty())
			resultArray[i++] = queue0.remove();
		
		while(!queue1.isEmpty())
			resultArray[i++] = queue1.remove();
		
		while(!queue2.isEmpty())
			resultArray[i++] = queue2.remove();
		
		Arrays.sort(resultArray);
		
		int result = 0;
		int n = resultArray.length;
		
		for (i = 0; i < n; i++)
			result = 10 * result + resultArray[n - i - 1];
		
		return result;
	}

	/**
	 * Check whether each array number is a single digit.
	 * 
	 * @param numbers
	 * 		given numbers
	 * 
	 * @throws IllegalArgumentException
	 * 		if the given numbers are not digits
	 */
	private static void validate(int[] numbers) {
		for(int number : numbers) 
			if(number < 0 || number > 9)
				throw new IllegalArgumentException("Invalid number found: " + number);
	}
}
