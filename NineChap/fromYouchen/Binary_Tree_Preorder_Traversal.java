/**
 * O(n)
 * O(n)
 * 
 * 203 ms
 * 194 ms
 * 
 * BT
 * ArrayList, Stack
 * 
 * Note:
 *      Reconsider this problem.
 * 
 * DO THIS AGAIN
 * 
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
    public List<Integer> preorderTraversal(TreeNode root) {
    //   ArrayList<Integer> al = new ArrayList<Integer>();
    //   if(root == null) return al;
       
    //   al.add(root.val);
    //   al.addAll(preorderTraversal(root.left));
    //   al.addAll(preorderTraversal(root.right));
    //   return al;
    
        ArrayList<Integer> al = new ArrayList<Integer>();
        if(root == null) return al;
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        
        while(! s.isEmpty()){
            TreeNode p = s.pop();
            al.add(p.val);
            if(p.right != null) s.push(p.right);
            if(p.left != null) s.push(p.left);
        }
        return al;
    }
}