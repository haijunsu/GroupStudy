/*
* LeetCode: 658. Find K Closest Elements
* https://leetcode.com/problems/find-k-closest-elements/description/
* Amazon OA: amazon warehouse。。。其实就是给你 x,y 然后算 x,y 到原点的距离，输出最小的几个，java 应该
* priorityqueue 就够了，我用的 python，也还可以
* 不确定是不是这道 有人提了一下 K ClosestElements
* Need do more search about Amazon Warehouse.
*/
import java.util.*;
public class KClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null) return null;
        List<Integer> results = new ArrayList<Integer>();
        int start = 0;
        int len = arr.length;
        if (x > arr[len - 1]) {
            start = len - k;
        } else if (x >= arr[0] && x <= arr[len -1]) {
            int low = 0;
            int high = len;
            // search x position
            while (low < high) {
                int mid = (low + high) / 2;
                if (x > arr[mid]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            int end = Math.min(len - 1, low + k - 1);
            start = Math.max(0, low - k - 1);
            while (end - start + 1> k) {
                if (x - arr[start] > arr[end] - x) {
                    ++start;
                } else {
                    --end;
                }
            }
        }

        for (int i = start; i < start + k; ++i) {
            results.add(arr[i]);
        }
        return results;
    }


     public static void main(String []args){
        KClosestElements kcle = new KClosestElements();
        int[] arr = new int[]{1,2,3,4,5};
        List<Integer> results = kcle.findClosestElements(arr, 4, -1);
        System.out.println(results);
        results = kcle.findClosestElements(arr, 4, 3);
        System.out.println(results);
        results = kcle.findClosestElements(arr, 4, 7);
        System.out.println(results);
     }
}
