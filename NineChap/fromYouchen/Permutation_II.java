/**
 * O(n!)?
 * O(n)?
 * 283 ms
 * 
 * Note:
 *      Have 1 more array called "visited" to keep track of which one has been added to the list already.
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {//21:06
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0)
            return res;
            
        Arrays.sort(num);
        helper(res, new ArrayList<Integer>(), num, new int[num.length]);
        return res;
    }
    private void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, int[] num, int[] visited){
        if(list.size() == num.length){
            res.add(new ArrayList<Integer>(list));
            return;   
        }
        for(int i = 0; i < num.length; i++){
            if(visited[i] == 1) continue;
            if(i > 0 && visited[i - 1] == 1 && num[i - 1] == num[i]) continue;
            
            visited[i] = 1;
            list.add(num[i]);
            
            helper(res, list, num, visited);
            
            visited[i] = 0;
            list.remove(list.size() - 1);
        }
    }
}