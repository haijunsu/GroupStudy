/**
 * O(n)
 * 259 ms
 * 
 * String
 *      charAt();
 * switch
 * It's easy to traverse Roman Numeral from right to left!
 */
public class Solution {
    public int romanToInt(String s) {
        int val = 0, right = 0, num = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            switch(s.charAt(i)){
                case 'M':
                    val = 1000;
                    break;
                case 'D':
                    val = 500;
                    break;
                case 'C':
                    val = 100;
                    break;
                case 'L':
                    val = 50;
                    break;
                case 'X':
                    val = 10;
                    break;
                case 'V':
                    val = 5;
                    break;
                case 'I':
                    val = 1;
                    break;
            }//switch
            if(i == s.length() - 1) num += val;
            if(s.length() == 1) return val;
            if(i < s.length() - 1){
                if(val >= right)
                    num += val;
                else
                    num -= val;
            }
            right = val;
        }//for
        return num;
    }//romanToInt
    
}