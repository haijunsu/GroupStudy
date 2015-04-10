/**
 * O(n)
 * 219 ms
 * 
 * Math
 */ 
public class Solution {
    public int titleToNumber(String s) {
        int num = 0;
        for(int i = s.length() - 1; i >= 0; i--)
            num += (s.charAt(i) - 'A' + 1) * Math.pow(26, (s.length() - 1 - i));
        return num;
        /**
         * Copied from
         * https://oj.leetcode.com/discuss/19725/solutions-languages-does-any-one-have-one-line-solution-java
         */
        //return s != "" ? 26 * titleToNumber( s.substr(0, s.size()-1 ) ) + s[s.size()-1] - 'A' + 1 : 0;
    }
}