package org.algos4j.heap;

/**
 * A test class for max binary heap.
 * 
 * @author psajja
 *
 */
public class MaxBinaryHeapTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] elements = {10, 15, 30, 25, 50, 40, 24};
		BinaryHeap<Integer> maxHeap = new MaxBinaryHeap<>();
		for(int elt : elements) {
			maxHeap.add(elt);
		}
		System.out.println("MaxHeap now");
		System.out.println(maxHeap);
		System.out.println("Removed: " + maxHeap.remove());
		System.out.println("MaxHeap now");
		System.out.println(maxHeap);
	}
}
