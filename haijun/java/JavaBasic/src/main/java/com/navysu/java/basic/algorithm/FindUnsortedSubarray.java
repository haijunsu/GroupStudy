package com.navysu.java.basic.algorithm;

import java.util.Arrays;

/**
 * leetcode 581 Shortest Unsorted Continuous Subarray
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 *
 * Given an integer array nums, you need to find one continuous subarray such
 * that if you only sort this subarray in non-decreasing order, then the whole
 * array will be sorted in non-decreasing order.
 *
 * Return the shortest such subarray and output its length.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the
 * whole array sorted in ascending order.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 *
 *
 * Follow up: Can you solve it in O(n) time complexity?
 */

public class FindUnsortedSubarray {

    /**
     * Finds the shortest unsorted continuous subarray such that if you only sort
     * this subarray in non-decreasing order, then the whole array will be sorted
     * in non-decreasing order.
     *
     * This method uses the following approach: first, it makes a copy of the
     * array, sorts it and then iterates through the array to find the start and
     * end of the shortest unsorted subarray. The start is the first element that
     * is not equal to the sorted array and the end is the last element that is
     * not equal to the sorted array. If there is no such subarray, it returns 0.
     * Otherwise, it returns the end - start + 1.
     *
     * @param nums the input array
     * @return the length of the shortest unsorted subarray
     */
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int start = 0, end = n - 1;
        while (start < n && nums[start] == sorted[start]) {
            start++;
        }
        while (end >= 0 && nums[end] == sorted[end]) {
            end--;
        }
        return start < end ? end - start + 1 : 0;
    }

    /**
     * Finds the shortest unsorted continuous subarray such that if you only sort
     * this subarray in non-decreasing order, then the whole array will be sorted
     * in non-decreasing order.
     *
     * This method uses the following approach: first, it finds the rightmost
     * element that is not in the correct position by iterating through the array
     * from left to right. Then it finds the leftmost element that is not in the
     * correct position by iterating through the array from right to left. The
     * length of the subarray is the difference between the two positions plus
     * one. If there is no such subarray, it returns 0.
     *
     * @param nums the input array
     * @return the length of the shortest unsorted subarray
     */
    public int findUnsortedSubarray2(int[] nums) {
        int right = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < max) {
                right = i;
            } else {
                max = nums[i];
            }
        }
        int left = right;
        int min = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > min) {
                left = i;
            } else {
                min = nums[i];
            }
        }
        return right > left ? right - left + 1 : 0;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 6, 4, 8, 10, 9, 15 };
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray(nums));
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray2(nums));

        nums = new int[] { 1, 2, 3, 4 };
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray(nums));
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray2(nums));

        nums = new int[] { 1 };
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray(nums));
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray2(nums));

        nums = new int[] { 1, 3, 2, 3, 4, 5 };
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray(nums));
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray2(nums));
        nums = new int[] { 1, 2, 3, 5, 4, 7 };
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray(nums));
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray2(nums));
        nums = new int[] { 1, 3, 5, 4, 7, 2 };
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray(nums));
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray2(nums));
    }

}
