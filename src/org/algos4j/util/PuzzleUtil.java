package org.algos4j.util;

import org.algos4j.stack.StackUtil;

/**
 * Some puzzles.
 * 
 * @author psajja
 * 
 */
public class PuzzleUtil {

	/**
	 * A recursive version of hanoi puzzle. See other variant at
	 * {@link StackUtil#hanoi(int)}
	 * 
	 * @param disks
	 */
	public static void hanoi(int disks) {
		if (disks <= 0)
			throw new IllegalArgumentException("Invalid disks: " + disks);
		hanoi(disks, 'A', 'B', 'C');
	}

	/**
	 * @param disks
	 * @param c
	 * @param d
	 * @param e
	 */
	private static void hanoi(int disks, char from, char inter, char to) {
		if (disks == 1) {
			System.out.println("Disk 1 from " + from + " to " + to);
		} else {
			hanoi(disks - 1, from, to, inter);
			System.out.println("Disk " + disks + " from " + from + " to " + to);
			hanoi(disks - 1, inter, from, to);
		}
	}

}
