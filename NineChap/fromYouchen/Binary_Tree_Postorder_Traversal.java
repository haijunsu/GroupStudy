/**
 * Round 2 - recur. (1 min) iter. (7 min)
 * 
 * Note:
 *      The recursive method can be applied to this problem as well.
 *      For the iterative method:
 *          Hard one.
 *          But don't forget the line of
 *              cur = s.peek();
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
    public List<Integer> postorderTraversal(TreeNode root) {//4/19 22:49
        List<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
            
        //recursive 22:50 (1 min)
        // res.addAll(postorderTraversal(root.left));
        // res.addAll(postorderTraversal(root.right));
        // res.add(root.val);
        
        // return res;
        
        
        //4/20 12:08 - 12:15 (7 min)
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode pre = null, cur = root;
        
        s.push(root);
        while(! s.isEmpty()){
            cur = s.peek();//DON'T FORGET THIS LINE!!
            //traversing down
            if(pre == null || pre.left == cur || pre.right == cur){
                if(cur.left != null)
                    s.push(cur.left);
                else if(cur.right != null)
                    s.push(cur.right);
            }
            //traversing up from left
            else if(cur.left == pre){
                if(cur.right != null)
                    s.push(cur.right);
            }
            //traversing up from right
            else if(cur.right == pre || cur == pre){
                res.add(cur.val);
                s.pop();
            }
            pre = cur;
        }
        
        return res;
    }
}





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