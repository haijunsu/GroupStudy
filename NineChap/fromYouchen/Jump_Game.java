/**
 * O(n)
 * 237 ms
 *
 * Array
 * Greedy
 */
public class Solution {
    public boolean canJump(int[] A) {
        //1st trail
        // if(A.length == 0) return false;
        
        // int farestIndex = 0;
        // while(farestIndex < A.length - 1){
        //     if(A[farestIndex] == 0) break;
        //     farestIndex = farestIndex + A[farestIndex];
        // }
        // if(farestIndex >= A.length - 1) return true;
        // else return false;
        
        
        //2nd trail
        if(A.length == 0) return false;
        
        int farPos = 0;
        for(int i = 0; i <= farPos && i < A.length; i++){
            if(farPos < A[i] + i) farPos = A[i] + i;
            if(farPos >= A.length - 1) return true;
        }
        return false;
    }
}