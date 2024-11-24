package com.navysu.java.basic.algorithm.backtrack;

import java.util.HashMap;
import java.util.Map;

import com.navysu.java.basic.algorithm.TreeNode;

/**
 * leetcode 437 Path Sum III
 * https://leetcode.com/problems/path-sum-iii/
 *
 * Given the root of a binary tree and an integer targetSum, return the number
 * of nodes that have a path such that the sum of the node values in the path
 * equals targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards (i.e., traveling only from parent nodes to child nodes).
 *
 * Example 1:
 *
 *
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are:
 * 1. 5 -> 3
 * 2. 5 -> 2 -> 1
 * 3. -3 -> 11
 *
 *
 * Example 2:
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 1000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */

public class PathSum {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(3);
        root.left.right.left = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        System.out.println(pathSum(root, 8)); // Output: 3

        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        System.out.println(pathSum(root, 22)); // Output: 3
    }

    /**
     * Given the root of a binary tree and an integer targetSum, return the
     * number of paths where the sum of the node values in the path equals
     * targetSum.
     *
     * @param root      the root of the binary tree
     * @param targetSum the target sum
     * @return the number of paths
     */
    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Map<Long, Integer> map = new HashMap<>();
        map.put(0l, 1);
        return helper(root, targetSum, 0, map);
    }

    /**
     * A helper function to find the number of paths that sum up to the target
     * sum.
     *
     * This function takes a TreeNode root, an int targetSum, a long sum, and a
     * Map<Long, Integer> as parameters. It returns the number of paths that sum
     * up to the target sum.
     *
     * The function works by recursively traversing the tree and adding the
     * current node's value to the sum. It then checks if the difference between
     * the sum and the target sum is present in the map, which means that there
     * is a path that sums up to the target sum. The count of such paths is then
     * incremented. The function then recursively calls itself on the left and
     * right children of the current node, and finally decrements the count in
     * the map.
     *
     * @param root      the root of the binary tree
     * @param targetSum the target sum
     * @param sum       the current sum
     * @param map       a map of sums to counts
     * @return the number of paths that sum up to the target sum
     */
    public static int helper(TreeNode root, int targetSum, long sum, Map<Long, Integer> map) {
        if (root == null) {
            return 0;
        }
        sum += root.val;
        int count = map.getOrDefault(sum - targetSum, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        count += helper(root.left, targetSum, sum, map);
        count += helper(root.right, targetSum, sum, map);
        map.put(sum, map.get(sum) - 1);
        return count;
    }
}
