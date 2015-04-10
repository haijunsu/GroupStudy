/**
 * O(log x)
 * O(log x)
 * 361 ms
 * 
 * Note:
 *      Difficult to think about. REMEMBER this algorithm.
 *      DO THIS AGAIN.
 * 
 * Reference:
 *      http://www.programcreek.com/2012/12/leetcode-powx-n/
 */
public class Solution {
    public double pow(double x, int n){
    	if(n < 0)//if something to the negative raise of power. return 1 / power(x, and negative negative number).
    		return 1 / power(x, -n);
    	else
    		return power(x, n);
    }
    /**
     * Compute the positive power coeficient.
     */
    public double power(double x, int n){
    	if(n == 0)
    		return 1;
    
    	double v = power(x, n / 2);
    	if(n % 2 == 0)
    		return v * v;
    	else
    		return v * v * x;
    }
    /**
     * 1st TRAIL. FAIL.
     */
    // public double pow(double x, int n) {
    //     for(int i = 1; i <= n - 1; i++)
    //         x *= x;
    //     return x;
    // }
}