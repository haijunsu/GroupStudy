/**
 * DO THIS AGAIN.
 */
public class Solution {
    int[] sol = new int[16000];
    public int maxSubArray(int[] A) {
        //Solution #1 - Time Limit Exceed
    //     for(int i = 0; i < sol.length; i++)
    //         sol[i] = -1;
    //     return maxSubArray_2(A, sol);
    // }
    
    // public int maxSubArray_2(int[] A, int[] sol){
    //     if(A.length == 0) return 0;
    //     if(A.length == 1) return A[0];
    //     if(A.length == 2) 
    //         return (A[0] >= A[1]) ? Math.max(A[0], (A[0] + A[1])) : Math.max(A[1], (A[0] + A[1]));
        
    //     if(sol[A.length - 1] != -1)
    //         return sol[A.length - 1];
        
    //     int[] Asub = Arrays.copyOfRange(A, 0, A.length - 1);
        
    //     sol[A.length - 1] = Math.max(maxSubArray_2(Asub, sol), A[A.length - 1]);
    //     return sol[A.length - 1];
    
    //Solution #2 - Time Limit Exceed
        // int[] sum = new int[A.length];
        // sum[0] = A[0];
        
        // for(int i = 1; i < A.length; i++){
        //     sum[i] = (sum[i - 1] > A[i])? Math.max(sum[i - 1], A[i] + sum[i - 1]) : Math.max(A[i], sum[i - 1] + A[i]); 
        // }
        // return sum[sum.length - 1];
        
        //Solution #3 - Accepted
        /**
         * O(n)
         * O(n)
         */
        //Idea comes from: 
        //  http://blog.csdn.net/fightforyourdream/article/details/14515425
        // int[] sum = new int[A.length];
        // sum[0] = A[0];
        
        // int max = A[0];
        
        // for(int i = 1; i < A.length; i++){
        //     sum[i] = Math.max(A[i], sum[i - 1] + A[i]);
        //     max = Math.max(max, sum[i]);
        // }
        // return max;
        
        
        
        //Solution #4
        /**
         * O(n)
         * O(1)
         */
        int sum = A[0];//= Integer.MIN_VALUE;
        int max = A[0];//Integer.MIN_VALUE;
        
        for(int i = 1; i < A.length; i++){
           sum = Math.max(A[i], sum + A[i]);
           max = Math.max(max, sum);
        }
        return max;
    
    }
}