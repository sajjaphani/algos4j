package org.algos4j.heap;

/**
 * A test class for min binary heap.
 * 
 * @author psajja
 *
 */
public class MinBinaryHeapTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {10, 15, 30, 25, 50, 40, 24};
		BinaryHeap<Integer> minHeap = new MinBinaryHeap<>();
		for(int elt : elements) {
			minHeap.add(elt);
		}
		System.out.println("MinHeap now");
		System.out.println(minHeap);
		System.out.println("Removed: " + minHeap.remove());
		System.out.println("MinHeap now");
		System.out.println(minHeap);
	}
}
