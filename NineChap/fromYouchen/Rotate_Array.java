public class Solution {
	import java.util.*;
	public class Solution {
    /**
     * O(n)
     * O(1)
     * 202 ms
     * 
     * Note:
     *      Only the way of 
     *          1. Reverse the entire array.
     *          2. reverse the 0 ~ k - 1 items.
     *          3. reverse the k ~ len - 1 items.
     *      is working.
     *      Don't know why. Just REMEMBER.
     */
    public void rotate(int[] nums, int k){
    	int len = nums.length;
    	k = k % len;
        // if(len <= 1 || k == 0) return;
    	
        // reverse(nums, 0, k);
        // reverse(nums, k + 1, len - 1);
        // reverse(nums, 0, len - 1);
    	
    	reverse(nums, 0, len-1);
    	reverse(nums, 0, k-1);
    	reverse(nums, k, len-1);
    	
    }
    
    // public void reverse(int[] arr, int start, int end){
    //     int len = end - start + 1;
    //     if(len <= 1 || end > start) return;
    
    //     int stopIndex = (len % 2 == 0)? (len / 2 - 1) : ((len - 1) / 2 - 1);
    //     stopIndex = stopIndex + start;
    
    //     for(int i = start; i <= stopIndex; i++){
    //         int temp = arr[i];
    //         arr[i] = arr[end];
    //         arr[end] = temp;
    //         end--;
    //     }
    // }
    
    public void reverse(int[] arr, int l, int r){
    	while(l < r){
    		int temp = arr[l];
    		arr[l] = arr[r];
    		arr[r] = temp;
    		r--;
    		l++;
    	}
    // 	System.out.println("Reverse: " + Arrays.toString(arr));
    }
    
    /**
     * O(n)
     * O(n)
     * 226 ms
     */
    // public void rotate(int[] nums, int k){//22: 42 (62m)
    //     int len = nums.length;
    //     if(len <= 1 || k == 0) return;
    
    //     int[] nums2 = new int[len];
    //     int index = 0;
    
    //     for(int i = k; i <= k + len - 1; i++){
    //         // nums2[index] = nums[i % len];
    //         // index++;
    //         nums2[i % len] = nums[index];
    //         index++;
    //     }
    
    //     for(int i = 0; i < len; i++)
    //         nums[i] = nums2[i];
    // }
    /**
     * 2nd Trail. FAIL
     * Time limits exceeds.
     */
    // public void rotate(int[] nums, int k) {
    //     int len = nums.length;
    //     if(len <= 1 || k == 0) return;
    
    //     for(int i = 1; i <= k; i++){
    //         //rotate
    //         int temp = nums[0];
    //         for(int j = len; j >= 2; j--){
    //           nums[j % len] = nums[(j - 1) % len];
    //         }
    //         nums[1] = temp;
    //     }
    // }
    /**
     * 1st Trail FAIL
     * did not pass {1, 2, 3}, 1 test.
     */
    // public void rotate(int[] nums, int k) {//Mar 6 - 19:19 pause 19:59 resume 22:20
    //     int len = nums.length;
    //     if(len <= 1 || k == 0) return;
    
    //     int[] numsDouble = new int[len * 2];
    //     for(int i = 0; i <= len * 2 - 1; i++){
    //         numsDouble[i] = nums[i % len];
    //     }
    
    //     int index = 0;
    //     for(int i = k; i <= k + len - 1; i++){
    //         nums[index] = numsDouble[i % (len * 2)];
    //         index++;
    //     }
    // }
}
}