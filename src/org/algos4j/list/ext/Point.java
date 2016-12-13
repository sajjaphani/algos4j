package org.algos4j.list.ext;

import java.util.Comparator;

/**
 * Represents immutable point.
 * 
 * @author psajja
 *
 */
class Point {

	private final int x;
	private final int y;

	/**
	 * Initialize with the given data
	 * 
	 * @param x
	 * 		x coordinate
	 * @param y
	 * 		y coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + x;
		hash = hash * 31 + y;

		return hash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Point))
			return false;
		Point p = (Point) obj;

		return x == p.x && y == p.y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	/**
	 * A Comparator that orders <code>Point</code> objects based on their
	 * coordinates x followed by y.
	 *
	 */
	public static final Comparator<Point> POINT_COMPARATOR = new PointComparator();

	/**
	 * Point comparator.
	 * 
	 * @author psajja
	 *
	 */
	private static class PointComparator implements Comparator<Point> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Point p1, Point p2) {
			int xComp = Integer.compare(p1.x, p2.x);
			if (xComp != 0)
				return xComp;

			return Integer.compare(p1.y, p2.y);
		}
	}
}
