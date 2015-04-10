/**
 * O(2^n) why?
 * O(n) why?
 * 204 ms
 * 
 * Note:
 *      This is the template for Permutation problems.
 *      Better remember this and be fast.
 * Reference:
 *      http://www.cnblogs.com/yuzhangcmu/p/4211815.html
 */
public class Solution {
    // public List<List<Integer>> subsets(int[] S) {
    //     List<List<Integer>> res = new ArrayList<List<Integer>>();
    //     if(S == null || S.length == 0)
    //         return res;
        
    //     Arrays.sort(S);
    //     helper(res, new ArrayList<Integer>(), S, 0);
    //     return res;
    // }
    // public void helper(List<List<Integer>> res, List<Integer> list, int[] num, int pos){
    //     res.add(new ArrayList<Integer>(list));//need to build a new List<Integer> based on list. but not "list" itself.
    //     for(int i = pos; i < num.length; i++){
    //         list.add(num[i]);
    //         helper(res, list, num, i + 1);
    //         list.remove(list.size() - 1);
    //     }
    // }
    
    

    
    
    /**
     * Another one, by the principle of add one.
     * O(n^2)
     * O(n)
     * 270 ms
     * 
     * Note: easy to understand and implement.
     */
     public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        if(S == null || S.length == 0)
            return ans;
            
        Arrays.sort(S);
        ans.add(new ArrayList<Integer>());
        
        for(int i = 0; i < S.length; i++){
            int curAnsSize = ans.size();
            for(int j = 0; j < curAnsSize; j++){
                ArrayList<Integer> cur = new ArrayList<Integer>(ans.get(j));
                cur.add(S[i]);
                ans.add(cur);
            }
        }
        return ans;
     }
}