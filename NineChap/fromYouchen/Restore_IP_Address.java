/**
 * O(?)
 * O(?)
 * 252 ms
 * 
 * Note: 
 *      This problem is different than the typical backtracking ones.
 *      Need to have a new structure to solve. (i.e. use count, temp...)
 */
public class Solution {
    public ArrayList<String> restoreIpAddresses(String s) {//17:55
        ArrayList<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0 || s.length() >= 13)//Ip address at most has 12 digits.
            return res;
        
        helper(res, "", s, 0);
        return res;
    }
    private void helper(ArrayList<String> res, String temp, String s, int count){
       if(count == 3 && validIP(s)){
           res.add(temp + s);
       }
       
       for(int i = 1; i <= 3 && i < s.length(); i++){
           String sub = s.substring(0, i);
           if(validIP(sub))
                helper(res, temp + sub + ".", s.substring(i), count + 1);
       }
    }
    private boolean validIP(String s){
        /**
         * It's should be aknowledged that "010", "001" "03" are all ILLEGAL!!!
         * 
         * Test case:
         *      Input:      "010010"
         *      Expected:	["0.10.0.10","0.100.1.0"]
         */
        if (s.charAt(0)=='0') return s.equals("0");//this line is very important!!!
        if(s.length() >= 4)
            return false;
        int intIP = Integer.parseInt(s);
        if(intIP >= 0 && intIP <= 255)
            return true;
        return false;
    }
}