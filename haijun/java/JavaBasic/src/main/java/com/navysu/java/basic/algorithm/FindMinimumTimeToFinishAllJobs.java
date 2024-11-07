package com.navysu.java.basic.algorithm;

import java.util.Arrays;

/**
 * Leetcode 1723 Find Minimum Time To Finish All Jobs
 * https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/
 *
 * You are given an integer array jobs, where jobs[i] is the amount of time it
 * takes to complete the ith job.
 *
 * There are k workers that you can assign jobs to. Each job should be assigned
 * to exactly one worker. The working time of a worker is the sum of the time it
 * takes to complete all jobs assigned to them. Your goal is to devise an
 * optimal assignment such that the maximum working time of any worker is
 * minimized.
 *
 * Return the minimum possible maximum working time of any assignment.
 *
 *
 *
 * Example 1:
 *
 * Input: jobs = [3,2,3], k = 3
 * Output: 3
 * Explanation: By assigning each person one job, the maximum time is 3.
 * Example 2:
 *
 * Input: jobs = [1,2,4,7,8], k = 2
 * Output: 11
 * Explanation: Assign the jobs the following way:
 * Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
 * Worker 2: 4, 7 (working time = 4 + 7 = 11)
 * The maximum working time is 11.
 */

public class FindMinimumTimeToFinishAllJobs {

    public static void main(String[] args) {
        int[] jobs = { 3, 2, 3 };
        int k = 3;
        System.out.println(minimumTimeRequired(jobs, k));
        jobs = new int[] { 1, 2, 4, 7, 8 };
        k = 2;
        System.out.println(minimumTimeRequired(jobs, k));
    }

    public static int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int[] workers = new int[k];
        int[] result = new int[] { Integer.MAX_VALUE };
        backtrack(jobs, workers, jobs.length - 1, result);
        return result[0];
    }

    private static void backtrack(int[] jobs, int[] workers, int index, int[] result) {
        if (index < 0) {
            result[0] = Math.min(result[0], Arrays.stream(workers).max().getAsInt());
            return;
        }
        for (int i = 0; i < workers.length; i++) {
            if (i > 0 && workers[i] == workers[i - 1]) {
                continue; // Skip duplicate workers in the same round
            }
            if (workers[i] + jobs[index] > result[0]) {
                continue; // Skip this worker because it will lead to jobs that exceed the current maximum
                          // working time. We want to minimize the maximum working time
            }
            workers[i] += jobs[index];
            backtrack(jobs, workers, index - 1, result);
            workers[i] -= jobs[index];
        }
    }

}
