package com.navysu.java.basic.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxValueInSlideWindow {

    public static void main(String[] args) {
        int[] nums = { -1, 5, 7, 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;
        int[] result = maxInSlideWindow(nums, k);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        System.out.println("=======");
        int[] result2 = maxInSlideWindow2(nums, k);
        for (int i = 0; i < result2.length; i++) {
            System.out.println(result2[i]);
        }
    }

    // O(nk) time complexity, O(k) space complexity
    public static int[] maxInSlideWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            result[index++] = max;
        }
        return result;
    }

    // O(nlogk) time complexity, O(k) space complexity with priority queue
    public static int[] maxInSlideWindow2(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            maxHeap.offer(nums[i]);
            if (maxHeap.size() == k) {
                result[index++] = maxHeap.peek();
                maxHeap.remove(Integer.valueOf(nums[i - k + 1]));
            }
        }
        return result;
    }
}
