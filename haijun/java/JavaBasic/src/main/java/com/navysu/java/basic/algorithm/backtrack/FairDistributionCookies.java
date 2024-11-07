package com.navysu.java.basic.algorithm.backtrack;

import java.util.Arrays;

/**
 * Leetcode 2305 Fair Distribution of Cookies
 * https://leetcode.com/problems/fair-distribution-of-cookies/
 *
 * You are given an integer array cookies, where cookies[i] denotes the number
 * of cookies in the ith bag. You are also given an integer k that denotes the
 * number of children to distribute all the bags of cookies to. All the cookies
 * in the same bag must go to the same child and cannot be split up.
 *
 * The unfairness of a distribution is defined as the maximum total cookies
 * obtained by a single child in the distribution.
 *
 * Return the minimum unfairness of all distributions.
 *
 *
 *
 * Example 1:
 *
 * Input: cookies = [8,15,10,20,8], k = 2
 * Output: 31
 * Explanation: One optimal distribution is [8,15,8] and [10,20]
 * - The 1st child receives [8,15,8] which has a total of 8 + 15 + 8 = 31
 * cookies.
 * - The 2nd child receives [10,20] which has a total of 10 + 20 = 30 cookies.
 * The unfairness of the distribution is max(31,30) = 31.
 * It can be shown that there is no distribution with an unfairness less than
 * 31.
 * Example 2:
 *
 * Input: cookies = [6,1,3,2,2,4,1,2], k = 3
 * Output: 7
 * Explanation: One optimal distribution is [6,1], [3,2,2], and [4,1,2]
 * - The 1st child receives [6,1] which has a total of 6 + 1 = 7 cookies.
 * - The 2nd child receives [3,2,2] which has a total of 3 + 2 + 2 = 7 cookies.
 * - The 3rd child receives [4,1,2] which has a total of 4 + 1 + 2 = 7 cookies.
 * The unfairness of the distribution is max(7,7,7) = 7.
 * It can be shown that there is no distribution with an unfairness less than 7.
 *
 */

public class FairDistributionCookies {

    public static void main(String[] args) {
        int[] cookies = { 8, 15, 10, 20, 8 };
        int k = 2;
        System.out.println(fairDistributionCookies(cookies, k));
        cookies = new int[] { 6, 1, 3, 2, 2, 4, 1, 2 };
        k = 3;
        System.out.println(fairDistributionCookies(cookies, k));
    }

    public static int fairDistributionCookies(int[] cookies, int k) {
        Arrays.sort(cookies);
        int[] bags = new int[k];
        int[] unfairness = new int[] { Integer.MAX_VALUE };
        backtrack(cookies, bags, unfairness, cookies.length - 1);
        return unfairness[0];
    }

    public static void backtrack(int[] cookies, int[] bags, int[] unfairness, int index) {
        if (index < 0) {
            int maxBag = Integer.MIN_VALUE;
            for (int bag : bags) {
                maxBag = Math.max(maxBag, bag);
            }
            unfairness[0] = Math.min(unfairness[0], maxBag);
            return;
        }
        for (int i = 0; i < bags.length; i++) {
            bags[i] += cookies[index];
            backtrack(cookies, bags, unfairness, index - 1);
            bags[i] -= cookies[index];
        }
    }

}
