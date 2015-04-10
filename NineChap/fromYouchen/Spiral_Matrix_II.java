/**
 * O(n^2)
 * O(n^2)
 * 200 ms
 * 
 * Note:
 *      Remember this method to do the spiral Matrix.
 */
public class Solution {
    public int[][] generateMatrix(int n) {//Mar 11 - 19:00 - 20:00 (1 h)
        int[][] ans = new int[n][n];
    	int k = 1;
    	int top = 0, bottom = n - 1, left = 0, right = n - 1;
    
    	//the most close they are is when the center 2 x 2 square is worked on.
    	while(left < right && top < bottom){
    		for(int j = left; j < right; j++)
    			ans[top][j] = k++;
    
    		for(int i = top; i < bottom; i++)
    			ans[i][right] = k++;
    
    		for(int j = right; j > left; j--)
    			ans[bottom][j] = k++;
    
    		for(int i = bottom; i > top; i--)
    			ans[i][left] = k++;
    
    		top++;
    		bottom--;
    		left++;
    		right--;
    	}
    
    	if(n % 2 == 1)
    		ans[n / 2][n / 2] = k;
    	return ans;
    }
}