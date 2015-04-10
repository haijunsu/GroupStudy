import java.util.*;
/**
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
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         * My solution
         * O(m + n)
         * O(m) or O(n)
         * 371 ms
         * 
         * LinkedList
         * 
         * Note: Hard to have thought of HashSet.
         */
        if(headA == null || headB == null) return null;
        
        HashSet<ListNode> hs = new HashSet<ListNode>();
        while(headA != null){
            hs.add(headA);
            headA = headA.next;
        }
        while(headB != null){
            if(! hs.add(headB)) return headB;
            headB = headB.next;
        }
        return null;
        
        /**
         * Idea from official solution.
         * O(m + n)
         * O(1)
         * 362 ms
         * 
         * Note:
         *      O(n) time complexity could be O(2n) or O(3n) time complexity
         *      It's not equally saying that only pass ONCE!
         */
        //  if(headA == null || headB == null) return null;
         
        //  ListNode pa = headA, pb = headB;
        //  int diff = 0;
        //  boolean flag = false;
         
        //  while(pa.next != null && pb.next != null){
        //      pa = pa.next;
        //      pb = pb.next;
        //  }
         
        //  if(pa.next == null){
        //     flag = true;//true indicates that A is shorter than B.
         
        //     while(pb.next != null){
        //         pb = pb.next;
        //         diff++;
        //     }
        //  }
        //  else if(pb.next == null)
        //     while(pa.next != null){
        //         pa = pa.next;
        //         diff++;
        //     }
        
        // pa = headA;
        // pb = headB;
         
        // if(flag){//pb goes first!
        //     for(int i = 0; i < diff; i++)
        //         pb = pb.next;
        // }else{
        //   for(int i = 0; i < diff; i++)
        //         pa = pa.next;
        // }
        // while(pa != pb){
        //         pa = pa.next;
        //         pb = pb.next;
        //     }
        // if(pa == pb) return pa;
        // else return null;
    }
}