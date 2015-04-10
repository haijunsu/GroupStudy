/**
 * O(n^2)
 * O(1)
 * 496 ms
 * 
 * LinkedList
 * Insertion Sort
 * 
 * Note:
 *      Keep calm, and Care in detail, track a small case input.
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
    //1st Submission: 18 min
    //2nd Submission: 20 min (Accepted)
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        
        ListNode p = head.next, pPre = head, p1 = dummy, p2 = dummy.next;
        boolean inserted = false;
        
        while(p != null){
            while(p1 != p){
                if(p2.val > p.val){
                    pPre.next = p.next;
                    p1.next = p;
                    p.next = p2;
                    
                    p = pPre.next;
                    inserted = true;
                    break;
                }else{
                    p1 = p1.next;
                    p2 = p2.next;
                }
            }
            
            p1 = dummy;
            p2 = dummy.next;
            
            if(inserted) inserted = false;
            else{
                pPre = pPre.next;
                p = p.next;
            }
        }
        return dummy.next;
    }
}