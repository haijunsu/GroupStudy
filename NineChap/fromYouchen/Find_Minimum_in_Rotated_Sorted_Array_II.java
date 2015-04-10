/**
 * O(n)
 * O(1)
 * 229 ms
 * 
 * Note:
 *      This is a very nice practice for BS.
 *      Same algorithm principle as "Search in Rotated Sorted Array II"
 */
public class Solution {
    public int findMin(int[] num) {
        if(num.length == 0 || num == null) return -1;
        
        int left = 0, right = num.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            
            if(num[mid] > num[right] )
                left = mid + 1;
            else if(num[mid] == num[right])
                right--;
            else//mid < right
                right = mid;
            
            // if(left + 1 == right) break;
        }
        return num[left];
    }
}