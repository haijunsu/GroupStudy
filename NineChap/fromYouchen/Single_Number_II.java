import java.util.*;
public class Solution {
    public int singleNumber(int[] A) {
        Hashtable<Integer, Integer> hs = new Hashtable<Integer, Integer>();
        for(int i : A){
            if(! hs.containsKey(i) )
                hs.put(i, 1);
            else if(hs.get(i) + 1 == 3)
                hs.remove(i);
            else
                hs.put(i, 2);
        }
        
        return hs.keys().nextElement();
    }
}