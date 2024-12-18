package com.navysu.java.basic.algorithm;

/**
 * Leetcode 169 Majority Element
 * https://leetcode.com/problems/majority-element/
 *
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 *
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 */

public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = { 3, 2, 3 };
        System.out.println(majorityElement(nums));
        nums = new int[] { 2, 2, 1, 1, 1, 2, 2 };
        System.out.println(majorityElement(nums));
    }

    /**
     * Finds the majority element from given array.
     *
     * The majority element is the element that appears more than ⌊n / 2⌋ times.
     *
     * The algorithm works by essentially maintaining a counter for the majority
     * element. If the counter is zero, the algorithm simply sets the current
     * element as the majority element. If the counter is not zero, the algorithm
     * increments the counter if the current element is the same as the majority
     * element and decrements the counter if it is not. At the end of the loop,
     * the majority element is the element that the counter is keeping track of.
     *
     * @param nums the given array
     * @return the majority element from the given array
     */
    public static int majorityElement(int[] nums) {
        int result = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) { // If count is 0, initialize result to current element.
                result = nums[i];
            }
            if (nums[i] == result) {
                count++;
            } else {
                count--;
            }
        } // At the end of the loop, result is the majority element.
        return result;
    }
}
