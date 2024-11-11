package com.navysu.java.basic.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode 39 Combination Sum
 * https://leetcode.com/problems/combination-sum/
 *
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen
 * numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the
 * frequency
 * of at least one of the chosen numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that
 * sum up to target is less than 150 combinations for the given input.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple
 * times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 *
 * Input: candidates = [2], target = 1
 * Output: []
 */

public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;
        System.out.println(combinationSum(candidates, target));

        candidates = new int[] { 2, 3, 5 };
        target = 8;
        System.out.println(combinationSum(candidates, target));

        candidates = new int[] { 2 };
        target = 1;
        System.out.println(combinationSum(candidates, target));

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        if (candidates[0] > target) {
            return result;
        }
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * Recursively generate combinations of numbers in candidates that sum up to
     * target.
     * <p>
     * The base cases are when target is 0 (add current combination to result) or
     * target is negative (stop
     * exploring current branch).
     * <p>
     * The main idea is to pick each candidate number from index to the end of the
     * array and recursively call
     * backtrack with the remaining target and the current combination. After the
     * recursive call, the last element
     * is removed from the current combination to backtrack.
     * <p>
     * Note that the loop starts from index to avoid duplicate combinations (i.e.,
     * [2, 2, 3] and [2, 3, 2]).
     */
    private static void backtrack(int[] candidates, int target, int index, List<Integer> list,
            List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = index; i < candidates.length; i++) { // Notes: i starts with index instead of zero to avoid
                                                          // duplicate combination.
            list.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, list, result);
            list.remove(list.size() - 1);
        }
    }

}
