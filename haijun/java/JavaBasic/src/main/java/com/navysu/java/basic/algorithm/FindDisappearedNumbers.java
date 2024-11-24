package com.navysu.java.basic.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 448 Find All Numbers Disappeared in an Array
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 * Given an array nums of n integers where nums[i] is in the range [1, n],
 * return an array of all the integers in the range [1, n] that do not appear in
 * nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [2]
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 */

public class FindDisappearedNumbers {

    public static void main(String[] args) {
        int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };
        System.out.println(findDisappearedNumbers(nums)); // Output: [5, 6]
        System.out.println(findDisappearedNumbers2(nums)); // Output: [5, 6]

        nums = new int[] { 1, 1 };
        System.out.println(findDisappearedNumbers(nums)); // Output: [2]
        System.out.println(findDisappearedNumbers2(nums)); // Output: [2]
        nums = new int[] { 5, 4, 3, 2, 1 };
        System.out.println(findDisappearedNumbers(nums)); // Output: []
        System.out.println(findDisappearedNumbers2(nums)); // Output: []
    }

    /**
     * Finds all the numbers that are not present in the given array.
     *
     * This method uses the following approach: it iterates through the array and
     * marks the index that is equal to the absolute value of the current element
     * as visited by negating the value at that index. After that, it iterates
     * through the array again and collects all the elements that are still
     * positive into the result array. The result array is then returned.
     *
     * @param nums the given array
     * @return the array of all the numbers that are not present in the given
     *         array
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    /**
     * Finds all the numbers that are not present in the given array.
     *
     * This method uses the following approach: it iterates through the array and
     * swaps the element at the current index with the element at the index that
     * is equal to the value of the current element minus one. It then negates the
     * value at that index to mark it as visited. After that, it iterates through
     * the array again and collects all the elements that are still positive into
     * the result array. The result array is then returned.
     *
     * @param nums the given array
     * @return the array of all the numbers that are not present in the given
     *         array
     */
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            index = nums[i] - 1;
            if (index > i) {
                while (index > i) {
                    int tmp = nums[index] - 1;
                    nums[index] = -1;
                    index = tmp;
                }
            }
            if (index >= 0) {
                nums[index] = -1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}