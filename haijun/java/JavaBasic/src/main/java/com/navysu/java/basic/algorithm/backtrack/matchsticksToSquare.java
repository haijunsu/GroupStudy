package com.navysu.java.basic.algorithm.backtrack;

import java.util.Arrays;

/**
 * Leetcode 473 Matchsticks to Square
 * https://leetcode.com/problems/matchsticks-to-square/
 *
 * You are given an integer array matchsticks where matchsticks[i] is the length
 * of the ith matchstick. You want to use all the matchsticks to make one
 * square. You should not break any stick, but you can link them up, and each
 * matchstick must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matchsticks = [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came
 * two sticks with length 1.
 * Example 2:
 *
 * Input: matchsticks = [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 *
 */

public class matchsticksToSquare {

    public static void main(String[] args) {
        matchsticksToSquare m = new matchsticksToSquare();
        System.out.println(m.makesquare(new int[] { 1, 1, 2, 2, 2 }));
        System.out.println(m.makesquare(new int[] { 3, 3, 3, 3, 4 }));
    }

    public boolean makesquare(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) {
            return false;
        }
        int target = sum / 4;
        int[] sides = new int[4];
        Arrays.sort(matchsticks);
        return backtrack(matchsticks, matchsticks.length - 1, sides, target);
    }

    private boolean backtrack(int[] matchsticks, int index, int[] sides, int target) {
        if (index < 0) {
            for (int side : sides) {
                if (side != target) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < 4; i++) {
            if (sides[i] + matchsticks[index] <= target) {
                sides[i] += matchsticks[index];
                if (backtrack(matchsticks, index - 1, sides, target)) {
                    return true;
                }
                sides[i] -= matchsticks[index];
            }
            if (sides[i] == 0) {
                break; // Skip duplicates
            }
        }
        return false;
    }
}
