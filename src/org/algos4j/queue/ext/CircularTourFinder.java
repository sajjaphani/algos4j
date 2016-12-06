package org.algos4j.queue.ext;

/**
 * Finds the circular tour that visits all petrol stations, given following
 * data.
 * <ul>
 * <li>The amount of petrol that every station has.</li>
 * <li>Distance from that station to the next one.</li>
 * </ul>
 * 
 * Assumption: Can cover 1 unit of distance for 1 unit of petrol.
 * 
 * @author psajja
 * 
 */
public class CircularTourFinder {

	/**
	 * Default-constructor.
	 */
	public CircularTourFinder() {
	}

	/**
	 * Given data a two dimensional array data it returns the the starting point
	 * where there is a circular tour is possible. The data in each row
	 * represented by the amount of petrol it has, and the distance to the next
	 * station.
	 * 
	 * @param data
	 *            input data
	 * 
	 * @return starting position if tour is possible, -1 otherwise.
	 * 
	 * @throws NullPointerException
	 *             if the data is null or one of the row data is null
	 * @throws IllegalArgumentException
	 *             if data in a row is not valid.
	 */
	public int findStartingPoint(int[][] data) {
		if (data == null)
			throw new NullPointerException("Input data cannot be null.");

		PetrolStationData[] stationData = prepareStationData(data);

		return findStartingPoint(stationData);
	}

	/**
	 * Constructs <code>PetrolStationData</code> from array data.
	 * 
	 * @param data
	 *            data to construct
	 * 
	 * @return object data
	 * 
	 * @throws NullPointerException
	 *             if any of the row data is null
	 * @throws IllegalArgumentException
	 *             if the given data is not valid
	 */
	private static PetrolStationData[] prepareStationData(int[][] data) {
		PetrolStationData[] stationData = new PetrolStationData[data.length];

		for (int i = 0; i < data.length; i++) {
			if (data[i] == null)
				throw new NullPointerException("Row data cannot be null.");
			if (data[i].length != 2)
				throw new IllegalArgumentException("Not a valid data, must be [capacity, distanceToNextStation]");
			if (data[i][0] < 0)
				throw new IllegalArgumentException("Capacity cannot be negative.");
			if (data[i][1] < 0)
				throw new IllegalArgumentException("Distance cannot be negative.");

			stationData[i] = new PetrolStationData(data[i][0], data[i][1]);
		}

		return stationData;
	}

	/**
	 * It computes and returns the starting point of circular tour.
	 * 
	 * @param stationData
	 *            data
	 * 
	 * @return starting point, -1 if tour is not possible
	 */
	private int findStartingPoint(PetrolStationData[] stationData) {

		// Start and end index of virtual queue
		int start = 0;
		int end = 1;

		int currentCapacity = stationData[start].capacity - stationData[start].distanceToNext;

		while (end != start || currentCapacity < 0) {

			while (currentCapacity < 0 && start != end) {
				currentCapacity -= stationData[start].capacity - stationData[start].distanceToNext;
				start = (start + 1) % stationData.length;

				if (start == 0)
					return -1;
			}

			currentCapacity += stationData[end].capacity - stationData[end].distanceToNext;
			end = (end + 1) % stationData.length;
		}

		return start;
	}

	/**
	 * Petrol station, consists of its capacity and distance to next one.
	 * 
	 * @author psajja
	 * 
	 */
	private static class PetrolStationData {

		int capacity;
		int distanceToNext;

		/**
		 * Initialize with the given data.
		 * 
		 * @param capacity
		 * 		station capacity
		 * @param distanceToNext
		 * 		distance to be coverted to reach next one
		 */
		PetrolStationData(int capacity, int distanceToNext) {
			this.capacity = capacity;
			this.distanceToNext = distanceToNext;
		}
	}
}
