/**
 * O(digits of n)
 * O(digits of n)
 * 174 ms
 * 
 * Note:
 *      This is easy one but also easy to get wrong.
 *      The base conversion is involved. 10 -> 26
 *      DO THIS AGAIN.
 */
public class Solution {
    public String convertToTitle(int n) {//Mar 11 - 11:46
        StringBuilder sb = new StringBuilder();
        
        while(n > 0){
            n--;
            int digit = n % 26;//0 ~ 25
            sb.insert(0, (char)('A' + digit));
            n /= 26;
        }
        return sb.toString();
    }
}