/**
 * DO IT AGAIN!
 * O(n)
 * O(1)
 * 233 ms
 * 
 * LinkedList
 * Two-pointers
 * 
 * Note:
 *      Remember this merge linked list way to solving the problem.
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
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
/**
 * My idea.
 * three pointer traverse the list and sew a new list,
 * 		This does not work well.
 */
//		//ensure that list has at least 2 nodes.
//		ListNode dummy = new ListNode(Integer.MIN_VALUE);
//		dummy.next = head;
//		ListNode p1 = dummy;
//
//
//		ListNode p2 = head, p3 = p2.next;
//		while(p1.next != null && p1.next.val <= x){//p1.next is larger than x
//			p1 = p1.next;
//		}
//		
//		while(p3 != null){
//			if(p3.val < x){
//				p2.next = p3.next;
//				p3.next = p1.next;
//				p1.next = p3;
//
//				p3 = p2.next;//put p3 back to p2.next again.
//			}else{
//				p2 = p2.next;
//				p3 = p3.next;
//			}
//		}
//		return head;

/**
 * Book's idea.
 * Keep two list, before and after, traverse the input list
 * 		Then add nodes into before or after list.
 * 		At last, merge the before and after.
 */
		ListNode beforeHead = null, 
				 beforeT = null, 
				 afterHead = null, 
				 afterT = null, 
				 t = head;
		
		while(t != null) {
			if(t.val < x) {
				if(beforeHead == null) {
					beforeHead = t;
					beforeT = beforeHead;
				}
				else {
					beforeT.next = t;
					beforeT = beforeT.next;
				}
				t = t.next;
				beforeT.next = null;
			}//if
			else {
				if(afterHead == null) {
					afterHead = t;
					afterT = afterHead;
				}
				else {
					afterT.next = t;
					afterT = afterT.next;
				}
				t = t.next;
				afterT.next = null;
			}
		}//while
		
	    if(beforeT != null) {
			beforeT.next = afterHead;
			return beforeHead;
		}
		return afterHead;
    }
}