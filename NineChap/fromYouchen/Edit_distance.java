/**
 * DO IT AGAIN, COST SO MUCH TIME!!!
 * O(m + n)
 * O(m + n)
 * 359 ms
 * 
 * Note:
 *      It's so hard to understand the insert and delete expression for the 2d array.
 * 
 * Reference:
 *      http://www.cnblogs.com/springfor/p/3896167.html
 *      http://www.programcreek.com/2013/12/edit-distance-in-java/
 */
public class Solution{
	public int minDistance(String word1, String word2){
		int len1 = word1.length();
		int len2 = word2.length();

		int[][] dp = new int[len1 + 1][len2 + 1];

		//length i become length 0, needs i steps to delete the chars.
		for(int i = 0; i <= len1; i++)
			dp[i][0] = i;

		//length 0 become length j, needs j steps to insert the chars.
		for(int j = 0; j <= len2; j++)
			dp[0][j] = j;

		for(int i = 1; i <= len1; i++){
			char c1 = word1.charAt(i - 1);
			for(int j = 1; j <= len2; j++){
				char c2 = word2.charAt(j - 1);



				if(c1 == c2)
					dp[i][j] = dp[i - 1][j - 1];
				else{
					int replace = dp[i - 1][j - 1] + 1;
					int insert = dp[i - 1][j] + 1;
					int delete = dp[i][j - 1] + 1;

					int min = Math.min(replace, Math.min(insert, delete));
					dp[i][j] = min;
				}



			}
		}

		return dp[len1][len2];

	}
}