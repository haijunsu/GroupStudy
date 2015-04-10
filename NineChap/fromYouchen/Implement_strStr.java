/**
 * O(mn)
 * O(1)
 * 723 ms
 * 
 * From Official Solution:
 *      You could demonstrate to your interviewer that this problem 
 *      can be solved using known efficient algorithms such as 
 *      Rabin-Karp algorithm, KMP algorithm, and the Boyer- Moore algorithm
 * 
 * Note:
 *      Little hard to implement.
 *      DO THIS AGAIN.
 */
public class Solution {
    /**
     * Example:
     *      haystack: abcdefg00abcde
     *      needle:   cde
     *      return:   2
     */
    public int strStr(String haystack, String needle) {
        // if(
        //     haystack == null || needle == null || 
        //         (haystack.length() == 0 && needle.length() != 0)
        //         )
        //     return -1;
            
        // if( needle.length() == 0)
        //     return 0;
            
        
        // for(int i = 0; i < haystack.length(); i++){
        //     int checkOneByOne = i;
        //     int n = 0;
        //     while(
        //             checkOneByOne < haystack.length() && n <= needle.length() 
        //             &&haystack.charAt(checkOneByOne) == needle.charAt(n)
        //             ){
        //                 checkOneByOne++;
        //                 n++;
        //                 if(n == needle.length() )
        //                     return checkOneByOne - n;
        //             }
        // }
        // return -1;
        
        
        
        /**
         * From JiuZhang
         * This one is short!
         * 
         * O(mn)
         * O(1)
         * 292 ms
         */
        if(haystack == null || needle == null)
            return -1;
        
        int hlen = haystack.length(), nlen = needle.length();
        for(int i = 0; i < hlen - nlen + 1; i++){
            int j;
            for(j = 0; j < nlen; j++){
                if(haystack.charAt(i + j) != needle.charAt(j) )
                    break;
            }
            if(j == nlen)
                return i;
        }
        return -1;
    }
}