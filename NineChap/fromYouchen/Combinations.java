/**
 * O(2^n)
 * O(n)
 * 254 ms
 * 
 * Note:
 *      Similar to subset problem.
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {//20:45
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(n <= 0 || k <= 0)
            return res;
        
        helper(res, new ArrayList<Integer>(), n, k, 1);
        return res;
    }
    private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, int n, int k, int pos){
        if(list.size() == k)
            res.add(new ArrayList<Integer>(list));
            
        for(int i = pos; i <= n; i++){
            list.add(i);
            helper(res, list, n, k, i + 1);
            list.remove(list.size() - 1);
        }
    }
}