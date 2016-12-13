package org.algos4j.list.ext;

import org.algos4j.list.ext.LinkedList.Node;

/**
 * This class processes the point coordinates. The primary function of this is
 * to remove the middle coordinates, where adjacent points either form a
 * vertical line or a horizontal line.
 * 
 * @author psajja
 *
 */
public class PointCoordinatesHandler {

	/**
	 * Default constructor.
	 */
	public PointCoordinatesHandler() {
	}
	
	/**
	 * Entry point. Given a 2d array represents the point coordinates, it
	 * removes the middle points that form a vertical or horizontal line.
	 * 
	 * @param points
	 * 		2d array containing points
	 * 
	 * @return
	 * 		returns the remaining coordinates
	 */
	public int[][] process(int[][] points) {
		 LinkedList<Point> linkedList = constructList(points);
		 removeMiddlePoints(linkedList);
		 int length = linkedList.size();
		 int[][] newPoints = new int[length][2];
		 Node<Point> head = linkedList.getHead();
		 int i =0;
		 while(head != null) {
			 newPoints[i++] = new int[]{head.getData().getX(), head.getData().getY()};
			 head = head.next;
		 }
		 
		 return newPoints;
	}
	
	/**
	 * Given a linked list points it removes all the middle points where there
	 * is no deviation from x or y to the next element.
	 * 
	 * @param linkedList
	 *            the linked list.
	 */
	private void removeMiddlePoints(LinkedList<Point> linkedList) {
		if (linkedList == null)
			throw new NullPointerException("Linked list cannot be null.");
		Node<Point> head = linkedList.getHead();

		linkedList.setHead(removeMiddlePoints(head));
	}

	/**
	 * Recursively remove the middle points.
	 * 
	 * @param head
	 *    	the current head
	 * 
	 * @return 
	 * 		new head
	 * 
	 * @throws IllegalArgumentException
	 * 		if the coordinates does not follow either x or y direction
	 */
	private Node<Point> removeMiddlePoints(Node<Point> head) {
		// base case
		if (head == null || head.next == null || head.next.next == null)
			return head;

		Node<Point> next = head.next;
		Node<Point> nextNext = next.next;

		if (head.getData().getX() == next.getData().getX()) {
			// Remove nodes with same x coordinate
			while (nextNext != null && next.getData().getX() == nextNext.getData().getX()) {
				head.next = next.next;
				next = nextNext;
				nextNext = nextNext.next;
			}
		} else if (head.getData().getY() == next.getData().getY()) {
			// Remove nodes with same y coordinate
			while (nextNext != null && next.getData().getY() == nextNext.getData().getY()) {
				head.next = next.next;
				next = nextNext;
				nextNext = nextNext.next;
			}
		} else {
			throw new IllegalArgumentException("Illegal adjacent points, should be equal to 'x' or 'y'");
		}

		Node<Point> temp = head;
		head = head.next;

		// Proceed to the next segment
		removeMiddlePoints(head);

		head = temp;

		return head;
	}

	/**
	 * Construct a linked list from the array of points.
	 * 
	 * @param points
	 *            the points
	 * 
	 * @return linked list constructed.
	 * 
	 * @throws IllegalArgumentException
	 *             if any of the point size is not 2
	 */
	private LinkedList<Point> constructList(int[][] points) {
		LinkedList<Point> list = new LinkedList<>();
		for (int[] point : points) {
			if (point.length != 2)
				throw new IllegalArgumentException("Point length must be 2");
			list.insertLast(new Point(point[0], point[1]));
		}
		
		return list;
	}
}
