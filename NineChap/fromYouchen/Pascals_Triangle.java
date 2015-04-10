import java.util.*;
/**
 * O(1 + 2 + 3 + ... + numRows) = O(n^2)
 * O(n^2)
 * 215 ms
 * 
 * Note:
 *      Be careful for the index that involved.
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {//Mar 6 - 18:20 - 19：07  (47 m)
        ArrayList<ArrayList<Integer>> outer = new ArrayList<ArrayList<Integer>>(numRows);
        
        for(int i = 0; i <= numRows - 1; i++){//i is the level currently working on.
            ArrayList<Integer> inner = new ArrayList<Integer>(i + 1);
            inner.add(1);
            
            if(i >= 1){
                ArrayList<Integer> lastLevel = outer.get(i - 1);
                for(int j = 0; j <= lastLevel.size() - 2; j++){
                    inner.add(lastLevel.get(j) + lastLevel.get(j + 1));
                }
                inner.add(1);
            }
            outer.add(inner);
        }
        return outer;
    }
}