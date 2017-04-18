package org.algos4j.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Some utilities on two dimensional arrays and also related to matrices.
 * 
 * @author psajja
 *
 */
public class Array2dUtil {

	/**
	 * Not-instantiable
	 */
	private Array2dUtil() {
	}
	
	/**
	 * Given an array of two dimensional, it prints the elements in row and column.
	 * 
	 * @param array
	 * 		given array
	 * 
	 * @throws NullPointerException
	 * 		if the given array is null
	 */
	public static void print(int[][] array) {
		if (array == null)
			throw new NullPointerException("Array should not be null.");
		StringBuilder sb = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		sb.append("[");
		for (int i = 0; i < array.length; i++) {
			sb.append(newLine);
			for (int j = 0; j < array[i].length; j++)
				sb.append(" " + array[i][j]);
		}
		sb.append(newLine + "]");
		System.out.println(sb.toString());
	}
	
	/**
	 * Given an array (nXn), this method rotates the elements by 90 degrees. It
	 * rotates in clock-wise direction. It does nothing if it is nXn array.
	 * Time: O(n^2)
	 * 
	 * @param array
	 *   	given integer array
	 * 
	 * @throws NullPointerException
	 *   	if the given array is null
	 */
	public static void rotate(int[][] array) {
		if (array == null)
			throw new NullPointerException("Array should not be null.");

		// TODO we need to check for every row
		if (array.length == 0 || array.length != array[0].length)
			return;

		for (int layer = 0; layer < array.length / 2; layer++) {
			int first = layer;
			int last = array.length - 1 - layer;
			for (int i = first; i < last; i++) {
				int offset = i - first;
				// Save Top
				int top = array[first][i];

				// Move Left to Top
				array[first][i] = array[last - offset][first];

				// Move Bottom to Left
				array[last - offset][first] = array[last][last - offset];

				// Move Right to Bottom
				array[last][last - offset] = array[i][last];

				// Move Top to Right
				array[i][last] = top;
			}
		}
	}
	
