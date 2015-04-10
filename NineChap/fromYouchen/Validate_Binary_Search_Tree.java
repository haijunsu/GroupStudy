/**
 * O(n)
 * O(n)
 * 285 ms
 * 
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
// public class Solution {
//     public boolean isValidBST(TreeNode root) {
//         ArrayList<Integer> al = createArr(root);
//         for(int i = 1; i < al.size(); i++)
//             if(al.get(i) <= al.get(i - 1))
//                 return false;
//         return true;
//     }
    
//     public ArrayList<Integer> createArr(TreeNode root){
//         ArrayList<Integer> al = new ArrayList<Integer>();
//         if(root == null) return al;
        
//         al.addAll(createArr(root.left));
//         al.add(root.val);
//         al.addAll(createArr(root.right));
        
//         return al;
//     }
// }
/**
 * O(n)
 * O(1)
 * 361 ms
 */
public class Solution {
    public Integer last_printed = null;
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;
        
        if(! isValidBST(root.left)) return false;
        if(last_printed != null && root.val <= last_printed) return false;
        last_printed = root.val;
        
        if(! isValidBST(root.right)) return false;
        
        return true;
    }
}