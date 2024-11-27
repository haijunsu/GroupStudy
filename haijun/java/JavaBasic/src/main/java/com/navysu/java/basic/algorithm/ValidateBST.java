package com.navysu.java.basic.algorithm;

/**
 * Leetcode 98 Validate Binary Search Tree
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
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 */
public class ValidateBST {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * A helper function to check if a subtree is a valid BST.
     *
     * This function takes a node, a minimum value, and a maximum value as
     * parameters. The minimum and maximum values are used to check if the given
     * node is a valid BST. If the given node is null, it is considered a valid
     * BST. Otherwise, it recursively checks the left and right subtrees to see
     * if they are valid BSTs. If either the left or right subtree is not a valid
     * BST, this function returns false. Otherwise, it returns true.
     *
     * @param node the current node to check
     * @param min  the minimum value the current node can have
     * @param max  the maximum value the current node can have
     * @return true if the subtree rooted at the given node is a valid BST,
     *         false otherwise
     */
    private boolean isValidBST(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if (min != null && node.val <= min) {
            return false;
        }
        if (max != null && node.val >= max) {
            return false;
        }
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    public TreeNode pre = null;

    /**
     * Checks if the given binary tree is a valid binary search tree (BST).
     *
     * This function works by in-order traversing the tree and checking if the
     * current node's value is valid (greater than the previous node's value). If
     * the current node's value is not valid, the function returns false.
     * Otherwise, it recursively calls the function on the right subtree.
     *
     * @param root the root of the binary tree to check
     * @return true if the tree is a valid BST, false otherwise
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST2(root.left)) {
            return false;
        }
        if (pre != null && pre.val >= root.val) {
            return false;
        }
        pre = root;
        return isValidBST2(root.right);
    }

    public static void main(String[] args) {
        ValidateBST solution = new ValidateBST();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        System.out.println(solution.isValidBST(root)); // false
        System.out.println(solution.isValidBST2(root)); // false

        solution.pre = null;
        root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(solution.isValidBST(root)); // true
        System.out.println(solution.isValidBST2(root)); // true
    }

}
