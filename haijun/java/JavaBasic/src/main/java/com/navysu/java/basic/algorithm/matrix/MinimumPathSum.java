package com.navysu.java.basic.algorithm.matrix;

/**
 * Leetcode 64 Minimum Path Sum
 * https://leetcode.com/problems/minimum-path-sum/
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * Example 2:
 *
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 *
 */

public class MinimumPathSum {

    public static void main(String[] args) {
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        System.out.println(minimumPathSum.minPathSum(grid));

        grid = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        System.out.println(minimumPathSum.minPathSum(grid));
    }

    public int minPathSum(int[][] grid) {
        int[][] sums = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    sums[i][j] = grid[i][j];
                } else if (i == 0) {
                    sums[i][j] = sums[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    sums[i][j] = sums[i - 1][j] + grid[i][j];
                } else {
                    sums[i][j] = Math.min(sums[i - 1][j], sums[i][j - 1]) + grid[i][j];
                }
            }
        }
        return sums[grid.length - 1][grid[0].length - 1];
    }

}
