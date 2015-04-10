/**
 * O(1 * 2 * (n - 1) * (n - 2) * ... * 3 * 2 * 1) = O(n!)
 *      just use the left-side sign for computing.
 * O(n)
 *      since the deepest recursion is n steps.
 * 216 ms
 * 
 * Note:
 *      Key idea is to use the helper function to justify the "left" and "right"
 *      as the recursion goes.
 */
public class Solution {
    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<String>();
        if(n <= 0)
            return res;
            
        helper(res, "", n, n);
        return res;
    }
    private void helper(ArrayList<String> res, String s, int left, int right){
        /**
         * left > right indicating that:
         *      Left-side sign left more than the right-side sign.
         *      like: n = 3 ())) left = 2, right = 0
         */
        if(left > right)//formations like ")(" is illegal.
            return;
            
        if(left == 0 && right == 0){//all the left and right are used up.
            res.add(s);
            return;
        }
        
        if(left > 0)
            helper(res, s + '(', left - 1, right);
        if(right > 0)
            helper(res, s + ')', left, right - 1);
    }
}