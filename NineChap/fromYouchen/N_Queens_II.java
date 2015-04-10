/**
 * O(n!)
 * O(n)
 * 216 ms
 * 
 * Note:
 *      The error-prone point is the global variable.
 *      You need to declare it as global but do not pass it together with the recursion
 *          method. like
 *          helper(count ...);
 *      or else, the count will not be updated!!
 */
public class Solution {
    int count;
    public int totalNQueens(int n) {
        if(n <= 0) return 0;
        
        count = 0;
        helper(n, new int[n], 0);
        return count;
    }
    private void helper(int n, int[] columnForRow, int row){
        if(row == n){
            count++;
            return;
        }
        for(int i = 0; i < n; i++){
            columnForRow[row] = i;
            
            if(isValid(columnForRow, row)){
                helper(n, columnForRow, row + 1);
            }
        }
    }
    private boolean isValid(int[] columnForRow, int row){
        for(int i = 0; i < row; i++){//CAUTION! here should be "i < row" NOT "i < columnForRow.length"
            if(columnForRow[i] == columnForRow[row] //cannot be in same column
                || row - i == Math.abs(columnForRow[i] - columnForRow[row]) //cannot be palced diagonally
                )
                return false;
        }
        return true;
    }
}