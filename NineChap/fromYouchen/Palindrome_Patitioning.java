/**
 * suppose s.length() = n;
 * O(1Cn + 2Cn + 3Cn + ... + nCn) = O(2^n - 1)
 * O(n)
 * 261 ms
 *
 * Note:
 *      for the proof of the time complexity equation.
 *      think the case when x = 1 in following equation:
 *          (1 + x)^n = nC0*x^0 + nC1*x + nC2*x^2 + nC3*x^3 + .... + nC(n-1)*x^(n-1) + nCn * x^n
 *                2^n = 1 + nC1 + nC2* + nC3* + .... + nC(n-1) + nCn
 */
public class Solution {
    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        if(s == null || s.length() == 0) return res;
        
        //s is not null && s.length() >= 1
        helper(res, new ArrayList<String>(), s, 0);
        return res;
    }
    private void helper(ArrayList<ArrayList<String>> res, ArrayList<String> list, String s, int pos){
        if(pos == s.length()){
            res.add(new ArrayList<String>(list));
            return;
        }
        for(int i = pos + 1; i <= s.length(); i++){//be careful with equal sign in "i <= s.length()"
            String sub = s.substring(pos, i);
            
            if(! isP(sub)) continue;
            
            list.add(sub);
            helper(res, list, s, i);//here, since the initial value of i in for loop is "pos + 1", no need to have "i + 1" here.
            list.remove(list.size() - 1);
        }
    }
    private boolean isP(String s){
        int left = 0, right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
}