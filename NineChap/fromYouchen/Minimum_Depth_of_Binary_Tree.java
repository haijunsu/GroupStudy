/**
 * O(n)
 * O(n)
 * 347 ms
 * 
 * DO THIS AGAIN.
 * 
 * Note:
 *      Thinking together with the problem of "Max depth of BT".
 *      Idea is
 *          if the node is leaf, return 0
 *          if the node has INCOMPLETE children, just traverse down, in order to reach the leaf.
 *          compare the left child min and right child min.
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int minDepth(TreeNode root) {//4/20 13:10 - 15:10 (2 hours)
        if(root == null)
            return 0;
            
        if(root.left == null || root.right == null)
            return 1 + (root.left == null ? minDepth(root.right) : minDepth(root.left));
        
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}