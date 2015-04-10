/**
 * Round 2 - 4/3 30 min
 * Note:
 *      This one is tricky for the condition statement in the middle.
 *      Feel it when you solve it again.
 *      
 *      The first if is the "cross AND" relationship, 
 *                write mid == 0 then  write mid == num.length - 1 || num[mid] > num[mid + 1]
 *                the later whole part is the AND condition for "if mid == 0";
 *
 *                then, look at the later whole part, and think if mid == num.length - 1, what
 *                     condition should be in counterpart?
 *
 *                Well if mid == lenght AND mid == 0, mid should be returned.
 *                then, if num[mid] > num[mid + 1], except mid == 0, what other condition should
 *                be met? Obviously it's
 *                    num[mid] > num[mid - 1] as well, num[mid] will be the local peak.
 * 
 */
public class Solution {
    public int findPeakElement(int[] num) {//23:05 - 23:10
        if(num == null || num.length == 0)
            return -1;
        
        int left = 0, right = num.length - 1, mid = left + (right - left ) / 2;
        while(left <= right){
            mid = left + (right - left ) / 2;
            if( (mid == 0 || num[mid] > num[mid - 1])
                && (mid == num.length - 1 || num[mid] > num[mid + 1])
                )
                return mid;
            
            /**
             *  If it's not so obvious that we can directly return mid, then we need to adjust the left and right.
             */

            // else if(mid >= 1 && num[mid] < num[mid - 1])
            //     right = mid - 1;
            // else
            //     left = mid + 1;
            /**
             * the above "else if ... else" OR the below "else if ... else"
             *      pick either, the program works.
             */
            else if(mid <= num.length - 2 && num[mid] < num[mid + 1])
                left = mid + 1;
            else
                right = mid - 1;
        }
        return mid;
    }
}









/**
 * O(log n)
 * O(1)
 * 245 ms
 * 
 * Note:
 *      The tricky part is the first if statement inside the while loop.
 *      It saying that if the num[mid] is greater than its neighbors. then it's the peak index.
 *      And you need to add the corner case for if the mid is the left or right boundry of the array.
 *
 * Reference:
 *      http://siddontang.gitbooks.io/leetcode-solution/content/array/find_peak_element.html
 */
public class Solution {
    public int findPeakElement(int[] num) {//Mar 8 - 00:35
        int len = num.length;
        if(len == 1) return 0;
        
        //len >= 2.
        int left = 0, right = len - 1, mid = left + (right - left) / 2;
        while(left <= right){
            if( (mid == 0 || num[mid] > num[mid - 1]) && (mid == len - 1 || num[mid] > num[mid + 1]) )
                return mid;
            else if(mid >= 1 && num[mid] < num[mid - 1])
                right = mid - 1;
            else
                left = mid + 1;
            mid = left + (right - left) / 2;
        }
        return mid;
        
        
        /**
         * 1st Trail. Fail
         */
        // int len = num.length;
        // // if(len == 0 || num == null) return -1;
        
        // // if(len == 1) return 0;
        // // if(len == 2) return (num[0] < num[1])? 1 : 0;
        
        // int left = 0, right = len - 1, mid = len / 2;//these are the indices
        // while(left + 2 < right){
        //     if(num[left] <= num[mid]){//increasing(same) 
        //         if(num[mid] <= num[right]){//increasing(same) -> pick right
        //             left = mid;
        //         }else{//decreasing -> pick right ?
        //             left = mid;
        //         }
        //     }
        //     else{//decreasing
        //         if(num[mid] <= num[right]){//increasing(same) -> pick right
        //             left = mid;
        //         }else{//decreasing -> pick left
        //             right = mid;
        //         }
        //     }
        //     mid = left + (right - left) / 2;
        // }
        // return mid;
    }
}