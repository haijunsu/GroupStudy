/*
 * Leetcode 438
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 *
 * Since there are only lowercase, we can use an array with size 26 to present the count of each char. see index(String, pos)
 */
import java.util.*;

public class Anagrams {

    public static List<Integer> findAllAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        if (s == null || p == null || s.length() < 2 || s.length() < p.length()) {
            return result;
        }
        int[] pchars = new int[26];
        for (int i = 0; i < p.length(); ++i) {
            ++pchars[p.charAt(i) - 'a'];
        }
        int start = 0, count = p.length();
        for (int i = 0; i < s.length(); ++i) {
            if (--pchars[s.charAt(i) - 'a'] >= 0) {
                --count;
            }
            if (count == 0) result.add(start);
            if (p.length() == (i - start  + 1)) {
                if (pchars[s.charAt(start) - 'a'] >= 0) {
                    ++count;
                }
                ++pchars[s.charAt(start) - 'a'];
                ++start;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        test("", "", "[]");
        test("cbaebabacd", "abc", "[0, 6]");
        test("abab", "ab", "[0, 1, 2]");
    }

    private static void test(String s, String p, String answer) {
        String result = findAllAnagrams(s, p).toString();
        System.out.println("Expect: " + answer + ", your answer: " + result);
        if (answer.equals(String.join(",", result))) {
            System.out.println("Accept");
        } else {
            System.out.println("Wrong Answer.");
        }

    }
}
