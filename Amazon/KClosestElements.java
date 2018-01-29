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

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<Integer>();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k,
                    new Comparator<Integer>(){
                        public int compare(Integer a, Integer b) {
                            int absA = a >= 0? a : (~a + 1);
                            int absB = b >= 0? b : (~b + 1);
                            return -1 * Integer.compare(absA, absB);
                        }
                    }
                );

        for (int i = 0; i < arr.length; ++i) {
            if (queue.size() < k) {
                queue.offer(x - arr[i]);
            } else {
                int peek = queue.peek();
                peek = peek >= 0 ? peek : ~peek + 1;
                int value = x - arr[i];
                value = value >= 0 ? value : ~value + 1;
                if (peek > value) {
                    queue.poll();
                    queue.offer(x - arr[i]);
                }
            }
        }

        while (!queue.isEmpty()) {
            ans.add(0, x - queue.poll());
        }
        Collections.sort(ans);
        return ans;
    }

    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<Integer>();
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(k,
                (int[] a, int[] b) -> -1 * Integer.compare(a[1], b[1])
                );
        int[][] dist = new int[arr.length][2];
        for (int i = 0; i < arr.length; ++i) {
            dist[i][0] = arr[i];
            dist[i][1] = Math.abs(x - arr[i]);
            if (queue.size() < k) {
                queue.offer(dist[i]);
            } else {
                int [] peek = queue.peek();
                if (peek[1] > dist[i][1]) {
                    queue.poll();
                    queue.offer(dist[i]);
                }
            }
        }
        while (!queue.isEmpty()) {
            ans.add(queue.poll()[0]);
        }
        Collections.sort(ans);
        return ans;
    }


     public static void main(String []args){
        int[] arr = new int[]{0,1,2,4,5,7,10};
        test(arr, 4, -1);
        test(arr, 4, 3);
        test(arr, 4, 7);
     }

     public static void test(int[] arr, int k, int x) {
         KClosestElements kcle = new KClosestElements();
         List<Integer> verification = kcle.findClosestElements(arr, k, x);
         List<Integer> answer = kcle.findClosestElements3(arr, k, x);
         System.out.println("Expected: " + verification + ", your answer: " + answer);
         System.out.println(verification.toString().equals(answer.toString())? "Accept" : "Wrong answer");
     }
}
