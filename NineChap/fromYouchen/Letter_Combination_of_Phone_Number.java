/**
 * Round 2 - 3/27
 */
public class Solution {
    public ArrayList<String> letterCombinations(String digits){
        ArrayList<String> res = new ArrayList<String>();
        if(digits == null || digits.length() == 0)
            return res;
    
        String[] pad = {
            " ", 
            "", "abc", "def", 
            "ghi", "jkl", "mno", 
            "pqrs", "tuv", "wxyz"
        };
        helper(res, "", pad, digits, 0);
        return res;
    }
    // private void helper(ArrayList<String> res, String list, String[] pad, String digits, int pos){
    //  if(list.length() == digits.length() ){
    //      res.add(new String(list));
    //      return;
    //  }
    
    //  String letters = pad[digits.charAt(pos) - '0'];
    //  for(int i = 0; i < letters.length(); i++){
    //      list +=  letters.charAt(i);
    //      helper(res, list, pad, digits, pos + 1);
    //      list = list.substring(0, list.length() - 1);
    //  }
    // }
    
    
    //2nd round Mar 27 - 12:32 - 12:40
    private void helper(ArrayList<String> res, String sub, String[] pad, String digits, int pos){
        if(sub.length() == digits.length()){
            res.add(new String(sub));
            return;
        }
        String letters = pad[digits.charAt(pos) - '0'];
        for(int i = 0; i < letters.length(); i++){
            sub += letters.charAt(i);
            /**
              * Notes on "pos + 1":
              * Since every recursion step, you need to pass the next position of
              *         the ORIGINAL String instead of the next position of the letters.
              */
            helper(res, sub, pad, digits, pos + 1);
            sub = sub.substring(0, sub.length() - 1);
        }
    }
}









/**
 * O(?)
 * O(?)
 * 192 ms
 * 
 * Note:
 *      Try to understand it again!
 */
public class Solution {
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        if(digits == null || digits.length() == 0)
            return res;
            
        String[] pad = new String[]{
            	" ", 
            	"", "abc", "def", 
            	"ghi", "jkl", "mno", 
            	"pqrs", "tuv", "wxyz"
        };
        helper(res, digits, "", pad, 0);
        return res;
    }
    private void helper(ArrayList<String> res, String digits, String list, String[] pad, int pos){
        if(list.length() == digits.length()){
            res.add(new String(list));
            return;//Here is very important!!! must return!
        }
        
        String letter = pad[digits.charAt(pos) - '0'];
        for(int i = 0; i < letter.length(); i++){
            list += letter.charAt(i);
            helper(res, digits, list, pad, pos + 1);
            list = list.substring(0, list.length() - 1);
        }
    }
}