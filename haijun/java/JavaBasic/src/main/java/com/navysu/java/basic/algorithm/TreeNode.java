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
}
