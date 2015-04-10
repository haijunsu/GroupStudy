/**
 * O(n)
 * O(1)
 * 198 ms
 * 
 * LinkedList
 * Three-pointers
 * 
 * Note:
 *      Iterate the program after finish.
 *      Sometimes you may need 3 pointers rather than two!!
 * 
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
    public ListNode swapPairs(ListNode head) {
       if(head == null || head.next == null) return head;
        
        //this list has at least two nodes.
        ListNode p1 = head, p2 = head.next, pre = head;
        boolean firstSwap = true;
        
        while(true){
            if(firstSwap) {
            	p1.next = p2.next;
                p2.next = p1;
                
            	head = p2;
            	firstSwap = false;
            }else {
            	pre.next = p2;
            	p1.next = p2.next;
            	p2.next = p1;
            }
            pre = p1;
            
            p1 = p1.next;
            if(p1 == null) break;
            p2 = p1.next;
            if(p2 == null) break;
        }
        return head;
    }
}