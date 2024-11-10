package com.navysu.java.basic.algorithm.backtrack;

import java.util.Arrays;

/**
 * Leetcode 31 Next Permutation
 * https://leetcode.com/problems/next-permutation
 *
 * A permutation of an array of integers is an arrangement of its members into a
 * sequence or linear order.
 *
 * For example, for arr = [1,2,3], the following are all the permutations of
 * arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * The next permutation of an array of integers is the next lexicographically
 * greater permutation of its integer. More formally, if all the permutations of
 * the array are sorted in one container according to their lexicographical
 * order, then the next permutation of that array is the permutation that
 * follows it in the sorted container. If such arrangement is not possible, the
 * array must be rearranged as the lowest possible order (i.e., sorted in
 * ascending order).
 *
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does
 * not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 */
public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = { 1, 3, 2 };
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] { 3, 2, 1 };
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] { 1, 1, 5 };
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] { 1, 3, 2 };
        nextPermutation2(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] { 3, 2, 1 };
        nextPermutation2(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] { 1, 1, 5 };
        nextPermutation2(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * Given an array of integers nums, reorder it to form the lexicographicaly
     * next greater permutation. If such arrangement is not possible, it must
     * rearrange it as the lowest possible order (i.e., sorted in ascending
     * order).
     *
     * The replacement must be in place and use only constant extra memory.
     *
     * This Java method generates the next lexicographically greater permutation of
     * the input array nums. If no such permutation exists, it rearranges the array
     * in ascending order.
     *
     * Here's a step-by-step breakdown:
     *
     * Find the first pair of elements from the right that are in descending order
     * (nums[i] >= nums[i + 1]).
     * If such a pair is found, swap the first element with the smallest element to
     * its right that is greater than it.
     * Reverse the elements to the right of the swapped pair to get the next
     * permutation.
     * This algorithm ensures that the resulting permutation is the next
     * lexicographically greater permutation, or the smallest possible permutation
     * if no greater permutation exists.
     *
     * @param nums the given array of integers
     */
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    /**
     * Generates the next permutation in lexicographically increasing order. The
     * algorithm iterates through the array from right to left, and finds the first
     * pair of elements where the element to the left is less than the element to
     * the right. It then finds the smallest element to the right that is greater
     * than the element to the left, and swaps the two. Finally, it sorts the
     * elements to the right of the swapped pair to get the next permutation.
     *
     * If the array is already in the lexicographically largest permutation, the
     * algorithm will sort the array to get the lexicographically smallest
     * permutation.
     *
     * This Java method generates the next lexicographically greater permutation of
     * the input array nums. If no such permutation exists, it rearranges the array
     * in ascending order.
     *
     * Here's a step-by-step breakdown:
     *
     * Find the first pair of elements from right to left where the left element is
     * less than the right element.
     * Find the smallest element to the right of the left element that is greater
     * than it, and swap them.
     * Sort the elements to the right of the swapped pair.
     * If no such pair is found, the array is already in the lexicographically
     * largest permutation, so sort it in ascending order.
     * This method modifies the input array in-place.
     *
     * @param nums the given array of integers
     */
    public static void nextPermutation2(int[] nums) {
        int flag = 0;
        for (int i = nums.length - 1; i > 0; i--) {

            if (nums[i - 1] < nums[i]) {// 从往前找到第一个前一个数<后一个数的情况

                int min = i;
                // 再后面找到一个比nums[i-1]大，但是又最小的值
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] < nums[min] && nums[j] > nums[i - 1]) {
                        min = j;
                    }
                }
                // 进行交换
                int temp = nums[i - 1];
                nums[i - 1] = nums[min];
                nums[min] = temp;
                flag = 1;
                // 然后再对后面的元素进行排序
                Arrays.sort(nums, i, nums.length);
                break;
            }
        }
        // 如果现在的数组就是字典序最大的，那么就排序，得到字典序最小的进行返回。
        if (flag == 0) {
            Arrays.sort(nums);
        }
    }

}
