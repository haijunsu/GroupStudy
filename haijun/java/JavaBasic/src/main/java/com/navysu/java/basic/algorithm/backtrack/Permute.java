package com.navysu.java.basic.algorithm.backtrack;

import java.util.Arrays;

/**
 * Leetcode 46 Permutations
 * https://leetcode.com/problems/permutations/
 *
 * Given an array nums of distinct integers, return all the possible
 * permutations
 * . You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 */

public class Permute {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        permute(nums);

        nums = new int[] { 0, 1 };
        permute(nums);

        nums = new int[] { 1 };
        permute(nums);
    }

    public static void permute(int[] nums) {
        int n = nums.length;
        boolean[] used = new boolean[n];
        backtrack(nums, used, new int[n], 0);
    }

    private static void backtrack(int[] nums, boolean[] used, int[] result, int index) {
        if (index == nums.length) {
            System.out.println(Arrays.toString(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                result[index] = nums[i];
                backtrack(nums, used, result, index + 1);
                used[i] = false;
            }
        }
    }
}
