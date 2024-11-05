package com.navysu.java.basic.algorithm;

public class MaxSumSubarray {

    public static void main(String[] args) {
        int[] array = new int[] { 1, -2, 3, 10, -4, 7, 2, -5 };
        int maxSum = maxSumSubArray(array, 3);
        System.out.println(maxSum);
        maxSum = findMaxSumSubArray(array);
        System.out.println(maxSum);
    }

    public static int maxSumSubArray(int[] array, int k) {
        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += array[i];
        }
        sum = maxSum;
        for (int i = k; i < array.length; i++) {
            sum = sum + array[i] - array[i - k];
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static int findMaxSumSubArray(int[] array) {
        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            maxSum = Math.max(sum, maxSum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }
}
