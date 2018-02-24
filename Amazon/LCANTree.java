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
    Map<NTreeNode, NTreeNode> parentMap = new HashMap<NTreeNode, NTreeNode>();
    Map<NTreeNode, Integer> rankMap = new HashMap<NTreeNode, Integer>();
    Map<NTreeNode, Boolean> visitedMap = new HashMap<NTreeNode, Boolean>();
    Map<NTreeNode, NTreeNode> anscMap = new HashMap<NTreeNode, NTreeNode>();

    void makeSet(NTreeNode node) {
        parentMap.put(node, node);
        anscMap.put(node, node);
        rankMap.put(node, 0);
        visitedMap.put(node, false);
    }

    NTreeNode find(NTreeNode node) {
        NTreeNode current = node;
        while (current != parentMap.get(current)) {
            current = parentMap.get(parentMap.get(current));
        }
        while (current != parentMap.get(node)) {
            NTreeNode tmp = parentMap.get(node);
            parentMap.put(node, current);
            node = tmp;
        }
        return current;
    }

    void union(NTreeNode node1, NTreeNode node2) {
        NTreeNode root1 = find(node1);
        NTreeNode root2 = find(node2);
        if(root1 == root2) return;
        if (rankMap.get(root1) > rankMap.get(root2)) {
            parentMap.put(root2, root1);
        } else {
            parentMap.put(root1, root2);
            if (rankMap.get(root1) == rankMap.get(root2)) {
                rankMap.put(root2, rankMap.get(root2) + 1);
            }
        }
    }


    public boolean lca(NTreeNode node, NTreeNode node1, NTreeNode node2) {
        makeSet(node);
        boolean found = false;
        for (NTreeNode child: node.children) {
            found = lca(child, node1, node2);
            if (found) return true;
            union(node, child);
            anscMap.put(find(node), node);
        }
        visitedMap.put(node, true);
        if (node1 == node && visitedMap.getOrDefault(node2, false)) {
            found = true;
            System.out.println("Found: " + anscMap.get(find(node2)));
        } else if (node2 == node && visitedMap.getOrDefault(node1, false)) {
            found = true;
            System.out.println("Found: " + anscMap.get(find(node1)));

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

