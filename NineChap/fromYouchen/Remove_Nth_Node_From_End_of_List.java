/**
 * O(n)
 * O(n)
 * 251 ms
 * 
 * LinkedList
 * Two-pointers
 * 
 * Note:
 *      Analyze the special small input case!
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //My Solution: O(n) O(n) 251 ms
        if(head.next == null) return null;
        
        ListNode[] p = new ListNode[n+1];
        p[0] = head;
        for(int i = 1; i < p.length; i++){
            p[i] = p[i - 1].next;
        }
        
        if(p[p.length - 1] == null){
            head = head.next;
            return head;
        }
        
        
        while(p[p.length - 1].next != null){
            for(int i = 0; i < p.length; i++)
                p[i] = p[i].next;
        }
        p[0].next = p[1].next;
        return head;
        
        
        
        
        
        /**
         * idea From 
         *      http://fisherlei.blogspot.com/2012/12/leetcode-remove-nth-node-from-end-of.html
         * O(n)
         * O(1)
         * 245 ms
         */
        // ListNode p1 = head, p2 = head;
        // for(int i = 0; i < n; i++)
        //     p2 = p2.next;
        // if(p2 == null){//remove the head.
        //     head = head.next;
        //     return head;
        // }
        // while(p2.next != null){
        //     p1 = p1.next;
        //     p2 = p2.next;
        // }
        // p1.next = p1.next.next;
        // return head;
    }
}