/**
 * O(n)
 * 197 ms
 * (204 ms for better code)
 * 
 * Binary Tree
 * Recursion
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null )
            return true;
        if( (p == null && q != null) || (p != null && q == null) )
            return false;
        if(p.val != q.val) return false;
        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
        /**
         * Better Code
         * https://oj.leetcode.com/discuss/3470/seeking-for-better-solution
         */
        // if(p == null || q == null)
        //     return p == q;
        // else
        //     return (p.val == q.val) && (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}