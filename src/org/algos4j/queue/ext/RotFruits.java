package org.algos4j.queue.ext;

/**
 * You are given a matrix of size m*n, where each cell in the matrix can
 * have values 0,1 or 2 which represents:
 * 
 * <pre>
 * 	0:	An empty cell
 * 	1:	Cell have fresh fruit
 * 	2:	Cell have rotten fruit
 * </pre>
 * 
 * Devise an algorithm to determine the minimum iterations required to rot all the fresh
 * fruits. A rotten fruit at index [i,j] can rot other fresh
 * fruits at indexes [i+1,j], [i,j+1], [i-1,j], [i,j-1]. 
 * 
 * @author psajja
 * 
 */
public class RotFruits {

	/**
	 * Determines the iterations required to rot all fruits.
	 * If it is not possible to rot every fruit then it simply return -1.
	 * <p>Time: O(m*n), Space: O(m*n)</p>
	 * 
	 * @param fruitsData
	 * 		fruits data
	 * 
	 * @return
	 * 		iterations required to rot fruits, -1 if every fruit can not be rotten
	 */
	public int rotFruits(int[][] fruitsData) {
		if(fruitsData == null)
			throw new NullPointerException("Input data should not be null.");
		
		int rows = fruitsData.length;
		int cols = rows != 0 ? fruitsData[0].length : 0;
	
		validate(fruitsData, cols);
		
		Queue<Cell> queue = new Queue<>();
		
		 // Initially store all the rotten ones
		for (int i = 0; i < fruitsData.length; i++) {
			for (int j = 0; j < fruitsData[i].length; j++) {
				if (fruitsData[i][j] == 2) {
					queue.insert(new Cell(i, j));
				}
			}
		}
		
		// add a delimiter
		queue.insert(new Cell(-1, -1));
	    Cell temp = null;
		int times = 0;
		
		while (!queue.isEmpty()) {

			// Flag to increment iterations once per delimiter
			boolean incremented = false;

			while (!isDelimiter(queue.getFront())) {
				temp = queue.getFront();

				// Check right cell
				if ((temp.i + 1 >= 0 && temp.j >= 0 && temp.i + 1 < rows && temp.j < cols) && fruitsData[temp.i + 1][temp.j] == 1) {
					if (!incremented) {
						times++;
						incremented = true;
					}

					fruitsData[temp.i + 1][temp.j] = 2;
					queue.insert(new Cell(temp.i + 1, temp.j));
				}

				// Check left cell
				if ((temp.i - 1 >= 0 && temp.j >= 0 && temp.i - 1 < rows && temp.j < cols) && fruitsData[temp.i - 1][temp.j] == 1) {
					if (!incremented) {
						times++;
						incremented = true;
					}
					fruitsData[temp.i - 1][temp.j] = 2;
					queue.insert(new Cell(temp.i - 1, temp.j));
				}

				// Check top cell
				if ((temp.i >= 0 && temp.j + 1 >= 0 && temp.i < rows && temp.j + 1 < cols) && fruitsData[temp.i][temp.j + 1] == 1) {
					if (!incremented) {
						times++;
						incremented = true;
					}
					fruitsData[temp.i][temp.j + 1] = 2;
					queue.insert(new Cell(temp.i, temp.j + 1));
				}

				// Check bottom cell
				if ((temp.i >= 0 && temp.j - 1 >= 0 && temp.i < rows && temp.j - 1 < cols) && fruitsData[temp.i][temp.j - 1] == 1) {
					if (!incremented) {
						times++;
						incremented = true;
					}
					fruitsData[temp.i][temp.j - 1] = 2;
					queue.insert(new Cell(temp.i, temp.j - 1));
				}

				queue.remove();
			}

			// remove the delimiter
			queue.remove();

			// Starting next iteration
			if (!queue.isEmpty()) {
				queue.insert(new Cell(-1, -1));
			}
		}
		
		return rottenAll(fruitsData) ? times: -1;
	}
	
	/**
	 * Check whether the given cell represents a delimiter.
	 * 
	 * @param cell
	 * 		cell to check
	 * 
	 * @return
	 * 		true if it is a delimiter, false otherwise
	 */
	private boolean isDelimiter(Cell cell) {
		return cell.i == -1 && cell.j == -1;
	}
	
	/**
	 * Check whether all the fruits are rotten.
	 * 
	 * @param fruitsData
	 * 		fruit data
	 * 
	 * @return
	 * 		true if all the fruits are rotten, false otherwise
	 */
	private boolean rottenAll(int[][] fruitsData) {
		for (int[] fruitData : fruitsData) {
			for (int item : fruitData) {
				if (item == 1)
					return false;
			}
		}
		return true;
	}

	/**
	 * Further validate the input data.
	 * 
	 * @param fruitsData
	 * 		fruits data
	 * @param cols
	 * 		number of columns allowed per row
	 * 
	 * @throws NullPointerException
	 * 		if any of the row given is null
	 * @throws IllegalArgumentException
	 * 		if columns are not same in each row or if the data in a cell is not (0, 1 or 2)
	 */
	private void validate(int[][] fruitsData, int cols) {
		for(int[] fruitData : fruitsData) {
			if(fruitData == null)
				throw new NullPointerException("Row in input data should not be null.");
			if(fruitData.length != cols)
				throw new IllegalArgumentException("Expected columns: " + cols + " found: " + fruitData.length);
			for(int item : fruitData) {
				if(item < 0 || item > 2)
					throw new IllegalArgumentException("Data in cells should be 0, 1, or 2.");
			}
		}
	}

	/**
	 * Represents a cell in the 2d array.
	 * 
	 * @author psajja
	 *
	 */
	static class Cell {
		
		final int i;
		final int j;
		
		Cell(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}