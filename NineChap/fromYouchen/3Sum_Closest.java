/**
 * O(nlogn + n^2) = O(n^2)
 * O(1)
 * 236 ms
 * 
 * Note:
 *      Consider carefully
 */
public class Solution {
    public int threeSumClosest(int[] num, int target) {
        //You may assume that each input would have exactly one solution.
        //  Therefore, num must have 3 or more elements.
        Arrays.sort(num);
        
        int iterator = 0, left, right, sum, len = num.length;
        int minDistance = Integer.MAX_VALUE, minSum = -1, distance;
        
        while(iterator <= len - 3 ){
            while(iterator >= 1 && iterator <= len - 3 && num[iterator] == num[iterator - 1])
                iterator++;
            left = iterator + 1;
            right = len - 1;
            
            while(left < right){
                sum = num[iterator] + num[left] + num[right];
                distance = Math.abs(sum - target);
                
                if(distance == 0) {
                    return sum;
                }
                else if(distance < minDistance){
                    minDistance = distance;
                    minSum = sum;
                    
                    if(sum - target > 0){
                        right--;
                        while(right >= 2 &&(num[right] == num[right + 1]))
                            right--;
                    }
                    else if(sum - target < 0){
                        left++;
                        while(left <= len - 2 && (num[left] == num[left - 1]))
                            left++;
                    }
                    if(left >= right) break;
                    
                }
                else if(distance > minDistance && sum - target > 0){
                    right--;
                }
                else if(distance > minDistance && sum - target < 0){
                    left++;
                }
                else if(distance == minDistance){
                    if(sum - target > 0){
                        right--;
                        while(right >= 2 &&(num[right] == num[right + 1]))
                            right--;
                    }
                    else if(sum - target < 0){
                        left++;
                        while(left <= len - 2 && (num[left] == num[left - 1]))
                            left++;
                    }
                    if(left >= right) break;
                }//==
            }//while(left < right)
            iterator++;
        }//while
        return minSum;
    }
}