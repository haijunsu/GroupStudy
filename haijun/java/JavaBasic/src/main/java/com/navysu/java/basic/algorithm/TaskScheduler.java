package com.navysu.java.basic.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode leastInterval
 * https://leetcode.com/problems/task-scheduler/
 * You are given an array of CPU tasks, each labeled with a letter from A to Z,
 * and a number n. Each CPU interval can be idle or allow the completion of one
 * task. Tasks can be completed in any order, but there's a constraint: there
 * has to be a gap of at least n intervals between two tasks with the same
 * label.
 *
 * Return the minimum number of CPU intervals required to complete all tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 *
 * Output: 8
 *
 * Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A ->
 * B.
 *
 * After completing task A, you must wait two intervals before doing A again.
 * The same applies to task B. In the 3rd interval, neither A nor B can be done,
 * so you idle. By the 4th interval, you can do A again as 2 intervals have
 * passed.
 *
 * Example 2:
 *
 * Input: tasks = ["A","C","A","B","D","B"], n = 1
 *
 * Output: 6
 *
 * Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
 *
 * With a cooling interval of 1, you can repeat a task after just one other
 * task.
 *
 * Example 3:
 *
 * Input: tasks = ["A","A","A", "B","B","B"], n = 3
 *
 * Output: 10
 *
 * Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle
 * -> idle -> A -> B.
 *
 * There are only two types of tasks, A and B, which need to be separated by 3
 * intervals. This leads to idling twice between repetitions of these tasks.
 *
 *
 */

public class TaskScheduler {

    public static void main(String[] args) {
        char[] jobs = { 'A', 'A', 'A', 'B', 'B', 'B' };
        int k = 2;
        System.out.println(leastInterval(jobs, k));
        jobs = new char[] { 'A', 'C', 'A', 'B', 'D', 'B' };
        k = 1;
        System.out.println(leastInterval(jobs, k));
        jobs = new char[] { 'A', 'A', 'A', 'B', 'B', 'B' };
        k = 3;
        System.out.println(leastInterval(jobs, k));
    }

    public static int leastInterval(char[] jobs, int k) {
        // count the number of jobs for each job and find the maximum number of jobs
        // (frequency)
        // the max frequency is the period of the job
        Map<Character, Integer> map = new HashMap<>();
        int maxCount = 0;
        for (char job : jobs) {
            map.put(job, map.getOrDefault(job, 0) + 1);
            maxCount = Math.max(maxCount, map.get(job));
        }

        System.out.println("maxCount: " + maxCount);

        // count how many jobs have the same max count
        int maxJobs = 0;
        for (int count : map.values()) {
            if (count == maxCount)
                maxJobs++;
        }

        System.out.println("maxJobs: " + maxJobs);

        // Calculate the total periods (intervals) needed to arrange jobs with the max
        // count (frequency)
        int periodCount = maxCount - 1;
        System.out.println("periodCount: " + periodCount);

        // Calculate the empty slots. A job of maxJobs can fill into a slot. So the gap
        // K needs to be reduced. The empty slots will be filled with other jobs that do
        // not have the max frequency
        int emptySlots = periodCount * (k - (maxJobs - 1));
        System.out.println("emptySlots: " + emptySlots);

        // Calculate the other jobs that do not have the max frequency
        int otherJobs = jobs.length - maxCount * maxJobs;
        System.out.println("otherJobs: " + otherJobs);

        // Calculate the idle slots if there are not enough other jobs to fill the
        // slots.
        int idleSolts = Math.max(0, emptySlots - otherJobs);
        System.out.println("idleSolts: " + idleSolts);

        return jobs.length + idleSolts;
    }

}
