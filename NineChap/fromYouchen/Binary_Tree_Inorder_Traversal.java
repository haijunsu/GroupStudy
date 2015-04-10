/**
 * DO THIS AGAIN.
 * 
 * O(n)
 * O(n)
 * 200 ms
 * 
 * BT
 * ArrayList
 * Stack
 * 
 * Note:
 *      ReConsider this problem.
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
    public List<Integer> inorderTraversal(TreeNode root) {//2:29
        ArrayList<Integer> al = new ArrayList<Integer>();
        if(root == null) return al;
        
        // al.addAll(inorderTraversal(root.left));
        // al.add(root.val);
        // al.addAll(inorderTraversal(root.right));
        
        // return al;
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        
        TreeNode p = root;
        
        while(! s.isEmpty() || p != null){
            if(p != null ){
                s.push(p);
                p = p.left;
            }else{
                TreeNode t = s.pop();
                al.add(t.val);
                p = t.right;
            }
        }
        return al; 
    }
}