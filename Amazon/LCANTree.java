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

    Map<Integer, NTreeNode> parentMap = new HashMap<Integer, NTreeNode>();
    Map<Integer, Integer> rankMap = new HashMap<Integer, Integer>();
    Map<Integer, NTreeNode> ancerstorMap = new HashMap<Integer, NTreeNode>();
    BitSet visited = new BitSet();

    public void makeSet(NTreeNode node) {
        parentMap.put(node.val, node);
        ancerstorMap.put(node.val, node);
        rankMap.put(node.val, 0);
    }

    public NTreeNode find(NTreeNode node) {
        NTreeNode current = node;
        while(node != parentMap.get(node.val)) {
            node = parentMap.get(parentMap.get(node.val).val);
        }
        while(current != node) {
            parentMap.put(current.val, node);
            current = parentMap.get(current.val);
        }
        return node;
    }

    public void union(NTreeNode node1, NTreeNode node2) {
        NTreeNode root1 = find(node1);
        NTreeNode root2 = find(node2);
        if (root1 == root2) return;
        if (rankMap.get(root1.val) > rankMap.get(root2.val)) {
            parentMap.put(root2.val, root1);
        } else if (rankMap.get(root1.val) < rankMap.get(root2.val)) {
            parentMap.put(root1.val, root2);
        } else {
            parentMap.put(root1.val, root2);
            rankMap.put(root2.val, rankMap.get(root2.val) + 1);
        }
    }

    public boolean lca(NTreeNode node, NTreeNode node1, NTreeNode node2) {
        makeSet(node);
        for (NTreeNode child: node.children) {
            if (lca(child, node1, node2)) {
                return true;
            }
            union(node, child);
            ancerstorMap.put(find(node).val, node); // Note: ancerstor doesn't equal parent
        }
        visited.set(node.val);
        boolean isfound = false;
        if (node == node1 && visited.get(node2.val)) {
            isfound = true;
            System.out.println("Found: " + ancerstorMap.get(find(node2).val));
        } else if (node == node2 && visited.get(node1.val)) {
            isfound = true;
            System.out.println("Found: " + ancerstorMap.get(find(node1).val));
        }
        return isfound;
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

