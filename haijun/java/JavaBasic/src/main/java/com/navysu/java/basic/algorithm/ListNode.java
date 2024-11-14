package com.navysu.java.basic.algorithm;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode prev;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val, ListNode prev, ListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}
