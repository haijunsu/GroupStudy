/**
 * O(1)
 * 166 ms
 * 
 * String
 *      trim(), lastIndexOf(char c), substring
 */
public class Solution {
    public int lengthOfLastWord(String s) {//27 min
        s = s.trim();
        int lastSpace = s.lastIndexOf(' ');
        if(lastSpace == -1) return s.length();
        return s.substring(lastSpace + 1, s.length() ).length();
        //better way for return statement above
        //return s.length() - 1 - lastSpace;
    }
}