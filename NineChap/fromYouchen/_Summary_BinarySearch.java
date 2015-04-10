/**
 * @author Youchen
 * 
 * This one is to compare the two BS template
 * 		T1. left + 1 < right	if(A[mid] < t) left = mid; 		else right = mid;
 * 		T2. left <= right		if(A[mid] < t) left = mid + 1; 	else right = mid - 1; 
 *
 *
 * For list
 *  	  		   0, 1, 2, 3, 4, 5, 6
 *		int[] A = {1, 3, 5, 5, 7, 9, 11};
 * 
 * After while loop, the left and right will be:
 * If the target IS in the list: (target = 5)
 * 		T1:
 * 			A[mid] < t: 	1, 2
 * 			A[mid] <= t:	3, 4
 * 	
 * 		T2:
 * 			A[mid] < t: 	2, 1
 * 			A[mid] <= t:	4, 3
 * 				
 * 
 * If the target IS NOT in the list: (target = 6)
 * 		T1:
 * 			A[mid] < t: 	3, 4
 * 			A[mid] <= t:	3, 4
 * 	
 * 		T2:
 * 			A[mid] < t: 	4, 3
 * 			A[mid] <= t:	4, 3
 * 				
 */
public class Summary_BinarySearch {
	public static void main(String[] args) {
//		   		   0, 1, 2, 3, 4
		int[] A = {1, 3, 5, 5, 7, 9, 11};
		//		   0, 1, 2, 3, 4, 5, 6
//		int[] A = {1, 7, 7, 7, 7, 7, 9};
		
		int t = 6;
		System.out.println(BS(A, t));
		System.out.println(BS2(A, t));
	}
	/**
	 * 
	 * @param A
	 * @param t
	 * @return the index of A if t exist, return -1 otherwise;
	 */
	public static int BS(int[] A, int t) {
		if(A == null || A.length == 0)
            return -1;
        
        int start = 0, end = A.length - 1;
        int mid;
        
        while(start + 1 < end){
            mid = start + (end - start) / 2;
            if(A[mid] < t)
                start = mid;
            else
                end = mid; 
        }
        System.out.println("BS: " + start + " " + end);
        if(A[start] == t)
        	return start;
        if(A[end] == t)
        	return end;
        return -1;
	}

	
	public static int BS2(int[] A, int t) {
		if(A == null || A.length == 0)
            return -1;
        
        int start = 0, end = A.length - 1;
        int mid;
        
        while(start <= end){
            mid = start + (end - start) / 2;
            if(A[mid] < t)
                start = mid + 1;
            else
                end = mid - 1; 
        }
        System.out.println("\n\nBS2: " + start + " " + end);
        if(A[start] == t)
        	return start;
        if(A[end] == t)
        	return end;
        return -1;
	}
}
