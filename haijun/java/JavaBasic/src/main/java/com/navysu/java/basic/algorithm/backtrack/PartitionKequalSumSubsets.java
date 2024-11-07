package com.navysu.java.basic.algorithm.backtrack;

import java.util.Arrays;

/**
 * Leetcode 698 Partition to K Equal Sum Subsets
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 *
 * Given an integer array nums and an integer k, return true if it is possible
 * to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3),
 * (2,3) with equal sums.
 * Example 2:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 */

public class PartitionKequalSumSubsets {

    public static void main(String[] args) {
        PartitionKequalSumSubsets p = new PartitionKequalSumSubsets();
        System.out.println(p.canPartitionKSubsets(new int[] { 4, 3, 2, 3, 5, 2, 1 }, 4));
        System.out.println(p.canPartitionKSubsets(new int[] { 1, 2, 3, 4 }, 3));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        int[] totals = new int[nums.length];
        return backtrack(nums, totals, nums.length - 1, target);
    }

    private boolean backtrack(int[] nums, int[] totals, int index, int target) {
        if (index == -1) {
            return true;
        }
        for (int i = 0; i < nums.length; i++) {
            if (totals[i] + nums[index] > target) {
                continue;
            }
            totals[i] += nums[index];
            if (backtrack(nums, totals, index - 1, target)) {
                return true;
            }
            totals[i] -= nums[index];

            if (totals[i] == 0) {
                break; // Skip duplicates
            }
        }
        return false;
    }

}
