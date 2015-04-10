/**
 * O(m + n)
 * O(m + n)
 * 268 ms
 * 
 * Note:
 *      Think carefully.
 *      Easy one, Nothing to care.
 */
public class Solution {
    public String addBinary(String a, String b) {
        if(a.equals("0") ) return b;
        if(b.equals("0") ) return a;
        
        int indexA = a.length() - 1, indexB = b.length() - 1;
        String s = "", sum = "0", carry = "0";
        
        while(indexA >= 0 || indexB >= 0){
            String aCur = (indexA == -1)? "0" : String.valueOf(a.charAt(indexA));
            String bCur = (indexB == -1)? "0" : String.valueOf(b.charAt(indexB));
            if(carry.equals("0")){
                if(aCur.equals("1") && bCur.equals("1") ){
                    sum = "0";
                    carry = "1";
                }
                else if(aCur.equals("0") && bCur.equals("0") ){
                    sum = "0";
                    carry = "0";
                }
                else{
                    sum = "1";
                    carry = "0";
                }
            }
            else if(carry.equals("1")){
                if(aCur.equals("1") && bCur.equals("1") ){
                    sum = "1";
                    carry = "1";
                }
                else if(aCur.equals("0") && bCur.equals("0") ){
                    sum = "1";
                    carry = "0";
                }
                else{
                    sum = "0";
                    carry = "1";
                }
            }
            
            
            s = sum + s;
            
            indexA--;
            if(indexA < 0) indexA = -1;
            indexB--;
            if(indexB < 0) indexB = -1;
        } 
        if(carry.equals("1"))
            s = carry + s;
        return s;
        
    }
}