/**
 * O(n)
 * O(n + n) = O(n)
 * 540 ms
 * 
 * Note:
 *      Simple one.
 */
public class Solution {
    public String reverseWords(String s) {//4/19 00:56 - 1:10 (14 min)
        if(s == null || s.length() <= 0)
            return s;
            
        s = s.trim();
        
        String res = "", word = "";
        int p = 0;
        while(p < s.length()){
            while(p < s.length() && s.charAt(p) == ' ')
                p++;
            while(p < s.length() && s.charAt(p) != ' '){
                word += s.charAt(p);
                p++;
            }
            res = word + res;
            res = " " + res;
            word = "";
        }
        
        res = res.trim();
        return res;
    }
}