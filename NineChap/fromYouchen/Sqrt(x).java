/**
 * O(log n)
 * 238 ms
 * 
 * BinarySearch
 */
public class Solution {
    public int sqrt(int x) {
        /**
         * Binary Search
         */
        if(x == 0) return x;
        int left = 1, right = x, mid, ans = 1;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(mid <= x / mid){//(mid * mid <= x){
                left = mid + 1;
                ans = mid;
            }
            else
                right = mid - 1;
        }//while
        return ans;
    }
}