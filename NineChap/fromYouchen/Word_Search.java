/**
 * O(board.row * board.col + 4 * word.length)
 * O(Math.max(board.row * board.col, word.length))
 * 311 ms
 * 
 * Note:
 *      The visited boolean array is necessary.
 */
public class Solution {
    public boolean exist(char[][] board, String word) {//around 70 min
        if(board == null || board.length == 0)
            return false;
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    visited[i][j] = true;
                    if(helper(board, word.substring(1, word.length()), i, j, visited))
                        return true;
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }
    private boolean helper(char[][] board, String word, int row, int col, boolean[][] visited){
        if(word.equals(""))
            return true;
        
        //up
        if(row >= 1 && col >= 0 && col <= board[0].length - 1 && board[row - 1][col] == word.charAt(0) && visited[row - 1][col] == false) {
            visited[row - 1][col] = true;
            if(helper(board, word.substring(1, word.length()), row - 1, col, visited))
                return true;
            visited[row - 1][col] = false;
        }
            
        //right
        if(col <= board[0].length - 2 && row >= 0 && row <= board.length - 1 && board[row][col + 1] == word.charAt(0) && visited[row][col + 1] == false) {
            visited[row][col + 1] = true;
            if(helper(board, word.substring(1, word.length()), row, col + 1, visited))
                return true;
            visited[row][col + 1] = false;
        }
            
        //down
        if(row <= board.length - 2 && col >= 0 && col <= board[0].length - 1 && board[row + 1][col] == word.charAt(0) && visited[row + 1][col] == false) {
            visited[row + 1][col] = true;
            if(helper(board, word.substring(1, word.length()), row + 1, col, visited))
                return true;
            visited[row + 1][col] = false;
        }
            
        //left
        if(col >= 1 && row >= 0 && row <= board.length - 1 && board[row][col - 1] == word.charAt(0) && visited[row][col - 1] == false) {
            visited[row][col - 1] = true;
            if(helper(board, word.substring(1, word.length()), row, col - 1, visited))
                return true;
            visited[row][col - 1] = false;
        }
        return false;
    }
}