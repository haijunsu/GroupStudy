/*
* Given a list of values, create a BST that can determine the distance between
* two given nodes.
*/

import java.util.*;
public class BSTDistance{

    public int bstDistance(int[] values, int n, int node1, int node2){
        BTreeNode root = createBST(values, n);
        BTreeNode commParent = lca(root, node1, node2);
        return distance(commParent, node1) + distance(commParent, node2);
    }

    public BTreeNode createBST(int[] values, int n) {
        if (values == null || values.length == 0) return null;
        BTreeNode root = new BTreeNode(values[0]);
        for (int i = 1; i < values.length; ++i) {
            BTreeNode parent = root;
            BTreeNode newNode = new BTreeNode(values[i]);
            while (true) {
                if(newNode.val > parent.val) {
                    if (parent.right == null) {
                        parent.right = newNode;
                        break;
                    } else{
                        parent = parent.right;
                    }
                } else {
                    if (parent.left == null) {
                        parent.left = newNode;
                        break;
                    } else {
                        parent = parent.left;
                    }
                }
            }
        }
        return root;
    }

    public BTreeNode lca(BTreeNode root, int node1, int node2) {
        if (root == null) return null;
        if (root.val == node1 || root.val == node2) return root;
        BTreeNode left = lca(root.left, node1, node2);
        BTreeNode right =  lca(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        }
        return right;
    }

    public int distance(BTreeNode parent, int node) {
        int distance = 0;
        while (parent.val != node) {
            ++distance;
            if (node > parent.val) {
                parent = parent.right;
            } else {
                parent = parent.left;
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
        int[] input = new int[]{5,6,3,1,2,4};
        test(input, 6, 3, 5, 1);
        test(input, 6, 3, 6, 2);
        test(input, 6, 4, 6, 3);
        test(input, 6, 2, 6, 4);
        test(input, 6, 1, 4, 2);
        test(input, 6, 1, 3, 1);
        test(input, 6, 2, 3, 2);
        test(input, 6, 2, 4, 3);
    }

    private static void test(int[] input, int size, int node1, int node2, int answer) {
        System.out.println("Expect: " + answer + ". ");
        BSTDistance bstd = new BSTDistance();
        int result = bstd.bstDistance(input, size, node1, node2);
        System.out.println("Your answer: " + result);
        System.out.println(answer == result ? "Accept" : "Wrong answer");
    }

    public int bsdDistance2(int[] input, int size, int node1, int node2) {
        BTreeNode root = buildBstTree(input, size);
        BTreeNode parent = findLca(root, node1, node2);
        return dist(parent, node1) + dist(parent, node2);
    }

    BTreeNode buildBstTree(int[] input, int size) {
        if (input == null || input.length == 0) return null;
        BTreeNode root = new BTreeNode(input[0]);
        for (int i = 1; i < size; ++i) {
            BTreeNode curr = root;
            while (true) {
                if (curr.val > input[i]) {
                    if (curr.left == null) {
                        curr.left = new BTreeNode(input[i]);
                        break;
                    } else {
                        curr = curr.left;
                    }
                } else {
                    if (curr.right == null) {
                        curr.right = new BTreeNode(input[i]);
                        break;
                    } else {
                        curr = curr.right;
                    }
                }
            }
        }
        return root;
    }

    BTreeNode findLca(BTreeNode root, int val1, int val2) {
        if (root == null) return null;
        if (root.val == val1 || root.val == val2) return root;
        BTreeNode left = findLca(root.left, val1, val2);
        BTreeNode right = findLca(root.right, val1, val2);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        }
        return right;
    }

    int dist(BTreeNode parent, int val) {
        int distance = 0;
        while (parent.val != val) {
            if (parent.val > val) {
                // left
                ++distance;
                parent = parent.left;
            } else if (parent.val < val) {
                ++ distance;
                parent = parent.right;
            }
        }
        return distance;
    }
}
