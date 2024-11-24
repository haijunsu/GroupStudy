package com.navysu.java.basic.algorithm;

/**
 * leetcode 416 Partition Equal Subset Sum
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * Given an integer array nums, return true if you can partition the array into
 * two subsets such that the sum of the elements in both subsets is equal or
 * false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */

public class PartitionArray {

    /**
     * Checks if it is possible to partition the given array into two subsets
     * such that the sum of the elements in both subsets is equal.
     * <p>
     * The basic idea is to use a dynamic programming approach. The dp array is
     * used to keep track of whether a sum is possible. dp[i] is true if the sum
     * i is possible using the elements of the array. The dp array is filled up
     * from dp[0] to dp[target], where target is the half of the total sum of
     * the array.
     * <p>
     * The time complexity is O(n*target), where n is the length of the array
     * and target is the half of the total sum of the array. The space complexity
     * is O(target).
     * <p>
     * Note: The backtrack implementation is time time exceeding.
     *
     * @param nums the given array
     * @return true if the array can be partitioned, false otherwise
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        PartitionArray partitionArray = new PartitionArray();
        System.out.println(partitionArray.canPartition(new int[] { 1, 5, 11, 5 })); // true
        System.out.println(partitionArray.canPartition(new int[] { 1, 2, 3, 5 })); // false
    }
}
