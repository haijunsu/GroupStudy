/**
 * O(n)
 * 250 ms
 *
 * Bit manipulation
 */
import java.util.*;
public class Solution {
    public int singleNumber(int[] A) {
        /**
         * #1 trial Hashtable
         */
//         Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
// 		        int key = 0, target = 0;
// 		        for(int i = 0; i < A.length; i++){
// 		            if(ht.get(A[i]) == null) {key = 1; target = A[i];}
// 		            else if(ht.get(A[i]) == 1) key = 2;
// 		            ht.put(A[i], key);
// //		        	if(ht.contains(A[i])) ht.remove(new Integer(A[i]));
// //		        	else ht.put(A[i], 0);
// 		        }
// 		        return target;
        /**
         * #2 Bit Manipulation
         * Idea is:
         *      0 XOR anything = anything;
         *      A XOR A = 0;
         *      A XOR B XOR C XOR D = A XOR D XOR B XOR C (Order doesn't matter).
         */
        int x = 0;
        for(int i : A){
            x = x ^ i;
        }
        return x;
    }
}