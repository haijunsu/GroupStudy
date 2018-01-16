/**
 * Find LCA in N-Tree (Tarjan)
 *
 * http://www.voidcn.com/article/p-pvuybkig-bcq.html
 * http://www.blogjava.net/kuaister/archive/2008/01/07/173485.html
 *
 */
import java.util.*;

class NTreeNode {
    List<NTreeNode> children;
    int val;
    NTreeNode(int value) {
        this.val = value;
        this.children = new ArrayList<NTreeNode>();
    }
    public String toString() {
        return String.valueOf(val);
    }
}

public class LCANTree {
    Map<String, NTreeNode> parentMap = new HashMap<String, NTreeNode>();
    Map<String, NTreeNode> ancerstorMap = new HashMap<String, NTreeNode>();
    Map<String, Integer> rankMap = new HashMap<String, Integer>();
    Map<String, Boolean> visitedMap = new HashMap<String, Boolean>();

    public void makeSet(NTreeNode node) {
        String key = getNodeValueAsString(node);
        parentMap.put(key, node);
        ancerstorMap.put(key, node);
        rankMap.put(key, 0);
        visitedMap.put(key, false);
    }

    public NTreeNode find(NTreeNode node) {
        NTreeNode root = node;
        String key = getNodeValueAsString(root);
        while (root != parentMap.get(key)) {// find root
            root = parentMap.get(key);
            key = getNodeValueAsString(root);
        }
        // flat the tree, make sure each node's parent is root.
        NTreeNode parent = null;
        while (node != root) {
            key = getNodeValueAsString(node);
            parent = parentMap.get(key);
            parentMap.put(key, root);
            node = parent;
        }
        return root;
    }

    public void union(NTreeNode node1, NTreeNode node2) {
        NTreeNode parent1 = find(node1);
        NTreeNode parent2 = find(node2);
        if (parent1 == parent2) {
            return;
        }
        String key1 = getNodeValueAsString(parent1);
        String key2 = getNodeValueAsString(parent2);
        if (rankMap.get(key1) > rankMap.get(key2)) {
            parentMap.put(key2, parent1);
        } else {
            parentMap.put(key1, parent2);
            if (rankMap.get(key1) == rankMap.get(key2)) {
                rankMap.put(key2, rankMap.get(key2) + 1);
            }
        }
    }

    private String getNodeValueAsString(NTreeNode node) {
        return String.valueOf(node.val);
    }

    public boolean lca(NTreeNode node, NTreeNode node1, NTreeNode node2) {
        makeSet(node);
        for (NTreeNode child: node.children) {
            if (lca(child, node1, node2)) {
                return true;
            }
            union(node, child);
            ancerstorMap.put(getNodeValueAsString(find(node)), node);
        }
        visitedMap.put(getNodeValueAsString(node), true);
        boolean found = false;
        if (node == node1 &&
            visitedMap.getOrDefault(getNodeValueAsString(node2),false)) {
                System.out.println("LCA: " + node1 + " -> " + node2 + " => " +  ancerstorMap.get(getNodeValueAsString(find(node2))).val);
                found = true;
        } else if (node == node2 &&
           visitedMap.getOrDefault(getNodeValueAsString(node1), false)) {
                System.out.println("LCA: " + node1 + " -> " + node2 + " => " + ancerstorMap.get(getNodeValueAsString(find(node1))).val);
                found = true;
        }
        return found;
    }

    public static void test(NTreeNode root, NTreeNode expected,
            NTreeNode node1, NTreeNode node2) {
        LCANTree lcan = new LCANTree();
		System.out.println("Expect value: " + expected);
		lcan.lca(root, node1, node2);
    }

	public static void main(String[] args) {
		NTreeNode[] nodes = new NTreeNode[16];
		for (int i = 0; i < 16; ++i) {
			nodes[i] = new NTreeNode(i);
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

        test(nodes[0], nodes[4], nodes[9], nodes[10]);
        test(nodes[0], nodes[1], nodes[6], nodes[10]);
        test(nodes[0], nodes[0], nodes[14], nodes[10]);
        test(nodes[0], nodes[13], nodes[14], nodes[15]);
        test(nodes[0], nodes[8], nodes[8], nodes[15]);
        test(nodes[0], nodes[0], nodes[2], nodes[15]);
        test(nodes[0], nodes[8], nodes[12], nodes[15]);
        test(nodes[0], nodes[0], nodes[9], nodes[15]);
        test(nodes[0], nodes[0], nodes[9], nodes[7]);
        test(nodes[0], nodes[0], nodes[9], nodes[8]);
        test(nodes[0], nodes[0], nodes[11], nodes[15]);
        test(nodes[0], nodes[0], nodes[5], nodes[7]);
        test(nodes[0], nodes[1], nodes[5], nodes[11]);
	}
}

