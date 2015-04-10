public class Solution {
    /**
     * O(k + 1 + 2(1 + 2 + 3 + ... + k)) = O(k^2)
     * O(k)
     * 232 ms
     * Note:
     *      Really do not get the reason why using this algorithm, and
     *          where comes up this algorithm.
     *      REMEMBER THIS!
     */
    public List<Integer> getRow(int rowIndex) {//Mar 6 - 16:30 - 18:19 (1 h 50 m)
        ArrayList<Integer> al = new ArrayList<Integer>(rowIndex + 1);
        for(int i = 0; i <= rowIndex; i++)
            al.add(0);
            
        al.set(0, 1);
        
        for(int i = 1; i <= rowIndex; i++){
            al.set(i, 1);
            for(int j = i - 1; j >= 1; j--)
                al.set(j, al.get(j) + al.get(j - 1));
        }
        return al;
    }
    
    
    
    
    /**
     * Use Combination Formula to solve. FAIL.
     */
    // public List<Integer> getRow(int rowIndex) {//Mar 6 - 16:30
    //     //length = rowIndex + 1;
    //     ArrayList<Integer> al = new ArrayList<Integer>();
    //     for(int i = 0; i <= rowIndex; i++)
    //         al.add(Cnr(rowIndex, i));
    //     return al;
    // }
    // public int Cnr(int n, int r){
    //     if(r == 0 || r == n) return 1;
    //     return (int) ( factorial((long)n, (long)(n - r + 1)) / factorial((long)r, 1) );
    //     // return factorial(n, 1) / (factorial(n - r, 1) * factorial(r, 1));
    // }
    // public long factorial(long s, long t){//both inclusive
    //     if(s == 0) return 1;
    //     for(long i = s - 1; i >= t; i--)
    //         s = s * i;
    //     return s;
    // }
}