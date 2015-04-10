public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //1st Trail
    //     if(obstacleGrid[0][0] == 1 || obstacleGrid == null || obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1]) == 1) return 0;
        
    //     for(int i = 0; i < obstacleGrid.length; i++)
    //         for(int j = 0; j < obstacleGrid[0].length; j++){
    //             if(obstacleGrid[i][j] == 1)
    //                 obstacleGrid[i][j] = -1;
    //         }
        
    //     // obstacleGrid[0][0] = 1;
    //     return helper(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1 );
    // }
    
    // public int helper(int[][] A, int i, int j){
    //     if(i == 0){
    //         for(int m = 0; m <= i; m++){
    //             if(A[i][m] == -1){
    //                 A[i][j] = 0;
    //                 return 0;
    //             }
    //         }
    //         A[i][j] = 1;
    //         return 1;
    //     }
    //     if(j == 0){
    //         for(int m = 0; m <= j; m++){
    //             if(A[m][j] == -1){
    //                 A[i][j] = 0;
    //                 return 0;
    //             }
    //         }
    //         A[i][j] = 1;
    //         return 1;
    //     }
        
    //     if( (A[i - 1][j] != -1) && (A[i][j - 1] != -1) )
    //         return helper(A, i - 1, j) + helper(A, i, j - 1);
            
    //     else if( (A[i - 1][j] != -1) && (A[i][j - 1] == -1) )
    //         return A[i - 1][j];
            
    //     else if( (A[i - 1][j] == -1) && (A[i][j - 1] != -1) )
    //         return A[i][j - 1];
    //     else//both are -1;
    //         return 0;
    
    
    //Solution #1
    /**
     * O(m + n)
     * O(m + n)
     */
        // int[][] A = new int[obstacleGrid.length][obstacleGrid[0].length];
        // if(obstacleGrid[0][0] == 1) return 0;
        // // else
        // //     A[0][0] = 1;
            
        // for(int i = 0; i < obstacleGrid.length; i++){
        //     for(int j = 0; j < obstacleGrid[0].length; j++){
        //         if(i == 0 && j == 0)
        //             A[0][0] = 1;
        //         else{
        //             if(obstacleGrid[i][j] == 0){
        //                 if(i != 0 && j != 0)
        //                     A[i][j] = A[i - 1][j] + A[i][j - 1];
                    
        //                 else if(i == 0 && j != 0)
        //                     A[i][j] = A[i][j - 1];
                        
        //                 else if ( i != 0 && j == 0)
        //                     A[i][j] = A[i - 1][j];
        //                 // else
        //                 //     A[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
        //             }
        //             else// == 1
        //                 A[i][j] = 0;
        //         }
        //     }
        // }
        // return A[obstacleGrid.length - 1][obstacleGrid[0].length - 1]; 
        
        
        
        
        
        //Solution #2
        /**
         * inspired by 
         *      http://blog.csdn.net/linhuanmars/article/details/22135231
         * use 1D array.
         * 
         * O(m + n) 
         * O(m) - row size
         */
         int[] A = new int[obstacleGrid[0].length];
         A[0] = 1;
          for(int i = 0; i < obstacleGrid.length; i++){
            for(int j = 0; j < obstacleGrid[0].length; j++){
                if(obstacleGrid[i][j] == 1)
                    A[j] = 0;
                else if(j > 0)
                    A[j] += A[j - 1];
            }
          }
          return A[A.length - 1];
    }
}