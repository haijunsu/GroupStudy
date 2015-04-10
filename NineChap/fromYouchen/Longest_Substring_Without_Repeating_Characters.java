/**
 * O(n)
 * 325 ms
 * 
 * Hashtable
 *      constructor, containsKey(), remove()
 * Math.max(,)
 * String
 *      indexOf(char c, fromIndex)
 */
import java.util.*;
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        //if(s.length() <= 1) return s.length();

		Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
		int longest = 0, left = 0;
		
		for(int i = 0; i < s.length(); i++){
			if(ht.containsKey(s.charAt(i))){
				int occurPos = s.indexOf(s.charAt(i), left);
				while(left <= occurPos) {
					ht.remove(s.charAt(left));
					left++;
				}
			}
			ht.put(s.charAt(i), 0);
			longest = Math.max(i - left + 1, longest);
		}
		return longest;
    }
}