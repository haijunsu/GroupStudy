/**
 * O(n)
 * 14 ms
 * 
 * Array
 */
import java.util.*;
public class Solution {
    public int removeElement(int[] A, int elem) {
    	//Solution #1 529 ms
        ArrayList<Integer> arr = new ArrayList<Integer>();
        
		for(int item: A) {
			arr.add((Integer)item);
		}
		
// 		while(arr.contains(new Integer(elem)))
		while(arr.remove(new Integer(elem)));
		
		for(int i = 0; i < arr.size(); i++)
		    A[i] = arr.get(i);
		    
		return arr.size();

		//Solution #2 440 ms
		//Copied from https://oj.leetcode.com/discuss/3753/my-solution-for-your-reference
		// int begin = 0;
		// for (int i = 0; i < A.length; ++i) 
		// 	if (A[i] != elem) 
		// 		A[begin++] = A[i];
		// return begin;

		//Solution #3 440 ms
		// int size = 0;
		// for (int i = 0; i < A.length; ++i) {
		// 	if (A[i] != elem) {
		// 		A[size] = A[i];
		// 		size++;
		// 	}
		// }
		// return size;
	}//removeElement
}