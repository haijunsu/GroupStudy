package permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * Given a set of distinct integers, S, return all possible subsets.
 * 
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,3], a solution is:
 * 
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * </pre>
 * 
 * @author suhaijun
 *
 */
public class Subsets {

	// copy solution from NineChap website
	public List<List<Integer>> subsets(int[] num) {
		// check
		if (num == null || num.length == 0)
			return null;
		List<List<Integer>> sets = new ArrayList<List<Integer>>();
		Arrays.sort(num);
	    helper(sets, new LinkedList<Integer>(), num, 0);
		return sets;
	}
	
	private void helper(List<List<Integer>> ans, List<Integer> path, int[] num, int position) {
	    ans.add(new LinkedList<Integer>(path));
	    for (int i = position; i < num.length; i ++) {
	        path.add(num[i]);
	        helper(ans, path, num, i + 1);
	        path.remove(path.size() - 1);
	    }
	}

}
