/**
 * O(n)
 * O(height)
 * 376 ms
 * 
 * Note:
 *      Hard one.
 *      But the reference solution is nice.
 * 
 * Reference:
 *      http://www.cnblogs.com/springfor/p/3886411.html
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
    public int maxPathSum(TreeNode root) {//4/20 15:52 - 17:01 (1 h 9 min)
        // if(root == null)
        //     return 0;
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        
        findMax(root, max);
        return max[0];
    }
    private int findMax(TreeNode root, int[] max){
        if(root == null)
            return 0;
        
        int left = findMax(root.left, max);
        int right = findMax(root.right, max);
        
        int curMax = Math.max(root.val, Math.max(left + root.val, right + root.val));
        max[0] = Math.max(max[0], Math.max(curMax, root.val + left + right));
        
        return curMax;
    }
}