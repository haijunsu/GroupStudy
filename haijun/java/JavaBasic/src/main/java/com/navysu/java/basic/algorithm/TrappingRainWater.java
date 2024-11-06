package com.navysu.java.basic.algorithm;

/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it can trap after raining.
 *
 * https://leetcode.com/problems/trapping-rain-water/description/
 */

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println(trap(height));
        System.out.println(trapDP(height));
        height = new int[] { 4, 2, 0, 3, 2, 5 };
        System.out.println(trap(height));
        System.out.println(trapDP(height));
    }

    public static int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }

            // System.out.println(left + " " + right + " " + leftMax + " " + rightMax + " "
            // + water);
        }
        return water;
    }

    public static int trapDP(int[] height) {
        int[] leftMaxValues = new int[height.length];
        int[] rightMaxValues = new int[height.length];
        int water = 0;
        leftMaxValues[0] = height[0];
        rightMaxValues[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++) {
            leftMaxValues[i] = Math.max(leftMaxValues[i - 1], height[i]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            rightMaxValues[i] = Math.max(rightMaxValues[i + 1], height[i]);
        }
        for (int i = 0; i < height.length; i++) {
            water += Math.max(0, Math.min(leftMaxValues[i], rightMaxValues[i]) - height[i]);
        }

        return water;
    }
}
