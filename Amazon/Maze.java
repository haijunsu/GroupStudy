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
 */

public class Maze {

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

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] paths = new int[m][n];
        for (int i = 0; i < m; ++i) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            paths[i][0] = 1;
        }
        for (int i = 0; i < n; ++i) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            paths[0][i] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    paths[i][j] = 0;
                } else {
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
                }
            }
        }
        return paths[m - 1][n - 1];

   }

    public static void main(String[] args) {
        int[][] maze = {{0}};
        System.out.println(1);
        System.out.println(solution(maze));
        maze = new int[][]{{1}}; //bad case
        System.out.println(0);
        System.out.println(solution(maze));
        maze = new int[][]{{0,0,1},{0,1,0},{0,0,0}};
        System.out.println(1);
        System.out.println(solution(maze));
        maze = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(2);
        System.out.println(solution(maze));
    }

}

