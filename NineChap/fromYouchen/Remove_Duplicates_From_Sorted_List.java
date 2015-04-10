/**
 * O(n)
 * O(1)
 * 261 ms
 * 
 * Linked List
 * Two pointers
 * 
 * Note: check head == null first, 
 *          then head.next == null;
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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode p1 = head;
        ListNode p2 = head.next;
        while(p2 != null){
            while(p2.val == p1.val){
                p2 = p2.next;
                if(p2 == null) {
                    p1.next = p2;
                    break;
                }//if
            }
            if(p2 != null){
                p1.next = p2;
                p1 = p1.next;
                p2 = p2.next;
            }
        }//while
        return head;
    }
}