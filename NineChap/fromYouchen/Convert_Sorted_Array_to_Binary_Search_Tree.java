/**
 * DO THIS AGAIN.
 * 
 * 294 ms
 * 
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode sortedArrayToBST(int[] num) {
        return createTree(num, 0, num.length - 1);
    }
    
    public TreeNode createTree(int[] arr, int start, int end){
        if(start > end)
            return null;
            
        int mid = (start + end) / 2;
        TreeNode t = new TreeNode(arr[mid]);
        t.left = createTree(arr, start, mid - 1);
        t.right = createTree(arr, mid + 1, end);
        return t;
    }
    
    
}