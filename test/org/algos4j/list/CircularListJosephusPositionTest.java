package org.algos4j.list;

/**
 * This test class tests computation of leader using Josephus approach.
 * 
 * @author psajja
 *
 */
public class CircularListJosephusPositionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("No of persons: " + 5 + ", Skip in iteration: " + 2);
		System.out.println("Using Circular Linked List");
		System.out.println("Josephus Position: " + CircularListUtil.getJosephusPosition(5, 2));
		System.out.println();
		System.out.println("Using a mathematical formula");
		System.out.println("Josephus Position With Formula: " + CircularListUtil.getJosephusPosition1(5, 2));
	}
}
