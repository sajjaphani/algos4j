package org.algos4j.heap;

/**
 * Maximum binary heap, the top element is the maximum element in the heap.
 * <p>The elements of the heap must be {@link Comparable}</p>.
 * 
 * @author psajja
 *
 */
public class MaxBinaryHeap<E extends Comparable<E>> extends AbstractBinaryHeap<E> {

	/**
	 * Initialize the heap with a default capacity.
	 */
	public MaxBinaryHeap() {
		super();
	}
	
	/**
	 * Initialize the heap with the initial capacity.
	 * 
	 * @param initialCapacity
	 * 		initial capacity
	 * 
	 * @throws IllegalArgumentException
	 * 		if the initial capacity is -ve
	 */
	public MaxBinaryHeap(int initialCapacity) {
		super(initialCapacity);
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.heap.AbstractBinaryHeap#heapifyUp(int, java.lang.Comparable)
	 */
	@Override
	protected void heapifyUp(int pos, E e) {
		E key = e;
		while (pos > 0) {
			int parent = (pos - 1) >>> 1;
			@SuppressWarnings("unchecked")
			E cur = (E) elements[parent];
			if (key.compareTo(cur) <= 0)
				break;
			elements[pos] = cur;
			pos = parent;
		}
		elements[pos] = key;
	}

	/* (non-Javadoc)
	 * @see org.algos4j.heap.AbstractBinaryHeap#heapifyDown(int, java.lang.Comparable)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void heapifyDown(int pos, E e) {
		E key = e;
		int half = size() >>> 1;
		while (pos < half) {
			int lpos = (pos << 1) + 1;
			E left = (E) elements[lpos];
			int rpos = lpos + 1;
			E right = (E) elements[rpos];
			if (rpos < size() && left.compareTo(right) < 0)
				left = (E) elements[lpos = rpos];
			if (key.compareTo(left) >= 0)
				break;
			elements[pos] = left;
			pos = lpos;
		}
		elements[pos] = key;
	}

}
