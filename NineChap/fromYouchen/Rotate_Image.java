/**
 * O(n^2)
 * 191 ms
 * 
 * Array
 */ 
public class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //if(n > 1)
        for(int i = 0; i <= n / 2 - 1; i++){
            for(int j = i; j <= n - 2 - i; j++){
                /**
                 * KEEP IN MIND!!!
                 *      IF J IS START FROM I, THE TOP CELL IS
                 *      MATRIX[I][J] RATHER THAN [I][I + J]
                 */
                
                //copy top
                int temp = matrix[i][j];
                
                //left to top : top = left
                matrix[i][j] = matrix[n - 1 - j][i];
                //bottom to left: left = bottom
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                //right to bottom: bottom = right
                matrix[n - 1 - i][n - 1 - j] =  matrix[j][n - 1 - i];
                //top to right: right = top
                matrix[j][n - 1 - i] =  temp;
            }
        }//for i
    }
}