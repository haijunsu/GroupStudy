/**
 * Round 2 - 3/11
 * Easy. Practice.
 * 
 * Remember the special case of 1 row and 1 col
 *      also the special case of 1 row and 1 col in the iteration process.
 */
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {//Mar 11 - 20:11 - 21:26 (1h 15m)
        ArrayList<Integer> l = new ArrayList<Integer>();
        if(matrix.length == 0 || matrix == null) return l;
        
        int row = matrix.length, col = matrix[0].length;
        int top = 0, bottom = row - 1, left = 0, right = col - 1;
        
        //matrix only has 1 row
        if(row == 1){
            for(int i = 0; i < col; i++)
                l.add(matrix[0][i]);
            return l;
        }
        
        //matrix only has 1 col
        if(col == 1){
            for(int i = 0; i < row; i++)
                l.add(matrix[i][0]);
            return l;
        }
        
        
        
        while(left <= right && top <= bottom){
            //here you need to consider the 1 row and 1 col case
            if(left == right){
                for(int i = top; i <= bottom; i++)
                    l.add(matrix[i][left]);
                break;
            }
            if(top == bottom){
                for(int i = left; i <= right; i++)
                    l.add(matrix[top][i]);
                break;
            }
            
            
            
            
            for(int i = left; i < right; i++)
                l.add(matrix[top][i]);
            
            for(int i = top; i < bottom; i++)
                l.add(matrix[i][right]);
            
            for(int i = right; i > left; i--)
                l.add(matrix[bottom][i]);
                
            for(int i = bottom; i > top; i--)
                l.add(matrix[i][left]);
                
            left++;
            right--;
            top++;
            bottom--;
        }
        return l;
        
    }
}




/**
 * O(n)
 * 354 ms
 * 
 * List is an interface, implementing classes including
 *      ArrayList, LinkedList, Stack, Vector and etc...
 * ArrayList
 *      constructor, add(), size()
 * Array
 *      2D array matrix:
 *          matrix.length -> num of rows
 *          matrix[0].length -> num of cols.
 */
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        /**
        * Algorithm:
        *  ——————--
        *  |      |
        *  |      |
        *  |      |
        *  —————--|
        */
        ArrayList<Integer> sol = new ArrayList<Integer>();

        if (matrix.length == 0 || matrix == null) return sol;
            //1 row
        if (matrix.length == 1) {
            for (int i = 0; i < matrix[0].length; i++) {
                sol.add(matrix[0][i]);
            }
            return sol;
        }
            //1 col
        if (matrix[0].length == 1) {
            for (int i = 0; i < matrix.length; i++) {
                sol.add(matrix[i][0]);
            }
            return sol;
        }

        int row = matrix.length, col = matrix[0].length,
        totalElements = row * col;

        int upperRowBound = 0, leftColBound = 0, 
        bottomRowBound = row - 1, rightColBound = col - 1;

        int curRow = 0, curCol = 0;

        while(sol.size() <= totalElements){
            while (curRow == upperRowBound && curCol <= rightColBound) {

                if(sol.size() == totalElements) return sol;
                sol.add(matrix[curRow][curCol++]);
            }
            if(curCol - 1 == rightColBound) curCol--;
            curRow++;
            if(upperRowBound + 1 <= bottomRowBound - 1)
                upperRowBound++;


            while (curCol == rightColBound && curRow <= bottomRowBound) {
                if(sol.size() == totalElements) return sol;
                sol.add(matrix[curRow++][curCol]);
            }
            if(curRow - 1 == bottomRowBound) curRow--;
            curCol--;
            if(rightColBound - 1 >= leftColBound + 1)
                rightColBound--;


            while (curRow == bottomRowBound && curCol >= leftColBound){
                if(sol.size() == totalElements) return sol;
                sol.add(matrix[curRow][curCol--]);
            }
            if(curCol + 1 == leftColBound) curCol++;
            curRow--;
            if(bottomRowBound - 1 >= upperRowBound + 1)
                bottomRowBound--;


            while (curCol == leftColBound && curRow >= upperRowBound){
                if(sol.size() == totalElements) return sol;
                sol.add(matrix[curRow--][curCol]);
            }
            if(curRow + 1 == upperRowBound) curRow++;
            curCol++;
            if(leftColBound + 1 <= rightColBound - 1)
                leftColBound++;
        }//while

        return sol;
    }
}