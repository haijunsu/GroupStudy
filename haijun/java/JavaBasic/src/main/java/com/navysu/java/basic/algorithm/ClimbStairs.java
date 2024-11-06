package com.navysu.java.basic.algorithm;

/**
 * Leetcode 70 Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 *
 * You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 */

import java.util.HashMap;
import java.util.Map;

public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(10));
    }

    /**
     * 爬楼梯
     * 这个就是斐波拉契数列，就是f(n) =
     * f(n-1)+f(n-2),需要注意的是，如果使用递归来实现，会有重复计算重叠子问题的问题。比如f(5)=f(4)+f(3)=(f(3)+f(2))+(f(2)+f(1))
     * 其实是计算了两遍f(3)，所以可以使用hashMap来缓存f(3)的结果，这样避免重复递归计算。
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        Map<Integer, Integer> mapStairs = new HashMap<>();
        if (n < 3) {
            return Math.max(0, n);
        } else if (mapStairs.containsKey(n)) {
            return mapStairs.get(n);
        } else {
            int result = climbStairs(n - 1) + climbStairs(n - 2);
            mapStairs.put(n, result);
            return result;
        }
    }

}
