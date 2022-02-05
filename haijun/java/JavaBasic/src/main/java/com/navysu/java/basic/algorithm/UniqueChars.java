package com.navysu.java.basic.algorithm;

/**
 * Impletement an algorithm to determine if a string has all unique characters. What if you can not use additional data structures?
 * Source: Cracking the code interview book.
 */

import java.util.*;
class UniqueChars {

    public static boolean isUniqueChars (String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); ++i) {
            int val  = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) return false;
            checker |= (1 << val);
        }
        return true;
    }

    public static boolean isUniqueChars2 (String str) {
        // practice Bitset. Not for this question
        BitSet visited = new BitSet();
        for (int i = 0; i < str.length(); ++i) {
            int val = str.charAt(i) - 'a';
            if (visited.get(val)) return false;
            visited.set(val);
        }
        return true;
    }
    public static void main(String args[]) {
        String str = "abcdefgfedcba";
        System.out.println("False");
        System.out.println(isUniqueChars(str));
        System.out.println(isUniqueChars2(str));
        System.out.println("===========");
        str = "aaaa";
        System.out.println("False");
        System.out.println(isUniqueChars(str));
        System.out.println(isUniqueChars2(str));
        System.out.println("===========");
        str = "abcde";
        System.out.println("True");
        System.out.println(isUniqueChars(str));
        System.out.println(isUniqueChars2(str));
    }
}
