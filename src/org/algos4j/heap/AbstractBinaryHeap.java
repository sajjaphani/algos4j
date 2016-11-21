package org.algos4j.heap;

import java.util.Arrays;

/**
 * An abstract array based binary heap implementation.
 * 
 * @author psajja
 *
 */
public abstract class AbstractBinaryHeap<E extends Comparable<E>> implements BinaryHeap<E> {
	
	protected static final int DEFAULT_CAPACITY = 16;
	protected Object[] elements;
	private int size;
	
	/**
	 * Initialize the heap with a default capacity.
	 */
	public AbstractBinaryHeap() {
		this(DEFAULT_CAPACITY);
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
	protected AbstractBinaryHeap(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);

		elements = new Object[initialCapacity];;
		
	}

	/* (non-Javadoc)
	 * @see org.algos4j.heap.BinaryHeap#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/* (non-Javadoc)
	 * @see org.algos4j.heap.BinaryHeap#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see org.algos4j.heap.BinaryHeap#add(java.lang.Object)
	 */
	@Override
	public void add(E e) {
        if (e == null)
            throw new NullPointerException("Element cannot be null");
        
        if (size == elements.length)
            grow();
        if (size == 0)
            elements[0] = e;
        else
            heapifyUp(size, e);
        size = size + 1;
	}
	
	/**
	 * Heapify upwards and insert the given element.
	 * 
	 * @param pos
	 * 		likely position
	 * @param e
	 * 		element to insert
	 */
	protected abstract void heapifyUp(int pos, E e);

	/* (non-Javadoc)
	 * @see org.algos4j.heap.BinaryHeap#remove()
	 */
	@Override
	public E remove() {
		if (isEmpty())
			throw new IllegalStateException("Heap is empty.");
		size = size - 1;
		@SuppressWarnings("unchecked")
		E result = (E) elements[0];
		@SuppressWarnings("unchecked")
		E e = (E) elements[size];
		elements[size] = null;
		if (size != 0)
			heapifyDown(0, e);
		if (size < (elements.length >> 1))
            shrink();
		return result;
	}
	
	/**
	 * Heapify downwards and insert the given element.
	 * 
	 * @param pos
	 * 		likely position
	 * @param e
	 * 		element to insert
	 */
	protected abstract void heapifyDown(int pos, E e);
	
	/* (non-Javadoc)
	 * @see org.algos4j.heap.BinaryHeap#peek()
	 */
	@Override
	public E peek() {
		if (isEmpty())
			throw new IllegalStateException("Heap is empty.");
		@SuppressWarnings("unchecked")
		E result = (E) elements[0];
		return result;
	}
	
	/**
     * Increases the capacity of the heap.
     *
     */
	private void grow() {
		int newCapacity = size + (size >> 1);
	
		if (newCapacity < 0) // Overflow
			throw new OutOfMemoryError("Cannot grow the size of heap.");
		
		newCapacity = newCapacity > 0 ? newCapacity : DEFAULT_CAPACITY;

		elements = Arrays.copyOf(elements, newCapacity);
	}
	
	/**
     * Decreases the capacity of the heap.
     *
     */
	private void shrink() {
		int newCapacity = elements.length - (elements.length >> 1);

		if (newCapacity <= DEFAULT_CAPACITY) // maintain minimum default size
			return;
		
		// maintain half of additional size
		if (newCapacity <= (size + size >> 1))
			return;

		elements = Arrays.copyOf(elements, newCapacity);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (isEmpty())
			return "[]";
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(elements[0]);
		for (int i = 1; i < size; i++)
			sb.append(", " + elements[i]);
		sb.append("]");
		return sb.toString();
	}
}
