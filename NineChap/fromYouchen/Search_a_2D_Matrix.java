/**
 * Round 2 - 4/3
 * Applying 1D array Bin. S.
 * O(log (A.row * A.col) )
 * O(1)
 * 235 ms
 * 
 * Note:
 *      the 9zhang uses head < tail.
 */
public class Solution {
    public boolean searchMatrix(int[][] A, int t) {//19:42 - 19:56 (14 min)
        if(A == null || A.length == 0)
            return false;
        
        int row = A.length, col = A[0].length;
        int head = 0, tail = row * col - 1, mid, r, c;
        
        while(head + 1 < tail){
            mid = head + (tail - head) / 2;
            r = mid / col; 
            c = mid % col;
            
            if(A[r][c] == t)
                return true;
            if(A[r][c] < t)
                head = mid;
            else
                tail = mid;
        }
        //head and tail is the sequence number of A[][], like 15, 24 ...
        return (A[head / col][head % col] == t)? true : ( (A[tail / col][tail % col] == t)? true : false);
    }
}







/**
 * Round 2 - 4/3
 * 
 * Note:
 *      Do not get mess and confused with the problem "Search in Rotated Sorted Array"
 *          since in that problem, the code is like
 *              up = mid + 1;
 *              bottom = mid - 1;
 * 
 *      But this problem is using the standard template.
 *      writing as:
 *              up = mid;
 *              bottom = mid;
 */
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {//29 min
        if(matrix == null || matrix.length == 0)
            return false;

        int up = 0, bottom = matrix.length - 1, mid;
        while(up + 1 < bottom){
            mid = up + (bottom - up) / 2;
            if(matrix[mid][0] == target)
                return true;
            if(matrix[mid][0] < target)
                up = mid;
            else
                bottom = mid;
        }
        
        if(matrix[up][0] == target || matrix[bottom][0] == target)
            return true;
        
        //determine the row
        int row = 0;//the assignment only for initialization.
        if(matrix[up][0] > target)
            //row = up;
            return false;
        else{// if(matrix[up][0] < target){
            if(matrix[bottom][0] > target)
                row = up;
            else if(matrix[bottom][0] < target)
                row = bottom;
        }

        int left = 0, right = matrix[0].length - 1;
        while(left + 1 < right){
            mid = left + (right - left) / 2;
            if(matrix[row][mid] == target)
                return true;
            if(matrix[row][mid] < target)
                left = mid;
            else
                right = mid;
        }


        return (matrix[row][left] == target)? true : ((matrix[row][right] == target)? true: false);
    }
}













/**
 * O(log row + log col)
 * O(1)
 * 235 ms
 * 
 * Note:
 *      Easy one. But you need to remember the core principle of Binary Search.
 *      when the code "jump out of while loop", where the top bottom points to.
 *
 * Reference:
 *      http://www.cnblogs.com/springfor/p/3857959.html
 * Better to look at the other solution, only use 1 time Binary Search.
 */
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {//Mar 11 21:35
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        
        int top = 0, left = 0, bottom = matrix.length - 1, right = matrix[0].length - 1;
        int Vmid = top + (bottom - top) / 2, Hmid = left + (right - left) / 2;
        
        //find the row.
        while(top <= bottom){
            if(matrix[Vmid][0] == target) return true;
            else if(matrix[Vmid][0] < target){
                top = Vmid + 1;
                // Vmid = top + (bottom - top) / 2;
            }
            else if(matrix[Vmid][0] > target){
                bottom = Vmid - 1;
                // Vmid = top + (bottom - top) / 2;
            }
            Vmid = top + (bottom - top) / 2;
        }
        
        /**
         * out of while, form the condition:
         *      bottom  ->  less    than Target
         *      top     ->  greater then Target
         * THIS IS VERY IMPORTANT RULE FOR BINARY SEARCH!!!
         */
         
        //if the bottom is pointing to -1, meaning no such element in the matrix.
        //      since reversely, if top points to matrix.length, there might be
        //          this element in the row of matrix[matrix.length - 1];
        if(bottom < 0) return false;
        
        //so the element must be in the row of which bottom points to.
        //      search for the element in this row.
        while(left <= right){
            if(matrix[bottom][Hmid] == target) return true;
            else if(matrix[bottom][Hmid] < target)
                left = Hmid + 1;
            else if(matrix[bottom][Hmid] > target)
                right = Hmid - 1;
            Hmid = left + (right - left) / 2;
        }
        return false;
    }
}