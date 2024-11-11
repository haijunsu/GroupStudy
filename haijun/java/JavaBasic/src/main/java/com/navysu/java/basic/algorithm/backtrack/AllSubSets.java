package com.navysu.java.basic.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 78 Subsets
 * https://leetcode.com/problems/subsets/
 *
 * Given an integer array nums of unique elements, return all possible
 * subsets
 * (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in
 * any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */

public class AllSubSets {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> result = subsets(nums);
        System.out.println(result);

        nums = new int[] { 0 };
        result = subsets(nums);
        System.out.println(result);

    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int index, List<Integer> list, List<List<Integer>> result) {
        result.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            backtrack(nums, i + 1, list, result);
            list.remove(list.size() - 1);
        }
    }

}
