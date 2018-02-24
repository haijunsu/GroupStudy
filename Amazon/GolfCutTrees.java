/*
 * Leetcode: https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
 * It likes Maze problem but each position has four directories.
 */

import java.util.*;
class GolfCutTrees {
    // directions
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    // dimension
    static int X = 0;
    static int Y = 0;

    public static int cutOffTree(List<List<Integer>> forest) {
        X = forest.size();
        Y = forest.get(0).size();
        // sort tree
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((int[]a, int[] b) -> Integer.compare(a[2], b[2]));

        for (int x = 0; x < X; ++x) {
            for (int y = 0; y < Y; ++y) {
                int val = forest.get(x).get(y);
                if (val > 1) {
                    queue.offer(new int[]{x, y, val});
                }
            }
        }
        int steps = 0, sx = 0, sy = 0;
        while(!queue.isEmpty()) {
            int[] tree = queue.poll();
            int dist = dist(forest, sx, sy, tree[0], tree[1]);
            if (dist < 0) return -1;
            steps += dist;
            sx = tree[0];
            sy = tree[1];
        }
        return steps;
    }

    static int dist(List<List<Integer>> forest, int sx, int sy, int tx, int ty) {
        Queue<int[]> processQueue = new LinkedList<int[]>();
        boolean[][] visited = new boolean[X][Y];
        processQueue.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;
        while(!processQueue.isEmpty()) {
            int[] curr = processQueue.poll();
            if (curr[0] == tx && curr[1] == ty) {
                return curr[2];
            }
            for (int i = 0; i < 4; ++i) {
                int x = curr[0] + dx[i];
                int y = curr[1] + dy[i];
                if (x >= 0 && x < X && y >= 0 && y < Y && !visited[x][y] && forest.get(x).get(y) > 0) {
                    visited[x][y] = true;
                    processQueue.add(new int[]{x, y, curr[2] + 1});
                }
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        int[][] testData = new int[][]{{1,2,3},{0,0,4},{7,6,5}};
        test(testData);
        testData = new int[][]{{1,2,3},{0,0,0},{7,6,5}};
        test(testData);

    }

    private static List<List<Integer>> buildList(int[][] data) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < data.length; ++i) {
            List<Integer> items = new ArrayList<Integer>();
            for (int j = 0; j < data[i].length; ++j) {
                items.add(data[i][j]);
            }
            result.add(items);
        }
        return result;
    }

    private static void test(int[][] data) {
        int expected = cutOffTree(buildList(data)); // has been tested on Leetcode
        int result = cutOffTree2(buildList(data));
        System.out.println("Yours: " + result + ", excepted: " + expected);
        System.out.println(result == expected?"Accept":"Wrong answer");
    }


    static int[] dx2 = {1, -1, 0, 0};
    static int[] dy2 = {0, 0, 1, -1};
    static int X2 = 0;
    static int Y2 = 0;

    public static int cutOffTree2(List<List<Integer>> forest) {
        // this method for practice
        // sort first
        X2 = forest.size();
        Y2 = forest.get(0).size();
        PriorityQueue<int[]> treeQueue = new PriorityQueue<int[]>(
                    (int[] a, int[] b) -> Integer.compare(a[2], b[2])
                );
        for (int x = 0; x < X2; ++x) {
            for (int y = 0; y < Y2; ++y) {
                int val = forest.get(x).get(y);
                if (val > 1) {
                    treeQueue.offer(new int[]{x, y, val});
                }
            }
        }
        int steps = 0, sx = 0, sy = 0;
        while( !treeQueue.isEmpty() ) {
            int[] tree = treeQueue.poll();
            int dist = distance(forest, sx, sy, tree[0], tree[1]);
            if (dist == -1) return -1;
            steps += dist;
            sx = tree[0];
            sy = tree[1];
        }
        return steps;
    }

    private static int distance(List<List<Integer>> forest, int sx, int sy, int tx, int ty) {
        Queue<int[]> processQueue = new LinkedList<int[]>();
        boolean[][] visited = new boolean[X2][Y2];
        processQueue.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;
        while (!processQueue.isEmpty()) {
            int[] curr = processQueue.poll();
            if (curr[0] == tx && curr[1] == ty)
                return curr[2];
            for (int i = 0; i < 4; ++i) {
                int x = dx[i] + curr[0];
                int y = dy[i] + curr[1];
                if (x >= 0 && x < X2 && y >=0 && y < Y2 && !visited[x][y] && forest.get(x).get(y) >= 1) {
                    visited[x][y] = true;
                    processQueue.offer(new int[]{x, y, curr[2] + 1});
                }
            }
        }
        return -1;
    }

}
