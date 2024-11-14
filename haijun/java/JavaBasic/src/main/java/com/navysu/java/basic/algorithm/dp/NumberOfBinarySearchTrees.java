package com.navysu.java.basic.algorithm.dp;

/**
 * Leetcode 96 Unique Binary Search Trees
 * https://leetcode.com/problems/unique-binary-search-trees/
 *
 * Given an integer n, return the number of structurally unique BST's (binary
 * search trees) which has exactly n nodes of unique values from 1 to n.
 *
 * Example 1:
 * Input: n = 3
 * Output: 5
 * Example 2:
 * Input: n = 1
 * Output: 1
 *
 * Constraints:
 * 1 <= n <= 19
 */

public class NumberOfBinarySearchTrees {

    public static void main(String[] args) {
        int n = 1;
        System.out.println(numTrees(n));
        n = 2;
        System.out.println(numTrees(n));
        n = 3;
        System.out.println(numTrees(n));
        n = 4;
        System.out.println(numTrees(n));
        n = 5;
        System.out.println(numTrees(n));
        n = 6;
        System.out.println(numTrees(n));
        n = 7;
        System.out.println(numTrees(n));
        n = 8;
        System.out.println(numTrees(n));
        n = 9;
        System.out.println(numTrees(n));
        n = 10;
        System.out.println(numTrees(n));
    }

    /**
     * Computes the number of unique binary search trees that can be formed using
     * the values 1 to n.
     *
     * 就是把一个二叉搜索树的组合种数，其实是左子树的组合数乘以右子树的组合数，假设n为4，f(n)代表组合种数，
     * 二叉树共有四个节点，那么二叉树肯定有根节点，组合主要分为以下几类：
     * 1.左子树有0个节点，右子树有3个节点，f(0)f(3)
     * 2.左子树有1个节点，右子树有2个节点,f(1)f(2)
     * 3.左子树有2个节点，右子树有1个节点,f(2)f(1)
     * 4.左子树有3个节点，右子树有0个节点,f(3)f(0)
     * 所以，f(4)=f(0)f(3)+f(1)f(2)+f(2)f(1)+f(3)f(0)
     *
     * @param n the number of nodes in the tree
     * @return the number of unique binary search trees
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

}