	/**
	 * Given an array (mXn), this method nullifies (zeroes) the rows and columns if 
	 * any of the row or column having a zero in it. Time: O(n^2), Space O(n).
	 * 
	 * @param array
	 *   	given integer array
	 * 
	 * @throws NullPointerException
	 *   	if the given array is null
	 */
	public static void nullify(int[][] array) {
		if (array == null)
			throw new NullPointerException("Array should not be null.");

		boolean[] row = new boolean[array.length];
		boolean[] column = new boolean[array[0].length];

		// Identify the rows and columns to be nullified
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				if (array[i][j] == 0) {
					row[i] = true;
					column[j] = true;
				}
			}
		}

		// Nullify rows
		for (int i = 0; i < row.length; i++) {
			if (row[i])
				nullifyRow(array, i);
		}

		// Nullify columns
		for (int i = 0; i < column.length; i++) {
			if (column[i])
				nullifyColumn(array, i);
		}
	}

	/**
	 * Zeroes the given row elements.
	 * 
	 * @param array
	 * 		given 2d array
	 * @param row
	 * 		given row
	 */
	private static void nullifyRow(int[][] array, int row) {
		for (int i = 0; i < array[0].length; i++)
			array[row][i] = 0;
	}

	/**
	 * Zeroes the given column elements.
	 * 
	 * @param array
	 * 		given 2d array
	 * @param row
	 * 		given row
	 */
	private static void nullifyColumn(int[][] array, int col) {
		for (int i = 0; i < array.length; i++)
			array[i][col] = 0;
	}
	
	/**
	 * Given an array (mXn), this method nullifies (zeroes) the rows and columns if 
	 * any of the row or column having a zero in it. Time: O(n^2), Space O(n).
	 * This is space optimized version of {@link #nullify(int[][])}.
	 * 
	 * @param array
	 *   	given integer array
	 * 
	 * @throws NullPointerException
	 *   	if the given array is null
	 */
	public static void nullify1(int[][] array) {
		if (array == null)
			throw new NullPointerException("Array should not be null.");

		boolean rowHasZero = false;
		boolean colHasZero = false;

		// Check if first row has a zero
		for (int j = 0; j < array[0].length; j++) {
			if (array[0][j] == 0) {
				rowHasZero = true;
				break;
			}
		}

		// Check if first column has a zero
		for (int i = 0; i < array.length; i++) {
			if (array[i][0] == 0) {
				colHasZero = true;
				break;
			}
		}

		// If array[i][j], set array[i][0] and array[0][j] to zero
		for (int i = 1; i < array.length; i++) {
			for (int j = 1; j < array[0].length; j++) {
				if (array[i][j] == 0) {
					array[i][0] = 0;
					array[0][j] = 0;
				}
			}
		}

		// Nullify each row if there's a zero in array[i][0]
		for (int i = 1; i < array.length; i++) {
			if (array[i][0] == 0) {
				nullifyRow(array, i);
			}
		}

		// Nullify each column if there's a zero in array[0][i]
		for (int i = 1; i < array[0].length; i++) {
			if (array[0][i] == 0) {
				nullifyColumn(array, i);
			}
		}

		// Nullify first row, if necessary
		if (rowHasZero)
			nullifyRow(array, 0);

		// Nullify first column, if necessary
		if (colHasZero)
			nullifyColumn(array, 0);
	}
	
	/**
	 * Given a m X n matrix, which contains 0s and 1s, this method computes the
	 * max region of 1s that is in the array. Based on DFS.
	 * 
	 * @param array
	 *   	given matrix
	 * 
	 * @return 
	 * 		the max region
	 */
	public static int maxRegion(int[][] array) {
		// TODO check array or any of the row is null
		// TODO validate data for only 0s and 1s

		int maxRegion = 0;
		int[][] matrix = array.clone();
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				if (matrix[row][col] == 1) {
					maxRegion = Math.max(getResionSize(matrix, row, col), maxRegion);
				}
			}
		}

		return maxRegion;
	}

	/**
	 * Recursively computes the region starting from given row and column.
	 * 
	 * @param matrix
	 * 		given matrix
	 * @param row
	 * 		current row
	 * @param col
	 * 		current column
	 * 
	 * @return
	 * 		region starting from [row][col]
	 */
	private static int getResionSize(int[][] matrix, int row, int col) {
		if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[row].length)
			return 0;
		
		if (matrix[row][col] == 0)
			return 0;
		
		matrix[row][col] = 0;
		int size = 0;
		for (int r = row - 1; r <= row + 1; r++) {
			for (int c = col - 1; r <= col + 1; c++) {
				if(r != row || c != col)
					size += getResionSize(matrix, r, c);
			}
		}

		return size;
	}
	
	/**
	 * Given a 2d array of nXn size, which holds the relation knows(a, b) which
	 * holds either true or false, this method will find the celebrity if any.
	 * In a party of n people, there may exists only one celebrity and is known
	 * to everyone and celebrity doesn't know anyone. Uses Stack, Time: O(n).
	 * 
	 * @param array
	 * 		given array holds relation knows(a, b)
	 * 
	 * @return
	 * 		celebrity if exists, -1 otherwise
	 */
	static int findCelebrity1(boolean[][] array) {
		if (array == null)
			throw new NullPointerException("Input array cannot be null.");
		// TODO check for nXn size

		Stack<Integer> s = new Stack<>();

		int candidate;

		for (int i = 0; i < array.length - 1; i++)
			s.push(i);

		int person1 = s.pop();
		int person2 = s.pop();

		while (s.size() > 1) {
			if (knows(array, person1, person2)) {
				person1 = s.pop();
			} else {
				person2 = s.pop();
			}
		}

		// Potential candidate?
		candidate = s.pop();

		// person1 or person2 or candidate can be a celebrity
		if (knows(array, candidate, person2))
			candidate = person2;

		if (knows(array, candidate, person1))
			candidate = person1;

		// is 'candidate' celebrity?
		for (int i = 0; i < array.length; i++) {
			if ((i != candidate) && (knows(array, candidate, i) || !knows(array, i, candidate)))
				return -1;
		}

		return candidate;
	}
	
	/**
	 * Returns true if a knows b, false otherwise.
	 * 
	 * @param array
	 * 		given array holds relation knows(a, b)
	 * 
	 * @param a
	 * 		person a
	 * @param b
	 * 		person b
	 * 
	 * @return
	 * 		true if a knows b, false otherwise
	 */
	private static boolean knows(boolean[][] array, int a, int b) {
		return array[a][b];
	}
	
	/**
	 * Given a 2d array of nXn size, which holds the relation knows(a, b) which
	 * holds either true or false, this method will find the celebrity if any.
	 * In a party of n people, there may exists only one celebrity and is known
	 * to everyone and celebrity doesn't know anyone. No extra space, Time: O(n).
	 * 
	 * @param array
	 * 		given array holds relation knows(a, b)
	 * 
	 * @return
	 * 		celebrity if exists, -1 otherwise
	 */
	public static int findCelebrity(boolean[][] array) {
		if (array == null)
			throw new NullPointerException("Input array cannot be null.");
		// TODO check for nXn size

		int person1 = 0;
		int person2 = array.length - 1;

		while (person1 < person2) {
			if (knows(array, person1, person2))
				person1++;
			else
				person2--;
		}

		// We can use 'person1' or 'person2'
		for (int i = 0; i < array.length; i++) {
			if ((i != person1) && (knows(array, person1, i) || !knows(array, i, person1)))
				return -1;
		}

		return person1;
	}
	
	/**
	 * Represents an interval.
	 * 
	 * @author psajja
	 *
	 */
	static class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "{" + start + ", " + end + "}";
		}
	}
	
	/**
	 * Given a list of intervals, this method merges the overlapping intervals.
	 * Time: O(n log n), Space: O(n)
	 * 
	 * @param intervals
	 * 		list of interval objects
	 * 
	 * @return
	 * 		the intervals after merging overlapping once
	 */
	static List<Interval> mergeIntervals(List<Interval> intervals) {
		if (intervals == null)
			throw new NullPointerException("Input intervals can not be null.");

		if (intervals.size() < 2)
			return intervals;

		// Sort based on increasing order of start interval
		Collections.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start)
					return i1.start - i2.start;
				else
					return i1.end - i2.end;
			}
		});

		Stack<Interval> s = new Stack<>();

		s.push(intervals.get(0));

		for (int i = 1; i < intervals.size(); i++) {
			Interval top = s.peek();

			if (top.end < intervals.get(i).start) {
				s.push(intervals.get(i));
			} else if (top.end < intervals.get(i).end) {
				top.end = intervals.get(i).end;
			}
		}

		List<Interval> mergedIntervals = new ArrayList<>();

		while (!s.empty())
			mergedIntervals.add(s.pop());

		return mergedIntervals;
	}
	
	/**
	 * Given a list of intervals, this method merges the overlapping intervals.
	 * Time: O(n log n)
	 * 
	 * @param intervals
	 * 		list of interval objects
	 * 
	 * @return
	 * 		the intervals after merging overlapping once
	 */
	static List<Interval> mergeIntervals1(List<Interval> intervals) {
		if (intervals == null)
			throw new NullPointerException("Input intervals can not be null.");

		if (intervals.size() < 2)
			return intervals;

		// Sort based on decreasing order of start interval
		Collections.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start)
					return i2.start - i1.start;
				else
					return i1.end - i2.end;
			}
		});

		List<Interval> mergedIntervals = new ArrayList<>();

		for (int i = 0, index = 0; i < intervals.size(); i++, index++) {
			if (index != 0 && intervals.get(index - 1).start <= intervals.get(i).end) {
				while (index != 0 && intervals.get(index - 1).start <= intervals.get(i).end) {
					// Merge the overlapping intervals
					mergedIntervals.get(index - 1).end = Math.max(mergedIntervals.get(index - 1).end, intervals.get(i).end);
					mergedIntervals.get(index - 1).start = Math.min(mergedIntervals.get(index - 1).start, intervals.get(i).start);
					index--;
				}
			} else {
				mergedIntervals.add(intervals.get(i));
			}
		}

		return mergedIntervals;
	}
}