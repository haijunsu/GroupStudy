public class Solution {
    /**
     * O(n^2)
     * O(1)
     * 272 ms
     * 
     * Note:
     *      This algorithm is simply iterating the chars of the array and while in doing this,
     *          it checks if take the current visiting char (or this visiting char and its neighbor right next to it)
     *          as a pivot(s), can it be extended to the both direction in order to form the longest Palindrome.
     */
    public String longestPalindrome(String s) {//Mar 8 15:51 - 22:17
    
        int len = s.length();
        if(len <= 1 || s == null) return s;

        String longest = "";
        for(int i = 0; i < len; i++){
        	String findPalindromeBySingleChar = findPalindrome(s, i, i);
        	if(findPalindromeBySingleChar.length() > longest.length())
        		longest = findPalindromeBySingleChar;

        	/**
        	  * The method findPalindrome has the constrain regarding the situation of
        	  * 	"out of bound", so here, you may pass (s, i, i + 1) to the method.
        	  */
        	String findPalindromeByTwoChars = findPalindrome(s, i, i + 1);
        	if(findPalindromeByTwoChars.length() > longest.length())
        		longest = findPalindromeByTwoChars;
            }
            return longest;
   	
   	
   		
    
    /**
     * 2nd Trial. FAIL.
     * DP approach.
     * 
     * O(n^2)
     * O(n^2)
     * 
     * Did not pass the test case of input which has the length of 1000.
     */
    //     int len = s.length();

    // 	if(s == null) return s;
    // 	if(len <= 1) return s;

    // 	String longest = "";
    // 	int[][] dp = new int[len][len];
    	
    // 	for(int i = 0; i <= len - 1; i++){
    // 	    //every single letter is a palindrome;
    // 		dp[i][i] = 1;
    		
    // 	    if(i <= len - 2 && s.charAt(i) == s.charAt(i + 1) ){
    // 			dp[i][i + 1] = 1;
    // 			longest = s.substring(i, i + 2);
    // 		}
    // 	}

    // 	for(int window = 3; window <= len; window++){
    // 		for(int start = 0; start <= len - window; start++){
    		    
    // 			int end = start + window - 1;
    			
    // 			if(s.charAt(start) == s.charAt(end)){
    // 				dp[start][end] = dp[start + 1][end - 1];
    // 				if(dp[start][end] == 1 && window > longest.length())
    // 					longest = s.substring(start, end + 1);
    // 			}else{
    // 				dp[start][end] = 0;
    // 			}
    			
    // 		}
    // 	}
    // 	return longest;
    
    
    /**
     * Naive Approach FAIL
     * O(n^3)
     * O(1)
     */
    //     int len = s.length();
    //     if(s == null || len == 0) return null;
    //     if(len == 1) return s;
        
    //     /**
    //      * Naive approach:
    //      *      Test if there exist palindrome for length 1, 2, 3, ..., len
    //      */
    //      String longest = "";
    //      for(int i = len; i >= 2; i--){
    //          for(int j = 0; j <= len - i; j++){
    //              String sub = s.substring(j, j + i);
    //              if(isP( sub ) && sub.length() > longest.length())
    //                 longest = sub;
    //          }
    //      }
    //      //No Palindromic Substring which has length of more than 2 is found.
    //      //     just return any of the single letter. since it's longest palindrome is 1;
    //      if(longest == "") return s.substring(0, 1);
    //      return longest;
    // }
    // public Boolean isP(String s){
    //     if(s.length() == 1) return true;
        
    //     int r = s.length() - 1, l = 0;
    //     while(l <= r){
    //         if(s.charAt(l) != s.charAt(r))
    //             return false;
    //         l++;
    //         r--;
    //     }
    //     return true;
    }
    /**
   	 * This method is returning the longest Palindrome based on the
   	 *		given string and the pivot index which either single index or two indices.
   	 */
   	public String findPalindrome(String s, int left, int right){
   		while(left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)){
   			left--;
   			right++;
   		}
   		return s.substring(left + 1, right);
   	}
}