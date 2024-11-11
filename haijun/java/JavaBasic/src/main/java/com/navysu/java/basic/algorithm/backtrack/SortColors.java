package com.navysu.java.basic.algorithm.backtrack;

import java.util.Arrays;

/**
 * Leetcode 75 Sort Colors
 * https://leetcode.com/problems/sort-colors/
 * Given an array nums with n objects colored red, white, or blue, sort them
 * in-place so that objects of the same color are adjacent, with the colors in
 * the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and
 * blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 */

public class SortColors {

    public static void main(String[] args) {
        int[] nums = { 2, 0, 2, 1, 1, 0 };
        sortColors(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] { 2, 0, 1 };
        sortColors(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] { 2, 0, 2, 1, 1, 0 };
        sortColors2(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] { 2, 0, 1 };
        sortColors2(nums);
        System.out.println(Arrays.toString(nums));

    }

    /**
     * Rearranges the input array `nums` such that objects of the same color are
     * adjacent, with the colors in the order red, white, and blue. The integers
     * 0, 1, and 2 represent the colors red, white, and blue, respectively.
     *
     * This method partitions the array into three sections: one for each color.
     * It first moves all the 0s to the front, then it moves the 1s to follow the
     * 0s, effectively placing all the 2s at the end. This is done in-place and
     * does not use any additional space beyond a few integer variables.
     *
     * @param nums the array of integers representing colors, to be sorted in-place
     */

    public static void sortColors(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, index, i);
                index++;
            }
        }
        for (int i = index; i < nums.length; i++) {
            if (nums[i] == 1) {
                swap(nums, index, i);
                index++;
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 三路快排思路，就是遇到0添加到数组前面，遇到2就添加到数组后面的元素交换，
     * 遇到1就继续遍历，这样可以保证0在最前面，2在最后面，1在中间。
     *
     * @param nums
     */
    public static void sortColors2(int[] nums) {
        int red = 0;
        int blue = nums.length - 1;
        int index = 0;
        while (index <= blue) {
            if (nums[index] == 0) {
                swap(nums, index, red);
                red++;
                index++;
            } else if (nums[index] == 2) {
                swap(nums, index, blue);
                blue--;
            } else {
                index++;
            }
        }
    }
}
