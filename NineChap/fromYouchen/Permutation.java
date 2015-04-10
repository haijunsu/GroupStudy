/**
 * O(n!)
 * O(n)?
 * 261 ms
 * 
 * Note:
 *      Typical problem by using the backtracking template.
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0)
            return res;
            
        helper(res, new ArrayList<Integer>(), num);
        return res;
    }
    private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path, int[] num){
        if(path.size() == num.length){
            res.add(new ArrayList<Integer>(path));
            // return;
        }
        for(int i = 0; i < num.length; i++){
            if(! path.contains(num[i])){
                path.add(num[i]);
                helper(res, path, num);
                path.remove(path.size() - 1);
            }
        }
    }
}