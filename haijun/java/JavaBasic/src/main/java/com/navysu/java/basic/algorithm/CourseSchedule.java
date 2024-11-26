package com.navysu.java.basic.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leetcode 207 Course Schedule
 * https://leetcode.com/problems/course-schedule/
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses - 1. You are given an array prerequisites where prerequisites[i] =
 * [ai, bi] indicates that you must take course bi first if you want to take
 * course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to
 * first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you
 * should also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 */

public class CourseSchedule {

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        System.out.println(cs.canFinish(2, new int[][] { { 1, 0 } }));
        System.out.println(cs.canFinish(2, new int[][] { { 1, 0 }, { 0, 1 } }));
        System.out.println(cs.canFinish(3, new int[][] { { 1, 0 }, { 2, 0 }, { 0, 1 } }));
        System.out.println(cs.canFinish(4, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 }, { 0, 3 } }));
    }

    /**
     * Determines if it is possible to finish all courses given the prerequisites.
     *
     * The function uses a depth-first search (DFS) approach to detect cycles in
     * the course prerequisite graph. If a cycle is detected, it is not possible
     * to complete all courses.
     *
     * @param numCourses    the total number of courses
     * @param prerequisites a list of prerequisite pairs, where each pair
     *                      [a, b] indicates that course b must be taken before
     *                      course a
     * @return true if it is possible to finish all courses, false otherwise
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> reqMap = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < prerequisites.length; i++) {
            reqMap.computeIfAbsent(prerequisites[i][0], k -> new ArrayList<>()).add(prerequisites[i][1]);
        }
        int[] status = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!canFinishCourse(reqMap, status, i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Recursively checks if the course can be finished given the prerequisites.
     *
     * @param reqMap the map of prerequisites
     * @param status the status array of courses
     * @param course the course to check
     * @return true if the course can be finished, false otherwise
     */
    private boolean canFinishCourse(Map<Integer, List<Integer>> reqMap, int[] status, int course) {
        if (status[course] == 1) { // used by a course
            return false;
        }
        if (status[course] == 2) { // course finished
            return true;
        }
        status[course] = 1; // set status to used by a course
        for (int next : reqMap.getOrDefault(course, new ArrayList<>())) {
            if (!canFinishCourse(reqMap, status, next)) {
                return false;
            }
        }
        status[course] = 2; // set status to finished so any other course can use it
        return true;
    }

}
