package org.algos4j.tree;

import org.algos4j.tree.BinaryTree.BTNode;

/**
 * Represents a data structure to reserve future jobs.
 * <ul>
 * <li>Each job requires k time units of the machine.</li>
 * <li>The machine can process single job at a time.</li>
 * <li>Job reservation is done only if there is no existing job within k time frame.</li>
 * <li>Whenever a job finishes it is removed.</li>
 * </ul>
 * 
 * Uses <code>BinarySearchTree</code>
 * 
 * @author psajja
 *
 */
public class ResourceAllocator {

	private BinaryTree bt;
	private int k;
	
	// TODO set running time, implement remove etc.
	
	/**
	 * Initialize the resource allocator with the given time frame.
	 * 
	 * @param k
	 * 		time required to finish a job
	 */
	public ResourceAllocator(int k) {
		if(k <= 0)
			throw new IllegalArgumentException("Invalid time frame of job.");
		
		this.k = k;
		bt = new BinarySearchTree();
	}
	
	/**
	 * Reserve a future time for the given time frame.
	 * 
	 * @param time
	 * 		future time for reservation
	 */
	public void insert(int time) {
		if(time <= 0)
			throw new IllegalArgumentException("Invalid time future time, must be > 0.");
		
		bt.root = insert(bt.root, time);
	}
	
	/**
	 * Recursively check and allocate time.
	 * 
	 * @param node
	 * 		current subtree root
	 * @param time
	 * 		reservation at time
	 * 
	 * @return
	 * 		new root after inserting the node
	 */
	private BTNode insert(BTNode node, int time) {
		if (node == null)
			return new BTNode(time);

		// Cannot make reservation
		if ((time - k < node.getData()) && (time + k > node.getData()))
			return node;

		if (time < node.getData())
			node.left = insert(node.left, time);
		else
			node.right = insert(node.right, time);

		return node;
	}
}
