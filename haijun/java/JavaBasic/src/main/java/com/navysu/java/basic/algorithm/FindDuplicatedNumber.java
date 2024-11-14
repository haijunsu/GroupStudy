package com.navysu.java.basic.algorithm;

/**
 * Leetcode 287. Find the Duplicate Number
 * https://leetcode.com/problems/find-the-duplicate-number/
 *
 * Given an array of integers nums containing n + 1 integers where each integer
 * is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and using only
 * constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * Example 3:
 *
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer
 * which appears two or more times.
 *
 */
public class FindDuplicatedNumber {

    public static void main(String[] args) {
        int[] nums = { 1, 3, 4, 2, 2 };
        System.out.println(new FindDuplicatedNumber().findDuplicate(nums)); // Output: 2

        nums = new int[] { 3, 1, 3, 4, 2 };
        System.out.println(new FindDuplicatedNumber().findDuplicate(nums)); // Output: 3

        nums = new int[] { 3, 3, 3, 3, 3 };
        System.out.println(new FindDuplicatedNumber().findDuplicate(nums)); // Output: 3
    }

    /**
     * This Java method finds the first duplicate in the given array of integers
     * nums.
     * It uses Floyd's Tortoise and Hare (Cycle Detection) algorithm to achieve
     * this.
     * The algorithm works by using two pointers, one of which moves twice as fast
     * as the other.
     * The two pointers will eventually meet at the start of the cycle, which is the
     * duplicate number.
     *
     * @param nums the given array of integers
     * @return the first duplicate in the given array of integers nums
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

}
