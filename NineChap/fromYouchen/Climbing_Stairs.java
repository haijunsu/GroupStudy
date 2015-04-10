/**
 * O(n)
 * O(n)
 * 182 ms
 * 
 */
public class Solution {
    //#1 solution -- use only recursion.
    // public int climbStairs(int n) {
    //     if(n < 0)
    //         return 0;
    //     if(n == 0)
    //         return 1;
    //     return climbStairs(n - 1) + climbStairs(n - 2);
    // }
    
    //#2 solution -- use DP.
    // public int climbStairs(int n) {
    //     int[] arr = new int[1000];
    //     for(int i = 0; i < arr.length; i++){
    //         arr[i] = -1;
    //     }
    //     return dp(n, arr);
    // }
    // public int dp(int n, int[] arr){
    //     if(n < 0)
    //         return 0;
    //     if(n == 0)
    //         return 1;
    //     if(arr[n] != -1)
    //         return arr[n];
            
    //     arr[n] = dp(n - 1, arr) + dp(n - 2, arr);
    //     return arr[n];
    // }
    
    //#3 Solution attempted on Feb 28.
    public static int[] steps = new int[1000];

    public int climbStairs(int n){
        if(n == 0) return 1;
        if(n == 1) return 1;
    //  if(n == 2) return 2;
        if(steps[n] != 0)
            return steps[n];
        
        steps[n] = climbStairs(n - 1) + climbStairs(n - 2);// + steps(n - 3);
        return steps[n];
    }
    
}