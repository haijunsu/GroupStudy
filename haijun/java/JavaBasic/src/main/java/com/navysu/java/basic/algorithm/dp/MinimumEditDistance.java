package com.navysu.java.basic.algorithm.dp;

/**
 * Leetcode 72 edit distance
 * https://leetcode.com/problems/edit-distance/
 * Given two strings word1 and word2, return the minimum number of operations
 * required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 *
 * Constraints:
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 */

public class MinimumEditDistance {

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros")); // 3
        System.out.println(minDistance("intention", "execution")); // 5
    }

    /**
     * Computes the minimum edit distance between two strings, which is the
     * minimum number of operations (insertions, deletions, and substitutions)
     * required to change one word into the other.
     *
     * Make a table of the two strings as a matrix.
     * <code>
     *  dp   h  o  r  s  e
     *     0 1  2  3  4  5
     *  r  1 1  2  2  3  4
     *  o  2 2  1  2  3  4
     *  s  3 3  2  2  2  3
     * </code>
     *
     * The selected code is a method named minDistance within the
     * MinimumEditDistance class. This method calculates the minimum edit distance
     * (MED) between two strings, which is the minimum number of operations
     * (insertions, deletions, and substitutions) required to change one word into
     * the other.
     *
     * The algorithm used in this method is dynamic programming (DP). It creates a
     * 2D array dp of size (word1.length() + 1) x (word2.length() + 1) to store the
     * intermediate results. The first row and first column of dp represent the base
     * cases where one of the strings is empty.
     *
     * The algorithm iterates through each character of both strings, comparing
     * them. If the characters are equal, the cost of transforming the prefix of
     * word1 up to the current character into the prefix of word2 up to the current
     * character is the same as the cost of transforming the prefixes without the
     * current character. Therefore, dp[i][j] is set to dp[i - 1][j - 1].
     *
     * If the characters are not equal, the algorithm calculates the cost of
     * transforming the prefixes with the current character included. It considers
     * three possibilities:
     * 1.
     * Replace the current character in word1 with the current character in word2.
     * The cost is dp[i - 1][j - 1] + 1.
     * 2.
     * Delete the current character from word1. The cost is dp[i - 1][j] + 1.
     * 3.
     * Insert the current character into word1. The cost is dp[i][j - 1] + 1.
     *
     *
     * The minimum cost among these possibilities is chosen as dp[i][j].
     *
     * Finally, the method returns dp[word1.length()][word2.length()], which
     * represents the minimum edit distance between the two input strings.
     *
     * This implementation efficiently solves the problem using DP, reducing the
     * time complexity from exponential to quadratic.
     *
     * @param word1 the first string
     * @param word2 the second string
     * @return the minimum edit distance between the two strings
     */
    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }

        }
        return dp[word1.length()][word2.length()];
    }

}
