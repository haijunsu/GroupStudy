/*
 * 意思是说有一个M*N的maze，0代表可以通过，1代表不可以通过，然后给你一个出口（x,y），
 * 找从（0,0）到出口的最少steps，如果找不到path就返回-1
 *
 * Leetcode原题 Unique Path II
 * https://leetcode.com/problems/unique-paths-ii/description/
 *
 * 思路就是先把左边和上边的设置为1， 如果遇到1，则把后面的都设为0
 * steps[i][j] = steps[i - 1][j] + steps[i][j - 1]
 * 如果单元值为1，则 steps[i][j] = 0
 *
 * I think it is not the Unique Path II. It likes a part Golf Cut Off Tree.
 * We can go 4 directions instead of 2 directions (down and right);
 */

import java.util.*;
public class Maze {
    // for Maze
    // from 0,0 to x,y
    public static int minSteps(int[][] maze, int x, int y) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int X = maze.length;
        int Y = maze[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] visited = new boolean[X][Y];
        queue.offer(new int[]{0, 0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            if (cell[0] == x && cell[1] == y) {
                return cell[2];
            }
            for (int i = 0; i < 4; ++i) {
                int m = cell[0] + dx[i];
                int n = cell[1] + dy[i];
                if (m >= 0 && m < X && n >= 0 && n < Y && !visited[m][n] && maze[m][n] == 0) {
                    visited[m][n] = true;
                    queue.offer(new int[]{m, n, cell[2] + 1});
                }
            }
        }
        return -1;
    }


    // for Unique PathII
    public static int solution(int[][] maze) {
        int m = maze.length, n = maze[0].length;
        int[][] steps = new int[m][n];
        for (int i = 0; i < m; ++i) {
            if (maze[i][0] == 1) break;
            steps[i][0] = 1;
        }
        for (int i = 0; i < n; ++i) {
            if (maze[0][i] == 1) break;
            steps[0][i] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (maze[i][j] == 1) {
                    steps[i][j] = 0;
                } else {
                    steps[i][j] = steps[i - 1][j] + steps[i][j -  1];
                }
            }
        }
        return steps[m -  1][n - 1];
    }

    public static void main(String[] args) {
        int[][] maze = {{0}};
        test(maze, 0, 0, 0);
        maze = new int[][]{{0,0,1},{0,1,0},{0,0,0}};
        test(maze, 1, 2, 5);
        test(maze, 2, 1, 3);
        maze = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        test(maze, 1, 0, 1);
        test(maze, 1, 2, 3);
    }

    public static void test(int[][] maze, int x, int y, int expected) {
        int ans = minSteps(maze, x, y);
        System.out.println("Expected: " + expected + ", your answer: " + ans);
        System.out.println(expected == ans? "Accept" : "Wrong answer");
    }
}

