/**
 * O(n)
 * O(n)
 * 500 ms
 * 
 * String
 * StringBuffer
 * Array
 * Char initilized as '\u0000'
 * 
 * Note:
 *      Think carefully.
 *      Do not re-declare varible.
 */ 
public class Solution {
    // 1: 23 min
    // 2: 23 min
    // 3: 23 min
    // 4: 24 min
    // 5: 25 min
    // 6: 35 min
    // 7: 38 min (Accepted)
    public String convert(String s, int nRows) {//20150122 18:50 - 19:28
        if(nRows == 1 || s.length() <= 1 || s.length() <= nRows) return s;
        
        int nCols = s.length() / (nRows * 2 - 2) * (nRows - 1) + s.length() % (nRows * 2 - 2);//nRows * 2 - 2 = nRows + (nRows - 2)
        char[][] arr = new char[nRows][nCols];
        
        int sIndex = 0;
        int r, c = 0;
        //assgin the 2d array.
        while(sIndex < s.length()){
            for(r = 0; r <= nRows - 1; r++){
                arr[r][c] = s.charAt(sIndex);
                sIndex++;
                if(sIndex == s.length()) break;
            }
            
            r--;
            while(true){
                if(sIndex == s.length()) break;
                r--;
                if(r == 0) break;
                c++;
                arr[r][c] = s.charAt(sIndex);
                sIndex++;
            }
            c++;
        }
        
        StringBuffer sb = new StringBuffer();
        
        for(char[] ca : arr)
            for(char cc : ca)
                if(cc != '\u0000')
                    sb.append(String.valueOf(cc));
        
        return sb.toString();
        
    }
}