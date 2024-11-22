package com.navysu.java.basic.algorithm;

import java.util.ArrayList;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;
    public ArrayList<TreeNode> children;

    public TreeNode(int val) {
        this.val = val;
        children = new ArrayList<>();
    }

    @Override
    public String toString() {
        String str = Integer.toString(val);
        if (left == null) {
            str += ",,";
        } else {
            str += "," + left.toString() + ",";
        }
        if (right == null) {
            str += ",";
        } else {
            str += "," + right.toString() + ",";
        }

        return str;
    }
}
