package org.algos4j.list.ext;

/**
 * A test class to test deleting middle points in given coordinates.
 * 
 * @author psajja
 *
 */
public class CoordinatesHandlerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[][] points = { { 0, 10 }, { 1, 10 }, { 5, 10 }, { 7, 10 }, { 7, 5 }, { 20, 5 }, { 40, 5 } };
		 // int[][] points = { { 2, 3 }, { 4, 3 }, { 6, 3 }, { 10, 3 }, { 12, 3 } };
		 PointCoordinatesHandler handler = new PointCoordinatesHandler();
		 int[][] newPoints = handler.process(points);
		 for(int[] point : newPoints) {
			 System.out.println("(" + point[0] + ", " + point[1] + ")");
		 }
	}
}
