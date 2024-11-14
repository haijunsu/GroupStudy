package com.navysu.java.basic.algorithm.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode 139 word breaking
 * https://leetcode.com/problems/word-break/
 * Given a string s and a dictionary of strings wordDict, return true if s can
 * be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the
 * segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */

public class Wordbreak {

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(wordBreak(s, wordDict)); // true
        System.out.println(wordBreak2(s, wordDict)); // true

        s = "applepenapple";
        wordDict = Arrays.asList("apple", "pen");
        System.out.println(wordBreak(s, wordDict)); // true
        System.out.println(wordBreak2(s, wordDict)); // true

        s = "catsandog";
        wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak(s, wordDict)); // false
        System.out.println(wordBreak2(s, wordDict)); // false
    }

    /**
     * Determines if a given string can be segmented into a space-separated
     * sequence of words in the given dictionary.
     *
     * This method uses dynamic programming to build up an array dp, where dp[i]
     * is true if the string can be segmented up to index i. The dp array is
     * built up by iterating through the string and checking all prefixes. If a
     * prefix can be segmented and the remaining substring is a word in the
     * dictionary, then dp[i] is set to true.
     *
     * @param s        the input string
     * @param wordDict the list of words in the dictionary
     * @return true if the string can be segmented, false otherwise
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        // change list to set for faster lookup
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        // base search case always matches
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * Determines if a given string can be segmented into a space-separated
     * sequence of words in the given dictionary.
     *
     * This method uses recursion to check all prefixes of the string and see if
     * they can be segmented into words in the dictionary. If a prefix can be
     * segmented, the method calls itself on the remaining substring.
     *
     * Time limit exceeded. This solution is not optimal.
     *
     * @param s        the input string
     * @param wordDict the list of words in the dictionary
     * @return true if the string can be segmented, false otherwise
     */
    public static boolean wordBreak2(String s, List<String> wordDict) {
        boolean result = false;

        for (int i = 0; i < s.length(); i++) {
            if (wordDict.contains(s.substring(0, i + 1))) {
                if (i == s.length() - 1) {
                    result = true;
                } else {
                    result = wordBreak2(s.substring(i + 1), wordDict);
                }
                if (result) {
                    break;
                }
            }
        }

        return result;
    }

}
