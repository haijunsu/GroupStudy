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
* ==========
* ITEM ASSOCIATION。
* input
* [itemA, itemB], 表示物品 A 和物品 B 相互关联。
* [itemB, itemC], 表示物品 B 和物品 C 相互关联。
* 如果物品相互关联，就组成一个组。最后要求找出物品最多的那个组
*
*
* ==========
* 给出一串 Pair, 每个ｐａｉｒ说明两个人互为朋友，[A,B] 说明Ａ和Ｂ是朋友，[Ｃ,B] 说明Ｃ和Ｂ是朋
* 友，｛Ｄ，Ｅ｝说明Ｅ和Ｄ是朋友。
* 找出人数最大的朋友圈，如果两个朋友圈人数相等，返回有着字典顺序最小朋友的那个圈。
*
*
* ==========
* 类似 movie，改成了 books，关系更简单，
* 【book1， book2】
* 【book3， book4】
* 【book5， book6】
* 找出最大的 network 是什么
*/
import java.util.*;

public class FriendCycle {

    // Union Find templete https://algs4.cs.princeton.edu/15uf/UF.java.html
    // Reference: https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf

    Map<String, String> parentMap = new HashMap<String, String>();
    Map<String, Integer> rankMap = new HashMap<String, Integer>();

    void makeSet(String[][] people) {
        for (String[] friends : people) {
            for (String person : friends) {
                parentMap.put(person, person);
                rankMap.put(person, 0);
            }
        }
    }

    String find(String person) {
        while(!person.equals(parentMap.get(person))) {
            person = parentMap.get(parentMap.get(person));
        }
        return person;
    }

    void union(String person1, String person2) {
        String parent1 = find(person1);
        String parent2 = find(person2);
        if (parent1.equals(parent2)) return;
        if (rankMap.get(parent1) > rankMap.get(parent2)) {
            parentMap.put(parent2, parent1);
        } else {
            parentMap.put(parent1, parent2);
            if (rankMap.get(parent1) == rankMap.get(parent2)) {
                rankMap.put(parent2, rankMap.get(parent2) + 1);
            }
        }
    }

    void flatTree() {
        Set<String> keys = parentMap.keySet();
        for (String key : keys) {
            parentMap.put(key, find(key));
        }
    }

    String[] findLargest() {
        List<Map.Entry<String, String>> allList = new ArrayList<Map.Entry<String, String>>();
        flatTree();
        allList.addAll(parentMap.entrySet());
        Collections.sort(allList,
                    (Map.Entry<String, String> a, Map.Entry<String, String> b) -> a.getValue().compareTo(b.getValue())
                    );
        System.out.println(allList);
        List<String> names = new ArrayList<String>();
        List<String> largeNames = new ArrayList<String>();
        String preName = null;
        for (Map.Entry<String, String> entry : allList) {
            if (preName != null && !preName.equals(entry.getValue())) {
                if (largeNames.size() < names.size()) {
                    largeNames = names;
                }
                names = new ArrayList<String>();
            }
            names.add(entry.getKey());
            preName = entry.getValue();
        }
        if (largeNames.size() < names.size()) {
            largeNames = names;
        }
        Collections.sort(largeNames);
        return largeNames.toArray(new String[0]);
    }

    public String[] findLargestNetwork(String[][] people) {
        makeSet(people);
        for (String[] friends : people) {
            union(friends[0], friends[1]);
        }
        return findLargest();
     }

     public static void main(String []args){
        // Case 1: [friend3, friend4, friend5]
        String[][] people = new String[][]{
                        {"friend1", "friend2"},
                        {"friend3", "friend4"},
                        {"friend4", "friend5"}
                };
        test(people, "[friend3, friend4, friend5]");

        // Case 2: [friend1, friend2]
        people = new String[][]{
                        {"friend1", "friend2"},
                        {"friend3", "friend4"}
                };
        test(people, "[friend1, friend2]");

        // Case 3: [friend1, friend2, friend3]
        people = new String[][]{
                        {"friend1", "friend2"},
                        {"friend2", "friend3"}
                };
        test(people, "[friend1, friend2, friend3]");

        // Case 4: [friend1, friend2, friend3]
        people = new String[][]{
                        {"friend1", "friend2"},
                        {"friend3", "friend2"}
                };
        test(people, "[friend1, friend2, friend3]");

        // Case 5: [friend1, friend2, friend3, friend4]
        people = new String[][]{
                        {"friend1", "friend2"},
                        {"friend3", "friend4"},
                        {"friend1", "friend3"}
                };
        test(people, "[friend1, friend2, friend3, friend4]");
     }

     private static void test(String[][] people, String answer) {
        FriendCycle fc = new FriendCycle();
        String result = Arrays.toString(fc.findLargestNetwork(people));
        System.out.println("Expect: " + answer + ", your answer: " + result);
        if (result.equals(answer)) {
            System.out.println("Accept");
        } else {
            System.out.println("Wrong Answer");
        }

     }
}

