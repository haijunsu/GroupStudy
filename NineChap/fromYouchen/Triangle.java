public class Solution {
    /**
     * O(n)
     * O(n)
     * 268 ms
     * 
     * Try to use 2 DP arrays if one array is not enough or will messed up the procedure.
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.get(0) == null) return 0;

		ArrayList<Integer> al = new ArrayList<Integer>();
		ArrayList<Integer> al0 = new ArrayList<Integer>();

		al0.add( triangle.get(0).get(0) );
		al.add( triangle.get(0).get(0) );

//		System.out.println(al.toString());

		for(int i = 1; i < triangle.size(); i++){
			List<Integer> cur = triangle.get(i);
			al.add(0);

			for(int j = 1; j < cur.size() - 1; j++){
				al.set(j, Math.min(  al0.get(j - 1) + cur.get(j),   al0.get(j) + cur.get(j)   ));
			}

			//set 1st and last index
			al.set(0, al0.get(0) + cur.get(0));
			al.set(al.size() - 1, al0.get(al.size() - 2) + cur.get(cur.size() - 1));

			//append al0 to the size of al.
			al0.add(0);
			for(int k = 0; k < al.size(); k++)
				al0.set(k, al.get(k));
			
//			System.out.println(al.toString());
		}

		int min = al.get(0);
		for(int i = 1; i < al.size(); i++){
			if(al.get(i) < min)
				min = al.get(i);
		}

		return min;
		
		
		
		
		
        /**
         * 2nd Trial - fail
         * the modification of the list al is messed up.
         */
        // if(triangle == null || triangle.get(0) == null) return 0;
        
        // ArrayList<Integer> al = new ArrayList<Integer>();
        // al.add( triangle.get(0).get(0) );
        // // int min = al.get(0);//keep a min all the time.
        
        // for(int i = 1; i < triangle.size(); i++){
        //     List<Integer> cur = triangle.get(i);
        //     al.add(0);
            
        //     al.set(al.size() - 1, al.get(al.size() - 2) + cur.get(cur.size() - 1));
            
        //     //from index 1 to the index prior to the last index.
        //     for(int j = 1; j < cur.size() - 1; j++){
                
        //         al.set(j, Math.min(  al.get(j - 1) + cur.get(j),   al.get(j) + cur.get(j)   ) );
                
        //     }
            
        //     //set 1st and last index
        //     al.set(0, al.get(0) + cur.get(0));
        //     // al.set(al.size() - 1, al.get(al.size() - 2) + cur.get(cur.size() - 1));
        // }
        
        // int min = al.get(0);
        // for(int i = 1; i < al.size(); i++){
        //     if(al.get(i) < min)
        //         min = al.get(i);
        // }
        
        // return min;
        
        
        
        /**
         * 1st trial - Fail
         * This solution is going based on the min value that the path can touch,
         *      Does not consider the entire triangle case.
         */
        // if(triangle == null || triangle.get(0) == null) return 0;
        
        // int sum = triangle.get(0).get(0), index = 0;
        // for(int i = 1; i < triangle.size(); i++){
        //     List<Integer> cur = triangle.get(i);
            
        //     if(cur.get(index) < cur.get(index + 1) ){
        //         sum += cur.get(index);
        //     }
        //     else {
        //         sum += cur.get(index + 1);
        //         index = index + 1;
        //     }
        // }
        // return sum;
    }
}