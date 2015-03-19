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
 *             if T[i] > -1 then
 *                 let m ← m + i - T[i], i ← T[i]
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
 *  //I've made this line - Youchen
 */
public class StrStr {

	public static void main(String[] args) {
		StrStr sstr = new StrStr();
		String haytack = "ABC ABCDAB ABCDABCDABDE";
		String needle = "ABCDABD";
		System.out.println(sstr.strStr(haytack, needle));
		System.out.println(sstr.strStr(haytack, "C"));
		System.out.println(sstr.strStr(haytack, "CDAB"));
		System.out.println(sstr.strStr(haytack, "CDABD"));
	}

	public int strStr(String haystack, String needle) {
		// check
		if ((haystack == null || needle == null) || needle.length() == 0
				|| haystack.length() < needle.length()) {
			return -1;
		}
		char trackFlag = needle.charAt(0); // used for partial match
		int m = 0; // (the beginning of the current match in S)
		int i = 0; // (the position of the current character in W)
		int t = -1; // backtrack position
		int t1 = -1; // backtrack previous state
		while (m + i < haystack.length()) {
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
