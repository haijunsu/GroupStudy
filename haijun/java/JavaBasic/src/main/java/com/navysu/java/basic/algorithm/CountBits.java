package com.navysu.java.basic.algorithm;

import java.util.Arrays;

/**
 * leetcode 338 Counting Bits
 * https://leetcode.com/problems/counting-bits/
 *
 * Given an integer n, return an array ans of length n + 1 such that for each i
 * (0 <= i <= n), ans[i] is the number of 1's in the binary representation of
 * i.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,1]
 * Example 2:
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 *
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 * Constraints:
 *
 * 0 <= n <= 105
 */

public class CountBits {

    public static void main(String[] args) {
        int[] ans = countBits(5);
        System.out.println(Arrays.toString(ans));

        ans = countBits(2);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Given an integer n, return an array ans of length n + 1 such that for each
     * i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of
     * i.
     *
     * 0 --> 0
     * 1 --> 1 ===> 1 >> 1 = 0: (ans[0]) + 1 & 1 = 0 + 1 = 1
     * 2 --> 10 ===> 2 >> 1 = 1: (ans[1]) + 2 & 1 = 1 + 0 = 1
     * 3 --> 11 ===> 3 >> 1 = 1: (ans[1]) + 3 & 1 = 1 + 1 = 2
     * 4 --> 100 ===> 4 >> 1 = 2: (ans[2]) + 4 & 1 = 1 + 0 = 1
     * 5 --> 101 ===> 5 >> 1 = 2: (ans[2]) + 5 & 1 = 1 + 1 = 2
     *
     * @param n the size of the array to be generated
     * @return an array of the number of 1's in the binary representation of each
     *         number in the range [0, n]
     */
    public static int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }

}
