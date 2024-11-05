package com.navysu.java.basic.algorithm;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Given a string, find the length of the longest substring without repeating
     * characters
     *
     * 就是用滑动窗口，初始left指针指向第一个元素，right指针指向第二个元素，然后在while循环中判断，set中是否包含right指针当前的字符（set会包含left到right之间所有的字符）
     * 1.包含，说明之前窗口已经出现了right指针当前的字符，那么从set中移除left指针对应的字符，然后left指针右移。
     * 2.不包含，说明之前窗口没有出现right指针当前的字符，那么更新最大窗口值max，并且right指针右移。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> subCharset = new HashSet<>();
        int maxLength = 1;
        int left = 0;
        int right = 1;
        char[] chars = s.toCharArray();
        subCharset.add(chars[0]);
        while (right < chars.length) {
            if (subCharset.contains(chars[right])) {
                subCharset.remove(chars[left]);
                left++;
            } else {
                subCharset.add(chars[right]);
                right++;
                maxLength = Math.max(maxLength, right - left);
            }
        }
        return maxLength;
        // Time complexity: O(n), where n is the length of the input string
        // Space complexity: O(n), where n is the length of the input string
    }
}
