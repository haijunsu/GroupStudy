/*
* 2. item association
* Find the biggest size of union, if the same size, return the first one order by lex
* String[] maxAssociation(String[][] associations){}
* example
* input
* [itemA, itemB],
* [itemB, itemC],
* [itemG, itemD],.
* [itemD, itemF];
* -->[itemA, itemB, itemC]   [itemG, itemD, itemF]
* output:
* [itemA, itemB, itemC];
* 解法类似：https://leetcode.com/problems/friend-circles/description/
*
*
* ITEM ASSOCIATION。
* input
* [itemA, itemB], 表示物品 A 和物品 B 相互关联。
* [itemB, itemC], 表示物品 B 和物品 C 相互关联。
* 如果物品相互关联，就组成一个组。最后要求找出物品最多的那个组
*
*
* 给出一串 Pair, 每个ｐａｉｒ说明两个人互为朋友，[A,B] 说明Ａ和Ｂ是朋友，[Ｃ,B] 说明Ｃ和Ｂ是朋
* 友，｛Ｄ，Ｅ｝说明Ｅ和Ｄ是朋友。
* 找出人数最大的朋友圈，如果两个朋友圈人数相等，返回有着字典顺序最小朋友的那个圈。
*/
import java.util.*;

public class FriendCycle {

    // Union Find templete https://algs4.cs.princeton.edu/15uf/UF.java.html
    // Reference: https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
     class UnionFind {
        Map<String, String> parentMap = new HashMap<String, String>();
        Map<String, Integer> rankMap = new HashMap<String, Integer>();
        int count = 0;

        /**
         * Items is element relationships. Example: [["p1", "p2"], ["p3"],["p4"]]
         */
        UnionFind(String[][] items) {
            for (int i = 0; i < items.length; i++) {
                parentMap.put(items[i][0], items[i][0]);
                parentMap.put(items[i][1], items[i][1]);
                rankMap.put(items[i][0], 0);
                rankMap.put(items[i][1], 0);
            }
            Set<String> keySet = parentMap.keySet();
            count = keySet.size();
        }

        public String root(String item) {
            while (!item.equals(parentMap.get(item))) {
                parentMap.put(item, parentMap.get(parentMap.get(item)));
                item = parentMap.get(item);
            }
            return item;
        }

        public boolean connected(String item1, String item2) {
            return parentMap.get(item1).equals(parentMap.get(item2));
        }

        public void union(String item1, String item2) {
            String root1 = root(item1);
            String root2 = root(item2);
            if (root1.equals(root2)) return;
            if (rankMap.get(root2) > rankMap.get(root1)) {
                parentMap.put(root1, root2);
            } else {
                parentMap.put(root2, root1);
                if (rankMap.get(root2) == rankMap.get(root1)) {
                    rankMap.put(root2, rankMap.get(root2) + 1);
                }
            }
            --count;
        }

        public int getCount() {
            return count;
        }

        public String[] getLargetElements() {
            System.out.println(parentMap);
            Set<String> keySet = parentMap.keySet();
            Map<String, List<String>> results = new HashMap<String, List<String>>();
            for (String key:keySet) {
                // In one cycle, parent can be different but root is same. So we cannot use parentMap.get(key).
                String value = root(key);
                List<String> list = results.getOrDefault(value, new ArrayList<String>());
                list.add(key);
                results.put(value, list);
            }
            int max = 0;
            List<String> maxList = null;
            keySet = results.keySet();
            Set<String> sortedSet = new TreeSet<String>();
            sortedSet.addAll(keySet);
            for (String key: sortedSet) {
                List<String> tmp = results.get(key);
                if (tmp.size() > max) {
                    max = tmp.size();
                    maxList = tmp;
                }
            }
            String[] fcycle = (String[]) maxList.toArray(new String[0]);
            Arrays.sort(fcycle);
            return fcycle;
        }
     }

     public String[] findLargestNetwork(String[][] people) {
         UnionFind uf = new UnionFind(people);
         for (int i = 0; i < people.length; ++i) {
             uf.union(people[i][0], people[i][1]);
         }
         System.out.println(uf.getCount());
         return uf.getLargetElements();
     }

     public static void main(String []args){
        FriendCycle fc = new FriendCycle();
        // Case 1: [friend3, friend4, friend5]
        String[][] people = new String[][]{
                        {"friend1", "friend2"},
                        {"friend3", "friend4"},
                        {"friend4", "friend5"}
                };
        String[] result = fc.findLargestNetwork(people);
        System.out.println(Arrays.toString(result));

        // Case 2: [friend1, friend2]
        people = new String[][]{
                        {"friend1", "friend2"},
                        {"friend3", "friend4"}
                };
        result = fc.findLargestNetwork(people);
        System.out.println(Arrays.toString(result));

        // Case 3: [friend1, friend2, friend3]
        people = new String[][]{
                        {"friend1", "friend2"},
                        {"friend2", "friend3"}
                };
        result = fc.findLargestNetwork(people);
        System.out.println(Arrays.toString(result));

        // Case 4: [friend1, friend2, friend3]
        people = new String[][]{
                        {"friend1", "friend2"},
                        {"friend3", "friend2"}
                };
        result = fc.findLargestNetwork(people);
        System.out.println(Arrays.toString(result));

        // Case 5: [friend1, friend2, friend3, friend4]
        people = new String[][]{
                        {"friend1", "friend2"},
                        {"friend3", "friend4"},
                        {"friend1", "friend3"}
                };
        result = fc.findLargestNetwork(people);
        System.out.println(Arrays.toString(result));
     }
}

