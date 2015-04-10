/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
/**
 * O(n)
 * O(1) - constant extra space
 * 233 ms
 * 
 * Note:
 *      for the do-while part, just write the task (the thing after "//") first
 *          then add the details.
 *      Be careful regarding the null case, you cannot say cur.left if cur is null.
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        TreeLinkNode nextLevelLeft = root.left;
        TreeLinkNode cur = root;
        
        while(cur != null){
	        do{
        		//connect left and right
        		if(nextLevelLeft != null)
        			cur.left.next = cur.right;
        
        		//connect 5 to 6
        		if(cur.next != null && nextLevelLeft != null)
        			cur.right.next = cur.next.left;
        
        		//cur = cur.next
        		cur = cur.next;
	        }while(cur != null);//REMEMBER: A SEMICOLON IS NEEDED HERE!!!

        	cur = nextLevelLeft;
        	if(cur != null)
        		nextLevelLeft = cur.left;
        }//while
    }
}
 
 
/**
 * O(n)
 * O(h) = O(log n)
 * 240 ms
 *
 * Note:
 *      This algorithm does not use constant extra space!
 */
// public class Solution {
//     public void connect(TreeLinkNode root) {
//         if(root == null) return;
        
//         if(root.left != null)//since this is perfect binary tree, no need to add "&& root.right != null"
//             root.left.next = root.right;
        
//         if(root.next != null && root.right != null)
//             root.right.next = root.next.left;
            
//         connect(root.left);
//         connect(root.right);
//     }
// }