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
        // System.out.println(trap(height));
        height = new int[] { 4, 2, 0, 3, 2, 5 };
        System.out.println(trap(height));
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

            System.out.println(left + " " + right + " " + leftMax + " " + rightMax + " " + water);
        }
        return water;
    }

}
