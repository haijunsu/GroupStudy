/**
 * Round 2 - 3/27
 * O(2^n)?
 * O(n)
 * 283 ms
 * 
 * Note:
 *      Nice done!
 *      Be careful on the result, because result is a "Set", it cannot have duplicates.
 */
public class Solution {
    // public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
    //     ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    //     if(num == null || num.length == 0)
    //         return res;
    //     Arrays.sort(num);
    //     helper(res, num, new ArrayList<Integer>(), 0);
    //     return res;
    // }
    // private void helper(ArrayList<ArrayList<Integer>> res, int[] num, ArrayList<Integer> list, int pos){
    //     if(! res.contains(new ArrayList<Integer>(list)))//just adding this line!
    //         res.add(new ArrayList<Integer>(list));
    //     for(int i = pos; i < num.length; i++){
    //         list.add(num[i]);
    //         helper(res, num, list, i + 1);
    //         list.remove(list.size() - 1);
    //     }
    // }
    
    
    
    
    //2nd round. Mar 26 20:15
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0)
            return res;

        Arrays.sort(num);
        helper(res, new ArrayList<Integer>(), num, 0);
        return res;
    }
    private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, int[] num, int pos){
        if(! res.contains(new ArrayList<Integer>(list)))
            res.add(new ArrayList<Integer>(list));
            
        for(int i = pos; i < num.length; i++){
            list.add(num[i]);
            /**
             * below you cannot write pos + 1 instead of i + 1.
             * Since each iteration, after list add num[i], you need to pass the next number close to num[i]
             *      to the next step recursion instead of the num[pos], because num[pos] will remain same
             *      in that step recursion.
             */
            helper(res, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }
}







/**
 * O(2^n)?
 * O(n)
 * 283 ms
 * 
 * Note:
 *      Nice done!
 *      Be careful on the result, because result is a "Set", it cannot have duplicates.
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0)
            return res;
        Arrays.sort(num);
        helper(res, num, new ArrayList<Integer>(), 0);
        return res;
    }
    private void helper(ArrayList<ArrayList<Integer>> res, int[] num, ArrayList<Integer> list, int pos){
        if(! res.contains(new ArrayList<Integer>(list)))//just adding this line!
            res.add(new ArrayList<Integer>(list));
        for(int i = pos; i < num.length; i++){
            list.add(num[i]);
            helper(res, num, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}