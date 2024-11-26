package com.navysu.java.basic.algorithm;

import java.util.Arrays;

/**
 * leetcode 238 Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Given an integer array nums, return an array answer such that answer[i] is
 * equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
 * integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the
 * division operation.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
 * integer.
 *
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The
 * output array does not count as extra space for space complexity analysis.)
 */

public class ProductExceptSelf {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        System.out.println(Arrays.toString(productExceptSelf(nums)));
        nums = new int[] { -1, 1, 0, -3, 3 };
        System.out.println(Arrays.toString(productExceptSelf(nums)));
        nums = new int[] { 0, 0, 0, 0, 0 };
        System.out.println(Arrays.toString(productExceptSelf(nums)));
        nums = new int[] { 1 };
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    /**
     * Returns an array such that the i-th element is the product of all the numbers
     * in the given array except the number at the i-th index.
     *
     * This method uses the following approach: it iterates through the given array
     * from left to right and from right to left, during which it keeps track of the
     * product of all the elements to the left and right of the current element,
     * and multiplies the two products together to get the final product of all
     * the elements except the current element.
     *
     * @param nums the given array
     * @return the array of products of all the elements except the element at the
     *         same index
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = result[i] * right;
            right = right * nums[i];
        }
        return result;
    }
}
