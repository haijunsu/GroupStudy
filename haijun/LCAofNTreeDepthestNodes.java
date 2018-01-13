/*
 * 变种LCA 求一棵树的最深叶子节点的公共父节点
 * 求一颗任意树（不一定是二叉树）所有最深叶子节点（数量可以是大于等于1的任意值，取决于树的结构）的最深公共前驱节点。
 * Source: <http://www.elvisyu.com/%E5%8F%98%E7%A7%8Dlca-%E6%B1%82%E4%B8%80%E6%A3%B5%E6%A0%91%E7%9A%84%E6%9C%80%E6%B7%B1%E5%8F%B6%E5%AD%90%E8%8A%82%E7%82%B9%E7%9A%84%E5%85%AC%E5%85%B1%E7%88%B6%E8%8A%82%E7%82%B9/>
 */
import java.util.*;
public class LCAofNTreeDepthestNodes {
	TreeNode findLCA(TreeNode node) {
		if (node == null || node.children.isEmpty()) return node;
		Result result = helper(node);
		return result.node;
	}

	Result helper(TreeNode node) {
		if (node.children.isEmpty()) return new Result(node, 1);
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
        //System.out.println(result.node.val + ", " + result.maxDepth);
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
class TreeNode{
	int val;
	ArrayList<TreeNode> children;
	public TreeNode(int val){
			this.val = val;
			children = new ArrayList<>();
	}
}

class Result{
	TreeNode node;
	int maxDepth;
	public Result(TreeNode node, int maxDepth){
			this.node = node;
			this.maxDepth = maxDepth;
	}
}


