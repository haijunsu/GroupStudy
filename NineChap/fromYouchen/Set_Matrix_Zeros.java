/**
 * O(m + n + mn + m + n + m + n) = O(m + n) + O(mn)
 * O(4) = O(1)
 * 265 ms
 * 
 * Note:
 *      Remember you needs a boolean var firstRow and firstCol.
 *      Do this again. Easy Problem, but difficult to get bug free.
 * 
 *      Algorithm:
 *          1. check if the 1st row/col needs to be set to 0. remember this by two boolean
 *               variable firstRow and firstCol.
 *          2. iterate ALL cells, and mark the [0][j] and [i][0] to 0, if [i][j] is 0.
 *          3. Based on the 1st row and 1st col, set all the cells EXCEPT the 1st row and 1st col to 0.
 *          4. Set the 1st row and 1st col based on the two boolean variables.
 */
public class Solution {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        if(matrix == null || row == 0) return;
        
        int col = matrix[0].length;
        boolean firstRow = false, firstCol = false;
        
        //Determine if the first row and col needs to set to zero.
        for(int i = 0; i < row; i++)
            if(matrix[i][0] == 0)
                firstCol = true;
        
        for(int i = 0; i < col; i++)
            if(matrix[0][i] == 0)
                firstRow = true;
        
        //mark the cells on first row and column to 0 if ever found 0 in any cells.
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        
        //fill the cells that other than the 1st row and 1st col
        if(row >= 2)
        for(int i = 1; i < row; i++){
            if(matrix[i][0] == 0){//if 0 ever found in the 1st column, set entire row to 0;
                for(int j = 1; j < col; j++)
                    matrix[i][j] = 0;
            }
        }
        
        if(col >= 2)
        for(int i = 1; i < col; i++){
            if(matrix[0][i] == 0){//if 0 ever found in the 1st row, set entire column to 0;
                for(int j = 1; j < row; j++)
                    matrix[j][i] = 0;
            }
        }
        
        if(firstRow)
            Arrays.fill(matrix[0], 0, col, 0);//the toIndex is exclusive!!!
            // for(int i = 0; i < col; i++)
            //     matrix[0][i] = 0;
        
        if(firstCol){
            for(int i = 0; i < row; i++){
                matrix[i][0] = 0;
            }
        }
        
    }
}