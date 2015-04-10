import java.util.*;
/**
 * DO IT AGAIN.
 * O(n) since O(3n)
 * O(n)
 * 312 ms
 * 
 * LinkedList
 * 
 * Note:
 *      Think about the idea deeply.
 *      Try to find a special way to solve.
 * 
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    //1st Submission 37 min
    //2nd Submission 37 min
    //3rd Submission 38 min (Accepted)
    public RandomListNode copyRandomList(RandomListNode head) {//01182015 23:05 - 23:43
        if(head == null) return null;
        
        //insert duplicate node after each original node 
        RandomListNode p = head;
        while(p != null){
            RandomListNode temp = new RandomListNode(p.label);
            temp.next = p.next;
            p.next = temp;
            
            p = p.next.next;
        }
        
        //copy random pointers.
        p = head;
        while(p != null){
            if(p.random != null)
                p.next.random = p.random.next;
            else
                p.next.random = null;
                
            p = p.next.next;
        }
        
        //split two list
        p = head;
        RandomListNode q = p.next.next, returnHead = head.next;
        
        while(q != null){
            p.next.next = q.next;
            p.next = q;
            
            p = p.next;
            q = q.next.next;
        }
        p.next = null;
        
        return returnHead;
    }
}