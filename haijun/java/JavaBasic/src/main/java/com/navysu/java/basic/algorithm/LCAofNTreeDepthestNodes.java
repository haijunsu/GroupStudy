package com.navysu.java.basic.algorithm;

public class LCAofNTreeDepthestNodes {
	TreeNode findLCA(TreeNode node) {
		if (node == null || node.children.isEmpty())
			return node;
		Result result = helper(node);
		return result.node;
	}

	Result helper(TreeNode node) {
		if (node.children.isEmpty())
			return new Result(node, 1);
		int maxDepth = Integer.MIN_VALUE;
		Result result = new Result(node, maxDepth);
		for (int i = 0; i < node.children.size(); ++i) {
			Result child = helper(node.children.get(i));
			if (child.maxDepth > maxDepth) {
				maxDepth = child.maxDepth;
				if (maxDepth > 1) { // if maxDepth is 1, common parent is current node.
					result.node = child.node;
				}
				result.maxDepth = child.maxDepth + 1;
			} else if (maxDepth == child.maxDepth) {
				result.node = node;
			}
		}
		// System.out.println(result.node.val + ", " + result.maxDepth);
		return result;
	}

	public static void main(String[] args) {
		TreeNode[] nodes = new TreeNode[16];
		for (int i = 0; i < 16; ++i) {
			nodes[i] = new TreeNode(i);
		}
		nodes[0].children.add(nodes[1]);
		nodes[0].children.add(nodes[2]);
		nodes[0].children.add(nodes[3]);
		nodes[1].children.add(nodes[4]);
		nodes[1].children.add(nodes[5]);
		nodes[1].children.add(nodes[6]);
		nodes[2].children.add(nodes[7]);
		nodes[3].children.add(nodes[8]);
		nodes[4].children.add(nodes[9]);
		nodes[4].children.add(nodes[10]);
		nodes[6].children.add(nodes[11]);
		nodes[8].children.add(nodes[12]);
		nodes[8].children.add(nodes[13]);
		nodes[13].children.add(nodes[14]);
		nodes[13].children.add(nodes[15]);
		LCAofNTreeDepthestNodes lca = new LCAofNTreeDepthestNodes();
		// 13
		System.out.println(lca.findLCA(nodes[0]).val);
		// 0
		nodes[13].children.clear();
		System.out.println(lca.findLCA(nodes[0]).val);
		// 13
		nodes[13].children.add(nodes[14]);
		System.out.println(lca.findLCA(nodes[0]).val);
		// 1
		nodes[8].children.clear();
		System.out.println(lca.findLCA(nodes[0]).val);
		// 6
		nodes[4].children.clear();
		System.out.println(lca.findLCA(nodes[0]).val);
	}

}

class Result {
	TreeNode node;
	int maxDepth;

	public Result(TreeNode node, int maxDepth) {
		this.node = node;
		this.maxDepth = maxDepth;
	}
}
