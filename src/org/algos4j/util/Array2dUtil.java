package org.algos4j.util;

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
}