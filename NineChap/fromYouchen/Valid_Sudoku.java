/**
 * O(n * n * (2n + 9) ) = O(2n^3 + 9n^2) = O(n^3)
 * O(1)
 * 302 ms
 * 
 * Note:
 *      sub-problem of "Sudoku solver".
 */
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return true;
        
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] != '.'){
                    
                    //check horizontal
                    for(int k = 0; k < board[0].length; k++){
                        if(k == j) continue;
                        if(board[i][k] == board[i][j])
                            return false;
                    }
                    
                    //check vertical
                    for(int k = 0; k < board.length; k++){
                        if(k == i) continue;
                        if(board[k][j] == board[i][j])
                            return false;
                    }
                    
                    //check block
                    for(int row = i / 3 * 3; row < i / 3 * 3 + 3; row++){
                        for(int col = j / 3 * 3; col < j / 3 * 3 + 3; col++){
                            if(row == i && col == j) continue;
                            if(board[row][col] == board[i][j])
                                return false;
                        }
                    }
                }//if '.'
            }
        }//outter for
        
        return true;
    }
}