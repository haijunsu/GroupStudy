public class Solution {
    /**
     * O(length of x)
     * O(1)
     * 420 ms
     * 
     * Note:
     *      Easy like problem but Easy to get wrong.
     *      DO THIS AGAIN!!!
     * 
     *      Be careful and also should be proficient about the % and / operation for getting the digits of numbers.
     */
    public boolean isPalindrome(int x) {
        if(x <= -1) return false;//negative number is not Palindrome.
        if(x <= 9) return true;
        
        int digits = 1;
        while(x / digits >= 10)
            digits *= 10;
        //digits of "digits" is the same as the digits of x.
        //      i.e. input 7944, digits = 1000.
        
        while(x > 0){
            int left = x / digits;
            int right = x % 10;
            if(left != right) return false;
            
            x = x % digits / 10;
            digits /= 100;
        }
        return true;
        
        
        
        /**
         * Accepted. But with extra space used.
         * O(length of x)
         * O(length of x)
         * 388 ms
         */
        // if(x <= 9 && x >= 0) return true;
        
        // String s = Integer.toString(x);
        // int l = 0, r = s.length() - 1;
        // while(l <= r){
        //     if(s.charAt(l) != s.charAt(r))
        //         return false;
        //     l++;
        //     r--;
        // }
        // return true;
    }
}