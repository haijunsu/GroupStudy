package com.navysu.java.basic.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 560 Subarray Sum Equals K
 * https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * Given an array of integers nums and an integer k, return the total number of
 * subarrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */

public class SubArraySum {

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1 };
        int k = 2;
        System.out.println(new SubArraySum().subarraySum(nums, k));

        nums = new int[] { 1, 2, 3 };
        k = 3;
        System.out.println(new SubArraySum().subarraySum(nums, k));
        nums = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
        k = 0;
        System.out.println(new SubArraySum().subarraySum(nums, k));
    }

    /**
     * Finds the total number of subarrays whose sum equals to k.
     *
     * Uses a HashMap to keep track of the prefix sums of the array, and
     * their counts. Then iterates through the array, and for each prefix sum,
     * it checks if the difference between the prefix sum and the target k
     * is present in the HashMap. If it is, then it adds the count of such
     * prefix sums to the result.
     *
     * @param nums the input array
     * @param k    the target sum
     * @return the total number of subarrays whose sum equals to k
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
