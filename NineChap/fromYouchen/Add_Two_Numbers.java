/**
 * DO IT AGAIN!
 * 
 * O(n)
 * O(1)
 * 424 ms
 * 
 * LinkedList
 * Two-pointers
 * 
 * Note:
 *      Think carefully. Don't go crazy.
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
		if(l2 == null) return l1;
		
		//ensure that both of them are concrete.
		int carry = 0, n1V, n2V;
		boolean firstTime = true;
		ListNode returnHead = null, head = null;
		
		while(l1 != null || l2 != null || carry != 0) {
			
			if(l1 == null){
			    n1V = 0;
			}else{
			    n1V = l1.val;
			}
			
			if(l2 == null){
			    n2V = 0;
			}else{
			    n2V = l2.val;
			}
			
			
			if(firstTime){
			    head = new ListNode((n1V + n2V + carry) % 10);
			    returnHead = head;
			    firstTime = false;
			}else{
			    ListNode sum = new ListNode((n1V + n2V + carry) % 10);
			    head.next = sum;
			    head = head.next;
			}
			
			
			carry = (n1V + n2V + carry) / 10;
			
			
			
			if(l1 != null)
			    l1 = l1.next;
			if(l2 != null)
			    l2 = l2.next;
		}//while
		
		return returnHead;
    }
}