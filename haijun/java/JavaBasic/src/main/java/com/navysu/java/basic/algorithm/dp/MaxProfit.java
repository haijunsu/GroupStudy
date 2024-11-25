package com.navysu.java.basic.algorithm.dp;

/**
 * Leetcode 309 Best Time to Buy and Sell Stock with Cooldown
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * You are given an array prices where prices[i] is the price of a given stock
 * on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many
 * transactions as you like (i.e., buy one and sell one share of the stock
 * multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e.,
 * cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you
 * must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 *
 * Input: prices = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class MaxProfit {

    public static void main(String[] args) {
        int[] prices = { 1, 2, 3, 0, 2 };
        System.out.println(maxProfit(prices));

        prices = new int[] { 1 };
        System.out.println(maxProfit(prices));
    }

    /**
     * Calculate the maximum profit by buying and selling stocks with cooldown.
     *
     * The code initializes a 2D array profits to store the maximum profit for each
     * day and whether the stock is held or not. It then iterates through the prices
     * array and updates the profits array accordingly.
     *
     * The key insight is that if you hold the stock on a certain day, you can
     * either sell it on that day or sell it on a previous day and then buy it back
     * on the current day. The code uses this insight to calculate the maximum
     * profit.
     *
     * The function returns the maximum profit that can be achieved by not holding
     * any stock on the last day.
     *
     * @param prices the prices of the stock at each day
     * @return the maximum profit
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        // base: day 0 and day 1.
        int[][] profits = new int[prices.length][2]; // second index 0 = not hold, 1 = hold stock
        // day 0
        profits[0][0] = 0; // not hold
        profits[0][1] = -prices[0]; // buy stock and hold
        profits[1][0] = Math.max(0, prices[1] - prices[0]); // not hold or sell stock.
        profits[1][1] = Math.max(-prices[0], -prices[1]); // can be buy on day 0 or day 1, buy with the lower price.
        for (int i = 2; i < prices.length; i++) {
            // not hold on i day. i - 1 day was not hold or i - 1 day was hold but sold
            // stock this day.
            profits[i][0] = Math.max(profits[i - 1][0], profits[i - 1][1] + prices[i]);
            // hold on i day. i - 1 day was hold or i - 2 day was not hold and bought stock
            // this day.
            profits[i][1] = Math.max(profits[i - 1][1], profits[i - 2][0] - prices[i]);
        }
        // final case is not hold any stock
        return profits[prices.length - 1][0];

    }
}
