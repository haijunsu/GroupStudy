/**
 * Round 2 - 4/16 (4 min)
 */
public class Solution {
    public int removeDuplicates(int[] A) {//17:47 - 17:51 (4 min)
        if(A == null || A.length == 0)
            return 0;
        
        int left = 1, right = 1, time = 1;
        while(right < A.length){
            if(A[right] != A[right - 1]){
                A[left] = A[right];
                left++;
                time = 1;
            }
            else if(A[right] == A[right - 1] && time == 1){
                A[left] = A[right];
                time = 2;
                left++;
            }
            
            right++;
        }
        return left;
    }
}




/**
 * O(n)
 * O(1)
 * 264 ms
 * 
 * Note:
 *      Easy but error-prone problem.
 *      Be careful, time should be 1, not 0.
 */
public class Solution {
    public int removeDuplicates(int[] A) {//20:44 - 20:52 (8 min)
        if(A == null || A.length == 0)
            return 0;
        
        int left = 1, right = 1;
        int time = 1;
        while(right < A.length){
            if(A[right] != A[right - 1]){
                A[left] = A[right];
                left++;
                time = 1;
            }
            else if(A[right] == A[right - 1] && time == 1){
                time++;
                A[left] = A[right];
                left++;
            }
            right++;
        }
        return left;
    }
}