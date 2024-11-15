package com.navysu.java.basic.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Leetcode 128 longest consecutive sequence
 * https://leetcode.com/problems/longest-consecutive-sequence/description/
 *
 * Given an unsorted array of integers nums, return the length of the longest
 * consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */

public class LongestConsecutiveSequence {

    public static void main(String[] args) {

        int[] nums = { 100, 4, 200, 1, 3, 2 };
        System.out.println(longestConsecutive(nums)); // Output: 4

        nums = new int[] { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };
        System.out.println(longestConsecutive(nums)); // Output: 9

    }

    /**
     * Given an unsorted array of integers, return the length of the longest
     * consecutive elements sequence.
     *
     * Put all numbers into a set. For each number, find the left and right
     * neighbors. If the left neighbor is not in the set, go left until it is.
     * If the right neighbor is not in the set, go right until it is.
     * Update left and right to include the current number.
     * Update longest to the maximum of longest and right - left + 1.
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param nums an array of integers
     * @return the length of the longest consecutive elements sequence
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int longest = 1;
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int left = Integer.MIN_VALUE;
        int right = Integer.MAX_VALUE;
        for (int num : numSet) {
            if (left != Integer.MIN_VALUE && num >= left && num <= right) {
                // skip used numbers
                continue;
            }
            left = num - 1;
            right = num + 1;
            while (numSet.contains(left)) {// go small
                left--;
            }
            while (numSet.contains(right)) { // go big
                right++;
            }
            // update left and right to include the current number
            left++;
            right--;
            longest = Math.max(longest, right - left + 1);
        }
        return longest;

    }
}