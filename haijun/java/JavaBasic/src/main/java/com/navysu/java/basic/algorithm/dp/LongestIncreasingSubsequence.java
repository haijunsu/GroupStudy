package com.navysu.java.basic.algorithm.dp;

/**
 * Leetcode 300 Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Given an integer array nums, return the length of the longest strictly
 * increasing
 * subsequence
 * .
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the
 * length is 4.
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 */

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(nums));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS2(nums));

        nums = new int[] { 0, 1, 0, 3, 2, 3 };
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(nums));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS2(nums));

        nums = new int[] { 7, 7, 7, 7, 7, 7, 7 };
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(nums));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS2(nums));

        nums = new int[] { 1, 3, 6, 7, 9, 4, 10, 5, 6 };
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(nums));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS2(nums));

    }

    /**
     * Finds the length of the longest increasing subsequence in the given array.
     * The basic idea is to use dynamic programming to build up an array dp, where
     * dp[i] is the length of the longest increasing subsequence ending at the
     * index i. The dp array is built up by iterating through the input array and
     * finding the maximum value of dp[i] such that nums[j] < nums[i] for all j <
     * i. The answer is the maximum value in the dp array.
     *
     * This Java method calculates the length of the Longest Increasing Subsequence
     * (LIS) in a given array of integers.
     *
     * Here's a succinct explanation:
     *
     * It initializes an array dp of the same length as the input array, where dp[i]
     * will store the length of the longest increasing subsequence ending at index
     * i.
     * It iterates through the array, and for each element, it checks all previous
     * elements. If a previous element is smaller, it updates dp[i] to be the
     * maximum of its current value and the length of the subsequence ending at the
     * previous element plus one.
     * It keeps track of the maximum length of the subsequence found so far in the
     * variable ans.
     * Finally, it returns the length of the longest increasing subsequence.
     * This algorithm has a time complexity of O(n^2), where n is the length of the
     * input array.
     *
     * @param nums the input array
     * @return the length of the longest increasing subsequence
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * Finds the length of the longest increasing subsequence in the given array.
     * This method uses binary search to improve the time complexity of the dp
     * approach. The dp array is used to store the longest increasing subsequence
     * ending at each index. For each element in the array, it performs a binary
     * search on the dp array to find the position where the element should be
     * inserted. If the element is greater than the last element in the dp array,
     * it increases the length of the longest increasing subsequence by one.
     * Otherwise, it replaces the element in the dp array at the position found
     * by the binary search.
     *
     * This Java method, lengthOfLIS2, finds the length of the longest increasing
     * subsequence (LIS) in a given array nums. It uses a dynamic programming (DP)
     * approach with binary search to achieve a time complexity of O(n log n).
     *
     * Here's a step-by-step breakdown:
     *
     * It initializes an array dp of the same length as nums and a variable ans to
     * store the length of the LIS.
     * It iterates through nums. For each element, it performs a binary search on dp
     * to find the position where the element should be inserted.
     * If the element is greater than the last element in dp, it increases the
     * length of the LIS by one.
     * Otherwise, it replaces the element in dp at the position found by the binary
     * search.
     * Finally, it returns the length of the LIS, which is stored in ans.
     * This approach ensures that dp always contains the smallest tail of each
     * subsequence length, allowing for efficient binary search and update
     * operations.
     * This algorithm has a time complexity of O(n log n), where n is the length of
     * the input array.
     *
     * @param nums the input array
     * @return the length of the longest increasing subsequence
     */
    public int lengthOfLIS2(int[] nums) {
        int ans = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int start = 0;
            int end = ans;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (dp[mid] < nums[i]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            dp[end] = nums[i];
            if (end == ans) {
                ans++;
            }
        }
        return ans;
    }

}
