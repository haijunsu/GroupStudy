package com.navysu.java.basic.algorithm;

/**
 * leetcode 337 House Robber III
 * https://leetcode.com/problems/house-robber-iii
 * The thief has found himself a new place for his thievery again. There is only
 * one entrance to this area, called root.
 *
 * Besides the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that all houses in this place form a binary tree. It
 * will automatically contact the police if two directly-linked houses were
 * broken into on the same night.
 *
 * Given the root of the binary tree, return the maximum amount of money the
 * thief can rob without alerting the police.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *
 *
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 104
 */

public class HouseRob3 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        HouseRob3 solution = new HouseRob3();
        System.out.println(solution.rob(root));

        root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        System.out.println(solution.rob(root));
    }

    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * A helper function to calculate the maximum amount of money that can be robbed
     * from the binary tree rooted at the given node.
     *
     * This function returns an integer array, where:
     * - The first element represents the maximum money that can be robbed if the
     * current
     * node is included.
     * - The second element represents the maximum money that can be robbed if the
     * current
     * node is not included.
     *
     * The function works recursively by considering two scenarios:
     * 1. Including the current node's value and the values from excluding its
     * children.
     * 2. Excluding the current node's value, and taking the maximum values from
     * both
     * inclusion and exclusion of its children.
     *
     * @param root the root node of the binary tree
     * @return an integer array containing two elements: the maximum money that can
     *         be
     *         robbed with or without including the current node.
     */
    private int[] helper(TreeNode root) {
        if (root == null)
            return new int[] { 0, 0 };
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int includeRoot = root.val + left[1] + right[1]; // only one choice that to get children values
        int excludeRoot = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // get max children without root
        return new int[] { includeRoot, excludeRoot };
    }

}
