/**
 * O(n)
 * O(n)
 * 213 ms
 * 
 * Note:
 *      This problem is kinda Math one. Not using the backtracking algorithm.
 * 
 * Reference:
 *      http://www.cnblogs.com/springfor/p/3896201.html
 */
public class Solution {
    public String getPermutation(int n, int k) {
        //Given n will be between 1 and 9 inclusive.
        //      So no need for check the possibility of n.
        
        //Make a interger sequence list.
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++)
            list.add(i);
            
        //calculate (n - 1)!
        int factorial = 1;
        for(int i = 2; i <= n - 1; i++)
            factorial *= i;
         
        //In order to determine the INDEX of the 1st number in the kth sequence, 
        //      calculate the k / (n - 1)!   
        k--;
        // int FirstNumIndex = k / factorial;
    
        //Use StringBuilder to make the sequence.
        StringBuilder sb = new StringBuilder();
        int time = n - 1;
        while(time >= 0){
            int FirstNumIndex = k / factorial;
            sb.append(list.get(FirstNumIndex));
            list.remove(FirstNumIndex);
            
            //for the next round.
            k = k % factorial;//the new k;
            
            if(time != 0)
                factorial /= time;//the new (n - 1)!
            
            time--;
        }
        return sb.toString();
    }
}