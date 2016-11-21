package org.algos4j.heap;

/**
 * Represents a binary heap. a heap is a complete binary tree.
 * The insertions and deletion of elements into the heap 
 * depends on the implementation, which can be either max or min heap.
 * 
 * @author psajja
 *
 */
public interface BinaryHeap<E> {

	/**
	 * Checks whether the heap is empty.
	 * 
	 * @return
	 * 		true if this heap is empty, false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Returns the current size of the heap.
	 * 
	 * @return
	 * 		heap size
	 */
	public int size();

	/**
	 * Adds an element into the heap.
	 * 
	 * @param e
	 * 		element to add
	 */
	public void add(E e);

	/**
	 * Removes and returns the top of the heap.
	 * 
	 * @return
	 * 		top element of the heap
	 * 
	 * @throws IllegalStateException
	 * 		if the heap is empty.
	 */
	public E remove();

	/**
	 * Returns the top of the heap.
	 * 
	 * @return
	 * 		top of the heap
	 * 
	 * @throws IllegalStateException
	 * 		if the heap is empty.
	 */
	public E peek();
}
