public class Solution {
    /**
     * O(n / 1000 + n / 900 + n / 500 + n / 400 + n / 100 + 
     *          n / 90 + n / 50 + n / 40 + n / 10 + n / 9 + n / 5 + n / 4 + n / 1)
     * O(1)
     * 332 ms
     * 
     * Note:
     *      Code might be a little long.
     *      See the short solution at the end.
     */     
    public String intToRoman(int num) {//Mar 10 - 17 : 45 - 17: 59 (14 min)
        // if(num <= 0) return null;
        /**
         * Symbol	Value
                I	1
                V	5
                X	10
                L	50
                C	100
                D	500
                M	1,000
        */
        String str = "";
        while (num / 1000 >= 1){
            str += "M";
            num -= 1000;
        }
        
        while(num / 900 >= 1){
            str += "CM";
            num -= 900;
        }
        while(num / 500 >= 1){
            str += "D";
            num -= 500;
        }
        while(num / 400 >= 1){
            str += "CD";
            num -= 400;
        }
        while(num / 100 >= 1){
            str += "C";
            num -= 100;
        }
        
        while(num / 90 >= 1){
            str += "XC";
            num -= 90;
        }
        while(num / 50 >= 1){
            str += "L";
            num -= 50;
        }
        while(num / 40 >= 1){
            str += "XL";
            num -= 40;
        }
        while(num / 10 >= 1){
            str += "X";
            num -= 10;
        }
        
        
        while(num / 9 >= 1){
            str += "IX";
            num -= 9;
        }
        while(num / 5 >= 1){
            str += "V";
            num -= 5;
        }
        while(num / 4 >= 1){
            str += "IV";
            num -= 4;
        }
        while(num / 1 >= 1){
            str += "I";
            num -= 1;
        }
        
        return str;

    }
}

/**
 * Another short solution.
 * Reference
 *  https://stupidcodergoodluck.wordpress.com/2014/03/31/leetcode-integer-to-roman/
 */
//  public class Solution {
//     public String intToRoman(int num) {
//         String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
//         int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
//         StringBuilder ret = new StringBuilder();
//         for (int i=0; i<values.length; i++) {
//             while (num >= values[i]) {
//                 ret.append(symbols[i]);
//                 num -= values[i];
//             }
//         }
//         return new String(ret);
//     }
// }