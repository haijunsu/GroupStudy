/**
 * O(n)
 * O(1) = p1 + p2 + head + returnHead;
 * 278 ms
 * 
 * LinkedList
 * Three pointers
 * 
 * Note:
 *      Consider less pointer if possible.
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
        if( l1 == null && l2 != null ) return l2;
        if( l1 != null && l2 == null ) return l1;
        
        //they are both concrete!
        ListNode p1 = l1, p2 = l2, head, returnHead;
        
        if(p1.val <= p2.val){
            head = p1;
            returnHead = head;
            p1 = p1.next;
        }
        else{
            head = p2;
            returnHead = head;
            p2 = p2.next;
        }
            
        while(p1 != null || p2 != null){
            if(p1 == null){
                head.next = p2;
                break;
            }
            if(p2 == null){
                head.next = p1;
                break;
            }
            if(p1.val <= p2.val){
                head.next = p1;
                head = head.next;
                p1 = p1.next;
            }
            else{
                head.next = p2;
                head = head.next;
                p2 = p2.next;
            }
        }
        
        return returnHead;
    }
}