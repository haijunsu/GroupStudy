package com.navysu.java.basic.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode 76 Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/
 * Given two strings s and t of lengths m and n respectively, return the minimum
 * window
 * substring
 * of s such that every character in t (including duplicates) is included in the
 * window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C'
 * from string t.
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 *
 * Constraints:
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 */

public class MinimumWindow {

    public static void main(String[] args) {
        String s = "DADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t)); // BANC

        s = "DABCDOBECODEBANC";
        t = "ABC";
        System.out.println(minWindow(s, t)); // BANC

        s = "DDDDDDDDDDDABEEEEECFFFFFDOBECODEBANC";
        t = "ABC";
        System.out.println(minWindow(s, t)); // BANC

        s = "a";
        t = "a";
        System.out.println(minWindow(s, t)); // a

        s = "a";
        t = "aa";
        System.out.println(minWindow(s, t)); // ""
    }

    /**
     * Finds the minimum window substring in s that contains all the characters in
     * t, including duplicates.
     *
     * @param s
     *          the string to search in
     * @param t
     *          the string that has the characters to search for
     * @return the minimum window substring, or an empty string if no such window
     *         exists
     */
    public static String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            // count characters in t
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int count = t.length();
        int start = 0;
        int end = 0;
        int minLen = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            // if c is in t, then decrement count. Otherwise, decrement count from negative
            // 1
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) >= 0) {
                // find a matching character
                count--;
            }
            while (count == 0) { // find a window
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                    end = right;
                }
                char d = s.charAt(left);
                map.put(d, map.getOrDefault(d, 0) + 1);
                if (map.get(d) > 0) {
                    // from the beginning of the window, find the first character that is in t
                    // then break the while loop by adding the count
                    // starting at the beginning of the window
                    count++;
                }
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end + 1);
    }
}
