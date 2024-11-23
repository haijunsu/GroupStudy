package com.navysu.java.basic.algorithm;

/**
 * leetcode 1038 Binary Search Tree to Greater Sum Tree
 * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 *
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree
 * such that every key of the original BST is changed to the original key plus
 * the sum of all keys greater than the original key in BST.
 *
 * As a reminder, a binary search tree is a tree that satisfies these
 * constraints:
 *
 * The left subtree of a node contains only nodes with keys less than the node's
 * key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * Example 2:
 *
 * Input: root = [0,null,1]
 * Output: [1,null,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 100].
 * 0 <= Node.val <= 100
 * All the values in the tree are unique.
 */
public class BinarySearchTreeToGreaterSumTree {

    private int sum = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.left.left = new TreeNode(3);
        root.right.left.right = new TreeNode(8);
        BinarySearchTreeToGreaterSumTree bstToGst = new BinarySearchTreeToGreaterSumTree();
        TreeNode convertedRoot = bstToGst.bstToGst(root);
        TreeNode convertedRoot2 = bstToGst.bstToGst2(root);
        System.out.println(convertedRoot);
        System.out.println(convertedRoot2);

    }

    /**
     * A recursive function to convert a binary search tree to a greater tree.
     *
     * This function works by first checking if the root is null. If it is, it
     * returns null. Otherwise, it recursively calls the function on the right
     * child to add the sum of all values greater than the root's value to the
     * root's value. Finally, it recursively calls the function on the left
     * child.
     *
     * @param root the root of the binary search tree
     * @return the root of the modified tree
     */
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }
        bstToGst(root.right);
        sum += root.val;
        root.val = sum;
        bstToGst(root.left);
        return root;
    }

    /**
     * A function to convert a binary search tree to a greater tree.
     *
     * This function works by calling a helper function on the root of the tree.
     * The helper function takes a TreeNode root and an int sum as parameters.
     * It returns the root of the modified tree.
     *
     * @param root the root of the binary search tree
     * @return the root of the modified tree
     */
    public TreeNode bstToGst2(TreeNode root) {
        if (root == null) {
            return null;
        }
        return helper(root, 0);
    }

    /**
     * A helper function to recursively convert a binary search tree to a
     * greater tree. It takes a TreeNode root and an int sum as parameters.
     *
     * This function works by first checking if the root is null. If it is, it
     * returns null. If the root has no right child, it adds the sum to the
     * root's value. Otherwise, it recursively calls the helper function on the
     * right child and adds the result to the root's value. Finally, it
     * recursively calls the helper function on the left child and assigns the
     * result to the root's left child.
     *
     * @param root the root of the binary search tree
     * @param sum  the sum of values greater than the root's value
     * @return the root of the modified tree
     */
    private TreeNode helper(TreeNode root, int sum) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            root.val += sum;
        } else {
            root.right = helper(root.right, root.val);
        }
        root.val += getMaxNodeValue(root.right);
        root.left = helper(root.left, root.val);
        return root;
    }

    /**
     * Gets the maximum node value in the leftmost path of the subtree rooted at the
     * given node.
     *
     * This function recursively traverses the left child of each node in the
     * subtree to find
     * the maximum node value in the leftmost path. If the subtree is empty, it
     * returns 0.
     *
     * @param root the root node of the subtree
     * @return the maximum node value in the leftmost path of the subtree
     */
    private int getMaxNodeValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.left == null ? root.val : getMaxNodeValue(root.left);

    }
}
