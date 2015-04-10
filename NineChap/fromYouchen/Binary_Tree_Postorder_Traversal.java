/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        if(root == null) return al;
        
        al.addAll(postorderTraversal(root.left));
        al.addAll(postorderTraversal(root.right));
        al.add(root.val);
        
        return al;
    }
}