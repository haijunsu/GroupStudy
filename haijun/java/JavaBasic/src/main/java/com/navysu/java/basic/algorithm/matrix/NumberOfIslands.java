package com.navysu.java.basic.algorithm.matrix;

/**
 * leetcode 200 Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 *
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and
 * '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all
 * surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 */

public class NumberOfIslands {

    public static void main(String[] args) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        char[][] grid = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } };
        System.out.println(numberOfIslands.numIslands(grid));

        grid = new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' } };
        System.out.println(numberOfIslands.numIslands(grid));

        grid = new char[][] { { '1', '1', '1' }, { '0', '1', '0' }, { '1', '1', '1' } };
        System.out.println(numberOfIslands.numIslands(grid));
    }

    /**
     * Returns the number of islands in the given grid.
     *
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of
     * islands. An island is surrounded by water and is formed by connecting
     * adjacent lands horizontally or vertically. You may assume all four edges
     * of the grid are all surrounded by water.
     *
     * @param grid a 2d grid map of '1's (land) and '0's (water)
     * @return the number of islands in the given grid
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Depth-first search to find all adjacent lands given a land coordinate (i, j).
     * It will mark all visited lands as '0' and return once all adjacent lands are
     * visited.
     *
     * @param grid a 2d grid map of '1's (land) and '0's (water)
     * @param i    the row index of the given land coordinate
     * @param j    the column index of the given land coordinate
     */
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        if (grid[i][j] == '1') {
            grid[i][j] = '0'; // set it to visited
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }
    }

}
