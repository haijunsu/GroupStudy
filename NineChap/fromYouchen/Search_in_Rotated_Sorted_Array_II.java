/**
 * Round 2 - 4/2
 * O(n)
 * O(1)
 * 233 ms
 * 
 * Note:
 *      By using the template, check A[mid] <, > target first
 *      then, if 
 *          target < A[mid], first check if right-side is sorted, then A[mid] == A[right];
 *          target > A[mid], first check if left-side is sorted, then A[mid] == A[left];
 */
public class Solution {
    public boolean search(int[] A, int target) {
        if(A == null || A.length == 0)
            return false;
        
        int left = 0, right = A.length - 1, mid;
        while(left + 1 < right){
            mid = left + (right - left) / 2;
            if(A[mid] == target)
                return true;
            
            if(target < A[mid]){
                //check if right-side is sorted
                if(A[mid] < A[right])
                    right = mid - 1;
                else if(A[mid] == A[right])//cannot say if right-side is sorted, move "right" left.
                    right--;
                
                //if program goes here, saying that left side must be sorted.
                else{
                    if(target < A[left])//target must be in right-side
                        left = mid + 1;
                    else//A[left] <= target
                        right = mid - 1;
                }
            }else{//target > A[mid]
                //check if left side is sorted
                if(A[mid] > A[left])
                    left = mid + 1;
                else if(A[mid] == A[left])//cannot say if left-side is sorted, move "left" right.
                    left++;
                
                //if program goes here, saying that right side is sorted.
                else{
                    if(A[right] < target)//target must be in left-side
                        right = mid - 1;
                    else//target <= A[right]
                        left = mid + 1;
                }
            }
        }
        return (A[left] == target || A[right] == target)? true : false;
    }
}










/**
 * Round 2 - 4/2
 * This is not a good algorithm since the worst-case is O(n), you cannot
 *      suppose all the cases are O(n), there must exist the case between O(log n) and O(n).
 *
 * O(n)
 * O(1)
 * 276 ms
 * 
 * Note:
 *      Since the time complexity is O(n)
 *      It's the same if we use brute force (O(n)) to find the element.
 */
public class Solution {
    public boolean search(int[] A, int target) {
        if(A == null || A.length == 0)
            return false;
        
        for(int i = 0; i < A.length; i++)
            if(A[i] == target)
                return true;
        return false;
    }
}










/**
 * O(n)
 * O(1)
 * 264 ms
 * 
 * Note:
 *      Pretty like the related one.
 *      But this one, you need to deal with the duplicate.
 *      You cannot sure that left or right is sorted or not.
 *          But sometime, the A[mid] might equal to A[left] or A[right].
 *      You need to tackle the case of A[mid] == A[left] and A[mid] == A[right].
 *      
 *      Time Complexity:
 *          Time Complexity is increased since the duplicate condition.
 *          From O(log n) to O(n). consider the case of {1, 1, 1, 1, 1} target = 2.
 *              In this case, by using the algorithm of below, you need to iterate all
 *              the elements inside the array.
 * 
 * Reference:
 *      http://www.cnblogs.com/springfor/p/3859525.html
 */
public class Solution {
    public boolean search(int[] A, int target) {
        if(A.length == 0 || A == null) return false;
        
        int left = 0, right = A.length - 1;
        int mid = left + (right - left) / 2;
        
        while(left <= right){
            if(A[mid] == target) return true;
            if(target < A[mid]){
                if(A[mid] < A[right]) //right is sorted, and target must in left side.
                    right = mid - 1;
                else if(A[mid] == A[right])//cannot tell if right is sorted, move pointer right.
                    right--;
                //left side is sorted.
                else if(A[left] <= target)//target in left side
                    right = mid - 1;
                else//target in right side
                    left = mid + 1;
            }
            else{//i.e. target > A[mid]
                if(A[left] < A[mid]) //left is sorted, target must in right side
                    left = mid + 1;
                else if(A[left] == A[mid])//cannot tell if left is sorted, move pointer left.
                    left++;
                //right is sorted
                else if(A[right] >= target)
                    left = mid + 1;
                else//target must in left side
                    right = mid - 1;
            }
            mid = left + (right - left) / 2;
        }
        return false;
    }
}