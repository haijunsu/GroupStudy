package com.navysu.java.basic.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 494 Target Sum
 * https://leetcode.com/problems/target-sum/
 *
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+'
 * and '-' before each integer in nums and then concatenate all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1
 * and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which
 * evaluates to target.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be
 * target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * Example 2:
 *
 * Input: nums = [1], target = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class FindTargetSumWays {

    public static int findTargetSumWays2(int[] nums, int target) {
        List<Integer> result = new ArrayList<Integer>();
        findSumWay(nums, target, 0, result);
        return result.size();
    }

    public static void findSumWay(int[] nums, int target, int index, List<Integer> result) {
        if (index == nums.length) {
            if (target == 0) {
                result.add(1);
            }
            return;
        }
        findSumWay(nums, target - nums[index], index + 1, result);
        findSumWay(nums, target + nums[index], index + 1, result);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 1, 1 };
        int target = 3;
        System.out.println(findTargetSumWays2(nums, target)); // Output: 5

        nums = new int[] { 1 };
        target = 1;
        System.out.println(findTargetSumWays2(nums, target)); // Output: 1
        nums = new int[] { 1, 2, 3 };
        target = 4;
        System.out.println(findTargetSumWays2(nums, target)); // Output: 1
        nums = new int[] { -1, -1, 1, 1, 1 };
        target = 3;
        System.out.println(findTargetSumWays2(nums, target)); // Output: 5
    }

}
