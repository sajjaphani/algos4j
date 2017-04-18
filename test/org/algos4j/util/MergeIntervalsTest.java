package org.algos4j.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.algos4j.util.Array2dUtil.Interval;

/**
 * This class tests the merging of overlapping intervals.
 * 
 * @author psajja
 *
 */
public class MergeIntervalsTest {

	public static void main(String[] args) {
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(6, 8));
		intervals.add(new Interval(1,9));
		intervals.add(new Interval(2,4));
		intervals.add(new Interval(4,7));
		System.out.println();
		System.out.println("Input: " + Arrays.toString(intervals.toArray(new Interval[0])));;

		System.out.println();
		List<Interval> mergedIntervals = Array2dUtil.mergeIntervals(intervals);
		System.out.println(Arrays.toString(mergedIntervals.toArray(new Interval[0])));;
		
		mergeIntervals1();
	}

	private static void mergeIntervals1() {
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(6, 8));
		intervals.add(new Interval(1,9));
		intervals.add(new Interval(2,4));
		intervals.add(new Interval(4,7));
		System.out.println();
		System.out.println("Input: " + Arrays.toString(intervals.toArray(new Interval[0])));;

		System.out.println();
		List<Interval> mergedIntervals = Array2dUtil.mergeIntervals1(intervals);
		System.out.println(Arrays.toString(mergedIntervals.toArray(new Interval[0])));;		
	}
}
