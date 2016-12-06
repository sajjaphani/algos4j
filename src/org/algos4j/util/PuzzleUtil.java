package org.algos4j.util;

/**
 * Some puzzles.
 * 
 * @author psajja
 * 
 */
public class PuzzleUtil {

	/**
	 * A recursive version of hanoi puzzle. 
	 * 
	 * @param disks
	 * 		number of disks
	 * 
	 * @throws IllegalArgumentException
	 * 		if the number of disks is not valid (<=0)
	 */
	public static void hanoi(int disks) {
		if (disks <= 0)
			throw new IllegalArgumentException("Invalid disks: " + disks);
		hanoi(disks, 'A', 'B', 'C');
	}

	/**
	 * Recursively moves the disks from source to destination.
	 * 
	 * @param disks
	 * 		disk number
	 * @param from
	 * 		from disk
	 * @param aux
	 * 		auxiliary disk
	 * @param to
	 * 		to disk
	 */
	private static void hanoi(int disks, char from, char aux, char to) {
		if (disks == 1) {
			System.out.println("Disk 1 from " + from + " to " + to);
		} else {
			hanoi(disks - 1, from, to, aux);
			System.out.println("Disk " + disks + " from " + from + " to " + to);
			hanoi(disks - 1, aux, from, to);
		}
	}
}
