package com.navysu.java.basic.algorithm.graph;

import java.util.PriorityQueue;

/**
 * Leetcode 23 Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted
 * in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 */

public class MergeSortedLists {

    public static void main(String[] args) {
        int[] nums1 = { 1, 4, 5 };
        int[] nums2 = { 1, 3, 4 };
        int[] nums3 = { 2, 6 };
        ListNode list1 = createList(nums1);
        ListNode list2 = createList(nums2);
        ListNode list3 = createList(nums3);
        ListNode[] lists = { list1, list2, list3 };
        ListNode head = mergeKLists(lists);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        // Output: 1 1 2 3 4 4 5 6
        // Test case 2: []
        ListNode[] lists2 = {};
        ListNode head2 = mergeKLists(lists2);
        while (head2 != null) {
            System.out.println(head2.val);
            head2 = head2.next;
        }
        // Output: []
        // Test case 3: [[]]
        ListNode lists3 = new ListNode();
        ListNode[] lists4 = { lists3 };
        ListNode head3 = mergeKLists(lists4);
        while (head3 != null) {
            System.out.println(head3.val);
            head3 = head3.next;
        }
        // Output: []

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
        return head.next;
    }

    private static ListNode createList(int[] nums) {
        ListNode head = new ListNode();
        ListNode tail = head;
        for (int num : nums) {
            tail.next = new ListNode(num);
            tail = tail.next;
        }
        return head.next;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
