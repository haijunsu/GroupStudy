/**
 * O(n)
 * 288 ms
 * 
 * Array
 * Two Pointers
 * Greedy
 */
public class Solution {
    public int maxArea(int[] height) {
        if(height.length <= 1) return 0;
        
        int left = 0, right = height.length - 1, area = 0;
        // h = (height[left] <= height[right]) ? height[left] : height[right];
        // area = (right - left) * h;
        
        while(left < right){
            area = Math.max( area, (right - left) * Math.min( height[left], height[right] ) );
            if(height[left] < height[right]) left++;
            else right--;
            //compute for new area
            // h = (height[left] <= height[right]) ? height[left] : height[right];
            // if((right - left) * h > area)
            //     area = (right - left) * h;
        }
        return area;
    }
}