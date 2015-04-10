/**
 * O(n)
 * O(1)
 * 250 ms
 * 
 * LinkedList
 * 
 * Note:
 *      Consider the most difficult case, do not assume the easy one.
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
    //1: 10 min
    //2: 13 min
    //3: 17 min (Accepted)
    public ListNode rotateRight(ListNode head, int n) {//01182015 23:52 - 0:09(19)
        if(head == null || head.next == null) return head;
        
        ListNode p1 = head, p2 = head;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        
        while(n >= 1){
            p2 = p2.next;
            if(p2 == null)
                p2 = head;
            n--;
        }
        while(p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        
        p2.next = dummy.next;
        dummy.next = p1.next;
        p1.next = null;
        
        return dummy.next;
    }
}