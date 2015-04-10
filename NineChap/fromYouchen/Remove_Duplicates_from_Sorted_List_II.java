/**
 * O(n)
 * O(1)
 * 261 ms
 * 
 * LinkedList
 * Two pointers
 * 
 * Note:
 *      consider the small special input.
 *      run it in mind before submission.
 * 
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    //1st Submission: 13 min
    //2nd Submission: 17 min (Accepted)
    public ListNode deleteDuplicates(ListNode head) {//01182015 22:37 - 22:54
        if(head == null) return head;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        
        ListNode p1 = dummy, p2 = head.next;
        
        while(p2 != null){
            if(p2.val == p1.next.val){
                while(p2 != null && p2.val == p1.next.val)
                    p2 = p2.next;
                p1.next = p2;
                if(p2 != null)
                    p2 = p2.next;
            }
            else{
                p1 = p1.next;
                p2 = p2.next;
            }
        }//while
        
        return dummy.next;
    }
}