public class Solution {
    /**
     * inspired by:
     *      http://www.lifeincode.net/programming/leetcode-median-of-two-sorted-arrays-java/
     * 
     * Solution #2
     * O(log(m + n))
     * O(?) //each time, eliminate either half of A OR half of B
     * 472 ms
     * 
     * Note:
     *      This is hard one.
     *      Consider this way, after determine the relation of A[midA] and B[midB], we need to determin the 
     *          relation between k and (n / 2 + m / 2).
     *      At this moment, we need to find out the section (either the 1st half of A(or B) or the 2nd half of A(or B) )
     *          that MUST be DROPED, i.e. the k-th number must not in that section, then search again.
     */
    public double findMedianSortedArrays(int A[], int B[]) {
    	int lengthA = A.length;
    	int lengthB = B.length;

    	if( (lengthA + lengthB) % 2 == 0){
    		double r1 = findMedianSortedArrays(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB) / 2);
    		double r2 = findMedianSortedArrays(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB) / 2 + 1);
    		return (r1 + r2) / 2.0;
    	}else{
    		return findMedianSortedArrays(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB + 1) / 2);
    	}
    }

    //indexical start is inclusive;
    //indexical end is exclusive.
    public double findMedianSortedArrays(int[] A, int startA, int endA, int[] B, int startB, int endB, int k){
    	//length of the range
    	int n = endA - startA;
    	int m = endB - startB;

    	if(n <= 0 && m <= 0) return 0;
    	if(n <= 0 && m > 0)
    		return B[startB + k - 1];
    	if(n > 0 && m <= 0)
    		return A[startA + k - 1];

    	int midA = (startA + endA) / 2;//indexical mid
    	int midB = (startB + endB) / 2;//indexical mid

    	if(A[midA] <= B[midB]){
    		if (k - 1 <= (n / 2 + m / 2)) //k-th element must NOT be in 2nd half of B. search in entire A and 1st half of B.
    			return findMedianSortedArrays(A, startA, endA, B, startB, midB, k);
    		else
    			return findMedianSortedArrays(A, midA + 1, endA, B, startB, endB, k - n / 2 - 1);
    	}else{
    		if (k - 1 <= (n / 2 + m / 2))
    			return findMedianSortedArrays(A, startA, midA, B, startB, endB, k);
    		else
    			return findMedianSortedArrays(A, startA, endA, B, midB + 1, endB, k - m / 2 - 1);
    	}
    }
    
    
    
    
    
    
    
    
    
    /**
     * Solution #1
     * O(m + n)
     * O(m + n)
     * 540 ms
     */
    // public double findMedianSortedArrays(int A[], int B[]) {
        
    //     //Suppose both A and B are legal, and non-empty.
    //     int a = A.length, b = B.length, indexA = 0, indexB = 0, indexC = 0;
    //     double midDouble = (a + b) / 2.0;
        
    //     if((a+b) % 2 == 0) midDouble -= 1;
    //     else midDouble -= 0.5;
        
    //     int mid = (int)midDouble;
        
    //     if(a == 0 && b == 0) return 0;
        
    //     if(a == 0 && b != 0) {
    //     	if(b % 2 == 0) return (B[mid] + B[mid + 1] ) / 2.0;
    //     	else
    //     		return B[mid];
    //     }
    //     if(a != 0 && b == 0) {
    //     	if(a % 2 == 0) return (A[mid] + A[mid + 1] ) / 2.0;
    //     	else
    //     		return A[mid];
    //     }
        
    //     System.out.println("mid: " + mid);
        
    //     //a != 0 && b != 0
    //     int[] C = new int[a + b];
        
    //     while(indexC <= mid + 1){
    //         if(indexA <= a - 1 && indexB <= b - 1 && A[indexA] < B[indexB]){
    //             C[indexC] = A[indexA];
    //             indexA++;
    //         }else if(indexA <= a - 1 && indexB <= b - 1 && A[indexA] >= B[indexB]){
    //             C[indexC] = B[indexB];
    //             indexB++;
    //         }else if(indexA >= a && indexB <= b - 1) {
    //         	C[indexC] = B[indexB];
    //         	indexB++;
    //         }else if(indexB >= b && indexA <= a - 1) {
    //         	C[indexC] = A[indexA];
    //         	indexA++;
    //         }
    //         indexC++;
    //     }
    //     //indexC now is mid + 2;
    //     System.out.println(indexC);
    //     System.out.println(Arrays.toString(C));
        
        
    //     if((a+b) % 2 == 0)
    //         return (C[indexC - 2] + C[indexC - 1] ) / 2.0;
    //     return C[indexC - 2];
    // }
}