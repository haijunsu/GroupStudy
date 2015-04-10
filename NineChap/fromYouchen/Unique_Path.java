/**
 * O(100 * 100) = O(1) since m, n <= 100 actually that's O(m * n) 
 * O(m * n)
 * 
 * 195 ms
 * 
 * Note:
 *  This problem could solved by using 1D array as well.
 */
// public class Solution {
//     public int[][] arr = new int[101][101];
//     public int uniquePaths(int m, int n) {
//         for(int i = 0; i < arr.length; i++)
// 			for(int j = 0; j < arr.length; j++)
// 				arr[i][j] = -1;
// 		return uniquePaths_2(m, n, arr);
// 	}
// 	public int uniquePaths_2(int m, int n, int[][] arr) {
// 		if(m == 0 || n == 0) return 0;
// 		if(m == 1 || n == 1) return 1;
		
// 		if(arr[m][n] != -1) return arr[m][n];
// 		arr[m][n] = uniquePaths_2(m - 1, n, arr) + uniquePaths_2(m, n - 1, arr);
// 		return arr[m][n];
// 	}
// }



/**
 * O(m * n) - size of the array. Must need to iterate all the cells.
 * O(n)
 * 
 * Solution by using 1D array.
 */
// public class Solution {
//     public int uniquePaths(int m, int n) {
//         int[] sol = new int[n];
//         sol[0] = 1;
        
//         for(int i = 0; i < m; i++){
//             for(int j = 0; j < n; j++){
//                 if(j > 0)
//                     sol[j] += sol[j - 1];
//             }
//         }
        
//         return sol[sol.length - 1];
//     }
// }
    



//attempted on Feb 28.
// public class Solution{
//     public static int[][] move = new int[101][101];
    
//     public int uniquePaths(int m, int n){
//         return uniquePaths2(m - 1, n - 1);
//     }
//     public int uniquePaths2(int m, int n){
//      if(m == 0 || n == 0) return 1;
//      if(move[m][n] != 0) return move[m][n];
    
//      move[m][n] = uniquePaths2(m - 1, n) + uniquePaths2(m, n - 1);
//      return move[m][n];
//     }
// }




//attempted on Feb 28
public class Solution{
    public static int[] move;
        
    public int uniquePaths(int m, int n){
        if(m <= 0 || n <= 0) return 0;
        move = new int[n];
        return func(m - 1, n - 1);
    }

    public int func(int m, int n){
        move[0] = 1;
        for(int i = 0; i <= m; i++){
            for(int j = 1; j <= n; j++){
                move[j] += move[j - 1];
            }
        }
        System.out.println( Arrays.toString(move));
        return move[n];
    }
}