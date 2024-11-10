package com.navysu.java.basic.algorithm.dp;

/**
 * Leetcode 322 Coin Change
 * https://leetcode.com/problems/coin-change/description/
 *
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If
 * that amount of money cannot be made up by any combination of the coins,
 * return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 */

public class CoinChange {

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(new int[] { 1, 2, 5 }, 11));
        System.out.println(coinChange.coinChange(new int[] { 1, 2, 5 }, 100));
        System.out.println(coinChange.coinChange(new int[] { 2 }, 3));
        System.out.println(coinChange.coinChange(new int[] { 1 }, 0));
    }

    /**
     * Given an integer array coins representing coins of different
     * denominations and an integer amount representing a total amount of money,
     * return the fewest number of coins that you need to make up that amount. If
     * that amount of money cannot be made up by any combination of the coins,
     * return -1.
     *
     * You may assume that you have an infinite number of each kind of coin.
     *
     * use cases:
     * 1. amount = 0, return 0
     * 2. amount < 0, return -1
     * 3. amount > 0, 0 < i <=amount, return coins[i-coin] + 1 if coins[i-coin]
     * exists.
     *
     * @param coins  an integer array of coin denominations
     * @param amount an integer amount of money
     * @return the fewest number of coins that you need to make up that amount
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
