package com.navysu.java.basic.algorithm.matrix;

/**
 * Leetcode 79 Word Search
 * https://leetcode.com/problems/word-search/
 *
 * Given an m x n grid of characters board and a string word, return true if
 * word
 * exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells,
 * where
 * adjacent cells are horizontally or vertically neighboring. The same letter
 * cell may not be used more than once.
 *
 * Example 1:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
 * =
 * "ABCCED"
 * Output: true
 * Example 2:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
 * =
 * "SEE"
 * Output: true
 * Example 3:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
 * =
 * "ABCB"
 * Output: false
 *
 */

public class WordSearch {

    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' } };
        String word = "ABCCED";
        System.out.println(exist(board, word));

        board = new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' } };
        word = "SEE";
        System.out.println(exist(board, word));

        board = new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' } };
        word = "ABCB";
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Performs a depth-first search to determine if the word exists in the board
     * starting from the given cell (i, j).
     *
     * This method checks if a sequence of characters in the board matches the
     * given word. It recursively explores neighboring cells (up, down, left,
     * right) to check if the word can be constructed from letters of sequentially
     * adjacent cells.
     *
     * @param board a 2D grid of characters representing the board
     * @param word  the string to be searched in the board
     * @param i     the current row index in the board
     * @param j     the current column index in the board
     * @param index the current index in the word being matched
     * @return true if the word exists in the board starting from cell (i, j),
     *         false otherwise
     */
    public static boolean dfs(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = '#'; // mark visited cell as '#' to avoid visiting it again
        boolean result = dfs(board, word, i + 1, j, index + 1) || dfs(board, word, i - 1, j, index + 1)
                || dfs(board, word, i, j + 1, index + 1) || dfs(board, word, i, j - 1, index + 1);
        board[i][j] = temp;
        return result;
    }

}
