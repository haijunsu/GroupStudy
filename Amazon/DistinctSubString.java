/*
 * Give a string and number k. The string only has lowercase characters.
 * return all substring which length is k and has no duplicate characters.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class DistinctSubString {

    public static List<String> solution(String str, int k) {
        List<String> ans = new  ArrayList<String>();
        for (int i = 0; i <= str.length() - k; ++i) {
            String subs = str.substring(i, i + k);
            if (isDistinct(subs) && !ans.contains(subs)) {
                ans.add(subs);
            }
        }

        return ans;
    }

    static boolean isDistinct(String str) {
        int[] sChars = new int[26];
        for (int i = 0; i < str.length(); ++i) {
            int pos = str.charAt(i) - 'a';
            if (sChars[pos] > 0) return false;
            ++sChars[pos];
        }
        return true;
    }

    public static void main(String args[]) {
        String str = "abcdedfabcdw";
        int k = 4;
        String[] expected = {"abcd", "bcde", "edfa", "dfab", "fabc", "bcdw"};
        test(str, k, expected);
    }

    static void test(String input, int k, String[] expected) {
        List<String> ans = solution(input, k);
        List<String> answer = Arrays.asList(expected);

        System.out.println("Expected: " + answer + ", your answer: " + ans);
        System.out.println(ans.toString().equals(answer.toString())? "Accept": "Wrong answer");

    }
}
