package com.navysu.java.basic.algorithm;

import java.util.Arrays;

/**
 * leetcode 239. Sliding Window Maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 * Given an array nums, there is a sliding window of size k which is moving from
 * the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one
 * position. Return the max sliding window.
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position Max
 * --------------- -----
 * [1 3 -1] -3 5 3 6 7 3
 * 1 [3 -1 -3] 5 3 6 7 3
 * 1 3 [-1 -3 5] 3 6 7 5
 * 1 3 -1 [-3 5 3] 6 7 5
 * 1 3 -1 -3 [5 3 6] 7 6
 * 1 3 -1 -3 5 [3 6 7] 7
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * Example 3:
 * Input: nums = [1,-1], k = 1
 * Output: [1,-1]
 * Example 4:
 * Input: nums = [9,11], k = 2
 * Output: [11]
 * Example 5:
 * Input: nums = [4,-2], k = 2
 * Output: [4]
 */

public class MaxSlidingWindow {

    public static void main(String[] args) {

        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));

        nums = new int[] { 1 };
        k = 1;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));

        nums = new int[] { 1, -1 };
        k = 1;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));

        nums = new int[] { 9, 11 };
        k = 2;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));

        nums = new int[] { 4, -2 };
        k = 2;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length - k + 1; i++) {
            result[index++] = maxInSlideWindow(nums, i, i + k - 1);
        }
        return result;
    }

    public static int maxInSlideWindow(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}
