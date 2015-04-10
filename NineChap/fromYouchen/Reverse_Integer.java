/**
 * O(1) mostly 9 times since integer is 9 digits at most.
 * O(1)
 * 287 ms
 * 
 * Note:
 *      check overflow could check if the increasing number is overflowed by / 10.
 */
public class Solution {
    public int reverse(int x) {
        int val = 0;
        while(x != 0){
            int num = x % 10;
            if(val > Integer.MAX_VALUE / 10 || val < Integer.MIN_VALUE / 10)
                return 0;
            val = val * 10 + num;
            x = x / 10;
        }
        return val;
    }
}