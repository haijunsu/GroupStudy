import java.util.*;
/**
 * O(n)
 * O(k)
 * 321 ms
 * 
 * LinkedList
 * Stack
 *      empty(), push(), pop()
 * 
 * Note:
 *      Use dummy node if necessary. 
 *      Stay calm, trust yourself.
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
    //1st Submission: 29 min
    //2nd Submission: 31 min
    //3rd Submission: 33 min (Accepted)
    public ListNode reverseKGroup(ListNode head, int k) {//01182015: 17:30 - 18:03
        if(head == null || head.next == null || k == 1) return head;
        
        Stack<ListNode> sta = new Stack<ListNode>();
        int staSize = 0;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        
        ListNode p = head, PreGroup_lastNode = dummy;
        
        while(p != null){
            for(int i = 0; i <= k - 1; i++){//go k+1 steps
                sta.push(p);
                staSize++;
                
                p = p.next;
                if(p == null && staSize < k) return dummy.next;//break;
                
            }
            while(! sta.empty() ){
                PreGroup_lastNode.next = sta.pop();
                PreGroup_lastNode = PreGroup_lastNode.next;
            }
            PreGroup_lastNode.next = p;
            staSize = 0;
        }
        return dummy.next;
    }
}