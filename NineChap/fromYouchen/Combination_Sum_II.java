/**
 * O(n!)
 * O(n)
 * 255 ms
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {//17:48
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) return res;
        
        Arrays.sort(num);
        helper(res, new ArrayList<Integer>(), num, target, 0);
        return res;
    }
    private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, int[] num, int t, int pos){
        if(t < 0) return;
        if(t == 0){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = pos; i < num.length; i++){
            if(i > pos && num[i - 1] == num[i])
                continue;
            list.add(num[i]);
            helper(res, list, num, t - num[i], i + 1);
            list.remove(list.size() - 1);
        }
    }
}