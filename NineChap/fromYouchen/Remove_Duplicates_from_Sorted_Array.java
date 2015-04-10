/**
 * Round 2 - 4/6 (4 min)
 * O(n)
 * O(1)
 * 259 ms
 */
public class Solution {
    public int removeDuplicates(int[] A) {//20:17 - 20:21 (4 min)
        if(A == null || A.length == 0)
            return 0;
        
        int left = 1, right = 1;
        while(right < A.length){
            if(A[right] != A[right - 1]){
                A[left] = A[right];
                left++;
            }
            right++;
        }
        return left;
    }
}





/**
 * O(n)
 * 264 ms
 * 
 * Array
 *      Two pointers
 */
public class Solution {
    public int removeDuplicates(int[] A) {
        /**
         * This Problem cost so much time,
         * Do it again.
         */
        if(A.length <= 1) return A.length;
        int i = 0, j = 1;
        while(j < A.length){
            if(A[j] == A[i]) j++;
            else{
                i++;
                A[i] = A[j];
                j++;
            }
        }
        return i + 1;
        
//       //HashSet
// 		HashSet<Integer> hs = new HashSet<Integer>();
// 		for(int item : A)
// 			hs.add(item);
// 		int index = 0;
// 		for(Object item : hs.toArray())
// 			A[index++] = (int) item;

// 		for(int i : A)
// 			System.out.println(i + " ");
		
// 		return hs.size();
        
        
    }//method
}