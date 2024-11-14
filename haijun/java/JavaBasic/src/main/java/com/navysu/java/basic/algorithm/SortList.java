package com.navysu.java.basic.algorithm;

/**
 * Leetcode 148. Sort List
 * https://leetcode.com/problems/sort-list/
 *
 * Given the head of a linked list, return the list after sorting it in
 * ascending order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 *
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 *
 *
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory
 * (i.e. constant space)?
 */

public class SortList {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        SortList solution = new SortList();
        ListNode sortedHead = solution.sortList(head);
        while (sortedHead != null) {
            System.out.println(sortedHead.val);
            sortedHead = sortedHead.next;
        }
        // Output: 1 2 3 4
        head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);
        sortedHead = solution.sortList(head);
        while (sortedHead != null) {
            System.out.println(sortedHead.val);
            sortedHead = sortedHead.next;
        }
        // Output: -1 0 3 4 5
        head = new ListNode();
        sortedHead = solution.sortList(head);
        while (sortedHead != null) {
            System.out.println(sortedHead.val);
            sortedHead = sortedHead.next;
        }
        // Output:
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        tail.next = left != null ? left : right;
        return dummy.next;
    }

}
