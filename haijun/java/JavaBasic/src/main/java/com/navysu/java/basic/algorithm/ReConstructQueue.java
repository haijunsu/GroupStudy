package com.navysu.java.basic.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode 406 Queue Reconstruction by Height
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 *
 * You are given an array of people, people, which are the attributes of some
 * people in a queue (not necessarily in order). Each people[i] = [hi, ki]
 * represents the ith person of height hi with exactly ki other people in front
 * who have a height greater than or equal to hi.
 *
 * Reconstruct and return the queue that is represented by the input array
 * people. The returned queue should be formatted as an array queue, where
 * queue[j] = [hj, kj] is the attributes of the jth person in the queue
 * (queue[0] is the person at the front of the queue).
 *
 *
 *
 * Example 1:
 *
 * Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * Explanation:
 * Person 0 has height 5 with no other people taller or the same height in
 * front.
 * Person 1 has height 7 with no other people taller or the same height in
 * front.
 * Person 2 has height 5 with two persons taller or the same height in front,
 * which is person 0 and 1.
 * Person 3 has height 6 with one person taller or the same height in front,
 * which is person 1.
 * Person 4 has height 4 with four people taller or the same height in front,
 * which are people 0, 1, 2, and 3.
 * Person 5 has height 7 with one person taller or the same height in front,
 * which is person 1.
 * Hence [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] is the reconstructed queue.
 * Example 2:
 *
 * Input: people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * Output: [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 *
 *
 * Constraints:
 *
 * 1 <= people.length <= 2000
 * 0 <= hi <= 106
 * 0 <= ki < people.length
 * It is guaranteed that the queue can be reconstructed.
 */

public class ReConstructQueue {

    public static void main(String[] args) {
        int[][] people = { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } };
        System.out.println(Arrays.deepToString(reconstructQueue(people)));

        people = new int[][] { { 6, 0 }, { 5, 0 }, { 4, 0 }, { 3, 2 }, { 2, 2 }, { 1, 4 } };
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }

    /**
     * Reconstructs a queue given a list of people where each person is a list
     * [h, k] where h is the height of the person and k is the number of people
     * in front of the person with heights greater than or equal to h.
     *
     * @param people
     *               the list of people, where each person is a list [h, k]
     * @return the reconstructed queue
     */
    public static int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        List<int[]> resList = new ArrayList<int[]>();
        for (int i = 0; i < people.length; i++) {
            resList.add(people[i][1], people[i]);
        }
        /*
         * int[][] result = new int[people.length][2];
         * for (int i = 0; i < people.length; i++) {
         * result[i] = resList.get(i);
         * }
         * return result;
         */
        return resList.toArray(new int[resList.size()][2]);
    }

}
