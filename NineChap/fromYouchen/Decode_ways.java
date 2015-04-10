/**
 * DO THIS AGAIN.
 * 
 * O(n)
 * O(n)
 * 224 ms
 * 
 */
public class Solution {
    public int numDecodings(String s) {//0:28
        if(s.length() == 0 || s == null) return 0;
        
        //now, s.length() must >= 1
        if(s.charAt(0) == '0') return 0;
        
        //s.charAt(0) != '0' => the first char is valid.
        int[] dpArr = new int[s.length() + 1];
        dpArr[0] = 1;
        dpArr[1] = 1;
        
        //NOTICE: 
        //		arr[i] corresponds to s.substring(i - 1, i);
        for(int i = 2; i <= dpArr.length - 1; i++){
            //if the succeeding char is valid, at least now,
            //      the decoding way is remain the same.
            if(s.charAt(i - 1) != '0')
                dpArr[i] = dpArr[ i - 1 ];
            
            //if the combination of succeeding char AND the previous
            //      char is valid, the decoding way is ++;
            if(Integer.valueOf(s.substring(i - 2, i)) >= 10 && Integer.valueOf(s.substring(i - 2, i)) <= 26){
            		dpArr[i] = dpArr[i] + dpArr[i - 2];
            }
        }
        return dpArr[s.length()];
    }
}