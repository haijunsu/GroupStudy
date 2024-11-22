package com.navysu.java.basic.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leetcode 438 Find All Anagrams in a String
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * Given two strings s and p, return an array of all the start indices of p's
 * anagrams
 * in s. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 *
 * Constraints:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 */

public class FindAnagrams {

    public static void main(String[] args) {
        FindAnagrams f = new FindAnagrams();
        System.out.println(f.findAnagrams("cbaebabacd", "abc"));
        System.out.println(f.findAnagrams1("cbaebabacd", "abc"));
        System.out.println(f.findAnagrams2("cbaebabacd", "abc"));

        // Test case with multiple anagrams
        System.out.println(f.findAnagrams("cbaebabacd", "bca"));
        System.out.println(f.findAnagrams1("cbaebabacd", "bca"));
        System.out.println(f.findAnagrams2("cbaebabacd", "bca"));

        System.out.println(f.findAnagrams("abab", "ba"));
        System.out.println(f.findAnagrams1("abab", "ba"));
        System.out.println(f.findAnagrams2("abab", "ba"));

        System.out.println(f.findAnagrams("abaacbabc", "abc"));
        System.out.println(f.findAnagrams1("abaacbabc", "abc"));
        System.out.println(f.findAnagrams2("abaacbabc", "abc"));
    }

    /**
     * Finds all start indices of p's anagrams in s.
     * Uses a HashMap to keep track of the characters in p and their counts.
     * Then iterates through s, and for each substring of length p.length(),
     * creates a temp map that is a copy of the original map. Then iterates through
     * the substring, and for each character, decrements the count in the temp map.
     * If all counts in the temp map are 0, then the substring is an anagram of p,
     * and the start index is added to the result list.
     * 
     * @param s the string to search in
     * @param p the string that has the characters to search for
     * @return the list of start indices of p's anagrams in s
     */
    private List<Integer> findAnagrams(String s, String p) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            map.put(p.charAt(i) + "", map.getOrDefault(p.charAt(i) + "", 0) + 1);
        }
        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            Map<String, Integer> temp = new HashMap<>(map);
            for (int j = 0; j < p.length(); j++) {
                temp.put(s.charAt(i + j) + "", temp.getOrDefault(s.charAt(i + j) + "", 0) - 1);
            }
            if (temp.values().stream().allMatch(n -> n == 0)) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * Finds all start indices of p's anagrams in s.
     * Uses the Sliding Window approach with a HashMap to keep track of the
     * characters
     * in the current window. The window is expanded to the right by adding a
     * character
     * to the window map, and shrunk from the left by removing a character from the
     * window map. If the window map matches the map of p, then the window is a
     * valid
     * anagram of p and the start index is added to the result list.
     * 
     * @param s the string to search in
     * @param p the string that has the characters to search for
     * @return the list of start indices of p's anagrams in s
     */
    private List<Integer> findAnagrams1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length())
            return res;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            map.put(p.charAt(i), map.getOrDefault(p.charAt(i), 0) + 1);
        }
        int left = 0;
        int right = 0;
        int count = 0;
        Map<Character, Integer> windowMap = new HashMap<>();
        while (right < s.length()) {
            char curr = s.charAt(right++);
            if (!map.containsKey(curr)) {
                count = 0;
                left = right;
                windowMap = new HashMap<>();
                continue;
            }
            windowMap.put(curr, windowMap.getOrDefault(curr, 0) + 1);
            if (windowMap.get(curr) > map.get(curr)) {
                while (left < right - 1) {
                    char prev = s.charAt(left++);
                    windowMap.put(prev, windowMap.getOrDefault(prev, 0) - 1);
                    if (prev == curr) {
                        break;
                    } else {
                        count--;
                    }

                }
                continue;
            }
            count++;
            if (count == p.length()) {
                res.add(left);
                windowMap.put(s.charAt(left), windowMap.getOrDefault(s.charAt(left), 0) - 1);
                left++;
                count--;
            }

        }
        return res;

    }

    /**
     * Finds all start indices of p's anagrams in s.
     * Uses two int arrays to count the characters in p and s, and then slide the
     * window
     * over s to find all the anagrams of p.
     * 
     * @param s the string to search in
     * @param p the string that has the characters to search for
     * @return the list of start indices of p's anagrams in s
     */
    private List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length())
            return res;
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pCount, sCount))
            res.add(0);
        for (int i = 1; i < s.length() - p.length() + 1; i++) {
            sCount[s.charAt(i - 1) - 'a']--;
            sCount[s.charAt(i + p.length() - 1) - 'a']++;
            if (Arrays.equals(pCount, sCount))
                res.add(i);
        }
        return res;
    }
}
