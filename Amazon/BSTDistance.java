/*
* Given a list of values, create a BST that can determine the distance between
* two given nodes.
*/

import java.util.*;
public class BSTDistance{

    public int bstDistance(int[] values, int n, int node1, int node2){
        BTreeNode tree = createBTree(values, n);
        BTreeNode lca = findLCA(tree, node1, node2);
        return getDistance(lca, node1) + getDistance(lca, node2);
    }

    BTreeNode createBTree(int[] values, int n) {
        BTreeNode root = new BTreeNode(values[0]);
        for (int i = 1; i < n; ++i) {
            BTreeNode current = root;
            while (current != null) {
                // how to handle if value equals current value?
                // assume there is no duplicate values in the given list
                if (values[i] > current.val) {
                    // right
                    if (current.right == null) {
                        current.right = new BTreeNode(values[i]);
                        break;
                    } else {
                        current = current.right;
                    }
                } else {
                    // left
                    if (current.left == null) {
                        current.left = new BTreeNode(values[i]);
                        break;
                    } else {
                        current = current.left;
                    }
                }
            }
        }
        return root;
    }

    BTreeNode findLCA(BTreeNode root, int node1, int node2) {
        if (root == null) {
            return null;
        }
        if (root.val == node1 || root.val == node2) {
            return root;
        }
        BTreeNode left = findLCA(root.left, node1, node2);
        BTreeNode right = findLCA(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        return right;
    }

    int getDistance(BTreeNode root, int node) {
        if (root == null) return -1; // error input
        int distance = 0;
        while (root.val != node) {
            ++distance;
            if (node > root.val) {
                // right
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return distance;
    }

    class BTreeNode {
        int val;
        BTreeNode left;
        BTreeNode right;
        BTreeNode(int x) { val = x; }
    }

    public static void main(String []args){
        BSTDistance bstd = new BSTDistance();
        int[] input = new int[]{5,6,3,1,2,4};
        int result = bstd.bstDistance(input, 6, 3, 5);
        System.out.println(result);
    }
}
