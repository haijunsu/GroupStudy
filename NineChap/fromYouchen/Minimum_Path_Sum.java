
public class Solution {
    public int minPathSum(int[][] grid) {
        /**
         * Solution with 2D array
         * O(m * n)
         * O(m * n)
         * 
         * 231 ms
         */
    //     if(grid == null || grid[0] == null) return 0;
        
    //  int row = grid.length, col = grid[0].length;
    //  int[][] minPath = new int[row][col];
        
    //  //copy the top-left cell
    //  minPath[0][0] = grid[0][0];
        
    //  //row
    //  for(int i = 1; i < col; i++) {
    //      minPath[0][i] = grid[0][i] + minPath[0][i - 1];
    //  }
        
    //  //col
    //  for(int i = 1; i < row; i++) {
    //      minPath[i][0] = grid[i][0] + minPath[i - 1][0];
    //  }
        
    //  for(int i = 1; i < row; i++) {
    //      for(int j = 1; j < col; j++) {
    //          minPath[i][j] = grid[i][j] + Math.min(minPath[i - 1][j], minPath[i][j - 1]);
    //      }
    //  }
        
    //  return minPath[row - 1][col - 1];
    
    
    
    
        /**
         * Solution with 1D array
         * O(m * n)
         * O(m)
         * 
         * 228 ms
         */
        if(grid == null || grid[0] == null) return 0;
        
        int row = grid.length, col = grid[0].length;
        
        //1d array, size equal to a entire row of grid.
        int[] dp = new int[col];
        dp[0] = grid[0][0];
        
        for (int i = 0; i < row; i++) {
            if(i > 0) dp[0] = dp[0] + grid[i][0];
            
            for (int j = 1; j < col; j++) {
                if(i == 0)
                    dp[j] = dp[j - 1] + grid[i][j]; // for the 1st row;
                else
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);//MIN ( last value, left value)
            }
        }
        return dp[col - 1];
    }
}