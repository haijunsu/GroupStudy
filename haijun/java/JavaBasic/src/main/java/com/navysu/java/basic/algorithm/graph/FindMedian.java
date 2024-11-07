package com.navysu.java.basic.algorithm.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedian {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        System.out.println(findMedian(nums));

        nums = new int[] { 1, 3, 4, 5, 6, 7, 2 };
        System.out.println(findMedian2(nums));
    }

    public static double findMedian(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        if (n % 2 == 0) {
            return (nums[n / 2 - 1] + nums[n / 2]) / 2.0;
        } else {
            return nums[n / 2];
        }
    }

    public static double findMedian2(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        return maxHeap.size() == minHeap.size() ? (maxHeap.peek() + minHeap.peek()) / 2.0 : maxHeap.peek();
    }
}