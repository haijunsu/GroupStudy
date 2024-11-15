package com.navysu.java.basic.algorithm;

/**
 * Leetcode 461 Hamming Distance
 * https://leetcode.com/problems/hamming-distance/
 *
 * The Hamming distance between two integers is the number of positions at which
 * the corresponding bits are different.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 1, y = 4
 * Output: 2
 * Example 2:
 *
 * Input: x = 3, y = 1
 * Output: 1
 *
 * Input: x = 1, y = 2
 * Output: 2
 *
 *
 */
public class HammingDistance {

    public static void main(String[] args) {
        int x = 1;
        int y = 4;
        System.out.println(hammingDistance(x, y));

        x = 3;
        y = 1;
        System.out.println(hammingDistance(x, y));

        x = 1;
        y = 2;
        System.out.println(hammingDistance(x, y));

    }

    /**
     * Calculates the Hamming distance between two integers, which is the number
     * of positions at which the corresponding bits are different.
     *
     * @param x the first integer
     * @param y the second integer
     * @return the Hamming distance between the two integers
     */
    public static int hammingDistance(int x, int y) {
        // return Integer.bitCount(x ^ y);
        int xorValue = x ^ y;
        int count = 0;
        while (xorValue != 0) {
            count += xorValue & 1;
            xorValue >>= 1;
        }
        return count;
    }
}
