package com.navysu.java.basic.algorithm;

/**
 * Leetcode 152 Maximum Product Subarray
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Given an integer array nums, find a
 * subarray
 * that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
 */

public class MaxProductSubArray {

    public static void main(String[] args) {
        int[] nums = { 2, 3, -2, 4 };
        System.out.println(maxProduct(nums)); // 6
        System.out.println(maxProduct2(nums)); // 6

        nums = new int[] { -2, 0, -1 };
        System.out.println(maxProduct(nums)); // 0
        System.out.println(maxProduct2(nums)); // 0
    }

    /**
     * Finds the maximum product of a contiguous subarray within the given integer
     * array.
     *
     * This method uses a dynamic programming approach to calculate the maximum
     * product
     * by keeping track of the maximum and minimum products up to the current
     * element,
     * taking into account that multiplying by a negative number can swap the
     * maximum
     * and minimum products.
     *
     * @param nums the array of integers to evaluate
     * @return the maximum product of any contiguous subarray
     */
    public static int maxProduct(int[] nums) {
        int result = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(nums[i], nums[i] * max);
            min = Math.min(nums[i], nums[i] * min);
            System.out.println("max: " + max + ", min: " + min);
            result = Math.max(result, max);
        }
        return result;
    }

    /**
     * product straightforward max and reverse order products max.
     *
     * @param nums
     * @return
     */
    public static int maxProduct2(int[] nums) {
        int result = Integer.MIN_VALUE;
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            product *= nums[i];
            result = Math.max(result, product);
            if (product == 0) {
                product = 1;
            }
        }
        product = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            product *= nums[i];
            result = Math.max(result, product);
            if (product == 0) {
                product = 1;
            }
        }
        return result;
    }
}
