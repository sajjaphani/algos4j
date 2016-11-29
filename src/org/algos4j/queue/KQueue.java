package org.algos4j.queue;

/**
 * A fixed length k queue implementation using a single array. 
 * Queue indices are zero based.
 * 
 * @author psajja
 *
 */
public class KQueue {

	private int[] elements;
	private int[] front;
	private int[] rear;
	private int[] next;
	
	private int free;
	private int queues, size;
	
	/**
	 * Creates instance.
	 * 
	 * @param queues
	 *            number of queues
	 * @param size
	 *            total number of elements
	 * 
	 * @throws IllegalArgumentException
	 *             if the size is less than queues is <= 0 or no.of queues
	 */
	public KQueue(int queues, int size) {
		if (queues <= 0 || size < queues)
			throw new IllegalArgumentException(
					"Check for no.of queues > 0 & size >= no.of queues");
		this.queues = queues;
		this.size = size;
		elements = new int[size];
		front = new int[queues];
		rear = new int[queues];
		next = new int[size];

		free = 0;
		
		init();
	}

	/**
	 * Initialize the front and next arrays.
	 */
	private void init() {
		for (int i = 0; i < queues; i++) {
			front[i] = -1;
		}
		for (int i = 0; i < size - 1; i++) {
			next[i] = i + 1;
		}
		// -1 to indicate end of free list
		next[size - 1] = -1;
	}
	
	/**
	 * Checks whether there is any room for queues.
	 * 
	 * @return true if there is no room for new elements, false otherwise
	 * 
	 */
	public boolean isFull() {
		return free == -1;
	}

	/**
	 * Checks whether the given queue is empty.
	 * 
	 * @param queueId
	 *            queue from
	 * 
	 * @return true if the given queue is empty, false otherwise
	 * 
	 * @throws IllegalArgumentException
	 *             if the queue id is invalid
	 */
	public boolean isEmpty(int queueId) {
		if (queueId < 0 || queueId >= queues)
			throw new IllegalArgumentException("Invalid queue specified: "
					+ queueId);
		return front[queueId] == -1;
	}
	
	/**
	 * Inserts an element in the specified queue.
	 * 
	 * @param element
	 *            element to insert
	 * @param queueId
	 *            queue id
	 * 
	 * @throws IllegalArgumentException
	 *             if the queue is invalid
	 * @throws IllegalStateException
	 *             if the specified queue is full
	 */
	public void insert(int element, int queueId) {
		if (queueId < 0 || queueId >= queues)
			throw new IllegalArgumentException("Invalid queue specified: "
					+ queueId);
		if (isFull())
			throw new IllegalStateException("Queue " + queueId + " is full");

	    int i = free;
	    free = next[i];
	 
	    if (isEmpty(queueId))
	        front[queueId] = i;
	    else
	        next[rear[queueId]] = i;
	 
	    next[i] = -1;
	    rear[queueId] = i;
	    elements[i] = element;
	}
	
	/**
	 * Removes and returns an element from the specified queue.
	 * 
	 * @param queueId
	 *            queue to remove an element from
	 * 
	 * @return removed element from the given queue
	 * 
	 * @throws IllegalArgumentException
	 *             if the queue is invalid
	 * @throws IllegalStateException
	 *             if the specified queue is empty
	 */
	public int remove(int queueId) {
		if (queueId < 0 || queueId >= queues)
			throw new IllegalArgumentException("Invalid queue specified: "
					+ queueId);
		if (isEmpty(queueId))
			throw new IllegalStateException("Queue " + queueId + " is empty");

		int i = front[queueId];

		front[queueId] = next[i];
		next[i] = free;
		free = i;

		return elements[i];
	}
	
	/**
	 * Returns front element from the specified queue with out removing.
	 * 
	 * @param queueId
	 *            queue from
	 * 
	 * @return front element from the given queue
	 * 
	 * @throws IllegalArgumentException
	 *             if the queue id is invalid
	 * @throws IllegalStateException
	 *             if the specified queue is empty
	 */
	public int peek(int queueId) {
		if (queueId < 0 || queueId >= queues)
			throw new IllegalArgumentException("Invalid queue specified: "
					+ queueId);
		if (isEmpty(queueId))
			throw new IllegalStateException("Queue " + queueId + " is empty");

		return elements[front[queueId]];
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Queues: [");
		sb.append(System.getProperty("line.separator"));
		sb.append("\tQueue 0: ");
		appendString(sb, 0);
		for (int i = 1; i < queues; i++) {
			sb.append(System.getProperty("line.separator"));
			sb.append("\tQueue " + i + ": ");
			appendString(sb, i);
		}
		sb.append(System.getProperty("line.separator"));
		sb.append("]");
		return sb.toString();
	}

	/**
	 * add string for each of the individual queue.
	 * 
	 * @param sb
	 * 		string builder to append
	 * 
	 * @param queueId
	 * 		queue id
	 */
	private void appendString(StringBuilder sb, int queueId) {
		if(isEmpty(queueId))
			sb.append("[]");
		else {
			sb.append("[");
			int stackTop = front[queueId];
			sb.append(elements[stackTop]);
			int nextFront = next[stackTop];
			while(nextFront != -1) {
				sb.append(", " + elements[nextFront]);
				nextFront = next[nextFront];
			}
			sb.append("]");
		}
	}
}