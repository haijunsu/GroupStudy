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
        if (s == null || p == null || p.length() == 0)
            return result;
        if (s.length() < p.length())
            return result;
        int start = 0, count = p.length();
        int[] pchars = new int[26];
        for (int i = 0; i < p.length(); ++i) {
            ++pchars[index(p, i)];
        }
        for (int i = 0; i < s.length(); ++i) {
            if (--pchars[index(s, i)] >= 0) {
                // found
                --count;
            }
            if (count == 0) {
                result.add(start);
            }
            if (p.length() == (i - start + 1)) {
                if (pchars[index(s, start)] >= 0) {
                    ++count;
                }
                ++pchars[index(s, start)];
                ++start; // add start at the last step
            }
        }
        return result;
    }

    public static int index(String s, int pos) {
        return s.charAt(pos) - 'a';
    }

    public static void main(String[] args) {
        Anagrams anag = new Anagrams();
        String s = "";
        String p = "";
        System.out.println("[]");
        System.out.println(findAllAnagrams(s, p));
        s = "cbaebabacd";
        p = "abc";
        System.out.println("[0, 6]");
        System.out.println(findAllAnagrams(s, p));
        s = "abab";
        p = "ab";
        System.out.println("[0, 1, 2]");
        System.out.println(findAllAnagrams(s, p));
    }
}
