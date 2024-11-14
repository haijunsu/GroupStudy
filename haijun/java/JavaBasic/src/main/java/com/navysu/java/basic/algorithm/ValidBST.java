package com.navysu.java.basic.algorithm;

/**
 * LeetCode 98 Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Given the root of a binary tree, determine if it is a valid binary search
 * tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left
 * subtree
 * of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidBST {

    private Integer lastVal = null;

    public static void main(String[] args) {

        BstTreeNode root = new BstTreeNode(2, new BstTreeNode(1), new BstTreeNode(3));
        System.out.println(new ValidBST().isValidBST(root));

        root = new BstTreeNode(5, new BstTreeNode(1), new BstTreeNode(4, new BstTreeNode(3), new BstTreeNode(6)));
        System.out.println(new ValidBST().isValidBST(root));

    }

    /**
     * Checks if the given binary tree is a valid binary search tree (BST).
     *
     * A valid BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the
     * node's key.
     * The right subtree of a node contains only nodes with keys greater than the
     * node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     * @param root the root of the binary tree
     * @return true if the tree is a valid BST, false otherwise
     */
    public boolean isValidBST(BstTreeNode root) {
        if (root == null)
            return true;
        if (!isValidBST(root.left))
            return false;
        if (lastVal != null && root.val <= lastVal)
            return false;
        lastVal = root.val;
        return isValidBST(root.right);

    }

}

class BstTreeNode {
    int val;
    BstTreeNode left;
    BstTreeNode right;

    public BstTreeNode() {
    }

    public BstTreeNode(int val) {
        this.val = val;
    }

    BstTreeNode(int val, BstTreeNode left, BstTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}