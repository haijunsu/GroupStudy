/**
 * Implement strStr().
 * 
 * Returns the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 * Implement with Knuth–Morris–Pratt (KMP) algorithm
 *
 * <a herf=
 * "http://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm#KMP_algorithm"
 * >KMP algorithm</a>
 * 
 * <pre>
 * algorithm kmp_search:
 *     input:
 *         an array of characters, S (the text to be searched)
 *         an array of characters, W (the word sought)
 *     output:
 *         an integer (the zero-based position in S at which W is found)
 * 
 *     define variables:
 *         an integer, m ← 0 (the beginning of the current match in S)
 *         an integer, i ← 0 (the position of the current character in W)
 *         an array of integers, T (the table, computed elsewhere)
 * 
 *     while m + i < length(S) do
 *         if W[i] = S[m + i] then
 *             if i = length(W) - 1 then
 *                 return m
 *             let i ← i + 1
 *         else
 *             if T[i] > -1 then							//Not so clear with these two lines.
 *                 let m ← m + i - T[i], i ← T[i]			//How should we initialize T ?
 *                 											//T is prepared by other codes. It shows partial match.
 *             else
 *                 let i ← 0, m ← m + 1
 *             
 *     (if we reach here, we have searched all of S unsuccessfully)
 *     return the length of S
 * 
 * 
 *
 * </pre>
 * 
 * @author suhaijun
 *
 */
public class StrStr {
	
															//I heared that it's better using JUnit test for the test cases in the interview process.
															//So, Hj and Xd, if you know how to make a JUnit test, please teach me.
															//Or else, we may learn it together.
															//Added testcase for this class 
	public static void main(String[] args) {
		StrStr sstr = new StrStr();
		String haytack = "ABC ABCDAB ABCDABCDABDE";
		String needle = "ABCDABD";
		System.out.println(sstr.strStr(haytack, needle));
		System.out.println(sstr.strStr(haytack, "C"));
		System.out.println(sstr.strStr(haytack, "CDAB"));
		System.out.println(sstr.strStr(haytack, "CDABD"));
	}
															//And here, could you make a TIME and SPACE complexity analysis?
															//		since it's been asked in the interview.
															//like O(something). I believe the KMP has linear time complexity - O(w.length + s.length)
															//How about the space?
															//In wikipedia, the space seems like O(w.length) since the T[] keeps track the backtrack
															//		position. So in your implementation, is it O(1)? Because you only allocate fix amount of vars.
															// Time complexity is O(n) and Space complexity also is O(n).
	public int strStr(String haystack, String needle) {
		// check
		if ((haystack == null || needle == null) || needle.length() == 0
				|| haystack.length() < needle.length()) {
			return -1;
		}
		char trackFlag = needle.charAt(0); // used for partial match
		int m = 0; // (the beginning of the current match in S)
		int i = 0; // (the position of the current character in W)
		int t = -1; // backtrack position									//I aknowledged that the "t" is the backtrack position,
		int t1 = -1; // backtrack previous state							//	But why we need to "t"s? Little confusing.
		while (m + i < haystack.length()) {									// I use t instead T[]. It will save space and no extract time for preparing T[]
			if (t == t1 && trackFlag == haystack.charAt(m + i)) {
				t = m + i; // update backtrack position
			}
			System.out.println("Backtrack t = " + t);
			if (needle.charAt(i) == haystack.charAt(m + i)) {
				if (i == (needle.length() - 1)) {
					return m;
				}
				++i;
			} else {
				if (t > t1) {
					// find partial match
					m = t;
					i = 0;
					t1 = t;
				} else {
					// no partial match is found and skip i length
					m = m + i + 1;
					i = 0;
				}
			}
		}

		// (if we reach here, we have searched all of S unsuccessfully)
		return -1;
	}
}
