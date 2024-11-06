package com.navysu.java.basic.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Leetcode 56 Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all
 * overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 */

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        System.out.println(Arrays.deepToString(merge(intervals)));
        intervals = new int[][] { { 1, 4 }, { 4, 5 } };
        System.out.println(Arrays.deepToString(merge(intervals)));
        intervals = new int[][] { { 1, 4 }, { 2, 3 } };
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    public static int[][] merge(int[][] intervals) {

        if (intervals.length <= 1) {
            return intervals;
        }

        // 1. sort
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        // 2. merge
        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[index][1]) {
                intervals[index][1] = Math.max(intervals[index][1], intervals[i][1]);
            } else {
                index++;
                intervals[index] = intervals[i];
            }
        }

        return Arrays.copyOfRange(intervals, 0, index + 1);
    }

}
