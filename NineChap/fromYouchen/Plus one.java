/**
 * O(n)
 * O(n)
 * 211 ms
 * 
 * Array
 * Math
 * 
 * Note:
 *      Just imitate the human computing procedure, check
 *      the value changes before you using it.
 *      Like this problem:
 * 
 *          int val = digits[index] + carry;
 *          digits[index] = (val) % 10;//#1
 *          carry = (val) / 10;//#2
 *          index--;
 *          
 *      You need a val for computing rather than replace val
 *      in #1 and #2 line.
 */ 
public class Solution {
    public int[] plusOne(int[] digits) {
        
        if(digits[digits.length - 1] <= 8){
            digits[digits.length - 1]++;
            return digits;
        }
        
        //the rightmost digits must be 9;
        int index = digits.length - 1;
        int carry = (digits[index] + 1) / 10;
        digits[index] = (digits[index] + 1) % 10;
        index--;
        
        while(index >= 0){
            int val = digits[index] + carry;
            digits[index] = (val) % 10;
            carry = (val) / 10;
            index--;
        }
        
        if(carry == 1){
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for(int i = 1; i < newDigits.length; i++)
                newDigits[i] = digits[i - 1];
            return newDigits;
        }else{
            return digits;
        }
    }
}