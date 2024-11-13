package com.navysu.java.basic.algorithm;

import java.util.Stack;

/**
 * Leetcode 84. Largest Rectangle in Histogram
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Given an array of integers heights representing the histogram's bar height
 * where the width of each bar is 1, return the area of the largest rectangle in
 * the histogram.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2:
 *
 *
 * Input: heights = [2,4]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= heights.length <= 105
 * 0 <= heights[i] <= 104
 */

public class LargestRectangleArea {

    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = heights[i];
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
        // Time complexity: O(n^2)
        // Space complexity: O(1)
    }

    /**
     * Computes the largest rectangle area in a histogram using a stack-based
     * approach for optimal time complexity.
     *
     * The algorithm iterates through each bar and uses a stack to maintain
     * indices of bars in non-decreasing order of their heights. When a bar is
     * found that is lower than the bar at the index stored on top of the stack,
     * it means that the rectangle with the height of the bar at the top of the
     * stack can be closed, since the current bar provides a right boundary.
     * The area is calculated by considering the height of the bar at the top
     * of the stack and the width determined by the current index and the index
     * just before the bar at the top of the stack.
     *
     * This method efficiently calculates the maximum rectangular area with
     * time complexity of O(n) and space complexity of O(n).
     *
     * @param heights an array representing the heights of the histogram bars
     * @return the area of the largest rectangle
     */
    public static int largestRectangleArea2(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            int height = heights[stack.pop()];
            int width = heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        return maxArea;
        // Time complexity: O(n)
        // Space complexity: O(n)
    }

    public static void main(String[] args) {
        int[] heights1 = { 2, 1, 5, 6, 2, 3 };
        System.out.println(largestRectangleArea(heights1)); // Output: 10
        System.out.println(largestRectangleArea2(heights1)); // Output: 10

        int[] heights2 = { 2, 4 };
        System.out.println(largestRectangleArea(heights2)); // Output: 4
        System.out.println(largestRectangleArea2(heights2)); // Output: 4

        int[] heights3 = { 1, 2, 3, 4, 5 };
        System.out.println(largestRectangleArea(heights3)); // Output: 9
        System.out.println(largestRectangleArea2(heights3)); // Output: 9

        heights3 = new int[] { 2, 1, 2 };
        System.out.println(largestRectangleArea(heights3)); // Output: 3
        System.out.println(largestRectangleArea2(heights3)); // Output: 3
    }

}
