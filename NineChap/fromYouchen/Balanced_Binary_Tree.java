/**
 * DO THIS AGAIN
 * O(n)
 * O(1)
 * 259 ms
 * 
 * Note:
 *      Math.abs()
 *      You could have helper method.
 *      Think carefully and it must have solution if you think long time.
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
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        
        int diff = Math.abs(height(root.left) - height(root.right));
        if(diff > 1)
            return false;
        else
            return isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int height(TreeNode root){
        if(root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}