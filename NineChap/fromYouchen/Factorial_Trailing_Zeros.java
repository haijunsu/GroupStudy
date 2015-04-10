/**
 * O(Math.floor(log5 N)) - log5 N indicate that 5 is the base, N is the logrithm number.
 * O(1)
 * 212 ms
 * 
 * Note:
 *      UNDERSTAND THE PROBLEM BEFORE DOING IT!!!!
 *      Try to analyse how to solve the problem efficiently.
 *          Like this one, count 5 instead count 2.
 *      Easy one, but not easy to have an bug free code.
 * 
 * Reference: (great explanation)
 *      http://www.danielbit.com/blog/puzzle/leetcode/leetcode-factorial-trailing-zeroes#comment-2716
 */
public class Solution {
    public int trailingZeroes(int n) {
        if(n < 0) return 0;
        int count = 0;
        for(long i = 5; n / i >= 1; i *= 5)//Note: i must be long becasue i might exceeds the Integer.MAX_VALUE.
            count += n / i;
        return count;
        
        
        
        /**
         * 1st Trail. FAIL.
         * Because I understanding the problem a wrong way.
         * I was thinking the problem is asking to compute how many trailing zeros 
         *      in a given integer n.
         * Totally different! Be careful reading the question!!
         */
        // if(n <= 0) return 0;
        
        // String s = Integer.toString(n);
        // int left = 0, right = s.length() - 1, mid = left + (right - left) / 2;
        
        // //move left if right part is 0.
        // //move right if right part is not 0;
        // while(left < right){
        //     if(Integer.parseInt(s.substring(mid, right + 1)) == 0)
        //         right = mid;
        //     else
        //         left = mid;
                
        //     if(left + 1 == right) break;
        //     mid = left + (right - left) / 2;
        // }
        // if(s.charAt(left) != '0' && s.charAt(right) != '0') return 0;
        // if(s.charAt(left) == '0') return s.length() - left;
        // return s.length() - right;
        
    }
}