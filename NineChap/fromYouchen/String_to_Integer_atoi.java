/**
 * O(n)
 * O(1)
 * 217 ms
 * 
 * Note:
 *      The highlight of this algorithm is that the using of curIndex.
 *      It keeps knowing if the current Index char is valid for converting and 
 *          terminating iterate at which is invalid.
 * Reference:
 *      http://www.cnblogs.com/springfor/p/3896499.html
 */
public class Solution {
    public int atoi(String str) {
        if(str.length() == 0 || str == null) return 0;
        
        str = str.trim();
        
        boolean negative = false;//, positive = false;
        int curIndex = 0;

        char beginChar = str.charAt(curIndex);
        if(beginChar == '+') {
            // positive = true;
            curIndex++;
        }
        else if(beginChar == '-') {
            negative = true;
            curIndex++;
        }

        int num = 0;
        int power10 = 0;
        while(curIndex < str.length() && str.charAt(curIndex) <= '9' && str.charAt(curIndex) >= '0'){
        	if(num > Integer.MAX_VALUE / 10 || 
        		( num == Integer.MAX_VALUE / 10 && (str.charAt(curIndex) - '0') > Integer.MAX_VALUE % 10))
        		return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        	num = num * 10 + (str.charAt(curIndex) - '0');
        	curIndex++;
        }
        
        return negative ? 0 - num : num;
        
        /**
         * 1st Trail. FAIL on the overflow test case: -2,147,483,648
         */
        // int len = str.length();
        // if(len == 0 || str == null) return 0;
        
        // str = str.trim();
        // len = str.length();
        
        // boolean negative = false, positive = false;
        
        // char begin = str.charAt(0);
        // if(begin == '+') {
        //     positive = true;
        // }
        // else if(begin == '-') {
        //     negative = true;
        // }
        
        // if(negative || positive){
        //     str = str.substring(1, len);
        //     len = str.length();
        // }
        
        
        
        // // boolean negative = (str.charAt(0) == '-')? true : false;
        // // if(negative){
        // //     str = str.substring(1, len);
        // //     len = str.length();
        // // }
        
        // // boolean positive = (str.charAt(0) == '+')? true : false;
        // // if(positive){
        // //     str = str.substring(1, len);
        // //     len = str.length();
        // // }
        
        
        // int ii = 0;
        // for(; ii < len; ii++){
        //     if(str.charAt(ii) > '9' || str.charAt(ii) < '0')
        //         break;//current i is the index of invalid char.
        // }
        // str = str.substring(0, ii);
        // len = str.length();
        // // if(str.length() == 0) return 0;
        
        // int num = 0;
        // int power10 = 0;
        // for(int i = len - 1; i >= 0; i--){
        //     //check overflow:
        //     if(num > Integer.MAX_VALUE / 10 && i >= 1) return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        //     else if(i == 1 && num == Integer.MAX_VALUE / 10 && str.charAt(i - 1) == '8' )
        //     num += Character.getNumericValue(str.charAt(i)) * (Math.pow(10, power10));
        //     power10++;
        // }
        
        // //see if the string is equal to the max value.
        // if(negative && num + Integer.MIN_VALUE == 0) return Integer.MIN_VALUE;
        // // if(positive && num)
        // // if(positive)
        // //     return num;
        
        // return negative ? 0 - num : num;
    }
}