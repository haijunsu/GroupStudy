/**
 * HARD TO THINK
 * DO THIS AGAIN.
 * 
 * O(n^2)
 * O(n)
 * 171 ms
 */
public class Solution {
    public int numTrees(int n) {
        if(n <= 1) return 1;
        
        int[] trees = new int[n + 1];
        trees[0] = 1;
        trees[1] = 1;
        
        for(int i = 2; i <= n; i++){
            for(int j = 0; j <= i - 1; j++){
                trees[i] += trees[j] * trees[i - j - 1];
            }
        }
        
        return trees[n];
        
    }
}