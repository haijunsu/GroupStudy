/**
 * O(ListLength) since O(m + n) < O(List_length)
 * O(n) since O(n - m + 2 + 1) < O(n - m) < O(n)
 * 200 ms 
 * 
 * LinkedList
 * Two-pointers
 * 
 * Note:
 *      Be careful for the variable using.
 *      Better keep in mind if the var is changed or not before using it.
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
    public ListNode reverseBetween(ListNode head, int m, int n) {//35 min
        if(head == null || head.next == null || m == n) return head;
        
        ListNode p1 = head, p2 = head;
        int len = n - m + 1;
        int[] value = new int[len];
        m--;
        
        while(m >= 1){
        	m--;
        	p1 = p1.next;
        	p2 = p2.next;
        }//while
        
        //p1 stay here, p2 traverse untial n;
        for(int i = 0; i <= len - 1; i++){//before here is i <= n - m, actually, m is changed to 0.
            value[i] = p2.val;
            p2 = p2.next;
        }
        
        for(int i = len - 1; i >= 0; i--){
            p1.val = value[i];
            p1 = p1.next;
        }
        
        return head; 
    }
}