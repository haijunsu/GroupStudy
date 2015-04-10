/**
 * Round 2 - 4/2
 * Note:
 *      1. Remember that rotated Array is sorted either in left side or right side.
 *              so check which side is sorted first.
 *      2. if left side is sorted, write 
 *              A[left] <= target && target < A[mid]    //Take notice to "<="
 *         right side sorted, write
 *              A[mid] < target && target <= A[right]   //Take notice to "<="
 */
public class Solution {
    public int search(int[] A, int target) {//Apr 2 - 10:18
        if(A == null || A.length == 0)
            return -1;
        
        int left = 0, right = A.length - 1;
        int mid;
        while(left + 1 < right){
            mid = left + (right - left ) / 2;
            if(A[mid] == target)
                return mid;
            if(A[left] < A[mid]){//left is sorted
                if(A[left] <= target && target < A[mid])
                    right = mid;
                else
                    left = mid;
            }else{//right is sorted
                if(A[mid] < target && target <= A[right])
                    left = mid;
                else
                    right = mid;
            }
        }
        return (A[left] == target)? left : ((A[right] == target)? right : -1);
    }
}









/**
 * O(log n)
 * O(1)
 * 238 ms
 * 
 * Note:
 *      Nice one for Binary Search.
 *      The key point for solution is that
 *          Whatever how this array rotate, there must at least exist a sorted part either left or right or both!!
 *
 *      要解决这道题，需要明确rotated sorted array的特性，那么就是至少有一侧是排好序的（无论pivot在哪，自己画看看）
 *
 *      Caution the marked part which is easy to get wrong!!
 *
 * Reference:
 *    http://www.cnblogs.com/springfor/p/3858140.html
 */
public class Solution {
    public int search(int[] A, int target) {
        if(A.length == 0 || A == null) return -1;

    int left = 0, right = A.length - 1;
    int mid = left + (right - left ) / 2;

    while(left <= right){
      if(A[mid] == target) return mid;
      else if(A[mid] < target){
        if(A[left] < A[mid])//left is sorted, find within right side.
          left = mid + 1;
        //then right is sorted.
//        else if(A[right] >= target)
//          left = mid + 1;
//        else
//          right = mid - 1;
        /**
         * Pick either above or below.
         * CAUTION!!:
         *    Here is so easy to get wrong. you need to consider all the cases.
         */
        else if(A[right] < target)//target is larger than the largest one on right, then it must in left side. ===========Easy to get wrong here, see the previous submission.
          right = mid - 1;
        else//right is sorted, A[right] >= target, target must be in right side.
          left = mid + 1;

      }
      else if(A[mid] > target){
        if(A[right] > A[mid])//right is sorted
          right = mid - 1;//find left side
        //then left is sorted.
        
//        else if(A[left] <= target)//left is sorted
//          right = mid - 1;
//        else//left is sorted, and A[left] >= target, target must in right side.
//          left = mid + 1;
        /**
         * Same here.
         * Pick either above or below.
         */
        else if(A[left] > target)//left is sorted
          left = mid + 1;
        else//left is sorted, and A[left] >= target, target must in right side.
          right = mid - 1;

      }
      mid = left + (right - left ) / 2;
    }

    return -1;
    }
}