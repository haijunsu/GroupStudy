import java.util.*;
public class Solution {
    public int majorityElement(int[] num) {
        Hashtable<Integer, Integer> hs = new Hashtable<Integer, Integer>();
        for(int i : num){
            if( ! hs.containsKey(i) )
                hs.put(i, 1);
            else
                hs.put(i, hs.get(i) + 1);
                
            if(hs.get(i) > Math.floor(num.length / 2.0))
                return i;
        }
        return -1;   
    }
}