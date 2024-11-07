package com.navysu.java.basic.algorithm;

import java.util.PriorityQueue;

/**
 * Hackerranking MinimumLoss
 * https://www.hackerrank.com/challenges/minimum-loss/problem
 *
 * Lauren has a chart of distinct projected prices for a house over the next
 * several years. She must buy the house in one year and sell it in another, and
 * she must do so at a loss. She wants to minimize her financial loss.
 *
 * Example
 *
 * Her minimum loss is incurred by purchasing in year at and reselling in year
 * at . Return .
 *
 * Function Description
 *
 * Complete the minimumLoss function in the editor below.
 *
 * minimumLoss has the following parameter(s):
 *
 * int price[n]: home prices at each year
 * Returns
 *
 * int: the minimum loss possible
 * Input Format
 *
 * The first line contains an integer , the number of years of house data.
 * The second line contains space-separated long integers that describe each .
 *
 * Constraints
 *
 * All the prices are distinct.
 * A valid answer exists.
 * Subtasks
 *
 * for of the maximum score.
 * Sample Input 0
 *
 * 3
 * 5 10 3
 * Sample Output 0
 *
 * 2
 * Explanation 0
 *
 * Lauren buys the house in year at and sells it in year at for a minimal loss
 * of .
 *
 * Sample Input 1
 *
 * 5
 * 20 7 8 2 5
 * Sample Output 1
 *
 * 2
 * Explanation 1
 *
 * Lauren buys the house in year at and sells it in year at for a minimal loss
 * of .
 */

public class MinimumLoss {

    public static void main(String[] args) {
        long[] prices = { 20, 7, 8, 2, 5 };
        System.out.println(minimumLoss(prices));
        prices = new long[] { 5, 10, 3 };
        System.out.println(minimumLoss(prices));
        prices = new long[] { 1, 3, 2, 4, 5 };
        System.out.println(minimumLoss(prices));
    }

    public static int minimumLoss(long[] prices) {
        long loss = Long.MAX_VALUE;
        PriorityQueue<Long[]> queue = new PriorityQueue<Long[]>((a, b) -> b[1].compareTo(a[1]));
        for (int i = 0; i < prices.length; i++) {
            queue.add(new Long[] { (long) i, prices[i] });
        }
        while (queue.size() > 1) {
            Long[] curr = queue.poll();
            Long[] next = queue.peek();
            if (curr[0] < next[0]) { // make sure next is after curr
                loss = Math.min(loss, curr[1] - next[1]);
            }
        }
        return (int) loss;
    }

}
