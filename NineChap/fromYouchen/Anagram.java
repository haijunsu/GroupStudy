/**
 * DO THIS AGAIN.
 * 
 * O(n)
 * O(n)
 * 578 ms
 * 
 * Note:
 *      ArrayList
 *      Hashtable
 *          containsKey(), addAll()
 *      Array  
 *          Arrays.sort()
 *      String
 *          String.valueOf(char[] x)
 * 
 * Please Note!!
 *      contains(VALUE), containsKey(KEY)
 * 
 */ 
import java.util.*;
public class Solution {
    public List<String> anagrams(String[] strs) {//18:41
        ArrayList<String> l = new ArrayList<String>();
        if(strs.length == 0) return l;
        
        Hashtable<String, ArrayList<String>> ht = new Hashtable<String, ArrayList<String>>();
        ArrayList<String> all;
        for(String s : strs){
        	char[] temp = s.toCharArray();
            Arrays.sort(temp);
            String sortedStr = String.valueOf(temp);

            if(ht.containsKey(sortedStr) ) {//does not have
            	ht.get(sortedStr).add(s);
            }
            else {
            	all = new ArrayList<String>();
            	all.add(s);
                ht.put(sortedStr, all);
            }
        }
        
        for(ArrayList<String> iter : ht.values()) {
        	if(iter.size() <= 1) continue;
        	else l.addAll(iter);
        }
        
        return l;
    }
}