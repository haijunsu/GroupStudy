package com.navysu.java.basic.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Leetcode 297 Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * erialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes
 * a binary tree. You do not necessarily need to follow this format, so please
 * be creative and come up with different approaches yourself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 */
public class SerializeNode {

    public static void main(String[] args) {
        SerializeNode serializeNode = new SerializeNode();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String serialized = serializeNode.serialize(root);
        System.out.println(serialized);
        TreeNode deserialize = serializeNode.deserialize(serialized);
        System.out.println(deserialize);
    }

    /**
     * Serializes a binary tree to a comma-separated string.
     *
     * This method uses level-order traversal (BFS) to serialize the tree. It
     * represents null nodes with the string "null" and separates each value
     * with a comma. If the tree is empty, it returns an empty string.
     *
     * @param root the root node of the binary tree to serialize
     * @return a string representation of the binary tree in level-order
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode nullNode = new TreeNode(Integer.MIN_VALUE);
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode node = queue.poll();
            if (node.val == Integer.MIN_VALUE) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");

                if (node.left == null) {
                    queue.offer(nullNode);
                } else {
                    queue.offer(node.left);
                }
                if (node.right == null) {
                    queue.offer(nullNode);
                } else {
                    queue.offer(node.right);
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Deserializes a comma-separated string back into a binary tree.
     *
     * This method uses level-order traversal (BFS) to deserialize the tree. It
     * assumes that the input string is a valid level-order traversal of a binary
     * tree, where null nodes are represented by the string "null". If the tree is
     * empty, it returns null.
     *
     * @param data a string representation of the binary tree in level-order
     * @return the root node of the deserialized binary tree
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() < 2) {
            return null;
        }
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        Queue<TreeNode> layer = new LinkedList<>();
        int index = 1;
        layer.offer(root);
        while (layer.size() > 0) {
            TreeNode node = layer.poll();
            TreeNode left = null;
            TreeNode right = null;
            if (!values[index].equals("null")) {
                left = new TreeNode(Integer.valueOf(values[index]));
                layer.offer(left);
                node.left = left;
            }
            index++;
            if (!values[index].equals("null")) {
                right = new TreeNode(Integer.valueOf(values[index]));
                layer.offer(right);
                node.right = right;
            }
            index++;
        }
        return root;
    }
}